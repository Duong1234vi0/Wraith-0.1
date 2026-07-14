/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicates
 */
package lightning.product;

import com.google.common.base.Predicates;
import java.util.function.Predicate;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPattern;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_91_Z;
import lightning.product.g_3049_G;
import lightning.product.DirectionProperty;
import lightning.product.BlockInWorld;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.t_3546_P;
import lightning.product.x_268_Y;

public class EndPortalFrameBlock
extends T_2915_h {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    public static final U_1266_O h_1847_R = BlockStateProperties.w_1484_f;
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 13.0, 16.0);
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(4.0, 13.0, 4.0, 12.0, 16.0, 12.0);
    protected static final s_1395_c t_1786_h = x_268_Y.n_1700_B(Q_4569_t, M_182_A);
    private static BlockPattern multiplayerClientSuggestionProvider;

    public EndPortalFrameBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, false));
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return true;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return state.R_4764_Y(h_1847_R) != false ? t_1786_h : Q_4569_t;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.getPlacementHorizontalFacing().u_1723_Y())).n_1700_B(h_1847_R, false);
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        return blockState.R_4764_Y(h_1847_R) != false ? 15 : 0;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }

    public static BlockPattern J_1907_R() {
        if (multiplayerClientSuggestionProvider == null) {
            multiplayerClientSuggestionProvider = e_91_Z.n_1700_B().n_1700_B("?vvv?", ">???<", ">???<", ">???<", "?^^^?").n_1700_B('?', BlockInWorld.n_1700_B(g_3049_G.n_1700_B)).n_1700_B('^', BlockInWorld.n_1700_B(g_3049_G.n_1700_B(a_3742_W.l_2995_s).n_1700_B(h_1847_R, (Predicate<Object>)Predicates.equalTo((Object)true)).n_1700_B(P_4830_p, (Predicate<Object>)Predicates.equalTo((Object)b_257_Y.G_564_y)))).n_1700_B('>', BlockInWorld.n_1700_B(g_3049_G.n_1700_B(a_3742_W.l_2995_s).n_1700_B(h_1847_R, (Predicate<Object>)Predicates.equalTo((Object)true)).n_1700_B(P_4830_p, (Predicate<Object>)Predicates.equalTo((Object)b_257_Y.P_1922_E)))).n_1700_B('v', BlockInWorld.n_1700_B(g_3049_G.n_1700_B(a_3742_W.l_2995_s).n_1700_B(h_1847_R, (Predicate<Object>)Predicates.equalTo((Object)true)).n_1700_B(P_4830_p, (Predicate<Object>)Predicates.equalTo((Object)b_257_Y.R_4764_Y)))).n_1700_B('<', BlockInWorld.n_1700_B(g_3049_G.n_1700_B(a_3742_W.l_2995_s).n_1700_B(h_1847_R, (Predicate<Object>)Predicates.equalTo((Object)true)).n_1700_B(P_4830_p, (Predicate<Object>)Predicates.equalTo((Object)b_257_Y.u_1723_Y)))).J_1907_R();
        }
        return multiplayerClientSuggestionProvider;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


