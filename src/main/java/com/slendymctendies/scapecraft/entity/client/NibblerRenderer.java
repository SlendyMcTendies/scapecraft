package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.entity.inferno.NibblerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NibblerRenderer extends GeoEntityRenderer<NibblerEntity> {
    public NibblerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NibblerModel());
        this.shadowRadius = 0.3f;
    }
}
