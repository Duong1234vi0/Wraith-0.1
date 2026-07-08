/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10034
 *  net.minecraft.class_10055
 *  net.minecraft.class_572
 *  net.minecraft.class_630
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.emotions.EmotionWheelManager;
import net.minecraft.class_10034;
import net.minecraft.class_10055;
import net.minecraft.class_572;
import net.minecraft.class_630;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_572.class})
public abstract class BipedEntityModelMixin {
    @Shadow
    public class_630 field_3398;
    @Shadow
    public class_630 field_3394;
    @Shadow
    public class_630 field_3391;
    @Shadow
    public class_630 field_3401;
    @Shadow
    public class_630 field_27433;
    @Shadow
    public class_630 field_3392;
    @Shadow
    public class_630 field_3397;

    @Inject(method={"setAngles(Lnet/minecraft/client/render/entity/state/BipedEntityRenderState;)V"}, at={@At(value="TAIL")})
    private void AlphaDLC$applyEmotionPose(class_10034 state, CallbackInfo ci) {
        if (!(state instanceof class_10055)) {
            return;
        }
        class_10055 playerState = (class_10055)state;
        EmotionWheelManager manager = EmotionWheelManager.getInstance();
        if (manager != null) {
            manager.applyToModel(playerState, this.field_3398, this.field_3394, this.field_3391, this.field_3401, this.field_27433, this.field_3392, this.field_3397);
        }
    }
}
