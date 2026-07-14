/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.Set;
import lightning.product.F_2904_S;
import lightning.product.BlockHitResult;
import lightning.product.TutorialToast;
import lightning.product.I_14_v;
import lightning.product.HitResult;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.V_772_m;
import lightning.product.W_1671_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.k_4690_i;
import lightning.product.t_4467_k;
import lightning.product.TutorialStepInstance;
import lightning.product.x_282_a;

/*
 * Renamed from lightning.product.J_744_K
 */
public class j_744_k_0
implements TutorialStepInstance {
    private static final Set<T_2915_h> n_1700_B = Sets.newHashSet((Object[])new T_2915_h[]{a_3742_W.z_1737_N, a_3742_W.v_4276_D, a_3742_W.d_2461_k, a_3742_W.G_624_v, a_3742_W.T_2506_i, a_3742_W.q_4610_l, a_3742_W.X_1303_p, a_3742_W.T_4001_f, a_3742_W.Z_976_R, a_3742_W.H_1990_U, a_3742_W.N_2525_X, a_3742_W.c_4037_x, a_3742_W.g_2268_R, a_3742_W.T_3594_S, a_3742_W.A_2629_w, a_3742_W.M_712_N, a_3742_W.A_1038_p, a_3742_W.i_1637_u, a_3742_W.Ping, a_3742_W.p_178_J, a_3742_W.RealmsClientConfig, a_3742_W.f_4016_n, a_3742_W.LockSlot, a_3742_W.J_1008_m});
    private static final x_282_a J_1907_R = new F_2904_S("tutorial.find_tree.title");
    private static final x_282_a R_4764_Y = new F_2904_S("tutorial.find_tree.description");
    private final W_1671_y G_564_y;
    private TutorialToast P_1922_E;
    private int u_1723_Y;

    public j_744_k_0(W_1671_y tutorial) {
        this.G_564_y = tutorial;
    }

    @Override
    public void n_1700_B() {
        ++this.u_1723_Y;
        if (this.G_564_y.u_1723_Y() != I_14_v.J_1907_R) {
            this.G_564_y.n_1700_B(t_4467_k.u_1723_Y);
        } else {
            V_772_m clientplayerentity;
            if (this.u_1723_Y == 1 && (clientplayerentity = this.G_564_y.P_1922_E().Y_259_p) != null) {
                for (T_2915_h block : n_1700_B) {
                    if (!clientplayerentity.l_1268_F.w_1484_f(new Z_1993_T(block))) continue;
                    this.G_564_y.n_1700_B(t_4467_k.P_1922_E);
                    return;
                }
                if (j_744_k_0.n_1700_B(clientplayerentity)) {
                    this.G_564_y.n_1700_B(t_4467_k.P_1922_E);
                    return;
                }
            }
            if (this.u_1723_Y >= 6000 && this.P_1922_E == null) {
                this.P_1922_E = new TutorialToast(TutorialToast.n_1700_B.R_4764_Y, J_1907_R, R_4764_Y, false);
                this.G_564_y.P_1922_E().e_1992_r().n_1700_B(this.P_1922_E);
            }
        }
    }

    @Override
    public void J_1907_R() {
        if (this.P_1922_E != null) {
            this.P_1922_E.G_564_y();
            this.P_1922_E = null;
        }
    }

    @Override
    public void n_1700_B(k_4690_i worldIn, HitResult result) {
        K_4074_S blockstate;
        if (result.R_4764_Y() == HitResult.n_1700_B.J_1907_R && n_1700_B.contains((blockstate = worldIn.getBlockState(((BlockHitResult)result).n_1700_B())).J_1907_R())) {
            this.G_564_y.n_1700_B(t_4467_k.R_4764_Y);
        }
    }

    @Override
    public void n_1700_B(Z_1993_T stack) {
        for (T_2915_h block : n_1700_B) {
            if (stack.J_1907_R() != block.u_1723_Y()) continue;
            this.G_564_y.n_1700_B(t_4467_k.P_1922_E);
            return;
        }
    }

    public static boolean n_1700_B(V_772_m p_194070_0_) {
        for (T_2915_h block : n_1700_B) {
            if (p_194070_0_.Q_4569_t().n_1700_B(Stats.n_1700_B.J_1907_R(block)) <= 0) continue;
            return true;
        }
        return false;
    }
}



