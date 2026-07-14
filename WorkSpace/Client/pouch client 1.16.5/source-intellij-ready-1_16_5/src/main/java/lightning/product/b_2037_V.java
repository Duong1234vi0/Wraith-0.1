/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.Setting;

public class b_2037_V
extends Setting<String> {
    public b_2037_V(String name) {
        super(name, "");
    }

    public b_2037_V(String name, Supplier<Boolean> visible) {
        super(name, "");
        this.n_1700_B(visible);
    }
}

