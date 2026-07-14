/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.q_1613_l;
import lightning.product.x_607_J;
import lombok.Generated;

public class O_1795_e
extends d_2427_y
implements x_607_J {
    private q_1613_l n_1700_B;
    private float J_1907_R;

    @Generated
    public q_1613_l J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public float R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(q_1613_l item) {
        this.n_1700_B = item;
    }

    @Generated
    public void n_1700_B(float partialTicks) {
        this.J_1907_R = partialTicks;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof O_1795_e)) {
            return false;
        }
        O_1795_e other = (O_1795_e)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        q_1613_l this$item = this.J_1907_R();
        q_1613_l other$item = other.J_1907_R();
        return !(this$item == null ? other$item != null : !this$item.equals(other$item));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof O_1795_e;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        q_1613_l $item = this.J_1907_R();
        result = result * 59 + ($item == null ? 43 : $item.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventCooldownTracker(item=" + String.valueOf(this.J_1907_R()) + ", partialTicks=" + this.R_4764_Y() + ")";
    }

    @Generated
    public O_1795_e(q_1613_l item, float partialTicks) {
        this.n_1700_B = item;
        this.J_1907_R = partialTicks;
    }
}

