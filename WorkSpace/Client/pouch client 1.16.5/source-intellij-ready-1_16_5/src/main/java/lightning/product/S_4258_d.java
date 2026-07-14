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

public class S_4258_d
extends d_2427_y
implements x_607_J {
    private final int n_1700_B;
    private final float J_1907_R;
    private final float R_4764_Y;

    @Generated
    public int J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public float R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public float G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof S_4258_d)) {
            return false;
        }
        S_4258_d other = (S_4258_d)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.J_1907_R() != other.J_1907_R()) {
            return false;
        }
        if (Float.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        return Float.compare(this.G_564_y(), other.G_564_y()) == 0;
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof S_4258_d;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.J_1907_R();
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        result = result * 59 + Float.floatToIntBits(this.G_564_y());
        return result;
    }

    @Generated
    public String toString() {
        return "EventMouseClicked(key=" + this.J_1907_R() + ", mouseX=" + this.R_4764_Y() + ", mouseY=" + this.G_564_y() + ")";
    }

    @Generated
    public S_4258_d(int key, float mouseX, float mouseY) {
        this.n_1700_B = key;
        this.J_1907_R = mouseX;
        this.R_4764_Y = mouseY;
    }
}

