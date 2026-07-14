/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.g_221_o;
import lightning.product.x_607_J;
import lombok.Generated;

public class l_2647_k
extends d_2427_y
implements x_607_J {
    private g_221_o n_1700_B;
    private float J_1907_R;

    @Generated
    public g_221_o J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public float R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(g_221_o stack) {
        this.n_1700_B = stack;
    }

    @Generated
    public void n_1700_B(float partialTicks) {
        this.J_1907_R = partialTicks;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof l_2647_k)) {
            return false;
        }
        l_2647_k other = (l_2647_k)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        g_221_o this$stack = this.J_1907_R();
        g_221_o other$stack = other.J_1907_R();
        return !(this$stack == null ? other$stack != null : !this$stack.equals(other$stack));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof l_2647_k;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        g_221_o $stack = this.J_1907_R();
        result = result * 59 + ($stack == null ? 43 : $stack.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventHotbarRender(stack=" + String.valueOf(this.J_1907_R()) + ", partialTicks=" + this.R_4764_Y() + ")";
    }

    @Generated
    public l_2647_k(g_221_o stack, float partialTicks) {
        this.n_1700_B = stack;
        this.J_1907_R = partialTicks;
    }
}

