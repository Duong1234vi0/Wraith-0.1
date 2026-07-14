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

public class T_2971_J
extends d_2427_y
implements x_607_J {
    private final int n_1700_B;
    private final double J_1907_R;
    private final double R_4764_Y;

    @Generated
    public int J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public double R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public double G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof T_2971_J)) {
            return false;
        }
        T_2971_J other = (T_2971_J)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.J_1907_R() != other.J_1907_R()) {
            return false;
        }
        if (Double.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        return Double.compare(this.G_564_y(), other.G_564_y()) == 0;
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof T_2971_J;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.J_1907_R();
        long $mouseX = Double.doubleToLongBits(this.R_4764_Y());
        result = result * 59 + (int)($mouseX >>> 32 ^ $mouseX);
        long $mouseY = Double.doubleToLongBits(this.G_564_y());
        result = result * 59 + (int)($mouseY >>> 32 ^ $mouseY);
        return result;
    }

    @Generated
    public String toString() {
        return "EventMouseReleased(key=" + this.J_1907_R() + ", mouseX=" + this.R_4764_Y() + ", mouseY=" + this.G_564_y() + ")";
    }

    @Generated
    public T_2971_J(int key, double mouseX, double mouseY) {
        this.n_1700_B = key;
        this.J_1907_R = mouseX;
        this.R_4764_Y = mouseY;
    }
}

