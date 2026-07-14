/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.UnaryOperator;
import lightning.product.D_4024_W;
import lightning.product.U_2871_b;
import lightning.product.Z_1567_W;
import lightning.product.x_282_a;

public interface MutableComponent
extends x_282_a {
    public MutableComponent n_1700_B(Z_1567_W var1);

    default public MutableComponent n_1700_B(String string) {
        return this.n_1700_B(new U_2871_b(string));
    }

    public MutableComponent n_1700_B(x_282_a var1);

    default public MutableComponent n_1700_B(UnaryOperator<Z_1567_W> modifyFunc) {
        this.n_1700_B((Z_1567_W)modifyFunc.apply(this.n_1700_B()));
        return this;
    }

    default public MutableComponent J_1907_R(Z_1567_W style) {
        this.n_1700_B(style.n_1700_B(this.n_1700_B()));
        return this;
    }

    default public MutableComponent n_1700_B(D_4024_W ... formats) {
        this.n_1700_B(this.n_1700_B().n_1700_B(formats));
        return this;
    }

    default public MutableComponent n_1700_B(D_4024_W format) {
        this.n_1700_B(this.n_1700_B().J_1907_R(format));
        return this;
    }
}


