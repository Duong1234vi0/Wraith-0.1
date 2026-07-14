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

public class I_4348_c
extends d_2427_y
implements x_607_J {
    private boolean n_1700_B;

    @Generated
    public boolean J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void J_1907_R(boolean isFreecam) {
        this.n_1700_B = isFreecam;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof I_4348_c)) {
            return false;
        }
        I_4348_c other = (I_4348_c)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        return this.J_1907_R() == other.J_1907_R();
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof I_4348_c;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.J_1907_R() ? 79 : 97);
        return result;
    }

    @Generated
    public String toString() {
        return "EventFreeCameraWorldRender(isFreecam=" + this.J_1907_R() + ")";
    }

    @Generated
    public I_4348_c(boolean isFreecam) {
        this.n_1700_B = isFreecam;
    }
}

