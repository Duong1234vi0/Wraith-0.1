/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

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
import lightning.product.Position;
import lightning.product.U_253_b;
import lightning.product.V_772_m;
import lightning.product.Y_3383_J;
import lightning.product.b_1722_e;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.h_3572_K;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.z_3539_x;

public class S_3601_T
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;
    private final Map<c_1514_x, J_1907_R> J_1907_R = Maps.newHashMap();
    private final Map<UUID, n_1700_B> R_4764_Y = Maps.newHashMap();
    private UUID G_564_y;

    public S_3601_T(MinecraftClient p_i226027_1_) {
        this.n_1700_B = p_i226027_1_;
    }

    @Override
    public void n_1700_B() {
        this.J_1907_R.clear();
        this.R_4764_Y.clear();
        this.G_564_y = null;
    }

    public void n_1700_B(J_1907_R p_228966_1_) {
        this.J_1907_R.put(p_228966_1_.n_1700_B, p_228966_1_);
    }

    public void n_1700_B(n_1700_B p_228964_1_) {
        this.R_4764_Y.put(p_228964_1_.n_1700_B, p_228964_1_);
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        this.R_4764_Y();
        this.J_1907_R();
        this.G_564_y();
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
        if (!this.n_1700_B.Y_259_p.d_2461_k()) {
            this.t_148_a();
        }
    }

    private void J_1907_R() {
        this.R_4764_Y.entrySet().removeIf(p_228984_1_ -> this.n_1700_B.Y_601_j.J_1907_R(((n_1700_B)p_228984_1_.getValue()).J_1907_R) == null);
    }

    private void R_4764_Y() {
        long i = this.n_1700_B.Y_601_j.X_933_l() - 20L;
        this.J_1907_R.entrySet().removeIf(p_228962_2_ -> ((J_1907_R)p_228962_2_.getValue()).u_1723_Y < i);
    }

    private void G_564_y() {
        c_1514_x blockpos = this.v_4262_N().R_4764_Y();
        this.R_4764_Y.values().forEach(p_228994_1_ -> {
            if (this.P_1922_E((n_1700_B)p_228994_1_)) {
                this.R_4764_Y((n_1700_B)p_228994_1_);
            }
        });
        this.u_1723_Y();
        for (c_1514_x blockpos1 : this.J_1907_R.keySet()) {
            if (!blockpos.withinDistance(blockpos1, 30.0)) continue;
            S_3601_T.n_1700_B(blockpos1);
        }
        Map<c_1514_x, Set<UUID>> map = this.P_1922_E();
        this.J_1907_R.values().forEach(p_228973_3_ -> {
            if (blockpos.withinDistance(p_228973_3_.n_1700_B, 30.0)) {
                Set set = (Set)map.get(p_228973_3_.n_1700_B);
                this.n_1700_B((J_1907_R)p_228973_3_, set == null ? Sets.newHashSet() : set);
            }
        });
        this.w_1484_f().forEach((p_228971_2_, p_228971_3_) -> {
            if (blockpos.withinDistance((z_3539_x)p_228971_2_, 30.0)) {
                this.n_1700_B((c_1514_x)p_228971_2_, (List<String>)p_228971_3_);
            }
        });
    }

    private Map<c_1514_x, Set<UUID>> P_1922_E() {
        HashMap map = Maps.newHashMap();
        this.R_4764_Y.values().forEach(p_228985_1_ -> p_228985_1_.t_148_a.forEach(p_228986_2_ -> map.computeIfAbsent(p_228986_2_, p_241727_0_ -> Sets.newHashSet()).add(p_228985_1_.n_1700_B())));
        return map;
    }

    private void u_1723_Y() {
        HashMap map = Maps.newHashMap();
        this.R_4764_Y.values().stream().filter(n_1700_B::R_4764_Y).forEach(p_241722_1_ -> map.computeIfAbsent(p_241722_1_.u_1723_Y, p_241726_0_ -> Sets.newHashSet()).add(p_241722_1_.n_1700_B()));
        map.entrySet().forEach(p_228978_0_ -> {
            c_1514_x blockpos = (c_1514_x)p_228978_0_.getKey();
            Set set = (Set)p_228978_0_.getValue();
            Set set1 = set.stream().map(U_253_b::n_1700_B).collect(Collectors.toSet());
            int i = 1;
            S_3601_T.n_1700_B(set1.toString(), blockpos, i++, -256);
            S_3601_T.n_1700_B("Flower", blockpos, i++, -1);
            float f = 0.05f;
            S_3601_T.n_1700_B(blockpos, 0.05f, 0.8f, 0.8f, 0.0f, 0.3f);
        });
    }

    private static String n_1700_B(Collection<UUID> p_228977_0_) {
        if (p_228977_0_.isEmpty()) {
            return "-";
        }
        return p_228977_0_.size() > 3 ? p_228977_0_.size() + " bees" : p_228977_0_.stream().map(U_253_b::n_1700_B).collect(Collectors.toSet()).toString();
    }

    private static void n_1700_B(c_1514_x p_228968_0_) {
        float f = 0.05f;
        S_3601_T.n_1700_B(p_228968_0_, 0.05f, 0.2f, 0.2f, 1.0f, 0.3f);
    }

    private void n_1700_B(c_1514_x p_228972_1_, List<String> p_228972_2_) {
        float f = 0.05f;
        S_3601_T.n_1700_B(p_228972_1_, 0.05f, 0.2f, 0.2f, 1.0f, 0.3f);
        S_3601_T.n_1700_B(String.valueOf(p_228972_2_), p_228972_1_, 0, -256);
        S_3601_T.n_1700_B("Ghost Hive", p_228972_1_, 1, -65536);
    }

    private static void n_1700_B(c_1514_x p_228969_0_, float p_228969_1_, float p_228969_2_, float p_228969_3_, float p_228969_4_, float p_228969_5_) {
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        n_4915_r.n_1700_B(p_228969_0_, p_228969_1_, p_228969_2_, p_228969_3_, p_228969_4_, p_228969_5_);
    }

    private void n_1700_B(J_1907_R p_228967_1_, Collection<UUID> p_228967_2_) {
        int i = 0;
        if (!p_228967_2_.isEmpty()) {
            S_3601_T.n_1700_B("Blacklisted by " + S_3601_T.n_1700_B(p_228967_2_), p_228967_1_, i++, -65536);
        }
        S_3601_T.n_1700_B("Out: " + S_3601_T.n_1700_B(this.J_1907_R(p_228967_1_.n_1700_B)), p_228967_1_, i++, -3355444);
        if (p_228967_1_.R_4764_Y == 0) {
            S_3601_T.n_1700_B("In: -", p_228967_1_, i++, -256);
        } else if (p_228967_1_.R_4764_Y == 1) {
            S_3601_T.n_1700_B("In: 1 bee", p_228967_1_, i++, -256);
        } else {
            S_3601_T.n_1700_B("In: " + p_228967_1_.R_4764_Y + " bees", p_228967_1_, i++, -256);
        }
        S_3601_T.n_1700_B("Honey: " + p_228967_1_.G_564_y, p_228967_1_, i++, -23296);
        S_3601_T.n_1700_B(p_228967_1_.J_1907_R + (p_228967_1_.P_1922_E ? " (sedated)" : ""), p_228967_1_, i++, -1);
    }

    private void J_1907_R(n_1700_B p_228982_1_) {
        if (p_228982_1_.G_564_y != null) {
            Y_3383_J.n_1700_B(p_228982_1_.G_564_y, 0.5f, false, false, this.v_4262_N().J_1907_R().n_1700_B(), this.v_4262_N().J_1907_R().J_1907_R(), this.v_4262_N().J_1907_R().R_4764_Y());
        }
    }

    private void R_4764_Y(n_1700_B p_228988_1_) {
        boolean flag = this.G_564_y(p_228988_1_);
        int i = 0;
        S_3601_T.n_1700_B(p_228988_1_.R_4764_Y, i++, p_228988_1_.toString(), -1, 0.03f);
        if (p_228988_1_.P_1922_E == null) {
            S_3601_T.n_1700_B(p_228988_1_.R_4764_Y, i++, "No hive", -98404, 0.02f);
        } else {
            S_3601_T.n_1700_B(p_228988_1_.R_4764_Y, i++, "Hive: " + this.n_1700_B(p_228988_1_, p_228988_1_.P_1922_E), -256, 0.02f);
        }
        if (p_228988_1_.u_1723_Y == null) {
            S_3601_T.n_1700_B(p_228988_1_.R_4764_Y, i++, "No flower", -98404, 0.02f);
        } else {
            S_3601_T.n_1700_B(p_228988_1_.R_4764_Y, i++, "Flower: " + this.n_1700_B(p_228988_1_, p_228988_1_.u_1723_Y), -256, 0.02f);
        }
        for (String s : p_228988_1_.w_1484_f) {
            S_3601_T.n_1700_B(p_228988_1_.R_4764_Y, i++, s, -16711936, 0.02f);
        }
        if (flag) {
            this.J_1907_R(p_228988_1_);
        }
        if (p_228988_1_.v_4262_N > 0) {
            int j = p_228988_1_.v_4262_N < 600 ? -3355444 : -23296;
            S_3601_T.n_1700_B(p_228988_1_.R_4764_Y, i++, "Travelling: " + p_228988_1_.v_4262_N + " ticks", j, 0.02f);
        }
    }

    private static void n_1700_B(String p_228975_0_, J_1907_R p_228975_1_, int p_228975_2_, int p_228975_3_) {
        c_1514_x blockpos = p_228975_1_.n_1700_B;
        S_3601_T.n_1700_B(p_228975_0_, blockpos, p_228975_2_, p_228975_3_);
    }

    private static void n_1700_B(String p_228976_0_, c_1514_x p_228976_1_, int p_228976_2_, int p_228976_3_) {
        double d0 = 1.3;
        double d1 = 0.2;
        double d2 = (double)p_228976_1_.getX() + 0.5;
        double d3 = (double)p_228976_1_.getY() + 1.3 + (double)p_228976_2_ * 0.2;
        double d4 = (double)p_228976_1_.getZ() + 0.5;
        n_4915_r.n_1700_B(p_228976_0_, d2, d3, d4, p_228976_3_, 0.02f, true, 0.0f, true);
    }

    private static void n_1700_B(Position p_228974_0_, int p_228974_1_, String p_228974_2_, int p_228974_3_, float p_228974_4_) {
        double d0 = 2.4;
        double d1 = 0.25;
        c_1514_x blockpos = new c_1514_x(p_228974_0_);
        double d2 = (double)blockpos.getX() + 0.5;
        double d3 = p_228974_0_.J_1907_R() + 2.4 + (double)p_228974_1_ * 0.25;
        double d4 = (double)blockpos.getZ() + 0.5;
        float f = 0.5f;
        n_4915_r.n_1700_B(p_228974_2_, d2, d3, d4, p_228974_3_, p_228974_4_, false, 0.5f, true);
    }

    private h_3572_K v_4262_N() {
        return this.n_1700_B.s_956_w.M_588_G();
    }

    private String n_1700_B(n_1700_B p_228965_1_, c_1514_x p_228965_2_) {
        float f = u_530_F.n_1700_B(p_228965_2_.distanceSq(p_228965_1_.R_4764_Y.n_1700_B(), p_228965_1_.R_4764_Y.J_1907_R(), p_228965_1_.R_4764_Y.R_4764_Y(), true));
        double d0 = (double)Math.round(f * 10.0f) / 10.0;
        return p_228965_2_.getCoordinatesAsString() + " (dist " + d0 + ")";
    }

    private boolean G_564_y(n_1700_B p_228990_1_) {
        return Objects.equals(this.G_564_y, p_228990_1_.n_1700_B);
    }

    private boolean P_1922_E(n_1700_B p_228992_1_) {
        V_772_m playerentity = this.n_1700_B.Y_259_p;
        c_1514_x blockpos = new c_1514_x(playerentity.O_3598_v(), p_228992_1_.R_4764_Y.J_1907_R(), playerentity.l_2647_k());
        c_1514_x blockpos1 = new c_1514_x(p_228992_1_.R_4764_Y);
        return blockpos.withinDistance(blockpos1, 30.0);
    }

    private Collection<UUID> J_1907_R(c_1514_x p_228983_1_) {
        return this.R_4764_Y.values().stream().filter(p_228970_1_ -> p_228970_1_.n_1700_B(p_228983_1_)).map(n_1700_B::n_1700_B).collect(Collectors.toSet());
    }

    private Map<c_1514_x, List<String>> w_1484_f() {
        HashMap map = Maps.newHashMap();
        for (n_1700_B beedebugrenderer$bee : this.R_4764_Y.values()) {
            if (beedebugrenderer$bee.P_1922_E == null || this.J_1907_R.containsKey(beedebugrenderer$bee.P_1922_E)) continue;
            map.computeIfAbsent(beedebugrenderer$bee.P_1922_E, p_241725_0_ -> Lists.newArrayList()).add(beedebugrenderer$bee.J_1907_R());
        }
        return map;
    }

    private void t_148_a() {
        n_4915_r.n_1700_B(this.n_1700_B.g_2268_R(), 8).ifPresent(p_228963_1_ -> {
            this.G_564_y = p_228963_1_.w_2705_t();
        });
    }

    public static class J_1907_R {
        public final c_1514_x n_1700_B;
        public final String J_1907_R;
        public final int R_4764_Y;
        public final int G_564_y;
        public final boolean P_1922_E;
        public final long u_1723_Y;

        public J_1907_R(c_1514_x p_i226029_1_, String p_i226029_2_, int p_i226029_3_, int p_i226029_4_, boolean p_i226029_5_, long p_i226029_6_) {
            this.n_1700_B = p_i226029_1_;
            this.J_1907_R = p_i226029_2_;
            this.R_4764_Y = p_i226029_3_;
            this.G_564_y = p_i226029_4_;
            this.P_1922_E = p_i226029_5_;
            this.u_1723_Y = p_i226029_6_;
        }
    }

    public static class n_1700_B {
        public final UUID n_1700_B;
        public final int J_1907_R;
        public final Position R_4764_Y;
        @Nullable
        public final b_1722_e G_564_y;
        @Nullable
        public final c_1514_x P_1922_E;
        @Nullable
        public final c_1514_x u_1723_Y;
        public final int v_4262_N;
        public final List<String> w_1484_f = Lists.newArrayList();
        public final Set<c_1514_x> t_148_a = Sets.newHashSet();

        public n_1700_B(UUID p_i226028_1_, int p_i226028_2_, Position p_i226028_3_, b_1722_e p_i226028_4_, c_1514_x p_i226028_5_, c_1514_x p_i226028_6_, int p_i226028_7_) {
            this.n_1700_B = p_i226028_1_;
            this.J_1907_R = p_i226028_2_;
            this.R_4764_Y = p_i226028_3_;
            this.G_564_y = p_i226028_4_;
            this.P_1922_E = p_i226028_5_;
            this.u_1723_Y = p_i226028_6_;
            this.v_4262_N = p_i226028_7_;
        }

        public boolean n_1700_B(c_1514_x p_229008_1_) {
            return this.P_1922_E != null && this.P_1922_E.equals(p_229008_1_);
        }

        public UUID n_1700_B() {
            return this.n_1700_B;
        }

        public String J_1907_R() {
            return U_253_b.n_1700_B(this.n_1700_B);
        }

        public String toString() {
            return this.J_1907_R();
        }

        public boolean R_4764_Y() {
            return this.u_1723_Y != null;
        }
    }
}



