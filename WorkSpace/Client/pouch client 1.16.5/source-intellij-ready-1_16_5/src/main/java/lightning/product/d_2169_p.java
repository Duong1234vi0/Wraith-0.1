/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.K_3372_t;
import lightning.product.P_3504_Q;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.q_3401_q;
import lightning.product.u_530_F;
import lombok.Generated;

public class d_2169_p {
    private static boolean n_1700_B;
    private static float J_1907_R;
    private static float R_4764_Y;

    @Y_1740_V
    private void n_1700_B(K_3372_t e) {
        if (n_1700_B) {
            this.n_1700_B(e.n_1700_B, e.J_1907_R);
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    private void n_1700_B(q_3401_q e) {
        if (n_1700_B) {
            e.n_1700_B(new P_3504_Q(J_1907_R, R_4764_Y));
        } else {
            J_1907_R = e.R_4764_Y().t_148_a;
            R_4764_Y = e.R_4764_Y().s_956_w;
        }
    }

    public static void n_1700_B(boolean state) {
        if (n_1700_B != state) {
            n_1700_B = state;
            d_2169_p.G_564_y();
        }
    }

    private void n_1700_B(double yaw, double pitch) {
        double d0 = pitch * 0.15;
        double d1 = yaw * 0.15;
        R_4764_Y = (float)((double)R_4764_Y + d0);
        J_1907_R = (float)((double)J_1907_R + d1);
        R_4764_Y = u_530_F.n_1700_B(R_4764_Y, -90.0f, 90.0f);
    }

    private static void G_564_y() {
        MinecraftAccess.c_3005_b.Y_259_p.p_178_J = J_1907_R;
        MinecraftAccess.c_3005_b.Y_259_p.f_4016_n = R_4764_Y;
    }

    @Generated
    public static boolean n_1700_B() {
        return n_1700_B;
    }

    @Generated
    public static float J_1907_R() {
        return J_1907_R;
    }

    @Generated
    public static float R_4764_Y() {
        return R_4764_Y;
    }

    @Generated
    public static void n_1700_B(float freeYaw) {
        J_1907_R = freeYaw;
    }

    @Generated
    public static void J_1907_R(float freePitch) {
        R_4764_Y = freePitch;
    }
}


