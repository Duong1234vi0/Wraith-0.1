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

public class LlamaSpitModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z n_1700_B = new e_4189_z(this);

    public LlamaSpitModel() {
        this(0.0f);
    }

    public LlamaSpitModel(float p_i47225_1_) {
        int i = 2;
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(-4.0f, 0.0f, 0.0f, 2.0f, 2.0f, 2.0f, p_i47225_1_);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(0.0f, -4.0f, 0.0f, 2.0f, 2.0f, 2.0f, p_i47225_1_);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(0.0f, 0.0f, -4.0f, 2.0f, 2.0f, 2.0f, p_i47225_1_);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(0.0f, 0.0f, 0.0f, 2.0f, 2.0f, 2.0f, p_i47225_1_);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(2.0f, 0.0f, 0.0f, 2.0f, 2.0f, 2.0f, p_i47225_1_);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(0.0f, 2.0f, 0.0f, 2.0f, 2.0f, 2.0f, p_i47225_1_);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(0.0f, 0.0f, 2.0f, 2.0f, 2.0f, 2.0f, p_i47225_1_);
        this.n_1700_B.n_1700_B(0.0f, 0.0f, 0.0f);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B);
    }
}


