package net.slendymctendies.scapecraft.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.slendymctendies.scapecraft.Scapecraft;

public class ScapecraftItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Scapecraft.MOD_ID);

    //RUNES
    public static final RegistryObject<Item> BLANKRUNE = ITEMS.register( "blankrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AIRRUNE = ITEMS.register("airrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ASTRALRUNE = ITEMS.register("astralrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLOODRUNE = ITEMS.register("bloodrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BODYRUNE = ITEMS.register("bodyrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHAOSRUNE = ITEMS.register("chaosrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COSMICRUNE = ITEMS.register("cosmicrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DEATHRUNE = ITEMS.register("deathrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EARTHRUNE = ITEMS.register("earthrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIRERUNE = ITEMS.register("firerune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LAWRUNE = ITEMS.register("lawrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MINDRUNE = ITEMS.register("mindrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NATURERUNE = ITEMS.register("naturerune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SOULRUNE = ITEMS.register("soulrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WATERRUNE = ITEMS.register("waterrune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WRATHRUNE = ITEMS.register("wrathrune", () -> new Item(new Item.Properties()));

    //TALISMANS
    public static final RegistryObject<Item> AIRTALISMAN = ITEMS.register("airtalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ASTRALTALISMAN = ITEMS.register("astraltalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLOODTALISMAN = ITEMS.register("bloodtalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BODYTALISMAN = ITEMS.register("bodytalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHAOSTALISMAN = ITEMS.register("chaostalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COSMICTALISMAN = ITEMS.register("cosmictalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DEATHTALISMAN = ITEMS.register("deathtalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EARTHTALISMAN = ITEMS.register("earthtalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIRETALISMAN = ITEMS.register("firetalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LAWTALISMAN = ITEMS.register("lawtalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MINDTALISMAN = ITEMS.register("mindtalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NATURETALISMAN = ITEMS.register("naturetalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SOULTALISMAN = ITEMS.register("soultalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WATERTALISMAN = ITEMS.register("watertalisman", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WRATHTALISMAN = ITEMS.register("wrathtalisman", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
