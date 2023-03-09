package com.slendymctendies.goonspvm.events;

import com.slendymctendies.goonspvm.GoonsPvM;
import com.slendymctendies.goonspvm.entity.ModEntityTypes;
import com.slendymctendies.goonspvm.entity.inferno.NibblerEntity;
import com.slendymctendies.goonspvm.item.ModSpawnEggItem;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GoonsPvM.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event){
        //event.put(ModEntityTypes.INFERNO_NIBBLER.get(), NibblerEntity.createMonsterAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        //ModSpawnEggItem.initSpawnEggs();
    }
}
