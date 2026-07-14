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

public class x_92_N
extends d_2427_y
implements x_607_J {
    private N_4263_v n_1700_B;

    @Generated
    public N_4263_v J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(N_4263_v entity) {
        this.n_1700_B = entity;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof x_92_N)) {
            return false;
        }
        x_92_N other = (x_92_N)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        N_4263_v this$entity = this.J_1907_R();
        N_4263_v other$entity = other.J_1907_R();
        return !(this$entity == null ? other$entity != null : !((Object)this$entity).equals(other$entity));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof x_92_N;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        N_4263_v $entity = this.J_1907_R();
        result = result * 59 + ($entity == null ? 43 : ((Object)$entity).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventAddEntity(entity=" + String.valueOf(this.J_1907_R()) + ")";
    }

    @Generated
    public x_92_N(N_4263_v entity) {
        this.n_1700_B = entity;
    }
}

