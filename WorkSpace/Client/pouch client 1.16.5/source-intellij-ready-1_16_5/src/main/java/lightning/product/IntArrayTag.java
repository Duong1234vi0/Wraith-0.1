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
import lightning.product.NumericTag;
import lightning.product.U_2871_b;
import lightning.product.Tag;
import lightning.product.CollectionTag;
import lightning.product.TagType;
import lightning.product.o_926_S;
import lightning.product.IntTag;
import lightning.product.x_282_a;
import org.apache.commons.lang3.ArrayUtils;

public class IntArrayTag
extends CollectionTag<IntTag> {
    public static final TagType<IntArrayTag> n_1700_B = new TagType<IntArrayTag>(){

        public IntArrayTag n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
            accounter.n_1700_B(192L);
            int i = input.readInt();
            accounter.n_1700_B(32L * (long)i);
            int[] aint = new int[i];
            for (int j = 0; j < i; ++j) {
                aint[j] = input.readInt();
            }
            return new IntArrayTag(aint);
        }

        @Override
        public String n_1700_B() {
            return "INT[]";
        }

        @Override
        public String J_1907_R() {
            return "TAG_Int_Array";
        }

        @Override
        public /* synthetic */ Tag J_1907_R(DataInput dataInput, int n, o_926_S o_926_S2) throws IOException {
            return this.n_1700_B(dataInput, n, o_926_S2);
        }
    };
    private int[] J_1907_R;

    public IntArrayTag(int[] intArray) {
        this.J_1907_R = intArray;
    }

    public IntArrayTag(List<Integer> integers) {
        this(IntArrayTag.n_1700_B(integers));
    }

    private static int[] n_1700_B(List<Integer> integers) {
        int[] aint = new int[integers.size()];
        for (int i = 0; i < integers.size(); ++i) {
            Integer integer = integers.get(i);
            aint[i] = integer == null ? 0 : integer;
        }
        return aint;
    }

    @Override
    public void n_1700_B(DataOutput output) throws IOException {
        output.writeInt(this.J_1907_R.length);
        for (int i : this.J_1907_R) {
            output.writeInt(i);
        }
    }

    @Override
    public byte n_1700_B() {
        return 11;
    }

    public TagType<IntArrayTag> J_1907_R() {
        return n_1700_B;
    }

    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder("[I;");
        for (int i = 0; i < this.J_1907_R.length; ++i) {
            if (i != 0) {
                stringbuilder.append(',');
            }
            stringbuilder.append(this.J_1907_R[i]);
        }
        return stringbuilder.append(']').toString();
    }

    public IntArrayTag G_564_y() {
        int[] aint = new int[this.J_1907_R.length];
        System.arraycopy(this.J_1907_R, 0, aint, 0, this.J_1907_R.length);
        return new IntArrayTag(aint);
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ instanceof IntArrayTag && Arrays.equals(this.J_1907_R, ((IntArrayTag)p_equals_1_).J_1907_R);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.J_1907_R);
    }

    public int[] u_1723_Y() {
        return this.J_1907_R;
    }

    @Override
    public x_282_a n_1700_B(String indentation, int indentDepth) {
        MutableComponent itextcomponent = new U_2871_b("I").n_1700_B(v_4262_N);
        MutableComponent iformattabletextcomponent = new U_2871_b("[").n_1700_B(itextcomponent).n_1700_B(";");
        for (int i = 0; i < this.J_1907_R.length; ++i) {
            iformattabletextcomponent.n_1700_B(" ").n_1700_B(new U_2871_b(String.valueOf(this.J_1907_R[i])).n_1700_B(u_1723_Y));
            if (i == this.J_1907_R.length - 1) continue;
            iformattabletextcomponent.n_1700_B(",");
        }
        iformattabletextcomponent.n_1700_B("]");
        return iformattabletextcomponent;
    }

    @Override
    public int size() {
        return this.J_1907_R.length;
    }

    public IntTag n_1700_B(int p_get_1_) {
        return IntTag.n_1700_B(this.J_1907_R[p_get_1_]);
    }

    public IntTag n_1700_B(int p_set_1_, IntTag p_set_2_) {
        int i = this.J_1907_R[p_set_1_];
        this.J_1907_R[p_set_1_] = p_set_2_.u_1723_Y();
        return IntTag.n_1700_B(i);
    }

    public void J_1907_R(int p_add_1_, IntTag p_add_2_) {
        this.J_1907_R = ArrayUtils.add((int[])this.J_1907_R, (int)p_add_1_, (int)p_add_2_.u_1723_Y());
    }

    @Override
    public boolean n_1700_B(int index, Tag nbt) {
        if (nbt instanceof NumericTag) {
            this.J_1907_R[index] = ((NumericTag)nbt).u_1723_Y();
            return true;
        }
        return false;
    }

    @Override
    public boolean J_1907_R(int index, Tag nbt) {
        if (nbt instanceof NumericTag) {
            this.J_1907_R = ArrayUtils.add((int[])this.J_1907_R, (int)index, (int)((NumericTag)nbt).u_1723_Y());
            return true;
        }
        return false;
    }

    public IntTag J_1907_R(int p_remove_1_) {
        int i = this.J_1907_R[p_remove_1_];
        this.J_1907_R = ArrayUtils.remove((int[])this.J_1907_R, (int)p_remove_1_);
        return IntTag.n_1700_B(i);
    }

    @Override
    public byte P_1922_E() {
        return 3;
    }

    @Override
    public void clear() {
        this.J_1907_R = new int[0];
    }

    @Override
    public /* synthetic */ Tag R_4764_Y(int n) {
        return this.J_1907_R(n);
    }

    @Override
    public /* synthetic */ void R_4764_Y(int n, Tag y_890_Q) {
        this.J_1907_R(n, (IntTag)y_890_Q);
    }

    @Override
    public /* synthetic */ Tag G_564_y(int n, Tag y_890_Q) {
        return this.n_1700_B(n, (IntTag)y_890_Q);
    }

    @Override
    public /* synthetic */ Tag R_4764_Y() {
        return this.G_564_y();
    }

    @Override
    public /* synthetic */ Object remove(int n) {
        return this.J_1907_R(n);
    }

    @Override
    public /* synthetic */ void add(int n, Object object) {
        this.J_1907_R(n, (IntTag)object);
    }

    @Override
    public /* synthetic */ Object set(int n, Object object) {
        return this.n_1700_B(n, (IntTag)object);
    }

    @Override
    public /* synthetic */ Object get(int n) {
        return this.n_1700_B(n);
    }
}


