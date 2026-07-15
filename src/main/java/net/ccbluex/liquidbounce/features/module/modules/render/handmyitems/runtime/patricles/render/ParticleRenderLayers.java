package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.render;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.platform.SourceFactor;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.Optional;
import java.util.function.Function;

public final class ParticleRenderLayers {
    private static final BlendFunction ADDITIVE = new BlendFunction(SourceFactor.SRC_ALPHA, DestFactor.ONE);
    private static final RenderPipeline ADDITIVE_PARTICLE = RenderPipelines.register(
        RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath("liquidbounce", "pipeline/handmyitems_additive_particle"))
            .withVertexShader("core/position_tex_color")
            .withFragmentShader("core/position_tex_color")
            .withSampler("Sampler0")
            .withColorTargetState(new ColorTargetState(ADDITIVE))
            .withDepthStencilState(Optional.<DepthStencilState>empty())
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
            .build()
    );
    private static final Function<Identifier, RenderType> ADDITIVE_PARTICLE_LAYER = Util.memoize(texture ->
        RenderType.create(
            "handmyitems_additive_particle",
            RenderSetup.builder(ADDITIVE_PARTICLE)
                .withTexture("Sampler0", texture)
                .bufferSize(4096)
                .createRenderSetup()
        )
    );

    private ParticleRenderLayers() {
    }

    public static RenderType additiveParticle(Identifier texture) {
        return ADDITIVE_PARTICLE_LAYER.apply(texture);
    }
}
