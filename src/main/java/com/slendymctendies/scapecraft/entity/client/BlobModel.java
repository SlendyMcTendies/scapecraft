package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.inferno.BlobEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BlobModel extends AnimatedGeoModel<BlobEntity> {
    @Override
    public ResourceLocation getModelLocation(BlobEntity object) {
        return new ResourceLocation(Main.MOD_ID, "geo/blob.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BlobEntity object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/blob.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BlobEntity animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/blob.animation.json");
    }
}
