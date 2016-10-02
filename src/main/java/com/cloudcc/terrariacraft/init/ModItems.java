package com.cloudcc.terrariacraft.init;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.items.*;
import com.cloudcc.terrariacraft.items.armor.ItemAdamantiumArmor;
import com.cloudcc.terrariacraft.items.armor.ItemMithrilArmor;
import com.cloudcc.terrariacraft.items.weapons.ItemAdamantitSword;
import com.cloudcc.terrariacraft.items.weapons.ItemMithrilSword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Tobias on 07.08.2016.
 */
public class ModItems {


    //weapons

    public static Item.ToolMaterial MITHRIL = EnumHelper.addToolMaterial("mithril", 3, 1000, 15.0f,5.0f,30);
    public static Item mithril_sword;

    public static Item.ToolMaterial ADAMANTIT = EnumHelper.addToolMaterial("adamantit",3,2000,16.0f,7.0f,45);
    public static Item adamantit_sword;


    //armor
    //public static ItemModArmor.ArmorMaterial ArmorMaterial = EnumHelper.addArmorMaterial("ArmorMaterial","Mod_ID:armor",durability, new int[] { helm,chest,leggings,boots} ReductionArmor,enchantability, SoundonEquip SoundEvents.,toughness)
    public static ItemAdamantiumArmor.ArmorMaterial AdamantiumArmor = EnumHelper.addArmorMaterial("AdamantiumArmor","terrariacraft:adamantium",100, new int[] { 5,8,6,3}, 100, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10);
    public static Item adamantium_helmet;
    public static Item adamantium_chestplate;
    public static Item adamantium_leggings;
    public static Item adamantium_boots;

    public static ItemMithrilArmor.ArmorMaterial MithrilArmor = EnumHelper.addArmorMaterial("MithrilArmor","terrariacraft:mithril",50, new int[] {2,3,2,1},40, SoundEvents.ITEM_ARMOR_EQUIP_IRON,3);
    public static Item mithril_helmet;
    public static Item mithril_chestplate;
    public static Item mithril_leggings;
    public static Item mithril_boots;



    public static Item adamantit_dust;
    public static Item adamantit_ingot;
    public static Item mithril_dust;
    public static Item mithril_ingot;

    public static Item ironcoalingot;




    public static void init()
    {

        //weapons
        mithril_sword = new ItemMithrilSword(MITHRIL);
        adamantit_sword = new ItemAdamantitSword(ADAMANTIT);

        //armor
        adamantium_helmet = new ItemAdamantiumArmor("adamantium_helmet", AdamantiumArmor, 1, EntityEquipmentSlot.HEAD);
        adamantium_chestplate = new ItemAdamantiumArmor("adamantium_chestplate", AdamantiumArmor, 1, EntityEquipmentSlot.CHEST);
        adamantium_leggings = new ItemAdamantiumArmor("adamantium_leggings", AdamantiumArmor, 2, EntityEquipmentSlot.LEGS);
        adamantium_boots = new ItemAdamantiumArmor("adamantium_boots", AdamantiumArmor,1,EntityEquipmentSlot.FEET);

        mithril_helmet = new ItemMithrilArmor("mithril_helmet", MithrilArmor, 1, EntityEquipmentSlot.HEAD);
        mithril_chestplate = new ItemMithrilArmor("mithril_chestplate", MithrilArmor, 1, EntityEquipmentSlot.CHEST);
        mithril_leggings = new ItemMithrilArmor("mithril_leggings", MithrilArmor, 2, EntityEquipmentSlot.LEGS);
        mithril_boots = new ItemMithrilArmor("mithril_boots", MithrilArmor, 1, EntityEquipmentSlot.FEET);



        adamantit_ingot = new ItemAdamantitIngot();
        adamantit_dust = new ItemAdamantitDust();
        mithril_dust = new ItemMithrilDust();
        mithril_ingot = new ItemMithrilIngot();
        ironcoalingot = new ItemIronCoalIngot();
        //Tutorial


    }

    public static void register(){

        //Weapons
        GameRegistry.registerItem(mithril_sword);
        GameRegistry.registerItem(adamantit_sword);

        //Armor
        GameRegistry.registerItem(adamantium_helmet,adamantium_helmet.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(adamantium_chestplate,adamantium_chestplate.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(adamantium_leggings,adamantium_leggings.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(adamantium_boots,adamantium_boots.getUnlocalizedName().substring(5));

        GameRegistry.registerItem(mithril_helmet, mithril_helmet.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(mithril_chestplate, mithril_chestplate.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(mithril_leggings, mithril_leggings.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(mithril_boots, mithril_boots.getUnlocalizedName().substring(5));


        GameRegistry.register(adamantit_ingot);
        GameRegistry.register(adamantit_dust);
        GameRegistry.register(ironcoalingot);
        GameRegistry.register(mithril_ingot);
        GameRegistry.register(mithril_dust);


    }




    public static void registerRenders() {

        //Weapons
        registerRender(mithril_sword);
        registerRender(adamantit_sword);

        //Armor
        registerRender(adamantium_helmet);
        registerRender(adamantium_chestplate);
        registerRender(adamantium_leggings);
        registerRender(adamantium_boots);

        registerRender(mithril_helmet);
        registerRender(mithril_chestplate);
        registerRender(mithril_leggings);
        registerRender(mithril_boots);


        //ingots
        registerRender(adamantit_ingot);
        registerRender(ironcoalingot);
        registerRender(mithril_ingot);

        //dust
        registerRender(adamantit_dust);
        registerRender(mithril_dust);



    }



    private static void registerRender(Item item){


       Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,0,new ModelResourceLocation(item.getRegistryName(), "inventory"));

    }

    public static Item registerItem(Item item, String name, CreativeTabs tabs)
    {
        GameRegistry.register(item, new ResourceLocation((Reference.MOD_ID), name));
        return item;
    }






}
