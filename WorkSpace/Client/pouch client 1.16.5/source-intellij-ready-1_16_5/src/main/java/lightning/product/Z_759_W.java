/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class Z_759_W
extends d_2427_y
implements x_607_J {
    private K_4074_S n_1700_B;
    private c_1514_x J_1907_R;

    @Generated
    public K_4074_S J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public c_1514_x R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(K_4074_S state) {
        this.n_1700_B = state;
    }

    @Generated
    public void n_1700_B(c_1514_x pos) {
        this.J_1907_R = pos;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Z_759_W)) {
            return false;
        }
        Z_759_W other = (Z_759_W)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        K_4074_S this$state = this.J_1907_R();
        K_4074_S other$state = other.J_1907_R();
        if (this$state == null ? other$state != null : !this$state.equals(other$state)) {
            return false;
        }
        c_1514_x this$pos = this.R_4764_Y();
        c_1514_x other$pos = other.R_4764_Y();
        return !(this$pos == null ? other$pos != null : !((Object)this$pos).equals(other$pos));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof Z_759_W;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        K_4074_S $state = this.J_1907_R();
        result = result * 59 + ($state == null ? 43 : $state.hashCode());
        c_1514_x $pos = this.R_4764_Y();
        result = result * 59 + ($pos == null ? 43 : ((Object)$pos).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventBlockRayTrace(state=" + String.valueOf(this.J_1907_R()) + ", pos=" + String.valueOf(this.R_4764_Y()) + ")";
    }

    @Generated
    public Z_759_W(K_4074_S state, c_1514_x pos) {
        this.n_1700_B = state;
        this.J_1907_R = pos;
    }
}

