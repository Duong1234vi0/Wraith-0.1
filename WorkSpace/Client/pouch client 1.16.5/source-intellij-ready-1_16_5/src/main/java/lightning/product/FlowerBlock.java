/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.g_422_i;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;

public class FlowerBlock
extends BushBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(5.0, 0.0, 5.0, 11.0, 10.0, 11.0);
    private final g_422_i h_1847_R;
    private final int Q_4569_t;

    public FlowerBlock(g_422_i effect, int effectDuration, q_4293_E.P_1922_E properties) {
        super(properties);
        this.h_1847_R = effect;
        this.Q_4569_t = effect.n_1700_B() ? effectDuration : effectDuration * 20;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        e_2866_D vector3d = state.h_1847_R(worldIn, pos);
        return P_4830_p.n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
    }

    @Override
    public q_4293_E.G_564_y R_4764_Y() {
        return q_4293_E.G_564_y.J_1907_R;
    }

    public g_422_i J_1907_R() {
        return this.h_1847_R;
    }

    public int t_148_a() {
        return this.Q_4569_t;
    }
}


