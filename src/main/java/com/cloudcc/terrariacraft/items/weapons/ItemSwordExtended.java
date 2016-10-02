package com.cloudcc.terrariacraft.items.weapons;

import net.minecraft.item.ItemSword;

/**
 * Created by hi112 on 22.08.2016.
 */
public class ItemSwordExtended extends ItemSword implements IExtendedReach {

    public ItemSwordExtended(ToolMaterial material)
    {
        super(material);
        setUnlocalizedName("swordExtended");
    }

    @Override
    public float getReach()
    {
        return 20.0F;
    }
}
