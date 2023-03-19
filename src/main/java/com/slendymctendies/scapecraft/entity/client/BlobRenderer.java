package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.entity.inferno.BlobEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class BlobRenderer extends GeoEntityRenderer<BlobEntity> {
    public BlobRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BlobModel());
        this.shadowRadius = 1.2f;
    }
}
