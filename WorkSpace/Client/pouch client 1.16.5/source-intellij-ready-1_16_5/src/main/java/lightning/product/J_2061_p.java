/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.x_1688_C;
import lightning.product.x_607_J;
import lombok.Generated;

public class J_2061_p
extends d_2427_y
implements x_607_J {
    private int n_1700_B;
    private x_1688_C J_1907_R;

    @Generated
    public int J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public x_1688_C R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(int swipeSpeed) {
        this.n_1700_B = swipeSpeed;
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
        if (!(o instanceof J_2061_p)) {
            return false;
        }
        J_2061_p other = (J_2061_p)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.J_1907_R() != other.J_1907_R()) {
            return false;
        }
        x_1688_C this$hand = this.R_4764_Y();
        x_1688_C other$hand = other.R_4764_Y();
        return !(this$hand == null ? other$hand != null : !((Object)((Object)this$hand)).equals((Object)other$hand));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof J_2061_p;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.J_1907_R();
        x_1688_C $hand = this.R_4764_Y();
        result = result * 59 + ($hand == null ? 43 : ((Object)((Object)$hand)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventSwingSpeed(swipeSpeed=" + this.J_1907_R() + ", hand=" + String.valueOf((Object)this.R_4764_Y()) + ")";
    }

    @Generated
    public J_2061_p(int swipeSpeed, x_1688_C hand) {
        this.n_1700_B = swipeSpeed;
        this.J_1907_R = hand;
    }
}

