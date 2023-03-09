package com.slendymctendies.goonspvm.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.tags.ItemTags;
import java.util.function.Supplier;

public enum ModItemTier implements IItemTier {
    INFERNAL_NETHERITE(4, 2600, 9.0f, 5.0f, 12, () -> { return Ingredient.of(ModItems.INFERNALNETHERITEINGOT.get()); }),
    GLACIAL_NETHERITE(4, 2600, 9.0f, 5.0f, 12, () -> { return Ingredient.of(ModItems.GLACIALNETHERITEINGOT.get()); });

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    ModItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
