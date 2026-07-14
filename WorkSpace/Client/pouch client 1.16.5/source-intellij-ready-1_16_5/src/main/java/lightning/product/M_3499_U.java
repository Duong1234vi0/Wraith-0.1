/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lightning.product.LocateHidingPlace;
import lightning.product.GoToPotentialJobSite;
import lightning.product.AgableMob;
import lightning.product.ShowTradesToPlayer;
import lightning.product.StrollToPoiList;
import lightning.product.SetRaidStatus;
import lightning.product.E_1879_e;
import lightning.product.SetWalkTargetFromLookTarget;
import lightning.product.SetLookAndInteract;
import lightning.product.GoOutsideToCelebrate;
import lightning.product.I_2956_L;
import lightning.product.GoToWantedItem;
import lightning.product.L_2225_p;
import lightning.product.L_2946_U;
import lightning.product.YieldJobSite;
import lightning.product.GoToClosestVillage;
import lightning.product.LookAtTargetSink;
import lightning.product.CelebrateVillagersSurvivedRaid;
import lightning.product.SetClosestHomeAsWalkTarget;
import lightning.product.AssignProfessionFromJobSite;
import lightning.product.InsideBrownianWalk;
import lightning.product.SetWalkTargetFromBlockMemory;
import lightning.product.VillagerProfession;
import lightning.product.AcquirePoi;
import lightning.product.PoiCompetitorScan;
import lightning.product.R_1722_J;
import lightning.product.WakeUp;
import lightning.product.SetWalkTargetAwayFrom;
import lightning.product.WorkAtPoi;
import lightning.product.S_50_d;
import lightning.product.LookAndFollowTradingPlayerSink;
import lightning.product.VillageBoundRandomStroll;
import lightning.product.WorkAtComposter;
import lightning.product.StrollToPoi;
import lightning.product.SetHiddenState;
import lightning.product.SleepInBed;
import lightning.product.ResetRaidStatus;
import lightning.product.Z_749_F;
import lightning.product.SetEntityLookTarget;
import lightning.product.a_4468_e;
import lightning.product.Swim;
import lightning.product.RingBell;
import lightning.product.VillagerCalmDown;
import lightning.product.SocializeAtBell;
import lightning.product.MoveToTargetSink;
import lightning.product.Behavior;
import lightning.product.InteractWith;
import lightning.product.ReactToBell;
import lightning.product.StrollAroundPoi;
import lightning.product.UpdateActivityFromSchedule;
import lightning.product.k_1732_P;
import lightning.product.LocateHidingPlaceDuringRaid;
import lightning.product.HarvestFarmland;
import lightning.product.VillagerPanicTrigger;
import lightning.product.DoNothing;
import lightning.product.q_2232_A;
import lightning.product.r_1628_p;
import lightning.product.r_4811_B;
import lightning.product.VillagerMakeLove;
import lightning.product.t_5_h;
import lightning.product.v_2575_G;
import lightning.product.MemoryModuleType;
import lightning.product.VictoryStroll;
import lightning.product.ValidateNearbyPoi;
import lightning.product.ResetProfession;

public class M_3499_U {
    public static ImmutableList<Pair<Integer, ? extends Behavior<? super L_2225_p>>> n_1700_B(VillagerProfession profession, float p_220638_1_) {
        return ImmutableList.of((Object)Pair.of((Object)0, (Object)new Swim(0.8f)), (Object)Pair.of((Object)0, (Object)new a_4468_e()), (Object)Pair.of((Object)0, (Object)new LookAtTargetSink(45, 90)), (Object)Pair.of((Object)0, (Object)new VillagerPanicTrigger()), (Object)Pair.of((Object)0, (Object)new WakeUp()), (Object)Pair.of((Object)0, (Object)new ReactToBell()), (Object)Pair.of((Object)0, (Object)new SetRaidStatus()), (Object)Pair.of((Object)0, (Object)new ValidateNearbyPoi(profession.n_1700_B(), MemoryModuleType.R_4764_Y)), (Object)Pair.of((Object)0, (Object)new ValidateNearbyPoi(profession.n_1700_B(), MemoryModuleType.G_564_y)), (Object)Pair.of((Object)1, (Object)new MoveToTargetSink()), (Object)Pair.of((Object)2, (Object)new PoiCompetitorScan(profession)), (Object)Pair.of((Object)3, (Object)new LookAndFollowTradingPlayerSink(p_220638_1_)), (Object[])new Pair[]{Pair.of((Object)5, new GoToWantedItem(p_220638_1_, false, 4)), Pair.of((Object)6, (Object)new AcquirePoi(profession.n_1700_B(), MemoryModuleType.R_4764_Y, MemoryModuleType.G_564_y, true, Optional.empty())), Pair.of((Object)7, (Object)new GoToPotentialJobSite(p_220638_1_)), Pair.of((Object)8, (Object)new YieldJobSite(p_220638_1_)), Pair.of((Object)10, (Object)new AcquirePoi(q_2232_A.multiplayerClientSuggestionProvider, MemoryModuleType.J_1907_R, false, Optional.of((byte)14))), Pair.of((Object)10, (Object)new AcquirePoi(q_2232_A.w_1457_N, MemoryModuleType.P_1922_E, true, Optional.of((byte)14))), Pair.of((Object)10, (Object)new AssignProfessionFromJobSite()), Pair.of((Object)10, (Object)new ResetProfession())});
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super L_2225_p>>> J_1907_R(VillagerProfession profession, float p_220639_1_) {
        WorkAtPoi spawngolemtask = profession == VillagerProfession.u_1723_Y ? new WorkAtComposter() : new WorkAtPoi();
        return ImmutableList.of(M_3499_U.J_1907_R(), (Object)Pair.of((Object)5, new E_1879_e(ImmutableList.of((Object)Pair.of((Object)spawngolemtask, (Object)7), (Object)Pair.of((Object)new StrollAroundPoi(MemoryModuleType.R_4764_Y, 0.4f, 4), (Object)2), (Object)Pair.of((Object)new StrollToPoi(MemoryModuleType.R_4764_Y, 0.4f, 1, 10), (Object)5), (Object)Pair.of((Object)new StrollToPoiList(MemoryModuleType.u_1723_Y, p_220639_1_, 1, 6, MemoryModuleType.R_4764_Y), (Object)5), (Object)Pair.of((Object)new HarvestFarmland(), (Object)(profession == VillagerProfession.u_1723_Y ? 2 : 5)), (Object)Pair.of((Object)new I_2956_L(), (Object)(profession == VillagerProfession.u_1723_Y ? 4 : 7))))), (Object)Pair.of((Object)10, (Object)new ShowTradesToPlayer(400, 1600)), (Object)Pair.of((Object)10, (Object)new SetLookAndInteract(t_5_h.g_4106_L, 4)), (Object)Pair.of((Object)2, (Object)new SetWalkTargetFromBlockMemory(MemoryModuleType.R_4764_Y, p_220639_1_, 9, 100, 1200)), (Object)Pair.of((Object)3, (Object)new R_1722_J(100)), (Object)Pair.of((Object)99, (Object)new UpdateActivityFromSchedule()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super L_2225_p>>> n_1700_B(float walkingSpeed) {
        return ImmutableList.of((Object)Pair.of((Object)0, (Object)new MoveToTargetSink(80, 120)), M_3499_U.n_1700_B(), (Object)Pair.of((Object)5, (Object)new r_1628_p()), (Object)Pair.of((Object)5, new E_1879_e((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.t_148_a, (Object)((Object)S_50_d.J_1907_R)), ImmutableList.of((Object)Pair.of(InteractWith.n_1700_B(t_5_h.RealmsDefaultUncaughtExceptionHandler, 8, MemoryModuleType.t_1786_h, walkingSpeed, 2), (Object)2), (Object)Pair.of(InteractWith.n_1700_B(t_5_h.w_1484_f, 8, MemoryModuleType.t_1786_h, walkingSpeed, 2), (Object)1), (Object)Pair.of((Object)new VillageBoundRandomStroll(walkingSpeed), (Object)1), (Object)Pair.of((Object)new SetWalkTargetFromLookTarget(walkingSpeed, 2), (Object)1), (Object)Pair.of((Object)new v_2575_G(walkingSpeed), (Object)2), (Object)Pair.of((Object)new DoNothing(20, 40), (Object)2)))), (Object)Pair.of((Object)99, (Object)new UpdateActivityFromSchedule()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super L_2225_p>>> R_4764_Y(VillagerProfession profession, float walkingSpeed) {
        return ImmutableList.of((Object)Pair.of((Object)2, (Object)new SetWalkTargetFromBlockMemory(MemoryModuleType.J_1907_R, walkingSpeed, 1, 150, 1200)), (Object)Pair.of((Object)3, (Object)new ValidateNearbyPoi(q_2232_A.multiplayerClientSuggestionProvider, MemoryModuleType.J_1907_R)), (Object)Pair.of((Object)3, (Object)new SleepInBed()), (Object)Pair.of((Object)5, new E_1879_e((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.J_1907_R, (Object)((Object)S_50_d.J_1907_R)), ImmutableList.of((Object)Pair.of((Object)new SetClosestHomeAsWalkTarget(walkingSpeed), (Object)1), (Object)Pair.of((Object)new InsideBrownianWalk(walkingSpeed), (Object)4), (Object)Pair.of((Object)new GoToClosestVillage(walkingSpeed, 4), (Object)2), (Object)Pair.of((Object)new DoNothing(20, 40), (Object)2)))), M_3499_U.J_1907_R(), (Object)Pair.of((Object)99, (Object)new UpdateActivityFromSchedule()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super L_2225_p>>> G_564_y(VillagerProfession profession, float p_220637_1_) {
        return ImmutableList.of((Object)Pair.of((Object)2, new E_1879_e(ImmutableList.of((Object)Pair.of((Object)new StrollAroundPoi(MemoryModuleType.P_1922_E, 0.4f, 40), (Object)2), (Object)Pair.of((Object)new SocializeAtBell(), (Object)2)))), (Object)Pair.of((Object)10, (Object)new ShowTradesToPlayer(400, 1600)), (Object)Pair.of((Object)10, (Object)new SetLookAndInteract(t_5_h.g_4106_L, 4)), (Object)Pair.of((Object)2, (Object)new SetWalkTargetFromBlockMemory(MemoryModuleType.P_1922_E, p_220637_1_, 6, 100, 200)), (Object)Pair.of((Object)3, (Object)new R_1722_J(100)), (Object)Pair.of((Object)3, (Object)new ValidateNearbyPoi(q_2232_A.w_1457_N, MemoryModuleType.P_1922_E)), (Object)Pair.of((Object)3, new L_2946_U((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(), (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.t_1786_h), L_2946_U.n_1700_B.n_1700_B, L_2946_U.J_1907_R.n_1700_B, ImmutableList.of((Object)Pair.of((Object)new k_1732_P(), (Object)1)))), M_3499_U.n_1700_B(), (Object)Pair.of((Object)99, (Object)new UpdateActivityFromSchedule()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super L_2225_p>>> P_1922_E(VillagerProfession profession, float p_220641_1_) {
        return ImmutableList.of((Object)Pair.of((Object)2, new E_1879_e(ImmutableList.of((Object)Pair.of(InteractWith.n_1700_B(t_5_h.RealmsDefaultUncaughtExceptionHandler, 8, MemoryModuleType.t_1786_h, p_220641_1_, 2), (Object)2), (Object)Pair.of(new InteractWith<L_2225_p, AgableMob>(t_5_h.RealmsDefaultUncaughtExceptionHandler, 8, AgableMob::w_, AgableMob::w_, MemoryModuleType.multiplayerClientSuggestionProvider, p_220641_1_, 2), (Object)1), (Object)Pair.of(InteractWith.n_1700_B(t_5_h.w_1484_f, 8, MemoryModuleType.t_1786_h, p_220641_1_, 2), (Object)1), (Object)Pair.of((Object)new VillageBoundRandomStroll(p_220641_1_), (Object)1), (Object)Pair.of((Object)new SetWalkTargetFromLookTarget(p_220641_1_, 2), (Object)1), (Object)Pair.of((Object)new v_2575_G(p_220641_1_), (Object)1), (Object)Pair.of((Object)new DoNothing(30, 60), (Object)1)))), (Object)Pair.of((Object)3, (Object)new R_1722_J(100)), (Object)Pair.of((Object)3, (Object)new SetLookAndInteract(t_5_h.g_4106_L, 4)), (Object)Pair.of((Object)3, (Object)new ShowTradesToPlayer(400, 1600)), (Object)Pair.of((Object)3, new L_2946_U((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(), (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.t_1786_h), L_2946_U.n_1700_B.n_1700_B, L_2946_U.J_1907_R.n_1700_B, ImmutableList.of((Object)Pair.of((Object)new k_1732_P(), (Object)1)))), (Object)Pair.of((Object)3, new L_2946_U((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(), (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.multiplayerClientSuggestionProvider), L_2946_U.n_1700_B.n_1700_B, L_2946_U.J_1907_R.n_1700_B, ImmutableList.of((Object)Pair.of((Object)new VillagerMakeLove(), (Object)1)))), M_3499_U.n_1700_B(), (Object)Pair.of((Object)99, (Object)new UpdateActivityFromSchedule()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super L_2225_p>>> u_1723_Y(VillagerProfession profession, float p_220636_1_) {
        float f = p_220636_1_ * 1.5f;
        return ImmutableList.of((Object)Pair.of((Object)0, (Object)new VillagerCalmDown()), (Object)Pair.of((Object)1, SetWalkTargetAwayFrom.J_1907_R(MemoryModuleType.c_3005_b, f, 6, false)), (Object)Pair.of((Object)1, SetWalkTargetAwayFrom.J_1907_R(MemoryModuleType.q_2307_F, f, 6, false)), (Object)Pair.of((Object)3, (Object)new VillageBoundRandomStroll(f, 2, 2)), M_3499_U.J_1907_R());
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super L_2225_p>>> v_4262_N(VillagerProfession profession, float p_220642_1_) {
        return ImmutableList.of((Object)Pair.of((Object)0, (Object)new RingBell()), (Object)Pair.of((Object)0, new E_1879_e(ImmutableList.of((Object)Pair.of((Object)new SetWalkTargetFromBlockMemory(MemoryModuleType.P_1922_E, p_220642_1_ * 1.5f, 2, 150, 200), (Object)6), (Object)Pair.of((Object)new VillageBoundRandomStroll(p_220642_1_ * 1.5f), (Object)2)))), M_3499_U.J_1907_R(), (Object)Pair.of((Object)99, (Object)new ResetRaidStatus()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super L_2225_p>>> w_1484_f(VillagerProfession profession, float p_220640_1_) {
        return ImmutableList.of((Object)Pair.of((Object)0, new E_1879_e(ImmutableList.of((Object)Pair.of((Object)new GoOutsideToCelebrate(p_220640_1_), (Object)5), (Object)Pair.of((Object)new VictoryStroll(p_220640_1_ * 1.1f), (Object)2)))), (Object)Pair.of((Object)0, (Object)new CelebrateVillagersSurvivedRaid(600, 600)), (Object)Pair.of((Object)2, (Object)new LocateHidingPlaceDuringRaid(24, p_220640_1_ * 1.4f)), M_3499_U.J_1907_R(), (Object)Pair.of((Object)99, (Object)new ResetRaidStatus()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super L_2225_p>>> t_148_a(VillagerProfession profession, float p_220644_1_) {
        int i = 2;
        return ImmutableList.of((Object)Pair.of((Object)0, (Object)new SetHiddenState(15, 3)), (Object)Pair.of((Object)1, (Object)new LocateHidingPlace(32, p_220644_1_ * 1.25f, 2)), M_3499_U.J_1907_R());
    }

    private static Pair<Integer, Behavior<r_4811_B>> n_1700_B() {
        return Pair.of((Object)5, new E_1879_e(ImmutableList.of((Object)Pair.of((Object)new SetEntityLookTarget(t_5_h.w_1484_f, 8.0f), (Object)8), (Object)Pair.of((Object)new SetEntityLookTarget(t_5_h.RealmsDefaultUncaughtExceptionHandler, 8.0f), (Object)2), (Object)Pair.of((Object)new SetEntityLookTarget(t_5_h.g_4106_L, 8.0f), (Object)2), (Object)Pair.of((Object)new SetEntityLookTarget(Z_749_F.J_1907_R, 8.0f), (Object)1), (Object)Pair.of((Object)new SetEntityLookTarget(Z_749_F.G_564_y, 8.0f), (Object)1), (Object)Pair.of((Object)new SetEntityLookTarget(Z_749_F.P_1922_E, 8.0f), (Object)1), (Object)Pair.of((Object)new SetEntityLookTarget(Z_749_F.n_1700_B, 8.0f), (Object)1), (Object)Pair.of((Object)new DoNothing(30, 60), (Object)2))));
    }

    private static Pair<Integer, Behavior<r_4811_B>> J_1907_R() {
        return Pair.of((Object)5, new E_1879_e(ImmutableList.of((Object)Pair.of((Object)new SetEntityLookTarget(t_5_h.RealmsDefaultUncaughtExceptionHandler, 8.0f), (Object)2), (Object)Pair.of((Object)new SetEntityLookTarget(t_5_h.g_4106_L, 8.0f), (Object)2), (Object)Pair.of((Object)new DoNothing(30, 60), (Object)8))));
    }
}


