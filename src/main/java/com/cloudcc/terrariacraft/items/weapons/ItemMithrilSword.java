package com.cloudcc.terrariacraft.items.weapons;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by hi112 on 21.08.2016.
 */
public class ItemMithrilSword extends ItemSword{


    ResourceLocation res = new ResourceLocation(Reference.MOD_ID + "textures/items/mithril_sword.png");
    public ItemMithrilSword(ToolMaterial material) {
        super(material);
        setRegistryName(Reference.TerrariacraftItems.MITHRIL_SWORD.getRegistryName());
        setUnlocalizedName(Reference.TerrariacraftItems.MITHRIL_SWORD.getUnlocalizedName());
        setCreativeTab(Terrariacraft.CREATIVE_TAB);

    }
}
