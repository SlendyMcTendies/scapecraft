package net.slendymctendies.scapecraft.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slendymctendies.scapecraft.Scapecraft;

public class ScapecraftItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Scapecraft.MOD_ID);

    public static final RegistryObject<Item> BLANKRUNE = ITEMS.register( "blankrune", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FIRERUNE = ITEMS.register("firerune", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
