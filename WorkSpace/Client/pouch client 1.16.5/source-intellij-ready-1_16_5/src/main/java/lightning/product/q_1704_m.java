/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.common.collect.Sets$SetView
 *  com.google.gson.TypeAdapter
 *  com.google.gson.stream.JsonReader
 *  com.google.gson.stream.JsonWriter
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.LootContextParams;
import lightning.product.I_2011_f;
import lightning.product.I_2176_d;
import lightning.product.N_4263_v;
import lightning.product.LootItemCondition;
import lightning.product.Z_1993_T;
import lightning.product.e_3591_l;
import lightning.product.f_4186_T;
import lightning.product.g_2336_b;
import lightning.product.k_1471_n;
import lightning.product.p_4985_U;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMeta;
import net.minecraft.server.G_564_y;

public class q_1704_m {
    private final Random n_1700_B;
    private final float J_1907_R;
    private final e_3591_l R_4764_Y;
    private final Function<g_2336_b, p_4985_U> G_564_y;
    private final Set<p_4985_U> P_1922_E = Sets.newLinkedHashSet();
    private final Function<g_2336_b, LootItemCondition> u_1723_Y;
    private final Set<LootItemCondition> v_4262_N = Sets.newLinkedHashSet();
    private final Map<I_2011_f<?>, Object> w_1484_f;
    private final Map<g_2336_b, R_4764_Y> t_148_a;

    private q_1704_m(Random rand, float luckIn, e_3591_l worldIn, Function<g_2336_b, p_4985_U> lootTableManagerIn, Function<g_2336_b, LootItemCondition> p_i225885_5_, Map<I_2011_f<?>, Object> parametersIn, Map<g_2336_b, R_4764_Y> conditionsIn) {
        this.n_1700_B = rand;
        this.J_1907_R = luckIn;
        this.R_4764_Y = worldIn;
        this.G_564_y = lootTableManagerIn;
        this.u_1723_Y = p_i225885_5_;
        this.w_1484_f = ImmutableMap.copyOf(parametersIn);
        this.t_148_a = ImmutableMap.copyOf(conditionsIn);
    }

    public boolean n_1700_B(I_2011_f<?> parameter) {
        return this.w_1484_f.containsKey(parameter);
    }

    public void n_1700_B(g_2336_b name, Consumer<Z_1993_T> consumer) {
        R_4764_Y lootcontext$idynamicdropprovider = this.t_148_a.get(name);
        if (lootcontext$idynamicdropprovider != null) {
            lootcontext$idynamicdropprovider.add(this, consumer);
        }
    }

    @Nullable
    public <T> T J_1907_R(I_2011_f<T> parameter) {
        return (T)this.w_1484_f.get(parameter);
    }

    public boolean n_1700_B(p_4985_U lootTableIn) {
        return this.P_1922_E.add(lootTableIn);
    }

    public void J_1907_R(p_4985_U lootTableIn) {
        this.P_1922_E.remove(lootTableIn);
    }

    public boolean n_1700_B(LootItemCondition conditionIn) {
        return this.v_4262_N.add(conditionIn);
    }

    public void J_1907_R(LootItemCondition conditionIn) {
        this.v_4262_N.remove(conditionIn);
    }

    public p_4985_U n_1700_B(g_2336_b tableId) {
        return this.G_564_y.apply(tableId);
    }

    public LootItemCondition J_1907_R(g_2336_b conditionId) {
        return this.u_1723_Y.apply(conditionId);
    }

    public Random n_1700_B() {
        return this.n_1700_B;
    }

    public float J_1907_R() {
        return this.J_1907_R;
    }

    public e_3591_l R_4764_Y() {
        return this.R_4764_Y;
    }

    private static G_564_y n_1700_B(e_3591_l world) {
        if (world == null) {
            return null;
        }
        return world.T_2506_i();
    }

    private static f_4186_T n_1700_B(G_564_y server) {
        if (server == null) {
            return BlockOptionalMeta.getManager();
        }
        return server.F_2624_D();
    }

    private static k_1471_n J_1907_R(G_564_y server) {
        if (server == null) {
            return BlockOptionalMeta.getPredicateManager();
        }
        return server.RealmsDefaultUncaughtExceptionHandler();
    }

    @FunctionalInterface
    public static interface R_4764_Y {
        public void add(q_1704_m var1, Consumer<Z_1993_T> var2);
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("this", LootContextParams.n_1700_B);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("killer", LootContextParams.G_564_y);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R("direct_killer", LootContextParams.P_1922_E);
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R("killer_player", LootContextParams.J_1907_R);
        private final String P_1922_E;
        private final I_2011_f<? extends N_4263_v> u_1723_Y;
        private static final /* synthetic */ J_1907_R[] v_4262_N;

        public static J_1907_R[] values() {
            return (J_1907_R[])v_4262_N.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(String targetTypeIn, I_2011_f<? extends N_4263_v> parameterIn) {
            this.P_1922_E = targetTypeIn;
            this.u_1723_Y = parameterIn;
        }

        public I_2011_f<? extends N_4263_v> n_1700_B() {
            return this.u_1723_Y;
        }

        public static J_1907_R n_1700_B(String type) {
            for (J_1907_R lootcontext$entitytarget : lightning.product.q_1704_m$J_1907_R.values()) {
                if (!lootcontext$entitytarget.P_1922_E.equals(type)) continue;
                return lootcontext$entitytarget;
            }
            throw new IllegalArgumentException("Invalid entity target " + type);
        }

        private static /* synthetic */ J_1907_R[] J_1907_R() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            v_4262_N = lightning.product.q_1704_m$J_1907_R.J_1907_R();
        }

        public static class n_1700_B
        extends TypeAdapter<J_1907_R> {
            public void n_1700_B(JsonWriter p_write_1_, J_1907_R p_write_2_) throws IOException {
                p_write_1_.value(p_write_2_.P_1922_E);
            }

            public J_1907_R n_1700_B(JsonReader p_read_1_) throws IOException {
                return lightning.product.q_1704_m$J_1907_R.n_1700_B(p_read_1_.nextString());
            }

            public /* synthetic */ Object read(JsonReader jsonReader) throws IOException {
                return this.n_1700_B(jsonReader);
            }

            public /* synthetic */ void write(JsonWriter jsonWriter, Object object) throws IOException {
                this.n_1700_B(jsonWriter, (J_1907_R)((Object)object));
            }
        }
    }

    public static class n_1700_B {
        private final e_3591_l n_1700_B;
        private final Map<I_2011_f<?>, Object> J_1907_R = Maps.newIdentityHashMap();
        private final Map<g_2336_b, R_4764_Y> R_4764_Y = Maps.newHashMap();
        private Random G_564_y;
        private float P_1922_E;

        public n_1700_B(e_3591_l worldIn) {
            this.n_1700_B = worldIn;
        }

        public n_1700_B n_1700_B(Random randomIn) {
            this.G_564_y = randomIn;
            return this;
        }

        public n_1700_B n_1700_B(long seed) {
            if (seed != 0L) {
                this.G_564_y = new Random(seed);
            }
            return this;
        }

        public n_1700_B n_1700_B(long seed, Random p_216020_3_) {
            this.G_564_y = seed == 0L ? p_216020_3_ : new Random(seed);
            return this;
        }

        public n_1700_B n_1700_B(float luckIn) {
            this.P_1922_E = luckIn;
            return this;
        }

        public <T> n_1700_B n_1700_B(I_2011_f<T> parameter, T value) {
            this.J_1907_R.put(parameter, value);
            return this;
        }

        public <T> n_1700_B J_1907_R(I_2011_f<T> parameter, @Nullable T value) {
            if (value == null) {
                this.J_1907_R.remove(parameter);
            } else {
                this.J_1907_R.put(parameter, value);
            }
            return this;
        }

        public n_1700_B n_1700_B(g_2336_b p_216017_1_, R_4764_Y p_216017_2_) {
            R_4764_Y lootcontext$idynamicdropprovider = this.R_4764_Y.put(p_216017_1_, p_216017_2_);
            if (lootcontext$idynamicdropprovider != null) {
                throw new IllegalStateException("Duplicated dynamic drop '" + String.valueOf(this.R_4764_Y) + "'");
            }
            return this;
        }

        public e_3591_l n_1700_B() {
            return this.n_1700_B;
        }

        public <T> T n_1700_B(I_2011_f<T> parameter) {
            Object t = this.J_1907_R.get(parameter);
            if (t == null) {
                throw new IllegalArgumentException("No parameter " + String.valueOf(parameter));
            }
            return (T)t;
        }

        @Nullable
        public <T> T J_1907_R(I_2011_f<T> parameter) {
            return (T)this.J_1907_R.get(parameter);
        }

        public q_1704_m n_1700_B(I_2176_d parameterSet) {
            Sets.SetView set = Sets.difference(this.J_1907_R.keySet(), parameterSet.J_1907_R());
            if (!set.isEmpty()) {
                throw new IllegalArgumentException("Parameters not allowed in this parameter set: " + String.valueOf(set));
            }
            Sets.SetView set1 = Sets.difference(parameterSet.n_1700_B(), this.J_1907_R.keySet());
            if (!set1.isEmpty()) {
                throw new IllegalArgumentException("Missing required parameters: " + String.valueOf(set1));
            }
            Random random = this.G_564_y;
            if (random == null) {
                random = new Random();
            }
            G_564_y minecraftserver = q_1704_m.n_1700_B(this.n_1700_B);
            f_4186_T lootTableManager1 = q_1704_m.n_1700_B(minecraftserver);
            k_1471_n LootPredicateManager = q_1704_m.J_1907_R(minecraftserver);
            return new q_1704_m(random, this.P_1922_E, this.n_1700_B, lootTableManager1::n_1700_B, LootPredicateManager::n_1700_B, this.J_1907_R, this.R_4764_Y);
        }
    }
}


