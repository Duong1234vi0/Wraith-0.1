/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.SimpleMenuProvider;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_3895_t;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.t_3286_u;
import lightning.product.x_1688_C;
import lightning.product.ContainerLevelAccess;
import lightning.product.x_282_a;

public class CartographyTableBlock
extends T_2915_h {
    private static final x_282_a P_4830_p = new F_2904_S("container.cartography_table");

    protected CartographyTableBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        player.n_1700_B(state.J_1907_R(worldIn, pos));
        player.J_1907_R(Stats.V_1446_Y);
        return m_3054_I.J_1907_R;
    }

    @Override
    @Nullable
    public t_3286_u n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new i_3895_t(id, inventory, ContainerLevelAccess.n_1700_B(worldIn, pos)), P_4830_p);
    }
}


