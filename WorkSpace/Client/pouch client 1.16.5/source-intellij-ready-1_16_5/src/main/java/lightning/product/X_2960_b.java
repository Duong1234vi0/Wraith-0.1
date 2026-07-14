/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.x_607_J;
import lombok.Generated;

public class X_2960_b
implements x_607_J {
    @Generated
    public X_2960_b() {
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof X_2960_b)) {
            return false;
        }
        X_2960_b other = (X_2960_b)o;
        return other.n_1700_B(this);
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof X_2960_b;
    }

    @Generated
    public int hashCode() {
        boolean result = true;
        return 1;
    }

    @Generated
    public String toString() {
        return "EventGuardUpdate()";
    }
}

