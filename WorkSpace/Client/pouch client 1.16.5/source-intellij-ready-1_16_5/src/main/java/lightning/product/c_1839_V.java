/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.List$ListType
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 *  it.unimi.dsi.fastutil.ints.Int2IntMap
 *  it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.ints.IntIterator
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 *  it.unimi.dsi.fastutil.ints.IntSet
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.List;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.A_1434_p;
import lightning.product.References;

public class c_1839_V
extends DataFix {
    private static final int[][] n_1700_B = new int[][]{{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
    private static final Object2IntMap<String> J_1907_R = (Object2IntMap)DataFixUtils.make((Object)new Object2IntOpenHashMap(), p_208417_0_ -> {
        p_208417_0_.put((Object)"minecraft:acacia_leaves", 0);
        p_208417_0_.put((Object)"minecraft:birch_leaves", 1);
        p_208417_0_.put((Object)"minecraft:dark_oak_leaves", 2);
        p_208417_0_.put((Object)"minecraft:jungle_leaves", 3);
        p_208417_0_.put((Object)"minecraft:oak_leaves", 4);
        p_208417_0_.put((Object)"minecraft:spruce_leaves", 5);
    });
    private static final Set<String> R_4764_Y = ImmutableSet.of((Object)"minecraft:acacia_bark", (Object)"minecraft:birch_bark", (Object)"minecraft:dark_oak_bark", (Object)"minecraft:jungle_bark", (Object)"minecraft:oak_bark", (Object)"minecraft:spruce_bark", (Object[])new String[]{"minecraft:acacia_log", "minecraft:birch_log", "minecraft:dark_oak_log", "minecraft:jungle_log", "minecraft:oak_log", "minecraft:spruce_log", "minecraft:stripped_acacia_log", "minecraft:stripped_birch_log", "minecraft:stripped_dark_oak_log", "minecraft:stripped_jungle_log", "minecraft:stripped_oak_log", "minecraft:stripped_spruce_log"});

    public c_1839_V(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.R_4764_Y);
        OpticFinder opticfinder = type.findField("Level");
        OpticFinder opticfinder1 = opticfinder.type().findField("Sections");
        Type type1 = opticfinder1.type();
        if (!(type1 instanceof List.ListType)) {
            throw new IllegalStateException("Expecting sections to be a list.");
        }
        Type type2 = ((List.ListType)type1).getElement();
        OpticFinder opticfinder2 = DSL.typeFinder((Type)type2);
        return this.fixTypeEverywhereTyped("Leaves fix", type, p_208422_4_ -> p_208422_4_.updateTyped(opticfinder, p_208420_3_ -> {
            int[] aint = new int[]{0};
            Typed typed = p_208420_3_.updateTyped(opticfinder1, p_208415_3_ -> {
                Int2ObjectOpenHashMap int2objectmap = new Int2ObjectOpenHashMap(p_208415_3_.getAllTyped(opticfinder2).stream().map(p_212527_1_ -> new n_1700_B((Typed<?>)p_212527_1_, this.getInputSchema())).collect(Collectors.toMap(J_1907_R::R_4764_Y, p_208410_0_ -> p_208410_0_)));
                if (int2objectmap.values().stream().allMatch(J_1907_R::J_1907_R)) {
                    return p_208415_3_;
                }
                ArrayList list = Lists.newArrayList();
                for (int i = 0; i < 7; ++i) {
                    list.add(new IntOpenHashSet());
                }
                for (n_1700_B leavesfix$leavessection : int2objectmap.values()) {
                    if (leavesfix$leavessection.J_1907_R()) continue;
                    for (int j = 0; j < 4096; ++j) {
                        int k = leavesfix$leavessection.R_4764_Y(j);
                        if (leavesfix$leavessection.n_1700_B(k)) {
                            ((IntSet)list.get(0)).add(leavesfix$leavessection.R_4764_Y() << 12 | j);
                            continue;
                        }
                        if (!leavesfix$leavessection.J_1907_R(k)) continue;
                        int l = this.n_1700_B(j);
                        int i1 = this.R_4764_Y(j);
                        aint[0] = aint[0] | c_1839_V.n_1700_B(l == 0, l == 15, i1 == 0, i1 == 15);
                    }
                }
                for (int j3 = 1; j3 < 7; ++j3) {
                    IntSet intset = (IntSet)list.get(j3 - 1);
                    IntSet intset1 = (IntSet)list.get(j3);
                    IntIterator intiterator = intset.iterator();
                    while (intiterator.hasNext()) {
                        int k3 = intiterator.nextInt();
                        int l3 = this.n_1700_B(k3);
                        int j1 = this.J_1907_R(k3);
                        int k1 = this.R_4764_Y(k3);
                        for (int[] aint1 : n_1700_B) {
                            int i3;
                            int k2;
                            int l2;
                            n_1700_B leavesfix$leavessection1;
                            int l1 = l3 + aint1[0];
                            int i2 = j1 + aint1[1];
                            int j2 = k1 + aint1[2];
                            if (l1 < 0 || l1 > 15 || j2 < 0 || j2 > 15 || i2 < 0 || i2 > 255 || (leavesfix$leavessection1 = (n_1700_B)int2objectmap.get(i2 >> 4)) == null || leavesfix$leavessection1.J_1907_R() || !leavesfix$leavessection1.J_1907_R(l2 = leavesfix$leavessection1.R_4764_Y(k2 = c_1839_V.n_1700_B(l1, i2 & 0xF, j2))) || (i3 = leavesfix$leavessection1.G_564_y(l2)) <= j3) continue;
                            leavesfix$leavessection1.n_1700_B(k2, l2, j3);
                            intset1.add(c_1839_V.n_1700_B(l1, i2, j2));
                        }
                    }
                }
                return p_208415_3_.updateTyped(opticfinder2, arg_0 -> c_1839_V.n_1700_B((Int2ObjectMap)int2objectmap, arg_0));
            });
            if (aint[0] != 0) {
                typed = typed.update(DSL.remainderFinder(), p_208419_1_ -> {
                    Dynamic dynamic = (Dynamic)DataFixUtils.orElse((Optional)p_208419_1_.get("UpgradeData").result(), (Object)p_208419_1_.emptyMap());
                    return p_208419_1_.set("UpgradeData", dynamic.set("Sides", p_208419_1_.createByte((byte)(dynamic.get("Sides").asByte((byte)0) | aint[0]))));
                });
            }
            return typed;
        }));
    }

    public static int n_1700_B(int p_208411_0_, int p_208411_1_, int p_208411_2_) {
        return p_208411_1_ << 8 | p_208411_2_ << 4 | p_208411_0_;
    }

    private int n_1700_B(int p_208412_1_) {
        return p_208412_1_ & 0xF;
    }

    private int J_1907_R(int p_208421_1_) {
        return p_208421_1_ >> 8 & 0xFF;
    }

    private int R_4764_Y(int p_208409_1_) {
        return p_208409_1_ >> 4 & 0xF;
    }

    public static int n_1700_B(boolean p_210537_0_, boolean p_210537_1_, boolean p_210537_2_, boolean p_210537_3_) {
        int i = 0;
        if (p_210537_2_) {
            i = p_210537_1_ ? (i |= 2) : (p_210537_0_ ? (i |= 0x80) : (i |= 1));
        } else if (p_210537_3_) {
            i = p_210537_0_ ? (i |= 0x20) : (p_210537_1_ ? (i |= 8) : (i |= 0x10));
        } else if (p_210537_1_) {
            i |= 4;
        } else if (p_210537_0_) {
            i |= 0x40;
        }
        return i;
    }

    private static /* synthetic */ Typed n_1700_B(Int2ObjectMap int2objectmap, Typed p_208413_1_) {
        return ((n_1700_B)int2objectmap.get(((Dynamic)p_208413_1_.get(DSL.remainderFinder())).get("Y").asInt(0))).n_1700_B(p_208413_1_);
    }

    public static final class n_1700_B
    extends J_1907_R {
        @Nullable
        private IntSet P_1922_E;
        @Nullable
        private IntSet u_1723_Y;
        @Nullable
        private Int2IntMap v_4262_N;

        public n_1700_B(Typed<?> p_i49851_1_, Schema p_i49851_2_) {
            super(p_i49851_1_, p_i49851_2_);
        }

        @Override
        protected boolean n_1700_B() {
            this.P_1922_E = new IntOpenHashSet();
            this.u_1723_Y = new IntOpenHashSet();
            this.v_4262_N = new Int2IntOpenHashMap();
            for (int i = 0; i < this.J_1907_R.size(); ++i) {
                Dynamic dynamic = (Dynamic)this.J_1907_R.get(i);
                String s = dynamic.get("Name").asString("");
                if (J_1907_R.containsKey((Object)s)) {
                    boolean flag = Objects.equals(dynamic.get("Properties").get("decayable").asString(""), "false");
                    this.P_1922_E.add(i);
                    this.v_4262_N.put(this.n_1700_B(s, flag, 7), i);
                    this.J_1907_R.set(i, this.n_1700_B(dynamic, s, flag, 7));
                }
                if (!R_4764_Y.contains(s)) continue;
                this.u_1723_Y.add(i);
            }
            return this.P_1922_E.isEmpty() && this.u_1723_Y.isEmpty();
        }

        private Dynamic<?> n_1700_B(Dynamic<?> p_209770_1_, String p_209770_2_, boolean p_209770_3_, int p_209770_4_) {
            Dynamic dynamic = p_209770_1_.emptyMap();
            dynamic = dynamic.set("persistent", dynamic.createString(p_209770_3_ ? "true" : "false"));
            dynamic = dynamic.set("distance", dynamic.createString(Integer.toString(p_209770_4_)));
            Dynamic dynamic1 = p_209770_1_.emptyMap();
            dynamic1 = dynamic1.set("Properties", dynamic);
            return dynamic1.set("Name", dynamic1.createString(p_209770_2_));
        }

        public boolean n_1700_B(int p_208457_1_) {
            return this.u_1723_Y.contains(p_208457_1_);
        }

        public boolean J_1907_R(int p_208460_1_) {
            return this.P_1922_E.contains(p_208460_1_);
        }

        private int G_564_y(int p_208459_1_) {
            return this.n_1700_B(p_208459_1_) ? 0 : Integer.parseInt(((Dynamic)this.J_1907_R.get(p_208459_1_)).get("Properties").get("distance").asString(""));
        }

        private void n_1700_B(int p_208454_1_, int p_208454_2_, int p_208454_3_) {
            boolean flag;
            Dynamic dynamic = (Dynamic)this.J_1907_R.get(p_208454_2_);
            String s = dynamic.get("Name").asString("");
            int i = this.n_1700_B(s, flag = Objects.equals(dynamic.get("Properties").get("persistent").asString(""), "true"), p_208454_3_);
            if (!this.v_4262_N.containsKey(i)) {
                int j = this.J_1907_R.size();
                this.P_1922_E.add(j);
                this.v_4262_N.put(i, j);
                this.J_1907_R.add(this.n_1700_B(dynamic, s, flag, p_208454_3_));
            }
            int l = this.v_4262_N.get(i);
            if (1 << this.G_564_y.J_1907_R() <= l) {
                A_1434_p arbitrarybitlengthintarray = new A_1434_p(this.G_564_y.J_1907_R() + 1, 4096);
                for (int k = 0; k < 4096; ++k) {
                    arbitrarybitlengthintarray.n_1700_B(k, this.G_564_y.n_1700_B(k));
                }
                this.G_564_y = arbitrarybitlengthintarray;
            }
            this.G_564_y.n_1700_B(p_208454_1_, l);
        }
    }

    public static abstract class J_1907_R {
        private final Type<Pair<String, Dynamic<?>>> P_1922_E = DSL.named((String)References.P_4830_p.typeName(), (Type)DSL.remainderType());
        protected final OpticFinder<List<Pair<String, Dynamic<?>>>> n_1700_B = DSL.fieldFinder((String)"Palette", (Type)DSL.list(this.P_1922_E));
        protected final List J_1907_R;
        protected final int R_4764_Y;
        @Nullable
        protected A_1434_p G_564_y;

        public J_1907_R(Typed<?> p_i49850_1_, Schema p_i49850_2_) {
            if (!Objects.equals(p_i49850_2_.getType(References.P_4830_p), this.P_1922_E)) {
                throw new IllegalStateException("Block state type is not what was expected.");
            }
            Optional optional = p_i49850_1_.getOptional(this.n_1700_B);
            this.J_1907_R = optional.map(p_208463_0_ -> p_208463_0_.stream().map(Pair::getSecond).collect(Collectors.toList())).orElse((List)ImmutableList.of());
            Dynamic dynamic = (Dynamic)p_i49850_1_.get(DSL.remainderFinder());
            this.R_4764_Y = dynamic.get("Y").asInt(0);
            this.n_1700_B(dynamic);
        }

        protected void n_1700_B(Dynamic<?> p_212507_1_) {
            if (this.n_1700_B()) {
                this.G_564_y = null;
            } else {
                long[] along = p_212507_1_.get("BlockStates").asLongStream().toArray();
                int i = Math.max(4, DataFixUtils.ceillog2((int)this.J_1907_R.size()));
                this.G_564_y = new A_1434_p(i, 4096, along);
            }
        }

        public Typed<?> n_1700_B(Typed<?> p_208465_1_) {
            return this.J_1907_R() ? p_208465_1_ : p_208465_1_.update(DSL.remainderFinder(), p_212510_1_ -> p_212510_1_.set("BlockStates", p_212510_1_.createLongList(Arrays.stream(this.G_564_y.n_1700_B())))).set(this.n_1700_B, this.J_1907_R.stream().map(p_212509_0_ -> Pair.of((Object)References.P_4830_p.typeName(), (Object)p_212509_0_)).collect(Collectors.toList()));
        }

        public boolean J_1907_R() {
            return this.G_564_y == null;
        }

        public int R_4764_Y(int p_208453_1_) {
            return this.G_564_y.n_1700_B(p_208453_1_);
        }

        protected int n_1700_B(String p_208464_1_, boolean p_208464_2_, int p_208464_3_) {
            return J_1907_R.get((Object)p_208464_1_) << 5 | (p_208464_2_ ? 16 : 0) | p_208464_3_;
        }

        int R_4764_Y() {
            return this.R_4764_Y;
        }

        protected abstract boolean n_1700_B();
    }
}


