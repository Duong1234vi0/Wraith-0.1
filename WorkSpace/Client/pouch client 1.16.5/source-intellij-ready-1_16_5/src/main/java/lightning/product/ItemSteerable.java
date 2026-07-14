/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.e_2866_D;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.ItemBasedSteering;

public interface ItemSteerable {
    public boolean P_1922_E();

    public void n_1700_B(e_2866_D var1);

    public float u_1723_Y();

    default public boolean n_1700_B(Z_530_i mount, ItemBasedSteering helper, e_2866_D p_233622_3_) {
        N_4263_v entity;
        if (!mount.RealmsLongRunningMcoTaskScreen()) {
            return false;
        }
        N_4263_v n_4263_v = entity = mount.o_3599_Z().isEmpty() ? null : mount.o_3599_Z().get(0);
        if (mount.H_1883_T() && mount.g_2268_R() && entity instanceof a_3913_L) {
            mount.j_276_v = mount.p_178_J = entity.p_178_J;
            mount.f_4016_n = entity.f_4016_n * 0.5f;
            mount.J_1907_R(mount.p_178_J, mount.f_4016_n);
            mount.C_1162_e = mount.p_178_J;
            mount.f_3449_S = mount.p_178_J;
            mount.RealmsServerPing = 1.0f;
            mount.y_2772_m = mount.l_2995_s() * 0.1f;
            if (helper.n_1700_B && helper.J_1907_R++ > helper.R_4764_Y) {
                helper.n_1700_B = false;
            }
            if (mount.v_887_r()) {
                float f = this.u_1723_Y();
                if (helper.n_1700_B) {
                    f += f * 1.15f * u_530_F.n_1700_B((float)helper.J_1907_R / (float)helper.R_4764_Y * (float)Math.PI);
                }
                mount.w_1457_N(f);
                this.n_1700_B(new e_2866_D(0.0, 0.0, 1.0));
                mount.O_1309_Q = 0;
            } else {
                mount.n_1700_B((r_4811_B)mount, false);
                mount.v_4262_N(e_2866_D.n_1700_B);
            }
            return true;
        }
        mount.RealmsServerPing = 0.5f;
        mount.y_2772_m = 0.02f;
        this.n_1700_B(p_233622_3_);
        return false;
    }
}


