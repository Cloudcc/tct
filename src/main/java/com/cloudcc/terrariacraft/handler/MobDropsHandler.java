package com.cloudcc.terrariacraft.handler;

import com.cloudcc.terrariacraft.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

/**
 * Created by hi112 on 20.08.2016.
 */
public class MobDropsHandler  {

    public static double rand;
    public Random r = new Random();



    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {

        if (event.getEntityLiving() instanceof EntityCow) {
            if (!event.getEntityLiving().isChild()) {

                System.out.println("Itemdrop event");
                ItemStack itemStack = new ItemStack(ModBlocks.cow_head);
                EntityItem drop = new EntityItem(((EntityCow) event.getEntityLiving()).worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, itemStack);





                event.getDrops().add(drop);


            }
        }
    }




}
