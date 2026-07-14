/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.Setting;

public class KeyBindSetting
extends Setting<Integer> {
    public KeyBindSetting(String name) {
        super(name, -1);
    }

    public KeyBindSetting(String name, Supplier<Boolean> visible) {
        super(name, -1);
        this.n_1700_B(visible);
    }
}

