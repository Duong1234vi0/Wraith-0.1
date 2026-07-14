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

public class d_3244_b
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
    public void n_1700_B(g_221_o Stack) {
        this.n_1700_B = Stack;
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
        if (!(o instanceof d_3244_b)) {
            return false;
        }
        d_3244_b other = (d_3244_b)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        g_221_o this$Stack = this.J_1907_R();
        g_221_o other$Stack = other.J_1907_R();
        return !(this$Stack == null ? other$Stack != null : !this$Stack.equals(other$Stack));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof d_3244_b;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        g_221_o $Stack = this.J_1907_R();
        result = result * 59 + ($Stack == null ? 43 : $Stack.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventCrosshair(Stack=" + String.valueOf(this.J_1907_R()) + ", partialTicks=" + this.R_4764_Y() + ")";
    }

    @Generated
    public d_3244_b(g_221_o Stack, float partialTicks) {
        this.n_1700_B = Stack;
        this.J_1907_R = partialTicks;
    }
}

