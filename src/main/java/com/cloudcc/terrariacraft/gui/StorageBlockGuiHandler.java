package com.cloudcc.terrariacraft.gui;

import com.cloudcc.terrariacraft.tile.TileEntityStorageBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Tobias on 14.08.2016.
 */
public class StorageBlockGuiHandler implements IGuiHandler {

    public static final int STORAGE_BLOCK_GUI = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if(ID == STORAGE_BLOCK_GUI)
            return new ContainerStorageBlock(player.inventory, ((TileEntityStorageBlock)world.getTileEntity(new BlockPos(x,y,z))));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if(ID == STORAGE_BLOCK_GUI)
            return new GuiStorageBlock(player.inventory,(TileEntityStorageBlock)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
