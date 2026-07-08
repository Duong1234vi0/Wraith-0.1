/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_4184
 *  net.minecraft.class_638
 *  net.minecraft.class_6854
 *  net.minecraft.class_758
 *  net.minecraft.class_758$class_4596
 *  net.minecraft.class_9958
 *  org.joml.Vector4f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.events.render.FogEvent;
import code.alphadlc.features.impl.render.NoRender;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.color.ColorAssist;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_6854;
import net.minecraft.class_758;
import net.minecraft.class_9958;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_758.class})
public class BackGroundRendererMixin {
    @Inject(method={"getFogModifier(Lnet/minecraft/entity/Entity;F)Lnet/minecraft/client/render/BackgroundRenderer$StatusEffectFogModifier;"}, at={@At(value="RETURN")}, cancellable=true)
    private static void onGetFogModifier(class_1297 entity, float tickDelta, CallbackInfoReturnable<Object> info) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.isState() && entity instanceof class_1309) {
            class_1309 living = (class_1309)entity;
            Object modifier = info.getReturnValue();
            if (modifier != null) {
                if (noRender.shouldHideBadEffects() && living.method_6059(class_1294.field_5919)) {
                    info.setReturnValue(null);
                    return;
                }
                if (noRender.shouldHideDarkness() && living.method_6059(class_1294.field_38092)) {
                    info.setReturnValue(null);
                    return;
                }
            }
        }
    }

    @Inject(method={"getFogColor"}, at={@At(value="HEAD")}, cancellable=true)
    private static void getFogColorHook(class_4184 camera, float tickDelta, class_638 world, int clampedViewDistance, float skyDarkness, CallbackInfoReturnable<Vector4f> cir) {
        FogEvent event = SafeEvents.dispatch(() -> new FogEvent());
        if (event != null && event.isCancelled()) {
            int color = event.getColor();
            cir.setReturnValue((Object)new Vector4f(ColorAssist.redf(color), ColorAssist.greenf(color), ColorAssist.bluef(color), ColorAssist.alphaf(color)));
        }
    }

    @Inject(method={"applyFog"}, at={@At(value="HEAD")}, cancellable=true)
    private static void modifyFog(class_4184 camera, class_758.class_4596 fogType, Vector4f vector4f, float viewDistance, boolean thickenFog, float tickDelta, CallbackInfoReturnable<class_9958> cir) {
        FogEvent event = SafeEvents.dispatch(() -> new FogEvent());
        if (event != null && event.isCancelled()) {
            int color = event.getColor();
            float fogEnd = event.getDistance();
            float fogStart = Math.max(0.1f, fogEnd * (1.0f - event.getDensity() / 100.0f));
            cir.setReturnValue((Object)new class_9958(fogStart, fogEnd, class_6854.field_36351, ColorAssist.redf(color), ColorAssist.greenf(color), ColorAssist.bluef(color), ColorAssist.alphaf(color)));
        }
    }
}
