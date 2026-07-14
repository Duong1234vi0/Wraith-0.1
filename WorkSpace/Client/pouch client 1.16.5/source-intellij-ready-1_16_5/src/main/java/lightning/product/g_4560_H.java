/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.T_2915_h;
import lightning.product.c_1514_x;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class g_4560_H
extends d_2427_y
implements x_607_J {
    private final T_2915_h n_1700_B;
    private final c_1514_x J_1907_R;

    @Generated
    public g_4560_H(T_2915_h block, c_1514_x pos) {
        this.n_1700_B = block;
        this.J_1907_R = pos;
    }

    @Generated
    public T_2915_h J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public c_1514_x R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof g_4560_H)) {
            return false;
        }
        g_4560_H other = (g_4560_H)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        T_2915_h this$block = this.J_1907_R();
        T_2915_h other$block = other.J_1907_R();
        if (this$block == null ? other$block != null : !this$block.equals(other$block)) {
            return false;
        }
        c_1514_x this$pos = this.R_4764_Y();
        c_1514_x other$pos = other.R_4764_Y();
        return !(this$pos == null ? other$pos != null : !((Object)this$pos).equals(other$pos));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof g_4560_H;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        T_2915_h $block = this.J_1907_R();
        result = result * 59 + ($block == null ? 43 : $block.hashCode());
        c_1514_x $pos = this.R_4764_Y();
        result = result * 59 + ($pos == null ? 43 : ((Object)$pos).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventPlaceBlock(block=" + String.valueOf(this.J_1907_R()) + ", pos=" + String.valueOf(this.R_4764_Y()) + ")";
    }
}

