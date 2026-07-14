/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_3138_X;
import lightning.product.J_2868_p;
import lightning.product.K_4074_S;
import lightning.product.K_550_M;
import lightning.product.MoveToBlockGoal;
import lightning.product.T_1316_M;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.h_2829_o;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.t_693_s;

public class CatSitOnBlockGoal
extends MoveToBlockGoal {
    private final K_550_M v_4262_N;

    public CatSitOnBlockGoal(K_550_M cat, double speed) {
        super(cat, speed, 8);
        this.v_4262_N = cat;
    }

    @Override
    public boolean n_1700_B() {
        return this.v_4262_N.U_3758_B() && !this.v_4262_N.D_3612_q() && super.n_1700_B();
    }

    @Override
    public void R_4764_Y() {
        super.R_4764_Y();
        this.v_4262_N.C_2741_M(false);
    }

    @Override
    public void G_564_y() {
        super.G_564_y();
        this.v_4262_N.C_2741_M(false);
    }

    @Override
    public void P_1922_E() {
        super.P_1922_E();
        this.v_4262_N.C_2741_M(this.M_588_G());
    }

    @Override
    protected boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
        if (!worldIn.u_1723_Y(pos.up())) {
            return false;
        }
        K_4074_S blockstate = worldIn.getBlockState(pos);
        if (blockstate.n_1700_B(a_3742_W.L_1362_X)) {
            return t_693_s.n_1700_B(worldIn, pos) < 1;
        }
        return blockstate.n_1700_B(a_3742_W.P_925_e) && blockstate.R_4764_Y(A_3138_X.h_1847_R) != false ? true : blockstate.n_1700_B(BlockTags.d_2461_k, (q_4293_E.n_1700_B state) -> state.G_564_y(J_2868_p.P_4830_p).map(bedPart -> bedPart != h_2829_o.n_1700_B).orElse(true));
    }
}


