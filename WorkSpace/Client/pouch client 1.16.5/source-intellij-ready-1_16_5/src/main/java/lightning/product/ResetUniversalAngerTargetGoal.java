/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.A_2352_Z;
import lightning.product.G_3246_f;
import lightning.product.Attributes;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;

public class ResetUniversalAngerTargetGoal<T extends Z_530_i>
extends Goal {
    private final T n_1700_B;
    private final boolean J_1907_R;
    private int R_4764_Y;

    public ResetUniversalAngerTargetGoal(T p_i241234_1_, boolean p_i241234_2_) {
        this.n_1700_B = p_i241234_1_;
        this.J_1907_R = p_i241234_2_;
    }

    @Override
    public boolean n_1700_B() {
        return ((Z_530_i)this.n_1700_B).O_508_d.H_1990_U().J_1907_R(A_2352_Z.e_4240_b) && this.v_4262_N();
    }

    private boolean v_4262_N() {
        return ((r_4811_B)this.n_1700_B).q_817_e() != null && ((r_4811_B)this.n_1700_B).q_817_e().f_4016_n() == t_5_h.g_4106_L && ((r_4811_B)this.n_1700_B).r_260_T() > this.R_4764_Y;
    }

    @Override
    public void R_4764_Y() {
        this.R_4764_Y = ((r_4811_B)this.n_1700_B).r_260_T();
        ((G_3246_f)this.n_1700_B).v_4262_N();
        if (this.J_1907_R) {
            this.w_1484_f().stream().filter(mob -> mob != this.n_1700_B).map(mob -> (G_3246_f)((Object)mob)).forEach(G_3246_f::v_4262_N);
        }
        super.R_4764_Y();
    }

    private List<Z_530_i> w_1484_f() {
        double d0 = ((r_4811_B)this.n_1700_B).J_1907_R(Attributes.J_1907_R);
        I_4817_s axisalignedbb = I_4817_s.fromVector(((N_4263_v)this.n_1700_B).s_4990_V()).grow(d0, 10.0, d0);
        return ((Z_530_i)this.n_1700_B).O_508_d.J_1907_R(this.n_1700_B.getClass(), axisalignedbb);
    }
}


