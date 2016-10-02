package com.cloudcc.terrariacraft.items;

import com.cloudcc.terrariacraft.Reference;
import com.cloudcc.terrariacraft.Terrariacraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Tobias on 07.08.2016.
 */
public class ItemIronCoalIngot extends Item {

    public ItemIronCoalIngot(){

        setUnlocalizedName(Reference.TerrariacraftItems.IRONCOALINGOT.getUnlocalizedName());
        setRegistryName(Reference.TerrariacraftItems.IRONCOALINGOT.getRegistryName());
        setCreativeTab(Terrariacraft.CREATIVE_TAB);
    }
}
