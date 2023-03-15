package com.slendymctendies.scapecraft.entity.inferno;

import com.slendymctendies.scapecraft.entity.client.JalBatRenderer;
import com.slendymctendies.scapecraft.entity.projectile.JalBatProjectileEntity;
import com.slendymctendies.scapecraft.item.ItemHandler;
import com.slendymctendies.scapecraft.item.projectile.JalBatProjectileItem;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.Random;

public class JalBatEntity extends FlyingMob implements IAnimatable, Enemy {
    private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(JalBatEntity.class, EntityDataSerializers.BOOLEAN);
    public JalBatEntity(EntityType<? extends FlyingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new JalBatEntity.BatMoveController(this);
    }

    public boolean isCharging() {return this.entityData.get(DATA_IS_CHARGING);}

    public void setCharging(boolean batCharging){this.entityData.set(DATA_IS_CHARGING, batCharging);}

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_CHARGING, false);
    }

    //Attributes
    public static AttributeSupplier setAttributes(){
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .build();
    }

    public boolean isSensitiveToWater(){return true;}
    public boolean fireImmune(){return true;}
    protected int getExperienceReward(Player player){return 0;}

    //Goals
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new JalBatEntity.ShootHungerDartGoal(this));
        this.goalSelector.addGoal(4, new JalBatEntity.RandomFlyGoal(this));
        this.goalSelector.addGoal(2, new JalBatEntity.BatLookGoal(this));
        this.goalSelector.addGoal(2, new JalBatEntity.FlyToTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, false, true, (p_32755_) -> {
            return Math.abs(p_32755_.getY() - this.getY()) <= 4.0D;
        }));
    }

    static class BatLookGoal extends Goal {
        private final JalBatEntity bat;

        public BatLookGoal(JalBatEntity pBat) {
            this.bat = pBat;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return true;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (this.bat.getTarget() == null) {
                Vec3 vec3 = this.bat.getDeltaMovement();
                this.bat.setYRot(-((float)Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI));
                this.bat.yBodyRot = this.bat.getYRot();
            } else {
                LivingEntity livingentity = this.bat.getTarget();
                //double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.bat) < 4096.0D) {
                    double d1 = livingentity.getX() - this.bat.getX();
                    double d2 = livingentity.getZ() - this.bat.getZ();
                    this.bat.setYRot(-((float)Mth.atan2(d1, d2)) * (180F / (float)Math.PI));
                    this.bat.yBodyRot = this.bat.getYRot();
                }
            }

        }
    }

    static class RandomFlyGoal extends Goal {
        private final JalBatEntity bat;

        public RandomFlyGoal(JalBatEntity pBat) {
            this.bat = pBat;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            MoveControl movecontrol = this.bat.getMoveControl();
            if (!movecontrol.hasWanted()) {
                return true;
            } else {
                double d0 = movecontrol.getWantedX() - this.bat.getX();
                double d1 = movecontrol.getWantedY() - this.bat.getY();
                double d2 = movecontrol.getWantedZ() - this.bat.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            Random random = this.bat.getRandom();
            double d0 = this.bat.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.bat.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.bat.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.bat.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }
    }

    static class FlyToTargetGoal extends Goal {
        private final JalBatEntity bat;
        public FlyToTargetGoal(JalBatEntity pBat){
            this.bat = pBat;
        }

        public boolean canUse(){return true;}

        public boolean canContinueToUse() {return false;}

        public void start() {
            if (this.bat.getTarget() != null) {
                Random random = this.bat.getRandom();
                double d0 = this.bat.getTarget().getX() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
                double d1 = this.bat.getTarget().getY() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
                double d2 = this.bat.getTarget().getZ() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
                this.bat.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
            }
        }
    }

    //Hunger Dart Goal
    static class ShootHungerDartGoal extends Goal {
        private final JalBatEntity bat;
        public int chargeTime;

        public ShootHungerDartGoal(JalBatEntity pBat) {
            this.bat = pBat;
        }

        public boolean canUse() {
            return this.bat.getTarget() != null;
        }

        public void start() {
            this.chargeTime = 0;
        }

        public void stop() {
            this.bat.setCharging(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = this.bat.getTarget();
            if (livingentity != null) {
                if (livingentity.distanceToSqr(this.bat) < 4096.0D && this.bat.hasLineOfSight(livingentity)) {
                    Level level = this.bat.level;
                    ++this.chargeTime;

                    if (this.chargeTime >= 20) {
                        Vec3 vec3 = this.bat.getViewVector(1.0F);
                        double d2 = livingentity.getX() - (this.bat.getX() + vec3.x * 4.0D);
                        double d3 = livingentity.getY(0.5D) - (0.5D + this.bat.getY(0.5D));
                        double d4 = livingentity.getZ() - (this.bat.getZ() + vec3.z * 4.0D);

                        //JalBatProjectileItem dartItem = new JalBatProjectileItem(new Item.Properties());
                        JalBatProjectileEntity dartEntity = new JalBatProjectileEntity(this.bat, level);
                        SoundEvent shootSound = SoundEvents.ARROW_SHOOT;
                        dartEntity.setPos(this.bat.getX() + vec3.x * 4.0D, this.bat.getY(0.5D) + 0.5D, dartEntity.getZ() + vec3.z * 4.0D);
                        dartEntity.shoot(d2, d3+0.5d, d4, 3.0f, 2.0f);
                        dartEntity.playSound(shootSound, 1.0f, 1.0f);
                        MobEffectInstance hungerEffect = new MobEffectInstance(MobEffects.HUNGER, 400, 1);
                        dartEntity.addEffect(hungerEffect);
                        level.addFreshEntity(dartEntity);
                        this.chargeTime = -40;
                    }
                } else if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

                this.bat.setCharging(this.chargeTime > 10);
            }
        }
    }

    //Movement Controller
    static class BatMoveController extends MoveControl{
        private final JalBatEntity bat;
        private int lingerDuration;

        public BatMoveController(JalBatEntity pBat){
            super(pBat);
            this.bat = pBat;
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.lingerDuration-- <= 0) {
                    this.lingerDuration += this.bat.getRandom().nextInt(5) + 2;
                    Vec3 vec3 = new Vec3(this.wantedX - this.bat.getX(), this.wantedY - this.bat.getY(), this.wantedZ - this.bat.getZ());
                    double d0 = vec3.length();
                    vec3 = vec3.normalize();
                    if (this.canReach(vec3, Mth.ceil(d0))) {
                        this.bat.setDeltaMovement(this.bat.getDeltaMovement().add(vec3.scale(0.1D)));
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 pPos, int pLength) {
            AABB aabb = this.bat.getBoundingBox();

            for(int i = 1; i < pLength; ++i) {
                aabb = aabb.move(pPos);
                if (!this.bat.level.noCollision(this.bat, aabb)) {
                    return false;
                }
            }

            return true;
        }
    }

    //Animation Factory
    private AnimationFactory animFactory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event){
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bat.flap", true));
        return PlayState.CONTINUE;
    }
/*
    private PlayState attackPredicate(AnimationEvent event){
        event.getController().markNeedsReload();
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bat.dartattack", false));
        return PlayState.CONTINUE;
    }
*/
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<JalBatEntity>(this, "defaultController", 0, this::predicate));
        //data.addAnimationController(new AnimationController<JalBatEntity>(this, "attackController", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animFactory;
    }
}
