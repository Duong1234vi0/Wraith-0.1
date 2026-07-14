/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.x_607_J;
import lombok.Generated;

public class i_4434_b
implements x_607_J {
    private int n_1700_B;
    private boolean J_1907_R;

    @Generated
    public int n_1700_B() {
        return this.n_1700_B;
    }

    @Generated
    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(int key) {
        this.n_1700_B = key;
    }

    @Generated
    public void n_1700_B(boolean hold) {
        this.J_1907_R = hold;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof i_4434_b)) {
            return false;
        }
        i_4434_b other = (i_4434_b)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.n_1700_B() != other.n_1700_B()) {
            return false;
        }
        return this.J_1907_R() == other.J_1907_R();
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof i_4434_b;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.n_1700_B();
        result = result * 59 + (this.J_1907_R() ? 79 : 97);
        return result;
    }

    @Generated
    public String toString() {
        return "EventKey(key=" + this.n_1700_B() + ", hold=" + this.J_1907_R() + ")";
    }

    @Generated
    public i_4434_b(int key, boolean hold) {
        this.n_1700_B = key;
        this.J_1907_R = hold;
    }
}

