/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_1174_E;
import lightning.product.BlockTags;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;

public class w_1885_h
extends q_1613_l {
    public w_1885_h(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, b_4507_u worldIn, K_4074_S state, c_1514_x pos, r_4811_B entityLiving) {
        if (!worldIn.Y_259_p && !state.J_1907_R().n_1700_B(BlockTags.j_276_v)) {
            stack.n_1700_B(1, entityLiving, (T entity) -> entity.R_4764_Y(e_1174_E.n_1700_B));
        }
        return !state.n_1700_B(BlockTags.d_2427_y) && !state.n_1700_B(a_3742_W.y_1700_S) && !state.n_1700_B(a_3742_W.u_744_e) && !state.n_1700_B(a_3742_W.RetryCallException) && !state.n_1700_B(a_3742_W.r_3651_U) && !state.n_1700_B(a_3742_W.U_4087_m) && !state.n_1700_B(a_3742_W.I_3637_j) && !state.n_1700_B(BlockTags.J_1907_R) ? super.n_1700_B(stack, worldIn, state, pos, entityLiving) : true;
    }

    @Override
    public boolean J_1907_R(K_4074_S blockIn) {
        return blockIn.n_1700_B(a_3742_W.y_1700_S) || blockIn.n_1700_B(a_3742_W.P_5000_x) || blockIn.n_1700_B(a_3742_W.I_3637_j);
    }

    @Override
    public float n_1700_B(Z_1993_T stack, K_4074_S state) {
        if (!state.n_1700_B(a_3742_W.y_1700_S) && !state.n_1700_B(BlockTags.d_2427_y)) {
            return state.n_1700_B(BlockTags.J_1907_R) ? 5.0f : super.n_1700_B(stack, state);
        }
        return 15.0f;
    }
}


