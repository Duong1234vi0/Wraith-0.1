package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.GlobalsStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(UseDuration.class)
public abstract class MixinHandMyItemsUseDuration {

    @Inject(method = "get", at = @At("RETURN"), cancellable = true)
    private void hmi$overrideUseDuration(
        ItemStack stack,
        @Nullable ClientLevel level,
        @Nullable ItemOwner owner,
        int seed,
        CallbackInfoReturnable<Float> cir
    ) {
        Minecraft minecraft = Minecraft.getInstance();
        String key = stack.getItem().toString();
        if (HandMyItemsRuntime.isActive()
            && minecraft.player != null
            && minecraft.player.getActiveItem() == stack
            && GlobalsStorage.useDuration.containsKey(key)) {
            cir.setReturnValue(Float.parseFloat(String.valueOf(GlobalsStorage.useDuration.get(key))));
        }
    }
}
