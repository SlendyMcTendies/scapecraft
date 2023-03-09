package com.slendymctendies.goonspvm.entity.render;

import com.slendymctendies.goonspvm.GoonsPvM;
import com.slendymctendies.goonspvm.entity.inferno.NibblerEntity;
import com.slendymctendies.goonspvm.entity.model.NibblerModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NibblerRenderer extends MobRenderer<NibblerEntity, EntityModel<NibblerEntity>> {

    public NibblerRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager, new NibblerModel(), 0.1f);
    }
    //Removed extraneous Parameters from NibblerRenderer
    /*, EntityModel<InfernoNibblerEntity> p_i50961_2_, float p_i50961_3_*/

    public static final ResourceLocation TEXTURE = new ResourceLocation(GoonsPvM.MOD_ID, "textures/entity/nibbler.png");

    @Override
    public ResourceLocation getTextureLocation(NibblerEntity p_110775_1_) {
        return TEXTURE;
    }
}
