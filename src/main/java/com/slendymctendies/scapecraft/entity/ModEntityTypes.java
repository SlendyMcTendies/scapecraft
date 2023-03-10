package com.slendymctendies.scapecraft.entity;

import com.slendymctendies.scapecraft.Scapecraft;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Scapecraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Scapecraft.MOD_ID);

    /*
    public static final RegistryObject<EntityType<NibblerEntity>> INFERNO_NIBBLER = ENTITY_TYPES.register("inferno_nibbler",
            () -> EntityType.Builder.of(NibblerEntity::new, EntityClassification.MONSTER).sized(0.375f, 0.375f)
                    .build(new ResourceLocation(GoonsPvM.MOD_ID, "inferno_nibbler").toString()));

    @SubscribeEvent
    public static void onCreateAttribiutes(EntityAttributeCreationEvent event){
        event.put(ModEntityTypes.INFERNO_NIBBLER.get(), NibblerEntity.createAttributes().build());
    }
*/
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
