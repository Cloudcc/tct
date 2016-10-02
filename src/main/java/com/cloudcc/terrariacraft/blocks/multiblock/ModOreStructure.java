package com.cloudcc.terrariacraft.blocks.multiblock;

import com.cloudcc.terrariacraft.init.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by hi112 on 25.08.2016.
 */
public class ModOreStructure extends CheckMultiBlock {

    public static boolean isMultiBlockStructure(World world, BlockPos masterPos) {
        if (getBlock(world, masterPos) == ModBlocks.multi_block) {
            if (check(world, masterPos, 0) || check(world, masterPos, 1) || check(world, masterPos, 2) || check(world, masterPos, 3)) {
                return true;
            }

        }
        return false;
    }

    public static boolean isIncorrect(World world, BlockPos masterPos) {
        if (check(world, masterPos, 0) == false && check(world, masterPos, 1) == false && check(world, masterPos, 2) && check(world, masterPos, 3) == false)
            return true;
        return false;
    }

    public static boolean check(World world, BlockPos masterPos, int orientation) {
        int x = masterPos.getX();
        int y = masterPos.getY();
        int z = masterPos.getZ();

        //bottom layer
        if (getBlock(world, masterPos) == ModBlocks.multi_block) {
            if (getBlockAndRotate(world, x + 1, y, z, masterPos, orientation) != ModBlocks.multi_block)
                return false;
            if (getBlockAndRotate(world, x - 1, y, z, masterPos, orientation) != ModBlocks.multi_block)
                return false;
            if (getBlockAndRotate(world, x, y, z + 1, masterPos, orientation) != ModBlocks.multi_block)
                return false;
            if (getBlockAndRotate(world, x, y, z - 1, masterPos, orientation) != ModBlocks.multi_block)
                return false;
            if (getBlockAndRotate(world, x + 1, y, z + 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
            if (getBlockAndRotate(world, x + 1, y, z - 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
            if (getBlockAndRotate(world, x - 1, y, z + 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
            if (getBlockAndRotate(world, x - 1, y, z - 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
        } else
            return false;

        //middle layer
        if (getBlock(world, x, y + 1, z) == ModBlocks.adamantit_ore) {
            if (getBlockAndRotate(world, x + 1, y + 1, z, masterPos, orientation) != Blocks.NETHER_BRICK_FENCE)
                return false;
            if (getBlockAndRotate(world, x - 1, y + 1, z, masterPos, orientation) != Blocks.NETHER_BRICK_FENCE)
                return false;
            if (getBlockAndRotate(world, x, y + 1, z + 1, masterPos, orientation) != Blocks.NETHER_BRICK_FENCE)
                return false;
            if (getBlockAndRotate(world, x, y + 1, z - 1, masterPos, orientation) != Blocks.NETHER_BRICK_FENCE)
                return false;
            if (getBlockAndRotate(world, x + 1, y + 1, z + 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
            if (getBlockAndRotate(world, x + 1, y + 1, z - 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
            if (getBlockAndRotate(world, x - 1, y + 1, z + 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
            if (getBlockAndRotate(world, x - 1, y + 1, z - 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
        } else
            return false;

        //top layer
        if (getBlock(world, x, y + 2, z) == ModBlocks.multi_block) {
            if (getBlockAndRotate(world, x + 1, y + 2, z, masterPos, orientation) != ModBlocks.multi_block)
                return false;
            if (getBlockAndRotate(world, x - 1, y + 2, z, masterPos, orientation) != ModBlocks.multi_block)
                return false;
            if (getBlockAndRotate(world, x, y + 2, z + 1, masterPos, orientation) != ModBlocks.multi_block)
                return false;
            if (getBlockAndRotate(world, x, y + 2, z - 1, masterPos, orientation) != ModBlocks.multi_block)
                return false;
            if (getBlockAndRotate(world, x + 1, y + 2, z + 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
            if (getBlockAndRotate(world, x + 1, y + 2, z - 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
            if (getBlockAndRotate(world, x - 1, y + 2, z + 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
            if (getBlockAndRotate(world, x - 1, y + 2, z - 1, masterPos, orientation) != Blocks.BRICK_BLOCK)
                return false;
        } else
            return false;

        //roof layer
        for (int i = -1; i <= 1; i++)
            for (int k = -1; k <= 1; k++) {
                if (getBlockAndRotate(world, x + i, y + 3, z + k, masterPos, orientation) != Blocks.STONE_SLAB)
                    return false;
            }

        return true;
    }


}