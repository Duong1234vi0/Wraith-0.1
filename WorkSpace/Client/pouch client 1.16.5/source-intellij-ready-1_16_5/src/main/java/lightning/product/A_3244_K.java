/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.e_2866_D;
import lightning.product.x_607_J;
import lombok.Generated;

public class A_3244_K
extends d_2427_y
implements x_607_J {
    private e_2866_D n_1700_B;
    private float J_1907_R;

    @Generated
    public e_2866_D J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public float R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(e_2866_D vector3d) {
        this.n_1700_B = vector3d;
    }

    @Generated
    public void n_1700_B(float f) {
        this.J_1907_R = f;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof A_3244_K)) {
            return false;
        }
        A_3244_K other = (A_3244_K)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        e_2866_D this$vector3d = this.J_1907_R();
        e_2866_D other$vector3d = other.J_1907_R();
        return !(this$vector3d == null ? other$vector3d != null : !((Object)this$vector3d).equals(other$vector3d));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof A_3244_K;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        e_2866_D $vector3d = this.J_1907_R();
        result = result * 59 + ($vector3d == null ? 43 : ((Object)$vector3d).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventElytraFlying(vector3d=" + String.valueOf(this.J_1907_R()) + ", f=" + this.R_4764_Y() + ")";
    }

    @Generated
    public A_3244_K(e_2866_D vector3d, float f) {
        this.n_1700_B = vector3d;
        this.J_1907_R = f;
    }
}

