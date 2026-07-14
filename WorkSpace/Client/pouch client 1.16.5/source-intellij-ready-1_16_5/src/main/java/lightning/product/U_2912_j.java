/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lightning.product.D_908_R;
import lightning.product.StringTag;
import lightning.product.LongArrayTag;
import lightning.product.MutableComponent;
import lightning.product.L_3985_e;
import lightning.product.T_2717_K;
import lightning.product.NumericTag;
import lightning.product.U_2871_b;
import lightning.product.IntArrayTag;
import lightning.product.Y_3433_n;
import lightning.product.Tag;
import lightning.product.a_969_m;
import lightning.product.ByteArrayTag;
import lightning.product.l_4118_l;
import lightning.product.TagType;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.n_3832_I;
import lightning.product.o_926_S;
import lightning.product.q_2567_I;
import lightning.product.q_2896_o;
import lightning.product.CrashReportCategory;
import lightning.product.IntTag;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class U_2912_j
implements Tag {
    public static final Codec<U_2912_j> n_1700_B = Codec.PASSTHROUGH.comapFlatMap(dynamic -> {
        Tag inbt = (Tag)dynamic.convert((DynamicOps)l_4118_l.n_1700_B).getValue();
        return inbt instanceof U_2912_j ? DataResult.success((Object)((U_2912_j)inbt)) : DataResult.error((String)("Not a compound tag: " + String.valueOf(inbt)));
    }, nbt -> new Dynamic((DynamicOps)l_4118_l.n_1700_B, nbt));
    private static final Logger R_4764_Y = LogManager.getLogger();
    private static final Pattern w_1484_f = Pattern.compile("[A-Za-z0-9._+-]+");
    public static final TagType<U_2912_j> J_1907_R = new TagType<U_2912_j>(){

        public U_2912_j n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
            byte b0;
            accounter.n_1700_B(384L);
            if (depth > 512) {
                throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
            }
            HashMap map = Maps.newHashMap();
            while ((b0 = U_2912_j.n_1700_B(input, accounter)) != 0) {
                String s = U_2912_j.J_1907_R(input, accounter);
                accounter.n_1700_B(224 + 16 * s.length());
                Tag inbt = U_2912_j.n_1700_B(Y_3433_n.n_1700_B(b0), s, input, depth + 1, accounter);
                if (map.put(s, inbt) == null) continue;
                accounter.n_1700_B(288L);
            }
            return new U_2912_j(map);
        }

        @Override
        public String n_1700_B() {
            return "COMPOUND";
        }

        @Override
        public String J_1907_R() {
            return "TAG_Compound";
        }

        @Override
        public /* synthetic */ Tag J_1907_R(DataInput dataInput, int n, o_926_S o_926_S2) throws IOException {
            return this.n_1700_B(dataInput, n, o_926_S2);
        }
    };
    private final Map<String, Tag> t_148_a;

    protected U_2912_j(Map<String, Tag> tagMap) {
        this.t_148_a = tagMap;
    }

    public U_2912_j() {
        this(Maps.newHashMap());
    }

    @Override
    public void n_1700_B(DataOutput output) throws IOException {
        for (String s : this.t_148_a.keySet()) {
            Tag inbt = this.t_148_a.get(s);
            U_2912_j.n_1700_B(s, inbt, output);
        }
        output.writeByte(0);
    }

    public Set<String> G_564_y() {
        return this.t_148_a.keySet();
    }

    @Override
    public byte n_1700_B() {
        return 10;
    }

    public TagType<U_2912_j> J_1907_R() {
        return J_1907_R;
    }

    public int P_1922_E() {
        return this.t_148_a.size();
    }

    @Nullable
    public Tag n_1700_B(String key, Tag value) {
        return this.t_148_a.put(key, value);
    }

    public void n_1700_B(String key, byte value) {
        this.t_148_a.put(key, L_3985_e.n_1700_B(value));
    }

    public void n_1700_B(String key, short value) {
        this.t_148_a.put(key, a_969_m.n_1700_B(value));
    }

    public void J_1907_R(String key, int value) {
        this.t_148_a.put(key, IntTag.n_1700_B(value));
    }

    public void n_1700_B(String key, long value) {
        this.t_148_a.put(key, q_2567_I.n_1700_B(value));
    }

    public void n_1700_B(String key, UUID value) {
        this.t_148_a.put(key, n_3832_I.n_1700_B(value));
    }

    public UUID n_1700_B(String key) {
        return n_3832_I.n_1700_B(this.R_4764_Y(key));
    }

    public boolean J_1907_R(String key) {
        Tag inbt = this.R_4764_Y(key);
        return inbt != null && inbt.J_1907_R() == IntArrayTag.n_1700_B && ((IntArrayTag)inbt).u_1723_Y().length == 4;
    }

    public void n_1700_B(String key, float value) {
        this.t_148_a.put(key, T_2717_K.n_1700_B(value));
    }

    public void n_1700_B(String key, double value) {
        this.t_148_a.put(key, D_908_R.n_1700_B(value));
    }

    public void n_1700_B(String key, String value) {
        this.t_148_a.put(key, StringTag.n_1700_B(value));
    }

    public void n_1700_B(String key, byte[] value) {
        this.t_148_a.put(key, new ByteArrayTag(value));
    }

    public void n_1700_B(String key, int[] value) {
        this.t_148_a.put(key, new IntArrayTag(value));
    }

    public void n_1700_B(String key, List<Integer> value) {
        this.t_148_a.put(key, new IntArrayTag(value));
    }

    public void n_1700_B(String key, long[] value) {
        this.t_148_a.put(key, new LongArrayTag(value));
    }

    public void J_1907_R(String key, List<Long> value) {
        this.t_148_a.put(key, new LongArrayTag(value));
    }

    public void n_1700_B(String key, boolean value) {
        this.t_148_a.put(key, L_3985_e.n_1700_B(value));
    }

    @Nullable
    public Tag R_4764_Y(String key) {
        return this.t_148_a.get(key);
    }

    public byte G_564_y(String key) {
        Tag inbt = this.t_148_a.get(key);
        return inbt == null ? (byte)0 : inbt.n_1700_B();
    }

    public boolean P_1922_E(String key) {
        return this.t_148_a.containsKey(key);
    }

    public boolean R_4764_Y(String key, int type) {
        byte i = this.G_564_y(key);
        if (i == type) {
            return true;
        }
        if (type != 99) {
            return false;
        }
        return i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6;
    }

    public byte u_1723_Y(String key) {
        try {
            if (this.R_4764_Y(key, 99)) {
                return ((NumericTag)this.t_148_a.get(key)).w_1484_f();
            }
        }
        catch (ClassCastException classCastException) {
            // empty catch block
        }
        return 0;
    }

    public short v_4262_N(String key) {
        try {
            if (this.R_4764_Y(key, 99)) {
                return ((NumericTag)this.t_148_a.get(key)).v_4262_N();
            }
        }
        catch (ClassCastException classCastException) {
            // empty catch block
        }
        return 0;
    }

    public int w_1484_f(String key) {
        try {
            if (this.R_4764_Y(key, 99)) {
                return ((NumericTag)this.t_148_a.get(key)).u_1723_Y();
            }
        }
        catch (ClassCastException classCastException) {
            // empty catch block
        }
        return 0;
    }

    public long t_148_a(String key) {
        try {
            if (this.R_4764_Y(key, 99)) {
                return ((NumericTag)this.t_148_a.get(key)).P_1922_E();
            }
        }
        catch (ClassCastException classCastException) {
            // empty catch block
        }
        return 0L;
    }

    public float s_956_w(String key) {
        try {
            if (this.R_4764_Y(key, 99)) {
                return ((NumericTag)this.t_148_a.get(key)).s_956_w();
            }
        }
        catch (ClassCastException classCastException) {
            // empty catch block
        }
        return 0.0f;
    }

    public double u_2550_I(String key) {
        try {
            if (this.R_4764_Y(key, 99)) {
                return ((NumericTag)this.t_148_a.get(key)).t_148_a();
            }
        }
        catch (ClassCastException classCastException) {
            // empty catch block
        }
        return 0.0;
    }

    public String M_588_G(String key) {
        try {
            if (this.R_4764_Y(key, 8)) {
                return this.t_148_a.get(key).M_588_G();
            }
        }
        catch (ClassCastException classCastException) {
            // empty catch block
        }
        return "";
    }

    public byte[] P_4830_p(String key) {
        try {
            if (this.R_4764_Y(key, 7)) {
                return ((ByteArrayTag)this.t_148_a.get(key)).G_564_y();
            }
        }
        catch (ClassCastException classcastexception) {
            throw new ReportedException(this.n_1700_B(key, ByteArrayTag.n_1700_B, classcastexception));
        }
        return new byte[0];
    }

    public int[] h_1847_R(String key) {
        try {
            if (this.R_4764_Y(key, 11)) {
                return ((IntArrayTag)this.t_148_a.get(key)).u_1723_Y();
            }
        }
        catch (ClassCastException classcastexception) {
            throw new ReportedException(this.n_1700_B(key, IntArrayTag.n_1700_B, classcastexception));
        }
        return new int[0];
    }

    public long[] Q_4569_t(String key) {
        try {
            if (this.R_4764_Y(key, 12)) {
                return ((LongArrayTag)this.t_148_a.get(key)).u_1723_Y();
            }
        }
        catch (ClassCastException classcastexception) {
            throw new ReportedException(this.n_1700_B(key, LongArrayTag.n_1700_B, classcastexception));
        }
        return new long[0];
    }

    public U_2912_j M_182_A(String key) {
        try {
            if (this.R_4764_Y(key, 10)) {
                return (U_2912_j)this.t_148_a.get(key);
            }
        }
        catch (ClassCastException classcastexception) {
            throw new ReportedException(this.n_1700_B(key, J_1907_R, classcastexception));
        }
        return new U_2912_j();
    }

    public q_2896_o G_564_y(String key, int type) {
        try {
            if (this.G_564_y(key) == 9) {
                q_2896_o listnbt = (q_2896_o)this.t_148_a.get(key);
                if (!listnbt.isEmpty() && listnbt.P_1922_E() != type) {
                    return new q_2896_o();
                }
                return listnbt;
            }
        }
        catch (ClassCastException classcastexception) {
            throw new ReportedException(this.n_1700_B(key, q_2896_o.n_1700_B, classcastexception));
        }
        return new q_2896_o();
    }

    public boolean t_1786_h(String key) {
        return this.u_1723_Y(key) != 0;
    }

    public void multiplayerClientSuggestionProvider(String key) {
        this.t_148_a.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder("{");
        Collection<String> collection = this.t_148_a.keySet();
        if (R_4764_Y.isDebugEnabled()) {
            ArrayList list = Lists.newArrayList(this.t_148_a.keySet());
            Collections.sort(list);
            collection = list;
        }
        for (String s : collection) {
            if (stringbuilder.length() != 1) {
                stringbuilder.append(',');
            }
            stringbuilder.append(U_2912_j.w_1457_N(s)).append(':').append(this.t_148_a.get(s));
        }
        return stringbuilder.append('}').toString();
    }

    public boolean u_1723_Y() {
        return this.t_148_a.isEmpty();
    }

    private n_3236_c n_1700_B(String tagName, TagType<?> type, ClassCastException exception) {
        n_3236_c crashreport = n_3236_c.n_1700_B(exception, "Reading NBT data");
        CrashReportCategory crashreportcategory = crashreport.n_1700_B("Corrupt NBT tag", 1);
        crashreportcategory.n_1700_B("Tag type found", () -> this.t_148_a.get(tagName).J_1907_R().n_1700_B());
        crashreportcategory.n_1700_B("Tag type expected", type::n_1700_B);
        crashreportcategory.n_1700_B("Tag name", tagName);
        return crashreport;
    }

    public U_2912_j v_4262_N() {
        HashMap map = Maps.newHashMap((Map)Maps.transformValues(this.t_148_a, Tag::R_4764_Y));
        return new U_2912_j(map);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ instanceof U_2912_j && Objects.equals(this.t_148_a, ((U_2912_j)p_equals_1_).t_148_a);
    }

    public int hashCode() {
        return this.t_148_a.hashCode();
    }

    private static void n_1700_B(String name, Tag data, DataOutput output) throws IOException {
        output.writeByte(data.n_1700_B());
        if (data.n_1700_B() != 0) {
            output.writeUTF(name);
            data.n_1700_B(output);
        }
    }

    private static byte n_1700_B(DataInput input, o_926_S sizeTracker) throws IOException {
        return input.readByte();
    }

    private static String J_1907_R(DataInput input, o_926_S sizeTracker) throws IOException {
        return input.readUTF();
    }

    private static Tag n_1700_B(TagType<?> type, String name, DataInput input, int depth, o_926_S accounter) {
        try {
            return type.J_1907_R(input, depth, accounter);
        }
        catch (IOException ioexception) {
            n_3236_c crashreport = n_3236_c.n_1700_B(ioexception, "Loading NBT data");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("NBT Tag");
            crashreportcategory.n_1700_B("Tag name", name);
            crashreportcategory.n_1700_B("Tag type", type.n_1700_B());
            throw new ReportedException(crashreport);
        }
    }

    public U_2912_j n_1700_B(U_2912_j other) {
        for (String s : other.t_148_a.keySet()) {
            Tag inbt = other.t_148_a.get(s);
            if (inbt.n_1700_B() == 10) {
                if (this.R_4764_Y(s, 10)) {
                    U_2912_j compoundnbt = this.M_182_A(s);
                    compoundnbt.n_1700_B((U_2912_j)inbt);
                    continue;
                }
                this.n_1700_B(s, inbt.R_4764_Y());
                continue;
            }
            this.n_1700_B(s, inbt.R_4764_Y());
        }
        return this;
    }

    protected static String w_1457_N(String name) {
        return w_1484_f.matcher(name).matches() ? name : StringTag.J_1907_R(name);
    }

    protected static x_282_a Y_601_j(String name) {
        if (w_1484_f.matcher(name).matches()) {
            return new U_2871_b(name).n_1700_B(G_564_y);
        }
        String s = StringTag.J_1907_R(name);
        String s1 = s.substring(0, 1);
        MutableComponent itextcomponent = new U_2871_b(s.substring(1, s.length() - 1)).n_1700_B(G_564_y);
        return new U_2871_b(s1).n_1700_B(itextcomponent).n_1700_B(s1);
    }

    @Override
    public x_282_a n_1700_B(String indentation, int indentDepth) {
        if (this.t_148_a.isEmpty()) {
            return new U_2871_b("{}");
        }
        U_2871_b iformattabletextcomponent = new U_2871_b("{");
        Collection<String> collection = this.t_148_a.keySet();
        if (R_4764_Y.isDebugEnabled()) {
            ArrayList list = Lists.newArrayList(this.t_148_a.keySet());
            Collections.sort(list);
            collection = list;
        }
        if (!indentation.isEmpty()) {
            iformattabletextcomponent.n_1700_B("\n");
        }
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            String s = (String)iterator.next();
            MutableComponent iformattabletextcomponent1 = new U_2871_b(Strings.repeat((String)indentation, (int)(indentDepth + 1))).n_1700_B(U_2912_j.Y_601_j(s)).n_1700_B(String.valueOf(':')).n_1700_B(" ").n_1700_B(this.t_148_a.get(s).n_1700_B(indentation, indentDepth + 1));
            if (iterator.hasNext()) {
                iformattabletextcomponent1.n_1700_B(String.valueOf(',')).n_1700_B(indentation.isEmpty() ? " " : "\n");
            }
            iformattabletextcomponent.n_1700_B(iformattabletextcomponent1);
        }
        if (!indentation.isEmpty()) {
            iformattabletextcomponent.n_1700_B("\n").n_1700_B(Strings.repeat((String)indentation, (int)indentDepth));
        }
        iformattabletextcomponent.n_1700_B("}");
        return iformattabletextcomponent;
    }

    protected Map<String, Tag> w_1484_f() {
        return Collections.unmodifiableMap(this.t_148_a);
    }

    @Override
    public /* synthetic */ Tag R_4764_Y() {
        return this.v_4262_N();
    }
}


