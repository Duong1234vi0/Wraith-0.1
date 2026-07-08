/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_761
 *  net.minecraft.class_9909
 *  net.minecraft.class_9958
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.NoRender;
import net.minecraft.class_243;
import net.minecraft.class_761;
import net.minecraft.class_9909;
import net.minecraft.class_9958;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_761.class})
public class WorldRendererWeatherMixin {
    @Inject(method={"renderWeather"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderWeather(class_9909 frameGraphBuilder, class_243 pos, float tickDelta, class_9958 fog, CallbackInfo ci) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.shouldHideWeather()) {
            ci.cancel();
        }
    }
}
