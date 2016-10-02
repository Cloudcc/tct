package com.cloudcc.terrariacraft.items.armor;

import com.cloudcc.terrariacraft.Terrariacraft;
import com.cloudcc.terrariacraft.init.ModItems;
import com.sun.javafx.collections.MappingChange;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import sun.java2d.StateTracker;

import java.util.HashMap;

/**
 * Created by Tobias on 11.08.2016.
 */
public class ItemAdamantiumArmor extends ItemArmor {


 /*   public ItemModArmor(String unlocalizedName,ArmorMaterial material, EntityEquipmentSlot equipmentSlot, String name)
    {
        super(material,0,equipmentSlot);
        setUnlocalizedName(unlocalizedName);
        setCreativeTab(Terrariacraft.CREATIVE_TAB);
    }*/



    public ItemAdamantiumArmor(String unlocalizedName, ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlot) {

        super(material, renderIndex, equipmentSlot);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(Terrariacraft.CREATIVE_TAB);

        this.setMaxStackSize(1);

    }



    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (itemStack.getItem() == ModItems.adamantium_boots) effectPlayer(player,Potion.getPotionById(1),2);
        if (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == ModItems.adamantium_helmet
                && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == ModItems.adamantium_chestplate
                && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == ModItems.adamantium_leggings
                && player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == ModItems.adamantium_boots) {
            effectPlayer(player, Potion.getPotionById(11), 2);
            effectPlayer(player, Potion.getPotionById(12),3);
        }
    }




    private void effectPlayer(EntityPlayer player, Potion potion, int amplifier) {
        if (player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1)
            player.addPotionEffect(new PotionEffect(potion, 159, amplifier, true, true));
    }

}



