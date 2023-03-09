package com.slendymctendies.goonspvm.entity.model;

// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.slendymctendies.goonspvm.entity.inferno.NibblerEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import java.util.function.Consumer;

public class NibblerModel extends EntityModel<NibblerEntity> {
	private final ModelRenderer Hitbox;
	private final ModelRenderer Body;
	private final ModelRenderer PincerLeft;
	private final ModelRenderer LeftB_r1;
	private final ModelRenderer PincerRight;
	private final ModelRenderer RightB_r1;

	public NibblerModel() {
		int texWidth = 64;
		int texHeight = 64;

		Hitbox = new ModelRenderer(this);
		Hitbox.setPos(0.0F, 24.0F, 0.0F);
		Hitbox.texOffs(25, 42).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 24.0F, 0.0F);
		Body.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		Body.texOffs(24, 0).addBox(-2.0F, -4.0F, 3.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		Body.texOffs(8, 18).addBox(-1.0F, -2.0F, 7.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		PincerLeft = new ModelRenderer(this);
		PincerLeft.setPos(0.0F, 24.0F, 0.0F);
		PincerLeft.texOffs(12, 12).addBox(2.0F, -3.0F, -6.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		LeftB_r1 = new ModelRenderer(this);
		LeftB_r1.setPos(2.5F, 0.0F, -6.5F);
		PincerLeft.addChild(LeftB_r1);
		setRotationAngle(LeftB_r1, 0.0F, 0.3927F, 0.0F);
		LeftB_r1.texOffs(24, 12).addBox(-0.5F, -2.5F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		PincerRight = new ModelRenderer(this);
		PincerRight.setPos(0.0F, 24.0F, 0.0F);
		PincerRight.texOffs(0, 12).addBox(-4.0F, -3.0F, -6.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		RightB_r1 = new ModelRenderer(this);
		RightB_r1.setPos(-2.5F, -2.0F, -6.5F);
		PincerRight.addChild(RightB_r1);
		setRotationAngle(RightB_r1, 0.0F, -0.3927F, 0.0F);
		RightB_r1.texOffs(0, 18).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(NibblerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Hitbox.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		PincerLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		PincerRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		//this.Body.xRot = ((float) Math.PI/360F);
		//this.PincerLeft.xRot = ((float) Math.PI/20);
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
/*
	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {

	}
*/
	@Override
	public Consumer<ModelRenderer> andThen(Consumer<? super ModelRenderer> after) {
		return super.andThen(after);
	}
}