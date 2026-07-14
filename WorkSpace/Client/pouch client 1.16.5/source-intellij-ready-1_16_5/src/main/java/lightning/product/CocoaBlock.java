/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;

public class CocoaBlock
extends HorizontalDirectionalBlock
implements BonemealableBlock {
    public static final g_88_D P_4830_p = BlockStateProperties.O_508_d;
    protected static final s_1395_c[] h_1847_R = new s_1395_c[]{T_2915_h.n_1700_B(11.0, 7.0, 6.0, 15.0, 12.0, 10.0), T_2915_h.n_1700_B(9.0, 5.0, 5.0, 15.0, 12.0, 11.0), T_2915_h.n_1700_B(7.0, 3.0, 4.0, 15.0, 12.0, 12.0)};
    protected static final s_1395_c[] Q_4569_t = new s_1395_c[]{T_2915_h.n_1700_B(1.0, 7.0, 6.0, 5.0, 12.0, 10.0), T_2915_h.n_1700_B(1.0, 5.0, 5.0, 7.0, 12.0, 11.0), T_2915_h.n_1700_B(1.0, 3.0, 4.0, 9.0, 12.0, 12.0)};
    protected static final s_1395_c[] M_182_A = new s_1395_c[]{T_2915_h.n_1700_B(6.0, 7.0, 1.0, 10.0, 12.0, 5.0), T_2915_h.n_1700_B(5.0, 5.0, 1.0, 11.0, 12.0, 7.0), T_2915_h.n_1700_B(4.0, 3.0, 1.0, 12.0, 12.0, 9.0)};
    protected static final s_1395_c[] t_1786_h = new s_1395_c[]{T_2915_h.n_1700_B(6.0, 7.0, 11.0, 10.0, 12.0, 15.0), T_2915_h.n_1700_B(5.0, 5.0, 9.0, 11.0, 12.0, 15.0), T_2915_h.n_1700_B(4.0, 3.0, 7.0, 12.0, 12.0, 15.0)};

    public CocoaBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(w_612_n, b_257_Y.R_4764_Y)).n_1700_B(P_4830_p, 0));
    }

    @Override
    public boolean a_(K_4074_S state) {
        return state.R_4764_Y(P_4830_p) < 2;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        int i;
        if (worldIn.w_1457_N.nextInt(5) == 0 && (i = state.R_4764_Y(P_4830_p).intValue()) < 2) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, i + 1), 2);
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        T_2915_h block = worldIn.getBlockState(pos.offset(state.R_4764_Y(w_612_n))).J_1907_R();
        return block.n_1700_B(BlockTags.k_2293_S);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        int i = state.R_4764_Y(P_4830_p);
        switch (state.R_4764_Y(w_612_n)) {
            case G_564_y: {
                return t_1786_h[i];
            }
            default: {
                return M_182_A[i];
            }
            case P_1922_E: {
                return Q_4569_t[i];
            }
            case u_1723_Y: 
        }
        return h_1847_R[i];
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate = this.multiplayerClientSuggestionProvider();
        b_4507_u iworldreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        for (b_257_Y direction : context.G_564_y()) {
            if (!direction.h_1847_R().G_564_y() || !(blockstate = (K_4074_S)blockstate.n_1700_B(w_612_n, direction)).n_1700_B((T_1316_M)iworldreader, blockpos)) continue;
            return blockstate;
        }
        return null;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing == stateIn.R_4764_Y(w_612_n) && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return state.R_4764_Y(P_4830_p) < 2;
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, state.R_4764_Y(P_4830_p) + 1), 2);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(w_612_n, P_4830_p);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


