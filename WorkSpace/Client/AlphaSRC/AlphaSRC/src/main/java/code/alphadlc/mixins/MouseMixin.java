/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.v2.WrapWithCondition
 *  net.minecraft.class_310
 *  net.minecraft.class_312
 *  net.minecraft.class_3675$class_307
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.events.keyboard.HotBarScrollEvent;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.keyboard.MouseRotationEvent;
import code.alphadlc.events.render.FovEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_3675;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_312.class})
public class MouseMixin {
    @Final
    @Shadow
    private class_310 field_1779;
    @Shadow
    public double field_1789;
    @Shadow
    public double field_1787;

    @Inject(method={"onMouseButton"}, at={@At(value="HEAD")})
    public void onMouseButtonHook(long window, int button, int action, int mods, CallbackInfo ci) {
        if (button != -1 && window == this.field_1779.method_22683().method_4490()) {
            SafeEvents.dispatch(() -> new KeyEvent(this.field_1779.field_1755, class_3675.class_307.field_1672, button, action));
        }
    }

    @Inject(method={"onMouseScroll"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;getInventory()Lnet/minecraft/entity/player/PlayerInventory;")}, cancellable=true)
    public void onMouseScrollHook(long window, double horizontal, double vertical, CallbackInfo ci) {
        HotBarScrollEvent event = SafeEvents.dispatch(() -> new HotBarScrollEvent(horizontal, vertical));
        if (event != null && event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"updateMouse"}, at={@At(value="HEAD")})
    private void onUpdateMouse(double timeDelta, CallbackInfo ci) {
        FovEvent event = SafeEvents.dispatch(FovEvent::new);
        if (event != null && event.isCancelled()) {
            double slowdown = (double)event.getFov() / (double)((Integer)this.field_1779.field_1690.method_41808().method_41753()).intValue();
            this.field_1789 *= slowdown;
            this.field_1787 *= slowdown;
        }
    }

    @WrapWithCondition(method={"updateMouse"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V")}, require=1, allow=1)
    private boolean modifyMouseRotationInput(class_746 instance, double cursorDeltaX, double cursorDeltaY) {
        MouseRotationEvent event = SafeEvents.dispatch(() -> new MouseRotationEvent((float)cursorDeltaX, (float)cursorDeltaY));
        if (event == null) {
            instance.method_5872(cursorDeltaX, cursorDeltaY);
            return false;
        }
        if (event.isCancelled()) {
            return false;
        }
        instance.method_5872((double)event.getCursorDeltaX(), (double)event.getCursorDeltaY());
        return false;
    }
}
