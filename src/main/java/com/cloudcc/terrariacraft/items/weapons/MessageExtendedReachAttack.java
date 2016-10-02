package com.cloudcc.terrariacraft.items.weapons;

import com.cloudcc.terrariacraft.Terrariacraft;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hi112 on 22.08.2016.
 */
public class MessageExtendedReachAttack implements IMessage {

    private int entityId;

    public MessageExtendedReachAttack()
    {

    }

    public MessageExtendedReachAttack(int parEntityId)
    {
        entityId = parEntityId;
        System.out.println("Constructor");
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        entityId = ByteBufUtils.readVarInt(buf, 4);
        //DEBUG
        System.out.println("fromBytes");
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeVarInt(buf,entityId,4);
        //DEBUG
        System.out.println("toBytes encoded");
    }


}
