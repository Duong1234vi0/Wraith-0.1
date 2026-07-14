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
import lightning.product.Fluids;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.g_88_D;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;

public class H_4584_y
extends BushBlock
implements BonemealableBlock,
SimpleWaterloggedBlock {
    public static final g_88_D P_4830_p = BlockStateProperties.U_1241_n;
    public static final U_1266_O h_1847_R = BlockStateProperties.A_4115_X;
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(6.0, 0.0, 6.0, 10.0, 6.0, 10.0);
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(3.0, 0.0, 3.0, 13.0, 6.0, 13.0);
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 6.0, 14.0);
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 7.0, 14.0);

    protected H_4584_y(q_4293_E.P_1922_E propertiesfsp) {
        super(propertiesfsp);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 1)).n_1700_B(h_1847_R, true));
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate = context.getWorld().getBlockState(context.getPos());
        if (blockstate.n_1700_B(this)) {
            return (K_4074_S)blockstate.n_1700_B(P_4830_p, Math.min(4, blockstate.R_4764_Y(P_4830_p) + 1));
        }
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        boolean flag = fluidstate.n_1700_B() == Fluids.R_4764_Y;
        return (K_4074_S)super.n_1700_B(context).n_1700_B(h_1847_R, flag);
    }

    public static boolean w_1484_f(K_4074_S state) {
        return state.R_4764_Y(h_1847_R) == false;
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return !state.u_2550_I(worldIn, pos).n_1700_B(b_257_Y.J_1907_R).J_1907_R() || state.G_564_y(worldIn, pos, b_257_Y.J_1907_R);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        return this.v_4262_N(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (!stateIn.n_1700_B(worldIn, currentPos)) {
            return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        if (stateIn.R_4764_Y(h_1847_R).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockPlaceContext useContext) {
        return useContext.getItem().J_1907_R() == this.u_1723_Y() && state.R_4764_Y(P_4830_p) < 4 ? true : super.n_1700_B(state, useContext);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        switch (state.R_4764_Y(P_4830_p)) {
            default: {
                return Q_4569_t;
            }
            case 2: {
                return M_182_A;
            }
            case 3: {
                return t_1786_h;
            }
            case 4: 
        }
        return multiplayerClientSuggestionProvider;
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(h_1847_R) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return true;
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        if (!H_4584_y.w_1484_f(state) && worldIn.getBlockState(pos.down()).n_1700_B(BlockTags.c_4037_x)) {
            int i = 5;
            int j = 1;
            int k = 2;
            int l = 0;
            int i1 = pos.getX() - 2;
            int j1 = 0;
            for (int k1 = 0; k1 < 5; ++k1) {
                for (int l1 = 0; l1 < j; ++l1) {
                    int i2 = 2 + pos.getY() - 1;
                    for (int j2 = i2 - 2; j2 < i2; ++j2) {
                        K_4074_S blockstate;
                        c_1514_x blockpos = new c_1514_x(i1 + k1, j2, pos.getZ() - j1 + l1);
                        if (blockpos == pos || rand.nextInt(6) != 0 || !worldIn.getBlockState(blockpos).n_1700_B(a_3742_W.c_3005_b) || !(blockstate = worldIn.getBlockState(blockpos.down())).n_1700_B(BlockTags.c_4037_x)) continue;
                        worldIn.n_1700_B(blockpos, (K_4074_S)a_3742_W.Easing.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, rand.nextInt(4) + 1), 3);
                    }
                }
                if (l < 2) {
                    j += 2;
                    ++j1;
                } else {
                    j -= 2;
                    --j1;
                }
                ++l;
            }
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, 4), 2);
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


