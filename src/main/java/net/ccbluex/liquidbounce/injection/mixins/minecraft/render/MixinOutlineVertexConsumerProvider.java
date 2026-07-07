package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ccbluex.liquidbounce.interfaces.OutlineVertexConsumerProviderSingleDrawAddition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.OutlineBufferSource;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(OutlineBufferSource.class)
public abstract class MixinOutlineVertexConsumerProvider implements OutlineVertexConsumerProviderSingleDrawAddition {

    @Shadow
    @Final
    private MultiBufferSource.BufferSource outlineBufferSource;

    @Shadow
    private int outlineColor;

    @Override
    public @Nullable VertexConsumer liquid_bounce_getSingleDrawBuffers(RenderType layer) {
        var affectedOutline = layer.outline();
        if (affectedOutline.isEmpty()) {
            return null;
        }

        VertexConsumer vertexConsumer = this.outlineBufferSource.getBuffer(affectedOutline.get());
        return new OutlineBufferSource.EntityOutlineGenerator(vertexConsumer, this.outlineColor);
    }

}
