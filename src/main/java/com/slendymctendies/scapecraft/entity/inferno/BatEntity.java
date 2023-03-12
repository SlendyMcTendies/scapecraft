package com.slendymctendies.scapecraft.entity.inferno;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;

import java.util.EnumSet;
import java.util.Random;

public class BatEntity extends FlyingEntity implements IAnimatable {

    public BatEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                .add(Attributes.MAX_HEALTH, 25D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(5, new BatEntity.RandomFlyGoal(this));
        this.goalSelector.addGoal(7, new BatEntity.LookAroundGoal(this));
        this.goalSelector.addGoal(7, new BatEntity.HungerDartGoal(this, 1.0D, 40, 10.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213812_1_) -> {
            return Math.abs(p_213812_1_.getY() - this.getY()) <= 4.0D;
        }));
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
}
