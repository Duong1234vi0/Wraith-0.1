/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import lightning.product.D_908_R;
import lightning.product.F_2904_S;
import lightning.product.StringTag;
import lightning.product.LongArrayTag;
import lightning.product.L_3985_e;
import lightning.product.T_2717_K;
import lightning.product.NumericTag;
import lightning.product.U_2912_j;
import lightning.product.IntArrayTag;
import lightning.product.Tag;
import lightning.product.a_969_m;
import lightning.product.ByteArrayTag;
import lightning.product.TagType;
import lightning.product.q_2567_I;
import lightning.product.q_2896_o;
import lightning.product.IntTag;

public class r_4318_c {
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.nbt.trailing"));
    public static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("argument.nbt.expected.key"));
    public static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("argument.nbt.expected.value"));
    public static final Dynamic2CommandExceptionType G_564_y = new Dynamic2CommandExceptionType((error1, error2) -> new F_2904_S("argument.nbt.list.mixed", error1, error2));
    public static final Dynamic2CommandExceptionType P_1922_E = new Dynamic2CommandExceptionType((error, error2) -> new F_2904_S("argument.nbt.array.mixed", error, error2));
    public static final DynamicCommandExceptionType u_1723_Y = new DynamicCommandExceptionType(error -> new F_2904_S("argument.nbt.array.invalid", error));
    private static final Pattern v_4262_N = Pattern.compile("[-+]?(?:[0-9]+[.]|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?", 2);
    private static final Pattern w_1484_f = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", 2);
    private static final Pattern t_148_a = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?f", 2);
    private static final Pattern s_956_w = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)b", 2);
    private static final Pattern u_2550_I = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)l", 2);
    private static final Pattern M_588_G = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)s", 2);
    private static final Pattern P_4830_p = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)");
    private final StringReader h_1847_R;

    public static U_2912_j n_1700_B(String jsonString) throws CommandSyntaxException {
        return new r_4318_c(new StringReader(jsonString)).n_1700_B();
    }

    @VisibleForTesting
    U_2912_j n_1700_B() throws CommandSyntaxException {
        U_2912_j compoundnbt = this.u_1723_Y();
        this.h_1847_R.skipWhitespace();
        if (this.h_1847_R.canRead()) {
            throw n_1700_B.createWithContext((ImmutableStringReader)this.h_1847_R);
        }
        return compoundnbt;
    }

    public r_4318_c(StringReader readerIn) {
        this.h_1847_R = readerIn;
    }

    protected String J_1907_R() throws CommandSyntaxException {
        this.h_1847_R.skipWhitespace();
        if (!this.h_1847_R.canRead()) {
            throw J_1907_R.createWithContext((ImmutableStringReader)this.h_1847_R);
        }
        return this.h_1847_R.readString();
    }

    protected Tag R_4764_Y() throws CommandSyntaxException {
        this.h_1847_R.skipWhitespace();
        int i = this.h_1847_R.getCursor();
        if (StringReader.isQuotedStringStart((char)this.h_1847_R.peek())) {
            return StringTag.n_1700_B(this.h_1847_R.readQuotedString());
        }
        String s = this.h_1847_R.readUnquotedString();
        if (s.isEmpty()) {
            this.h_1847_R.setCursor(i);
            throw R_4764_Y.createWithContext((ImmutableStringReader)this.h_1847_R);
        }
        return this.J_1907_R(s);
    }

    private Tag J_1907_R(String stringIn) {
        try {
            if (t_148_a.matcher(stringIn).matches()) {
                return T_2717_K.n_1700_B(Float.parseFloat(stringIn.substring(0, stringIn.length() - 1)));
            }
            if (s_956_w.matcher(stringIn).matches()) {
                return L_3985_e.n_1700_B(Byte.parseByte(stringIn.substring(0, stringIn.length() - 1)));
            }
            if (u_2550_I.matcher(stringIn).matches()) {
                return q_2567_I.n_1700_B(Long.parseLong(stringIn.substring(0, stringIn.length() - 1)));
            }
            if (M_588_G.matcher(stringIn).matches()) {
                return a_969_m.n_1700_B(Short.parseShort(stringIn.substring(0, stringIn.length() - 1)));
            }
            if (P_4830_p.matcher(stringIn).matches()) {
                return IntTag.n_1700_B(Integer.parseInt(stringIn));
            }
            if (w_1484_f.matcher(stringIn).matches()) {
                return D_908_R.n_1700_B(Double.parseDouble(stringIn.substring(0, stringIn.length() - 1)));
            }
            if (v_4262_N.matcher(stringIn).matches()) {
                return D_908_R.n_1700_B(Double.parseDouble(stringIn));
            }
            if ("true".equalsIgnoreCase(stringIn)) {
                return L_3985_e.R_4764_Y;
            }
            if ("false".equalsIgnoreCase(stringIn)) {
                return L_3985_e.J_1907_R;
            }
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        return StringTag.n_1700_B(stringIn);
    }

    public Tag G_564_y() throws CommandSyntaxException {
        this.h_1847_R.skipWhitespace();
        if (!this.h_1847_R.canRead()) {
            throw R_4764_Y.createWithContext((ImmutableStringReader)this.h_1847_R);
        }
        char c0 = this.h_1847_R.peek();
        if (c0 == '{') {
            return this.u_1723_Y();
        }
        return c0 == '[' ? this.P_1922_E() : this.R_4764_Y();
    }

    protected Tag P_1922_E() throws CommandSyntaxException {
        return this.h_1847_R.canRead(3) && !StringReader.isQuotedStringStart((char)this.h_1847_R.peek(1)) && this.h_1847_R.peek(2) == ';' ? this.w_1484_f() : this.v_4262_N();
    }

    public U_2912_j u_1723_Y() throws CommandSyntaxException {
        this.n_1700_B('{');
        U_2912_j compoundnbt = new U_2912_j();
        this.h_1847_R.skipWhitespace();
        while (this.h_1847_R.canRead() && this.h_1847_R.peek() != '}') {
            int i = this.h_1847_R.getCursor();
            String s = this.J_1907_R();
            if (s.isEmpty()) {
                this.h_1847_R.setCursor(i);
                throw J_1907_R.createWithContext((ImmutableStringReader)this.h_1847_R);
            }
            this.n_1700_B(':');
            compoundnbt.n_1700_B(s, this.G_564_y());
            if (!this.t_148_a()) break;
            if (this.h_1847_R.canRead()) continue;
            throw J_1907_R.createWithContext((ImmutableStringReader)this.h_1847_R);
        }
        this.n_1700_B('}');
        return compoundnbt;
    }

    private Tag v_4262_N() throws CommandSyntaxException {
        this.n_1700_B('[');
        this.h_1847_R.skipWhitespace();
        if (!this.h_1847_R.canRead()) {
            throw R_4764_Y.createWithContext((ImmutableStringReader)this.h_1847_R);
        }
        q_2896_o listnbt = new q_2896_o();
        TagType<?> inbttype = null;
        while (this.h_1847_R.peek() != ']') {
            int i = this.h_1847_R.getCursor();
            Tag inbt = this.G_564_y();
            TagType<?> inbttype1 = inbt.J_1907_R();
            if (inbttype == null) {
                inbttype = inbttype1;
            } else if (inbttype1 != inbttype) {
                this.h_1847_R.setCursor(i);
                throw G_564_y.createWithContext((ImmutableStringReader)this.h_1847_R, (Object)inbttype1.J_1907_R(), (Object)inbttype.J_1907_R());
            }
            listnbt.add(inbt);
            if (!this.t_148_a()) break;
            if (this.h_1847_R.canRead()) continue;
            throw R_4764_Y.createWithContext((ImmutableStringReader)this.h_1847_R);
        }
        this.n_1700_B(']');
        return listnbt;
    }

    private Tag w_1484_f() throws CommandSyntaxException {
        this.n_1700_B('[');
        int i = this.h_1847_R.getCursor();
        char c0 = this.h_1847_R.read();
        this.h_1847_R.read();
        this.h_1847_R.skipWhitespace();
        if (!this.h_1847_R.canRead()) {
            throw R_4764_Y.createWithContext((ImmutableStringReader)this.h_1847_R);
        }
        if (c0 == 'B') {
            return new ByteArrayTag(this.n_1700_B(ByteArrayTag.n_1700_B, L_3985_e.n_1700_B));
        }
        if (c0 == 'L') {
            return new LongArrayTag(this.n_1700_B(LongArrayTag.n_1700_B, q_2567_I.n_1700_B));
        }
        if (c0 == 'I') {
            return new IntArrayTag(this.n_1700_B(IntArrayTag.n_1700_B, IntTag.n_1700_B));
        }
        this.h_1847_R.setCursor(i);
        throw u_1723_Y.createWithContext((ImmutableStringReader)this.h_1847_R, (Object)String.valueOf(c0));
    }

    private <T extends Number> List<T> n_1700_B(TagType<?> arrayType, TagType<?> numberType) throws CommandSyntaxException {
        ArrayList list = Lists.newArrayList();
        while (this.h_1847_R.peek() != ']') {
            int i = this.h_1847_R.getCursor();
            Tag inbt = this.G_564_y();
            TagType<?> inbttype = inbt.J_1907_R();
            if (inbttype != numberType) {
                this.h_1847_R.setCursor(i);
                throw P_1922_E.createWithContext((ImmutableStringReader)this.h_1847_R, (Object)inbttype.J_1907_R(), (Object)arrayType.J_1907_R());
            }
            if (numberType == L_3985_e.n_1700_B) {
                list.add(((NumericTag)inbt).w_1484_f());
            } else if (numberType == q_2567_I.n_1700_B) {
                list.add(((NumericTag)inbt).P_1922_E());
            } else {
                list.add(((NumericTag)inbt).u_1723_Y());
            }
            if (!this.t_148_a()) break;
            if (this.h_1847_R.canRead()) continue;
            throw R_4764_Y.createWithContext((ImmutableStringReader)this.h_1847_R);
        }
        this.n_1700_B(']');
        return list;
    }

    private boolean t_148_a() {
        this.h_1847_R.skipWhitespace();
        if (this.h_1847_R.canRead() && this.h_1847_R.peek() == ',') {
            this.h_1847_R.skip();
            this.h_1847_R.skipWhitespace();
            return true;
        }
        return false;
    }

    private void n_1700_B(char expected) throws CommandSyntaxException {
        this.h_1847_R.skipWhitespace();
        this.h_1847_R.expect(expected);
    }
}


