/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.utils.features.aura.rotations.constructor;

import code.alphadlc.utils.features.aura.rotations.constructor.RotateConstructor;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class LinearConstructor
extends RotateConstructor {
    public LinearConstructor() {
        super("Linear");
    }

    @Override
    public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
        Turns delta = MathAngle.calculateDelta(currentAngle, targetAngle);
        float yawStep = class_3532.method_15363((float)delta.getYaw(), (float)-180.0f, (float)180.0f);
        float pitchStep = class_3532.method_15363((float)delta.getPitch(), (float)-90.0f, (float)90.0f);
        return new Turns(currentAngle.getYaw() + yawStep, currentAngle.getPitch() + pitchStep);
    }
}
