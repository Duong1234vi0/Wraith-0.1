/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Projectile;
import lightning.product.H_2333_J;
import lightning.product.HitResult;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.ParticleOptions;
import lightning.product.U_2912_j;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.q_2896_o;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;

public abstract class D_3925_G
extends Projectile {
    public double n_1700_B;
    public double J_1907_R;
    public double R_4764_Y;

    protected D_3925_G(t_5_h<? extends D_3925_G> p_i50173_1_, b_4507_u p_i50173_2_) {
        super((t_5_h<? extends Projectile>)p_i50173_1_, p_i50173_2_);
    }

    public D_3925_G(t_5_h<? extends D_3925_G> p_i50174_1_, double p_i50174_2_, double p_i50174_4_, double p_i50174_6_, double p_i50174_8_, double p_i50174_10_, double p_i50174_12_, b_4507_u p_i50174_14_) {
        this(p_i50174_1_, p_i50174_14_);
        this.J_1907_R(p_i50174_2_, p_i50174_4_, p_i50174_6_, this.p_178_J, this.f_4016_n);
        this.t_4219_U();
        double d0 = u_530_F.n_1700_B(p_i50174_8_ * p_i50174_8_ + p_i50174_10_ * p_i50174_10_ + p_i50174_12_ * p_i50174_12_);
        if (d0 != 0.0) {
            this.n_1700_B = p_i50174_8_ / d0 * 0.1;
            this.J_1907_R = p_i50174_10_ / d0 * 0.1;
            this.R_4764_Y = p_i50174_12_ / d0 * 0.1;
        }
    }

    public D_3925_G(t_5_h<? extends D_3925_G> p_i50175_1_, r_4811_B p_i50175_2_, double p_i50175_3_, double p_i50175_5_, double p_i50175_7_, b_4507_u p_i50175_9_) {
        this(p_i50175_1_, p_i50175_2_.O_3598_v(), p_i50175_2_.X_2960_b(), p_i50175_2_.l_2647_k(), p_i50175_3_, p_i50175_5_, p_i50175_7_, p_i50175_9_);
        this.J_1907_R(p_i50175_2_);
        this.J_1907_R(p_i50175_2_.p_178_J, p_i50175_2_.f_4016_n);
    }

    @Override
    protected void a_() {
    }

    @Override
    public boolean n_1700_B(double distance) {
        double d0 = this.i_601_W().getAverageEdgeLength() * 4.0;
        if (Double.isNaN(d0)) {
            d0 = 4.0;
        }
        return distance < (d0 *= 64.0) * d0;
    }

    @Override
    public void v_() {
        N_4263_v entity = this.Y_601_j();
        if (this.O_508_d.Y_259_p || (entity == null || !entity.t_4219_U) && this.O_508_d.M_588_G(this.b_2312_j())) {
            HitResult raytraceresult;
            super.v_();
            if (this.u_1723_Y()) {
                this.P_1922_E(1);
            }
            if ((raytraceresult = H_2333_J.n_1700_B((N_4263_v)this, this::n_1700_B)).R_4764_Y() != HitResult.n_1700_B.n_1700_B) {
                this.n_1700_B(raytraceresult);
            }
            this.F_2624_D();
            e_2866_D vector3d = this.I_4348_c();
            double d0 = this.O_3598_v() + vector3d.J_1907_R;
            double d1 = this.X_2960_b() + vector3d.R_4764_Y;
            double d2 = this.l_2647_k() + vector3d.G_564_y;
            H_2333_J.n_1700_B((N_4263_v)this, 0.2f);
            float f = this.w_1484_f();
            if (this.RowButton()) {
                for (int i = 0; i < 4; ++i) {
                    float f1 = 0.25f;
                    this.O_508_d.n_1700_B(ParticleTypes.P_1922_E, d0 - vector3d.J_1907_R * 0.25, d1 - vector3d.R_4764_Y * 0.25, d2 - vector3d.G_564_y * 0.25, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
                }
                f = 0.8f;
            }
            this.v_4262_N(vector3d.J_1907_R(this.n_1700_B, this.J_1907_R, this.R_4764_Y).n_1700_B((double)f));
            this.O_508_d.n_1700_B(this.v_4262_N(), d0, d1 + 0.5, d2, 0.0, 0.0, 0.0);
            this.J_1907_R(d0, d1, d2);
        } else {
            this.Ops();
        }
    }

    @Override
    protected boolean n_1700_B(N_4263_v p_230298_1_) {
        return super.n_1700_B(p_230298_1_) && !p_230298_1_.j_1564_a;
    }

    protected boolean u_1723_Y() {
        return true;
    }

    protected ParticleOptions v_4262_N() {
        return ParticleTypes.B_1668_F;
    }

    protected float w_1484_f() {
        return 0.95f;
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("power", this.n_1700_B(new double[]{this.n_1700_B, this.J_1907_R, this.R_4764_Y}));
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        q_2896_o listnbt;
        super.J_1907_R(compound);
        if (compound.R_4764_Y("power", 9) && (listnbt = compound.G_564_y("power", 6)).size() == 3) {
            this.n_1700_B = listnbt.v_4262_N(0);
            this.J_1907_R = listnbt.v_4262_N(1);
            this.R_4764_Y = listnbt.v_4262_N(2);
        }
    }

    @Override
    public boolean C_290_v() {
        return true;
    }

    @Override
    public float G_424_k() {
        return 1.0f;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        this.RealmsCreateRealmScreen();
        N_4263_v entity = source.u_2550_I();
        if (entity != null) {
            e_2866_D vector3d = entity.RealmsSettingsScreen();
            this.v_4262_N(vector3d);
            this.n_1700_B = vector3d.J_1907_R * 0.1;
            this.J_1907_R = vector3d.R_4764_Y * 0.1;
            this.R_4764_Y = vector3d.G_564_y * 0.1;
            this.J_1907_R(entity);
            return true;
        }
        return false;
    }

    @Override
    public float RealmsConfirmScreen() {
        return 1.0f;
    }

    @Override
    public Packet<?> f_() {
        N_4263_v entity = this.Y_601_j();
        int i = entity == null ? 0 : entity.j_276_v();
        return new ClientboundAddEntityPacket(this.j_276_v(), this.w_2705_t(), this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.f_4016_n, this.p_178_J, this.f_4016_n(), i, new e_2866_D(this.n_1700_B, this.J_1907_R, this.R_4764_Y));
    }
}


