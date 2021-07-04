package com.github.signekatt.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.collection.DefaultedList;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;increment(I)V"), method = "craftRecipe", locals = LocalCapture.CAPTURE_FAILHARD)
    private static void craftRecipeIncrementItemStack(Recipe<?> recipe, DefaultedList<ItemStack> list, int count,
            CallbackInfoReturnable<Boolean> callbackInfo, ItemStack itemStack, ItemStack itemStack2,
            ItemStack itemStack3) {
        itemStack3.increment(itemStack2.getCount());
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;increment(I)V"), method = "craftRecipe")
    private static void craftRecipeRedirectIncrementItemStack(ItemStack itemStack, int value) {
        // Do Nothing
    }

    @Inject(at = @At("HEAD"), method = "canAcceptRecipeOutput", locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void craftRecipeIncrementItemStack(Recipe<?> recipe, DefaultedList<ItemStack> slots, int count, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if(!slots.get(2).isEmpty() && recipe != null && (slots.get(2).getCount() + recipe.getOutput().getCount() > slots.get(2).getMaxCount())) {
            callbackInfoReturnable.setReturnValue(false);
            callbackInfoReturnable.cancel();
        }
    }
}