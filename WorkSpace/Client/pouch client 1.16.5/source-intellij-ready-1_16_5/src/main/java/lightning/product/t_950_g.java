/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;

public class t_950_g
extends N_4263_v {
    private int n_1700_B;
    private boolean J_1907_R;
    private int R_4764_Y = 22;
    private boolean G_564_y;
    private r_4811_B P_1922_E;
    private UUID u_1723_Y;

    public t_950_g(t_5_h<? extends t_950_g> p_i50170_1_, b_4507_u p_i50170_2_) {
        super(p_i50170_1_, p_i50170_2_);
    }

    public t_950_g(b_4507_u worldIn, double x, double y, double z, float p_i47276_8_, int p_i47276_9_, r_4811_B casterIn) {
        this((t_5_h<? extends t_950_g>)t_5_h.k_2293_S, worldIn);
        this.n_1700_B = p_i47276_9_;
        this.n_1700_B(casterIn);
        this.p_178_J = p_i47276_8_ * 57.295776f;
        this.J_1907_R(x, y, z);
    }

    @Override
    protected void a_() {
    }

    public void n_1700_B(@Nullable r_4811_B p_190549_1_) {
        this.P_1922_E = p_190549_1_;
        this.u_1723_Y = p_190549_1_ == null ? null : p_190549_1_.w_2705_t();
    }

    @Nullable
    public r_4811_B P_1922_E() {
        N_4263_v entity;
        if (this.P_1922_E == null && this.u_1723_Y != null && this.O_508_d instanceof e_3591_l && (entity = ((e_3591_l)this.O_508_d).J_1907_R(this.u_1723_Y)) instanceof r_4811_B) {
            this.P_1922_E = (r_4811_B)entity;
        }
        return this.P_1922_E;
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        this.n_1700_B = compound.w_1484_f("Warmup");
        if (compound.J_1907_R("Owner")) {
            this.u_1723_Y = compound.n_1700_B("Owner");
        }
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        compound.J_1907_R("Warmup", this.n_1700_B);
        if (this.u_1723_Y != null) {
            compound.n_1700_B("Owner", this.u_1723_Y);
        }
    }

    @Override
    public void v_() {
        super.v_();
        if (this.O_508_d.Y_259_p) {
            if (this.G_564_y) {
                --this.R_4764_Y;
                if (this.R_4764_Y == 14) {
                    for (int i = 0; i < 12; ++i) {
                        double d0 = this.O_3598_v() + (this.RealmsWorldOptions.nextDouble() * 2.0 - 1.0) * (double)this.C_415_h() * 0.5;
                        double d1 = this.X_2960_b() + 0.05 + this.RealmsWorldOptions.nextDouble();
                        double d2 = this.l_2647_k() + (this.RealmsWorldOptions.nextDouble() * 2.0 - 1.0) * (double)this.C_415_h() * 0.5;
                        double d3 = (this.RealmsWorldOptions.nextDouble() * 2.0 - 1.0) * 0.3;
                        double d4 = 0.3 + this.RealmsWorldOptions.nextDouble() * 0.3;
                        double d5 = (this.RealmsWorldOptions.nextDouble() * 2.0 - 1.0) * 0.3;
                        this.O_508_d.n_1700_B(ParticleTypes.v_4262_N, d0, d1 + 1.0, d2, d3, d4, d5);
                    }
                }
            }
        } else if (--this.n_1700_B < 0) {
            if (this.n_1700_B == -8) {
                for (r_4811_B livingentity : this.O_508_d.n_1700_B(r_4811_B.class, this.i_601_W().grow(0.2, 0.0, 0.2))) {
                    this.G_564_y(livingentity);
                }
            }
            if (!this.J_1907_R) {
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)4);
                this.J_1907_R = true;
            }
            if (--this.R_4764_Y < 0) {
                this.Ops();
            }
        }
    }

    private void G_564_y(r_4811_B p_190551_1_) {
        r_4811_B livingentity = this.P_1922_E();
        if (p_190551_1_.RealmsLongRunningMcoTaskScreen() && !p_190551_1_.P_925_e() && p_190551_1_ != livingentity) {
            if (livingentity == null) {
                p_190551_1_.n_1700_B(P_11_z.Q_4569_t, 6.0f);
            } else {
                if (livingentity.Q_4569_t(p_190551_1_)) {
                    return;
                }
                p_190551_1_.n_1700_B(P_11_z.R_4764_Y(this, livingentity), 6.0f);
            }
        }
    }

    @Override
    public void n_1700_B(byte id) {
        super.n_1700_B(id);
        if (id == 4) {
            this.G_564_y = true;
            if (!this.y_1700_S()) {
                this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.k_3129_Y, this.r_2478_U(), 1.0f, this.RealmsWorldOptions.nextFloat() * 0.2f + 0.85f, false);
            }
        }
    }

    public float n_1700_B(float partialTicks) {
        if (!this.G_564_y) {
            return 0.0f;
        }
        int i = this.R_4764_Y - 2;
        return i <= 0 ? 1.0f : 1.0f - ((float)i - partialTicks) / 20.0f;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }
}


