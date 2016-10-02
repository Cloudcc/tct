package com.cloudcc.terrariacraft.init;

import com.cloudcc.terrariacraft.Terrariacraft;
import com.cloudcc.terrariacraft.entity.EntityArms;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.BitSet;

/**
 * Created by Tobias on 13.08.2016.
 */
public class ModEntities {

    private static final ModEntities INSTANCE = new ModEntities();
    private BitSet availableIndicies;

    public static ModEntities instance() {
        return INSTANCE;
    }

    private ModEntities(){
        availableIndicies = new BitSet(256);
        availableIndicies.set(1,255);
        for (Object id : EntityList.ID_TO_CLASS.keySet()){
            availableIndicies.clear((Integer)id);
        }
    }

    public static int findGlobalUniqueEntityId(){
        int res = instance().availableIndicies.nextSetBit(0);

        if(res < 0){
            throw new RuntimeException("No more Entity indicies left");
        }
        return res;
    }

    public static void registerEntity(){

        createEntity(EntityArms.class,"Doctor_Trivial", 0x0000ff, 0xed1515);

    }

    public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){

        int randomId = findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(entityClass, entityName, randomId, Terrariacraft.instance,64,1,true,solidColor,spotColor);

    }

    public static void registerSpawn(){
        ;
    }
}
