/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1735
 *  net.minecraft.class_332
 *  net.minecraft.class_364
 *  net.minecraft.class_465
 *  net.minecraft.class_490
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.events.container.HandledScreenEvent;
import code.alphadlc.mixins.IScreen;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import net.minecraft.class_1735;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_465;
import net.minecraft.class_490;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_465.class})
public abstract class HandledScreenMixin {
    @Shadow
    public int field_2792;
    @Shadow
    public int field_2779;
    @Shadow
    @Nullable
    protected class_1735 field_2787;

    @Inject(method={"render"}, at={@At(value="RETURN")})
    public void render(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        SafeEvents.dispatch(() -> new HandledScreenEvent(context, this.field_2787, this.field_2792, this.field_2779, mouseX, mouseY, delta));
    }

    @Inject(method={"mouseClicked"}, at={@At(value="HEAD")}, cancellable=true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof class_490) {
            for (class_364 element : ((IScreen)((Object)this)).getChildren()) {
                if (!"code.alphadlc.display.widgets.ClearButtonWidget".equals(element.getClass().getName()) || !element.method_25402(mouseX, mouseY, button)) continue;
                cir.setReturnValue((Object)true);
                cir.cancel();
                return;
            }
        }
    }
}
