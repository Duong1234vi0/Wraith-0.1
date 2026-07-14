/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.m_3054_I;
import lightning.product.t_5_h;
import lightning.product.x_1688_C;
import lightning.product.y_4319_k;

public class Minecart
extends y_4319_k {
    public Minecart(t_5_h<?> type, b_4507_u world) {
        super(type, world);
    }

    public Minecart(b_4507_u worldIn, double x, double y, double z) {
        super(t_5_h.g_164_R, worldIn, x, y, z);
    }

    @Override
    public m_3054_I n_1700_B(a_3913_L player, x_1688_C hand) {
        if (player.z_3000_g()) {
            return m_3054_I.R_4764_Y;
        }
        if (this.H_1883_T()) {
            return m_3054_I.R_4764_Y;
        }
        if (!this.O_508_d.Y_259_p) {
            return player.s_956_w(this) ? m_3054_I.J_1907_R : m_3054_I.R_4764_Y;
        }
        return m_3054_I.n_1700_B;
    }

    @Override
    public void n_1700_B(int x, int y, int z, boolean receivingPower) {
        if (receivingPower) {
            if (this.H_1883_T()) {
                this.C_3538_G();
            }
            if (this.t_148_a() == 0) {
                this.J_1907_R(-this.u_2550_I());
                this.n_1700_B(10);
                this.n_1700_B(50.0f);
                this.RealmsCreateRealmScreen();
            }
        }
    }

    @Override
    public y_4319_k.n_1700_B h_1847_R() {
        return y_4319_k.n_1700_B.n_1700_B;
    }
}


