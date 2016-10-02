package com.cloudcc.terrariacraft.world;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by Tobias on 08.08.2016.
 */
public class WorldGen implements IWorldGenerator {


    private WorldGenerator MITHRIL_ORE;
    private WorldGenerator ADAMANTIT_ORE;

    public WorldGen(){

        this.MITHRIL_ORE = new WorldGenMineable(ModBlocks.mithril_ore.getDefaultState(),3);
        this.ADAMANTIT_ORE = new NetherGenMineable(ModBlocks.adamantit_ore.getDefaultState(),3);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension())
        {
            case 0:     //Overworld


                this.runGenerator(this.MITHRIL_ORE, world, random, chunkX, chunkZ, 40,0,35);

                break;

            case -1:    //Nether

                this.runGenerator(this.ADAMANTIT_ORE, world, random, chunkX,chunkZ,30,45,112);

                break;

            case 1:    //The End

                break;
        }
    }


    private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chanceToSpawn, int minHeight, int maxHeight)
    {
        if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Min or Max Height out of bounds");

        int heightDiff = maxHeight - minHeight + 1;
        for(int i = 0; i < chanceToSpawn; i++)
        {
            int x = chunkX * 16 + random.nextInt(16);
            int y = minHeight + random.nextInt(heightDiff);
            int z = chunkZ * 16 + random.nextInt(16);
            generator.generate(world, random, new BlockPos(x,y,z));
        }
    }
}


