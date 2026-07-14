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
import lightning.product.x_282_a;

public class q_2567_I
extends NumericTag {
    public static final TagType<q_2567_I> n_1700_B = new TagType<q_2567_I>(){

        public q_2567_I n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
            accounter.n_1700_B(128L);
            return q_2567_I.n_1700_B(input.readLong());
        }

        @Override
        public String n_1700_B() {
            return "LONG";
        }

        @Override
        public String J_1907_R() {
            return "TAG_Long";
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
    private final long J_1907_R;

    private q_2567_I(long data) {
        this.J_1907_R = data;
    }

    public static q_2567_I n_1700_B(long value) {
        return value >= -128L && value <= 1024L ? lightning.product.q_2567_I$n_1700_B.n_1700_B[(int)value + 128] : new q_2567_I(value);
    }

    @Override
    public void n_1700_B(DataOutput output) throws IOException {
        output.writeLong(this.J_1907_R);
    }

    @Override
    public byte n_1700_B() {
        return 4;
    }

    public TagType<q_2567_I> J_1907_R() {
        return n_1700_B;
    }

    @Override
    public String toString() {
        return this.J_1907_R + "L";
    }

    public q_2567_I G_564_y() {
        return this;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ instanceof q_2567_I && this.J_1907_R == ((q_2567_I)p_equals_1_).J_1907_R;
    }

    public int hashCode() {
        return (int)(this.J_1907_R ^ this.J_1907_R >>> 32);
    }

    @Override
    public x_282_a n_1700_B(String indentation, int indentDepth) {
        MutableComponent itextcomponent = new U_2871_b("L").n_1700_B(v_4262_N);
        return new U_2871_b(String.valueOf(this.J_1907_R)).n_1700_B(itextcomponent).n_1700_B(u_1723_Y);
    }

    @Override
    public long P_1922_E() {
        return this.J_1907_R;
    }

    @Override
    public int u_1723_Y() {
        return (int)(this.J_1907_R & 0xFFFFFFFFFFFFFFFFL);
    }

    @Override
    public short v_4262_N() {
        return (short)(this.J_1907_R & 0xFFFFL);
    }

    @Override
    public byte w_1484_f() {
        return (byte)(this.J_1907_R & 0xFFL);
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
        static final q_2567_I[] n_1700_B = new q_2567_I[1153];

        n_1700_B() {
        }

        static {
            for (int i = 0; i < n_1700_B.length; ++i) {
                lightning.product.q_2567_I$n_1700_B.n_1700_B[i] = new q_2567_I(-128 + i);
            }
        }
    }
}


