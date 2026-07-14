/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.bytes.ByteOpenHashSet
 *  it.unimi.dsi.fastutil.bytes.ByteSet
 */
package lightning.product;

import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.bytes.ByteOpenHashSet;
import it.unimi.dsi.fastutil.bytes.ByteSet;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lightning.product.D_908_R;
import lightning.product.T_2717_K;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.IntArrayTag;
import lightning.product.Y_3433_n;
import lightning.product.Tag;
import lightning.product.a_969_m;
import lightning.product.CollectionTag;
import lightning.product.TagType;
import lightning.product.o_926_S;
import lightning.product.IntTag;
import lightning.product.x_282_a;

public class q_2896_o
extends CollectionTag<Tag> {
    public static final TagType<q_2896_o> n_1700_B = new TagType<q_2896_o>(){

        public q_2896_o n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
            accounter.n_1700_B(296L);
            if (depth > 512) {
                throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
            }
            byte b0 = input.readByte();
            int i = input.readInt();
            if (b0 == 0 && i > 0) {
                throw new RuntimeException("Missing type on ListTag");
            }
            accounter.n_1700_B(32L * (long)i);
            TagType<?> inbttype = Y_3433_n.n_1700_B(b0);
            ArrayList list = Lists.newArrayListWithCapacity((int)i);
            for (int j = 0; j < i; ++j) {
                list.add(inbttype.J_1907_R(input, depth + 1, accounter));
            }
            return new q_2896_o(list, b0);
        }

        @Override
        public String n_1700_B() {
            return "LIST";
        }

        @Override
        public String J_1907_R() {
            return "TAG_List";
        }

        @Override
        public /* synthetic */ Tag J_1907_R(DataInput dataInput, int n, o_926_S o_926_S2) throws IOException {
            return this.n_1700_B(dataInput, n, o_926_S2);
        }
    };
    private static final ByteSet J_1907_R = new ByteOpenHashSet(Arrays.asList((byte)1, (byte)2, (byte)3, (byte)4, (byte)5, (byte)6));
    private final List<Tag> R_4764_Y;
    private byte w_1484_f;

    private q_2896_o(List<Tag> tagList, byte tagType) {
        this.R_4764_Y = tagList;
        this.w_1484_f = tagType;
    }

    public q_2896_o() {
        this(Lists.newArrayList(), 0);
    }

    @Override
    public void n_1700_B(DataOutput output) throws IOException {
        this.w_1484_f = this.R_4764_Y.isEmpty() ? (byte)0 : this.R_4764_Y.get(0).n_1700_B();
        output.writeByte(this.w_1484_f);
        output.writeInt(this.R_4764_Y.size());
        for (Tag inbt : this.R_4764_Y) {
            inbt.n_1700_B(output);
        }
    }

    @Override
    public byte n_1700_B() {
        return 9;
    }

    public TagType<q_2896_o> J_1907_R() {
        return n_1700_B;
    }

    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder("[");
        for (int i = 0; i < this.R_4764_Y.size(); ++i) {
            if (i != 0) {
                stringbuilder.append(',');
            }
            stringbuilder.append(this.R_4764_Y.get(i));
        }
        return stringbuilder.append(']').toString();
    }

    private void u_1723_Y() {
        if (this.R_4764_Y.isEmpty()) {
            this.w_1484_f = 0;
        }
    }

    @Override
    public Tag R_4764_Y(int p_remove_1_) {
        Tag inbt = this.R_4764_Y.remove(p_remove_1_);
        this.u_1723_Y();
        return inbt;
    }

    @Override
    public boolean isEmpty() {
        return this.R_4764_Y.isEmpty();
    }

    public U_2912_j n_1700_B(int i) {
        Tag inbt;
        if (i >= 0 && i < this.R_4764_Y.size() && (inbt = this.R_4764_Y.get(i)).n_1700_B() == 10) {
            return (U_2912_j)inbt;
        }
        return new U_2912_j();
    }

    public q_2896_o J_1907_R(int iIn) {
        Tag inbt;
        if (iIn >= 0 && iIn < this.R_4764_Y.size() && (inbt = this.R_4764_Y.get(iIn)).n_1700_B() == 9) {
            return (q_2896_o)inbt;
        }
        return new q_2896_o();
    }

    public short G_564_y(int iIn) {
        Tag inbt;
        if (iIn >= 0 && iIn < this.R_4764_Y.size() && (inbt = this.R_4764_Y.get(iIn)).n_1700_B() == 2) {
            return ((a_969_m)inbt).v_4262_N();
        }
        return 0;
    }

    public int P_1922_E(int iIn) {
        Tag inbt;
        if (iIn >= 0 && iIn < this.R_4764_Y.size() && (inbt = this.R_4764_Y.get(iIn)).n_1700_B() == 3) {
            return ((IntTag)inbt).u_1723_Y();
        }
        return 0;
    }

    public int[] u_1723_Y(int i) {
        Tag inbt;
        if (i >= 0 && i < this.R_4764_Y.size() && (inbt = this.R_4764_Y.get(i)).n_1700_B() == 11) {
            return ((IntArrayTag)inbt).u_1723_Y();
        }
        return new int[0];
    }

    public double v_4262_N(int i) {
        Tag inbt;
        if (i >= 0 && i < this.R_4764_Y.size() && (inbt = this.R_4764_Y.get(i)).n_1700_B() == 6) {
            return ((D_908_R)inbt).t_148_a();
        }
        return 0.0;
    }

    public float w_1484_f(int i) {
        Tag inbt;
        if (i >= 0 && i < this.R_4764_Y.size() && (inbt = this.R_4764_Y.get(i)).n_1700_B() == 5) {
            return ((T_2717_K)inbt).s_956_w();
        }
        return 0.0f;
    }

    public String t_148_a(int i) {
        if (i >= 0 && i < this.R_4764_Y.size()) {
            Tag inbt = this.R_4764_Y.get(i);
            return inbt.n_1700_B() == 8 ? inbt.M_588_G() : inbt.toString();
        }
        return "";
    }

    @Override
    public int size() {
        return this.R_4764_Y.size();
    }

    public Tag s_956_w(int p_get_1_) {
        return this.R_4764_Y.get(p_get_1_);
    }

    @Override
    public Tag G_564_y(int p_set_1_, Tag p_set_2_) {
        Tag inbt = this.s_956_w(p_set_1_);
        if (!this.n_1700_B(p_set_1_, p_set_2_)) {
            throw new UnsupportedOperationException(String.format("Trying to add tag of type %d to list of %d", p_set_2_.n_1700_B(), this.w_1484_f));
        }
        return inbt;
    }

    @Override
    public void R_4764_Y(int p_add_1_, Tag p_add_2_) {
        if (!this.J_1907_R(p_add_1_, p_add_2_)) {
            throw new UnsupportedOperationException(String.format("Trying to add tag of type %d to list of %d", p_add_2_.n_1700_B(), this.w_1484_f));
        }
    }

    @Override
    public boolean n_1700_B(int index, Tag nbt) {
        if (this.n_1700_B(nbt)) {
            this.R_4764_Y.set(index, nbt);
            return true;
        }
        return false;
    }

    @Override
    public boolean J_1907_R(int index, Tag nbt) {
        if (this.n_1700_B(nbt)) {
            this.R_4764_Y.add(index, nbt);
            return true;
        }
        return false;
    }

    private boolean n_1700_B(Tag nbt) {
        if (nbt.n_1700_B() == 0) {
            return false;
        }
        if (this.w_1484_f == 0) {
            this.w_1484_f = nbt.n_1700_B();
            return true;
        }
        return this.w_1484_f == nbt.n_1700_B();
    }

    public q_2896_o G_564_y() {
        List<Tag> iterable = Y_3433_n.n_1700_B(this.w_1484_f).R_4764_Y() ? this.R_4764_Y : Iterables.transform(this.R_4764_Y, Tag::R_4764_Y);
        ArrayList list = Lists.newArrayList(iterable);
        return new q_2896_o(list, this.w_1484_f);
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ instanceof q_2896_o && Objects.equals(this.R_4764_Y, ((q_2896_o)p_equals_1_).R_4764_Y);
    }

    @Override
    public int hashCode() {
        return this.R_4764_Y.hashCode();
    }

    @Override
    public x_282_a n_1700_B(String indentation, int indentDepth) {
        if (this.isEmpty()) {
            return new U_2871_b("[]");
        }
        if (J_1907_R.contains(this.w_1484_f) && this.size() <= 8) {
            String s1 = ", ";
            U_2871_b iformattabletextcomponent2 = new U_2871_b("[");
            for (int j = 0; j < this.R_4764_Y.size(); ++j) {
                if (j != 0) {
                    iformattabletextcomponent2.n_1700_B(", ");
                }
                iformattabletextcomponent2.n_1700_B(this.R_4764_Y.get(j).P_4830_p());
            }
            iformattabletextcomponent2.n_1700_B("]");
            return iformattabletextcomponent2;
        }
        U_2871_b iformattabletextcomponent = new U_2871_b("[");
        if (!indentation.isEmpty()) {
            iformattabletextcomponent.n_1700_B("\n");
        }
        String s = String.valueOf(',');
        for (int i = 0; i < this.R_4764_Y.size(); ++i) {
            U_2871_b iformattabletextcomponent1 = new U_2871_b(Strings.repeat((String)indentation, (int)(indentDepth + 1)));
            iformattabletextcomponent1.n_1700_B(this.R_4764_Y.get(i).n_1700_B(indentation, indentDepth + 1));
            if (i != this.R_4764_Y.size() - 1) {
                iformattabletextcomponent1.n_1700_B(s).n_1700_B(indentation.isEmpty() ? " " : "\n");
            }
            iformattabletextcomponent.n_1700_B(iformattabletextcomponent1);
        }
        if (!indentation.isEmpty()) {
            iformattabletextcomponent.n_1700_B("\n").n_1700_B(Strings.repeat((String)indentation, (int)indentDepth));
        }
        iformattabletextcomponent.n_1700_B("]");
        return iformattabletextcomponent;
    }

    @Override
    public byte P_1922_E() {
        return this.w_1484_f;
    }

    @Override
    public void clear() {
        this.R_4764_Y.clear();
        this.w_1484_f = 0;
    }

    @Override
    public /* synthetic */ Tag R_4764_Y() {
        return this.G_564_y();
    }

    @Override
    public /* synthetic */ Object remove(int n) {
        return this.R_4764_Y(n);
    }

    @Override
    public /* synthetic */ void add(int n, Object object) {
        this.R_4764_Y(n, (Tag)object);
    }

    @Override
    public /* synthetic */ Object set(int n, Object object) {
        return this.G_564_y(n, (Tag)object);
    }

    @Override
    public /* synthetic */ Object get(int n) {
        return this.s_956_w(n);
    }
}


