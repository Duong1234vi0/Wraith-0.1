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

public class b_3528_u
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
        if (!(o instanceof b_3528_u)) {
            return false;
        }
        b_3528_u other = (b_3528_u)o;
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
        return other instanceof b_3528_u;
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
        return "EventRender2D(Stack=" + String.valueOf(this.J_1907_R()) + ", partialTicks=" + this.R_4764_Y() + ")";
    }

    @Generated
    public b_3528_u(g_221_o Stack, float partialTicks) {
        this.n_1700_B = Stack;
        this.J_1907_R = partialTicks;
    }

    public static class n_1700_B
    extends b_3528_u {
        public n_1700_B(g_221_o stack, float partialTicks) {
            super(stack, partialTicks);
        }
    }

    public static class G_564_y
    extends b_3528_u {
        public G_564_y(g_221_o stack, float partialTicks) {
            super(stack, partialTicks);
        }
    }

    public static class J_1907_R
    extends b_3528_u {
        public J_1907_R(g_221_o stack, float partialTicks) {
            super(stack, partialTicks);
        }
    }

    public static class R_4764_Y
    extends b_3528_u {
        public R_4764_Y(g_221_o stack, float partialTicks) {
            super(stack, partialTicks);
        }
    }
}

