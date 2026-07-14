/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.C_4114_x;
import lightning.product.C_990_G;
import lightning.product.D_1436_R;
import lightning.product.D_38_f;
import lightning.product.Attributes;
import lightning.product.I_408_V;
import lightning.product.EndPodiumFeature;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.M_3179_b;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3354_l;
import lightning.product.SoundEvents;
import lightning.product.V_600_c;
import lightning.product.SoundEvent;
import lightning.product.Z_1164_j;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_1722_e;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.f_2785_f;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.k_2610_C;
import lightning.product.n_4637_L;
import lightning.product.BlockTags;
import lightning.product.EnderDragonPart;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.DragonPhaseInstance;
import lightning.product.ParticleTypes;
import lightning.product.Material;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.x_1835_e;
import lightning.product.z_2963_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class b_2971_b
extends Z_530_i
implements x_1835_e {
    private static final Logger Y_259_p = LogManager.getLogger();
    public static final h_256_u<Integer> n_1700_B = C_4114_x.n_1700_B(b_2971_b.class, EntityDataSerializers.J_1907_R);
    private static final TargetingConditions Q_2552_b = new TargetingConditions().n_1700_B(64.0);
    public final double[][] J_1907_R = new double[64][3];
    public int R_4764_Y = -1;
    private final EnderDragonPart[] C_2741_M;
    public final EnderDragonPart h_1847_R;
    private final EnderDragonPart k_2293_S;
    private final EnderDragonPart q_2307_F;
    private final EnderDragonPart Z_875_P;
    private final EnderDragonPart c_3005_b;
    private final EnderDragonPart H_2857_Y;
    private final EnderDragonPart A_4115_X;
    private final EnderDragonPart Y_1740_V;
    public float Q_4569_t;
    public float M_182_A;
    public boolean t_1786_h;
    public int multiplayerClientSuggestionProvider;
    public float w_1457_N;
    @Nullable
    public V_3354_l Y_601_j;
    @Nullable
    private final C_990_G t_4043_B;
    private final V_600_c x_607_J;
    private int e_4240_b = 100;
    private int n_3318_d;
    private final D_1436_R[] d_2427_y = new D_1436_R[24];
    private final int[] z_1737_N = new int[24];
    private final M_3179_b v_4276_D = new M_3179_b();

    public b_2971_b(t_5_h<? extends b_2971_b> type, b_4507_u worldIn) {
        super((t_5_h<? extends Z_530_i>)t_5_h.Y_601_j, worldIn);
        this.h_1847_R = new EnderDragonPart(this, "head", 1.0f, 1.0f);
        this.k_2293_S = new EnderDragonPart(this, "neck", 3.0f, 3.0f);
        this.q_2307_F = new EnderDragonPart(this, "body", 5.0f, 3.0f);
        this.Z_875_P = new EnderDragonPart(this, "tail", 2.0f, 2.0f);
        this.c_3005_b = new EnderDragonPart(this, "tail", 2.0f, 2.0f);
        this.H_2857_Y = new EnderDragonPart(this, "tail", 2.0f, 2.0f);
        this.A_4115_X = new EnderDragonPart(this, "wing", 4.0f, 2.0f);
        this.Y_1740_V = new EnderDragonPart(this, "wing", 4.0f, 2.0f);
        this.C_2741_M = new EnderDragonPart[]{this.h_1847_R, this.k_2293_S, this.q_2307_F, this.Z_875_P, this.c_3005_b, this.H_2857_Y, this.A_4115_X, this.Y_1740_V};
        this.t_1786_h(this.L_1733_J());
        this.j_1564_a = true;
        this.RowButton = true;
        this.t_4043_B = worldIn instanceof e_3591_l ? ((e_3591_l)worldIn).UploadStatus() : null;
        this.x_607_J = new V_600_c(this);
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 200.0);
    }

    @Override
    protected void a_() {
        super.a_();
        this.D_60_a().n_1700_B(n_1700_B, Z_1164_j.u_2550_I.J_1907_R());
    }

    public double[] n_1700_B(int p_70974_1_, float partialTicks) {
        if (this.Z_2812_M()) {
            partialTicks = 0.0f;
        }
        partialTicks = 1.0f - partialTicks;
        int i = this.R_4764_Y - p_70974_1_ & 0x3F;
        int j = this.R_4764_Y - p_70974_1_ - 1 & 0x3F;
        double[] adouble = new double[3];
        double d0 = this.J_1907_R[i][0];
        double d1 = u_530_F.u_1723_Y(this.J_1907_R[j][0] - d0);
        adouble[0] = d0 + d1 * (double)partialTicks;
        d0 = this.J_1907_R[i][1];
        d1 = this.J_1907_R[j][1] - d0;
        adouble[1] = d0 + d1 * (double)partialTicks;
        adouble[2] = u_530_F.G_564_y((double)partialTicks, this.J_1907_R[i][2], this.J_1907_R[j][2]);
        return adouble;
    }

    @Override
    public void Y_1740_V() {
        if (this.O_508_d.Y_259_p) {
            this.t_1786_h(this.g_46_E());
            if (!this.y_1700_S()) {
                float f = u_530_F.J_1907_R(this.M_182_A * ((float)Math.PI * 2));
                float f1 = u_530_F.J_1907_R(this.Q_4569_t * ((float)Math.PI * 2));
                if (f1 <= -0.3f && f >= -0.3f) {
                    this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.g_4560_H, this.r_2478_U(), 5.0f, 0.8f + this.RealmsWorldOptions.nextFloat() * 0.3f, false);
                }
                if (!this.x_607_J.n_1700_B().u_() && --this.e_4240_b < 0) {
                    this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.z_2025_Z, this.r_2478_U(), 2.5f, 0.8f + this.RealmsWorldOptions.nextFloat() * 0.3f, false);
                    this.e_4240_b = 200 + this.RealmsWorldOptions.nextInt(200);
                }
            }
        }
        this.Q_4569_t = this.M_182_A;
        if (this.Z_2812_M()) {
            float f11 = (this.RealmsWorldOptions.nextFloat() - 0.5f) * 8.0f;
            float f13 = (this.RealmsWorldOptions.nextFloat() - 0.5f) * 4.0f;
            float f14 = (this.RealmsWorldOptions.nextFloat() - 0.5f) * 8.0f;
            this.O_508_d.n_1700_B(ParticleTypes.C_2741_M, this.O_3598_v() + (double)f11, this.X_2960_b() + 2.0 + (double)f13, this.l_2647_k() + (double)f14, 0.0, 0.0, 0.0);
        } else {
            this.y_2447_C();
            e_2866_D vector3d4 = this.I_4348_c();
            float f12 = 0.2f / (u_530_F.n_1700_B(b_2971_b.R_4764_Y(vector3d4)) * 10.0f + 1.0f);
            this.M_182_A = this.x_607_J.n_1700_B().u_() ? (this.M_182_A += 0.1f) : (this.t_1786_h ? (this.M_182_A += f12 * 0.5f) : (this.M_182_A += (f12 *= (float)Math.pow(2.0, vector3d4.R_4764_Y))));
            this.p_178_J = u_530_F.v_4262_N(this.p_178_J);
            if (this.n_473_l()) {
                this.M_182_A = 0.5f;
            } else {
                if (this.R_4764_Y < 0) {
                    for (int i = 0; i < this.J_1907_R.length; ++i) {
                        this.J_1907_R[i][0] = this.p_178_J;
                        this.J_1907_R[i][1] = this.X_2960_b();
                    }
                }
                if (++this.R_4764_Y == this.J_1907_R.length) {
                    this.R_4764_Y = 0;
                }
                this.J_1907_R[this.R_4764_Y][0] = this.p_178_J;
                this.J_1907_R[this.R_4764_Y][1] = this.X_2960_b();
                if (this.O_508_d.Y_259_p) {
                    if (this.O_1309_Q > 0) {
                        double d7 = this.O_3598_v() + (this.O_2934_T - this.O_3598_v()) / (double)this.O_1309_Q;
                        double d0 = this.X_2960_b() + (this.l_4088_R - this.X_2960_b()) / (double)this.O_1309_Q;
                        double d1 = this.l_2647_k() + (this.Z_735_d - this.l_2647_k()) / (double)this.O_1309_Q;
                        double d2 = u_530_F.u_1723_Y(this.P_925_e - (double)this.p_178_J);
                        this.p_178_J = (float)((double)this.p_178_J + d2 / (double)this.O_1309_Q);
                        this.f_4016_n = (float)((double)this.f_4016_n + (this.X_4895_T - (double)this.f_4016_n) / (double)this.O_1309_Q);
                        --this.O_1309_Q;
                        this.J_1907_R(d7, d0, d1);
                        this.J_1907_R(this.p_178_J, this.f_4016_n);
                    }
                    this.x_607_J.n_1700_B().n_1700_B();
                } else {
                    e_2866_D vector3d;
                    DragonPhaseInstance iphase = this.x_607_J.n_1700_B();
                    iphase.J_1907_R();
                    if (this.x_607_J.n_1700_B() != iphase) {
                        iphase = this.x_607_J.n_1700_B();
                        iphase.J_1907_R();
                    }
                    if ((vector3d = iphase.u_1723_Y()) != null) {
                        double d8 = vector3d.J_1907_R - this.O_3598_v();
                        double d9 = vector3d.R_4764_Y - this.X_2960_b();
                        double d10 = vector3d.G_564_y - this.l_2647_k();
                        double d3 = d8 * d8 + d9 * d9 + d10 * d10;
                        float f6 = iphase.P_1922_E();
                        double d4 = u_530_F.n_1700_B(d8 * d8 + d10 * d10);
                        if (d4 > 0.0) {
                            d9 = u_530_F.n_1700_B(d9 / d4, (double)(-f6), (double)f6);
                        }
                        this.v_4262_N(this.I_4348_c().J_1907_R(0.0, d9 * 0.01, 0.0));
                        this.p_178_J = u_530_F.v_4262_N(this.p_178_J);
                        double d5 = u_530_F.n_1700_B(u_530_F.u_1723_Y(180.0 - u_530_F.G_564_y(d8, d10) * 57.2957763671875 - (double)this.p_178_J), -50.0, 50.0);
                        e_2866_D vector3d1 = vector3d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k()).G_564_y();
                        e_2866_D vector3d2 = new e_2866_D(u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180)), this.I_4348_c().R_4764_Y, -u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180))).G_564_y();
                        float f8 = Math.max(((float)vector3d2.J_1907_R(vector3d1) + 0.5f) / 1.5f, 0.0f);
                        this.w_1457_N *= 0.8f;
                        this.w_1457_N = (float)((double)this.w_1457_N + d5 * (double)iphase.t_148_a());
                        this.p_178_J += this.w_1457_N * 0.1f;
                        float f9 = (float)(2.0 / (d3 + 1.0));
                        float f10 = 0.06f;
                        this.n_1700_B(0.06f * (f8 * f9 + (1.0f - f9)), new e_2866_D(0.0, 0.0, -1.0));
                        if (this.t_1786_h) {
                            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c().n_1700_B((double)0.8f));
                        } else {
                            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
                        }
                        e_2866_D vector3d3 = this.I_4348_c().G_564_y();
                        double d6 = 0.8 + 0.15 * (vector3d3.J_1907_R(vector3d2) + 1.0) / 2.0;
                        this.v_4262_N(this.I_4348_c().G_564_y(d6, 0.91f, d6));
                    }
                }
                this.C_1162_e = this.p_178_J;
                e_2866_D[] avector3d = new e_2866_D[this.C_2741_M.length];
                for (int j = 0; j < this.C_2741_M.length; ++j) {
                    avector3d[j] = new e_2866_D(this.C_2741_M[j].O_3598_v(), this.C_2741_M[j].X_2960_b(), this.C_2741_M[j].l_2647_k());
                }
                float f15 = (float)(this.n_1700_B(5, 1.0f)[1] - this.n_1700_B(10, 1.0f)[1]) * 10.0f * ((float)Math.PI / 180);
                float f16 = u_530_F.J_1907_R(f15);
                float f2 = u_530_F.n_1700_B(f15);
                float f17 = this.p_178_J * ((float)Math.PI / 180);
                float f3 = u_530_F.n_1700_B(f17);
                float f18 = u_530_F.J_1907_R(f17);
                this.n_1700_B(this.q_2307_F, (double)(f3 * 0.5f), 0.0, (double)(-f18 * 0.5f));
                this.n_1700_B(this.A_4115_X, (double)(f18 * 4.5f), 2.0, (double)(f3 * 4.5f));
                this.n_1700_B(this.Y_1740_V, (double)(f18 * -4.5f), 2.0, (double)(f3 * -4.5f));
                if (!this.O_508_d.Y_259_p && this.RealmsLongRunningMcoTaskScreen == 0) {
                    this.n_1700_B(this.O_508_d.J_1907_R((N_4263_v)this, this.A_4115_X.i_601_W().grow(4.0, 2.0, 4.0).offset(0.0, -2.0, 0.0), I_408_V.P_1922_E));
                    this.n_1700_B(this.O_508_d.J_1907_R((N_4263_v)this, this.Y_1740_V.i_601_W().grow(4.0, 2.0, 4.0).offset(0.0, -2.0, 0.0), I_408_V.P_1922_E));
                    this.J_1907_R(this.O_508_d.J_1907_R((N_4263_v)this, this.h_1847_R.i_601_W().grow(1.0), I_408_V.P_1922_E));
                    this.J_1907_R(this.O_508_d.J_1907_R((N_4263_v)this, this.k_2293_S.i_601_W().grow(1.0), I_408_V.P_1922_E));
                }
                float f4 = u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180) - this.w_1457_N * 0.01f);
                float f19 = u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180) - this.w_1457_N * 0.01f);
                float f5 = this.V_1176_p();
                this.n_1700_B(this.h_1847_R, (double)(f4 * 6.5f * f16), (double)(f5 + f2 * 6.5f), (double)(-f19 * 6.5f * f16));
                this.n_1700_B(this.k_2293_S, (double)(f4 * 5.5f * f16), (double)(f5 + f2 * 5.5f), (double)(-f19 * 5.5f * f16));
                double[] adouble = this.n_1700_B(5, 1.0f);
                for (int k = 0; k < 3; ++k) {
                    EnderDragonPart enderdragonpartentity = null;
                    if (k == 0) {
                        enderdragonpartentity = this.Z_875_P;
                    }
                    if (k == 1) {
                        enderdragonpartentity = this.c_3005_b;
                    }
                    if (k == 2) {
                        enderdragonpartentity = this.H_2857_Y;
                    }
                    double[] adouble1 = this.n_1700_B(12 + k * 2, 1.0f);
                    float f7 = this.p_178_J * ((float)Math.PI / 180) + this.t_148_a(adouble1[0] - adouble[0]) * ((float)Math.PI / 180);
                    float f20 = u_530_F.n_1700_B(f7);
                    float f21 = u_530_F.J_1907_R(f7);
                    float f22 = 1.5f;
                    float f23 = (float)(k + 1) * 2.0f;
                    this.n_1700_B(enderdragonpartentity, (double)(-(f3 * 1.5f + f20 * f23) * f16), adouble1[1] - adouble[1] - (double)((f23 + 1.5f) * f2) + 1.5, (double)((f18 * 1.5f + f21 * f23) * f16));
                }
                if (!this.O_508_d.Y_259_p) {
                    this.t_1786_h = this.J_1907_R(this.h_1847_R.i_601_W()) | this.J_1907_R(this.k_2293_S.i_601_W()) | this.J_1907_R(this.q_2307_F.i_601_W());
                    if (this.t_4043_B != null) {
                        this.t_4043_B.J_1907_R(this);
                    }
                }
                for (int l = 0; l < this.C_2741_M.length; ++l) {
                    this.C_2741_M[l].r_715_M = avector3d[l].J_1907_R;
                    this.C_2741_M[l].A_1038_p = avector3d[l].R_4764_Y;
                    this.C_2741_M[l].i_1637_u = avector3d[l].G_564_y;
                    this.C_2741_M[l].q_1982_R = avector3d[l].J_1907_R;
                    this.C_2741_M[l].dtoRealmsServerAddress = avector3d[l].R_4764_Y;
                    this.C_2741_M[l].w_612_n = avector3d[l].G_564_y;
                }
            }
        }
    }

    private void n_1700_B(EnderDragonPart part, double offsetX, double offsetY, double offsetZ) {
        part.J_1907_R(this.O_3598_v() + offsetX, this.X_2960_b() + offsetY, this.l_2647_k() + offsetZ);
    }

    private float V_1176_p() {
        if (this.x_607_J.n_1700_B().u_()) {
            return -1.0f;
        }
        double[] adouble = this.n_1700_B(5, 1.0f);
        double[] adouble1 = this.n_1700_B(0, 1.0f);
        return (float)(adouble[1] - adouble1[1]);
    }

    private void y_2447_C() {
        if (this.Y_601_j != null) {
            if (this.Y_601_j.t_4219_U) {
                this.Y_601_j = null;
            } else if (this.RealmsWorldResetDto % 10 == 0 && this.g_46_E() < this.L_1733_J()) {
                this.t_1786_h(this.g_46_E() + 1.0f);
            }
        }
        if (this.RealmsWorldOptions.nextInt(10) == 0) {
            List<V_3354_l> list = this.O_508_d.n_1700_B(V_3354_l.class, this.i_601_W().grow(32.0));
            V_3354_l endercrystalentity = null;
            double d0 = Double.MAX_VALUE;
            for (V_3354_l endercrystalentity1 : list) {
                double d1 = endercrystalentity1.G_564_y(this);
                if (!(d1 < d0)) continue;
                d0 = d1;
                endercrystalentity = endercrystalentity1;
            }
            this.Y_601_j = endercrystalentity;
        }
    }

    private void n_1700_B(List<N_4263_v> entities) {
        double d0 = (this.q_2307_F.i_601_W().minX + this.q_2307_F.i_601_W().maxX) / 2.0;
        double d1 = (this.q_2307_F.i_601_W().minZ + this.q_2307_F.i_601_W().maxZ) / 2.0;
        for (N_4263_v entity : entities) {
            if (!(entity instanceof r_4811_B)) continue;
            double d2 = entity.O_3598_v() - d0;
            double d3 = entity.l_2647_k() - d1;
            double d4 = Math.max(d2 * d2 + d3 * d3, 0.1);
            entity.w_1484_f(d2 / d4 * 4.0, 0.2f, d3 / d4 * 4.0);
            if (this.x_607_J.n_1700_B().u_() || ((r_4811_B)entity).r_260_T() >= entity.RealmsWorldResetDto - 2) continue;
            entity.n_1700_B(P_11_z.R_4764_Y(this), 5.0f);
            this.n_1700_B((r_4811_B)this, entity);
        }
    }

    private void J_1907_R(List<N_4263_v> entities) {
        for (N_4263_v entity : entities) {
            if (!(entity instanceof r_4811_B)) continue;
            entity.n_1700_B(P_11_z.R_4764_Y(this), 10.0f);
            this.n_1700_B((r_4811_B)this, entity);
        }
    }

    private float t_148_a(double angle) {
        return (float)u_530_F.u_1723_Y(angle);
    }

    private boolean J_1907_R(I_4817_s area) {
        int i = u_530_F.R_4764_Y(area.minX);
        int j = u_530_F.R_4764_Y(area.minY);
        int k = u_530_F.R_4764_Y(area.minZ);
        int l = u_530_F.R_4764_Y(area.maxX);
        int i1 = u_530_F.R_4764_Y(area.maxY);
        int j1 = u_530_F.R_4764_Y(area.maxZ);
        boolean flag = false;
        boolean flag1 = false;
        for (int k1 = i; k1 <= l; ++k1) {
            for (int l1 = j; l1 <= i1; ++l1) {
                for (int i2 = k; i2 <= j1; ++i2) {
                    c_1514_x blockpos = new c_1514_x(k1, l1, i2);
                    K_4074_S blockstate = this.O_508_d.getBlockState(blockpos);
                    T_2915_h block = blockstate.J_1907_R();
                    if (blockstate.v_4262_N() || blockstate.R_4764_Y() == Material.h_1847_R) continue;
                    if (this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R) && !BlockTags.r_715_M.n_1700_B(block)) {
                        flag1 = this.O_508_d.n_1700_B(blockpos, false) || flag1;
                        continue;
                    }
                    flag = true;
                }
            }
        }
        if (flag1) {
            c_1514_x blockpos1 = new c_1514_x(i + this.RealmsWorldOptions.nextInt(l - i + 1), j + this.RealmsWorldOptions.nextInt(i1 - j + 1), k + this.RealmsWorldOptions.nextInt(j1 - k + 1));
            this.O_508_d.R_4764_Y(2008, blockpos1, 0);
        }
        return flag;
    }

    public boolean n_1700_B(EnderDragonPart part, P_11_z source, float damage) {
        if (this.x_607_J.n_1700_B().G_564_y() == Z_1164_j.s_956_w) {
            return false;
        }
        damage = this.x_607_J.n_1700_B().n_1700_B(source, damage);
        if (part != this.h_1847_R) {
            damage = damage / 4.0f + Math.min(damage, 1.0f);
        }
        if (damage < 0.01f) {
            return false;
        }
        if (source.u_2550_I() instanceof a_3913_L || source.G_564_y()) {
            float f = this.g_46_E();
            this.u_1723_Y(source, damage);
            if (this.Z_2812_M() && !this.x_607_J.n_1700_B().u_()) {
                this.t_1786_h(1.0f);
                this.x_607_J.n_1700_B(Z_1164_j.s_956_w);
            }
            if (this.x_607_J.n_1700_B().u_()) {
                this.n_3318_d = (int)((float)this.n_3318_d + (f - this.g_46_E()));
                if ((float)this.n_3318_d > 0.25f * this.L_1733_J()) {
                    this.n_3318_d = 0;
                    this.x_607_J.n_1700_B(Z_1164_j.P_1922_E);
                }
            }
        }
        return true;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (source instanceof f_2785_f && ((f_2785_f)source).q_2307_F()) {
            this.n_1700_B(this.q_2307_F, source, amount);
        }
        return false;
    }

    protected boolean u_1723_Y(P_11_z source, float amount) {
        return super.n_1700_B(source, amount);
    }

    @Override
    public void e_1992_r() {
        this.Ops();
        if (this.t_4043_B != null) {
            this.t_4043_B.J_1907_R(this);
            this.t_4043_B.n_1700_B(this);
        }
    }

    @Override
    protected void T_2971_J() {
        if (this.t_4043_B != null) {
            this.t_4043_B.J_1907_R(this);
        }
        ++this.multiplayerClientSuggestionProvider;
        if (this.multiplayerClientSuggestionProvider >= 180 && this.multiplayerClientSuggestionProvider <= 200) {
            float f = (this.RealmsWorldOptions.nextFloat() - 0.5f) * 8.0f;
            float f1 = (this.RealmsWorldOptions.nextFloat() - 0.5f) * 4.0f;
            float f2 = (this.RealmsWorldOptions.nextFloat() - 0.5f) * 8.0f;
            this.O_508_d.n_1700_B(ParticleTypes.Q_2552_b, this.O_3598_v() + (double)f, this.X_2960_b() + 2.0 + (double)f1, this.l_2647_k() + (double)f2, 0.0, 0.0, 0.0);
        }
        boolean flag = this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.P_1922_E);
        int i = 500;
        if (this.t_4043_B != null && !this.t_4043_B.G_564_y()) {
            i = 12000;
        }
        if (!this.O_508_d.Y_259_p) {
            if (this.multiplayerClientSuggestionProvider > 150 && this.multiplayerClientSuggestionProvider % 5 == 0 && flag) {
                this.n_1700_B(u_530_F.G_564_y((float)i * 0.08f));
            }
            if (this.multiplayerClientSuggestionProvider == 1 && !this.y_1700_S()) {
                this.O_508_d.J_1907_R(1028, this.b_2312_j(), 0);
            }
        }
        this.n_1700_B(L_461_d.n_1700_B, new e_2866_D(0.0, 0.1f, 0.0));
        this.p_178_J += 20.0f;
        this.C_1162_e = this.p_178_J;
        if (this.multiplayerClientSuggestionProvider == 200 && !this.O_508_d.Y_259_p) {
            if (flag) {
                this.n_1700_B(u_530_F.G_564_y((float)i * 0.2f));
            }
            if (this.t_4043_B != null) {
                this.t_4043_B.n_1700_B(this);
            }
            this.Ops();
        }
    }

    private void n_1700_B(int xp) {
        while (xp > 0) {
            int i = n_4637_L.n_1700_B(xp);
            xp -= i;
            this.O_508_d.a_(new n_4637_L(this.O_508_d, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), i));
        }
    }

    public int w_1484_f() {
        if (this.d_2427_y[0] == null) {
            for (int i = 0; i < 24; ++i) {
                int i1;
                int l;
                int j = 5;
                if (i < 12) {
                    l = u_530_F.G_564_y(60.0f * u_530_F.J_1907_R(2.0f * ((float)(-Math.PI) + 0.2617994f * (float)i)));
                    i1 = u_530_F.G_564_y(60.0f * u_530_F.n_1700_B(2.0f * ((float)(-Math.PI) + 0.2617994f * (float)i)));
                } else if (i < 20) {
                    int lvt_3_1_ = i - 12;
                    l = u_530_F.G_564_y(40.0f * u_530_F.J_1907_R(2.0f * ((float)(-Math.PI) + 0.3926991f * (float)lvt_3_1_)));
                    i1 = u_530_F.G_564_y(40.0f * u_530_F.n_1700_B(2.0f * ((float)(-Math.PI) + 0.3926991f * (float)lvt_3_1_)));
                    j += 10;
                } else {
                    int k1 = i - 20;
                    l = u_530_F.G_564_y(20.0f * u_530_F.J_1907_R(2.0f * ((float)(-Math.PI) + 0.7853982f * (float)k1)));
                    i1 = u_530_F.G_564_y(20.0f * u_530_F.n_1700_B(2.0f * ((float)(-Math.PI) + 0.7853982f * (float)k1)));
                }
                int j1 = Math.max(this.O_508_d.d_2461_k() + 10, this.O_508_d.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, new c_1514_x(l, 0, i1)).getY() + j);
                this.d_2427_y[i] = new D_1436_R(l, j1, i1);
            }
            this.z_1737_N[0] = 6146;
            this.z_1737_N[1] = 8197;
            this.z_1737_N[2] = 8202;
            this.z_1737_N[3] = 16404;
            this.z_1737_N[4] = 32808;
            this.z_1737_N[5] = 32848;
            this.z_1737_N[6] = 65696;
            this.z_1737_N[7] = 131392;
            this.z_1737_N[8] = 131712;
            this.z_1737_N[9] = 263424;
            this.z_1737_N[10] = 526848;
            this.z_1737_N[11] = 525313;
            this.z_1737_N[12] = 1581057;
            this.z_1737_N[13] = 3166214;
            this.z_1737_N[14] = 2138120;
            this.z_1737_N[15] = 6373424;
            this.z_1737_N[16] = 4358208;
            this.z_1737_N[17] = 12910976;
            this.z_1737_N[18] = 9044480;
            this.z_1737_N[19] = 9706496;
            this.z_1737_N[20] = 15216640;
            this.z_1737_N[21] = 0xD0E000;
            this.z_1737_N[22] = 11763712;
            this.z_1737_N[23] = 0x7E0000;
        }
        return this.M_182_A(this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
    }

    public int M_182_A(double x, double y, double z) {
        float f = 10000.0f;
        int i = 0;
        D_1436_R pathpoint = new D_1436_R(u_530_F.R_4764_Y(x), u_530_F.R_4764_Y(y), u_530_F.R_4764_Y(z));
        int j = 0;
        if (this.t_4043_B == null || this.t_4043_B.R_4764_Y() == 0) {
            j = 12;
        }
        for (int k = j; k < 24; ++k) {
            float f1;
            if (this.d_2427_y[k] == null || !((f1 = this.d_2427_y[k].J_1907_R(pathpoint)) < f)) continue;
            f = f1;
            i = k;
        }
        return i;
    }

    @Nullable
    public b_1722_e n_1700_B(int startIdx, int finishIdx, @Nullable D_1436_R andThen) {
        for (int i = 0; i < 24; ++i) {
            D_1436_R pathpoint = this.d_2427_y[i];
            pathpoint.t_148_a = false;
            pathpoint.v_4262_N = 0.0f;
            pathpoint.P_1922_E = 0.0f;
            pathpoint.u_1723_Y = 0.0f;
            pathpoint.w_1484_f = null;
            pathpoint.G_564_y = -1;
        }
        D_1436_R pathpoint4 = this.d_2427_y[startIdx];
        D_1436_R pathpoint5 = this.d_2427_y[finishIdx];
        pathpoint4.P_1922_E = 0.0f;
        pathpoint4.v_4262_N = pathpoint4.u_1723_Y = pathpoint4.n_1700_B(pathpoint5);
        this.v_4276_D.n_1700_B();
        this.v_4276_D.n_1700_B(pathpoint4);
        D_1436_R pathpoint1 = pathpoint4;
        int j = 0;
        if (this.t_4043_B == null || this.t_4043_B.R_4764_Y() == 0) {
            j = 12;
        }
        while (!this.v_4276_D.R_4764_Y()) {
            D_1436_R pathpoint2 = this.v_4276_D.J_1907_R();
            if (pathpoint2.equals(pathpoint5)) {
                if (andThen != null) {
                    andThen.w_1484_f = pathpoint5;
                    pathpoint5 = andThen;
                }
                return this.n_1700_B(pathpoint4, pathpoint5);
            }
            if (pathpoint2.n_1700_B(pathpoint5) < pathpoint1.n_1700_B(pathpoint5)) {
                pathpoint1 = pathpoint2;
            }
            pathpoint2.t_148_a = true;
            int k = 0;
            for (int l = 0; l < 24; ++l) {
                if (this.d_2427_y[l] != pathpoint2) continue;
                k = l;
                break;
            }
            for (int i1 = j; i1 < 24; ++i1) {
                if ((this.z_1737_N[k] & 1 << i1) <= 0) continue;
                D_1436_R pathpoint3 = this.d_2427_y[i1];
                if (pathpoint3.t_148_a) continue;
                float f = pathpoint2.P_1922_E + pathpoint2.n_1700_B(pathpoint3);
                if (pathpoint3.G_564_y() && !(f < pathpoint3.P_1922_E)) continue;
                pathpoint3.w_1484_f = pathpoint2;
                pathpoint3.P_1922_E = f;
                pathpoint3.u_1723_Y = pathpoint3.n_1700_B(pathpoint5);
                if (pathpoint3.G_564_y()) {
                    this.v_4276_D.n_1700_B(pathpoint3, pathpoint3.P_1922_E + pathpoint3.u_1723_Y);
                    continue;
                }
                pathpoint3.v_4262_N = pathpoint3.P_1922_E + pathpoint3.u_1723_Y;
                this.v_4276_D.n_1700_B(pathpoint3);
            }
        }
        if (pathpoint1 == pathpoint4) {
            return null;
        }
        Y_259_p.debug("Failed to find path from {} to {}", (Object)startIdx, (Object)finishIdx);
        if (andThen != null) {
            andThen.w_1484_f = pathpoint1;
            pathpoint1 = andThen;
        }
        return this.n_1700_B(pathpoint4, pathpoint1);
    }

    private b_1722_e n_1700_B(D_1436_R start, D_1436_R finish) {
        ArrayList list = Lists.newArrayList();
        D_1436_R pathpoint = finish;
        list.add(0, finish);
        while (pathpoint.w_1484_f != null) {
            pathpoint = pathpoint.w_1484_f;
            list.add(0, pathpoint);
        }
        return new b_1722_e(list, new c_1514_x(finish.n_1700_B, finish.J_1907_R, finish.R_4764_Y), true);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("DragonPhase", this.x_607_J.n_1700_B().G_564_y().J_1907_R());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.P_1922_E("DragonPhase")) {
            this.x_607_J.n_1700_B(Z_1164_j.n_1700_B(compound.w_1484_f("DragonPhase")));
        }
    }

    @Override
    public void a_178_J() {
    }

    public EnderDragonPart[] Q_4569_t() {
        return this.C_2741_M;
    }

    @Override
    public boolean C_290_v() {
        return false;
    }

    @Override
    public D_38_f r_2478_U() {
        return D_38_f.u_1723_Y;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.r_260_T;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.f_691_R;
    }

    @Override
    protected float d_4500_Q() {
        return 5.0f;
    }

    public float n_1700_B(int p_184667_1_, double[] spineEndOffsets, double[] headPartOffsets) {
        double d0;
        DragonPhaseInstance iphase = this.x_607_J.n_1700_B();
        Z_1164_j<? extends DragonPhaseInstance> phasetype = iphase.G_564_y();
        if (phasetype != Z_1164_j.G_564_y && phasetype != Z_1164_j.P_1922_E) {
            d0 = iphase.u_() ? (double)p_184667_1_ : (p_184667_1_ == 6 ? 0.0 : headPartOffsets[1] - spineEndOffsets[1]);
        } else {
            c_1514_x blockpos = this.O_508_d.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, EndPodiumFeature.n_1700_B);
            float f = Math.max(u_530_F.n_1700_B(blockpos.distanceSq(this.s_4990_V(), true)) / 4.0f, 1.0f);
            d0 = (float)p_184667_1_ / f;
        }
        return (float)d0;
    }

    public e_2866_D G_564_y(float partialTicks) {
        e_2866_D vector3d;
        DragonPhaseInstance iphase = this.x_607_J.n_1700_B();
        Z_1164_j<? extends DragonPhaseInstance> phasetype = iphase.G_564_y();
        if (phasetype != Z_1164_j.G_564_y && phasetype != Z_1164_j.P_1922_E) {
            if (iphase.u_()) {
                float f4 = this.f_4016_n;
                float f5 = 1.5f;
                this.f_4016_n = -45.0f;
                vector3d = this.t_148_a(partialTicks);
                this.f_4016_n = f4;
            } else {
                vector3d = this.t_148_a(partialTicks);
            }
        } else {
            c_1514_x blockpos = this.O_508_d.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, EndPodiumFeature.n_1700_B);
            float f = Math.max(u_530_F.n_1700_B(blockpos.distanceSq(this.s_4990_V(), true)) / 4.0f, 1.0f);
            float f1 = 6.0f / f;
            float f2 = this.f_4016_n;
            float f3 = 1.5f;
            this.f_4016_n = -f1 * 1.5f * 5.0f;
            vector3d = this.t_148_a(partialTicks);
            this.f_4016_n = f2;
        }
        return vector3d;
    }

    public void n_1700_B(V_3354_l crystal, c_1514_x pos, P_11_z dmgSrc) {
        a_3913_L playerentity = dmgSrc.u_2550_I() instanceof a_3913_L ? (a_3913_L)dmgSrc.u_2550_I() : this.O_508_d.n_1700_B(Q_2552_b, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        if (crystal == this.Y_601_j) {
            this.n_1700_B(this.h_1847_R, P_11_z.G_564_y(playerentity), 10.0f);
        }
        this.x_607_J.n_1700_B().n_1700_B(crystal, pos, dmgSrc, playerentity);
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (n_1700_B.equals(key) && this.O_508_d.Y_259_p) {
            this.x_607_J.n_1700_B(Z_1164_j.n_1700_B(this.D_60_a().n_1700_B(n_1700_B)));
        }
        super.n_1700_B(key);
    }

    public V_600_c y_4642_Y() {
        return this.x_607_J;
    }

    @Nullable
    public C_990_G h_1640_b() {
        return this.t_4043_B;
    }

    @Override
    public boolean n_1700_B(k_2610_C effectInstanceIn) {
        return false;
    }

    @Override
    protected boolean u_2550_I(N_4263_v entityIn) {
        return false;
    }

    @Override
    public boolean L_103_L() {
        return false;
    }
}


