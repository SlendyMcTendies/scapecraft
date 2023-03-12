package com.slendymctendies.scapecraft.entity.model;

// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.inferno.NibblerEntity;
import net.minecraft.util.ResourceLocation;
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
}