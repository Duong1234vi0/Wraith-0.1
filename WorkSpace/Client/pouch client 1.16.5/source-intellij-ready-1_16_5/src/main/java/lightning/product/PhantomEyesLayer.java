/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.W_4144_J;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.PhantomModel;

public class PhantomEyesLayer<T extends N_4263_v>
extends W_4144_J<T, PhantomModel<T>> {
    private static final o_2576_A n_1700_B = o_2576_A.P_4830_p(new g_2336_b("textures/entity/phantom_eyes.png"));

    public PhantomEyesLayer(j_4203_m<T, PhantomModel<T>> p_i50928_1_) {
        super(p_i50928_1_);
    }

    @Override
    public o_2576_A n_1700_B() {
        return n_1700_B;
    }
}


