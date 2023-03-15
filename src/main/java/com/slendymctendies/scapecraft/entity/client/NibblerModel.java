package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.inferno.NibblerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NibblerModel extends AnimatedGeoModel<NibblerEntity> {
    @Override
    public ResourceLocation getModelLocation(NibblerEntity object) {
        return new ResourceLocation(Main.MOD_ID, "geo/nibbler.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NibblerEntity object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/nibbler.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NibblerEntity animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/nibbler.animation.json");
    }
}
