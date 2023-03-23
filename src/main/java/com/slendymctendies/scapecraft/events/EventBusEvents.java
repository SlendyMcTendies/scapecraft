package com.slendymctendies.scapecraft.events;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.EntityHandler;
import com.slendymctendies.scapecraft.entity.inferno.BlobEntity;
import com.slendymctendies.scapecraft.entity.inferno.JalBatEntity;
import com.slendymctendies.scapecraft.entity.inferno.NibblerEntity;
import com.slendymctendies.scapecraft.entity.inferno.SnakeEntity;
import com.slendymctendies.scapecraft.entity.projectile.JalBatProjectileRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventBusEvents {

    @SubscribeEvent
    public static void setAttributesEvent(EntityAttributeCreationEvent event){
        event.put(EntityHandler.NIBBLER.get(), NibblerEntity.setAttributes());
        event.put(EntityHandler.JALBAT.get(), JalBatEntity.setAttributes());
        event.put(EntityHandler.BLOB.get(), BlobEntity.setAttributes());
        event.put(EntityHandler.SNAKE.get(), SnakeEntity.setAttributes());
    }

    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(EntityHandler.JALBAT_PROJECTILEENTITY.get(), JalBatProjectileRenderer::new);
    }
}
