package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import net.minecraft.client.renderer.chunk.SectionRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SectionRenderDispatcher.RenderSection.class)
public abstract class MixinBuiltChunk {
}
