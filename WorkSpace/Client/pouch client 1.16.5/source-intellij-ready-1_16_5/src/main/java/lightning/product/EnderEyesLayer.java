/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.W_4144_J;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.r_4811_B;
import lightning.product.w_2498_n;

public class EnderEyesLayer<T extends r_4811_B>
extends W_4144_J<T, w_2498_n<T>> {
    private static final o_2576_A n_1700_B = o_2576_A.P_4830_p(new g_2336_b("textures/entity/enderman/enderman_eyes.png"));

    public EnderEyesLayer(j_4203_m<T, w_2498_n<T>> rendererIn) {
        super(rendererIn);
    }

    @Override
    public o_2576_A n_1700_B() {
        return n_1700_B;
    }
}


