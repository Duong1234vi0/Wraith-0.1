/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.Stats;
import lightning.product.SimpleMenuProvider;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.CraftingTableBlock;
import lightning.product.SmithingMenu;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.t_3286_u;
import lightning.product.x_1688_C;
import lightning.product.ContainerLevelAccess;
import lightning.product.x_282_a;

public class SmithingTableBlock
extends CraftingTableBlock {
    private static final x_282_a P_4830_p = new F_2904_S("container.upgrade");

    protected SmithingTableBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public t_3286_u n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new SmithingMenu(id, inventory, ContainerLevelAccess.n_1700_B(worldIn, pos)), P_4830_p);
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        player.n_1700_B(state.J_1907_R(worldIn, pos));
        player.J_1907_R(Stats.M_1641_O);
        return m_3054_I.J_1907_R;
    }
}


