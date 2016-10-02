package com.cloudcc.terrariacraft.blocks.Ore;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import com.cloudcc.terrariacraft.init.ModBlocks;
import com.cloudcc.terrariacraft.init.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Tobias on 08.08.2016.
 */
public class BlockMithrilOre extends BlockOre {


    public BlockMithrilOre(){
        super();
        setUnlocalizedName(Reference.TerrariacraftOres.MITHRIL_ORE.getUnlocalizedName());
        setRegistryName(Reference.TerrariacraftOres.MITHRIL_ORE.getRegistryName());
        setHardness(1.0F); //weiß noch nicht was
        setResistance(2F);//weiß noch nicht w
        setHarvestLevel("pickaxe", 3);
        setCreativeTab(Terrariacraft.CREATIVE_TAB);

    }

 /*   You dont need this shit
        public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this == ModBlocks.mithril_ore ? ModItems.mithril_dust : Item.getItemFromBlock(this);
    }*/


}
