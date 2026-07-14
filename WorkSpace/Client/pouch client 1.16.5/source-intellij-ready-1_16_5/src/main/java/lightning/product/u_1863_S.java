/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.CustomSpawner;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.Stats;
import lightning.product.V_3157_k;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.k_2895_h;
import lightning.product.m_3937_C;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.u_743_i;

public class u_1863_S
implements CustomSpawner {
    private int n_1700_B;

    @Override
    public int n_1700_B(e_3591_l p_230253_1_, boolean p_230253_2_, boolean p_230253_3_) {
        if (!p_230253_2_) {
            return 0;
        }
        if (!p_230253_1_.H_1990_U().J_1907_R(A_2352_Z.q_2307_F)) {
            return 0;
        }
        Random random = p_230253_1_.w_1457_N;
        --this.n_1700_B;
        if (this.n_1700_B > 0) {
            return 0;
        }
        this.n_1700_B += (60 + random.nextInt(60)) * 20;
        if (p_230253_1_.d_2427_y() < 5 && p_230253_1_.G_624_v().J_1907_R()) {
            return 0;
        }
        int i = 0;
        for (a_3913_L a_3913_L2 : p_230253_1_.multiplayerClientSuggestionProvider()) {
            FluidState fluidstate;
            K_4074_S blockstate;
            c_1514_x blockpos1;
            DifficultyInstance difficultyinstance;
            if (a_3913_L2.d_2461_k()) continue;
            c_1514_x blockpos = a_3913_L2.b_2312_j();
            if (p_230253_1_.G_624_v().J_1907_R() && (blockpos.getY() < p_230253_1_.d_2461_k() || !p_230253_1_.canSeeSky(blockpos)) || !(difficultyinstance = p_230253_1_.J_1907_R(blockpos)).n_1700_B(random.nextFloat() * 3.0f)) continue;
            k_2895_h serverstatisticsmanager = ((B_4088_l)a_3913_L2).n_3318_d();
            int j = u_530_F.n_1700_B(serverstatisticsmanager.n_1700_B(Stats.t_148_a.J_1907_R(Stats.P_4830_p)), 1, Integer.MAX_VALUE);
            int k = 24000;
            if (random.nextInt(j) < 72000 || !u_743_i.n_1700_B((BlockGetter)p_230253_1_, blockpos1 = blockpos.up(20 + random.nextInt(15)).east(-10 + random.nextInt(21)).south(-10 + random.nextInt(21)), blockstate = p_230253_1_.getBlockState(blockpos1), fluidstate = p_230253_1_.getFluidState(blockpos1), t_5_h.r_715_M)) continue;
            V_3157_k ilivingentitydata = null;
            int l = 1 + random.nextInt(difficultyinstance.n_1700_B().n_1700_B() + 1);
            for (int i1 = 0; i1 < l; ++i1) {
                m_3937_C phantomentity = t_5_h.r_715_M.n_1700_B(p_230253_1_);
                phantomentity.n_1700_B(blockpos1, 0.0f, 0.0f);
                ilivingentitydata = phantomentity.n_1700_B(p_230253_1_, difficultyinstance, a_3160_D.n_1700_B, ilivingentitydata, null);
                p_230253_1_.n_1700_B((N_4263_v)phantomentity);
            }
            i += l;
        }
        return i;
    }
}


