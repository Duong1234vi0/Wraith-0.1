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
import lightning.product.A_268_Q;
import lightning.product.ListModel;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class LavaSlimeModel<T extends A_268_Q>
extends ListModel<T> {
    private final e_4189_z[] n_1700_B = new e_4189_z[8];
    private final e_4189_z J_1907_R;
    private final ImmutableList<e_4189_z> R_4764_Y;

    public LavaSlimeModel() {
        for (int i = 0; i < this.n_1700_B.length; ++i) {
            int j = 0;
            int k = i;
            if (i == 2) {
                j = 24;
                k = 10;
            } else if (i == 3) {
                j = 24;
                k = 19;
            }
            this.n_1700_B[i] = new e_4189_z(this, j, k);
            this.n_1700_B[i].n_1700_B(-4.0f, 16 + i, -4.0f, 8.0f, 1.0f, 8.0f);
        }
        this.J_1907_R = new e_4189_z(this, 0, 16);
        this.J_1907_R.n_1700_B(-2.0f, 18.0f, -2.0f, 4.0f, 4.0f, 4.0f);
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.add((Object)this.J_1907_R);
        builder.addAll(Arrays.asList(this.n_1700_B));
        this.R_4764_Y = builder.build();
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        float f = u_530_F.v_4262_N(partialTick, ((A_268_Q)entityIn).R_4764_Y, ((A_268_Q)entityIn).J_1907_R);
        if (f < 0.0f) {
            f = 0.0f;
        }
        for (int i = 0; i < this.n_1700_B.length; ++i) {
            this.n_1700_B[i].G_564_y = (float)(-(4 - i)) * f * 1.7f;
        }
    }

    public ImmutableList<e_4189_z> J_1907_R() {
        return this.R_4764_Y;
    }

    @Override
    public /* synthetic */ Iterable n_1700_B() {
        return this.J_1907_R();
    }
}


