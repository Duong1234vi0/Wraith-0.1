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

public class SilverfishModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z[] n_1700_B;
    private final e_4189_z[] J_1907_R;
    private final ImmutableList<e_4189_z> R_4764_Y;
    private final float[] G_564_y = new float[7];
    private static final int[][] P_1922_E = new int[][]{{3, 2, 2}, {4, 3, 2}, {6, 4, 3}, {3, 3, 3}, {2, 2, 3}, {2, 1, 2}, {1, 1, 2}};
    private static final int[][] u_1723_Y = new int[][]{{0, 0}, {0, 4}, {0, 9}, {0, 16}, {0, 22}, {11, 0}, {13, 4}};

    public SilverfishModel() {
        this.n_1700_B = new e_4189_z[7];
        float f = -3.5f;
        for (int i = 0; i < this.n_1700_B.length; ++i) {
            this.n_1700_B[i] = new e_4189_z(this, u_1723_Y[i][0], u_1723_Y[i][1]);
            this.n_1700_B[i].n_1700_B((float)P_1922_E[i][0] * -0.5f, 0.0f, (float)P_1922_E[i][2] * -0.5f, P_1922_E[i][0], P_1922_E[i][1], P_1922_E[i][2]);
            this.n_1700_B[i].n_1700_B(0.0f, 24 - P_1922_E[i][1], f);
            this.G_564_y[i] = f;
            if (i >= this.n_1700_B.length - 1) continue;
            f += (float)(P_1922_E[i][2] + P_1922_E[i + 1][2]) * 0.5f;
        }
        this.J_1907_R = new e_4189_z[3];
        this.J_1907_R[0] = new e_4189_z(this, 20, 0);
        this.J_1907_R[0].n_1700_B(-5.0f, 0.0f, (float)P_1922_E[2][2] * -0.5f, 10.0f, 8.0f, P_1922_E[2][2]);
        this.J_1907_R[0].n_1700_B(0.0f, 16.0f, this.G_564_y[2]);
        this.J_1907_R[1] = new e_4189_z(this, 20, 11);
        this.J_1907_R[1].n_1700_B(-3.0f, 0.0f, (float)P_1922_E[4][2] * -0.5f, 6.0f, 4.0f, P_1922_E[4][2]);
        this.J_1907_R[1].n_1700_B(0.0f, 20.0f, this.G_564_y[4]);
        this.J_1907_R[2] = new e_4189_z(this, 20, 18);
        this.J_1907_R[2].n_1700_B(-3.0f, 0.0f, (float)P_1922_E[4][2] * -0.5f, 6.0f, 5.0f, P_1922_E[1][2]);
        this.J_1907_R[2].n_1700_B(0.0f, 19.0f, this.G_564_y[1]);
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.addAll(Arrays.asList(this.n_1700_B));
        builder.addAll(Arrays.asList(this.J_1907_R));
        this.R_4764_Y = builder.build();
    }

    public ImmutableList<e_4189_z> J_1907_R() {
        return this.R_4764_Y;
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        for (int i = 0; i < this.n_1700_B.length; ++i) {
            this.n_1700_B[i].v_4262_N = u_530_F.J_1907_R(ageInTicks * 0.9f + (float)i * 0.15f * (float)Math.PI) * (float)Math.PI * 0.05f * (float)(1 + Math.abs(i - 2));
            this.n_1700_B[i].R_4764_Y = u_530_F.n_1700_B(ageInTicks * 0.9f + (float)i * 0.15f * (float)Math.PI) * (float)Math.PI * 0.2f * (float)Math.abs(i - 2);
        }
        this.J_1907_R[0].v_4262_N = this.n_1700_B[2].v_4262_N;
        this.J_1907_R[1].v_4262_N = this.n_1700_B[4].v_4262_N;
        this.J_1907_R[1].R_4764_Y = this.n_1700_B[4].R_4764_Y;
        this.J_1907_R[2].v_4262_N = this.n_1700_B[1].v_4262_N;
        this.J_1907_R[2].R_4764_Y = this.n_1700_B[1].R_4764_Y;
    }

    @Override
    public /* synthetic */ Iterable n_1700_B() {
        return this.J_1907_R();
    }
}


