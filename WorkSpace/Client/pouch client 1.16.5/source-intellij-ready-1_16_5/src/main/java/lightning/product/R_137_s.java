/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Projectile;
import lightning.product.C_4114_x;
import lightning.product.G_1066_I;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.j_3341_s;
import lightning.product.n_1494_c;
import lightning.product.Items;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;

public class R_137_s
extends N_4263_v
implements G_1066_I {
    private static final h_256_u<Z_1993_T> n_1700_B = C_4114_x.n_1700_B(R_137_s.class, EntityDataSerializers.v_4262_N);
    private double J_1907_R;
    private double R_4764_Y;
    private double G_564_y;
    private int P_1922_E;
    private boolean u_1723_Y;

    public R_137_s(t_5_h<? extends R_137_s> p_i50169_1_, b_4507_u p_i50169_2_) {
        super(p_i50169_1_, p_i50169_2_);
    }

    public R_137_s(b_4507_u worldIn, double x, double y, double z) {
        this((t_5_h<? extends R_137_s>)t_5_h.Z_875_P, worldIn);
        this.P_1922_E = 0;
        this.J_1907_R(x, y, z);
    }

    public void J_1907_R(Z_1993_T p_213863_1_) {
        if (p_213863_1_.J_1907_R() != Items.V_1824_v || p_213863_1_.h_1847_R()) {
            this.D_60_a().J_1907_R(n_1700_B, j_3341_s.n_1700_B(p_213863_1_.t_148_a(), p_213862_0_ -> p_213862_0_.P_1922_E(1)));
        }
    }

    private Z_1993_T P_1922_E() {
        return this.D_60_a().n_1700_B(n_1700_B);
    }

    @Override
    public Z_1993_T n_1700_B() {
        Z_1993_T itemstack = this.P_1922_E();
        return itemstack.n_1700_B() ? new Z_1993_T(Items.V_1824_v) : itemstack;
    }

    @Override
    protected void a_() {
        this.D_60_a().n_1700_B(n_1700_B, Z_1993_T.J_1907_R);
    }

    @Override
    public boolean n_1700_B(double distance) {
        double d0 = this.i_601_W().getAverageEdgeLength() * 4.0;
        if (Double.isNaN(d0)) {
            d0 = 4.0;
        }
        return distance < (d0 *= 64.0) * d0;
    }

    public void n_1700_B(c_1514_x pos) {
        double d3;
        double d0 = pos.getX();
        int i = pos.getY();
        double d1 = pos.getZ();
        double d2 = d0 - this.O_3598_v();
        float f = u_530_F.n_1700_B(d2 * d2 + (d3 = d1 - this.l_2647_k()) * d3);
        if (f > 12.0f) {
            this.J_1907_R = this.O_3598_v() + d2 / (double)f * 12.0;
            this.G_564_y = this.l_2647_k() + d3 / (double)f * 12.0;
            this.R_4764_Y = this.X_2960_b() + 8.0;
        } else {
            this.J_1907_R = d0;
            this.R_4764_Y = i;
            this.G_564_y = d1;
        }
        this.P_1922_E = 0;
        this.u_1723_Y = this.RealmsWorldOptions.nextInt(5) > 0;
    }

    @Override
    public void s_956_w(double x, double y, double z) {
        this.h_1847_R(x, y, z);
        if (this.UploadStatus == 0.0f && this.j_276_v == 0.0f) {
            float f = u_530_F.n_1700_B(x * x + z * z);
            this.p_178_J = (float)(u_530_F.G_564_y(x, z) * 57.2957763671875);
            this.f_4016_n = (float)(u_530_F.G_564_y(y, (double)f) * 57.2957763671875);
            this.j_276_v = this.p_178_J;
            this.UploadStatus = this.f_4016_n;
        }
    }

    @Override
    public void v_() {
        super.v_();
        e_2866_D vector3d = this.I_4348_c();
        double d0 = this.O_3598_v() + vector3d.J_1907_R;
        double d1 = this.X_2960_b() + vector3d.R_4764_Y;
        double d2 = this.l_2647_k() + vector3d.G_564_y;
        float f = u_530_F.n_1700_B(R_137_s.R_4764_Y(vector3d));
        this.f_4016_n = Projectile.n_1700_B(this.UploadStatus, (float)(u_530_F.G_564_y(vector3d.R_4764_Y, (double)f) * 57.2957763671875));
        this.p_178_J = Projectile.n_1700_B(this.j_276_v, (float)(u_530_F.G_564_y(vector3d.J_1907_R, vector3d.G_564_y) * 57.2957763671875));
        if (!this.O_508_d.Y_259_p) {
            double d3 = this.J_1907_R - d0;
            double d4 = this.G_564_y - d2;
            float f1 = (float)Math.sqrt(d3 * d3 + d4 * d4);
            float f2 = (float)u_530_F.G_564_y(d4, d3);
            double d5 = u_530_F.G_564_y(0.0025, (double)f, (double)f1);
            double d6 = vector3d.R_4764_Y;
            if (f1 < 1.0f) {
                d5 *= 0.8;
                d6 *= 0.8;
            }
            int j = this.X_2960_b() < this.R_4764_Y ? 1 : -1;
            vector3d = new e_2866_D(Math.cos(f2) * d5, d6 + ((double)j - d6) * (double)0.015f, Math.sin(f2) * d5);
            this.v_4262_N(vector3d);
        }
        float f3 = 0.25f;
        if (this.RowButton()) {
            for (int i = 0; i < 4; ++i) {
                this.O_508_d.n_1700_B(ParticleTypes.P_1922_E, d0 - vector3d.J_1907_R * 0.25, d1 - vector3d.R_4764_Y * 0.25, d2 - vector3d.G_564_y * 0.25, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
            }
        } else {
            this.O_508_d.n_1700_B(ParticleTypes.g_221_o, d0 - vector3d.J_1907_R * 0.25 + this.RealmsWorldOptions.nextDouble() * 0.6 - 0.3, d1 - vector3d.R_4764_Y * 0.25 - 0.5, d2 - vector3d.G_564_y * 0.25 + this.RealmsWorldOptions.nextDouble() * 0.6 - 0.3, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
        }
        if (!this.O_508_d.Y_259_p) {
            this.J_1907_R(d0, d1, d2);
            ++this.P_1922_E;
            if (this.P_1922_E > 80 && !this.O_508_d.Y_259_p) {
                this.n_1700_B(SoundEvents.g_1734_y, 1.0f, 1.0f);
                this.Ops();
                if (this.u_1723_Y) {
                    this.O_508_d.a_(new n_1494_c(this.O_508_d, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.n_1700_B()));
                } else {
                    this.O_508_d.R_4764_Y(2003, this.b_2312_j(), 0);
                }
            }
        } else {
            this.Q_4569_t(d0, d1, d2);
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        Z_1993_T itemstack = this.P_1922_E();
        if (!itemstack.n_1700_B()) {
            compound.n_1700_B("Item", itemstack.J_1907_R(new U_2912_j()));
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        Z_1993_T itemstack = Z_1993_T.n_1700_B(compound.M_182_A("Item"));
        this.J_1907_R(itemstack);
    }

    @Override
    public float RealmsConfirmScreen() {
        return 1.0f;
    }

    @Override
    public boolean Z_735_d() {
        return false;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }
}


