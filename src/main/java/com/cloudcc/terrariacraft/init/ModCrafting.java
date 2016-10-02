package com.cloudcc.terrariacraft.init;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.items.ItemAdamantitIngot;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.functions.ModIdFunction;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Tobias on 07.08.2016.
 */
public class ModCrafting {

    public static void register(){

        //weapons
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.mithril_sword)," M "," M ", " D ",'M', ModItems.mithril_ingot, 'D', Blocks.DIAMOND_BLOCK);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.adamantit_sword), "NAN","MAM","DDD", 'N', Items.NETHER_STAR, 'A', ModItems.adamantit_ingot,'D', Blocks.DIAMOND_BLOCK);

        //ingot
        GameRegistry.addSmelting(ModBlocks.mithril_ore, new ItemStack(ModItems.mithril_ingot),10);
        GameRegistry.addSmelting(ModBlocks.adamantit_ore, new ItemStack(ModItems.adamantit_ingot),20);
        GameRegistry.addSmelting(ModItems.mithril_dust, new ItemStack(ModItems.mithril_ingot), 10);
        GameRegistry.addSmelting(ModItems.adamantit_dust,new ItemStack(ModItems.adamantit_ingot), 20);

        ///armor
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.adamantium_boots),"A A","ADA", 'A', ModItems.adamantit_ingot, 'D', ModItems.mithril_boots);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.adamantium_chestplate),"ADA","AAA","AAA", 'A', ModItems.adamantit_ingot, 'D', ModItems.mithril_chestplate);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.adamantium_helmet),"AAA","ADA", 'A', ModItems.adamantit_ingot, 'D', ModItems.mithril_helmet);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.adamantium_leggings),"AAA","ADA","A A", 'A', ModItems.adamantit_ingot, 'D', ModItems.mithril_leggings);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.mithril_boots),"A A","ADA", 'A', ModItems.mithril_ingot, 'D', Items.DIAMOND_BOOTS);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.mithril_chestplate),"ADA","AAA","AAA", 'A', ModItems.mithril_ingot, 'D', Items.DIAMOND_CHESTPLATE);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.mithril_helmet),"AAA","ADA", 'A', ModItems.mithril_ingot, 'D', Items.DIAMOND_HELMET);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.mithril_leggings),"AAA","ADA","A A", 'A', ModItems.mithril_ingot, 'D', Items.DIAMOND_LEGGINGS);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ironcoalingot),"eke", "kek", "eke",'e', Items.IRON_INGOT, 'k',Items.COAL);






    }
}
