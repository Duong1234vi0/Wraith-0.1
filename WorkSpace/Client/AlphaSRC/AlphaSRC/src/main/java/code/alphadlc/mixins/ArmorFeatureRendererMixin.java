/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10034
 *  net.minecraft.class_10055
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_572
 *  net.minecraft.class_970
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.CustomModelModule;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_10034;
import net.minecraft.class_10055;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_572;
import net.minecraft.class_970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_970.class})
public abstract class ArmorFeatureRendererMixin<S extends class_10034, M extends class_572<S>, A extends class_572<S>>
implements QuickImports {
    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true)
    private void AlphaDLC$hideArmorForCustomModels(class_4587 matrices, class_4597 vertexConsumers, int light, S state, float limbAngle, float limbDistance, CallbackInfo ci) {
        class_10055 playerState;
        block6: {
            block5: {
                if (!(state instanceof class_10055)) break block5;
                playerState = (class_10055)state;
                if (ArmorFeatureRendererMixin.mc.field_1687 != null) break block6;
            }
            return;
        }
        class_1297 entity = ArmorFeatureRendererMixin.mc.field_1687.method_8469(playerState.field_53528);
        if (!(entity instanceof class_1657)) {
            return;
        }
        class_1657 player = (class_1657)entity;
        if (CustomModelModule.getModelType(player) != null) {
            ci.cancel();
        }
    }
}
