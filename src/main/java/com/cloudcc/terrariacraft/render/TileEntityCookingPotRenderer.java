package com.cloudcc.terrariacraft.render;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.model.ModelCookingPot;
import com.cloudcc.terrariacraft.tile.TileEntityStorageBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Tobias on 14.08.2016.
 */
public class TileEntityCookingPotRenderer extends TileEntitySpecialRenderer {

    ResourceLocation textures = new ResourceLocation(Reference.MOD_ID + ":" + "textures/entity/cooking_pot.png");
    private ModelCookingPot model;

    public TileEntityCookingPotRenderer(){
        this.model = new ModelCookingPot();
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5f, (float)y + 1.5f, (float)z + 0.5f);
        GL11.glRotatef(180,0f,0f,1f);
        this.bindTexture(textures);
        GL11.glPushMatrix();
        this.model.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
