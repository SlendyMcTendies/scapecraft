package com.slendymctendies.scapecraft.entity.render;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.inferno.NibblerEntity;
import com.slendymctendies.scapecraft.entity.model.NibblerModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

/*
@OnlyIn(Dist.CLIENT)
public class NibblerRenderer extends MobRenderer<NibblerEntity, EntityModel<NibblerEntity>> {

    public NibblerRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager, new NibblerModel(), 0.1f);
    }
    //Removed extraneous Parameters from NibblerRenderer
    //, EntityModel<InfernoNibblerEntity> p_i50961_2_, float p_i50961_3_

    public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/entity/nibbler.png");

    @Override
    public ResourceLocation getTextureLocation(NibblerEntity p_110775_1_) {
        return TEXTURE;
    }
}
 */

public class NibblerRenderer extends GeoEntityRenderer<NibblerEntity> {
    public NibblerRenderer(EntityRendererManager rendererManager){
        super(rendererManager, new NibblerModel());
    }
}