package com.cloudcc.terrariacraft.blocks.chest;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import com.cloudcc.terrariacraft.gui.StorageBlockGuiHandler;
import com.cloudcc.terrariacraft.tile.TileEntityStorageBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Tobias on 14.08.2016.
 */
public class BlockStorageBlock extends Block implements ITileEntityProvider {

    public BlockStorageBlock(){
        super(Material.WOOD);
        setUnlocalizedName(Reference.TerrariacraftBlocks.STORAGE_BLOCK.getUnlocalizedName());
        setRegistryName(Reference.TerrariacraftBlocks.STORAGE_BLOCK.getRegistryName());
        setHardness(2.0F);
        setResistance(6.0F);
        setCreativeTab(Terrariacraft.CREATIVE_TAB);
        setHarvestLevel("axe",0);

    }
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            player.openGui(Reference.MOD_ID, StorageBlockGuiHandler.STORAGE_BLOCK_GUI, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public int getRenderType() {
        return -1;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityStorageBlock te = (TileEntityStorageBlock)world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, te);
        super.breakBlock(world, pos, state);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack) {
        if(stack.hasDisplayName()) {
            ((TileEntityStorageBlock)world.getTileEntity(pos)).setCustomName(stack.getDisplayName());
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityStorageBlock();
    }
}