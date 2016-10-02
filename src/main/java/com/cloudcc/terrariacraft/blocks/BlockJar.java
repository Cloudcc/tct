package com.cloudcc.terrariacraft.blocks;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Tobias on 07.08.2016.
 */
public class BlockJar extends Block {

    //1/16 = 0.0625

    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0625 * 2 ,0,0.0625 * 2, 0.0625 *2,0.0625 * 3,0.0625 *13);
    private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB(0.0625 * 3 ,0,0.0625 * 3, 0.0625 *3,0.0625 * 2,0.0625 *12);


    public BlockJar(){
        super(Material.CAKE);
        setUnlocalizedName(Reference.TerrariacraftBlocks.JAR.getUnlocalizedName());
        setRegistryName(Reference.TerrariacraftBlocks.JAR.getRegistryName());
        setHardness(1.0F);
        setCreativeTab(Terrariacraft.CREATIVE_TAB);

    }

    @Override
    public boolean isFullCube(IBlockState state){
        return false;
    }
    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
        super.addCollisionBoxToList(pos,entityBox,collidingBoxes,COLLISION_BOX);
    }
}
