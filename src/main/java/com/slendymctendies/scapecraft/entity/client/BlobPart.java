package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.entity.inferno.BlobEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityDimensions;

public class BlobPart extends net.minecraftforge.entity.PartEntity<BlobEntity> {
    public final BlobEntity parentMob;
    public final String name;
    private final EntityDimensions size;

    public BlobPart(BlobEntity parent, String pName, float pWidth, float pHeight) {
        super(parent);
        this.size = EntityDimensions.scalable(pWidth, pHeight);
        this.refreshDimensions();
        this.parentMob = parent;
        this.name = pName;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }
}
