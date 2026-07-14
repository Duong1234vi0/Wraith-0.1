/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.N_4263_v;
import lightning.product.HalfTransparentBlock;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;

public class SlimeBlock
extends HalfTransparentBlock {
    public SlimeBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn, float fallDistance) {
        if (entityIn.UploadTokenCache()) {
            super.n_1700_B(worldIn, pos, entityIn, fallDistance);
        } else {
            entityIn.R_4764_Y(fallDistance, 0.0f);
        }
    }

    @Override
    public void n_1700_B(BlockGetter worldIn, N_4263_v entityIn) {
        if (entityIn.UploadTokenCache()) {
            super.n_1700_B(worldIn, entityIn);
        } else {
            this.n_1700_B(entityIn);
        }
    }

    private void n_1700_B(N_4263_v entity) {
        e_2866_D vector3d = entity.I_4348_c();
        if (vector3d.R_4764_Y < 0.0) {
            double d0 = entity instanceof r_4811_B ? 1.0 : 0.8;
            entity.h_1847_R(vector3d.J_1907_R, -vector3d.R_4764_Y * d0, vector3d.G_564_y);
        }
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        double d0 = Math.abs(entityIn.I_4348_c().R_4764_Y);
        if (d0 < 0.1 && !entityIn.TextRenderingUtils()) {
            double d1 = 0.4 + d0 * 0.2;
            entityIn.v_4262_N(entityIn.I_4348_c().G_564_y(d1, 1.0, d1));
        }
        super.n_1700_B(worldIn, pos, entityIn);
    }
}


