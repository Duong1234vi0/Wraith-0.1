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
import lightning.product.ListModel;
import lightning.product.N_4263_v;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class BlazeModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z[] n_1700_B;
    private final e_4189_z J_1907_R = new e_4189_z(this, 0, 0);
    private final ImmutableList<e_4189_z> R_4764_Y;

    public BlazeModel() {
        this.J_1907_R.n_1700_B(-4.0f, -4.0f, -4.0f, 8.0f, 8.0f, 8.0f);
        this.n_1700_B = new e_4189_z[12];
        for (int i = 0; i < this.n_1700_B.length; ++i) {
            this.n_1700_B[i] = new e_4189_z(this, 0, 16);
            this.n_1700_B[i].n_1700_B(0.0f, 0.0f, 0.0f, 2.0f, 8.0f, 2.0f);
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.add((Object)this.J_1907_R);
        builder.addAll(Arrays.asList(this.n_1700_B));
        this.R_4764_Y = builder.build();
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return this.R_4764_Y;
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = ageInTicks * (float)Math.PI * -0.1f;
        for (int i = 0; i < 4; ++i) {
            this.n_1700_B[i].G_564_y = -2.0f + u_530_F.J_1907_R(((float)(i * 2) + ageInTicks) * 0.25f);
            this.n_1700_B[i].R_4764_Y = u_530_F.J_1907_R(f) * 9.0f;
            this.n_1700_B[i].P_1922_E = u_530_F.n_1700_B(f) * 9.0f;
            f += 1.0f;
        }
        f = 0.7853982f + ageInTicks * (float)Math.PI * 0.03f;
        for (int j = 4; j < 8; ++j) {
            this.n_1700_B[j].G_564_y = 2.0f + u_530_F.J_1907_R(((float)(j * 2) + ageInTicks) * 0.25f);
            this.n_1700_B[j].R_4764_Y = u_530_F.J_1907_R(f) * 7.0f;
            this.n_1700_B[j].P_1922_E = u_530_F.n_1700_B(f) * 7.0f;
            f += 1.0f;
        }
        f = 0.47123894f + ageInTicks * (float)Math.PI * -0.05f;
        for (int k = 8; k < 12; ++k) {
            this.n_1700_B[k].G_564_y = 11.0f + u_530_F.J_1907_R(((float)k * 1.5f + ageInTicks) * 0.5f);
            this.n_1700_B[k].R_4764_Y = u_530_F.J_1907_R(f) * 5.0f;
            this.n_1700_B[k].P_1922_E = u_530_F.n_1700_B(f) * 5.0f;
            f += 1.0f;
        }
        this.J_1907_R.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.J_1907_R.u_1723_Y = headPitch * ((float)Math.PI / 180);
    }
}


