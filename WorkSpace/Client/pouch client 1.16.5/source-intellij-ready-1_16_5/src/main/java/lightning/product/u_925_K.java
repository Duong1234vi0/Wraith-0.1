/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_4817_s;
import lightning.product.a_178_J;
import lightning.product.MinecraftAccess;
import lightning.product.u_530_F;

public class u_925_K
implements MinecraftAccess {
    public static boolean n_1700_B() {
        return (double)u_925_K.c_3005_b.Y_259_p.G_564_y.moveStrafe != 0.0 || (double)u_925_K.c_3005_b.Y_259_p.G_564_y.moveForward != 0.0;
    }

    public static double n_1700_B(float rotationYaw, double moveForward, double moveStrafing) {
        if (moveForward < 0.0) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (moveForward < 0.0) {
            forward = -0.5f;
        } else if (moveForward > 0.0) {
            forward = 0.5f;
        }
        if (moveStrafing > 0.0) {
            rotationYaw -= 90.0f * forward;
        }
        if (moveStrafing < 0.0) {
            rotationYaw += 90.0f * forward;
        }
        return Math.toRadians(rotationYaw);
    }

    public static void n_1700_B(a_178_J event, float yaw) {
        float forward = event.n_1700_B();
        float strafe = event.J_1907_R();
        double angle = u_530_F.u_1723_Y(Math.toDegrees(u_925_K.n_1700_B(u_925_K.c_3005_b.Y_259_p.k_578_l() ? u_925_K.c_3005_b.Y_259_p.p_178_J : yaw, forward, strafe)));
        if (forward == 0.0f && strafe == 0.0f) {
            return;
        }
        float closestForward = 0.0f;
        float closestStrafe = 0.0f;
        float closestDifference = Float.MAX_VALUE;
        for (float predictedForward = -1.0f; predictedForward <= 1.0f; predictedForward += 1.0f) {
            for (float predictedStrafe = -1.0f; predictedStrafe <= 1.0f; predictedStrafe += 1.0f) {
                double predictedAngle;
                double difference;
                if (predictedStrafe == 0.0f && predictedForward == 0.0f || !((difference = Math.abs(angle - (predictedAngle = u_530_F.u_1723_Y(Math.toDegrees(u_925_K.n_1700_B(u_925_K.c_3005_b.Y_259_p.p_178_J, predictedForward, predictedStrafe)))))) < (double)closestDifference)) continue;
                closestDifference = (float)difference;
                closestForward = predictedForward;
                closestStrafe = predictedStrafe;
            }
        }
        event.n_1700_B(closestForward);
        event.J_1907_R(closestStrafe);
    }

    public static void n_1700_B(double speed) {
        if (!u_925_K.n_1700_B()) {
            return;
        }
        double yaw = u_925_K.n_1700_B(true);
        u_925_K.c_3005_b.Y_259_p.h_1847_R(-Math.sin(yaw) * speed, u_925_K.c_3005_b.Y_259_p.Ping.R_4764_Y, Math.cos(yaw) * speed);
    }

    public static double n_1700_B(boolean toRadians) {
        float rotationYaw = u_925_K.c_3005_b.Y_259_p.p_178_J;
        if (u_925_K.c_3005_b.Y_259_p.L_4248_u < 0.0f) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (u_925_K.c_3005_b.Y_259_p.L_4248_u < 0.0f) {
            forward = -0.5f;
        } else if (u_925_K.c_3005_b.Y_259_p.L_4248_u > 0.0f) {
            forward = 0.5f;
        }
        if (u_925_K.c_3005_b.Y_259_p.L_1362_X > 0.0f) {
            rotationYaw -= 90.0f * forward;
        }
        if (u_925_K.c_3005_b.Y_259_p.L_1362_X < 0.0f) {
            rotationYaw += 90.0f * forward;
        }
        return toRadians ? Math.toRadians(rotationYaw) : (double)rotationYaw;
    }

    public static boolean n_1700_B(float under) {
        if (u_925_K.c_3005_b.Y_259_p.X_2960_b() < 0.0) {
            return false;
        }
        I_4817_s aab = u_925_K.c_3005_b.Y_259_p.i_601_W().offset(0.0, -under, 0.0);
        return u_925_K.c_3005_b.Y_601_j.J_1907_R(u_925_K.c_3005_b.Y_259_p, aab).toList().isEmpty();
    }
}



