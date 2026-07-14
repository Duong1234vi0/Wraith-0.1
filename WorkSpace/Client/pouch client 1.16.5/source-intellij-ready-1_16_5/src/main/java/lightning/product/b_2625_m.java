/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.Z_1993_T;
import lightning.product.d_2427_y;
import lightning.product.x_1688_C;
import lightning.product.x_607_J;
import lombok.Generated;

public class b_2625_m
extends d_2427_y
implements x_607_J {
    private Z_1993_T n_1700_B;
    private x_1688_C J_1907_R;

    @Generated
    public Z_1993_T J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public x_1688_C R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(Z_1993_T itemStack) {
        this.n_1700_B = itemStack;
    }

    @Generated
    public void n_1700_B(x_1688_C hand) {
        this.J_1907_R = hand;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof b_2625_m)) {
            return false;
        }
        b_2625_m other = (b_2625_m)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        Z_1993_T this$itemStack = this.J_1907_R();
        Z_1993_T other$itemStack = other.J_1907_R();
        if (this$itemStack == null ? other$itemStack != null : !this$itemStack.equals(other$itemStack)) {
            return false;
        }
        x_1688_C this$hand = this.R_4764_Y();
        x_1688_C other$hand = other.R_4764_Y();
        return !(this$hand == null ? other$hand != null : !((Object)((Object)this$hand)).equals((Object)other$hand));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof b_2625_m;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Z_1993_T $itemStack = this.J_1907_R();
        result = result * 59 + ($itemStack == null ? 43 : $itemStack.hashCode());
        x_1688_C $hand = this.R_4764_Y();
        result = result * 59 + ($hand == null ? 43 : ((Object)((Object)$hand)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventRightClickItemCheck(itemStack=" + String.valueOf(this.J_1907_R()) + ", hand=" + String.valueOf((Object)this.R_4764_Y()) + ")";
    }

    @Generated
    public b_2625_m(Z_1993_T itemStack, x_1688_C hand) {
        this.n_1700_B = itemStack;
        this.J_1907_R = hand;
    }
}

