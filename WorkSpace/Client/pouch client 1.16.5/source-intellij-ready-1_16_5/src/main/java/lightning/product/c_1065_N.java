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
import lightning.product.AgableMob;
import lightning.product.E_1879_e;
import lightning.product.SetWalkTargetFromLookTarget;
import lightning.product.E_4668_a;
import lightning.product.BabyFollowAdult;
import lightning.product.I_408_V;
import lightning.product.J_2548_M;
import lightning.product.SetWalkTargetFromAttackTargetIfTargetOutOfReach;
import lightning.product.L_1885_c;
import lightning.product.LookAtTargetSink;
import lightning.product.Hoglin;
import lightning.product.SetWalkTargetAwayFrom;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.EraseMemoryIf;
import lightning.product.Z_530_i;
import lightning.product.SetEntityLookTarget;
import lightning.product.a_3236_r;
import lightning.product.b_3448_R;
import lightning.product.c_1514_x;
import lightning.product.MoveToTargetSink;
import lightning.product.TimeUtil;
import lightning.product.Behavior;
import lightning.product.StartAttacking;
import lightning.product.Activity;
import lightning.product.MeleeAttack;
import lightning.product.PathfinderMob;
import lightning.product.RandomStroll;
import lightning.product.AnimalMakeLove;
import lightning.product.BecomePassiveIfMemoryPresent;
import lightning.product.DoNothing;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;
import lightning.product.z_1480_R;

public class c_1065_N {
    private static final J_2548_M n_1700_B = TimeUtil.n_1700_B(5, 20);
    private static final J_2548_M J_1907_R = J_2548_M.n_1700_B(5, 16);

    protected static E_4668_a<?> n_1700_B(E_4668_a<Hoglin> p_234376_0_) {
        c_1065_N.J_1907_R(p_234376_0_);
        c_1065_N.R_4764_Y(p_234376_0_);
        c_1065_N.G_564_y(p_234376_0_);
        c_1065_N.P_1922_E(p_234376_0_);
        p_234376_0_.n_1700_B((Set<Activity>)ImmutableSet.of((Object)Activity.n_1700_B));
        p_234376_0_.J_1907_R(Activity.J_1907_R);
        p_234376_0_.R_4764_Y();
        return p_234376_0_;
    }

    private static void J_1907_R(E_4668_a<Hoglin> p_234382_0_) {
        p_234382_0_.n_1700_B(Activity.n_1700_B, 0, (ImmutableList<Behavior<Hoglin>>)ImmutableList.of((Object)new LookAtTargetSink(45, 90), (Object)new MoveToTargetSink()));
    }

    private static void R_4764_Y(E_4668_a<Hoglin> p_234385_0_) {
        p_234385_0_.n_1700_B(Activity.J_1907_R, 10, (ImmutableList<Behavior<Hoglin>>)ImmutableList.of((Object)new BecomePassiveIfMemoryPresent(MemoryModuleType.r_715_M, 200), (Object)new AnimalMakeLove(t_5_h.e_4240_b, 0.6f), SetWalkTargetAwayFrom.n_1700_B(MemoryModuleType.r_715_M, 1.0f, 8, true), new StartAttacking<Hoglin>(c_1065_N::G_564_y), new L_1885_c<PathfinderMob>(Hoglin::V_1176_p, SetWalkTargetAwayFrom.J_1907_R(MemoryModuleType.T_3594_S, 0.4f, 8, false)), new z_1480_R<r_4811_B>(new SetEntityLookTarget(8.0f), J_2548_M.n_1700_B(30, 60)), new BabyFollowAdult(J_1907_R, 0.6f), c_1065_N.n_1700_B()));
    }

    private static void G_564_y(E_4668_a<Hoglin> p_234388_0_) {
        p_234388_0_.n_1700_B(Activity.u_2550_I, 10, (ImmutableList<Behavior<Hoglin>>)ImmutableList.of((Object)new BecomePassiveIfMemoryPresent(MemoryModuleType.r_715_M, 200), (Object)new AnimalMakeLove(t_5_h.e_4240_b, 0.6f), (Object)new SetWalkTargetFromAttackTargetIfTargetOutOfReach(1.0f), new L_1885_c<Z_530_i>(Hoglin::V_1176_p, new MeleeAttack(40)), new L_1885_c<Z_530_i>(AgableMob::d_, new MeleeAttack(15)), new b_3448_R(), new EraseMemoryIf<Hoglin>(c_1065_N::t_148_a, MemoryModuleType.Q_4569_t)), MemoryModuleType.Q_4569_t);
    }

    private static void P_1922_E(E_4668_a<Hoglin> p_234391_0_) {
        p_234391_0_.n_1700_B(Activity.h_1847_R, 10, (ImmutableList<Behavior<Hoglin>>)ImmutableList.of(SetWalkTargetAwayFrom.J_1907_R(MemoryModuleType.Z_875_P, 1.3f, 15, false), c_1065_N.n_1700_B(), new z_1480_R<r_4811_B>(new SetEntityLookTarget(8.0f), J_2548_M.n_1700_B(30, 60)), new EraseMemoryIf<Hoglin>(c_1065_N::P_1922_E, MemoryModuleType.Z_875_P)), MemoryModuleType.Z_875_P);
    }

    private static E_1879_e<Hoglin> n_1700_B() {
        return new E_1879_e<Hoglin>((List<Pair<Behavior<Hoglin>, Integer>>)ImmutableList.of((Object)Pair.of((Object)new RandomStroll(0.4f), (Object)2), (Object)Pair.of((Object)new SetWalkTargetFromLookTarget(0.4f, 3), (Object)2), (Object)Pair.of((Object)new DoNothing(30, 60), (Object)1)));
    }

    protected static void n_1700_B(Hoglin p_234377_0_) {
        E_4668_a<Hoglin> brain = p_234377_0_.y_1945_D();
        Activity activity = brain.G_564_y().orElse(null);
        brain.n_1700_B((List<Activity>)ImmutableList.of((Object)Activity.u_2550_I, (Object)Activity.h_1847_R, (Object)Activity.J_1907_R));
        Activity activity1 = brain.G_564_y().orElse(null);
        if (activity != activity1) {
            c_1065_N.J_1907_R(p_234377_0_).ifPresent(p_234377_0_::n_1700_B);
        }
        p_234377_0_.multiplayerClientSuggestionProvider(brain.n_1700_B(MemoryModuleType.Q_4569_t));
    }

    protected static void n_1700_B(Hoglin p_234378_0_, r_4811_B p_234378_1_) {
        if (!p_234378_0_.d_()) {
            if (p_234378_1_.f_4016_n() == t_5_h.i_1637_u && c_1065_N.u_1723_Y(p_234378_0_)) {
                c_1065_N.P_1922_E(p_234378_0_, p_234378_1_);
                c_1065_N.R_4764_Y(p_234378_0_, p_234378_1_);
            } else {
                c_1065_N.w_1484_f(p_234378_0_, p_234378_1_);
            }
        }
    }

    private static void R_4764_Y(Hoglin p_234387_0_, r_4811_B p_234387_1_) {
        c_1065_N.v_4262_N(p_234387_0_).forEach(p_234381_1_ -> c_1065_N.G_564_y(p_234381_1_, p_234387_1_));
    }

    private static void G_564_y(Hoglin p_234390_0_, r_4811_B p_234390_1_) {
        E_4668_a<Hoglin> brain = p_234390_0_.y_1945_D();
        r_4811_B lvt_2_1_ = a_3236_r.n_1700_B((r_4811_B)p_234390_0_, brain.R_4764_Y(MemoryModuleType.Z_875_P), p_234390_1_);
        lvt_2_1_ = a_3236_r.n_1700_B((r_4811_B)p_234390_0_, brain.R_4764_Y(MemoryModuleType.Q_4569_t), lvt_2_1_);
        c_1065_N.P_1922_E(p_234390_0_, lvt_2_1_);
    }

    private static void P_1922_E(Hoglin p_234393_0_, r_4811_B p_234393_1_) {
        p_234393_0_.y_1945_D().J_1907_R(MemoryModuleType.Q_4569_t);
        p_234393_0_.y_1945_D().J_1907_R(MemoryModuleType.P_4830_p);
        p_234393_0_.y_1945_D().n_1700_B(MemoryModuleType.Z_875_P, p_234393_1_, n_1700_B.n_1700_B(p_234393_0_.O_508_d.w_1457_N));
    }

    private static Optional<? extends r_4811_B> G_564_y(Hoglin p_234392_0_) {
        return !c_1065_N.R_4764_Y(p_234392_0_) && !c_1065_N.t_148_a(p_234392_0_) ? p_234392_0_.y_1945_D().R_4764_Y(MemoryModuleType.M_588_G) : Optional.empty();
    }

    static boolean n_1700_B(Hoglin p_234380_0_, c_1514_x p_234380_1_) {
        Optional<c_1514_x> optional = p_234380_0_.y_1945_D().R_4764_Y(MemoryModuleType.r_715_M);
        return optional.isPresent() && optional.get().withinDistance(p_234380_1_, 8.0);
    }

    private static boolean P_1922_E(Hoglin p_234394_0_) {
        return p_234394_0_.V_1176_p() && !c_1065_N.u_1723_Y(p_234394_0_);
    }

    private static boolean u_1723_Y(Hoglin p_234396_0_) {
        int j;
        if (p_234396_0_.d_()) {
            return false;
        }
        int i = p_234396_0_.y_1945_D().R_4764_Y(MemoryModuleType.s_2632_s).orElse(0);
        return i > (j = p_234396_0_.y_1945_D().R_4764_Y(MemoryModuleType.l_1233_K).orElse(0) + 1);
    }

    protected static void J_1907_R(Hoglin p_234384_0_, r_4811_B p_234384_1_) {
        E_4668_a<Hoglin> brain = p_234384_0_.y_1945_D();
        brain.J_1907_R(MemoryModuleType.A_1038_p);
        brain.J_1907_R(MemoryModuleType.multiplayerClientSuggestionProvider);
        if (p_234384_0_.d_()) {
            c_1065_N.G_564_y(p_234384_0_, p_234384_1_);
        } else {
            c_1065_N.u_1723_Y(p_234384_0_, p_234384_1_);
        }
    }

    private static void u_1723_Y(Hoglin p_234395_0_, r_4811_B p_234395_1_) {
        if (!(p_234395_0_.y_1945_D().R_4764_Y(Activity.h_1847_R) && p_234395_1_.f_4016_n() == t_5_h.i_1637_u || !I_408_V.u_1723_Y.test(p_234395_1_) || p_234395_1_.f_4016_n() == t_5_h.e_4240_b || a_3236_r.n_1700_B((r_4811_B)p_234395_0_, p_234395_1_, 4.0))) {
            c_1065_N.v_4262_N(p_234395_0_, p_234395_1_);
            c_1065_N.w_1484_f(p_234395_0_, p_234395_1_);
        }
    }

    private static void v_4262_N(Hoglin p_234397_0_, r_4811_B p_234397_1_) {
        E_4668_a<Hoglin> brain = p_234397_0_.y_1945_D();
        brain.J_1907_R(MemoryModuleType.Y_1740_V);
        brain.J_1907_R(MemoryModuleType.multiplayerClientSuggestionProvider);
        brain.n_1700_B(MemoryModuleType.Q_4569_t, p_234397_1_, 200L);
    }

    private static void w_1484_f(Hoglin p_234399_0_, r_4811_B p_234399_1_) {
        c_1065_N.v_4262_N(p_234399_0_).forEach(p_234375_1_ -> c_1065_N.t_148_a(p_234375_1_, p_234399_1_));
    }

    private static void t_148_a(Hoglin p_234401_0_, r_4811_B p_234401_1_) {
        if (!c_1065_N.R_4764_Y(p_234401_0_)) {
            Optional<r_4811_B> optional = p_234401_0_.y_1945_D().R_4764_Y(MemoryModuleType.Q_4569_t);
            r_4811_B livingentity = a_3236_r.n_1700_B((r_4811_B)p_234401_0_, optional, p_234401_1_);
            c_1065_N.v_4262_N(p_234401_0_, livingentity);
        }
    }

    public static Optional<SoundEvent> J_1907_R(Hoglin p_234398_0_) {
        return p_234398_0_.y_1945_D().G_564_y().map(p_234379_1_ -> c_1065_N.n_1700_B(p_234398_0_, p_234379_1_));
    }

    private static SoundEvent n_1700_B(Hoglin p_241413_0_, Activity p_241413_1_) {
        if (p_241413_1_ != Activity.h_1847_R && !p_241413_0_.y_2447_C()) {
            if (p_241413_1_ == Activity.u_2550_I) {
                return SoundEvents.R_3213_X;
            }
            return c_1065_N.w_1484_f(p_241413_0_) ? SoundEvents.l_4397_i : SoundEvents.f_887_Z;
        }
        return SoundEvents.l_4397_i;
    }

    private static List<Hoglin> v_4262_N(Hoglin p_234400_0_) {
        return p_234400_0_.y_1945_D().R_4764_Y(MemoryModuleType.g_2268_R).orElse((List<Hoglin>)ImmutableList.of());
    }

    private static boolean w_1484_f(Hoglin p_241416_0_) {
        return p_241416_0_.y_1945_D().n_1700_B(MemoryModuleType.r_715_M);
    }

    private static boolean t_148_a(Hoglin p_234402_0_) {
        return p_234402_0_.y_1945_D().n_1700_B(MemoryModuleType.multiplayerClientSuggestionProvider);
    }

    protected static boolean R_4764_Y(Hoglin p_234386_0_) {
        return p_234386_0_.y_1945_D().n_1700_B(MemoryModuleType.A_1038_p);
    }
}


