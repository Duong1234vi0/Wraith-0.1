/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.FluidTags;
import lightning.product.LootContextParams;
import lightning.product.B_4088_l;
import lightning.product.Projectile;
import lightning.product.C_4114_x;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.H_2333_J;
import lightning.product.HitResult;
import lightning.product.I_685_r;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.ItemTags;
import lightning.product.N_4263_v;
import lightning.product.Stats;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.f_1402_I;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.n_1494_c;
import lightning.product.n_4637_L;
import lightning.product.o_4810_o;
import lightning.product.p_4985_U;
import lightning.product.q_1704_m;
import lightning.product.Items;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.EntityHitResult;

public class W_1247_f
extends Projectile {
    private final Random n_1700_B = new Random();
    private boolean J_1907_R;
    private int R_4764_Y;
    private static final h_256_u<Integer> G_564_y = C_4114_x.n_1700_B(W_1247_f.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Boolean> P_1922_E = C_4114_x.n_1700_B(W_1247_f.class, EntityDataSerializers.t_148_a);
    private int u_1723_Y;
    private int v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private float s_956_w;
    private boolean u_2550_I = true;
    private N_4263_v M_588_G;
    private n_1700_B P_4830_p = lightning.product.W_1247_f$n_1700_B.n_1700_B;
    private final int h_1847_R;
    private final int Q_4569_t;

    private W_1247_f(b_4507_u p_i50219_1_, a_3913_L p_i50219_2_, int p_i50219_3_, int p_i50219_4_) {
        super((t_5_h<? extends Projectile>)t_5_h.RealmsClientOutdatedScreen, p_i50219_1_);
        this.RowButton = true;
        this.J_1907_R(p_i50219_2_);
        p_i50219_2_.X_2960_b = this;
        this.h_1847_R = Math.max(0, p_i50219_3_);
        this.Q_4569_t = Math.max(0, p_i50219_4_);
    }

    public W_1247_f(b_4507_u worldIn, a_3913_L p_i47290_2_, double x, double y, double z) {
        this(worldIn, p_i47290_2_, 0, 0);
        this.J_1907_R(x, y, z);
        this.r_715_M = this.O_3598_v();
        this.A_1038_p = this.X_2960_b();
        this.i_1637_u = this.l_2647_k();
    }

    public W_1247_f(a_3913_L p_i50220_1_, b_4507_u p_i50220_2_, int p_i50220_3_, int p_i50220_4_) {
        this(p_i50220_2_, p_i50220_1_, p_i50220_3_, p_i50220_4_);
        float f = p_i50220_1_.f_4016_n;
        float f1 = p_i50220_1_.p_178_J;
        float f2 = u_530_F.J_1907_R(-f1 * ((float)Math.PI / 180) - (float)Math.PI);
        float f3 = u_530_F.n_1700_B(-f1 * ((float)Math.PI / 180) - (float)Math.PI);
        float f4 = -u_530_F.J_1907_R(-f * ((float)Math.PI / 180));
        float f5 = u_530_F.n_1700_B(-f * ((float)Math.PI / 180));
        double d0 = p_i50220_1_.O_3598_v() - (double)f3 * 0.3;
        double d1 = p_i50220_1_.X_2048_Y();
        double d2 = p_i50220_1_.l_2647_k() - (double)f2 * 0.3;
        this.J_1907_R(d0, d1, d2, f1, f);
        e_2866_D vector3d = new e_2866_D(-f3, u_530_F.n_1700_B(-(f5 / f4), -5.0f, 5.0f), -f2);
        double d3 = vector3d.u_1723_Y();
        vector3d = vector3d.G_564_y(0.6 / d3 + 0.5 + this.RealmsWorldOptions.nextGaussian() * 0.0045, 0.6 / d3 + 0.5 + this.RealmsWorldOptions.nextGaussian() * 0.0045, 0.6 / d3 + 0.5 + this.RealmsWorldOptions.nextGaussian() * 0.0045);
        this.v_4262_N(vector3d);
        this.p_178_J = (float)(u_530_F.G_564_y(vector3d.J_1907_R, vector3d.G_564_y) * 57.2957763671875);
        this.f_4016_n = (float)(u_530_F.G_564_y(vector3d.R_4764_Y, (double)u_530_F.n_1700_B(W_1247_f.R_4764_Y(vector3d))) * 57.2957763671875);
        this.j_276_v = this.p_178_J;
        this.UploadStatus = this.f_4016_n;
    }

    @Override
    protected void a_() {
        this.D_60_a().n_1700_B(G_564_y, 0);
        this.D_60_a().n_1700_B(P_1922_E, false);
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (G_564_y.equals(key)) {
            int i = this.D_60_a().n_1700_B(G_564_y);
            N_4263_v n_4263_v = this.M_588_G = i > 0 ? this.O_508_d.J_1907_R(i - 1) : null;
        }
        if (P_1922_E.equals(key)) {
            this.J_1907_R = this.D_60_a().n_1700_B(P_1922_E);
            if (this.J_1907_R) {
                this.h_1847_R(this.I_4348_c().J_1907_R, -0.4f * u_530_F.n_1700_B(this.n_1700_B, 0.6f, 1.0f), this.I_4348_c().G_564_y);
            }
        }
        super.n_1700_B(key);
    }

    @Override
    public boolean n_1700_B(double distance) {
        double d0 = 64.0;
        return distance < 4096.0;
    }

    @Override
    public void n_1700_B(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
    }

    @Override
    public void v_() {
        this.n_1700_B.setSeed(this.w_2705_t().getLeastSignificantBits() ^ this.O_508_d.X_933_l());
        super.v_();
        a_3913_L playerentity = this.v_4262_N();
        if (playerentity == null) {
            this.Ops();
        } else if (this.O_508_d.Y_259_p || !this.R_4764_Y(playerentity)) {
            boolean flag;
            if (this.e_1992_r) {
                ++this.u_1723_Y;
                if (this.u_1723_Y >= 1200) {
                    this.Ops();
                    return;
                }
            } else {
                this.u_1723_Y = 0;
            }
            float f = 0.0f;
            c_1514_x blockpos = this.b_2312_j();
            FluidState fluidstate = this.O_508_d.getFluidState(blockpos);
            if (fluidstate.n_1700_B(FluidTags.J_1907_R)) {
                f = fluidstate.n_1700_B((BlockGetter)this.O_508_d, blockpos);
            }
            boolean bl = flag = f > 0.0f;
            if (this.P_4830_p == lightning.product.W_1247_f$n_1700_B.n_1700_B) {
                if (this.M_588_G != null) {
                    this.v_4262_N(e_2866_D.n_1700_B);
                    this.P_4830_p = lightning.product.W_1247_f$n_1700_B.J_1907_R;
                    return;
                }
                if (flag) {
                    this.v_4262_N(this.I_4348_c().G_564_y(0.3, 0.2, 0.3));
                    this.P_4830_p = lightning.product.W_1247_f$n_1700_B.R_4764_Y;
                    return;
                }
                this.t_148_a();
            } else {
                if (this.P_4830_p == lightning.product.W_1247_f$n_1700_B.J_1907_R) {
                    if (this.M_588_G != null) {
                        if (this.M_588_G.t_4219_U) {
                            this.M_588_G = null;
                            this.P_4830_p = lightning.product.W_1247_f$n_1700_B.n_1700_B;
                        } else {
                            this.J_1907_R(this.M_588_G.O_3598_v(), this.M_588_G.P_1922_E(0.8), this.M_588_G.l_2647_k());
                        }
                    }
                    return;
                }
                if (this.P_4830_p == lightning.product.W_1247_f$n_1700_B.R_4764_Y) {
                    e_2866_D vector3d = this.I_4348_c();
                    double d0 = this.X_2960_b() + vector3d.R_4764_Y - (double)blockpos.getY() - (double)f;
                    if (Math.abs(d0) < 0.01) {
                        d0 += Math.signum(d0) * 0.1;
                    }
                    this.h_1847_R(vector3d.J_1907_R * 0.9, vector3d.R_4764_Y - d0 * (double)this.RealmsWorldOptions.nextFloat() * 0.2, vector3d.G_564_y * 0.9);
                    if (this.v_4262_N <= 0 && this.t_148_a <= 0) {
                        this.u_2550_I = true;
                    } else {
                        boolean bl2 = this.u_2550_I = this.u_2550_I && this.R_4764_Y < 10 && this.R_4764_Y(blockpos);
                    }
                    if (flag) {
                        this.R_4764_Y = Math.max(0, this.R_4764_Y - 1);
                        if (this.J_1907_R) {
                            this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.1 * (double)this.n_1700_B.nextFloat() * (double)this.n_1700_B.nextFloat(), 0.0));
                        }
                        if (!this.O_508_d.Y_259_p) {
                            this.n_1700_B(blockpos);
                        }
                    } else {
                        this.R_4764_Y = Math.min(10, this.R_4764_Y + 1);
                    }
                }
            }
            if (!fluidstate.n_1700_B(FluidTags.J_1907_R)) {
                this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.03, 0.0));
            }
            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
            this.Y_259_p();
            if (this.P_4830_p == lightning.product.W_1247_f$n_1700_B.n_1700_B && (this.e_1992_r || this.D_60_a)) {
                this.v_4262_N(e_2866_D.n_1700_B);
            }
            double d1 = 0.92;
            this.v_4262_N(this.I_4348_c().n_1700_B(0.92));
            this.t_4219_U();
        }
    }

    private boolean R_4764_Y(a_3913_L p_234600_1_) {
        boolean flag1;
        Z_1993_T itemstack = p_234600_1_.A_2714_y();
        Z_1993_T itemstack1 = p_234600_1_.S_4035_N();
        boolean flag = itemstack.J_1907_R() == Items.w_2223_C;
        boolean bl = flag1 = itemstack1.J_1907_R() == Items.w_2223_C;
        if (!p_234600_1_.t_4219_U && p_234600_1_.RealmsLongRunningMcoTaskScreen() && (flag || flag1) && !(this.G_564_y(p_234600_1_) > 1024.0)) {
            return false;
        }
        this.Ops();
        return true;
    }

    private void t_148_a() {
        HitResult raytraceresult = H_2333_J.n_1700_B((N_4263_v)this, this::n_1700_B);
        this.n_1700_B(raytraceresult);
    }

    @Override
    protected boolean n_1700_B(N_4263_v p_230298_1_) {
        return super.n_1700_B(p_230298_1_) || p_230298_1_.RealmsLongRunningMcoTaskScreen() && p_230298_1_ instanceof n_1494_c;
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        super.n_1700_B(p_213868_1_);
        if (!this.O_508_d.Y_259_p) {
            this.M_588_G = p_213868_1_.n_1700_B();
            this.u_2550_I();
        }
    }

    @Override
    protected void n_1700_B(BlockHitResult p_230299_1_) {
        super.n_1700_B(p_230299_1_);
        this.v_4262_N(this.I_4348_c().G_564_y().n_1700_B(p_230299_1_.n_1700_B(this)));
    }

    private void u_2550_I() {
        this.D_60_a().J_1907_R(G_564_y, this.M_588_G.j_276_v() + 1);
    }

    private void n_1700_B(c_1514_x p_190621_1_) {
        e_3591_l serverworld = (e_3591_l)this.O_508_d;
        int i = 1;
        c_1514_x blockpos = p_190621_1_.up();
        if (this.RealmsWorldOptions.nextFloat() < 0.25f && this.O_508_d.Q_2552_b(blockpos)) {
            ++i;
        }
        if (this.RealmsWorldOptions.nextFloat() < 0.5f && !this.O_508_d.canSeeSky(blockpos)) {
            --i;
        }
        if (this.v_4262_N > 0) {
            --this.v_4262_N;
            if (this.v_4262_N <= 0) {
                this.w_1484_f = 0;
                this.t_148_a = 0;
                this.D_60_a().J_1907_R(P_1922_E, false);
            }
        } else if (this.t_148_a > 0) {
            this.t_148_a -= i;
            if (this.t_148_a > 0) {
                double d2;
                double d1;
                this.s_956_w = (float)((double)this.s_956_w + this.RealmsWorldOptions.nextGaussian() * 4.0);
                float f = this.s_956_w * ((float)Math.PI / 180);
                float f1 = u_530_F.n_1700_B(f);
                float f2 = u_530_F.J_1907_R(f);
                double d0 = this.O_3598_v() + (double)(f1 * (float)this.t_148_a * 0.1f);
                K_4074_S blockstate = serverworld.getBlockState(new c_1514_x(d0, (d1 = (double)((float)u_530_F.R_4764_Y(this.X_2960_b()) + 1.0f)) - 1.0, d2 = this.l_2647_k() + (double)(f2 * (float)this.t_148_a * 0.1f)));
                if (blockstate.n_1700_B(a_3742_W.c_3005_b)) {
                    if (this.RealmsWorldOptions.nextFloat() < 0.15f) {
                        serverworld.n_1700_B(ParticleTypes.P_1922_E, d0, d1 - (double)0.1f, d2, 1, (double)f1, 0.1, f2, 0.0);
                    }
                    float f3 = f1 * 0.04f;
                    float f4 = f2 * 0.04f;
                    serverworld.n_1700_B(ParticleTypes.Z_875_P, d0, d1, d2, 0, (double)f4, 0.01, -f3, 1.0);
                    serverworld.n_1700_B(ParticleTypes.Z_875_P, d0, d1, d2, 0, (double)(-f4), 0.01, f3, 1.0);
                }
            } else {
                this.n_1700_B(SoundEvents.I_3637_j, 0.25f, 1.0f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.4f);
                double d3 = this.X_2960_b() + 0.5;
                serverworld.n_1700_B(ParticleTypes.P_1922_E, this.O_3598_v(), d3, this.l_2647_k(), (int)(1.0f + this.C_415_h() * 20.0f), (double)this.C_415_h(), 0.0, this.C_415_h(), 0.2f);
                serverworld.n_1700_B(ParticleTypes.Z_875_P, this.O_3598_v(), d3, this.l_2647_k(), (int)(1.0f + this.C_415_h() * 20.0f), (double)this.C_415_h(), 0.0, this.C_415_h(), 0.2f);
                this.v_4262_N = u_530_F.n_1700_B(this.RealmsWorldOptions, 20, 40);
                this.D_60_a().J_1907_R(P_1922_E, true);
            }
        } else if (this.w_1484_f > 0) {
            this.w_1484_f -= i;
            float f5 = 0.15f;
            if (this.w_1484_f < 20) {
                f5 = (float)((double)f5 + (double)(20 - this.w_1484_f) * 0.05);
            } else if (this.w_1484_f < 40) {
                f5 = (float)((double)f5 + (double)(40 - this.w_1484_f) * 0.02);
            } else if (this.w_1484_f < 60) {
                f5 = (float)((double)f5 + (double)(60 - this.w_1484_f) * 0.01);
            }
            if (this.RealmsWorldOptions.nextFloat() < f5) {
                double d6;
                double d5;
                float f6 = u_530_F.n_1700_B(this.RealmsWorldOptions, 0.0f, 360.0f) * ((float)Math.PI / 180);
                float f7 = u_530_F.n_1700_B(this.RealmsWorldOptions, 25.0f, 60.0f);
                double d4 = this.O_3598_v() + (double)(u_530_F.n_1700_B(f6) * f7 * 0.1f);
                K_4074_S blockstate1 = serverworld.getBlockState(new c_1514_x(d4, (d5 = (double)((float)u_530_F.R_4764_Y(this.X_2960_b()) + 1.0f)) - 1.0, d6 = this.l_2647_k() + (double)(u_530_F.J_1907_R(f6) * f7 * 0.1f)));
                if (blockstate1.n_1700_B(a_3742_W.c_3005_b)) {
                    serverworld.n_1700_B(ParticleTypes.g_2268_R, d4, d5, d6, 2 + this.RealmsWorldOptions.nextInt(2), (double)0.1f, 0.0, 0.1f, 0.0);
                }
            }
            if (this.w_1484_f <= 0) {
                this.s_956_w = u_530_F.n_1700_B(this.RealmsWorldOptions, 0.0f, 360.0f);
                this.t_148_a = u_530_F.n_1700_B(this.RealmsWorldOptions, 20, 80);
            }
        } else {
            this.w_1484_f = u_530_F.n_1700_B(this.RealmsWorldOptions, 100, 600);
            this.w_1484_f -= this.Q_4569_t * 20 * 5;
        }
    }

    private boolean R_4764_Y(c_1514_x p_234603_1_) {
        J_1907_R fishingbobberentity$watertype = lightning.product.W_1247_f$J_1907_R.R_4764_Y;
        for (int i = -1; i <= 2; ++i) {
            J_1907_R fishingbobberentity$watertype1 = this.n_1700_B(p_234603_1_.add(-2, i, -2), p_234603_1_.add(2, i, 2));
            switch (fishingbobberentity$watertype1.ordinal()) {
                case 2: {
                    return false;
                }
                case 0: {
                    if (fishingbobberentity$watertype != lightning.product.W_1247_f$J_1907_R.R_4764_Y) break;
                    return false;
                }
                case 1: {
                    if (fishingbobberentity$watertype != lightning.product.W_1247_f$J_1907_R.n_1700_B) break;
                    return false;
                }
            }
            fishingbobberentity$watertype = fishingbobberentity$watertype1;
        }
        return true;
    }

    private J_1907_R n_1700_B(c_1514_x p_234602_1_, c_1514_x p_234602_2_) {
        return c_1514_x.getAllInBox(p_234602_1_, p_234602_2_).map(this::G_564_y).reduce((p_234601_0_, p_234601_1_) -> p_234601_0_ == p_234601_1_ ? p_234601_0_ : lightning.product.W_1247_f$J_1907_R.R_4764_Y).orElse(lightning.product.W_1247_f$J_1907_R.R_4764_Y);
    }

    private J_1907_R G_564_y(c_1514_x p_234604_1_) {
        K_4074_S blockstate = this.O_508_d.getBlockState(p_234604_1_);
        if (!blockstate.v_4262_N() && !blockstate.n_1700_B(a_3742_W.S_4035_N)) {
            FluidState fluidstate = blockstate.P_4830_p();
            return fluidstate.n_1700_B(FluidTags.J_1907_R) && fluidstate.J_1907_R() && blockstate.u_2550_I(this.O_508_d, p_234604_1_).J_1907_R() ? lightning.product.W_1247_f$J_1907_R.J_1907_R : lightning.product.W_1247_f$J_1907_R.R_4764_Y;
        }
        return lightning.product.W_1247_f$J_1907_R.n_1700_B;
    }

    public boolean P_1922_E() {
        return this.u_2550_I;
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
    }

    public int J_1907_R(Z_1993_T p_146034_1_) {
        a_3913_L playerentity = this.v_4262_N();
        if (!this.O_508_d.Y_259_p && playerentity != null) {
            int i = 0;
            if (this.M_588_G != null) {
                this.u_1723_Y();
                U_3554_Q.Y_1740_V.n_1700_B((B_4088_l)playerentity, p_146034_1_, this, Collections.emptyList());
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)31);
                i = this.M_588_G instanceof n_1494_c ? 3 : 5;
            } else if (this.v_4262_N > 0) {
                q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B((e_3591_l)this.O_508_d).n_1700_B(LootContextParams.u_1723_Y, this.s_4990_V()).n_1700_B(LootContextParams.t_148_a, p_146034_1_).n_1700_B(LootContextParams.n_1700_B, this).n_1700_B(this.RealmsWorldOptions).n_1700_B((float)this.h_1847_R + playerentity.Module());
                p_4985_U loottable = this.O_508_d.T_2506_i().F_2624_D().n_1700_B(o_4810_o.r_715_M);
                List<Z_1993_T> list = loottable.n_1700_B(lootcontext$builder.n_1700_B(f_1402_I.P_1922_E));
                U_3554_Q.Y_1740_V.n_1700_B((B_4088_l)playerentity, p_146034_1_, this, list);
                for (Z_1993_T itemstack : list) {
                    n_1494_c itementity = new n_1494_c(this.O_508_d, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), itemstack);
                    double d0 = playerentity.O_3598_v() - this.O_3598_v();
                    double d1 = playerentity.X_2960_b() - this.X_2960_b();
                    double d2 = playerentity.l_2647_k() - this.l_2647_k();
                    double d3 = 0.1;
                    itementity.h_1847_R(d0 * 0.1, d1 * 0.1 + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08, d2 * 0.1);
                    this.O_508_d.a_(itementity);
                    playerentity.O_508_d.a_(new n_4637_L(playerentity.O_508_d, playerentity.O_3598_v(), playerentity.X_2960_b() + 0.5, playerentity.l_2647_k() + 0.5, this.RealmsWorldOptions.nextInt(6) + 1));
                    if (!itemstack.J_1907_R().n_1700_B(ItemTags.g_164_R)) continue;
                    playerentity.n_1700_B(Stats.g_221_o, 1);
                }
                i = 1;
            }
            if (this.e_1992_r) {
                i = 2;
            }
            this.Ops();
            return i;
        }
        return 0;
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 31 && this.O_508_d.Y_259_p && this.M_588_G instanceof a_3913_L && ((a_3913_L)this.M_588_G).w_1484_f()) {
            I_685_r eventNoPush = new I_685_r(I_685_r.n_1700_B.G_564_y);
            A_4115_X.n_1700_B(eventNoPush);
            if (!eventNoPush.n_1700_B()) {
                this.u_1723_Y();
            }
            super.n_1700_B(id);
            return;
        }
        super.n_1700_B(id);
    }

    protected void u_1723_Y() {
        N_4263_v entity = this.Y_601_j();
        if (entity != null) {
            e_2866_D vector3d = new e_2866_D(entity.O_3598_v() - this.O_3598_v(), entity.X_2960_b() - this.X_2960_b(), entity.l_2647_k() - this.l_2647_k()).n_1700_B(0.1);
            this.M_588_G.v_4262_N(this.M_588_G.I_4348_c().P_1922_E(vector3d));
        }
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    public void Ops() {
        super.Ops();
        a_3913_L playerentity = this.v_4262_N();
        if (playerentity != null) {
            playerentity.X_2960_b = null;
        }
    }

    @Nullable
    public a_3913_L v_4262_N() {
        N_4263_v entity = this.Y_601_j();
        return entity instanceof a_3913_L ? (a_3913_L)entity : null;
    }

    @Nullable
    public N_4263_v w_1484_f() {
        return this.M_588_G;
    }

    @Override
    public boolean L_103_L() {
        return false;
    }

    @Override
    public Packet<?> f_() {
        N_4263_v entity = this.Y_601_j();
        return new ClientboundAddEntityPacket(this, entity == null ? this.j_276_v() : entity.j_276_v());
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.W_1247_f$n_1700_B.n_1700_B();
        }
    }

    static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] G_564_y;

        public static J_1907_R[] values() {
            return (J_1907_R[])G_564_y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.W_1247_f$J_1907_R.n_1700_B();
        }
    }
}



