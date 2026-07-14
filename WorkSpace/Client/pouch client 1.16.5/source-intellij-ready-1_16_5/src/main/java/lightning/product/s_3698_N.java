/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Map;
import lightning.product.K_4074_S;
import lightning.product.PipeBlock;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;

public class s_3698_N
extends T_2915_h {
    public static final U_1266_O P_4830_p = PipeBlock.P_4830_p;
    public static final U_1266_O h_1847_R = PipeBlock.h_1847_R;
    public static final U_1266_O Q_4569_t = PipeBlock.Q_4569_t;
    public static final U_1266_O M_182_A = PipeBlock.M_182_A;
    public static final U_1266_O t_1786_h = PipeBlock.t_1786_h;
    public static final U_1266_O multiplayerClientSuggestionProvider = PipeBlock.multiplayerClientSuggestionProvider;
    private static final Map<b_257_Y, U_1266_O> w_1457_N = PipeBlock.w_1457_N;

    public s_3698_N(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, true)).n_1700_B(h_1847_R, true)).n_1700_B(Q_4569_t, true)).n_1700_B(M_182_A, true)).n_1700_B(t_1786_h, true)).n_1700_B(multiplayerClientSuggestionProvider, true));
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_4507_u iblockreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(multiplayerClientSuggestionProvider, this != iblockreader.getBlockState(blockpos.down()).J_1907_R())).n_1700_B(t_1786_h, this != iblockreader.getBlockState(blockpos.up()).J_1907_R())).n_1700_B(P_4830_p, this != iblockreader.getBlockState(blockpos.north()).J_1907_R())).n_1700_B(h_1847_R, this != iblockreader.getBlockState(blockpos.east()).J_1907_R())).n_1700_B(Q_4569_t, this != iblockreader.getBlockState(blockpos.south()).J_1907_R())).n_1700_B(M_182_A, this != iblockreader.getBlockState(blockpos.west()).J_1907_R());
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facingState.n_1700_B(this) ? (K_4074_S)stateIn.n_1700_B(w_1457_N.get(facing), false) : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(w_1457_N.get(rot.n_1700_B(b_257_Y.R_4764_Y)), state.R_4764_Y(P_4830_p))).n_1700_B(w_1457_N.get(rot.n_1700_B(b_257_Y.G_564_y)), state.R_4764_Y(Q_4569_t))).n_1700_B(w_1457_N.get(rot.n_1700_B(b_257_Y.u_1723_Y)), state.R_4764_Y(h_1847_R))).n_1700_B(w_1457_N.get(rot.n_1700_B(b_257_Y.P_1922_E)), state.R_4764_Y(M_182_A))).n_1700_B(w_1457_N.get(rot.n_1700_B(b_257_Y.J_1907_R)), state.R_4764_Y(t_1786_h))).n_1700_B(w_1457_N.get(rot.n_1700_B(b_257_Y.n_1700_B)), state.R_4764_Y(multiplayerClientSuggestionProvider));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(w_1457_N.get(mirrorIn.J_1907_R(b_257_Y.R_4764_Y)), state.R_4764_Y(P_4830_p))).n_1700_B(w_1457_N.get(mirrorIn.J_1907_R(b_257_Y.G_564_y)), state.R_4764_Y(Q_4569_t))).n_1700_B(w_1457_N.get(mirrorIn.J_1907_R(b_257_Y.u_1723_Y)), state.R_4764_Y(h_1847_R))).n_1700_B(w_1457_N.get(mirrorIn.J_1907_R(b_257_Y.P_1922_E)), state.R_4764_Y(M_182_A))).n_1700_B(w_1457_N.get(mirrorIn.J_1907_R(b_257_Y.J_1907_R)), state.R_4764_Y(t_1786_h))).n_1700_B(w_1457_N.get(mirrorIn.J_1907_R(b_257_Y.n_1700_B)), state.R_4764_Y(multiplayerClientSuggestionProvider));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(t_1786_h, multiplayerClientSuggestionProvider, P_4830_p, h_1847_R, Q_4569_t, M_182_A);
    }
}


