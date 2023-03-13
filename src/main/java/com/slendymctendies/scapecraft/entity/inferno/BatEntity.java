package com.slendymctendies.scapecraft.entity.inferno;

//import com.slendymctendies.scapecraft.entity.projectile.JalBatProjectileEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.Random;

public class BatEntity extends FlyingEntity implements IAnimatable, IMob {
    private static final DataParameter<Boolean> DATA_IS_CHARGING = EntityDataManager.defineId(BatEntity.class, DataSerializers.BOOLEAN);
    //private int explosionPower = 0;

    public BatEntity(EntityType<? extends FlyingEntity> type, World worldIn) {

        super(type, worldIn);

        this.moveControl = new BatEntity.MoveHelperController(this);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isCharging() {
        return this.entityData.get(DATA_IS_CHARGING);
    }

    public void setCharging(boolean p_175454_1_) {
        this.entityData.set(DATA_IS_CHARGING, p_175454_1_);
    }

    /*
    public int getExplosionPower() {
        return this.explosionPower;
    }
     */

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_CHARGING, false);
    }

    //--ATTRIBUTES--
    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                .add(Attributes.MAX_HEALTH, 25D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    public boolean doHurtTarget(Entity target){
        if(!super.doHurtTarget(target)){
            return false;
        } else {
            if (target instanceof LivingEntity){
                ((LivingEntity)target).addEffect(new EffectInstance(Effects.HUNGER, 400, 1));
                //addEffect(new EffectInstance(Effects.[EFFECTNAME], [DURATION IN TICKS], [AMPLIFIER - INT {Level 2 = 1; Level 1 = EMPTY}]));
            }
            return  true;
        }
    }

    public boolean isSensitiveToWater() {return true;}
    public boolean fireImmune() {return true;}
    protected int getExperienceReward(PlayerEntity player){
        return 0;
    }
    protected boolean isMovementNoisy() {
        return false;
    }

    //--GOALS--
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new BatEntity.RandomFlyGoal(this));
        this.goalSelector.addGoal(7, new BatEntity.LookAroundGoal(this));
        this.goalSelector.addGoal(0, new BatEntity.HungerDartAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213812_1_) -> Math.abs(p_213812_1_.getY() - this.getY()) <= 4.0D));
    }

    static class MoveHelperController extends MovementController {
        private final BatEntity JalBat;
        private int floatDuration;

        public MoveHelperController(BatEntity p_i45838_1_) {
            super(p_i45838_1_);
            this.JalBat = p_i45838_1_;
        }

        public void tick() {
            if (this.operation == MovementController.Action.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.JalBat.getRandom().nextInt(5) + 2;
                    Vector3d vector3d = new Vector3d(this.wantedX - this.JalBat.getX(), this.wantedY - this.JalBat.getY(), this.wantedZ - this.JalBat.getZ());
                    double d0 = vector3d.length();
                    vector3d = vector3d.normalize();
                    if (this.canReach(vector3d, MathHelper.ceil(d0))) {
                        this.JalBat.setDeltaMovement(this.JalBat.getDeltaMovement().add(vector3d.scale(0.1D)));
                    } else {
                        this.operation = MovementController.Action.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vector3d p_220673_1_, int p_220673_2_) {
            AxisAlignedBB axisalignedbb = this.JalBat.getBoundingBox();

            for(int i = 1; i < p_220673_2_; ++i) {
                axisalignedbb = axisalignedbb.move(p_220673_1_);
                if (!this.JalBat.level.noCollision(this.JalBat, axisalignedbb)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class RandomFlyGoal extends Goal {
        private final BatEntity JalBat;

        public RandomFlyGoal(BatEntity p_i45836_1_) {
            this.JalBat = p_i45836_1_;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            MovementController movementcontroller = this.JalBat.getMoveControl();
            if (!movementcontroller.hasWanted()) {
                return true;
            } else {
                double d0 = movementcontroller.getWantedX() - this.JalBat.getX();
                double d1 = movementcontroller.getWantedY() - this.JalBat.getY();
                double d2 = movementcontroller.getWantedZ() - this.JalBat.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            Random random = this.JalBat.getRandom();
            double d0 = this.JalBat.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.JalBat.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.JalBat.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.JalBat.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }
    }

    static class LookAroundGoal extends Goal {
        private final BatEntity JalBat;

        public LookAroundGoal(BatEntity p_i45839_1_) {
            this.JalBat = p_i45839_1_;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return true;
        }

        public void tick() {
            if (this.JalBat.getTarget() == null) {
                Vector3d vector3d = this.JalBat.getDeltaMovement();
                this.JalBat.yRot = -((float) MathHelper.atan2(vector3d.x, vector3d.z)) * (90F / (float)Math.PI);
                this.JalBat.yBodyRot = this.JalBat.yRot;
            } else {
                LivingEntity livingentity = this.JalBat.getTarget();
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.JalBat) < 4096.0D) {
                    double d1 = livingentity.getX() - this.JalBat.getX();
                    double d2 = livingentity.getZ() - this.JalBat.getZ();
                    this.JalBat.yRot = -((float)MathHelper.atan2(d1, d2)) * (90F / (float)Math.PI);
                    this.JalBat.yBodyRot = this.JalBat.yRot;
                }
            }

        }
    }

    static class HungerDartAttackGoal extends Goal {
        private final BatEntity JalBat;
        public int chargeTime;

        HungerDartAttackGoal(BatEntity jalBat) {
            JalBat = jalBat;
        }

        public boolean canUse() {
            return this.JalBat.getTarget() != null;
        }

        public void start() {
            this.chargeTime = 0;
        }

        public void stop() {
            this.JalBat.setCharging(false);
        }

        public void tick() {
            LivingEntity livingentity = this.JalBat.getTarget();
            double d0 = 64.0D;
            if (livingentity.distanceToSqr(this.JalBat) < 4096.0D && this.JalBat.canSee(livingentity)) {
                World world = this.JalBat.level;
                ++this.chargeTime;

                //Force Bat to look at Target, which is "livingentity"
                this.JalBat.lookControl.setLookAt(livingentity, 2.0f, 2.0f);

                if (this.chargeTime >= 20) {
                    double d1 = 4.0D;
                    Vector3d vector3d = this.JalBat.getViewVector(1.0F);
                    double d2 = livingentity.getX() - (this.JalBat.getX() + vector3d.x);
                    double d3 = livingentity.getY(0.5D) - (0.5D + this.JalBat.getY(0.5D));
                    double d4 = livingentity.getZ() - (this.JalBat.getZ() + vector3d.z);

                    //Arrow Properties & Spawning
                    ArrowEntity arrowEntity = new ArrowEntity(world, this.JalBat);
                    arrowEntity.setPos(this.JalBat.getX() + vector3d.x * 4.0D, this.JalBat.getY(0.5D) + 0.5D, arrowEntity.getZ() + vector3d.z * 4.0D);

                    // Target [X, Y, Z] Coordinates, Speed, Dispersion
                    arrowEntity.shoot(d2, d3+0.5d, d4, 3.0f, 2.0f);

                    //create and apply Hunger effect to arrow
                    EffectInstance hungerEffect = new EffectInstance(Effects.HUNGER, 400, 1);
                    arrowEntity.addEffect(hungerEffect);

                    world.addFreshEntity(arrowEntity);
                    this.chargeTime = -40;
                }
            } else if (this.chargeTime > 0) {
                this.chargeTime = 0;
            }

            this.JalBat.setCharging(this.chargeTime > 10);
        }
    }

    //--ANIMATION FACTORY--
    private AnimationFactory animFactory = GeckoLibUtil.createFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        //Bat should always be flapping. HE AINT GOT NO LEGS!
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bat.flap", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event){
        if(this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)){
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bat.dartattack", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data){
        data.addAnimationController(new AnimationController<BatEntity>(this,"defaultController", 0, this::predicate));
        data.addAnimationController(new AnimationController<BatEntity>(this, "attackController", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animFactory;
    }
}
