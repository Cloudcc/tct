package com.cloudcc.terrariacraft.gui;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.tile.TileEntityStorageBlock;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Tobias on 14.08.2016.
 */
public class GuiStorageBlock extends GuiContainer {

    private IInventory playerInv;
    private TileEntityStorageBlock te;

    public GuiStorageBlock(IInventory playerInv, TileEntityStorageBlock te){
        super(new ContainerStorageBlock(playerInv,te));

        this.playerInv = playerInv;
        this.te = te;

        this.xSize = 176;
        this.ySize = 166;

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":" + "textures/gui/container/storage_block_gui.png"));
        this.drawTexturedModalRect(this.guiLeft,this.guiTop,0,0,this.xSize,this.ySize);

    }


    protected void drawGuiContainerForeGroundLayer(int mouseX, int mouseY){

        String s = this.te.getDisplayName().getUnformattedComponentText();
        this.fontRendererObj.drawString(s, 88- this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(),8,72,4210752);
    }
}
