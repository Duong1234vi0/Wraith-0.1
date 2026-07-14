/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_563_h;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.n_1769_f;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.Fluid;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.x_268_Y;

public class y_3008_A
extends T_2915_h
implements SimpleWaterloggedBlock {
    public static final e_563_h<n_1769_f> P_4830_p = BlockStateProperties.ValueObject;
    public static final U_1266_O h_1847_R = BlockStateProperties.A_4115_X;
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);

    public y_3008_A(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, n_1769_f.J_1907_R)).n_1700_B(h_1847_R, false));
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return state.R_4764_Y(P_4830_p) != n_1769_f.R_4764_Y;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        n_1769_f slabtype = state.R_4764_Y(P_4830_p);
        switch (slabtype) {
            case R_4764_Y: {
                return x_268_Y.J_1907_R();
            }
            case n_1700_B: {
                return M_182_A;
            }
        }
        return Q_4569_t;
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        c_1514_x blockpos = context.getPos();
        K_4074_S blockstate = context.getWorld().getBlockState(blockpos);
        if (blockstate.n_1700_B(this)) {
            return (K_4074_S)((K_4074_S)blockstate.n_1700_B(P_4830_p, n_1769_f.R_4764_Y)).n_1700_B(h_1847_R, false);
        }
        FluidState fluidstate = context.getWorld().getFluidState(blockpos);
        K_4074_S blockstate1 = (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, n_1769_f.J_1907_R)).n_1700_B(h_1847_R, fluidstate.n_1700_B() == Fluids.R_4764_Y);
        b_257_Y direction = context.getFace();
        return !(direction == b_257_Y.n_1700_B || direction != b_257_Y.J_1907_R && context.getHitVec().R_4764_Y - (double)blockpos.getY() > 0.5) ? blockstate1 : (K_4074_S)blockstate1.n_1700_B(P_4830_p, n_1769_f.n_1700_B);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockPlaceContext useContext) {
        Z_1993_T itemstack = useContext.getItem();
        n_1769_f slabtype = state.R_4764_Y(P_4830_p);
        if (slabtype != n_1769_f.R_4764_Y && itemstack.J_1907_R() == this.u_1723_Y()) {
            if (useContext.J_1907_R()) {
                boolean flag = useContext.getHitVec().R_4764_Y - (double)useContext.getPos().getY() > 0.5;
                b_257_Y direction = useContext.getFace();
                if (slabtype == n_1769_f.J_1907_R) {
                    return direction == b_257_Y.J_1907_R || flag && direction.h_1847_R().G_564_y();
                }
                return direction == b_257_Y.n_1700_B || !flag && direction.h_1847_R().G_564_y();
            }
            return true;
        }
        return false;
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(h_1847_R) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    @Override
    public boolean n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S state, FluidState fluidStateIn) {
        return state.R_4764_Y(P_4830_p) != n_1769_f.R_4764_Y ? SimpleWaterloggedBlock.super.n_1700_B(worldIn, pos, state, fluidStateIn) : false;
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, Fluid fluidIn) {
        return state.R_4764_Y(P_4830_p) != n_1769_f.R_4764_Y ? SimpleWaterloggedBlock.super.n_1700_B(worldIn, pos, state, fluidIn) : false;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(h_1847_R).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        switch (type) {
            case n_1700_B: {
                return false;
            }
            case J_1907_R: {
                return worldIn.getFluidState(pos).n_1700_B(FluidTags.J_1907_R);
            }
            case R_4764_Y: {
                return false;
            }
        }
        return false;
    }
}


