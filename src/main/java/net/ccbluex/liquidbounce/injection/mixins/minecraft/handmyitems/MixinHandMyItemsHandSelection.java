package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.minecraft.client.renderer.ItemInHandRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemInHandRenderer.class)
public abstract class MixinHandMyItemsHandSelection {

    @ModifyExpressionValue(
        method = "renderHandsWithItems",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/renderer/ItemInHandRenderer$HandRenderSelection;renderMainHand:Z"
        )
    )
    private boolean hmi$allowMainHand(boolean original) {
        return HandMyItemsRuntime.isActive() || original;
    }

    @ModifyExpressionValue(
        method = "renderHandsWithItems",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/renderer/ItemInHandRenderer$HandRenderSelection;renderOffHand:Z"
        )
    )
    private boolean hmi$allowOffhand(boolean original) {
        return HandMyItemsRuntime.isActive() || original;
    }
}
