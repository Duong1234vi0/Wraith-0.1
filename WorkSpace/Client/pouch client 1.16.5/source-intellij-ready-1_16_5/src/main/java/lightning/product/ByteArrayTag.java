/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.ArrayUtils
 */
package lightning.product;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lightning.product.MutableComponent;
import lightning.product.L_3985_e;
import lightning.product.NumericTag;
import lightning.product.U_2871_b;
import lightning.product.Tag;
import lightning.product.CollectionTag;
import lightning.product.TagType;
import lightning.product.o_926_S;
import lightning.product.x_282_a;
import org.apache.commons.lang3.ArrayUtils;

public class ByteArrayTag
extends CollectionTag<L_3985_e> {
    public static final TagType<ByteArrayTag> n_1700_B = new TagType<ByteArrayTag>(){

        public ByteArrayTag n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
            accounter.n_1700_B(192L);
            int i = input.readInt();
            accounter.n_1700_B(8L * (long)i);
            byte[] abyte = new byte[i];
            input.readFully(abyte);
            return new ByteArrayTag(abyte);
        }

        @Override
        public String n_1700_B() {
            return "BYTE[]";
        }

        @Override
        public String J_1907_R() {
            return "TAG_Byte_Array";
        }

        @Override
        public /* synthetic */ Tag J_1907_R(DataInput dataInput, int n, o_926_S o_926_S2) throws IOException {
            return this.n_1700_B(dataInput, n, o_926_S2);
        }
    };
    private byte[] J_1907_R;

    public ByteArrayTag(byte[] data) {
        this.J_1907_R = data;
    }

    public ByteArrayTag(List<Byte> bytes) {
        this(ByteArrayTag.n_1700_B(bytes));
    }

    private static byte[] n_1700_B(List<Byte> bytes) {
        byte[] abyte = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); ++i) {
            Byte obyte = bytes.get(i);
            abyte[i] = obyte == null ? (byte)0 : obyte;
        }
        return abyte;
    }

    @Override
    public void n_1700_B(DataOutput output) throws IOException {
        output.writeInt(this.J_1907_R.length);
        output.write(this.J_1907_R);
    }

    @Override
    public byte n_1700_B() {
        return 7;
    }

    public TagType<ByteArrayTag> J_1907_R() {
        return n_1700_B;
    }

    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder("[B;");
        for (int i = 0; i < this.J_1907_R.length; ++i) {
            if (i != 0) {
                stringbuilder.append(',');
            }
            stringbuilder.append(this.J_1907_R[i]).append('B');
        }
        return stringbuilder.append(']').toString();
    }

    @Override
    public Tag R_4764_Y() {
        byte[] abyte = new byte[this.J_1907_R.length];
        System.arraycopy(this.J_1907_R, 0, abyte, 0, this.J_1907_R.length);
        return new ByteArrayTag(abyte);
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ instanceof ByteArrayTag && Arrays.equals(this.J_1907_R, ((ByteArrayTag)p_equals_1_).J_1907_R);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.J_1907_R);
    }

    @Override
    public x_282_a n_1700_B(String indentation, int indentDepth) {
        MutableComponent itextcomponent = new U_2871_b("B").n_1700_B(v_4262_N);
        MutableComponent iformattabletextcomponent = new U_2871_b("[").n_1700_B(itextcomponent).n_1700_B(";");
        for (int i = 0; i < this.J_1907_R.length; ++i) {
            MutableComponent iformattabletextcomponent1 = new U_2871_b(String.valueOf(this.J_1907_R[i])).n_1700_B(u_1723_Y);
            iformattabletextcomponent.n_1700_B(" ").n_1700_B(iformattabletextcomponent1).n_1700_B(itextcomponent);
            if (i == this.J_1907_R.length - 1) continue;
            iformattabletextcomponent.n_1700_B(",");
        }
        iformattabletextcomponent.n_1700_B("]");
        return iformattabletextcomponent;
    }

    public byte[] G_564_y() {
        return this.J_1907_R;
    }

    @Override
    public int size() {
        return this.J_1907_R.length;
    }

    public L_3985_e n_1700_B(int p_get_1_) {
        return L_3985_e.n_1700_B(this.J_1907_R[p_get_1_]);
    }

    public L_3985_e n_1700_B(int p_set_1_, L_3985_e p_set_2_) {
        byte b0 = this.J_1907_R[p_set_1_];
        this.J_1907_R[p_set_1_] = p_set_2_.w_1484_f();
        return L_3985_e.n_1700_B(b0);
    }

    public void J_1907_R(int p_add_1_, L_3985_e p_add_2_) {
        this.J_1907_R = ArrayUtils.add((byte[])this.J_1907_R, (int)p_add_1_, (byte)p_add_2_.w_1484_f());
    }

    @Override
    public boolean n_1700_B(int index, Tag nbt) {
        if (nbt instanceof NumericTag) {
            this.J_1907_R[index] = ((NumericTag)nbt).w_1484_f();
            return true;
        }
        return false;
    }

    @Override
    public boolean J_1907_R(int index, Tag nbt) {
        if (nbt instanceof NumericTag) {
            this.J_1907_R = ArrayUtils.add((byte[])this.J_1907_R, (int)index, (byte)((NumericTag)nbt).w_1484_f());
            return true;
        }
        return false;
    }

    public L_3985_e J_1907_R(int p_remove_1_) {
        byte b0 = this.J_1907_R[p_remove_1_];
        this.J_1907_R = ArrayUtils.remove((byte[])this.J_1907_R, (int)p_remove_1_);
        return L_3985_e.n_1700_B(b0);
    }

    @Override
    public byte P_1922_E() {
        return 1;
    }

    @Override
    public void clear() {
        this.J_1907_R = new byte[0];
    }

    @Override
    public /* synthetic */ Tag R_4764_Y(int n) {
        return this.J_1907_R(n);
    }

    @Override
    public /* synthetic */ void R_4764_Y(int n, Tag y_890_Q) {
        this.J_1907_R(n, (L_3985_e)y_890_Q);
    }

    @Override
    public /* synthetic */ Tag G_564_y(int n, Tag y_890_Q) {
        return this.n_1700_B(n, (L_3985_e)y_890_Q);
    }

    @Override
    public /* synthetic */ Object remove(int n) {
        return this.J_1907_R(n);
    }

    @Override
    public /* synthetic */ void add(int n, Object object) {
        this.J_1907_R(n, (L_3985_e)object);
    }

    @Override
    public /* synthetic */ Object set(int n, Object object) {
        return this.n_1700_B(n, (L_3985_e)object);
    }

    @Override
    public /* synthetic */ Object get(int n) {
        return this.n_1700_B(n);
    }
}


