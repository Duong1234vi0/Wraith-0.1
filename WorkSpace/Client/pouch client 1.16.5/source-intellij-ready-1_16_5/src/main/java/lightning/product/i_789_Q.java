/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.X_4340_E;
import lightning.product.d_2427_y;
import lightning.product.g_221_o;
import lightning.product.x_1688_C;
import lightning.product.x_607_J;
import lombok.Generated;

public class i_789_Q
extends d_2427_y
implements x_607_J {
    private X_4340_E n_1700_B;
    private float J_1907_R;
    private x_1688_C R_4764_Y;
    private g_221_o G_564_y;

    @Generated
    public X_4340_E J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public float R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public x_1688_C G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public g_221_o P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public void n_1700_B(X_4340_E player) {
        this.n_1700_B = player;
    }

    @Generated
    public void n_1700_B(float swingProgress) {
        this.J_1907_R = swingProgress;
    }

    @Generated
    public void n_1700_B(x_1688_C hand) {
        this.R_4764_Y = hand;
    }

    @Generated
    public void n_1700_B(g_221_o matrixStack) {
        this.G_564_y = matrixStack;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof i_789_Q)) {
            return false;
        }
        i_789_Q other = (i_789_Q)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Float.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        X_4340_E this$player = this.J_1907_R();
        X_4340_E other$player = other.J_1907_R();
        if (this$player == null ? other$player != null : !((Object)this$player).equals(other$player)) {
            return false;
        }
        x_1688_C this$hand = this.G_564_y();
        x_1688_C other$hand = other.G_564_y();
        if (this$hand == null ? other$hand != null : !((Object)((Object)this$hand)).equals((Object)other$hand)) {
            return false;
        }
        g_221_o this$matrixStack = this.P_1922_E();
        g_221_o other$matrixStack = other.P_1922_E();
        return !(this$matrixStack == null ? other$matrixStack != null : !this$matrixStack.equals(other$matrixStack));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof i_789_Q;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.R_4764_Y());
        X_4340_E $player = this.J_1907_R();
        result = result * 59 + ($player == null ? 43 : ((Object)$player).hashCode());
        x_1688_C $hand = this.G_564_y();
        result = result * 59 + ($hand == null ? 43 : ((Object)((Object)$hand)).hashCode());
        g_221_o $matrixStack = this.P_1922_E();
        result = result * 59 + ($matrixStack == null ? 43 : $matrixStack.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventSwingAnimation(player=" + String.valueOf(this.J_1907_R()) + ", swingProgress=" + this.R_4764_Y() + ", hand=" + String.valueOf((Object)this.G_564_y()) + ", matrixStack=" + String.valueOf(this.P_1922_E()) + ")";
    }

    @Generated
    public i_789_Q(X_4340_E player, float swingProgress, x_1688_C hand, g_221_o matrixStack) {
        this.n_1700_B = player;
        this.J_1907_R = swingProgress;
        this.R_4764_Y = hand;
        this.G_564_y = matrixStack;
    }
}

