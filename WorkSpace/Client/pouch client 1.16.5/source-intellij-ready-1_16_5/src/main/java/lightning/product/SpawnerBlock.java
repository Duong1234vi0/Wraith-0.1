/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.Z_1993_T;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.SpawnerBlockEntity;
import lightning.product.q_4293_E;

public class SpawnerBlock
extends BaseEntityBlock {
    protected SpawnerBlock(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new SpawnerBlockEntity();
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Z_1993_T stack) {
        super.n_1700_B(state, worldIn, pos, stack);
        int i = 15 + worldIn.w_1457_N.nextInt(15) + worldIn.w_1457_N.nextInt(15);
        this.n_1700_B(worldIn, pos, i);
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return Z_1993_T.J_1907_R;
    }
}


