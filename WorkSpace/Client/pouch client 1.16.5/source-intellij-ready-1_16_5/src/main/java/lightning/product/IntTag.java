/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import lightning.product.NumericTag;
import lightning.product.U_2871_b;
import lightning.product.Tag;
import lightning.product.TagType;
import lightning.product.o_926_S;
import lightning.product.x_282_a;

public class IntTag
extends NumericTag {
    public static final TagType<IntTag> n_1700_B = new TagType<IntTag>(){

        public IntTag n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
            accounter.n_1700_B(96L);
            return IntTag.n_1700_B(input.readInt());
        }

        @Override
        public String n_1700_B() {
            return "INT";
        }

        @Override
        public String J_1907_R() {
            return "TAG_Int";
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
    private final int J_1907_R;

    private IntTag(int data) {
        this.J_1907_R = data;
    }

    public static IntTag n_1700_B(int dataIn) {
        return dataIn >= -128 && dataIn <= 1024 ? lightning.product.IntTag$n_1700_B.n_1700_B[dataIn + 128] : new IntTag(dataIn);
    }

    @Override
    public void n_1700_B(DataOutput output) throws IOException {
        output.writeInt(this.J_1907_R);
    }

    @Override
    public byte n_1700_B() {
        return 3;
    }

    public TagType<IntTag> J_1907_R() {
        return n_1700_B;
    }

    @Override
    public String toString() {
        return String.valueOf(this.J_1907_R);
    }

    public IntTag G_564_y() {
        return this;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ instanceof IntTag && this.J_1907_R == ((IntTag)p_equals_1_).J_1907_R;
    }

    public int hashCode() {
        return this.J_1907_R;
    }

    @Override
    public x_282_a n_1700_B(String indentation, int indentDepth) {
        return new U_2871_b(String.valueOf(this.J_1907_R)).n_1700_B(u_1723_Y);
    }

    @Override
    public long P_1922_E() {
        return this.J_1907_R;
    }

    @Override
    public int u_1723_Y() {
        return this.J_1907_R;
    }

    @Override
    public short v_4262_N() {
        return (short)(this.J_1907_R & 0xFFFF);
    }

    @Override
    public byte w_1484_f() {
        return (byte)(this.J_1907_R & 0xFF);
    }

    @Override
    public double t_148_a() {
        return this.J_1907_R;
    }

    @Override
    public float s_956_w() {
        return this.J_1907_R;
    }

    @Override
    public Number u_2550_I() {
        return this.J_1907_R;
    }

    @Override
    public /* synthetic */ Tag R_4764_Y() {
        return this.G_564_y();
    }

    static class n_1700_B {
        static final IntTag[] n_1700_B = new IntTag[1153];

        n_1700_B() {
        }

        static {
            for (int i = 0; i < n_1700_B.length; ++i) {
                lightning.product.IntTag$n_1700_B.n_1700_B[i] = new IntTag(-128 + i);
            }
        }
    }
}


