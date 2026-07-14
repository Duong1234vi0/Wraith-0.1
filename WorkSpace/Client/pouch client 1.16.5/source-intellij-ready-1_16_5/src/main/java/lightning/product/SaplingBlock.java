/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.AbstractTreeGrower;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.v_3760_Q;

public class SaplingBlock
extends BushBlock
implements BonemealableBlock {
    public static final g_88_D P_4830_p = BlockStateProperties.dtoRealmsServerAddress;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 12.0, 14.0);
    private final AbstractTreeGrower Q_4569_t;

    protected SaplingBlock(AbstractTreeGrower treeIn, q_4293_E.P_1922_E properties) {
        super(properties);
        this.Q_4569_t = treeIn;
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (worldIn.u_2550_I(pos.up()) >= 9 && random.nextInt(7) == 0) {
            this.n_1700_B(worldIn, pos, state, random);
        }
    }

    public void n_1700_B(e_3591_l world, c_1514_x pos, K_4074_S state, Random rand) {
        if (state.R_4764_Y(P_4830_p) == 0) {
            world.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p), 4);
        } else {
            this.Q_4569_t.n_1700_B(world, world.Y_259_p().t_148_a(), pos, state, rand);
        }
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return true;
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return (double)worldIn.w_1457_N.nextFloat() < 0.45;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        this.n_1700_B(worldIn, pos, state, rand);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}


