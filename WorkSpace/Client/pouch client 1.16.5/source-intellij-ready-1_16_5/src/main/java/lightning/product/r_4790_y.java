/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_1446_q;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.d_2169_p;
import lightning.product.h_1015_G;
import lightning.product.u_530_F;

public class r_4790_y
implements MinecraftAccess {
    private static n_1700_B n_1700_B = lightning.product.r_4790_y$n_1700_B.R_4764_Y;
    private static float J_1907_R;
    private static float R_4764_Y;
    private static int G_564_y;
    private static int P_1922_E;
    private static int u_1723_Y;

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        if (n_1700_B == lightning.product.r_4790_y$n_1700_B.n_1700_B && ++u_1723_Y > P_1922_E) {
            n_1700_B = lightning.product.r_4790_y$n_1700_B.J_1907_R;
        }
        if (n_1700_B == lightning.product.r_4790_y$n_1700_B.J_1907_R && r_4790_y.n_1700_B(F_1446_q.n_1700_B(), R_4764_Y)) {
            n_1700_B = lightning.product.r_4790_y$n_1700_B.R_4764_Y;
            G_564_y = 0;
            d_2169_p.n_1700_B(false);
        }
    }

    public static void n_1700_B(F_1446_q rotation, float turnSpeed, int timeout, int priority) {
        r_4790_y.n_1700_B(rotation, turnSpeed, turnSpeed, timeout, priority);
    }

    public static void n_1700_B(F_1446_q rotation, float aimSpeed, float resetSpeed, float timeout, int priority) {
        if (G_564_y <= priority) {
            if (n_1700_B == lightning.product.r_4790_y$n_1700_B.R_4764_Y) {
                d_2169_p.n_1700_B(true);
            }
            J_1907_R = aimSpeed;
            R_4764_Y = resetSpeed;
            P_1922_E = (int)timeout;
            G_564_y = priority;
            n_1700_B = lightning.product.r_4790_y$n_1700_B.n_1700_B;
            u_1723_Y = 0;
            r_4790_y.n_1700_B(rotation, J_1907_R);
        }
    }

    public static void n_1700_B(F_1446_q rotation, float aimSpeed, float resetSpeed, int timeout, int priority) {
        if (G_564_y <= priority) {
            if (n_1700_B == lightning.product.r_4790_y$n_1700_B.R_4764_Y) {
                d_2169_p.n_1700_B(true);
            }
            J_1907_R = aimSpeed;
            R_4764_Y = resetSpeed;
            P_1922_E = timeout;
            G_564_y = priority;
            n_1700_B = lightning.product.r_4790_y$n_1700_B.n_1700_B;
            u_1723_Y = 0;
            r_4790_y.n_1700_B(rotation, J_1907_R);
        }
    }

    private static boolean n_1700_B(F_1446_q rotation, float turnSpeed) {
        F_1446_q currentRotation = new F_1446_q(r_4790_y.c_3005_b.Y_259_p);
        float yawDelta = u_530_F.v_4262_N(rotation.R_4764_Y() - currentRotation.R_4764_Y());
        float pitchDelta = rotation.G_564_y() - currentRotation.G_564_y();
        float totalDelta = Math.abs(yawDelta) + Math.abs(pitchDelta);
        float yawSpeed = totalDelta == 0.0f ? 0.0f : Math.abs(yawDelta / totalDelta) * turnSpeed;
        float pitchSpeed = totalDelta == 0.0f ? 0.0f : Math.abs(pitchDelta / totalDelta) * turnSpeed;
        float newYaw = r_4790_y.c_3005_b.Y_259_p.p_178_J + u_530_F.n_1700_B(yawDelta, -yawSpeed, yawSpeed);
        float newPitch = r_4790_y.c_3005_b.Y_259_p.f_4016_n + u_530_F.n_1700_B(pitchDelta, -pitchSpeed, pitchSpeed);
        newYaw = r_4790_y.n_1700_B(r_4790_y.c_3005_b.Y_259_p.p_178_J, newYaw);
        newPitch = r_4790_y.n_1700_B(r_4790_y.c_3005_b.Y_259_p.f_4016_n, newPitch);
        newPitch = u_530_F.n_1700_B(newPitch, -90.0f, 90.0f);
        r_4790_y.c_3005_b.Y_259_p.p_178_J = newYaw;
        r_4790_y.c_3005_b.Y_259_p.f_4016_n = newPitch;
        F_1446_q finalRotation = new F_1446_q(r_4790_y.c_3005_b.Y_259_p);
        u_1723_Y = 0;
        return finalRotation.n_1700_B(rotation) < (double)turnSpeed;
    }

    public static float n_1700_B(float lastYaw, float current) {
        double sens = r_4790_y.c_3005_b.P_4830_p.n_1700_B * (double)0.6f + (double)0.2f;
        double gcd = sens * sens * sens * 8.0;
        return (float)((double)lastYaw + Math.ceil((double)(current - lastYaw) / gcd / (double)0.15f) * gcd * (double)0.15f);
    }

    public static boolean n_1700_B() {
        return n_1700_B != lightning.product.r_4790_y$n_1700_B.R_4764_Y;
    }

    public static void J_1907_R() {
        n_1700_B = lightning.product.r_4790_y$n_1700_B.R_4764_Y;
        G_564_y = 0;
        u_1723_Y = 0;
        d_2169_p.n_1700_B(false);
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.r_4790_y$n_1700_B.n_1700_B();
        }
    }
}


