/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.x_282_a;

public interface Nameable {
    public x_282_a O_1309_Q();

    default public boolean t_3452_g() {
        return this.k_2302_P() != null;
    }

    default public x_282_a c_() {
        return this.O_1309_Q();
    }

    @Nullable
    default public x_282_a k_2302_P() {
        return null;
    }
}


