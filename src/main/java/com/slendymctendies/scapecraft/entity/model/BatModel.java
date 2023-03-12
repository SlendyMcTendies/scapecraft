package com.slendymctendies.scapecraft.entity.model;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.inferno.BatEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BatModel extends AnimatedGeoModel<BatEntity> {
    private static final ResourceLocation MODEL = new ResourceLocation(Main.MOD_ID, "geo/bat.geo.json");
    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/entity/bat.png");
    private static final ResourceLocation ANIMATIONS = new ResourceLocation(Main.MOD_ID, "animations/bat.animation.json");

    @Override
    public ResourceLocation getModelLocation(BatEntity object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureLocation(BatEntity object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BatEntity animatable) {
        return ANIMATIONS;
    }
}
