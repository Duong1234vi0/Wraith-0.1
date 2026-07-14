/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.g_221_o;
import lightning.product.k_4231_L;
import lightning.product.x_607_J;
import lombok.Generated;

public class E_453_w
extends d_2427_y
implements x_607_J {
    private g_221_o n_1700_B;
    private k_4231_L J_1907_R;

    @Generated
    public g_221_o J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public k_4231_L R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(g_221_o matrixStack) {
        this.n_1700_B = matrixStack;
    }

    @Generated
    public void n_1700_B(k_4231_L handside) {
        this.J_1907_R = handside;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof E_453_w)) {
            return false;
        }
        E_453_w other = (E_453_w)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        g_221_o this$matrixStack = this.J_1907_R();
        g_221_o other$matrixStack = other.J_1907_R();
        if (this$matrixStack == null ? other$matrixStack != null : !this$matrixStack.equals(other$matrixStack)) {
            return false;
        }
        k_4231_L this$handside = this.R_4764_Y();
        k_4231_L other$handside = other.R_4764_Y();
        return !(this$handside == null ? other$handside != null : !((Object)((Object)this$handside)).equals((Object)other$handside));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof E_453_w;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        g_221_o $matrixStack = this.J_1907_R();
        result = result * 59 + ($matrixStack == null ? 43 : $matrixStack.hashCode());
        k_4231_L $handside = this.R_4764_Y();
        result = result * 59 + ($handside == null ? 43 : ((Object)((Object)$handside)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventViewModel(matrixStack=" + String.valueOf(this.J_1907_R()) + ", handside=" + String.valueOf((Object)this.R_4764_Y()) + ")";
    }

    @Generated
    public E_453_w(g_221_o matrixStack, k_4231_L handside) {
        this.n_1700_B = matrixStack;
        this.J_1907_R = handside;
    }
}

