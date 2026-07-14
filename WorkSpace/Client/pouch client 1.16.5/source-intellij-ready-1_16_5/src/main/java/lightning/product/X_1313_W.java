/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.c_1514_x;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class X_1313_W
extends d_2427_y
implements x_607_J {
    private c_1514_x n_1700_B;

    @Generated
    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(c_1514_x pos) {
        this.n_1700_B = pos;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof X_1313_W)) {
            return false;
        }
        X_1313_W other = (X_1313_W)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        c_1514_x this$pos = this.J_1907_R();
        c_1514_x other$pos = other.J_1907_R();
        return !(this$pos == null ? other$pos != null : !((Object)this$pos).equals(other$pos));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof X_1313_W;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        c_1514_x $pos = this.J_1907_R();
        result = result * 59 + ($pos == null ? 43 : ((Object)$pos).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventBlockCollide(pos=" + String.valueOf(this.J_1907_R()) + ")";
    }

    @Generated
    public X_1313_W(c_1514_x pos) {
        this.n_1700_B = pos;
    }
}

