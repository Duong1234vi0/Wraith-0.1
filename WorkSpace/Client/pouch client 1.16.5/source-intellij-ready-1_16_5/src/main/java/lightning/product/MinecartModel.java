/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import lightning.product.ListModel;
import lightning.product.N_4263_v;
import lightning.product.e_4189_z;

public class MinecartModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z[] n_1700_B = new e_4189_z[6];

    public MinecartModel() {
        this.n_1700_B[0] = new e_4189_z(this, 0, 10);
        this.n_1700_B[1] = new e_4189_z(this, 0, 0);
        this.n_1700_B[2] = new e_4189_z(this, 0, 0);
        this.n_1700_B[3] = new e_4189_z(this, 0, 0);
        this.n_1700_B[4] = new e_4189_z(this, 0, 0);
        this.n_1700_B[5] = new e_4189_z(this, 44, 10);
        int i = 20;
        int j = 8;
        int k = 16;
        int l = 4;
        this.n_1700_B[0].n_1700_B(-10.0f, -8.0f, -1.0f, 20.0f, 16.0f, 2.0f, 0.0f);
        this.n_1700_B[0].n_1700_B(0.0f, 4.0f, 0.0f);
        this.n_1700_B[5].n_1700_B(-9.0f, -7.0f, -1.0f, 18.0f, 14.0f, 1.0f, 0.0f);
        this.n_1700_B[5].n_1700_B(0.0f, 4.0f, 0.0f);
        this.n_1700_B[1].n_1700_B(-8.0f, -9.0f, -1.0f, 16.0f, 8.0f, 2.0f, 0.0f);
        this.n_1700_B[1].n_1700_B(-9.0f, 4.0f, 0.0f);
        this.n_1700_B[2].n_1700_B(-8.0f, -9.0f, -1.0f, 16.0f, 8.0f, 2.0f, 0.0f);
        this.n_1700_B[2].n_1700_B(9.0f, 4.0f, 0.0f);
        this.n_1700_B[3].n_1700_B(-8.0f, -9.0f, -1.0f, 16.0f, 8.0f, 2.0f, 0.0f);
        this.n_1700_B[3].n_1700_B(0.0f, 4.0f, -7.0f);
        this.n_1700_B[4].n_1700_B(-8.0f, -9.0f, -1.0f, 16.0f, 8.0f, 2.0f, 0.0f);
        this.n_1700_B[4].n_1700_B(0.0f, 4.0f, 7.0f);
        this.n_1700_B[0].u_1723_Y = 1.5707964f;
        this.n_1700_B[1].v_4262_N = 4.712389f;
        this.n_1700_B[2].v_4262_N = 1.5707964f;
        this.n_1700_B[3].v_4262_N = (float)Math.PI;
        this.n_1700_B[5].u_1723_Y = -1.5707964f;
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B[5].G_564_y = 4.0f - ageInTicks;
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return Arrays.asList(this.n_1700_B);
    }
}


