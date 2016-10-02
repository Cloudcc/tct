package com.cloudcc.terrariacraft.items;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import net.minecraft.item.Item;

/**
 * Created by Tobias on 09.08.2016.
 */
public class ItemMithrilIngot extends Item {

    public ItemMithrilIngot()
    {
        setUnlocalizedName(Reference.TerrariacraftItems.MITHRIL_INGOT.getUnlocalizedName());
        setRegistryName(Reference.TerrariacraftItems.MITHRIL_INGOT.getRegistryName());
        setCreativeTab(Terrariacraft.CREATIVE_TAB);
    }
}
