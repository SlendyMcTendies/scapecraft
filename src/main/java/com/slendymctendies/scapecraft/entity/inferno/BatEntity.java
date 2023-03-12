package com.slendymctendies.scapecraft.entity.inferno;

//import com.slendymctendies.scapecraft.entity.projectile.JalBatProjectileEntity;
import com.slendymctendies.scapecraft.item.ItemHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
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

public class BatEntity extends FlyingEntity implements IAnimatable, IRangedAttackMob, IMob {

    public BatEntity(EntityType<? extends FlyingEntity> type, World worldIn) {

        super(type, worldIn);

        this.moveControl = new BatEntity.MoveHelperController(this);
    }

    //--ATTRIBUTES--
    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                .add(Attributes.MAX_HEALTH, 25D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
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
        this.goalSelector.addGoal(7, new BatEntity.HungerDartGoal(this, 1.0D, 40, 10.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213812_1_) -> {
            return Math.abs(p_213812_1_.getY() - this.getY()) <= 4.0D;
        }));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float p_82196_2_) {

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
                this.JalBat.yRot = -((float) MathHelper.atan2(vector3d.x, vector3d.z)) * (180F / (float)Math.PI);
                this.JalBat.yBodyRot = this.JalBat.yRot;
            } else {
                LivingEntity livingentity = this.JalBat.getTarget();
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.JalBat) < 4096.0D) {
                    double d1 = livingentity.getX() - this.JalBat.getX();
                    double d2 = livingentity.getZ() - this.JalBat.getZ();
                    this.JalBat.yRot = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                    this.JalBat.yBodyRot = this.JalBat.yRot;
                }
            }

        }
    }

    static class HungerDartGoal extends RangedAttackGoal {
        private final BatEntity JalBat;

        public HungerDartGoal(IRangedAttackMob attacker, double var1, int var2, float var3){
            super(attacker, var1, var2, var3);
            this.JalBat = (BatEntity)attacker;
        }

        public boolean canUse() {return super.canUse();}
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
