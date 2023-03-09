package com.slendymctendies.goonspvm.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup INFERNAL_GROUP = new ItemGroup("infernalItemsTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.INFERNALNETHERITEINGOT.get());
        }
    };

    public static final ItemGroup GLACIAL_GROUP = new ItemGroup("glacialItemsTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.GLACIALNETHERITEINGOT.get());
        }
    };

}
