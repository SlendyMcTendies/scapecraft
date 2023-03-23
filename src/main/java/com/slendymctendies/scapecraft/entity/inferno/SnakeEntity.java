package com.slendymctendies.scapecraft.entity.inferno;

import com.slendymctendies.scapecraft.entity.client.MultipartSegment;
import net.minecraft.world.entity.EntityType;
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

public class SnakeEntity extends Monster implements IAnimatable {

    private MultipartSegment[] subEntities;
    private MultipartSegment body;
    private MultipartSegment head;
    private MultipartSegment tail1;
    private MultipartSegment tail2;
    private MultipartSegment tail3;

    private AnimationFactory animFactory = new AnimationFactory(this);

    public SnakeEntity(EntityType<? extends Monster> pEntityType, Level pLevel){
        super(pEntityType, pLevel);
        this.head = new MultipartSegment(this, 0.9f, 0.0f, 4.75f, 0.875f, 0.786f, 2.0f);
        this.body = new MultipartSegment(this, 0.0f, 0.0f, 0.0f, 1.0f, 5.3125f, 1.0f);
        this.tail1 = new MultipartSegment(this, -0.9375f, 0.0f, 0.0f, 1.0f,1.0f, 1.0f);
        this.tail2 = new MultipartSegment(this, -2.3125f, 0.0f, 0.0f, 1.0f,1.0f, 0.5f);
        this.tail3 = new MultipartSegment(this, -3.5f, 0.0f, 0.0f, 0.75f,1.0f, 0.25f);
        this.subEntities = new MultipartSegment[]{this.body, this.head, this.tail1, this.tail2, this.tail3};
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 75.0D)
                .add(Attributes.ATTACK_DAMAGE, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.ATTACK_SPEED, 0.25)
                .build();
    }

    public boolean isSensitiveToWater() {return true;}
    public boolean fireImmune() { return true;}
    protected int getExperienceReward(Player player){return 0;}

    protected void registerGoals() {
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.5F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    /* Keep method in case it is needed */
    public void resetSnakeParts(float scale){
        removeSnakeParts();
        head = new MultipartSegment(this, 0.9f, 0.0f, 4.75f, 0.875f, 0.786f, 2.0f);
        head.copyPosition(this);
        head.setParent(this);
        body = new MultipartSegment(this, 0.0f, 0.0f, 0.0f, 1.0f, 5.3125f, 1.0f);
        body.copyPosition(this);
        body.setParent(this);
        this.tail1 = new MultipartSegment(this, -0.9375f, 0.0f, 0.0f, 1.0f,1.0f, 1.0f);
        tail1.copyPosition(this);
        tail1.setParent(this);
        this.tail2 = new MultipartSegment(this, -2.3125f, 0.0f, 0.0f, 1.0f,1.0f, 0.5f);
        tail2.copyPosition(this);
        tail2.setParent(this);
        this.tail3 = new MultipartSegment(this, -3.6875f, 0.0f, 0.0f, 0.75f,1.0f, 0.25f);
        tail3.copyPosition(this);
        tail3.setParent(this);
    }

    public void removeSnakeParts(){
        if(head != null){
            head.remove(RemovalReason.DISCARDED);
            head = null;
        }
        if(body != null){
            body.remove(RemovalReason.DISCARDED);
            body = null;
        }
        if(tail1 != null){
            tail1.remove(RemovalReason.DISCARDED);
            tail1 = null;
        }
        if(tail2 != null){
            tail2.remove(RemovalReason.DISCARDED);
            tail2 = null;
        }
        if(tail3 != null){
            tail3.remove(RemovalReason.DISCARDED);
            tail3 = null;
        }
    }

    public void updateSnakeParts() {
        if(head != null){
            if(!head.shouldContinuePersisting()){
                level.addFreshEntity(head);
            }
            head.setParent(this);
        }
        if(body != null){
            if(!body.shouldContinuePersisting()){
                level.addFreshEntity(body);
            }
            body.setParent(this);
        }
        if(tail1 != null){
            if(!tail1.shouldContinuePersisting()){
                level.addFreshEntity(tail1);
            }
            tail1.setParent(this);
        }
        if(tail2 != null){
            if(!tail2.shouldContinuePersisting()){
                level.addFreshEntity(tail2);
            }
            tail2.setParent(this);
        }
        if(tail3 != null){
            if(!tail3.shouldContinuePersisting()){
                level.addFreshEntity(tail3);
            }
            tail3.setParent(this);
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
        updateSnakeParts();
    }

    public void kill(){
        this.remove(RemovalReason.KILLED);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.snake.walk2", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event){
        event.getController().markNeedsReload();
        if(this.swinging){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.snake.attack", false));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<SnakeEntity>(this, "defaultController", 0, this::predicate));
        data.addAnimationController(new AnimationController<SnakeEntity>(this, "attackController", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animFactory;
    }
}
