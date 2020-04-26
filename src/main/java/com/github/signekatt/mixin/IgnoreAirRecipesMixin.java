package com.github.signekatt.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;

@Mixin(Recipe.class)
public interface IgnoreAirRecipesMixin<C extends Inventory> {

    @SuppressWarnings("unchecked")
    @Overwrite
    default boolean isIgnoredInRecipeBook() {
        Recipe<C> recipe = (Recipe<C>) this;
        if (recipe.getOutput().getItem() == Items.AIR) {
            return true;
        }

        return false;
    }
}