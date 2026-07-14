/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.List;
import lightning.product.Z_1993_T;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class o_3599_Z
extends d_2427_y
implements x_607_J {
    private int n_1700_B;
    private List<Z_1993_T> J_1907_R;

    @Generated
    public int J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public List<Z_1993_T> R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(int windowId) {
        this.n_1700_B = windowId;
    }

    @Generated
    public void n_1700_B(List<Z_1993_T> items) {
        this.J_1907_R = items;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof o_3599_Z)) {
            return false;
        }
        o_3599_Z other = (o_3599_Z)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.J_1907_R() != other.J_1907_R()) {
            return false;
        }
        List<Z_1993_T> this$items = this.R_4764_Y();
        List<Z_1993_T> other$items = other.R_4764_Y();
        return !(this$items == null ? other$items != null : !((Object)this$items).equals(other$items));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof o_3599_Z;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.J_1907_R();
        List<Z_1993_T> $items = this.R_4764_Y();
        result = result * 59 + ($items == null ? 43 : ((Object)$items).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventContainerTick(windowId=" + this.J_1907_R() + ", items=" + String.valueOf(this.R_4764_Y()) + ")";
    }

    @Generated
    public o_3599_Z(int windowId, List<Z_1993_T> items) {
        this.n_1700_B = windowId;
        this.J_1907_R = items;
    }
}

