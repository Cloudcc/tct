package com.cloudcc.terrariacraft.world;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by hi112 on 25.09.2016.
 */
public class ModStructureGenerator implements IWorldGenerator {
    //@formatter:off

    House house = new House();

    //@formatter:on

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()){
            case 0: //Overworld

                this.runHouseGenerator(world, random, chunkX, chunkZ);
                break;
            case -1: //Nether

                break;
            case 1: //The End

                break;
        }
    }

    public void runHouseGenerator(World world, Random random, int chunkX, int chunkZ){
        int x = chunkX * 16 + random.nextInt(16); //randomly per chunk
        int z = chunkZ * 16 + random.nextInt(16); //randomly per chunk
        int xy = x >> 4;
        int zy = z >> 4;
        int height = world.getChunkFromChunkCoords(xy , zy).getHeight(new BlockPos(x & 15, 0, z & 15));
        int y = height - 1; //fixing hovering part of structure

        if((random.nextInt(1000) + 1) <= 10) {
            boolean place = true;


            for (int j = 0; j <= 7; j++) {//y{
                for (int k = 0; k <= 13; k++) {// z{
                    for (int i = 0; i <= 13; i++) { // x
                        if (world.getBlockState(new BlockPos(i + x, j + y + 1, k + z)).getBlock() != Blocks.AIR) {
                            place = false;
                        }
                    }
                }
            }

            if (place) {
                house.generate(world, random, new BlockPos(x, y, z));
            }
        }
}
}

