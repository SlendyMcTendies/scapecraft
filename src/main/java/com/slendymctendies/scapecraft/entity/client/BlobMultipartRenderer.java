package com.slendymctendies.scapecraft.entity.client;

import com.slendymctendies.scapecraft.entity.inferno.BlobEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BlobMultipartRenderer extends MobRenderer<BlobEntity, BlobMultipartModel> {
    public BlobMultipartRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BlobMultipartModel(), 0.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(BlobEntity pEntity) {
        return null;
    }
}
