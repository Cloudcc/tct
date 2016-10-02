package com.cloudcc.terrariacraft.init;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.blocks.BlockJar;
import com.cloudcc.terrariacraft.blocks.Heads.BlockCowHead;
import com.cloudcc.terrariacraft.blocks.Ore.BlockAdamantitOre;
import com.cloudcc.terrariacraft.blocks.Ore.BlockMithrilOre;
import com.cloudcc.terrariacraft.blocks.chest.BlockStorageBlock;
import com.cloudcc.terrariacraft.blocks.chest.testcontainer.TileEntityTestContainer;
import com.cloudcc.terrariacraft.blocks.chest.testcontainer.TestcontainerBlock;
import com.cloudcc.terrariacraft.blocks.furnace.BlockCookingPot;
import com.cloudcc.terrariacraft.blocks.furnace.BlockYellowFurnace;
import com.cloudcc.terrariacraft.blocks.grinder.BlockGrinder;
import com.cloudcc.terrariacraft.blocks.BlockMultiBlock;
import com.cloudcc.terrariacraft.blocks.multiblock.TileEntityMultiBlock;
import com.cloudcc.terrariacraft.tile.TileEntityStorageBlock;
import com.cloudcc.terrariacraft.tile.blocks.furnace.TileEntityCookingPot;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Tobias on 07.08.2016.
 */
public class ModBlocks {


    public static Block storage_block;

    public static Block jar;




    public static Block cooking_pot;
    public static Block yellow_furnace;
    public static Block lit_yellow_furnace;
    public static Block grinder;

    public static Block adamantit_ore;
    public static Block mithril_ore;

    public static Block cow_head;
    public static Block multi_block;

    public static Block testcontainer;

    public static void init()
    {


        testcontainer = new TestcontainerBlock();
        cow_head = new BlockCowHead();
        grinder = new BlockGrinder(true, "grinder");
        storage_block = new BlockStorageBlock();

        mithril_ore = new BlockMithrilOre();
       adamantit_ore = new BlockAdamantitOre();


        jar = new BlockJar();
        yellow_furnace = new BlockYellowFurnace(false, "yellow_furnace");
        lit_yellow_furnace = new BlockYellowFurnace(true, "lit_yellow_furnace");
        cooking_pot = new BlockCookingPot();

        multi_block = new BlockMultiBlock();

    }

    public static void register(){

        registerBlock(multi_block);

        registerBlock(cow_head);
        registerBlock(storage_block);
        registerBlock(grinder);

        registerBlock(mithril_ore);
        registerBlock(adamantit_ore);


        registerBlock(jar);

     //   registerBlock(lit_yellow_furnace);
        registerBlock(yellow_furnace);
        GameRegistry.registerTileEntity(TileEntityStorageBlock.class, "tile_entity_storage_block");
        registerBlock(cooking_pot);
        GameRegistry.registerTileEntity(TileEntityCookingPot.class,"tile_entity_cooking_pot");
        GameRegistry.registerTileEntity(TileEntityMultiBlock.class, "multiblock");
        GameRegistry.registerTileEntity(TileEntityTestContainer.class, Reference.MOD_ID + "_testcontainerblock");



    }
    private static void registerBlock(Block block){
        GameRegistry.register(block);
        ItemBlock item = new ItemBlock(block);
        item.setRegistryName(block.getRegistryName());
        GameRegistry.register(item);
    }

    public static void registerRenders() {

        registerRender(multi_block);

        registerRender(cow_head);

        registerRender(adamantit_ore);
        registerRender(mithril_ore);
        registerRender(storage_block);

        registerRender(jar);

        registerRender(yellow_furnace);
        registerRender(lit_yellow_furnace);
        registerRender(cooking_pot);
        registerRender(grinder);
    }

    private static void registerRender(Block block){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block),0,new ModelResourceLocation(block.getRegistryName(), "inventory"));

    }





}
