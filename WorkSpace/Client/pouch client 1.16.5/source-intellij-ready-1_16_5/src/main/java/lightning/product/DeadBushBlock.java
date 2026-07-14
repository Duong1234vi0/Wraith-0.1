/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;

public class DeadBushBlock
extends BushBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

    protected DeadBushBlock(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        T_2915_h block = state.J_1907_R();
        return block == a_3742_W.A_4115_X || block == a_3742_W.Y_1740_V || block == a_3742_W.InventoryPlus || block == a_3742_W.I_2209_R || block == a_3742_W.h_3858_e || block == a_3742_W.l_4397_i || block == a_3742_W.t_4433_T || block == a_3742_W.AimAssist || block == a_3742_W.AntiBot || block == a_3742_W.AntiSurround || block == a_3742_W.s_4447_V || block == a_3742_W.AttackAura || block == a_3742_W.AutoAnchor || block == a_3742_W.AutoCrystal || block == a_3742_W.AutoExplosion || block == a_3742_W.AutoSwap || block == a_3742_W.AutoTotem || block == a_3742_W.AutoTrap || block == a_3742_W.s_4054_j || block == a_3742_W.s_956_w || block == a_3742_W.u_2550_I || block == a_3742_W.M_588_G;
    }
}



