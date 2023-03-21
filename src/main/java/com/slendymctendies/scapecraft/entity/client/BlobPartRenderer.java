package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.entity.inferno.BlobEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BlobPartRenderer extends GeoEntityRenderer<BlobEntity> {
    public BlobPartRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BlobModel());
        this.shadowRadius = 0.0f;
    }
}
