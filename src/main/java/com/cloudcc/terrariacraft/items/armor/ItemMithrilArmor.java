package com.cloudcc.terrariacraft.items.armor;

import com.cloudcc.terrariacraft.Terrariacraft;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

/**
 * Created by hi112 on 21.08.2016.
 */
public class ItemMithrilArmor extends ItemArmor {


 /*   public ItemModArmor(String unlocalizedName,ArmorMaterial material, EntityEquipmentSlot equipmentSlot, String name)
    {
        super(material,0,equipmentSlot);
        setUnlocalizedName(unlocalizedName);
        setCreativeTab(Terrariacraft.CREATIVE_TAB);
    }*/


    public ItemMithrilArmor(String unlocalizedName, ItemArmor.ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlot) {

        super(material, renderIndex, equipmentSlot);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(Terrariacraft.CREATIVE_TAB);

        this.setMaxStackSize(1);

    }



  /*  @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (itemStack.getItem() == ModItems.garnet_helmet) ;
        effectPlayer(player, Potion.getPotionById(16), 0);
        if (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == ModItems.garnet_helmet
                && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == ModItems.garnet_chestplate
                && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == ModItems.garnet_leggings
                && player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == ModItems.garnet_boots)
            this.effectPlayer(player, Potion.getPotionById(11), 2);
    }*/


   /* public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (stack.getItem() == ModItems.garnet_helmet || stack.getItem() == ModItems.garnet_chestplate || stack.getItem() == ModItems.garnet_boots)
            return Reference.MOD_ID + ":models/armor/garnet_layer_1.png";
        else if (stack.getItem() == ModItems.garnet_leggings)
            return Reference.MOD_ID + ":models/armor/garnet_layer_2.png";
        else
            return null;
    }*/

    /*private void effectPlayer(EntityPlayer player, Potion potion, int amplifier) {
        if (player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1)
            player.addPotionEffect(new PotionEffect(potion, 159, amplifier, true, true));
    }*/ {
    }
}
