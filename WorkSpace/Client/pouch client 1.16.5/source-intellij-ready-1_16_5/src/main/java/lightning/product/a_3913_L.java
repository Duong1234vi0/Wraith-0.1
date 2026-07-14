/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  com.mojang.authlib.GameProfile
 *  com.mojang.datafixers.util.Either
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.A_4115_X;
import lightning.product.A_4313_D;
import lightning.product.FluidTags;
import lightning.product.AxeItem;
import lightning.product.B_4088_l;
import lightning.product.B_4271_P;
import lightning.product.C_3622_I;
import lightning.product.C_4114_x;
import lightning.product.D_38_f;
import lightning.product.D_686_b;
import lightning.product.E_4346_v;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_14_v;
import lightning.product.I_4817_s;
import lightning.product.I_685_r;
import lightning.product.SharedConstants;
import lightning.product.J_2868_p;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.L_3233_K;
import lightning.product.L_461_d;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.O_3598_v;
import lightning.product.P_11_z;
import lightning.product.P_2605_j;
import lightning.product.R_1299_M;
import lightning.product.FoodData;
import lightning.product.R_1815_U;
import lightning.product.R_2450_T;
import lightning.product.R_2515_i;
import lightning.product.MobEffectUtil;
import lightning.product.ParticleOptions;
import lightning.product.T_1368_k;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_2534_D;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.W_1247_f;
import lightning.product.W_3491_f;
import lightning.product.PlayerEnderChestContainer;
import lightning.product.SoundEvent;
import lightning.product.X_1446_C;
import lightning.product.ProjectileWeaponItem;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_2900_S;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.PlayerTeam;
import lightning.product.Abilities;
import lightning.product.Enchantments;
import lightning.product.FreeCam;
import lightning.product.d_742_e;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.g_1462_f;
import lightning.product.g_2336_b;
import lightning.product.MobType;
import lightning.product.ElytraItem;
import lightning.product.h_256_u;
import lightning.product.i_2909_p;
import lightning.product.Recipe;
import lightning.product.i_4895_l;
import lightning.product.MerchantOffers;
import lightning.product.EntityDataSerializers;
import lightning.product.j_2644_e;
import lightning.product.k_2610_C;
import lightning.product.k_4231_L;
import lightning.product.l_697_B;
import lightning.product.m_3054_I;
import lightning.product.ClientboundSetEntityMotionPacket;
import lightning.product.n_1494_c;
import lightning.product.ClientBootstrap;
import lightning.product.o_3050_h;
import lightning.product.o_3283_D;
import lightning.product.BlockInWorld;
import lightning.product.o_98_P;
import lightning.product.JigsawBlockEntity;
import lightning.product.EnderDragonPart;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.r_4879_Z;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_3286_u;
import lightning.product.t_5_h;
import lightning.product.SwordItem;
import lightning.product.u_530_F;
import lightning.product.v_576_m;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import lightning.product.y_4319_k;
import lightning.product.y_6_Q;

public abstract class a_3913_L
extends r_4811_B {
    public static final R_1815_U h_2739_B = R_1815_U.J_1907_R(0.6f, 1.8f);
    private static final Map<I_1170_F, R_1815_U> n_1700_B = ImmutableMap.builder().put((Object)I_1170_F.n_1700_B, (Object)h_2739_B).put((Object)I_1170_F.R_4764_Y, (Object)RealmsConfirmScreen).put((Object)I_1170_F.J_1907_R, (Object)R_1815_U.J_1907_R(0.6f, 0.6f)).put((Object)I_1170_F.G_564_y, (Object)R_1815_U.J_1907_R(0.6f, 0.6f)).put((Object)I_1170_F.P_1922_E, (Object)R_1815_U.J_1907_R(0.6f, 0.6f)).put((Object)I_1170_F.u_1723_Y, (Object)R_1815_U.J_1907_R(0.6f, 1.5f)).put((Object)I_1170_F.v_4262_N, (Object)R_1815_U.R_4764_Y(0.2f, 0.2f)).build();
    private static final h_256_u<Float> J_1907_R = C_4114_x.n_1700_B(a_3913_L.class, EntityDataSerializers.R_4764_Y);
    private static final h_256_u<Integer> R_4764_Y = C_4114_x.n_1700_B(a_3913_L.class, EntityDataSerializers.J_1907_R);
    protected static final h_256_u<Byte> X_1313_W = C_4114_x.n_1700_B(a_3913_L.class, EntityDataSerializers.n_1700_B);
    protected static final h_256_u<Byte> x_4991_F = C_4114_x.n_1700_B(a_3913_L.class, EntityDataSerializers.n_1700_B);
    protected static final h_256_u<U_2912_j> Z_759_W = C_4114_x.n_1700_B(a_3913_L.class, EntityDataSerializers.M_182_A);
    protected static final h_256_u<U_2912_j> f_1574_f = C_4114_x.n_1700_B(a_3913_L.class, EntityDataSerializers.M_182_A);
    private long G_564_y;
    public W_3491_f l_1268_F = new W_3491_f(this);
    protected PlayerEnderChestContainer J_303_C = new PlayerEnderChestContainer();
    public y_6_Q o_1800_r;
    public a_2900_S H_1873_g;
    protected FoodData n_3864_h = new FoodData();
    protected int o_3599_Z;
    public float X_290_I;
    public float O_1795_e;
    public int l_697_B;
    public double d_3244_b;
    public double v_887_r;
    public double l_3609_d;
    public double r_2478_U;
    public double h_2848_I;
    public double A_3244_K;
    private int P_1922_E;
    protected boolean i_3196_G;
    public final Abilities C_415_h = new Abilities();
    public int v_165_F;
    public int s_4990_V;
    public float b_2312_j;
    protected int I_4348_c;
    protected final float O_3598_v = 0.02f;
    private int u_1723_Y;
    private final GameProfile v_4262_N;
    private boolean w_1484_f;
    private Z_1993_T t_148_a = Z_1993_T.J_1907_R;
    private final v_576_m s_956_w = this.n_473_l();
    @Nullable
    public W_1247_f X_2960_b;

    public a_3913_L(b_4507_u p_i241920_1_, c_1514_x p_i241920_2_, float p_i241920_3_, GameProfile p_i241920_4_) {
        super((t_5_h<? extends r_4811_B>)t_5_h.g_4106_L, p_i241920_1_);
        this.a_(a_3913_L.n_1700_B(p_i241920_4_));
        this.v_4262_N = p_i241920_4_;
        this.o_1800_r = new y_6_Q(this.l_1268_F, !p_i241920_1_.Y_259_p, this);
        this.H_1873_g = this.o_1800_r;
        this.J_1907_R((double)p_i241920_2_.getX() + 0.5, p_i241920_2_.getY() + 1, (double)p_i241920_2_.getZ() + 0.5, p_i241920_3_, 0.0f);
        this.x_612_B = 180.0f;
    }

    public boolean n_1700_B(b_4507_u worldIn, c_1514_x pos, I_14_v gameMode) {
        if (!gameMode.G_564_y()) {
            return false;
        }
        if (gameMode == I_14_v.P_1922_E) {
            return true;
        }
        if (this.V_537_k()) {
            return false;
        }
        Z_1993_T itemstack = this.A_2714_y();
        return itemstack.n_1700_B() || !itemstack.n_1700_B(worldIn.M_182_A(), new BlockInWorld(worldIn, pos, false));
    }

    public static s_1415_m.n_1700_B L_3537_K() {
        return r_4811_B.P_4639_N().n_1700_B(Attributes.u_1723_Y, 1.0).n_1700_B(Attributes.G_564_y, 0.1f).n_1700_B(Attributes.w_1484_f).n_1700_B(Attributes.u_2550_I);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(J_1907_R, Float.valueOf(0.0f));
        this.l_4537_E.n_1700_B(R_4764_Y, 0);
        this.l_4537_E.n_1700_B(X_1313_W, (byte)0);
        this.l_4537_E.n_1700_B(x_4991_F, (byte)1);
        this.l_4537_E.n_1700_B(Z_759_W, new U_2912_j());
        this.l_4537_E.n_1700_B(f_1574_f, new U_2912_j());
    }

    @Override
    public void v_() {
        ClientBootstrap pouch = lightning.product.ClientBootstrap.Y_601_j();
        boolean freeCamEnabled = pouch != null && pouch.J_1907_R() != null && pouch.J_1907_R().n_1700_B(FreeCam.class) != null && pouch.J_1907_R().n_1700_B(FreeCam.class).w_1484_f();
        boolean bl = this.j_1564_a = this.d_2461_k() || freeCamEnabled;
        if (this.d_2461_k()) {
            this.e_1992_r = false;
        }
        if (this.l_697_B > 0) {
            --this.l_697_B;
        }
        if (this.z_2372_L()) {
            ++this.P_1922_E;
            if (this.P_1922_E > 100) {
                this.P_1922_E = 100;
            }
            if (!this.O_508_d.Y_259_p && this.O_508_d.q_4610_l()) {
                this.n_1700_B(false, true);
            }
        } else if (this.P_1922_E > 0) {
            ++this.P_1922_E;
            if (this.P_1922_E >= 110) {
                this.P_1922_E = 0;
            }
        }
        this.v_4276_D();
        super.v_();
        if (!this.O_508_d.Y_259_p && this.H_1873_g != null && !this.H_1873_g.n_1700_B(this)) {
            this.P_1922_E();
            this.H_1873_g = this.o_1800_r;
        }
        this.B_1668_F();
        if (!this.O_508_d.Y_259_p) {
            this.n_3864_h.n_1700_B(this);
            this.J_1907_R(Stats.u_2550_I);
            if (this.RealmsLongRunningMcoTaskScreen()) {
                this.J_1907_R(Stats.M_588_G);
            }
            if (this.U_1341_G()) {
                this.J_1907_R(Stats.h_1847_R);
            }
            if (!this.z_2372_L()) {
                this.J_1907_R(Stats.P_4830_p);
            }
        }
        int i = 29999999;
        double d0 = u_530_F.n_1700_B(this.O_3598_v(), -2.9999999E7, 2.9999999E7);
        double d1 = u_530_F.n_1700_B(this.l_2647_k(), -2.9999999E7, 2.9999999E7);
        if (d0 != this.O_3598_v() || d1 != this.l_2647_k()) {
            this.J_1907_R(d0, this.X_2960_b(), d1);
        }
        ++this.C_3538_G;
        Z_1993_T itemstack = this.A_2714_y();
        if (!Z_1993_T.J_1907_R(this.t_148_a, itemstack)) {
            if (!Z_1993_T.G_564_y(this.t_148_a, itemstack)) {
                this.ModuleCategory();
            }
            this.t_148_a = itemstack.t_148_a();
        }
        this.u_1723_Y();
        this.s_956_w.n_1700_B();
        this.b_();
    }

    public boolean z_3000_g() {
        return this.q_2307_F();
    }

    protected boolean n_4915_F() {
        return this.q_2307_F();
    }

    protected boolean y_2622_c() {
        return this.q_2307_F();
    }

    protected boolean v_4276_D() {
        this.i_3196_G = ((N_4263_v)this).n_1700_B(FluidTags.J_1907_R);
        return this.i_3196_G;
    }

    private void u_1723_Y() {
        Z_1993_T itemstack = this.J_1907_R(e_1174_E.u_1723_Y);
        if (itemstack.J_1907_R() == Items.S_315_z && !((N_4263_v)this).n_1700_B(FluidTags.J_1907_R)) {
            this.n_1700_B(new k_2610_C(MobEffects.P_4830_p, 200, 0, false, false, true));
        }
    }

    protected v_576_m n_473_l() {
        return new v_576_m();
    }

    protected void B_1668_F() {
        this.d_3244_b = this.r_2478_U;
        this.v_887_r = this.h_2848_I;
        this.l_3609_d = this.A_3244_K;
        double d0 = this.O_3598_v() - this.r_2478_U;
        double d1 = this.X_2960_b() - this.h_2848_I;
        double d2 = this.l_2647_k() - this.A_3244_K;
        double d3 = 10.0;
        if (d0 > 10.0) {
            this.d_3244_b = this.r_2478_U = this.O_3598_v();
        }
        if (d2 > 10.0) {
            this.l_3609_d = this.A_3244_K = this.l_2647_k();
        }
        if (d1 > 10.0) {
            this.v_887_r = this.h_2848_I = this.X_2960_b();
        }
        if (d0 < -10.0) {
            this.d_3244_b = this.r_2478_U = this.O_3598_v();
        }
        if (d2 < -10.0) {
            this.l_3609_d = this.A_3244_K = this.l_2647_k();
        }
        if (d1 < -10.0) {
            this.v_887_r = this.h_2848_I = this.X_2960_b();
        }
        this.r_2478_U += d0 * 0.25;
        this.A_3244_K += d2 * 0.25;
        this.h_2848_I += d1 * 0.25;
    }

    protected void b_() {
        if (this.R_4764_Y(I_1170_F.G_564_y)) {
            I_1170_F pose = this.k_578_l() ? I_1170_F.J_1907_R : (this.z_2372_L() ? I_1170_F.R_4764_Y : (this.C_1269_X() ? I_1170_F.G_564_y : (this.B_3040_x() ? I_1170_F.P_1922_E : (this.q_2307_F() && !this.C_415_h.J_1907_R ? I_1170_F.u_1723_Y : I_1170_F.n_1700_B))));
            I_1170_F pose1 = !(this.d_2461_k() || this.y_2772_m() || this.R_4764_Y(pose)) ? (this.R_4764_Y(I_1170_F.u_1723_Y) ? I_1170_F.u_1723_Y : I_1170_F.G_564_y) : pose;
            this.J_1907_R(pose1);
        }
    }

    @Override
    public int q_1982_R() {
        return this.C_415_h.n_1700_B ? 1 : 80;
    }

    @Override
    protected SoundEvent F_1410_V() {
        return SoundEvents.i_1479_B;
    }

    @Override
    protected SoundEvent S_4022_R() {
        return SoundEvents.Y_2143_L;
    }

    @Override
    protected SoundEvent l_4537_E() {
        return SoundEvents.U_567_E;
    }

    @Override
    public int C_1162_e() {
        return 10;
    }

    @Override
    public void n_1700_B(SoundEvent soundIn, float volume, float pitch) {
        this.O_508_d.n_1700_B(this, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), soundIn, this.r_2478_U(), volume, pitch);
    }

    public void n_1700_B(SoundEvent p_213823_1_, D_38_f p_213823_2_, float p_213823_3_, float p_213823_4_) {
    }

    @Override
    public D_38_f r_2478_U() {
        return D_38_f.w_1484_f;
    }

    @Override
    protected int h_2848_I() {
        return 20;
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 9) {
            this.I_3637_j();
        } else if (id == 23) {
            this.w_1484_f = false;
        } else if (id == 22) {
            this.w_1484_f = true;
        } else if (id == 43) {
            this.n_1700_B(ParticleTypes.u_1723_Y);
        } else {
            super.n_1700_B(id);
        }
    }

    private void n_1700_B(ParticleOptions p_213824_1_) {
        for (int i = 0; i < 5; ++i) {
            double d0 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            double d1 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            double d2 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            this.O_508_d.n_1700_B(p_213824_1_, this.G_564_y(1.0), this.M_766_z() + 1.0, this.v_4262_N(1.0), d0, d1, d2);
        }
    }

    protected void P_1922_E() {
        this.H_1873_g = this.o_1800_r;
    }

    @Override
    public void x_607_J() {
        if (this.n_4915_F() && this.y_2772_m()) {
            this.A_3959_N();
            this.t_148_a(false);
        } else {
            double d0 = this.O_3598_v();
            double d1 = this.X_2960_b();
            double d2 = this.l_2647_k();
            super.x_607_J();
            this.X_290_I = this.O_1795_e;
            this.O_1795_e = 0.0f;
            this.t_1786_h(this.O_3598_v() - d0, this.X_2960_b() - d1, this.l_2647_k() - d2);
        }
    }

    @Override
    public void k_3961_g() {
        this.J_1907_R(I_1170_F.n_1700_B);
        super.k_3961_g();
        this.t_1786_h(this.L_1733_J());
        this.O_2151_c = 0;
    }

    @Override
    protected void H_2857_Y() {
        super.H_2857_Y();
        this.k_3129_Y();
        this.f_3449_S = this.p_178_J;
        this.u_55_V = this.f_4016_n;
    }

    @Override
    public void Y_1740_V() {
        if (this.o_3599_Z > 0) {
            --this.o_3599_Z;
        }
        if (this.O_508_d.x_607_J() == R_2450_T.n_1700_B && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.t_148_a)) {
            if (this.g_46_E() < this.L_1733_J() && this.RealmsWorldResetDto % 20 == 0) {
                this.n_1700_B(1.0f);
            }
            if (this.n_3864_h.J_1907_R() && this.RealmsWorldResetDto % 10 == 0) {
                this.n_3864_h.n_1700_B(this.n_3864_h.n_1700_B() + 1);
            }
        }
        this.l_1268_F.v_4262_N();
        this.X_290_I = this.O_1795_e;
        super.Y_1740_V();
        this.y_2772_m = 0.02f;
        if (this.o_2341_D()) {
            this.y_2772_m = (float)((double)this.y_2772_m + 0.005999999865889549);
        }
        this.w_1457_N((float)this.J_1907_R(Attributes.G_564_y));
        float f = this.e_1992_r && !this.Z_2812_M() && !this.C_1269_X() ? Math.min(0.1f, u_530_F.n_1700_B(a_3913_L.R_4764_Y(this.I_4348_c()))) : 0.0f;
        this.O_1795_e += (f - this.O_1795_e) * 0.4f;
        if (this.g_46_E() > 0.0f && !this.d_2461_k()) {
            I_4817_s axisalignedbb = this.y_2772_m() && !this.l_3609_d().t_4219_U ? this.i_601_W().union(this.l_3609_d().i_601_W()).grow(1.0, 0.0, 1.0) : this.i_601_W().grow(1.0, 0.5, 1.0);
            List<N_4263_v> list = this.O_508_d.n_1700_B((N_4263_v)this, axisalignedbb);
            for (int i = 0; i < list.size(); ++i) {
                N_4263_v entity = list.get(i);
                if (entity.t_4219_U) continue;
                this.A_4115_X(entity);
            }
        }
        this.s_956_w(this.A_1306_N());
        this.s_956_w(this.D_3612_q());
        if (!this.O_508_d.Y_259_p && (this.U_1241_n > 0.5f || this.RowButton()) || this.C_415_h.J_1907_R || this.z_2372_L()) {
            this.o_4117_e();
        }
    }

    private void s_956_w(@Nullable U_2912_j p_192028_1_) {
        if (!(p_192028_1_ == null || p_192028_1_.P_1922_E("Silent") && p_192028_1_.t_1786_h("Silent") || this.O_508_d.w_1457_N.nextInt(200) != 0)) {
            String s = p_192028_1_.M_588_G("id");
            t_5_h.n_1700_B(s).filter(p_213830_0_ -> p_213830_0_ == t_5_h.O_508_d).ifPresent(p_213834_1_ -> {
                if (!R_1299_M.n_1700_B(this.O_508_d, this)) {
                    this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), R_1299_M.n_1700_B(this.O_508_d, this.O_508_d.w_1457_N), this.r_2478_U(), 1.0f, R_1299_M.n_1700_B(this.O_508_d.w_1457_N));
                }
            });
        }
    }

    private void A_4115_X(N_4263_v entityIn) {
        entityIn.c_(this);
    }

    public int r_4414_L() {
        return this.l_4537_E.n_1700_B(R_4764_Y);
    }

    public void J_1907_R(int scoreIn) {
        this.l_4537_E.J_1907_R(R_4764_Y, scoreIn);
    }

    public void t_1786_h(int scoreIn) {
        int i = this.r_4414_L();
        this.l_4537_E.J_1907_R(R_4764_Y, i + scoreIn);
    }

    @Override
    public void R_4764_Y(P_11_z cause) {
        super.R_4764_Y(cause);
        this.t_4219_U();
        if (!this.d_2461_k()) {
            this.G_564_y(cause);
        }
        if (cause != null) {
            this.h_1847_R(-u_530_F.J_1907_R((this.RealmsParentalConsentScreen + this.p_178_J) * ((float)Math.PI / 180)) * 0.1f, 0.1f, -u_530_F.n_1700_B((this.RealmsParentalConsentScreen + this.p_178_J) * ((float)Math.PI / 180)) * 0.1f);
        } else {
            this.h_1847_R(0.0, 0.1, 0.0);
        }
        this.J_1907_R(Stats.G_624_v);
        this.J_1907_R(Stats.t_148_a.J_1907_R(Stats.M_588_G));
        this.J_1907_R(Stats.t_148_a.J_1907_R(Stats.P_4830_p));
        this.RealmsServerPing();
        this.J_1907_R(0, false);
    }

    @Override
    protected void A_229_v() {
        super.A_229_v();
        if (!this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.R_4764_Y)) {
            this.P_2272_O();
            this.l_1268_F.w_1484_f();
        }
    }

    protected void P_2272_O() {
        for (int i = 0; i < this.l_1268_F.Y_259_p(); ++i) {
            Z_1993_T itemstack = this.l_1268_F.s_956_w(i);
            if (itemstack.n_1700_B() || !K_4096_w.P_1922_E(itemstack)) continue;
            this.l_1268_F.u_2550_I(i);
        }
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        if (damageSourceIn == P_11_z.R_4764_Y) {
            return SoundEvents.p_863_D;
        }
        if (damageSourceIn == P_11_z.w_1484_f) {
            return SoundEvents.q_3115_L;
        }
        return damageSourceIn == P_11_z.Y_259_p ? SoundEvents.E_4612_l : SoundEvents.l_3729_r;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.j_1654_T;
    }

    public boolean n_1700_B(boolean p_225609_1_) {
        return this.n_1700_B(this.l_1268_F.n_1700_B(this.l_1268_F.G_564_y, p_225609_1_ && !this.l_1268_F.R_4764_Y().n_1700_B() ? this.l_1268_F.R_4764_Y().t_4043_B() : 1), false, true) != null;
    }

    @Nullable
    public n_1494_c n_1700_B(Z_1993_T itemStackIn, boolean unused) {
        return this.n_1700_B(itemStackIn, false, unused);
    }

    @Nullable
    public n_1494_c n_1700_B(Z_1993_T droppedItem, boolean dropAround, boolean traceItem) {
        if (droppedItem.n_1700_B()) {
            return null;
        }
        if (this.O_508_d.Y_259_p) {
            this.n_1700_B(x_1688_C.n_1700_B);
        }
        double d0 = this.X_2048_Y() - (double)0.3f;
        n_1494_c itementity = new n_1494_c(this.O_508_d, this.O_3598_v(), d0, this.l_2647_k(), droppedItem);
        itementity.n_1700_B(40);
        if (traceItem) {
            itementity.R_4764_Y(this.w_2705_t());
        }
        if (dropAround) {
            float f = this.RealmsWorldOptions.nextFloat() * 0.5f;
            float f1 = this.RealmsWorldOptions.nextFloat() * ((float)Math.PI * 2);
            itementity.h_1847_R(-u_530_F.n_1700_B(f1) * f, 0.2f, u_530_F.J_1907_R(f1) * f);
        } else {
            float f7 = 0.3f;
            float f8 = u_530_F.n_1700_B(this.f_4016_n * ((float)Math.PI / 180));
            float f2 = u_530_F.J_1907_R(this.f_4016_n * ((float)Math.PI / 180));
            float f3 = u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180));
            float f4 = u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180));
            float f5 = this.RealmsWorldOptions.nextFloat() * ((float)Math.PI * 2);
            float f6 = 0.02f * this.RealmsWorldOptions.nextFloat();
            itementity.h_1847_R((double)(-f3 * f2 * 0.3f) + Math.cos(f5) * (double)f6, -f8 * 0.3f + 0.1f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.1f, (double)(f4 * f2 * 0.3f) + Math.sin(f5) * (double)f6);
        }
        return itementity;
    }

    public float R_4764_Y(K_4074_S state) {
        float f = this.l_1268_F.n_1700_B(state);
        if (f > 1.0f) {
            int i = K_4096_w.u_1723_Y(this);
            Z_1993_T itemstack = this.A_2714_y();
            if (i > 0 && !itemstack.n_1700_B()) {
                f += (float)(i * i + 1);
            }
        }
        if (MobEffectUtil.n_1700_B(this)) {
            f *= 1.0f + (float)(MobEffectUtil.J_1907_R(this) + 1) * 0.2f;
        }
        if (this.J_1907_R(MobEffects.G_564_y)) {
            f *= (switch (this.R_4764_Y(MobEffects.G_564_y).R_4764_Y()) {
                case 0 -> 0.3f;
                case 1 -> 0.09f;
                case 2 -> 0.0027f;
                default -> 8.1E-4f;
            });
        }
        if (((N_4263_v)this).n_1700_B(FluidTags.J_1907_R) && !K_4096_w.w_1484_f(this)) {
            f /= 5.0f;
        }
        if (!this.e_1992_r) {
            f /= 5.0f;
        }
        O_3598_v event = new O_3598_v(state, f);
        A_4115_X.n_1700_B(event);
        if (event.n_1700_B()) {
            f = event.R_4764_Y();
        }
        return f;
    }

    public boolean G_564_y(K_4074_S p_234569_1_) {
        return !p_234569_1_.t_1786_h() || this.l_1268_F.R_4764_Y().J_1907_R(p_234569_1_);
    }

    public float n_1700_B(Z_1993_T stack, K_4074_S blockState) {
        int efficiency;
        float speed = stack.n_1700_B(blockState);
        if (speed > 1.0f && (efficiency = K_4096_w.n_1700_B(Enchantments.Y_601_j, stack)) > 0) {
            speed += (float)(efficiency * efficiency + 1);
        }
        if (MobEffectUtil.n_1700_B(this)) {
            speed *= 1.0f + (float)(MobEffectUtil.J_1907_R(this) + 1) * 0.2f;
        }
        if (this.J_1907_R(MobEffects.G_564_y)) {
            speed *= (switch (this.R_4764_Y(MobEffects.G_564_y).R_4764_Y()) {
                case 0 -> 0.3f;
                case 1 -> 0.09f;
                case 2 -> 0.0027f;
                default -> 8.1E-4f;
            });
        }
        if (((N_4263_v)this).n_1700_B(FluidTags.J_1907_R) && !K_4096_w.w_1484_f(this)) {
            speed /= 5.0f;
        }
        if (!this.e_1992_r) {
            speed /= 5.0f;
        }
        return speed;
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.a_(a_3913_L.n_1700_B(this.v_4262_N));
        q_2896_o listnbt = compound.G_564_y("Inventory", 10);
        this.l_1268_F.J_1907_R(listnbt);
        this.l_1268_F.G_564_y = compound.w_1484_f("SelectedItemSlot");
        this.P_1922_E = compound.v_4262_N("SleepTimer");
        this.b_2312_j = compound.s_956_w("XpP");
        this.v_165_F = compound.w_1484_f("XpLevel");
        this.s_4990_V = compound.w_1484_f("XpTotal");
        this.I_4348_c = compound.w_1484_f("XpSeed");
        if (this.I_4348_c == 0) {
            this.I_4348_c = this.RealmsWorldOptions.nextInt();
        }
        this.J_1907_R(compound.w_1484_f("Score"));
        this.n_3864_h.n_1700_B(compound);
        this.C_415_h.J_1907_R(compound);
        this.n_1700_B(Attributes.G_564_y).n_1700_B(this.C_415_h.J_1907_R());
        if (compound.R_4764_Y("EnderItems", 9)) {
            this.J_303_C.n_1700_B(compound.G_564_y("EnderItems", 10));
        }
        if (compound.R_4764_Y("ShoulderEntityLeft", 10)) {
            this.w_1484_f(compound.M_182_A("ShoulderEntityLeft"));
        }
        if (compound.R_4764_Y("ShoulderEntityRight", 10)) {
            this.t_148_a(compound.M_182_A("ShoulderEntityRight"));
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("DataVersion", SharedConstants.n_1700_B().getWorldVersion());
        compound.n_1700_B("Inventory", this.l_1268_F.n_1700_B(new q_2896_o()));
        compound.J_1907_R("SelectedItemSlot", this.l_1268_F.G_564_y);
        compound.n_1700_B("SleepTimer", (short)this.P_1922_E);
        compound.n_1700_B("XpP", this.b_2312_j);
        compound.J_1907_R("XpLevel", this.v_165_F);
        compound.J_1907_R("XpTotal", this.s_4990_V);
        compound.J_1907_R("XpSeed", this.I_4348_c);
        compound.J_1907_R("Score", this.r_4414_L());
        this.n_3864_h.J_1907_R(compound);
        this.C_415_h.n_1700_B(compound);
        compound.n_1700_B("EnderItems", this.J_303_C.R_4764_Y());
        if (!this.A_1306_N().u_1723_Y()) {
            compound.n_1700_B("ShoulderEntityLeft", this.A_1306_N());
        }
        if (!this.D_3612_q().u_1723_Y()) {
            compound.n_1700_B("ShoulderEntityRight", this.D_3612_q());
        }
    }

    @Override
    public boolean n_1700_B(P_11_z source) {
        if (super.n_1700_B(source)) {
            return true;
        }
        if (source == P_11_z.w_1484_f) {
            return !this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.c_3005_b);
        }
        if (source == P_11_z.u_2550_I) {
            return !this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.H_2857_Y);
        }
        if (source.M_182_A()) {
            return !this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.A_4115_X);
        }
        return false;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        if (this.C_415_h.n_1700_B && !source.w_1484_f()) {
            return false;
        }
        this.UploadTokenCache = 0;
        if (this.Z_2812_M()) {
            return false;
        }
        this.o_4117_e();
        if (source.w_1457_N()) {
            if (this.O_508_d.x_607_J() == R_2450_T.n_1700_B) {
                amount = 0.0f;
            }
            if (this.O_508_d.x_607_J() == R_2450_T.J_1907_R) {
                amount = Math.min(amount / 2.0f + 1.0f, amount);
            }
            if (this.O_508_d.x_607_J() == R_2450_T.G_564_y) {
                amount = amount * 3.0f / 2.0f;
            }
        }
        return amount == 0.0f ? false : super.n_1700_B(source, amount);
    }

    @Override
    protected void G_564_y(r_4811_B entityIn) {
        super.G_564_y(entityIn);
        if (entityIn.A_2714_y().J_1907_R() instanceof AxeItem) {
            this.multiplayerClientSuggestionProvider(true);
        }
    }

    public boolean G_564_y(a_3913_L other) {
        o_3050_h team = this.L_1362_X();
        o_3050_h team1 = other.L_1362_X();
        if (team == null) {
            return true;
        }
        return !team.n_1700_B(team1) ? true : team.v_4262_N();
    }

    @Override
    protected void R_4764_Y(P_11_z damageSource, float damage) {
        this.l_1268_F.n_1700_B(damageSource, damage);
    }

    @Override
    protected void multiplayerClientSuggestionProvider(float damage) {
        if (this.O_4761_U.J_1907_R() == Items.NoteBlock) {
            if (!this.O_508_d.Y_259_p) {
                this.n_1700_B(Stats.R_4764_Y.J_1907_R(this.O_4761_U.J_1907_R()));
            }
            if (damage >= 3.0f) {
                int i = 1 + u_530_F.G_564_y(damage);
                x_1688_C hand = this.Q_2552_b();
                this.O_4761_U.n_1700_B(i, this, (T p_213833_1_) -> p_213833_1_.G_564_y(hand));
                if (this.O_4761_U.n_1700_B()) {
                    if (hand == x_1688_C.n_1700_B) {
                        this.n_1700_B(e_1174_E.n_1700_B, Z_1993_T.J_1907_R);
                    } else {
                        this.n_1700_B(e_1174_E.J_1907_R, Z_1993_T.J_1907_R);
                    }
                    this.O_4761_U = Z_1993_T.J_1907_R;
                    this.n_1700_B(SoundEvents.n_3115_n, 0.8f, 0.8f + this.O_508_d.w_1457_N.nextFloat() * 0.4f);
                }
            }
        }
    }

    @Override
    protected void J_1907_R(P_11_z damageSrc, float damageAmount) {
        if (!this.n_1700_B(damageSrc)) {
            damageAmount = this.G_564_y(damageSrc, damageAmount);
            damageAmount = this.P_1922_E(damageSrc, damageAmount);
            float f2 = Math.max(damageAmount - this.U_3823_u(), 0.0f);
            this.Y_259_p(this.U_3823_u() - (damageAmount - f2));
            float f = damageAmount - f2;
            if (f > 0.0f && f < 3.4028235E37f) {
                this.n_1700_B(Stats.v_4276_D, Math.round(f * 10.0f));
            }
            if (f2 != 0.0f) {
                this.C_2741_M(damageSrc.v_4262_N());
                float f1 = this.g_46_E();
                this.t_1786_h(this.g_46_E() - f2);
                this.i_789_Q().n_1700_B(damageSrc, f1, f2);
                if (f2 < 3.4028235E37f) {
                    this.n_1700_B(Stats.d_2427_y, Math.round(f2 * 10.0f));
                }
            }
        }
    }

    @Override
    protected boolean K_3372_t() {
        return !this.C_415_h.J_1907_R && super.K_3372_t();
    }

    public void n_1700_B(A_4313_D signTile) {
    }

    public void n_1700_B(d_742_e commandBlock) {
    }

    public void n_1700_B(T_1368_k commandBlock) {
    }

    public void n_1700_B(j_2644_e structure) {
    }

    public void n_1700_B(JigsawBlockEntity p_213826_1_) {
    }

    public void n_1700_B(U_2534_D horse, Container inventoryIn) {
    }

    public OptionalInt n_1700_B(@Nullable t_3286_u p_213829_1_) {
        return OptionalInt.empty();
    }

    public void n_1700_B(int containerId, MerchantOffers offers, int level, int xp, boolean p_213818_5_, boolean p_213818_6_) {
    }

    public void n_1700_B(Z_1993_T stack, x_1688_C hand) {
    }

    public m_3054_I n_1700_B(N_4263_v entityToInteractOn, x_1688_C hand) {
        if (this.d_2461_k()) {
            if (entityToInteractOn instanceof t_3286_u) {
                this.n_1700_B((t_3286_u)((Object)entityToInteractOn));
            }
            return m_3054_I.R_4764_Y;
        }
        Z_1993_T itemstack = this.R_4764_Y(hand);
        Z_1993_T itemstack1 = itemstack.t_148_a();
        m_3054_I actionresulttype = entityToInteractOn.n_1700_B(this, hand);
        if (actionresulttype.n_1700_B()) {
            if (this.C_415_h.G_564_y && itemstack == this.R_4764_Y(hand) && itemstack.t_4043_B() < itemstack1.t_4043_B()) {
                itemstack.P_1922_E(itemstack1.t_4043_B());
            }
            return actionresulttype;
        }
        if (!itemstack.n_1700_B() && entityToInteractOn instanceof r_4811_B) {
            m_3054_I actionresulttype1;
            if (this.C_415_h.G_564_y) {
                itemstack = itemstack1;
            }
            if ((actionresulttype1 = itemstack.n_1700_B(this, (r_4811_B)entityToInteractOn, hand)).n_1700_B()) {
                if (itemstack.n_1700_B() && !this.C_415_h.G_564_y) {
                    this.n_1700_B(hand, Z_1993_T.J_1907_R);
                }
                return actionresulttype1;
            }
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    public double O_2151_c() {
        return -0.35;
    }

    @Override
    public void t_() {
        super.t_();
        this.l_1233_K = 0;
    }

    @Override
    protected boolean W_3729_Q() {
        return super.W_3729_Q() || this.z_2372_L();
    }

    @Override
    public boolean m_891_U() {
        return !this.C_415_h.J_1907_R;
    }

    @Override
    protected e_2866_D n_1700_B(e_2866_D vec, L_461_d mover) {
        if (!this.C_415_h.J_1907_R && (mover == L_461_d.n_1700_B || mover == L_461_d.J_1907_R) && this.y_2622_c() && this.h_1847_R()) {
            double d0 = vec.J_1907_R;
            double d1 = vec.G_564_y;
            double d2 = 0.05;
            while (d0 != 0.0 && this.O_508_d.a_(this, this.i_601_W().offset(d0, -this.RealmsServerPing, 0.0))) {
                if (d0 < 0.05 && d0 >= -0.05) {
                    d0 = 0.0;
                    continue;
                }
                if (d0 > 0.0) {
                    d0 -= 0.05;
                    continue;
                }
                d0 += 0.05;
            }
            while (d1 != 0.0 && this.O_508_d.a_(this, this.i_601_W().offset(0.0, -this.RealmsServerPing, d1))) {
                if (d1 < 0.05 && d1 >= -0.05) {
                    d1 = 0.0;
                    continue;
                }
                if (d1 > 0.0) {
                    d1 -= 0.05;
                    continue;
                }
                d1 += 0.05;
            }
            while (d0 != 0.0 && d1 != 0.0 && this.O_508_d.a_(this, this.i_601_W().offset(d0, -this.RealmsServerPing, d1))) {
                d0 = d0 < 0.05 && d0 >= -0.05 ? 0.0 : (d0 > 0.0 ? (d0 -= 0.05) : (d0 += 0.05));
                if (d1 < 0.05 && d1 >= -0.05) {
                    d1 = 0.0;
                    continue;
                }
                if (d1 > 0.0) {
                    d1 -= 0.05;
                    continue;
                }
                d1 += 0.05;
            }
            vec = new e_2866_D(d0, vec.R_4764_Y, d1);
        }
        return vec;
    }

    private boolean h_1847_R() {
        return this.e_1992_r || this.U_1241_n < this.RealmsServerPing && !this.O_508_d.a_(this, this.i_601_W().offset(0.0, this.U_1241_n - this.RealmsServerPing, 0.0));
    }

    public void H_2857_Y(N_4263_v targetEntity) {
        if (targetEntity.Z_735_d() && !targetEntity.t_1786_h(this)) {
            float f = (float)this.J_1907_R(Attributes.u_1723_Y);
            float f1 = targetEntity instanceof r_4811_B ? K_4096_w.n_1700_B(this.A_2714_y(), ((r_4811_B)targetEntity).F_2860_q()) : K_4096_w.n_1700_B(this.A_2714_y(), MobType.n_1700_B);
            float f2 = this.k_2293_S(0.5f);
            f1 *= f2;
            this.ModuleCategory();
            if ((f *= 0.2f + f2 * f2 * 0.8f) > 0.0f || f1 > 0.0f) {
                Z_1993_T itemstack;
                boolean flag = f2 > 0.9f;
                boolean flag1 = false;
                int i = 0;
                i += K_4096_w.J_1907_R(this);
                if (this.o_2341_D() && flag) {
                    this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.m_38_G, this.r_2478_U(), 1.0f, 1.0f);
                    ++i;
                    flag1 = true;
                }
                boolean flag2 = flag && this.U_1241_n > 0.0f && !this.e_1992_r && !this.e_() && !this.RowButton() && !this.J_1907_R(MobEffects.Q_4569_t) && !this.y_2772_m() && targetEntity instanceof r_4811_B;
                boolean bl = flag2 = flag2 && !this.o_2341_D();
                if (flag2) {
                    f *= 1.5f;
                }
                f += f1;
                boolean flag3 = false;
                double d0 = this.PlayerInfo - this.V_1446_Y;
                if (flag && !flag2 && !flag1 && this.e_1992_r && d0 < (double)this.l_2995_s() && (itemstack = this.R_4764_Y(x_1688_C.n_1700_B)).J_1907_R() instanceof SwordItem) {
                    flag3 = true;
                }
                float f4 = 0.0f;
                boolean flag4 = false;
                int j = K_4096_w.R_4764_Y(this);
                if (targetEntity instanceof r_4811_B) {
                    f4 = ((r_4811_B)targetEntity).g_46_E();
                    if (j > 0 && !targetEntity.RealmsPersistence()) {
                        flag4 = true;
                        targetEntity.P_1922_E(1);
                    }
                }
                e_2866_D vector3d = targetEntity.I_4348_c();
                boolean flag5 = targetEntity.n_1700_B(P_11_z.n_1700_B(this), f);
                if (flag5) {
                    if (i > 0) {
                        if (targetEntity instanceof r_4811_B) {
                            ((r_4811_B)targetEntity).n_1700_B((float)i * 0.5f, (double)u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180)), (double)(-u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180))));
                        } else {
                            targetEntity.w_1484_f(-u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180)) * (float)i * 0.5f, 0.1, u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180)) * (float)i * 0.5f);
                        }
                        if (!this.k_578_l()) {
                            r_4879_Z event = new r_4879_Z();
                            A_4115_X.n_1700_B(event);
                            if (!event.n_1700_B()) {
                                this.v_4262_N(this.I_4348_c().G_564_y(0.6, 1.0, 0.6));
                                this.b_(false);
                            }
                        }
                    }
                    if (flag3) {
                        float f3 = 1.0f + K_4096_w.n_1700_B(this) * f;
                        for (r_4811_B livingentity : this.O_508_d.n_1700_B(r_4811_B.class, targetEntity.i_601_W().grow(1.0, 0.25, 1.0))) {
                            if (livingentity == this || livingentity == targetEntity || this.Q_4569_t(livingentity) || livingentity instanceof D_686_b && ((D_686_b)livingentity).Q_4569_t() || !(this.G_564_y((N_4263_v)livingentity) < 9.0)) continue;
                            livingentity.n_1700_B(0.4f, (double)u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180)), (double)(-u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180))));
                            livingentity.n_1700_B(P_11_z.n_1700_B(this), f3);
                        }
                        this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.u_488_m, this.r_2478_U(), 1.0f, 1.0f);
                        this.S_2828_i();
                    }
                    if (targetEntity instanceof B_4088_l && targetEntity.Ops) {
                        ((B_4088_l)targetEntity).n_1700_B.n_1700_B(new ClientboundSetEntityMotionPacket(targetEntity));
                        targetEntity.Ops = false;
                        targetEntity.v_4262_N(vector3d);
                    }
                    if (flag2) {
                        l_697_B eventCrit = new l_697_B(targetEntity);
                        A_4115_X.n_1700_B(eventCrit);
                        this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.h_3066_J, this.r_2478_U(), 1.0f, 1.0f);
                        this.n_1700_B(targetEntity);
                    }
                    if (!flag2 && !flag3) {
                        if (flag) {
                            this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.m_4644_u, this.r_2478_U(), 1.0f, 1.0f);
                        } else {
                            this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.O_1043_U, this.r_2478_U(), 1.0f, 1.0f);
                        }
                    }
                    if (f1 > 0.0f) {
                        this.J_1907_R(targetEntity);
                    }
                    this.C_2741_M(targetEntity);
                    if (targetEntity instanceof r_4811_B) {
                        K_4096_w.n_1700_B((r_4811_B)targetEntity, (N_4263_v)this);
                    }
                    K_4096_w.J_1907_R(this, targetEntity);
                    Z_1993_T itemstack1 = this.A_2714_y();
                    N_4263_v entity = targetEntity;
                    if (targetEntity instanceof EnderDragonPart) {
                        entity = ((EnderDragonPart)targetEntity).n_1700_B;
                    }
                    if (!this.O_508_d.Y_259_p && !itemstack1.n_1700_B() && entity instanceof r_4811_B) {
                        itemstack1.n_1700_B((r_4811_B)entity, this);
                        if (itemstack1.n_1700_B()) {
                            this.n_1700_B(x_1688_C.n_1700_B, Z_1993_T.J_1907_R);
                        }
                    }
                    if (targetEntity instanceof r_4811_B) {
                        float f5 = f4 - ((r_4811_B)targetEntity).g_46_E();
                        this.n_1700_B(Stats.x_607_J, Math.round(f5 * 10.0f));
                        if (j > 0) {
                            targetEntity.P_1922_E(j * 4);
                        }
                        if (this.O_508_d instanceof e_3591_l && f5 > 2.0f) {
                            int k = (int)((double)f5 * 0.5);
                            ((e_3591_l)this.O_508_d).n_1700_B(ParticleTypes.w_1484_f, targetEntity.O_3598_v(), targetEntity.P_1922_E(0.5), targetEntity.l_2647_k(), k, 0.1, 0.0, 0.1, 0.2);
                        }
                    }
                    this.C_2741_M(0.1f);
                } else {
                    this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.k_4946_A, this.r_2478_U(), 1.0f, 1.0f);
                    if (flag4) {
                        targetEntity.RealmsServerPing();
                    }
                }
            }
        }
    }

    @Override
    protected void v_4262_N(r_4811_B p_204804_1_) {
        this.H_2857_Y(p_204804_1_);
    }

    public void multiplayerClientSuggestionProvider(boolean p_190777_1_) {
        float f = 0.25f + (float)K_4096_w.u_1723_Y(this) * 0.05f;
        if (p_190777_1_) {
            f += 0.75f;
        }
        if (this.RealmsWorldOptions.nextFloat() < f) {
            this.p_1458_L().n_1700_B(Items.NoteBlock, 100);
            this.Y_259_p();
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)30);
        }
    }

    public void n_1700_B(N_4263_v entityHit) {
    }

    public void J_1907_R(N_4263_v entityHit) {
    }

    public void S_2828_i() {
        double d0 = -u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180));
        double d1 = u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180));
        if (this.O_508_d instanceof e_3591_l) {
            ((e_3591_l)this.O_508_d).n_1700_B(ParticleTypes.H_1990_U, this.O_3598_v() + d0, this.P_1922_E(0.5), this.l_2647_k() + d1, 0, d0, 0.0, d1, 0.0);
        }
    }

    public void G_564_y() {
    }

    @Override
    public void Ops() {
        super.Ops();
        this.o_1800_r.J_1907_R(this);
        if (this.H_1873_g != null) {
            this.H_1873_g.J_1907_R(this);
        }
    }

    public boolean w_1484_f() {
        return false;
    }

    public GameProfile y_4642_Y() {
        return this.v_4262_N;
    }

    public Either<n_1700_B, X_1446_C> n_1700_B(c_1514_x at) {
        this.P_1922_E(at);
        this.P_1922_E = 0;
        return Either.right((Object)((Object)X_1446_C.n_1700_B));
    }

    public void n_1700_B(boolean p_225652_1_, boolean p_225652_2_) {
        super.t_2932_z();
        if (this.O_508_d instanceof e_3591_l && p_225652_2_) {
            ((e_3591_l)this.O_508_d).u_1723_Y();
        }
        this.P_1922_E = p_225652_1_ ? 0 : 100;
    }

    @Override
    public void t_2932_z() {
        this.n_1700_B(true, true);
    }

    public static Optional<e_2866_D> n_1700_B(e_3591_l p_242374_0_, c_1514_x p_242374_1_, float p_242374_2_, boolean p_242374_3_, boolean p_242374_4_) {
        K_4074_S blockstate = p_242374_0_.getBlockState(p_242374_1_);
        T_2915_h block = blockstate.J_1907_R();
        if (block instanceof P_2605_j && blockstate.R_4764_Y(P_2605_j.P_4830_p) > 0 && P_2605_j.n_1700_B(p_242374_0_)) {
            Optional<e_2866_D> optional = P_2605_j.n_1700_B(t_5_h.g_4106_L, p_242374_0_, p_242374_1_);
            if (!p_242374_4_ && optional.isPresent()) {
                p_242374_0_.n_1700_B(p_242374_1_, (K_4074_S)blockstate.n_1700_B(P_2605_j.P_4830_p, blockstate.R_4764_Y(P_2605_j.P_4830_p) - 1), 3);
            }
            return optional;
        }
        if (block instanceof J_2868_p && J_2868_p.n_1700_B(p_242374_0_)) {
            return J_2868_p.n_1700_B(t_5_h.g_4106_L, (o_3283_D)p_242374_0_, p_242374_1_, p_242374_2_);
        }
        if (!p_242374_3_) {
            return Optional.empty();
        }
        boolean flag = block.n_1700_B();
        boolean flag1 = p_242374_0_.getBlockState(p_242374_1_.up()).J_1907_R().n_1700_B();
        return flag && flag1 ? Optional.of(new e_2866_D((double)p_242374_1_.getX() + 0.5, (double)p_242374_1_.getY() + 0.1, (double)p_242374_1_.getZ() + 0.5)) : Optional.empty();
    }

    public boolean h_1640_b() {
        return this.z_2372_L() && this.P_1922_E >= 100;
    }

    public int V_1176_p() {
        return this.P_1922_E;
    }

    public void n_1700_B(x_282_a chatComponent, boolean actionBar) {
    }

    public void J_1907_R(g_2336_b stat) {
        this.n_1700_B(Stats.t_148_a.J_1907_R(stat));
    }

    public void n_1700_B(g_2336_b p_195067_1_, int p_195067_2_) {
        this.n_1700_B(Stats.t_148_a.J_1907_R(p_195067_1_), p_195067_2_);
    }

    public void n_1700_B(o_98_P<?> stat) {
        this.n_1700_B(stat, 1);
    }

    public void n_1700_B(o_98_P<?> stat, int amount) {
    }

    public void J_1907_R(o_98_P<?> stat) {
    }

    public int J_1907_R(Collection<Recipe<?>> p_195065_1_) {
        return 0;
    }

    public void n_1700_B(g_2336_b[] p_193102_1_) {
    }

    public int R_4764_Y(Collection<Recipe<?>> p_195069_1_) {
        return 0;
    }

    @Override
    public void e_837_t() {
        super.e_837_t();
        this.J_1907_R(Stats.Y_1740_V);
        if (this.o_2341_D()) {
            this.C_2741_M(0.2f);
        } else {
            this.C_2741_M(0.05f);
        }
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        double d0 = this.O_3598_v();
        double d1 = this.X_2960_b();
        double d2 = this.l_2647_k();
        if (this.C_1269_X() && !this.y_2772_m()) {
            double d4;
            double d3 = this.RealmsSettingsScreen().R_4764_Y;
            double d = d4 = d3 < -0.2 ? 0.085 : 0.06;
            if (d3 <= 0.0 || this.F_3572_x || !this.O_508_d.getBlockState(new c_1514_x(this.O_3598_v(), this.X_2960_b() + 1.0 - 0.1, this.l_2647_k())).P_4830_p().R_4764_Y()) {
                e_2866_D vector3d1 = this.I_4348_c();
                this.v_4262_N(vector3d1.J_1907_R(0.0, (d3 - vector3d1.R_4764_Y) * d4, 0.0));
            }
        }
        if (this.C_415_h.J_1907_R && !this.y_2772_m()) {
            double d5 = this.I_4348_c().R_4764_Y;
            float f = this.y_2772_m;
            this.y_2772_m = this.C_415_h.n_1700_B() * (float)(this.o_2341_D() ? 2 : 1);
            super.w_1484_f(travelVector);
            e_2866_D vector3d = this.I_4348_c();
            this.h_1847_R(vector3d.J_1907_R, d5 * 0.6, vector3d.G_564_y);
            this.y_2772_m = f;
            this.U_1241_n = 0.0f;
            this.J_1907_R(7, false);
        } else {
            super.w_1484_f(travelVector);
        }
        this.M_182_A(this.O_3598_v() - d0, this.X_2960_b() - d1, this.l_2647_k() - d2);
    }

    @Override
    public void R_3077_Z() {
        if (this.C_415_h.J_1907_R) {
            this.s_956_w(false);
        } else {
            super.R_3077_Z();
        }
    }

    protected boolean u_1723_Y(c_1514_x pos) {
        return !this.O_508_d.getBlockState(pos).Q_4569_t(this.O_508_d, pos);
    }

    @Override
    public float l_2995_s() {
        return (float)this.J_1907_R(Attributes.G_564_y);
    }

    public void M_182_A(double p_71000_1_, double p_71000_3_, double p_71000_5_) {
        if (!this.y_2772_m()) {
            if (this.C_1269_X()) {
                int i = Math.round(u_530_F.n_1700_B(p_71000_1_ * p_71000_1_ + p_71000_3_ * p_71000_3_ + p_71000_5_ * p_71000_5_) * 100.0f);
                if (i > 0) {
                    this.n_1700_B(Stats.H_2857_Y, i);
                    this.C_2741_M(0.01f * (float)i * 0.01f);
                }
            } else if (((N_4263_v)this).n_1700_B(FluidTags.J_1907_R)) {
                int j = Math.round(u_530_F.n_1700_B(p_71000_1_ * p_71000_1_ + p_71000_3_ * p_71000_3_ + p_71000_5_ * p_71000_5_) * 100.0f);
                if (j > 0) {
                    this.n_1700_B(Stats.Q_2552_b, j);
                    this.C_2741_M(0.01f * (float)j * 0.01f);
                }
            } else if (this.RowButton()) {
                int k = Math.round(u_530_F.n_1700_B(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0f);
                if (k > 0) {
                    this.n_1700_B(Stats.multiplayerClientSuggestionProvider, k);
                    this.C_2741_M(0.01f * (float)k * 0.01f);
                }
            } else if (this.e_()) {
                if (p_71000_3_ > 0.0) {
                    this.n_1700_B(Stats.Y_601_j, (int)Math.round(p_71000_3_ * 100.0));
                }
            } else if (this.e_1992_r) {
                int l = Math.round(u_530_F.n_1700_B(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0f);
                if (l > 0) {
                    if (this.o_2341_D()) {
                        this.n_1700_B(Stats.t_1786_h, l);
                        this.C_2741_M(0.1f * (float)l * 0.01f);
                    } else if (this.Z_875_P()) {
                        this.n_1700_B(Stats.M_182_A, l);
                        this.C_2741_M(0.0f * (float)l * 0.01f);
                    } else {
                        this.n_1700_B(Stats.Q_4569_t, l);
                        this.C_2741_M(0.0f * (float)l * 0.01f);
                    }
                }
            } else if (this.k_578_l()) {
                int i1 = Math.round(u_530_F.n_1700_B(p_71000_1_ * p_71000_1_ + p_71000_3_ * p_71000_3_ + p_71000_5_ * p_71000_5_) * 100.0f);
                this.n_1700_B(Stats.c_3005_b, i1);
            } else {
                int j1 = Math.round(u_530_F.n_1700_B(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0f);
                if (j1 > 25) {
                    this.n_1700_B(Stats.Y_259_p, j1);
                }
            }
        }
    }

    private void t_1786_h(double p_71015_1_, double p_71015_3_, double p_71015_5_) {
        int i;
        if (this.y_2772_m() && (i = Math.round(u_530_F.n_1700_B(p_71015_1_ * p_71015_1_ + p_71015_3_ * p_71015_3_ + p_71015_5_ * p_71015_5_) * 100.0f)) > 0) {
            N_4263_v entity = this.l_3609_d();
            if (entity instanceof y_4319_k) {
                this.n_1700_B(Stats.C_2741_M, i);
            } else if (entity instanceof g_1462_f) {
                this.n_1700_B(Stats.k_2293_S, i);
            } else if (entity instanceof B_4271_P) {
                this.n_1700_B(Stats.q_2307_F, i);
            } else if (entity instanceof U_2534_D) {
                this.n_1700_B(Stats.Z_875_P, i);
            } else if (entity instanceof L_3233_K) {
                this.n_1700_B(Stats.A_4115_X, i);
            }
        }
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        if (this.C_415_h.R_4764_Y) {
            return false;
        }
        if (distance >= 2.0f) {
            this.n_1700_B(Stats.w_1457_N, (int)Math.round((double)distance * 100.0));
        }
        return super.R_4764_Y(distance, damageMultiplier);
    }

    public boolean y_2447_C() {
        Z_1993_T itemstack;
        if (!(this.e_1992_r || this.k_578_l() || this.RowButton() || this.J_1907_R(MobEffects.q_2307_F) || (itemstack = this.J_1907_R(e_1174_E.P_1922_E)).J_1907_R() != Items.NyliumBlock || !ElytraItem.G_564_y(itemstack))) {
            this.J_3635_s();
            return true;
        }
        return false;
    }

    public void J_3635_s() {
        this.J_1907_R(7, true);
    }

    public void o_82_k() {
        this.J_1907_R(7, true);
        this.J_1907_R(7, false);
    }

    @Override
    protected void c_132_F() {
        if (!this.d_2461_k()) {
            super.c_132_F();
        }
    }

    @Override
    protected SoundEvent M_588_G(int heightIn) {
        return heightIn > 4 ? SoundEvents.v_1900_v : SoundEvents.U_3443_A;
    }

    @Override
    public void n_1700_B(e_3591_l p_241847_1_, r_4811_B p_241847_2_) {
        this.n_1700_B(Stats.v_4262_N.J_1907_R(p_241847_2_.f_4016_n()));
    }

    @Override
    public void n_1700_B(K_4074_S state, e_2866_D motionMultiplierIn) {
        if (!this.C_415_h.J_1907_R) {
            super.n_1700_B(state, motionMultiplierIn);
        }
    }

    public void multiplayerClientSuggestionProvider(int p_195068_1_) {
        this.t_1786_h(p_195068_1_);
        this.b_2312_j += (float)p_195068_1_ / (float)this.f_2787_O();
        this.s_4990_V = u_530_F.n_1700_B(this.s_4990_V + p_195068_1_, 0, Integer.MAX_VALUE);
        while (this.b_2312_j < 0.0f) {
            float f = this.b_2312_j * (float)this.f_2787_O();
            if (this.v_165_F > 0) {
                this.w_1457_N(-1);
                this.b_2312_j = 1.0f + f / (float)this.f_2787_O();
                continue;
            }
            this.w_1457_N(-1);
            this.b_2312_j = 0.0f;
        }
        while (this.b_2312_j >= 1.0f) {
            this.b_2312_j = (this.b_2312_j - 1.0f) * (float)this.f_2787_O();
            this.w_1457_N(1);
            this.b_2312_j /= (float)this.f_2787_O();
        }
    }

    public int h_973_D() {
        return this.I_4348_c;
    }

    public void J_1907_R(Z_1993_T enchantedItem, int cost) {
        this.v_165_F -= cost;
        if (this.v_165_F < 0) {
            this.v_165_F = 0;
            this.b_2312_j = 0.0f;
            this.s_4990_V = 0;
        }
        this.I_4348_c = this.RealmsWorldOptions.nextInt();
    }

    public void w_1457_N(int levels) {
        this.v_165_F += levels;
        if (this.v_165_F < 0) {
            this.v_165_F = 0;
            this.b_2312_j = 0.0f;
            this.s_4990_V = 0;
        }
        if (levels > 0 && this.v_165_F % 5 == 0 && (float)this.u_1723_Y < (float)this.RealmsWorldResetDto - 100.0f) {
            float f = this.v_165_F > 30 ? 1.0f : (float)this.v_165_F / 30.0f;
            this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.v_143_j, this.r_2478_U(), f * 0.75f, 1.0f);
            this.u_1723_Y = this.RealmsWorldResetDto;
        }
    }

    public int f_2787_O() {
        if (this.v_165_F >= 30) {
            return 112 + (this.v_165_F - 30) * 9;
        }
        return this.v_165_F >= 15 ? 37 + (this.v_165_F - 15) * 5 : 7 + this.v_165_F * 2;
    }

    public void C_2741_M(float exhaustion) {
        if (!this.C_415_h.n_1700_B && !this.O_508_d.Y_259_p) {
            this.n_3864_h.n_1700_B(exhaustion);
        }
    }

    public FoodData P_2295_B() {
        return this.n_3864_h;
    }

    public boolean w_1457_N(boolean ignoreHunger) {
        return this.C_415_h.n_1700_B || ignoreHunger || this.n_3864_h.J_1907_R();
    }

    public boolean U_1697_c() {
        return this.g_46_E() > 0.0f && this.g_46_E() < this.L_1733_J();
    }

    public boolean V_537_k() {
        return this.C_415_h.P_1922_E;
    }

    public boolean n_1700_B(c_1514_x pos, b_257_Y facing, Z_1993_T stack) {
        if (this.C_415_h.P_1922_E) {
            return true;
        }
        c_1514_x blockpos = pos.offset(facing.u_1723_Y());
        BlockInWorld cachedblockinfo = new BlockInWorld(this.O_508_d, blockpos, false);
        return stack.J_1907_R(this.O_508_d.M_182_A(), cachedblockinfo);
    }

    @Override
    protected int R_4764_Y(a_3913_L player) {
        if (!this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.R_4764_Y) && !this.d_2461_k()) {
            int i = this.v_165_F * 7;
            return i > 100 ? 100 : i;
        }
        return 0;
    }

    @Override
    protected boolean h_3270_j() {
        return true;
    }

    @Override
    public boolean I_1407_m() {
        return true;
    }

    @Override
    protected boolean RetryCallException() {
        return !this.C_415_h.J_1907_R && (!this.e_1992_r || !this.U_1341_G());
    }

    public void v_4262_N() {
    }

    public void n_1700_B(I_14_v gameType) {
    }

    @Override
    public x_282_a O_1309_Q() {
        return new U_2871_b(this.v_4262_N.getName());
    }

    public PlayerEnderChestContainer c_2086_l() {
        return this.J_303_C;
    }

    @Override
    public Z_1993_T J_1907_R(e_1174_E slotIn) {
        if (slotIn == e_1174_E.n_1700_B) {
            return this.l_1268_F.R_4764_Y();
        }
        if (slotIn == e_1174_E.J_1907_R) {
            return this.l_1268_F.R_4764_Y.get(0);
        }
        return slotIn.n_1700_B() == e_1174_E.n_1700_B.J_1907_R ? this.l_1268_F.J_1907_R.get(slotIn.J_1907_R()) : Z_1993_T.J_1907_R;
    }

    @Override
    public void n_1700_B(e_1174_E slotIn, Z_1993_T stack) {
        if (slotIn == e_1174_E.n_1700_B) {
            this.J_1907_R(stack);
            this.l_1268_F.n_1700_B.set(this.l_1268_F.G_564_y, stack);
        } else if (slotIn == e_1174_E.J_1907_R) {
            this.J_1907_R(stack);
            this.l_1268_F.R_4764_Y.set(0, stack);
        } else if (slotIn.n_1700_B() == e_1174_E.n_1700_B.J_1907_R) {
            this.J_1907_R(stack);
            this.l_1268_F.J_1907_R.set(slotIn.J_1907_R(), stack);
        }
    }

    public boolean v_4262_N(Z_1993_T p_191521_1_) {
        this.J_1907_R(p_191521_1_);
        return this.l_1268_F.P_1922_E(p_191521_1_);
    }

    @Override
    public Iterable<Z_1993_T> f_3449_S() {
        return Lists.newArrayList((Object[])new Z_1993_T[]{this.A_2714_y(), this.S_4035_N()});
    }

    @Override
    public Iterable<Z_1993_T> u_55_V() {
        return this.l_1268_F.J_1907_R;
    }

    public boolean v_4262_N(U_2912_j p_192027_1_) {
        if (!this.y_2772_m() && this.e_1992_r && !this.RowButton()) {
            if (this.A_1306_N().u_1723_Y()) {
                this.w_1484_f(p_192027_1_);
                this.G_564_y = this.O_508_d.X_933_l();
                return true;
            }
            if (this.D_3612_q().u_1723_Y()) {
                this.t_148_a(p_192027_1_);
                this.G_564_y = this.O_508_d.X_933_l();
                return true;
            }
            return false;
        }
        return false;
    }

    protected void o_4117_e() {
        if (this.G_564_y + 20L < this.O_508_d.X_933_l()) {
            this.u_2550_I(this.A_1306_N());
            this.w_1484_f(new U_2912_j());
            this.u_2550_I(this.D_3612_q());
            this.t_148_a(new U_2912_j());
        }
    }

    private void u_2550_I(U_2912_j p_192026_1_) {
        if (!this.O_508_d.Y_259_p && !p_192026_1_.u_1723_Y()) {
            t_5_h.n_1700_B(p_192026_1_, this.O_508_d).ifPresent(p_226562_1_ -> {
                if (p_226562_1_ instanceof C_3622_I) {
                    ((C_3622_I)p_226562_1_).J_1907_R(this.RealmsScreenWithCallback);
                }
                p_226562_1_.J_1907_R(this.O_3598_v(), this.X_2960_b() + (double)0.7f, this.l_2647_k());
                ((e_3591_l)this.O_508_d).G_564_y((N_4263_v)p_226562_1_);
            });
        }
    }

    @Override
    public abstract boolean d_2461_k();

    @Override
    public boolean C_1269_X() {
        return !this.C_415_h.J_1907_R && !this.d_2461_k() && super.C_1269_X();
    }

    public abstract boolean G_624_v();

    @Override
    public boolean Y_776_s() {
        I_685_r eventNoPush = new I_685_r(I_685_r.n_1700_B.J_1907_R);
        A_4115_X.n_1700_B(eventNoPush);
        if (!eventNoPush.n_1700_B()) {
            return !this.C_415_h.J_1907_R;
        }
        return false;
    }

    public i_4895_l U_3758_B() {
        return this.O_508_d.Q_4569_t();
    }

    @Override
    public x_282_a c_() {
        MutableComponent iformattabletextcomponent = PlayerTeam.n_1700_B(this.L_1362_X(), this.O_1309_Q());
        return this.n_1700_B(iformattabletextcomponent);
    }

    private MutableComponent n_1700_B(MutableComponent p_208016_1_) {
        String s = this.y_4642_Y().getName();
        return p_208016_1_.n_1700_B(p_234565_2_ -> p_234565_2_.n_1700_B(new i_2909_p(i_2909_p.n_1700_B.G_564_y, "/tell " + s + " ")).n_1700_B(this.x_92_N()).n_1700_B(s));
    }

    @Override
    public String L_3570_A() {
        return this.y_4642_Y().getName();
    }

    @Override
    public float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        switch (poseIn) {
            case G_564_y: {
                return 0.4f;
            }
            case J_1907_R: 
            case P_1922_E: {
                return 0.4f;
            }
            case u_1723_Y: {
                return 1.27f;
            }
        }
        return 1.62f;
    }

    @Override
    public void Y_259_p(float amount) {
        if (amount < 0.0f) {
            amount = 0.0f;
        }
        this.D_60_a().J_1907_R(J_1907_R, Float.valueOf(amount));
    }

    @Override
    public float U_3823_u() {
        return this.D_60_a().n_1700_B(J_1907_R).floatValue();
    }

    public static UUID n_1700_B(GameProfile profile) {
        UUID uuid = profile.getId();
        if (uuid == null) {
            uuid = a_3913_L.u_1723_Y(profile.getName());
        }
        return uuid;
    }

    public static UUID u_1723_Y(String username) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(StandardCharsets.UTF_8));
    }

    public boolean n_1700_B(E_4346_v part) {
        return (this.D_60_a().n_1700_B(X_1313_W) & part.n_1700_B()) == part.n_1700_B();
    }

    @Override
    public boolean n_1700_B(int inventorySlot, Z_1993_T itemStackIn) {
        if (inventorySlot >= 0 && inventorySlot < this.l_1268_F.n_1700_B.size()) {
            this.l_1268_F.J_1907_R(inventorySlot, itemStackIn);
            return true;
        }
        e_1174_E equipmentslottype = inventorySlot == 100 + e_1174_E.u_1723_Y.J_1907_R() ? e_1174_E.u_1723_Y : (inventorySlot == 100 + e_1174_E.P_1922_E.J_1907_R() ? e_1174_E.P_1922_E : (inventorySlot == 100 + e_1174_E.G_564_y.J_1907_R() ? e_1174_E.G_564_y : (inventorySlot == 100 + e_1174_E.R_4764_Y.J_1907_R() ? e_1174_E.R_4764_Y : null)));
        if (inventorySlot == 98) {
            this.n_1700_B(e_1174_E.n_1700_B, itemStackIn);
            return true;
        }
        if (inventorySlot == 99) {
            this.n_1700_B(e_1174_E.J_1907_R, itemStackIn);
            return true;
        }
        if (equipmentslottype == null) {
            int i = inventorySlot - 200;
            if (i >= 0 && i < this.J_303_C.Y_259_p()) {
                this.J_303_C.J_1907_R(i, itemStackIn);
                return true;
            }
            return false;
        }
        if (!itemStackIn.n_1700_B() && (!(itemStackIn.J_1907_R() instanceof R_2515_i) && !(itemStackIn.J_1907_R() instanceof ElytraItem) ? equipmentslottype != e_1174_E.u_1723_Y : Z_530_i.s_956_w(itemStackIn) != equipmentslottype)) {
            return false;
        }
        this.l_1268_F.J_1907_R(equipmentslottype.J_1907_R() + this.l_1268_F.n_1700_B.size(), itemStackIn);
        return true;
    }

    public boolean y_3417_N() {
        return this.w_1484_f;
    }

    public void Y_601_j(boolean reducedDebug) {
        this.w_1484_f = reducedDebug;
    }

    @Override
    public void u_1723_Y(int ticks) {
        super.u_1723_Y(this.C_415_h.n_1700_B ? Math.min(ticks, 1) : ticks);
    }

    @Override
    public k_4231_L d_2169_p() {
        return this.l_4537_E.n_1700_B(x_4991_F) == 0 ? k_4231_L.n_1700_B : k_4231_L.J_1907_R;
    }

    public void n_1700_B(k_4231_L hand) {
        this.l_4537_E.J_1907_R(x_4991_F, (byte)(hand != k_4231_L.n_1700_B ? 1 : 0));
    }

    public U_2912_j A_1306_N() {
        return this.l_4537_E.n_1700_B(Z_759_W);
    }

    protected void w_1484_f(U_2912_j tag) {
        this.l_4537_E.J_1907_R(Z_759_W, tag);
    }

    public U_2912_j D_3612_q() {
        return this.l_4537_E.n_1700_B(f_1574_f);
    }

    protected void t_148_a(U_2912_j tag) {
        this.l_4537_E.J_1907_R(f_1574_f, tag);
    }

    public float R_2822_N() {
        return (float)(1.0 / this.J_1907_R(Attributes.w_1484_f) * 20.0);
    }

    public float k_2293_S(float adjustTicks) {
        return u_530_F.n_1700_B(((float)this.C_3538_G + adjustTicks) / this.R_2822_N(), 0.0f, 1.0f);
    }

    public void ModuleCategory() {
        this.C_3538_G = 0;
    }

    public v_576_m p_1458_L() {
        return this.s_956_w;
    }

    @Override
    protected float RegionPingResult() {
        return !this.C_415_h.J_1907_R && !this.k_578_l() ? super.RegionPingResult() : 1.0f;
    }

    public float Module() {
        return (float)this.J_1907_R(Attributes.u_2550_I);
    }

    public boolean ModuleManager() {
        return this.C_415_h.G_564_y && this.t_1786_h() >= 2;
    }

    @Override
    public boolean P_1922_E(Z_1993_T itemstackIn) {
        e_1174_E equipmentslottype = Z_530_i.s_956_w(itemstackIn);
        return this.J_1907_R(equipmentslottype).n_1700_B();
    }

    @Override
    public R_1815_U n_1700_B(I_1170_F poseIn) {
        return n_1700_B.getOrDefault((Object)poseIn, h_2739_B);
    }

    @Override
    public ImmutableList<I_1170_F> x_2635_q() {
        return ImmutableList.of((Object)((Object)I_1170_F.n_1700_B), (Object)((Object)I_1170_F.u_1723_Y), (Object)((Object)I_1170_F.G_564_y));
    }

    @Override
    public Z_1993_T u_1723_Y(Z_1993_T shootable) {
        if (!(shootable.J_1907_R() instanceof ProjectileWeaponItem)) {
            return Z_1993_T.J_1907_R;
        }
        Predicate<Z_1993_T> predicate = ((ProjectileWeaponItem)shootable.J_1907_R()).v_4262_N();
        Z_1993_T itemstack = ProjectileWeaponItem.n_1700_B(this, predicate);
        if (!itemstack.n_1700_B()) {
            return itemstack;
        }
        predicate = ((ProjectileWeaponItem)shootable.J_1907_R()).R_4764_Y();
        for (int i = 0; i < this.l_1268_F.Y_259_p(); ++i) {
            Z_1993_T itemstack1 = this.l_1268_F.s_956_w(i);
            if (!predicate.test(itemstack1)) continue;
            return itemstack1;
        }
        return this.C_415_h.G_564_y ? new Z_1993_T(Items.g_24_p) : Z_1993_T.J_1907_R;
    }

    @Override
    public Z_1993_T n_1700_B(b_4507_u p_213357_1_, Z_1993_T p_213357_2_) {
        this.P_2295_B().n_1700_B(p_213357_2_.J_1907_R(), p_213357_2_);
        this.n_1700_B(Stats.R_4764_Y.J_1907_R(p_213357_2_.J_1907_R()));
        p_213357_1_.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.W_1488_x, D_38_f.w_1484_f, 0.5f, p_213357_1_.w_1457_N.nextFloat() * 0.1f + 0.9f);
        if (this instanceof B_4088_l) {
            U_3554_Q.Z_875_P.n_1700_B((B_4088_l)this, p_213357_2_);
        }
        return super.n_1700_B(p_213357_1_, p_213357_2_);
    }

    @Override
    protected boolean J_1907_R(K_4074_S p_230295_1_) {
        return this.C_415_h.J_1907_R || super.J_1907_R(p_230295_1_);
    }

    @Override
    public e_2866_D P_1922_E(float partialTicks) {
        float f2;
        double d0 = 0.22 * (this.d_2169_p() == k_4231_L.J_1907_R ? -1.0 : 1.0);
        float f = u_530_F.v_4262_N(partialTicks * 0.5f, this.f_4016_n, this.UploadStatus) * ((float)Math.PI / 180);
        float f1 = u_530_F.v_4262_N(partialTicks, this.D_4361_a, this.C_1162_e) * ((float)Math.PI / 180);
        if (!this.k_578_l() && !this.B_3040_x()) {
            if (this.x_612_B()) {
                return this.P_4830_p(partialTicks).P_1922_E(new e_2866_D(d0, 0.2, -0.15).n_1700_B(-f).J_1907_R(-f1));
            }
            double d5 = this.i_601_W().getYSize() - 1.0;
            double d6 = this.Z_875_P() ? -0.2 : 0.07;
            return this.P_4830_p(partialTicks).P_1922_E(new e_2866_D(d0, d5, d6).J_1907_R(-f1));
        }
        e_2866_D vector3d = this.t_148_a(partialTicks);
        e_2866_D vector3d1 = this.I_4348_c();
        double d1 = N_4263_v.R_4764_Y(vector3d1);
        double d2 = N_4263_v.R_4764_Y(vector3d);
        if (d1 > 0.0 && d2 > 0.0) {
            double d3 = (vector3d1.J_1907_R * vector3d.J_1907_R + vector3d1.G_564_y * vector3d.G_564_y) / Math.sqrt(d1 * d2);
            double d4 = vector3d1.J_1907_R * vector3d.G_564_y - vector3d1.G_564_y * vector3d.J_1907_R;
            f2 = (float)(Math.signum(d4) * Math.acos(d3));
        } else {
            f2 = 0.0f;
        }
        return this.P_4830_p(partialTicks).P_1922_E(new e_2866_D(d0, -0.11, 0.85).R_4764_Y(-f2).n_1700_B(-f).J_1907_R(-f1));
    }

    public boolean Setting() {
        return !this.J_1907_R(MobEffects.q_2307_F) && !this.J_1907_R(MobEffects.Q_4569_t) && !((N_4263_v)this).n_1700_B(FluidTags.J_1907_R) && !((N_4263_v)this).n_1700_B(FluidTags.R_4764_Y) && !this.C_415_h.J_1907_R && !this.k_578_l() && !this.e_() && !this.y_2772_m() && this.U_1241_n > 0.0f && !this.M_1641_O();
    }

    public boolean KeyBindSetting() {
        return !this.J_1907_R(MobEffects.q_2307_F) && !this.J_1907_R(MobEffects.Q_4569_t) && !((N_4263_v)this).n_1700_B(FluidTags.J_1907_R) && !((N_4263_v)this).n_1700_B(FluidTags.R_4764_Y) && !this.RealmsClientOutdatedScreen && !this.C_415_h.J_1907_R && !this.k_578_l() && !this.e_() && !this.y_2772_m();
    }

    @Override
    public void T_437_o() {
        if (!this.C_415_h.J_1907_R) {
            super.T_437_o();
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(new F_2904_S("block.minecraft.bed.no_sleep"));
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(new F_2904_S("block.minecraft.bed.too_far_away"));
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(new F_2904_S("block.minecraft.bed.obstructed"));
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B(new F_2904_S("block.minecraft.bed.not_safe"));
        @Nullable
        private final x_282_a v_4262_N;
        private static final /* synthetic */ n_1700_B[] w_1484_f;

        public static n_1700_B[] values() {
            return (n_1700_B[])w_1484_f.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B() {
            this.v_4262_N = null;
        }

        private n_1700_B(x_282_a msg) {
            this.v_4262_N = msg;
        }

        @Nullable
        public x_282_a n_1700_B() {
            return this.v_4262_N;
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            w_1484_f = lightning.product.a_3913_L$n_1700_B.J_1907_R();
        }
    }
}



