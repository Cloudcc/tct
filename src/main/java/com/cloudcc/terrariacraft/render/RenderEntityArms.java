package com.cloudcc.terrariacraft.render;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.entity.EntityArms;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class RenderEntityArms extends RenderLiving {
    private static final ResourceLocation EntityArmsTextures = new ResourceLocation(Reference.MOD_ID + ":textures/entity/doctor_trivial.png");

    public RenderEntityArms(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }

    protected ResourceLocation getEntityArmsTextures(EntityArms entity)
    {
        return EntityArmsTextures;
    }



    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityArmsTextures((EntityArms)entity);
    }
}

