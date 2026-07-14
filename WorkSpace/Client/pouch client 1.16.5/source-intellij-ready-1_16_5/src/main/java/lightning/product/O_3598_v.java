/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class O_3598_v
extends d_2427_y
implements x_607_J {
    private K_4074_S n_1700_B;
    private float J_1907_R;

    @Generated
    public K_4074_S J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public float R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(K_4074_S blockState) {
        this.n_1700_B = blockState;
    }

    @Generated
    public void n_1700_B(float digSpeed) {
        this.J_1907_R = digSpeed;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof O_3598_v)) {
            return false;
        }
        O_3598_v other = (O_3598_v)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        K_4074_S this$blockState = this.J_1907_R();
        K_4074_S other$blockState = other.J_1907_R();
        return !(this$blockState == null ? other$blockState != null : !this$blockState.equals(other$blockState));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof O_3598_v;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        K_4074_S $blockState = this.J_1907_R();
        result = result * 59 + ($blockState == null ? 43 : $blockState.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventGetDigSpeed(blockState=" + String.valueOf(this.J_1907_R()) + ", digSpeed=" + this.R_4764_Y() + ")";
    }

    @Generated
    public O_3598_v(K_4074_S blockState, float digSpeed) {
        this.n_1700_B = blockState;
        this.J_1907_R = digSpeed;
    }
}

