/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SpiderModel;
import lightning.product.N_4263_v;
import lightning.product.W_4144_J;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;

public class SpiderEyesLayer<T extends N_4263_v, M extends SpiderModel<T>>
extends W_4144_J<T, M> {
    private static final o_2576_A n_1700_B = o_2576_A.P_4830_p(new g_2336_b("textures/entity/spider_eyes.png"));

    public SpiderEyesLayer(j_4203_m<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public o_2576_A n_1700_B() {
        return n_1700_B;
    }
}


