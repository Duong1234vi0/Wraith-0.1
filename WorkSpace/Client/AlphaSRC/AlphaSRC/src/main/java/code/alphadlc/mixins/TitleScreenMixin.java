/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_437
 *  net.minecraft.class_442
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.display.screens.mainmenu.MainMenu;
import code.alphadlc.utils.client.FirstLaunchManager;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_442;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_310.class})
public class TitleScreenMixin {
    private static boolean AlphaDLCRedirectScheduled = false;

    @Inject(method={"setScreen"}, at={@At(value="HEAD")}, cancellable=true)
    private void replaceTitleScreen(class_437 screen, CallbackInfo ci) {
        if (screen instanceof class_442 && !this.isPanicModeSafe()) {
            class_310 client = (class_310)this;
            FirstLaunchManager.applySavedPreferences();
            ci.cancel();
            if (client.field_1755 instanceof MainMenu) {
                return;
            }
            if (AlphaDLCRedirectScheduled) {
                return;
            }
            AlphaDLCRedirectScheduled = true;
            client.execute(() -> {
                try {
                    client.method_1507((class_437)MainMenu.INSTANCE);
                }
                finally {
                    AlphaDLCRedirectScheduled = false;
                }
            });
        }
    }

    private boolean isPanicModeSafe() {
        try {
            Boolean panicMode;
            Class<?> panicClass = Class.forName("code.alphadlc.features.impl.misc.Panic");
            Object result = panicClass.getMethod("isPanicMode", new Class[0]).invoke(null, new Object[0]);
            return result instanceof Boolean && (panicMode = (Boolean)result) != false;
        }
        catch (Throwable ignored) {
            return false;
        }
    }
}
