/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.Random;
import lightning.product.ListModel;
import lightning.product.N_4263_v;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class GhastModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z[] n_1700_B = new e_4189_z[9];
    private final ImmutableList<e_4189_z> J_1907_R;

    public GhastModel() {
        ImmutableList.Builder builder = ImmutableList.builder();
        e_4189_z modelrenderer = new e_4189_z(this, 0, 0);
        modelrenderer.n_1700_B(-8.0f, -8.0f, -8.0f, 16.0f, 16.0f, 16.0f);
        modelrenderer.G_564_y = 17.6f;
        builder.add((Object)modelrenderer);
        Random random = new Random(1660L);
        for (int i = 0; i < this.n_1700_B.length; ++i) {
            this.n_1700_B[i] = new e_4189_z(this, 0, 0);
            float f = (((float)(i % 3) - (float)(i / 3 % 2) * 0.5f + 0.25f) / 2.0f * 2.0f - 1.0f) * 5.0f;
            float f1 = ((float)(i / 3) / 2.0f * 2.0f - 1.0f) * 5.0f;
            int j = random.nextInt(7) + 8;
            this.n_1700_B[i].n_1700_B(-1.0f, 0.0f, -1.0f, 2.0f, j, 2.0f);
            this.n_1700_B[i].R_4764_Y = f;
            this.n_1700_B[i].P_1922_E = f1;
            this.n_1700_B[i].G_564_y = 24.6f;
            builder.add((Object)this.n_1700_B[i]);
        }
        this.J_1907_R = builder.build();
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        for (int i = 0; i < this.n_1700_B.length; ++i) {
            this.n_1700_B[i].u_1723_Y = 0.2f * u_530_F.n_1700_B(ageInTicks * 0.3f + (float)i) + 0.4f;
        }
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return this.J_1907_R;
    }
}


