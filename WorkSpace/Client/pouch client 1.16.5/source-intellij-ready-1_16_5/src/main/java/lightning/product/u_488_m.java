/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.ThreadLocalRandom;
import lightning.product.A_4514_U;
import lightning.product.F_1446_q;
import lightning.product.P_3504_Q;
import lightning.product.a_1344_X;
import lightning.product.MinecraftAccess;
import lightning.product.d_2169_p;
import lightning.product.e_2866_D;
import lightning.product.h_3066_J;
import lightning.product.r_4790_y;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public final class u_488_m
implements A_4514_U {
    private static float J_1907_R;

    private u_488_m() {
    }

    public static void n_1700_B(r_4811_B target, boolean isAttack, boolean blockBypass, int auraCount, P_3504_Q currentRotation) {
        if (MinecraftAccess.c_3005_b.Y_259_p == null || target == null) {
            return;
        }
        if (!MinecraftAccess.c_3005_b.Y_259_p.c_3005_b(target) && blockBypass) {
            u_488_m.n_1700_B(target, isAttack, auraCount, currentRotation);
        } else {
            u_488_m.n_1700_B(target, currentRotation);
        }
    }

    public static void n_1700_B(r_4811_B target, boolean isAttack, int auraCount, P_3504_Q currentRotation) {
        e_2866_D eyePos = MinecraftAccess.c_3005_b.Y_259_p.u_2550_I(MinecraftAccess.c_3005_b.RealmsClientConfig());
        e_2866_D aimRel = a_1344_X.n_1700_B(eyePos, target).G_564_y(eyePos).G_564_y();
        boolean attack = false;
        if (isAttack) {
            J_1907_R = 6.0f;
        }
        if (J_1907_R > 0.0f) {
            attack = true;
            J_1907_R -= 1.0f;
        }
        float angleY = u_530_F.v_4262_N(u_488_m.n_1700_B() + u_488_m.n_1700_B(-12.0f, 12.0f));
        float angleX = u_530_F.v_4262_N(u_488_m.J_1907_R() + u_488_m.n_1700_B(-12.0f, 12.0f));
        float yawSpeed = u_488_m.n_1700_B(12.0f, 24.0f);
        float pitchSpeed = u_488_m.n_1700_B(12.0f, 24.0f);
        float angleY1 = angleY;
        float angleX1 = angleX;
        if (attack) {
            double h = Math.hypot(aimRel.J_1907_R, aimRel.G_564_y);
            angleX1 = (float)u_530_F.n_1700_B(-Math.toDegrees(Math.atan2(aimRel.R_4764_Y, h)), -90.0, 90.0);
            angleY1 = (float)Math.toDegrees(Math.atan2(-aimRel.J_1907_R, aimRel.G_564_y));
            yawSpeed = u_488_m.n_1700_B(66.0f, 122.0f);
            pitchSpeed = u_488_m.n_1700_B(66.0f, 122.0f);
        } else {
            int mode = auraCount % 2;
            angleX1 = switch (mode) {
                case 0 -> -87.0f + u_488_m.n_1700_B(-3.0f, 3.0f);
                case 1 -> 87.0f + u_488_m.n_1700_B(-3.0f, 3.0f);
                default -> angleX;
            };
            angleY1 = u_530_F.v_4262_N(u_488_m.n_1700_B() + u_488_m.n_1700_B(-3.0f, 3.0f));
        }
        float lerpedYaw = u_530_F.t_148_a(MinecraftAccess.c_3005_b.Y_259_p.p_178_J, angleY1, 0.7f);
        float lerpedPitch = u_530_F.t_148_a(MinecraftAccess.c_3005_b.Y_259_p.f_4016_n, angleX1, 0.7f);
        r_4790_y.n_1700_B(new F_1446_q(angleY1, lerpedPitch), yawSpeed, pitchSpeed, 12, 14);
        currentRotation.t_148_a = lerpedYaw;
        currentRotation.s_956_w = angleX1;
    }

    private static void n_1700_B(r_4811_B target, P_3504_Q currentRotation) {
        e_2866_D predicted = u_488_m.n_1700_B(target);
        e_2866_D rel = predicted.G_564_y(MinecraftAccess.c_3005_b.Y_259_p.u_2550_I(1.0f));
        float yawToTarget = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(rel.G_564_y, rel.J_1907_R)) - 90.0);
        float pitchToTarget = (float)(-Math.toDegrees(Math.atan2(rel.R_4764_Y, Math.hypot(rel.J_1907_R, rel.G_564_y))));
        float currentYaw = MinecraftAccess.c_3005_b.Y_259_p.p_178_J;
        float currentPitch = MinecraftAccess.c_3005_b.Y_259_p.f_4016_n;
        float yawDelta = u_530_F.v_4262_N(yawToTarget - currentYaw);
        float pitchDelta = u_530_F.v_4262_N(pitchToTarget - currentPitch);
        float clampedYaw = Math.min(Math.max(Math.abs(yawDelta), 1.0f), 50.2f);
        float clampedPitch = Math.min(Math.max(Math.abs(pitchDelta), 1.0f), 16.2f);
        float targetYaw = currentYaw + (yawDelta > 0.0f ? clampedYaw : -clampedYaw);
        float targetPitch = currentPitch + (pitchDelta > 0.0f ? clampedPitch : -clampedPitch);
        float yaw = u_530_F.t_148_a(currentYaw, targetYaw, 0.977f);
        float pitch = u_530_F.t_148_a(currentPitch, targetPitch, 0.977f);
        yaw += u_488_m.n_1700_B(-3.0f, 3.0f);
        pitch += u_488_m.n_1700_B(-3.0f, 3.0f);
        pitch = u_530_F.n_1700_B(pitch, -90.0f, 90.0f);
        float gcd = h_3066_J.n_1700_B();
        if (gcd > 1.0E-6f) {
            yaw -= (yaw - currentYaw) % gcd;
            pitch -= (pitch - currentPitch) % gcd;
        }
        r_4790_y.n_1700_B(new F_1446_q(yaw, pitch), 360.0f, 360.0f, 15, 15);
        currentRotation.t_148_a = yaw;
        currentRotation.s_956_w = pitch;
    }

    private static e_2866_D n_1700_B(r_4811_B t) {
        float strength;
        double distanceToTarget = MinecraftAccess.c_3005_b.Y_259_p.R_4764_Y(t);
        float yaw = t.p_178_J;
        if (distanceToTarget <= 2.0) {
            strength = 0.19f;
        } else {
            float yawDelta = u_530_F.v_4262_N(t.p_178_J - t.j_276_v);
            yaw += yawDelta * 2.5f;
            strength = 0.15f;
        }
        double yawRad = Math.toRadians(yaw);
        e_2866_D forward = new e_2866_D(-Math.sin(yawRad), 0.0, Math.cos(yawRad));
        return t.s_4990_V().P_1922_E(forward.n_1700_B((double)strength)).J_1907_R(0.0, (double)t.v_165_F() * 0.7, 0.0);
    }

    private static float n_1700_B() {
        return d_2169_p.n_1700_B() ? d_2169_p.J_1907_R() : MinecraftAccess.c_3005_b.Y_259_p.p_178_J;
    }

    private static float J_1907_R() {
        return d_2169_p.n_1700_B() ? d_2169_p.R_4764_Y() : MinecraftAccess.c_3005_b.Y_259_p.f_4016_n;
    }

    private static float n_1700_B(float min, float max) {
        return min + ThreadLocalRandom.current().nextFloat() * (max - min);
    }
}



