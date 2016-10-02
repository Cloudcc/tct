package com.cloudcc.terrariacraft.items.weapons;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

/**
 * Created by hi112 on 21.08.2016.
 */
public class ItemAdamantitSword extends ItemSword{


   // ResourceLocation res = new ResourceLocation(Reference.MOD_ID + "textures/items/adamantit_sword.png");
    public ItemAdamantitSword(ToolMaterial material) {
        super(material);
        setRegistryName(Reference.TerrariacraftItems.ADAMANTIT_SWORD.getRegistryName());
        setUnlocalizedName(Reference.TerrariacraftItems.ADAMANTIT_SWORD.getUnlocalizedName());
        setCreativeTab(Terrariacraft.CREATIVE_TAB);


    }

}
