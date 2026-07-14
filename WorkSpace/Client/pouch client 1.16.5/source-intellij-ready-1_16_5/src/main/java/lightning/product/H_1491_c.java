/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.Setting;

public class H_1491_c
extends Setting<Void> {
    private final Runnable G_564_y;

    public H_1491_c(String name, Runnable action) {
        super(name, null);
        this.G_564_y = action;
    }

    public H_1491_c(String name, Runnable action, Supplier<Boolean> visible) {
        super(name, null);
        this.G_564_y = action;
        this.n_1700_B(visible);
    }

    public void w_1484_f() {
        if (this.G_564_y != null) {
            this.G_564_y.run();
        }
    }
}

