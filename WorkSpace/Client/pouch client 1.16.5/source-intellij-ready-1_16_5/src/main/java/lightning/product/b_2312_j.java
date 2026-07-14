/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.e_2866_D;
import lightning.product.x_607_J;
import lombok.Generated;

public class b_2312_j
implements x_607_J {
    private e_2866_D n_1700_B;

    @Generated
    public b_2312_j(e_2866_D velocity) {
        this.n_1700_B = velocity;
    }

    @Generated
    public e_2866_D n_1700_B() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(e_2866_D velocity) {
        this.n_1700_B = velocity;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof b_2312_j)) {
            return false;
        }
        b_2312_j other = (b_2312_j)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        e_2866_D this$velocity = this.n_1700_B();
        e_2866_D other$velocity = other.n_1700_B();
        return !(this$velocity == null ? other$velocity != null : !((Object)this$velocity).equals(other$velocity));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof b_2312_j;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        e_2866_D $velocity = this.n_1700_B();
        result = result * 59 + ($velocity == null ? 43 : ((Object)$velocity).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventFireworkVelocity(velocity=" + String.valueOf(this.n_1700_B()) + ")";
    }
}

