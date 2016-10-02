package com.cloudcc.terrariacraft.gui;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.tile.blocks.furnace.TileEntityCookingPot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Tobias on 15.08.2016.
 */
@SideOnly(Side.CLIENT)
public class GuiCookingPot extends GuiContainer{

    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":" + "textures/gui/container/cooking_pot_gui.png");
    private TileEntityCookingPot entity;

    public GuiCookingPot(InventoryPlayer player, TileEntityCookingPot entity) {
        super(new ContainerCookingPot(player, entity));

        xSize = 176;
        ySize = 207;

        this.entity = entity;
    }

    final int cook_bar_xpos = 49;
    final int cook_bar_ypos = 60;
    final int cook_bar_icon_u = 0;
    final int cook_bar_icon_v = 207;
    final int cook_bar_width = 80;
    final int cook_bar_height = 17;

    final int flame_xpos = 54;
    final int flame_ypos = 80;
    final int flame_icon_u = 176;
    final int flame_icon_v = 0;
    final int flame_width = 14;
    final int flame_height = 14;
    final int flame_x_spacing = 18;

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);
        double cookProgress = entity.fractionOfCookTimeComplete();
        this.drawTexturedModalRect(this.guiLeft + cook_bar_xpos, this.guiTop + cook_bar_ypos, cook_bar_icon_u, cook_bar_icon_v, (int)(cookProgress * cook_bar_width), cook_bar_height);

        for(int i = 0; i < entity.fuel_slots; ++i) {
            double burnRemaining = entity.fractionOfFuelRemaining(i);
            int yOffset = (int)((1.0 - burnRemaining) * flame_height);
            this.drawTexturedModalRect(guiLeft + flame_xpos + flame_x_spacing * i , guiTop + flame_ypos + yOffset, flame_icon_u, flame_icon_v + yOffset, flame_width, flame_height - yOffset);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        final int label_xpos = 5;
        final int label_ypos = 5;
        fontRendererObj.drawString(entity.getDisplayName().getUnformattedText(), label_xpos, label_ypos, Color.DARK_GRAY.getRGB());
        List<String> text = new ArrayList<String>();

        if(isInRect(guiLeft + cook_bar_xpos, guiTop + cook_bar_ypos, cook_bar_width, cook_bar_height, mouseX, mouseY)) {
            text.add("Progress:");
            int cookPercentage = (int)(entity.fractionOfCookTimeComplete() * 100);
            text.add(cookPercentage + "%");
        }

        for(int i = 0; i < entity.fuel_slots; ++i) {
            if(isInRect(guiLeft + flame_xpos + flame_x_spacing * i, guiTop + flame_ypos, flame_width, flame_height, mouseX, mouseY)) {
                text.add("Fuel Time:");
                text.add(entity.secondsOfFuelRemaining(i) + "s");
            }
        }

        if(!text.isEmpty()) {
            drawHoveringText(text, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
        }
    }

    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }
}