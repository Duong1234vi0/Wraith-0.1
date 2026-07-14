/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.k_4231_L;
import lightning.product.n_1658_l;
import lightning.product.AnimationUtils;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;

public class SkeletonModel<T extends Z_530_i>
extends n_1658_l<T> {
    public SkeletonModel() {
        this(0.0f, false);
    }

    public SkeletonModel(float modelSize, boolean p_i46303_2_) {
        super(modelSize);
        if (!p_i46303_2_) {
            this.G_564_y = new e_4189_z(this, 40, 16);
            this.G_564_y.n_1700_B(-1.0f, -2.0f, -1.0f, 2.0f, 12.0f, 2.0f, modelSize);
            this.G_564_y.n_1700_B(-5.0f, 2.0f, 0.0f);
            this.P_1922_E = new e_4189_z(this, 40, 16);
            this.P_1922_E.t_148_a = true;
            this.P_1922_E.n_1700_B(-1.0f, -2.0f, -1.0f, 2.0f, 12.0f, 2.0f, modelSize);
            this.P_1922_E.n_1700_B(5.0f, 2.0f, 0.0f);
            this.u_1723_Y = new e_4189_z(this, 0, 16);
            this.u_1723_Y.n_1700_B(-1.0f, 0.0f, -1.0f, 2.0f, 12.0f, 2.0f, modelSize);
            this.u_1723_Y.n_1700_B(-2.0f, 12.0f, 0.0f);
            this.v_4262_N = new e_4189_z(this, 0, 16);
            this.v_4262_N.t_148_a = true;
            this.v_4262_N.n_1700_B(-1.0f, 0.0f, -1.0f, 2.0f, 12.0f, 2.0f, modelSize);
            this.v_4262_N.n_1700_B(2.0f, 12.0f, 0.0f);
        }
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.t_148_a = n_1658_l.n_1700_B.n_1700_B;
        this.w_1484_f = n_1658_l.n_1700_B.n_1700_B;
        Z_1993_T itemstack = ((r_4811_B)entityIn).R_4764_Y(x_1688_C.n_1700_B);
        if (itemstack.J_1907_R() == Items.R_1796_s && ((Z_530_i)entityIn).P_2272_O()) {
            if (((Z_530_i)entityIn).d_2169_p() == k_4231_L.J_1907_R) {
                this.t_148_a = n_1658_l.n_1700_B.G_564_y;
            } else {
                this.w_1484_f = n_1658_l.n_1700_B.G_564_y;
            }
        }
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        Z_1993_T itemstack = ((r_4811_B)entityIn).A_2714_y();
        if (((Z_530_i)entityIn).P_2272_O() && (itemstack.n_1700_B() || itemstack.J_1907_R() != Items.R_1796_s)) {
            float f = u_530_F.n_1700_B(this.h_1847_R * (float)Math.PI);
            float f1 = u_530_F.n_1700_B((1.0f - (1.0f - this.h_1847_R) * (1.0f - this.h_1847_R)) * (float)Math.PI);
            this.G_564_y.w_1484_f = 0.0f;
            this.P_1922_E.w_1484_f = 0.0f;
            this.G_564_y.v_4262_N = -(0.1f - f * 0.6f);
            this.P_1922_E.v_4262_N = 0.1f - f * 0.6f;
            this.G_564_y.u_1723_Y = -1.5707964f;
            this.P_1922_E.u_1723_Y = -1.5707964f;
            this.G_564_y.u_1723_Y -= f * 1.2f - f1 * 0.4f;
            this.P_1922_E.u_1723_Y -= f * 1.2f - f1 * 0.4f;
            AnimationUtils.n_1700_B(this.G_564_y, this.P_1922_E, ageInTicks);
        }
    }

    @Override
    public void n_1700_B(k_4231_L sideIn, g_221_o matrixStackIn) {
        float f = sideIn == k_4231_L.J_1907_R ? 1.0f : -1.0f;
        e_4189_z modelrenderer = this.n_1700_B(sideIn);
        modelrenderer.R_4764_Y += f;
        modelrenderer.n_1700_B(matrixStackIn);
        modelrenderer.R_4764_Y -= f;
    }
}


