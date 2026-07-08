/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1058
 *  net.minecraft.class_310
 *  net.minecraft.class_3486
 *  net.minecraft.class_3610
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4603
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.NoRender;
import net.minecraft.class_1058;
import net.minecraft.class_310;
import net.minecraft.class_3486;
import net.minecraft.class_3610;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4603;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_4603.class})
public class InGameOverlayRendererMixin {
    @Inject(method={"renderFireOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private static void renderFireOverlayHook(class_4587 matrices, class_4597 vertexConsumers, CallbackInfo ci) {
        class_3610 fluidState;
        class_310 client;
        NoRender noRender = NoRender.getInstance();
        if (noRender == null || !noRender.isState()) {
            return;
        }
        if (noRender.modeSetting.isSelected("\u041e\u0433\u043e\u043d\u044c")) {
            ci.cancel();
            return;
        }
        if (noRender.shouldHideLavaOverlay() && (client = class_310.method_1551()) != null && client.field_1724 != null && (fluidState = client.field_1724.method_37908().method_8316(client.field_1724.method_24515())).method_15767(class_3486.field_15518)) {
            ci.cancel();
        }
    }

    @Inject(method={"renderInWallOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private static void renderInWallOverlayHook(class_1058 sprite, class_4587 matrices, class_4597 vertexConsumers, CallbackInfo ci) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.isState() && noRender.modeSetting.isSelected("\u041e\u0432\u0435\u0440\u043b\u0435\u0439 \u0431\u043b\u043e\u043a\u043e\u0432")) {
            ci.cancel();
        }
    }

    @Inject(method={"renderUnderwaterOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private static void renderUnderwaterOverlayHook(class_310 client, class_4587 matrices, class_4597 vertexConsumers, CallbackInfo ci) {
        NoRender noRender = NoRender.getInstance();
        if (noRender == null || !noRender.isState() || client == null || client.field_1724 == null) {
            return;
        }
        class_3610 fluidState = client.field_1724.method_37908().method_8316(client.field_1724.method_24515().method_10084());
        if (fluidState.method_15767(class_3486.field_15517) && noRender.shouldHideWaterOverlay()) {
            ci.cancel();
            return;
        }
        if (fluidState.method_15767(class_3486.field_15518) && noRender.shouldHideLavaOverlay()) {
            ci.cancel();
        }
    }
}
