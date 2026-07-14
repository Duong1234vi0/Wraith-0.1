/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.Tag;
import lightning.product.TagType;
import lightning.product.o_926_S;
import lightning.product.x_282_a;

public class StringTag
implements Tag {
    public static final TagType<StringTag> n_1700_B = new TagType<StringTag>(){

        public StringTag n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
            accounter.n_1700_B(288L);
            String s = input.readUTF();
            accounter.n_1700_B(16 * s.length());
            return StringTag.n_1700_B(s);
        }

        @Override
        public String n_1700_B() {
            return "STRING";
        }

        @Override
        public String J_1907_R() {
            return "TAG_String";
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
    private static final StringTag J_1907_R = new StringTag("");
    private final String R_4764_Y;

    private StringTag(String data) {
        Objects.requireNonNull(data, "Null string not allowed");
        this.R_4764_Y = data;
    }

    public static StringTag n_1700_B(String value) {
        return value.isEmpty() ? J_1907_R : new StringTag(value);
    }

    @Override
    public void n_1700_B(DataOutput output) throws IOException {
        output.writeUTF(this.R_4764_Y);
    }

    @Override
    public byte n_1700_B() {
        return 8;
    }

    public TagType<StringTag> J_1907_R() {
        return n_1700_B;
    }

    @Override
    public String toString() {
        return StringTag.J_1907_R(this.R_4764_Y);
    }

    public StringTag G_564_y() {
        return this;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ instanceof StringTag && Objects.equals(this.R_4764_Y, ((StringTag)p_equals_1_).R_4764_Y);
    }

    public int hashCode() {
        return this.R_4764_Y.hashCode();
    }

    @Override
    public String M_588_G() {
        return this.R_4764_Y;
    }

    @Override
    public x_282_a n_1700_B(String indentation, int indentDepth) {
        String s = StringTag.J_1907_R(this.R_4764_Y);
        String s1 = s.substring(0, 1);
        MutableComponent itextcomponent = new U_2871_b(s.substring(1, s.length() - 1)).n_1700_B(P_1922_E);
        return new U_2871_b(s1).n_1700_B(itextcomponent).n_1700_B(s1);
    }

    public static String J_1907_R(String name) {
        StringBuilder stringbuilder = new StringBuilder(" ");
        char c0 = '\u0000';
        for (int i = 0; i < name.length(); ++i) {
            char c1 = name.charAt(i);
            if (c1 == '\\') {
                stringbuilder.append('\\');
            } else if (c1 == '\"' || c1 == '\'') {
                if (c0 == '\u0000') {
                    c0 = (char)(c1 == '\"' ? 39 : 34);
                }
                if (c0 == c1) {
                    stringbuilder.append('\\');
                }
            }
            stringbuilder.append(c1);
        }
        if (c0 == '\u0000') {
            c0 = '\"';
        }
        stringbuilder.setCharAt(0, c0);
        stringbuilder.append(c0);
        return stringbuilder.toString();
    }

    @Override
    public /* synthetic */ Tag R_4764_Y() {
        return this.G_564_y();
    }
}


