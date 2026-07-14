/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.i_218_M;
import lightning.product.y_2603_k;
import lightning.product.z_2909_G;
import lombok.Generated;

public class j_4679_k
extends X_3546_T {
    private static long v_4262_N;
    private static boolean w_1484_f;

    public j_4679_k() {
        super("Sprint", y_2603_k.J_1907_R);
        this.n_1700_B(new i_218_M[0]);
    }

    public boolean h_1847_R() {
        return false;
    }

    private boolean t_1786_h() {
        double z;
        if (j_4679_k.c_3005_b.Y_259_p == null || j_4679_k.c_3005_b.Y_601_j == null) {
            return false;
        }
        e_2866_D playerPos = j_4679_k.c_3005_b.Y_259_p.s_4990_V();
        c_1514_x playerBlockPos = new c_1514_x(playerPos);
        c_1514_x belowPos = playerBlockPos.down();
        if (j_4679_k.c_3005_b.Y_601_j.getBlockState(belowPos).J_1907_R() instanceof z_2909_G) {
            return true;
        }
        double yaw = Math.toRadians(j_4679_k.c_3005_b.Y_259_p.p_178_J);
        double x = -Math.sin(yaw);
        c_1514_x frontPos = new c_1514_x(playerPos.J_1907_R + x, playerPos.R_4764_Y, playerPos.G_564_y + (z = Math.cos(yaw)));
        if (j_4679_k.c_3005_b.Y_601_j.getBlockState(frontPos).J_1907_R() instanceof z_2909_G) {
            return true;
        }
        c_1514_x frontBelowPos = frontPos.down();
        return j_4679_k.c_3005_b.Y_601_j.getBlockState(frontBelowPos).J_1907_R() instanceof z_2909_G;
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (j_4679_k.c_3005_b.Y_259_p == null) {
            return;
        }
        if (System.currentTimeMillis() >= v_4262_N && !w_1484_f) {
            w_1484_f = true;
            v_4262_N = 0L;
            if (j_4679_k.c_3005_b.Y_259_p.D_60_a && (j_4679_k.c_3005_b.Y_259_p.L_4248_u > 0.0f || j_4679_k.c_3005_b.Y_259_p.L_1362_X != 0.0f)) {
                j_4679_k.c_3005_b.Y_259_p.b_(false);
                return;
            }
            if (w_1484_f && j_4679_k.c_3005_b.Y_259_p.L_4248_u > 0.0f && !j_4679_k.c_3005_b.Y_259_p.q_2307_F()) {
                j_4679_k.c_3005_b.Y_259_p.b_(true);
            }
        }
    }

    @Generated
    public static long Q_4569_t() {
        return v_4262_N;
    }

    @Generated
    public static void n_1700_B(long unlockTime) {
        v_4262_N = unlockTime;
    }

    @Generated
    public static boolean M_182_A() {
        return w_1484_f;
    }

    @Generated
    public static void P_1922_E(boolean sprinting) {
        w_1484_f = sprinting;
    }

    static {
        w_1484_f = true;
    }
}

