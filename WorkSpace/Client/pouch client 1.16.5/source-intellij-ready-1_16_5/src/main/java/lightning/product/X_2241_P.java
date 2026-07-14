/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  it.unimi.dsi.fastutil.objects.ObjectArrays
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lightning.product.GravityProcessor;
import lightning.product.E_4700_p;
import lightning.product.V_3137_a;
import lightning.product.W_2163_m;
import lightning.product.StructureProcessor;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.StructurePoolElement;
import lightning.product.n_4684_C;
import lightning.product.z_2963_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class X_2241_P {
    private static final Logger R_4764_Y = LogManager.getLogger();
    public static final Codec<X_2241_P> n_1700_B = RecordCodecBuilder.create(p_236854_0_ -> p_236854_0_.group((App)g_2336_b.n_1700_B.fieldOf("name").forGetter(X_2241_P::J_1907_R), (App)g_2336_b.n_1700_B.fieldOf("fallback").forGetter(X_2241_P::n_1700_B), (App)Codec.mapPair((MapCodec)StructurePoolElement.R_4764_Y.fieldOf("element"), (MapCodec)Codec.INT.fieldOf("weight")).codec().listOf().promotePartial(j_3341_s.n_1700_B("Pool element: ", arg_0 -> ((Logger)R_4764_Y).error(arg_0))).fieldOf("elements").forGetter(p_236857_0_ -> p_236857_0_.P_1922_E)).apply((Applicative)p_236854_0_, X_2241_P::new));
    public static final Codec<Supplier<X_2241_P>> J_1907_R = n_4684_C.n_1700_B(V_3137_a.V_1446_Y, n_1700_B);
    private final g_2336_b G_564_y;
    private final List<Pair<StructurePoolElement, Integer>> P_1922_E;
    private final List<StructurePoolElement> u_1723_Y;
    private final g_2336_b v_4262_N;
    private int w_1484_f = Integer.MIN_VALUE;

    public X_2241_P(g_2336_b p_i242010_1_, g_2336_b p_i242010_2_, List<Pair<StructurePoolElement, Integer>> p_i242010_3_) {
        this.G_564_y = p_i242010_1_;
        this.P_1922_E = p_i242010_3_;
        this.u_1723_Y = Lists.newArrayList();
        for (Pair<StructurePoolElement, Integer> pair : p_i242010_3_) {
            StructurePoolElement jigsawpiece = (StructurePoolElement)pair.getFirst();
            for (int i = 0; i < (Integer)pair.getSecond(); ++i) {
                this.u_1723_Y.add(jigsawpiece);
            }
        }
        this.v_4262_N = p_i242010_2_;
    }

    public X_2241_P(g_2336_b nameIn, g_2336_b p_i51397_2_, List<Pair<Function<n_1700_B, ? extends StructurePoolElement>, Integer>> p_i51397_3_, n_1700_B placementBehaviourIn) {
        this.G_564_y = nameIn;
        this.P_1922_E = Lists.newArrayList();
        this.u_1723_Y = Lists.newArrayList();
        for (Pair<Function<n_1700_B, ? extends StructurePoolElement>, Integer> pair : p_i51397_3_) {
            StructurePoolElement jigsawpiece = (StructurePoolElement)((Function)pair.getFirst()).apply(placementBehaviourIn);
            this.P_1922_E.add((Pair<StructurePoolElement, Integer>)Pair.of((Object)jigsawpiece, (Object)((Integer)pair.getSecond())));
            for (int i = 0; i < (Integer)pair.getSecond(); ++i) {
                this.u_1723_Y.add(jigsawpiece);
            }
        }
        this.v_4262_N = p_i51397_2_;
    }

    public int n_1700_B(b_2085_h templateManagerIn) {
        if (this.w_1484_f == Integer.MIN_VALUE) {
            this.w_1484_f = this.u_1723_Y.stream().mapToInt(p_236856_1_ -> p_236856_1_.n_1700_B(templateManagerIn, c_1514_x.ZERO, W_2163_m.n_1700_B).P_1922_E()).max().orElse(0);
        }
        return this.w_1484_f;
    }

    public g_2336_b n_1700_B() {
        return this.v_4262_N;
    }

    public StructurePoolElement n_1700_B(Random rand) {
        return this.u_1723_Y.get(rand.nextInt(this.u_1723_Y.size()));
    }

    public List<StructurePoolElement> J_1907_R(Random rand) {
        return ImmutableList.copyOf((Object[])((StructurePoolElement[])ObjectArrays.shuffle((Object[])this.u_1723_Y.toArray(new StructurePoolElement[0]), (Random)rand)));
    }

    public g_2336_b J_1907_R() {
        return this.G_564_y;
    }

    public int R_4764_Y() {
        return this.u_1723_Y.size();
    }

    public static final class n_1700_B
    extends Enum<n_1700_B>
    implements E_4700_p {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("terrain_matching", (ImmutableList<StructureProcessor>)ImmutableList.of((Object)new GravityProcessor(z_2963_s.n_1700_B.n_1700_B, -1)));
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("rigid", (ImmutableList<StructureProcessor>)ImmutableList.of());
        public static final Codec<n_1700_B> R_4764_Y;
        private static final Map<String, n_1700_B> G_564_y;
        private final String P_1922_E;
        private final ImmutableList<StructureProcessor> u_1723_Y;
        private static final /* synthetic */ n_1700_B[] v_4262_N;

        public static n_1700_B[] values() {
            return (n_1700_B[])v_4262_N.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String nameIn, ImmutableList<StructureProcessor> structureProcessorsIn) {
            this.P_1922_E = nameIn;
            this.u_1723_Y = structureProcessorsIn;
        }

        public String J_1907_R() {
            return this.P_1922_E;
        }

        public static n_1700_B n_1700_B(String nameIn) {
            return G_564_y.get(nameIn);
        }

        public ImmutableList<StructureProcessor> R_4764_Y() {
            return this.u_1723_Y;
        }

        @Override
        public String n_1700_B() {
            return this.P_1922_E;
        }

        private static /* synthetic */ n_1700_B[] G_564_y() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            v_4262_N = lightning.product.X_2241_P$n_1700_B.G_564_y();
            R_4764_Y = E_4700_p.n_1700_B(n_1700_B::values, n_1700_B::n_1700_B);
            G_564_y = Arrays.stream(lightning.product.X_2241_P$n_1700_B.values()).collect(Collectors.toMap(n_1700_B::J_1907_R, p_214935_0_ -> p_214935_0_));
        }
    }
}


