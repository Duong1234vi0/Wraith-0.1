/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.SkullBlock;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.u_4834_E;

public class WitherWallSkullBlock
extends u_4834_E {
    protected WitherWallSkullBlock(q_4293_E.P_1922_E properties) {
        super(SkullBlock.J_1907_R.J_1907_R, properties);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, @Nullable r_4811_B placer, Z_1993_T stack) {
        a_3742_W.ModuleCategory.n_1700_B(worldIn, pos, state, placer, stack);
    }
}



