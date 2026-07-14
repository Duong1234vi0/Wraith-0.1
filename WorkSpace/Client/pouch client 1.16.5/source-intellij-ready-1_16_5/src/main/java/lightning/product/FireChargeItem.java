/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_4998_y;
import lightning.product.D_38_f;
import lightning.product.K_4074_S;
import lightning.product.BaseFireBlock;
import lightning.product.SoundEvents;
import lightning.product.UseOnContext;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;

public class FireChargeItem
extends q_1613_l {
    public FireChargeItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        b_4507_u world = context.getWorld();
        c_1514_x blockpos = context.getPos();
        K_4074_S blockstate = world.getBlockState(blockpos);
        boolean flag = false;
        if (C_4998_y.t_148_a(blockstate)) {
            this.n_1700_B(world, blockpos);
            world.J_1907_R(blockpos, (K_4074_S)blockstate.n_1700_B(C_4998_y.h_1847_R, true));
            flag = true;
        } else if (BaseFireBlock.n_1700_B(world, blockpos = blockpos.offset(context.getFace()), context.getPlacementHorizontalFacing())) {
            this.n_1700_B(world, blockpos);
            world.J_1907_R(blockpos, BaseFireBlock.n_1700_B(world, blockpos));
            flag = true;
        }
        if (flag) {
            context.getItem().v_4262_N(1);
            return m_3054_I.n_1700_B(world.Y_259_p);
        }
        return m_3054_I.G_564_y;
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos) {
        worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.E_453_w, D_38_f.P_1922_E, 1.0f, (w_1484_f.nextFloat() - w_1484_f.nextFloat()) * 0.2f + 1.0f);
    }
}


