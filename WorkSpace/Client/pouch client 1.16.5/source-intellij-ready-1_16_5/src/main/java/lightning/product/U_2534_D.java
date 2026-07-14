/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.D_38_f;
import lightning.product.G_652_w;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_4817_s;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.PanicGoal;
import lightning.product.Container;
import lightning.product.Animal;
import lightning.product.Saddleable;
import lightning.product.N_1216_z;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.R_1815_U;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.PlayerRideableJumping;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.SoundType;
import lightning.product.d_2511_z;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.g_1941_L;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.k_4231_L;
import lightning.product.m_3054_I;
import lightning.product.SimpleParticleType;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_3375_n;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.v_2621_q;
import lightning.product.RunAroundLikeCrazyGoal;

public abstract class U_2534_D
extends Animal
implements Saddleable,
PlayerRideableJumping,
u_3375_n {
    private static final Predicate<r_4811_B> Q_2552_b = p_213617_0_ -> p_213617_0_ instanceof U_2534_D && ((U_2534_D)p_213617_0_).R_2822_N();
    private static final TargetingConditions C_2741_M = new TargetingConditions().n_1700_B(16.0).n_1700_B().J_1907_R().R_4764_Y().n_1700_B(Q_2552_b);
    private static final b_3278_X k_2293_S = b_3278_X.n_1700_B(Items.V_3441_j, Items.o_3456_E, a_3742_W.M_4609_z.u_1723_Y(), Items.E_738_L, Items.DoublePlantBlock, Items.p_863_D, Items.E_4612_l);
    private static final h_256_u<Byte> q_2307_F = C_4114_x.n_1700_B(U_2534_D.class, EntityDataSerializers.n_1700_B);
    private static final h_256_u<Optional<UUID>> Z_875_P = C_4114_x.n_1700_B(U_2534_D.class, EntityDataSerializers.Q_4569_t);
    private int c_3005_b;
    private int H_2857_Y;
    private int A_4115_X;
    public int h_1847_R;
    public int Q_4569_t;
    protected boolean M_182_A;
    protected N_1216_z t_1786_h;
    protected int multiplayerClientSuggestionProvider;
    protected float w_1457_N;
    private boolean Y_1740_V;
    private float t_4043_B;
    private float x_607_J;
    private float e_4240_b;
    private float n_3318_d;
    private float d_2427_y;
    private float z_1737_N;
    protected boolean Y_601_j = true;
    protected int Y_259_p;

    protected U_2534_D(t_5_h<? extends U_2534_D> type, b_4507_u worldIn) {
        super((t_5_h<? extends Animal>)type, worldIn);
        this.RealmsServerPing = 1.0f;
        this.p_1458_L();
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(1, new PanicGoal(this, 1.2));
        this.s_956_w.n_1700_B(1, new RunAroundLikeCrazyGoal(this, 1.2));
        this.s_956_w.n_1700_B(2, new BreedGoal(this, 1.0, U_2534_D.class));
        this.s_956_w.n_1700_B(4, new v_2621_q(this, 1.0));
        this.s_956_w.n_1700_B(6, new g_1941_L(this, 0.7));
        this.s_956_w.n_1700_B(7, new LookAtPlayerGoal(this, a_3913_L.class, 6.0f));
        this.s_956_w.n_1700_B(8, new RandomLookAroundGoal(this));
        this.c_2086_l();
    }

    protected void c_2086_l() {
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(q_2307_F, (byte)0);
        this.l_4537_E.n_1700_B(Z_875_P, Optional.empty());
    }

    protected boolean Y_601_j(int p_110233_1_) {
        return (this.l_4537_E.n_1700_B(q_2307_F) & p_110233_1_) != 0;
    }

    protected void G_564_y(int p_110208_1_, boolean p_110208_2_) {
        byte b0 = this.l_4537_E.n_1700_B(q_2307_F);
        if (p_110208_2_) {
            this.l_4537_E.J_1907_R(q_2307_F, (byte)(b0 | p_110208_1_));
        } else {
            this.l_4537_E.J_1907_R(q_2307_F, (byte)(b0 & ~p_110208_1_));
        }
    }

    public boolean o_4117_e() {
        return this.Y_601_j(2);
    }

    @Nullable
    public UUID U_3758_B() {
        return this.l_4537_E.n_1700_B(Z_875_P).orElse(null);
    }

    public void J_1907_R(@Nullable UUID uniqueId) {
        this.l_4537_E.J_1907_R(Z_875_P, Optional.ofNullable(uniqueId));
    }

    public boolean y_3417_N() {
        return this.M_182_A;
    }

    public void Y_601_j(boolean tamed) {
        this.G_564_y(2, tamed);
    }

    public void Y_259_p(boolean jumping) {
        this.M_182_A = jumping;
    }

    @Override
    protected void G_564_y(float distance) {
        if (distance > 6.0f && this.A_1306_N()) {
            this.C_2741_M(false);
        }
    }

    public boolean A_1306_N() {
        return this.Y_601_j(16);
    }

    public boolean D_3612_q() {
        return this.Y_601_j(32);
    }

    public boolean R_2822_N() {
        return this.Y_601_j(8);
    }

    public void Q_2552_b(boolean breeding) {
        this.G_564_y(8, breeding);
    }

    @Override
    public boolean n_1700_B() {
        return this.RealmsLongRunningMcoTaskScreen() && !this.d_() && this.o_4117_e();
    }

    @Override
    public void n_1700_B(@Nullable D_38_f p_230266_1_) {
        this.t_1786_h.J_1907_R(0, new Z_1993_T(Items.Z_361_l));
        if (p_230266_1_ != null) {
            this.O_508_d.n_1700_B((a_3913_L)null, this, SoundEvents.r_4217_P, p_230266_1_, 0.5f, 1.0f);
        }
    }

    @Override
    public boolean G_564_y() {
        return this.Y_601_j(4);
    }

    public int ModuleCategory() {
        return this.multiplayerClientSuggestionProvider;
    }

    public void Y_259_p(int temperIn) {
        this.multiplayerClientSuggestionProvider = temperIn;
    }

    public int Q_2552_b(int p_110198_1_) {
        int i = u_530_F.n_1700_B(this.ModuleCategory() + p_110198_1_, 0, this.SoundEventRegistration());
        this.Y_259_p(i);
        return i;
    }

    @Override
    public boolean w_728_N() {
        return !this.H_1883_T();
    }

    private void h_1640_b() {
        SoundEvent soundevent;
        this.J_3635_s();
        if (!this.y_1700_S() && (soundevent = this.Setting()) != null) {
            this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), soundevent, this.r_2478_U(), 1.0f, 1.0f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f);
        }
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        int i;
        if (distance > 1.0f) {
            this.n_1700_B(SoundEvents.NoServerDesync, 0.4f, 1.0f);
        }
        if ((i = this.u_1723_Y(distance, damageMultiplier)) <= 0) {
            return false;
        }
        this.n_1700_B(P_11_z.u_2550_I, (float)i);
        if (this.H_1883_T()) {
            for (N_4263_v entity : this.X_290_I()) {
                entity.n_1700_B(P_11_z.u_2550_I, (float)i);
            }
        }
        this.q_839_y();
        return true;
    }

    @Override
    protected int u_1723_Y(float distance, float damageMultiplier) {
        return u_530_F.u_1723_Y((distance * 0.5f - 3.0f) * damageMultiplier);
    }

    protected int y_2447_C() {
        return 2;
    }

    protected void p_1458_L() {
        N_1216_z inventory = this.t_1786_h;
        this.t_1786_h = new N_1216_z(this.y_2447_C());
        if (inventory != null) {
            inventory.J_1907_R(this);
            int i = Math.min(inventory.Y_259_p(), this.t_1786_h.Y_259_p());
            for (int j = 0; j < i; ++j) {
                Z_1993_T itemstack = inventory.s_956_w(j);
                if (itemstack.n_1700_B()) continue;
                this.t_1786_h.J_1907_R(j, itemstack.t_148_a());
            }
        }
        this.t_1786_h.n_1700_B(this);
        this.Module();
    }

    protected void Module() {
        if (!this.O_508_d.Y_259_p) {
            this.G_564_y(4, !this.t_1786_h.s_956_w(0).n_1700_B());
        }
    }

    @Override
    public void n_1700_B(Container invBasic) {
        boolean flag = this.G_564_y();
        this.Module();
        if (this.RealmsWorldResetDto > 20 && !flag && this.G_564_y()) {
            this.n_1700_B(SoundEvents.r_4217_P, 0.5f, 1.0f);
        }
    }

    public double ModuleManager() {
        return this.J_1907_R(Attributes.P_4830_p);
    }

    @Nullable
    protected SoundEvent Setting() {
        return null;
    }

    @Override
    @Nullable
    protected SoundEvent u_796_y() {
        return null;
    }

    @Override
    @Nullable
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        if (this.RealmsWorldOptions.nextInt(3) == 0) {
            this.V_537_k();
        }
        return null;
    }

    @Override
    @Nullable
    protected SoundEvent z_4693_k() {
        if (this.RealmsWorldOptions.nextInt(10) == 0 && !this.W_3729_Q()) {
            this.V_537_k();
        }
        return null;
    }

    @Nullable
    protected SoundEvent KeyBindSetting() {
        this.V_537_k();
        return null;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        if (!blockIn.R_4764_Y().n_1700_B()) {
            K_4074_S blockstate = this.O_508_d.getBlockState(pos.up());
            SoundType soundtype = blockIn.Q_4569_t();
            if (blockstate.n_1700_B(a_3742_W.X_290_I)) {
                soundtype = blockstate.Q_4569_t();
            }
            if (this.H_1883_T() && this.Y_601_j) {
                ++this.Y_259_p;
                if (this.Y_259_p > 5 && this.Y_259_p % 3 == 0) {
                    this.n_1700_B(soundtype);
                } else if (this.Y_259_p <= 5) {
                    this.n_1700_B(SoundEvents.PacketCriticals, soundtype.n_1700_B() * 0.15f, soundtype.J_1907_R());
                }
            } else if (soundtype == SoundType.n_1700_B) {
                this.n_1700_B(SoundEvents.PacketCriticals, soundtype.n_1700_B() * 0.15f, soundtype.J_1907_R());
            } else {
                this.n_1700_B(SoundEvents.Velocity, soundtype.n_1700_B() * 0.15f, soundtype.J_1907_R());
            }
        }
    }

    protected void n_1700_B(SoundType p_190680_1_) {
        this.n_1700_B(SoundEvents.KBDisplacement, p_190680_1_.n_1700_B() * 0.15f, p_190680_1_.J_1907_R());
    }

    public static s_1415_m.n_1700_B BooleanSetting() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.P_4830_p).n_1700_B(Attributes.n_1700_B, 53.0).n_1700_B(Attributes.G_564_y, 0.225f);
    }

    @Override
    public int c_4037_x() {
        return 6;
    }

    public int SoundEventRegistration() {
        return 100;
    }

    @Override
    protected float d_4500_Q() {
        return 0.8f;
    }

    @Override
    public int v_4276_D() {
        return 400;
    }

    public void u_1723_Y(a_3913_L playerEntity) {
        if (!this.O_508_d.Y_259_p && (!this.H_1883_T() || this.Y_601_j(playerEntity)) && this.o_4117_e()) {
            playerEntity.n_1700_B(this, this.t_1786_h);
        }
    }

    public m_3054_I J_1907_R(a_3913_L p_241395_1_, Z_1993_T p_241395_2_) {
        boolean flag = this.R_4764_Y(p_241395_1_, p_241395_2_);
        if (!p_241395_1_.C_415_h.G_564_y) {
            p_241395_2_.v_4262_N(1);
        }
        if (this.O_508_d.Y_259_p) {
            return m_3054_I.J_1907_R;
        }
        return flag ? m_3054_I.n_1700_B : m_3054_I.R_4764_Y;
    }

    protected boolean R_4764_Y(a_3913_L player, Z_1993_T stack) {
        boolean flag = false;
        float f = 0.0f;
        int i = 0;
        int j = 0;
        q_1613_l item = stack.J_1907_R();
        if (item == Items.V_3441_j) {
            f = 2.0f;
            i = 20;
            j = 3;
        } else if (item == Items.o_3456_E) {
            f = 1.0f;
            i = 30;
            j = 3;
        } else if (item == a_3742_W.M_4609_z.u_1723_Y()) {
            f = 20.0f;
            i = 180;
        } else if (item == Items.E_738_L) {
            f = 3.0f;
            i = 60;
            j = 3;
        } else if (item == Items.DoublePlantBlock) {
            f = 4.0f;
            i = 60;
            j = 5;
            if (!this.O_508_d.Y_259_p && this.o_4117_e() && this.x_() == 0 && !this.P_2295_B()) {
                flag = true;
                this.P_1922_E(player);
            }
        } else if (item == Items.p_863_D || item == Items.E_4612_l) {
            f = 10.0f;
            i = 240;
            j = 10;
            if (!this.O_508_d.Y_259_p && this.o_4117_e() && this.x_() == 0 && !this.P_2295_B()) {
                flag = true;
                this.P_1922_E(player);
            }
        }
        if (this.g_46_E() < this.L_1733_J() && f > 0.0f) {
            this.n_1700_B(f);
            flag = true;
        }
        if (this.d_() && i > 0) {
            this.O_508_d.n_1700_B(ParticleTypes.t_4043_B, this.G_564_y(1.0), this.M_766_z() + 0.5, this.v_4262_N(1.0), 0.0, 0.0, 0.0);
            if (!this.O_508_d.Y_259_p) {
                this.a_(i);
            }
            flag = true;
        }
        if (j > 0 && (flag || !this.o_4117_e()) && this.ModuleCategory() < this.SoundEventRegistration()) {
            flag = true;
            if (!this.O_508_d.Y_259_p) {
                this.Q_2552_b(j);
            }
        }
        if (flag) {
            this.h_1640_b();
        }
        return flag;
    }

    protected void v_4262_N(a_3913_L player) {
        this.C_2741_M(false);
        this.k_2293_S(false);
        if (!this.O_508_d.Y_259_p) {
            player.p_178_J = this.p_178_J;
            player.f_4016_n = this.f_4016_n;
            player.s_956_w(this);
        }
    }

    @Override
    protected boolean W_3729_Q() {
        return super.W_3729_Q() && this.H_1883_T() && this.G_564_y() || this.A_1306_N() || this.D_3612_q();
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return k_2293_S.n_1700_B(stack);
    }

    private void V_1176_p() {
        this.h_1847_R = 1;
    }

    @Override
    protected void A_229_v() {
        super.A_229_v();
        if (this.t_1786_h != null) {
            for (int i = 0; i < this.t_1786_h.Y_259_p(); ++i) {
                Z_1993_T itemstack = this.t_1786_h.s_956_w(i);
                if (itemstack.n_1700_B() || K_4096_w.P_1922_E(itemstack)) continue;
                this.a_(itemstack);
            }
        }
    }

    @Override
    public void Y_1740_V() {
        if (this.RealmsWorldOptions.nextInt(200) == 0) {
            this.V_1176_p();
        }
        super.Y_1740_V();
        if (!this.O_508_d.Y_259_p && this.RealmsLongRunningMcoTaskScreen()) {
            if (this.RealmsWorldOptions.nextInt(900) == 0 && this.O_2151_c == 0) {
                this.n_1700_B(1.0f);
            }
            if (this.h_2367_h()) {
                if (!this.A_1306_N() && !this.H_1883_T() && this.RealmsWorldOptions.nextInt(300) == 0 && this.O_508_d.getBlockState(this.b_2312_j().down()).n_1700_B(a_3742_W.t_148_a)) {
                    this.C_2741_M(true);
                }
                if (this.A_1306_N() && ++this.c_3005_b > 50) {
                    this.c_3005_b = 0;
                    this.C_2741_M(false);
                }
            }
            this.H_1491_c();
        }
    }

    protected void H_1491_c() {
        U_2534_D livingentity;
        if (this.R_2822_N() && this.d_() && !this.A_1306_N() && (livingentity = this.O_508_d.n_1700_B(U_2534_D.class, C_2741_M, this, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.i_601_W().grow(16.0))) != null && this.G_564_y((N_4263_v)livingentity) > 4.0) {
            this.t_148_a.n_1700_B((N_4263_v)livingentity, 0);
        }
    }

    public boolean h_2367_h() {
        return true;
    }

    @Override
    public void v_() {
        super.v_();
        if (this.H_2857_Y > 0 && ++this.H_2857_Y > 30) {
            this.H_2857_Y = 0;
            this.G_564_y(64, false);
        }
        if ((this.v_887_r() || this.w_1457_N()) && this.A_4115_X > 0 && ++this.A_4115_X > 20) {
            this.A_4115_X = 0;
            this.k_2293_S(false);
        }
        if (this.h_1847_R > 0 && ++this.h_1847_R > 8) {
            this.h_1847_R = 0;
        }
        if (this.Q_4569_t > 0) {
            ++this.Q_4569_t;
            if (this.Q_4569_t > 300) {
                this.Q_4569_t = 0;
            }
        }
        this.x_607_J = this.t_4043_B;
        if (this.A_1306_N()) {
            this.t_4043_B += (1.0f - this.t_4043_B) * 0.4f + 0.05f;
            if (this.t_4043_B > 1.0f) {
                this.t_4043_B = 1.0f;
            }
        } else {
            this.t_4043_B += (0.0f - this.t_4043_B) * 0.4f - 0.05f;
            if (this.t_4043_B < 0.0f) {
                this.t_4043_B = 0.0f;
            }
        }
        this.n_3318_d = this.e_4240_b;
        if (this.D_3612_q()) {
            this.x_607_J = this.t_4043_B = 0.0f;
            this.e_4240_b += (1.0f - this.e_4240_b) * 0.4f + 0.05f;
            if (this.e_4240_b > 1.0f) {
                this.e_4240_b = 1.0f;
            }
        } else {
            this.Y_1740_V = false;
            this.e_4240_b += (0.8f * this.e_4240_b * this.e_4240_b * this.e_4240_b - this.e_4240_b) * 0.6f - 0.05f;
            if (this.e_4240_b < 0.0f) {
                this.e_4240_b = 0.0f;
            }
        }
        this.z_1737_N = this.d_2427_y;
        if (this.Y_601_j(64)) {
            this.d_2427_y += (1.0f - this.d_2427_y) * 0.7f + 0.05f;
            if (this.d_2427_y > 1.0f) {
                this.d_2427_y = 1.0f;
            }
        } else {
            this.d_2427_y += (0.0f - this.d_2427_y) * 0.7f - 0.05f;
            if (this.d_2427_y < 0.0f) {
                this.d_2427_y = 0.0f;
            }
        }
    }

    private void J_3635_s() {
        if (!this.O_508_d.Y_259_p) {
            this.H_2857_Y = 1;
            this.G_564_y(64, true);
        }
    }

    public void C_2741_M(boolean p_110227_1_) {
        this.G_564_y(16, p_110227_1_);
    }

    public void k_2293_S(boolean rearing) {
        if (rearing) {
            this.C_2741_M(false);
        }
        this.G_564_y(32, rearing);
    }

    private void V_537_k() {
        if (this.v_887_r() || this.w_1457_N()) {
            this.A_4115_X = 1;
            this.k_2293_S(true);
        }
    }

    public void c_1608_O() {
        if (!this.D_3612_q()) {
            this.V_537_k();
            SoundEvent soundevent = this.KeyBindSetting();
            if (soundevent != null) {
                this.n_1700_B(soundevent, this.d_4500_Q(), this.O_2761_o());
            }
        }
    }

    public boolean w_1484_f(a_3913_L player) {
        this.J_1907_R(player.w_2705_t());
        this.Y_601_j(true);
        if (player instanceof B_4088_l) {
            U_3554_Q.k_2293_S.n_1700_B((B_4088_l)player, this);
        }
        this.O_508_d.n_1700_B((N_4263_v)this, (byte)7);
        return true;
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        if (this.RealmsLongRunningMcoTaskScreen()) {
            if (this.H_1883_T() && this.g_2268_R() && this.G_564_y()) {
                r_4811_B livingentity = (r_4811_B)this.n_3864_h();
                this.j_276_v = this.p_178_J = livingentity.p_178_J;
                this.f_4016_n = livingentity.f_4016_n * 0.5f;
                this.J_1907_R(this.p_178_J, this.f_4016_n);
                this.f_3449_S = this.C_1162_e = this.p_178_J;
                float f = livingentity.L_1362_X * 0.5f;
                float f1 = livingentity.L_4248_u;
                if (f1 <= 0.0f) {
                    f1 *= 0.25f;
                    this.Y_259_p = 0;
                }
                if (this.e_1992_r && this.w_1457_N == 0.0f && this.D_3612_q() && !this.Y_1740_V) {
                    f = 0.0f;
                    f1 = 0.0f;
                }
                if (this.w_1457_N > 0.0f && !this.y_3417_N() && this.e_1992_r) {
                    double d0 = this.ModuleManager() * (double)this.w_1457_N * (double)this.RealmsWorldResetDto();
                    double d1 = this.J_1907_R(MobEffects.w_1484_f) ? d0 + (double)((float)(this.R_4764_Y(MobEffects.w_1484_f).R_4764_Y() + 1) * 0.1f) : d0;
                    e_2866_D vector3d = this.I_4348_c();
                    this.h_1847_R(vector3d.J_1907_R, d1, vector3d.G_564_y);
                    this.Y_259_p(true);
                    this.LongRunningTask = true;
                    if (f1 > 0.0f) {
                        float f2 = u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180));
                        float f3 = u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180));
                        this.v_4262_N(this.I_4348_c().J_1907_R(-0.4f * f2 * this.w_1457_N, 0.0, 0.4f * f3 * this.w_1457_N));
                    }
                    this.w_1457_N = 0.0f;
                }
                this.y_2772_m = this.l_2995_s() * 0.1f;
                if (this.v_887_r()) {
                    this.w_1457_N((float)this.J_1907_R(Attributes.G_564_y));
                    super.w_1484_f(new e_2866_D(f, travelVector.R_4764_Y, f1));
                } else if (livingentity instanceof a_3913_L) {
                    this.v_4262_N(e_2866_D.n_1700_B);
                }
                if (this.e_1992_r) {
                    this.w_1457_N = 0.0f;
                    this.Y_259_p(false);
                }
                this.n_1700_B((r_4811_B)this, false);
            } else {
                this.y_2772_m = 0.02f;
                super.w_1484_f(travelVector);
            }
        }
    }

    protected void ModeSetting() {
        this.n_1700_B(SoundEvents.NoFriendDamage, 0.4f, 1.0f);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("EatingHaystack", this.A_1306_N());
        compound.n_1700_B("Bred", this.R_2822_N());
        compound.J_1907_R("Temper", this.ModuleCategory());
        compound.n_1700_B("Tame", this.o_4117_e());
        if (this.U_3758_B() != null) {
            compound.n_1700_B("Owner", this.U_3758_B());
        }
        if (!this.t_1786_h.s_956_w(0).n_1700_B()) {
            compound.n_1700_B("SaddleItem", this.t_1786_h.s_956_w(0).J_1907_R(new U_2912_j()));
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        Z_1993_T itemstack;
        UUID uuid;
        super.J_1907_R(compound);
        this.C_2741_M(compound.t_1786_h("EatingHaystack"));
        this.Q_2552_b(compound.t_1786_h("Bred"));
        this.Y_259_p(compound.w_1484_f("Temper"));
        this.Y_601_j(compound.t_1786_h("Tame"));
        if (compound.J_1907_R("Owner")) {
            uuid = compound.n_1700_B("Owner");
        } else {
            String s = compound.M_588_G("Owner");
            uuid = d_2511_z.n_1700_B(this.f_1574_f(), s);
        }
        if (uuid != null) {
            this.J_1907_R(uuid);
        }
        if (compound.R_4764_Y("SaddleItem", 10) && (itemstack = Z_1993_T.n_1700_B(compound.M_182_A("SaddleItem"))).J_1907_R() == Items.Z_361_l) {
            this.t_1786_h.J_1907_R(0, itemstack);
        }
        this.Module();
    }

    @Override
    public boolean n_1700_B(Animal otherAnimal) {
        return false;
    }

    protected boolean MultiBooleanSetting() {
        return !this.H_1883_T() && !this.y_2772_m() && this.o_4117_e() && !this.d_() && this.g_46_E() >= this.L_1733_J() && this.P_2295_B();
    }

    @Override
    @Nullable
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return null;
    }

    protected void n_1700_B(AgableMob p_190681_1_, U_2534_D p_190681_2_) {
        double d0 = this.R_4764_Y(Attributes.n_1700_B) + p_190681_1_.R_4764_Y(Attributes.n_1700_B) + (double)this.NumberSetting();
        p_190681_2_.n_1700_B(Attributes.n_1700_B).n_1700_B(d0 / 3.0);
        double d1 = this.R_4764_Y(Attributes.P_4830_p) + p_190681_1_.R_4764_Y(Attributes.P_4830_p) + this.O_3016_i();
        p_190681_2_.n_1700_B(Attributes.P_4830_p).n_1700_B(d1 / 3.0);
        double d2 = this.R_4764_Y(Attributes.G_564_y) + p_190681_1_.R_4764_Y(Attributes.G_564_y) + this.b_2037_V();
        p_190681_2_.n_1700_B(Attributes.G_564_y).n_1700_B(d2 / 3.0);
    }

    @Override
    public boolean g_2268_R() {
        return this.n_3864_h() instanceof r_4811_B;
    }

    public float c_3005_b(float p_110258_1_) {
        return u_530_F.v_4262_N(p_110258_1_, this.x_607_J, this.t_4043_B);
    }

    public float H_2857_Y(float p_110223_1_) {
        return u_530_F.v_4262_N(p_110223_1_, this.n_3318_d, this.e_4240_b);
    }

    public float A_4115_X(float p_110201_1_) {
        return u_530_F.v_4262_N(p_110201_1_, this.z_1737_N, this.d_2427_y);
    }

    @Override
    public void n_1700_B(int jumpPowerIn) {
        if (this.G_564_y()) {
            if (jumpPowerIn < 0) {
                jumpPowerIn = 0;
            } else {
                this.Y_1740_V = true;
                this.V_537_k();
            }
            this.w_1457_N = jumpPowerIn >= 90 ? 1.0f : 0.4f + 0.4f * (float)jumpPowerIn / 90.0f;
        }
    }

    @Override
    public boolean u_1723_Y() {
        return this.G_564_y();
    }

    @Override
    public void J_1907_R(int jumpPower) {
        this.Y_1740_V = true;
        this.V_537_k();
        this.ModeSetting();
    }

    @Override
    public void v_4262_N() {
    }

    protected void q_2307_F(boolean p_110216_1_) {
        SimpleParticleType iparticledata = p_110216_1_ ? ParticleTypes.e_4240_b : ParticleTypes.B_1668_F;
        for (int i = 0; i < 7; ++i) {
            double d0 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            double d1 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            double d2 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            this.O_508_d.n_1700_B(iparticledata, this.G_564_y(1.0), this.M_766_z() + 0.5, this.v_4262_N(1.0), d0, d1, d2);
        }
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 7) {
            this.q_2307_F(true);
        } else if (id == 6) {
            this.q_2307_F(false);
        } else {
            super.n_1700_B(id);
        }
    }

    @Override
    public void v_4262_N(N_4263_v passenger) {
        super.v_4262_N(passenger);
        if (passenger instanceof Z_530_i) {
            Z_530_i mobentity = (Z_530_i)passenger;
            this.C_1162_e = mobentity.C_1162_e;
        }
        if (this.n_3318_d > 0.0f) {
            float f3 = u_530_F.n_1700_B(this.C_1162_e * ((float)Math.PI / 180));
            float f = u_530_F.J_1907_R(this.C_1162_e * ((float)Math.PI / 180));
            float f1 = 0.7f * this.n_3318_d;
            float f2 = 0.15f * this.n_3318_d;
            passenger.J_1907_R(this.O_3598_v() + (double)(f1 * f3), this.X_2960_b() + this.s_1671_u() + passenger.O_2151_c() + (double)f2, this.l_2647_k() - (double)(f1 * f));
            if (passenger instanceof r_4811_B) {
                ((r_4811_B)passenger).C_1162_e = this.C_1162_e;
            }
        }
    }

    protected float NumberSetting() {
        return 15.0f + (float)this.RealmsWorldOptions.nextInt(8) + (float)this.RealmsWorldOptions.nextInt(9);
    }

    protected double O_3016_i() {
        return (double)0.4f + this.RealmsWorldOptions.nextDouble() * 0.2 + this.RealmsWorldOptions.nextDouble() * 0.2 + this.RealmsWorldOptions.nextDouble() * 0.2;
    }

    protected double b_2037_V() {
        return ((double)0.45f + this.RealmsWorldOptions.nextDouble() * 0.3 + this.RealmsWorldOptions.nextDouble() * 0.3 + this.RealmsWorldOptions.nextDouble() * 0.3) * 0.25;
    }

    @Override
    public boolean e_() {
        return false;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R * 0.95f;
    }

    public boolean N_4006_T() {
        return false;
    }

    public boolean k_1608_N() {
        return !this.J_1907_R(e_1174_E.P_1922_E).n_1700_B();
    }

    public boolean M_588_G(Z_1993_T stack) {
        return false;
    }

    @Override
    public boolean n_1700_B(int inventorySlot, Z_1993_T itemStackIn) {
        int i = inventorySlot - 400;
        if (i >= 0 && i < 2 && i < this.t_1786_h.Y_259_p()) {
            if (i == 0 && itemStackIn.J_1907_R() != Items.Z_361_l) {
                return false;
            }
            if (i != 1 || this.N_4006_T() && this.M_588_G(itemStackIn)) {
                this.t_1786_h.J_1907_R(i, itemStackIn);
                this.Module();
                return true;
            }
            return false;
        }
        int j = inventorySlot - 500 + 2;
        if (j >= 2 && j < this.t_1786_h.Y_259_p()) {
            this.t_1786_h.J_1907_R(j, itemStackIn);
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public N_4263_v n_3864_h() {
        return this.o_3599_Z().isEmpty() ? null : this.o_3599_Z().get(0);
    }

    @Nullable
    private e_2866_D n_1700_B(e_2866_D p_234236_1_, r_4811_B p_234236_2_) {
        double d0 = this.O_3598_v() + p_234236_1_.J_1907_R;
        double d1 = this.i_601_W().minY;
        double d2 = this.l_2647_k() + p_234236_1_.G_564_y;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        block0: for (I_1170_F pose : p_234236_2_.x_2635_q()) {
            blockpos$mutable.n_1700_B(d0, d1, d2);
            double d3 = this.i_601_W().maxY + 0.75;
            do {
                e_2866_D vector3d;
                I_4817_s axisalignedbb;
                double d4 = this.O_508_d.G_564_y(blockpos$mutable);
                if ((double)blockpos$mutable.getY() + d4 > d3) continue block0;
                if (G_652_w.n_1700_B(d4) && G_652_w.n_1700_B(this.O_508_d, p_234236_2_, (axisalignedbb = p_234236_2_.u_1723_Y(pose)).offset(vector3d = new e_2866_D(d0, (double)blockpos$mutable.getY() + d4, d2)))) {
                    p_234236_2_.J_1907_R(pose);
                    return vector3d;
                }
                blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
            } while ((double)blockpos$mutable.getY() < d3);
        }
        return null;
    }

    @Override
    public e_2866_D b_(r_4811_B livingEntity) {
        e_2866_D vector3d = U_2534_D.n_1700_B(this.C_415_h(), (double)livingEntity.C_415_h(), this.p_178_J + (livingEntity.d_2169_p() == k_4231_L.J_1907_R ? 90.0f : -90.0f));
        e_2866_D vector3d1 = this.n_1700_B(vector3d, livingEntity);
        if (vector3d1 != null) {
            return vector3d1;
        }
        e_2866_D vector3d2 = U_2534_D.n_1700_B(this.C_415_h(), (double)livingEntity.C_415_h(), this.p_178_J + (livingEntity.d_2169_p() == k_4231_L.n_1700_B ? 90.0f : -90.0f));
        e_2866_D vector3d3 = this.n_1700_B(vector3d2, livingEntity);
        return vector3d3 != null ? vector3d3 : this.s_4990_V();
    }

    protected void y_4642_Y() {
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        if (spawnDataIn == null) {
            spawnDataIn = new AgableMob.n_1700_B(0.2f);
        }
        this.y_4642_Y();
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
}



