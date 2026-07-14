/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import lightning.product.I_3700_V;
import lightning.product.ListModel;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class WitherBossModel<T extends I_3700_V>
extends ListModel<T> {
    private final e_4189_z[] n_1700_B;
    private final e_4189_z[] J_1907_R;
    private final ImmutableList<e_4189_z> R_4764_Y;

    public WitherBossModel(float p_i46302_1_) {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.n_1700_B = new e_4189_z[3];
        this.n_1700_B[0] = new e_4189_z(this, 0, 16);
        this.n_1700_B[0].n_1700_B(-10.0f, 3.9f, -0.5f, 20.0f, 3.0f, 3.0f, p_i46302_1_);
        this.n_1700_B[1] = new e_4189_z(this).J_1907_R(this.textureWidth, this.textureHeight);
        this.n_1700_B[1].n_1700_B(-2.0f, 6.9f, -0.5f);
        this.n_1700_B[1].n_1700_B(0, 22).n_1700_B(0.0f, 0.0f, 0.0f, 3.0f, 10.0f, 3.0f, p_i46302_1_);
        this.n_1700_B[1].n_1700_B(24, 22).n_1700_B(-4.0f, 1.5f, 0.5f, 11.0f, 2.0f, 2.0f, p_i46302_1_);
        this.n_1700_B[1].n_1700_B(24, 22).n_1700_B(-4.0f, 4.0f, 0.5f, 11.0f, 2.0f, 2.0f, p_i46302_1_);
        this.n_1700_B[1].n_1700_B(24, 22).n_1700_B(-4.0f, 6.5f, 0.5f, 11.0f, 2.0f, 2.0f, p_i46302_1_);
        this.n_1700_B[2] = new e_4189_z(this, 12, 22);
        this.n_1700_B[2].n_1700_B(0.0f, 0.0f, 0.0f, 3.0f, 6.0f, 3.0f, p_i46302_1_);
        this.J_1907_R = new e_4189_z[3];
        this.J_1907_R[0] = new e_4189_z(this, 0, 0);
        this.J_1907_R[0].n_1700_B(-4.0f, -4.0f, -4.0f, 8.0f, 8.0f, 8.0f, p_i46302_1_);
        this.J_1907_R[1] = new e_4189_z(this, 32, 0);
        this.J_1907_R[1].n_1700_B(-4.0f, -4.0f, -4.0f, 6.0f, 6.0f, 6.0f, p_i46302_1_);
        this.J_1907_R[1].R_4764_Y = -8.0f;
        this.J_1907_R[1].G_564_y = 4.0f;
        this.J_1907_R[2] = new e_4189_z(this, 32, 0);
        this.J_1907_R[2].n_1700_B(-4.0f, -4.0f, -4.0f, 6.0f, 6.0f, 6.0f, p_i46302_1_);
        this.J_1907_R[2].R_4764_Y = 10.0f;
        this.J_1907_R[2].G_564_y = 4.0f;
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.addAll(Arrays.asList(this.J_1907_R));
        builder.addAll(Arrays.asList(this.n_1700_B));
        this.R_4764_Y = builder.build();
    }

    public ImmutableList<e_4189_z> J_1907_R() {
        return this.R_4764_Y;
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = u_530_F.J_1907_R(ageInTicks * 0.1f);
        this.n_1700_B[1].u_1723_Y = (0.065f + 0.05f * f) * (float)Math.PI;
        this.n_1700_B[2].n_1700_B(-2.0f, 6.9f + u_530_F.J_1907_R(this.n_1700_B[1].u_1723_Y) * 10.0f, -0.5f + u_530_F.n_1700_B(this.n_1700_B[1].u_1723_Y) * 10.0f);
        this.n_1700_B[2].u_1723_Y = (0.265f + 0.1f * f) * (float)Math.PI;
        this.J_1907_R[0].v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.J_1907_R[0].u_1723_Y = headPitch * ((float)Math.PI / 180);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        for (int i = 1; i < 3; ++i) {
            this.J_1907_R[i].v_4262_N = (((I_3700_V)entityIn).n_1700_B(i - 1) - ((I_3700_V)entityIn).C_1162_e) * ((float)Math.PI / 180);
            this.J_1907_R[i].u_1723_Y = ((I_3700_V)entityIn).J_1907_R(i - 1) * ((float)Math.PI / 180);
        }
    }

    @Override
    public /* synthetic */ Iterable n_1700_B() {
        return this.J_1907_R();
    }
}


