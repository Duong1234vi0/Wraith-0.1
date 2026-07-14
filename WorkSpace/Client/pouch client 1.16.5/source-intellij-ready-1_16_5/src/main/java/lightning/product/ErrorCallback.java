/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_2871_b;
import lightning.product.x_282_a;

public interface ErrorCallback {
    public void n_1700_B(x_282_a var1);

    default public void n_1700_B(String p_237703_1_) {
        this.n_1700_B(new U_2871_b(p_237703_1_));
    }
}


