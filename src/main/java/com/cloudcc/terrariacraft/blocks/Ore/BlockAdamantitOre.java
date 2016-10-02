package com.cloudcc.terrariacraft.blocks.Ore;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import com.cloudcc.terrariacraft.init.ModBlocks;
import com.cloudcc.terrariacraft.init.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Tobias on 08.08.2016.
 */
public class BlockAdamantitOre extends BlockOre {


    public BlockAdamantitOre(){
        super();
        setUnlocalizedName(Reference.TerrariacraftOres.ADAMANTIT_ORE.getUnlocalizedName());
        setRegistryName(Reference.TerrariacraftOres.ADAMANTIT_ORE.getRegistryName());
        setHardness(1.0F); //weiß noch nicht was
        setResistance(2F);//weiß noch nicht w
        setHarvestLevel("pickaxe", 3);
        setCreativeTab(Terrariacraft.CREATIVE_TAB);
    }

    /*public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this == ModBlocks.adamantit_ore ? ModItems.adamantit_dust : Item.getItemFromBlock(this);
    }*/


}
