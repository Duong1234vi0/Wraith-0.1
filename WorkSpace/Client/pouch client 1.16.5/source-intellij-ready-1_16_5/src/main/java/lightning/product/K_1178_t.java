/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  org.apache.commons.lang3.mutable.MutableBoolean
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.lang.invoke.LambdaMetafactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lightning.product.F_2904_S;
import lightning.product.U_2912_j;
import lightning.product.Tag;
import lightning.product.CollectionTag;
import lightning.product.n_3832_I;
import lightning.product.q_2896_o;
import lightning.product.r_4318_c;
import lightning.product.y_2498_m;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class K_1178_t
implements ArgumentType<v_4262_N> {
    private static final Collection<String> R_4764_Y = Arrays.asList("foo", "foo.bar", "foo[0]", "[0]", "[]", "{foo=bar}");
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("arguments.nbtpath.node.invalid"));
    public static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208665_0_ -> new F_2904_S("arguments.nbtpath.nothing_found", p_208665_0_));

    public static K_1178_t n_1700_B() {
        return new K_1178_t();
    }

    public static v_4262_N n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (v_4262_N)context.getArgument(name, v_4262_N.class);
    }

    public v_4262_N n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        ArrayList list = Lists.newArrayList();
        int i = p_parse_1_.getCursor();
        Object2IntOpenHashMap object2intmap = new Object2IntOpenHashMap();
        boolean flag = true;
        while (p_parse_1_.canRead() && p_parse_1_.peek() != ' ') {
            char c0;
            G_564_y nbtpathargument$inode = K_1178_t.n_1700_B(p_parse_1_, flag);
            list.add(nbtpathargument$inode);
            object2intmap.put((Object)nbtpathargument$inode, p_parse_1_.getCursor() - i);
            flag = false;
            if (!p_parse_1_.canRead() || (c0 = p_parse_1_.peek()) == ' ' || c0 == '[' || c0 == '{') continue;
            p_parse_1_.expect('.');
        }
        return new v_4262_N(p_parse_1_.getString().substring(i, p_parse_1_.getCursor()), list.toArray(new G_564_y[0]), (Object2IntMap<G_564_y>)object2intmap);
    }

    private static G_564_y n_1700_B(StringReader p_218079_0_, boolean p_218079_1_) throws CommandSyntaxException {
        switch (p_218079_0_.peek()) {
            case '\"': {
                String s = p_218079_0_.readString();
                return K_1178_t.n_1700_B(p_218079_0_, s);
            }
            case '[': {
                p_218079_0_.skip();
                char j = p_218079_0_.peek();
                if (j == '{') {
                    U_2912_j compoundnbt1 = new r_4318_c(p_218079_0_).u_1723_Y();
                    p_218079_0_.expect(']');
                    return new u_1723_Y(compoundnbt1);
                }
                if (j == ']') {
                    p_218079_0_.skip();
                    return lightning.product.K_1178_t$R_4764_Y.n_1700_B;
                }
                int i = p_218079_0_.readInt();
                p_218079_0_.expect(']');
                return new n_1700_B(i);
            }
            case '{': {
                if (!p_218079_1_) {
                    throw n_1700_B.createWithContext((ImmutableStringReader)p_218079_0_);
                }
                U_2912_j compoundnbt = new r_4318_c(p_218079_0_).u_1723_Y();
                return new J_1907_R(compoundnbt);
            }
        }
        String s1 = K_1178_t.J_1907_R(p_218079_0_);
        return K_1178_t.n_1700_B(p_218079_0_, s1);
    }

    private static G_564_y n_1700_B(StringReader p_218083_0_, String p_218083_1_) throws CommandSyntaxException {
        if (p_218083_0_.canRead() && p_218083_0_.peek() == '{') {
            U_2912_j compoundnbt = new r_4318_c(p_218083_0_).u_1723_Y();
            return new P_1922_E(p_218083_1_, compoundnbt);
        }
        return new w_1484_f(p_218083_1_);
    }

    private static String J_1907_R(StringReader p_197151_0_) throws CommandSyntaxException {
        int i = p_197151_0_.getCursor();
        while (p_197151_0_.canRead() && K_1178_t.n_1700_B(p_197151_0_.peek())) {
            p_197151_0_.skip();
        }
        if (p_197151_0_.getCursor() == i) {
            throw n_1700_B.createWithContext((ImmutableStringReader)p_197151_0_);
        }
        return p_197151_0_.getString().substring(i, p_197151_0_.getCursor());
    }

    public Collection<String> getExamples() {
        return R_4764_Y;
    }

    private static boolean n_1700_B(char ch) {
        return ch != ' ' && ch != '\"' && ch != '[' && ch != ']' && ch != '.' && ch != '{' && ch != '}';
    }

    private static Predicate<Tag> n_1700_B(U_2912_j p_218080_0_) {
        return p_218081_1_ -> n_3832_I.n_1700_B(p_218080_0_, p_218081_1_, true);
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    public static class v_4262_N {
        private final String n_1700_B;
        private final Object2IntMap<G_564_y> J_1907_R;
        private final G_564_y[] R_4764_Y;

        public v_4262_N(String p_i51148_1_, G_564_y[] p_i51148_2_, Object2IntMap<G_564_y> p_i51148_3_) {
            this.n_1700_B = p_i51148_1_;
            this.R_4764_Y = p_i51148_2_;
            this.J_1907_R = p_i51148_3_;
        }

        public List<Tag> n_1700_B(Tag p_218071_1_) throws CommandSyntaxException {
            List<Tag> list = Collections.singletonList(p_218071_1_);
            for (G_564_y nbtpathargument$inode : this.R_4764_Y) {
                if (!(list = nbtpathargument$inode.n_1700_B(list)).isEmpty()) continue;
                throw this.n_1700_B(nbtpathargument$inode);
            }
            return list;
        }

        public int J_1907_R(Tag p_218069_1_) {
            List<Tag> list = Collections.singletonList(p_218069_1_);
            for (G_564_y nbtpathargument$inode : this.R_4764_Y) {
                if (!(list = nbtpathargument$inode.n_1700_B(list)).isEmpty()) continue;
                return 0;
            }
            return list.size();
        }

        private List<Tag> G_564_y(Tag p_218072_1_) throws CommandSyntaxException {
            List<Tag> list = Collections.singletonList(p_218072_1_);
            for (int i = 0; i < this.R_4764_Y.length - 1; ++i) {
                G_564_y nbtpathargument$inode = this.R_4764_Y[i];
                int j = i + 1;
                if (!(list = nbtpathargument$inode.n_1700_B(list, this.R_4764_Y[j]::n_1700_B)).isEmpty()) continue;
                throw this.n_1700_B(nbtpathargument$inode);
            }
            return list;
        }

        public List<Tag> n_1700_B(Tag p_218073_1_, Supplier<Tag> p_218073_2_) throws CommandSyntaxException {
            List<Tag> list = this.G_564_y(p_218073_1_);
            G_564_y nbtpathargument$inode = this.R_4764_Y[this.R_4764_Y.length - 1];
            return nbtpathargument$inode.n_1700_B(list, p_218073_2_);
        }

        private static int n_1700_B(List<Tag> p_218075_0_, Function<Tag, Integer> p_218075_1_) {
            return p_218075_0_.stream().map(p_218075_1_).reduce(0, (p_218074_0_, p_218074_1_) -> p_218074_0_ + p_218074_1_);
        }

        public int J_1907_R(Tag p_218076_1_, Supplier<Tag> p_218076_2_) throws CommandSyntaxException {
            List<Tag> list = this.G_564_y(p_218076_1_);
            G_564_y nbtpathargument$inode = this.R_4764_Y[this.R_4764_Y.length - 1];
            return v_4262_N.n_1700_B(list, (Tag p_218077_2_) -> nbtpathargument$inode.n_1700_B((Tag)p_218077_2_, p_218076_2_));
        }

        public int R_4764_Y(Tag p_218068_1_) {
            List<Tag> list = Collections.singletonList(p_218068_1_);
            for (int i = 0; i < this.R_4764_Y.length - 1; ++i) {
                list = this.R_4764_Y[i].n_1700_B(list);
            }
            G_564_y nbtpathargument$inode = this.R_4764_Y[this.R_4764_Y.length - 1];
            return v_4262_N.n_1700_B(list, nbtpathargument$inode::n_1700_B);
        }

        private CommandSyntaxException n_1700_B(G_564_y p_218070_1_) {
            int i = this.J_1907_R.getInt((Object)p_218070_1_);
            return J_1907_R.create((Object)this.n_1700_B.substring(0, i));
        }

        public String toString() {
            return this.n_1700_B;
        }
    }

    static interface G_564_y {
        public void n_1700_B(Tag var1, List<Tag> var2);

        public void n_1700_B(Tag var1, Supplier<Tag> var2, List<Tag> var3);

        public Tag n_1700_B();

        public int n_1700_B(Tag var1, Supplier<Tag> var2);

        public int n_1700_B(Tag var1);

        default public List<Tag> n_1700_B(List<Tag> p_218056_1_) {
            return this.n_1700_B(p_218056_1_, this::n_1700_B);
        }

        default public List<Tag> n_1700_B(List<Tag> p_218052_1_, Supplier<Tag> p_218052_2_) {
            return this.n_1700_B(p_218052_1_, (Tag p_218055_2_, List<Tag> p_218055_3_) -> this.n_1700_B((Tag)p_218055_2_, p_218052_2_, (List<Tag>)p_218055_3_));
        }

        default public List<Tag> n_1700_B(List<Tag> p_218057_1_, BiConsumer<Tag, List<Tag>> p_218057_2_) {
            ArrayList list = Lists.newArrayList();
            for (Tag inbt : p_218057_1_) {
                p_218057_2_.accept(inbt, list);
            }
            return list;
        }
    }

    static class u_1723_Y
    implements G_564_y {
        private final U_2912_j n_1700_B;
        private final Predicate<Tag> J_1907_R;

        public u_1723_Y(U_2912_j p_i51151_1_) {
            this.n_1700_B = p_i51151_1_;
            this.J_1907_R = K_1178_t.n_1700_B(p_i51151_1_);
        }

        @Override
        public void n_1700_B(Tag p_218050_1_, List<Tag> p_218050_2_) {
            if (p_218050_1_ instanceof q_2896_o) {
                q_2896_o listnbt = (q_2896_o)p_218050_1_;
                listnbt.stream().filter(this.J_1907_R).forEach(p_218050_2_::add);
            }
        }

        @Override
        public void n_1700_B(Tag p_218054_1_, Supplier<Tag> p_218054_2_, List<Tag> p_218054_3_) {
            MutableBoolean mutableboolean = new MutableBoolean();
            if (p_218054_1_ instanceof q_2896_o) {
                q_2896_o listnbt = (q_2896_o)p_218054_1_;
                listnbt.stream().filter(this.J_1907_R).forEach(p_218060_2_ -> {
                    p_218054_3_.add((Tag)p_218060_2_);
                    mutableboolean.setTrue();
                });
                if (mutableboolean.isFalse()) {
                    U_2912_j compoundnbt = this.n_1700_B.v_4262_N();
                    listnbt.add(compoundnbt);
                    p_218054_3_.add(compoundnbt);
                }
            }
        }

        @Override
        public Tag n_1700_B() {
            return new q_2896_o();
        }

        @Override
        public int n_1700_B(Tag p_218051_1_, Supplier<Tag> p_218051_2_) {
            int i = 0;
            if (p_218051_1_ instanceof q_2896_o) {
                q_2896_o listnbt = (q_2896_o)p_218051_1_;
                int j = listnbt.size();
                if (j == 0) {
                    listnbt.add(p_218051_2_.get());
                    ++i;
                } else {
                    for (int k = 0; k < j; ++k) {
                        Tag inbt1;
                        Tag inbt = listnbt.s_956_w(k);
                        if (!this.J_1907_R.test(inbt) || (inbt1 = p_218051_2_.get()).equals(inbt) || !listnbt.n_1700_B(k, inbt1)) continue;
                        ++i;
                    }
                }
            }
            return i;
        }

        @Override
        public int n_1700_B(Tag p_218053_1_) {
            int i = 0;
            if (p_218053_1_ instanceof q_2896_o) {
                q_2896_o listnbt = (q_2896_o)p_218053_1_;
                for (int j = listnbt.size() - 1; j >= 0; --j) {
                    if (!this.J_1907_R.test(listnbt.s_956_w(j))) continue;
                    listnbt.R_4764_Y(j);
                    ++i;
                }
            }
            return i;
        }
    }

    static class R_4764_Y
    implements G_564_y {
        public static final R_4764_Y n_1700_B = new R_4764_Y();

        private R_4764_Y() {
        }

        @Override
        public void n_1700_B(Tag p_218050_1_, List<Tag> p_218050_2_) {
            if (p_218050_1_ instanceof CollectionTag) {
                p_218050_2_.addAll((CollectionTag)p_218050_1_);
            }
        }

        @Override
        public void n_1700_B(Tag p_218054_1_, Supplier<Tag> p_218054_2_, List<Tag> p_218054_3_) {
            if (p_218054_1_ instanceof CollectionTag) {
                CollectionTag collectionnbt = (CollectionTag)p_218054_1_;
                if (collectionnbt.isEmpty()) {
                    Tag inbt = p_218054_2_.get();
                    if (collectionnbt.J_1907_R(0, inbt)) {
                        p_218054_3_.add(inbt);
                    }
                } else {
                    p_218054_3_.addAll(collectionnbt);
                }
            }
        }

        @Override
        public Tag n_1700_B() {
            return new q_2896_o();
        }

        @Override
        public int n_1700_B(Tag p_218051_1_, Supplier<Tag> p_218051_2_) {
            if (!(p_218051_1_ instanceof CollectionTag)) {
                return 0;
            }
            CollectionTag collectionnbt = (CollectionTag)p_218051_1_;
            int i = collectionnbt.size();
            if (i == 0) {
                collectionnbt.J_1907_R(0, p_218051_2_.get());
                return 1;
            }
            Tag inbt = p_218051_2_.get();
            int j = i - (int)collectionnbt.stream().filter((Predicate<Tag>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, equals(java.lang.Object ), (Llightning/product/Tag;)Z)((Tag)inbt)).count();
            if (j == 0) {
                return 0;
            }
            collectionnbt.clear();
            if (!collectionnbt.J_1907_R(0, inbt)) {
                return 0;
            }
            for (int k = 1; k < i; ++k) {
                collectionnbt.J_1907_R(k, p_218051_2_.get());
            }
            return j;
        }

        @Override
        public int n_1700_B(Tag p_218053_1_) {
            CollectionTag collectionnbt;
            int i;
            if (p_218053_1_ instanceof CollectionTag && (i = (collectionnbt = (CollectionTag)p_218053_1_).size()) > 0) {
                collectionnbt.clear();
                return i;
            }
            return 0;
        }
    }

    static class n_1700_B
    implements G_564_y {
        private final int n_1700_B;

        public n_1700_B(int p_i51153_1_) {
            this.n_1700_B = p_i51153_1_;
        }

        @Override
        public void n_1700_B(Tag p_218050_1_, List<Tag> p_218050_2_) {
            if (p_218050_1_ instanceof CollectionTag) {
                int j;
                CollectionTag collectionnbt = (CollectionTag)p_218050_1_;
                int i = collectionnbt.size();
                int n = j = this.n_1700_B < 0 ? i + this.n_1700_B : this.n_1700_B;
                if (0 <= j && j < i) {
                    p_218050_2_.add((Tag)collectionnbt.get(j));
                }
            }
        }

        @Override
        public void n_1700_B(Tag p_218054_1_, Supplier<Tag> p_218054_2_, List<Tag> p_218054_3_) {
            this.n_1700_B(p_218054_1_, p_218054_3_);
        }

        @Override
        public Tag n_1700_B() {
            return new q_2896_o();
        }

        @Override
        public int n_1700_B(Tag p_218051_1_, Supplier<Tag> p_218051_2_) {
            if (p_218051_1_ instanceof CollectionTag) {
                int j;
                CollectionTag collectionnbt = (CollectionTag)p_218051_1_;
                int i = collectionnbt.size();
                int n = j = this.n_1700_B < 0 ? i + this.n_1700_B : this.n_1700_B;
                if (0 <= j && j < i) {
                    Tag inbt = (Tag)collectionnbt.get(j);
                    Tag inbt1 = p_218051_2_.get();
                    if (!inbt1.equals(inbt) && collectionnbt.n_1700_B(j, inbt1)) {
                        return 1;
                    }
                }
            }
            return 0;
        }

        @Override
        public int n_1700_B(Tag p_218053_1_) {
            if (p_218053_1_ instanceof CollectionTag) {
                int j;
                CollectionTag collectionnbt = (CollectionTag)p_218053_1_;
                int i = collectionnbt.size();
                int n = j = this.n_1700_B < 0 ? i + this.n_1700_B : this.n_1700_B;
                if (0 <= j && j < i) {
                    collectionnbt.R_4764_Y(j);
                    return 1;
                }
            }
            return 0;
        }
    }

    static class J_1907_R
    implements G_564_y {
        private final Predicate<Tag> n_1700_B;

        public J_1907_R(U_2912_j p_i51149_1_) {
            this.n_1700_B = K_1178_t.n_1700_B(p_i51149_1_);
        }

        @Override
        public void n_1700_B(Tag p_218050_1_, List<Tag> p_218050_2_) {
            if (p_218050_1_ instanceof U_2912_j && this.n_1700_B.test(p_218050_1_)) {
                p_218050_2_.add(p_218050_1_);
            }
        }

        @Override
        public void n_1700_B(Tag p_218054_1_, Supplier<Tag> p_218054_2_, List<Tag> p_218054_3_) {
            this.n_1700_B(p_218054_1_, p_218054_3_);
        }

        @Override
        public Tag n_1700_B() {
            return new U_2912_j();
        }

        @Override
        public int n_1700_B(Tag p_218051_1_, Supplier<Tag> p_218051_2_) {
            return 0;
        }

        @Override
        public int n_1700_B(Tag p_218053_1_) {
            return 0;
        }
    }

    static class P_1922_E
    implements G_564_y {
        private final String n_1700_B;
        private final U_2912_j J_1907_R;
        private final Predicate<Tag> R_4764_Y;

        public P_1922_E(String p_i51150_1_, U_2912_j p_i51150_2_) {
            this.n_1700_B = p_i51150_1_;
            this.J_1907_R = p_i51150_2_;
            this.R_4764_Y = K_1178_t.n_1700_B(p_i51150_2_);
        }

        @Override
        public void n_1700_B(Tag p_218050_1_, List<Tag> p_218050_2_) {
            Tag inbt;
            if (p_218050_1_ instanceof U_2912_j && this.R_4764_Y.test(inbt = ((U_2912_j)p_218050_1_).R_4764_Y(this.n_1700_B))) {
                p_218050_2_.add(inbt);
            }
        }

        @Override
        public void n_1700_B(Tag p_218054_1_, Supplier<Tag> p_218054_2_, List<Tag> p_218054_3_) {
            if (p_218054_1_ instanceof U_2912_j) {
                U_2912_j compoundnbt = (U_2912_j)p_218054_1_;
                Tag inbt = compoundnbt.R_4764_Y(this.n_1700_B);
                if (inbt == null) {
                    U_2912_j compoundnbt1 = this.J_1907_R.v_4262_N();
                    compoundnbt.n_1700_B(this.n_1700_B, compoundnbt1);
                    p_218054_3_.add(compoundnbt1);
                } else if (this.R_4764_Y.test(inbt)) {
                    p_218054_3_.add(inbt);
                }
            }
        }

        @Override
        public Tag n_1700_B() {
            return new U_2912_j();
        }

        @Override
        public int n_1700_B(Tag p_218051_1_, Supplier<Tag> p_218051_2_) {
            Tag inbt1;
            U_2912_j compoundnbt;
            Tag inbt;
            if (p_218051_1_ instanceof U_2912_j && this.R_4764_Y.test(inbt = (compoundnbt = (U_2912_j)p_218051_1_).R_4764_Y(this.n_1700_B)) && !(inbt1 = p_218051_2_.get()).equals(inbt)) {
                compoundnbt.n_1700_B(this.n_1700_B, inbt1);
                return 1;
            }
            return 0;
        }

        @Override
        public int n_1700_B(Tag p_218053_1_) {
            U_2912_j compoundnbt;
            Tag inbt;
            if (p_218053_1_ instanceof U_2912_j && this.R_4764_Y.test(inbt = (compoundnbt = (U_2912_j)p_218053_1_).R_4764_Y(this.n_1700_B))) {
                compoundnbt.multiplayerClientSuggestionProvider(this.n_1700_B);
                return 1;
            }
            return 0;
        }
    }

    static class w_1484_f
    implements G_564_y {
        private final String n_1700_B;

        public w_1484_f(String p_i51154_1_) {
            this.n_1700_B = p_i51154_1_;
        }

        @Override
        public void n_1700_B(Tag p_218050_1_, List<Tag> p_218050_2_) {
            Tag inbt;
            if (p_218050_1_ instanceof U_2912_j && (inbt = ((U_2912_j)p_218050_1_).R_4764_Y(this.n_1700_B)) != null) {
                p_218050_2_.add(inbt);
            }
        }

        @Override
        public void n_1700_B(Tag p_218054_1_, Supplier<Tag> p_218054_2_, List<Tag> p_218054_3_) {
            if (p_218054_1_ instanceof U_2912_j) {
                Tag inbt;
                U_2912_j compoundnbt = (U_2912_j)p_218054_1_;
                if (compoundnbt.P_1922_E(this.n_1700_B)) {
                    inbt = compoundnbt.R_4764_Y(this.n_1700_B);
                } else {
                    inbt = p_218054_2_.get();
                    compoundnbt.n_1700_B(this.n_1700_B, inbt);
                }
                p_218054_3_.add(inbt);
            }
        }

        @Override
        public Tag n_1700_B() {
            return new U_2912_j();
        }

        @Override
        public int n_1700_B(Tag p_218051_1_, Supplier<Tag> p_218051_2_) {
            if (p_218051_1_ instanceof U_2912_j) {
                Tag inbt1;
                U_2912_j compoundnbt = (U_2912_j)p_218051_1_;
                Tag inbt = p_218051_2_.get();
                if (!inbt.equals(inbt1 = compoundnbt.n_1700_B(this.n_1700_B, inbt))) {
                    return 1;
                }
            }
            return 0;
        }

        @Override
        public int n_1700_B(Tag p_218053_1_) {
            U_2912_j compoundnbt;
            if (p_218053_1_ instanceof U_2912_j && (compoundnbt = (U_2912_j)p_218053_1_).P_1922_E(this.n_1700_B)) {
                compoundnbt.multiplayerClientSuggestionProvider(this.n_1700_B);
                return 1;
            }
            return 0;
        }
    }
}


