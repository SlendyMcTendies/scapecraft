package com.slendymctendies.scapecraft.item;

import com.slendymctendies.scapecraft.Scapecraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Scapecraft.MOD_ID);

    public static final RegistryObject<Item> INFERNALNETHERITEINGOT = ITEMS.register("infernal_netherite_ingot", () -> new Item(new Item.Properties().tab(ModItemGroup.INFERNAL_GROUP)));
    public static final RegistryObject<Item> GLACIALNETHERITEINGOT = ITEMS.register("glacial_netherite_ingot", () -> new Item(new Item.Properties().tab(ModItemGroup.GLACIAL_GROUP)));

    //SwordItem(ModItemTier.INFERNAL_NETHERITE, [attackDamage {p_i48460_2_}], [attackSpeed {p_i48460_3_}]

    //Infernal Netherite Items
    public static final RegistryObject<Item> INFERNALNETHERITE_SWORD = ITEMS.register("infernal_netherite_sword", () -> new SwordItem(ModItemTier.INFERNAL_NETHERITE, 3, -2.4f, new Item.Properties().tab(ModItemGroup.INFERNAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> INFERNALNETHERITE_SHOVEL = ITEMS.register("infernal_netherite_shovel", () -> new ShovelItem(ModItemTier.INFERNAL_NETHERITE, 1.5F, -3f, new Item.Properties().tab(ModItemGroup.INFERNAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> INFERNALNETHERITE_AXE = ITEMS.register("infernal_netherite_axe", () -> new AxeItem(ModItemTier.INFERNAL_NETHERITE, 5, -2.9f, new Item.Properties().tab(ModItemGroup.INFERNAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> INFERNALNETHERITE_PICKAXE = ITEMS.register("infernal_netherite_pickaxe", () -> new PickaxeItem(ModItemTier.INFERNAL_NETHERITE, 1, -2.8f, new Item.Properties().tab(ModItemGroup.INFERNAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> INFERNALNETHERITE_HOE = ITEMS.register("infernal_netherite_hoe", () -> new HoeItem(ModItemTier.INFERNAL_NETHERITE, -4, 0f, new Item.Properties().tab(ModItemGroup.INFERNAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> INFERNALNETHERITE_BOOTS = ITEMS.register("infernal_netherite_boots", () -> new ArmorItem(ModArmorMaterial.INFERNAL_NETHERITE, EquipmentSlotType.FEET, new Item.Properties().tab(ModItemGroup.INFERNAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> INFERNALNETHERITE_LEGGINGS = ITEMS.register("infernal_netherite_leggings", () -> new ArmorItem(ModArmorMaterial.INFERNAL_NETHERITE, EquipmentSlotType.LEGS, new Item.Properties().tab(ModItemGroup.INFERNAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> INFERNALNETHERITE_CHESTPLATE = ITEMS.register("infernal_netherite_chestplate", () -> new ArmorItem(ModArmorMaterial.INFERNAL_NETHERITE, EquipmentSlotType.CHEST, new Item.Properties().tab(ModItemGroup.INFERNAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> INFERNALNETHERITE_HELMET = ITEMS.register("infernal_netherite_helmet", () -> new ArmorItem(ModArmorMaterial.INFERNAL_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().tab(ModItemGroup.INFERNAL_GROUP).fireResistant()));

    //Glacial Netherite Items
    public static final RegistryObject<Item> GLACIALNETHERITE_SWORD = ITEMS.register("glacial_netherite_sword", () -> new SwordItem(ModItemTier.GLACIAL_NETHERITE, 3, -2.4f, new Item.Properties().tab(ModItemGroup.GLACIAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> GLACIALNETHERITE_SHOVEL = ITEMS.register("glacial_netherite_shovel", () -> new ShovelItem(ModItemTier.GLACIAL_NETHERITE, 1.5F, -3f, new Item.Properties().tab(ModItemGroup.GLACIAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> GLACIALNETHERITE_AXE = ITEMS.register("glacial_netherite_axe", () -> new AxeItem(ModItemTier.GLACIAL_NETHERITE, 5, -2.9f, new Item.Properties().tab(ModItemGroup.GLACIAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> GLACIALNETHERITE_PICKAXE = ITEMS.register("glacial_netherite_pickaxe", () -> new PickaxeItem(ModItemTier.GLACIAL_NETHERITE, 1, -2.8f, new Item.Properties().tab(ModItemGroup.GLACIAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> GLACIALNETHERITE_HOE = ITEMS.register("glacial_netherite_hoe", () -> new HoeItem(ModItemTier.GLACIAL_NETHERITE, -4, 0f, new Item.Properties().tab(ModItemGroup.GLACIAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> GLACIALNETHERITE_BOOTS = ITEMS.register("glacial_netherite_boots", () -> new ArmorItem(ModArmorMaterial.GLACIAL_NETHERITE, EquipmentSlotType.FEET, new Item.Properties().tab(ModItemGroup.GLACIAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> GLACIALNETHERITE_LEGGINGS = ITEMS.register("glacial_netherite_leggings", () -> new ArmorItem(ModArmorMaterial.GLACIAL_NETHERITE, EquipmentSlotType.LEGS, new Item.Properties().tab(ModItemGroup.GLACIAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> GLACIALNETHERITE_CHESTPLATE = ITEMS.register("glacial_netherite_chestplate", () -> new ArmorItem(ModArmorMaterial.GLACIAL_NETHERITE, EquipmentSlotType.CHEST, new Item.Properties().tab(ModItemGroup.GLACIAL_GROUP).fireResistant()));
    public static final RegistryObject<Item> GLACIALNETHERITE_HELMET = ITEMS.register("glacial_netherite_helmet", () -> new ArmorItem(ModArmorMaterial.GLACIAL_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().tab(ModItemGroup.GLACIAL_GROUP).fireResistant()));

    //Spawn Eggs
    //public static final RegistryObject<ModSpawnEggItem> NIBBLER_SPAWN_EGG = ITEMS.register("nibbler_spawn_egg", () -> ModSpawnEggItem(ModEntityTypes.INFERNO_NIBBLER, 0x8b0000, 0x000000, new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_MISC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
