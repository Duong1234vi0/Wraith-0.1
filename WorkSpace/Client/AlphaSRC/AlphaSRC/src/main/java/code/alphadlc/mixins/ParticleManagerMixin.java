/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2394
 *  net.minecraft.class_2398
 *  net.minecraft.class_702
 *  net.minecraft.class_703
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.NoRender;
import code.alphadlc.features.impl.render.Particles;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_702;
import net.minecraft.class_703;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_702.class})
public class ParticleManagerMixin {
    @Inject(method={"addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAddParticle(class_2394 parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfoReturnable<class_703> cir) {
        Particles particles2;
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.isState()) {
            if (noRender.shouldHideSmoke() && (parameters.method_10295() == class_2398.field_11251 || parameters.method_10295() == class_2398.field_11237 || parameters.method_10295() == class_2398.field_17430 || parameters.method_10295() == class_2398.field_17431)) {
                cir.setReturnValue(null);
                return;
            }
            if (noRender.shouldHideAirBubbles() && (parameters.method_10295() == class_2398.field_11247 || parameters.method_10295() == class_2398.field_11241 || parameters.method_10295() == class_2398.field_11243)) {
                cir.setReturnValue(null);
                return;
            }
        }
        if ((particles2 = Particles.getInstance()) != null && particles2.isState() && particles2.typep.isSelected("\u0422\u043e\u0442\u0435\u043c\u0435") && parameters.method_10295() == class_2398.field_11220) {
            cir.setReturnValue(null);
        }
    }
}
