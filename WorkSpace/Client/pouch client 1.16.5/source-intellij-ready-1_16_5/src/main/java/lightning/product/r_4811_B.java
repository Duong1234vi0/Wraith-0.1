/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  javax.annotation.Nullable
 *  lombok.Generated
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.A_3244_K;
import lightning.product.CombatRules;
import lightning.product.A_4115_X;
import lightning.product.A_4388_s;
import lightning.product.FluidTags;
import lightning.product.LootContextParams;
import lightning.product.AttributeMap;
import lightning.product.B_4088_l;
import lightning.product.C_1375_J;
import lightning.product.C_1985_D;
import lightning.product.C_3615_s;
import lightning.product.C_4114_x;
import lightning.product.D_38_f;
import lightning.product.E_4668_a;
import lightning.product.F_1573_j;
import lightning.product.ClipContext;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_3700_V;
import lightning.product.HitResult;
import lightning.product.I_408_V;
import lightning.product.I_4817_s;
import lightning.product.I_685_r;
import lightning.product.J_2061_p;
import lightning.product.J_2868_p;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.L_1875_m;
import lightning.product.L_461_d;
import lightning.product.Attribute;
import lightning.product.N_3869_i;
import lightning.product.FoodProperties;
import lightning.product.N_4263_v;
import lightning.product.HoneyBlock;
import lightning.product.P_11_z;
import lightning.product.P_4639_N;
import lightning.product.ClientboundAddMobPacket;
import lightning.product.NonNullList;
import lightning.product.R_1815_U;
import lightning.product.R_2515_i;
import lightning.product.R_4053_F;
import lightning.product.MobEffectUtil;
import lightning.product.S_4035_N;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_1880_G;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.V_772_m;
import lightning.product.SoundEvent;
import lightning.product.X_426_i;
import lightning.product.X_508_u;
import lightning.product.ClientboundSetEquipmentPacket;
import lightning.product.BlockUtil;
import lightning.product.Tag;
import lightning.product.FrostWalkerEnchantment;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.PlayerTeam;
import lightning.product.FluidState;
import lightning.product.SoundType;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.e_837_t;
import lightning.product.f_1402_I;
import lightning.product.f_2785_f;
import lightning.product.g_2336_b;
import lightning.product.MobType;
import lightning.product.g_422_i;
import lightning.product.ElytraItem;
import lightning.product.h_256_u;
import lightning.product.h_384_L;
import lightning.product.EntityDataSerializers;
import lightning.product.k_2610_C;
import lightning.product.k_4231_L;
import lightning.product.l_3609_d;
import lightning.product.l_4118_l;
import lightning.product.m_3216_j;
import lightning.product.PathfinderMob;
import lightning.product.n_1494_c;
import lightning.product.n_4637_L;
import lightning.product.o_3283_D;
import lightning.product.BlockTags;
import lightning.product.p_4985_U;
import lightning.product.q_1613_l;
import lightning.product.q_1704_m;
import lightning.product.q_2335_j;
import lightning.product.q_2896_o;
import lightning.product.q_3092_O;
import lightning.product.Items;
import lightning.product.r_109_r;
import lightning.product.s_1415_m;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.v_887_r;
import lightning.product.x_1688_C;
import lightning.product.x_2838_H;
import lightning.product.x_937_q;
import lightning.product.EntityAnchorArgument;
import lombok.Generated;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import org.apache.logging.log4j.Logger;

public abstract class r_4811_B
extends N_4263_v {
    private static final UUID n_1700_B = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
    private static final UUID J_1907_R = UUID.fromString("87f46a96-686f-4796-b035-22e16ee9e038");
    private static final U_1880_G R_4764_Y = new U_1880_G(n_1700_B, "Sprinting speed boost", (double)0.3f, U_1880_G.n_1700_B.R_4764_Y);
    protected static final h_256_u<Byte> W_3464_O = C_4114_x.n_1700_B(r_4811_B.class, EntityDataSerializers.n_1700_B);
    private static final h_256_u<Float> G_564_y = C_4114_x.n_1700_B(r_4811_B.class, EntityDataSerializers.R_4764_Y);
    private static final h_256_u<Integer> P_1922_E = C_4114_x.n_1700_B(r_4811_B.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Boolean> u_1723_Y = C_4114_x.n_1700_B(r_4811_B.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Integer> v_4262_N = C_4114_x.n_1700_B(r_4811_B.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Integer> w_1484_f = C_4114_x.n_1700_B(r_4811_B.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Optional<c_1514_x>> t_148_a = C_4114_x.n_1700_B(r_4811_B.class, EntityDataSerializers.P_4830_p);
    protected static final R_1815_U RealmsConfirmScreen = R_1815_U.R_4764_Y(0.2f, 0.2f);
    private final AttributeMap s_956_w;
    private final x_937_q u_2550_I = new x_937_q(this);
    private final Map<g_422_i, k_2610_C> M_588_G = Maps.newHashMap();
    private final NonNullList<Z_1993_T> P_4830_p = NonNullList.n_1700_B(2, Z_1993_T.J_1907_R);
    private final NonNullList<Z_1993_T> h_1847_R = NonNullList.n_1700_B(4, Z_1993_T.J_1907_R);
    public boolean RealmsCreateRealmScreen;
    public x_1688_C C_290_v;
    public int w_728_N;
    public int J_4256_G;
    public int RealmsLongConfirmationScreen;
    public int RealmsLongRunningMcoTaskScreen;
    public int i_2993_w;
    public float RealmsParentalConsentScreen;
    public int O_2151_c;
    public float s_1671_u;
    public float RealmsResetNormalWorldScreen;
    protected int C_3538_G;
    public float A_3959_N;
    public float G_424_k;
    public float RealmsSettingsScreen;
    public final int f_1043_S = 20;
    public final float F_4247_a;
    public final float J_739_q;
    public float C_1162_e;
    public float D_4361_a;
    public float f_3449_S;
    public float u_55_V;
    public float JsonUtils;
    public float RealmsPersistence;
    public float y_2772_m = 0.02f;
    @Nullable
    protected a_3913_L H_1883_T;
    protected int d_4007_L;
    protected boolean TextRenderingUtils;
    protected int UploadTokenCache;
    protected float U_1341_G;
    protected float ClientBootstrap;
    protected float o_2341_D;
    protected float C_1269_X;
    protected float x_612_B;
    protected int t_1446_I;
    protected float j_306_t;
    protected boolean F_3572_x;
    public float L_1362_X;
    public float P_5000_x;
    public float L_4248_u;
    protected int O_1309_Q;
    protected double O_2934_T;
    protected double l_4088_R;
    protected double Z_735_d;
    protected double P_925_e;
    protected double X_4895_T;
    protected double L_103_L;
    protected int n_3197_X;
    private boolean Q_4569_t = true;
    @Nullable
    private r_4811_B M_182_A;
    private int t_1786_h;
    private r_4811_B multiplayerClientSuggestionProvider;
    private int w_1457_N;
    private float Y_601_j;
    public int P_2947_S;
    private float Y_259_p;
    protected Z_1993_T O_4761_U = Z_1993_T.J_1907_R;
    protected int w_2705_t;
    protected int F_518_D;
    private c_1514_x Q_2552_b;
    private Optional<c_1514_x> C_2741_M = Optional.empty();
    private P_11_z k_2293_S;
    private long q_2307_F;
    protected int L_3570_A;
    private float Z_875_P;
    private float c_3005_b;
    protected E_4668_a<?> Y_776_s;
    public double S_3139_t;
    public double k_2302_P;
    public double t_3452_g;
    public double V_118_c;
    public double I_1407_m;
    public double o_2767_H;
    public double d_2545_n;
    public double x_92_N;
    public double i_601_W;

    protected r_4811_B(t_5_h<? extends r_4811_B> type, b_4507_u worldIn) {
        super(type, worldIn);
        this.s_956_w = new AttributeMap(m_3216_j.n_1700_B(type));
        this.t_1786_h(this.L_1733_J());
        this.s_2632_s = true;
        this.J_739_q = (float)((Math.random() + 1.0) * (double)0.01f);
        this.t_4219_U();
        this.F_4247_a = (float)Math.random() * 12398.0f;
        this.f_3449_S = this.p_178_J = (float)(Math.random() * 6.2831854820251465);
        this.RealmsServerPing = 0.6f;
        l_4118_l nbtdynamicops = l_4118_l.n_1700_B;
        this.Y_776_s = this.n_1700_B(new Dynamic((DynamicOps)nbtdynamicops, (Object)((Tag)nbtdynamicops.createMap((Map)ImmutableMap.of((Object)nbtdynamicops.n_1700_B("memories"), (Object)((Tag)nbtdynamicops.emptyMap()))))));
    }

    public E_4668_a<?> y_1945_D() {
        return this.Y_776_s;
    }

    protected E_4668_a.n_1700_B<?> U_532_X() {
        return E_4668_a.n_1700_B(ImmutableList.of(), ImmutableList.of());
    }

    protected E_4668_a<?> n_1700_B(Dynamic<?> dynamicIn) {
        return this.U_532_X().n_1700_B(dynamicIn);
    }

    @Override
    public void e_1992_r() {
        this.n_1700_B(P_11_z.P_4830_p, Float.MAX_VALUE);
    }

    public boolean n_1700_B(t_5_h<?> typeIn) {
        return true;
    }

    @Override
    protected void a_() {
        this.l_4537_E.n_1700_B(W_3464_O, (byte)0);
        this.l_4537_E.n_1700_B(P_1922_E, 0);
        this.l_4537_E.n_1700_B(u_1723_Y, false);
        this.l_4537_E.n_1700_B(v_4262_N, 0);
        this.l_4537_E.n_1700_B(w_1484_f, 0);
        this.l_4537_E.n_1700_B(G_564_y, Float.valueOf(1.0f));
        this.l_4537_E.n_1700_B(t_148_a, Optional.empty());
    }

    public static s_1415_m.n_1700_B P_4639_N() {
        return s_1415_m.n_1700_B().n_1700_B(Attributes.n_1700_B).n_1700_B(Attributes.R_4764_Y).n_1700_B(Attributes.G_564_y).n_1700_B(Attributes.t_148_a).n_1700_B(Attributes.s_956_w);
    }

    @Override
    protected void n_1700_B(double y, boolean onGroundIn, K_4074_S state, c_1514_x pos) {
        if (!this.RowButton()) {
            this.M_2677_i();
        }
        if (!this.O_508_d.Y_259_p && onGroundIn && this.U_1241_n > 0.0f) {
            this.Q_2467_v();
            this.m_2262_U();
        }
        if (!this.O_508_d.Y_259_p && this.U_1241_n > 3.0f && onGroundIn) {
            float f = u_530_F.u_1723_Y(this.U_1241_n - 3.0f);
            if (!state.v_4262_N()) {
                double d0 = Math.min((double)(0.2f + f / 15.0f), 2.5);
                int i = (int)(150.0 * d0);
                ((e_3591_l)this.O_508_d).n_1700_B(new X_426_i(ParticleTypes.G_564_y, state), this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), i, 0.0, 0.0, 0.0, 0.15f);
            }
        }
        super.n_1700_B(y, onGroundIn, state, pos);
    }

    public e_2866_D i_4434_b() {
        return e_2866_D.n_1700_B((float)this.X_4895_T, (float)this.P_925_e);
    }

    public boolean P_328_a() {
        return this.F_2860_q() == MobType.J_1907_R;
    }

    public float u_1723_Y(float partialTicks) {
        return u_530_F.v_4262_N(partialTicks, this.c_3005_b, this.Z_875_P);
    }

    @Override
    public void V_1446_Y() {
        boolean flag1;
        this.s_1671_u = this.RealmsResetNormalWorldScreen;
        if (this.S_4022_R) {
            this.f_2403_E().ifPresent(this::n_1700_B);
        }
        if (this.u_2550_I()) {
            this.l_4627_h();
        }
        super.V_1446_Y();
        boolean flag = this instanceof a_3913_L;
        if (this.RealmsLongRunningMcoTaskScreen()) {
            double d1;
            double d0;
            if (this.i_2993_w()) {
                this.n_1700_B(P_11_z.u_1723_Y, 1.0f);
                if (this instanceof V_772_m) {
                    A_4115_X.n_1700_B(new v_887_r(v_887_r.n_1700_B.G_564_y));
                }
            } else if (flag && !this.O_508_d.H_2857_Y().n_1700_B(this.i_601_W()) && (d0 = this.O_508_d.H_2857_Y().n_1700_B(this) + this.O_508_d.H_2857_Y().h_1847_R()) < 0.0 && (d1 = this.O_508_d.H_2857_Y().Q_4569_t()) > 0.0) {
                this.n_1700_B(P_11_z.u_1723_Y, (float)Math.max(1, u_530_F.R_4764_Y(-d0 * d1)));
            }
        }
        if (this.r_3651_U() || this.O_508_d.Y_259_p) {
            this.RealmsServerPing();
        }
        boolean bl = flag1 = flag && ((a_3913_L)this).C_415_h.n_1700_B;
        if (this.RealmsLongRunningMcoTaskScreen()) {
            c_1514_x blockpos;
            if (((N_4263_v)this).n_1700_B(FluidTags.J_1907_R) && !this.O_508_d.getBlockState(new c_1514_x(this.O_3598_v(), this.X_2048_Y(), this.l_2647_k())).n_1700_B(a_3742_W.S_4325_V)) {
                if (!(this.P_328_a() || MobEffectUtil.R_4764_Y(this) || flag1)) {
                    this.w_1484_f(this.R_4764_Y(this.L_4248_u()));
                    if (this.L_4248_u() == -20) {
                        this.w_1484_f(0);
                        e_2866_D vector3d = this.I_4348_c();
                        for (int i = 0; i < 8; ++i) {
                            double d2 = this.RealmsWorldOptions.nextDouble() - this.RealmsWorldOptions.nextDouble();
                            double d3 = this.RealmsWorldOptions.nextDouble() - this.RealmsWorldOptions.nextDouble();
                            double d4 = this.RealmsWorldOptions.nextDouble() - this.RealmsWorldOptions.nextDouble();
                            this.O_508_d.n_1700_B(ParticleTypes.P_1922_E, this.O_3598_v() + d2, this.X_2960_b() + d3, this.l_2647_k() + d4, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
                        }
                        this.n_1700_B(P_11_z.w_1484_f, 2.0f);
                    }
                }
                if (!this.O_508_d.Y_259_p && this.y_2772_m() && this.l_3609_d() != null && !this.l_3609_d().d_4007_L()) {
                    this.A_3959_N();
                }
            } else if (this.L_4248_u() < this.P_5000_x()) {
                this.w_1484_f(this.s_956_w(this.L_4248_u()));
            }
            if (!this.O_508_d.Y_259_p && !Objects.equal((Object)this.Q_2552_b, (Object)(blockpos = this.b_2312_j()))) {
                this.Q_2552_b = blockpos;
                this.R_4764_Y(blockpos);
            }
        }
        if (this.RealmsLongRunningMcoTaskScreen() && this.j_2266_I()) {
            this.RealmsServerPing();
        }
        if (this.RealmsLongRunningMcoTaskScreen > 0) {
            --this.RealmsLongRunningMcoTaskScreen;
        }
        if (this.F_1410_V > 0 && !(this instanceof B_4088_l)) {
            --this.F_1410_V;
        }
        if (this.Z_2812_M()) {
            this.T_2971_J();
        }
        if (this.d_4007_L > 0) {
            --this.d_4007_L;
        } else {
            this.H_1883_T = null;
        }
        if (this.multiplayerClientSuggestionProvider != null && !this.multiplayerClientSuggestionProvider.RealmsLongRunningMcoTaskScreen()) {
            this.multiplayerClientSuggestionProvider = null;
        }
        if (this.M_182_A != null) {
            if (!this.M_182_A.RealmsLongRunningMcoTaskScreen()) {
                this.J_1907_R((r_4811_B)null);
            } else if (this.RealmsWorldResetDto - this.t_1786_h > 100) {
                this.J_1907_R((r_4811_B)null);
            }
        }
        this.z_2025_Z();
        this.C_1269_X = this.o_2341_D;
        this.D_4361_a = this.C_1162_e;
        this.JsonUtils = this.f_3449_S;
        this.j_276_v = this.p_178_J;
        this.UploadStatus = this.f_4016_n;
        this.RealmsPersistence = this.u_55_V;
    }

    public boolean u_2550_I() {
        return this.RealmsWorldResetDto % 5 == 0 && this.I_4348_c().J_1907_R != 0.0 && this.I_4348_c().G_564_y != 0.0 && !this.d_2461_k() && K_4096_w.s_956_w(this) && this.K_3372_t();
    }

    protected void l_4627_h() {
        e_2866_D vector3d = this.I_4348_c();
        this.O_508_d.n_1700_B(ParticleTypes.A_4115_X, this.O_3598_v() + (this.RealmsWorldOptions.nextDouble() - 0.5) * (double)this.C_415_h(), this.X_2960_b() + 0.1, this.l_2647_k() + (this.RealmsWorldOptions.nextDouble() - 0.5) * (double)this.C_415_h(), vector3d.J_1907_R * -0.2, 0.1, vector3d.G_564_y * -0.2);
        float f = this.RealmsWorldOptions.nextFloat() * 0.4f + this.RealmsWorldOptions.nextFloat() > 0.9f ? 0.6f : 0.0f;
        this.n_1700_B(SoundEvents.AttachedStemBlock, f, 0.6f + this.RealmsWorldOptions.nextFloat() * 0.4f);
    }

    protected boolean K_3372_t() {
        return this.O_508_d.getBlockState(this.H_1083_k()).n_1700_B(BlockTags.k_3961_g);
    }

    @Override
    protected float RegionPingResult() {
        return this.K_3372_t() && K_4096_w.n_1700_B(Enchantments.M_588_G, this) > 0 ? 1.0f : super.RegionPingResult();
    }

    protected boolean J_1907_R(K_4074_S p_230295_1_) {
        return !p_230295_1_.v_4262_N() || this.k_578_l();
    }

    protected void Q_2467_v() {
        A_4388_s modifiableattributeinstance = this.n_1700_B(Attributes.G_564_y);
        if (modifiableattributeinstance != null && modifiableattributeinstance.n_1700_B(J_1907_R) != null) {
            modifiableattributeinstance.J_1907_R(J_1907_R);
        }
    }

    protected void m_2262_U() {
        int i;
        if (!this.g_4106_L().v_4262_N() && (i = K_4096_w.n_1700_B(Enchantments.M_588_G, this)) > 0 && this.K_3372_t()) {
            A_4388_s modifiableattributeinstance = this.n_1700_B(Attributes.G_564_y);
            if (modifiableattributeinstance == null) {
                return;
            }
            modifiableattributeinstance.J_1907_R(new U_1880_G(J_1907_R, "Soul speed boost", (double)(0.03f * (1.0f + (float)i * 0.35f)), U_1880_G.n_1700_B.n_1700_B));
            if (this.M_3508_C().nextFloat() < 0.04f) {
                Z_1993_T itemstack = this.J_1907_R(e_1174_E.R_4764_Y);
                itemstack.n_1700_B(1, this, (T p_233654_0_) -> p_233654_0_.R_4764_Y(e_1174_E.R_4764_Y));
            }
        }
    }

    protected void R_4764_Y(c_1514_x pos) {
        int i = K_4096_w.n_1700_B(Enchantments.s_956_w, this);
        if (i > 0) {
            FrostWalkerEnchantment.n_1700_B(this, this.O_508_d, pos, i);
        }
        if (this.J_1907_R(this.g_4106_L())) {
            this.Q_2467_v();
        }
        this.m_2262_U();
    }

    public boolean d_() {
        return false;
    }

    public float S_4258_d() {
        return this.d_() ? 0.5f : 1.0f;
    }

    protected boolean m_891_U() {
        return true;
    }

    @Override
    public boolean d_4007_L() {
        return false;
    }

    protected void T_2971_J() {
        if (this.O_2151_c < 1 && this == MinecraftClient.A_4115_X().Y_259_p) {
            A_4115_X.n_1700_B(new l_3609_d());
        }
        ++this.O_2151_c;
        if (this.O_2151_c == 20) {
            this.Ops();
            for (int i = 0; i < 20; ++i) {
                double d0 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                double d1 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                double d2 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                this.O_508_d.n_1700_B(ParticleTypes.z_4693_k, this.G_564_y(1.0), this.M_766_z(), this.v_4262_N(1.0), d0, d1, d2);
            }
        }
    }

    protected boolean Q_3581_n() {
        return !this.d_();
    }

    protected boolean I_685_r() {
        return !this.d_();
    }

    protected int R_4764_Y(int air) {
        int i = K_4096_w.G_564_y(this);
        return i > 0 && this.RealmsWorldOptions.nextInt(i + 1) > 0 ? air : air - 1;
    }

    protected int s_956_w(int currentAir) {
        return Math.min(currentAir + 4, this.P_5000_x());
    }

    protected int R_4764_Y(a_3913_L player) {
        return 0;
    }

    protected boolean h_3270_j() {
        return false;
    }

    public Random M_3508_C() {
        return this.RealmsWorldOptions;
    }

    @Nullable
    public r_4811_B q_817_e() {
        return this.M_182_A;
    }

    public int r_260_T() {
        return this.t_1786_h;
    }

    public void J_1907_R(@Nullable a_3913_L p_230246_1_) {
        this.H_1883_T = p_230246_1_;
        this.d_4007_L = this.RealmsWorldResetDto;
    }

    public void J_1907_R(@Nullable r_4811_B livingBase) {
        this.M_182_A = livingBase;
        this.t_1786_h = this.RealmsWorldResetDto;
    }

    @Nullable
    public r_4811_B Q_2753_H() {
        return this.multiplayerClientSuggestionProvider;
    }

    public int Y_2080_q() {
        return this.w_1457_N;
    }

    public void C_2741_M(N_4263_v entityIn) {
        this.multiplayerClientSuggestionProvider = entityIn instanceof r_4811_B ? (r_4811_B)entityIn : null;
        this.w_1457_N = this.RealmsWorldResetDto;
    }

    public int g_4560_H() {
        return this.UploadTokenCache;
    }

    public void u_2550_I(int idleTimeIn) {
        this.UploadTokenCache = idleTimeIn;
    }

    protected void J_1907_R(Z_1993_T stack) {
        if (!stack.n_1700_B()) {
            SoundEvent soundevent = SoundEvents.G_624_v;
            q_1613_l item = stack.J_1907_R();
            if (item instanceof R_2515_i) {
                soundevent = ((R_2515_i)item).P_1922_E().J_1907_R();
            } else if (item == Items.NyliumBlock) {
                soundevent = SoundEvents.d_2461_k;
            }
            this.n_1700_B(soundevent, 1.0f, 1.0f);
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        compound.n_1700_B("Health", this.g_46_E());
        compound.n_1700_B("HurtTime", (short)this.RealmsLongRunningMcoTaskScreen);
        compound.J_1907_R("HurtByTimestamp", this.t_1786_h);
        compound.n_1700_B("DeathTime", (short)this.O_2151_c);
        compound.n_1700_B("AbsorptionAmount", this.U_3823_u());
        compound.n_1700_B("Attributes", this.B_1146_q().R_4764_Y());
        if (!this.M_588_G.isEmpty()) {
            q_2896_o listnbt = new q_2896_o();
            for (k_2610_C effectinstance : this.M_588_G.values()) {
                listnbt.add(effectinstance.n_1700_B(new U_2912_j()));
            }
            compound.n_1700_B("ActiveEffects", listnbt);
        }
        compound.n_1700_B("FallFlying", this.k_578_l());
        this.f_2403_E().ifPresent(p_213338_1_ -> {
            compound.J_1907_R("SleepingX", p_213338_1_.getX());
            compound.J_1907_R("SleepingY", p_213338_1_.getY());
            compound.J_1907_R("SleepingZ", p_213338_1_.getZ());
        });
        DataResult<Tag> dataresult = this.Y_776_s.n_1700_B(l_4118_l.n_1700_B);
        dataresult.resultOrPartial(arg_0 -> ((Logger)D_4792_h).error(arg_0)).ifPresent(p_233636_1_ -> compound.n_1700_B("Brain", (Tag)p_233636_1_));
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        this.Y_259_p(compound.s_956_w("AbsorptionAmount"));
        if (compound.R_4764_Y("Attributes", 9) && this.O_508_d != null && !this.O_508_d.Y_259_p) {
            this.B_1146_q().n_1700_B(compound.G_564_y("Attributes", 10));
        }
        if (compound.R_4764_Y("ActiveEffects", 9)) {
            q_2896_o listnbt = compound.G_564_y("ActiveEffects", 10);
            for (int i = 0; i < listnbt.size(); ++i) {
                U_2912_j compoundnbt = listnbt.n_1700_B(i);
                k_2610_C effectinstance = k_2610_C.J_1907_R(compoundnbt);
                if (effectinstance == null) continue;
                this.M_588_G.put(effectinstance.n_1700_B(), effectinstance);
            }
        }
        if (compound.R_4764_Y("Health", 99)) {
            this.t_1786_h(compound.s_956_w("Health"));
        }
        this.RealmsLongRunningMcoTaskScreen = compound.v_4262_N("HurtTime");
        this.O_2151_c = compound.v_4262_N("DeathTime");
        this.t_1786_h = compound.w_1484_f("HurtByTimestamp");
        if (compound.R_4764_Y("Team", 8)) {
            boolean flag;
            String s = compound.M_588_G("Team");
            PlayerTeam scoreplayerteam = this.O_508_d.Q_4569_t().P_1922_E(s);
            boolean bl = flag = scoreplayerteam != null && this.O_508_d.Q_4569_t().n_1700_B(this.F_518_D(), scoreplayerteam);
            if (!flag) {
                D_4792_h.warn("Unable to add mob to team \"{}\" (that team probably doesn't exist)", (Object)s);
            }
        }
        if (compound.t_1786_h("FallFlying")) {
            this.J_1907_R(7, true);
        }
        if (compound.R_4764_Y("SleepingX", 99) && compound.R_4764_Y("SleepingY", 99) && compound.R_4764_Y("SleepingZ", 99)) {
            c_1514_x blockpos = new c_1514_x(compound.w_1484_f("SleepingX"), compound.w_1484_f("SleepingY"), compound.w_1484_f("SleepingZ"));
            this.G_564_y(blockpos);
            this.l_4537_E.J_1907_R(RealmsDefaultUncaughtExceptionHandler, I_1170_F.R_4764_Y);
            if (!this.S_4022_R) {
                this.n_1700_B(blockpos);
            }
        }
        if (compound.R_4764_Y("Brain", 10)) {
            this.Y_776_s = this.n_1700_B(new Dynamic((DynamicOps)l_4118_l.n_1700_B, (Object)compound.R_4764_Y("Brain")));
        }
    }

    protected void z_2025_Z() {
        Iterator<g_422_i> iterator = this.M_588_G.keySet().iterator();
        try {
            while (iterator.hasNext()) {
                g_422_i effect = iterator.next();
                k_2610_C effectinstance = this.M_588_G.get(effect);
                if (!effectinstance.n_1700_B(this, () -> this.n_1700_B(effectinstance, true))) {
                    if (this.O_508_d.Y_259_p) continue;
                    iterator.remove();
                    this.P_1922_E(effectinstance);
                    continue;
                }
                if (effectinstance.J_1907_R() % 600 != 0) continue;
                this.n_1700_B(effectinstance, false);
            }
        }
        catch (ConcurrentModificationException effect) {
            // empty catch block
        }
        if (this.Q_4569_t) {
            if (!this.O_508_d.Y_259_p) {
                this.f_691_R();
            }
            this.Q_4569_t = false;
        }
        int i = this.l_4537_E.n_1700_B(P_1922_E);
        boolean flag1 = this.l_4537_E.n_1700_B(u_1723_Y);
        if (i > 0) {
            boolean flag = this.F_3572_x() ? this.RealmsWorldOptions.nextInt(15) == 0 : this.RealmsWorldOptions.nextBoolean();
            if (flag1) {
                flag &= this.RealmsWorldOptions.nextInt(5) == 0;
            }
            if (flag && i > 0) {
                double d0 = (double)(i >> 16 & 0xFF) / 255.0;
                double d1 = (double)(i >> 8 & 0xFF) / 255.0;
                double d2 = (double)(i >> 0 & 0xFF) / 255.0;
                this.O_508_d.n_1700_B(flag1 ? ParticleTypes.n_1700_B : ParticleTypes.Y_259_p, this.G_564_y(0.5), this.M_766_z(), this.v_4262_N(0.5), d0, d1, d2);
            }
        }
    }

    protected void f_691_R() {
        if (this.M_588_G.isEmpty()) {
            this.I_4481_g();
            this.M_588_G(false);
        } else {
            Collection<k_2610_C> collection = this.M_588_G.values();
            this.l_4537_E.J_1907_R(u_1723_Y, r_4811_B.n_1700_B(collection));
            this.l_4537_E.J_1907_R(P_1922_E, L_1875_m.n_1700_B(collection));
            this.M_588_G(this.J_1907_R(MobEffects.h_1847_R));
        }
    }

    public double k_2293_S(@Nullable N_4263_v lookingEntity) {
        double d0 = 1.0;
        if (this.U_1341_G()) {
            d0 *= 0.8;
        }
        if (this.F_3572_x()) {
            float f = this.h_1015_G();
            if (f < 0.1f) {
                f = 0.1f;
            }
            d0 *= 0.7 * (double)f;
        }
        if (lookingEntity != null) {
            Z_1993_T itemstack = this.J_1907_R(e_1174_E.u_1723_Y);
            q_1613_l item = itemstack.J_1907_R();
            t_5_h<?> entitytype = lookingEntity.f_4016_n();
            if (entitytype == t_5_h.V_1446_Y && item == Items.DragonEggBlock || entitytype == t_5_h.R_3077_Z && item == Items.EndGatewayBlock || entitytype == t_5_h.P_4830_p && item == Items.EndPortalBlock) {
                d0 *= 0.5;
            }
        }
        return d0;
    }

    public boolean n_1700_B(r_4811_B target) {
        return true;
    }

    public boolean n_1700_B(r_4811_B livingentityIn, TargetingConditions predicateIn) {
        return predicateIn.n_1700_B(this, livingentityIn);
    }

    public static boolean n_1700_B(Collection<k_2610_C> potionEffects) {
        for (k_2610_C effectinstance : potionEffects) {
            if (effectinstance.G_564_y()) continue;
            return false;
        }
        return true;
    }

    protected void I_4481_g() {
        this.l_4537_E.J_1907_R(u_1723_Y, false);
        this.l_4537_E.J_1907_R(P_1922_E, 0);
    }

    public boolean g_1734_y() {
        if (this.O_508_d.Y_259_p) {
            return false;
        }
        Iterator<k_2610_C> iterator = this.M_588_G.values().iterator();
        boolean flag = false;
        while (iterator.hasNext()) {
            this.P_1922_E(iterator.next());
            iterator.remove();
            flag = true;
        }
        return flag;
    }

    public Collection<k_2610_C> I_3457_f() {
        return this.M_588_G.values();
    }

    public Map<g_422_i, k_2610_C> b_3528_u() {
        return this.M_588_G;
    }

    public boolean J_1907_R(g_422_i potionIn) {
        return this.M_588_G.containsKey(potionIn);
    }

    @Nullable
    public k_2610_C R_4764_Y(g_422_i potionIn) {
        return this.M_588_G.get(potionIn);
    }

    public boolean n_1700_B(k_2610_C effectInstanceIn) {
        if (!this.J_1907_R(effectInstanceIn)) {
            return false;
        }
        k_2610_C effectinstance = this.M_588_G.get(effectInstanceIn.n_1700_B());
        if (effectinstance == null) {
            this.M_588_G.put(effectInstanceIn.n_1700_B(), effectInstanceIn);
            this.G_564_y(effectInstanceIn);
            return true;
        }
        if (effectinstance.J_1907_R(effectInstanceIn)) {
            this.n_1700_B(effectinstance, true);
            return true;
        }
        return false;
    }

    public boolean J_1907_R(k_2610_C potioneffectIn) {
        g_422_i effect;
        return this.F_2860_q() != MobType.J_1907_R || (effect = potioneffectIn.n_1700_B()) != MobEffects.s_956_w && effect != MobEffects.w_1457_N;
    }

    public void R_4764_Y(k_2610_C p_233646_1_) {
        if (this.J_1907_R(p_233646_1_)) {
            k_2610_C effectinstance = this.M_588_G.put(p_233646_1_.n_1700_B(), p_233646_1_);
            if (effectinstance == null) {
                this.G_564_y(p_233646_1_);
            } else {
                this.n_1700_B(p_233646_1_, true);
            }
        }
    }

    public boolean I_4477_R() {
        return this.F_2860_q() == MobType.J_1907_R;
    }

    @Nullable
    public k_2610_C n_1700_B(@Nullable g_422_i potioneffectin) {
        return this.M_588_G.remove(potioneffectin);
    }

    public boolean G_564_y(g_422_i effectIn) {
        k_2610_C effectinstance = this.n_1700_B(effectIn);
        if (effectinstance != null) {
            this.P_1922_E(effectinstance);
            return true;
        }
        return false;
    }

    protected void G_564_y(k_2610_C id) {
        this.Q_4569_t = true;
        if (!this.O_508_d.Y_259_p) {
            id.n_1700_B().J_1907_R(this, this.B_1146_q(), id.R_4764_Y());
        }
    }

    protected void n_1700_B(k_2610_C id, boolean reapply) {
        this.Q_4569_t = true;
        if (reapply && !this.O_508_d.Y_259_p) {
            g_422_i effect = id.n_1700_B();
            effect.n_1700_B(this, this.B_1146_q(), id.R_4764_Y());
            effect.J_1907_R(this, this.B_1146_q(), id.R_4764_Y());
        }
    }

    protected void P_1922_E(k_2610_C effect) {
        this.Q_4569_t = true;
        if (!this.O_508_d.Y_259_p) {
            effect.n_1700_B().n_1700_B(this, this.B_1146_q(), effect.R_4764_Y());
        }
    }

    public void n_1700_B(float healAmount) {
        float f = this.g_46_E();
        if (f > 0.0f) {
            this.t_1786_h(f + healAmount);
        }
    }

    public float g_46_E() {
        return this.l_4537_E.n_1700_B(G_564_y).floatValue();
    }

    public void t_1786_h(float health) {
        this.l_4537_E.J_1907_R(G_564_y, Float.valueOf(u_530_F.n_1700_B(health, 0.0f, this.L_1733_J())));
    }

    public boolean Z_2812_M() {
        return this.g_46_E() <= 0.0f;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        boolean flag2;
        if (this.n_1700_B(source)) {
            return false;
        }
        if (this.O_508_d.Y_259_p) {
            return false;
        }
        if (this.Z_2812_M()) {
            return false;
        }
        if (source.M_182_A() && this.J_1907_R(MobEffects.M_588_G)) {
            return false;
        }
        if (this.z_2372_L() && !this.O_508_d.Y_259_p) {
            this.t_2932_z();
        }
        this.UploadTokenCache = 0;
        float f = amount;
        if (!(source != P_11_z.t_1786_h && source != P_11_z.multiplayerClientSuggestionProvider || this.J_1907_R(e_1174_E.u_1723_Y).n_1700_B())) {
            this.J_1907_R(e_1174_E.u_1723_Y).n_1700_B((int)(amount * 4.0f + this.RealmsWorldOptions.nextFloat() * amount * 2.0f), this, (T p_233653_0_) -> p_233653_0_.R_4764_Y(e_1174_E.u_1723_Y));
            amount *= 0.75f;
        }
        boolean flag = false;
        float f1 = 0.0f;
        if (amount > 0.0f && this.v_4262_N(source)) {
            N_4263_v entity;
            this.multiplayerClientSuggestionProvider(amount);
            f1 = amount;
            amount = 0.0f;
            if (!source.J_1907_R() && (entity = source.s_956_w()) instanceof r_4811_B) {
                this.G_564_y((r_4811_B)entity);
            }
            flag = true;
        }
        this.G_424_k = 1.5f;
        boolean flag1 = true;
        if ((float)this.F_1410_V > 10.0f) {
            if (amount <= this.j_306_t) {
                return false;
            }
            this.J_1907_R(source, amount - this.j_306_t);
            this.j_306_t = amount;
            flag1 = false;
        } else {
            this.j_306_t = amount;
            this.F_1410_V = 20;
            this.J_1907_R(source, amount);
            this.RealmsLongRunningMcoTaskScreen = this.i_2993_w = 10;
        }
        this.RealmsParentalConsentScreen = 0.0f;
        N_4263_v entity1 = source.u_2550_I();
        if (entity1 != null) {
            q_2335_j wolfentity;
            if (entity1 instanceof r_4811_B) {
                this.J_1907_R((r_4811_B)entity1);
            }
            if (entity1 instanceof a_3913_L) {
                this.d_4007_L = 100;
                this.H_1883_T = (a_3913_L)entity1;
            } else if (entity1 instanceof q_2335_j && (wolfentity = (q_2335_j)entity1).U_3758_B()) {
                this.d_4007_L = 100;
                r_4811_B livingentity = wolfentity.A_1306_N();
                this.H_1883_T = livingentity != null && livingentity.f_4016_n() == t_5_h.g_4106_L ? (a_3913_L)livingentity : null;
            }
        }
        if (flag1) {
            if (flag) {
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)29);
            } else if (source instanceof f_2785_f && ((f_2785_f)source).q_2307_F()) {
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)33);
            } else {
                int b0 = source == P_11_z.w_1484_f ? 36 : (source.M_182_A() ? 37 : (source == P_11_z.Y_259_p ? 44 : 2));
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)b0);
            }
            if (source != P_11_z.w_1484_f && (!flag || amount > 0.0f)) {
                this.RealmsCreateRealmScreen();
            }
            if (entity1 != null) {
                double d1 = entity1.O_3598_v() - this.O_3598_v();
                double d0 = entity1.l_2647_k() - this.l_2647_k();
                while (d1 * d1 + d0 * d0 < 1.0E-4) {
                    d1 = (Math.random() - Math.random()) * 0.01;
                    d0 = (Math.random() - Math.random()) * 0.01;
                }
                this.RealmsParentalConsentScreen = (float)(u_530_F.G_564_y(d0, d1) * 57.2957763671875 - (double)this.p_178_J);
                this.n_1700_B(0.4f, d1, d0);
            } else {
                this.RealmsParentalConsentScreen = (int)(Math.random() * 2.0) * 180;
            }
        }
        if (this.Z_2812_M()) {
            if (!this.u_1723_Y(source)) {
                SoundEvent soundevent = this.u_796_y();
                if (flag1 && soundevent != null) {
                    this.n_1700_B(soundevent, this.d_4500_Q(), this.O_2761_o());
                }
                this.R_4764_Y(source);
            }
        } else if (flag1) {
            this.J_1907_R(source);
        }
        boolean bl = flag2 = !flag || amount > 0.0f;
        if (flag2) {
            this.k_2293_S = source;
            this.q_2307_F = this.O_508_d.X_933_l();
        }
        if (this instanceof B_4088_l) {
            U_3554_Q.w_1484_f.n_1700_B((B_4088_l)this, source, f, amount, flag);
            if (f1 > 0.0f && f1 < 3.4028235E37f) {
                ((B_4088_l)this).n_1700_B(Stats.z_1737_N, Math.round(f1 * 10.0f));
            }
        }
        if (entity1 instanceof B_4088_l) {
            U_3554_Q.v_4262_N.n_1700_B((B_4088_l)entity1, this, source, f, amount, flag);
        }
        return flag2;
    }

    protected void G_564_y(r_4811_B entityIn) {
        entityIn.P_1922_E(this);
    }

    protected void P_1922_E(r_4811_B entityIn) {
        entityIn.n_1700_B(0.5f, entityIn.O_3598_v() - this.O_3598_v(), entityIn.l_2647_k() - this.l_2647_k());
    }

    private boolean u_1723_Y(P_11_z damageSourceIn) {
        if (damageSourceIn.w_1484_f()) {
            return false;
        }
        Z_1993_T itemstack = null;
        for (x_1688_C hand : x_1688_C.values()) {
            Z_1993_T itemstack1 = this.R_4764_Y(hand);
            if (itemstack1.J_1907_R() != Items.N_81_X) continue;
            itemstack = itemstack1.t_148_a();
            itemstack1.v_4262_N(1);
            break;
        }
        if (itemstack != null) {
            if (this instanceof B_4088_l) {
                B_4088_l serverplayerentity = (B_4088_l)this;
                serverplayerentity.n_1700_B(Stats.R_4764_Y.J_1907_R(Items.N_81_X));
                U_3554_Q.H_2857_Y.n_1700_B(serverplayerentity, itemstack);
            }
            this.t_1786_h(1.0f);
            this.g_1734_y();
            this.n_1700_B(new k_2610_C(MobEffects.s_956_w, 900, 1));
            this.n_1700_B(new k_2610_C(MobEffects.Q_2552_b, 100, 1));
            this.n_1700_B(new k_2610_C(MobEffects.M_588_G, 800, 0));
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)35);
        }
        return itemstack != null;
    }

    @Nullable
    public P_11_z J_4125_o() {
        if (this.O_508_d.X_933_l() - this.q_2307_F > 40L) {
            this.k_2293_S = null;
        }
        return this.k_2293_S;
    }

    protected void J_1907_R(P_11_z source) {
        SoundEvent soundevent = this.P_1922_E(source);
        if (soundevent != null) {
            this.n_1700_B(soundevent, this.d_4500_Q(), this.O_2761_o());
        }
    }

    private boolean v_4262_N(P_11_z damageSourceIn) {
        e_2866_D vector3d2;
        h_384_L abstractarrowentity;
        N_4263_v entity = damageSourceIn.s_956_w();
        boolean flag = false;
        if (entity instanceof h_384_L && (abstractarrowentity = (h_384_L)entity).Q_4569_t() > 0) {
            flag = true;
        }
        if (!damageSourceIn.u_1723_Y() && this.N_260_m() && !flag && (vector3d2 = damageSourceIn.C_2741_M()) != null) {
            e_2866_D vector3d = this.t_148_a(1.0f);
            e_2866_D vector3d1 = vector3d2.n_1700_B(this.s_4990_V()).G_564_y();
            vector3d1 = new e_2866_D(vector3d1.J_1907_R, 0.0, vector3d1.G_564_y);
            if (vector3d1.J_1907_R(vector3d) < 0.0) {
                return true;
            }
        }
        return false;
    }

    private void v_4262_N(Z_1993_T stack) {
        if (!stack.n_1700_B()) {
            if (!this.y_1700_S()) {
                this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.ClientSpoof, this.r_2478_U(), 0.8f, 0.8f + this.O_508_d.w_1457_N.nextFloat() * 0.4f, false);
            }
            this.J_1907_R(stack, 5);
        }
    }

    public void R_4764_Y(P_11_z cause) {
        if (!this.t_4219_U && !this.TextRenderingUtils) {
            N_4263_v entity = cause.u_2550_I();
            r_4811_B livingentity = this.J_2061_p();
            if (this.t_1446_I >= 0 && livingentity != null) {
                livingentity.n_1700_B(this, this.t_1446_I, cause);
            }
            if (this.z_2372_L()) {
                this.t_2932_z();
            }
            this.TextRenderingUtils = true;
            this.i_789_Q().P_1922_E();
            if (this.O_508_d instanceof e_3591_l) {
                if (entity != null) {
                    entity.n_1700_B((e_3591_l)this.O_508_d, this);
                }
                this.G_564_y(cause);
                this.u_1723_Y(livingentity);
            }
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)3);
            this.J_1907_R(I_1170_F.v_4262_N);
        }
    }

    protected void u_1723_Y(@Nullable r_4811_B entitySource) {
        if (!this.O_508_d.Y_259_p) {
            boolean flag = false;
            if (entitySource instanceof I_3700_V) {
                if (this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
                    c_1514_x blockpos = this.b_2312_j();
                    K_4074_S blockstate = a_3742_W.f_3449_S.multiplayerClientSuggestionProvider();
                    if (this.O_508_d.getBlockState(blockpos).v_4262_N() && blockstate.n_1700_B((T_1316_M)this.O_508_d, blockpos)) {
                        this.O_508_d.n_1700_B(blockpos, blockstate, 3);
                        flag = true;
                    }
                }
                if (!flag) {
                    n_1494_c itementity = new n_1494_c(this.O_508_d, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), new Z_1993_T(Items.G_424_k));
                    this.O_508_d.a_(itementity);
                }
            }
        }
    }

    protected void G_564_y(P_11_z damageSourceIn) {
        boolean flag;
        N_4263_v entity = damageSourceIn.u_2550_I();
        int i = entity instanceof a_3913_L ? K_4096_w.v_4262_N((r_4811_B)entity) : 0;
        boolean bl = flag = this.d_4007_L > 0;
        if (this.I_685_r() && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.P_1922_E)) {
            this.n_1700_B(damageSourceIn, flag);
            this.n_1700_B(damageSourceIn, i, flag);
        }
        this.A_229_v();
        this.b_2625_m();
    }

    protected void A_229_v() {
    }

    protected void b_2625_m() {
        if (!this.O_508_d.Y_259_p && (this.h_3270_j() || this.d_4007_L > 0 && this.Q_3581_n() && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.P_1922_E))) {
            int j;
            for (int i = this.R_4764_Y(this.H_1883_T); i > 0; i -= j) {
                j = n_4637_L.n_1700_B(i);
                this.O_508_d.a_(new n_4637_L(this.O_508_d, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), j));
            }
        }
    }

    protected void n_1700_B(P_11_z source, int looting, boolean recentlyHitIn) {
    }

    public g_2336_b q_3401_q() {
        return this.f_4016_n().w_1484_f();
    }

    protected void n_1700_B(P_11_z damageSourceIn, boolean attackedRecently) {
        g_2336_b resourcelocation = this.q_3401_q();
        p_4985_U loottable = this.O_508_d.T_2506_i().F_2624_D().n_1700_B(resourcelocation);
        q_1704_m.n_1700_B lootcontext$builder = this.n_1700_B(attackedRecently, damageSourceIn);
        loottable.J_1907_R(lootcontext$builder.n_1700_B(f_1402_I.u_1723_Y), this::a_);
    }

    protected q_1704_m.n_1700_B n_1700_B(boolean attackedRecently, P_11_z damageSourceIn) {
        q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B((e_3591_l)this.O_508_d).n_1700_B(this.RealmsWorldOptions).n_1700_B(LootContextParams.n_1700_B, this).n_1700_B(LootContextParams.u_1723_Y, this.s_4990_V()).n_1700_B(LootContextParams.R_4764_Y, damageSourceIn).J_1907_R(LootContextParams.G_564_y, damageSourceIn.u_2550_I()).J_1907_R(LootContextParams.P_1922_E, damageSourceIn.s_956_w());
        if (attackedRecently && this.H_1883_T != null) {
            lootcontext$builder = lootcontext$builder.n_1700_B(LootContextParams.J_1907_R, this.H_1883_T).n_1700_B(this.H_1883_T.Module());
        }
        return lootcontext$builder;
    }

    public void n_1700_B(float strength, double ratioX, double ratioZ) {
        if (!((strength = (float)((double)strength * (1.0 - this.J_1907_R(Attributes.R_4764_Y)))) <= 0.0f)) {
            this.LongRunningTask = true;
            e_2866_D vector3d = this.I_4348_c();
            e_2866_D vector3d1 = new e_2866_D(ratioX, 0.0, ratioZ).G_564_y().n_1700_B((double)strength);
            this.h_1847_R(vector3d.J_1907_R / 2.0 - vector3d1.J_1907_R, this.e_1992_r ? Math.min(0.4, vector3d.R_4764_Y / 2.0 + (double)strength) : vector3d.R_4764_Y, vector3d.G_564_y / 2.0 - vector3d1.G_564_y);
        }
    }

    @Nullable
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.h_1640_b;
    }

    @Nullable
    protected SoundEvent u_796_y() {
        return SoundEvents.n_473_l;
    }

    protected SoundEvent M_588_G(int heightIn) {
        return heightIn > 4 ? SoundEvents.n_4915_F : SoundEvents.V_1176_p;
    }

    protected SoundEvent R_4764_Y(Z_1993_T stack) {
        return stack.e_4240_b();
    }

    public SoundEvent G_564_y(Z_1993_T itemStackIn) {
        return itemStackIn.n_3318_d();
    }

    @Override
    public void u_1723_Y(boolean grounded) {
        super.u_1723_Y(grounded);
        if (grounded) {
            this.C_2741_M = Optional.empty();
        }
    }

    public Optional<c_1514_x> Z_4720_K() {
        return this.C_2741_M;
    }

    public boolean e_() {
        if (this.d_2461_k()) {
            return false;
        }
        c_1514_x blockpos = this.b_2312_j();
        K_4074_S blockstate = this.r_4879_Z();
        T_2915_h block = blockstate.J_1907_R();
        if (block.n_1700_B(BlockTags.h_4320_q)) {
            this.C_2741_M = Optional.of(blockpos);
            return true;
        }
        if (block instanceof x_2838_H && this.R_4764_Y(blockpos, blockstate)) {
            this.C_2741_M = Optional.of(blockpos);
            return true;
        }
        return false;
    }

    public K_4074_S r_4879_Z() {
        return this.O_508_d.getBlockState(this.b_2312_j());
    }

    private boolean R_4764_Y(c_1514_x pos, K_4074_S state) {
        K_4074_S blockstate;
        return state.R_4764_Y(x_2838_H.P_4830_p) != false && (blockstate = this.O_508_d.getBlockState(pos.down())).n_1700_B(a_3742_W.L_3570_A) && blockstate.R_4764_Y(C_1985_D.P_4830_p) == state.R_4764_Y(x_2838_H.w_612_n);
    }

    @Override
    public boolean RealmsLongRunningMcoTaskScreen() {
        return !this.t_4219_U && this.g_46_E() > 0.0f;
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        boolean flag = super.R_4764_Y(distance, damageMultiplier);
        int i = this.u_1723_Y(distance, damageMultiplier);
        if (i > 0) {
            this.n_1700_B(this.M_588_G(i), 1.0f, 1.0f);
            this.q_839_y();
            this.n_1700_B(P_11_z.u_2550_I, (float)i);
            if (this instanceof V_772_m) {
                A_4115_X.n_1700_B(new v_887_r(v_887_r.n_1700_B.n_1700_B));
            }
            return true;
        }
        return flag;
    }

    protected int u_1723_Y(float distance, float damageMultiplier) {
        k_2610_C effectinstance = this.R_4764_Y(MobEffects.w_1484_f);
        float f = effectinstance == null ? 0.0f : (float)(effectinstance.R_4764_Y() + 1);
        return u_530_F.u_1723_Y((distance - 3.0f - f) * damageMultiplier);
    }

    protected void q_839_y() {
        int k;
        int j;
        int i;
        K_4074_S blockstate;
        if (!this.y_1700_S() && !(blockstate = this.O_508_d.getBlockState(new c_1514_x(i = u_530_F.R_4764_Y(this.O_3598_v()), j = u_530_F.R_4764_Y(this.X_2960_b() - (double)0.2f), k = u_530_F.R_4764_Y(this.l_2647_k())))).v_4262_N()) {
            SoundType soundtype = blockstate.Q_4569_t();
            this.n_1700_B(soundtype.v_4262_N(), soundtype.n_1700_B() * 0.5f, soundtype.J_1907_R() * 0.75f);
        }
    }

    @Override
    public void D_4361_a() {
        this.RealmsLongRunningMcoTaskScreen = this.i_2993_w = 10;
        this.RealmsParentalConsentScreen = 0.0f;
    }

    public int E_3343_g() {
        return u_530_F.R_4764_Y(this.J_1907_R(Attributes.t_148_a));
    }

    protected void R_4764_Y(P_11_z damageSource, float damage) {
    }

    protected void multiplayerClientSuggestionProvider(float damage) {
    }

    protected float G_564_y(P_11_z source, float damage) {
        if (!source.u_1723_Y()) {
            this.R_4764_Y(source, damage);
            damage = CombatRules.n_1700_B(damage, this.E_3343_g(), (float)this.J_1907_R(Attributes.s_956_w));
        }
        return damage;
    }

    protected float P_1922_E(P_11_z source, float damage) {
        int i;
        int j;
        float f;
        float f1;
        float f2;
        if (source.t_148_a()) {
            return damage;
        }
        if (this.J_1907_R(MobEffects.u_2550_I) && source != P_11_z.P_4830_p && (f2 = (f1 = damage) - (damage = Math.max((f = damage * (float)(j = 25 - (i = (this.R_4764_Y(MobEffects.u_2550_I).R_4764_Y() + 1) * 5))) / 25.0f, 0.0f))) > 0.0f && f2 < 3.4028235E37f) {
            if (this instanceof B_4088_l) {
                ((B_4088_l)this).n_1700_B(Stats.d_2461_k, Math.round(f2 * 10.0f));
            } else if (source.u_2550_I() instanceof B_4088_l) {
                ((B_4088_l)source.u_2550_I()).n_1700_B(Stats.n_3318_d, Math.round(f2 * 10.0f));
            }
        }
        if (damage <= 0.0f) {
            return 0.0f;
        }
        int k = K_4096_w.n_1700_B(this.u_55_V(), source);
        if (k > 0) {
            damage = CombatRules.n_1700_B(damage, k);
        }
        return damage;
    }

    protected void J_1907_R(P_11_z damageSrc, float damageAmount) {
        if (!this.n_1700_B(damageSrc)) {
            damageAmount = this.G_564_y(damageSrc, damageAmount);
            damageAmount = this.P_1922_E(damageSrc, damageAmount);
            float f2 = Math.max(damageAmount - this.U_3823_u(), 0.0f);
            this.Y_259_p(this.U_3823_u() - (damageAmount - f2));
            float f = damageAmount - f2;
            if (f > 0.0f && f < 3.4028235E37f && damageSrc.u_2550_I() instanceof B_4088_l) {
                ((B_4088_l)damageSrc.u_2550_I()).n_1700_B(Stats.e_4240_b, Math.round(f * 10.0f));
            }
            if (f2 != 0.0f) {
                float f1 = this.g_46_E();
                this.t_1786_h(f1 - f2);
                this.i_789_Q().n_1700_B(damageSrc, f1, f2);
                this.Y_259_p(this.U_3823_u() - f2);
            }
        }
    }

    public x_937_q i_789_Q() {
        return this.u_2550_I;
    }

    @Nullable
    public r_4811_B J_2061_p() {
        if (this.u_2550_I.R_4764_Y() != null) {
            return this.u_2550_I.R_4764_Y();
        }
        if (this.H_1883_T != null) {
            return this.H_1883_T;
        }
        return this.M_182_A != null ? this.M_182_A : null;
    }

    public final float L_1733_J() {
        return (float)this.J_1907_R(Attributes.n_1700_B);
    }

    public final int n_4539_g() {
        return this.l_4537_E.n_1700_B(v_4262_N);
    }

    public final void P_4830_p(int count) {
        this.l_4537_E.J_1907_R(v_4262_N, count);
    }

    public final int U_4087_m() {
        return this.l_4537_E.n_1700_B(w_1484_f);
    }

    public final void h_1847_R(int p_226300_1_) {
        this.l_4537_E.J_1907_R(w_1484_f, p_226300_1_);
    }

    private int h_1847_R() {
        int swipeSpeed = 6;
        J_2061_p event = new J_2061_p(swipeSpeed, this.C_290_v);
        A_4115_X.n_1700_B(event);
        if (event.n_1700_B() && this instanceof V_772_m) {
            return event.J_1907_R();
        }
        if (MobEffectUtil.n_1700_B(this)) {
            return 6 - (1 + MobEffectUtil.J_1907_R(this));
        }
        return this.J_1907_R(MobEffects.G_564_y) ? 6 + (1 + this.R_4764_Y(MobEffects.G_564_y).R_4764_Y()) * 2 : 6;
    }

    public void n_1700_B(x_1688_C hand) {
        this.n_1700_B(hand, false);
    }

    public void n_1700_B(x_1688_C handIn, boolean updateSelf) {
        if (!this.RealmsCreateRealmScreen || this.w_728_N >= this.h_1847_R() / 2 || this.w_728_N < 0) {
            this.w_728_N = -1;
            this.RealmsCreateRealmScreen = true;
            this.C_290_v = handIn;
            if (this.O_508_d instanceof e_3591_l) {
                q_3092_O sanimatehandpacket = new q_3092_O(this, handIn == x_1688_C.n_1700_B ? 0 : 3);
                C_3615_s serverchunkprovider = ((e_3591_l)this.O_508_d).Y_259_p();
                if (updateSelf) {
                    serverchunkprovider.n_1700_B(this, sanimatehandpacket);
                } else {
                    serverchunkprovider.J_1907_R(this, sanimatehandpacket);
                }
            }
        }
    }

    @Override
    public void n_1700_B(byte id) {
        switch (id) {
            case 2: 
            case 33: 
            case 36: 
            case 37: 
            case 44: {
                P_11_z damagesource;
                SoundEvent soundevent1;
                boolean flag1 = id == 33;
                boolean flag2 = id == 36;
                boolean flag3 = id == 37;
                boolean flag = id == 44;
                this.G_424_k = 1.5f;
                this.F_1410_V = 20;
                this.RealmsLongRunningMcoTaskScreen = this.i_2993_w = 10;
                this.RealmsParentalConsentScreen = 0.0f;
                if (flag1) {
                    this.n_1700_B(SoundEvents.DirectionalBlock, this.d_4500_Q(), (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
                }
                if ((soundevent1 = this.P_1922_E(damagesource = flag3 ? P_11_z.R_4764_Y : (flag2 ? P_11_z.w_1484_f : (flag ? P_11_z.Y_259_p : P_11_z.h_1847_R)))) != null) {
                    this.n_1700_B(soundevent1, this.d_4500_Q(), (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
                }
                this.n_1700_B(P_11_z.h_1847_R, 0.0f);
                break;
            }
            case 3: {
                SoundEvent soundevent = this.u_796_y();
                if (soundevent != null) {
                    this.n_1700_B(soundevent, this.d_4500_Q(), (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
                }
                if (this instanceof a_3913_L) break;
                this.t_1786_h(0.0f);
                this.R_4764_Y(P_11_z.h_1847_R);
                break;
            }
            default: {
                super.n_1700_B(id);
                break;
            }
            case 29: {
                this.n_1700_B(SoundEvents.b_1430_k, 1.0f, 0.8f + this.O_508_d.w_1457_N.nextFloat() * 0.4f);
                break;
            }
            case 30: {
                this.n_1700_B(SoundEvents.n_3115_n, 0.8f, 0.8f + this.O_508_d.w_1457_N.nextFloat() * 0.4f);
                break;
            }
            case 46: {
                int i = 128;
                for (int j = 0; j < 128; ++j) {
                    double d0 = (double)j / 127.0;
                    float f = (this.RealmsWorldOptions.nextFloat() - 0.5f) * 0.2f;
                    float f1 = (this.RealmsWorldOptions.nextFloat() - 0.5f) * 0.2f;
                    float f2 = (this.RealmsWorldOptions.nextFloat() - 0.5f) * 0.2f;
                    double d1 = u_530_F.G_564_y(d0, this.r_715_M, this.O_3598_v()) + (this.RealmsWorldOptions.nextDouble() - 0.5) * (double)this.C_415_h() * 2.0;
                    double d2 = u_530_F.G_564_y(d0, this.A_1038_p, this.X_2960_b()) + this.RealmsWorldOptions.nextDouble() * (double)this.v_165_F();
                    double d3 = u_530_F.G_564_y(d0, this.i_1637_u, this.l_2647_k()) + (this.RealmsWorldOptions.nextDouble() - 0.5) * (double)this.C_415_h() * 2.0;
                    this.O_508_d.n_1700_B(ParticleTypes.g_221_o, d1, d2, d3, (double)f, (double)f1, f2);
                }
                break;
            }
            case 47: {
                this.v_4262_N(this.J_1907_R(e_1174_E.n_1700_B));
                break;
            }
            case 48: {
                this.v_4262_N(this.J_1907_R(e_1174_E.J_1907_R));
                break;
            }
            case 49: {
                this.v_4262_N(this.J_1907_R(e_1174_E.u_1723_Y));
                break;
            }
            case 50: {
                this.v_4262_N(this.J_1907_R(e_1174_E.P_1922_E));
                break;
            }
            case 51: {
                this.v_4262_N(this.J_1907_R(e_1174_E.G_564_y));
                break;
            }
            case 52: {
                this.v_4262_N(this.J_1907_R(e_1174_E.R_4764_Y));
                break;
            }
            case 54: {
                HoneyBlock.J_1907_R(this);
                break;
            }
            case 55: {
                this.Q_4569_t();
            }
        }
    }

    private void Q_4569_t() {
        Z_1993_T itemstack = this.J_1907_R(e_1174_E.J_1907_R);
        this.n_1700_B(e_1174_E.J_1907_R, this.J_1907_R(e_1174_E.n_1700_B));
        this.n_1700_B(e_1174_E.n_1700_B, itemstack);
    }

    @Override
    protected void j_1564_a() {
        this.n_1700_B(P_11_z.P_4830_p, 4.0f);
    }

    protected void k_3129_Y() {
        int i = this.h_1847_R();
        if (this.RealmsCreateRealmScreen) {
            ++this.w_728_N;
            if (this.w_728_N >= i) {
                this.w_728_N = 0;
                this.RealmsCreateRealmScreen = false;
            }
        } else {
            this.w_728_N = 0;
        }
        this.RealmsResetNormalWorldScreen = (float)this.w_728_N / (float)i;
    }

    @Nullable
    public A_4388_s n_1700_B(Attribute attribute) {
        return this.B_1146_q().n_1700_B(attribute);
    }

    public double J_1907_R(Attribute attribute) {
        return this.B_1146_q().R_4764_Y(attribute);
    }

    public double R_4764_Y(Attribute attribute) {
        return this.B_1146_q().G_564_y(attribute);
    }

    public AttributeMap B_1146_q() {
        return this.s_956_w;
    }

    public MobType F_2860_q() {
        return MobType.n_1700_B;
    }

    public Z_1993_T A_2714_y() {
        return this.J_1907_R(e_1174_E.n_1700_B);
    }

    public Z_1993_T S_4035_N() {
        return this.J_1907_R(e_1174_E.J_1907_R);
    }

    public boolean n_1700_B(q_1613_l item) {
        return this.n_1700_B((q_1613_l p_233632_1_) -> p_233632_1_ == item);
    }

    public boolean n_1700_B(Predicate<q_1613_l> p_233634_1_) {
        return p_233634_1_.test(this.A_2714_y().J_1907_R()) || p_233634_1_.test(this.S_4035_N().J_1907_R());
    }

    public Z_1993_T R_4764_Y(x_1688_C hand) {
        if (hand == x_1688_C.n_1700_B) {
            return this.J_1907_R(e_1174_E.n_1700_B);
        }
        if (hand == x_1688_C.J_1907_R) {
            return this.J_1907_R(e_1174_E.J_1907_R);
        }
        throw new IllegalArgumentException("Invalid hand " + String.valueOf((Object)hand));
    }

    public void n_1700_B(x_1688_C hand, Z_1993_T stack) {
        if (hand == x_1688_C.n_1700_B) {
            this.n_1700_B(e_1174_E.n_1700_B, stack);
        } else {
            if (hand != x_1688_C.J_1907_R) {
                throw new IllegalArgumentException("Invalid hand " + String.valueOf((Object)hand));
            }
            this.n_1700_B(e_1174_E.J_1907_R, stack);
        }
    }

    public boolean n_1700_B(e_1174_E slotIn) {
        return !this.J_1907_R(slotIn).n_1700_B();
    }

    @Override
    public abstract Iterable<Z_1993_T> u_55_V();

    public abstract Z_1993_T J_1907_R(e_1174_E var1);

    @Override
    public abstract void n_1700_B(e_1174_E var1, Z_1993_T var2);

    public float h_1015_G() {
        Iterable<Z_1993_T> iterable = this.u_55_V();
        int i = 0;
        int j = 0;
        for (Z_1993_T itemstack : iterable) {
            if (!itemstack.n_1700_B()) {
                ++j;
            }
            ++i;
        }
        return i > 0 ? (float)j / (float)i : 0.0f;
    }

    @Override
    public void b_(boolean sprinting) {
        super.b_(sprinting);
        A_4388_s modifiableattributeinstance = this.n_1700_B(Attributes.G_564_y);
        if (modifiableattributeinstance.n_1700_B(n_1700_B) != null) {
            modifiableattributeinstance.G_564_y(R_4764_Y);
        }
        if (sprinting) {
            modifiableattributeinstance.J_1907_R(R_4764_Y);
        }
    }

    protected float d_4500_Q() {
        return 1.0f;
    }

    protected float O_2761_o() {
        return this.d_() ? (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.5f : (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f;
    }

    protected boolean W_3729_Q() {
        return this.Z_2812_M();
    }

    @Override
    public void P_1922_E(N_4263_v entityIn) {
        if (!this.z_2372_L()) {
            super.P_1922_E(entityIn);
        }
    }

    private void n_1700_B(N_4263_v p_233628_1_) {
        e_2866_D vector3d = !p_233628_1_.t_4219_U && !this.O_508_d.getBlockState(p_233628_1_.b_2312_j()).J_1907_R().n_1700_B(BlockTags.f_4016_n) ? p_233628_1_.b_(this) : new e_2866_D(p_233628_1_.O_3598_v(), p_233628_1_.X_2960_b() + (double)p_233628_1_.v_165_F(), p_233628_1_.l_2647_k());
        this.P_4830_p(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
    }

    @Override
    public boolean I_1407_m() {
        return this.V_118_c();
    }

    protected float E_453_w() {
        return 0.42f * this.RealmsWorldResetDto();
    }

    protected void e_837_t() {
        if (this instanceof V_772_m) {
            A_4115_X.n_1700_B(new P_4639_N());
        }
        float f = this.E_453_w();
        if (this.J_1907_R(MobEffects.w_1484_f)) {
            f += 0.1f * (float)(this.R_4764_Y(MobEffects.w_1484_f).R_4764_Y() + 1);
        }
        e_2866_D vector3d = this.I_4348_c();
        this.h_1847_R(vector3d.J_1907_R, f, vector3d.G_564_y);
        if (this.o_2341_D()) {
            float f1 = this.p_178_J * ((float)Math.PI / 180);
            this.v_4262_N(this.I_4348_c().J_1907_R(-u_530_F.n_1700_B(f1) * 0.2f, 0.0, u_530_F.J_1907_R(f1) * 0.2f));
        }
        this.LongRunningTask = true;
    }

    private Optional<IBaritone> M_182_A() {
        if (V_772_m.class.isInstance(this)) {
            return Optional.ofNullable(BaritoneAPI.getProvider().getBaritoneForPlayer((V_772_m)this));
        }
        return Optional.empty();
    }

    protected void m_1621_v() {
        this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.04f, 0.0));
    }

    protected void R_4764_Y(r_109_r<Fluid> fluidTag) {
        this.v_4262_N(this.I_4348_c().J_1907_R(0.0, 0.04f, 0.0));
    }

    protected float M_2562_s() {
        return 0.8f;
    }

    public boolean n_1700_B(Fluid p_230285_1_) {
        return false;
    }

    public void w_1484_f(e_2866_D travelVector) {
        S_4035_N eventTravel = new S_4035_N(this);
        A_4115_X.n_1700_B(eventTravel);
        if (eventTravel.n_1700_B()) {
            this.n_1700_B(this, this instanceof R_4053_F);
            return;
        }
        if (this.w_1457_N() || this.v_887_r()) {
            boolean flag;
            double d0 = 0.08;
            boolean bl = flag = this.I_4348_c().R_4764_Y <= 0.0;
            if (flag && this.J_1907_R(MobEffects.H_2857_Y)) {
                d0 = 0.01;
                this.U_1241_n = 0.0f;
            }
            FluidState fluidstate = this.O_508_d.getFluidState(this.b_2312_j());
            if (this.RowButton() && this.m_891_U() && !this.n_1700_B(fluidstate.n_1700_B())) {
                double d8 = this.X_2960_b();
                float f5 = this.o_2341_D() ? 0.9f : this.M_2562_s();
                float f6 = 0.02f;
                float f7 = K_4096_w.P_1922_E(this);
                if (f7 > 3.0f) {
                    f7 = 3.0f;
                }
                if (!this.e_1992_r) {
                    f7 *= 0.5f;
                }
                if (f7 > 0.0f) {
                    f5 += (0.54600006f - f5) * f7 / 3.0f;
                    f6 += (this.l_2995_s() - f6) * f7 / 3.0f;
                }
                if (this.J_1907_R(MobEffects.Y_1740_V)) {
                    f5 = 0.96f;
                }
                this.n_1700_B(f6, travelVector);
                this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
                e_2866_D vector3d6 = this.I_4348_c();
                if (this.D_60_a && this.e_()) {
                    vector3d6 = new e_2866_D(vector3d6.J_1907_R, 0.2, vector3d6.G_564_y);
                }
                this.v_4262_N(vector3d6.G_564_y(f5, 0.8f, f5));
                e_2866_D vector3d2 = this.n_1700_B(d0, flag, this.I_4348_c());
                this.v_4262_N(vector3d2);
                if (this.D_60_a && this.R_4764_Y(vector3d2.J_1907_R, vector3d2.R_4764_Y + (double)0.6f - this.X_2960_b() + d8, vector3d2.G_564_y)) {
                    this.h_1847_R(vector3d2.J_1907_R, 0.3f, vector3d2.G_564_y);
                }
            } else if (this.W_3464_O() && this.m_891_U() && !this.n_1700_B(fluidstate.n_1700_B())) {
                double d7 = this.X_2960_b();
                this.n_1700_B(0.02f, travelVector);
                this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
                if (this.J_1907_R(FluidTags.R_4764_Y) <= this.i_3196_G()) {
                    this.v_4262_N(this.I_4348_c().G_564_y(0.5, 0.8f, 0.5));
                    e_2866_D vector3d3 = this.n_1700_B(d0, flag, this.I_4348_c());
                    this.v_4262_N(vector3d3);
                } else {
                    this.v_4262_N(this.I_4348_c().n_1700_B(0.5));
                }
                if (!this.u_744_e()) {
                    this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -d0 / 4.0, 0.0));
                }
                e_2866_D vector3d4 = this.I_4348_c();
                if (this.D_60_a && this.R_4764_Y(vector3d4.J_1907_R, vector3d4.R_4764_Y + (double)0.6f - this.X_2960_b() + d7, vector3d4.G_564_y)) {
                    this.h_1847_R(vector3d4.J_1907_R, 0.3f, vector3d4.G_564_y);
                }
            } else if (this.k_578_l()) {
                double d10;
                double d6;
                float f2;
                e_2866_D vector3d = this.I_4348_c();
                if (vector3d.R_4764_Y > -0.5) {
                    this.U_1241_n = 1.0f;
                }
                A_3244_K eventElytraFlying = new A_3244_K(this.RealmsSettingsScreen(), this.f_4016_n);
                A_4115_X.n_1700_B(eventElytraFlying);
                e_2866_D vector3d1 = eventElytraFlying.J_1907_R();
                float f = eventElytraFlying.R_4764_Y() * ((float)Math.PI / 180);
                double d1 = Math.sqrt(vector3d1.J_1907_R * vector3d1.J_1907_R + vector3d1.G_564_y * vector3d1.G_564_y);
                double d3 = Math.sqrt(r_4811_B.R_4764_Y(vector3d));
                double d4 = vector3d1.u_1723_Y();
                float f1 = u_530_F.J_1907_R(f);
                f1 = (float)((double)f1 * (double)f1 * Math.min(1.0, d4 / 0.4));
                vector3d = this.I_4348_c().J_1907_R(0.0, d0 * (-1.0 + (double)f1 * 0.75), 0.0);
                if (vector3d.R_4764_Y < 0.0 && d1 > 0.0) {
                    double d5 = vector3d.R_4764_Y * -0.1 * (double)f1;
                    vector3d = vector3d.J_1907_R(vector3d1.J_1907_R * d5 / d1, d5, vector3d1.G_564_y * d5 / d1);
                }
                if (f < 0.0f && d1 > 0.0) {
                    double d9 = d3 * (double)(-u_530_F.n_1700_B(f)) * 0.04;
                    vector3d = vector3d.J_1907_R(-vector3d1.J_1907_R * d9 / d1, d9 * 3.2, -vector3d1.G_564_y * d9 / d1);
                }
                if (d1 > 0.0) {
                    vector3d = vector3d.J_1907_R((vector3d1.J_1907_R / d1 * d3 - vector3d.J_1907_R) * 0.1, 0.0, (vector3d1.G_564_y / d1 * d3 - vector3d.G_564_y) * 0.1);
                }
                this.v_4262_N(vector3d.G_564_y(0.99f, 0.98f, 0.99f));
                this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
                if (this.D_60_a && !this.O_508_d.Y_259_p && (f2 = (float)((d6 = d3 - (d10 = Math.sqrt(r_4811_B.R_4764_Y(this.I_4348_c())))) * 10.0 - 3.0)) > 0.0f) {
                    this.n_1700_B(this.M_588_G((int)f2), 1.0f, 1.0f);
                    this.n_1700_B(P_11_z.M_588_G, f2);
                }
                if (this.e_1992_r && !this.O_508_d.Y_259_p) {
                    this.J_1907_R(7, false);
                }
            } else {
                c_1514_x blockpos = this.H_1083_k();
                float f3 = this.O_508_d.getBlockState(blockpos).J_1907_R().h_1847_R();
                float f4 = this.e_1992_r ? f3 * 0.91f : 0.91f;
                e_2866_D vector3d5 = this.n_1700_B(travelVector, f3);
                double d2 = vector3d5.R_4764_Y;
                if (this.J_1907_R(MobEffects.q_2307_F)) {
                    d2 += (0.05 * (double)(this.R_4764_Y(MobEffects.q_2307_F).R_4764_Y() + 1) - vector3d5.R_4764_Y) * 0.2;
                    this.U_1241_n = 0.0f;
                } else if (this.O_508_d.Y_259_p && !this.O_508_d.M_588_G(blockpos)) {
                    d2 = this.X_2960_b() > 0.0 ? -0.1 : 0.0;
                } else if (!this.u_744_e()) {
                    d2 -= d0;
                }
                this.h_1847_R(vector3d5.J_1907_R * (double)f4, d2 * (double)0.98f, vector3d5.G_564_y * (double)f4);
                double dy2 = (this.I_4348_c().R_4764_Y - d0) * 0.98;
                e_2866_D attempt = new e_2866_D(0.0, dy2, 0.0);
                e_2866_D allowed = this.G_564_y(attempt);
                boolean willLand = dy2 < 0.0 && allowed.R_4764_Y != dy2;
                A_4115_X.n_1700_B(new e_837_t(willLand));
            }
        }
        this.n_1700_B(this, this instanceof R_4053_F);
    }

    public void n_1700_B(r_4811_B p_233629_1_, boolean p_233629_2_) {
        double d2;
        double d1;
        p_233629_1_.A_3959_N = p_233629_1_.G_424_k;
        double d0 = p_233629_1_.O_3598_v() - p_233629_1_.r_715_M;
        float f = u_530_F.n_1700_B(d0 * d0 + (d1 = p_233629_2_ ? p_233629_1_.X_2960_b() - p_233629_1_.A_1038_p : 0.0) * d1 + (d2 = p_233629_1_.l_2647_k() - p_233629_1_.i_1637_u) * d2) * 4.0f;
        if (f > 1.0f) {
            f = 1.0f;
        }
        p_233629_1_.G_424_k += (f - p_233629_1_.G_424_k) * 0.4f;
        p_233629_1_.RealmsSettingsScreen += p_233629_1_.G_424_k;
    }

    public e_2866_D n_1700_B(e_2866_D p_233633_1_, float p_233633_2_) {
        this.n_1700_B(this.G_564_y(p_233633_2_), p_233633_1_);
        this.v_4262_N(this.s_956_w(this.I_4348_c()));
        this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
        e_2866_D vector3d = this.I_4348_c();
        if ((this.D_60_a || this.F_3572_x) && this.e_()) {
            vector3d = new e_2866_D(vector3d.J_1907_R, 0.2, vector3d.G_564_y);
        }
        return vector3d;
    }

    public e_2866_D n_1700_B(double p_233626_1_, boolean p_233626_3_, e_2866_D p_233626_4_) {
        if (!this.u_744_e() && !this.o_2341_D()) {
            double d0 = p_233626_3_ && Math.abs(p_233626_4_.R_4764_Y - 0.005) >= 0.003 && Math.abs(p_233626_4_.R_4764_Y - p_233626_1_ / 16.0) < 0.003 ? -0.003 : p_233626_4_.R_4764_Y - p_233626_1_ / 16.0;
            return new e_2866_D(p_233626_4_.J_1907_R, d0, p_233626_4_.G_564_y);
        }
        return p_233626_4_;
    }

    private e_2866_D s_956_w(e_2866_D p_213362_1_) {
        if (this.e_()) {
            this.U_1241_n = 0.0f;
            float f = 0.15f;
            double d0 = u_530_F.n_1700_B(p_213362_1_.J_1907_R, (double)-0.15f, (double)0.15f);
            double d1 = u_530_F.n_1700_B(p_213362_1_.G_564_y, (double)-0.15f, (double)0.15f);
            double d2 = Math.max(p_213362_1_.R_4764_Y, (double)-0.15f);
            if (d2 < 0.0 && !this.r_4879_Z().n_1700_B(a_3742_W.i_770_g) && this.q_() && this instanceof a_3913_L) {
                d2 = 0.0;
            }
            p_213362_1_ = new e_2866_D(d0, d2, d1);
        }
        return p_213362_1_;
    }

    private float G_564_y(float p_213335_1_) {
        return this.e_1992_r ? this.l_2995_s() * (0.21600002f / (p_213335_1_ * p_213335_1_ * p_213335_1_)) : this.y_2772_m;
    }

    public float l_2995_s() {
        return this.Y_601_j;
    }

    public void w_1457_N(float speedIn) {
        this.Y_601_j = speedIn;
    }

    public boolean q_2307_F(N_4263_v entityIn) {
        this.C_2741_M(entityIn);
        return false;
    }

    @Override
    public void v_() {
        super.v_();
        this.c_3005_b();
        this.t_4043_B();
        if (!this.O_508_d.Y_259_p) {
            int j;
            int i = this.n_4539_g();
            if (i > 0) {
                if (this.J_4256_G <= 0) {
                    this.J_4256_G = 20 * (30 - i);
                }
                --this.J_4256_G;
                if (this.J_4256_G <= 0) {
                    this.P_4830_p(i - 1);
                }
            }
            if ((j = this.U_4087_m()) > 0) {
                if (this.RealmsLongConfirmationScreen <= 0) {
                    this.RealmsLongConfirmationScreen = 20 * (30 - j);
                }
                --this.RealmsLongConfirmationScreen;
                if (this.RealmsLongConfirmationScreen <= 0) {
                    this.h_1847_R(j - 1);
                }
            }
            this.multiplayerClientSuggestionProvider();
            if (this.RealmsWorldResetDto % 20 == 0) {
                this.i_789_Q().P_1922_E();
            }
            if (!this.c_132_F) {
                boolean flag = this.J_1907_R(MobEffects.k_2293_S);
                if (this.v_4262_N(6) != flag) {
                    this.J_1907_R(6, flag);
                }
            }
            if (this.z_2372_L() && !this.e_4240_b()) {
                this.t_2932_z();
            }
        }
        this.Y_1740_V();
        double d0 = this.O_3598_v() - this.r_715_M;
        double d1 = this.l_2647_k() - this.i_1637_u;
        float f = (float)(d0 * d0 + d1 * d1);
        float f1 = this.C_1162_e;
        float f2 = 0.0f;
        this.U_1341_G = this.ClientBootstrap;
        float f3 = 0.0f;
        if (f > 0.0025000002f) {
            f3 = 1.0f;
            f2 = (float)Math.sqrt(f) * 3.0f;
            float f4 = (float)u_530_F.G_564_y(d1, d0) * 57.295776f - 90.0f;
            float f5 = u_530_F.P_1922_E(u_530_F.v_4262_N(this.p_178_J) - f4);
            f1 = 95.0f < f5 && f5 < 265.0f ? f4 - 180.0f : f4;
        }
        if (this.RealmsResetNormalWorldScreen > 0.0f) {
            f1 = this.p_178_J;
        }
        if (!this.e_1992_r) {
            f3 = 0.0f;
        }
        this.ClientBootstrap += (f3 - this.ClientBootstrap) * 0.3f;
        f2 = this.v_4262_N(f1, f2);
        while (this.p_178_J - this.j_276_v < -180.0f) {
            this.j_276_v -= 360.0f;
        }
        while (this.p_178_J - this.j_276_v >= 180.0f) {
            this.j_276_v += 360.0f;
        }
        while (this.C_1162_e - this.D_4361_a < -180.0f) {
            this.D_4361_a -= 360.0f;
        }
        while (this.C_1162_e - this.D_4361_a >= 180.0f) {
            this.D_4361_a += 360.0f;
        }
        while (this.f_4016_n - this.UploadStatus < -180.0f) {
            this.UploadStatus -= 360.0f;
        }
        while (this.f_4016_n - this.UploadStatus >= 180.0f) {
            this.UploadStatus += 360.0f;
        }
        while (this.f_3449_S - this.JsonUtils < -180.0f) {
            this.JsonUtils -= 360.0f;
        }
        while (this.f_3449_S - this.JsonUtils >= 180.0f) {
            this.JsonUtils += 360.0f;
        }
        while (this.u_55_V - this.RealmsPersistence < -180.0f) {
            this.RealmsPersistence -= 360.0f;
        }
        while (this.u_55_V - this.RealmsPersistence >= 180.0f) {
            this.RealmsPersistence += 360.0f;
        }
        this.o_2341_D += f2;
        this.F_518_D = this.k_578_l() ? ++this.F_518_D : 0;
        if (this.z_2372_L()) {
            this.f_4016_n = 0.0f;
        }
    }

    private void multiplayerClientSuggestionProvider() {
        Map<e_1174_E, Z_1993_T> map = this.C_2741_M();
        if (map != null) {
            this.n_1700_B(map);
            if (!map.isEmpty()) {
                this.J_1907_R(map);
            }
        }
    }

    @Nullable
    private Map<e_1174_E, Z_1993_T> C_2741_M() {
        EnumMap map = null;
        block4: for (e_1174_E equipmentslottype : e_1174_E.values()) {
            Z_1993_T itemstack;
            switch (equipmentslottype.n_1700_B()) {
                case n_1700_B: {
                    itemstack = this.P_1922_E(equipmentslottype);
                    break;
                }
                case J_1907_R: {
                    itemstack = this.G_564_y(equipmentslottype);
                    break;
                }
                default: {
                    continue block4;
                }
            }
            Z_1993_T itemstack1 = this.J_1907_R(equipmentslottype);
            if (Z_1993_T.J_1907_R(itemstack1, itemstack)) continue;
            if (map == null) {
                map = Maps.newEnumMap(e_1174_E.class);
            }
            map.put(equipmentslottype, itemstack1);
            if (!itemstack.n_1700_B()) {
                this.B_1146_q().n_1700_B(itemstack.n_1700_B(equipmentslottype));
            }
            if (itemstack1.n_1700_B()) continue;
            this.B_1146_q().J_1907_R(itemstack1.n_1700_B(equipmentslottype));
        }
        return map;
    }

    private void n_1700_B(Map<e_1174_E, Z_1993_T> p_241342_1_) {
        Z_1993_T itemstack = p_241342_1_.get((Object)e_1174_E.n_1700_B);
        Z_1993_T itemstack1 = p_241342_1_.get((Object)e_1174_E.J_1907_R);
        if (itemstack != null && itemstack1 != null && Z_1993_T.J_1907_R(itemstack, this.P_1922_E(e_1174_E.J_1907_R)) && Z_1993_T.J_1907_R(itemstack1, this.P_1922_E(e_1174_E.n_1700_B))) {
            ((e_3591_l)this.O_508_d).Y_259_p().J_1907_R(this, new C_1375_J(this, 55));
            p_241342_1_.remove((Object)e_1174_E.n_1700_B);
            p_241342_1_.remove((Object)e_1174_E.J_1907_R);
            this.R_4764_Y(e_1174_E.n_1700_B, itemstack.t_148_a());
            this.R_4764_Y(e_1174_E.J_1907_R, itemstack1.t_148_a());
        }
    }

    private void J_1907_R(Map<e_1174_E, Z_1993_T> p_241344_1_) {
        ArrayList list = Lists.newArrayListWithCapacity((int)p_241344_1_.size());
        p_241344_1_.forEach((p_241341_2_, p_241341_3_) -> {
            Z_1993_T itemstack = p_241341_3_.t_148_a();
            list.add(Pair.of((Object)p_241341_2_, (Object)itemstack));
            switch (p_241341_2_.n_1700_B()) {
                case n_1700_B: {
                    this.R_4764_Y((e_1174_E)((Object)p_241341_2_), itemstack);
                    break;
                }
                case J_1907_R: {
                    this.J_1907_R((e_1174_E)((Object)p_241341_2_), itemstack);
                }
            }
        });
        ((e_3591_l)this.O_508_d).Y_259_p().J_1907_R(this, new ClientboundSetEquipmentPacket(this.j_276_v(), list));
    }

    private Z_1993_T G_564_y(e_1174_E slot) {
        return this.h_1847_R.get(slot.J_1907_R());
    }

    private void J_1907_R(e_1174_E slot, Z_1993_T stack) {
        this.h_1847_R.set(slot.J_1907_R(), stack);
    }

    private Z_1993_T P_1922_E(e_1174_E slot) {
        return this.P_4830_p.get(slot.J_1907_R());
    }

    private void R_4764_Y(e_1174_E slot, Z_1993_T stack) {
        this.P_4830_p.set(slot.J_1907_R(), stack);
    }

    protected float v_4262_N(float p_110146_1_, float p_110146_2_) {
        boolean flag;
        float f = u_530_F.v_4262_N(p_110146_1_ - this.C_1162_e);
        this.C_1162_e += f * 0.3f;
        float f1 = u_530_F.v_4262_N(this.p_178_J - this.C_1162_e);
        boolean bl = flag = f1 < -90.0f || f1 >= 90.0f;
        if (f1 < -75.0f) {
            f1 = -75.0f;
        }
        if (f1 >= 75.0f) {
            f1 = 75.0f;
        }
        this.C_1162_e = this.p_178_J - f1;
        if (f1 * f1 > 2500.0f) {
            this.C_1162_e += f1 * 0.2f;
        }
        if (flag) {
            p_110146_2_ *= -1.0f;
        }
        return p_110146_2_;
    }

    public void Y_1740_V() {
        if (this.P_2947_S > 0) {
            --this.P_2947_S;
        }
        if (this.v_887_r()) {
            this.O_1309_Q = 0;
            this.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
        }
        if (this.O_1309_Q > 0) {
            double d0 = this.O_3598_v() + (this.O_2934_T - this.O_3598_v()) / (double)this.O_1309_Q;
            double d2 = this.X_2960_b() + (this.l_4088_R - this.X_2960_b()) / (double)this.O_1309_Q;
            double d4 = this.l_2647_k() + (this.Z_735_d - this.l_2647_k()) / (double)this.O_1309_Q;
            double d6 = u_530_F.u_1723_Y(this.P_925_e - (double)this.p_178_J);
            this.p_178_J = (float)((double)this.p_178_J + d6 / (double)this.O_1309_Q);
            this.f_4016_n = (float)((double)this.f_4016_n + (this.X_4895_T - (double)this.f_4016_n) / (double)this.O_1309_Q);
            --this.O_1309_Q;
            this.J_1907_R(d0, d2, d4);
            this.J_1907_R(this.p_178_J, this.f_4016_n);
        } else if (!this.w_1457_N()) {
            this.v_4262_N(this.I_4348_c().n_1700_B(0.98));
        }
        if (this.n_3197_X > 0) {
            this.f_3449_S = (float)((double)this.f_3449_S + u_530_F.u_1723_Y(this.L_103_L - (double)this.f_3449_S) / (double)this.n_3197_X);
            --this.n_3197_X;
        }
        e_2866_D vector3d = this.I_4348_c();
        double d1 = vector3d.J_1907_R;
        double d3 = vector3d.R_4764_Y;
        double d5 = vector3d.G_564_y;
        if (Math.abs(vector3d.J_1907_R) < 0.003) {
            d1 = 0.0;
        }
        if (Math.abs(vector3d.R_4764_Y) < 0.003) {
            d3 = 0.0;
        }
        if (Math.abs(vector3d.G_564_y) < 0.003) {
            d5 = 0.0;
        }
        this.h_1847_R(d1, d3, d5);
        if (this.W_3729_Q()) {
            this.F_3572_x = false;
            this.L_1362_X = 0.0f;
            this.L_4248_u = 0.0f;
        } else if (this.w_1457_N()) {
            this.H_2857_Y();
        }
        if (this.F_3572_x && this.m_891_U()) {
            double d7 = this.W_3464_O() ? this.J_1907_R(FluidTags.R_4764_Y) : this.J_1907_R(FluidTags.J_1907_R);
            boolean flag = this.RowButton() && d7 > 0.0;
            double d8 = this.i_3196_G();
            if (!flag || this.e_1992_r && !(d7 > d8)) {
                if (!this.W_3464_O() || this.e_1992_r && !(d7 > d8)) {
                    if ((this.e_1992_r || flag && d7 <= d8) && this.P_2947_S == 0) {
                        this.e_837_t();
                        this.P_2947_S = 10;
                    }
                } else {
                    this.R_4764_Y(FluidTags.R_4764_Y);
                }
            } else {
                this.R_4764_Y(FluidTags.J_1907_R);
            }
        } else {
            this.P_2947_S = 0;
        }
        this.L_1362_X *= 0.98f;
        this.L_4248_u *= 0.98f;
        this.k_2293_S();
        I_4817_s axisalignedbb = this.i_601_W();
        this.w_1484_f(new e_2866_D(this.L_1362_X, this.P_5000_x, this.L_4248_u));
        if (this.L_3570_A > 0) {
            --this.L_3570_A;
            this.n_1700_B(axisalignedbb, this.i_601_W());
        }
        this.F_391_H();
        if (!this.O_508_d.Y_259_p && this.e_1231_S() && this.j_2266_I()) {
            this.n_1700_B(P_11_z.w_1484_f, 1.0f);
        }
    }

    public boolean e_1231_S() {
        return false;
    }

    private void k_2293_S() {
        boolean flag = this.v_4262_N(7);
        if (flag && !this.e_1992_r && !this.y_2772_m() && !this.J_1907_R(MobEffects.q_2307_F)) {
            Z_1993_T itemstack = this.J_1907_R(e_1174_E.P_1922_E);
            if (itemstack.J_1907_R() == Items.NyliumBlock && ElytraItem.G_564_y(itemstack)) {
                flag = true;
                if (!this.O_508_d.Y_259_p && (this.F_518_D + 1) % 20 == 0) {
                    itemstack.n_1700_B(1, this, (T p_233652_0_) -> p_233652_0_.R_4764_Y(e_1174_E.P_1922_E));
                }
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        if (!this.O_508_d.Y_259_p) {
            this.J_1907_R(7, flag);
        }
    }

    protected void H_2857_Y() {
    }

    protected void F_391_H() {
        List<N_4263_v> list = this.O_508_d.J_1907_R((N_4263_v)this, this.i_601_W(), I_408_V.n_1700_B(this));
        if (!list.isEmpty()) {
            int i = this.O_508_d.H_1990_U().R_4764_Y(A_2352_Z.w_1457_N);
            if (i > 0 && list.size() > i - 1 && this.RealmsWorldOptions.nextInt(4) == 0) {
                int j = 0;
                for (int k = 0; k < list.size(); ++k) {
                    if (list.get(k).y_2772_m()) continue;
                    ++j;
                }
                if (j > i - 1) {
                    this.n_1700_B(P_11_z.v_4262_N, 6.0f);
                }
            }
            for (int l = 0; l < list.size(); ++l) {
                N_4263_v entity = list.get(l);
                this.Z_875_P(entity);
            }
        }
    }

    protected void n_1700_B(I_4817_s p_204801_1_, I_4817_s p_204801_2_) {
        I_4817_s axisalignedbb = p_204801_1_.union(p_204801_2_);
        List<N_4263_v> list = this.O_508_d.n_1700_B((N_4263_v)this, axisalignedbb);
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                N_4263_v entity = list.get(i);
                if (!(entity instanceof r_4811_B)) continue;
                this.v_4262_N((r_4811_B)entity);
                this.L_3570_A = 0;
                this.v_4262_N(this.I_4348_c().n_1700_B(-0.2));
                break;
            }
        } else if (this.D_60_a) {
            this.L_3570_A = 0;
        }
        if (!this.O_508_d.Y_259_p && this.L_3570_A <= 0) {
            this.R_4764_Y(4, false);
        }
    }

    protected void Z_875_P(N_4263_v entityIn) {
        entityIn.P_1922_E(this);
    }

    protected void v_4262_N(r_4811_B p_204804_1_) {
    }

    public void Q_4569_t(int p_204803_1_) {
        this.L_3570_A = p_204803_1_;
        if (!this.O_508_d.Y_259_p) {
            this.R_4764_Y(4, true);
        }
    }

    public boolean B_3040_x() {
        return (this.l_4537_E.n_1700_B(W_3464_O) & 4) != 0;
    }

    @Override
    public void A_3959_N() {
        N_4263_v entity = this.l_3609_d();
        super.A_3959_N();
        if (entity != null && entity != this.l_3609_d() && !this.O_508_d.Y_259_p) {
            this.n_1700_B(entity);
        }
    }

    @Override
    public void x_607_J() {
        super.x_607_J();
        this.U_1341_G = this.ClientBootstrap;
        this.ClientBootstrap = 0.0f;
        this.U_1241_n = 0.0f;
    }

    @Override
    public void n_1700_B(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.d_2545_n = this.V_118_c;
        this.x_92_N = this.I_1407_m;
        this.i_601_W = this.o_2767_H;
        this.V_118_c = x;
        this.I_1407_m = y;
        this.o_2767_H = z;
        this.O_2934_T = x;
        this.l_4088_R = y;
        this.Z_735_d = z;
        this.P_925_e = yaw;
        this.X_4895_T = pitch;
        this.O_1309_Q = posRotationIncrements;
    }

    @Override
    public void n_1700_B(float yaw, int pitch) {
        this.L_103_L = yaw;
        this.n_3197_X = pitch;
    }

    public void t_1786_h(boolean jumping) {
        this.F_3572_x = jumping;
    }

    public void n_1700_B(n_1494_c item) {
        a_3913_L playerentity;
        a_3913_L a_3913_L2 = playerentity = item.v_4262_N() != null ? this.O_508_d.n_1700_B(item.v_4262_N()) : null;
        if (playerentity instanceof B_4088_l) {
            U_3554_Q.q_4610_l.n_1700_B((B_4088_l)playerentity, item.P_1922_E(), this);
        }
    }

    public void n_1700_B(N_4263_v entityIn, int quantity) {
        if (!entityIn.t_4219_U && !this.O_508_d.Y_259_p && (entityIn instanceof n_1494_c || entityIn instanceof h_384_L || entityIn instanceof n_4637_L)) {
            ((e_3591_l)this.O_508_d).Y_259_p().J_1907_R(entityIn, new X_508_u(entityIn.j_276_v(), this.j_276_v(), quantity));
        }
    }

    public boolean c_3005_b(N_4263_v entityIn) {
        e_2866_D vector3d1;
        e_2866_D vector3d = new e_2866_D(this.O_3598_v(), this.X_2048_Y(), this.l_2647_k());
        return this.O_508_d.n_1700_B(new ClipContext(vector3d, vector3d1 = new e_2866_D(entityIn.O_3598_v(), entityIn.X_2048_Y(), entityIn.l_2647_k()), ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, this)).R_4764_Y() == HitResult.n_1700_B.n_1700_B;
    }

    @Override
    public float R_4764_Y(float partialTicks) {
        return partialTicks == 1.0f ? this.f_3449_S : u_530_F.v_4262_N(partialTicks, this.JsonUtils, this.f_3449_S);
    }

    public float Y_601_j(float partialTickTime) {
        float f = this.RealmsResetNormalWorldScreen - this.s_1671_u;
        if (f < 0.0f) {
            f += 1.0f;
        }
        return this.s_1671_u + f * partialTickTime;
    }

    public boolean w_1457_N() {
        return !this.O_508_d.Y_259_p;
    }

    @Override
    public boolean C_290_v() {
        return !this.t_4219_U;
    }

    @Override
    public boolean w_728_N() {
        I_685_r eventNoPush = new I_685_r(I_685_r.n_1700_B.R_4764_Y);
        A_4115_X.n_1700_B(eventNoPush);
        if (eventNoPush.n_1700_B()) {
            return false;
        }
        return this.RealmsLongRunningMcoTaskScreen() && !this.d_2461_k() && !this.e_();
    }

    @Override
    protected void RealmsCreateRealmScreen() {
        this.Ops = this.RealmsWorldOptions.nextDouble() >= this.J_1907_R(Attributes.R_4764_Y);
    }

    @Override
    public float l_4088_R() {
        return this.f_3449_S;
    }

    @Override
    public void h_1847_R(float rotation) {
        this.f_3449_S = rotation;
    }

    @Override
    public void Q_4569_t(float offset) {
        this.C_1162_e = offset;
    }

    @Override
    protected e_2866_D n_1700_B(b_257_Y.n_1700_B axis, BlockUtil.J_1907_R result) {
        return r_4811_B.t_148_a(super.n_1700_B(axis, result));
    }

    public static e_2866_D t_148_a(e_2866_D p_242288_0_) {
        return new e_2866_D(p_242288_0_.J_1907_R, p_242288_0_.R_4764_Y, 0.0);
    }

    public float U_3823_u() {
        return this.Y_259_p;
    }

    public void Y_259_p(float amount) {
        if (amount < 0.0f) {
            amount = 0.0f;
        }
        this.Y_259_p = amount;
    }

    public void E_4256_w() {
    }

    public void V_1665_T() {
    }

    protected void k_2348_i() {
        this.Q_4569_t = true;
    }

    public abstract k_4231_L d_2169_p();

    public boolean Y_601_j() {
        return (this.l_4537_E.n_1700_B(W_3464_O) & 1) > 0;
    }

    public x_1688_C Q_2552_b() {
        return (this.l_4537_E.n_1700_B(W_3464_O) & 2) > 0 ? x_1688_C.J_1907_R : x_1688_C.n_1700_B;
    }

    private void c_3005_b() {
        if (this.Y_601_j()) {
            if (Z_1993_T.G_564_y(this.R_4764_Y(this.Q_2552_b()), this.O_4761_U)) {
                this.O_4761_U = this.R_4764_Y(this.Q_2552_b());
                this.O_4761_U.J_1907_R(this.O_508_d, this, this.U_144_f());
                if (this.A_4115_X()) {
                    this.n_1700_B(this.O_4761_U, 5);
                }
                if (--this.w_2705_t == 0 && !this.O_508_d.Y_259_p && !this.O_4761_U.P_4830_p()) {
                    this.I_3637_j();
                }
            } else {
                this.Y_259_p();
            }
        }
    }

    private boolean A_4115_X() {
        int i = this.U_144_f();
        FoodProperties food = this.O_4761_U.J_1907_R().Q_2552_b();
        boolean flag = food != null && food.P_1922_E();
        return (flag |= i <= this.O_4761_U.u_2550_I() - 7) && i % 4 == 0;
    }

    private void t_4043_B() {
        this.c_3005_b = this.Z_875_P;
        this.Z_875_P = this.x_612_B() ? Math.min(1.0f, this.Z_875_P + 0.09f) : Math.max(0.0f, this.Z_875_P - 0.09f);
    }

    protected void R_4764_Y(int key, boolean value) {
        int i = this.l_4537_E.n_1700_B(W_3464_O).byteValue();
        i = value ? (i |= key) : (i &= ~key);
        this.l_4537_E.J_1907_R(W_3464_O, (byte)i);
    }

    public void J_1907_R(x_1688_C hand) {
        Z_1993_T itemstack = this.R_4764_Y(hand);
        if (!itemstack.n_1700_B() && !this.Y_601_j()) {
            this.O_4761_U = itemstack;
            this.w_2705_t = itemstack.u_2550_I();
            if (!this.O_508_d.Y_259_p) {
                this.R_4764_Y(1, true);
                this.R_4764_Y(2, hand == x_1688_C.J_1907_R);
            }
        }
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        super.n_1700_B(key);
        if (t_148_a.equals(key)) {
            if (this.O_508_d.Y_259_p) {
                this.f_2403_E().ifPresent(this::n_1700_B);
            }
        } else if (W_3464_O.equals(key) && this.O_508_d.Y_259_p) {
            if (this.Y_601_j() && this.O_4761_U.n_1700_B()) {
                this.O_4761_U = this.R_4764_Y(this.Q_2552_b());
                if (!this.O_4761_U.n_1700_B()) {
                    this.w_2705_t = this.O_4761_U.u_2550_I();
                }
            } else if (!this.Y_601_j() && !this.O_4761_U.n_1700_B()) {
                this.O_4761_U = Z_1993_T.J_1907_R;
                this.w_2705_t = 0;
            }
        }
    }

    @Override
    public void n_1700_B(EntityAnchorArgument.n_1700_B anchor, e_2866_D target) {
        super.n_1700_B(anchor, target);
        this.JsonUtils = this.f_3449_S;
        this.D_4361_a = this.C_1162_e = this.f_3449_S;
    }

    protected void n_1700_B(Z_1993_T stack, int count) {
        if (!stack.n_1700_B() && this.Y_601_j()) {
            if (stack.M_588_G() == F_1573_j.R_4764_Y) {
                this.n_1700_B(this.R_4764_Y(stack), 0.5f, this.O_508_d.w_1457_N.nextFloat() * 0.1f + 0.9f);
            }
            if (stack.M_588_G() == F_1573_j.J_1907_R) {
                this.J_1907_R(stack, count);
                this.n_1700_B(this.G_564_y(stack), 0.5f + 0.5f * (float)this.RealmsWorldOptions.nextInt(2), (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
            }
        }
    }

    private void J_1907_R(Z_1993_T stack, int count) {
        for (int i = 0; i < count; ++i) {
            e_2866_D vector3d = new e_2866_D(((double)this.RealmsWorldOptions.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
            vector3d = vector3d.n_1700_B(-this.f_4016_n * ((float)Math.PI / 180));
            vector3d = vector3d.J_1907_R(-this.p_178_J * ((float)Math.PI / 180));
            double d0 = (double)(-this.RealmsWorldOptions.nextFloat()) * 0.6 - 0.3;
            e_2866_D vector3d1 = new e_2866_D(((double)this.RealmsWorldOptions.nextFloat() - 0.5) * 0.3, d0, 0.6);
            vector3d1 = vector3d1.n_1700_B(-this.f_4016_n * ((float)Math.PI / 180));
            vector3d1 = vector3d1.J_1907_R(-this.p_178_J * ((float)Math.PI / 180));
            vector3d1 = vector3d1.J_1907_R(this.O_3598_v(), this.X_2048_Y(), this.l_2647_k());
            this.O_508_d.n_1700_B(new N_3869_i(ParticleTypes.d_2427_y, stack), vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, vector3d.J_1907_R, vector3d.R_4764_Y + 0.05, vector3d.G_564_y);
        }
    }

    protected void I_3637_j() {
        x_1688_C hand = this.Q_2552_b();
        if (!this.O_4761_U.equals(this.R_4764_Y(hand))) {
            this.g_134_G();
        } else if (!this.O_4761_U.n_1700_B() && this.Y_601_j()) {
            this.n_1700_B(this.O_4761_U, 16);
            Z_1993_T itemstack = this.O_4761_U.n_1700_B(this.O_508_d, this);
            if (itemstack != this.O_4761_U) {
                this.n_1700_B(hand, itemstack);
            }
            this.Y_259_p();
        }
    }

    public Z_1993_T B_2580_P() {
        return this.O_4761_U;
    }

    public int U_144_f() {
        return this.w_2705_t;
    }

    public int g_1031_K() {
        return this.Y_601_j() ? this.O_4761_U.u_2550_I() - this.U_144_f() : 0;
    }

    public void g_134_G() {
        if (!this.O_4761_U.n_1700_B()) {
            this.O_4761_U.n_1700_B(this.O_508_d, this, this.U_144_f());
            if (this.O_4761_U.P_4830_p()) {
                this.c_3005_b();
            }
        }
        this.Y_259_p();
    }

    public void Y_259_p() {
        if (!this.O_508_d.Y_259_p) {
            this.R_4764_Y(1, false);
        }
        this.O_4761_U = Z_1993_T.J_1907_R;
        this.w_2705_t = 0;
    }

    public boolean N_260_m() {
        if (this.Y_601_j() && !this.O_4761_U.n_1700_B()) {
            q_1613_l item = this.O_4761_U.J_1907_R();
            if (item.R_4764_Y(this.O_4761_U) != F_1573_j.G_564_y) {
                return false;
            }
            return item.J_1907_R(this.O_4761_U) - this.w_2705_t >= 5;
        }
        return false;
    }

    public boolean M_182_A(int ticks) {
        if (this.Y_601_j() && !this.O_4761_U.n_1700_B()) {
            q_1613_l item = this.O_4761_U.J_1907_R();
            if (item.R_4764_Y(this.O_4761_U) != F_1573_j.G_564_y) {
                return false;
            }
            return item.J_1907_R(this.O_4761_U) - this.w_2705_t >= ticks;
        }
        return false;
    }

    public boolean q_() {
        return this.q_2307_F();
    }

    public boolean k_578_l() {
        return this.v_4262_N(7);
    }

    @Override
    public boolean x_612_B() {
        return super.x_612_B() || !this.k_578_l() && this.h_4320_q() == I_1170_F.J_1907_R;
    }

    public int h_3859_C() {
        return this.F_518_D;
    }

    public boolean n_1700_B(double x, double y, double z, boolean p_213373_7_) {
        double d0 = this.O_3598_v();
        double d1 = this.X_2960_b();
        double d2 = this.l_2647_k();
        double d3 = y;
        boolean flag = false;
        b_4507_u world = this.O_508_d;
        c_1514_x blockpos = new c_1514_x(x, y, z);
        if (world.M_588_G(blockpos)) {
            boolean flag1 = false;
            while (!flag1 && blockpos.getY() > 0) {
                c_1514_x blockpos1 = blockpos.down();
                K_4074_S blockstate = world.getBlockState(blockpos1);
                if (blockstate.R_4764_Y().R_4764_Y()) {
                    flag1 = true;
                    continue;
                }
                d3 -= 1.0;
                blockpos = blockpos1;
            }
            if (flag1) {
                this.P_4830_p(x, d3, z);
                if (world.u_1723_Y(this) && !world.G_564_y(this.i_601_W())) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            this.P_4830_p(d0, d1, d2);
            return false;
        }
        if (p_213373_7_) {
            world.n_1700_B((N_4263_v)this, (byte)46);
        }
        if (this instanceof PathfinderMob) {
            ((PathfinderMob)this).e_4240_b().h_1847_R();
        }
        return true;
    }

    public boolean F_1446_q() {
        return true;
    }

    public boolean r_4790_y() {
        return true;
    }

    public void n_1700_B(c_1514_x pos, boolean isPartying) {
    }

    public boolean P_1922_E(Z_1993_T itemstackIn) {
        return false;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddMobPacket(this);
    }

    @Override
    public R_1815_U n_1700_B(I_1170_F poseIn) {
        return poseIn == I_1170_F.R_4764_Y ? RealmsConfirmScreen : super.n_1700_B(poseIn).n_1700_B(this.S_4258_d());
    }

    public ImmutableList<I_1170_F> x_2635_q() {
        return ImmutableList.of((Object)((Object)I_1170_F.n_1700_B));
    }

    public I_4817_s u_1723_Y(I_1170_F pose) {
        R_1815_U entitysize = this.n_1700_B(pose);
        return new I_4817_s(-entitysize.n_1700_B / 2.0f, 0.0, -entitysize.n_1700_B / 2.0f, entitysize.n_1700_B / 2.0f, entitysize.J_1907_R, entitysize.n_1700_B / 2.0f);
    }

    public Optional<c_1514_x> f_2403_E() {
        return this.l_4537_E.n_1700_B(t_148_a);
    }

    public void G_564_y(c_1514_x p_213369_1_) {
        this.l_4537_E.J_1907_R(t_148_a, Optional.of(p_213369_1_));
    }

    public void c_776_E() {
        this.l_4537_E.J_1907_R(t_148_a, Optional.empty());
    }

    public boolean z_2372_L() {
        return this.f_2403_E().isPresent();
    }

    public void P_1922_E(c_1514_x pos) {
        K_4074_S blockstate;
        if (this.y_2772_m()) {
            this.A_3959_N();
        }
        if ((blockstate = this.O_508_d.getBlockState(pos)).J_1907_R() instanceof J_2868_p) {
            this.O_508_d.n_1700_B(pos, (K_4074_S)blockstate.n_1700_B(J_2868_p.h_1847_R, true), 3);
        }
        this.J_1907_R(I_1170_F.R_4764_Y);
        this.n_1700_B(pos);
        this.G_564_y(pos);
        this.v_4262_N(e_2866_D.n_1700_B);
        this.LongRunningTask = true;
    }

    private void n_1700_B(c_1514_x p_213370_1_) {
        this.J_1907_R((double)p_213370_1_.getX() + 0.5, (double)p_213370_1_.getY() + 0.6875, (double)p_213370_1_.getZ() + 0.5);
    }

    private boolean e_4240_b() {
        return this.f_2403_E().map(p_241350_1_ -> this.O_508_d.getBlockState((c_1514_x)p_241350_1_).J_1907_R() instanceof J_2868_p).orElse(false);
    }

    public void t_2932_z() {
        this.f_2403_E().filter(this.O_508_d::M_588_G).ifPresent(p_241348_1_ -> {
            K_4074_S blockstate = this.O_508_d.getBlockState((c_1514_x)p_241348_1_);
            if (blockstate.J_1907_R() instanceof J_2868_p) {
                this.O_508_d.n_1700_B((c_1514_x)p_241348_1_, (K_4074_S)blockstate.n_1700_B(J_2868_p.h_1847_R, false), 3);
                e_2866_D vector3d1 = J_2868_p.n_1700_B(this.f_4016_n(), (o_3283_D)this.O_508_d, p_241348_1_, this.p_178_J).orElseGet(() -> {
                    c_1514_x blockpos = p_241348_1_.up();
                    return new e_2866_D((double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.1, (double)blockpos.getZ() + 0.5);
                });
                e_2866_D vector3d2 = e_2866_D.R_4764_Y(p_241348_1_).G_564_y(vector3d1).G_564_y();
                float f = (float)u_530_F.u_1723_Y(u_530_F.G_564_y(vector3d2.G_564_y, vector3d2.J_1907_R) * 57.2957763671875 - 90.0);
                this.J_1907_R(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y);
                this.p_178_J = f;
                this.f_4016_n = 0.0f;
            }
        });
        e_2866_D vector3d = this.s_4990_V();
        this.J_1907_R(I_1170_F.n_1700_B);
        this.J_1907_R(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
        this.c_776_E();
    }

    @Nullable
    public b_257_Y m_3147_m() {
        c_1514_x blockpos = this.f_2403_E().orElse(null);
        return blockpos != null ? J_2868_p.n_1700_B(this.O_508_d, blockpos) : null;
    }

    @Override
    public boolean i_2993_w() {
        return !this.z_2372_L() && super.i_2993_w();
    }

    @Override
    protected final float n_1700_B(I_1170_F poseIn, R_1815_U sizeIn) {
        return poseIn == I_1170_F.R_4764_Y ? 0.2f : this.J_1907_R(poseIn, sizeIn);
    }

    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return super.n_1700_B(poseIn, sizeIn);
    }

    public Z_1993_T u_1723_Y(Z_1993_T shootable) {
        return Z_1993_T.J_1907_R;
    }

    public Z_1993_T n_1700_B(b_4507_u p_213357_1_, Z_1993_T p_213357_2_) {
        if (p_213357_2_.x_607_J()) {
            p_213357_1_.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.G_564_y(p_213357_2_), D_38_f.v_4262_N, 1.0f, 1.0f + (p_213357_1_.w_1457_N.nextFloat() - p_213357_1_.w_1457_N.nextFloat()) * 0.4f);
            this.n_1700_B(p_213357_2_, p_213357_1_, this);
            if (!(this instanceof a_3913_L) || !((a_3913_L)this).C_415_h.G_564_y) {
                p_213357_2_.v_4262_N(1);
            }
        }
        return p_213357_2_;
    }

    private void n_1700_B(Z_1993_T p_213349_1_, b_4507_u p_213349_2_, r_4811_B p_213349_3_) {
        q_1613_l item = p_213349_1_.J_1907_R();
        if (item.Y_259_p()) {
            for (Pair<k_2610_C, Float> pair : item.Q_2552_b().u_1723_Y()) {
                if (p_213349_2_.Y_259_p || pair.getFirst() == null || !(p_213349_2_.w_1457_N.nextFloat() < ((Float)pair.getSecond()).floatValue())) continue;
                p_213349_3_.n_1700_B(new k_2610_C((k_2610_C)pair.getFirst()));
            }
        }
    }

    private static byte u_1723_Y(e_1174_E p_213350_0_) {
        switch (p_213350_0_) {
            case n_1700_B: {
                return 47;
            }
            case J_1907_R: {
                return 48;
            }
            case u_1723_Y: {
                return 49;
            }
            case P_1922_E: {
                return 50;
            }
            case R_4764_Y: {
                return 52;
            }
            case G_564_y: {
                return 51;
            }
        }
        return 47;
    }

    public void R_4764_Y(e_1174_E p_213361_1_) {
        this.O_508_d.n_1700_B((N_4263_v)this, r_4811_B.u_1723_Y(p_213361_1_));
    }

    public void G_564_y(x_1688_C p_213334_1_) {
        this.R_4764_Y(p_213334_1_ == x_1688_C.n_1700_B ? e_1174_E.n_1700_B : e_1174_E.J_1907_R);
    }

    @Override
    public I_4817_s h_2739_B() {
        if (this.J_1907_R(e_1174_E.u_1723_Y).J_1907_R() == Items.EndPortalFrameBlock) {
            float f = 0.5f;
            return this.i_601_W().grow(0.5, 0.5, 0.5);
        }
        return super.h_2739_B();
    }

    public boolean I_1790_n() {
        return this.Y_601_j() && this.O_4761_U.J_1907_R().R_4764_Y(this.O_4761_U) == F_1573_j.G_564_y;
    }

    public boolean C_332_W() {
        return this.Y_601_j() && this.O_4761_U.J_1907_R().R_4764_Y(this.O_4761_U) == F_1573_j.J_1907_R;
    }

    @Generated
    public void Q_2552_b(float jumpMovementFactor) {
        this.y_2772_m = jumpMovementFactor;
    }
}



