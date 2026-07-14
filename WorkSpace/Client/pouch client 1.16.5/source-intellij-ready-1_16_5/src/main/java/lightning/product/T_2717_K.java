/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import lightning.product.MutableComponent;
import lightning.product.NumericTag;
import lightning.product.U_2871_b;
import lightning.product.Tag;
import lightning.product.TagType;
import lightning.product.o_926_S;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class T_2717_K
extends NumericTag {
    public static final T_2717_K n_1700_B = new T_2717_K(0.0f);
    public static final TagType<T_2717_K> J_1907_R = new TagType<T_2717_K>(){

        public T_2717_K n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
            accounter.n_1700_B(96L);
            return T_2717_K.n_1700_B(input.readFloat());
        }

        @Override
        public String n_1700_B() {
            return "FLOAT";
        }

        @Override
        public String J_1907_R() {
            return "TAG_Float";
        }

        @Override
        public boolean R_4764_Y() {
            return true;
        }

        @Override
        public /* synthetic */ Tag J_1907_R(DataInput dataInput, int n, o_926_S o_926_S2) throws IOException {
            return this.n_1700_B(dataInput, n, o_926_S2);
        }
    };
    private final float R_4764_Y;

    private T_2717_K(float data) {
        this.R_4764_Y = data;
    }

    public static T_2717_K n_1700_B(float value) {
        return value == 0.0f ? n_1700_B : new T_2717_K(value);
    }

    @Override
    public void n_1700_B(DataOutput output) throws IOException {
        output.writeFloat(this.R_4764_Y);
    }

    @Override
    public byte n_1700_B() {
        return 5;
    }

    public TagType<T_2717_K> J_1907_R() {
        return J_1907_R;
    }

    @Override
    public String toString() {
        return this.R_4764_Y + "f";
    }

    public T_2717_K G_564_y() {
        return this;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ instanceof T_2717_K && this.R_4764_Y == ((T_2717_K)p_equals_1_).R_4764_Y;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.R_4764_Y);
    }

    @Override
    public x_282_a n_1700_B(String indentation, int indentDepth) {
        MutableComponent itextcomponent = new U_2871_b("f").n_1700_B(v_4262_N);
        return new U_2871_b(String.valueOf(this.R_4764_Y)).n_1700_B(itextcomponent).n_1700_B(u_1723_Y);
    }

    @Override
    public long P_1922_E() {
        return (long)this.R_4764_Y;
    }

    @Override
    public int u_1723_Y() {
        return u_530_F.G_564_y(this.R_4764_Y);
    }

    @Override
    public short v_4262_N() {
        return (short)(u_530_F.G_564_y(this.R_4764_Y) & 0xFFFF);
    }

    @Override
    public byte w_1484_f() {
        return (byte)(u_530_F.G_564_y(this.R_4764_Y) & 0xFF);
    }

    @Override
    public double t_148_a() {
        return this.R_4764_Y;
    }

    @Override
    public float s_956_w() {
        return this.R_4764_Y;
    }

    @Override
    public Number u_2550_I() {
        return Float.valueOf(this.R_4764_Y);
    }

    @Override
    public /* synthetic */ Tag R_4764_Y() {
        return this.G_564_y();
    }
}


