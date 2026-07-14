/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.g_221_o;
import lightning.product.h_3572_K;
import lightning.product.x_607_J;
import lombok.Generated;

public class X_2048_Y
extends d_2427_y
implements x_607_J {
    private h_3572_K n_1700_B;
    private g_221_o J_1907_R;
    private float R_4764_Y;

    @Generated
    public h_3572_K J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public g_221_o R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public float G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public void n_1700_B(h_3572_K activeRenderInfo) {
        this.n_1700_B = activeRenderInfo;
    }

    @Generated
    public void n_1700_B(g_221_o stack) {
        this.J_1907_R = stack;
    }

    @Generated
    public void n_1700_B(float part) {
        this.R_4764_Y = part;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof X_2048_Y)) {
            return false;
        }
        X_2048_Y other = (X_2048_Y)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.G_564_y(), other.G_564_y()) != 0) {
            return false;
        }
        h_3572_K this$activeRenderInfo = this.J_1907_R();
        h_3572_K other$activeRenderInfo = other.J_1907_R();
        if (this$activeRenderInfo == null ? other$activeRenderInfo != null : !this$activeRenderInfo.equals(other$activeRenderInfo)) {
            return false;
        }
        g_221_o this$stack = this.R_4764_Y();
        g_221_o other$stack = other.R_4764_Y();
        return !(this$stack == null ? other$stack != null : !this$stack.equals(other$stack));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof X_2048_Y;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.G_564_y());
        h_3572_K $activeRenderInfo = this.J_1907_R();
        result = result * 59 + ($activeRenderInfo == null ? 43 : $activeRenderInfo.hashCode());
        g_221_o $stack = this.R_4764_Y();
        result = result * 59 + ($stack == null ? 43 : $stack.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventHandsRender(activeRenderInfo=" + String.valueOf(this.J_1907_R()) + ", stack=" + String.valueOf(this.R_4764_Y()) + ", part=" + this.G_564_y() + ")";
    }

    @Generated
    public X_2048_Y(h_3572_K activeRenderInfo, g_221_o stack, float part) {
        this.n_1700_B = activeRenderInfo;
        this.J_1907_R = stack;
        this.R_4764_Y = part;
    }

    public static class n_1700_B
    extends X_2048_Y {
        public n_1700_B(h_3572_K activeRenderInfo, g_221_o stack, float part) {
            super(activeRenderInfo, stack, part);
        }
    }

    public static class J_1907_R
    extends X_2048_Y {
        public J_1907_R(h_3572_K activeRenderInfo, g_221_o stack, float part) {
            super(activeRenderInfo, stack, part);
        }
    }
}

