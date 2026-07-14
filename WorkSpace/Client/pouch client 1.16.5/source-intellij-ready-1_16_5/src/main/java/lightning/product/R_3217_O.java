/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.LeashFenceKnotEntity;
import lightning.product.I_4817_s;
import lightning.product.T_2915_h;
import lightning.product.UseOnContext;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.BlockTags;
import lightning.product.q_1613_l;

public class R_3217_O
extends q_1613_l {
    public R_3217_O(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        c_1514_x blockpos;
        b_4507_u world = context.getWorld();
        T_2915_h block = world.getBlockState(blockpos = context.getPos()).J_1907_R();
        if (block.n_1700_B(BlockTags.G_624_v)) {
            a_3913_L playerentity = context.getPlayer();
            if (!world.Y_259_p && playerentity != null) {
                R_3217_O.n_1700_B(playerentity, world, blockpos);
            }
            return m_3054_I.n_1700_B(world.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }

    public static m_3054_I n_1700_B(a_3913_L player, b_4507_u world, c_1514_x pos) {
        LeashFenceKnotEntity leashknotentity = null;
        boolean flag = false;
        double d0 = 7.0;
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        for (Z_530_i mobentity : world.n_1700_B(Z_530_i.class, new I_4817_s((double)i - 7.0, (double)j - 7.0, (double)k - 7.0, (double)i + 7.0, (double)j + 7.0, (double)k + 7.0))) {
            if (mobentity.y_2622_c() != player) continue;
            if (leashknotentity == null) {
                leashknotentity = LeashFenceKnotEntity.n_1700_B(world, pos);
            }
            mobentity.J_1907_R(leashknotentity, true);
            flag = true;
        }
        return flag ? m_3054_I.n_1700_B : m_3054_I.R_4764_Y;
    }
}


