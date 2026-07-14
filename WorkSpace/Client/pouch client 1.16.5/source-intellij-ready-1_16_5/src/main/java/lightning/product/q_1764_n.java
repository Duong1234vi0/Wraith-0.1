/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  it.unimi.dsi.fastutil.longs.LongArrayList
 *  it.unimi.dsi.fastutil.longs.LongList
 *  it.unimi.dsi.fastutil.objects.Object2LongMap
 *  it.unimi.dsi.fastutil.objects.Object2LongMaps
 *  it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.util.Supplier
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongMaps;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import javax.annotation.Nullable;
import lightning.product.T_4971_s;
import lightning.product.V_3322_x;
import lightning.product.ProfileResults;
import lightning.product.MinecraftClient;
import lightning.product.ContinuousProfiler;
import lightning.product.j_3341_s;
import lightning.product.ProfilerPathEntry;
import net.optifine.Config;
import net.optifine.Lagometer;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorClass;
import net.optifine.reflect.ReflectorField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

public class q_1764_n
implements V_3322_x {
    private static final long n_1700_B = Duration.ofMillis(100L).toNanos();
    private static final Logger J_1907_R = LogManager.getLogger();
    private final List<String> R_4764_Y = Lists.newArrayList();
    private final LongList G_564_y = new LongArrayList();
    private final Map<String, n_1700_B> P_1922_E = Maps.newHashMap();
    private final IntSupplier u_1723_Y;
    private final LongSupplier v_4262_N;
    private final long w_1484_f;
    private final int t_148_a;
    private String s_956_w = "";
    private boolean u_2550_I;
    @Nullable
    private n_1700_B M_588_G;
    private final boolean P_4830_p;
    private boolean h_1847_R = false;
    private boolean Q_4569_t = false;
    private static final String M_182_A = "scheduledExecutables";
    private static final String t_1786_h = "tick";
    private static final String multiplayerClientSuggestionProvider = "sound";
    private static final int w_1457_N = "scheduledExecutables".hashCode();
    private static final int Y_601_j = "tick".hashCode();
    private static final int Y_259_p = "sound".hashCode();
    private static final ReflectorClass Q_2552_b = new ReflectorClass(MinecraftClient.class);
    private static final ReflectorField C_2741_M = new ReflectorField(Q_2552_b, ContinuousProfiler.class);

    public q_1764_n(LongSupplier p_i231482_1_, IntSupplier p_i231482_2_, boolean p_i231482_3_) {
        this.w_1484_f = p_i231482_1_.getAsLong();
        this.v_4262_N = p_i231482_1_;
        this.t_148_a = p_i231482_2_.getAsInt();
        this.u_1723_Y = p_i231482_2_;
        this.P_4830_p = p_i231482_3_;
    }

    @Override
    public void n_1700_B() {
        ContinuousProfiler timetracker = (ContinuousProfiler)Reflector.getFieldValue(MinecraftClient.A_4115_X(), C_2741_M);
        this.h_1847_R = timetracker != null && timetracker.G_564_y() == this;
        boolean bl = this.Q_4569_t = this.h_1847_R && Lagometer.isActive();
        if (this.u_2550_I) {
            J_1907_R.error("Profiler tick already started - missing endTick()?");
        } else {
            this.u_2550_I = true;
            this.s_956_w = "";
            this.R_4764_Y.clear();
            this.n_1700_B("root");
        }
    }

    @Override
    public void J_1907_R() {
        if (!this.u_2550_I) {
            J_1907_R.error("Profiler tick already ended - missing startTick()?");
        } else {
            this.R_4764_Y();
            this.u_2550_I = false;
            if (!this.s_956_w.isEmpty()) {
                J_1907_R.error("Profiler tick ended before path was fully popped (remainder: '{}'). Mismatched push/pop?", new Supplier[]{() -> ProfileResults.J_1907_R(this.s_956_w)});
            }
        }
    }

    @Override
    public void n_1700_B(String name) {
        if (this.Q_4569_t) {
            int i = name.hashCode();
            if (i == w_1457_N && name.equals(M_182_A)) {
                Lagometer.timerScheduledExecutables.start();
            } else if (i == Y_601_j && name.equals(t_1786_h) && Config.isMinecraftThread()) {
                Lagometer.timerScheduledExecutables.end();
                Lagometer.timerTick.start();
            }
        }
        if (!this.u_2550_I) {
            J_1907_R.error("Cannot push '{}' to profiler if profiler tick hasn't started - missing startTick()?", (Object)name);
        } else {
            if (!this.s_956_w.isEmpty()) {
                this.s_956_w = this.s_956_w + "\u001e";
            }
            this.s_956_w = this.s_956_w + name;
            this.R_4764_Y.add(this.s_956_w);
            this.G_564_y.add(j_3341_s.R_4764_Y());
            this.M_588_G = null;
        }
    }

    @Override
    public void n_1700_B(java.util.function.Supplier<String> nameSupplier) {
        this.n_1700_B(nameSupplier.get());
    }

    @Override
    public void R_4764_Y() {
        if (!this.u_2550_I) {
            J_1907_R.error("Cannot pop from profiler if profiler tick hasn't started - missing startTick()?");
        } else if (this.G_564_y.isEmpty()) {
            J_1907_R.error("Tried to pop one too many times! Mismatched push() and pop()?");
        } else {
            long i = j_3341_s.R_4764_Y();
            long j = this.G_564_y.removeLong(this.G_564_y.size() - 1);
            this.R_4764_Y.remove(this.R_4764_Y.size() - 1);
            long k = i - j;
            n_1700_B profiler$section = this.P_1922_E();
            profiler$section.n_1700_B = (profiler$section.n_1700_B * 49L + k) / 50L;
            profiler$section.J_1907_R = 1L;
            if (this.P_4830_p && k > n_1700_B) {
                J_1907_R.warn("Something's taking too long! '{}' took aprox {} ms", new Supplier[]{() -> ProfileResults.J_1907_R(this.s_956_w), () -> (double)k / 1000000.0});
            }
            this.s_956_w = this.R_4764_Y.isEmpty() ? "" : this.R_4764_Y.get(this.R_4764_Y.size() - 1);
            this.M_588_G = null;
        }
    }

    @Override
    public void J_1907_R(String name) {
        int i;
        if (this.Q_4569_t && (i = name.hashCode()) == Y_259_p && name.equals(multiplayerClientSuggestionProvider)) {
            Lagometer.timerTick.end();
        }
        this.R_4764_Y();
        this.n_1700_B(name);
    }

    @Override
    public void J_1907_R(java.util.function.Supplier<String> nameSupplier) {
        this.R_4764_Y();
        this.n_1700_B(nameSupplier);
    }

    private n_1700_B P_1922_E() {
        if (this.M_588_G == null) {
            this.M_588_G = this.P_1922_E.computeIfAbsent(this.s_956_w, p_lambda$func_230081_e_$3_0_ -> new n_1700_B());
        }
        return this.M_588_G;
    }

    @Override
    public void R_4764_Y(String p_230035_1_) {
        this.P_1922_E().R_4764_Y.addTo((Object)p_230035_1_, 1L);
    }

    @Override
    public void R_4764_Y(java.util.function.Supplier<String> p_230036_1_) {
        this.P_1922_E().R_4764_Y.addTo((Object)p_230036_1_.get(), 1L);
    }

    @Override
    public ProfileResults G_564_y() {
        return new T_4971_s(this.P_1922_E, this.w_1484_f, this.t_148_a, this.v_4262_N.getAsLong(), this.u_1723_Y.getAsInt());
    }

    static class n_1700_B
    implements ProfilerPathEntry {
        private long n_1700_B;
        private long J_1907_R;
        private Object2LongOpenHashMap<String> R_4764_Y = new Object2LongOpenHashMap();

        private n_1700_B() {
        }

        @Override
        public long n_1700_B() {
            return this.n_1700_B;
        }

        @Override
        public long J_1907_R() {
            return this.J_1907_R;
        }

        @Override
        public Object2LongMap<String> R_4764_Y() {
            return Object2LongMaps.unmodifiable(this.R_4764_Y);
        }
    }
}



