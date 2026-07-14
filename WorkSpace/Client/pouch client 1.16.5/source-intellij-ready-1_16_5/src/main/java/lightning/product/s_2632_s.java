/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_1726_L;
import lightning.product.D_4792_h;
import lightning.product.u_530_F;

public interface s_2632_s
extends D_4792_h {
    public A_1726_L J_1907_R();

    public void R_4764_Y();

    public void n_1700_B(int var1, byte var2);

    public void n_1700_B(int var1, short var2);

    public void n_1700_B(int var1, float var2);

    @Override
    default public D_4792_h pos(double x, double y, double z) {
        if (this.J_1907_R().n_1700_B() != A_1726_L.n_1700_B.n_1700_B) {
            throw new IllegalStateException();
        }
        this.n_1700_B(0, (float)x);
        this.n_1700_B(4, (float)y);
        this.n_1700_B(8, (float)z);
        this.R_4764_Y();
        return this;
    }

    @Override
    default public D_4792_h color(int red, int green, int blue, int alpha) {
        A_1726_L vertexformatelement = this.J_1907_R();
        if (vertexformatelement.J_1907_R() != A_1726_L.J_1907_R.R_4764_Y) {
            return this;
        }
        if (vertexformatelement.n_1700_B() != A_1726_L.n_1700_B.J_1907_R) {
            throw new IllegalStateException();
        }
        this.n_1700_B(0, (byte)red);
        this.n_1700_B(1, (byte)green);
        this.n_1700_B(2, (byte)blue);
        this.n_1700_B(3, (byte)alpha);
        this.R_4764_Y();
        return this;
    }

    @Override
    default public D_4792_h tex(float u, float v) {
        A_1726_L vertexformatelement = this.J_1907_R();
        if (vertexformatelement.J_1907_R() == A_1726_L.J_1907_R.G_564_y && vertexformatelement.R_4764_Y() == 0) {
            if (vertexformatelement.n_1700_B() != A_1726_L.n_1700_B.n_1700_B) {
                throw new IllegalStateException();
            }
            this.n_1700_B(0, u);
            this.n_1700_B(4, v);
            this.R_4764_Y();
            return this;
        }
        return this;
    }

    @Override
    default public D_4792_h overlay(int u, int v) {
        return this.n_1700_B((short)u, (short)v, 1);
    }

    @Override
    default public D_4792_h lightmap(int u, int v) {
        return this.n_1700_B((short)u, (short)v, 2);
    }

    default public D_4792_h n_1700_B(short u, short v, int index) {
        A_1726_L vertexformatelement = this.J_1907_R();
        if (vertexformatelement.J_1907_R() == A_1726_L.J_1907_R.G_564_y && vertexformatelement.R_4764_Y() == index) {
            if (vertexformatelement.n_1700_B() != A_1726_L.n_1700_B.P_1922_E) {
                throw new IllegalStateException();
            }
            this.n_1700_B(0, u);
            this.n_1700_B(2, v);
            this.R_4764_Y();
            return this;
        }
        return this;
    }

    @Override
    default public D_4792_h normal(float x, float y, float z) {
        A_1726_L vertexformatelement = this.J_1907_R();
        if (vertexformatelement.J_1907_R() != A_1726_L.J_1907_R.J_1907_R) {
            return this;
        }
        if (vertexformatelement.n_1700_B() != A_1726_L.n_1700_B.R_4764_Y) {
            throw new IllegalStateException();
        }
        this.n_1700_B(0, s_2632_s.n_1700_B(x));
        this.n_1700_B(1, s_2632_s.n_1700_B(y));
        this.n_1700_B(2, s_2632_s.n_1700_B(z));
        this.R_4764_Y();
        return this;
    }

    public static byte n_1700_B(float num) {
        return (byte)((int)(u_530_F.n_1700_B(num, -1.0f, 1.0f) * 127.0f) & 0xFF);
    }
}

