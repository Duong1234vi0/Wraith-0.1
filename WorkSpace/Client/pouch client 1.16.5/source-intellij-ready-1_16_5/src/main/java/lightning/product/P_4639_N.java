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

public class P_4639_N
extends d_2427_y
implements x_607_J {
    @Generated
    public P_4639_N() {
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof P_4639_N)) {
            return false;
        }
        P_4639_N other = (P_4639_N)o;
        return other.n_1700_B(this);
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof P_4639_N;
    }

    @Generated
    public int hashCode() {
        boolean result = true;
        return 1;
    }

    @Generated
    public String toString() {
        return "EventJump()";
    }
}

