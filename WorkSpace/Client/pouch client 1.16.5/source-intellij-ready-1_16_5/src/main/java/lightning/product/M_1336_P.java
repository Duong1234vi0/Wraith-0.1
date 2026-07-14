/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.floats.Float2FloatFunction
 *  lombok.Generated
 */
package lightning.product;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import lightning.product.e_2866_D;
import lightning.product.o_1290_k;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;
import lombok.Generated;

public final class M_1336_P {
    public static M_1336_P n_1700_B = new M_1336_P(-1.0f, 0.0f, 0.0f);
    public static M_1336_P J_1907_R = new M_1336_P(1.0f, 0.0f, 0.0f);
    public static M_1336_P R_4764_Y = new M_1336_P(0.0f, -1.0f, 0.0f);
    public static M_1336_P G_564_y = new M_1336_P(0.0f, 1.0f, 0.0f);
    public static M_1336_P P_1922_E = new M_1336_P(0.0f, 0.0f, -1.0f);
    public static M_1336_P u_1723_Y = new M_1336_P(0.0f, 0.0f, 1.0f);
    private float v_4262_N;
    private float w_1484_f;
    private float t_148_a;

    public M_1336_P() {
    }

    public M_1336_P(float x, float y, float z) {
        this.v_4262_N = x;
        this.w_1484_f = y;
        this.t_148_a = z;
    }

    public M_1336_P(e_2866_D vecIn) {
        this((float)vecIn.J_1907_R, (float)vecIn.R_4764_Y, (float)vecIn.G_564_y);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            M_1336_P vector3f = (M_1336_P)p_equals_1_;
            if (Float.compare(vector3f.v_4262_N, this.v_4262_N) != 0) {
                return false;
            }
            if (Float.compare(vector3f.w_1484_f, this.w_1484_f) != 0) {
                return false;
            }
            return Float.compare(vector3f.t_148_a, this.t_148_a) == 0;
        }
        return false;
    }

    public int hashCode() {
        int i = Float.floatToIntBits(this.v_4262_N);
        i = 31 * i + Float.floatToIntBits(this.w_1484_f);
        return 31 * i + Float.floatToIntBits(this.t_148_a);
    }

    public float n_1700_B() {
        return this.v_4262_N;
    }

    public float J_1907_R() {
        return this.w_1484_f;
    }

    public float R_4764_Y() {
        return this.t_148_a;
    }

    public void n_1700_B(float multiplier) {
        this.v_4262_N *= multiplier;
        this.w_1484_f *= multiplier;
        this.t_148_a *= multiplier;
    }

    public void n_1700_B(float mx, float my, float mz) {
        this.v_4262_N *= mx;
        this.w_1484_f *= my;
        this.t_148_a *= mz;
    }

    public void n_1700_B(float min, float max) {
        this.v_4262_N = u_530_F.n_1700_B(this.v_4262_N, min, max);
        this.w_1484_f = u_530_F.n_1700_B(this.w_1484_f, min, max);
        this.t_148_a = u_530_F.n_1700_B(this.t_148_a, min, max);
    }

    public void J_1907_R(float x, float y, float z) {
        this.v_4262_N = x;
        this.w_1484_f = y;
        this.t_148_a = z;
    }

    public void R_4764_Y(float x, float y, float z) {
        this.v_4262_N += x;
        this.w_1484_f += y;
        this.t_148_a += z;
    }

    public void n_1700_B(M_1336_P vectorIn) {
        this.v_4262_N += vectorIn.v_4262_N;
        this.w_1484_f += vectorIn.w_1484_f;
        this.t_148_a += vectorIn.t_148_a;
    }

    public void J_1907_R(M_1336_P vec) {
        this.v_4262_N -= vec.v_4262_N;
        this.w_1484_f -= vec.w_1484_f;
        this.t_148_a -= vec.t_148_a;
    }

    public float R_4764_Y(M_1336_P vec) {
        return this.v_4262_N * vec.v_4262_N + this.w_1484_f * vec.w_1484_f + this.t_148_a * vec.t_148_a;
    }

    public boolean G_564_y() {
        float f = this.v_4262_N * this.v_4262_N + this.w_1484_f * this.w_1484_f + this.t_148_a * this.t_148_a;
        if ((double)f < 1.0E-5) {
            return false;
        }
        float f1 = u_530_F.t_148_a(f);
        this.v_4262_N *= f1;
        this.w_1484_f *= f1;
        this.t_148_a *= f1;
        return true;
    }

    public void G_564_y(M_1336_P vec) {
        float f = this.v_4262_N;
        float f1 = this.w_1484_f;
        float f2 = this.t_148_a;
        float f3 = vec.n_1700_B();
        float f4 = vec.J_1907_R();
        float f5 = vec.R_4764_Y();
        this.v_4262_N = f1 * f5 - f2 * f4;
        this.w_1484_f = f2 * f3 - f * f5;
        this.t_148_a = f * f4 - f1 * f3;
    }

    public void n_1700_B(o_1290_k matrixIn) {
        float f = this.v_4262_N;
        float f1 = this.w_1484_f;
        float f2 = this.t_148_a;
        this.v_4262_N = matrixIn.n_1700_B * f + matrixIn.J_1907_R * f1 + matrixIn.R_4764_Y * f2;
        this.w_1484_f = matrixIn.G_564_y * f + matrixIn.P_1922_E * f1 + matrixIn.u_1723_Y * f2;
        this.t_148_a = matrixIn.v_4262_N * f + matrixIn.w_1484_f * f1 + matrixIn.t_148_a * f2;
    }

    public void n_1700_B(w_3785_E quaternionIn) {
        w_3785_E quaternion = new w_3785_E(quaternionIn);
        quaternion.n_1700_B(new w_3785_E(this.n_1700_B(), this.J_1907_R(), this.R_4764_Y(), 0.0f));
        w_3785_E quaternion1 = new w_3785_E(quaternionIn);
        quaternion1.P_1922_E();
        quaternion.n_1700_B(quaternion1);
        this.J_1907_R(quaternion.n_1700_B(), quaternion.J_1907_R(), quaternion.R_4764_Y());
    }

    public void n_1700_B(M_1336_P vectorIn, float pctIn) {
        float f = 1.0f - pctIn;
        this.v_4262_N = this.v_4262_N * f + vectorIn.v_4262_N * pctIn;
        this.w_1484_f = this.w_1484_f * f + vectorIn.w_1484_f * pctIn;
        this.t_148_a = this.t_148_a * f + vectorIn.t_148_a * pctIn;
    }

    public w_3785_E J_1907_R(float valueIn) {
        return new w_3785_E(this, valueIn, false);
    }

    public w_3785_E R_4764_Y(float valueIn) {
        return new w_3785_E(this, valueIn, true);
    }

    public M_1336_P P_1922_E() {
        return new M_1336_P(this.v_4262_N, this.w_1484_f, this.t_148_a);
    }

    public void n_1700_B(Float2FloatFunction functionIn) {
        this.v_4262_N = functionIn.get(this.v_4262_N);
        this.w_1484_f = functionIn.get(this.w_1484_f);
        this.t_148_a = functionIn.get(this.t_148_a);
    }

    public String toString() {
        return "[" + this.v_4262_N + ", " + this.w_1484_f + ", " + this.t_148_a + "]";
    }

    @Generated
    public void G_564_y(float x) {
        this.v_4262_N = x;
    }

    @Generated
    public void P_1922_E(float y) {
        this.w_1484_f = y;
    }

    @Generated
    public void u_1723_Y(float z) {
        this.t_148_a = z;
    }
}

