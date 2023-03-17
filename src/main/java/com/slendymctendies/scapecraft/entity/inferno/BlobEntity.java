package com.slendymctendies.scapecraft.entity.inferno;

import com.slendymctendies.scapecraft.entity.client.BlobPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BlobEntity extends Monster implements IAnimatable {

    private BlobPart body_front;
    private BlobPart body_back;

/*  --RIPPED FROM ENDER DRAGON
    private final BlobPart[] subEntities;
    public final BlobPart bodyfront;
    public final BlobPart bodyback;

 */
    private AnimationFactory animFactory = new AnimationFactory(this);
    public BlobEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        //this.bodyfront = new BlobPart(this, "body_front", 1.0f, 1.69f);
        //this.bodyback = new BlobPart(this, "body_back", 1.0f, 1.0f);
        //this.subEntities = new BlobPart[]{this.bodyfront, this.bodyback};
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

    public void resetBlobParts(float scale){
        removeBlobParts();
        body_front = new BlobPart(this, 1.0f, 0f, 0.0f, 1.0f, 1.0f, 1.0f);
        body_front.copyPosition(this);
        body_front.setParent(this);
        body_back = new BlobPart(this, 1.0f, 0f, 1.0f, 1.0f, 1.0f, 1.0f);
        body_back.copyPosition(this);
        body_back.setParent(this);
    }

    public void removeBlobParts(){
        if(body_front != null){
            body_front.remove(RemovalReason.DISCARDED);
            body_front = null;
        }
        if(body_back != null){
            body_back.remove(RemovalReason.DISCARDED);
            body_back = null;
        }
    }

    public void updateBlobParts() {
        if(body_front != null){
            if(!body_front.shouldContinuePersisting()){
                level.addFreshEntity(body_front);
            }
            body_front.setParent(this);
        }
        if(body_back != null){
            if(!body_back.shouldContinuePersisting()){
                level.addFreshEntity(body_back);
            }
            body_back.setParent(this);
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

/* --RIPPED FROM ENDER DRAGON
    private void tickPart(BlobPart pPart, double pOffsetX, double pOffsetY, double pOffsetZ) {
        pPart.setPos(this.getX() + pOffsetX, this.getY() + pOffsetY, this.getZ() + pOffsetZ);
    }
/*
    public void aiStep() {
        //Vec3 vec3 = this.getDeltaMovement();

        this.setYRot(Mth.wrapDegrees(this.getYRot()));

        this.yBodyRot = this.getYRot();
        Vec3[] avec3 = new Vec3[this.subEntities.length];

        for(int j = 0; j < this.subEntities.length; ++j) {
            avec3[j] = new Vec3(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
        }

        this.tickPart(this.bodyfront, 0.0D, 0.0D, 0.0D);
        this.tickPart(this.bodyback, -1.0D, 0.0D, 1.0D);

        for(int l = 0; l < this.subEntities.length; ++l) {
            this.subEntities[l].xo = avec3[l].x;
            this.subEntities[l].yo = avec3[l].y;
            this.subEntities[l].zo = avec3[l].z;
            this.subEntities[l].xOld = avec3[l].x;
            this.subEntities[l].yOld = avec3[l].y;
            this.subEntities[l].zOld = avec3[l].z;
        }
    }
*/
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
