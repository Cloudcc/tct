package com.cloudcc.terrariacraft.init;

import com.cloudcc.terrariacraft.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Tobias on 13.08.2016.
 */
public class ModSounds {

    public static SoundEvent Doctor;

    public static void registerSounds()
    {
        Doctor = registerSound("Doctor");

    }

    private static SoundEvent registerSound(String soundName)
    {
        final ResourceLocation id = new ResourceLocation(Reference.MOD_ID, soundName);
        return GameRegistry.register(new SoundEvent(id).setRegistryName(id));
    }
}
