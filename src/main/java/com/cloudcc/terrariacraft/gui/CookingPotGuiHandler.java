package com.cloudcc.terrariacraft.gui;

import com.cloudcc.terrariacraft.tile.blocks.furnace.TileEntityCookingPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Tobias on 15.08.2016.
 */
public class CookingPotGuiHandler implements IGuiHandler {

    public static final int COOKING_POT_GUI = 34;
    public static int getGUIID(){
        return COOKING_POT_GUI;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == COOKING_POT_GUI)
            return new ContainerCookingPot(player.inventory,(TileEntityCookingPot)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == COOKING_POT_GUI)
            return new GuiCookingPot(player.inventory, (TileEntityCookingPot)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
