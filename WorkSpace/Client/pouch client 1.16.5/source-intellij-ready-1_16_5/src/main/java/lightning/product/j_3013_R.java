/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.Attributes;
import lightning.product.I_408_V;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.N_3869_i;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.TemptGoal;
import lightning.product.MoveControl;
import lightning.product.AvoidEntityGoal;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.g_1941_L;
import lightning.product.g_3408_G;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.m_3054_I;
import lightning.product.PathfinderMob;
import lightning.product.n_1494_c;
import lightning.product.Goal;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.v_2621_q;
import lightning.product.x_1688_C;

public class j_3013_R
extends Animal {
    private static final h_256_u<Integer> Q_4569_t = C_4114_x.n_1700_B(j_3013_R.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Integer> M_182_A = C_4114_x.n_1700_B(j_3013_R.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Integer> t_1786_h = C_4114_x.n_1700_B(j_3013_R.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Byte> multiplayerClientSuggestionProvider = C_4114_x.n_1700_B(j_3013_R.class, EntityDataSerializers.n_1700_B);
    private static final h_256_u<Byte> w_1457_N = C_4114_x.n_1700_B(j_3013_R.class, EntityDataSerializers.n_1700_B);
    private static final h_256_u<Byte> Y_601_j = C_4114_x.n_1700_B(j_3013_R.class, EntityDataSerializers.n_1700_B);
    private static final TargetingConditions Y_259_p = new TargetingConditions().n_1700_B(8.0).J_1907_R().n_1700_B();
    private boolean Q_2552_b;
    private boolean C_2741_M;
    public int h_1847_R;
    private e_2866_D k_2293_S;
    private float q_2307_F;
    private float Z_875_P;
    private float c_3005_b;
    private float H_2857_Y;
    private float A_4115_X;
    private float Y_1740_V;
    private M_588_G t_4043_B;
    private static final Predicate<n_1494_c> x_607_J = p_213575_0_ -> {
        q_1613_l item = p_213575_0_.P_1922_E().J_1907_R();
        return (item == a_3742_W.t_1509_b.u_1723_Y() || item == a_3742_W.a_178_J.u_1723_Y()) && p_213575_0_.RealmsLongRunningMcoTaskScreen() && !p_213575_0_.Q_4569_t();
    };

    public j_3013_R(t_5_h<? extends j_3013_R> type, b_4507_u worldIn) {
        super((t_5_h<? extends Animal>)type, worldIn);
        this.v_4262_N = new v_4262_N(this);
        if (!this.d_()) {
            this.R_4764_Y(true);
        }
    }

    @Override
    public boolean P_1922_E(Z_1993_T itemstackIn) {
        e_1174_E equipmentslottype = Z_530_i.s_956_w(itemstackIn);
        if (!this.J_1907_R(equipmentslottype).n_1700_B()) {
            return false;
        }
        return equipmentslottype == e_1174_E.n_1700_B && super.P_1922_E(itemstackIn);
    }

    public int y_4642_Y() {
        return this.l_4537_E.n_1700_B(Q_4569_t);
    }

    public void Y_601_j(int p_213588_1_) {
        this.l_4537_E.J_1907_R(Q_4569_t, p_213588_1_);
    }

    public boolean h_1640_b() {
        return this.C_2741_M(2);
    }

    public boolean V_1176_p() {
        return this.C_2741_M(8);
    }

    public void w_1457_N(boolean p_213553_1_) {
        this.G_564_y(8, p_213553_1_);
    }

    public boolean y_2447_C() {
        return this.C_2741_M(16);
    }

    public void Y_601_j(boolean p_213542_1_) {
        this.G_564_y(16, p_213542_1_);
    }

    public boolean J_3635_s() {
        return this.l_4537_E.n_1700_B(t_1786_h) > 0;
    }

    public void Y_259_p(boolean p_213534_1_) {
        this.l_4537_E.J_1907_R(t_1786_h, p_213534_1_ ? 1 : 0);
    }

    private int KeyBindSetting() {
        return this.l_4537_E.n_1700_B(t_1786_h);
    }

    private void Q_2552_b(int p_213571_1_) {
        this.l_4537_E.J_1907_R(t_1786_h, p_213571_1_);
    }

    public void Q_2552_b(boolean p_213581_1_) {
        this.G_564_y(2, p_213581_1_);
        if (!p_213581_1_) {
            this.Y_259_p(0);
        }
    }

    public int V_537_k() {
        return this.l_4537_E.n_1700_B(M_182_A);
    }

    public void Y_259_p(int p_213562_1_) {
        this.l_4537_E.J_1907_R(M_182_A, p_213562_1_);
    }

    public G_564_y c_2086_l() {
        return lightning.product.j_3013_R$G_564_y.n_1700_B(this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider).byteValue());
    }

    public void n_1700_B(G_564_y pandaType) {
        if (pandaType.n_1700_B() > 6) {
            pandaType = lightning.product.j_3013_R$G_564_y.n_1700_B(this.RealmsWorldOptions);
        }
        this.l_4537_E.J_1907_R(multiplayerClientSuggestionProvider, (byte)pandaType.n_1700_B());
    }

    public G_564_y o_4117_e() {
        return lightning.product.j_3013_R$G_564_y.n_1700_B(this.l_4537_E.n_1700_B(w_1457_N).byteValue());
    }

    public void J_1907_R(G_564_y pandaType) {
        if (pandaType.n_1700_B() > 6) {
            pandaType = lightning.product.j_3013_R$G_564_y.n_1700_B(this.RealmsWorldOptions);
        }
        this.l_4537_E.J_1907_R(w_1457_N, (byte)pandaType.n_1700_B());
    }

    public boolean U_3758_B() {
        return this.C_2741_M(4);
    }

    public void C_2741_M(boolean p_213576_1_) {
        this.G_564_y(4, p_213576_1_);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(Q_4569_t, 0);
        this.l_4537_E.n_1700_B(M_182_A, 0);
        this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider, (byte)0);
        this.l_4537_E.n_1700_B(w_1457_N, (byte)0);
        this.l_4537_E.n_1700_B(Y_601_j, (byte)0);
        this.l_4537_E.n_1700_B(t_1786_h, 0);
    }

    private boolean C_2741_M(int flagId) {
        return (this.l_4537_E.n_1700_B(Y_601_j) & flagId) != 0;
    }

    private void G_564_y(int flagId, boolean p_213587_2_) {
        byte b0 = this.l_4537_E.n_1700_B(Y_601_j);
        if (p_213587_2_) {
            this.l_4537_E.J_1907_R(Y_601_j, (byte)(b0 | flagId));
        } else {
            this.l_4537_E.J_1907_R(Y_601_j, (byte)(b0 & ~flagId));
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("MainGene", this.c_2086_l().J_1907_R());
        compound.n_1700_B("HiddenGene", this.o_4117_e().J_1907_R());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.n_1700_B(lightning.product.j_3013_R$G_564_y.n_1700_B(compound.M_588_G("MainGene")));
        this.J_1907_R(lightning.product.j_3013_R$G_564_y.n_1700_B(compound.M_588_G("HiddenGene")));
    }

    @Override
    @Nullable
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        j_3013_R pandaentity = t_5_h.z_1333_t.n_1700_B(p_241840_1_);
        if (p_241840_2_ instanceof j_3013_R) {
            pandaentity.n_1700_B(this, (j_3013_R)p_241840_2_);
        }
        pandaentity.ModuleManager();
        return pandaentity;
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(2, new w_1484_f(this, 2.0));
        this.s_956_w.n_1700_B(2, new u_1723_Y(this, this, 1.0));
        this.s_956_w.n_1700_B(3, new n_1700_B(this, (double)1.2f, true));
        this.s_956_w.n_1700_B(4, new TemptGoal((PathfinderMob)this, 1.0, b_3278_X.n_1700_B(a_3742_W.t_1509_b.u_1723_Y()), false));
        this.s_956_w.n_1700_B(6, new J_1907_R<a_3913_L>(this, a_3913_L.class, 8.0f, 2.0, 2.0));
        this.s_956_w.n_1700_B(6, new J_1907_R<Monster>(this, Monster.class, 4.0f, 2.0, 2.0));
        this.s_956_w.n_1700_B(7, new u_2550_I());
        this.s_956_w.n_1700_B(8, new P_1922_E(this));
        this.s_956_w.n_1700_B(8, new R_4764_Y(this));
        this.t_4043_B = new M_588_G(this, a_3913_L.class, 6.0f);
        this.s_956_w.n_1700_B(9, this.t_4043_B);
        this.s_956_w.n_1700_B(10, new RandomLookAroundGoal(this));
        this.s_956_w.n_1700_B(12, new s_956_w(this));
        this.s_956_w.n_1700_B(13, new v_2621_q(this, 1.25));
        this.s_956_w.n_1700_B(14, new g_1941_L(this, 1.0));
        this.u_2550_I.n_1700_B(1, new t_148_a(this, new Class[0]).n_1700_B(new Class[0]));
    }

    public static s_1415_m.n_1700_B y_3417_N() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.G_564_y, 0.15f).n_1700_B(Attributes.u_1723_Y, 6.0);
    }

    public G_564_y A_1306_N() {
        return lightning.product.j_3013_R$G_564_y.n_1700_B(this.c_2086_l(), this.o_4117_e());
    }

    public boolean D_3612_q() {
        return this.A_1306_N() == lightning.product.j_3013_R$G_564_y.J_1907_R;
    }

    public boolean R_2822_N() {
        return this.A_1306_N() == lightning.product.j_3013_R$G_564_y.R_4764_Y;
    }

    public boolean ModuleCategory() {
        return this.A_1306_N() == lightning.product.j_3013_R$G_564_y.G_564_y;
    }

    public boolean p_1458_L() {
        return this.A_1306_N() == lightning.product.j_3013_R$G_564_y.u_1723_Y;
    }

    @Override
    public boolean P_2272_O() {
        return this.A_1306_N() == lightning.product.j_3013_R$G_564_y.v_4262_N;
    }

    @Override
    public boolean G_564_y(a_3913_L player) {
        return false;
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        this.n_1700_B(SoundEvents.m_229_F, 1.0f, 1.0f);
        if (!this.P_2272_O()) {
            this.C_2741_M = true;
        }
        return super.q_2307_F(entityIn);
    }

    @Override
    public void v_() {
        super.v_();
        if (this.R_2822_N()) {
            if (this.O_508_d.N_2525_X() && !this.RowButton()) {
                this.w_1457_N(true);
                this.Y_259_p(false);
            } else if (!this.J_3635_s()) {
                this.w_1457_N(false);
            }
        }
        if (this.t_148_a() == null) {
            this.Q_2552_b = false;
            this.C_2741_M = false;
        }
        if (this.y_4642_Y() > 0) {
            if (this.t_148_a() != null) {
                this.n_1700_B((N_4263_v)this.t_148_a(), 90.0f, 90.0f);
            }
            if (this.y_4642_Y() == 29 || this.y_4642_Y() == 14) {
                this.n_1700_B(SoundEvents.p_1976_q, 1.0f, 1.0f);
            }
            this.Y_601_j(this.y_4642_Y() - 1);
        }
        if (this.h_1640_b()) {
            this.Y_259_p(this.V_537_k() + 1);
            if (this.V_537_k() > 20) {
                this.Q_2552_b(false);
                this.MultiBooleanSetting();
            } else if (this.V_537_k() == 1) {
                this.n_1700_B(SoundEvents.ViewModel, 1.0f, 1.0f);
            }
        }
        if (this.U_3758_B()) {
            this.ModeSetting();
        } else {
            this.h_1847_R = 0;
        }
        if (this.V_1176_p()) {
            this.f_4016_n = 0.0f;
        }
        this.H_1491_c();
        this.BooleanSetting();
        this.h_2367_h();
        this.c_1608_O();
    }

    public boolean Module() {
        return this.R_2822_N() && this.O_508_d.N_2525_X();
    }

    private void BooleanSetting() {
        if (!this.J_3635_s() && this.V_1176_p() && !this.Module() && !this.J_1907_R(e_1174_E.n_1700_B).n_1700_B() && this.RealmsWorldOptions.nextInt(80) == 1) {
            this.Y_259_p(true);
        } else if (this.J_1907_R(e_1174_E.n_1700_B).n_1700_B() || !this.V_1176_p()) {
            this.Y_259_p(false);
        }
        if (this.J_3635_s()) {
            this.SoundEventRegistration();
            if (!this.O_508_d.Y_259_p && this.KeyBindSetting() > 80 && this.RealmsWorldOptions.nextInt(20) == 1) {
                if (this.KeyBindSetting() > 100 && this.M_588_G(this.J_1907_R(e_1174_E.n_1700_B))) {
                    if (!this.O_508_d.Y_259_p) {
                        this.n_1700_B(e_1174_E.n_1700_B, Z_1993_T.J_1907_R);
                    }
                    this.w_1457_N(false);
                }
                this.Y_259_p(false);
                return;
            }
            this.Q_2552_b(this.KeyBindSetting() + 1);
        }
    }

    private void SoundEventRegistration() {
        if (this.KeyBindSetting() % 5 == 0) {
            this.n_1700_B(SoundEvents.K_2336_H, 0.5f + 0.5f * (float)this.RealmsWorldOptions.nextInt(2), (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
            for (int i = 0; i < 6; ++i) {
                e_2866_D vector3d = new e_2866_D(((double)this.RealmsWorldOptions.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, ((double)this.RealmsWorldOptions.nextFloat() - 0.5) * 0.1);
                vector3d = vector3d.n_1700_B(-this.f_4016_n * ((float)Math.PI / 180));
                vector3d = vector3d.J_1907_R(-this.p_178_J * ((float)Math.PI / 180));
                double d0 = (double)(-this.RealmsWorldOptions.nextFloat()) * 0.6 - 0.3;
                e_2866_D vector3d1 = new e_2866_D(((double)this.RealmsWorldOptions.nextFloat() - 0.5) * 0.8, d0, 1.0 + ((double)this.RealmsWorldOptions.nextFloat() - 0.5) * 0.4);
                vector3d1 = vector3d1.J_1907_R(-this.C_1162_e * ((float)Math.PI / 180));
                vector3d1 = vector3d1.J_1907_R(this.O_3598_v(), this.X_2048_Y() + 1.0, this.l_2647_k());
                this.O_508_d.n_1700_B(new N_3869_i(ParticleTypes.d_2427_y, this.J_1907_R(e_1174_E.n_1700_B)), vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, vector3d.J_1907_R, vector3d.R_4764_Y + 0.05, vector3d.G_564_y);
            }
        }
    }

    private void H_1491_c() {
        this.Z_875_P = this.q_2307_F;
        this.q_2307_F = this.V_1176_p() ? Math.min(1.0f, this.q_2307_F + 0.15f) : Math.max(0.0f, this.q_2307_F - 0.19f);
    }

    private void h_2367_h() {
        this.H_2857_Y = this.c_3005_b;
        this.c_3005_b = this.y_2447_C() ? Math.min(1.0f, this.c_3005_b + 0.15f) : Math.max(0.0f, this.c_3005_b - 0.19f);
    }

    private void c_1608_O() {
        this.Y_1740_V = this.A_4115_X;
        this.A_4115_X = this.U_3758_B() ? Math.min(1.0f, this.A_4115_X + 0.15f) : Math.max(0.0f, this.A_4115_X - 0.19f);
    }

    public float c_3005_b(float p_213561_1_) {
        return u_530_F.v_4262_N(p_213561_1_, this.Z_875_P, this.q_2307_F);
    }

    public float H_2857_Y(float p_213583_1_) {
        return u_530_F.v_4262_N(p_213583_1_, this.H_2857_Y, this.c_3005_b);
    }

    public float A_4115_X(float p_213591_1_) {
        return u_530_F.v_4262_N(p_213591_1_, this.Y_1740_V, this.A_4115_X);
    }

    private void ModeSetting() {
        ++this.h_1847_R;
        if (this.h_1847_R > 32) {
            this.C_2741_M(false);
        } else if (!this.O_508_d.Y_259_p) {
            e_2866_D vector3d = this.I_4348_c();
            if (this.h_1847_R == 1) {
                float f = this.p_178_J * ((float)Math.PI / 180);
                float f1 = this.d_() ? 0.1f : 0.2f;
                this.k_2293_S = new e_2866_D(vector3d.J_1907_R + (double)(-u_530_F.n_1700_B(f) * f1), 0.0, vector3d.G_564_y + (double)(u_530_F.J_1907_R(f) * f1));
                this.v_4262_N(this.k_2293_S.J_1907_R(0.0, 0.27, 0.0));
            } else if ((float)this.h_1847_R != 7.0f && (float)this.h_1847_R != 15.0f && (float)this.h_1847_R != 23.0f) {
                this.h_1847_R(this.k_2293_S.J_1907_R, vector3d.R_4764_Y, this.k_2293_S.G_564_y);
            } else {
                this.h_1847_R(0.0, this.e_1992_r ? 0.27 : vector3d.R_4764_Y, 0.0);
            }
        }
    }

    private void MultiBooleanSetting() {
        e_2866_D vector3d = this.I_4348_c();
        this.O_508_d.n_1700_B(ParticleTypes.g_164_R, this.O_3598_v() - (double)(this.C_415_h() + 1.0f) * 0.5 * (double)u_530_F.n_1700_B(this.C_1162_e * ((float)Math.PI / 180)), this.X_2048_Y() - (double)0.1f, this.l_2647_k() + (double)(this.C_415_h() + 1.0f) * 0.5 * (double)u_530_F.J_1907_R(this.C_1162_e * ((float)Math.PI / 180)), vector3d.J_1907_R, 0.0, vector3d.G_564_y);
        this.n_1700_B(SoundEvents.WorldParticles, 1.0f, 1.0f);
        for (j_3013_R pandaentity : this.O_508_d.n_1700_B(j_3013_R.class, this.i_601_W().grow(10.0))) {
            if (pandaentity.d_() || !pandaentity.e_1992_r || pandaentity.RowButton() || !pandaentity.Setting()) continue;
            pandaentity.e_837_t();
        }
        if (!this.O_508_d.v_4276_D() && this.RealmsWorldOptions.nextInt(700) == 0 && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.P_1922_E)) {
            this.n_1700_B((q_1803_e)Items.Z_3822_q);
        }
    }

    @Override
    protected void J_1907_R(n_1494_c itemEntity) {
        if (this.J_1907_R(e_1174_E.n_1700_B).n_1700_B() && x_607_J.test(itemEntity)) {
            this.n_1700_B(itemEntity);
            Z_1993_T itemstack = itemEntity.P_1922_E();
            this.n_1700_B(e_1174_E.n_1700_B, itemstack);
            this.M_588_G[e_1174_E.n_1700_B.J_1907_R()] = 2.0f;
            this.n_1700_B((N_4263_v)itemEntity, itemstack.t_4043_B());
            itemEntity.Ops();
        }
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        this.w_1457_N(false);
        return super.n_1700_B(source, amount);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.n_1700_B(lightning.product.j_3013_R$G_564_y.n_1700_B(this.RealmsWorldOptions));
        this.J_1907_R(lightning.product.j_3013_R$G_564_y.n_1700_B(this.RealmsWorldOptions));
        this.ModuleManager();
        if (spawnDataIn == null) {
            spawnDataIn = new AgableMob.n_1700_B(0.2f);
        }
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public void n_1700_B(j_3013_R father, @Nullable j_3013_R mother) {
        if (mother == null) {
            if (this.RealmsWorldOptions.nextBoolean()) {
                this.n_1700_B(father.NumberSetting());
                this.J_1907_R(lightning.product.j_3013_R$G_564_y.n_1700_B(this.RealmsWorldOptions));
            } else {
                this.n_1700_B(lightning.product.j_3013_R$G_564_y.n_1700_B(this.RealmsWorldOptions));
                this.J_1907_R(father.NumberSetting());
            }
        } else if (this.RealmsWorldOptions.nextBoolean()) {
            this.n_1700_B(father.NumberSetting());
            this.J_1907_R(mother.NumberSetting());
        } else {
            this.n_1700_B(mother.NumberSetting());
            this.J_1907_R(father.NumberSetting());
        }
        if (this.RealmsWorldOptions.nextInt(32) == 0) {
            this.n_1700_B(lightning.product.j_3013_R$G_564_y.n_1700_B(this.RealmsWorldOptions));
        }
        if (this.RealmsWorldOptions.nextInt(32) == 0) {
            this.J_1907_R(lightning.product.j_3013_R$G_564_y.n_1700_B(this.RealmsWorldOptions));
        }
    }

    private G_564_y NumberSetting() {
        return this.RealmsWorldOptions.nextBoolean() ? this.c_2086_l() : this.o_4117_e();
    }

    public void ModuleManager() {
        if (this.p_1458_L()) {
            this.n_1700_B(Attributes.n_1700_B).n_1700_B(10.0);
        }
        if (this.D_3612_q()) {
            this.n_1700_B(Attributes.G_564_y).n_1700_B(0.07f);
        }
    }

    private void O_3016_i() {
        if (!this.RowButton()) {
            this.C_2741_M(0.0f);
            this.e_4240_b().h_1847_R();
            this.w_1457_N(true);
        }
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (this.Module()) {
            return m_3054_I.R_4764_Y;
        }
        if (this.y_2447_C()) {
            this.Y_601_j(false);
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        if (this.u_2550_I(itemstack)) {
            if (this.t_148_a() != null) {
                this.Q_2552_b = true;
            }
            if (this.d_()) {
                this.n_1700_B(p_230254_1_, itemstack);
                this.n_1700_B((int)((float)(-this.x_() / 20) * 0.1f), true);
            } else if (!this.O_508_d.Y_259_p && this.x_() == 0 && this.o_82_k()) {
                this.n_1700_B(p_230254_1_, itemstack);
                this.P_1922_E(p_230254_1_);
            } else {
                if (this.O_508_d.Y_259_p || this.V_1176_p() || this.RowButton()) {
                    return m_3054_I.R_4764_Y;
                }
                this.O_3016_i();
                this.Y_259_p(true);
                Z_1993_T itemstack1 = this.J_1907_R(e_1174_E.n_1700_B);
                if (!itemstack1.n_1700_B() && !p_230254_1_.C_415_h.G_564_y) {
                    this.a_(itemstack1);
                }
                this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(itemstack.J_1907_R(), 1));
                this.n_1700_B(p_230254_1_, itemstack);
            }
            return m_3054_I.n_1700_B;
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    @Nullable
    protected SoundEvent z_4693_k() {
        if (this.P_2272_O()) {
            return SoundEvents.A_4252_m;
        }
        return this.R_2822_N() ? SoundEvents.a_794_m : SoundEvents.CavityFinder;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.n_421_x, 0.15f, 1.0f);
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return stack.J_1907_R() == a_3742_W.t_1509_b.u_1723_Y();
    }

    private boolean M_588_G(Z_1993_T stack) {
        return this.u_2550_I(stack) || stack.J_1907_R() == a_3742_W.a_178_J.u_1723_Y();
    }

    @Override
    @Nullable
    protected SoundEvent u_796_y() {
        return SoundEvents.e_87_p;
    }

    @Override
    @Nullable
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.E_170_p;
    }

    public boolean Setting() {
        return !this.y_2447_C() && !this.Module() && !this.J_3635_s() && !this.U_3758_B() && !this.V_1176_p();
    }

    static class v_4262_N
    extends MoveControl {
        private final j_3013_R t_148_a;

        public v_4262_N(j_3013_R pandaIn) {
            super(pandaIn);
            this.t_148_a = pandaIn;
        }

        @Override
        public void n_1700_B() {
            if (this.t_148_a.Setting()) {
                super.n_1700_B();
            }
        }
    }

    public static final class G_564_y
    extends Enum<G_564_y> {
        public static final /* enum */ G_564_y n_1700_B = new G_564_y(0, "normal", false);
        public static final /* enum */ G_564_y J_1907_R = new G_564_y(1, "lazy", false);
        public static final /* enum */ G_564_y R_4764_Y = new G_564_y(2, "worried", false);
        public static final /* enum */ G_564_y G_564_y = new G_564_y(3, "playful", false);
        public static final /* enum */ G_564_y P_1922_E = new G_564_y(4, "brown", true);
        public static final /* enum */ G_564_y u_1723_Y = new G_564_y(5, "weak", true);
        public static final /* enum */ G_564_y v_4262_N = new G_564_y(6, "aggressive", false);
        private static final G_564_y[] w_1484_f;
        private final int t_148_a;
        private final String s_956_w;
        private final boolean u_2550_I;
        private static final /* synthetic */ G_564_y[] M_588_G;

        public static G_564_y[] values() {
            return (G_564_y[])M_588_G.clone();
        }

        public static G_564_y valueOf(String name) {
            return Enum.valueOf(G_564_y.class, name);
        }

        private G_564_y(int p_i51468_3_, String p_i51468_4_, boolean p_i51468_5_) {
            this.t_148_a = p_i51468_3_;
            this.s_956_w = p_i51468_4_;
            this.u_2550_I = p_i51468_5_;
        }

        public int n_1700_B() {
            return this.t_148_a;
        }

        public String J_1907_R() {
            return this.s_956_w;
        }

        public boolean R_4764_Y() {
            return this.u_2550_I;
        }

        private static G_564_y n_1700_B(G_564_y mainGene, G_564_y hiddenGene) {
            if (mainGene.R_4764_Y()) {
                return mainGene == hiddenGene ? mainGene : n_1700_B;
            }
            return mainGene;
        }

        public static G_564_y n_1700_B(int indexIn) {
            if (indexIn < 0 || indexIn >= w_1484_f.length) {
                indexIn = 0;
            }
            return w_1484_f[indexIn];
        }

        public static G_564_y n_1700_B(String p_221108_0_) {
            for (G_564_y pandaentity$gene : lightning.product.j_3013_R$G_564_y.values()) {
                if (!pandaentity$gene.s_956_w.equals(p_221108_0_)) continue;
                return pandaentity$gene;
            }
            return n_1700_B;
        }

        public static G_564_y n_1700_B(Random randIn) {
            int i = randIn.nextInt(16);
            if (i == 0) {
                return J_1907_R;
            }
            if (i == 1) {
                return R_4764_Y;
            }
            if (i == 2) {
                return G_564_y;
            }
            if (i == 4) {
                return v_4262_N;
            }
            if (i < 9) {
                return u_1723_Y;
            }
            return i < 11 ? P_1922_E : n_1700_B;
        }

        private static /* synthetic */ G_564_y[] G_564_y() {
            return new G_564_y[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            M_588_G = lightning.product.j_3013_R$G_564_y.G_564_y();
            w_1484_f = (G_564_y[])Arrays.stream(lightning.product.j_3013_R$G_564_y.values()).sorted(Comparator.comparingInt(G_564_y::n_1700_B)).toArray(G_564_y[]::new);
        }
    }

    static class w_1484_f
    extends PanicGoal {
        private final j_3013_R v_4262_N;

        public w_1484_f(j_3013_R pandaIn, double speedIn) {
            super(pandaIn, speedIn);
            this.v_4262_N = pandaIn;
        }

        @Override
        public boolean n_1700_B() {
            if (!this.v_4262_N.RealmsPersistence()) {
                return false;
            }
            c_1514_x blockpos = this.n_1700_B(this.n_1700_B.O_508_d, this.n_1700_B, 5, 4);
            if (blockpos != null) {
                this.R_4764_Y = blockpos.getX();
                this.G_564_y = blockpos.getY();
                this.P_1922_E = blockpos.getZ();
                return true;
            }
            return this.v_4262_N();
        }

        @Override
        public boolean J_1907_R() {
            if (this.v_4262_N.V_1176_p()) {
                this.v_4262_N.e_4240_b().h_1847_R();
                return false;
            }
            return super.J_1907_R();
        }
    }

    class u_1723_Y
    extends BreedGoal {
        private final j_3013_R G_564_y;
        private int P_1922_E;

        public u_1723_Y(j_3013_R this$0, j_3013_R pandaIn, double speedIn) {
            super(pandaIn, speedIn);
            this.G_564_y = pandaIn;
        }

        @Override
        public boolean n_1700_B() {
            if (super.n_1700_B() && this.G_564_y.y_4642_Y() == 0) {
                if (!this.w_1484_f()) {
                    if (this.P_1922_E <= this.G_564_y.RealmsWorldResetDto) {
                        this.G_564_y.Y_601_j(32);
                        this.P_1922_E = this.G_564_y.RealmsWorldResetDto + 600;
                        if (this.G_564_y.w_1457_N()) {
                            a_3913_L playerentity = this.J_1907_R.n_1700_B(Y_259_p, this.G_564_y);
                            this.G_564_y.t_4043_B.n_1700_B(playerentity);
                        }
                    }
                    return false;
                }
                return true;
            }
            return false;
        }

        private boolean w_1484_f() {
            c_1514_x blockpos = this.G_564_y.b_2312_j();
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 8; ++j) {
                    int k = 0;
                    while (k <= j) {
                        int l;
                        int n = l = k < j && k > -j ? j : 0;
                        while (l <= j) {
                            blockpos$mutable.n_1700_B(blockpos, k, i, l);
                            if (this.J_1907_R.getBlockState(blockpos$mutable).n_1700_B(a_3742_W.t_1509_b)) {
                                return true;
                            }
                            l = l > 0 ? -l : 1 - l;
                        }
                        k = k > 0 ? -k : 1 - k;
                    }
                }
            }
            return false;
        }
    }

    static class n_1700_B
    extends b_4953_N {
        private final j_3013_R J_1907_R;

        public n_1700_B(j_3013_R pandaIn, double speedIn, boolean useLongMemory) {
            super(pandaIn, speedIn, useLongMemory);
            this.J_1907_R = pandaIn;
        }

        @Override
        public boolean n_1700_B() {
            return this.J_1907_R.Setting() && super.n_1700_B();
        }
    }

    static class J_1907_R<T extends r_4811_B>
    extends AvoidEntityGoal<T> {
        private final j_3013_R t_148_a;

        public J_1907_R(j_3013_R pandaIn, Class<T> entityClassToAvoidIn, float distance, double nearSpeedIn, double farSpeedIn) {
            super(pandaIn, entityClassToAvoidIn, distance, nearSpeedIn, farSpeedIn, I_408_V.v_4262_N::test);
            this.t_148_a = pandaIn;
        }

        @Override
        public boolean n_1700_B() {
            return this.t_148_a.R_2822_N() && this.t_148_a.Setting() && super.n_1700_B();
        }
    }

    class u_2550_I
    extends Goal {
        private int J_1907_R;

        public u_2550_I() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            if (this.J_1907_R <= j_3013_R.this.RealmsWorldResetDto && !j_3013_R.this.d_() && !j_3013_R.this.RowButton() && j_3013_R.this.Setting() && j_3013_R.this.y_4642_Y() <= 0) {
                List<n_1494_c> list = j_3013_R.this.O_508_d.n_1700_B(n_1494_c.class, j_3013_R.this.i_601_W().grow(6.0, 6.0, 6.0), x_607_J);
                return !list.isEmpty() || !j_3013_R.this.J_1907_R(e_1174_E.n_1700_B).n_1700_B();
            }
            return false;
        }

        @Override
        public boolean J_1907_R() {
            if (!j_3013_R.this.RowButton() && (j_3013_R.this.D_3612_q() || j_3013_R.this.RealmsWorldOptions.nextInt(600) != 1)) {
                return j_3013_R.this.RealmsWorldOptions.nextInt(2000) != 1;
            }
            return false;
        }

        @Override
        public void P_1922_E() {
            if (!j_3013_R.this.V_1176_p() && !j_3013_R.this.J_1907_R(e_1174_E.n_1700_B).n_1700_B()) {
                j_3013_R.this.O_3016_i();
            }
        }

        @Override
        public void R_4764_Y() {
            List<n_1494_c> list = j_3013_R.this.O_508_d.n_1700_B(n_1494_c.class, j_3013_R.this.i_601_W().grow(8.0, 8.0, 8.0), x_607_J);
            if (!list.isEmpty() && j_3013_R.this.J_1907_R(e_1174_E.n_1700_B).n_1700_B()) {
                j_3013_R.this.e_4240_b().n_1700_B((N_4263_v)list.get(0), (double)1.2f);
            } else if (!j_3013_R.this.J_1907_R(e_1174_E.n_1700_B).n_1700_B()) {
                j_3013_R.this.O_3016_i();
            }
            this.J_1907_R = 0;
        }

        @Override
        public void G_564_y() {
            Z_1993_T itemstack = j_3013_R.this.J_1907_R(e_1174_E.n_1700_B);
            if (!itemstack.n_1700_B()) {
                j_3013_R.this.a_(itemstack);
                j_3013_R.this.n_1700_B(e_1174_E.n_1700_B, Z_1993_T.J_1907_R);
                int i = j_3013_R.this.D_3612_q() ? j_3013_R.this.RealmsWorldOptions.nextInt(50) + 10 : j_3013_R.this.RealmsWorldOptions.nextInt(150) + 10;
                this.J_1907_R = j_3013_R.this.RealmsWorldResetDto + i * 20;
            }
            j_3013_R.this.w_1457_N(false);
        }
    }

    static class P_1922_E
    extends Goal {
        private final j_3013_R n_1700_B;
        private int J_1907_R;

        public P_1922_E(j_3013_R pandaIn) {
            this.n_1700_B = pandaIn;
        }

        @Override
        public boolean n_1700_B() {
            return this.J_1907_R < this.n_1700_B.RealmsWorldResetDto && this.n_1700_B.D_3612_q() && this.n_1700_B.Setting() && this.n_1700_B.RealmsWorldOptions.nextInt(400) == 1;
        }

        @Override
        public boolean J_1907_R() {
            if (!this.n_1700_B.RowButton() && (this.n_1700_B.D_3612_q() || this.n_1700_B.RealmsWorldOptions.nextInt(600) != 1)) {
                return this.n_1700_B.RealmsWorldOptions.nextInt(2000) != 1;
            }
            return false;
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B.Y_601_j(true);
            this.J_1907_R = 0;
        }

        @Override
        public void G_564_y() {
            this.n_1700_B.Y_601_j(false);
            this.J_1907_R = this.n_1700_B.RealmsWorldResetDto + 200;
        }
    }

    static class R_4764_Y
    extends Goal {
        private final j_3013_R n_1700_B;

        public R_4764_Y(j_3013_R pandaIn) {
            this.n_1700_B = pandaIn;
        }

        @Override
        public boolean n_1700_B() {
            if (this.n_1700_B.d_() && this.n_1700_B.Setting()) {
                if (this.n_1700_B.p_1458_L() && this.n_1700_B.RealmsWorldOptions.nextInt(500) == 1) {
                    return true;
                }
                return this.n_1700_B.RealmsWorldOptions.nextInt(6000) == 1;
            }
            return false;
        }

        @Override
        public boolean J_1907_R() {
            return false;
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B.Q_2552_b(true);
        }
    }

    static class M_588_G
    extends LookAtPlayerGoal {
        private final j_3013_R v_4262_N;

        public M_588_G(j_3013_R p_i51458_1_, Class<? extends r_4811_B> p_i51458_2_, float p_i51458_3_) {
            super(p_i51458_1_, p_i51458_2_, p_i51458_3_);
            this.v_4262_N = p_i51458_1_;
        }

        public void n_1700_B(r_4811_B p_229975_1_) {
            this.J_1907_R = p_229975_1_;
        }

        @Override
        public boolean J_1907_R() {
            return this.J_1907_R != null && super.J_1907_R();
        }

        @Override
        public boolean n_1700_B() {
            if (this.n_1700_B.M_3508_C().nextFloat() >= this.G_564_y) {
                return false;
            }
            if (this.J_1907_R == null) {
                this.J_1907_R = this.P_1922_E == a_3913_L.class ? this.n_1700_B.O_508_d.n_1700_B(this.u_1723_Y, this.n_1700_B, this.n_1700_B.O_3598_v(), this.n_1700_B.X_2048_Y(), this.n_1700_B.l_2647_k()) : this.n_1700_B.O_508_d.J_1907_R(this.P_1922_E, this.u_1723_Y, this.n_1700_B, this.n_1700_B.O_3598_v(), this.n_1700_B.X_2048_Y(), this.n_1700_B.l_2647_k(), this.n_1700_B.i_601_W().grow(this.R_4764_Y, 3.0, this.R_4764_Y));
            }
            return this.v_4262_N.Setting() && this.J_1907_R != null;
        }

        @Override
        public void P_1922_E() {
            if (this.J_1907_R != null) {
                super.P_1922_E();
            }
        }
    }

    static class s_956_w
    extends Goal {
        private final j_3013_R n_1700_B;

        public s_956_w(j_3013_R pandaIn) {
            this.n_1700_B = pandaIn;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R, Goal.n_1700_B.R_4764_Y));
        }

        @Override
        public boolean n_1700_B() {
            if ((this.n_1700_B.d_() || this.n_1700_B.ModuleCategory()) && this.n_1700_B.e_1992_r) {
                if (!this.n_1700_B.Setting()) {
                    return false;
                }
                float f = this.n_1700_B.p_178_J * ((float)Math.PI / 180);
                int i = 0;
                int j = 0;
                float f1 = -u_530_F.n_1700_B(f);
                float f2 = u_530_F.J_1907_R(f);
                if ((double)Math.abs(f1) > 0.5) {
                    i = (int)((float)i + f1 / Math.abs(f1));
                }
                if ((double)Math.abs(f2) > 0.5) {
                    j = (int)((float)j + f2 / Math.abs(f2));
                }
                if (this.n_1700_B.O_508_d.getBlockState(this.n_1700_B.b_2312_j().add(i, -1, j)).v_4262_N()) {
                    return true;
                }
                if (this.n_1700_B.ModuleCategory() && this.n_1700_B.RealmsWorldOptions.nextInt(60) == 1) {
                    return true;
                }
                return this.n_1700_B.RealmsWorldOptions.nextInt(500) == 1;
            }
            return false;
        }

        @Override
        public boolean J_1907_R() {
            return false;
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B.C_2741_M(true);
        }

        @Override
        public boolean r_() {
            return false;
        }
    }

    static class t_148_a
    extends g_3408_G {
        private final j_3013_R n_1700_B;

        public t_148_a(j_3013_R pandaIn, Class<?> ... p_i51462_2_) {
            super(pandaIn, p_i51462_2_);
            this.n_1700_B = pandaIn;
        }

        @Override
        public boolean J_1907_R() {
            if (!this.n_1700_B.Q_2552_b && !this.n_1700_B.C_2741_M) {
                return super.J_1907_R();
            }
            this.n_1700_B.R_4764_Y((r_4811_B)null);
            return false;
        }

        @Override
        protected void n_1700_B(Z_530_i mobIn, r_4811_B targetIn) {
            if (mobIn instanceof j_3013_R && ((j_3013_R)mobIn).P_2272_O()) {
                mobIn.R_4764_Y(targetIn);
            }
        }
    }
}



