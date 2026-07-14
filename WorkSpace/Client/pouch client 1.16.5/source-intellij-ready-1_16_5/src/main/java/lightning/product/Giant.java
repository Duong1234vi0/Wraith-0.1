/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.R_1815_U;
import lightning.product.T_1316_M;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Monster;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;

public class Giant
extends Monster {
    public Giant(t_5_h<? extends Giant> type, b_4507_u worldIn) {
        super((t_5_h<? extends Monster>)type, worldIn);
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 10.440001f;
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 100.0).n_1700_B(Attributes.G_564_y, 0.5).n_1700_B(Attributes.u_1723_Y, 50.0);
    }

    @Override
    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        return worldIn.w_1484_f(pos) - 0.5f;
    }
}


