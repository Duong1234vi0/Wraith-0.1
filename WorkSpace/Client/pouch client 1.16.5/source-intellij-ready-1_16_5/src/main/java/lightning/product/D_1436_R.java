/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_1869_h;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.u_530_F;

public class D_1436_R {
    public final int n_1700_B;
    public final int J_1907_R;
    public final int R_4764_Y;
    private final int P_4830_p;
    public int G_564_y = -1;
    public float P_1922_E;
    public float u_1723_Y;
    public float v_4262_N;
    public D_1436_R w_1484_f;
    public boolean t_148_a;
    public float s_956_w;
    public float u_2550_I;
    public I_1869_h M_588_G = I_1869_h.n_1700_B;

    public D_1436_R(int x, int y, int z) {
        this.n_1700_B = x;
        this.J_1907_R = y;
        this.R_4764_Y = z;
        this.P_4830_p = D_1436_R.J_1907_R(x, y, z);
    }

    public D_1436_R n_1700_B(int x, int y, int z) {
        D_1436_R pathpoint = new D_1436_R(x, y, z);
        pathpoint.G_564_y = this.G_564_y;
        pathpoint.P_1922_E = this.P_1922_E;
        pathpoint.u_1723_Y = this.u_1723_Y;
        pathpoint.v_4262_N = this.v_4262_N;
        pathpoint.w_1484_f = this.w_1484_f;
        pathpoint.t_148_a = this.t_148_a;
        pathpoint.s_956_w = this.s_956_w;
        pathpoint.u_2550_I = this.u_2550_I;
        pathpoint.M_588_G = this.M_588_G;
        return pathpoint;
    }

    public static int J_1907_R(int x, int y, int z) {
        return y & 0xFF | (x & Short.MAX_VALUE) << 8 | (z & Short.MAX_VALUE) << 24 | (x < 0 ? Integer.MIN_VALUE : 0) | (z < 0 ? 32768 : 0);
    }

    public float n_1700_B(D_1436_R pathpointIn) {
        float f = pathpointIn.n_1700_B - this.n_1700_B;
        float f1 = pathpointIn.J_1907_R - this.J_1907_R;
        float f2 = pathpointIn.R_4764_Y - this.R_4764_Y;
        return u_530_F.R_4764_Y(f * f + f1 * f1 + f2 * f2);
    }

    public float J_1907_R(D_1436_R pathpointIn) {
        float f = pathpointIn.n_1700_B - this.n_1700_B;
        float f1 = pathpointIn.J_1907_R - this.J_1907_R;
        float f2 = pathpointIn.R_4764_Y - this.R_4764_Y;
        return f * f + f1 * f1 + f2 * f2;
    }

    public float R_4764_Y(D_1436_R p_224757_1_) {
        float f = Math.abs(p_224757_1_.n_1700_B - this.n_1700_B);
        float f1 = Math.abs(p_224757_1_.J_1907_R - this.J_1907_R);
        float f2 = Math.abs(p_224757_1_.R_4764_Y - this.R_4764_Y);
        return f + f1 + f2;
    }

    public float n_1700_B(c_1514_x p_224758_1_) {
        float f = Math.abs(p_224758_1_.getX() - this.n_1700_B);
        float f1 = Math.abs(p_224758_1_.getY() - this.J_1907_R);
        float f2 = Math.abs(p_224758_1_.getZ() - this.R_4764_Y);
        return f + f1 + f2;
    }

    public c_1514_x R_4764_Y() {
        return new c_1514_x(this.n_1700_B, this.J_1907_R, this.R_4764_Y);
    }

    public boolean equals(Object p_equals_1_) {
        if (!(p_equals_1_ instanceof D_1436_R)) {
            return false;
        }
        D_1436_R pathpoint = (D_1436_R)p_equals_1_;
        return this.P_4830_p == pathpoint.P_4830_p && this.n_1700_B == pathpoint.n_1700_B && this.J_1907_R == pathpoint.J_1907_R && this.R_4764_Y == pathpoint.R_4764_Y;
    }

    public int hashCode() {
        return this.P_4830_p;
    }

    public boolean G_564_y() {
        return this.G_564_y >= 0;
    }

    public String toString() {
        return "Node{x=" + this.n_1700_B + ", y=" + this.J_1907_R + ", z=" + this.R_4764_Y + "}";
    }

    public static D_1436_R J_1907_R(b_2585_i buf) {
        D_1436_R pathpoint = new D_1436_R(buf.readInt(), buf.readInt(), buf.readInt());
        pathpoint.s_956_w = buf.readFloat();
        pathpoint.u_2550_I = buf.readFloat();
        pathpoint.t_148_a = buf.readBoolean();
        pathpoint.M_588_G = I_1869_h.values()[buf.readInt()];
        pathpoint.v_4262_N = buf.readFloat();
        return pathpoint;
    }
}

