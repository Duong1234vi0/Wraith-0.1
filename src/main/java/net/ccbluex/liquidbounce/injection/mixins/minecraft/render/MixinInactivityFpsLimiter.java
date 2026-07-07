package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import net.minecraft.client.FramerateLimiter;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FramerateLimiter.class)
public abstract class MixinInactivityFpsLimiter {
}
