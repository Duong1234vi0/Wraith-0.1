/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.Setting;

public class h_2367_h
extends Setting<Integer> {
    public int G_564_y;
    public boolean P_1922_E;

    public h_2367_h(String name, boolean alphaBar, Integer defaultVal) {
        super(name, defaultVal);
        this.G_564_y = defaultVal;
        this.P_1922_E = alphaBar;
    }

    public h_2367_h(String name, boolean alphaBar, Integer defaultVal, Supplier<Boolean> visible) {
        super(name, defaultVal);
        this.G_564_y = defaultVal;
        this.P_1922_E = alphaBar;
        this.n_1700_B(visible);
    }

    public float w_1484_f() {
        return (Integer)this.J_1907_R() >> 24 & 0xFF;
    }
}

