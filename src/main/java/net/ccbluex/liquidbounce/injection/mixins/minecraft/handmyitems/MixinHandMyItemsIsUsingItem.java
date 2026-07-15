package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.GlobalsStorage;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.IsUsingItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IsUsingItem.class)
public abstract class MixinHandMyItemsIsUsingItem {

    @Inject(method = "get", at = @At("RETURN"), cancellable = true)
    private void hmi$overrideUsingItem(
        ItemStack stack,
        @Nullable ClientLevel level,
        @Nullable LivingEntity entity,
        int seed,
        ItemDisplayContext displayContext,
        CallbackInfoReturnable<Boolean> cir
    ) {
        String key = stack.getItem().toString();
        if (HandMyItemsRuntime.isActive()
            && entity != null
            && entity.getActiveItem() == stack
            && GlobalsStorage.usingItem.containsKey(key)) {
            cir.setReturnValue(GlobalsStorage.usingItem.get(key));
        }
    }
}
