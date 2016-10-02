package com.cloudcc.terrariacraft.blocks.chest.testcontainer;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by hi112 on 26.08.2016.
 */
public class TestcontainerBlock extends BlockContainer implements ITileEntityProvider {

    public static final int GUI_ID = 1;

    public TestcontainerBlock(){
        super(Material.ROCK);
        setUnlocalizedName(Reference.MOD_ID + ".testcontainerblock");
        setRegistryName("testcontainerblock");
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());

        setCreativeTab(Terrariacraft.CREATIVE_TAB);
    }

    @SideOnly(Side.CLIENT)
    public void initModel(){
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        //only execute on server
        if(worldIn.isRemote){
            return true;
        }
        TileEntity te = worldIn.getTileEntity(pos);
        if(!(te instanceof TileEntityTestContainer)){
            return false;
        }

        playerIn.openGui(Terrariacraft.instance, GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {


        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTestContainer();
    }



}
