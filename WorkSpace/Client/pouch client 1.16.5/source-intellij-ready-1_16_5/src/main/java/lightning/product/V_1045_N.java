/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.F_2203_T;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.SoundEvent;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.h_384_L;
import lightning.product.m_3054_I;
import lightning.product.FaceAttachedHorizontalDirectionalBlock;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.x_1688_C;

public abstract class V_1045_N
extends FaceAttachedHorizontalDirectionalBlock {
    public static final U_1266_O P_4830_p = BlockStateProperties.C_2741_M;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(6.0, 14.0, 5.0, 10.0, 16.0, 11.0);
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(5.0, 14.0, 6.0, 11.0, 16.0, 10.0);
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(6.0, 0.0, 5.0, 10.0, 2.0, 11.0);
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(5.0, 0.0, 6.0, 11.0, 2.0, 10.0);
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(5.0, 6.0, 14.0, 11.0, 10.0, 16.0);
    protected static final s_1395_c w_1457_N = T_2915_h.n_1700_B(5.0, 6.0, 0.0, 11.0, 10.0, 2.0);
    protected static final s_1395_c Y_601_j = T_2915_h.n_1700_B(14.0, 6.0, 5.0, 16.0, 10.0, 11.0);
    protected static final s_1395_c Y_259_p = T_2915_h.n_1700_B(0.0, 6.0, 5.0, 2.0, 10.0, 11.0);
    protected static final s_1395_c Q_2552_b = T_2915_h.n_1700_B(6.0, 15.0, 5.0, 10.0, 16.0, 11.0);
    protected static final s_1395_c C_2741_M = T_2915_h.n_1700_B(5.0, 15.0, 6.0, 11.0, 16.0, 10.0);
    protected static final s_1395_c k_2293_S = T_2915_h.n_1700_B(6.0, 0.0, 5.0, 10.0, 1.0, 11.0);
    protected static final s_1395_c q_2307_F = T_2915_h.n_1700_B(5.0, 0.0, 6.0, 11.0, 1.0, 10.0);
    protected static final s_1395_c Z_875_P = T_2915_h.n_1700_B(5.0, 6.0, 15.0, 11.0, 10.0, 16.0);
    protected static final s_1395_c c_3005_b = T_2915_h.n_1700_B(5.0, 6.0, 0.0, 11.0, 10.0, 1.0);
    protected static final s_1395_c H_2857_Y = T_2915_h.n_1700_B(15.0, 6.0, 5.0, 16.0, 10.0, 11.0);
    protected static final s_1395_c A_4115_X = T_2915_h.n_1700_B(0.0, 6.0, 5.0, 1.0, 10.0, 11.0);
    private final boolean e_4240_b;

    protected V_1045_N(boolean isWooden, q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(w_612_n, b_257_Y.R_4764_Y)).n_1700_B(P_4830_p, false)).n_1700_B(RealmsServerPing, F_2203_T.J_1907_R));
        this.e_4240_b = isWooden;
    }

    private int J_1907_R() {
        return this.e_4240_b ? 30 : 20;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        b_257_Y direction = state.R_4764_Y(w_612_n);
        boolean flag = state.R_4764_Y(P_4830_p);
        switch ((F_2203_T)state.R_4764_Y(RealmsServerPing)) {
            case n_1700_B: {
                if (direction.h_1847_R() == b_257_Y.n_1700_B.n_1700_B) {
                    return flag ? k_2293_S : M_182_A;
                }
                return flag ? q_2307_F : t_1786_h;
            }
            case J_1907_R: {
                switch (direction) {
                    case u_1723_Y: {
                        return flag ? A_4115_X : Y_259_p;
                    }
                    case P_1922_E: {
                        return flag ? H_2857_Y : Y_601_j;
                    }
                    case G_564_y: {
                        return flag ? c_3005_b : w_1457_N;
                    }
                }
                return flag ? Z_875_P : multiplayerClientSuggestionProvider;
            }
        }
        if (direction.h_1847_R() == b_257_Y.n_1700_B.n_1700_B) {
            return flag ? Q_2552_b : h_1847_R;
        }
        return flag ? C_2741_M : Q_4569_t;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (state.R_4764_Y(P_4830_p).booleanValue()) {
            return m_3054_I.J_1907_R;
        }
        this.R_4764_Y(state, worldIn, pos);
        this.n_1700_B(player, (LevelAccessor)worldIn, pos, true);
        return m_3054_I.n_1700_B(worldIn.Y_259_p);
    }

    public void R_4764_Y(K_4074_S state, b_4507_u world, c_1514_x pos) {
        world.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, true), 3);
        this.u_1723_Y(state, world, pos);
        world.u_2550_I().n_1700_B(pos, this, this.J_1907_R());
    }

    protected void n_1700_B(@Nullable a_3913_L playerIn, LevelAccessor worldIn, c_1514_x pos, boolean hitByArrow) {
        worldIn.n_1700_B(hitByArrow ? playerIn : null, pos, this.n_1700_B(hitByArrow), D_38_f.P_1922_E, 0.3f, hitByArrow ? 0.6f : 0.5f);
    }

    protected abstract SoundEvent n_1700_B(boolean var1);

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!isMoving && !state.n_1700_B(newState.J_1907_R())) {
            if (state.R_4764_Y(P_4830_p).booleanValue()) {
                this.u_1723_Y(state, worldIn, pos);
            }
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(P_4830_p) != false ? 15 : 0;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(P_4830_p) != false && V_1045_N.w_1484_f(blockState) == side ? 15 : 0;
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (state.R_4764_Y(P_4830_p).booleanValue()) {
            if (this.e_4240_b) {
                this.P_1922_E(state, worldIn, pos);
            } else {
                worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, false), 3);
                this.u_1723_Y(state, worldIn, pos);
                this.n_1700_B((a_3913_L)null, (LevelAccessor)worldIn, pos, false);
            }
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        if (!worldIn.Y_259_p && this.e_4240_b && !state.R_4764_Y(P_4830_p).booleanValue()) {
            this.P_1922_E(state, worldIn, pos);
        }
    }

    private void P_1922_E(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        boolean flag1;
        List<h_384_L> list = worldIn.n_1700_B(h_384_L.class, state.s_956_w(worldIn, pos).n_1700_B().offset(pos));
        boolean flag = !list.isEmpty();
        if (flag != (flag1 = state.R_4764_Y(P_4830_p).booleanValue())) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, flag), 3);
            this.u_1723_Y(state, worldIn, pos);
            this.n_1700_B((a_3913_L)null, (LevelAccessor)worldIn, pos, flag);
        }
        if (flag) {
            worldIn.u_2550_I().n_1700_B(new c_1514_x(pos), this, this.J_1907_R());
        }
    }

    private void u_1723_Y(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        worldIn.J_1907_R(pos, this);
        worldIn.J_1907_R(pos.offset(V_1045_N.w_1484_f(state).u_1723_Y()), this);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(w_612_n, P_4830_p, RealmsServerPing);
    }
}


