/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat.components.interpolation;

import code.alphadlc.features.impl.combat.CombatModules;
import code.alphadlc.features.impl.combat.components.gcd.GCDUtil;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.combat.PredictUtils;
import net.minecraft.class_1309;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public final class PredictRots
implements QuickImports {
    public static class_241 rotating(class_241 rotation, class_1309 target) {
        class_243 vec = PredictRots.calcPointed(target);
        float rawYaw = (float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(vec.field_1350, vec.field_1352)) - 90.0));
        float rawPitch = (float)class_3532.method_15338((double)Math.toDegrees(-Math.atan2(vec.field_1351, Math.hypot(vec.field_1352, vec.field_1350))));
        float yawDelta = class_3532.method_15393((float)(rawYaw - rotation.field_1343));
        float pitchDelta = class_3532.method_15393((float)(rawPitch - rotation.field_1342));
        if (Math.abs(yawDelta) > 180.0f) {
            yawDelta -= Math.signum(yawDelta) * 360.0f;
        }
        float additionYaw = class_3532.method_15363((float)yawDelta, (float)-180.0f, (float)180.0f);
        float additionPitch = class_3532.method_15363((float)pitchDelta, (float)-90.0f, (float)90.0f);
        float yaw = rotation.field_1343 + additionYaw;
        float pitch = rotation.field_1342 + additionPitch;
        float yawFinal = GCDUtil.getFixedRotation(yaw);
        float pitchFinal = GCDUtil.getFixedRotation(pitch);
        return new class_241(yawFinal, pitchFinal);
    }

    public static class_243 calcPointed(class_1309 target) {
        if (target != null) {
            class_243 vecPosition = PredictRots.getPredictedPoint(target, target.method_5829().method_1005());
            return new class_243(vecPosition.method_10216() - PredictRots.mc.field_1724.method_23317(), vecPosition.method_10214() - PredictRots.mc.field_1724.method_23318(), vecPosition.method_10215() - PredictRots.mc.field_1724.method_23321());
        }
        return class_243.field_1353;
    }

    public static class_243 getPredictedPoint(class_1309 target, class_243 point) {
        if (PredictRots.mc.field_1724 != null && target != null && PredictRots.mc.field_1724.method_6128() && target.method_6128() && CombatModules.isElytraPredictActive()) {
            return PredictUtils.bypasselytrahacking(target);
        }
        return point;
    }

    private PredictRots() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
