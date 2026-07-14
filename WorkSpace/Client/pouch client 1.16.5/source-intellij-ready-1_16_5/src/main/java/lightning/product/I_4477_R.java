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

public class I_4477_R
extends d_2427_y
implements x_607_J {
    float n_1700_B;

    @Generated
    public float J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(float partialTicks) {
        this.n_1700_B = partialTicks;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof I_4477_R)) {
            return false;
        }
        I_4477_R other = (I_4477_R)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        return Float.compare(this.J_1907_R(), other.J_1907_R()) == 0;
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof I_4477_R;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.J_1907_R());
        return result;
    }

    @Generated
    public String toString() {
        return "EventRender3D(partialTicks=" + this.J_1907_R() + ")";
    }

    @Generated
    public I_4477_R(float partialTicks) {
        this.n_1700_B = partialTicks;
    }
}

