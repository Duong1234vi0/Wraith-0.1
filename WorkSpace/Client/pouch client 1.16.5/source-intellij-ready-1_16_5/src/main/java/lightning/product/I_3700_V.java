/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.F_1241_B;
import lightning.product.Attributes;
import lightning.product.J_548_T;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_2450_T;
import lightning.product.WitherSkull;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.W_1200_P;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.g_1941_L;
import lightning.product.MobType;
import lightning.product.g_3408_G;
import lightning.product.RangedAttackMob;
import lightning.product.BossEvent;
import lightning.product.h_256_u;
import lightning.product.h_384_L;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.k_2610_C;
import lightning.product.n_1494_c;
import lightning.product.ServerBossEvent;
import lightning.product.BlockTags;
import lightning.product.Goal;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.x_282_a;

public class I_3700_V
extends Monster
implements W_1200_P,
RangedAttackMob {
    private static final h_256_u<Integer> n_1700_B = C_4114_x.n_1700_B(I_3700_V.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Integer> J_1907_R = C_4114_x.n_1700_B(I_3700_V.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Integer> R_4764_Y = C_4114_x.n_1700_B(I_3700_V.class, EntityDataSerializers.J_1907_R);
    private static final List<h_256_u<Integer>> h_1847_R = ImmutableList.of(n_1700_B, J_1907_R, R_4764_Y);
    private static final h_256_u<Integer> Q_4569_t = C_4114_x.n_1700_B(I_3700_V.class, EntityDataSerializers.J_1907_R);
    private final float[] M_182_A = new float[2];
    private final float[] t_1786_h = new float[2];
    private final float[] multiplayerClientSuggestionProvider = new float[2];
    private final float[] w_1457_N = new float[2];
    private final int[] Y_601_j = new int[2];
    private final int[] Y_259_p = new int[2];
    private int Q_2552_b;
    private final ServerBossEvent C_2741_M = (ServerBossEvent)new ServerBossEvent(this.c_(), BossEvent.n_1700_B.u_1723_Y, BossEvent.J_1907_R.n_1700_B).n_1700_B(true);
    private static final Predicate<r_4811_B> k_2293_S = p_213797_0_ -> p_213797_0_.F_2860_q() != MobType.J_1907_R && p_213797_0_.r_4790_y();
    private static final TargetingConditions q_2307_F = new TargetingConditions().n_1700_B(20.0).n_1700_B(k_2293_S);

    public I_3700_V(t_5_h<? extends I_3700_V> wither, b_4507_u world) {
        super((t_5_h<? extends Monster>)wither, world);
        this.t_1786_h(this.L_1733_J());
        this.e_4240_b().R_4764_Y(true);
        this.P_1922_E = 50;
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new n_1700_B());
        this.s_956_w.n_1700_B(2, new J_548_T(this, 1.0, 40, 20.0f));
        this.s_956_w.n_1700_B(5, new g_1941_L(this, 1.0));
        this.s_956_w.n_1700_B(6, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(7, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, new Class[0]));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<Z_530_i>(this, Z_530_i.class, 0, false, false, k_2293_S));
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, 0);
        this.l_4537_E.n_1700_B(J_1907_R, 0);
        this.l_4537_E.n_1700_B(R_4764_Y, 0);
        this.l_4537_E.n_1700_B(Q_4569_t, 0);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Invul", this.h_1640_b());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.w_1457_N(compound.w_1484_f("Invul"));
        if (this.t_3452_g()) {
            this.C_2741_M.n_1700_B(this.c_());
        }
    }

    @Override
    public void n_1700_B(@Nullable x_282_a name) {
        super.n_1700_B(name);
        this.C_2741_M.n_1700_B(this.c_());
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.V_2454_J;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.Y_3462_U;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.H_4584_y;
    }

    @Override
    public void Y_1740_V() {
        N_4263_v entity;
        e_2866_D vector3d = this.I_4348_c().G_564_y(1.0, 0.6, 1.0);
        if (!this.O_508_d.Y_259_p && this.Y_601_j(0) > 0 && (entity = this.O_508_d.J_1907_R(this.Y_601_j(0))) != null) {
            double d0 = vector3d.R_4764_Y;
            if (this.X_2960_b() < entity.X_2960_b() || !this.n_1700_B() && this.X_2960_b() < entity.X_2960_b() + 5.0) {
                d0 = Math.max(0.0, d0);
                d0 += 0.3 - d0 * (double)0.6f;
            }
            vector3d = new e_2866_D(vector3d.J_1907_R, d0, vector3d.G_564_y);
            e_2866_D vector3d1 = new e_2866_D(entity.O_3598_v() - this.O_3598_v(), 0.0, entity.l_2647_k() - this.l_2647_k());
            if (I_3700_V.R_4764_Y(vector3d1) > 9.0) {
                e_2866_D vector3d2 = vector3d1.G_564_y();
                vector3d = vector3d.J_1907_R(vector3d2.J_1907_R * 0.3 - vector3d.J_1907_R * 0.6, 0.0, vector3d2.G_564_y * 0.3 - vector3d.G_564_y * 0.6);
            }
        }
        this.v_4262_N(vector3d);
        if (I_3700_V.R_4764_Y(vector3d) > 0.05) {
            this.p_178_J = (float)u_530_F.G_564_y(vector3d.G_564_y, vector3d.J_1907_R) * 57.295776f - 90.0f;
        }
        super.Y_1740_V();
        for (int i = 0; i < 2; ++i) {
            this.w_1457_N[i] = this.t_1786_h[i];
            this.multiplayerClientSuggestionProvider[i] = this.M_182_A[i];
        }
        for (int j = 0; j < 2; ++j) {
            int k = this.Y_601_j(j + 1);
            N_4263_v entity1 = null;
            if (k > 0) {
                entity1 = this.O_508_d.J_1907_R(k);
            }
            if (entity1 != null) {
                double d9 = this.Y_259_p(j + 1);
                double d1 = this.Q_2552_b(j + 1);
                double d3 = this.C_2741_M(j + 1);
                double d4 = entity1.O_3598_v() - d9;
                double d5 = entity1.X_2048_Y() - d1;
                double d6 = entity1.l_2647_k() - d3;
                double d7 = u_530_F.n_1700_B(d4 * d4 + d6 * d6);
                float f = (float)(u_530_F.G_564_y(d6, d4) * 57.2957763671875) - 90.0f;
                float f1 = (float)(-(u_530_F.G_564_y(d5, d7) * 57.2957763671875));
                this.M_182_A[j] = this.n_1700_B(this.M_182_A[j], f1, 40.0f);
                this.t_1786_h[j] = this.n_1700_B(this.t_1786_h[j], f, 10.0f);
                continue;
            }
            this.t_1786_h[j] = this.n_1700_B(this.t_1786_h[j], this.C_1162_e, 10.0f);
        }
        boolean flag = this.n_1700_B();
        for (int l = 0; l < 3; ++l) {
            double d8 = this.Y_259_p(l);
            double d10 = this.Q_2552_b(l);
            double d2 = this.C_2741_M(l);
            this.O_508_d.n_1700_B(ParticleTypes.B_1668_F, d8 + this.RealmsWorldOptions.nextGaussian() * (double)0.3f, d10 + this.RealmsWorldOptions.nextGaussian() * (double)0.3f, d2 + this.RealmsWorldOptions.nextGaussian() * (double)0.3f, 0.0, 0.0, 0.0);
            if (!flag || this.O_508_d.w_1457_N.nextInt(4) != 0) continue;
            this.O_508_d.n_1700_B(ParticleTypes.Y_259_p, d8 + this.RealmsWorldOptions.nextGaussian() * (double)0.3f, d10 + this.RealmsWorldOptions.nextGaussian() * (double)0.3f, d2 + this.RealmsWorldOptions.nextGaussian() * (double)0.3f, (double)0.7f, (double)0.7f, 0.5);
        }
        if (this.h_1640_b() > 0) {
            for (int i1 = 0; i1 < 3; ++i1) {
                this.O_508_d.n_1700_B(ParticleTypes.Y_259_p, this.O_3598_v() + this.RealmsWorldOptions.nextGaussian(), this.X_2960_b() + (double)(this.RealmsWorldOptions.nextFloat() * 3.3f), this.l_2647_k() + this.RealmsWorldOptions.nextGaussian(), (double)0.7f, (double)0.7f, 0.9f);
            }
        }
    }

    @Override
    protected void X_933_l() {
        if (this.h_1640_b() > 0) {
            int j1 = this.h_1640_b() - 1;
            if (j1 <= 0) {
                F_1241_B.n_1700_B explosion$mode = this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R) ? F_1241_B.n_1700_B.R_4764_Y : F_1241_B.n_1700_B.n_1700_B;
                this.O_508_d.n_1700_B(this, this.O_3598_v(), this.X_2048_Y(), this.l_2647_k(), 7.0f, false, explosion$mode);
                if (!this.y_1700_S()) {
                    this.O_508_d.J_1907_R(1023, this.b_2312_j(), 0);
                }
            }
            this.w_1457_N(j1);
            if (this.RealmsWorldResetDto % 10 == 0) {
                this.n_1700_B(10.0f);
            }
        } else {
            super.X_933_l();
            block0: for (int i = 1; i < 3; ++i) {
                int k1;
                if (this.RealmsWorldResetDto < this.Y_601_j[i - 1]) continue;
                this.Y_601_j[i - 1] = this.RealmsWorldResetDto + 10 + this.RealmsWorldOptions.nextInt(10);
                if (this.O_508_d.x_607_J() == R_2450_T.R_4764_Y || this.O_508_d.x_607_J() == R_2450_T.G_564_y) {
                    int j3 = i - 1;
                    int k3 = this.Y_259_p[i - 1];
                    this.Y_259_p[j3] = this.Y_259_p[i - 1] + 1;
                    if (k3 > 15) {
                        float f = 10.0f;
                        float f1 = 5.0f;
                        double d0 = u_530_F.n_1700_B(this.RealmsWorldOptions, this.O_3598_v() - 10.0, this.O_3598_v() + 10.0);
                        double d1 = u_530_F.n_1700_B(this.RealmsWorldOptions, this.X_2960_b() - 5.0, this.X_2960_b() + 5.0);
                        double d2 = u_530_F.n_1700_B(this.RealmsWorldOptions, this.l_2647_k() - 10.0, this.l_2647_k() + 10.0);
                        this.n_1700_B(i + 1, d0, d1, d2, true);
                        this.Y_259_p[i - 1] = 0;
                    }
                }
                if ((k1 = this.Y_601_j(i)) > 0) {
                    N_4263_v entity = this.O_508_d.J_1907_R(k1);
                    if (entity != null && entity.RealmsLongRunningMcoTaskScreen() && !(this.G_564_y(entity) > 900.0) && this.c_3005_b(entity)) {
                        if (entity instanceof a_3913_L && ((a_3913_L)entity).C_415_h.n_1700_B) {
                            this.n_1700_B(i, 0);
                            continue;
                        }
                        this.n_1700_B(i + 1, (r_4811_B)entity);
                        this.Y_601_j[i - 1] = this.RealmsWorldResetDto + 40 + this.RealmsWorldOptions.nextInt(20);
                        this.Y_259_p[i - 1] = 0;
                        continue;
                    }
                    this.n_1700_B(i, 0);
                    continue;
                }
                List<r_4811_B> list = this.O_508_d.n_1700_B(r_4811_B.class, q_2307_F, this, this.i_601_W().grow(20.0, 8.0, 20.0));
                for (int j2 = 0; j2 < 10 && !list.isEmpty(); ++j2) {
                    r_4811_B livingentity = list.get(this.RealmsWorldOptions.nextInt(list.size()));
                    if (livingentity != this && livingentity.RealmsLongRunningMcoTaskScreen() && this.c_3005_b(livingentity)) {
                        if (livingentity instanceof a_3913_L) {
                            if (((a_3913_L)livingentity).C_415_h.n_1700_B) continue block0;
                            this.n_1700_B(i, livingentity.j_276_v());
                            continue block0;
                        }
                        this.n_1700_B(i, livingentity.j_276_v());
                        continue block0;
                    }
                    list.remove(livingentity);
                }
            }
            if (this.t_148_a() != null) {
                this.n_1700_B(0, this.t_148_a().j_276_v());
            } else {
                this.n_1700_B(0, 0);
            }
            if (this.Q_2552_b > 0) {
                --this.Q_2552_b;
                if (this.Q_2552_b == 0 && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
                    int i1 = u_530_F.R_4764_Y(this.X_2960_b());
                    int l1 = u_530_F.R_4764_Y(this.O_3598_v());
                    int i2 = u_530_F.R_4764_Y(this.l_2647_k());
                    boolean flag = false;
                    for (int k2 = -1; k2 <= 1; ++k2) {
                        for (int l2 = -1; l2 <= 1; ++l2) {
                            for (int j = 0; j <= 3; ++j) {
                                int i3 = l1 + k2;
                                int k = i1 + j;
                                int l = i2 + l2;
                                c_1514_x blockpos = new c_1514_x(i3, k, l);
                                K_4074_S blockstate = this.O_508_d.getBlockState(blockpos);
                                if (!I_3700_V.R_4764_Y(blockstate)) continue;
                                flag = this.O_508_d.n_1700_B(blockpos, true, this) || flag;
                            }
                        }
                    }
                    if (flag) {
                        this.O_508_d.n_1700_B((a_3913_L)null, 1022, this.b_2312_j(), 0);
                    }
                }
            }
            if (this.RealmsWorldResetDto % 20 == 0) {
                this.n_1700_B(1.0f);
            }
            this.C_2741_M.n_1700_B(this.g_46_E() / this.L_1733_J());
        }
    }

    public static boolean R_4764_Y(K_4074_S blockIn) {
        return !blockIn.v_4262_N() && !BlockTags.A_1038_p.n_1700_B(blockIn.J_1907_R());
    }

    public void u_1723_Y() {
        this.w_1457_N(220);
        this.t_1786_h(this.L_1733_J() / 3.0f);
    }

    @Override
    public void n_1700_B(K_4074_S state, e_2866_D motionMultiplierIn) {
    }

    @Override
    public void J_1907_R(B_4088_l player) {
        super.J_1907_R(player);
        this.C_2741_M.n_1700_B(player);
    }

    @Override
    public void R_4764_Y(B_4088_l player) {
        super.R_4764_Y(player);
        this.C_2741_M.J_1907_R(player);
    }

    private double Y_259_p(int head) {
        if (head <= 0) {
            return this.O_3598_v();
        }
        float f = (this.C_1162_e + (float)(180 * (head - 1))) * ((float)Math.PI / 180);
        float f1 = u_530_F.J_1907_R(f);
        return this.O_3598_v() + (double)f1 * 1.3;
    }

    private double Q_2552_b(int head) {
        return head <= 0 ? this.X_2960_b() + 3.0 : this.X_2960_b() + 2.2;
    }

    private double C_2741_M(int head) {
        if (head <= 0) {
            return this.l_2647_k();
        }
        float f = (this.C_1162_e + (float)(180 * (head - 1))) * ((float)Math.PI / 180);
        float f1 = u_530_F.n_1700_B(f);
        return this.l_2647_k() + (double)f1 * 1.3;
    }

    private float n_1700_B(float p_82204_1_, float p_82204_2_, float p_82204_3_) {
        float f = u_530_F.v_4262_N(p_82204_2_ - p_82204_1_);
        if (f > p_82204_3_) {
            f = p_82204_3_;
        }
        if (f < -p_82204_3_) {
            f = -p_82204_3_;
        }
        return p_82204_1_ + f;
    }

    private void n_1700_B(int head, r_4811_B target) {
        this.n_1700_B(head, target.O_3598_v(), target.X_2960_b() + (double)target.X_1313_W() * 0.5, target.l_2647_k(), head == 0 && this.RealmsWorldOptions.nextFloat() < 0.001f);
    }

    private void n_1700_B(int head, double x, double y, double z, boolean invulnerable) {
        if (!this.y_1700_S()) {
            this.O_508_d.n_1700_B((a_3913_L)null, 1024, this.b_2312_j(), 0);
        }
        double d0 = this.Y_259_p(head);
        double d1 = this.Q_2552_b(head);
        double d2 = this.C_2741_M(head);
        double d3 = x - d0;
        double d4 = y - d1;
        double d5 = z - d2;
        WitherSkull witherskullentity = new WitherSkull(this.O_508_d, this, d3, d4, d5);
        witherskullentity.J_1907_R(this);
        if (invulnerable) {
            witherskullentity.n_1700_B(true);
        }
        witherskullentity.Q_4569_t(d0, d1, d2);
        this.O_508_d.a_(witherskullentity);
    }

    @Override
    public void J_1907_R(r_4811_B target, float distanceFactor) {
        this.n_1700_B(0, target);
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        if (source != P_11_z.w_1484_f && !(source.u_2550_I() instanceof I_3700_V)) {
            N_4263_v entity;
            if (this.h_1640_b() > 0 && source != P_11_z.P_4830_p) {
                return false;
            }
            if (this.n_1700_B() && (entity = source.s_956_w()) instanceof h_384_L) {
                return false;
            }
            N_4263_v entity1 = source.u_2550_I();
            if (entity1 != null && !(entity1 instanceof a_3913_L) && entity1 instanceof r_4811_B && ((r_4811_B)entity1).F_2860_q() == this.F_2860_q()) {
                return false;
            }
            if (this.Q_2552_b <= 0) {
                this.Q_2552_b = 20;
            }
            int i = 0;
            while (i < this.Y_259_p.length) {
                int n = i++;
                this.Y_259_p[n] = this.Y_259_p[n] + 3;
            }
            return super.n_1700_B(source, amount);
        }
        return false;
    }

    @Override
    protected void n_1700_B(P_11_z source, int looting, boolean recentlyHitIn) {
        super.n_1700_B(source, looting, recentlyHitIn);
        n_1494_c itementity = this.n_1700_B((q_1803_e)Items.FallingBlock);
        if (itementity != null) {
            itementity.M_182_A();
        }
    }

    @Override
    public void a_178_J() {
        if (this.O_508_d.x_607_J() == R_2450_T.n_1700_B && this.B_1668_F()) {
            this.Ops();
        } else {
            this.UploadTokenCache = 0;
        }
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    public boolean n_1700_B(k_2610_C effectInstanceIn) {
        return false;
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 300.0).n_1700_B(Attributes.G_564_y, 0.6f).n_1700_B(Attributes.J_1907_R, 40.0).n_1700_B(Attributes.t_148_a, 4.0);
    }

    public float n_1700_B(int head) {
        return this.t_1786_h[head];
    }

    public float J_1907_R(int head) {
        return this.M_182_A[head];
    }

    public int h_1640_b() {
        return this.l_4537_E.n_1700_B(Q_4569_t);
    }

    public void w_1457_N(int time) {
        this.l_4537_E.J_1907_R(Q_4569_t, time);
    }

    public int Y_601_j(int head) {
        return this.l_4537_E.n_1700_B(h_1847_R.get(head));
    }

    public void n_1700_B(int targetOffset, int newId) {
        this.l_4537_E.J_1907_R(h_1847_R.get(targetOffset), newId);
    }

    @Override
    public boolean n_1700_B() {
        return this.g_46_E() <= this.L_1733_J() / 2.0f;
    }

    @Override
    public MobType F_2860_q() {
        return MobType.J_1907_R;
    }

    @Override
    protected boolean u_2550_I(N_4263_v entityIn) {
        return false;
    }

    @Override
    public boolean L_103_L() {
        return false;
    }

    @Override
    public boolean J_1907_R(k_2610_C potioneffectIn) {
        return potioneffectIn.n_1700_B() == MobEffects.Y_601_j ? false : super.J_1907_R(potioneffectIn);
    }

    class n_1700_B
    extends Goal {
        public n_1700_B() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.R_4764_Y, Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            return I_3700_V.this.h_1640_b() > 0;
        }
    }
}


