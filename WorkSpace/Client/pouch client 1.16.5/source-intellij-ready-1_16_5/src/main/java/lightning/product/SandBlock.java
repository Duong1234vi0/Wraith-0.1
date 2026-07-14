/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.FallingBlock;

public class SandBlock
extends FallingBlock {
    private final int P_4830_p;

    public SandBlock(int dustColorIn, q_4293_E.P_1922_E properties) {
        super(properties);
        this.P_4830_p = dustColorIn;
    }

    @Override
    public int v_4262_N(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return this.P_4830_p;
    }
}


