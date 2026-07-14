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

public class n_4539_g
extends d_2427_y
implements x_607_J {
    private boolean n_1700_B;

    @Generated
    public boolean J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void J_1907_R(boolean thirdperson) {
        this.n_1700_B = thirdperson;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof n_4539_g)) {
            return false;
        }
        n_4539_g other = (n_4539_g)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        return this.J_1907_R() == other.J_1907_R();
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof n_4539_g;
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
        return "EventThirdPersonRender(thirdperson=" + this.J_1907_R() + ")";
    }

    @Generated
    public n_4539_g(boolean thirdperson) {
        this.n_1700_B = thirdperson;
    }
}

