package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleCustomAmbience;
import net.minecraft.client.renderer.SkyRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SkyRenderer.class)
public abstract class MixinSkyRenderer {

    @ModifyExpressionValue(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/attribute/EnvironmentAttributeProbe;getValue(Lnet/minecraft/world/attribute/EnvironmentAttribute;F)Ljava/lang/Object;",
                    ordinal = 6
            )
    )
    private Object hookCustomAmbienceSkyColor(Object original) {
        if (original instanceof Integer skyColor) {
            return ModuleCustomAmbience.ModifyWorld.INSTANCE.modifyColor(skyColor);
        }

        return original;
    }

}
