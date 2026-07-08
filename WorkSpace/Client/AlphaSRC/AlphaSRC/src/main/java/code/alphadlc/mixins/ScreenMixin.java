/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1921
 *  net.minecraft.class_2558
 *  net.minecraft.class_2583
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_500
 *  net.minecraft.class_526
 *  net.minecraft.class_751
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.display.screens.EmotionWheelScreen;
import code.alphadlc.display.screens.clickgui.MenuScreen;
import code.alphadlc.events.chat.ChatEvent;
import code.alphadlc.features.impl.combat.autoswapUtil.AutoSwapWheelScreen;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import net.minecraft.class_1921;
import net.minecraft.class_2558;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_500;
import net.minecraft.class_526;
import net.minecraft.class_751;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_437.class})
public class ScreenMixin {
    private static final class_751 CUSTOM_PANORAMA_RENDERER = new class_751(class_2960.method_60655((String)"minecraft", (String)"panorama/panorama"));
    private static final class_2960 CUSTOM_MENU_BACKGROUND = class_2960.method_60655((String)"minecraft", (String)"textures/gui/background.png");

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void alphadlc$restoreGuiRenderState(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
    }

    @Inject(method={"render"}, at={@At(value="RETURN")})
    private void alphadlc$restoreGuiRenderStateTail(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
    }

    @Inject(at={@At(value="INVOKE", target="Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V", remap=false, ordinal=1)}, method={"handleTextClick"}, cancellable=true)
    public void handleCustomClickEvent(class_2583 style, CallbackInfoReturnable<Boolean> cir) {
        class_2558 clickEvent = style.method_10970();
        if (clickEvent == null) {
            return;
        }
        SafeEvents.dispatch(() -> new ChatEvent(clickEvent.method_10844()));
        cir.setReturnValue((Object)true);
        cir.cancel();
    }

    @Inject(method={"renderBackground"}, at={@At(value="HEAD")}, cancellable=true)
    private void disableBackgroundBlurAndDimming(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this instanceof MenuScreen || this instanceof EmotionWheelScreen || this instanceof AutoSwapWheelScreen) {
            ci.cancel();
            return;
        }
        if (class_310.method_1551().field_1687 != null) {
            ci.cancel();
            return;
        }
        if (this instanceof class_500 || this instanceof class_526) {
            int width = context.method_51421();
            int height = context.method_51443();
            context.method_25290(class_1921::method_62277, CUSTOM_MENU_BACKGROUND, 0, 0, 0.0f, 0.0f, width, height, width, height);
            context.method_25294(0, 0, width, height, 0x66000000);
            ci.cancel();
        }
    }
}
