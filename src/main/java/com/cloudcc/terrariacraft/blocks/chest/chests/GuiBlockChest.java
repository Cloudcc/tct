package com.cloudcc.terrariacraft.blocks.chest.chests;


import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * Created by hi112 on 29.08.2016.
 */
public class GuiBlockChest extends GuiContainer {

    private TileEntityBlockChest te;
    private IInventory playerInv;

    public GuiBlockChest(IInventory playerInv, TileEntityBlockChest te){
        super(new ContainerBlockChest(playerInv,te));

        this.te = te;
        this.playerInv = playerInv;

        this.xSize = 176;
        this.ySize = 122;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("bags:textures/gui/container/minichest.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        String s = this.te.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s,88 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 30, 4210752);
    }
}
