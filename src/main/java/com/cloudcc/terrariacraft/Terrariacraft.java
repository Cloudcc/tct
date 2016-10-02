package com.cloudcc.terrariacraft;

import com.cloudcc.terrariacraft.entity.EntityArms;
import com.cloudcc.terrariacraft.gui.CookingPotGuiHandler;
import com.cloudcc.terrariacraft.gui.StorageBlockGuiHandler;
import com.cloudcc.terrariacraft.handler.MobDropsHandler;
import com.cloudcc.terrariacraft.init.ModEntities;
import com.cloudcc.terrariacraft.init.ModBlocks;
import com.cloudcc.terrariacraft.init.ModCrafting;
import com.cloudcc.terrariacraft.init.ModItems;
import com.cloudcc.terrariacraft.init.ModSounds;
import com.cloudcc.terrariacraft.proxy.CommonProxy;
import com.cloudcc.terrariacraft.proxy.GuiProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;


@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class Terrariacraft
{


    @Mod.Instance
    public static Terrariacraft instance;


    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    public static final CreativeTabs CREATIVE_TAB = new TerrariacraftTab();

    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        System.out.println("hallo Minecraft");
        ModItems.init();
        ModItems.register();


        ModBlocks.init();
        ModBlocks.register();
        ModSounds.registerSounds();
        ModEntities.registerEntity();
        ModCrafting.register();

        EntityRegistry.addSpawn(EntityArms.class, 100, 10,20, EnumCreatureType.CREATURE, Biome.REGISTRY.getObjectById(1));

        NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MOD_ID,new StorageBlockGuiHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MOD_ID, new CookingPotGuiHandler());

        MinecraftForge.EVENT_BUS.register(new MobDropsHandler());










    }

    @EventHandler
    public void Init(FMLInitializationEvent event)
    {
        proxy.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(Terrariacraft.instance, new GuiProxy());

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }


}
