/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.movement.autoMystGlider.handers;

import code.alphadlc.events.player.MoveEvent;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_243;
import net.minecraft.class_3532;

final class InternalNoclipFly
implements QuickImports {
    private static final float SPEED = 0.45f;
    private static final InternalNoclipFly INSTANCE = new InternalNoclipFly();

    private InternalNoclipFly() {
    }

    static InternalNoclipFly get() {
        return INSTANCE;
    }

    void onTick() {
        if (InternalNoclipFly.mc.field_1724 == null) {
            return;
        }
        InternalNoclipFly.mc.field_1724.field_5960 = true;
        InternalNoclipFly.mc.field_1724.field_6017 = 0.0f;
    }

    void onMove(MoveEvent moveEvent) {
        if (InternalNoclipFly.mc.field_1724 == null) {
            return;
        }
        boolean sneak = InternalNoclipFly.mc.field_1690.field_1832.method_1434();
        boolean jump = InternalNoclipFly.mc.field_1690.field_1903.method_1434();
        double y = moveEvent.getMovement().field_1351;
        if (sneak) {
            y = -0.45f;
        } else if (jump) {
            y = 0.45f;
        }
        float yaw = InternalNoclipFly.mc.field_1724.method_36454();
        float forward = InternalNoclipFly.mc.field_1724.field_3913.field_3905;
        float strafe = InternalNoclipFly.mc.field_1724.field_3913.field_3907;
        double x = 0.0;
        double z = 0.0;
        if (forward != 0.0f || strafe != 0.0f) {
            float yawRad = yaw * ((float)Math.PI / 180);
            x = -class_3532.method_15374((float)yawRad) * 0.45f * forward + class_3532.method_15362((float)yawRad) * 0.45f * strafe;
            z = class_3532.method_15362((float)yawRad) * 0.45f * forward + class_3532.method_15374((float)yawRad) * 0.45f * strafe;
        }
        moveEvent.setMovement(new class_243(x, y, z));
        InternalNoclipFly.mc.field_1724.field_5960 = true;
        InternalNoclipFly.mc.field_1724.field_6017 = 0.0f;
    }

    void reset() {
        if (InternalNoclipFly.mc.field_1724 != null) {
            InternalNoclipFly.mc.field_1724.field_5960 = false;
        }
    }
}
