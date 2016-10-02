package com.cloudcc.terrariacraft.items;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import net.minecraft.item.Item;

/**
 * Created by Tobias on 09.08.2016.
 */
public class ItemMithrilDust extends Item {

    public ItemMithrilDust()
    {
        setUnlocalizedName(Reference.TerrariacraftItems.MITHRIL_DUST.getUnlocalizedName());
        setRegistryName(Reference.TerrariacraftItems.MITHRIL_DUST.getRegistryName());
        setCreativeTab(Terrariacraft.CREATIVE_TAB);
    }
}
