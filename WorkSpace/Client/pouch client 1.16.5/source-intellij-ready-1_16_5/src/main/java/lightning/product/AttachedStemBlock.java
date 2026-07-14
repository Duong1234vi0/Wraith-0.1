/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.D_3746_J;
import lightning.product.BlockGetter;
import lightning.product.BushBlock;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.DirectionProperty;
import lightning.product.q_1613_l;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.StemGrownBlock;
import lightning.product.v_3760_Q;

public class AttachedStemBlock
extends BushBlock {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    private final StemGrownBlock h_1847_R;
    private static final Map<b_257_Y, s_1395_c> Q_4569_t = Maps.newEnumMap((Map)ImmutableMap.of((Object)b_257_Y.G_564_y, (Object)T_2915_h.n_1700_B(6.0, 0.0, 6.0, 10.0, 10.0, 16.0), (Object)b_257_Y.P_1922_E, (Object)T_2915_h.n_1700_B(0.0, 0.0, 6.0, 10.0, 10.0, 10.0), (Object)b_257_Y.R_4764_Y, (Object)T_2915_h.n_1700_B(6.0, 0.0, 0.0, 10.0, 10.0, 10.0), (Object)b_257_Y.u_1723_Y, (Object)T_2915_h.n_1700_B(6.0, 0.0, 6.0, 16.0, 10.0, 10.0)));

    protected AttachedStemBlock(StemGrownBlock grownFruit, q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y));
        this.h_1847_R = grownFruit;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return Q_4569_t.get(state.R_4764_Y(P_4830_p));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return !facingState.n_1700_B(this.h_1847_R) && facing == stateIn.R_4764_Y(P_4830_p) ? (K_4074_S)this.h_1847_R.J_1907_R().multiplayerClientSuggestionProvider().n_1700_B(D_3746_J.P_4830_p, 7) : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.n_1700_B(a_3742_W.Z_735_d);
    }

    protected q_1613_l J_1907_R() {
        if (this.h_1847_R == a_3742_W.A_3244_K) {
            return Items.WrappedMinMaxBounds;
        }
        return this.h_1847_R == a_3742_W.E_3343_g ? Items.y_2836_h : Items.n_1700_B;
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return new Z_1993_T(this.J_1907_R());
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
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}


