/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import java.util.function.Supplier;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.ConfiguredFeature;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.HugeFungusConfiguration;

public class FungusBlock
extends BushBlock
implements BonemealableBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(4.0, 0.0, 4.0, 12.0, 9.0, 12.0);
    private final Supplier<ConfiguredFeature<HugeFungusConfiguration, ?>> h_1847_R;

    protected FungusBlock(q_4293_E.P_1922_E properties, Supplier<ConfiguredFeature<HugeFungusConfiguration, ?>> fungusFeature) {
        super(properties);
        this.h_1847_R = fungusFeature;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.n_1700_B(BlockTags.UploadStatus) || state.n_1700_B(a_3742_W.A_2714_y) || state.n_1700_B(a_3742_W.v_165_F) || super.v_4262_N(state, worldIn, pos);
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        T_2915_h block = ((HugeFungusConfiguration)this.h_1847_R.get().u_1723_Y).u_1723_Y.J_1907_R();
        T_2915_h block1 = worldIn.getBlockState(pos.down()).J_1907_R();
        return block1 == block;
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return (double)rand.nextFloat() < 0.4;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        this.h_1847_R.get().n_1700_B(worldIn, worldIn.Y_259_p().t_148_a(), rand, pos);
    }
}


