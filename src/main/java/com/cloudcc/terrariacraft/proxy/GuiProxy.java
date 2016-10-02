package com.cloudcc.terrariacraft.proxy;

import com.cloudcc.terrariacraft.blocks.chest.testcontainer.TestContainer;
import com.cloudcc.terrariacraft.blocks.chest.testcontainer.TestContainerGui;
import com.cloudcc.terrariacraft.blocks.chest.testcontainer.TileEntityTestContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by hi112 on 26.08.2016.
 */
public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TileEntityTestContainer){
            return new TestContainer(player.inventory, (TileEntityTestContainer) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world. getTileEntity(pos);
        if(te instanceof TileEntityTestContainer){
            TileEntityTestContainer containerTileEntity = (TileEntityTestContainer) te;
            return new TestContainerGui(containerTileEntity, new TestContainer(player.inventory, containerTileEntity));
        }
        return null;
    }
}
