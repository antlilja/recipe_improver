package com.github.signekatt.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;increment(I)V"), method = "craftRecipe", locals = LocalCapture.CAPTURE_FAILHARD)
    private void craftRecipeIncrementItemStack(Recipe<?> recipe, CallbackInfo info, ItemStack itemStack,
            ItemStack itemStack2, ItemStack itemStack3) {
        itemStack3.increment(itemStack2.getCount());
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;increment(I)V"), method = "craftRecipe")
    private void craftRecipeRedirectIncrementItemStack(ItemStack itemStack, int value) {
        // Do Nothing
    }
}