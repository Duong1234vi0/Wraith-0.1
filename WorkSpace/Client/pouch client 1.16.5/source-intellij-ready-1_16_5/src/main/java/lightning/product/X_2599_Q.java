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

public class X_2599_Q<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z n_1700_B;

    public X_2599_Q() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.n_1700_B = new e_4189_z(this);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(-4.0f, -4.0f, -1.0f, 8.0f, 8.0f, 2.0f, 0.0f);
        this.n_1700_B.n_1700_B(0, 10).n_1700_B(-1.0f, -4.0f, -4.0f, 2.0f, 8.0f, 8.0f, 0.0f);
        this.n_1700_B.n_1700_B(20, 0).n_1700_B(-4.0f, -1.0f, -4.0f, 8.0f, 2.0f, 8.0f, 0.0f);
        this.n_1700_B.n_1700_B(0.0f, 0.0f, 0.0f);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
    }
}


