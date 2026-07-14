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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import lightning.product.A_2352_Z;
import lightning.product.A_69_b;
import lightning.product.LootContextParams;
import lightning.product.E_1879_e;
import lightning.product.SetWalkTargetFromLookTarget;
import lightning.product.E_4668_a;
import lightning.product.SetLookAndInteract;
import lightning.product.I_408_V;
import lightning.product.J_2548_M;
import lightning.product.SetWalkTargetFromAttackTargetIfTargetOutOfReach;
import lightning.product.GoToWantedItem;
import lightning.product.L_1434_v;
import lightning.product.L_1885_c;
import lightning.product.LookAtTargetSink;
import lightning.product.CrossbowAttack;
import lightning.product.ItemTags;
import lightning.product.N_4263_v;
import lightning.product.Hoglin;
import lightning.product.R_2515_i;
import lightning.product.SetWalkTargetAwayFrom;
import lightning.product.RememberIfHoglinWasKilled;
import lightning.product.SoundEvents;
import lightning.product.W_3371_U;
import lightning.product.SoundEvent;
import lightning.product.EraseMemoryIf;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.SetEntityLookTarget;
import lightning.product.a_3236_r;
import lightning.product.a_3913_L;
import lightning.product.a_4468_e;
import lightning.product.b_3448_R;
import lightning.product.AbstractPiglin;
import lightning.product.c_1514_x;
import lightning.product.StartCelebratingIfTargetDead;
import lightning.product.StopAdmiringIfTiredOfTryingToReachItem;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.MoveToTargetSink;
import lightning.product.TimeUtil;
import lightning.product.f_1402_I;
import lightning.product.Behavior;
import lightning.product.StartAttacking;
import lightning.product.InteractWith;
import lightning.product.Activity;
import lightning.product.StartHuntingHoglin;
import lightning.product.k_3514_p;
import lightning.product.MeleeAttack;
import lightning.product.StopBeingAngryIfTargetDead;
import lightning.product.BackUpIfTooClose;
import lightning.product.StopAdmiringIfItemTooFarAway;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.GoToCelebrateLocation;
import lightning.product.RandomStroll;
import lightning.product.o_4810_o;
import lightning.product.p_4985_U;
import lightning.product.DoNothing;
import lightning.product.q_1613_l;
import lightning.product.q_1704_m;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.StopHoldingItemIfNoLongerAdmiring;
import lightning.product.MemoryModuleType;
import lightning.product.CopyMemoryWithExpiry;
import lightning.product.StartAdmiringItemIfSeen;
import lightning.product.x_1688_C;
import lightning.product.z_1480_R;
import lightning.product.Mount;

public class A_4919_q {
    public static final q_1613_l n_1700_B = Items.ServerHandshakePacketListener;
    private static final J_2548_M J_1907_R = TimeUtil.n_1700_B(30, 120);
    private static final J_2548_M R_4764_Y = TimeUtil.n_1700_B(10, 40);
    private static final J_2548_M G_564_y = TimeUtil.n_1700_B(10, 30);
    private static final J_2548_M P_1922_E = TimeUtil.n_1700_B(5, 20);
    private static final J_2548_M u_1723_Y = TimeUtil.n_1700_B(5, 7);
    private static final J_2548_M v_4262_N = TimeUtil.n_1700_B(5, 7);
    private static final Set<q_1613_l> w_1484_f = ImmutableSet.of((Object)Items.j_1654_T, (Object)Items.l_3729_r);

    protected static E_4668_a<?> n_1700_B(A_69_b p_234469_0_, E_4668_a<A_69_b> p_234469_1_) {
        A_4919_q.n_1700_B(p_234469_1_);
        A_4919_q.J_1907_R(p_234469_1_);
        A_4919_q.G_564_y(p_234469_1_);
        A_4919_q.J_1907_R(p_234469_0_, p_234469_1_);
        A_4919_q.R_4764_Y(p_234469_1_);
        A_4919_q.P_1922_E(p_234469_1_);
        A_4919_q.u_1723_Y(p_234469_1_);
        p_234469_1_.n_1700_B((Set<Activity>)ImmutableSet.of((Object)Activity.n_1700_B));
        p_234469_1_.J_1907_R(Activity.J_1907_R);
        p_234469_1_.R_4764_Y();
        return p_234469_1_;
    }

    protected static void n_1700_B(A_69_b p_234466_0_) {
        int i = J_1907_R.n_1700_B(p_234466_0_.O_508_d.w_1457_N);
        p_234466_0_.y_1945_D().n_1700_B(MemoryModuleType.e_2887_G, true, i);
    }

    private static void n_1700_B(E_4668_a<A_69_b> p_234464_0_) {
        p_234464_0_.n_1700_B(Activity.n_1700_B, 0, (ImmutableList<Behavior<A_69_b>>)ImmutableList.of((Object)new LookAtTargetSink(45, 90), (Object)new MoveToTargetSink(), (Object)new a_4468_e(), A_4919_q.G_564_y(), A_4919_q.P_1922_E(), new StopHoldingItemIfNoLongerAdmiring(), new StartAdmiringItemIfSeen(120), (Object)new StartCelebratingIfTargetDead(300, A_4919_q::n_1700_B), new StopBeingAngryIfTargetDead()));
    }

    private static void J_1907_R(E_4668_a<A_69_b> p_234485_0_) {
        p_234485_0_.n_1700_B(Activity.J_1907_R, 10, (ImmutableList<Behavior<A_69_b>>)ImmutableList.of((Object)new SetEntityLookTarget(A_4919_q::J_1907_R, 14.0f), new StartAttacking<A_69_b>(AbstractPiglin::y_2447_C, A_4919_q::u_2550_I), new L_1885_c<A_69_b>(A_69_b::u_1723_Y, new StartHuntingHoglin()), A_4919_q.R_4764_Y(), A_4919_q.u_1723_Y(), A_4919_q.n_1700_B(), A_4919_q.J_1907_R(), (Object)new SetLookAndInteract(t_5_h.g_4106_L, 4)));
    }

    private static void J_1907_R(A_69_b p_234488_0_, E_4668_a<A_69_b> p_234488_1_) {
        p_234488_1_.n_1700_B(Activity.u_2550_I, 10, (ImmutableList<Behavior<A_69_b>>)ImmutableList.of(new b_3448_R(p_234523_1_ -> !A_4919_q.J_1907_R(p_234488_0_, p_234523_1_)), new L_1885_c<A_69_b>(A_4919_q::R_4764_Y, new BackUpIfTooClose(5, 0.75f)), (Object)new SetWalkTargetFromAttackTargetIfTargetOutOfReach(1.0f), (Object)new MeleeAttack(20), new CrossbowAttack(), new RememberIfHoglinWasKilled(), new EraseMemoryIf<A_69_b>(A_4919_q::s_956_w, MemoryModuleType.Q_4569_t)), MemoryModuleType.Q_4569_t);
    }

    private static void R_4764_Y(E_4668_a<A_69_b> p_234495_0_) {
        p_234495_0_.n_1700_B(Activity.M_588_G, 10, (ImmutableList<Behavior<A_69_b>>)ImmutableList.of(A_4919_q.R_4764_Y(), (Object)new SetEntityLookTarget(A_4919_q::J_1907_R, 14.0f), new StartAttacking<A_69_b>(AbstractPiglin::y_2447_C, A_4919_q::u_2550_I), new L_1885_c<A_69_b>(p_234457_0_ -> !p_234457_0_.P_2295_B(), new GoToCelebrateLocation(2, 1.0f)), new L_1885_c<A_69_b>(A_69_b::P_2295_B, new GoToCelebrateLocation(4, 0.6f)), new E_1879_e(ImmutableList.of((Object)Pair.of((Object)new SetEntityLookTarget(t_5_h.i_1637_u, 8.0f), (Object)1), (Object)Pair.of((Object)new RandomStroll(0.6f, 2, 1), (Object)1), (Object)Pair.of((Object)new DoNothing(10, 20), (Object)1)))), MemoryModuleType.B_1668_F);
    }

    private static void G_564_y(E_4668_a<A_69_b> p_234502_0_) {
        p_234502_0_.n_1700_B(Activity.P_4830_p, 10, (ImmutableList<Behavior<A_69_b>>)ImmutableList.of(new GoToWantedItem<A_69_b>(A_4919_q::q_2307_F, 1.0f, true, 9), new StopAdmiringIfItemTooFarAway(9), new StopAdmiringIfTiredOfTryingToReachItem(200, 200)), MemoryModuleType.T_2506_i);
    }

    private static void P_1922_E(E_4668_a<A_69_b> p_234507_0_) {
        p_234507_0_.n_1700_B(Activity.h_1847_R, 10, (ImmutableList<Behavior<A_69_b>>)ImmutableList.of(SetWalkTargetAwayFrom.J_1907_R(MemoryModuleType.Z_875_P, 1.0f, 12, true), A_4919_q.n_1700_B(), A_4919_q.J_1907_R(), new EraseMemoryIf<A_69_b>(A_4919_q::Q_4569_t, MemoryModuleType.Z_875_P)), MemoryModuleType.Z_875_P);
    }

    private static void u_1723_Y(E_4668_a<A_69_b> p_234511_0_) {
        p_234511_0_.n_1700_B(Activity.Q_4569_t, 10, (ImmutableList<Behavior<A_69_b>>)ImmutableList.of(new Mount(0.8f), (Object)new SetEntityLookTarget(A_4919_q::J_1907_R, 8.0f), new L_1885_c<A_69_b>(N_4263_v::y_2772_m, A_4919_q.n_1700_B()), new k_3514_p(8, A_4919_q::n_1700_B)), MemoryModuleType.w_1457_N);
    }

    private static E_1879_e<A_69_b> n_1700_B() {
        return new E_1879_e<A_69_b>((List<Pair<Behavior<A_69_b>, Integer>>)ImmutableList.of((Object)Pair.of((Object)new SetEntityLookTarget(t_5_h.g_4106_L, 8.0f), (Object)1), (Object)Pair.of((Object)new SetEntityLookTarget(t_5_h.i_1637_u, 8.0f), (Object)1), (Object)Pair.of((Object)new SetEntityLookTarget(8.0f), (Object)1), (Object)Pair.of((Object)new DoNothing(30, 60), (Object)1)));
    }

    private static E_1879_e<A_69_b> J_1907_R() {
        return new E_1879_e<A_69_b>((List<Pair<Behavior<A_69_b>, Integer>>)ImmutableList.of((Object)Pair.of((Object)new RandomStroll(0.6f), (Object)2), (Object)Pair.of(InteractWith.n_1700_B(t_5_h.i_1637_u, 8, MemoryModuleType.t_1786_h, 0.6f, 2), (Object)2), (Object)Pair.of(new L_1885_c<r_4811_B>(A_4919_q::v_4262_N, new SetWalkTargetFromLookTarget(0.6f, 3)), (Object)2), (Object)Pair.of((Object)new DoNothing(30, 60), (Object)1)));
    }

    private static SetWalkTargetAwayFrom<c_1514_x> R_4764_Y() {
        return SetWalkTargetAwayFrom.n_1700_B(MemoryModuleType.r_715_M, 1.0f, 8, false);
    }

    private static CopyMemoryWithExpiry<A_69_b, r_4811_B> G_564_y() {
        return new CopyMemoryWithExpiry<A_69_b, r_4811_B>(A_69_b::d_, MemoryModuleType.v_4276_D, MemoryModuleType.Z_875_P, v_4262_N);
    }

    private static CopyMemoryWithExpiry<A_69_b, r_4811_B> P_1922_E() {
        return new CopyMemoryWithExpiry<A_69_b, r_4811_B>(A_4919_q::s_956_w, MemoryModuleType.D_4792_h, MemoryModuleType.Z_875_P, u_1723_Y);
    }

    protected static void J_1907_R(A_69_b p_234486_0_) {
        E_4668_a<A_69_b> brain = p_234486_0_.y_1945_D();
        Activity activity = brain.G_564_y().orElse(null);
        brain.n_1700_B((List<Activity>)ImmutableList.of((Object)Activity.P_4830_p, (Object)Activity.u_2550_I, (Object)Activity.h_1847_R, (Object)Activity.M_588_G, (Object)Activity.Q_4569_t, (Object)Activity.J_1907_R));
        Activity activity1 = brain.G_564_y().orElse(null);
        if (activity != activity1) {
            A_4919_q.G_564_y(p_234486_0_).ifPresent(p_234486_0_::n_1700_B);
        }
        p_234486_0_.multiplayerClientSuggestionProvider(brain.n_1700_B(MemoryModuleType.Q_4569_t));
        if (!brain.n_1700_B(MemoryModuleType.w_1457_N) && A_4919_q.w_1484_f(p_234486_0_)) {
            p_234486_0_.A_3959_N();
        }
        if (!brain.n_1700_B(MemoryModuleType.B_1668_F)) {
            brain.J_1907_R(MemoryModuleType.g_164_R);
        }
        p_234486_0_.Y_601_j(brain.n_1700_B(MemoryModuleType.g_164_R));
    }

    private static boolean w_1484_f(A_69_b p_234522_0_) {
        if (!p_234522_0_.d_()) {
            return false;
        }
        N_4263_v entity = p_234522_0_.l_3609_d();
        return entity instanceof A_69_b && ((A_69_b)entity).d_() || entity instanceof Hoglin && ((Hoglin)entity).d_();
    }

    protected static void n_1700_B(A_69_b p_234470_0_, n_1494_c p_234470_1_) {
        Z_1993_T itemstack;
        A_4919_q.h_1847_R(p_234470_0_);
        if (p_234470_1_.P_1922_E().J_1907_R() == Items.u_3578_p) {
            p_234470_0_.n_1700_B((N_4263_v)p_234470_1_, p_234470_1_.P_1922_E().t_4043_B());
            itemstack = p_234470_1_.P_1922_E();
            p_234470_1_.Ops();
        } else {
            p_234470_0_.n_1700_B((N_4263_v)p_234470_1_, 1);
            itemstack = A_4919_q.n_1700_B(p_234470_1_);
        }
        q_1613_l item = itemstack.J_1907_R();
        if (A_4919_q.n_1700_B(item)) {
            p_234470_0_.y_1945_D().J_1907_R(MemoryModuleType.q_4610_l);
            A_4919_q.R_4764_Y(p_234470_0_, itemstack);
            A_4919_q.G_564_y((r_4811_B)p_234470_0_);
        } else if (A_4919_q.R_4764_Y(item) && !A_4919_q.Y_601_j(p_234470_0_)) {
            A_4919_q.multiplayerClientSuggestionProvider(p_234470_0_);
        } else {
            boolean flag = p_234470_0_.v_4262_N(itemstack);
            if (!flag) {
                A_4919_q.G_564_y(p_234470_0_, itemstack);
            }
        }
    }

    private static void R_4764_Y(A_69_b p_241427_0_, Z_1993_T p_241427_1_) {
        if (A_4919_q.k_2293_S(p_241427_0_)) {
            p_241427_0_.a_(p_241427_0_.R_4764_Y(x_1688_C.J_1907_R));
        }
        p_241427_0_.h_1847_R(p_241427_1_);
    }

    private static Z_1993_T n_1700_B(n_1494_c p_234465_0_) {
        Z_1993_T itemstack = p_234465_0_.P_1922_E();
        Z_1993_T itemstack1 = itemstack.n_1700_B(1);
        if (itemstack.n_1700_B()) {
            p_234465_0_.Ops();
        } else {
            p_234465_0_.J_1907_R(itemstack);
        }
        return itemstack1;
    }

    protected static void n_1700_B(A_69_b p_234477_0_, boolean p_234477_1_) {
        Z_1993_T itemstack = p_234477_0_.R_4764_Y(x_1688_C.J_1907_R);
        p_234477_0_.n_1700_B(x_1688_C.J_1907_R, Z_1993_T.J_1907_R);
        if (p_234477_0_.y_2447_C()) {
            boolean flag1;
            boolean flag = A_4919_q.J_1907_R(itemstack.J_1907_R());
            if (p_234477_1_ && flag) {
                A_4919_q.n_1700_B(p_234477_0_, A_4919_q.t_148_a(p_234477_0_));
            } else if (!flag && !(flag1 = p_234477_0_.v_4262_N(itemstack))) {
                A_4919_q.G_564_y(p_234477_0_, itemstack);
            }
        } else {
            boolean flag2 = p_234477_0_.v_4262_N(itemstack);
            if (!flag2) {
                Z_1993_T itemstack1 = p_234477_0_.A_2714_y();
                if (A_4919_q.n_1700_B(itemstack1.J_1907_R())) {
                    A_4919_q.G_564_y(p_234477_0_, itemstack1);
                } else {
                    A_4919_q.n_1700_B(p_234477_0_, Collections.singletonList(itemstack1));
                }
                p_234477_0_.P_4830_p(itemstack);
            }
        }
    }

    protected static void R_4764_Y(A_69_b p_234496_0_) {
        if (A_4919_q.Y_259_p(p_234496_0_) && !p_234496_0_.S_4035_N().n_1700_B()) {
            p_234496_0_.a_(p_234496_0_.S_4035_N());
            p_234496_0_.n_1700_B(x_1688_C.J_1907_R, Z_1993_T.J_1907_R);
        }
    }

    private static void G_564_y(A_69_b p_234498_0_, Z_1993_T p_234498_1_) {
        Z_1993_T itemstack = p_234498_0_.u_2550_I(p_234498_1_);
        A_4919_q.J_1907_R(p_234498_0_, Collections.singletonList(itemstack));
    }

    private static void n_1700_B(A_69_b p_234475_0_, List<Z_1993_T> p_234475_1_) {
        Optional<a_3913_L> optional = p_234475_0_.y_1945_D().R_4764_Y(MemoryModuleType.u_2550_I);
        if (optional.isPresent()) {
            A_4919_q.n_1700_B(p_234475_0_, optional.get(), p_234475_1_);
        } else {
            A_4919_q.J_1907_R(p_234475_0_, p_234475_1_);
        }
    }

    private static void J_1907_R(A_69_b p_234490_0_, List<Z_1993_T> p_234490_1_) {
        A_4919_q.n_1700_B(p_234490_0_, p_234490_1_, A_4919_q.w_1457_N(p_234490_0_));
    }

    private static void n_1700_B(A_69_b p_234472_0_, a_3913_L p_234472_1_, List<Z_1993_T> p_234472_2_) {
        A_4919_q.n_1700_B(p_234472_0_, p_234472_2_, p_234472_1_.s_4990_V());
    }

    private static void n_1700_B(A_69_b p_234476_0_, List<Z_1993_T> p_234476_1_, e_2866_D p_234476_2_) {
        if (!p_234476_1_.isEmpty()) {
            p_234476_0_.n_1700_B(x_1688_C.J_1907_R);
            for (Z_1993_T itemstack : p_234476_1_) {
                a_3236_r.n_1700_B((r_4811_B)p_234476_0_, itemstack, p_234476_2_.J_1907_R(0.0, 1.0, 0.0));
            }
        }
    }

    private static List<Z_1993_T> t_148_a(A_69_b p_234524_0_) {
        p_4985_U loottable = p_234524_0_.O_508_d.T_2506_i().F_2624_D().n_1700_B(o_4810_o.U_1241_n);
        return loottable.n_1700_B(new q_1704_m.n_1700_B((e_3591_l)p_234524_0_.O_508_d).n_1700_B(LootContextParams.n_1700_B, p_234524_0_).n_1700_B(p_234524_0_.O_508_d.w_1457_N).n_1700_B(f_1402_I.w_1484_f));
    }

    private static boolean n_1700_B(r_4811_B p_234461_0_, r_4811_B p_234461_1_) {
        if (p_234461_1_.f_4016_n() != t_5_h.e_4240_b) {
            return false;
        }
        return new Random(p_234461_0_.O_508_d.X_933_l()).nextFloat() < 0.1f;
    }

    protected static boolean n_1700_B(A_69_b p_234474_0_, Z_1993_T p_234474_1_) {
        q_1613_l item = p_234474_1_.J_1907_R();
        if (item.n_1700_B(ItemTags.T_2506_i)) {
            return false;
        }
        if (A_4919_q.C_2741_M(p_234474_0_) && p_234474_0_.y_1945_D().n_1700_B(MemoryModuleType.Q_4569_t)) {
            return false;
        }
        if (A_4919_q.J_1907_R(item)) {
            return A_4919_q.q_2307_F(p_234474_0_);
        }
        boolean flag = p_234474_0_.M_588_G(p_234474_1_);
        if (item == Items.u_3578_p) {
            return flag;
        }
        if (A_4919_q.R_4764_Y(item)) {
            return !A_4919_q.Y_601_j(p_234474_0_) && flag;
        }
        if (!A_4919_q.n_1700_B(item)) {
            return p_234474_0_.Q_4569_t(p_234474_1_);
        }
        return A_4919_q.q_2307_F(p_234474_0_) && flag;
    }

    protected static boolean n_1700_B(q_1613_l p_234480_0_) {
        return p_234480_0_.n_1700_B(ItemTags.q_4610_l);
    }

    private static boolean n_1700_B(A_69_b p_234467_0_, N_4263_v p_234467_1_) {
        if (!(p_234467_1_ instanceof Z_530_i)) {
            return false;
        }
        Z_530_i mobentity = (Z_530_i)p_234467_1_;
        return !mobentity.d_() || !mobentity.RealmsLongRunningMcoTaskScreen() || A_4919_q.w_1484_f((r_4811_B)p_234467_0_) || A_4919_q.w_1484_f(mobentity) || mobentity instanceof A_69_b && mobentity.l_3609_d() == null;
    }

    private static boolean J_1907_R(A_69_b p_234504_0_, r_4811_B p_234504_1_) {
        return A_4919_q.u_2550_I(p_234504_0_).filter(p_234483_1_ -> p_234483_1_ == p_234504_1_).isPresent();
    }

    private static boolean s_956_w(A_69_b p_234525_0_) {
        E_4668_a<A_69_b> brain = p_234525_0_.y_1945_D();
        if (brain.n_1700_B(MemoryModuleType.D_4792_h)) {
            r_4811_B livingentity = brain.R_4764_Y(MemoryModuleType.D_4792_h).get();
            return p_234525_0_.n_1700_B((N_4263_v)livingentity, 6.0);
        }
        return false;
    }

    private static Optional<? extends r_4811_B> u_2550_I(A_69_b p_234526_0_) {
        Optional<a_3913_L> optional1;
        E_4668_a<A_69_b> brain = p_234526_0_.y_1945_D();
        if (A_4919_q.s_956_w(p_234526_0_)) {
            return Optional.empty();
        }
        Optional<r_4811_B> optional = a_3236_r.n_1700_B((r_4811_B)p_234526_0_, MemoryModuleType.d_2461_k);
        if (optional.isPresent() && A_4919_q.P_1922_E(optional.get())) {
            return optional;
        }
        if (brain.n_1700_B(MemoryModuleType.G_624_v) && (optional1 = brain.R_4764_Y(MemoryModuleType.M_588_G)).isPresent()) {
            return optional1;
        }
        Optional<Z_530_i> optional3 = brain.R_4764_Y(MemoryModuleType.v_4276_D);
        if (optional3.isPresent()) {
            return optional3;
        }
        Optional<a_3913_L> optional2 = brain.R_4764_Y(MemoryModuleType.H_1990_U);
        return optional2.isPresent() && A_4919_q.P_1922_E(optional2.get()) ? optional2 : Optional.empty();
    }

    public static void n_1700_B(a_3913_L p_234478_0_, boolean p_234478_1_) {
        List<A_69_b> list = p_234478_0_.O_508_d.n_1700_B(A_69_b.class, p_234478_0_.i_601_W().grow(16.0));
        list.stream().filter(A_4919_q::G_564_y).filter(p_234491_2_ -> !p_234478_1_ || a_3236_r.R_4764_Y(p_234491_2_, p_234478_0_)).forEach(p_234479_1_ -> {
            if (p_234479_1_.O_508_d.H_1990_U().J_1907_R(A_2352_Z.e_4240_b)) {
                A_4919_q.G_564_y((AbstractPiglin)p_234479_1_, (r_4811_B)p_234478_0_);
            } else {
                A_4919_q.R_4764_Y((AbstractPiglin)p_234479_1_, (r_4811_B)p_234478_0_);
            }
        });
    }

    public static m_3054_I n_1700_B(A_69_b p_234471_0_, a_3913_L p_234471_1_, x_1688_C p_234471_2_) {
        Z_1993_T itemstack = p_234471_1_.R_4764_Y(p_234471_2_);
        if (A_4919_q.J_1907_R(p_234471_0_, itemstack)) {
            Z_1993_T itemstack1 = itemstack.n_1700_B(1);
            A_4919_q.R_4764_Y(p_234471_0_, itemstack1);
            A_4919_q.G_564_y((r_4811_B)p_234471_0_);
            A_4919_q.h_1847_R(p_234471_0_);
            return m_3054_I.J_1907_R;
        }
        return m_3054_I.R_4764_Y;
    }

    protected static boolean J_1907_R(A_69_b p_234489_0_, Z_1993_T p_234489_1_) {
        return !A_4919_q.C_2741_M(p_234489_0_) && !A_4919_q.Y_259_p(p_234489_0_) && p_234489_0_.y_2447_C() && A_4919_q.J_1907_R(p_234489_1_.J_1907_R());
    }

    protected static void n_1700_B(A_69_b p_234468_0_, r_4811_B p_234468_1_) {
        if (!(p_234468_1_ instanceof A_69_b)) {
            if (A_4919_q.k_2293_S(p_234468_0_)) {
                A_4919_q.n_1700_B(p_234468_0_, false);
            }
            E_4668_a<A_69_b> brain = p_234468_0_.y_1945_D();
            brain.J_1907_R(MemoryModuleType.B_1668_F);
            brain.J_1907_R(MemoryModuleType.g_164_R);
            brain.J_1907_R(MemoryModuleType.T_2506_i);
            if (p_234468_1_ instanceof a_3913_L) {
                brain.n_1700_B(MemoryModuleType.g_221_o, true, 400L);
            }
            A_4919_q.v_4262_N(p_234468_0_).ifPresent(p_234462_2_ -> {
                if (p_234462_2_.f_4016_n() != p_234468_1_.f_4016_n()) {
                    brain.J_1907_R(MemoryModuleType.Z_875_P);
                }
            });
            if (p_234468_0_.d_()) {
                brain.n_1700_B(MemoryModuleType.Z_875_P, p_234468_1_, 100L);
                if (A_4919_q.P_1922_E(p_234468_1_)) {
                    A_4919_q.J_1907_R((AbstractPiglin)p_234468_0_, p_234468_1_);
                }
            } else if (p_234468_1_.f_4016_n() == t_5_h.e_4240_b && A_4919_q.t_1786_h(p_234468_0_)) {
                A_4919_q.P_1922_E(p_234468_0_, p_234468_1_);
                A_4919_q.R_4764_Y(p_234468_0_, p_234468_1_);
            } else {
                A_4919_q.n_1700_B((AbstractPiglin)p_234468_0_, p_234468_1_);
            }
        }
    }

    protected static void n_1700_B(AbstractPiglin p_234509_0_, r_4811_B p_234509_1_) {
        if (!p_234509_0_.y_1945_D().R_4764_Y(Activity.h_1847_R) && A_4919_q.P_1922_E(p_234509_1_) && !a_3236_r.n_1700_B((r_4811_B)p_234509_0_, p_234509_1_, 4.0)) {
            if (p_234509_1_.f_4016_n() == t_5_h.g_4106_L && p_234509_0_.O_508_d.H_1990_U().J_1907_R(A_2352_Z.e_4240_b)) {
                A_4919_q.G_564_y(p_234509_0_, p_234509_1_);
                A_4919_q.n_1700_B(p_234509_0_);
            } else {
                A_4919_q.R_4764_Y(p_234509_0_, p_234509_1_);
                A_4919_q.J_1907_R(p_234509_0_, p_234509_1_);
            }
        }
    }

    public static Optional<SoundEvent> G_564_y(A_69_b p_241429_0_) {
        return p_241429_0_.y_1945_D().G_564_y().map(p_241426_1_ -> A_4919_q.n_1700_B(p_241429_0_, p_241426_1_));
    }

    private static SoundEvent n_1700_B(A_69_b p_241422_0_, Activity p_241422_1_) {
        if (p_241422_1_ == Activity.u_2550_I) {
            return SoundEvents.Easing;
        }
        if (p_241422_0_.V_1176_p()) {
            return SoundEvents.r_2090_h;
        }
        if (p_241422_1_ == Activity.h_1847_R && A_4919_q.M_588_G(p_241422_0_)) {
            return SoundEvents.r_2090_h;
        }
        if (p_241422_1_ == Activity.P_4830_p) {
            return SoundEvents.Animation;
        }
        if (p_241422_1_ == Activity.M_588_G) {
            return SoundEvents.G_4691_Q;
        }
        if (A_4919_q.u_1723_Y((r_4811_B)p_241422_0_)) {
            return SoundEvents.m_3828_C;
        }
        return A_4919_q.Q_2552_b(p_241422_0_) ? SoundEvents.r_2090_h : SoundEvents.H_274_C;
    }

    private static boolean M_588_G(A_69_b p_234528_0_) {
        E_4668_a<A_69_b> brain = p_234528_0_.y_1945_D();
        return !brain.n_1700_B(MemoryModuleType.Z_875_P) ? false : brain.R_4764_Y(MemoryModuleType.Z_875_P).get().n_1700_B((N_4263_v)p_234528_0_, 12.0);
    }

    protected static boolean P_1922_E(A_69_b p_234508_0_) {
        return p_234508_0_.y_1945_D().n_1700_B(MemoryModuleType.e_2887_G) || A_4919_q.P_4830_p(p_234508_0_).stream().anyMatch(p_234456_0_ -> p_234456_0_.y_1945_D().n_1700_B(MemoryModuleType.e_2887_G));
    }

    private static List<AbstractPiglin> P_4830_p(A_69_b p_234529_0_) {
        return p_234529_0_.y_1945_D().R_4764_Y(MemoryModuleType.c_4037_x).orElse((List<AbstractPiglin>)ImmutableList.of());
    }

    private static List<AbstractPiglin> P_1922_E(AbstractPiglin p_234530_0_) {
        return p_234530_0_.y_1945_D().R_4764_Y(MemoryModuleType.N_2525_X).orElse((List<AbstractPiglin>)ImmutableList.of());
    }

    public static boolean n_1700_B(r_4811_B p_234460_0_) {
        for (Z_1993_T itemstack : p_234460_0_.u_55_V()) {
            q_1613_l item = itemstack.J_1907_R();
            if (!(item instanceof R_2515_i) || ((R_2515_i)item).P_1922_E() != L_1434_v.G_564_y) continue;
            return true;
        }
        return false;
    }

    private static void h_1847_R(A_69_b p_234531_0_) {
        p_234531_0_.y_1945_D().J_1907_R(MemoryModuleType.P_4830_p);
        p_234531_0_.e_4240_b().h_1847_R();
    }

    private static z_1480_R<A_69_b> u_1723_Y() {
        return new z_1480_R<A_69_b>(new CopyMemoryWithExpiry<A_69_b, N_4263_v>(A_69_b::d_, MemoryModuleType.Z_976_R, MemoryModuleType.w_1457_N, G_564_y), R_4764_Y);
    }

    protected static void J_1907_R(AbstractPiglin p_234487_0_, r_4811_B p_234487_1_) {
        A_4919_q.P_1922_E(p_234487_0_).forEach(p_234484_1_ -> {
            if (p_234487_1_.f_4016_n() != t_5_h.e_4240_b || p_234484_1_.u_1723_Y() && ((Hoglin)p_234487_1_).J_3635_s()) {
                A_4919_q.P_1922_E(p_234484_1_, p_234487_1_);
            }
        });
    }

    protected static void n_1700_B(AbstractPiglin p_241430_0_) {
        A_4919_q.P_1922_E(p_241430_0_).forEach(p_241419_0_ -> A_4919_q.J_1907_R(p_241419_0_).ifPresent(p_241421_1_ -> A_4919_q.R_4764_Y(p_241419_0_, (r_4811_B)p_241421_1_)));
    }

    protected static void u_1723_Y(A_69_b p_234512_0_) {
        A_4919_q.P_4830_p(p_234512_0_).forEach(A_4919_q::R_4764_Y);
    }

    protected static void R_4764_Y(AbstractPiglin p_234497_0_, r_4811_B p_234497_1_) {
        if (A_4919_q.P_1922_E(p_234497_1_)) {
            p_234497_0_.y_1945_D().J_1907_R(MemoryModuleType.Y_1740_V);
            p_234497_0_.y_1945_D().n_1700_B(MemoryModuleType.d_2461_k, p_234497_1_.w_2705_t(), 600L);
            if (p_234497_1_.f_4016_n() == t_5_h.e_4240_b && p_234497_0_.u_1723_Y()) {
                A_4919_q.R_4764_Y(p_234497_0_);
            }
            if (p_234497_1_.f_4016_n() == t_5_h.g_4106_L && p_234497_0_.O_508_d.H_1990_U().J_1907_R(A_2352_Z.e_4240_b)) {
                p_234497_0_.y_1945_D().n_1700_B(MemoryModuleType.G_624_v, true, 600L);
            }
        }
    }

    private static void G_564_y(AbstractPiglin p_241431_0_, r_4811_B p_241431_1_) {
        Optional<a_3913_L> optional = A_4919_q.J_1907_R(p_241431_0_);
        if (optional.isPresent()) {
            A_4919_q.R_4764_Y(p_241431_0_, (r_4811_B)optional.get());
        } else {
            A_4919_q.R_4764_Y(p_241431_0_, p_241431_1_);
        }
    }

    private static void P_1922_E(AbstractPiglin p_234513_0_, r_4811_B p_234513_1_) {
        Optional<r_4811_B> optional = A_4919_q.u_1723_Y(p_234513_0_);
        r_4811_B livingentity = a_3236_r.n_1700_B((r_4811_B)p_234513_0_, optional, p_234513_1_);
        if (!optional.isPresent() || optional.get() != livingentity) {
            A_4919_q.R_4764_Y(p_234513_0_, livingentity);
        }
    }

    private static Optional<r_4811_B> u_1723_Y(AbstractPiglin p_234532_0_) {
        return a_3236_r.n_1700_B((r_4811_B)p_234532_0_, MemoryModuleType.d_2461_k);
    }

    public static Optional<r_4811_B> v_4262_N(A_69_b p_234515_0_) {
        return p_234515_0_.y_1945_D().n_1700_B(MemoryModuleType.Z_875_P) ? p_234515_0_.y_1945_D().R_4764_Y(MemoryModuleType.Z_875_P) : Optional.empty();
    }

    public static Optional<a_3913_L> J_1907_R(AbstractPiglin p_241432_0_) {
        return p_241432_0_.y_1945_D().n_1700_B(MemoryModuleType.M_588_G) ? p_241432_0_.y_1945_D().R_4764_Y(MemoryModuleType.M_588_G) : Optional.empty();
    }

    private static void R_4764_Y(A_69_b p_234516_0_, r_4811_B p_234516_1_) {
        A_4919_q.P_4830_p(p_234516_0_).stream().filter(p_242341_0_ -> p_242341_0_ instanceof A_69_b).forEach(p_234463_1_ -> A_4919_q.G_564_y((A_69_b)p_234463_1_, p_234516_1_));
    }

    private static void G_564_y(A_69_b p_234519_0_, r_4811_B p_234519_1_) {
        E_4668_a<A_69_b> brain = p_234519_0_.y_1945_D();
        r_4811_B lvt_3_1_ = a_3236_r.n_1700_B((r_4811_B)p_234519_0_, brain.R_4764_Y(MemoryModuleType.Z_875_P), p_234519_1_);
        lvt_3_1_ = a_3236_r.n_1700_B((r_4811_B)p_234519_0_, brain.R_4764_Y(MemoryModuleType.Q_4569_t), lvt_3_1_);
        A_4919_q.P_1922_E(p_234519_0_, lvt_3_1_);
    }

    private static boolean Q_4569_t(A_69_b p_234533_0_) {
        E_4668_a<A_69_b> brain = p_234533_0_.y_1945_D();
        if (!brain.n_1700_B(MemoryModuleType.Z_875_P)) {
            return true;
        }
        r_4811_B livingentity = brain.R_4764_Y(MemoryModuleType.Z_875_P).get();
        t_5_h<?> entitytype = livingentity.f_4016_n();
        if (entitytype == t_5_h.e_4240_b) {
            return A_4919_q.M_182_A(p_234533_0_);
        }
        if (A_4919_q.n_1700_B(entitytype)) {
            return !brain.J_1907_R(MemoryModuleType.D_4792_h, livingentity);
        }
        return false;
    }

    private static boolean M_182_A(A_69_b p_234534_0_) {
        return !A_4919_q.t_1786_h(p_234534_0_);
    }

    private static boolean t_1786_h(A_69_b p_234535_0_) {
        int i = p_234535_0_.y_1945_D().R_4764_Y(MemoryModuleType.s_2632_s).orElse(0) + 1;
        int j = p_234535_0_.y_1945_D().R_4764_Y(MemoryModuleType.l_1233_K).orElse(0);
        return j > i;
    }

    private static void P_1922_E(A_69_b p_234521_0_, r_4811_B p_234521_1_) {
        p_234521_0_.y_1945_D().J_1907_R(MemoryModuleType.d_2461_k);
        p_234521_0_.y_1945_D().J_1907_R(MemoryModuleType.Q_4569_t);
        p_234521_0_.y_1945_D().J_1907_R(MemoryModuleType.P_4830_p);
        p_234521_0_.y_1945_D().n_1700_B(MemoryModuleType.Z_875_P, p_234521_1_, P_1922_E.n_1700_B(p_234521_0_.O_508_d.w_1457_N));
        A_4919_q.R_4764_Y((AbstractPiglin)p_234521_0_);
    }

    protected static void R_4764_Y(AbstractPiglin p_234518_0_) {
        p_234518_0_.y_1945_D().n_1700_B(MemoryModuleType.e_2887_G, true, J_1907_R.n_1700_B(p_234518_0_.O_508_d.w_1457_N));
    }

    private static void multiplayerClientSuggestionProvider(A_69_b p_234536_0_) {
        p_234536_0_.y_1945_D().n_1700_B(MemoryModuleType.O_508_d, true, 200L);
    }

    private static e_2866_D w_1457_N(A_69_b p_234537_0_) {
        e_2866_D vector3d = W_3371_U.J_1907_R(p_234537_0_, 4, 2);
        return vector3d == null ? p_234537_0_.s_4990_V() : vector3d;
    }

    private static boolean Y_601_j(A_69_b p_234538_0_) {
        return p_234538_0_.y_1945_D().n_1700_B(MemoryModuleType.O_508_d);
    }

    protected static boolean G_564_y(AbstractPiglin p_234520_0_) {
        return p_234520_0_.y_1945_D().R_4764_Y(Activity.J_1907_R);
    }

    private static boolean R_4764_Y(r_4811_B p_234494_0_) {
        return p_234494_0_.n_1700_B(Items.V_2454_J);
    }

    private static void G_564_y(r_4811_B p_234501_0_) {
        p_234501_0_.y_1945_D().n_1700_B(MemoryModuleType.T_2506_i, true, 120L);
    }

    private static boolean Y_259_p(A_69_b p_234451_0_) {
        return p_234451_0_.y_1945_D().n_1700_B(MemoryModuleType.T_2506_i);
    }

    private static boolean J_1907_R(q_1613_l p_234492_0_) {
        return p_234492_0_ == n_1700_B;
    }

    private static boolean R_4764_Y(q_1613_l p_234499_0_) {
        return w_1484_f.contains(p_234499_0_);
    }

    private static boolean P_1922_E(r_4811_B p_234506_0_) {
        return I_408_V.u_1723_Y.test(p_234506_0_);
    }

    private static boolean Q_2552_b(A_69_b p_234452_0_) {
        return p_234452_0_.y_1945_D().n_1700_B(MemoryModuleType.r_715_M);
    }

    private static boolean u_1723_Y(r_4811_B p_234510_0_) {
        return p_234510_0_.y_1945_D().n_1700_B(MemoryModuleType.z_1333_t);
    }

    private static boolean v_4262_N(r_4811_B p_234514_0_) {
        return !A_4919_q.u_1723_Y(p_234514_0_);
    }

    public static boolean J_1907_R(r_4811_B p_234482_0_) {
        return p_234482_0_.f_4016_n() == t_5_h.g_4106_L && p_234482_0_.n_1700_B(A_4919_q::n_1700_B);
    }

    private static boolean C_2741_M(A_69_b p_234453_0_) {
        return p_234453_0_.y_1945_D().n_1700_B(MemoryModuleType.g_221_o);
    }

    private static boolean w_1484_f(r_4811_B p_234517_0_) {
        return p_234517_0_.y_1945_D().n_1700_B(MemoryModuleType.k_2293_S);
    }

    private static boolean k_2293_S(A_69_b p_234454_0_) {
        return !p_234454_0_.S_4035_N().n_1700_B();
    }

    private static boolean q_2307_F(A_69_b p_234455_0_) {
        return p_234455_0_.S_4035_N().n_1700_B() || !A_4919_q.n_1700_B(p_234455_0_.S_4035_N().J_1907_R());
    }

    public static boolean n_1700_B(t_5_h p_234459_0_) {
        return p_234459_0_ == t_5_h.c_132_F || p_234459_0_ == t_5_h.S_980_j;
    }
}


