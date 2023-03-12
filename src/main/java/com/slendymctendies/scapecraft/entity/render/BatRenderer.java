package com.slendymctendies.scapecraft.entity.render;

import com.slendymctendies.scapecraft.entity.inferno.BatEntity;
import com.slendymctendies.scapecraft.entity.model.BatModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BatRenderer extends GeoEntityRenderer<BatEntity> {
    public BatRenderer(EntityRendererManager rendererManager){super(rendererManager, new BatModel());}
}
