/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Optional;
import lightning.product.D_4024_W;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.q_3277_O;

public class M_1462_J {
    public static final Map<String, M_1462_J> n_1700_B = Maps.newHashMap();
    public static final M_1462_J J_1907_R = new M_1462_J("dummy");
    public static final M_1462_J R_4764_Y = new M_1462_J("trigger");
    public static final M_1462_J G_564_y = new M_1462_J("deathCount");
    public static final M_1462_J P_1922_E = new M_1462_J("playerKillCount");
    public static final M_1462_J u_1723_Y = new M_1462_J("totalKillCount");
    public static final M_1462_J v_4262_N = new M_1462_J("health", true, lightning.product.M_1462_J$n_1700_B.J_1907_R);
    public static final M_1462_J w_1484_f = new M_1462_J("food", true, lightning.product.M_1462_J$n_1700_B.n_1700_B);
    public static final M_1462_J t_148_a = new M_1462_J("air", true, lightning.product.M_1462_J$n_1700_B.n_1700_B);
    public static final M_1462_J s_956_w = new M_1462_J("armor", true, lightning.product.M_1462_J$n_1700_B.n_1700_B);
    public static final M_1462_J u_2550_I = new M_1462_J("xp", true, lightning.product.M_1462_J$n_1700_B.n_1700_B);
    public static final M_1462_J M_588_G = new M_1462_J("level", true, lightning.product.M_1462_J$n_1700_B.n_1700_B);
    public static final M_1462_J[] P_4830_p = new M_1462_J[]{new M_1462_J("teamkill." + D_4024_W.n_1700_B.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.J_1907_R.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.R_4764_Y.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.G_564_y.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.P_1922_E.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.u_1723_Y.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.v_4262_N.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.w_1484_f.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.t_148_a.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.s_956_w.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.u_2550_I.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.M_588_G.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.P_4830_p.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.h_1847_R.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.Q_4569_t.P_1922_E()), new M_1462_J("teamkill." + D_4024_W.M_182_A.P_1922_E())};
    public static final M_1462_J[] h_1847_R = new M_1462_J[]{new M_1462_J("killedByTeam." + D_4024_W.n_1700_B.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.J_1907_R.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.R_4764_Y.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.G_564_y.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.P_1922_E.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.u_1723_Y.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.v_4262_N.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.w_1484_f.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.t_148_a.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.s_956_w.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.u_2550_I.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.M_588_G.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.P_4830_p.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.h_1847_R.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.Q_4569_t.P_1922_E()), new M_1462_J("killedByTeam." + D_4024_W.M_182_A.P_1922_E())};
    private final String Q_4569_t;
    private final boolean M_182_A;
    private final n_1700_B t_1786_h;

    public M_1462_J(String p_i47676_1_) {
        this(p_i47676_1_, false, lightning.product.M_1462_J$n_1700_B.n_1700_B);
    }

    protected M_1462_J(String p_i47677_1_, boolean p_i47677_2_, n_1700_B p_i47677_3_) {
        this.Q_4569_t = p_i47677_1_;
        this.M_182_A = p_i47677_2_;
        this.t_1786_h = p_i47677_3_;
        n_1700_B.put(p_i47677_1_, this);
    }

    public static Optional<M_1462_J> n_1700_B(String p_216390_0_) {
        if (n_1700_B.containsKey(p_216390_0_)) {
            return Optional.of(n_1700_B.get(p_216390_0_));
        }
        int i = p_216390_0_.indexOf(58);
        return i < 0 ? Optional.empty() : V_3137_a.z_1333_t.J_1907_R(g_2336_b.n_1700_B(p_216390_0_.substring(0, i), '.')).flatMap(p_216392_2_ -> M_1462_J.n_1700_B(p_216392_2_, g_2336_b.n_1700_B(p_216390_0_.substring(i + 1), '.')));
    }

    private static <T> Optional<M_1462_J> n_1700_B(q_3277_O<T> p_216391_0_, g_2336_b p_216391_1_) {
        return p_216391_0_.n_1700_B().J_1907_R(p_216391_1_).map(p_216391_0_::J_1907_R);
    }

    public String n_1700_B() {
        return this.Q_4569_t;
    }

    public boolean J_1907_R() {
        return this.M_182_A;
    }

    public n_1700_B R_4764_Y() {
        return this.t_1786_h;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("integer");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("hearts");
        private final String R_4764_Y;
        private static final Map<String, n_1700_B> G_564_y;
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String p_i49784_3_) {
            this.R_4764_Y = p_i49784_3_;
        }

        public String n_1700_B() {
            return this.R_4764_Y;
        }

        public static n_1700_B n_1700_B(String p_211839_0_) {
            return G_564_y.getOrDefault(p_211839_0_, n_1700_B);
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            P_1922_E = lightning.product.M_1462_J$n_1700_B.J_1907_R();
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (n_1700_B scorecriteria$rendertype : lightning.product.M_1462_J$n_1700_B.values()) {
                builder.put((Object)scorecriteria$rendertype.R_4764_Y, (Object)scorecriteria$rendertype);
            }
            G_564_y = builder.build();
        }
    }
}

