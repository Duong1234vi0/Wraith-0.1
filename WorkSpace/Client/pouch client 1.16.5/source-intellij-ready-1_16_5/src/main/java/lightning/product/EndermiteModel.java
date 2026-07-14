/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import lightning.product.ListModel;
import lightning.product.N_4263_v;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class EndermiteModel<T extends N_4263_v>
extends ListModel<T> {
    private static final int[][] n_1700_B = new int[][]{{4, 3, 2}, {6, 4, 5}, {3, 3, 1}, {1, 2, 1}};
    private static final int[][] J_1907_R = new int[][]{{0, 0}, {0, 5}, {0, 14}, {0, 18}};
    private static final int R_4764_Y = n_1700_B.length;
    private final e_4189_z[] G_564_y = new e_4189_z[R_4764_Y];

    public EndermiteModel() {
        float f = -3.5f;
        for (int i = 0; i < this.G_564_y.length; ++i) {
            this.G_564_y[i] = new e_4189_z(this, J_1907_R[i][0], J_1907_R[i][1]);
            this.G_564_y[i].n_1700_B((float)n_1700_B[i][0] * -0.5f, 0.0f, (float)n_1700_B[i][2] * -0.5f, n_1700_B[i][0], n_1700_B[i][1], n_1700_B[i][2]);
            this.G_564_y[i].n_1700_B(0.0f, 24 - n_1700_B[i][1], f);
            if (i >= this.G_564_y.length - 1) continue;
            f += (float)(n_1700_B[i][2] + n_1700_B[i + 1][2]) * 0.5f;
        }
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return Arrays.asList(this.G_564_y);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        for (int i = 0; i < this.G_564_y.length; ++i) {
            this.G_564_y[i].v_4262_N = u_530_F.J_1907_R(ageInTicks * 0.9f + (float)i * 0.15f * (float)Math.PI) * (float)Math.PI * 0.01f * (float)(1 + Math.abs(i - 2));
            this.G_564_y[i].R_4764_Y = u_530_F.n_1700_B(ageInTicks * 0.9f + (float)i * 0.15f * (float)Math.PI) * (float)Math.PI * 0.1f * (float)Math.abs(i - 2);
        }
    }
}


