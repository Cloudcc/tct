package com.cloudcc.terrariacraft.blocks.multiblock;

import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;

/**
 * Created by hi112 on 25.08.2016.
 */
public abstract class CheckMultiBlock {

    static BlockPos northToEast(BlockPos pos, BlockPos masterPos)
    {

        int x = pos.getX();
        int z = pos.getZ();
        int masterX = masterPos.getX();
        int masterZ = masterPos.getZ();

        int newX = masterX + masterZ - z;
        int newZ = x - masterZ + masterZ;

        return new BlockPos(newX, pos.getY(), newZ);

    }
    static BlockPos northToWest(BlockPos pos, BlockPos masterPos)
    {
        int x = pos.getX();
        int z = pos.getZ();
        int masterX = masterPos.getX();
        int masterZ = masterPos.getZ();

        int newX = z - masterZ + masterX;
        int newZ = masterZ + masterX - x;

        return new BlockPos(newX, pos.getY(), newZ);

    }
    static BlockPos northToSouth(BlockPos pos, BlockPos masterPos)
    {
        int x = pos.getX();
        int z = pos.getZ();
        int masterX = masterPos.getX();
        int masterZ = masterPos.getZ();

        int newX = 2 * masterX - x;
        int newZ = 2 * masterZ - z;

        return new BlockPos(newX, pos.getY(), newZ);

    }



    static Block getBlock(World world, BlockPos pos)
    {
        return world.getBlockState(pos).getBlock();
    }

    static Block getBlock(World world, int x, int y, int z)
    {
        return getBlock(world, new BlockPos(x, y, z));
    }

    static BlockPos rotateCoords(BlockPos pos, BlockPos masterPos, int orientation){
        switch (orientation)
        {
            case 0: return pos;
            case 1: return northToEast(pos,masterPos);
            case 2: return northToSouth(pos, masterPos);
            case 3: return northToWest(pos,masterPos);
            default: return pos;
        }
    }

    static BlockPos rotateCoords(int x, int y, int z, BlockPos masterPos, int orientation)
    {
        return rotateCoords(new BlockPos(x, y, z), masterPos,orientation);
    }

    static Block getBlockAndRotate(World world, BlockPos pos, BlockPos masterPos, int orientation)
    {
        return getBlock(world, rotateCoords(pos, masterPos, orientation));
    }

    static Block getBlockAndRotate(World world, int x, int y, int z, BlockPos masterPos, int orientation)
    {
        return  getBlockAndRotate(world, new BlockPos(x, y, z), masterPos, orientation);
    }
}
