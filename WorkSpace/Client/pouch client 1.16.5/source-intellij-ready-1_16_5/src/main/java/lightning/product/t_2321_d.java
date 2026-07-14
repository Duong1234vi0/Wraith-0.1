/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.W_2163_m;
import lightning.product.W_4464_I;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.AnvilMenu;
import lightning.product.SimpleMenuProvider;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.DirectionProperty;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.t_3286_u;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;
import lightning.product.ContainerLevelAccess;
import lightning.product.FallingBlock;
import lightning.product.x_268_Y;
import lightning.product.x_282_a;

public class t_2321_d
extends FallingBlock {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    private static final s_1395_c h_1847_R = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);
    private static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(3.0, 4.0, 4.0, 13.0, 5.0, 12.0);
    private static final s_1395_c M_182_A = T_2915_h.n_1700_B(4.0, 5.0, 6.0, 12.0, 10.0, 10.0);
    private static final s_1395_c t_1786_h = T_2915_h.n_1700_B(0.0, 10.0, 3.0, 16.0, 16.0, 13.0);
    private static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(4.0, 4.0, 3.0, 12.0, 5.0, 13.0);
    private static final s_1395_c w_1457_N = T_2915_h.n_1700_B(6.0, 5.0, 4.0, 10.0, 10.0, 12.0);
    private static final s_1395_c Y_601_j = T_2915_h.n_1700_B(3.0, 10.0, 0.0, 13.0, 16.0, 16.0);
    private static final s_1395_c Y_259_p = x_268_Y.n_1700_B(h_1847_R, Q_4569_t, M_182_A, t_1786_h);
    private static final s_1395_c Q_2552_b = x_268_Y.n_1700_B(h_1847_R, multiplayerClientSuggestionProvider, w_1457_N, Y_601_j);
    private static final x_282_a C_2741_M = new F_2904_S("container.repair");

    public t_2321_d(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y));
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.getPlacementHorizontalFacing().v_4262_N());
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        player.n_1700_B(state.J_1907_R(worldIn, pos));
        player.J_1907_R(Stats.w_612_n);
        return m_3054_I.J_1907_R;
    }

    @Override
    @Nullable
    public t_3286_u n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new AnvilMenu(id, inventory, ContainerLevelAccess.n_1700_B(worldIn, pos)), C_2741_M);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        b_257_Y direction = state.R_4764_Y(P_4830_p);
        return direction.h_1847_R() == b_257_Y.n_1700_B.n_1700_B ? Y_259_p : Q_2552_b;
    }

    @Override
    protected void n_1700_B(W_4464_I fallingEntity) {
        fallingEntity.n_1700_B(true);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S fallingState, K_4074_S hitState, W_4464_I fallingBlock) {
        if (!fallingBlock.y_1700_S()) {
            worldIn.R_4764_Y(1031, pos, 0);
        }
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, W_4464_I fallingBlock) {
        if (!fallingBlock.y_1700_S()) {
            worldIn.R_4764_Y(1029, pos, 0);
        }
    }

    @Nullable
    public static K_4074_S w_1484_f(K_4074_S state) {
        if (state.n_1700_B(a_3742_W.c_1608_O)) {
            return (K_4074_S)a_3742_W.ModeSetting.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, state.R_4764_Y(P_4830_p));
        }
        return state.n_1700_B(a_3742_W.ModeSetting) ? (K_4074_S)a_3742_W.MultiBooleanSetting.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, state.R_4764_Y(P_4830_p)) : null;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }

    @Override
    public int v_4262_N(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return state.G_564_y((BlockGetter)reader, (c_1514_x)pos).i_1637_u;
    }
}


