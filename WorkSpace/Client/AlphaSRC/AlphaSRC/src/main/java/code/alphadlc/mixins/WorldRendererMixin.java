/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_3695
 *  net.minecraft.class_4063
 *  net.minecraft.class_4184
 *  net.minecraft.class_4604
 *  net.minecraft.class_761
 *  net.minecraft.class_9779
 *  net.minecraft.class_9909
 *  net.minecraft.class_9958
 *  org.joml.Matrix4f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.BlockOverlay;
import code.alphadlc.features.impl.render.ShaderFog;
import net.minecraft.class_243;
import net.minecraft.class_3695;
import net.minecraft.class_4063;
import net.minecraft.class_4184;
import net.minecraft.class_4604;
import net.minecraft.class_761;
import net.minecraft.class_9779;
import net.minecraft.class_9909;
import net.minecraft.class_9958;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_761.class})
public abstract class WorldRendererMixin {
    @Shadow
    protected abstract void method_62202(class_9909 var1, class_4604 var2, class_4184 var3, Matrix4f var4, Matrix4f var5, class_9958 var6, boolean var7, boolean var8, class_9779 var9, class_3695 var10);

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/WorldRenderer;renderMain(Lnet/minecraft/client/render/FrameGraphBuilder;Lnet/minecraft/client/render/Frustum;Lnet/minecraft/client/render/Camera;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/client/render/Fog;ZZLnet/minecraft/client/render/RenderTickCounter;Lnet/minecraft/util/profiler/Profiler;)V"))
    private void onRender(class_761 instance, class_9909 frameGraphBuilder, class_4604 frustum, class_4184 camera, Matrix4f positionMatrix, Matrix4f projectionMatrix, class_9958 fog, boolean renderBlockOutline, boolean hasEntitiesToRender, class_9779 renderTickCounter, class_3695 profiler) {
        BlockOverlay blockOverlay = BlockOverlay.getInstance();
        boolean shouldRenderOutline = blockOverlay == null || !blockOverlay.isState();
        this.method_62202(frameGraphBuilder, frustum, camera, positionMatrix, projectionMatrix, fog, shouldRenderOutline, hasEntitiesToRender, renderTickCounter, profiler);
    }

    @Inject(method={"renderSky"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderSky(class_9909 frameGraphBuilder, class_4184 camera, float tickDelta, class_9958 fog, CallbackInfo ci) {
        if (ShaderFog.shouldReplaceVanillaSky()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderClouds"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderClouds(class_9909 frameGraphBuilder, Matrix4f matrix4f, Matrix4f matrix4f2, class_4063 cloudRenderMode, class_243 vec3d, float f, int i, float f2, CallbackInfo ci) {
        if (ShaderFog.shouldReplaceVanillaSky()) {
            ci.cancel();
        }
    }
}
