/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.T_2915_h;
import lightning.product.UseOnContext;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.v_1669_V;
import lightning.product.x_1688_C;

public class WaterLilyBlockItem
extends v_1669_V {
    public WaterLilyBlockItem(T_2915_h blockIn, q_1613_l.n_1700_B builder) {
        super(blockIn, builder);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        return m_3054_I.R_4764_Y;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        BlockHitResult blockraytraceresult = WaterLilyBlockItem.n_1700_B(worldIn, playerIn, ClipContext.J_1907_R.J_1907_R);
        BlockHitResult blockraytraceresult1 = blockraytraceresult.n_1700_B(blockraytraceresult.n_1700_B().up());
        m_3054_I actionresulttype = super.n_1700_B(new UseOnContext(playerIn, handIn, blockraytraceresult1));
        return new InteractionResultHolder<Z_1993_T>(actionresulttype, playerIn.R_4764_Y(handIn));
    }
}


