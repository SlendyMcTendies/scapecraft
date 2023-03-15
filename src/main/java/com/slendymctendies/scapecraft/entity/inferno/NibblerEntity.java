package com.slendymctendies.scapecraft.entity.inferno;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class NibblerEntity extends Monster implements IAnimatable {
    private AnimationFactory animFactory = new AnimationFactory(this);

    public NibblerEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.20D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .build();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.5F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public boolean doHurtTarget(Entity target){
        if(!super.doHurtTarget(target)){
            return false;
        } else {
            if (target instanceof LivingEntity){
                ((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 0));
            }
            return true;
        }
    }

    public boolean isSensitiveToWater(){return true;}
    public boolean fireImmune(){return true;}
    protected int getExperienceReward(Player player){return 0;}
    protected boolean isMovementNoisy() {return false;}

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event){
        return null;
    }

    private PlayState attackPredicate(AnimationEvent event){
        if(this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)){
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.nibbler.pinchattack", false));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<NibblerEntity>(this, "attackController", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animFactory;
    }
}
