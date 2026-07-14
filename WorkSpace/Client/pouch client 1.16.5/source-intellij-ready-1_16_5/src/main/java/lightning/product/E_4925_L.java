/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.C_4114_x;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.h_384_L;
import lightning.product.EntityDataSerializers;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.LightningBolt;
import lightning.product.t_5_h;
import lightning.product.EntityHitResult;

public class E_4925_L
extends h_384_L {
    private static final h_256_u<Byte> u_1723_Y = C_4114_x.n_1700_B(E_4925_L.class, EntityDataSerializers.n_1700_B);
    private static final h_256_u<Boolean> v_4262_N = C_4114_x.n_1700_B(E_4925_L.class, EntityDataSerializers.t_148_a);
    private Z_1993_T w_1484_f = new Z_1993_T(Items.P_2605_j);
    private boolean t_148_a;
    public int P_1922_E;

    public E_4925_L(t_5_h<? extends E_4925_L> type, b_4507_u worldIn) {
        super((t_5_h<? extends h_384_L>)type, worldIn);
    }

    public E_4925_L(b_4507_u worldIn, r_4811_B thrower, Z_1993_T thrownStackIn) {
        super(t_5_h.ValueObject, thrower, worldIn);
        this.w_1484_f = thrownStackIn.t_148_a();
        this.l_4537_E.J_1907_R(u_1723_Y, (byte)K_4096_w.u_1723_Y(thrownStackIn));
        this.l_4537_E.J_1907_R(v_4262_N, thrownStackIn.Y_259_p());
    }

    public E_4925_L(b_4507_u worldIn, double x, double y, double z) {
        super(t_5_h.ValueObject, x, y, z, worldIn);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(u_1723_Y, (byte)0);
        this.l_4537_E.n_1700_B(v_4262_N, false);
    }

    @Override
    public void v_() {
        if (this.J_1907_R > 4) {
            this.t_148_a = true;
        }
        N_4263_v entity = this.Y_601_j();
        if ((this.t_148_a || this.multiplayerClientSuggestionProvider()) && entity != null) {
            byte i = this.l_4537_E.n_1700_B(u_1723_Y);
            if (i > 0 && !this.Q_2552_b()) {
                if (!this.O_508_d.Y_259_p && this.R_4764_Y == h_384_L.n_1700_B.J_1907_R) {
                    this.n_1700_B(this.w_1484_f(), 0.1f);
                }
                this.Ops();
            } else if (i > 0) {
                this.R_4764_Y(true);
                e_2866_D vector3d = new e_2866_D(entity.O_3598_v() - this.O_3598_v(), entity.X_2048_Y() - this.X_2960_b(), entity.l_2647_k() - this.l_2647_k());
                this.Q_4569_t(this.O_3598_v(), this.X_2960_b() + vector3d.R_4764_Y * 0.015 * (double)i, this.l_2647_k());
                if (this.O_508_d.Y_259_p) {
                    this.dtoRealmsServerAddress = this.X_2960_b();
                }
                double d0 = 0.05 * (double)i;
                this.v_4262_N(this.I_4348_c().n_1700_B(0.95).P_1922_E(vector3d.G_564_y().n_1700_B(d0)));
                if (this.P_1922_E == 0) {
                    this.n_1700_B(SoundEvents.DropperBlock, 10.0f, 1.0f);
                }
                ++this.P_1922_E;
            }
        }
        super.v_();
    }

    private boolean Q_2552_b() {
        N_4263_v entity = this.Y_601_j();
        if (entity != null && entity.RealmsLongRunningMcoTaskScreen()) {
            return !(entity instanceof B_4088_l) || !entity.d_2461_k();
        }
        return false;
    }

    @Override
    protected Z_1993_T w_1484_f() {
        return this.w_1484_f.t_148_a();
    }

    public boolean w_1457_N() {
        return this.l_4537_E.n_1700_B(v_4262_N);
    }

    @Override
    @Nullable
    protected EntityHitResult n_1700_B(e_2866_D startVec, e_2866_D endVec) {
        return this.t_148_a ? null : super.n_1700_B(startVec, endVec);
    }

    @Override
    protected void n_1700_B(EntityHitResult p_213868_1_) {
        c_1514_x blockpos;
        N_4263_v entity1;
        N_4263_v entity = p_213868_1_.n_1700_B();
        float f = 8.0f;
        if (entity instanceof r_4811_B) {
            r_4811_B livingentity = (r_4811_B)entity;
            f += K_4096_w.n_1700_B(this.w_1484_f, livingentity.F_2860_q());
        }
        P_11_z damagesource = P_11_z.n_1700_B((N_4263_v)this, (entity1 = this.Y_601_j()) == null ? this : entity1);
        this.t_148_a = true;
        SoundEvent soundevent = SoundEvents.DoublePlantBlock;
        if (entity.n_1700_B(damagesource, f)) {
            if (entity.f_4016_n() == t_5_h.Y_259_p) {
                return;
            }
            if (entity instanceof r_4811_B) {
                r_4811_B livingentity1 = (r_4811_B)entity;
                if (entity1 instanceof r_4811_B) {
                    K_4096_w.n_1700_B(livingentity1, entity1);
                    K_4096_w.J_1907_R((r_4811_B)entity1, (N_4263_v)livingentity1);
                }
                this.n_1700_B(livingentity1);
            }
        }
        this.v_4262_N(this.I_4348_c().G_564_y(-0.01, -0.1, -0.01));
        float f1 = 1.0f;
        if (this.O_508_d instanceof e_3591_l && this.O_508_d.N_2525_X() && K_4096_w.w_1484_f(this.w_1484_f) && this.O_508_d.canSeeSky(blockpos = entity.b_2312_j())) {
            LightningBolt lightningboltentity = t_5_h.z_4693_k.n_1700_B(this.O_508_d);
            lightningboltentity.P_1922_E(e_2866_D.R_4764_Y(blockpos));
            lightningboltentity.G_564_y(entity1 instanceof B_4088_l ? (B_4088_l)entity1 : null);
            this.O_508_d.a_(lightningboltentity);
            soundevent = SoundEvents.EndRodBlock;
            f1 = 5.0f;
        }
        this.n_1700_B(soundevent, f1, 1.0f);
    }

    @Override
    protected SoundEvent u_1723_Y() {
        return SoundEvents.DragonEggBlock;
    }

    @Override
    public void c_(a_3913_L entityIn) {
        N_4263_v entity = this.Y_601_j();
        if (entity == null || entity.w_2705_t() == entityIn.w_2705_t()) {
            super.c_(entityIn);
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("Trident", 10)) {
            this.w_1484_f = Z_1993_T.n_1700_B(compound.M_182_A("Trident"));
        }
        this.t_148_a = compound.t_1786_h("DealtDamage");
        this.l_4537_E.J_1907_R(u_1723_Y, (byte)K_4096_w.u_1723_Y(this.w_1484_f));
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("Trident", this.w_1484_f.J_1907_R(new U_2912_j()));
        compound.n_1700_B("DealtDamage", this.t_148_a);
    }

    @Override
    public void P_1922_E() {
        byte i = this.l_4537_E.n_1700_B(u_1723_Y);
        if (this.R_4764_Y != h_384_L.n_1700_B.J_1907_R || i <= 0) {
            super.P_1922_E();
        }
    }

    @Override
    protected float M_182_A() {
        return 0.99f;
    }

    @Override
    public boolean t_148_a(double x, double y, double z) {
        return true;
    }
}


