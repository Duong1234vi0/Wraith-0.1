/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.A_4313_D;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.StandingAndWallBlockItem;
import lightning.product.q_1613_l;

public class SignItem
extends StandingAndWallBlockItem {
    public SignItem(q_1613_l.n_1700_B propertiesIn, T_2915_h floorBlockIn, T_2915_h wallBlockIn) {
        super(floorBlockIn, wallBlockIn, propertiesIn);
    }

    @Override
    protected boolean n_1700_B(c_1514_x pos, b_4507_u worldIn, @Nullable a_3913_L player, Z_1993_T stack, K_4074_S state) {
        boolean flag = super.n_1700_B(pos, worldIn, player, stack, state);
        if (!worldIn.Y_259_p && !flag && player != null) {
            player.n_1700_B((A_4313_D)worldIn.getTileEntity(pos));
        }
        return flag;
    }
}


