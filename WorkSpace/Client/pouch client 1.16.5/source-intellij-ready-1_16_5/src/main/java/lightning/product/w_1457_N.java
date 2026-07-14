/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockHitResult;
import lightning.product.M_660_m;
import lightning.product.N_4263_v;
import lightning.product.Y_259_p;
import lightning.product.Z_1993_T;
import lightning.product.Z_875_P;
import lightning.product.a_3913_L;
import lightning.product.m_3054_I;
import lightning.product.n_1700_B;
import lightning.product.EntityHitResult;
import lightning.product.x_1688_C;

public class w_1457_N {
    n_1700_B n_1700_B;
    private final Y_259_p J_1907_R;

    public n_1700_B n_1700_B() {
        return this.n_1700_B;
    }

    public void n_1700_B(M_660_m gameRenderer) {
        n_1700_B bot1 = this.n_1700_B;
        Z_875_P player = this.n_1700_B.P_1922_E.Q_2552_b;
        if (!bot1.P_1922_E.C_2741_M.h_1847_R() && !player.e_4240_b()) {
            for (x_1688_C hand : x_1688_C.values()) {
                m_3054_I actionresulttype2;
                Z_1993_T itemstack = player.R_4764_Y(hand);
                if (bot1.P_1922_E.Q_2552_b.v_4262_N != null) {
                    switch (bot1.P_1922_E.Q_2552_b.v_4262_N.R_4764_Y()) {
                        case R_4764_Y: {
                            EntityHitResult entityraytraceresult = (EntityHitResult)bot1.P_1922_E.Q_2552_b.v_4262_N;
                            N_4263_v entity = entityraytraceresult.n_1700_B();
                            m_3054_I actionresulttype = bot1.P_1922_E.C_2741_M.n_1700_B((a_3913_L)player, entity, entityraytraceresult, hand);
                            if (!actionresulttype.n_1700_B()) {
                                actionresulttype = bot1.P_1922_E.C_2741_M.n_1700_B((a_3913_L)player, entity, hand);
                            }
                            if (!actionresulttype.n_1700_B()) break;
                            if (actionresulttype.J_1907_R()) {
                                player.n_1700_B(hand);
                            }
                            return;
                        }
                        case J_1907_R: {
                            BlockHitResult blockraytraceresult = (BlockHitResult)bot1.P_1922_E.Q_2552_b.v_4262_N;
                            int i = itemstack.t_4043_B();
                            m_3054_I actionresulttype1 = bot1.P_1922_E.C_2741_M.n_1700_B(bot1.P_1922_E.Q_2552_b, bot1.P_1922_E.G_564_y(), hand, blockraytraceresult);
                            if (actionresulttype1.n_1700_B()) {
                                if (actionresulttype1.J_1907_R()) {
                                    player.n_1700_B(hand);
                                    if (!itemstack.n_1700_B() && (itemstack.t_4043_B() != i || bot1.P_1922_E.C_2741_M.w_1484_f())) {
                                        gameRenderer.n_1700_B.n_1700_B(hand);
                                    }
                                }
                                return;
                            }
                            if (actionresulttype1 != m_3054_I.G_564_y) break;
                            return;
                        }
                    }
                }
                if (itemstack.n_1700_B() || !(actionresulttype2 = bot1.P_1922_E.C_2741_M.n_1700_B((a_3913_L)player, bot1.P_1922_E.G_564_y(), hand)).n_1700_B()) continue;
                if (actionresulttype2.J_1907_R()) {
                    player.n_1700_B(hand);
                }
                gameRenderer.n_1700_B.n_1700_B(hand);
                return;
            }
        }
    }

    public void J_1907_R() {
        this.J_1907_R.J_1907_R();
    }

    public w_1457_N(n_1700_B bot) {
        this.n_1700_B = bot;
        this.J_1907_R = new Y_259_p(bot);
    }
}


