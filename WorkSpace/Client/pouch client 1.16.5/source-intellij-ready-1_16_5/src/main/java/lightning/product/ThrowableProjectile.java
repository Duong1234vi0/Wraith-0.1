/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4605_O;
import lightning.product.Projectile;
import lightning.product.BlockHitResult;
import lightning.product.H_2333_J;
import lightning.product.HitResult;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.i_2154_H;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;

public abstract class ThrowableProjectile
extends Projectile {
    protected ThrowableProjectile(t_5_h<? extends ThrowableProjectile> type, b_4507_u worldIn) {
        super((t_5_h<? extends Projectile>)type, worldIn);
    }

    protected ThrowableProjectile(t_5_h<? extends ThrowableProjectile> type, double x, double y, double z, b_4507_u worldIn) {
        this(type, worldIn);
        this.J_1907_R(x, y, z);
    }

    protected ThrowableProjectile(t_5_h<? extends ThrowableProjectile> type, r_4811_B livingEntityIn, b_4507_u worldIn) {
        this(type, livingEntityIn.O_3598_v(), livingEntityIn.X_2048_Y() - (double)0.1f, livingEntityIn.l_2647_k(), worldIn);
        this.J_1907_R(livingEntityIn);
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
        float f;
        super.v_();
        HitResult raytraceresult = H_2333_J.n_1700_B((N_4263_v)this, this::n_1700_B);
        boolean flag = false;
        if (raytraceresult.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            c_1514_x blockpos = ((BlockHitResult)raytraceresult).n_1700_B();
            K_4074_S blockstate = this.O_508_d.getBlockState(blockpos);
            if (blockstate.n_1700_B(a_3742_W.M_766_z)) {
                this.J_1907_R(blockpos);
                flag = true;
            } else if (blockstate.n_1700_B(a_3742_W.ItemRelease)) {
                i_2154_H tileentity = this.O_508_d.getTileEntity(blockpos);
                if (tileentity instanceof A_4605_O && A_4605_O.n_1700_B(this)) {
                    ((A_4605_O)tileentity).J_1907_R(this);
                }
                flag = true;
            }
        }
        if (raytraceresult.R_4764_Y() != HitResult.n_1700_B.n_1700_B && !flag) {
            this.n_1700_B(raytraceresult);
        }
        this.F_2624_D();
        e_2866_D vector3d = this.I_4348_c();
        double d2 = this.O_3598_v() + vector3d.J_1907_R;
        double d0 = this.X_2960_b() + vector3d.R_4764_Y;
        double d1 = this.l_2647_k() + vector3d.G_564_y;
        this.Y_259_p();
        if (this.RowButton()) {
            for (int i = 0; i < 4; ++i) {
                float f1 = 0.25f;
                this.O_508_d.n_1700_B(ParticleTypes.P_1922_E, d2 - vector3d.J_1907_R * 0.25, d0 - vector3d.R_4764_Y * 0.25, d1 - vector3d.G_564_y * 0.25, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
            }
            f = 0.8f;
        } else {
            f = 0.99f;
        }
        this.v_4262_N(vector3d.n_1700_B((double)f));
        if (!this.u_744_e()) {
            e_2866_D vector3d1 = this.I_4348_c();
            this.h_1847_R(vector3d1.J_1907_R, vector3d1.R_4764_Y - (double)this.u_1723_Y(), vector3d1.G_564_y);
        }
        this.J_1907_R(d2, d0, d1);
    }

    protected float u_1723_Y() {
        return 0.03f;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }
}



