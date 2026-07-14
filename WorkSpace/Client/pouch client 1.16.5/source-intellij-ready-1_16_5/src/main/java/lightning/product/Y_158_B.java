/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.Position;
import lightning.product.U_253_b;
import lightning.product.V_772_m;
import lightning.product.Y_3383_J;
import lightning.product.b_1722_e;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;
import lightning.product.z_3539_x;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Y_158_B
implements n_4915_r.n_1700_B {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final MinecraftClient J_1907_R;
    private final Map<c_1514_x, J_1907_R> R_4764_Y = Maps.newHashMap();
    private final Map<UUID, n_1700_B> G_564_y = Maps.newHashMap();
    @Nullable
    private UUID P_1922_E;

    public Y_158_B(MinecraftClient client) {
        this.J_1907_R = client;
    }

    @Override
    public void n_1700_B() {
        this.R_4764_Y.clear();
        this.G_564_y.clear();
        this.P_1922_E = null;
    }

    public void n_1700_B(J_1907_R p_217691_1_) {
        this.R_4764_Y.put(p_217691_1_.n_1700_B, p_217691_1_);
    }

    public void n_1700_B(c_1514_x p_217698_1_) {
        this.R_4764_Y.remove(p_217698_1_);
    }

    public void n_1700_B(c_1514_x p_217706_1_, int p_217706_2_) {
        J_1907_R pointofinterestdebugrenderer$poiinfo = this.R_4764_Y.get(p_217706_1_);
        if (pointofinterestdebugrenderer$poiinfo == null) {
            n_1700_B.warn("Strange, setFreeTicketCount was called for an unknown POI: " + String.valueOf(p_217706_1_));
        } else {
            pointofinterestdebugrenderer$poiinfo.R_4764_Y = p_217706_2_;
        }
    }

    public void n_1700_B(n_1700_B p_217692_1_) {
        this.G_564_y.put(p_217692_1_.n_1700_B, p_217692_1_);
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        this.J_1907_R();
        this.n_1700_B(camX, camY, camZ);
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
        if (!this.J_1907_R.Y_259_p.d_2461_k()) {
            this.G_564_y();
        }
    }

    private void J_1907_R() {
        this.G_564_y.entrySet().removeIf(p_239330_1_ -> {
            N_4263_v entity = this.J_1907_R.Y_601_j.J_1907_R(((n_1700_B)p_239330_1_.getValue()).J_1907_R);
            return entity == null || entity.t_4219_U;
        });
    }

    private void n_1700_B(double p_229035_1_, double p_229035_3_, double p_229035_5_) {
        c_1514_x blockpos = new c_1514_x(p_229035_1_, p_229035_3_, p_229035_5_);
        this.G_564_y.values().forEach(p_222924_7_ -> {
            if (this.R_4764_Y((n_1700_B)p_222924_7_)) {
                this.J_1907_R((n_1700_B)p_222924_7_, p_229035_1_, p_229035_3_, p_229035_5_);
            }
        });
        for (c_1514_x blockpos1 : this.R_4764_Y.keySet()) {
            if (!blockpos.withinDistance(blockpos1, 30.0)) continue;
            Y_158_B.J_1907_R(blockpos1);
        }
        this.R_4764_Y.values().forEach(p_239324_2_ -> {
            if (blockpos.withinDistance(p_239324_2_.n_1700_B, 30.0)) {
                this.J_1907_R((J_1907_R)p_239324_2_);
            }
        });
        this.R_4764_Y().forEach((p_239325_2_, p_239325_3_) -> {
            if (blockpos.withinDistance((z_3539_x)p_239325_2_, 30.0)) {
                this.n_1700_B((c_1514_x)p_239325_2_, (List<String>)p_239325_3_);
            }
        });
    }

    private static void J_1907_R(c_1514_x p_217699_0_) {
        float f = 0.05f;
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        n_4915_r.n_1700_B(p_217699_0_, 0.05f, 0.2f, 0.2f, 1.0f, 0.3f);
    }

    private void n_1700_B(c_1514_x p_222921_1_, List<String> p_222921_2_) {
        float f = 0.05f;
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        n_4915_r.n_1700_B(p_222921_1_, 0.05f, 0.2f, 0.2f, 1.0f, 0.3f);
        Y_158_B.n_1700_B(String.valueOf(p_222921_2_), p_222921_1_, 0, -256);
        Y_158_B.n_1700_B("Ghost POI", p_222921_1_, 1, -65536);
    }

    private void J_1907_R(J_1907_R p_217705_1_) {
        int i = 0;
        Set<String> set = this.R_4764_Y(p_217705_1_);
        if (set.size() < 4) {
            Y_158_B.n_1700_B("Owners: " + String.valueOf(set), p_217705_1_, i, -256);
        } else {
            Y_158_B.n_1700_B(set.size() + " ticket holders", p_217705_1_, i, -256);
        }
        ++i;
        Set<String> set1 = this.G_564_y(p_217705_1_);
        if (set1.size() < 4) {
            Y_158_B.n_1700_B("Candidates: " + String.valueOf(set1), p_217705_1_, i, -23296);
        } else {
            Y_158_B.n_1700_B(set1.size() + " potential owners", p_217705_1_, i, -23296);
        }
        Y_158_B.n_1700_B("Free tickets: " + p_217705_1_.R_4764_Y, p_217705_1_, ++i, -256);
        Y_158_B.n_1700_B(p_217705_1_.J_1907_R, p_217705_1_, ++i, -1);
    }

    private void n_1700_B(n_1700_B p_229037_1_, double p_229037_2_, double p_229037_4_, double p_229037_6_) {
        if (p_229037_1_.s_956_w != null) {
            Y_3383_J.n_1700_B(p_229037_1_.s_956_w, 0.5f, false, false, p_229037_2_, p_229037_4_, p_229037_6_);
        }
    }

    private void J_1907_R(n_1700_B p_229038_1_, double p_229038_2_, double p_229038_4_, double p_229038_6_) {
        boolean flag = this.J_1907_R(p_229038_1_);
        int i = 0;
        Y_158_B.n_1700_B(p_229038_1_.w_1484_f, i, p_229038_1_.R_4764_Y, -1, 0.03f);
        ++i;
        if (flag) {
            Y_158_B.n_1700_B(p_229038_1_.w_1484_f, i, p_229038_1_.G_564_y + " " + p_229038_1_.P_1922_E + " xp", -1, 0.02f);
            ++i;
        }
        if (flag) {
            int j = p_229038_1_.u_1723_Y < p_229038_1_.v_4262_N ? -23296 : -1;
            Y_158_B.n_1700_B(p_229038_1_.w_1484_f, i, "health: " + String.format("%.1f", Float.valueOf(p_229038_1_.u_1723_Y)) + " / " + String.format("%.1f", Float.valueOf(p_229038_1_.v_4262_N)), j, 0.02f);
            ++i;
        }
        if (flag && !p_229038_1_.t_148_a.equals("")) {
            Y_158_B.n_1700_B(p_229038_1_.w_1484_f, i, p_229038_1_.t_148_a, -98404, 0.02f);
            ++i;
        }
        if (flag) {
            for (String s : p_229038_1_.P_4830_p) {
                Y_158_B.n_1700_B(p_229038_1_.w_1484_f, i, s, -16711681, 0.02f);
                ++i;
            }
        }
        if (flag) {
            for (String s1 : p_229038_1_.M_588_G) {
                Y_158_B.n_1700_B(p_229038_1_.w_1484_f, i, s1, -16711936, 0.02f);
                ++i;
            }
        }
        if (p_229038_1_.u_2550_I) {
            Y_158_B.n_1700_B(p_229038_1_.w_1484_f, i, "Wants Golem", -23296, 0.02f);
            ++i;
        }
        if (flag) {
            for (String s2 : p_229038_1_.Q_4569_t) {
                if (s2.startsWith(p_229038_1_.R_4764_Y)) {
                    Y_158_B.n_1700_B(p_229038_1_.w_1484_f, i, s2, -1, 0.02f);
                } else {
                    Y_158_B.n_1700_B(p_229038_1_.w_1484_f, i, s2, -23296, 0.02f);
                }
                ++i;
            }
        }
        if (flag) {
            for (String s3 : Lists.reverse(p_229038_1_.h_1847_R)) {
                Y_158_B.n_1700_B(p_229038_1_.w_1484_f, i, s3, -3355444, 0.02f);
                ++i;
            }
        }
        if (flag) {
            this.n_1700_B(p_229038_1_, p_229038_2_, p_229038_4_, p_229038_6_);
        }
    }

    private static void n_1700_B(String p_217695_0_, J_1907_R p_217695_1_, int p_217695_2_, int p_217695_3_) {
        c_1514_x blockpos = p_217695_1_.n_1700_B;
        Y_158_B.n_1700_B(p_217695_0_, blockpos, p_217695_2_, p_217695_3_);
    }

    private static void n_1700_B(String p_222923_0_, c_1514_x p_222923_1_, int p_222923_2_, int p_222923_3_) {
        double d0 = 1.3;
        double d1 = 0.2;
        double d2 = (double)p_222923_1_.getX() + 0.5;
        double d3 = (double)p_222923_1_.getY() + 1.3 + (double)p_222923_2_ * 0.2;
        double d4 = (double)p_222923_1_.getZ() + 0.5;
        n_4915_r.n_1700_B(p_222923_0_, d2, d3, d4, p_222923_3_, 0.02f, true, 0.0f, true);
    }

    private static void n_1700_B(Position p_217693_0_, int p_217693_1_, String p_217693_2_, int p_217693_3_, float p_217693_4_) {
        double d0 = 2.4;
        double d1 = 0.25;
        c_1514_x blockpos = new c_1514_x(p_217693_0_);
        double d2 = (double)blockpos.getX() + 0.5;
        double d3 = p_217693_0_.J_1907_R() + 2.4 + (double)p_217693_1_ * 0.25;
        double d4 = (double)blockpos.getZ() + 0.5;
        float f = 0.5f;
        n_4915_r.n_1700_B(p_217693_2_, d2, d3, d4, p_217693_3_, p_217693_4_, false, 0.5f, true);
    }

    private Set<String> R_4764_Y(J_1907_R p_217696_1_) {
        return this.R_4764_Y(p_217696_1_.n_1700_B).stream().map(U_253_b::n_1700_B).collect(Collectors.toSet());
    }

    private Set<String> G_564_y(J_1907_R p_239342_1_) {
        return this.G_564_y(p_239342_1_.n_1700_B).stream().map(U_253_b::n_1700_B).collect(Collectors.toSet());
    }

    private boolean J_1907_R(n_1700_B p_217703_1_) {
        return Objects.equals(this.P_1922_E, p_217703_1_.n_1700_B);
    }

    private boolean R_4764_Y(n_1700_B p_217694_1_) {
        V_772_m playerentity = this.J_1907_R.Y_259_p;
        c_1514_x blockpos = new c_1514_x(playerentity.O_3598_v(), p_217694_1_.w_1484_f.J_1907_R(), playerentity.l_2647_k());
        c_1514_x blockpos1 = new c_1514_x(p_217694_1_.w_1484_f);
        return blockpos.withinDistance(blockpos1, 30.0);
    }

    private Collection<UUID> R_4764_Y(c_1514_x p_239340_1_) {
        return this.G_564_y.values().stream().filter(p_239336_1_ -> p_239336_1_.n_1700_B(p_239340_1_)).map(n_1700_B::n_1700_B).collect(Collectors.toSet());
    }

    private Collection<UUID> G_564_y(c_1514_x p_239343_1_) {
        return this.G_564_y.values().stream().filter(p_239323_1_ -> p_239323_1_.J_1907_R(p_239343_1_)).map(n_1700_B::n_1700_B).collect(Collectors.toSet());
    }

    private Map<c_1514_x, List<String>> R_4764_Y() {
        HashMap map = Maps.newHashMap();
        for (n_1700_B pointofinterestdebugrenderer$braininfo : this.G_564_y.values()) {
            for (c_1514_x blockpos : Iterables.concat(pointofinterestdebugrenderer$braininfo.M_182_A, pointofinterestdebugrenderer$braininfo.t_1786_h)) {
                if (this.R_4764_Y.containsKey(blockpos)) continue;
                map.computeIfAbsent(blockpos, p_241729_0_ -> Lists.newArrayList()).add(pointofinterestdebugrenderer$braininfo.R_4764_Y);
            }
        }
        return map;
    }

    private void G_564_y() {
        n_4915_r.n_1700_B(this.J_1907_R.g_2268_R(), 8).ifPresent(p_239317_1_ -> {
            this.P_1922_E = p_239317_1_.w_2705_t();
        });
    }

    public static class J_1907_R {
        public final c_1514_x n_1700_B;
        public String J_1907_R;
        public int R_4764_Y;

        public J_1907_R(c_1514_x p_i50886_1_, String p_i50886_2_, int p_i50886_3_) {
            this.n_1700_B = p_i50886_1_;
            this.J_1907_R = p_i50886_2_;
            this.R_4764_Y = p_i50886_3_;
        }
    }

    public static class n_1700_B {
        public final UUID n_1700_B;
        public final int J_1907_R;
        public final String R_4764_Y;
        public final String G_564_y;
        public final int P_1922_E;
        public final float u_1723_Y;
        public final float v_4262_N;
        public final Position w_1484_f;
        public final String t_148_a;
        public final b_1722_e s_956_w;
        public final boolean u_2550_I;
        public final List<String> M_588_G = Lists.newArrayList();
        public final List<String> P_4830_p = Lists.newArrayList();
        public final List<String> h_1847_R = Lists.newArrayList();
        public final List<String> Q_4569_t = Lists.newArrayList();
        public final Set<c_1514_x> M_182_A = Sets.newHashSet();
        public final Set<c_1514_x> t_1786_h = Sets.newHashSet();

        public n_1700_B(UUID p_i241202_1_, int p_i241202_2_, String p_i241202_3_, String p_i241202_4_, int p_i241202_5_, float p_i241202_6_, float p_i241202_7_, Position p_i241202_8_, String p_i241202_9_, @Nullable b_1722_e p_i241202_10_, boolean p_i241202_11_) {
            this.n_1700_B = p_i241202_1_;
            this.J_1907_R = p_i241202_2_;
            this.R_4764_Y = p_i241202_3_;
            this.G_564_y = p_i241202_4_;
            this.P_1922_E = p_i241202_5_;
            this.u_1723_Y = p_i241202_6_;
            this.v_4262_N = p_i241202_7_;
            this.w_1484_f = p_i241202_8_;
            this.t_148_a = p_i241202_9_;
            this.s_956_w = p_i241202_10_;
            this.u_2550_I = p_i241202_11_;
        }

        private boolean n_1700_B(c_1514_x p_217744_1_) {
            return this.M_182_A.stream().anyMatch(p_217744_1_::equals);
        }

        private boolean J_1907_R(c_1514_x p_239365_1_) {
            return this.t_1786_h.contains(p_239365_1_);
        }

        public UUID n_1700_B() {
            return this.n_1700_B;
        }
    }
}



