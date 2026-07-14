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

public class r_4879_Z
extends d_2427_y
implements x_607_J {
    @Generated
    public r_4879_Z() {
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof r_4879_Z)) {
            return false;
        }
        r_4879_Z other = (r_4879_Z)o;
        return other.n_1700_B(this);
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof r_4879_Z;
    }

    @Generated
    public int hashCode() {
        boolean result = true;
        return 1;
    }

    @Generated
    public String toString() {
        return "EventSprintReset()";
    }
}

