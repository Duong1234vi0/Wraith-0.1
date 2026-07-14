/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.SoundEvents;
import lightning.product.X_1924_A;
import lightning.product.LidBlockEntity;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.i_2154_H;
import lightning.product.BlockEntityType;
import lightning.product.u_530_F;

public class s_3081_t
extends i_2154_H
implements X_1924_A,
LidBlockEntity {
    public float n_1700_B;
    public float J_1907_R;
    public int R_4764_Y;
    private int G_564_y;

    public s_3081_t() {
        super(BlockEntityType.G_564_y);
    }

    @Override
    public void P_1922_E() {
        if (++this.G_564_y % 20 * 4 == 0) {
            this.u_2550_I.n_1700_B(this.M_588_G, a_3742_W.k_2348_i, 1, this.R_4764_Y);
        }
        this.J_1907_R = this.n_1700_B;
        int i = this.M_588_G.getX();
        int j = this.M_588_G.getY();
        int k = this.M_588_G.getZ();
        float f = 0.1f;
        if (this.R_4764_Y > 0 && this.n_1700_B == 0.0f) {
            double d0 = (double)i + 0.5;
            double d1 = (double)k + 0.5;
            this.u_2550_I.n_1700_B((a_3913_L)null, d0, (double)j + 0.5, d1, SoundEvents.q_817_e, D_38_f.P_1922_E, 0.5f, this.u_2550_I.w_1457_N.nextFloat() * 0.1f + 0.9f);
        }
        if (this.R_4764_Y == 0 && this.n_1700_B > 0.0f || this.R_4764_Y > 0 && this.n_1700_B < 1.0f) {
            float f2 = this.n_1700_B;
            this.n_1700_B = this.R_4764_Y > 0 ? (this.n_1700_B += 0.1f) : (this.n_1700_B -= 0.1f);
            if (this.n_1700_B > 1.0f) {
                this.n_1700_B = 1.0f;
            }
            float f1 = 0.5f;
            if (this.n_1700_B < 0.5f && f2 >= 0.5f) {
                double d3 = (double)i + 0.5;
                double d2 = (double)k + 0.5;
                this.u_2550_I.n_1700_B((a_3913_L)null, d3, (double)j + 0.5, d2, SoundEvents.M_3508_C, D_38_f.P_1922_E, 0.5f, this.u_2550_I.w_1457_N.nextFloat() * 0.1f + 0.9f);
            }
            if (this.n_1700_B < 0.0f) {
                this.n_1700_B = 0.0f;
            }
        }
    }

    @Override
    public boolean a_(int id, int type) {
        if (id == 1) {
            this.R_4764_Y = type;
            return true;
        }
        return super.a_(id, type);
    }

    @Override
    public void I_() {
        this.d_2427_y();
        super.I_();
    }

    public void v_4262_N() {
        ++this.R_4764_Y;
        this.u_2550_I.n_1700_B(this.M_588_G, a_3742_W.k_2348_i, 1, this.R_4764_Y);
    }

    public void w_1484_f() {
        --this.R_4764_Y;
        this.u_2550_I.n_1700_B(this.M_588_G, a_3742_W.k_2348_i, 1, this.R_4764_Y);
    }

    public boolean n_1700_B(a_3913_L player) {
        if (this.u_2550_I.getTileEntity(this.M_588_G) != this) {
            return false;
        }
        return !(player.v_4262_N((double)this.M_588_G.getX() + 0.5, (double)this.M_588_G.getY() + 0.5, (double)this.M_588_G.getZ() + 0.5) > 64.0);
    }

    @Override
    public float n_1700_B(float partialTicks) {
        return u_530_F.v_4262_N(partialTicks, this.J_1907_R, this.n_1700_B);
    }
}


