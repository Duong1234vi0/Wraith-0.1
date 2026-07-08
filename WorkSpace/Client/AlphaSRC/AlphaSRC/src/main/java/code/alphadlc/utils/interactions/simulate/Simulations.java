/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10185
 *  net.minecraft.class_1297
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.utils.interactions.simulate;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import java.util.Objects;
import net.minecraft.class_10185;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public final class Simulations
implements QuickImports {
    public static boolean hasPlayerMovement() {
        return Simulations.mc.field_1724.field_3913.field_3905 != 0.0f || Simulations.mc.field_1724.field_3913.field_3907 != 0.0f;
    }

    public static double[] calculateDirection(double distance) {
        return Simulations.calculateDirection(Simulations.mc.field_1724.field_3913.field_3905, Simulations.mc.field_1724.field_3913.field_3907, distance);
    }

    public static final boolean moveKeyPressed(int keyNumber) {
        boolean w = Simulations.mc.field_1690.field_1894.method_1434();
        boolean a = Simulations.mc.field_1690.field_1913.method_1434();
        boolean s = Simulations.mc.field_1690.field_1881.method_1434();
        boolean d = Simulations.mc.field_1690.field_1849.method_1434();
        return keyNumber == 0 ? w : (keyNumber == 1 ? a : (keyNumber == 2 ? s : keyNumber == 3 && d));
    }

    public static final boolean w() {
        return Simulations.moveKeyPressed(0);
    }

    public static final boolean a() {
        return Simulations.moveKeyPressed(1);
    }

    public static final boolean s() {
        return Simulations.moveKeyPressed(2);
    }

    public static final boolean d() {
        return Simulations.moveKeyPressed(3);
    }

    public static final float moveYaw(float entityYaw) {
        return entityYaw + (float)(!Simulations.a() || !Simulations.d() || Simulations.w() && Simulations.s() || !Simulations.w() && !Simulations.s() ? (Simulations.w() && Simulations.s() && (!Simulations.a() || !Simulations.d()) && (Simulations.a() || Simulations.d()) ? (Simulations.a() ? -90 : (Simulations.d() ? 90 : 0)) : (Simulations.a() && Simulations.d() && (!Simulations.w() || !Simulations.s()) || Simulations.w() && Simulations.s() && (!Simulations.a() || !Simulations.d()) ? 0 : (!(Simulations.a() || Simulations.d() || Simulations.s()) ? 0 : (Simulations.w() && !Simulations.s() ? 45 : (Simulations.s() && !Simulations.w() ? (!Simulations.a() && !Simulations.d() ? 180 : 135) : (!(!Simulations.w() && !Simulations.s() || Simulations.w() && Simulations.s()) ? 0 : 90))) * (Simulations.a() ? -1 : 1)))) : (Simulations.w() ? 0 : (Simulations.s() ? 180 : 0)));
    }

    public static double[] forward(double d) {
        float f = Simulations.mc.field_1724.field_3913.field_3905;
        float f2 = Simulations.mc.field_1724.field_3913.field_3907;
        float f3 = TurnsConnection.INSTANCE.getRotation().getYaw();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d3 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d4 = (double)f * d * d3 + (double)f2 * d * d2;
        double d5 = (double)f * d * d2 - (double)f2 * d * d3;
        return new double[]{d4, d5};
    }

    public static float calculateBodyYaw(float yaw, float prevBodyYaw, double prevX, double prevZ, double currentX, double currentZ, float handSwingProgress) {
        double motionX = currentX - prevX;
        double motionZ = currentZ - prevZ;
        float motionSquared = (float)(motionX * motionX + motionZ * motionZ);
        float bodyYaw = prevBodyYaw;
        float swing = Simulations.mc.field_1724.field_6251;
        if (motionSquared > 0.0025000002f) {
            float movementYaw = (float)class_3532.method_15349((double)motionZ, (double)motionX) * 57.295776f - 90.0f;
            float yawDiff = class_3532.method_15379((float)(class_3532.method_15393((float)yaw) - movementYaw));
            bodyYaw = 95.0f < yawDiff && yawDiff < 265.0f ? movementYaw - 180.0f : movementYaw;
        }
        if (Simulations.mc.field_1724 != null && Simulations.mc.field_1724.field_6251 - 0.3f > 0.0f) {
            bodyYaw = yaw;
        }
        float deltaYaw = class_3532.method_15393((float)(bodyYaw - prevBodyYaw));
        bodyYaw = prevBodyYaw + deltaYaw * 0.32f;
        float yawOffsetDiff = class_3532.method_15393((float)(yaw - bodyYaw));
        float maxHeadRotation = 52.0f;
        if (Math.abs(yawOffsetDiff) > maxHeadRotation) {
            bodyYaw += yawOffsetDiff - (float)class_3532.method_17822((double)yawOffsetDiff) * maxHeadRotation;
        }
        return bodyYaw;
    }

    public static double kizdamati() {
        return 1488.0;
    }

    public static String fpsADDS() {
        return null;
    }

    public static double[] calculateDirection(float forward, float sideways, double distance) {
        float yaw = TurnsConnection.INSTANCE.getRotation().getYaw();
        if (forward != 0.0f) {
            if (sideways > 0.0f) {
                yaw += forward > 0.0f ? -45.0f : 45.0f;
            } else if (sideways < 0.0f) {
                yaw += forward > 0.0f ? 45.0f : -45.0f;
            }
            sideways = 0.0f;
            forward = forward > 0.0f ? 1.0f : -1.0f;
        }
        double sinYaw = Math.sin(Math.toRadians(yaw + 90.0f));
        double cosYaw = Math.cos(Math.toRadians(yaw + 90.0f));
        double xMovement = (double)forward * distance * cosYaw + (double)sideways * distance * sinYaw;
        double zMovement = (double)forward * distance * sinYaw - (double)sideways * distance * cosYaw;
        return new double[]{xMovement, zMovement};
    }

    public static double getSpeedSqrt(class_1297 entity) {
        return Math.sqrt(entity.method_5707(new class_243(entity.field_6014, entity.field_6036, entity.field_5969)));
    }

    public static void setVelocity(double velocity) {
        double[] direction = Simulations.calculateDirection(velocity);
        Objects.requireNonNull(Simulations.mc.field_1724).method_18800(direction[0], Simulations.mc.field_1724.method_18798().method_10214(), direction[1]);
    }

    public static void setVelocity(double velocity, double y) {
        double[] direction = Simulations.calculateDirection(velocity);
        Objects.requireNonNull(Simulations.mc.field_1724).method_18800(direction[0], y, direction[1]);
    }

    public static double getDegreesRelativeToView(class_243 positionRelativeToPlayer, float yaw) {
        float optimalYaw = (float)Math.atan2(-positionRelativeToPlayer.field_1352, positionRelativeToPlayer.field_1350);
        double currentYaw = Math.toRadians(class_3532.method_15393((float)yaw));
        return Math.toDegrees(class_3532.method_15338((double)((double)optimalYaw - currentYaw)));
    }

    public static class_10185 getDirectionalInputForDegrees(class_10185 input, double dgs, float deadAngle) {
        boolean forwards = input.comp_3159();
        boolean backwards = input.comp_3160();
        boolean left = input.comp_3161();
        boolean right = input.comp_3162();
        if (dgs >= (double)(-90.0f + deadAngle) && dgs <= (double)(90.0f - deadAngle)) {
            forwards = true;
        } else if (dgs < (double)(-90.0f - deadAngle) || dgs > (double)(90.0f + deadAngle)) {
            backwards = true;
        }
        if (dgs >= (double)(0.0f + deadAngle) && dgs <= (double)(180.0f - deadAngle)) {
            right = true;
        } else if (dgs >= (double)(-180.0f + deadAngle) && dgs <= (double)(0.0f - deadAngle)) {
            left = true;
        }
        return new class_10185(forwards, backwards, left, right, input.comp_3163(), input.comp_3164(), input.comp_3165());
    }

    public static class_10185 getDirectionalInputForDegrees(class_10185 input, double dgs) {
        return Simulations.getDirectionalInputForDegrees(input, dgs, 20.0f);
    }

    private Simulations() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
