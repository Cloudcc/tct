package com.cloudcc.terrariacraft.handler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.properties.EntityProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

/**
 * Created by hi112 on 23.08.2016.
 */
public class MultiblockHandler {

    static ArrayList<IMultiblock> multiblocks = new ArrayList<IMultiblock>();

    public static void registerMultiblock(IMultiblock multiblock)
    {
        multiblocks.add(multiblock);
    }

    public static ArrayList<IMultiblock> getMultiblocks()
    {
        return multiblocks;
    }

    public interface IMultiblock
    {
        String getUniqueName();

        boolean isBlockTrigger(IBlockState state);

        boolean createStructure(World world, BlockPos pos, EnumFacing side, EntityPlayer player);

        ItemStack[][][] getStructureManual();

        ItemStack[] getTotalMaterials();

        @SideOnly(Side.CLIENT)
        boolean overwriteBlockRender(ItemStack stack, int iterator);

        float getManualScale();

        @SideOnly(Side.CLIENT)
        boolean canRenderFormedStructure();

        @SideOnly(Side.CLIENT)
        void renderFormedStructure();
    }
}
