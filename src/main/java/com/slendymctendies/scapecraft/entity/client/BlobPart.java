package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.entity.EntityHandler;
import com.slendymctendies.scapecraft.entity.MultipartEntityBase;
import com.slendymctendies.scapecraft.entity.inferno.BlobEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class BlobPart extends MultipartEntityBase {
    private BlobEntity eBlob;

    public BlobPart(EntityType<?> t, Level world) {
        super(t, world);
    }

    public BlobPart(EntityType<?> type, BlobEntity pBlob, float radius, float angleYaw, float offsetY, float sizeX, float sizeY, float damageMultiplier) {
        super(type, pBlob, radius, angleYaw, offsetY, sizeX, sizeY, damageMultiplier);
        this.eBlob = pBlob;
    }

    public BlobPart(BlobEntity parent, float radius, float angleYaw, float offsetY, float sizeX, float sizeY, float damageMultiplier) {
        super(EntityHandler.BLOB_PART.get(), parent, radius, angleYaw, offsetY, sizeX, sizeY, damageMultiplier);
        this.eBlob = parent;
    }

    @Override
    public void collideWithNearbyEntities() {
    }
}
