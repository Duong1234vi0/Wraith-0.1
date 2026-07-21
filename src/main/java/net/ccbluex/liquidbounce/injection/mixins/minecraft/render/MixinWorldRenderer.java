package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.framegraph.FrameGraphBuilder;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.resource.ResourceHandle;
import com.mojang.blaze3d.systems.RenderSystem;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleBlockOutline;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleBetterWorld;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleCubes;
import net.ccbluex.liquidbounce.features.module.modules.combat.backtrack.ModuleBacktrack;
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaProjection;
import net.ccbluex.liquidbounce.features.module.modules.render.targetesp.ModuleTargetESP;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.chunk.ChunkSectionsToRender;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.state.level.SkyRenderState;
import net.minecraft.util.profiling.ProfilerFiller;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class MixinWorldRenderer {

    @Shadow
    @Final
    private LevelTargetBundle targets;

    @Shadow
    @Final
    private LevelRenderState levelRenderState;

    @Shadow
    private ClientLevel level;

    @Shadow
    @Final
    private EntityRenderDispatcher entityRenderDispatcher;

    @Inject(method = "addSkyPass", at = @At("HEAD"), cancellable = true)
    private void liquidbounce$betterWorldSkyPass(
            FrameGraphBuilder frameGraphBuilder,
            CameraRenderState cameraRenderState,
            GpuBufferSlice skyFog,
            CallbackInfo ci
    ) {
        SkyRenderState skyRenderState = this.levelRenderState.skyRenderState;
        if (!ModuleBetterWorld.shouldRenderCustomSky(cameraRenderState, skyRenderState, this.level)) {
            return;
        }

        var pass = frameGraphBuilder.addPass("better_world_sky");
        this.targets.main = pass.readsAndWrites(this.targets.main);
        Matrix4f projectionMatrix = new Matrix4f(cameraRenderState.projectionMatrix);
        Matrix4f viewMatrix = new Matrix4f(cameraRenderState.viewRotationMatrix);

        pass.executes(() -> {
            RenderSystem.setShaderFog(skyFog);
            ModuleBetterWorld.renderCustomSky(projectionMatrix, viewMatrix, cameraRenderState.fogData);
        });
        ci.cancel();
    }

    @Inject(method = "addMainPass", at = @At("HEAD"))
    private void liquidbounce$captureAlphaFrustum(
            FrameGraphBuilder frameGraphBuilder,
            Frustum frustum,
            Matrix4fc matrix4fc,
            GpuBufferSlice terrainFog,
            boolean renderOutline,
            LevelRenderState levelRenderState,
            DeltaTracker deltaTracker,
            ProfilerFiller profiler,
            ChunkSectionsToRender chunkSectionsToRender,
            CallbackInfo ci
    ) {
        AlphaProjection.INSTANCE.setFrustum(frustum);
    }

    @Inject(method = "extractVisibleEntities", at = @At("TAIL"))
    private void liquidbounce$appendBacktrackModelRenderState(
            Camera camera,
            Frustum frustum,
            DeltaTracker deltaTracker,
            LevelRenderState levelRenderState,
            CallbackInfo ci
    ) {
        ModuleBacktrack.appendModelRenderState(
                this.entityRenderDispatcher,
                levelRenderState,
                deltaTracker.getGameTimeDeltaPartialTick(false)
        );
    }

    @ModifyVariable(method = "lambda$addMainPass$0", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private boolean liquidbounce$suppressVanillaBlockOutline(boolean renderOutline) {
        return renderOutline && !ModuleBlockOutline.shouldSuppressVanillaOutline();
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
        // #Edit By Codex 2026-06-19: Cubes glow also needs the pre-composite shader-world path.
        ModuleCubes.renderShaderWorldPass(levelRenderState.cameraRenderState.pos);
    }
}
