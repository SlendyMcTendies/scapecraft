package com.slendymctendies.scapecraft.item.projectile;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class JalBatProjectileItem extends ArrowItem {

    public JalBatProjectileItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter){
        Arrow jDart = new Arrow(pLevel, pShooter);
        return jDart;
    }
}
