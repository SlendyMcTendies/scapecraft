package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.inferno.JalBatEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JalBatModel extends AnimatedGeoModel<JalBatEntity> {
    @Override
    public ResourceLocation getModelLocation(JalBatEntity object) {
        return new ResourceLocation(Main.MOD_ID, "geo/bat.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(JalBatEntity object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/bat.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(JalBatEntity animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/bat.animation.json");
    }
}
