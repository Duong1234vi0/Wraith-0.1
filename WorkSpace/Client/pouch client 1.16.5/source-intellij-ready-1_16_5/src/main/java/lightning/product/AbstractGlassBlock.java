/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.HalfTransparentBlock;
import lightning.product.CollisionContext;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.x_268_Y;

public abstract class AbstractGlassBlock
extends HalfTransparentBlock {
    protected AbstractGlassBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public s_1395_c R_4764_Y(K_4074_S state, BlockGetter reader, c_1514_x pos, CollisionContext context) {
        return x_268_Y.n_1700_B();
    }

    @Override
    public float P_1922_E(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return 1.0f;
    }

    @Override
    public boolean a_(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return true;
    }
}


