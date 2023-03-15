package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.entity.inferno.JalBatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class JalBatRenderer extends GeoEntityRenderer<JalBatEntity> {

    public JalBatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new JalBatModel());
        this.shadowRadius = 0.3F;
    }
}
