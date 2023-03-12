package com.slendymctendies.scapecraft.entity.render;

import com.slendymctendies.scapecraft.entity.inferno.NibblerEntity;
import com.slendymctendies.scapecraft.entity.model.NibblerModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NibblerRenderer extends GeoEntityRenderer<NibblerEntity> {
    public NibblerRenderer(EntityRendererManager rendererManager){
        super(rendererManager, new NibblerModel());
    }
}