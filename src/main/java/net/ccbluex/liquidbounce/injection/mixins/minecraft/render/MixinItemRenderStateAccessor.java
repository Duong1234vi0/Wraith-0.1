package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import net.minecraft.client.renderer.item.ItemStackRenderState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStackRenderState.class)
public interface MixinItemRenderStateAccessor {
}
