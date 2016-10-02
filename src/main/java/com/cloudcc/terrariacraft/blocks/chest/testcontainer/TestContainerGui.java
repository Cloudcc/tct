package com.cloudcc.terrariacraft.blocks.chest.testcontainer;

import com.cloudcc.terrariacraft.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by hi112 on 26.08.2016.
 */
public class TestContainerGui extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/testcontainer.png");

    public TestContainerGui(TileEntityTestContainer tileEntity, TestContainer container){
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
