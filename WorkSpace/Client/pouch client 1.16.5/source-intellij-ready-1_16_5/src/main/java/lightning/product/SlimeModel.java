/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.ListModel;
import lightning.product.N_4263_v;
import lightning.product.e_4189_z;

public class SlimeModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;

    public SlimeModel(int slimeBodyTexOffY) {
        this.n_1700_B = new e_4189_z(this, 0, slimeBodyTexOffY);
        this.J_1907_R = new e_4189_z(this, 32, 0);
        this.R_4764_Y = new e_4189_z(this, 32, 4);
        this.G_564_y = new e_4189_z(this, 32, 8);
        if (slimeBodyTexOffY > 0) {
            this.n_1700_B.n_1700_B(-3.0f, 17.0f, -3.0f, 6.0f, 6.0f, 6.0f);
            this.J_1907_R.n_1700_B(-3.25f, 18.0f, -3.5f, 2.0f, 2.0f, 2.0f);
            this.R_4764_Y.n_1700_B(1.25f, 18.0f, -3.5f, 2.0f, 2.0f, 2.0f);
            this.G_564_y.n_1700_B(0.0f, 21.0f, -3.5f, 1.0f, 1.0f, 1.0f);
        } else {
            this.n_1700_B.n_1700_B(-4.0f, 16.0f, -4.0f, 8.0f, 8.0f, 8.0f);
        }
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y);
    }
}


