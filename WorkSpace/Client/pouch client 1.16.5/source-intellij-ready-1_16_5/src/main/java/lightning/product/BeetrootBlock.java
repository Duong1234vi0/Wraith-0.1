/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.CropBlock;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.q_1803_e;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.s_1395_c;
import lightning.product.v_3760_Q;

public class BeetrootBlock
extends CropBlock {
    public static final g_88_D P_4830_p = BlockStateProperties.r_715_M;
    private static final s_1395_c[] Q_4569_t = new s_1395_c[]{T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)};

    public BeetrootBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public g_88_D J_1907_R() {
        return P_4830_p;
    }

    @Override
    public int t_148_a() {
        return 3;
    }

    @Override
    protected q_1803_e s_956_w() {
        return Items.MushroomBlock;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (random.nextInt(3) != 0) {
            super.n_1700_B(state, worldIn, pos, random);
        }
    }

    @Override
    protected int n_1700_B(b_4507_u worldIn) {
        return super.n_1700_B(worldIn) / 3;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return Q_4569_t[state.R_4764_Y(this.J_1907_R())];
    }
}


