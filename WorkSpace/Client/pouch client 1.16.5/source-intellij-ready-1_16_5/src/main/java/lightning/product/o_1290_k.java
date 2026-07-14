/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  org.apache.commons.lang3.tuple.Triple
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import java.util.Random;
import lightning.product.D_1098_v;
import lightning.product.M_1336_P;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;
import org.apache.commons.lang3.tuple.Triple;

public final class o_1290_k {
    private static final float s_956_w = 3.0f + 2.0f * (float)Math.sqrt(2.0);
    private static final float u_2550_I = (float)Math.cos(0.39269908169872414);
    private static final float M_588_G = (float)Math.sin(0.39269908169872414);
    private static final float P_4830_p = 1.0f / (float)Math.sqrt(2.0);
    protected float n_1700_B;
    protected float J_1907_R;
    protected float R_4764_Y;
    protected float G_564_y;
    protected float P_1922_E;
    protected float u_1723_Y;
    protected float v_4262_N;
    protected float w_1484_f;
    protected float t_148_a;

    public o_1290_k() {
    }

    public o_1290_k(w_3785_E quaternionIn) {
        float f = quaternionIn.n_1700_B();
        float f1 = quaternionIn.J_1907_R();
        float f2 = quaternionIn.R_4764_Y();
        float f3 = quaternionIn.G_564_y();
        float f4 = 2.0f * f * f;
        float f5 = 2.0f * f1 * f1;
        float f6 = 2.0f * f2 * f2;
        this.n_1700_B = 1.0f - f5 - f6;
        this.P_1922_E = 1.0f - f6 - f4;
        this.t_148_a = 1.0f - f4 - f5;
        float f7 = f * f1;
        float f8 = f1 * f2;
        float f9 = f2 * f;
        float f10 = f * f3;
        float f11 = f1 * f3;
        float f12 = f2 * f3;
        this.G_564_y = 2.0f * (f7 + f12);
        this.J_1907_R = 2.0f * (f7 - f12);
        this.v_4262_N = 2.0f * (f9 - f11);
        this.R_4764_Y = 2.0f * (f9 + f11);
        this.w_1484_f = 2.0f * (f8 + f10);
        this.u_1723_Y = 2.0f * (f8 - f10);
    }

    public static o_1290_k n_1700_B(float p_226117_0_, float p_226117_1_, float p_226117_2_) {
        o_1290_k matrix3f = new o_1290_k();
        matrix3f.n_1700_B = p_226117_0_;
        matrix3f.P_1922_E = p_226117_1_;
        matrix3f.t_148_a = p_226117_2_;
        return matrix3f;
    }

    public o_1290_k(D_1098_v matrixIn) {
        this.n_1700_B = matrixIn.n_1700_B;
        this.J_1907_R = matrixIn.J_1907_R;
        this.R_4764_Y = matrixIn.R_4764_Y;
        this.G_564_y = matrixIn.P_1922_E;
        this.P_1922_E = matrixIn.u_1723_Y;
        this.u_1723_Y = matrixIn.v_4262_N;
        this.v_4262_N = matrixIn.t_148_a;
        this.w_1484_f = matrixIn.s_956_w;
        this.t_148_a = matrixIn.u_2550_I;
    }

    public o_1290_k(o_1290_k matrixIn) {
        this.n_1700_B = matrixIn.n_1700_B;
        this.J_1907_R = matrixIn.J_1907_R;
        this.R_4764_Y = matrixIn.R_4764_Y;
        this.G_564_y = matrixIn.G_564_y;
        this.P_1922_E = matrixIn.P_1922_E;
        this.u_1723_Y = matrixIn.u_1723_Y;
        this.v_4262_N = matrixIn.v_4262_N;
        this.w_1484_f = matrixIn.w_1484_f;
        this.t_148_a = matrixIn.t_148_a;
    }

    private static Pair<Float, Float> P_1922_E(float p_226113_0_, float p_226113_1_, float p_226113_2_) {
        float f = 2.0f * (p_226113_0_ - p_226113_2_);
        if (s_956_w * p_226113_1_ * p_226113_1_ < f * f) {
            float f1 = u_530_F.t_148_a(p_226113_1_ * p_226113_1_ + f * f);
            return Pair.of((Object)Float.valueOf(f1 * p_226113_1_), (Object)Float.valueOf(f1 * f));
        }
        return Pair.of((Object)Float.valueOf(M_588_G), (Object)Float.valueOf(u_2550_I));
    }

    private static Pair<Float, Float> n_1700_B(float p_226112_0_, float p_226112_1_) {
        float f = (float)Math.hypot(p_226112_0_, p_226112_1_);
        float f1 = f > 1.0E-6f ? p_226112_1_ : 0.0f;
        float f2 = Math.abs(p_226112_0_) + Math.max(f, 1.0E-6f);
        if (p_226112_0_ < 0.0f) {
            float f3 = f1;
            f1 = f2;
            f2 = f3;
        }
        float f4 = u_530_F.t_148_a(f2 * f2 + f1 * f1);
        return Pair.of((Object)Float.valueOf(f1 *= f4), (Object)Float.valueOf(f2 *= f4));
    }

    private static w_3785_E G_564_y(o_1290_k p_226120_0_) {
        o_1290_k matrix3f = new o_1290_k();
        w_3785_E quaternion = w_3785_E.n_1700_B.v_4262_N();
        if (p_226120_0_.J_1907_R * p_226120_0_.J_1907_R + p_226120_0_.G_564_y * p_226120_0_.G_564_y > 1.0E-6f) {
            Pair<Float, Float> pair = o_1290_k.P_1922_E(p_226120_0_.n_1700_B, 0.5f * (p_226120_0_.J_1907_R + p_226120_0_.G_564_y), p_226120_0_.P_1922_E);
            Float f = (Float)pair.getFirst();
            Float f1 = (Float)pair.getSecond();
            w_3785_E quaternion1 = new w_3785_E(0.0f, 0.0f, f.floatValue(), f1.floatValue());
            float f2 = f1.floatValue() * f1.floatValue() - f.floatValue() * f.floatValue();
            float f3 = -2.0f * f.floatValue() * f1.floatValue();
            float f4 = f1.floatValue() * f1.floatValue() + f.floatValue() * f.floatValue();
            quaternion.n_1700_B(quaternion1);
            matrix3f.R_4764_Y();
            matrix3f.n_1700_B = f2;
            matrix3f.P_1922_E = f2;
            matrix3f.G_564_y = -f3;
            matrix3f.J_1907_R = f3;
            matrix3f.t_148_a = f4;
            p_226120_0_.J_1907_R(matrix3f);
            matrix3f.n_1700_B();
            matrix3f.J_1907_R(p_226120_0_);
            p_226120_0_.n_1700_B(matrix3f);
        }
        if (p_226120_0_.R_4764_Y * p_226120_0_.R_4764_Y + p_226120_0_.v_4262_N * p_226120_0_.v_4262_N > 1.0E-6f) {
            Pair<Float, Float> pair1 = o_1290_k.P_1922_E(p_226120_0_.n_1700_B, 0.5f * (p_226120_0_.R_4764_Y + p_226120_0_.v_4262_N), p_226120_0_.t_148_a);
            float f5 = -((Float)pair1.getFirst()).floatValue();
            Float f7 = (Float)pair1.getSecond();
            w_3785_E quaternion2 = new w_3785_E(0.0f, f5, 0.0f, f7.floatValue());
            float f9 = f7.floatValue() * f7.floatValue() - f5 * f5;
            float f11 = -2.0f * f5 * f7.floatValue();
            float f13 = f7.floatValue() * f7.floatValue() + f5 * f5;
            quaternion.n_1700_B(quaternion2);
            matrix3f.R_4764_Y();
            matrix3f.n_1700_B = f9;
            matrix3f.t_148_a = f9;
            matrix3f.v_4262_N = f11;
            matrix3f.R_4764_Y = -f11;
            matrix3f.P_1922_E = f13;
            p_226120_0_.J_1907_R(matrix3f);
            matrix3f.n_1700_B();
            matrix3f.J_1907_R(p_226120_0_);
            p_226120_0_.n_1700_B(matrix3f);
        }
        if (p_226120_0_.u_1723_Y * p_226120_0_.u_1723_Y + p_226120_0_.w_1484_f * p_226120_0_.w_1484_f > 1.0E-6f) {
            Pair<Float, Float> pair2 = o_1290_k.P_1922_E(p_226120_0_.P_1922_E, 0.5f * (p_226120_0_.u_1723_Y + p_226120_0_.w_1484_f), p_226120_0_.t_148_a);
            Float f6 = (Float)pair2.getFirst();
            Float f8 = (Float)pair2.getSecond();
            w_3785_E quaternion3 = new w_3785_E(f6.floatValue(), 0.0f, 0.0f, f8.floatValue());
            float f10 = f8.floatValue() * f8.floatValue() - f6.floatValue() * f6.floatValue();
            float f12 = -2.0f * f6.floatValue() * f8.floatValue();
            float f14 = f8.floatValue() * f8.floatValue() + f6.floatValue() * f6.floatValue();
            quaternion.n_1700_B(quaternion3);
            matrix3f.R_4764_Y();
            matrix3f.P_1922_E = f10;
            matrix3f.t_148_a = f10;
            matrix3f.w_1484_f = -f12;
            matrix3f.u_1723_Y = f12;
            matrix3f.n_1700_B = f14;
            p_226120_0_.J_1907_R(matrix3f);
            matrix3f.n_1700_B();
            matrix3f.J_1907_R(p_226120_0_);
            p_226120_0_.n_1700_B(matrix3f);
        }
        return quaternion;
    }

    public void n_1700_B() {
        float f = this.J_1907_R;
        this.J_1907_R = this.G_564_y;
        this.G_564_y = f;
        f = this.R_4764_Y;
        this.R_4764_Y = this.v_4262_N;
        this.v_4262_N = f;
        f = this.u_1723_Y;
        this.u_1723_Y = this.w_1484_f;
        this.w_1484_f = f;
    }

    public Triple<w_3785_E, M_1336_P, w_3785_E> J_1907_R() {
        w_3785_E quaternion = w_3785_E.n_1700_B.v_4262_N();
        w_3785_E quaternion1 = w_3785_E.n_1700_B.v_4262_N();
        o_1290_k matrix3f = this.u_1723_Y();
        matrix3f.n_1700_B();
        matrix3f.J_1907_R(this);
        for (int i = 0; i < 5; ++i) {
            quaternion1.n_1700_B(o_1290_k.G_564_y(matrix3f));
        }
        quaternion1.u_1723_Y();
        o_1290_k matrix3f4 = new o_1290_k(this);
        matrix3f4.J_1907_R(new o_1290_k(quaternion1));
        float f = 1.0f;
        Pair<Float, Float> pair = o_1290_k.n_1700_B(matrix3f4.n_1700_B, matrix3f4.G_564_y);
        Float f1 = (Float)pair.getFirst();
        Float f2 = (Float)pair.getSecond();
        float f3 = f2.floatValue() * f2.floatValue() - f1.floatValue() * f1.floatValue();
        float f4 = -2.0f * f1.floatValue() * f2.floatValue();
        float f5 = f2.floatValue() * f2.floatValue() + f1.floatValue() * f1.floatValue();
        w_3785_E quaternion2 = new w_3785_E(0.0f, 0.0f, f1.floatValue(), f2.floatValue());
        quaternion.n_1700_B(quaternion2);
        o_1290_k matrix3f1 = new o_1290_k();
        matrix3f1.R_4764_Y();
        matrix3f1.n_1700_B = f3;
        matrix3f1.P_1922_E = f3;
        matrix3f1.G_564_y = f4;
        matrix3f1.J_1907_R = -f4;
        matrix3f1.t_148_a = f5;
        f *= f5;
        matrix3f1.J_1907_R(matrix3f4);
        pair = o_1290_k.n_1700_B(matrix3f1.n_1700_B, matrix3f1.v_4262_N);
        float f6 = -((Float)pair.getFirst()).floatValue();
        Float f7 = (Float)pair.getSecond();
        float f8 = f7.floatValue() * f7.floatValue() - f6 * f6;
        float f9 = -2.0f * f6 * f7.floatValue();
        float f10 = f7.floatValue() * f7.floatValue() + f6 * f6;
        w_3785_E quaternion3 = new w_3785_E(0.0f, f6, 0.0f, f7.floatValue());
        quaternion.n_1700_B(quaternion3);
        o_1290_k matrix3f2 = new o_1290_k();
        matrix3f2.R_4764_Y();
        matrix3f2.n_1700_B = f8;
        matrix3f2.t_148_a = f8;
        matrix3f2.v_4262_N = -f9;
        matrix3f2.R_4764_Y = f9;
        matrix3f2.P_1922_E = f10;
        f *= f10;
        matrix3f2.J_1907_R(matrix3f1);
        pair = o_1290_k.n_1700_B(matrix3f2.P_1922_E, matrix3f2.w_1484_f);
        Float f11 = (Float)pair.getFirst();
        Float f12 = (Float)pair.getSecond();
        float f13 = f12.floatValue() * f12.floatValue() - f11.floatValue() * f11.floatValue();
        float f14 = -2.0f * f11.floatValue() * f12.floatValue();
        float f15 = f12.floatValue() * f12.floatValue() + f11.floatValue() * f11.floatValue();
        w_3785_E quaternion4 = new w_3785_E(f11.floatValue(), 0.0f, 0.0f, f12.floatValue());
        quaternion.n_1700_B(quaternion4);
        o_1290_k matrix3f3 = new o_1290_k();
        matrix3f3.R_4764_Y();
        matrix3f3.P_1922_E = f13;
        matrix3f3.t_148_a = f13;
        matrix3f3.w_1484_f = f14;
        matrix3f3.u_1723_Y = -f14;
        matrix3f3.n_1700_B = f15;
        f *= f15;
        matrix3f3.J_1907_R(matrix3f2);
        f = 1.0f / f;
        quaternion.n_1700_B((float)Math.sqrt(f));
        M_1336_P vector3f = new M_1336_P(matrix3f3.n_1700_B * f, matrix3f3.P_1922_E * f, matrix3f3.t_148_a * f);
        return Triple.of((Object)quaternion, (Object)vector3f, (Object)quaternion1);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            o_1290_k matrix3f = (o_1290_k)p_equals_1_;
            return Float.compare(matrix3f.n_1700_B, this.n_1700_B) == 0 && Float.compare(matrix3f.J_1907_R, this.J_1907_R) == 0 && Float.compare(matrix3f.R_4764_Y, this.R_4764_Y) == 0 && Float.compare(matrix3f.G_564_y, this.G_564_y) == 0 && Float.compare(matrix3f.P_1922_E, this.P_1922_E) == 0 && Float.compare(matrix3f.u_1723_Y, this.u_1723_Y) == 0 && Float.compare(matrix3f.v_4262_N, this.v_4262_N) == 0 && Float.compare(matrix3f.w_1484_f, this.w_1484_f) == 0 && Float.compare(matrix3f.t_148_a, this.t_148_a) == 0;
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
        return 31 * i + (this.t_148_a != 0.0f ? Float.floatToIntBits(this.t_148_a) : 0);
    }

    public void n_1700_B(o_1290_k p_226114_1_) {
        this.n_1700_B = p_226114_1_.n_1700_B;
        this.J_1907_R = p_226114_1_.J_1907_R;
        this.R_4764_Y = p_226114_1_.R_4764_Y;
        this.G_564_y = p_226114_1_.G_564_y;
        this.P_1922_E = p_226114_1_.P_1922_E;
        this.u_1723_Y = p_226114_1_.u_1723_Y;
        this.v_4262_N = p_226114_1_.v_4262_N;
        this.w_1484_f = p_226114_1_.w_1484_f;
        this.t_148_a = p_226114_1_.t_148_a;
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Matrix3f:\n");
        stringbuilder.append(this.n_1700_B);
        stringbuilder.append(" ");
        stringbuilder.append(this.J_1907_R);
        stringbuilder.append(" ");
        stringbuilder.append(this.R_4764_Y);
        stringbuilder.append("\n");
        stringbuilder.append(this.G_564_y);
        stringbuilder.append(" ");
        stringbuilder.append(this.P_1922_E);
        stringbuilder.append(" ");
        stringbuilder.append(this.u_1723_Y);
        stringbuilder.append("\n");
        stringbuilder.append(this.v_4262_N);
        stringbuilder.append(" ");
        stringbuilder.append(this.w_1484_f);
        stringbuilder.append(" ");
        stringbuilder.append(this.t_148_a);
        stringbuilder.append("\n");
        return stringbuilder.toString();
    }

    public void R_4764_Y() {
        this.n_1700_B = 1.0f;
        this.J_1907_R = 0.0f;
        this.R_4764_Y = 0.0f;
        this.G_564_y = 0.0f;
        this.P_1922_E = 1.0f;
        this.u_1723_Y = 0.0f;
        this.v_4262_N = 0.0f;
        this.w_1484_f = 0.0f;
        this.t_148_a = 1.0f;
    }

    public float G_564_y() {
        float f = this.P_1922_E * this.t_148_a - this.u_1723_Y * this.w_1484_f;
        float f1 = -(this.G_564_y * this.t_148_a - this.u_1723_Y * this.v_4262_N);
        float f2 = this.G_564_y * this.w_1484_f - this.P_1922_E * this.v_4262_N;
        float f3 = -(this.J_1907_R * this.t_148_a - this.R_4764_Y * this.w_1484_f);
        float f4 = this.n_1700_B * this.t_148_a - this.R_4764_Y * this.v_4262_N;
        float f5 = -(this.n_1700_B * this.w_1484_f - this.J_1907_R * this.v_4262_N);
        float f6 = this.J_1907_R * this.u_1723_Y - this.R_4764_Y * this.P_1922_E;
        float f7 = -(this.n_1700_B * this.u_1723_Y - this.R_4764_Y * this.G_564_y);
        float f8 = this.n_1700_B * this.P_1922_E - this.J_1907_R * this.G_564_y;
        float f9 = this.n_1700_B * f + this.J_1907_R * f1 + this.R_4764_Y * f2;
        this.n_1700_B = f;
        this.G_564_y = f1;
        this.v_4262_N = f2;
        this.J_1907_R = f3;
        this.P_1922_E = f4;
        this.w_1484_f = f5;
        this.R_4764_Y = f6;
        this.u_1723_Y = f7;
        this.t_148_a = f8;
        return f9;
    }

    public boolean P_1922_E() {
        float f = this.G_564_y();
        if (Math.abs(f) > 1.0E-6f) {
            this.n_1700_B(f);
            return true;
        }
        return false;
    }

    public void n_1700_B(int p_232605_1_, int p_232605_2_, float p_232605_3_) {
        if (p_232605_1_ == 0) {
            if (p_232605_2_ == 0) {
                this.n_1700_B = p_232605_3_;
            } else if (p_232605_2_ == 1) {
                this.J_1907_R = p_232605_3_;
            } else {
                this.R_4764_Y = p_232605_3_;
            }
        } else if (p_232605_1_ == 1) {
            if (p_232605_2_ == 0) {
                this.G_564_y = p_232605_3_;
            } else if (p_232605_2_ == 1) {
                this.P_1922_E = p_232605_3_;
            } else {
                this.u_1723_Y = p_232605_3_;
            }
        } else if (p_232605_2_ == 0) {
            this.v_4262_N = p_232605_3_;
        } else if (p_232605_2_ == 1) {
            this.w_1484_f = p_232605_3_;
        } else {
            this.t_148_a = p_232605_3_;
        }
    }

    public void J_1907_R(o_1290_k p_226118_1_) {
        float f = this.n_1700_B * p_226118_1_.n_1700_B + this.J_1907_R * p_226118_1_.G_564_y + this.R_4764_Y * p_226118_1_.v_4262_N;
        float f1 = this.n_1700_B * p_226118_1_.J_1907_R + this.J_1907_R * p_226118_1_.P_1922_E + this.R_4764_Y * p_226118_1_.w_1484_f;
        float f2 = this.n_1700_B * p_226118_1_.R_4764_Y + this.J_1907_R * p_226118_1_.u_1723_Y + this.R_4764_Y * p_226118_1_.t_148_a;
        float f3 = this.G_564_y * p_226118_1_.n_1700_B + this.P_1922_E * p_226118_1_.G_564_y + this.u_1723_Y * p_226118_1_.v_4262_N;
        float f4 = this.G_564_y * p_226118_1_.J_1907_R + this.P_1922_E * p_226118_1_.P_1922_E + this.u_1723_Y * p_226118_1_.w_1484_f;
        float f5 = this.G_564_y * p_226118_1_.R_4764_Y + this.P_1922_E * p_226118_1_.u_1723_Y + this.u_1723_Y * p_226118_1_.t_148_a;
        float f6 = this.v_4262_N * p_226118_1_.n_1700_B + this.w_1484_f * p_226118_1_.G_564_y + this.t_148_a * p_226118_1_.v_4262_N;
        float f7 = this.v_4262_N * p_226118_1_.J_1907_R + this.w_1484_f * p_226118_1_.P_1922_E + this.t_148_a * p_226118_1_.w_1484_f;
        float f8 = this.v_4262_N * p_226118_1_.R_4764_Y + this.w_1484_f * p_226118_1_.u_1723_Y + this.t_148_a * p_226118_1_.t_148_a;
        this.n_1700_B = f;
        this.J_1907_R = f1;
        this.R_4764_Y = f2;
        this.G_564_y = f3;
        this.P_1922_E = f4;
        this.u_1723_Y = f5;
        this.v_4262_N = f6;
        this.w_1484_f = f7;
        this.t_148_a = f8;
    }

    public void n_1700_B(w_3785_E p_226115_1_) {
        float f = p_226115_1_.n_1700_B();
        float f1 = p_226115_1_.J_1907_R();
        float f2 = p_226115_1_.R_4764_Y();
        float f3 = p_226115_1_.G_564_y();
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
        float f16 = 2.0f * (f7 + f12);
        float f17 = 1.0f - f6 - f4;
        float f18 = 2.0f * (f8 - f10);
        float f19 = 2.0f * (f9 - f11);
        float f20 = 2.0f * (f8 + f10);
        float f21 = 1.0f - f4 - f5;
        float f22 = this.n_1700_B * f13 + this.J_1907_R * f16 + this.R_4764_Y * f19;
        float f23 = this.n_1700_B * f14 + this.J_1907_R * f17 + this.R_4764_Y * f20;
        float f24 = this.n_1700_B * f15 + this.J_1907_R * f18 + this.R_4764_Y * f21;
        float f25 = this.G_564_y * f13 + this.P_1922_E * f16 + this.u_1723_Y * f19;
        float f26 = this.G_564_y * f14 + this.P_1922_E * f17 + this.u_1723_Y * f20;
        float f27 = this.G_564_y * f15 + this.P_1922_E * f18 + this.u_1723_Y * f21;
        float f28 = this.v_4262_N * f13 + this.w_1484_f * f16 + this.t_148_a * f19;
        float f29 = this.v_4262_N * f14 + this.w_1484_f * f17 + this.t_148_a * f20;
        float f30 = this.v_4262_N * f15 + this.w_1484_f * f18 + this.t_148_a * f21;
        this.n_1700_B = f22;
        this.J_1907_R = f23;
        this.R_4764_Y = f24;
        this.G_564_y = f25;
        this.P_1922_E = f26;
        this.u_1723_Y = f27;
        this.v_4262_N = f28;
        this.w_1484_f = f29;
        this.t_148_a = f30;
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
    }

    public o_1290_k u_1723_Y() {
        return new o_1290_k(this);
    }

    public float J_1907_R(float p_getTransformX_1_, float p_getTransformX_2_, float p_getTransformX_3_) {
        return this.n_1700_B * p_getTransformX_1_ + this.J_1907_R * p_getTransformX_2_ + this.R_4764_Y * p_getTransformX_3_;
    }

    public float R_4764_Y(float p_getTransformY_1_, float p_getTransformY_2_, float p_getTransformY_3_) {
        return this.G_564_y * p_getTransformY_1_ + this.P_1922_E * p_getTransformY_2_ + this.u_1723_Y * p_getTransformY_3_;
    }

    public float G_564_y(float p_getTransformZ_1_, float p_getTransformZ_2_, float p_getTransformZ_3_) {
        return this.v_4262_N * p_getTransformZ_1_ + this.w_1484_f * p_getTransformZ_2_ + this.t_148_a * p_getTransformZ_3_;
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
    }

    public void R_4764_Y(o_1290_k p_multiplyBackward_1_) {
        o_1290_k matrix3f = p_multiplyBackward_1_.u_1723_Y();
        matrix3f.J_1907_R(this);
        this.n_1700_B(matrix3f);
    }
}

