package com.slendymctendies.scapecraft.entity.model;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.inferno.BatEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

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

    @Override
    public void setLivingAnimations(BatEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone Entity = this.getAnimationProcessor().getBone("Entity");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (Entity != null){
            Entity.setRotationY(extraData.netHeadYaw * ((float) Math.PI/180F));
            Entity.setRotationX(extraData.headPitch * ((float) Math.PI/180));
            //Entity.setRotationY(extraData.headPitch * ((float) Math.PI /*/ 180F*/));
            //Entity.setRotationY(extraData.netHeadYaw);
        }
    }
}
