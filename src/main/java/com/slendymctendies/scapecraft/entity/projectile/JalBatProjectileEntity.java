/*
package com.slendymctendies.scapecraft.entity.projectile;

import com.slendymctendies.scapecraft.entity.EntityHandler;
import com.slendymctendies.scapecraft.item.ItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

public class JalBatProjectileEntity extends AbstractArrowEntity {
    private final Item referenceItem;
    public JalBatProjectileEntity(EntityType<? extends AbstractArrowEntity> type, World world) {
        super(type, world);
        this.referenceItem = ItemHandler.JALBATPROJECTILE.get();
    }

    public JalBatProjectileEntity(LivingEntity shooter, World world, Item referenceItem) {
        super(EntityHandler.JALBATPROJECTILE_ENTITY.get(), shooter, world);
        this.referenceItem = referenceItem;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }
}
*/