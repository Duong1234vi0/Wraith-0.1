/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_309
 *  net.minecraft.class_310
 *  net.minecraft.class_3675$class_307
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.display.screens.clickgui.MenuScreen;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.features.impl.misc.Panic;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import net.minecraft.class_309;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_309.class})
public class KeyboardMixin {
    @Final
    @Shadow
    private class_310 field_1678;

    @Inject(method={"onKey"}, at={@At(value="HEAD")})
    private void onKey(long window, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
        if (key != -1 && window == this.field_1678.method_22683().method_4490()) {
            if (key == 344 && action == 1 && this.field_1678.field_1755 == null && this.field_1678.field_1724 != null && !Panic.isPanicMode()) {
                MenuScreen.getInstance().openGui();
            }
            SafeEvents.dispatch(() -> new KeyEvent(this.field_1678.field_1755, class_3675.class_307.field_1668, key, action));
        }
    }
}
