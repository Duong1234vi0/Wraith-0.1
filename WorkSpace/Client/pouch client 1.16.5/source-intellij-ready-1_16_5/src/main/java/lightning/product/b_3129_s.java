/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.F_4023_g;
import lightning.product.J_2538_C;
import lightning.product.MobEffects;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.R_2450_T;
import lightning.product.Stats;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.W_4304_a;
import lightning.product.ClientboundSoundPacket;
import lightning.product.Y_1387_d;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.e_933_M;
import lightning.product.BossEvent;
import lightning.product.k_2610_C;
import lightning.product.ServerBossEvent;
import lightning.product.n_3832_I;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.u_743_i;
import lightning.product.x_282_a;
import lightning.product.z_2963_s;

public class b_3129_s {
    private static final x_282_a n_1700_B = new F_2904_S("event.minecraft.raid");
    private static final x_282_a J_1907_R = new F_2904_S("event.minecraft.raid.victory");
    private static final x_282_a R_4764_Y = new F_2904_S("event.minecraft.raid.defeat");
    private static final x_282_a G_564_y = n_1700_B.P_1922_E().n_1700_B(" - ").n_1700_B(J_1907_R);
    private static final x_282_a P_1922_E = n_1700_B.P_1922_E().n_1700_B(" - ").n_1700_B(R_4764_Y);
    private final Map<Integer, W_4304_a> u_1723_Y = Maps.newHashMap();
    private final Map<Integer, Set<W_4304_a>> v_4262_N = Maps.newHashMap();
    private final Set<UUID> w_1484_f = Sets.newHashSet();
    private long t_148_a;
    private c_1514_x s_956_w;
    private final e_3591_l u_2550_I;
    private boolean M_588_G;
    private final int P_4830_p;
    private float h_1847_R;
    private int Q_4569_t;
    private boolean M_182_A;
    private int t_1786_h;
    private final ServerBossEvent multiplayerClientSuggestionProvider = new ServerBossEvent(n_1700_B, BossEvent.n_1700_B.R_4764_Y, BossEvent.J_1907_R.R_4764_Y);
    private int w_1457_N;
    private int Y_601_j;
    private final Random Y_259_p = new Random();
    private final int Q_2552_b;
    private n_1700_B C_2741_M;
    private int k_2293_S;
    private Optional<c_1514_x> q_2307_F = Optional.empty();

    public b_3129_s(int p_i50144_1_, e_3591_l p_i50144_2_, c_1514_x p_i50144_3_) {
        this.P_4830_p = p_i50144_1_;
        this.u_2550_I = p_i50144_2_;
        this.M_182_A = true;
        this.Y_601_j = 300;
        this.multiplayerClientSuggestionProvider.n_1700_B(0.0f);
        this.s_956_w = p_i50144_3_;
        this.Q_2552_b = this.n_1700_B(p_i50144_2_.x_607_J());
        this.C_2741_M = lightning.product.b_3129_s$n_1700_B.n_1700_B;
    }

    public b_3129_s(e_3591_l p_i50145_1_, U_2912_j p_i50145_2_) {
        this.u_2550_I = p_i50145_1_;
        this.P_4830_p = p_i50145_2_.w_1484_f("Id");
        this.M_588_G = p_i50145_2_.t_1786_h("Started");
        this.M_182_A = p_i50145_2_.t_1786_h("Active");
        this.t_148_a = p_i50145_2_.t_148_a("TicksActive");
        this.Q_4569_t = p_i50145_2_.w_1484_f("BadOmenLevel");
        this.t_1786_h = p_i50145_2_.w_1484_f("GroupsSpawned");
        this.Y_601_j = p_i50145_2_.w_1484_f("PreRaidTicks");
        this.w_1457_N = p_i50145_2_.w_1484_f("PostRaidTicks");
        this.h_1847_R = p_i50145_2_.s_956_w("TotalHealth");
        this.s_956_w = new c_1514_x(p_i50145_2_.w_1484_f("CX"), p_i50145_2_.w_1484_f("CY"), p_i50145_2_.w_1484_f("CZ"));
        this.Q_2552_b = p_i50145_2_.w_1484_f("NumGroups");
        this.C_2741_M = lightning.product.b_3129_s$n_1700_B.n_1700_B(p_i50145_2_.M_588_G("Status"));
        this.w_1484_f.clear();
        if (p_i50145_2_.R_4764_Y("HeroesOfTheVillage", 9)) {
            q_2896_o listnbt = p_i50145_2_.G_564_y("HeroesOfTheVillage", 11);
            for (int i = 0; i < listnbt.size(); ++i) {
                this.w_1484_f.add(n_3832_I.n_1700_B(listnbt.s_956_w(i)));
            }
        }
    }

    public boolean n_1700_B() {
        return this.P_1922_E() || this.u_1723_Y();
    }

    public boolean J_1907_R() {
        return this.R_4764_Y() && this.M_182_A() == 0 && this.Y_601_j > 0;
    }

    public boolean R_4764_Y() {
        return this.t_1786_h > 0;
    }

    public boolean G_564_y() {
        return this.C_2741_M == lightning.product.b_3129_s$n_1700_B.G_564_y;
    }

    public boolean P_1922_E() {
        return this.C_2741_M == lightning.product.b_3129_s$n_1700_B.J_1907_R;
    }

    public boolean u_1723_Y() {
        return this.C_2741_M == lightning.product.b_3129_s$n_1700_B.R_4764_Y;
    }

    public b_4507_u v_4262_N() {
        return this.u_2550_I;
    }

    public boolean w_1484_f() {
        return this.M_588_G;
    }

    public int t_148_a() {
        return this.t_1786_h;
    }

    private Predicate<B_4088_l> Q_2552_b() {
        return p_221302_1_ -> {
            c_1514_x blockpos = p_221302_1_.b_2312_j();
            return p_221302_1_.RealmsLongRunningMcoTaskScreen() && this.u_2550_I.Z_875_P(blockpos) == this;
        };
    }

    private void C_2741_M() {
        HashSet set = Sets.newHashSet(this.multiplayerClientSuggestionProvider.M_182_A());
        List<B_4088_l> list = this.u_2550_I.n_1700_B(this.Q_2552_b());
        for (B_4088_l serverplayerentity : list) {
            if (set.contains(serverplayerentity)) continue;
            this.multiplayerClientSuggestionProvider.n_1700_B(serverplayerentity);
        }
        for (B_4088_l serverplayerentity1 : set) {
            if (list.contains(serverplayerentity1)) continue;
            this.multiplayerClientSuggestionProvider.J_1907_R(serverplayerentity1);
        }
    }

    public int s_956_w() {
        return 5;
    }

    public int u_2550_I() {
        return this.Q_4569_t;
    }

    public void n_1700_B(a_3913_L player) {
        if (player.J_1907_R(MobEffects.t_4043_B)) {
            this.Q_4569_t += player.R_4764_Y(MobEffects.t_4043_B).R_4764_Y() + 1;
            this.Q_4569_t = u_530_F.n_1700_B(this.Q_4569_t, 0, this.s_956_w());
        }
        player.G_564_y(MobEffects.t_4043_B);
    }

    public void M_588_G() {
        this.M_182_A = false;
        this.multiplayerClientSuggestionProvider.R_4764_Y();
        this.C_2741_M = lightning.product.b_3129_s$n_1700_B.G_564_y;
    }

    public void P_4830_p() {
        if (!this.G_564_y()) {
            if (this.C_2741_M == lightning.product.b_3129_s$n_1700_B.n_1700_B) {
                boolean flag = this.M_182_A;
                this.M_182_A = this.u_2550_I.M_588_G(this.s_956_w);
                if (this.u_2550_I.x_607_J() == R_2450_T.n_1700_B) {
                    this.M_588_G();
                    return;
                }
                if (flag != this.M_182_A) {
                    this.multiplayerClientSuggestionProvider.G_564_y(this.M_182_A);
                }
                if (!this.M_182_A) {
                    return;
                }
                if (!this.u_2550_I.q_2307_F(this.s_956_w)) {
                    this.k_2293_S();
                }
                if (!this.u_2550_I.q_2307_F(this.s_956_w)) {
                    if (this.t_1786_h > 0) {
                        this.C_2741_M = lightning.product.b_3129_s$n_1700_B.R_4764_Y;
                    } else {
                        this.M_588_G();
                    }
                }
                ++this.t_148_a;
                if (this.t_148_a >= 48000L) {
                    this.M_588_G();
                    return;
                }
                int i = this.M_182_A();
                if (i == 0 && this.q_2307_F()) {
                    if (this.Y_601_j <= 0) {
                        if (this.Y_601_j == 0 && this.t_1786_h > 0) {
                            this.Y_601_j = 300;
                            this.multiplayerClientSuggestionProvider.n_1700_B(n_1700_B);
                            return;
                        }
                    } else {
                        boolean flag2;
                        boolean flag1 = this.q_2307_F.isPresent();
                        boolean bl = flag2 = !flag1 && this.Y_601_j % 5 == 0;
                        if (flag1 && !this.u_2550_I.Y_259_p().n_1700_B(new Y_1387_d(this.q_2307_F.get()))) {
                            flag2 = true;
                        }
                        if (flag2) {
                            int j = 0;
                            if (this.Y_601_j < 100) {
                                j = 1;
                            } else if (this.Y_601_j < 40) {
                                j = 2;
                            }
                            this.q_2307_F = this.R_4764_Y(j);
                        }
                        if (this.Y_601_j == 300 || this.Y_601_j % 20 == 0) {
                            this.C_2741_M();
                        }
                        --this.Y_601_j;
                        this.multiplayerClientSuggestionProvider.n_1700_B(u_530_F.n_1700_B((float)(300 - this.Y_601_j) / 300.0f, 0.0f, 1.0f));
                    }
                }
                if (this.t_148_a % 20L == 0L) {
                    this.C_2741_M();
                    this.Y_1740_V();
                    if (i > 0) {
                        if (i <= 2) {
                            this.multiplayerClientSuggestionProvider.n_1700_B(n_1700_B.P_1922_E().n_1700_B(" - ").n_1700_B(new F_2904_S("event.minecraft.raid.raiders_remaining", i)));
                        } else {
                            this.multiplayerClientSuggestionProvider.n_1700_B(n_1700_B);
                        }
                    } else {
                        this.multiplayerClientSuggestionProvider.n_1700_B(n_1700_B);
                    }
                }
                boolean flag3 = false;
                int k = 0;
                while (this.t_4043_B()) {
                    c_1514_x blockpos;
                    c_1514_x c_1514_x2 = blockpos = this.q_2307_F.isPresent() ? this.q_2307_F.get() : this.n_1700_B(k, 20);
                    if (blockpos != null) {
                        this.M_588_G = true;
                        this.J_1907_R(blockpos);
                        if (!flag3) {
                            this.n_1700_B(blockpos);
                            flag3 = true;
                        }
                    } else {
                        ++k;
                    }
                    if (k <= 3) continue;
                    this.M_588_G();
                    break;
                }
                if (this.w_1484_f() && !this.q_2307_F() && i == 0) {
                    if (this.w_1457_N < 40) {
                        ++this.w_1457_N;
                    } else {
                        this.C_2741_M = lightning.product.b_3129_s$n_1700_B.J_1907_R;
                        for (UUID uuid : this.w_1484_f) {
                            N_4263_v entity = this.u_2550_I.J_1907_R(uuid);
                            if (!(entity instanceof r_4811_B) || entity.d_2461_k()) continue;
                            r_4811_B livingentity = (r_4811_B)entity;
                            livingentity.n_1700_B(new k_2610_C(MobEffects.x_607_J, 48000, this.Q_4569_t - 1, false, false, true));
                            if (!(livingentity instanceof B_4088_l)) continue;
                            B_4088_l serverplayerentity = (B_4088_l)livingentity;
                            serverplayerentity.J_1907_R(Stats.dtoRealmsServerAddress);
                            U_3554_Q.n_3318_d.n_1700_B(serverplayerentity);
                        }
                    }
                }
                this.x_607_J();
            } else if (this.n_1700_B()) {
                ++this.k_2293_S;
                if (this.k_2293_S >= 600) {
                    this.M_588_G();
                    return;
                }
                if (this.k_2293_S % 20 == 0) {
                    this.C_2741_M();
                    this.multiplayerClientSuggestionProvider.G_564_y(true);
                    if (this.P_1922_E()) {
                        this.multiplayerClientSuggestionProvider.n_1700_B(0.0f);
                        this.multiplayerClientSuggestionProvider.n_1700_B(G_564_y);
                    } else {
                        this.multiplayerClientSuggestionProvider.n_1700_B(P_1922_E);
                    }
                }
            }
        }
    }

    private void k_2293_S() {
        Stream<SectionPos> stream = SectionPos.n_1700_B(SectionPos.n_1700_B(this.s_956_w), 2);
        stream.filter(this.u_2550_I::n_1700_B).map(SectionPos::u_2550_I).min(Comparator.comparingDouble(p_223025_1_ -> p_223025_1_.distanceSq(this.s_956_w))).ifPresent(this::R_4764_Y);
    }

    private Optional<c_1514_x> R_4764_Y(int p_221313_1_) {
        for (int i = 0; i < 3; ++i) {
            c_1514_x blockpos = this.n_1700_B(p_221313_1_, 1);
            if (blockpos == null) continue;
            return Optional.of(blockpos);
        }
        return Optional.empty();
    }

    private boolean q_2307_F() {
        if (this.c_3005_b()) {
            return !this.H_2857_Y();
        }
        return !this.Z_875_P();
    }

    private boolean Z_875_P() {
        return this.t_148_a() == this.Q_2552_b;
    }

    private boolean c_3005_b() {
        return this.Q_4569_t > 1;
    }

    private boolean H_2857_Y() {
        return this.t_148_a() > this.Q_2552_b;
    }

    private boolean A_4115_X() {
        return this.Z_875_P() && this.M_182_A() == 0 && this.c_3005_b();
    }

    private void Y_1740_V() {
        Iterator<Set<W_4304_a>> iterator = this.v_4262_N.values().iterator();
        HashSet set = Sets.newHashSet();
        while (iterator.hasNext()) {
            Set<W_4304_a> set1 = iterator.next();
            for (W_4304_a abstractraiderentity : set1) {
                c_1514_x blockpos = abstractraiderentity.b_2312_j();
                if (!abstractraiderentity.t_4219_U && abstractraiderentity.O_508_d.g_2268_R() == this.u_2550_I.g_2268_R() && !(this.s_956_w.distanceSq(blockpos) >= 12544.0)) {
                    if (abstractraiderentity.RealmsWorldResetDto <= 600) continue;
                    if (this.u_2550_I.J_1907_R(abstractraiderentity.w_2705_t()) == null) {
                        set.add(abstractraiderentity);
                    }
                    if (!this.u_2550_I.q_2307_F(blockpos) && abstractraiderentity.g_4560_H() > 2400) {
                        abstractraiderentity.J_1907_R(abstractraiderentity.f_2787_O() + 1);
                    }
                    if (abstractraiderentity.f_2787_O() < 30) continue;
                    set.add(abstractraiderentity);
                    continue;
                }
                set.add(abstractraiderentity);
            }
        }
        for (W_4304_a abstractraiderentity1 : set) {
            this.n_1700_B(abstractraiderentity1, true);
        }
    }

    private void n_1700_B(c_1514_x p_221293_1_) {
        float f = 13.0f;
        int i = 64;
        Collection<B_4088_l> collection = this.multiplayerClientSuggestionProvider.M_182_A();
        for (B_4088_l serverplayerentity : this.u_2550_I.multiplayerClientSuggestionProvider()) {
            e_2866_D vector3d = serverplayerentity.s_4990_V();
            e_2866_D vector3d1 = e_2866_D.n_1700_B(p_221293_1_);
            float f1 = u_530_F.n_1700_B((vector3d1.J_1907_R - vector3d.J_1907_R) * (vector3d1.J_1907_R - vector3d.J_1907_R) + (vector3d1.G_564_y - vector3d.G_564_y) * (vector3d1.G_564_y - vector3d.G_564_y));
            double d0 = vector3d.J_1907_R + (double)(13.0f / f1) * (vector3d1.J_1907_R - vector3d.J_1907_R);
            double d1 = vector3d.G_564_y + (double)(13.0f / f1) * (vector3d1.G_564_y - vector3d.G_564_y);
            if (!(f1 <= 64.0f) && !collection.contains(serverplayerentity)) continue;
            serverplayerentity.n_1700_B.n_1700_B(new ClientboundSoundPacket(SoundEvents.Z_3822_q, D_38_f.v_4262_N, d0, serverplayerentity.X_2960_b(), d1, 64.0f, 1.0f));
        }
    }

    private void J_1907_R(c_1514_x p_221294_1_) {
        boolean flag = false;
        int i = this.t_1786_h + 1;
        this.h_1847_R = 0.0f;
        DifficultyInstance difficultyinstance = this.u_2550_I.J_1907_R(p_221294_1_);
        boolean flag1 = this.A_4115_X();
        for (J_1907_R raid$wavemember : lightning.product.b_3129_s$J_1907_R.u_1723_Y) {
            int j = this.n_1700_B(raid$wavemember, i, flag1) + this.n_1700_B(raid$wavemember, this.Y_259_p, i, difficultyinstance, flag1);
            int k = 0;
            for (int l = 0; l < j; ++l) {
                W_4304_a abstractraiderentity = raid$wavemember.v_4262_N.n_1700_B(this.u_2550_I);
                if (!flag && abstractraiderentity.c_2086_l()) {
                    abstractraiderentity.Y_259_p(true);
                    this.n_1700_B(i, abstractraiderentity);
                    flag = true;
                }
                this.n_1700_B(i, abstractraiderentity, p_221294_1_, false);
                if (raid$wavemember.v_4262_N != t_5_h.e_1992_r) continue;
                W_4304_a abstractraiderentity1 = null;
                if (i == this.n_1700_B(R_2450_T.R_4764_Y)) {
                    abstractraiderentity1 = t_5_h.p_178_J.n_1700_B(this.u_2550_I);
                } else if (i >= this.n_1700_B(R_2450_T.G_564_y)) {
                    abstractraiderentity1 = k == 0 ? (W_4304_a)t_5_h.C_2741_M.n_1700_B(this.u_2550_I) : (W_4304_a)t_5_h.y_1700_S.n_1700_B(this.u_2550_I);
                }
                ++k;
                if (abstractraiderentity1 == null) continue;
                this.n_1700_B(i, abstractraiderentity1, p_221294_1_, false);
                abstractraiderentity1.n_1700_B(p_221294_1_, 0.0f, 0.0f);
                abstractraiderentity1.s_956_w(abstractraiderentity);
            }
        }
        this.q_2307_F = Optional.empty();
        ++this.t_1786_h;
        this.h_1847_R();
        this.x_607_J();
    }

    public void n_1700_B(int wave, W_4304_a p_221317_2_, @Nullable c_1514_x p_221317_3_, boolean p_221317_4_) {
        boolean flag = this.J_1907_R(wave, p_221317_2_);
        if (flag) {
            p_221317_2_.n_1700_B(this);
            p_221317_2_.n_1700_B(wave);
            p_221317_2_.w_1457_N(true);
            p_221317_2_.J_1907_R(0);
            if (!p_221317_4_ && p_221317_3_ != null) {
                p_221317_2_.J_1907_R((double)p_221317_3_.getX() + 0.5, (double)p_221317_3_.getY() + 1.0, (double)p_221317_3_.getZ() + 0.5);
                p_221317_2_.n_1700_B(this.u_2550_I, this.u_2550_I.J_1907_R(p_221317_3_), a_3160_D.w_1484_f, (V_3157_k)null, null);
                p_221317_2_.n_1700_B(wave, false);
                p_221317_2_.u_1723_Y(true);
                this.u_2550_I.n_1700_B((N_4263_v)p_221317_2_);
            }
        }
    }

    public void h_1847_R() {
        this.multiplayerClientSuggestionProvider.n_1700_B(u_530_F.n_1700_B(this.Q_4569_t() / this.h_1847_R, 0.0f, 1.0f));
    }

    public float Q_4569_t() {
        float f = 0.0f;
        for (Set<W_4304_a> set : this.v_4262_N.values()) {
            for (W_4304_a abstractraiderentity : set) {
                f += abstractraiderentity.g_46_E();
            }
        }
        return f;
    }

    private boolean t_4043_B() {
        return this.Y_601_j == 0 && (this.t_1786_h < this.Q_2552_b || this.A_4115_X()) && this.M_182_A() == 0;
    }

    public int M_182_A() {
        return this.v_4262_N.values().stream().mapToInt(Set::size).sum();
    }

    public void n_1700_B(W_4304_a p_221322_1_, boolean p_221322_2_) {
        boolean flag;
        Set<W_4304_a> set = this.v_4262_N.get(p_221322_1_.o_82_k());
        if (set != null && (flag = set.remove(p_221322_1_))) {
            if (p_221322_2_) {
                this.h_1847_R -= p_221322_1_.g_46_E();
            }
            p_221322_1_.n_1700_B((b_3129_s)null);
            this.h_1847_R();
            this.x_607_J();
        }
    }

    private void x_607_J() {
        this.u_2550_I.RealmsClientConfig().R_4764_Y();
    }

    public static Z_1993_T t_1786_h() {
        Z_1993_T itemstack = new Z_1993_T(Items.o_3946_o);
        U_2912_j compoundnbt = itemstack.n_1700_B("BlockEntityTag");
        q_2896_o listnbt = new J_2538_C.n_1700_B().n_1700_B(J_2538_C.Z_875_P, e_933_M.s_956_w).n_1700_B(J_2538_C.u_1723_Y, e_933_M.t_148_a).n_1700_B(J_2538_C.s_956_w, e_933_M.w_1484_f).n_1700_B(J_2538_C.t_4043_B, e_933_M.t_148_a).n_1700_B(J_2538_C.u_2550_I, e_933_M.M_182_A).n_1700_B(J_2538_C.H_2857_Y, e_933_M.t_148_a).n_1700_B(J_2538_C.q_2307_F, e_933_M.t_148_a).n_1700_B(J_2538_C.t_4043_B, e_933_M.M_182_A).n_1700_B();
        compoundnbt.n_1700_B("Patterns", listnbt);
        itemstack.n_1700_B(Z_1993_T.n_1700_B.u_1723_Y);
        itemstack.n_1700_B(new F_2904_S("block.minecraft.ominous_banner").n_1700_B(D_4024_W.v_4262_N));
        return itemstack;
    }

    @Nullable
    public W_4304_a n_1700_B(int p_221332_1_) {
        return this.u_1723_Y.get(p_221332_1_);
    }

    @Nullable
    private c_1514_x n_1700_B(int p_221298_1_, int p_221298_2_) {
        int i = p_221298_1_ == 0 ? 2 : 2 - p_221298_1_;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i1 = 0; i1 < p_221298_2_; ++i1) {
            float f = this.u_2550_I.w_1457_N.nextFloat() * ((float)Math.PI * 2);
            int j = this.s_956_w.getX() + u_530_F.G_564_y(u_530_F.J_1907_R(f) * 32.0f * (float)i) + this.u_2550_I.w_1457_N.nextInt(5);
            int l = this.s_956_w.getZ() + u_530_F.G_564_y(u_530_F.n_1700_B(f) * 32.0f * (float)i) + this.u_2550_I.w_1457_N.nextInt(5);
            int k = this.u_2550_I.n_1700_B(z_2963_s.n_1700_B.J_1907_R, j, l);
            blockpos$mutable.n_1700_B(j, k, l);
            if (this.u_2550_I.q_2307_F(blockpos$mutable) && p_221298_1_ < 2 || !this.u_2550_I.n_1700_B(blockpos$mutable.getX() - 10, blockpos$mutable.getY() - 10, blockpos$mutable.getZ() - 10, blockpos$mutable.getX() + 10, blockpos$mutable.getY() + 10, blockpos$mutable.getZ() + 10) || !this.u_2550_I.Y_259_p().n_1700_B(new Y_1387_d(blockpos$mutable)) || !u_743_i.n_1700_B(F_4023_g.R_4764_Y.n_1700_B, this.u_2550_I, (c_1514_x)blockpos$mutable, t_5_h.e_1992_r) && (!this.u_2550_I.getBlockState((c_1514_x)blockpos$mutable.down()).n_1700_B(a_3742_W.X_290_I) || !this.u_2550_I.getBlockState(blockpos$mutable).v_4262_N())) continue;
            return blockpos$mutable;
        }
        return null;
    }

    private boolean J_1907_R(int p_221287_1_, W_4304_a p_221287_2_) {
        return this.n_1700_B(p_221287_1_, p_221287_2_, true);
    }

    public boolean n_1700_B(int p_221300_1_, W_4304_a p_221300_2_, boolean p_221300_3_) {
        this.v_4262_N.computeIfAbsent(p_221300_1_, p_221323_0_ -> Sets.newHashSet());
        Set<W_4304_a> set = this.v_4262_N.get(p_221300_1_);
        W_4304_a abstractraiderentity = null;
        for (W_4304_a abstractraiderentity1 : set) {
            if (!abstractraiderentity1.w_2705_t().equals(p_221300_2_.w_2705_t())) continue;
            abstractraiderentity = abstractraiderentity1;
            break;
        }
        if (abstractraiderentity != null) {
            set.remove(abstractraiderentity);
            set.add(p_221300_2_);
        }
        set.add(p_221300_2_);
        if (p_221300_3_) {
            this.h_1847_R += p_221300_2_.g_46_E();
        }
        this.h_1847_R();
        this.x_607_J();
        return true;
    }

    public void n_1700_B(int raidId, W_4304_a p_221324_2_) {
        this.u_1723_Y.put(raidId, p_221324_2_);
        p_221324_2_.n_1700_B(e_1174_E.u_1723_Y, b_3129_s.t_1786_h());
        p_221324_2_.n_1700_B(e_1174_E.u_1723_Y, 2.0f);
    }

    public void J_1907_R(int p_221296_1_) {
        this.u_1723_Y.remove(p_221296_1_);
    }

    public c_1514_x multiplayerClientSuggestionProvider() {
        return this.s_956_w;
    }

    private void R_4764_Y(c_1514_x p_223024_1_) {
        this.s_956_w = p_223024_1_;
    }

    public int w_1457_N() {
        return this.P_4830_p;
    }

    private int n_1700_B(J_1907_R p_221330_1_, int p_221330_2_, boolean p_221330_3_) {
        return p_221330_3_ ? p_221330_1_.w_1484_f[this.Q_2552_b] : p_221330_1_.w_1484_f[p_221330_2_];
    }

    private int n_1700_B(J_1907_R p_221335_1_, Random p_221335_2_, int wave, DifficultyInstance p_221335_4_, boolean p_221335_5_) {
        int i;
        R_2450_T difficulty = p_221335_4_.n_1700_B();
        boolean flag = difficulty == R_2450_T.J_1907_R;
        boolean flag1 = difficulty == R_2450_T.R_4764_Y;
        switch (p_221335_1_.ordinal()) {
            case 3: {
                if (flag || wave <= 2 || wave == 4) {
                    return 0;
                }
                i = 1;
                break;
            }
            case 0: 
            case 2: {
                if (flag) {
                    i = p_221335_2_.nextInt(2);
                    break;
                }
                if (flag1) {
                    i = 1;
                    break;
                }
                i = 2;
                break;
            }
            case 4: {
                i = !flag && p_221335_5_ ? 1 : 0;
                break;
            }
            default: {
                return 0;
            }
        }
        return i > 0 ? p_221335_2_.nextInt(i + 1) : 0;
    }

    public boolean Y_601_j() {
        return this.M_182_A;
    }

    public U_2912_j n_1700_B(U_2912_j nbt) {
        nbt.J_1907_R("Id", this.P_4830_p);
        nbt.n_1700_B("Started", this.M_588_G);
        nbt.n_1700_B("Active", this.M_182_A);
        nbt.n_1700_B("TicksActive", this.t_148_a);
        nbt.J_1907_R("BadOmenLevel", this.Q_4569_t);
        nbt.J_1907_R("GroupsSpawned", this.t_1786_h);
        nbt.J_1907_R("PreRaidTicks", this.Y_601_j);
        nbt.J_1907_R("PostRaidTicks", this.w_1457_N);
        nbt.n_1700_B("TotalHealth", this.h_1847_R);
        nbt.J_1907_R("NumGroups", this.Q_2552_b);
        nbt.n_1700_B("Status", this.C_2741_M.n_1700_B());
        nbt.J_1907_R("CX", this.s_956_w.getX());
        nbt.J_1907_R("CY", this.s_956_w.getY());
        nbt.J_1907_R("CZ", this.s_956_w.getZ());
        q_2896_o listnbt = new q_2896_o();
        for (UUID uuid : this.w_1484_f) {
            listnbt.add(n_3832_I.n_1700_B(uuid));
        }
        nbt.n_1700_B("HeroesOfTheVillage", listnbt);
        return nbt;
    }

    public int n_1700_B(R_2450_T difficultyIn) {
        switch (difficultyIn) {
            case J_1907_R: {
                return 3;
            }
            case R_4764_Y: {
                return 5;
            }
            case G_564_y: {
                return 7;
            }
        }
        return 0;
    }

    public float Y_259_p() {
        int i = this.u_2550_I();
        if (i == 2) {
            return 0.1f;
        }
        if (i == 3) {
            return 0.25f;
        }
        if (i == 4) {
            return 0.5f;
        }
        return i == 5 ? 0.75f : 0.0f;
    }

    public void n_1700_B(N_4263_v p_221311_1_) {
        this.w_1484_f.add(p_221311_1_.w_2705_t());
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        private static final n_1700_B[] P_1922_E;
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static n_1700_B n_1700_B(String name) {
            for (n_1700_B raid$status : P_1922_E) {
                if (!name.equalsIgnoreCase(raid$status.name())) continue;
                return raid$status;
            }
            return n_1700_B;
        }

        public String n_1700_B() {
            return this.name().toLowerCase(Locale.ROOT);
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            u_1723_Y = lightning.product.b_3129_s$n_1700_B.J_1907_R();
            P_1922_E = lightning.product.b_3129_s$n_1700_B.values();
        }
    }

    static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(t_5_h.y_1700_S, new int[]{0, 0, 2, 0, 1, 4, 2, 5});
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(t_5_h.C_2741_M, new int[]{0, 0, 0, 0, 0, 1, 1, 2});
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R(t_5_h.p_178_J, new int[]{0, 4, 3, 3, 4, 4, 4, 2});
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R(t_5_h.RetryCallException, new int[]{0, 0, 0, 0, 3, 0, 0, 1});
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R(t_5_h.e_1992_r, new int[]{0, 0, 0, 1, 0, 1, 0, 2});
        private static final J_1907_R[] u_1723_Y;
        private final t_5_h<? extends W_4304_a> v_4262_N;
        private final int[] w_1484_f;
        private static final /* synthetic */ J_1907_R[] t_148_a;

        public static J_1907_R[] values() {
            return (J_1907_R[])t_148_a.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(t_5_h<? extends W_4304_a> typeIn, int[] waveCountsIn) {
            this.v_4262_N = typeIn;
            this.w_1484_f = waveCountsIn;
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            t_148_a = lightning.product.b_3129_s$J_1907_R.n_1700_B();
            u_1723_Y = lightning.product.b_3129_s$J_1907_R.values();
        }
    }
}


