package com.slendymctendies.scapecraft.entity.model;

// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.inferno.NibblerEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NibblerModel extends AnimatedGeoModel<NibblerEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(Main.MOD_ID, "geo/nibbler.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/entity/nibbler.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(Main.MOD_ID, "animations/nibbler.animation.json");

	@Override
	public ResourceLocation getModelLocation(NibblerEntity object) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(NibblerEntity object) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(NibblerEntity animatable) {
		return ANIMATIONS;
	}

	//Old non-Geckolib Code
	/*
	private final ModelRenderer Entity;
	private final ModelRenderer Body;
	private final ModelRenderer PincerLeft;
	private final ModelRenderer Tooth_r1;
	private final ModelRenderer PincerRight;
	private final ModelRenderer Tooth_r2;

	public NibblerModel() {
		texWidth = 32;
		texHeight = 32;

		Entity = new ModelRenderer(this);
		Entity.setPos(0.0F, 24.0F, 2.0F);
		

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 0.0F, 0.0F);
		Entity.addChild(Body);
		Body.texOffs(0, 0).addBox(-3.0F, -6.0F, -6.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		Body.texOffs(0, 12).addBox(-2.0F, -4.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		Body.texOffs(20, 12).addBox(-1.0F, -2.0F, 4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		PincerLeft = new ModelRenderer(this);
		PincerLeft.setPos(0.0F, 0.0F, 0.0F);
		Body.addChild(PincerLeft);
		PincerLeft.texOffs(18, 0).addBox(2.0F, -3.0F, -9.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		Tooth_r1 = new ModelRenderer(this);
		Tooth_r1.setPos(3.0F, -2.0F, -9.0F);
		PincerLeft.addChild(Tooth_r1);
		setRotationAngle(Tooth_r1, 0.0F, 0.3491F, 0.0F);
		Tooth_r1.texOffs(0, 20).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		PincerRight = new ModelRenderer(this);
		PincerRight.setPos(-6.0F, 0.0F, 0.0F);
		Body.addChild(PincerRight);
		PincerRight.texOffs(12, 16).addBox(2.0F, -3.0F, -9.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		Tooth_r2 = new ModelRenderer(this);
		Tooth_r2.setPos(3.0F, -2.0F, -9.0F);
		PincerRight.addChild(Tooth_r2);
		setRotationAngle(Tooth_r2, 0.0F, -0.3491F, 0.0F);
		Tooth_r2.texOffs(12, 12).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
	}



	@Override
	public void setupAnim(NibblerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Entity.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	 */

}