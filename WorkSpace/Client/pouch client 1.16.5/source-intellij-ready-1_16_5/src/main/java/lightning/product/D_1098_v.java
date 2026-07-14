/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.nio.FloatBuffer;
import java.util.Random;
import lightning.product.M_1336_P;
import lightning.product.w_3785_E;

public final class D_1098_v {
    public float n_1700_B;
    protected float J_1907_R;
    protected float R_4764_Y;
    protected float G_564_y;
    protected float P_1922_E;
    public float u_1723_Y;
    protected float v_4262_N;
    protected float w_1484_f;
    protected float t_148_a;
    protected float s_956_w;
    protected float u_2550_I;
    protected float M_588_G;
    protected float P_4830_p;
    protected float h_1847_R;
    protected float Q_4569_t;
    protected float M_182_A;

    public D_1098_v() {
    }

    public D_1098_v(D_1098_v matrixIn) {
        this.n_1700_B = matrixIn.n_1700_B;
        this.J_1907_R = matrixIn.J_1907_R;
        this.R_4764_Y = matrixIn.R_4764_Y;
        this.G_564_y = matrixIn.G_564_y;
        this.P_1922_E = matrixIn.P_1922_E;
        this.u_1723_Y = matrixIn.u_1723_Y;
        this.v_4262_N = matrixIn.v_4262_N;
        this.w_1484_f = matrixIn.w_1484_f;
        this.t_148_a = matrixIn.t_148_a;
        this.s_956_w = matrixIn.s_956_w;
        this.u_2550_I = matrixIn.u_2550_I;
        this.M_588_G = matrixIn.M_588_G;
        this.P_4830_p = matrixIn.P_4830_p;
        this.h_1847_R = matrixIn.h_1847_R;
        this.Q_4569_t = matrixIn.Q_4569_t;
        this.M_182_A = matrixIn.M_182_A;
    }

    public D_1098_v(w_3785_E quaternionIn) {
        float f = quaternionIn.n_1700_B();
        float f1 = quaternionIn.J_1907_R();
        float f2 = quaternionIn.R_4764_Y();
        float f3 = quaternionIn.G_564_y();
        float f4 = 2.0f * f * f;
        float f5 = 2.0f * f1 * f1;
        float f6 = 2.0f * f2 * f2;
        this.n_1700_B = 1.0f - f5 - f6;
        this.u_1723_Y = 1.0f - f6 - f4;
        this.u_2550_I = 1.0f - f4 - f5;
        this.M_182_A = 1.0f;
        float f7 = f * f1;
        float f8 = f1 * f2;
        float f9 = f2 * f;
        float f10 = f * f3;
        float f11 = f1 * f3;
        float f12 = f2 * f3;
        this.P_1922_E = 2.0f * (f7 + f12);
        this.J_1907_R = 2.0f * (f7 - f12);
        this.t_148_a = 2.0f * (f9 - f11);
        this.R_4764_Y = 2.0f * (f9 + f11);
        this.s_956_w = 2.0f * (f8 + f10);
        this.v_4262_N = 2.0f * (f8 - f10);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            D_1098_v matrix4f = (D_1098_v)p_equals_1_;
            return Float.compare(matrix4f.n_1700_B, this.n_1700_B) == 0 && Float.compare(matrix4f.J_1907_R, this.J_1907_R) == 0 && Float.compare(matrix4f.R_4764_Y, this.R_4764_Y) == 0 && Float.compare(matrix4f.G_564_y, this.G_564_y) == 0 && Float.compare(matrix4f.P_1922_E, this.P_1922_E) == 0 && Float.compare(matrix4f.u_1723_Y, this.u_1723_Y) == 0 && Float.compare(matrix4f.v_4262_N, this.v_4262_N) == 0 && Float.compare(matrix4f.w_1484_f, this.w_1484_f) == 0 && Float.compare(matrix4f.t_148_a, this.t_148_a) == 0 && Float.compare(matrix4f.s_956_w, this.s_956_w) == 0 && Float.compare(matrix4f.u_2550_I, this.u_2550_I) == 0 && Float.compare(matrix4f.M_588_G, this.M_588_G) == 0 && Float.compare(matrix4f.P_4830_p, this.P_4830_p) == 0 && Float.compare(matrix4f.h_1847_R, this.h_1847_R) == 0 && Float.compare(matrix4f.Q_4569_t, this.Q_4569_t) == 0 && Float.compare(matrix4f.M_182_A, this.M_182_A) == 0;
        }
        return false;
    }

    public int hashCode() {
        int i = this.n_1700_B != 0.0f ? Float.floatToIntBits(this.n_1700_B) : 0;
        i = 31 * i + (this.J_1907_R != 0.0f ? Float.floatToIntBits(this.J_1907_R) : 0);
        i = 31 * i + (this.R_4764_Y != 0.0f ? Float.floatToIntBits(this.R_4764_Y) : 0);
        i = 31 * i + (this.G_564_y != 0.0f ? Float.floatToIntBits(this.G_564_y) : 0);
        i = 31 * i + (this.P_1922_E != 0.0f ? Float.floatToIntBits(this.P_1922_E) : 0);
        i = 31 * i + (this.u_1723_Y != 0.0f ? Float.floatToIntBits(this.u_1723_Y) : 0);
        i = 31 * i + (this.v_4262_N != 0.0f ? Float.floatToIntBits(this.v_4262_N) : 0);
        i = 31 * i + (this.w_1484_f != 0.0f ? Float.floatToIntBits(this.w_1484_f) : 0);
        i = 31 * i + (this.t_148_a != 0.0f ? Float.floatToIntBits(this.t_148_a) : 0);
        i = 31 * i + (this.s_956_w != 0.0f ? Float.floatToIntBits(this.s_956_w) : 0);
        i = 31 * i + (this.u_2550_I != 0.0f ? Float.floatToIntBits(this.u_2550_I) : 0);
        i = 31 * i + (this.M_588_G != 0.0f ? Float.floatToIntBits(this.M_588_G) : 0);
        i = 31 * i + (this.P_4830_p != 0.0f ? Float.floatToIntBits(this.P_4830_p) : 0);
        i = 31 * i + (this.h_1847_R != 0.0f ? Float.floatToIntBits(this.h_1847_R) : 0);
        i = 31 * i + (this.Q_4569_t != 0.0f ? Float.floatToIntBits(this.Q_4569_t) : 0);
        return 31 * i + (this.M_182_A != 0.0f ? Float.floatToIntBits(this.M_182_A) : 0);
    }

    private static int n_1700_B(int p_226594_0_, int p_226594_1_) {
        return p_226594_1_ * 4 + p_226594_0_;
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Matrix4f:\n");
        stringbuilder.append(this.n_1700_B);
        stringbuilder.append(" ");
        stringbuilder.append(this.J_1907_R);
        stringbuilder.append(" ");
        stringbuilder.append(this.R_4764_Y);
        stringbuilder.append(" ");
        stringbuilder.append(this.G_564_y);
        stringbuilder.append("\n");
        stringbuilder.append(this.P_1922_E);
        stringbuilder.append(" ");
        stringbuilder.append(this.u_1723_Y);
        stringbuilder.append(" ");
        stringbuilder.append(this.v_4262_N);
        stringbuilder.append(" ");
        stringbuilder.append(this.w_1484_f);
        stringbuilder.append("\n");
        stringbuilder.append(this.t_148_a);
        stringbuilder.append(" ");
        stringbuilder.append(this.s_956_w);
        stringbuilder.append(" ");
        stringbuilder.append(this.u_2550_I);
        stringbuilder.append(" ");
        stringbuilder.append(this.M_588_G);
        stringbuilder.append("\n");
        stringbuilder.append(this.P_4830_p);
        stringbuilder.append(" ");
        stringbuilder.append(this.h_1847_R);
        stringbuilder.append(" ");
        stringbuilder.append(this.Q_4569_t);
        stringbuilder.append(" ");
        stringbuilder.append(this.M_182_A);
        stringbuilder.append("\n");
        return stringbuilder.toString();
    }

    public void n_1700_B(FloatBuffer floatBufferIn) {
        floatBufferIn.put(D_1098_v.n_1700_B(0, 0), this.n_1700_B);
        floatBufferIn.put(D_1098_v.n_1700_B(0, 1), this.J_1907_R);
        floatBufferIn.put(D_1098_v.n_1700_B(0, 2), this.R_4764_Y);
        floatBufferIn.put(D_1098_v.n_1700_B(0, 3), this.G_564_y);
        floatBufferIn.put(D_1098_v.n_1700_B(1, 0), this.P_1922_E);
        floatBufferIn.put(D_1098_v.n_1700_B(1, 1), this.u_1723_Y);
        floatBufferIn.put(D_1098_v.n_1700_B(1, 2), this.v_4262_N);
        floatBufferIn.put(D_1098_v.n_1700_B(1, 3), this.w_1484_f);
        floatBufferIn.put(D_1098_v.n_1700_B(2, 0), this.t_148_a);
        floatBufferIn.put(D_1098_v.n_1700_B(2, 1), this.s_956_w);
        floatBufferIn.put(D_1098_v.n_1700_B(2, 2), this.u_2550_I);
        floatBufferIn.put(D_1098_v.n_1700_B(2, 3), this.M_588_G);
        floatBufferIn.put(D_1098_v.n_1700_B(3, 0), this.P_4830_p);
        floatBufferIn.put(D_1098_v.n_1700_B(3, 1), this.h_1847_R);
        floatBufferIn.put(D_1098_v.n_1700_B(3, 2), this.Q_4569_t);
        floatBufferIn.put(D_1098_v.n_1700_B(3, 3), this.M_182_A);
    }

    public void n_1700_B() {
        this.n_1700_B = 1.0f;
        this.J_1907_R = 0.0f;
        this.R_4764_Y = 0.0f;
        this.G_564_y = 0.0f;
        this.P_1922_E = 0.0f;
        this.u_1723_Y = 1.0f;
        this.v_4262_N = 0.0f;
        this.w_1484_f = 0.0f;
        this.t_148_a = 0.0f;
        this.s_956_w = 0.0f;
        this.u_2550_I = 1.0f;
        this.M_588_G = 0.0f;
        this.P_4830_p = 0.0f;
        this.h_1847_R = 0.0f;
        this.Q_4569_t = 0.0f;
        this.M_182_A = 1.0f;
    }

    public boolean J_1907_R() {
        return this.n_1700_B == 1.0f && this.J_1907_R == 0.0f && this.R_4764_Y == 0.0f && this.G_564_y == 0.0f && this.P_1922_E == 0.0f && this.u_1723_Y == 1.0f && this.v_4262_N == 0.0f && this.w_1484_f == 0.0f && this.t_148_a == 0.0f && this.s_956_w == 0.0f && this.u_2550_I == 1.0f && this.M_588_G == 0.0f && this.P_4830_p == 0.0f && this.h_1847_R == 0.0f && this.Q_4569_t == 0.0f && this.M_182_A == 1.0f;
    }

    public float R_4764_Y() {
        float f = this.n_1700_B * this.u_1723_Y - this.J_1907_R * this.P_1922_E;
        float f1 = this.n_1700_B * this.v_4262_N - this.R_4764_Y * this.P_1922_E;
        float f2 = this.n_1700_B * this.w_1484_f - this.G_564_y * this.P_1922_E;
        float f3 = this.J_1907_R * this.v_4262_N - this.R_4764_Y * this.u_1723_Y;
        float f4 = this.J_1907_R * this.w_1484_f - this.G_564_y * this.u_1723_Y;
        float f5 = this.R_4764_Y * this.w_1484_f - this.G_564_y * this.v_4262_N;
        float f6 = this.t_148_a * this.h_1847_R - this.s_956_w * this.P_4830_p;
        float f7 = this.t_148_a * this.Q_4569_t - this.u_2550_I * this.P_4830_p;
        float f8 = this.t_148_a * this.M_182_A - this.M_588_G * this.P_4830_p;
        float f9 = this.s_956_w * this.Q_4569_t - this.u_2550_I * this.h_1847_R;
        float f10 = this.s_956_w * this.M_182_A - this.M_588_G * this.h_1847_R;
        float f11 = this.u_2550_I * this.M_182_A - this.M_588_G * this.Q_4569_t;
        float f12 = this.u_1723_Y * f11 - this.v_4262_N * f10 + this.w_1484_f * f9;
        float f13 = -this.P_1922_E * f11 + this.v_4262_N * f8 - this.w_1484_f * f7;
        float f14 = this.P_1922_E * f10 - this.u_1723_Y * f8 + this.w_1484_f * f6;
        float f15 = -this.P_1922_E * f9 + this.u_1723_Y * f7 - this.v_4262_N * f6;
        float f16 = -this.J_1907_R * f11 + this.R_4764_Y * f10 - this.G_564_y * f9;
        float f17 = this.n_1700_B * f11 - this.R_4764_Y * f8 + this.G_564_y * f7;
        float f18 = -this.n_1700_B * f10 + this.J_1907_R * f8 - this.G_564_y * f6;
        float f19 = this.n_1700_B * f9 - this.J_1907_R * f7 + this.R_4764_Y * f6;
        float f20 = this.h_1847_R * f5 - this.Q_4569_t * f4 + this.M_182_A * f3;
        float f21 = -this.P_4830_p * f5 + this.Q_4569_t * f2 - this.M_182_A * f1;
        float f22 = this.P_4830_p * f4 - this.h_1847_R * f2 + this.M_182_A * f;
        float f23 = -this.P_4830_p * f3 + this.h_1847_R * f1 - this.Q_4569_t * f;
        float f24 = -this.s_956_w * f5 + this.u_2550_I * f4 - this.M_588_G * f3;
        float f25 = this.t_148_a * f5 - this.u_2550_I * f2 + this.M_588_G * f1;
        float f26 = -this.t_148_a * f4 + this.s_956_w * f2 - this.M_588_G * f;
        float f27 = this.t_148_a * f3 - this.s_956_w * f1 + this.u_2550_I * f;
        this.n_1700_B = f12;
        this.P_1922_E = f13;
        this.t_148_a = f14;
        this.P_4830_p = f15;
        this.J_1907_R = f16;
        this.u_1723_Y = f17;
        this.s_956_w = f18;
        this.h_1847_R = f19;
        this.R_4764_Y = f20;
        this.v_4262_N = f21;
        this.u_2550_I = f22;
        this.Q_4569_t = f23;
        this.G_564_y = f24;
        this.w_1484_f = f25;
        this.M_588_G = f26;
        this.M_182_A = f27;
        return f * f11 - f1 * f10 + f2 * f9 + f3 * f8 - f4 * f7 + f5 * f6;
    }

    public void G_564_y() {
        float f = this.P_1922_E;
        this.P_1922_E = this.J_1907_R;
        this.J_1907_R = f;
        f = this.t_148_a;
        this.t_148_a = this.R_4764_Y;
        this.R_4764_Y = f;
        f = this.s_956_w;
        this.s_956_w = this.v_4262_N;
        this.v_4262_N = f;
        f = this.P_4830_p;
        this.P_4830_p = this.G_564_y;
        this.G_564_y = f;
        f = this.h_1847_R;
        this.h_1847_R = this.w_1484_f;
        this.w_1484_f = f;
        f = this.Q_4569_t;
        this.Q_4569_t = this.M_588_G;
        this.M_588_G = f;
    }

    public boolean P_1922_E() {
        float f = this.R_4764_Y();
        if (Math.abs(f) > 1.0E-6f) {
            this.n_1700_B(f);
            return true;
        }
        return false;
    }

    public void n_1700_B(D_1098_v matrix) {
        float f = this.n_1700_B * matrix.n_1700_B + this.J_1907_R * matrix.P_1922_E + this.R_4764_Y * matrix.t_148_a + this.G_564_y * matrix.P_4830_p;
        float f1 = this.n_1700_B * matrix.J_1907_R + this.J_1907_R * matrix.u_1723_Y + this.R_4764_Y * matrix.s_956_w + this.G_564_y * matrix.h_1847_R;
        float f2 = this.n_1700_B * matrix.R_4764_Y + this.J_1907_R * matrix.v_4262_N + this.R_4764_Y * matrix.u_2550_I + this.G_564_y * matrix.Q_4569_t;
        float f3 = this.n_1700_B * matrix.G_564_y + this.J_1907_R * matrix.w_1484_f + this.R_4764_Y * matrix.M_588_G + this.G_564_y * matrix.M_182_A;
        float f4 = this.P_1922_E * matrix.n_1700_B + this.u_1723_Y * matrix.P_1922_E + this.v_4262_N * matrix.t_148_a + this.w_1484_f * matrix.P_4830_p;
        float f5 = this.P_1922_E * matrix.J_1907_R + this.u_1723_Y * matrix.u_1723_Y + this.v_4262_N * matrix.s_956_w + this.w_1484_f * matrix.h_1847_R;
        float f6 = this.P_1922_E * matrix.R_4764_Y + this.u_1723_Y * matrix.v_4262_N + this.v_4262_N * matrix.u_2550_I + this.w_1484_f * matrix.Q_4569_t;
        float f7 = this.P_1922_E * matrix.G_564_y + this.u_1723_Y * matrix.w_1484_f + this.v_4262_N * matrix.M_588_G + this.w_1484_f * matrix.M_182_A;
        float f8 = this.t_148_a * matrix.n_1700_B + this.s_956_w * matrix.P_1922_E + this.u_2550_I * matrix.t_148_a + this.M_588_G * matrix.P_4830_p;
        float f9 = this.t_148_a * matrix.J_1907_R + this.s_956_w * matrix.u_1723_Y + this.u_2550_I * matrix.s_956_w + this.M_588_G * matrix.h_1847_R;
        float f10 = this.t_148_a * matrix.R_4764_Y + this.s_956_w * matrix.v_4262_N + this.u_2550_I * matrix.u_2550_I + this.M_588_G * matrix.Q_4569_t;
        float f11 = this.t_148_a * matrix.G_564_y + this.s_956_w * matrix.w_1484_f + this.u_2550_I * matrix.M_588_G + this.M_588_G * matrix.M_182_A;
        float f12 = this.P_4830_p * matrix.n_1700_B + this.h_1847_R * matrix.P_1922_E + this.Q_4569_t * matrix.t_148_a + this.M_182_A * matrix.P_4830_p;
        float f13 = this.P_4830_p * matrix.J_1907_R + this.h_1847_R * matrix.u_1723_Y + this.Q_4569_t * matrix.s_956_w + this.M_182_A * matrix.h_1847_R;
        float f14 = this.P_4830_p * matrix.R_4764_Y + this.h_1847_R * matrix.v_4262_N + this.Q_4569_t * matrix.u_2550_I + this.M_182_A * matrix.Q_4569_t;
        float f15 = this.P_4830_p * matrix.G_564_y + this.h_1847_R * matrix.w_1484_f + this.Q_4569_t * matrix.M_588_G + this.M_182_A * matrix.M_182_A;
        this.n_1700_B = f;
        this.J_1907_R = f1;
        this.R_4764_Y = f2;
        this.G_564_y = f3;
        this.P_1922_E = f4;
        this.u_1723_Y = f5;
        this.v_4262_N = f6;
        this.w_1484_f = f7;
        this.t_148_a = f8;
        this.s_956_w = f9;
        this.u_2550_I = f10;
        this.M_588_G = f11;
        this.P_4830_p = f12;
        this.h_1847_R = f13;
        this.Q_4569_t = f14;
        this.M_182_A = f15;
    }

    public void n_1700_B(w_3785_E quaternion) {
        float f = quaternion.n_1700_B();
        float f1 = quaternion.J_1907_R();
        float f2 = quaternion.R_4764_Y();
        float f3 = quaternion.G_564_y();
        float f4 = 2.0f * f * f;
        float f5 = 2.0f * f1 * f1;
        float f6 = 2.0f * f2 * f2;
        float f7 = f * f1;
        float f8 = f1 * f2;
        float f9 = f2 * f;
        float f10 = f * f3;
        float f11 = f1 * f3;
        float f12 = f2 * f3;
        float f13 = 1.0f - f5 - f6;
        float f14 = 2.0f * (f7 - f12);
        float f15 = 2.0f * (f9 + f11);
        float f16 = 0.0f;
        float f17 = 2.0f * (f7 + f12);
        float f18 = 1.0f - f6 - f4;
        float f19 = 2.0f * (f8 - f10);
        float f20 = 0.0f;
        float f21 = 2.0f * (f9 - f11);
        float f22 = 2.0f * (f8 + f10);
        float f23 = 1.0f - f4 - f5;
        float f24 = 0.0f;
        float f25 = 0.0f;
        float f26 = 0.0f;
        float f27 = 0.0f;
        float f28 = 1.0f;
        float f29 = this.n_1700_B * f13 + this.J_1907_R * f17 + this.R_4764_Y * f21 + this.G_564_y * f25;
        float f30 = this.n_1700_B * f14 + this.J_1907_R * f18 + this.R_4764_Y * f22 + this.G_564_y * f26;
        float f31 = this.n_1700_B * f15 + this.J_1907_R * f19 + this.R_4764_Y * f23 + this.G_564_y * f27;
        float f32 = this.n_1700_B * f16 + this.J_1907_R * f20 + this.R_4764_Y * f24 + this.G_564_y * f28;
        float f33 = this.P_1922_E * f13 + this.u_1723_Y * f17 + this.v_4262_N * f21 + this.w_1484_f * f25;
        float f34 = this.P_1922_E * f14 + this.u_1723_Y * f18 + this.v_4262_N * f22 + this.w_1484_f * f26;
        float f35 = this.P_1922_E * f15 + this.u_1723_Y * f19 + this.v_4262_N * f23 + this.w_1484_f * f27;
        float f36 = this.P_1922_E * f16 + this.u_1723_Y * f20 + this.v_4262_N * f24 + this.w_1484_f * f28;
        float f37 = this.t_148_a * f13 + this.s_956_w * f17 + this.u_2550_I * f21 + this.M_588_G * f25;
        float f38 = this.t_148_a * f14 + this.s_956_w * f18 + this.u_2550_I * f22 + this.M_588_G * f26;
        float f39 = this.t_148_a * f15 + this.s_956_w * f19 + this.u_2550_I * f23 + this.M_588_G * f27;
        float f40 = this.t_148_a * f16 + this.s_956_w * f20 + this.u_2550_I * f24 + this.M_588_G * f28;
        float f41 = this.P_4830_p * f13 + this.h_1847_R * f17 + this.Q_4569_t * f21 + this.M_182_A * f25;
        float f42 = this.P_4830_p * f14 + this.h_1847_R * f18 + this.Q_4569_t * f22 + this.M_182_A * f26;
        float f43 = this.P_4830_p * f15 + this.h_1847_R * f19 + this.Q_4569_t * f23 + this.M_182_A * f27;
        float f44 = this.P_4830_p * f16 + this.h_1847_R * f20 + this.Q_4569_t * f24 + this.M_182_A * f28;
        this.n_1700_B = f29;
        this.J_1907_R = f30;
        this.R_4764_Y = f31;
        this.G_564_y = f32;
        this.P_1922_E = f33;
        this.u_1723_Y = f34;
        this.v_4262_N = f35;
        this.w_1484_f = f36;
        this.t_148_a = f37;
        this.s_956_w = f38;
        this.u_2550_I = f39;
        this.M_588_G = f40;
        this.P_4830_p = f41;
        this.h_1847_R = f42;
        this.Q_4569_t = f43;
        this.M_182_A = f44;
    }

    public void n_1700_B(float scale) {
        this.n_1700_B *= scale;
        this.J_1907_R *= scale;
        this.R_4764_Y *= scale;
        this.G_564_y *= scale;
        this.P_1922_E *= scale;
        this.u_1723_Y *= scale;
        this.v_4262_N *= scale;
        this.w_1484_f *= scale;
        this.t_148_a *= scale;
        this.s_956_w *= scale;
        this.u_2550_I *= scale;
        this.M_588_G *= scale;
        this.P_4830_p *= scale;
        this.h_1847_R *= scale;
        this.Q_4569_t *= scale;
        this.M_182_A *= scale;
    }

    public static D_1098_v n_1700_B(double fov, float aspectRatio, float nearPlane, float farPlane) {
        float f = (float)(1.0 / Math.tan(fov * 0.01745329238474369 / 2.0));
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B = f / aspectRatio;
        matrix4f.u_1723_Y = f;
        matrix4f.u_2550_I = (farPlane + nearPlane) / (nearPlane - farPlane);
        matrix4f.Q_4569_t = -1.0f;
        matrix4f.M_588_G = 2.0f * farPlane * nearPlane / (nearPlane - farPlane);
        return matrix4f;
    }

    public static D_1098_v n_1700_B(float width, float height, float nearPlane, float farPlane) {
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B = 2.0f / width;
        matrix4f.u_1723_Y = 2.0f / height;
        float f = farPlane - nearPlane;
        matrix4f.u_2550_I = -2.0f / f;
        matrix4f.M_182_A = 1.0f;
        matrix4f.G_564_y = -1.0f;
        matrix4f.w_1484_f = -1.0f;
        matrix4f.M_588_G = -(farPlane + nearPlane) / f;
        return matrix4f;
    }

    public void n_1700_B(M_1336_P vector) {
        this.G_564_y += vector.n_1700_B();
        this.w_1484_f += vector.J_1907_R();
        this.M_588_G += vector.R_4764_Y();
    }

    public D_1098_v u_1723_Y() {
        return new D_1098_v(this);
    }

    public static D_1098_v n_1700_B(float p_226593_0_, float p_226593_1_, float p_226593_2_) {
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B = p_226593_0_;
        matrix4f.u_1723_Y = p_226593_1_;
        matrix4f.u_2550_I = p_226593_2_;
        matrix4f.M_182_A = 1.0f;
        return matrix4f;
    }

    public static D_1098_v J_1907_R(float p_226599_0_, float p_226599_1_, float p_226599_2_) {
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B = 1.0f;
        matrix4f.u_1723_Y = 1.0f;
        matrix4f.u_2550_I = 1.0f;
        matrix4f.M_182_A = 1.0f;
        matrix4f.G_564_y = p_226599_0_;
        matrix4f.w_1484_f = p_226599_1_;
        matrix4f.M_588_G = p_226599_2_;
        return matrix4f;
    }

    public float J_1907_R(float p_getTransformX_1_, float p_getTransformX_2_, float p_getTransformX_3_, float p_getTransformX_4_) {
        return this.n_1700_B * p_getTransformX_1_ + this.J_1907_R * p_getTransformX_2_ + this.R_4764_Y * p_getTransformX_3_ + this.G_564_y * p_getTransformX_4_;
    }

    public float R_4764_Y(float p_getTransformY_1_, float p_getTransformY_2_, float p_getTransformY_3_, float p_getTransformY_4_) {
        return this.P_1922_E * p_getTransformY_1_ + this.u_1723_Y * p_getTransformY_2_ + this.v_4262_N * p_getTransformY_3_ + this.w_1484_f * p_getTransformY_4_;
    }

    public float G_564_y(float p_getTransformZ_1_, float p_getTransformZ_2_, float p_getTransformZ_3_, float p_getTransformZ_4_) {
        return this.t_148_a * p_getTransformZ_1_ + this.s_956_w * p_getTransformZ_2_ + this.u_2550_I * p_getTransformZ_3_ + this.M_588_G * p_getTransformZ_4_;
    }

    public float P_1922_E(float p_getTransformW_1_, float p_getTransformW_2_, float p_getTransformW_3_, float p_getTransformW_4_) {
        return this.P_4830_p * p_getTransformW_1_ + this.h_1847_R * p_getTransformW_2_ + this.Q_4569_t * p_getTransformW_3_ + this.M_182_A * p_getTransformW_4_;
    }

    public void R_4764_Y(float p_mulTranslate_1_, float p_mulTranslate_2_, float p_mulTranslate_3_) {
        this.G_564_y += this.n_1700_B * p_mulTranslate_1_ + this.J_1907_R * p_mulTranslate_2_ + this.R_4764_Y * p_mulTranslate_3_;
        this.w_1484_f += this.P_1922_E * p_mulTranslate_1_ + this.u_1723_Y * p_mulTranslate_2_ + this.v_4262_N * p_mulTranslate_3_;
        this.M_588_G += this.t_148_a * p_mulTranslate_1_ + this.s_956_w * p_mulTranslate_2_ + this.u_2550_I * p_mulTranslate_3_;
        this.M_182_A += this.P_4830_p * p_mulTranslate_1_ + this.h_1847_R * p_mulTranslate_2_ + this.Q_4569_t * p_mulTranslate_3_;
    }

    public void G_564_y(float p_mulScale_1_, float p_mulScale_2_, float p_mulScale_3_) {
        this.n_1700_B *= p_mulScale_1_;
        this.J_1907_R *= p_mulScale_2_;
        this.R_4764_Y *= p_mulScale_3_;
        this.P_1922_E *= p_mulScale_1_;
        this.u_1723_Y *= p_mulScale_2_;
        this.v_4262_N *= p_mulScale_3_;
        this.t_148_a *= p_mulScale_1_;
        this.s_956_w *= p_mulScale_2_;
        this.u_2550_I *= p_mulScale_3_;
        this.P_4830_p *= p_mulScale_1_;
        this.h_1847_R *= p_mulScale_2_;
        this.Q_4569_t *= p_mulScale_3_;
    }

    public void n_1700_B(Random p_setRandom_1_) {
        this.n_1700_B = p_setRandom_1_.nextFloat();
        this.J_1907_R = p_setRandom_1_.nextFloat();
        this.R_4764_Y = p_setRandom_1_.nextFloat();
        this.G_564_y = p_setRandom_1_.nextFloat();
        this.P_1922_E = p_setRandom_1_.nextFloat();
        this.u_1723_Y = p_setRandom_1_.nextFloat();
        this.v_4262_N = p_setRandom_1_.nextFloat();
        this.w_1484_f = p_setRandom_1_.nextFloat();
        this.t_148_a = p_setRandom_1_.nextFloat();
        this.s_956_w = p_setRandom_1_.nextFloat();
        this.u_2550_I = p_setRandom_1_.nextFloat();
        this.M_588_G = p_setRandom_1_.nextFloat();
        this.P_4830_p = p_setRandom_1_.nextFloat();
        this.h_1847_R = p_setRandom_1_.nextFloat();
        this.Q_4569_t = p_setRandom_1_.nextFloat();
        this.M_182_A = p_setRandom_1_.nextFloat();
    }

    public void n_1700_B(float[] p_write_1_) {
        p_write_1_[0] = this.n_1700_B;
        p_write_1_[1] = this.J_1907_R;
        p_write_1_[2] = this.R_4764_Y;
        p_write_1_[3] = this.G_564_y;
        p_write_1_[4] = this.P_1922_E;
        p_write_1_[5] = this.u_1723_Y;
        p_write_1_[6] = this.v_4262_N;
        p_write_1_[7] = this.w_1484_f;
        p_write_1_[8] = this.t_148_a;
        p_write_1_[9] = this.s_956_w;
        p_write_1_[10] = this.u_2550_I;
        p_write_1_[11] = this.M_588_G;
        p_write_1_[12] = this.P_4830_p;
        p_write_1_[13] = this.h_1847_R;
        p_write_1_[14] = this.Q_4569_t;
        p_write_1_[15] = this.M_182_A;
    }

    public D_1098_v(float[] p_i242105_1_) {
        this.n_1700_B = p_i242105_1_[0];
        this.J_1907_R = p_i242105_1_[1];
        this.R_4764_Y = p_i242105_1_[2];
        this.G_564_y = p_i242105_1_[3];
        this.P_1922_E = p_i242105_1_[4];
        this.u_1723_Y = p_i242105_1_[5];
        this.v_4262_N = p_i242105_1_[6];
        this.w_1484_f = p_i242105_1_[7];
        this.t_148_a = p_i242105_1_[8];
        this.s_956_w = p_i242105_1_[9];
        this.u_2550_I = p_i242105_1_[10];
        this.M_588_G = p_i242105_1_[11];
        this.P_4830_p = p_i242105_1_[12];
        this.h_1847_R = p_i242105_1_[13];
        this.Q_4569_t = p_i242105_1_[14];
        this.M_182_A = p_i242105_1_[15];
    }

    public void J_1907_R(D_1098_v p_set_1_) {
        this.n_1700_B = p_set_1_.n_1700_B;
        this.J_1907_R = p_set_1_.J_1907_R;
        this.R_4764_Y = p_set_1_.R_4764_Y;
        this.G_564_y = p_set_1_.G_564_y;
        this.P_1922_E = p_set_1_.P_1922_E;
        this.u_1723_Y = p_set_1_.u_1723_Y;
        this.v_4262_N = p_set_1_.v_4262_N;
        this.w_1484_f = p_set_1_.w_1484_f;
        this.t_148_a = p_set_1_.t_148_a;
        this.s_956_w = p_set_1_.s_956_w;
        this.u_2550_I = p_set_1_.u_2550_I;
        this.M_588_G = p_set_1_.M_588_G;
        this.P_4830_p = p_set_1_.P_4830_p;
        this.h_1847_R = p_set_1_.h_1847_R;
        this.Q_4569_t = p_set_1_.Q_4569_t;
        this.M_182_A = p_set_1_.M_182_A;
    }

    public void R_4764_Y(D_1098_v p_add_1_) {
        this.n_1700_B += p_add_1_.n_1700_B;
        this.J_1907_R += p_add_1_.J_1907_R;
        this.R_4764_Y += p_add_1_.R_4764_Y;
        this.G_564_y += p_add_1_.G_564_y;
        this.P_1922_E += p_add_1_.P_1922_E;
        this.u_1723_Y += p_add_1_.u_1723_Y;
        this.v_4262_N += p_add_1_.v_4262_N;
        this.w_1484_f += p_add_1_.w_1484_f;
        this.t_148_a += p_add_1_.t_148_a;
        this.s_956_w += p_add_1_.s_956_w;
        this.u_2550_I += p_add_1_.u_2550_I;
        this.M_588_G += p_add_1_.M_588_G;
        this.P_4830_p += p_add_1_.P_4830_p;
        this.h_1847_R += p_add_1_.h_1847_R;
        this.Q_4569_t += p_add_1_.Q_4569_t;
        this.M_182_A += p_add_1_.M_182_A;
    }

    public void G_564_y(D_1098_v p_multiplyBackward_1_) {
        D_1098_v matrix4f = p_multiplyBackward_1_.u_1723_Y();
        matrix4f.n_1700_B(this);
        this.J_1907_R(matrix4f);
    }

    public void P_1922_E(float p_setTranslation_1_, float p_setTranslation_2_, float p_setTranslation_3_) {
        this.n_1700_B = 1.0f;
        this.u_1723_Y = 1.0f;
        this.u_2550_I = 1.0f;
        this.M_182_A = 1.0f;
        this.G_564_y = p_setTranslation_1_;
        this.w_1484_f = p_setTranslation_2_;
        this.M_588_G = p_setTranslation_3_;
    }
}

