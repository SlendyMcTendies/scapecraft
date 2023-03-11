package com.slendymctendies.scapecraft.entity.inferno;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.client.model.animation.Animation;

public class NibblerEntity extends MonsterEntity {

    public NibblerEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.MAX_HEALTH, 10D)
                .add(Attributes.MOVEMENT_SPEED, 0.20D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public boolean doHurtTarget(Entity target){
        if(!super.doHurtTarget(target)){
            return false;
        } else {
            if (target instanceof LivingEntity){
                ((LivingEntity)target).addEffect(new EffectInstance(Effects.WITHER, 200));
                //addEffect(new EffectInstance(Effects.[EFFECTNAME], [DURATION IN TICKS], [AMPLIFIER - INT {Level 2 = 1; Level 1 = EMPTY}]));

                //Practice making targets on fire after damage
                /*
                if (!target.isOnFire()){
                    target.isOnFire();
                }
                 */
            }
            return  true;
        }
    }

    public boolean isSensitiveToWater() {return true;}

    @Override
    public boolean fireImmune() {
        return true;
    }

    protected int getExperienceReward(PlayerEntity player){
        return 0;
    }

    protected boolean isMovementNoisy() {
        return false;
    }

}
