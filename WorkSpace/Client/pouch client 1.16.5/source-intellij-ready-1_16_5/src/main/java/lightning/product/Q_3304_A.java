/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lightning.product.A_4919_q;
import lightning.product.E_1879_e;
import lightning.product.E_4668_a;
import lightning.product.F_427_K;
import lightning.product.SetLookAndInteract;
import lightning.product.I_408_V;
import lightning.product.SetWalkTargetFromAttackTargetIfTargetOutOfReach;
import lightning.product.LookAtTargetSink;
import lightning.product.N_4263_v;
import lightning.product.S_3014_o;
import lightning.product.StrollToPoi;
import lightning.product.SetEntityLookTarget;
import lightning.product.a_3236_r;
import lightning.product.a_4468_e;
import lightning.product.b_3448_R;
import lightning.product.AbstractPiglin;
import lightning.product.MoveToTargetSink;
import lightning.product.Behavior;
import lightning.product.StartAttacking;
import lightning.product.InteractWith;
import lightning.product.Activity;
import lightning.product.StrollAroundPoi;
import lightning.product.MeleeAttack;
import lightning.product.StopBeingAngryIfTargetDead;
import lightning.product.RandomStroll;
import lightning.product.DoNothing;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class Q_3304_A {
    protected static E_4668_a<?> n_1700_B(S_3014_o p_242354_0_, E_4668_a<S_3014_o> p_242354_1_) {
        Q_3304_A.J_1907_R(p_242354_0_, p_242354_1_);
        Q_3304_A.R_4764_Y(p_242354_0_, p_242354_1_);
        Q_3304_A.G_564_y(p_242354_0_, p_242354_1_);
        p_242354_1_.n_1700_B((Set<Activity>)ImmutableSet.of((Object)Activity.n_1700_B));
        p_242354_1_.J_1907_R(Activity.J_1907_R);
        p_242354_1_.R_4764_Y();
        return p_242354_1_;
    }

    protected static void n_1700_B(S_3014_o p_242352_0_) {
        F_427_K globalpos = F_427_K.n_1700_B(p_242352_0_.O_508_d.g_2268_R(), p_242352_0_.b_2312_j());
        p_242352_0_.y_1945_D().n_1700_B(MemoryModuleType.J_1907_R, globalpos);
    }

    private static void J_1907_R(S_3014_o p_242359_0_, E_4668_a<S_3014_o> p_242359_1_) {
        p_242359_1_.n_1700_B(Activity.n_1700_B, 0, (ImmutableList<Behavior<S_3014_o>>)ImmutableList.of((Object)new LookAtTargetSink(45, 90), (Object)new MoveToTargetSink(), (Object)new a_4468_e(), new StopBeingAngryIfTargetDead()));
    }

    private static void R_4764_Y(S_3014_o p_242362_0_, E_4668_a<S_3014_o> p_242362_1_) {
        p_242362_1_.n_1700_B(Activity.J_1907_R, 10, (ImmutableList<Behavior<S_3014_o>>)ImmutableList.of(new StartAttacking<S_3014_o>(Q_3304_A::n_1700_B), Q_3304_A.n_1700_B(), Q_3304_A.J_1907_R(), (Object)new SetLookAndInteract(t_5_h.g_4106_L, 4)));
    }

    private static void G_564_y(S_3014_o p_242364_0_, E_4668_a<S_3014_o> p_242364_1_) {
        p_242364_1_.n_1700_B(Activity.u_2550_I, 10, (ImmutableList<Behavior<S_3014_o>>)ImmutableList.of(new b_3448_R(p_242361_1_ -> !Q_3304_A.n_1700_B((AbstractPiglin)p_242364_0_, p_242361_1_)), (Object)new SetWalkTargetFromAttackTargetIfTargetOutOfReach(1.0f), (Object)new MeleeAttack(20)), MemoryModuleType.Q_4569_t);
    }

    private static E_1879_e<S_3014_o> n_1700_B() {
        return new E_1879_e<S_3014_o>((List<Pair<Behavior<S_3014_o>, Integer>>)ImmutableList.of((Object)Pair.of((Object)new SetEntityLookTarget(t_5_h.g_4106_L, 8.0f), (Object)1), (Object)Pair.of((Object)new SetEntityLookTarget(t_5_h.i_1637_u, 8.0f), (Object)1), (Object)Pair.of((Object)new SetEntityLookTarget(t_5_h.Ping, 8.0f), (Object)1), (Object)Pair.of((Object)new SetEntityLookTarget(8.0f), (Object)1), (Object)Pair.of((Object)new DoNothing(30, 60), (Object)1)));
    }

    private static E_1879_e<S_3014_o> J_1907_R() {
        return new E_1879_e<S_3014_o>((List<Pair<Behavior<S_3014_o>, Integer>>)ImmutableList.of((Object)Pair.of((Object)new RandomStroll(0.6f), (Object)2), (Object)Pair.of(InteractWith.n_1700_B(t_5_h.i_1637_u, 8, MemoryModuleType.t_1786_h, 0.6f, 2), (Object)2), (Object)Pair.of(InteractWith.n_1700_B(t_5_h.Ping, 8, MemoryModuleType.t_1786_h, 0.6f, 2), (Object)2), (Object)Pair.of((Object)new StrollToPoi(MemoryModuleType.J_1907_R, 0.6f, 2, 100), (Object)2), (Object)Pair.of((Object)new StrollAroundPoi(MemoryModuleType.J_1907_R, 0.6f, 5), (Object)2), (Object)Pair.of((Object)new DoNothing(30, 60), (Object)1)));
    }

    protected static void J_1907_R(S_3014_o p_242358_0_) {
        E_4668_a<S_3014_o> brain = p_242358_0_.y_1945_D();
        Activity activity = brain.G_564_y().orElse(null);
        brain.n_1700_B((List<Activity>)ImmutableList.of((Object)Activity.u_2550_I, (Object)Activity.J_1907_R));
        Activity activity1 = brain.G_564_y().orElse(null);
        if (activity != activity1) {
            Q_3304_A.G_564_y(p_242358_0_);
        }
        p_242358_0_.multiplayerClientSuggestionProvider(brain.n_1700_B(MemoryModuleType.Q_4569_t));
    }

    private static boolean n_1700_B(AbstractPiglin p_242350_0_, r_4811_B p_242350_1_) {
        return Q_3304_A.n_1700_B(p_242350_0_).filter(p_242348_1_ -> p_242348_1_ == p_242350_1_).isPresent();
    }

    private static Optional<? extends r_4811_B> n_1700_B(AbstractPiglin p_242349_0_) {
        Optional<r_4811_B> optional = a_3236_r.n_1700_B((r_4811_B)p_242349_0_, MemoryModuleType.d_2461_k);
        if (optional.isPresent() && Q_3304_A.n_1700_B(optional.get())) {
            return optional;
        }
        Optional<? extends r_4811_B> optional1 = Q_3304_A.n_1700_B(p_242349_0_, MemoryModuleType.M_588_G);
        return optional1.isPresent() ? optional1 : p_242349_0_.y_1945_D().R_4764_Y(MemoryModuleType.v_4276_D);
    }

    private static boolean n_1700_B(r_4811_B p_242347_0_) {
        return I_408_V.u_1723_Y.test(p_242347_0_);
    }

    private static Optional<? extends r_4811_B> n_1700_B(AbstractPiglin p_242351_0_, MemoryModuleType<? extends r_4811_B> p_242351_1_) {
        return p_242351_0_.y_1945_D().R_4764_Y(p_242351_1_).filter(p_242357_1_ -> p_242357_1_.n_1700_B((N_4263_v)p_242351_0_, 12.0));
    }

    protected static void n_1700_B(S_3014_o p_242353_0_, r_4811_B p_242353_1_) {
        if (!(p_242353_1_ instanceof AbstractPiglin)) {
            A_4919_q.n_1700_B((AbstractPiglin)p_242353_0_, p_242353_1_);
        }
    }

    protected static void R_4764_Y(S_3014_o p_242360_0_) {
        if ((double)p_242360_0_.O_508_d.w_1457_N.nextFloat() < 0.0125) {
            Q_3304_A.G_564_y(p_242360_0_);
        }
    }

    private static void G_564_y(S_3014_o p_242363_0_) {
        p_242363_0_.y_1945_D().G_564_y().ifPresent(p_242355_1_ -> {
            if (p_242355_1_ == Activity.u_2550_I) {
                p_242363_0_.P_2295_B();
            }
        });
    }
}


