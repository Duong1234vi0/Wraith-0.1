/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Supplier;

public class l_52_h<T> {
    private Supplier<T> n_1700_B;
    private T J_1907_R;

    public l_52_h(Supplier<T> supplierIn) {
        this.n_1700_B = supplierIn;
    }

    public T n_1700_B() {
        Supplier<T> supplier = this.n_1700_B;
        if (supplier != null) {
            this.J_1907_R = supplier.get();
            this.n_1700_B = null;
        }
        return this.J_1907_R;
    }
}

