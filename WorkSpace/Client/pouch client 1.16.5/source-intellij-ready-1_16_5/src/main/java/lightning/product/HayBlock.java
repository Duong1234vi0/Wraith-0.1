/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.RotatedPillarBlock;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;

public class HayBlock
extends RotatedPillarBlock {
    public HayBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(t_1786_h, b_257_Y.n_1700_B.J_1907_R));
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn, float fallDistance) {
        entityIn.R_4764_Y(fallDistance, 0.2f);
    }
}


