package com.slendymctendies.scapecraft.entity;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.inferno.BatEntity;
import com.slendymctendies.scapecraft.entity.inferno.NibblerEntity;
//import com.slendymctendies.scapecraft.entity.projectile.JalBatProjectileEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityHandler {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MOD_ID);

    public static final RegistryObject<EntityType<NibblerEntity>> INFERNO_NIBBLER =
            ENTITY_TYPES.register("inferno_nibbler", () -> EntityType.Builder.of(NibblerEntity::new, EntityClassification.MONSTER)
                    .sized(0.375f, 0.375f).build(new ResourceLocation(Main.MOD_ID, "inferno_nibbler").toString()));

    public static final RegistryObject<EntityType<BatEntity>> INFERNO_BAT =
            ENTITY_TYPES.register("inferno_bat", () -> EntityType.Builder.of(BatEntity::new, EntityClassification.MONSTER)
                    .sized(0.375f, 1.75f).build(new ResourceLocation(Main.MOD_ID, "inferno_bat").toString()));
/*
    public static final RegistryObject<EntityType<JalBatProjectileEntity>> JALBATPROJECTILE_ENTITY =
            ENTITY_TYPES.register("jalbatprojectile_entity", () -> EntityType.Builder.<JalBatProjectileEntity>of(JalBatProjectileEntity::new, EntityClassification.MISC)
                    .sized(0.5f, 0.f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(Main.MOD_ID, "jalbatprojectile").toString()));
*/
    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
