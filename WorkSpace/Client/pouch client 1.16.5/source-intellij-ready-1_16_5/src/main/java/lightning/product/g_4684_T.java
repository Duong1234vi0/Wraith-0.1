/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import java.util.function.Predicate;
import lightning.product.A_2352_Z;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.Z_530_i;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_3049_G;
import lightning.product.Goal;

public class g_4684_T
extends Goal {
    private static final Predicate<K_4074_S> n_1700_B = g_3049_G.n_1700_B(a_3742_W.u_744_e);
    private final Z_530_i J_1907_R;
    private final b_4507_u R_4764_Y;
    private int G_564_y;

    public g_4684_T(Z_530_i grassEaterEntityIn) {
        this.J_1907_R = grassEaterEntityIn;
        this.R_4764_Y = grassEaterEntityIn.O_508_d;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R, Goal.n_1700_B.R_4764_Y));
    }

    @Override
    public boolean n_1700_B() {
        if (this.J_1907_R.M_3508_C().nextInt(this.J_1907_R.d_() ? 50 : 1000) != 0) {
            return false;
        }
        c_1514_x blockpos = this.J_1907_R.b_2312_j();
        if (n_1700_B.test(this.R_4764_Y.getBlockState(blockpos))) {
            return true;
        }
        return this.R_4764_Y.getBlockState(blockpos.down()).n_1700_B(a_3742_W.t_148_a);
    }

    @Override
    public void R_4764_Y() {
        this.G_564_y = 40;
        this.R_4764_Y.n_1700_B((N_4263_v)this.J_1907_R, (byte)10);
        this.J_1907_R.e_4240_b().h_1847_R();
    }

    @Override
    public void G_564_y() {
        this.G_564_y = 0;
    }

    @Override
    public boolean J_1907_R() {
        return this.G_564_y > 0;
    }

    public int v_4262_N() {
        return this.G_564_y;
    }

    @Override
    public void P_1922_E() {
        this.G_564_y = Math.max(0, this.G_564_y - 1);
        if (this.G_564_y == 4) {
            c_1514_x blockpos = this.J_1907_R.b_2312_j();
            if (n_1700_B.test(this.R_4764_Y.getBlockState(blockpos))) {
                if (this.R_4764_Y.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
                    this.R_4764_Y.J_1907_R(blockpos, false);
                }
                this.J_1907_R.d_2427_y();
            } else {
                c_1514_x blockpos1 = blockpos.down();
                if (this.R_4764_Y.getBlockState(blockpos1).n_1700_B(a_3742_W.t_148_a)) {
                    if (this.R_4764_Y.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
                        this.R_4764_Y.R_4764_Y(2001, blockpos1, T_2915_h.s_956_w(a_3742_W.t_148_a.multiplayerClientSuggestionProvider()));
                        this.R_4764_Y.n_1700_B(blockpos1, a_3742_W.s_956_w.multiplayerClientSuggestionProvider(), 2);
                    }
                    this.J_1907_R.d_2427_y();
                }
            }
        }
    }
}


