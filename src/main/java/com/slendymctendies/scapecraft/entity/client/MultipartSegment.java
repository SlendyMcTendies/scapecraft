package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.entity.EntityHandler;
import com.slendymctendies.scapecraft.entity.MultipartEntityBase;
import com.slendymctendies.scapecraft.entity.inferno.BlobEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class MultipartSegment extends MultipartEntityBase {
    private Entity eSegment;

    public MultipartSegment(EntityType<?> t, Level world) {
        super(t, world);
    }

    public MultipartSegment(EntityType<?> type, Entity pEntity, float radius, float angleYaw, float offsetY, float sizeX, float sizeY, float damageMultiplier) {
        super(type, pEntity, radius, angleYaw, offsetY, sizeX, sizeY, damageMultiplier);
        this.eSegment = pEntity;
    }

    public MultipartSegment(Entity parent, float radius, float angleYaw, float offsetY, float sizeX, float sizeY, float damageMultiplier) {
        super(EntityHandler.MULTIPART_SEGMENT.get(), parent, radius, angleYaw, offsetY, sizeX, sizeY, damageMultiplier);
        this.eSegment = parent;
    }

    @Override
    public void collideWithNearbyEntities() {
    }
}
