package net.ccbluex.liquidbounce.interfaces;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.jetbrains.annotations.Nullable;

public interface OutlineVertexConsumerProviderSingleDrawAddition {

    @Nullable
    VertexConsumer liquid_bounce_getSingleDrawBuffers(RenderType layer);

}
