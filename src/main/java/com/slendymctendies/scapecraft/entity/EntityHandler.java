package com.slendymctendies.scapecraft.entity;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.inferno.JalBatEntity;
import com.slendymctendies.scapecraft.entity.inferno.NibblerEntity;
import com.slendymctendies.scapecraft.entity.projectile.JalBatProjectileEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityHandler {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MOD_ID);

    public static final RegistryObject<EntityType<NibblerEntity>> NIBBLER = ENTITY_TYPES.register("nibbler",
            () -> EntityType.Builder.of(NibblerEntity::new, MobCategory.MONSTER)
                    .sized(0.375f, 0.375f)
                    .build(new ResourceLocation(Main.MOD_ID, "nibbler").toString()));

    public static final RegistryObject<EntityType<JalBatEntity>> JALBAT = ENTITY_TYPES.register("jalbat",
            () -> EntityType.Builder.of(JalBatEntity::new, MobCategory.MONSTER)
                    .sized(0.375f, 1.75f)
                    .build(new ResourceLocation(Main.MOD_ID, "jalbat").toString()));

    public static final RegistryObject<EntityType<JalBatProjectileEntity>> JALBAT_PROJECTILEENTITY = ENTITY_TYPES.register("jalbat_projectile_entity",
            () -> EntityType.Builder.<JalBatProjectileEntity>of(JalBatProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(new ResourceLocation(Main.MOD_ID, "jalbat_projectile_entity").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
