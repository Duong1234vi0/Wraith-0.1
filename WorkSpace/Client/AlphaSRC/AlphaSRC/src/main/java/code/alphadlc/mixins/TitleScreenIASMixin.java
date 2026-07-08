/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_364
 *  net.minecraft.class_4068
 *  net.minecraft.class_4185
 *  net.minecraft.class_442
 *  net.minecraft.class_6379
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.mixins.IScreen;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_4185;
import net.minecraft.class_442;
import net.minecraft.class_6379;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_442.class})
public class TitleScreenIASMixin {
    @Unique
    private static class_4185 accountSwitcherButton;

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void onInit(CallbackInfo ci) {
        class_442 screen = (class_442)this;
        IScreen si = (IScreen)screen;
        si.getDrawables().add((class_4068)accountSwitcherButton);
        si.getSelectables().add((class_6379)accountSwitcherButton);
        si.getChildren().add((class_364)accountSwitcherButton);
    }
}
