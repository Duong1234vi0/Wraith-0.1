/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_2530_r;
import lightning.product.BlockGetter;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.O_2639_P;
import lightning.product.SkullBlock;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.q_4293_E;
import lightning.product.t_3546_P;

public abstract class AbstractSkullBlock
extends BaseEntityBlock
implements D_2530_r {
    private final SkullBlock.n_1700_B P_4830_p;

    public AbstractSkullBlock(SkullBlock.n_1700_B iSkullType, q_4293_E.P_1922_E properties) {
        super(properties);
        this.P_4830_p = iSkullType;
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new O_2639_P();
    }

    public SkullBlock.n_1700_B J_1907_R() {
        return this.P_4830_p;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


