/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.Z_1993_T;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class O_2761_o
extends d_2427_y
implements x_607_J {
    private Z_1993_T n_1700_B;
    private int J_1907_R;

    @Generated
    public Z_1993_T J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public int R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(Z_1993_T itemStack) {
        this.n_1700_B = itemStack;
    }

    @Generated
    public void n_1700_B(int cooldownTicks) {
        this.J_1907_R = cooldownTicks;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof O_2761_o)) {
            return false;
        }
        O_2761_o other = (O_2761_o)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.R_4764_Y() != other.R_4764_Y()) {
            return false;
        }
        Z_1993_T this$itemStack = this.J_1907_R();
        Z_1993_T other$itemStack = other.J_1907_R();
        return !(this$itemStack == null ? other$itemStack != null : !this$itemStack.equals(other$itemStack));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof O_2761_o;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.R_4764_Y();
        Z_1993_T $itemStack = this.J_1907_R();
        result = result * 59 + ($itemStack == null ? 43 : $itemStack.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventUseEnderPearl(itemStack=" + String.valueOf(this.J_1907_R()) + ", cooldownTicks=" + this.R_4764_Y() + ")";
    }

    @Generated
    public O_2761_o(Z_1993_T itemStack, int cooldownTicks) {
        this.n_1700_B = itemStack;
        this.J_1907_R = cooldownTicks;
    }
}

