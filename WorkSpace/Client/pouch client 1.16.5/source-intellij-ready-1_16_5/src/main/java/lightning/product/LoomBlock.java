/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.BlockHitResult;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.Y_1835_y;
import lightning.product.SimpleMenuProvider;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.t_3286_u;
import lightning.product.v_3760_Q;
import lightning.product.w_1471_F;
import lightning.product.x_1688_C;
import lightning.product.ContainerLevelAccess;
import lightning.product.x_282_a;

public class LoomBlock
extends HorizontalDirectionalBlock {
    private static final x_282_a P_4830_p = new F_2904_S("container.loom");

    protected LoomBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        player.n_1700_B(state.J_1907_R(worldIn, pos));
        player.J_1907_R(Stats.PlayerInfo);
        return m_3054_I.J_1907_R;
    }

    @Override
    public t_3286_u n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new w_1471_F(id, inventory, ContainerLevelAccess.n_1700_B(worldIn, pos)), P_4830_p);
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(w_612_n, context.getPlacementHorizontalFacing().u_1723_Y());
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{w_612_n});
    }
}


