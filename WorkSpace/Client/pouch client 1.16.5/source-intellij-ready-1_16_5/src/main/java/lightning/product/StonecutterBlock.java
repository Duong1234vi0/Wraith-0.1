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
import lightning.product.O_2369_F;
import lightning.product.R_4599_y;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.SimpleMenuProvider;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.DirectionProperty;
import lightning.product.m_3054_I;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.t_3286_u;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;
import lightning.product.ContainerLevelAccess;
import lightning.product.x_282_a;

public class StonecutterBlock
extends T_2915_h {
    private static final x_282_a Q_4569_t = new F_2904_S("container.stonecutter");
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 9.0, 16.0);

    public StonecutterBlock(q_4293_E.P_1922_E propertiesIn) {
        super(propertiesIn);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y));
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.getPlacementHorizontalFacing().u_1723_Y());
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        player.n_1700_B(state.J_1907_R(worldIn, pos));
        player.J_1907_R(Stats.V_1225_t);
        return m_3054_I.J_1907_R;
    }

    @Override
    @Nullable
    public t_3286_u n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new R_4599_y(id, inventory, ContainerLevelAccess.n_1700_B(worldIn, pos)), Q_4569_t);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return true;
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
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

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


