/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10185
 *  net.minecraft.class_1294
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 */
package code.alphadlc.utils.input;

import net.minecraft.class_10185;
import net.minecraft.class_1294;
import net.minecraft.class_243;
import net.minecraft.class_310;

public class MoveUtil {
    private static final class_310 mc = class_310.method_1551();

    public static double getMotion() {
        if (MoveUtil.mc.field_1724 == null) {
            return 0.0;
        }
        class_243 velocity = MoveUtil.mc.field_1724.method_18798();
        return Math.sqrt(velocity.field_1352 * velocity.field_1352 + velocity.field_1350 * velocity.field_1350);
    }

    public static void setMotion(double speed) {
        if (MoveUtil.mc.field_1724 == null) {
            return;
        }
        float forward = MoveUtil.mc.field_1724.field_3913.field_3905;
        float sideways = MoveUtil.mc.field_1724.field_3913.field_3907;
        float yaw = MoveUtil.mc.field_1724.method_36454();
        if (forward == 0.0f && sideways == 0.0f) {
            MoveUtil.mc.field_1724.method_18800(0.0, MoveUtil.mc.field_1724.method_18798().field_1351, 0.0);
            return;
        }
        if (forward != 0.0f) {
            if (sideways > 0.0f) {
                yaw -= (float)(forward > 0.0f ? 45 : -45);
            } else if (sideways < 0.0f) {
                yaw += (float)(forward > 0.0f ? 45 : -45);
            }
            sideways = 0.0f;
            forward = forward > 0.0f ? 1.0f : -1.0f;
        }
        double rad = Math.toRadians(yaw + 90.0f);
        double motionX = (double)forward * speed * Math.cos(rad) + (double)sideways * speed * Math.sin(rad);
        double motionZ = (double)forward * speed * Math.sin(rad) - (double)sideways * speed * Math.cos(rad);
        MoveUtil.mc.field_1724.method_18800(motionX, MoveUtil.mc.field_1724.method_18798().field_1351, motionZ);
    }

    public static void strafe() {
        MoveUtil.strafe(MoveUtil.getMotion());
    }

    public static void strafe(double speed) {
        if (MoveUtil.mc.field_1724 == null) {
            return;
        }
        float forward = MoveUtil.mc.field_1724.field_3913.field_3905;
        float sideways = MoveUtil.mc.field_1724.field_3913.field_3907;
        float yaw = MoveUtil.mc.field_1724.method_36454();
        if (forward == 0.0f && sideways == 0.0f) {
            MoveUtil.mc.field_1724.method_18800(0.0, MoveUtil.mc.field_1724.method_18798().field_1351, 0.0);
            return;
        }
        if (forward != 0.0f) {
            if (sideways > 0.0f) {
                yaw -= (float)(forward > 0.0f ? 45 : -45);
            } else if (sideways < 0.0f) {
                yaw += (float)(forward > 0.0f ? 45 : -45);
            }
            sideways = 0.0f;
            forward = forward > 0.0f ? 1.0f : -1.0f;
        }
        double rad = Math.toRadians(yaw + 90.0f);
        MoveUtil.mc.field_1724.method_18800((double)forward * speed * Math.cos(rad) + (double)sideways * speed * Math.sin(rad), MoveUtil.mc.field_1724.method_18798().field_1351, (double)forward * speed * Math.sin(rad) - (double)sideways * speed * Math.cos(rad));
    }

    public static boolean isMoving() {
        if (MoveUtil.mc.field_1724 == null) {
            return false;
        }
        return MoveUtil.mc.field_1724.field_3913.field_3905 != 0.0f || MoveUtil.mc.field_1724.field_3913.field_3907 != 0.0f;
    }

    public static double getDirection() {
        if (MoveUtil.mc.field_1724 == null) {
            return 0.0;
        }
        float forward = MoveUtil.mc.field_1724.field_3913.field_3905;
        float sideways = MoveUtil.mc.field_1724.field_3913.field_3907;
        float yaw = MoveUtil.mc.field_1724.method_36454();
        if (forward < 0.0f) {
            yaw += 180.0f;
        }
        float strafe = 1.0f;
        if (forward < 0.0f) {
            strafe = -0.5f;
        } else if (forward > 0.0f) {
            strafe = 0.5f;
        }
        if (sideways > 0.0f) {
            yaw -= 90.0f * strafe;
        }
        if (sideways < 0.0f) {
            yaw += 90.0f * strafe;
        }
        return Math.toRadians(yaw);
    }

    public static double getBaseSpeed() {
        int amplifier;
        if (MoveUtil.mc.field_1724 == null) {
            return 0.2873;
        }
        double baseSpeed = 0.2873;
        if (MoveUtil.mc.field_1724.method_6059(class_1294.field_5904)) {
            amplifier = MoveUtil.mc.field_1724.method_6112(class_1294.field_5904).method_5578();
            baseSpeed *= 1.0 + 0.2 * (double)(amplifier + 1);
        }
        if (MoveUtil.mc.field_1724.method_6059(class_1294.field_5909)) {
            amplifier = MoveUtil.mc.field_1724.method_6112(class_1294.field_5909).method_5578();
            baseSpeed *= 1.0 - 0.15 * (double)(amplifier + 1);
        }
        return baseSpeed;
    }

    public static void jump() {
        if (MoveUtil.mc.field_1724 == null) {
            return;
        }
        MoveUtil.mc.field_1724.method_6043();
    }

    public static void setSpeed(double speed, float yaw) {
        if (MoveUtil.mc.field_1724 == null) {
            return;
        }
        double rad = Math.toRadians(yaw + 90.0f);
        MoveUtil.mc.field_1724.method_18800(speed * Math.cos(rad), MoveUtil.mc.field_1724.method_18798().field_1351, speed * Math.sin(rad));
    }

    public static void addMotion(double speed) {
        if (MoveUtil.mc.field_1724 == null) {
            return;
        }
        float yaw = MoveUtil.mc.field_1724.method_36454();
        double rad = Math.toRadians(yaw + 90.0f);
        class_243 velocity = MoveUtil.mc.field_1724.method_18798();
        MoveUtil.mc.field_1724.method_18800(velocity.field_1352 + speed * Math.cos(rad), velocity.field_1351, velocity.field_1350 + speed * Math.sin(rad));
    }

    public static double getSpeedBPS() {
        return MoveUtil.getMotion() * 20.0;
    }

    public static boolean isOnGround() {
        return MoveUtil.mc.field_1724 != null && MoveUtil.mc.field_1724.method_24828();
    }

    public static class_243 predictPosition(int ticks) {
        if (MoveUtil.mc.field_1724 == null) {
            return class_243.field_1353;
        }
        class_243 pos = MoveUtil.mc.field_1724.method_19538();
        class_243 velocity = MoveUtil.mc.field_1724.method_18798();
        for (int i = 0; i < ticks; ++i) {
            pos = pos.method_1019(velocity);
            velocity = velocity.method_18805(0.91, 0.98, 0.91);
        }
        return pos;
    }

    public static class_10185 getPlayerInput(float angleDiff) {
        boolean forward = false;
        boolean back = false;
        boolean left = false;
        boolean right = false;
        if ((double)angleDiff >= -22.5 && (double)angleDiff < 22.5) {
            forward = true;
        } else if ((double)angleDiff >= 22.5 && (double)angleDiff < 67.5) {
            forward = true;
            right = true;
        } else if ((double)angleDiff >= 67.5 && (double)angleDiff < 112.5) {
            right = true;
        } else if ((double)angleDiff >= 112.5 && (double)angleDiff < 157.5) {
            back = true;
            right = true;
        } else if ((double)angleDiff >= -67.5 && (double)angleDiff < -22.5) {
            forward = true;
            left = true;
        } else if ((double)angleDiff >= -112.5 && (double)angleDiff < -67.5) {
            left = true;
        } else if ((double)angleDiff >= -157.5 && (double)angleDiff < -112.5) {
            back = true;
            left = true;
        } else {
            back = true;
        }
        return new class_10185(forward, back, left, right, false, false, false);
    }

    public static float getdir() {
        if (MoveUtil.mc.field_1724 == null) {
            return -1.0f;
        }
        float forward = MoveUtil.mc.field_1724.field_3913.field_3905;
        float sideways = MoveUtil.mc.field_1724.field_3913.field_3907;
        if (forward == 0.0f && sideways == 0.0f) {
            return -1.0f;
        }
        float yaw = MoveUtil.mc.field_1724.method_36454();
        if (forward < 0.0f) {
            yaw += 180.0f;
        }
        float strafe = 1.0f;
        if (forward < 0.0f) {
            strafe = -0.5f;
        } else if (forward > 0.0f) {
            strafe = 0.5f;
        }
        if (sideways > 0.0f) {
            yaw -= 90.0f * strafe;
        }
        if (sideways < 0.0f) {
            yaw += 90.0f * strafe;
        }
        return yaw;
    }
}
