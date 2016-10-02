package com.cloudcc.terrariacraft.blocks.furnace;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import com.cloudcc.terrariacraft.gui.CookingPotGuiHandler;
import com.cloudcc.terrariacraft.tile.blocks.furnace.TileEntityCookingPot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Tobias on 14.08.2016.
 */
public class BlockCookingPot extends Block implements ITileEntityProvider {

    public BlockCookingPot() {
        super(Material.ROCK);
        setUnlocalizedName(Reference.TerrariacraftBlocks.COOKING_POT.getUnlocalizedName());
        setRegistryName(Reference.TerrariacraftBlocks.COOKING_POT.getRegistryName());

        setHardness(1.0F);
        setCreativeTab(Terrariacraft.CREATIVE_TAB);

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCookingPot();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote){
            return true;
        }

        playerIn.openGui(Reference.MOD_ID, CookingPotGuiHandler.getGUIID(),worldIn,pos.getX(),pos.getY(),pos.getZ());
        return true;
    }
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state){
        TileEntityCookingPot te = (TileEntityCookingPot) world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world,pos,te);
        super.breakBlock(world,pos,state);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos){
        TileEntityCookingPot te = (TileEntityCookingPot)world.getTileEntity(pos);
        int burningSlots = te.numberOfBurningFuelSlots();
        burningSlots = MathHelper.clamp_int(burningSlots,0,4);
        return getDefaultState().withProperty(burning_sides, burningSlots);
    }

    public static final PropertyInteger burning_sides = PropertyInteger.create("burning_sides", 0, 4);

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state){
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {burning_sides});
    }

    private static final int four_side_light_value = 15;
    private static final int one_side_light = 8;

    @Override
    public int getLightValue(IBlockState state){
        int lightValue = 0;
        int burningSides = (Integer)state.getValue(burning_sides);

        if(burningSides == 0){
            lightValue = 0;
        } else {
            lightValue = one_side_light + (int)((four_side_light_value - one_side_light) / (4.0 - 1.0) * burningSides);
        }
        lightValue = MathHelper.clamp_int(lightValue,0,four_side_light_value);
        return lightValue;
    }

    @Override
    public boolean isFullCube(IBlockState state){
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state){
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }



}
