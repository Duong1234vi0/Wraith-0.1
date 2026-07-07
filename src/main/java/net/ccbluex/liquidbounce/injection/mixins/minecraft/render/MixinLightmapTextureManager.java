package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import com.mojang.blaze3d.textures.GpuTexture;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleCustomAmbience;
import net.ccbluex.liquidbounce.interfaces.LightmapTextureManagerAddition;
import net.minecraft.client.renderer.Lightmap;
import net.minecraft.client.renderer.state.LightmapRenderState;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Lightmap.class)
public abstract class MixinLightmapTextureManager implements LightmapTextureManagerAddition {

    @Shadow
    @Final
    private GpuTexture texture;

    @ModifyArg(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/GpuDevice;createTexture(Ljava/lang/String;ILcom/mojang/blaze3d/textures/TextureFormat;IIII)Lcom/mojang/blaze3d/textures/GpuTexture;"),
            index = 1
    )
    private int makeTextureWritable(int usage) {
        return usage | GpuTexture.USAGE_COPY_DST;
    }

    @Inject(
            method = "render(Lnet/minecraft/client/renderer/state/LightmapRenderState;)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/state/LightmapRenderState;needsUpdate:Z", ordinal = 0, opcode = Opcodes.GETFIELD),
            cancellable = true
    )
    private void injectCustomLightColor(LightmapRenderState renderState, CallbackInfo ci) {
        if (ModuleCustomAmbience.CustomLightColor.INSTANCE.edit(this.texture, renderState)) {
            ci.cancel();
        }
    }

    @Override
    public void liquid_bounce$restoreLightMap() {
    }

}
