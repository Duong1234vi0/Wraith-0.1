/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import java.util.List;
import lightning.product.LeashFenceKnotEntity;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.e_2866_D;
import lightning.product.g_4407_j;
import lightning.product.Goal;
import lightning.product.t_5_h;

public class w_4807_f
extends Goal {
    public final g_4407_j n_1700_B;
    private double J_1907_R;
    private int R_4764_Y;

    public w_4807_f(g_4407_j llamaIn, double speedModifierIn) {
        this.n_1700_B = llamaIn;
        this.J_1907_R = speedModifierIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        if (!this.n_1700_B.n_4915_F() && !this.n_1700_B.c_1732_c()) {
            List<N_4263_v> list = this.n_1700_B.O_508_d.J_1907_R((N_4263_v)this.n_1700_B, this.n_1700_B.i_601_W().grow(9.0, 4.0, 9.0), entity -> {
                t_5_h<?> entitytype = entity.f_4016_n();
                return entitytype == t_5_h.g_221_o || entitytype == t_5_h.F_1410_V;
            });
            Z_530_i llamaentity = null;
            double d0 = Double.MAX_VALUE;
            for (N_4263_v entity2 : list) {
                double d1;
                g_4407_j llamaentity1 = (g_4407_j)entity2;
                if (!llamaentity1.c_1732_c() || llamaentity1.H_1475_K() || (d1 = this.n_1700_B.G_564_y((N_4263_v)llamaentity1)) > d0) continue;
                d0 = d1;
                llamaentity = llamaentity1;
            }
            if (llamaentity == null) {
                for (N_4263_v entity1 : list) {
                    double d2;
                    g_4407_j llamaentity2 = (g_4407_j)entity1;
                    if (!llamaentity2.n_4915_F() || llamaentity2.H_1475_K() || (d2 = this.n_1700_B.G_564_y((N_4263_v)llamaentity2)) > d0) continue;
                    d0 = d2;
                    llamaentity = llamaentity2;
                }
            }
            if (llamaentity == null) {
                return false;
            }
            if (d0 < 4.0) {
                return false;
            }
            if (!llamaentity.n_4915_F() && !this.n_1700_B((g_4407_j)llamaentity, 1)) {
                return false;
            }
            this.n_1700_B.n_1700_B((g_4407_j)llamaentity);
            return true;
        }
        return false;
    }

    @Override
    public boolean J_1907_R() {
        if (this.n_1700_B.c_1732_c() && this.n_1700_B.I_2209_R().RealmsLongRunningMcoTaskScreen() && this.n_1700_B(this.n_1700_B, 0)) {
            double d0 = this.n_1700_B.G_564_y((N_4263_v)this.n_1700_B.I_2209_R());
            if (d0 > 676.0) {
                if (this.J_1907_R <= 3.0) {
                    this.J_1907_R *= 1.2;
                    this.R_4764_Y = 40;
                    return true;
                }
                if (this.R_4764_Y == 0) {
                    return false;
                }
            }
            if (this.R_4764_Y > 0) {
                --this.R_4764_Y;
            }
            return true;
        }
        return false;
    }

    @Override
    public void G_564_y() {
        this.n_1700_B.R_3213_X();
        this.J_1907_R = 2.1;
    }

    @Override
    public void P_1922_E() {
        if (this.n_1700_B.c_1732_c() && !(this.n_1700_B.y_2622_c() instanceof LeashFenceKnotEntity)) {
            g_4407_j llamaentity = this.n_1700_B.I_2209_R();
            double d0 = this.n_1700_B.R_4764_Y((N_4263_v)llamaentity);
            float f = 2.0f;
            e_2866_D vector3d = new e_2866_D(llamaentity.O_3598_v() - this.n_1700_B.O_3598_v(), llamaentity.X_2960_b() - this.n_1700_B.X_2960_b(), llamaentity.l_2647_k() - this.n_1700_B.l_2647_k()).G_564_y().n_1700_B(Math.max(d0 - 2.0, 0.0));
            this.n_1700_B.e_4240_b().n_1700_B(this.n_1700_B.O_3598_v() + vector3d.J_1907_R, this.n_1700_B.X_2960_b() + vector3d.R_4764_Y, this.n_1700_B.l_2647_k() + vector3d.G_564_y, this.J_1907_R);
        }
    }

    private boolean n_1700_B(g_4407_j llama, int p_190858_2_) {
        if (p_190858_2_ > 8) {
            return false;
        }
        if (llama.c_1732_c()) {
            if (llama.I_2209_R().n_4915_F()) {
                return true;
            }
            g_4407_j llamaentity = llama.I_2209_R();
            return this.n_1700_B(llamaentity, ++p_190858_2_);
        }
        return false;
    }
}


