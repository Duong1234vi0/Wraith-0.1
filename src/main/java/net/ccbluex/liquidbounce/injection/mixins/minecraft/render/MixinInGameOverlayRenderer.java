package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ScreenEffectRenderer.class)
public abstract class MixinInGameOverlayRenderer {
}
