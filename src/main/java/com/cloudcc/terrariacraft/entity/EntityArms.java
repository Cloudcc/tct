package com.cloudcc.terrariacraft.entity;

import com.cloudcc.terrariacraft.init.ModItems;
import com.cloudcc.terrariacraft.init.ModSounds;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

/**
 * Created by Tobias on 13.08.2016.
 */
public class EntityArms extends EntityMob {

    public EntityArms(World worldIn)
    {
        super(worldIn);

        this.experienceValue = 2000;
        this.tasks.addTask(0, new EntityAISwimming(this));


        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class,true));

        this.tasks.addTask(3, new EntityAIAttackMelee(this, 2.0d, true));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityCreeper.class, 100.0f, 2.0d, 5.0d));
        this.tasks.addTask(2, new EntityAIWander(this,2.0d));

    }

    public boolean isAIEnabled() {
        return true;
    }

    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0d);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2.0d);
    }

    protected void addRandonArmor(){
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND));
    }

    protected Item getDropItem()
    {
        return ModItems.adamantit_dust;
    }

    protected void dropRareDrop(int item){
        this.dropItem(ModItems.adamantit_dust,1);
    }

    protected SoundEvent getAmbientSound()
    {
    return ModSounds.Doctor;

    }

    protected SoundEvent getHurtSound()
    {
        return ModSounds.Doctor;

    }

    protected SoundEvent getDeathSound()
    {
        return null;

    }


}
