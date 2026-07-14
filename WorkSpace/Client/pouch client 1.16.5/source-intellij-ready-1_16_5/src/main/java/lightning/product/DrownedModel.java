/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_4355_q;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.e_4189_z;
import lightning.product.k_4231_L;
import lightning.product.n_1658_l;
import lightning.product.o_4662_o;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;

public class DrownedModel<T extends F_4355_q>
extends o_4662_o<T> {
    public DrownedModel(float p_i48915_1_, float p_i48915_2_, int p_i48915_3_, int p_i48915_4_) {
        super(p_i48915_1_, p_i48915_2_, p_i48915_3_, p_i48915_4_);
        this.G_564_y = new e_4189_z(this, 32, 48);
        this.G_564_y.n_1700_B(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, p_i48915_1_);
        this.G_564_y.n_1700_B(-5.0f, 2.0f + p_i48915_2_, 0.0f);
        this.u_1723_Y = new e_4189_z(this, 16, 48);
        this.u_1723_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, p_i48915_1_);
        this.u_1723_Y.n_1700_B(-1.9f, 12.0f + p_i48915_2_, 0.0f);
    }

    public DrownedModel(float p_i49398_1_, boolean p_i49398_2_) {
        super(p_i49398_1_, 0.0f, 64, p_i49398_2_ ? 32 : 64);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.t_148_a = n_1658_l.n_1700_B.n_1700_B;
        this.w_1484_f = n_1658_l.n_1700_B.n_1700_B;
        Z_1993_T itemstack = ((r_4811_B)entityIn).R_4764_Y(x_1688_C.n_1700_B);
        if (itemstack.J_1907_R() == Items.P_2605_j && ((Z_530_i)entityIn).P_2272_O()) {
            if (((Z_530_i)entityIn).d_2169_p() == k_4231_L.J_1907_R) {
                this.t_148_a = n_1658_l.n_1700_B.P_1922_E;
            } else {
                this.w_1484_f = n_1658_l.n_1700_B.P_1922_E;
            }
        }
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if (this.w_1484_f == n_1658_l.n_1700_B.P_1922_E) {
            this.P_1922_E.u_1723_Y = this.P_1922_E.u_1723_Y * 0.5f - (float)Math.PI;
            this.P_1922_E.v_4262_N = 0.0f;
        }
        if (this.t_148_a == n_1658_l.n_1700_B.P_1922_E) {
            this.G_564_y.u_1723_Y = this.G_564_y.u_1723_Y * 0.5f - (float)Math.PI;
            this.G_564_y.v_4262_N = 0.0f;
        }
        if (this.u_2550_I > 0.0f) {
            this.G_564_y.u_1723_Y = this.n_1700_B(this.u_2550_I, this.G_564_y.u_1723_Y, -2.5132742f) + this.u_2550_I * 0.35f * u_530_F.n_1700_B(0.1f * ageInTicks);
            this.P_1922_E.u_1723_Y = this.n_1700_B(this.u_2550_I, this.P_1922_E.u_1723_Y, -2.5132742f) - this.u_2550_I * 0.35f * u_530_F.n_1700_B(0.1f * ageInTicks);
            this.G_564_y.w_1484_f = this.n_1700_B(this.u_2550_I, this.G_564_y.w_1484_f, -0.15f);
            this.P_1922_E.w_1484_f = this.n_1700_B(this.u_2550_I, this.P_1922_E.w_1484_f, 0.15f);
            this.v_4262_N.u_1723_Y -= this.u_2550_I * 0.55f * u_530_F.n_1700_B(0.1f * ageInTicks);
            this.u_1723_Y.u_1723_Y += this.u_2550_I * 0.55f * u_530_F.n_1700_B(0.1f * ageInTicks);
            this.n_1700_B.u_1723_Y = 0.0f;
        }
    }
}


