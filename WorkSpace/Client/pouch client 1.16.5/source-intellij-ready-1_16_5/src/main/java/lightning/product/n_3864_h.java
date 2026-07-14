/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.a_2900_S;
import lightning.product.d_2427_y;
import lightning.product.g_221_o;
import lightning.product.x_607_J;
import lombok.Generated;

public class n_3864_h
extends d_2427_y
implements x_607_J {
    private g_221_o n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;
    private a_2900_S G_564_y;

    @Generated
    public g_221_o J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public int R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public int G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public a_2900_S P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public void n_1700_B(g_221_o stack) {
        this.n_1700_B = stack;
    }

    @Generated
    public void n_1700_B(int guiLeft) {
        this.J_1907_R = guiLeft;
    }

    @Generated
    public void J_1907_R(int guiTop) {
        this.R_4764_Y = guiTop;
    }

    @Generated
    public void n_1700_B(a_2900_S container) {
        this.G_564_y = container;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof n_3864_h)) {
            return false;
        }
        n_3864_h other = (n_3864_h)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.R_4764_Y() != other.R_4764_Y()) {
            return false;
        }
        if (this.G_564_y() != other.G_564_y()) {
            return false;
        }
        g_221_o this$stack = this.J_1907_R();
        g_221_o other$stack = other.J_1907_R();
        if (this$stack == null ? other$stack != null : !this$stack.equals(other$stack)) {
            return false;
        }
        a_2900_S this$container = this.P_1922_E();
        a_2900_S other$container = other.P_1922_E();
        return !(this$container == null ? other$container != null : !this$container.equals(other$container));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof n_3864_h;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.R_4764_Y();
        result = result * 59 + this.G_564_y();
        g_221_o $stack = this.J_1907_R();
        result = result * 59 + ($stack == null ? 43 : $stack.hashCode());
        a_2900_S $container = this.P_1922_E();
        result = result * 59 + ($container == null ? 43 : $container.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventContainerRender(stack=" + String.valueOf(this.J_1907_R()) + ", guiLeft=" + this.R_4764_Y() + ", guiTop=" + this.G_564_y() + ", container=" + String.valueOf(this.P_1922_E()) + ")";
    }

    @Generated
    public n_3864_h(g_221_o stack, int guiLeft, int guiTop, a_2900_S container) {
        this.n_1700_B = stack;
        this.J_1907_R = guiLeft;
        this.R_4764_Y = guiTop;
        this.G_564_y = container;
    }

    public static class n_1700_B
    extends n_3864_h {
        public n_1700_B(g_221_o stack, int guiLeft, int guiTop, a_2900_S container) {
            super(stack, guiLeft, guiTop, container);
        }
    }

    public static class J_1907_R
    extends n_3864_h {
        public J_1907_R(g_221_o stack, int guiLeft, int guiTop, a_2900_S container) {
            super(stack, guiLeft, guiTop, container);
        }
    }
}

