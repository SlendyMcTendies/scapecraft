package com.slendymctendies.scapecraft.entity.projectile;

import com.slendymctendies.scapecraft.entity.EntityHandler;
import com.slendymctendies.scapecraft.item.ItemHandler;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class JalBatProjectileEntity extends AbstractArrow {
    private final Item referenceItem;
    public JalBatProjectileEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.referenceItem = ItemHandler.JALBAT_PROJECTILE.get();
    }

    public JalBatProjectileEntity(LivingEntity pShooter, Level pLevel, Item referenceItem) {
        super(EntityHandler.JALBAT_PROJECTILEENTITY.get(), pShooter, pLevel);
        this.referenceItem = referenceItem;
    }

    @Override
    public ItemStack getPickupItem() {
        return null;
    }
}
