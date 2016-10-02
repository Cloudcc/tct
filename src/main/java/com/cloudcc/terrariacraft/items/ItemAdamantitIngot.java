package com.cloudcc.terrariacraft.items;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import net.minecraft.item.Item;

/**
 * Created by Tobias on 09.08.2016.
 */
public class ItemAdamantitIngot extends Item {

    public ItemAdamantitIngot()
    {
        setUnlocalizedName(Reference.TerrariacraftItems.ADAMANTIT_INGOT.getUnlocalizedName());
        setRegistryName(Reference.TerrariacraftItems.ADAMANTIT_INGOT.getRegistryName());
        setCreativeTab(Terrariacraft.CREATIVE_TAB);
    }
}
