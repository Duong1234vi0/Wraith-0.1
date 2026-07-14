/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.r_4811_B;
import lightning.product.x_607_J;
import lombok.Generated;

public class S_4035_N
extends d_2427_y
implements x_607_J {
    private r_4811_B n_1700_B;

    @Generated
    public r_4811_B J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(r_4811_B entity) {
        this.n_1700_B = entity;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof S_4035_N)) {
            return false;
        }
        S_4035_N other = (S_4035_N)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        r_4811_B this$entity = this.J_1907_R();
        r_4811_B other$entity = other.J_1907_R();
        return !(this$entity == null ? other$entity != null : !((Object)this$entity).equals(other$entity));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof S_4035_N;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        r_4811_B $entity = this.J_1907_R();
        result = result * 59 + ($entity == null ? 43 : ((Object)$entity).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventTravel(entity=" + String.valueOf(this.J_1907_R()) + ")";
    }

    @Generated
    public S_4035_N(r_4811_B entity) {
        this.n_1700_B = entity;
    }
}

