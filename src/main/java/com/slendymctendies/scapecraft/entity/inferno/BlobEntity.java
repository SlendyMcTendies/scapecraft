package com.slendymctendies.scapecraft.entity.inferno;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BlobEntity extends Monster implements IAnimatable {

    private BlobPartEntity body_front;
    private BlobPartEntity body_back;
    private final BlobPartEntity[] blobParts;

    public final double[][] ringBuffer = new double[64][3];
    public int ringBufferIndex = -1;
    private AnimationFactory animFactory = new AnimationFactory(this);
    public BlobEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.body_front = new BlobPartEntity(this, 1.0f, 1.6875f);
        this.body_back = new BlobPartEntity(this, 0.875f, 0.875f);
        this.blobParts = new BlobPartEntity[]{this.body_front,this.body_back};
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
    }

    public void kill(){
        this.remove(RemovalReason.KILLED);
    }

    public void scaleParts() {
        for (BlobPartEntity parts : blobParts) {
            float prev = parts.scale;
            parts.scale = 1f;
            if (prev != parts.scale) {
                parts.refreshDimensions();
            }
        }
    }

    public void aiStep() {
        super.aiStep();
        scaleParts();

        float rPitch = (float) -((float) this.getDeltaMovement().y * (double) (180F / (float) Math.PI));

        if (this.isOnGround()) {
            this.setXRot(0);
        }

        /*
        if (this.tickCount % 200 == 0) {
            this.heal(2);
        }
        */

        this.yHeadRot = this.getYRot();
        this.yBodyRot = this.getYRot();

        if (!this.isNoAi()) {
            if (this.ringBufferIndex < 0) {
                for (int i = 0; i < this.ringBuffer.length; ++i) {
                    this.ringBuffer[i][0] = this.getYRot();
                    this.ringBuffer[i][1] = this.getY();
                }
            }
            this.ringBufferIndex++;
            if (this.ringBufferIndex == this.ringBuffer.length) {
                this.ringBufferIndex = 0;
            }
            this.ringBuffer[this.ringBufferIndex][0] = this.getYRot();
            this.ringBuffer[ringBufferIndex][1] = this.getY();
            Vec3[] avector3d = new Vec3[this.blobParts.length];

            for (int j = 0; j < this.blobParts.length; ++j) {
                this.blobParts[j].collideWithNearbyEntities();
                avector3d[j] = new Vec3(this.blobParts[j].getX(), this.blobParts[j].getY(), this.blobParts[j].getZ());
            }
            float f4 = Mth.sin(this.getYRot() * ((float) Math.PI / 180F) - 0 * 0.01F);
            float f19 = Mth.cos(this.getYRot() * ((float) Math.PI / 180F) - 0 * 0.01F);
            float f15 = (float) (this.getMovementOffsets(5, 1.0F)[1] - this.getMovementOffsets(10, 1.0F)[1]) * 10.0F * ((float) Math.PI / 180F);
            float f16 = Mth.cos(f15);
            float f2 = Mth.sin(f15);
            float f17 = this.getYRot() * ((float) Math.PI / 180F);
            float pitch = this.getXRot() * ((float) Math.PI / 180F);
            float f3 = Mth.sin(f17) * (1 - Math.abs(this.getXRot() / 90F));
            float f18 = Mth.cos(f17) * (1 - Math.abs(this.getXRot() / 90F));

            this.setPartPosition(this.body_front, 1F, 1F, 1F);
            this.setPartPosition(this.body_back, (f3) * -3.5F, -pitch * 3F, (f18) * 3.5F);
            double[] adouble = this.getMovementOffsets(5, 1.0F);


            for (int k = 0; k < 3; ++k) {
                /*
                BlobPartEntity enderdragonpartentity = null;
                if (k == 0) {
                    enderdragonpartentity = this.tail1Part;
                }
                if (k == 1) {
                    enderdragonpartentity = this.tail2Part;
                }
                if (k == 2) {
                    enderdragonpartentity = this.tail3Part;
                }
                */

                double[] adouble1 = this.getMovementOffsets(15 + k * 5, 1.0F);
                float f7 = this.getYRot() * ((float) Math.PI / 180F) + (float) Mth.wrapDegrees(adouble1[0] - adouble[0]) * ((float) Math.PI / 180F);
                float f20 = Mth.sin(f7) * (1 - Math.abs(this.getXRot() / 90F));
                float f21 = Mth.cos(f7) * (1 - Math.abs(this.getXRot() / 90F));
                float f22 = -3.6F;
                float f23 = (float) (k + 1) * f22 - 2F;
                //this.setPartPosition(enderdragonpartentity, -(f3 * 0.5F + f20 * f23) * f16, pitch * 1.5F * (k + 1), (f18 * 0.5F + f21 * f23) * f16);
            }


            for (int l = 0; l < this.blobParts.length; ++l) {
                this.blobParts[l].xo = avector3d[l].x;
                this.blobParts[l].yo = avector3d[l].y;
                this.blobParts[l].zo = avector3d[l].z;
                this.blobParts[l].xOld = avector3d[l].x;
                this.blobParts[l].yOld = avector3d[l].y;
                this.blobParts[l].zOld = avector3d[l].z;
            }
        }
        if (!level.isClientSide) {
            LivingEntity target = this.getTarget();
            if (target instanceof Player && ((Player) target).isCreative()) {
                this.setTarget(null);
            }
        }
    }

    private void setPartPosition(BlobPartEntity part, double offsetX, double offsetY, double offsetZ) {
        part.setPos(this.getX() + offsetX * part.scale, this.getY() + offsetY * part.scale, this.getZ() + offsetZ * part.scale);
    }


    public double[] getMovementOffsets(int p_70974_1_, float partialTicks) {
        if (this.isDeadOrDying()) {
            partialTicks = 0.0F;
        }

        partialTicks = 1.0F - partialTicks;
        int i = this.ringBufferIndex - p_70974_1_ & 63;
        int j = this.ringBufferIndex - p_70974_1_ - 1 & 63;
        double[] adouble = new double[3];
        double d0 = this.ringBuffer[i][0];
        double d1 = this.ringBuffer[j][0] - d0;
        adouble[0] = d0 + d1 * (double) partialTicks;
        d0 = this.ringBuffer[i][1];
        d1 = this.ringBuffer[j][1] - d0;
        adouble[1] = d0 + d1 * (double) partialTicks;
        adouble[2] = Mth.lerp(partialTicks, this.ringBuffer[i][2], this.ringBuffer[j][2]);
        return adouble;
    }


    public boolean attackEntityPartFrom(BlobPartEntity blobPart, DamageSource source, float amount) {
        return this.hurt(source, amount);
    }

    public void push(Entity entityIn){

    }

    public void pushEntities(){

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
