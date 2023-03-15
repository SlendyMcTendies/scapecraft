package com.slendymctendies.scapecraft.entity.projectile;

import com.slendymctendies.scapecraft.Main;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JalBatProjectileRenderer extends ArrowRenderer<JalBatProjectileEntity> {
    public JalBatProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(JalBatProjectileEntity pEntity) {
        Item referenceItem = pEntity.getPickupItem().getItem();
        return new ResourceLocation(Main.MOD_ID, "textures/entity/projectiles/" + referenceItem.getRegistryName().getPath() + ".png");
    }
}
