/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1291
 *  net.minecraft.class_1294
 *  net.minecraft.class_1309
 *  net.minecraft.class_310
 *  net.minecraft.class_6880
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.NoRender;
import net.minecraft.class_1291;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_310;
import net.minecraft.class_6880;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1309.class})
public class WitherHeartsMixin {
    @Inject(method={"hasStatusEffect"}, at={@At(value="HEAD")}, cancellable=true)
    private void noRender$hideWitherEffect(class_6880<class_1291> effect, CallbackInfoReturnable<Boolean> cir) {
        NoRender noRender;
        class_1309 self = (class_1309)this;
        class_310 client = class_310.method_1551();
        if (client != null && client.field_1724 != null && self == client.field_1724 && effect != null && effect.comp_349() == class_1294.field_5920.comp_349() && (noRender = NoRender.getInstance()) != null && noRender.shouldHideWitherHearts()) {
            cir.setReturnValue((Object)false);
        }
    }
}
