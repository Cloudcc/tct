package com.cloudcc.terrariacraft.blocks.Ore;

import com.cloudcc.terrariacraft.init.ModBlocks;
import com.cloudcc.terrariacraft.world.WorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Tobias on 09.08.2016.
 */
public class BlockOre extends Block {

    public BlockOre()
    {
        super(Material.ROCK);
    }



    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if(fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(),random ,fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;

            if(i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn,pos,state,chance,fortune);
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int i = 0;

            if(this == ModBlocks.mithril_ore)
            {
                i = MathHelper.getRandomIntegerInRange(rand, 3, 6);
            }
            else if(this == ModBlocks.adamantit_ore)
            {
                i = MathHelper.getRandomIntegerInRange(rand, 3, 6);
            }
            return i;
        }
        return 0;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this);
    }
}
