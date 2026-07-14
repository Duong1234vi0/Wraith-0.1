/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class h_2739_B
extends d_2427_y
implements x_607_J {
    private N_4263_v n_1700_B;

    @Generated
    public N_4263_v J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(N_4263_v target) {
        this.n_1700_B = target;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof h_2739_B)) {
            return false;
        }
        h_2739_B other = (h_2739_B)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        N_4263_v this$target = this.J_1907_R();
        N_4263_v other$target = other.J_1907_R();
        return !(this$target == null ? other$target != null : !((Object)this$target).equals(other$target));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof h_2739_B;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        N_4263_v $target = this.J_1907_R();
        result = result * 59 + ($target == null ? 43 : ((Object)$target).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventAttack(target=" + String.valueOf(this.J_1907_R()) + ")";
    }

    @Generated
    public h_2739_B(N_4263_v target) {
        this.n_1700_B = target;
    }
}

