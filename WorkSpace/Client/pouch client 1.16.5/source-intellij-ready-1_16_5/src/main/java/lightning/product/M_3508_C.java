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

public class M_3508_C
extends d_2427_y
implements x_607_J {
    float n_1700_B;
    float J_1907_R;

    @Generated
    public float J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public float R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(float yaw) {
        this.n_1700_B = yaw;
    }

    @Generated
    public void J_1907_R(float pitch) {
        this.J_1907_R = pitch;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof M_3508_C)) {
            return false;
        }
        M_3508_C other = (M_3508_C)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.J_1907_R(), other.J_1907_R()) != 0) {
            return false;
        }
        return Float.compare(this.R_4764_Y(), other.R_4764_Y()) == 0;
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof M_3508_C;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.J_1907_R());
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        return result;
    }

    @Generated
    public String toString() {
        return "EventNoRotate(yaw=" + this.J_1907_R() + ", pitch=" + this.R_4764_Y() + ")";
    }

    @Generated
    public M_3508_C(float yaw, float pitch) {
        this.n_1700_B = yaw;
        this.J_1907_R = pitch;
    }
}

