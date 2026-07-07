package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.resource.ResourceHandle;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleCubes;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleCustomAmbience;
import net.ccbluex.liquidbounce.features.module.modules.render.popchams.ModulePopChams;
import net.ccbluex.liquidbounce.features.module.modules.render.targetesp.ModuleTargetESP;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.chunk.ChunkSectionsToRender;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.util.profiling.ProfilerFiller;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Mixin;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class MixinWorldRenderer {

    @ModifyExpressionValue(
            method = "lambda$renderLevel$0",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ARGB;colorFromFloat(FFFF)I")
    )
    private int injectCustomAmbienceClearColor(int original) {
        int color = ModuleCustomAmbience.FogConfigurable.INSTANCE.modifyClearColor(original);
        return ModuleCustomAmbience.ModifyWorld.INSTANCE.modifyColor(color);
    }

    @Inject(
            method = "lambda$addMainPass$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;endBatch()V",
                    ordinal = 1,
                    shift = At.Shift.AFTER
            )
    )
    private void hookTargetEspShaderWorldPass(
            GpuBufferSlice terrainFog,
            LevelRenderState levelRenderState,
            ProfilerFiller profiler,
            ChunkSectionsToRender chunkSectionsToRender,
            ResourceHandle<RenderTarget> entityOutlineTarget,
            ResourceHandle<RenderTarget> translucentTarget,
            ResourceHandle<RenderTarget> mainTarget,
            ResourceHandle<RenderTarget> itemEntityTarget,
            ResourceHandle<RenderTarget> particleTarget,
            boolean renderOutline,
            Matrix4fc modelViewMatrix,
            CallbackInfo ci
    ) {
        // #Edit By Codex 2026-06-13: let TargetESP render before Iris/Complementary final composite
        // so shaderpack bloom can see the emissive ghost pixels. The normal WorldRenderEvent fires too late.
        ModuleTargetESP.renderShaderWorldPass(levelRenderState.cameraRenderState.pos);
        // #Edit By Claude 2026-06-14: same Iris shader-world pass for PopChams totem-pop chams.
        ModulePopChams.renderShaderWorldPass(levelRenderState.cameraRenderState.pos);
        // #Edit By Codex 2026-06-19: Cubes glow also needs the pre-composite shader-world path.
        ModuleCubes.renderShaderWorldPass(levelRenderState.cameraRenderState.pos);
    }
}
