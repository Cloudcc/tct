package com.cloudcc.terrariacraft.items;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import net.minecraft.item.Item;

/**
 * Created by Tobias on 09.08.2016.
 */
public class ItemAdamantitDust extends Item {

    public ItemAdamantitDust()
    {
        setUnlocalizedName(Reference.TerrariacraftItems.ADAMANTIT_DUST.getUnlocalizedName());
        setRegistryName(Reference.TerrariacraftItems.ADAMANTIT_DUST.getRegistryName());
        setCreativeTab(Terrariacraft.CREATIVE_TAB);
    }
}
