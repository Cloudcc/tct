package com.cloudcc.terrariacraft.blocks.states;

import net.minecraft.block.BlockOre;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.IStringSerializable;

/**
 * Created by Tobias on 08.08.2016.
 */
public class BlockStateOre extends BlockStateContainer {

    public static final PropertyEnum<EnumOreType> typeProperty = PropertyEnum.create("type", EnumOreType.class);

    public BlockStateOre(BlockOre block)
    {
        super(block, typeProperty);
    }



    public static enum  EnumOreType implements IStringSerializable
    {
        ADAMANTIT,
        MITHRIL;

        @Override
        public String getName()
        {
            return name().toLowerCase();
        }
    }
}
