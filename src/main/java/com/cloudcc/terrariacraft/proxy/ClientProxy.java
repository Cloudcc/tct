package com.cloudcc.terrariacraft.proxy;


import com.cloudcc.terrariacraft.Terrariacraft;
import com.cloudcc.terrariacraft.entity.EntityArms;
import com.cloudcc.terrariacraft.init.ModBlocks;
import com.cloudcc.terrariacraft.init.ModItems;
import com.cloudcc.terrariacraft.model.DoctorTrivial;
import com.cloudcc.terrariacraft.render.RenderEntityArms;
import com.cloudcc.terrariacraft.render.TileEntityCookingPotRenderer;
import com.cloudcc.terrariacraft.render.TileEntityStorageBlockRenderer;
import com.cloudcc.terrariacraft.tile.TileEntityStorageBlock;
import com.cloudcc.terrariacraft.tile.blocks.furnace.TEYellowFurnace;
import com.cloudcc.terrariacraft.tile.blocks.furnace.TileEntityCookingPot;
import com.cloudcc.terrariacraft.world.WorldGen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Tobias on 07.08.2016.
 */
public class ClientProxy implements CommonProxy {

    @Override
    public void init(){
        ModItems.registerRenders();
        ModBlocks.registerRenders();


        GameRegistry.registerWorldGenerator(new WorldGen(), 0);
        GameRegistry.registerTileEntity(TEYellowFurnace.class,"YF");
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStorageBlock.class, new TileEntityStorageBlockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCookingPot.class, new TileEntityCookingPotRenderer());

        RenderingRegistry.registerEntityRenderingHandler(EntityArms.class, new RenderEntityArms(Minecraft.getMinecraft().getRenderManager(), new DoctorTrivial(),0));

    }


//

}
