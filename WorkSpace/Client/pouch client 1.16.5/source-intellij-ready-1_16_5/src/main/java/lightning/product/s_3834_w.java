/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.T_2915_h;
import lightning.product.U_4243_e;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.o_3946_o;
import lightning.product.q_1704_m;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.Fluid;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;
import lightning.product.x_268_Y;

public class s_3834_w
extends T_2915_h
implements o_3946_o {
    public static final g_88_D P_4830_p = BlockStateProperties.V_1446_Y;
    protected final U_4243_e h_1847_R;
    private final List<FluidState> M_182_A;
    public static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);

    protected s_3834_w(U_4243_e fluidIn, q_4293_E.P_1922_E builder) {
        super(builder);
        this.h_1847_R = fluidIn;
        this.M_182_A = Lists.newArrayList();
        this.M_182_A.add(fluidIn.n_1700_B(false));
        for (int i = 1; i < 8; ++i) {
            this.M_182_A.add(fluidIn.n_1700_B(8 - i, false));
        }
        this.M_182_A.add(fluidIn.n_1700_B(8, true));
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return context.n_1700_B(Q_4569_t, pos, true) && state.R_4764_Y(P_4830_p) == 0 && context.n_1700_B(worldIn.getFluidState(pos.up()), this.h_1847_R) ? Q_4569_t : x_268_Y.n_1700_B();
    }

    @Override
    public boolean a_(K_4074_S state) {
        return state.P_4830_p().u_1723_Y();
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        state.P_4830_p().J_1907_R(worldIn, pos, random);
    }

    @Override
    public boolean a_(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return false;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return !this.h_1847_R.n_1700_B(FluidTags.R_4764_Y);
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        int i = state.R_4764_Y(P_4830_p);
        return this.M_182_A.get(Math.min(i, 8));
    }

    @Override
    public boolean n_1700_B(K_4074_S state, K_4074_S adjacentBlockState, b_257_Y side) {
        return adjacentBlockState.P_4830_p().n_1700_B().n_1700_B(this.h_1847_R);
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.n_1700_B;
    }

    @Override
    public List<Z_1993_T> n_1700_B(K_4074_S state, q_1704_m.n_1700_B builder) {
        return Collections.emptyList();
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return x_268_Y.n_1700_B();
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (this.n_1700_B(worldIn, pos, state)) {
            worldIn.M_588_G().n_1700_B(pos, state.P_4830_p().n_1700_B(), this.h_1847_R.n_1700_B(worldIn));
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.P_4830_p().J_1907_R() || facingState.P_4830_p().J_1907_R()) {
            worldIn.M_588_G().n_1700_B(currentPos, stateIn.P_4830_p().n_1700_B(), this.h_1847_R.n_1700_B(worldIn));
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        if (this.n_1700_B(worldIn, pos, state)) {
            worldIn.M_588_G().n_1700_B(pos, state.P_4830_p().n_1700_B(), this.h_1847_R.n_1700_B(worldIn));
        }
    }

    private boolean n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        if (this.h_1847_R.n_1700_B(FluidTags.R_4764_Y)) {
            boolean flag = worldIn.getBlockState(pos.down()).n_1700_B(a_3742_W.v_165_F);
            for (b_257_Y direction : b_257_Y.values()) {
                if (direction == b_257_Y.n_1700_B) continue;
                c_1514_x blockpos = pos.offset(direction);
                if (worldIn.getFluidState(blockpos).n_1700_B(FluidTags.J_1907_R)) {
                    T_2915_h block = worldIn.getFluidState(pos).J_1907_R() ? a_3742_W.ClientBootstrap : a_3742_W.P_4830_p;
                    worldIn.J_1907_R(pos, block.multiplayerClientSuggestionProvider());
                    this.n_1700_B(worldIn, pos);
                    return false;
                }
                if (!flag || !worldIn.getBlockState(blockpos).n_1700_B(a_3742_W.G_4691_Q)) continue;
                worldIn.J_1907_R(pos, a_3742_W.s_4990_V.multiplayerClientSuggestionProvider());
                this.n_1700_B(worldIn, pos);
                return false;
            }
        }
        return true;
    }

    private void n_1700_B(LevelAccessor worldIn, c_1514_x pos) {
        worldIn.R_4764_Y(1501, pos, 0);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public Fluid J_1907_R(LevelAccessor worldIn, c_1514_x pos, K_4074_S state) {
        if (state.R_4764_Y(P_4830_p) == 0) {
            worldIn.n_1700_B(pos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 11);
            return this.h_1847_R;
        }
        return Fluids.n_1700_B;
    }
}



