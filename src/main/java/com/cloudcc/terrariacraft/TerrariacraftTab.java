package com.cloudcc.terrariacraft;

import com.cloudcc.terrariacraft.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Tobias on 07.08.2016.
 */
public class TerrariacraftTab extends CreativeTabs {

    public TerrariacraftTab(){
        super("tabTerrariacraft");
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.adamantit_ingot;
    }
}
