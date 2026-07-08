/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 *  net.minecraft.class_408
 *  net.minecraft.class_437
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.display.hud.CustomHudOverlay;
import code.alphadlc.display.hud.HudEditorOverlay;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_408;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_408.class})
public class ChatScreenMixin
extends class_437
implements QuickImports {
    protected ChatScreenMixin() {
        super((class_2561)class_2561.method_43473());
    }

    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void onRenderAboveChat(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        CustomHudOverlay.render(context, mc.method_61966(), true);
        Hud hud = Hud.getInstance();
        if (hud != null && hud.isState()) {
            HudEditorOverlay.getInstance().render(context, mouseX, mouseY, delta);
        }
    }

    @Inject(method={"mouseClicked"}, at={@At(value="HEAD")}, cancellable=true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (HudEditorOverlay.getInstance().mouseClicked(mouseX, mouseY, button)) {
            cir.setReturnValue((Object)true);
        }
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        return HudEditorOverlay.getInstance().mouseReleased(mouseX, mouseY, button) || super.method_25406(mouseX, mouseY, button);
    }

    @Inject(method={"mouseScrolled"}, at={@At(value="HEAD")}, cancellable=true)
    private void onMouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount, CallbackInfoReturnable<Boolean> cir) {
        if (HudEditorOverlay.getInstance().mouseScrolled(mouseX, mouseY, verticalAmount)) {
            cir.setReturnValue((Object)true);
        }
    }
}
