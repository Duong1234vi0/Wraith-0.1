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

public class D_908_R
extends NumericTag {
    public static final D_908_R n_1700_B = new D_908_R(0.0);
    public static final TagType<D_908_R> J_1907_R = new TagType<D_908_R>(){

        public D_908_R n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
            accounter.n_1700_B(128L);
            return D_908_R.n_1700_B(input.readDouble());
        }

        @Override
        public String n_1700_B() {
            return "DOUBLE";
        }

        @Override
        public String J_1907_R() {
            return "TAG_Double";
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
    private final double R_4764_Y;

    private D_908_R(double data) {
        this.R_4764_Y = data;
    }

    public static D_908_R n_1700_B(double value) {
        return value == 0.0 ? n_1700_B : new D_908_R(value);
    }

    @Override
    public void n_1700_B(DataOutput output) throws IOException {
        output.writeDouble(this.R_4764_Y);
    }

    @Override
    public byte n_1700_B() {
        return 6;
    }

    public TagType<D_908_R> J_1907_R() {
        return J_1907_R;
    }

    @Override
    public String toString() {
        return this.R_4764_Y + "d";
    }

    public D_908_R G_564_y() {
        return this;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ instanceof D_908_R && this.R_4764_Y == ((D_908_R)p_equals_1_).R_4764_Y;
    }

    public int hashCode() {
        long i = Double.doubleToLongBits(this.R_4764_Y);
        return (int)(i ^ i >>> 32);
    }

    @Override
    public x_282_a n_1700_B(String indentation, int indentDepth) {
        MutableComponent itextcomponent = new U_2871_b("d").n_1700_B(v_4262_N);
        return new U_2871_b(String.valueOf(this.R_4764_Y)).n_1700_B(itextcomponent).n_1700_B(u_1723_Y);
    }

    @Override
    public long P_1922_E() {
        return (long)Math.floor(this.R_4764_Y);
    }

    @Override
    public int u_1723_Y() {
        return u_530_F.R_4764_Y(this.R_4764_Y);
    }

    @Override
    public short v_4262_N() {
        return (short)(u_530_F.R_4764_Y(this.R_4764_Y) & 0xFFFF);
    }

    @Override
    public byte w_1484_f() {
        return (byte)(u_530_F.R_4764_Y(this.R_4764_Y) & 0xFF);
    }

    @Override
    public double t_148_a() {
        return this.R_4764_Y;
    }

    @Override
    public float s_956_w() {
        return (float)this.R_4764_Y;
    }

    @Override
    public Number u_2550_I() {
        return this.R_4764_Y;
    }

    @Override
    public /* synthetic */ Tag R_4764_Y() {
        return this.G_564_y();
    }
}


