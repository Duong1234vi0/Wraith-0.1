/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class g_1734_y
extends d_2427_y
implements x_607_J {
    @Generated
    public g_1734_y() {
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof g_1734_y)) {
            return false;
        }
        g_1734_y other = (g_1734_y)o;
        return other.n_1700_B(this);
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof g_1734_y;
    }

    @Generated
    public int hashCode() {
        boolean result = true;
        return 1;
    }

    @Generated
    public String toString() {
        return "EventPostUpdate()";
    }
}

