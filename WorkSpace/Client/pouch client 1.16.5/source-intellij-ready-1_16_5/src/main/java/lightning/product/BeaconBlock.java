/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.Stats;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_933_M;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.o_869_X;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;
import lightning.product.x_3974_Q;

public class BeaconBlock
extends BaseEntityBlock
implements o_869_X {
    public BeaconBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public e_933_M J_1907_R() {
        return e_933_M.n_1700_B;
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new x_3974_Q();
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof x_3974_Q) {
            player.n_1700_B((x_3974_Q)tileentity);
            player.J_1907_R(Stats.T_3594_S);
        }
        return m_3054_I.J_1907_R;
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        i_2154_H tileentity;
        if (stack.Y_601_j() && (tileentity = worldIn.getTileEntity(pos)) instanceof x_3974_Q) {
            ((x_3974_Q)tileentity).n_1700_B(stack.multiplayerClientSuggestionProvider());
        }
    }
}


