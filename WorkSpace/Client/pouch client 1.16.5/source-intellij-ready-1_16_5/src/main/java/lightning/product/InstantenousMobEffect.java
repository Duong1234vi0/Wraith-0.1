/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.g_422_i;
import lightning.product.j_956_y;

public class InstantenousMobEffect
extends g_422_i {
    public InstantenousMobEffect(j_956_y type, int liquidColor) {
        super(type, liquidColor);
    }

    @Override
    public boolean n_1700_B() {
        return true;
    }

    @Override
    public boolean n_1700_B(int duration, int amplifier) {
        return duration >= 1;
    }
}


