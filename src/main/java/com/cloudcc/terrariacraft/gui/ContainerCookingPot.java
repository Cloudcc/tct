package com.cloudcc.terrariacraft.gui;

import com.cloudcc.terrariacraft.tile.blocks.furnace.TileEntityCookingPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Tobias on 15.08.2016.
 */
public class ContainerCookingPot extends Container {

    private TileEntityCookingPot tileEntityCookingPot;

    private int [] cachedFields;

    private final int hotbar = 9;
    private final int player_inventory_row = 3;
    private final int player_inventory_column = 9;
    private final int player_inventory = player_inventory_column * player_inventory_row;
    private final int vanilla_slots = hotbar + player_inventory;

    public final int fuel_slots = 4;
    public final int input_slots = 5;
    public final int output_slots = 5;
    public final int furnace_slots = fuel_slots + input_slots + output_slots;

    private final int vanilla_first_index = 0;
    private final int first_fuel_index = vanilla_first_index + vanilla_slots;
    private final int first_input_index = first_fuel_index + fuel_slots;
    private final int first_output_index = first_fuel_index + input_slots;

    private final int first_fuel_slot = 0;
    private final int first_input_slot = first_fuel_slot + fuel_slots;
    private final int first_output_slot = first_input_slot + input_slots;

    public ContainerCookingPot(InventoryPlayer player, TileEntityCookingPot tileentity) {
        this.tileEntityCookingPot = tileentity;

        final int slot_x_spacing = 18;
        final int slot_y_spacing = 18;
        final int hotbar_xpos = 8;
        final int hotbar_ypos = 183;

        //Hotbar
        for (int x = 0; x < hotbar; x++) {
            int slotNumber = x;
            addSlotToContainer(new Slot(player, slotNumber, hotbar_xpos + slot_x_spacing * x, hotbar_ypos));
        }

        final int player_inventory_xpos = 8;
        final int player_inventory_ypos = 125;
        //Player Inventory
        for (int y = 0; y < player_inventory_row; y++) {
            for (int x = 0; x < player_inventory_column; x++) {
                int SlotNumber = hotbar + y * player_inventory_column + x;
                int xpos = player_inventory_xpos + x * slot_x_spacing;
                int ypos = player_inventory_ypos + y * slot_y_spacing;
                addSlotToContainer(new Slot(player, SlotNumber, xpos, ypos));
            }
        }

        final int fuel_slots_xpos = 53;
        final int fuel_slots_ypos = 96;
        //Fuel Slots
        for (int x = 0; x < fuel_slots; x++) {
            int slotNumber = x + first_fuel_slot;
            addSlotToContainer(new SlotFuel(player, slotNumber, fuel_slots_xpos + slot_x_spacing * x, fuel_slots_ypos));
        }

        final int input_slots_xpos = 26;
        final int input_slots_ypos = 24;
        //Input Slots
        for (int y = 0; y < input_slots; y++) {
            int SlotNumber = y + first_input_slot;
            addSlotToContainer(new SlotSmeltableInput(player, SlotNumber, input_slots_xpos, input_slots_ypos + slot_y_spacing * y));
        }

        final int output_slots_xpos = 134;
        final int output_slots_ypos = 24;
        //Output Slots
        for (int y = 0; y < output_slots; y++) {
            int slotNumber = y + first_output_slot;
            addSlotToContainer(new SlotOutput(player, slotNumber, output_slots_xpos, output_slots_ypos + slot_y_spacing * y));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn){
        return this.tileEntityCookingPot.isUseableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex){
        Slot sourceSlot = (Slot)inventorySlots.get(sourceSlotIndex);
        if(sourceSlot == null || !sourceSlot.getHasStack()){
            return null;
        }
        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if(sourceSlotIndex >= vanilla_first_index && sourceSlotIndex < vanilla_first_index + vanilla_slots){
            if(!mergeItemStack(sourceStack, first_input_index, first_fuel_index + input_slots, false)){
                return null;
            } else if(this.tileEntityCookingPot.getItemBurnTime(sourceStack) > 0){
                if(!mergeItemStack(sourceStack, first_fuel_index, first_fuel_index + fuel_slots, true)){
                    return null;
                }
            } else {
                return null;
            }
        } else if(sourceSlotIndex >= first_fuel_index && sourceSlotIndex < first_fuel_index + furnace_slots){
            if(!mergeItemStack(sourceStack, vanilla_first_index, vanilla_first_index + vanilla_slots, false)){
                return null;
            }
        } else {
            System.err.print("Invalid slotIndex:" + sourceSlotIndex);
            return null;
        }

        if(sourceStack.stackSize == 0){
            sourceSlot.putStack(null);
        } else {
            sourceSlot.onSlotChanged();
        }

        sourceSlot.onPickupFromSlot(player,sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public void detectAndSendChanges(){
        super.detectAndSendChanges();

        boolean allFieldsHaveChanged = false;
        boolean fieldHasChanged [] = new boolean[this.tileEntityCookingPot.getFieldCount()];
        if(cachedFields == null){
            cachedFields = new int[this.tileEntityCookingPot.getFieldCount()];
            allFieldsHaveChanged = true;
        }
        for(int i = 0; i < cachedFields.length; ++i){
            if(allFieldsHaveChanged || cachedFields[i] != this.tileEntityCookingPot.getField(i)){
                cachedFields[i] = this.tileEntityCookingPot.getField(i);
                fieldHasChanged[i] = true;
            }
        }
        for(int i = 0; i < this.listeners.size(); ++i){
            IContainerListener iCrafting = (IContainerListener) this.listeners.get(i);
            for(int fieldID = 0; fieldID < this.tileEntityCookingPot.getFieldCount(); ++fieldID){
                if(fieldHasChanged[fieldID]){
                    iCrafting.sendProgressBarUpdate(this,fieldID,cachedFields[fieldID]);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data){
        this.tileEntityCookingPot.setField(id, data);
    }

    public class SlotFuel extends Slot {
        public SlotFuel(IInventory inventory,int index, int xpos, int ypos){
            super(inventory,index,xpos,ypos);
        }

        @Override
        public boolean isItemValid(ItemStack stack){
            return TileEntityCookingPot.isItemValidForFuelSlot(stack);
        }
    }

    public class SlotSmeltableInput extends Slot {
        public SlotSmeltableInput(IInventory inventory, int index, int xpos, int ypos){
            super(inventory,index,xpos,ypos);
        }

        @Override
        public boolean isItemValid(ItemStack stack){
            return TileEntityCookingPot.isItemValidForInputSlot(stack);
        }
    }

    public class SlotOutput extends Slot {
        public SlotOutput(IInventory inventory, int index, int xpos, int ypos){
            super(inventory,index,xpos,ypos);
        }

        @Override
        public boolean isItemValid(ItemStack stack){
            return TileEntityCookingPot.isItemValidForOutputSlot(stack);
        }

    }




}
