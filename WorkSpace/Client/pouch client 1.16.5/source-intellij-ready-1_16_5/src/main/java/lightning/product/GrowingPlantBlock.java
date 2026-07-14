/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.GrowingPlantHeadBlock;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;

public abstract class GrowingPlantBlock
extends T_2915_h {
    protected final b_257_Y P_4830_p;
    protected final boolean h_1847_R;
    protected final s_1395_c Q_4569_t;

    protected GrowingPlantBlock(q_4293_E.P_1922_E properties, b_257_Y growthDirection, s_1395_c shape, boolean breaksInWater) {
        super(properties);
        this.P_4830_p = growthDirection;
        this.Q_4569_t = shape;
        this.h_1847_R = breaksInWater;
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate = context.getWorld().getBlockState(context.getPos().offset(this.P_4830_p));
        return !blockstate.n_1700_B(this.t_148_a()) && !blockstate.n_1700_B(this.J_1907_R()) ? this.n_1700_B(context.getWorld()) : this.J_1907_R().multiplayerClientSuggestionProvider();
    }

    public K_4074_S n_1700_B(LevelAccessor world) {
        return this.multiplayerClientSuggestionProvider();
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        c_1514_x blockpos = pos.offset(this.P_4830_p.u_1723_Y());
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        T_2915_h block = blockstate.J_1907_R();
        if (!this.n_1700_B(block)) {
            return false;
        }
        return block == this.t_148_a() || block == this.J_1907_R() || blockstate.G_564_y((BlockGetter)worldIn, blockpos, this.P_4830_p);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!state.n_1700_B((T_1316_M)worldIn, pos)) {
            worldIn.J_1907_R(pos, true);
        }
    }

    protected boolean n_1700_B(T_2915_h block) {
        return true;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.Q_4569_t;
    }

    protected abstract GrowingPlantHeadBlock t_148_a();

    protected abstract T_2915_h J_1907_R();
}


