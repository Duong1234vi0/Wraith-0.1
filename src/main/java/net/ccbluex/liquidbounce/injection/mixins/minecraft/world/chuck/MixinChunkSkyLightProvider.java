package net.ccbluex.liquidbounce.injection.mixins.minecraft.world.chuck;

import net.minecraft.world.level.lighting.SkyLightEngine;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SkyLightEngine.class)
public class MixinChunkSkyLightProvider {
}
