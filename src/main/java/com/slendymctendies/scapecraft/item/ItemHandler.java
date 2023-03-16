package com.slendymctendies.scapecraft.item;

import com.slendymctendies.scapecraft.Main;
import com.slendymctendies.scapecraft.entity.EntityHandler;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemHandler {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    //INGOTS
    public static final RegistryObject<Item> INFERNITE_INGOT = ITEMS.register("infernite_ingot",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    //PROJECTILES
    public static final RegistryObject<Item> JALBAT_PROJECTILE = ITEMS.register("jalbat_projectile",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    //SPAWN EGGS
    public static final RegistryObject<Item> NIBBLER_SPAWNEGG = ITEMS.register("nibbler_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityHandler.NIBBLER, 0x000000, 0xf9cb9c, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> JALBAT_SPAWNEGG = ITEMS.register("jalbat_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityHandler.JALBAT, 0x000000, 0xf9cb9c, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> BLOB_SPAWNEGG = ITEMS.register("blob_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityHandler.BLOB, 0x000000, 0xf9cb9c, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
