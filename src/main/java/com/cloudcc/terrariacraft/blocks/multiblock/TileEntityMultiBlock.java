package com.cloudcc.terrariacraft.blocks.multiblock;

import com.cloudcc.terrariacraft.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

/**
 * Created by hi112 on 25.08.2016.
 */
public class TileEntityMultiBlock extends TileEntity implements ITickable {

    private boolean hasMaster;
    private boolean isMaster;
    private BlockPos masterPos;
    private int timer;

    @Override
    public void update() {
        if(!worldObj.isRemote)
        {
            if(hasMaster && isMaster)
                doMultiBlockStuff();
            else
            {
                if(ModOreStructure.isMultiBlockStructure(worldObj,pos))
                    setupStructure(pos);
            }

            if(isMaster)
                if(ModOreStructure.isIncorrect(worldObj,masterPos))
                    reset();
            if(hasMaster && !isMaster)
                if(!checkForMaster())
                    reset();
        }
    }
    public void doMultiBlockStuff()
    {
        Random rand = new Random();

        if(!worldObj.isRemote)
        {
            int x = masterPos.getX();
            int y = masterPos.getY();
            int z = masterPos.getZ();

            int itemQtn = 0; //ItemQuantity

            timer++;

            //4sec
            if(timer >= 80)
            {
                //randomize itemQtn
                for(int i = 0; i<= 5; i++)
                    itemQtn += rand.nextInt(2) + 1;

                Entity theItem = new EntityItem(worldObj, x, y + 4, z, new ItemStack(ModItems.adamantit_ingot));
                worldObj.spawnEntityInWorld(theItem);
                worldObj.setBlockToAir(new BlockPos(x,y + 1, z));
            }

        }
    }

    public boolean checkForMaster()
    {
        TileEntity tile = worldObj.getTileEntity(masterPos);
        return (tile != null && (tile instanceof TileEntityMultiBlock));
    }

    public void setupStructure(BlockPos pos)
    {
        hasMaster = true;
        isMaster = true;
        masterPos = pos;
        timer = 0;
    }

    public void reset()
    {
        hasMaster = false;
        isMaster = false;
        masterPos = null;

    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);


        hasMaster = tag.getBoolean("hasMaster");
        isMaster = tag.getBoolean("isMaster");

        if(hasMaster)
        {
            masterPos = new BlockPos(tag.getInteger("x"),tag.getInteger("y"),tag.getInteger("z"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
         super.writeToNBT(tag);

        tag.setBoolean("hasMaster",hasMaster);
        tag.setBoolean("isMaster", isMaster);

        if(hasMaster)
        {
            tag.setInteger("x",masterPos.getX());
            tag.setInteger("y",masterPos.getY());
            tag.setInteger("z",masterPos.getZ());
        }


        return tag;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(getUpdatePacket().getNbtCompound());
    }




    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();

        this.writeToNBT(tag);

        return new SPacketUpdateTileEntity(pos, 1, tag);
    }
}
