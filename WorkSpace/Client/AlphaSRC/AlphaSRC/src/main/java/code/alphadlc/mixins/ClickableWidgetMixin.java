/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_339
 *  net.minecraft.class_342
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_342;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_339.class})
public abstract class ClickableWidgetMixin {
    @Unique
    private float AlphaDLC$hoverAnim;
    @Unique
    private float AlphaDLC$focusAnim;
    @Unique
    private boolean AlphaDLC$pushed;

    @Shadow
    public abstract boolean method_49606();

    @Shadow
    public abstract int method_46426();

    @Shadow
    public abstract int method_46427();

    @Shadow
    public abstract int method_25368();

    @Shadow
    public abstract int method_25364();

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void AlphaDLC$animateWidget(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        float focusScale;
        boolean isTextField = this instanceof class_342;
        boolean focused = isTextField && ((class_342)this).method_25370();
        float targetHover = this.method_49606() ? 1.0f : 0.0f;
        this.AlphaDLC$hoverAnim += (targetHover - this.AlphaDLC$hoverAnim) * 0.22f;
        this.AlphaDLC$focusAnim += ((focused ? 1.0f : 0.0f) - this.AlphaDLC$focusAnim) * 0.14f;
        float hoverLift = -1.4f * this.AlphaDLC$hoverAnim;
        float f = focusScale = isTextField ? 1.0f + 0.006f * this.AlphaDLC$focusAnim : 1.0f;
        if (Math.abs(hoverLift) < 0.01f && Math.abs(focusScale - 1.0f) < 0.001f) {
            this.AlphaDLC$pushed = false;
            return;
        }
        float centerX = (float)this.method_46426() + (float)this.method_25368() / 2.0f;
        float centerY = (float)this.method_46427() + (float)this.method_25364() / 2.0f;
        context.method_51448().method_22903();
        context.method_51448().method_46416(0.0f, hoverLift, 0.0f);
        context.method_51448().method_46416(centerX, centerY, 0.0f);
        context.method_51448().method_22905(focusScale, focusScale, 1.0f);
        context.method_51448().method_46416(-centerX, -centerY, 0.0f);
        this.AlphaDLC$pushed = true;
    }

    @Inject(method={"render"}, at={@At(value="RETURN")})
    private void AlphaDLC$restoreWidget(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (!this.AlphaDLC$pushed) {
            return;
        }
        context.method_51448().method_22909();
        this.AlphaDLC$pushed = false;
    }
}
