package com.cloudcc.terrariacraft.handler;


import com.cloudcc.terrariacraft.blocks.chest.chests.ContainerBlockChest;
import com.cloudcc.terrariacraft.blocks.chest.chests.GuiBlockChest;
import com.cloudcc.terrariacraft.blocks.chest.chests.TileEntityBlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by hi112 on 29.08.2016.
 */
public class GuiHandler implements IGuiHandler {

    public  static final int Block_CHEST = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if(ID == Block_CHEST){
            return new ContainerBlockChest(player.inventory, (TileEntityBlockChest) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == Block_CHEST){
            return new GuiBlockChest(player.inventory, (TileEntityBlockChest) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}
