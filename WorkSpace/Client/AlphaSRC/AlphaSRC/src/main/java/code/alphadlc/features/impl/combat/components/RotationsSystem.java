/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_238
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 */
package code.alphadlc.features.impl.combat.components;

import code.alphadlc.features.impl.combat.CombatModules;
import code.alphadlc.features.impl.combat.components.gcd.GCDUtil;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.combat.PredictUtils;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_241;
import net.minecraft.class_243;

public abstract class RotationsSystem
implements QuickImports {
    public class_241 rotate = class_241.field_1340;

    public abstract void updateRotations(class_1309 var1);

    public static class_241 correctRotation(float yaw, float pitch) {
        if (yaw == -90.0f && pitch == 90.0f || yaw == -180.0f) {
            return new class_241(RotationsSystem.mc.field_1724.method_36454(), RotationsSystem.mc.field_1724.method_36455());
        }
        float gcd = GCDUtil.getGCD();
        yaw -= yaw % gcd;
        pitch -= pitch % gcd;
        return new class_241(yaw, pitch);
    }

    protected boolean shouldUseElytraPredict(class_1309 target) {
        return RotationsSystem.mc.field_1724 != null && target != null && RotationsSystem.mc.field_1724.method_6128() && target.method_6128() && CombatModules.isElytraPredictActive();
    }

    protected int getElytraPredictTicks() {
        return CombatModules.getElytraForwardTicks();
    }

    protected class_243 getPredictedPoint(class_1309 target, class_243 point) {
        if (!this.shouldUseElytraPredict(target)) {
            return point;
        }
        return PredictUtils.bypasselytrahacking(target);
    }

    protected class_238 getPredictedBox(class_1309 target) {
        class_238 box = target.method_5829();
        if (!this.shouldUseElytraPredict(target)) {
            return box;
        }
        class_243 currentCenter = box.method_1005();
        class_243 predictedCenter = this.getPredictedPoint(target, currentCenter);
        return box.method_997(predictedCenter.method_1020(currentCenter));
    }
}
