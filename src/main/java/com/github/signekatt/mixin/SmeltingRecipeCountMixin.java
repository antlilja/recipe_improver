package com.github.signekatt.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

@Mixin(CookingRecipeSerializer.class)
public abstract class SmeltingRecipeCountMixin {
    @Inject(at = @At("RETURN"), method = "read", locals = LocalCapture.CAPTURE_FAILSOFT)
    public void read(Identifier identifier, JsonObject jsonObject, CallbackInfoReturnable<AbstractCookingRecipe> cir,
            String string, JsonElement jsonElement, Ingredient ingredient, String string2, Identifier identifier2,
            ItemStack itemStack) {
        if (JsonHelper.hasElement(jsonObject, "count")) {
            itemStack.setCount(JsonHelper.getInt(jsonObject, "count"));
        }
    }
}