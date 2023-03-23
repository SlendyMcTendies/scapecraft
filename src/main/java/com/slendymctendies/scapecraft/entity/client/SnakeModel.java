package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.inferno.SnakeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SnakeModel extends AnimatedGeoModel<SnakeEntity> {
    @Override
    public ResourceLocation getModelLocation(SnakeEntity object) {
        return new ResourceLocation(Main.MOD_ID, "geo/snake.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SnakeEntity object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/snake.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SnakeEntity animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/snake.animation.json");
    }
}
