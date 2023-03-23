package com.slendymctendies.scapecraft.entity.inferno;

import com.slendymctendies.scapecraft.entity.client.MultipartSegment;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BlobEntity extends Monster implements IAnimatable {

    private MultipartSegment[] subEntities;
    private MultipartSegment bodyfront;
    private MultipartSegment bodyback;

    private AnimationFactory animFactory = new AnimationFactory(this);
    public BlobEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.bodyfront = new MultipartSegment(this, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.bodyback = new MultipartSegment( this, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.subEntities = new MultipartSegment[]{this.bodyfront, this.bodyback};
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .build();
    }

    public boolean isSensitiveToWater() {return true;}
    public boolean fireImmune() { return true;}
    protected int getExperienceReward(Player player){return 0;}

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
                target.setSecondsOnFire(10);
            }
            return true;
        }
    }

    /* Keep method in case it is needed */
    public void resetBlobParts(float scale){
        removeBlobParts();
        bodyfront = new MultipartSegment(this, 1.0f, 0f, 0.0f, 1.0f, 1.0f, 1.0f);
        bodyfront.copyPosition(this);
        bodyfront.setParent(this);
        bodyback = new MultipartSegment(this, 1.0f, 0f, 1.0f, 1.0f, 1.0f, 1.0f);
        bodyback.copyPosition(this);
        bodyback.setParent(this);
    }

    public void removeBlobParts(){
        if(bodyfront != null){
            bodyfront.remove(RemovalReason.DISCARDED);
            bodyfront = null;
        }
        if(bodyback != null){
            bodyback.remove(RemovalReason.DISCARDED);
            bodyback = null;
        }
    }

    public void updateBlobParts() {
        if(bodyfront != null){
            if(!bodyfront.shouldContinuePersisting()){
                level.addFreshEntity(bodyfront);
            }
            bodyfront.setParent(this);
        }
        if(bodyback != null){
            if(!bodyback.shouldContinuePersisting()){
                level.addFreshEntity(bodyback);
            }
            bodyback.setParent(this);
        }
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    public void tick(){
        super.tick();
        updateBlobParts();
    }

    public void kill(){
        this.remove(RemovalReason.KILLED);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.blob.idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event){
        if(this.swinging){
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.blob.punch", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BlobEntity>(this, "defaultController", 0, this::predicate));
        data.addAnimationController(new AnimationController<BlobEntity>(this, "attackController", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animFactory;
    }
}
