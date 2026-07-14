/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.WaterAnimal;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_530_F;

public class Squid
extends WaterAnimal {
    public float n_1700_B;
    public float J_1907_R;
    public float R_4764_Y;
    public float h_1847_R;
    public float Q_4569_t;
    public float M_182_A;
    public float t_1786_h;
    public float multiplayerClientSuggestionProvider;
    private float w_1457_N;
    private float Y_601_j;
    private float Y_259_p;
    private float Q_2552_b;
    private float C_2741_M;
    private float k_2293_S;

    public Squid(t_5_h<? extends Squid> type, b_4507_u worldIn) {
        super((t_5_h<? extends WaterAnimal>)type, worldIn);
        this.RealmsWorldOptions.setSeed(this.j_276_v());
        this.Y_601_j = 1.0f / (this.RealmsWorldOptions.nextFloat() + 1.0f) * 0.2f;
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new J_1907_R(this, this));
        this.s_956_w.n_1700_B(1, new n_1700_B());
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 10.0);
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R * 0.5f;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.CocoaBlock;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.ComparatorBlock;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.CommandBlock;
    }

    @Override
    protected float d_4500_Q() {
        return 0.4f;
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        this.J_1907_R = this.n_1700_B;
        this.h_1847_R = this.R_4764_Y;
        this.M_182_A = this.Q_4569_t;
        this.multiplayerClientSuggestionProvider = this.t_1786_h;
        this.Q_4569_t += this.Y_601_j;
        if ((double)this.Q_4569_t > Math.PI * 2) {
            if (this.O_508_d.Y_259_p) {
                this.Q_4569_t = (float)Math.PI * 2;
            } else {
                this.Q_4569_t = (float)((double)this.Q_4569_t - Math.PI * 2);
                if (this.RealmsWorldOptions.nextInt(10) == 0) {
                    this.Y_601_j = 1.0f / (this.RealmsWorldOptions.nextFloat() + 1.0f) * 0.2f;
                }
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)19);
            }
        }
        if (this.S_980_j()) {
            if (this.Q_4569_t < (float)Math.PI) {
                float f = this.Q_4569_t / (float)Math.PI;
                this.t_1786_h = u_530_F.n_1700_B(f * f * (float)Math.PI) * (float)Math.PI * 0.25f;
                if ((double)f > 0.75) {
                    this.w_1457_N = 1.0f;
                    this.Y_259_p = 1.0f;
                } else {
                    this.Y_259_p *= 0.8f;
                }
            } else {
                this.t_1786_h = 0.0f;
                this.w_1457_N *= 0.9f;
                this.Y_259_p *= 0.99f;
            }
            if (!this.O_508_d.Y_259_p) {
                this.h_1847_R(this.Q_2552_b * this.w_1457_N, this.C_2741_M * this.w_1457_N, this.k_2293_S * this.w_1457_N);
            }
            e_2866_D vector3d = this.I_4348_c();
            float f1 = u_530_F.n_1700_B(Squid.R_4764_Y(vector3d));
            this.C_1162_e += (-((float)u_530_F.G_564_y(vector3d.J_1907_R, vector3d.G_564_y)) * 57.295776f - this.C_1162_e) * 0.1f;
            this.p_178_J = this.C_1162_e;
            this.R_4764_Y = (float)((double)this.R_4764_Y + Math.PI * (double)this.Y_259_p * 1.5);
            this.n_1700_B += (-((float)u_530_F.G_564_y((double)f1, vector3d.R_4764_Y)) * 57.295776f - this.n_1700_B) * 0.1f;
        } else {
            this.t_1786_h = u_530_F.P_1922_E(u_530_F.n_1700_B(this.Q_4569_t)) * (float)Math.PI * 0.25f;
            if (!this.O_508_d.Y_259_p) {
                double d0 = this.I_4348_c().R_4764_Y;
                if (this.J_1907_R(MobEffects.q_2307_F)) {
                    d0 = 0.05 * (double)(this.R_4764_Y(MobEffects.q_2307_F).R_4764_Y() + 1);
                } else if (!this.u_744_e()) {
                    d0 -= 0.08;
                }
                this.h_1847_R(0.0, d0 * (double)0.98f, 0.0);
            }
            this.n_1700_B = (float)((double)this.n_1700_B + (double)(-90.0f - this.n_1700_B) * 0.02);
        }
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (super.n_1700_B(source, amount) && this.q_817_e() != null) {
            this.h_1640_b();
            return true;
        }
        return false;
    }

    private e_2866_D s_956_w(e_2866_D p_207400_1_) {
        e_2866_D vector3d = p_207400_1_.n_1700_B(this.J_1907_R * ((float)Math.PI / 180));
        return vector3d.J_1907_R(-this.D_4361_a * ((float)Math.PI / 180));
    }

    private void h_1640_b() {
        this.n_1700_B(SoundEvents.a_648_i, this.d_4500_Q(), this.O_2761_o());
        e_2866_D vector3d = this.s_956_w(new e_2866_D(0.0, -1.0, 0.0)).J_1907_R(this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
        for (int i = 0; i < 30; ++i) {
            e_2866_D vector3d1 = this.s_956_w(new e_2866_D((double)this.RealmsWorldOptions.nextFloat() * 0.6 - 0.3, -1.0, (double)this.RealmsWorldOptions.nextFloat() * 0.6 - 0.3));
            e_2866_D vector3d2 = vector3d1.n_1700_B(0.3 + (double)(this.RealmsWorldOptions.nextFloat() * 2.0f));
            ((e_3591_l)this.O_508_d).n_1700_B(ParticleTypes.Z_976_R, vector3d.J_1907_R, vector3d.R_4764_Y + 0.5, vector3d.G_564_y, 0, vector3d2.J_1907_R, vector3d2.R_4764_Y, vector3d2.G_564_y, 0.1f);
        }
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
    }

    public static boolean J_1907_R(t_5_h<Squid> p_223365_0_, LevelAccessor p_223365_1_, a_3160_D reason, c_1514_x p_223365_3_, Random p_223365_4_) {
        return p_223365_3_.getY() > 45 && p_223365_3_.getY() < p_223365_1_.d_2461_k();
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 19) {
            this.Q_4569_t = 0.0f;
        } else {
            super.n_1700_B(id);
        }
    }

    public void n_1700_B(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn) {
        this.Q_2552_b = randomMotionVecXIn;
        this.C_2741_M = randomMotionVecYIn;
        this.k_2293_S = randomMotionVecZIn;
    }

    public boolean y_4642_Y() {
        return this.Q_2552_b != 0.0f || this.C_2741_M != 0.0f || this.k_2293_S != 0.0f;
    }

    class J_1907_R
    extends Goal {
        private final Squid n_1700_B;

        public J_1907_R(Squid this$0, Squid p_i48823_2_) {
            this.n_1700_B = p_i48823_2_;
        }

        @Override
        public boolean n_1700_B() {
            return true;
        }

        @Override
        public void P_1922_E() {
            int i = this.n_1700_B.g_4560_H();
            if (i > 100) {
                this.n_1700_B.n_1700_B(0.0f, 0.0f, 0.0f);
            } else if (this.n_1700_B.M_3508_C().nextInt(50) == 0 || !this.n_1700_B.RegionPingResult || !this.n_1700_B.y_4642_Y()) {
                float f = this.n_1700_B.M_3508_C().nextFloat() * ((float)Math.PI * 2);
                float f1 = u_530_F.J_1907_R(f) * 0.2f;
                float f2 = -0.1f + this.n_1700_B.M_3508_C().nextFloat() * 0.2f;
                float f3 = u_530_F.n_1700_B(f) * 0.2f;
                this.n_1700_B.n_1700_B(f1, f2, f3);
            }
        }
    }

    class n_1700_B
    extends Goal {
        private int J_1907_R;

        private n_1700_B() {
        }

        @Override
        public boolean n_1700_B() {
            r_4811_B livingentity = Squid.this.q_817_e();
            if (Squid.this.RowButton() && livingentity != null) {
                return Squid.this.G_564_y((N_4263_v)livingentity) < 100.0;
            }
            return false;
        }

        @Override
        public void R_4764_Y() {
            this.J_1907_R = 0;
        }

        @Override
        public void P_1922_E() {
            ++this.J_1907_R;
            r_4811_B livingentity = Squid.this.q_817_e();
            if (livingentity != null) {
                e_2866_D vector3d = new e_2866_D(Squid.this.O_3598_v() - livingentity.O_3598_v(), Squid.this.X_2960_b() - livingentity.X_2960_b(), Squid.this.l_2647_k() - livingentity.l_2647_k());
                K_4074_S blockstate = Squid.this.O_508_d.getBlockState(new c_1514_x(Squid.this.O_3598_v() + vector3d.J_1907_R, Squid.this.X_2960_b() + vector3d.R_4764_Y, Squid.this.l_2647_k() + vector3d.G_564_y));
                FluidState fluidstate = Squid.this.O_508_d.getFluidState(new c_1514_x(Squid.this.O_3598_v() + vector3d.J_1907_R, Squid.this.X_2960_b() + vector3d.R_4764_Y, Squid.this.l_2647_k() + vector3d.G_564_y));
                if (fluidstate.n_1700_B(FluidTags.J_1907_R) || blockstate.v_4262_N()) {
                    double d0 = vector3d.u_1723_Y();
                    if (d0 > 0.0) {
                        vector3d.G_564_y();
                        float f = 3.0f;
                        if (d0 > 5.0) {
                            f = (float)((double)f - (d0 - 5.0) / 5.0);
                        }
                        if (f > 0.0f) {
                            vector3d = vector3d.n_1700_B((double)f);
                        }
                    }
                    if (blockstate.v_4262_N()) {
                        vector3d = vector3d.n_1700_B(0.0, vector3d.R_4764_Y, 0.0);
                    }
                    Squid.this.n_1700_B((float)vector3d.J_1907_R / 20.0f, (float)vector3d.R_4764_Y / 20.0f, (float)vector3d.G_564_y / 20.0f);
                }
                if (this.J_1907_R % 10 == 5) {
                    Squid.this.O_508_d.n_1700_B(ParticleTypes.P_1922_E, Squid.this.O_3598_v(), Squid.this.X_2960_b(), Squid.this.l_2647_k(), 0.0, 0.0, 0.0);
                }
            }
        }
    }
}


