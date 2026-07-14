/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.Stats;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.f_395_A;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.o_98_P;
import lightning.product.q_4293_E;
import lightning.product.BlockEntityType;
import lightning.product.t_693_s;
import lightning.product.u_530_F;
import lightning.product.v_3445_Z;

public class TrappedChestBlock
extends v_3445_Z {
    public TrappedChestBlock(q_4293_E.P_1922_E properties) {
        super(properties, () -> BlockEntityType.R_4764_Y);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new f_395_A();
    }

    @Override
    protected o_98_P<g_2336_b> J_1907_R() {
        return Stats.t_148_a.J_1907_R(Stats.A_1038_p);
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return u_530_F.n_1700_B(t_693_s.n_1700_B(blockAccess, pos), 0, 15);
    }

    @Override
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return side == b_257_Y.J_1907_R ? blockState.J_1907_R(blockAccess, pos, side) : 0;
    }
}


