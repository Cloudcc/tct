package com.cloudcc.terrariacraft.tile.blocks.furnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumSkyBlock;

import java.util.Arrays;

public class TileEntityCookingPot extends TileEntity implements IInventory, ITickable {

    public static final int fuel_slots = 4;
    public static final int input_slots = 5;
    public static final int output_slots = 5;
    public static final int total_slots = fuel_slots + input_slots + output_slots;

    public static final int first_fuel_slot = 0;
    public static final int first_input_slot = first_fuel_slot + fuel_slots;
    public static final int first_output_slot = first_input_slot + input_slots;

    private ItemStack[] itemStacks = new ItemStack[total_slots];

    private int [] burnTimeRemaining = new int[fuel_slots];
    private int [] burnTimeInitialValue = new int[fuel_slots];

    private short cookTime;
    private static final short cook_time_for_completion = 200;
    private int cachedNumberOfBurningSlots = -1;

    public double fractionOfFuelRemaining(int fuelSlot) {
        if(burnTimeInitialValue[fuelSlot] <= 0) {
            return 0;
        }
        double fraction = burnTimeRemaining[fuelSlot] / (double)burnTimeInitialValue[fuelSlot];
        return MathHelper.clamp_double(fraction, 0.0, 1.0);
    }

    public int secondsOfFuelRemaining(int fuelSlot) {
        if(burnTimeRemaining[fuelSlot] <= 0) {
            return 0;
        }
        return burnTimeRemaining[fuelSlot] / 20;
    }

    public int numberOfBurningFuelSlots() {
        int burningCount = 0;
        for(int burnTime : burnTimeRemaining) {
            if(burnTime > 0) {
                ++burningCount;
            }
        }
        return burningCount;
    }

    public double fractionOfCookTimeComplete() {
        double fraction = cookTime/ (double)cook_time_for_completion;
        return MathHelper.clamp_double(fraction, 0.0, 1.0);
    }

    @Override
    public void update() {
        if(canSmelt()) {
            int numberOfFuelBurning = burnFuel();

            if(numberOfFuelBurning > 0) {
                cookTime += numberOfFuelBurning;
            } else {
                cookTime -=2;
            }

            if(cookTime < 0) {
                cookTime = 0;
            }

            if(cookTime >= cook_time_for_completion) {
                smeltItem();
                cookTime = 0;
            }
        } else {
            cookTime = 0;
        }

        int numberBurning = numberOfBurningFuelSlots();
        if(cachedNumberOfBurningSlots != numberBurning) {
            cachedNumberOfBurningSlots = numberBurning;
            if(worldObj.isRemote) {
                worldObj.markBlockRangeForRenderUpdate(pos, pos);
            }
            worldObj.checkLightFor(EnumSkyBlock.BLOCK, pos);
        }
    }

    private int burnFuel() {
        int burningCount = 0;
        boolean inventoryChanged = false;

        for(int i = 0; i < fuel_slots; i++) {
            int fuelSlotNumber = i + first_fuel_slot;
            if(burnTimeRemaining[i] > 0) {
                --burnTimeRemaining[i];
                ++burningCount;
            }
            if(burnTimeRemaining[i] == 0) {
                if(itemStacks[fuelSlotNumber] != null && getItemBurnTime(itemStacks[fuelSlotNumber]) > 0) {
                    burnTimeRemaining[i] = burnTimeInitialValue[i] = getItemBurnTime(itemStacks[fuelSlotNumber]);
                    --itemStacks[fuelSlotNumber].stackSize;
                    ++burningCount;
                    inventoryChanged = true;

                    if(itemStacks[fuelSlotNumber].stackSize == 0) {
                        itemStacks[fuelSlotNumber] = itemStacks[fuelSlotNumber].getItem().getContainerItem(itemStacks[fuelSlotNumber]);
                    }
                }
            }
        }
        if(inventoryChanged) {
            markDirty();
        }
        return burningCount;
    }

    private boolean canSmelt() {
        return smeltItem(false);
    }

    private void smeltItem() {
        smeltItem(true);
    }

    private boolean smeltItem(boolean performSmelt) {
        Integer firstSuitableInputSlot = null;
        Integer firstSuitableOutputSlot = null;
        ItemStack result = null;

        for(int inputSlot = first_input_slot; inputSlot < first_input_slot + input_slots; inputSlot++) {
            if(itemStacks[inputSlot] != null) {
                result = getSmeltingResultForItem(itemStacks[inputSlot]);
                if(result != null) {
                    for(int outputSlot = first_output_slot; outputSlot < first_output_slot + output_slots; outputSlot++) {
                        ItemStack outputStack = itemStacks[outputSlot];
                        if(outputStack == null) {
                            firstSuitableInputSlot = inputSlot;
                            firstSuitableOutputSlot = outputSlot;
                            break;
                        }
                        if(outputStack.getItem() == result.getItem() && (!outputStack.getHasSubtypes() || outputStack.getMetadata() == outputStack.getMetadata()) && ItemStack.areItemStackTagsEqual(outputStack, result)) {
                            int combinedSize = itemStacks[outputSlot].stackSize + result.stackSize;
                            if(combinedSize <= getInventoryStackLimit() && combinedSize <= itemStacks[outputSlot].getMaxStackSize()) {
                                firstSuitableInputSlot = inputSlot;
                                firstSuitableOutputSlot = outputSlot;
                                break;
                            }
                        }
                    }
                    if(firstSuitableInputSlot != null) {
                        break;
                    }
                }
            }
        }
        if(firstSuitableInputSlot == null) {
            return false;
        }
        if(!performSmelt) {
            return true;
        }
        itemStacks[firstSuitableInputSlot].stackSize--;
        if(itemStacks[firstSuitableInputSlot].stackSize <= 0) {
            itemStacks[firstSuitableInputSlot] = null;
        }
        if(itemStacks[firstSuitableOutputSlot] == null) {
            itemStacks[firstSuitableOutputSlot] = result.copy();
        } else {
            itemStacks[firstSuitableOutputSlot].stackSize += result.stackSize;
        }
        markDirty();
        return true;
    }

    public static ItemStack getSmeltingResultForItem(ItemStack stack) {
        return FurnaceRecipes.instance().getSmeltingResult(stack);
    }

    public static short getItemBurnTime(ItemStack stack) {
        int burntime = TileEntityFurnace.getItemBurnTime(stack);
        return (short)MathHelper.clamp_int(burntime, 0, Short.MAX_VALUE);
    }

    @Override
    public int getSizeInventory() {
        return itemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return itemStacks[i];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int count) {
        ItemStack itemStackInSlot = getStackInSlot(slotIndex);
        if(itemStackInSlot == null) {
            return null;
        }
        ItemStack itemStackRemoved;
        if(itemStackInSlot.stackSize <= count) {
            itemStackRemoved = itemStackInSlot;
            setInventorySlotContents(slotIndex, null);
        } else {
            itemStackRemoved = itemStackInSlot.splitStack(count);
            if(itemStackInSlot.stackSize == 0) {
                setInventorySlotContents(slotIndex, null);
            }
        }
        markDirty();
        return itemStackRemoved;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack stack) {
        itemStacks[slotIndex] = stack;
        if(stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if(this.worldObj.getTileEntity(this.pos) != this) {
            return false;
        }
        final double x_center_offset = 0.5;
        final double y_center_offset = 0.5;
        final double z_center_offset = 0.5;
        final double maximum_distance_sq = 8.0 * 8.0;
        return player.getDistanceSq(pos.getX() + x_center_offset, pos.getY() + y_center_offset, pos.getZ() + z_center_offset) < maximum_distance_sq;
    }

    public static boolean isItemValidForFuelSlot(ItemStack stack) {
        return true;
    }

    public static boolean isItemValidForInputSlot(ItemStack stack) {
        return true;
    }

    public static boolean isItemValidForOutputSlot(ItemStack stack) {
        return true;
    }



    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound) {
        super.writeToNBT(parentNBTTagCompound);

        NBTTagList dataForAllSlots = new NBTTagList();
        for(int i = 0; i < this.itemStacks.length; ++i) {
            if(this.itemStacks[i] != null) {
                NBTTagCompound dataForThisSlot = new NBTTagCompound();
                dataForThisSlot.setByte("Slot", (byte)i);
                this.itemStacks[i].writeToNBT(dataForThisSlot);
                dataForAllSlots.appendTag(dataForThisSlot);
            }
        }
        parentNBTTagCompound.setTag("Items", dataForAllSlots);
        parentNBTTagCompound.setShort("CookTime", cookTime);
        parentNBTTagCompound.setTag("burnTimeRemaining", new NBTTagIntArray(burnTimeRemaining));
        parentNBTTagCompound.setTag("burnTimeInitial", new NBTTagIntArray(burnTimeInitialValue));
        return parentNBTTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        final byte nbt_type_compound = 10;
        NBTTagList dataForAllSlots= nbtTagCompound.getTagList("Items", nbt_type_compound);
        Arrays.fill(itemStacks, null);
        for(int i = 0; i < dataForAllSlots.tagCount(); ++i) {
            NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
            byte slotNumber = dataForOneSlot.getByte("Slot");
            if(slotNumber >= 0 && slotNumber < this.itemStacks.length) {
                this.itemStacks[slotNumber] = ItemStack.loadItemStackFromNBT(dataForOneSlot);
            }
        }
        cookTime = nbtTagCompound.getShort("CookTime");
        burnTimeRemaining = Arrays.copyOf(nbtTagCompound.getIntArray("burnTimeRemaining"), fuel_slots);
        burnTimeInitialValue = Arrays.copyOf(nbtTagCompound.getIntArray("burnTimeInitial"), fuel_slots);
        cachedNumberOfBurningSlots = -1;
    }


    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        final int metadata = 0;
        return new SPacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public void clear() {
        Arrays.fill(itemStacks, null);
    }

    @Override
    public String getName() {
        return "container.tile_entity_cooking_pot.name";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    private static final byte cook_field_id = 0;
    private static final byte first_burn_time_remaining_field_id = 1;
    private static final byte first_burn_time_initial_field_id = first_burn_time_remaining_field_id + (byte)fuel_slots;
    private static final byte number_of_fields = first_burn_time_initial_field_id + (byte)fuel_slots;

    @Override
    public int getField(int id) {
        if(id == cook_field_id) {
            return cookTime;
        }
        if(id >= first_burn_time_remaining_field_id && id < first_burn_time_remaining_field_id + fuel_slots) {
            return burnTimeRemaining[id - first_burn_time_remaining_field_id];
        }
        if(id >= first_burn_time_initial_field_id && id < first_burn_time_initial_field_id + fuel_slots) {
            return burnTimeInitialValue[id - first_burn_time_initial_field_id];
        }
        System.err.println("Invalid field ID in TileInventorySmelting.getField:" + id);
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        if(id == cook_field_id) {
            cookTime = (short)value;
        } else if (id >= first_burn_time_remaining_field_id && id < first_burn_time_remaining_field_id + fuel_slots) {
            burnTimeRemaining[id - first_burn_time_remaining_field_id] = value;
        } else if (id >= first_burn_time_initial_field_id && id < first_burn_time_initial_field_id + fuel_slots) {
            burnTimeInitialValue[id - first_burn_time_initial_field_id] = value;
        } else {
            System.err.println("Invalid field ID in TileInventorySmelting.getField:" + id);
        }
    }

    @Override
    public int getFieldCount() {
        return number_of_fields;
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack removeStackFromSlot(int slotIndex) {
        ItemStack stack = getStackInSlot(slotIndex);
        if(stack != null) {
            setInventorySlotContents(slotIndex, null);
        }
        return stack;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }
}


