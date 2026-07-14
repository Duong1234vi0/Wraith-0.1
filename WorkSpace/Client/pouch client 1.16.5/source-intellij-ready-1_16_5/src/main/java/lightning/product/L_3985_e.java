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

public class L_3985_e
extends NumericTag {
    public static final TagType<L_3985_e> n_1700_B = new TagType<L_3985_e>(){

        public L_3985_e n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
            accounter.n_1700_B(72L);
            return L_3985_e.n_1700_B(input.readByte());
        }

        @Override
        public String n_1700_B() {
            return "BYTE";
        }

        @Override
        public String J_1907_R() {
            return "TAG_Byte";
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
    public static final L_3985_e J_1907_R = L_3985_e.n_1700_B((byte)0);
    public static final L_3985_e R_4764_Y = L_3985_e.n_1700_B((byte)1);
    private final byte w_1484_f;

    private L_3985_e(byte data) {
        this.w_1484_f = data;
    }

    public static L_3985_e n_1700_B(byte byteIn) {
        return lightning.product.L_3985_e$n_1700_B.n_1700_B[128 + byteIn];
    }

    public static L_3985_e n_1700_B(boolean one) {
        return one ? R_4764_Y : J_1907_R;
    }

    @Override
    public void n_1700_B(DataOutput output) throws IOException {
        output.writeByte(this.w_1484_f);
    }

    @Override
    public byte n_1700_B() {
        return 1;
    }

    public TagType<L_3985_e> J_1907_R() {
        return n_1700_B;
    }

    @Override
    public String toString() {
        return this.w_1484_f + "b";
    }

    public L_3985_e G_564_y() {
        return this;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ instanceof L_3985_e && this.w_1484_f == ((L_3985_e)p_equals_1_).w_1484_f;
    }

    public int hashCode() {
        return this.w_1484_f;
    }

    @Override
    public x_282_a n_1700_B(String indentation, int indentDepth) {
        MutableComponent itextcomponent = new U_2871_b("b").n_1700_B(v_4262_N);
        return new U_2871_b(String.valueOf(this.w_1484_f)).n_1700_B(itextcomponent).n_1700_B(u_1723_Y);
    }

    @Override
    public long P_1922_E() {
        return this.w_1484_f;
    }

    @Override
    public int u_1723_Y() {
        return this.w_1484_f;
    }

    @Override
    public short v_4262_N() {
        return this.w_1484_f;
    }

    @Override
    public byte w_1484_f() {
        return this.w_1484_f;
    }

    @Override
    public double t_148_a() {
        return this.w_1484_f;
    }

    @Override
    public float s_956_w() {
        return this.w_1484_f;
    }

    @Override
    public Number u_2550_I() {
        return this.w_1484_f;
    }

    @Override
    public /* synthetic */ Tag R_4764_Y() {
        return this.G_564_y();
    }

    static class n_1700_B {
        private static final L_3985_e[] n_1700_B = new L_3985_e[256];

        n_1700_B() {
        }

        static {
            for (int i = 0; i < n_1700_B.length; ++i) {
                lightning.product.L_3985_e$n_1700_B.n_1700_B[i] = new L_3985_e((byte)(i - 128));
            }
        }
    }
}


