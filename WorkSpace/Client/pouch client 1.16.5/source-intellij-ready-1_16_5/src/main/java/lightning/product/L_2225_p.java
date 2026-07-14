/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.DebugPackets;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.D_2364_U;
import lightning.product.D_4533_B;
import lightning.product.SensorType;
import lightning.product.E_4668_a;
import lightning.product.F_2904_S;
import lightning.product.F_427_K;
import lightning.product.Attributes;
import lightning.product.H_3779_g;
import lightning.product.ReputationEventType;
import lightning.product.I_2154_Z;
import lightning.product.I_4817_s;
import lightning.product.MobEffects;
import lightning.product.VillagerData;
import lightning.product.K_4074_S;
import lightning.product.M_3499_U;
import lightning.product.N_1216_z;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.VillagerProfession;
import lightning.product.Q_1649_w;
import lightning.product.R_2450_T;
import lightning.product.R_3043_n;
import lightning.product.S_50_d;
import lightning.product.T_1316_M;
import lightning.product.Stats;
import lightning.product.U_2912_j;
import lightning.product.GolemSensor;
import lightning.product.V_3137_a;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Tag;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_3129_s;
import lightning.product.Sensor;
import lightning.product.b_4507_u;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.g_4621_i;
import lightning.product.h_256_u;
import lightning.product.i_2099_H;
import lightning.product.Activity;
import lightning.product.Schedule;
import lightning.product.MerchantOffers;
import lightning.product.EntityDataSerializers;
import lightning.product.VillagerTrades;
import lightning.product.k_2610_C;
import lightning.product.l_4118_l;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.n_4637_L;
import lightning.product.q_1613_l;
import lightning.product.q_2232_A;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.LightningBolt;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.MemoryModuleType;
import lightning.product.w_3611_Y;
import lightning.product.MerchantOffer;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.Logger;

public class L_2225_p
extends g_4621_i
implements D_4533_B,
I_2154_Z {
    private static final h_256_u<VillagerData> t_1786_h = C_4114_x.n_1700_B(L_2225_p.class, EntityDataSerializers.t_1786_h);
    public static final Map<q_1613_l, Integer> Q_4569_t = ImmutableMap.of((Object)Items.m_3828_C, (Object)4, (Object)Items.l_683_e, (Object)1, (Object)Items.BaseCoralWallFanBlock, (Object)1, (Object)Items.s_3401_U, (Object)1);
    private static final Set<q_1613_l> multiplayerClientSuggestionProvider = ImmutableSet.of((Object)Items.m_3828_C, (Object)Items.l_683_e, (Object)Items.BaseCoralWallFanBlock, (Object)Items.V_3441_j, (Object)Items.G_4691_Q, (Object)Items.s_3401_U, (Object[])new q_1613_l[]{Items.MushroomBlock});
    private int w_1457_N;
    private boolean Y_601_j;
    @Nullable
    private a_3913_L Y_259_p;
    private byte Q_2552_b;
    private final H_3779_g C_2741_M = new H_3779_g();
    private long k_2293_S;
    private long q_2307_F;
    private int Z_875_P;
    private long c_3005_b;
    private int H_2857_Y;
    private long A_4115_X;
    private boolean Y_1740_V;
    private static final ImmutableList<MemoryModuleType<?>> t_4043_B = ImmutableList.of(MemoryModuleType.J_1907_R, MemoryModuleType.R_4764_Y, MemoryModuleType.G_564_y, MemoryModuleType.P_1922_E, MemoryModuleType.v_4262_N, MemoryModuleType.w_1484_f, MemoryModuleType.t_148_a, MemoryModuleType.s_956_w, MemoryModuleType.u_2550_I, MemoryModuleType.M_588_G, MemoryModuleType.z_1737_N, MemoryModuleType.P_4830_p, (Object[])new MemoryModuleType[]{MemoryModuleType.h_1847_R, MemoryModuleType.t_1786_h, MemoryModuleType.multiplayerClientSuggestionProvider, MemoryModuleType.Y_601_j, MemoryModuleType.Q_2552_b, MemoryModuleType.C_2741_M, MemoryModuleType.k_2293_S, MemoryModuleType.q_2307_F, MemoryModuleType.c_3005_b, MemoryModuleType.u_1723_Y, MemoryModuleType.H_2857_Y, MemoryModuleType.A_4115_X, MemoryModuleType.Y_1740_V, MemoryModuleType.x_607_J, MemoryModuleType.e_4240_b, MemoryModuleType.n_3318_d, MemoryModuleType.t_4043_B});
    private static final ImmutableList<SensorType<? extends Sensor<? super L_2225_p>>> x_607_J = ImmutableList.of(SensorType.R_4764_Y, SensorType.G_564_y, SensorType.J_1907_R, SensorType.P_1922_E, SensorType.u_1723_Y, SensorType.v_4262_N, SensorType.w_1484_f, SensorType.t_148_a, SensorType.s_956_w);
    public static final Map<MemoryModuleType<F_427_K>, BiPredicate<L_2225_p, q_2232_A>> M_182_A = ImmutableMap.of(MemoryModuleType.J_1907_R, (villager, poiType) -> poiType == q_2232_A.multiplayerClientSuggestionProvider, MemoryModuleType.R_4764_Y, (villager, poiType) -> villager.c_2086_l().J_1907_R().n_1700_B() == poiType, MemoryModuleType.G_564_y, (villager, poiType) -> q_2232_A.n_1700_B.test((q_2232_A)poiType), MemoryModuleType.P_1922_E, (villager, poiType) -> poiType == q_2232_A.w_1457_N);

    public L_2225_p(t_5_h<? extends L_2225_p> type, b_4507_u worldIn) {
        this(type, worldIn, R_3043_n.R_4764_Y);
    }

    public L_2225_p(t_5_h<? extends L_2225_p> type, b_4507_u worldIn, R_3043_n villagerType) {
        super((t_5_h<? extends g_4621_i>)type, worldIn);
        ((i_2099_H)this.e_4240_b()).n_1700_B(true);
        this.e_4240_b().R_4764_Y(true);
        this.R_4764_Y(true);
        this.n_1700_B(this.c_2086_l().n_1700_B(villagerType).n_1700_B(VillagerProfession.n_1700_B));
    }

    public E_4668_a<L_2225_p> y_1945_D() {
        return super.y_1945_D();
    }

    protected E_4668_a.n_1700_B<L_2225_p> U_532_X() {
        return E_4668_a.n_1700_B(t_4043_B, x_607_J);
    }

    @Override
    protected E_4668_a<?> n_1700_B(Dynamic<?> dynamicIn) {
        E_4668_a<L_2225_p> brain = this.U_532_X().n_1700_B(dynamicIn);
        this.n_1700_B(brain);
        return brain;
    }

    public void R_4764_Y(e_3591_l serverWorldIn) {
        E_4668_a<L_2225_p> brain = this.y_1945_D();
        brain.J_1907_R(serverWorldIn, this);
        this.Y_776_s = brain.P_1922_E();
        this.n_1700_B(this.y_1945_D());
    }

    private void n_1700_B(E_4668_a<L_2225_p> villagerBrain) {
        VillagerProfession villagerprofession = this.c_2086_l().J_1907_R();
        if (this.d_()) {
            villagerBrain.n_1700_B(Schedule.R_4764_Y);
            villagerBrain.n_1700_B(Activity.G_564_y, M_3499_U.n_1700_B(0.5f));
        } else {
            villagerBrain.n_1700_B(Schedule.G_564_y);
            villagerBrain.n_1700_B(Activity.R_4764_Y, (ImmutableList<Pair<Integer, Behavior<L_2225_p>>>)M_3499_U.J_1907_R(villagerprofession, 0.5f), (Set<Pair<MemoryModuleType<?>, S_50_d>>)ImmutableSet.of((Object)Pair.of(MemoryModuleType.R_4764_Y, (Object)((Object)S_50_d.n_1700_B))));
        }
        villagerBrain.n_1700_B(Activity.n_1700_B, M_3499_U.n_1700_B(villagerprofession, 0.5f));
        villagerBrain.n_1700_B(Activity.u_1723_Y, (ImmutableList<Pair<Integer, Behavior<L_2225_p>>>)M_3499_U.G_564_y(villagerprofession, 0.5f), (Set<Pair<MemoryModuleType<?>, S_50_d>>)ImmutableSet.of((Object)Pair.of(MemoryModuleType.P_1922_E, (Object)((Object)S_50_d.n_1700_B))));
        villagerBrain.n_1700_B(Activity.P_1922_E, M_3499_U.R_4764_Y(villagerprofession, 0.5f));
        villagerBrain.n_1700_B(Activity.J_1907_R, M_3499_U.P_1922_E(villagerprofession, 0.5f));
        villagerBrain.n_1700_B(Activity.v_4262_N, M_3499_U.u_1723_Y(villagerprofession, 0.5f));
        villagerBrain.n_1700_B(Activity.t_148_a, M_3499_U.v_4262_N(villagerprofession, 0.5f));
        villagerBrain.n_1700_B(Activity.w_1484_f, M_3499_U.w_1484_f(villagerprofession, 0.5f));
        villagerBrain.n_1700_B(Activity.s_956_w, M_3499_U.t_148_a(villagerprofession, 0.5f));
        villagerBrain.n_1700_B((Set<Activity>)ImmutableSet.of((Object)Activity.n_1700_B));
        villagerBrain.J_1907_R(Activity.J_1907_R);
        villagerBrain.n_1700_B(Activity.J_1907_R);
        villagerBrain.n_1700_B(this.O_508_d.Z_976_R(), this.O_508_d.X_933_l());
    }

    @Override
    protected void y_() {
        super.y_();
        if (this.O_508_d instanceof e_3591_l) {
            this.R_4764_Y((e_3591_l)this.O_508_d);
        }
    }

    public static s_1415_m.n_1700_B h_973_D() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.G_564_y, 0.5).n_1700_B(Attributes.J_1907_R, 48.0);
    }

    public boolean f_2787_O() {
        return this.Y_1740_V;
    }

    @Override
    protected void X_933_l() {
        b_3129_s raid;
        this.O_508_d.D_4792_h().n_1700_B("villagerBrain");
        this.y_1945_D().n_1700_B((e_3591_l)this.O_508_d, this);
        this.O_508_d.D_4792_h().R_4764_Y();
        if (this.Y_1740_V) {
            this.Y_1740_V = false;
        }
        if (!this.h_1640_b() && this.w_1457_N > 0) {
            --this.w_1457_N;
            if (this.w_1457_N <= 0) {
                if (this.Y_601_j) {
                    this.h_2367_h();
                    this.Y_601_j = false;
                }
                this.n_1700_B(new k_2610_C(MobEffects.s_956_w, 200, 0));
            }
        }
        if (this.Y_259_p != null && this.O_508_d instanceof e_3591_l) {
            ((e_3591_l)this.O_508_d).n_1700_B(ReputationEventType.P_1922_E, (N_4263_v)this.Y_259_p, this);
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)14);
            this.Y_259_p = null;
        }
        if (!this.n_473_l() && this.RealmsWorldOptions.nextInt(100) == 0 && (raid = ((e_3591_l)this.O_508_d).Z_875_P(this.b_2312_j())) != null && raid.Y_601_j() && !raid.n_1700_B()) {
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)42);
        }
        if (this.c_2086_l().J_1907_R() == VillagerProfession.n_1700_B && this.h_1640_b()) {
            this.y_2447_C();
        }
        super.X_933_l();
    }

    @Override
    public void v_() {
        super.v_();
        if (this.y_4642_Y() > 0) {
            this.w_1457_N(this.y_4642_Y() - 1);
        }
        this.ModeSetting();
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (itemstack.J_1907_R() != Items.CocoaBlock && this.RealmsLongRunningMcoTaskScreen() && !this.h_1640_b() && !this.z_2372_L()) {
            if (this.d_()) {
                this.R_2822_N();
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
            boolean flag = this.J_1907_R().isEmpty();
            if (p_230254_2_ == x_1688_C.n_1700_B) {
                if (flag && !this.O_508_d.Y_259_p) {
                    this.R_2822_N();
                }
                p_230254_1_.J_1907_R(Stats.e_2887_G);
            }
            if (flag) {
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
            if (!this.O_508_d.Y_259_p && !this.h_1847_R.isEmpty()) {
                this.u_1723_Y(p_230254_1_);
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    private void R_2822_N() {
        this.w_1457_N(40);
        if (!this.O_508_d.v_4276_D()) {
            this.n_1700_B(SoundEvents.C_1985_D, this.d_4500_Q(), this.O_2761_o());
        }
    }

    private void u_1723_Y(a_3913_L player) {
        this.v_4262_N(player);
        this.n_1700_B(player);
        this.n_1700_B(player, this.c_(), this.c_2086_l().R_4764_Y());
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L player) {
        boolean flag = this.n_1700_B() != null && player == null;
        super.n_1700_B(player);
        if (flag) {
            this.y_2447_C();
        }
    }

    @Override
    protected void y_2447_C() {
        super.y_2447_C();
        this.ModuleCategory();
    }

    private void ModuleCategory() {
        for (MerchantOffer merchantoffer : this.J_1907_R()) {
            merchantoffer.M_588_G();
        }
    }

    @Override
    public boolean v_4262_N() {
        return true;
    }

    public void P_2295_B() {
        this.Setting();
        for (MerchantOffer merchantoffer : this.J_1907_R()) {
            merchantoffer.w_1484_f();
        }
        this.c_3005_b = this.O_508_d.X_933_l();
        ++this.H_2857_Y;
    }

    private boolean p_1458_L() {
        for (MerchantOffer merchantoffer : this.J_1907_R()) {
            if (!merchantoffer.multiplayerClientSuggestionProvider()) continue;
            return true;
        }
        return false;
    }

    private boolean Module() {
        return this.H_2857_Y == 0 || this.H_2857_Y < 2 && this.O_508_d.X_933_l() > this.c_3005_b + 2400L;
    }

    public boolean U_1697_c() {
        long i = this.c_3005_b + 12000L;
        long j = this.O_508_d.X_933_l();
        boolean flag = j > i;
        long k = this.O_508_d.Z_976_R();
        if (this.A_4115_X > 0L) {
            long i1 = k / 24000L;
            long l = this.A_4115_X / 24000L;
            flag |= i1 > l;
        }
        this.A_4115_X = k;
        if (flag) {
            this.c_3005_b = j;
            this.MultiBooleanSetting();
        }
        return this.Module() && this.p_1458_L();
    }

    private void ModuleManager() {
        int i = 2 - this.H_2857_Y;
        if (i > 0) {
            for (MerchantOffer merchantoffer : this.J_1907_R()) {
                merchantoffer.w_1484_f();
            }
        }
        for (int j = 0; j < i; ++j) {
            this.Setting();
        }
    }

    private void Setting() {
        for (MerchantOffer merchantoffer : this.J_1907_R()) {
            merchantoffer.P_1922_E();
        }
    }

    private void v_4262_N(a_3913_L playerIn) {
        int i = this.P_1922_E(playerIn);
        if (i != 0) {
            for (MerchantOffer merchantoffer : this.J_1907_R()) {
                merchantoffer.n_1700_B(-u_530_F.G_564_y((float)i * merchantoffer.h_1847_R()));
            }
        }
        if (playerIn.J_1907_R(MobEffects.x_607_J)) {
            k_2610_C effectinstance = playerIn.R_4764_Y(MobEffects.x_607_J);
            int k = effectinstance.R_4764_Y();
            for (MerchantOffer merchantoffer1 : this.J_1907_R()) {
                double d0 = 0.3 + 0.0625 * (double)k;
                int j = (int)Math.floor(d0 * (double)merchantoffer1.n_1700_B().t_4043_B());
                merchantoffer1.n_1700_B(-Math.max(j, 1));
            }
        }
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(t_1786_h, new VillagerData(R_3043_n.R_4764_Y, VillagerProfession.n_1700_B, 1));
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        VillagerData.n_1700_B.encodeStart((DynamicOps)l_4118_l.n_1700_B, (Object)this.c_2086_l()).resultOrPartial(arg_0 -> ((Logger)D_4792_h).error(arg_0)).ifPresent(data -> compound.n_1700_B("VillagerData", (Tag)data));
        compound.n_1700_B("FoodLevel", this.Q_2552_b);
        compound.n_1700_B("Gossips", (Tag)this.C_2741_M.n_1700_B(l_4118_l.n_1700_B).getValue());
        compound.J_1907_R("Xp", this.Z_875_P);
        compound.n_1700_B("LastRestock", this.c_3005_b);
        compound.n_1700_B("LastGossipDecay", this.q_2307_F);
        compound.J_1907_R("RestocksToday", this.H_2857_Y);
        if (this.Y_1740_V) {
            compound.n_1700_B("AssignProfessionWhenSpawned", true);
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("VillagerData", 10)) {
            DataResult dataresult = VillagerData.n_1700_B.parse(new Dynamic((DynamicOps)l_4118_l.n_1700_B, (Object)compound.R_4764_Y("VillagerData")));
            dataresult.resultOrPartial(arg_0 -> ((Logger)D_4792_h).error(arg_0)).ifPresent(this::n_1700_B);
        }
        if (compound.R_4764_Y("Offers", 10)) {
            this.h_1847_R = new MerchantOffers(compound.M_182_A("Offers"));
        }
        if (compound.R_4764_Y("FoodLevel", 1)) {
            this.Q_2552_b = compound.u_1723_Y("FoodLevel");
        }
        q_2896_o listnbt = compound.G_564_y("Gossips", 10);
        this.C_2741_M.n_1700_B(new Dynamic((DynamicOps)l_4118_l.n_1700_B, (Object)listnbt));
        if (compound.R_4764_Y("Xp", 3)) {
            this.Z_875_P = compound.w_1484_f("Xp");
        }
        this.c_3005_b = compound.t_148_a("LastRestock");
        this.q_2307_F = compound.t_148_a("LastGossipDecay");
        this.R_4764_Y(true);
        if (this.O_508_d instanceof e_3591_l) {
            this.R_4764_Y((e_3591_l)this.O_508_d);
        }
        this.H_2857_Y = compound.w_1484_f("RestocksToday");
        if (compound.P_1922_E("AssignProfessionWhenSpawned")) {
            this.Y_1740_V = compound.t_1786_h("AssignProfessionWhenSpawned");
        }
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    @Nullable
    protected SoundEvent z_4693_k() {
        if (this.z_2372_L()) {
            return null;
        }
        return this.h_1640_b() ? SoundEvents.R_2215_C : SoundEvents.JigsawBlock;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.V_1395_p;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.KelpPlantBlock;
    }

    public void V_537_k() {
        SoundEvent soundevent = this.c_2086_l().J_1907_R().G_564_y();
        if (soundevent != null) {
            this.n_1700_B(soundevent, this.d_4500_Q(), this.O_2761_o());
        }
    }

    public void n_1700_B(VillagerData data) {
        VillagerData villagerdata = this.c_2086_l();
        if (villagerdata.J_1907_R() != data.J_1907_R()) {
            this.h_1847_R = null;
        }
        this.l_4537_E.J_1907_R(t_1786_h, data);
    }

    @Override
    public VillagerData c_2086_l() {
        return this.l_4537_E.n_1700_B(t_1786_h);
    }

    @Override
    protected void J_1907_R(MerchantOffer offer) {
        int i = 3 + this.RealmsWorldOptions.nextInt(4);
        this.Z_875_P += offer.Q_4569_t();
        this.Y_259_p = this.n_1700_B();
        if (this.H_1491_c()) {
            this.w_1457_N = 40;
            this.Y_601_j = true;
            i += 5;
        }
        if (offer.w_1457_N()) {
            this.O_508_d.a_(new n_4637_L(this.O_508_d, this.O_3598_v(), this.X_2960_b() + 0.5, this.l_2647_k(), i));
        }
    }

    @Override
    public void J_1907_R(@Nullable r_4811_B livingBase) {
        if (livingBase != null && this.O_508_d instanceof e_3591_l) {
            ((e_3591_l)this.O_508_d).n_1700_B(ReputationEventType.R_4764_Y, (N_4263_v)livingBase, this);
            if (this.RealmsLongRunningMcoTaskScreen() && livingBase instanceof a_3913_L) {
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)13);
            }
        }
        super.J_1907_R(livingBase);
    }

    @Override
    public void R_4764_Y(P_11_z cause) {
        D_4792_h.info("Villager {} died, message: '{}'", (Object)this, (Object)cause.n_1700_B(this).getString());
        N_4263_v entity = cause.u_2550_I();
        if (entity != null) {
            this.n_1700_B(entity);
        }
        this.KeyBindSetting();
        super.R_4764_Y(cause);
    }

    private void KeyBindSetting() {
        this.n_1700_B(MemoryModuleType.J_1907_R);
        this.n_1700_B(MemoryModuleType.R_4764_Y);
        this.n_1700_B(MemoryModuleType.G_564_y);
        this.n_1700_B(MemoryModuleType.P_1922_E);
    }

    private void n_1700_B(N_4263_v murderer) {
        Optional<List<r_4811_B>> optional;
        if (this.O_508_d instanceof e_3591_l && (optional = this.Y_776_s.R_4764_Y(MemoryModuleType.w_1484_f)).isPresent()) {
            e_3591_l serverworld = (e_3591_l)this.O_508_d;
            optional.get().stream().filter(gossipTarget -> gossipTarget instanceof D_4533_B).forEach(gossipTarget -> serverworld.n_1700_B(ReputationEventType.G_564_y, murderer, (D_4533_B)((Object)gossipTarget)));
        }
    }

    public void n_1700_B(MemoryModuleType<F_427_K> moduleType) {
        if (this.O_508_d instanceof e_3591_l) {
            G_564_y minecraftserver = ((e_3591_l)this.O_508_d).T_2506_i();
            this.Y_776_s.R_4764_Y(moduleType).ifPresent(jobSitePos -> {
                e_3591_l serverworld = minecraftserver.n_1700_B(jobSitePos.n_1700_B());
                if (serverworld != null) {
                    b_4946_z pointofinterestmanager = serverworld.p_178_J();
                    Optional<q_2232_A> optional = pointofinterestmanager.R_4764_Y(jobSitePos.J_1907_R());
                    BiPredicate<L_2225_p, q_2232_A> bipredicate = M_182_A.get(moduleType);
                    if (optional.isPresent() && bipredicate.test(this, optional.get())) {
                        pointofinterestmanager.J_1907_R(jobSitePos.J_1907_R());
                        DebugPackets.R_4764_Y(serverworld, jobSitePos.J_1907_R());
                    }
                }
            });
        }
    }

    @Override
    public boolean w_() {
        return this.Q_2552_b + this.c_1608_O() >= 12 && this.x_() == 0;
    }

    private boolean BooleanSetting() {
        return this.Q_2552_b < 12;
    }

    private void SoundEventRegistration() {
        if (this.BooleanSetting() && this.c_1608_O() != 0) {
            for (int i = 0; i < this.J_3635_s().Y_259_p(); ++i) {
                int j;
                Integer integer;
                Z_1993_T itemstack = this.J_3635_s().s_956_w(i);
                if (itemstack.n_1700_B() || (integer = Q_4569_t.get(itemstack.J_1907_R())) == null) continue;
                for (int k = j = itemstack.t_4043_B(); k > 0; --k) {
                    this.Q_2552_b = (byte)(this.Q_2552_b + integer);
                    this.J_3635_s().n_1700_B(i, 1);
                    if (this.BooleanSetting()) continue;
                    return;
                }
            }
        }
    }

    public int P_1922_E(a_3913_L player) {
        return this.C_2741_M.n_1700_B(player.w_2705_t(), (Q_1649_w gossipType) -> true);
    }

    private void Y_259_p(int qty) {
        this.Q_2552_b = (byte)(this.Q_2552_b - qty);
    }

    public void o_4117_e() {
        this.SoundEventRegistration();
        this.Y_259_p(12);
    }

    public void J_1907_R(MerchantOffers offersIn) {
        this.h_1847_R = offersIn;
    }

    private boolean H_1491_c() {
        int i = this.c_2086_l().R_4764_Y();
        return VillagerData.G_564_y(i) && this.Z_875_P >= VillagerData.R_4764_Y(i);
    }

    private void h_2367_h() {
        this.n_1700_B(this.c_2086_l().n_1700_B(this.c_2086_l().R_4764_Y() + 1));
        this.o_82_k();
    }

    @Override
    protected x_282_a O_2934_T() {
        return new F_2904_S(this.f_4016_n().u_1723_Y() + "." + V_3137_a.r_715_M.J_1907_R(this.c_2086_l().J_1907_R()).J_1907_R());
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 12) {
            this.n_1700_B(ParticleTypes.e_4240_b);
        } else if (id == 13) {
            this.n_1700_B(ParticleTypes.J_1907_R);
        } else if (id == 14) {
            this.n_1700_B(ParticleTypes.t_4043_B);
        } else if (id == 42) {
            this.n_1700_B(ParticleTypes.g_2268_R);
        } else {
            super.n_1700_B(id);
        }
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        if (reason == a_3160_D.P_1922_E) {
            this.n_1700_B(this.c_2086_l().n_1700_B(VillagerProfession.n_1700_B));
        }
        if (reason == a_3160_D.h_1847_R || reason == a_3160_D.P_4830_p || reason == a_3160_D.R_4764_Y || reason == a_3160_D.Q_4569_t) {
            this.n_1700_B(this.c_2086_l().n_1700_B(R_3043_n.n_1700_B(worldIn.n_1700_B(this.b_2312_j()))));
        }
        if (reason == a_3160_D.G_564_y) {
            this.Y_1740_V = true;
        }
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public L_2225_p J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        double d0 = this.RealmsWorldOptions.nextDouble();
        R_3043_n villagertype = d0 < 0.5 ? R_3043_n.n_1700_B(p_241840_1_.n_1700_B(this.b_2312_j())) : (d0 < 0.75 ? this.c_2086_l().n_1700_B() : ((L_2225_p)p_241840_2_).c_2086_l().n_1700_B());
        L_2225_p villagerentity = new L_2225_p(t_5_h.RealmsDefaultUncaughtExceptionHandler, p_241840_1_, villagertype);
        villagerentity.n_1700_B(p_241840_1_, p_241840_1_.J_1907_R(villagerentity.b_2312_j()), a_3160_D.P_1922_E, (V_3157_k)null, null);
        return villagerentity;
    }

    @Override
    public void n_1700_B(e_3591_l p_241841_1_, LightningBolt p_241841_2_) {
        if (p_241841_1_.x_607_J() != R_2450_T.n_1700_B) {
            D_4792_h.info("Villager {} was struck by lightning {}.", (Object)this, (Object)p_241841_2_);
            w_3611_Y witchentity = t_5_h.RetryCallException.n_1700_B(p_241841_1_);
            witchentity.J_1907_R(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.p_178_J, this.f_4016_n);
            witchentity.n_1700_B(p_241841_1_, p_241841_1_.J_1907_R(witchentity.b_2312_j()), a_3160_D.t_148_a, (V_3157_k)null, null);
            witchentity.G_564_y(this.n_473_l());
            if (this.t_3452_g()) {
                witchentity.n_1700_B(this.k_2302_P());
                witchentity.M_182_A(this.V_118_c());
            }
            witchentity.T_3594_S();
            p_241841_1_.n_1700_B((N_4263_v)witchentity);
            this.KeyBindSetting();
            this.Ops();
        } else {
            super.n_1700_B(p_241841_1_, p_241841_2_);
        }
    }

    @Override
    protected void J_1907_R(n_1494_c itemEntity) {
        Z_1993_T itemstack = itemEntity.P_1922_E();
        if (this.t_148_a(itemstack)) {
            N_1216_z inventory = this.J_3635_s();
            boolean flag = inventory.J_1907_R(itemstack);
            if (!flag) {
                return;
            }
            this.n_1700_B(itemEntity);
            this.n_1700_B((N_4263_v)itemEntity, itemstack.t_4043_B());
            Z_1993_T itemstack1 = inventory.n_1700_B(itemstack);
            if (itemstack1.n_1700_B()) {
                itemEntity.Ops();
            } else {
                itemstack.P_1922_E(itemstack1.t_4043_B());
            }
        }
    }

    @Override
    public boolean t_148_a(Z_1993_T p_230293_1_) {
        q_1613_l item = p_230293_1_.J_1907_R();
        return (multiplayerClientSuggestionProvider.contains(item) || this.c_2086_l().J_1907_R().J_1907_R().contains((Object)item)) && this.J_3635_s().J_1907_R(p_230293_1_);
    }

    public boolean U_3758_B() {
        return this.c_1608_O() >= 24;
    }

    public boolean y_3417_N() {
        return this.c_1608_O() < 12;
    }

    private int c_1608_O() {
        N_1216_z inventory = this.J_3635_s();
        return Q_4569_t.entrySet().stream().mapToInt(foodValueEntry -> inventory.n_1700_B((q_1613_l)foodValueEntry.getKey()) * (Integer)foodValueEntry.getValue()).sum();
    }

    public boolean A_1306_N() {
        return this.J_3635_s().n_1700_B((Set<q_1613_l>)ImmutableSet.of((Object)Items.G_4691_Q, (Object)Items.l_683_e, (Object)Items.BaseCoralWallFanBlock, (Object)Items.MushroomBlock));
    }

    @Override
    protected void o_82_k() {
        VillagerTrades.v_4262_N[] avillagertrades$itrade;
        VillagerData villagerdata = this.c_2086_l();
        Int2ObjectMap<VillagerTrades.v_4262_N[]> int2objectmap = VillagerTrades.n_1700_B.get(villagerdata.J_1907_R());
        if (int2objectmap != null && !int2objectmap.isEmpty() && (avillagertrades$itrade = (VillagerTrades.v_4262_N[])int2objectmap.get(villagerdata.R_4764_Y())) != null) {
            MerchantOffers merchantoffers = this.J_1907_R();
            this.n_1700_B(merchantoffers, avillagertrades$itrade, 2);
        }
    }

    public void n_1700_B(e_3591_l p_242368_1_, L_2225_p p_242368_2_, long p_242368_3_) {
        if (!(p_242368_3_ >= this.k_2293_S && p_242368_3_ < this.k_2293_S + 1200L || p_242368_3_ >= p_242368_2_.k_2293_S && p_242368_3_ < p_242368_2_.k_2293_S + 1200L)) {
            this.C_2741_M.n_1700_B(p_242368_2_.C_2741_M, this.RealmsWorldOptions, 10);
            this.k_2293_S = p_242368_3_;
            p_242368_2_.k_2293_S = p_242368_3_;
            this.n_1700_B(p_242368_1_, p_242368_3_, 5);
        }
    }

    private void ModeSetting() {
        long i = this.O_508_d.X_933_l();
        if (this.q_2307_F == 0L) {
            this.q_2307_F = i;
        } else if (i >= this.q_2307_F + 24000L) {
            this.C_2741_M.n_1700_B();
            this.q_2307_F = i;
        }
    }

    public void n_1700_B(e_3591_l p_242367_1_, long p_242367_2_, int p_242367_4_) {
        D_2364_U irongolementity;
        I_4817_s axisalignedbb;
        List<L_2225_p> list;
        List list1;
        if (this.n_1700_B(p_242367_2_) && (list1 = (list = p_242367_1_.n_1700_B(L_2225_p.class, axisalignedbb = this.i_601_W().grow(10.0, 10.0, 10.0))).stream().filter(villager -> villager.n_1700_B(p_242367_2_)).limit(5L).collect(Collectors.toList())).size() >= p_242367_4_ && (irongolementity = this.G_564_y(p_242367_1_)) != null) {
            list.forEach(GolemSensor::J_1907_R);
        }
    }

    public boolean n_1700_B(long gameTime) {
        if (!this.J_1907_R(this.O_508_d.X_933_l())) {
            return false;
        }
        return !this.Y_776_s.n_1700_B(MemoryModuleType.t_4043_B);
    }

    @Nullable
    private D_2364_U G_564_y(e_3591_l p_213759_1_) {
        c_1514_x blockpos = this.b_2312_j();
        for (int i = 0; i < 10; ++i) {
            D_2364_U irongolementity;
            double d1;
            double d0 = p_213759_1_.w_1457_N.nextInt(16) - 8;
            c_1514_x blockpos1 = this.n_1700_B(blockpos, d0, d1 = (double)(p_213759_1_.w_1457_N.nextInt(16) - 8));
            if (blockpos1 == null || (irongolementity = t_5_h.v_4276_D.J_1907_R(p_213759_1_, null, null, null, blockpos1, a_3160_D.u_1723_Y, false, false)) == null) continue;
            if (irongolementity.n_1700_B((LevelAccessor)p_213759_1_, a_3160_D.u_1723_Y) && irongolementity.n_1700_B((T_1316_M)p_213759_1_)) {
                p_213759_1_.n_1700_B((N_4263_v)irongolementity);
                return irongolementity;
            }
            irongolementity.Ops();
        }
        return null;
    }

    @Nullable
    private c_1514_x n_1700_B(c_1514_x pos, double x, double z) {
        int i = 6;
        c_1514_x blockpos = pos.add(x, 6.0, z);
        K_4074_S blockstate = this.O_508_d.getBlockState(blockpos);
        for (int j = 6; j >= -6; --j) {
            c_1514_x blockpos1 = blockpos;
            K_4074_S blockstate1 = blockstate;
            blockpos = blockpos.down();
            blockstate = this.O_508_d.getBlockState(blockpos);
            if (!blockstate1.v_4262_N() && !blockstate1.R_4764_Y().n_1700_B() || !blockstate.R_4764_Y().u_1723_Y()) continue;
            return blockpos1;
        }
        return null;
    }

    @Override
    public void n_1700_B(ReputationEventType type, N_4263_v target) {
        if (type == ReputationEventType.n_1700_B) {
            this.C_2741_M.n_1700_B(target.w_2705_t(), Q_1649_w.G_564_y, 20);
            this.C_2741_M.n_1700_B(target.w_2705_t(), Q_1649_w.R_4764_Y, 25);
        } else if (type == ReputationEventType.P_1922_E) {
            this.C_2741_M.n_1700_B(target.w_2705_t(), Q_1649_w.P_1922_E, 2);
        } else if (type == ReputationEventType.R_4764_Y) {
            this.C_2741_M.n_1700_B(target.w_2705_t(), Q_1649_w.J_1907_R, 25);
        } else if (type == ReputationEventType.G_564_y) {
            this.C_2741_M.n_1700_B(target.w_2705_t(), Q_1649_w.n_1700_B, 25);
        }
    }

    @Override
    public int G_564_y() {
        return this.Z_875_P;
    }

    public void Y_601_j(int xpIn) {
        this.Z_875_P = xpIn;
    }

    private void MultiBooleanSetting() {
        this.ModuleManager();
        this.H_2857_Y = 0;
    }

    public H_3779_g D_3612_q() {
        return this.C_2741_M;
    }

    public void n_1700_B(Tag gossip) {
        this.C_2741_M.n_1700_B(new Dynamic((DynamicOps)l_4118_l.n_1700_B, (Object)gossip));
    }

    @Override
    protected void g_164_R() {
        super.g_164_R();
        DebugPackets.n_1700_B(this);
    }

    @Override
    public void P_1922_E(c_1514_x pos) {
        super.P_1922_E(pos);
        this.Y_776_s.n_1700_B(MemoryModuleType.x_607_J, Long.valueOf(this.O_508_d.X_933_l()));
        this.Y_776_s.J_1907_R(MemoryModuleType.P_4830_p);
        this.Y_776_s.J_1907_R(MemoryModuleType.Y_1740_V);
    }

    @Override
    public void t_2932_z() {
        super.t_2932_z();
        this.Y_776_s.n_1700_B(MemoryModuleType.e_4240_b, Long.valueOf(this.O_508_d.X_933_l()));
    }

    private boolean J_1907_R(long gameTime) {
        Optional<Long> optional = this.Y_776_s.R_4764_Y(MemoryModuleType.x_607_J);
        if (optional.isPresent()) {
            return gameTime - optional.get() < 24000L;
        }
        return false;
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }
}



