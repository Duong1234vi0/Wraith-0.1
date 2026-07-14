/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class U_532_X
extends d_2427_y
implements x_607_J {
    private N_4263_v n_1700_B;
    private N_4263_v J_1907_R;
    private Z_1993_T R_4764_Y;
    private int G_564_y;

    @Generated
    public N_4263_v J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public N_4263_v R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public Z_1993_T G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public int P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public void n_1700_B(N_4263_v collectedEntity) {
        this.n_1700_B = collectedEntity;
    }

    @Generated
    public void J_1907_R(N_4263_v collector) {
        this.J_1907_R = collector;
    }

    @Generated
    public void n_1700_B(Z_1993_T itemStack) {
        this.R_4764_Y = itemStack;
    }

    @Generated
    public void n_1700_B(int amount) {
        this.G_564_y = amount;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof U_532_X)) {
            return false;
        }
        U_532_X other = (U_532_X)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.P_1922_E() != other.P_1922_E()) {
            return false;
        }
        N_4263_v this$collectedEntity = this.J_1907_R();
        N_4263_v other$collectedEntity = other.J_1907_R();
        if (this$collectedEntity == null ? other$collectedEntity != null : !((Object)this$collectedEntity).equals(other$collectedEntity)) {
            return false;
        }
        N_4263_v this$collector = this.R_4764_Y();
        N_4263_v other$collector = other.R_4764_Y();
        if (this$collector == null ? other$collector != null : !((Object)this$collector).equals(other$collector)) {
            return false;
        }
        Z_1993_T this$itemStack = this.G_564_y();
        Z_1993_T other$itemStack = other.G_564_y();
        return !(this$itemStack == null ? other$itemStack != null : !this$itemStack.equals(other$itemStack));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof U_532_X;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.P_1922_E();
        N_4263_v $collectedEntity = this.J_1907_R();
        result = result * 59 + ($collectedEntity == null ? 43 : ((Object)$collectedEntity).hashCode());
        N_4263_v $collector = this.R_4764_Y();
        result = result * 59 + ($collector == null ? 43 : ((Object)$collector).hashCode());
        Z_1993_T $itemStack = this.G_564_y();
        result = result * 59 + ($itemStack == null ? 43 : $itemStack.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventItemPickup(collectedEntity=" + String.valueOf(this.J_1907_R()) + ", collector=" + String.valueOf(this.R_4764_Y()) + ", itemStack=" + String.valueOf(this.G_564_y()) + ", amount=" + this.P_1922_E() + ")";
    }

    @Generated
    public U_532_X(N_4263_v collectedEntity, N_4263_v collector, Z_1993_T itemStack, int amount) {
        this.n_1700_B = collectedEntity;
        this.J_1907_R = collector;
        this.R_4764_Y = itemStack;
        this.G_564_y = amount;
    }
}

