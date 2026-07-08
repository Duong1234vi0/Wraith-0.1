/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat.components.rotations;

import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.combat.components.RotationsSystem;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.FreeLookStorage;
import code.alphadlc.utils.features.aura.RotationStorage;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.rotate.Rotation;
import code.alphadlc.utils.math.MathUtils;
import java.security.SecureRandom;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class ReallyworldRotation
extends RotationsSystem
implements QuickImports {
    private final SecureRandom random = new SecureRandom();
    private class_241 lerpRotation = class_241.field_1340;

    public void reset() {
        if (ReallyworldRotation.mc.field_1724 == null) {
            this.lerpRotation = class_241.field_1340;
            return;
        }
        this.lerpRotation = FreeLookStorage.isActive() ? new class_241(FreeLookStorage.getFreeYaw(), FreeLookStorage.getFreePitch()) : new class_241(ReallyworldRotation.mc.field_1724.method_36454(), ReallyworldRotation.mc.field_1724.method_36455());
    }

    @Override
    public void updateRotations(class_1309 target) {
        if (ReallyworldRotation.mc.field_1724 == null || target == null) {
            return;
        }
        AttackAura aura = AttackAura.getInstance();
        float attackDistance = aura != null ? aura.attackRange.getValue() : 3.0f;
        double maxHeight = MathAngle.getStrictDistance((class_1297)target) / (double)attackDistance;
        class_243 eyePos = ReallyworldRotation.mc.field_1724.method_33571();
        double aimY = target.method_23318() + class_3532.method_15350((double)(eyePos.field_1351 - target.method_23318()), (double)0.0, (double)maxHeight);
        class_243 direction = new class_243(target.method_23317(), aimY, target.method_23321()).method_1020(eyePos).method_1029();
        float rawYaw = (float)Math.toDegrees(Math.atan2(-direction.field_1352, direction.field_1350));
        float rawPitch = (float)class_3532.method_15350((double)(-Math.toDegrees(Math.atan2(direction.field_1351, Math.hypot(direction.field_1352, direction.field_1350)))), (double)-90.0, (double)90.0);
        float speed = this.random.nextBoolean() ? this.randomLerp(0.3f, 0.4f) : this.randomLerp(0.5f, 0.6f);
        long time = System.currentTimeMillis();
        float cos = (float)Math.cos((double)time / 70.0);
        float sin = (float)Math.sin((double)time / 115.0);
        float yawJitter = (float)Math.ceil(this.randomLerp(1.0f, 3.0f) * cos);
        float pitchJitter = (float)Math.ceil(this.randomLerp(1.0f, 2.0f) * sin);
        this.lerpRotation = new class_241(this.wrapLerp(speed, this.lerpRotation.field_1343, rawYaw + yawJitter), this.wrapLerp(speed / 2.0f, this.lerpRotation.field_1342, class_3532.method_15363((float)(rawPitch + pitchJitter), (float)-90.0f, (float)90.0f)));
        this.applyFastRotation();
    }

    private void applyFastRotation() {
        float newYaw = this.lerpRotation.field_1343 + MathUtils.random(-3.0f, 3.0f);
        float newPitch = class_3532.method_15363((float)(this.lerpRotation.field_1342 + MathUtils.random(-2.0f, 2.0f)), (float)-90.0f, (float)90.0f);
        RotationStorage.update(new Rotation(newYaw, newPitch), 180.0f, 180.0f, 0.0f, 0.0f, 0, 5, false);
    }

    private float wrapLerp(float step, float input, float target) {
        return input + step * class_3532.method_15393((float)(target - input));
    }

    private float randomLerp(float min, float max) {
        return class_3532.method_16439((float)this.random.nextFloat(), (float)min, (float)max);
    }
}
