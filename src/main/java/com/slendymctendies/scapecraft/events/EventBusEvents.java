package com.slendymctendies.scapecraft.events;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.EntityHandler;
import com.slendymctendies.scapecraft.entity.inferno.NibblerEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityHandler.INFERNO_NIBBLER.get(), NibblerEntity.createAttributes().build());
    }
}
