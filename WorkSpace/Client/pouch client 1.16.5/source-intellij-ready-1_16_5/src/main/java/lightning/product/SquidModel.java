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

public class SquidModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z[] J_1907_R = new e_4189_z[8];
    private final ImmutableList<e_4189_z> R_4764_Y;

    public SquidModel() {
        int i = -16;
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-6.0f, -8.0f, -6.0f, 12.0f, 16.0f, 12.0f);
        this.n_1700_B.G_564_y += 8.0f;
        for (int j = 0; j < this.J_1907_R.length; ++j) {
            this.J_1907_R[j] = new e_4189_z(this, 48, 0);
            double d0 = (double)j * Math.PI * 2.0 / (double)this.J_1907_R.length;
            float f = (float)Math.cos(d0) * 5.0f;
            float f1 = (float)Math.sin(d0) * 5.0f;
            this.J_1907_R[j].n_1700_B(-1.0f, 0.0f, -1.0f, 2.0f, 18.0f, 2.0f);
            this.J_1907_R[j].R_4764_Y = f;
            this.J_1907_R[j].P_1922_E = f1;
            this.J_1907_R[j].G_564_y = 15.0f;
            d0 = (double)j * Math.PI * -2.0 / (double)this.J_1907_R.length + 1.5707963267948966;
            this.J_1907_R[j].v_4262_N = (float)d0;
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.add((Object)this.n_1700_B);
        builder.addAll(Arrays.asList(this.J_1907_R));
        this.R_4764_Y = builder.build();
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        for (e_4189_z modelrenderer : this.J_1907_R) {
            modelrenderer.u_1723_Y = ageInTicks;
        }
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return this.R_4764_Y;
    }
}


