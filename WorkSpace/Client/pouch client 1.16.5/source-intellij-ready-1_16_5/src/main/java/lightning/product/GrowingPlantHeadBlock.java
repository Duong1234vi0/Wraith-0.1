/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.GrowingPlantBlock;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.v_3760_Q;

public abstract class GrowingPlantHeadBlock
extends GrowingPlantBlock
implements BonemealableBlock {
    public static final g_88_D M_182_A = BlockStateProperties.p_178_J;
    private final double t_1786_h;

    protected GrowingPlantHeadBlock(q_4293_E.P_1922_E properties, b_257_Y direction, s_1395_c shape, boolean waterloggable, double growthChance) {
        super(properties, direction, shape, waterloggable);
        this.t_1786_h = growthChance;
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(M_182_A, 0));
    }

    @Override
    public K_4074_S n_1700_B(LevelAccessor world) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(M_182_A, world.e_4240_b().nextInt(25));
    }

    @Override
    public boolean a_(K_4074_S state) {
        return state.R_4764_Y(M_182_A) < 25;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        c_1514_x blockpos;
        if (state.R_4764_Y(M_182_A) < 25 && random.nextDouble() < this.t_1786_h && this.w_1484_f(worldIn.getBlockState(blockpos = pos.offset(this.P_4830_p)))) {
            worldIn.J_1907_R(blockpos, (K_4074_S)state.n_1700_B(M_182_A));
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing == this.P_4830_p.u_1723_Y() && !stateIn.n_1700_B(worldIn, currentPos)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 1);
        }
        if (facing != this.P_4830_p || !facingState.n_1700_B(this) && !facingState.n_1700_B(this.J_1907_R())) {
            if (this.h_1847_R) {
                worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
            }
            return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
        return this.J_1907_R().multiplayerClientSuggestionProvider();
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{M_182_A});
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return this.w_1484_f(worldIn.getBlockState(pos.offset(this.P_4830_p)));
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        c_1514_x blockpos = pos.offset(this.P_4830_p);
        int i = Math.min(state.R_4764_Y(M_182_A) + 1, 25);
        int j = this.n_1700_B(rand);
        for (int k = 0; k < j && this.w_1484_f(worldIn.getBlockState(blockpos)); ++k) {
            worldIn.J_1907_R(blockpos, (K_4074_S)state.n_1700_B(M_182_A, i));
            blockpos = blockpos.offset(this.P_4830_p);
            i = Math.min(i + 1, 25);
        }
    }

    protected abstract int n_1700_B(Random var1);

    protected abstract boolean w_1484_f(K_4074_S var1);

    @Override
    protected GrowingPlantHeadBlock t_148_a() {
        return this;
    }
}


