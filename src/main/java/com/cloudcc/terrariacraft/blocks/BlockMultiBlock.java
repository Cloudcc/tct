package com.cloudcc.terrariacraft.blocks;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.blocks.multiblock.ModOreStructure;
import com.cloudcc.terrariacraft.blocks.multiblock.TileEntityMultiBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by hi112 on 25.08.2016.
 */
public class BlockMultiBlock extends BlockContainer {

    public BlockMultiBlock()
    {
        super(Material.ANVIL);
        setUnlocalizedName(Reference.TerrariacraftBlocks.multi_block.getUnlocalizedName());
        setRegistryName(Reference.TerrariacraftBlocks.multi_block.getUnlocalizedName());

        setHardness(2.0F);
        setResistance(5F);

    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return super.getRenderType(state);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityMultiBlock();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote)
        if(ModOreStructure.isMultiBlockStructure(worldIn,pos)) {
            Entity bolt = new EntityLightningBolt(worldIn, pos.getX()+ 3 , pos.getY()+ 3, pos.getZ(),true);
            playerIn.worldObj.addWeatherEffect(bolt);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("le baguette");
        }
        return true;
    }
}
