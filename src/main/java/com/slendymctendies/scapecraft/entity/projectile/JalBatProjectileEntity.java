package com.slendymctendies.scapecraft.entity.projectile;

import com.google.common.collect.Sets;
import com.slendymctendies.scapecraft.entity.EntityHandler;
import com.slendymctendies.scapecraft.item.ItemHandler;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

import java.util.Set;

public class JalBatProjectileEntity extends AbstractArrow {
    private final Item referenceItem;
    private final Set<MobEffectInstance> effects = Sets.newHashSet();
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(Arrow.class, EntityDataSerializers.INT);
    private Potion potion = Potions.EMPTY;
    public JalBatProjectileEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.referenceItem = ItemHandler.JALBAT_PROJECTILE.get();
    }

    public JalBatProjectileEntity(LivingEntity pShooter, Level pLevel, Item referenceItem) {
        super(EntityHandler.JALBAT_PROJECTILEENTITY.get(), pShooter, pLevel);
        this.referenceItem = referenceItem;
    }

    //public JalBatProjectileEntity(Level pLevel, LivingEntity pShooter) {super(EntityType.ARROW, pShooter, pLevel);}

    public void addEffect(MobEffectInstance pEffectInstance) {
        this.effects.add(pEffectInstance);
        this.getEntityData().set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
    }

    @Override
    public ItemStack getPickupItem() {
        return null;
    }
}
