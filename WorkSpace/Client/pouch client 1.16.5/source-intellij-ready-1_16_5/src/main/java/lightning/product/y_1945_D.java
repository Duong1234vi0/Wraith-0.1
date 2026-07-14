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

public class y_1945_D
extends d_2427_y
implements x_607_J {
    private int n_1700_B;

    @Generated
    public int J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(int windowId) {
        this.n_1700_B = windowId;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof y_1945_D)) {
            return false;
        }
        y_1945_D other = (y_1945_D)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        return this.J_1907_R() == other.J_1907_R();
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof y_1945_D;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.J_1907_R();
        return result;
    }

    @Generated
    public String toString() {
        return "EventInventoryClose(windowId=" + this.J_1907_R() + ")";
    }

    @Generated
    public y_1945_D(int windowId) {
        this.n_1700_B = windowId;
    }
}

