/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.F_1241_B;
import lightning.product.I_1170_F;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.R_1815_U;
import lightning.product.U_2912_j;
import lightning.product.b_4507_u;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;

public class PrimedTnt
extends N_4263_v {
    private static final h_256_u<Integer> n_1700_B = C_4114_x.n_1700_B(PrimedTnt.class, EntityDataSerializers.J_1907_R);
    @Nullable
    private r_4811_B J_1907_R;
    private int R_4764_Y = 80;

    public PrimedTnt(t_5_h<? extends PrimedTnt> type, b_4507_u worldIn) {
        super(type, worldIn);
        this.s_2632_s = true;
    }

    public PrimedTnt(b_4507_u worldIn, double x, double y, double z, @Nullable r_4811_B igniter) {
        this((t_5_h<? extends PrimedTnt>)t_5_h.f_4016_n, worldIn);
        this.J_1907_R(x, y, z);
        double d0 = worldIn.w_1457_N.nextDouble() * 6.2831854820251465;
        this.h_1847_R(-Math.sin(d0) * 0.02, 0.2f, -Math.cos(d0) * 0.02);
        this.n_1700_B(80);
        this.r_715_M = x;
        this.A_1038_p = y;
        this.i_1637_u = z;
        this.J_1907_R = igniter;
    }

    @Override
    protected void a_() {
        this.l_4537_E.n_1700_B(n_1700_B, 80);
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    public boolean C_290_v() {
        return !this.t_4219_U;
    }

    @Override
    public void v_() {
        if (!this.u_744_e()) {
            this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.04, 0.0));
        }
        this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
        this.v_4262_N(this.I_4348_c().n_1700_B(0.98));
        if (this.e_1992_r) {
            this.v_4262_N(this.I_4348_c().G_564_y(0.7, -0.5, 0.7));
        }
        --this.R_4764_Y;
        if (this.R_4764_Y <= 0) {
            this.Ops();
            if (!this.O_508_d.Y_259_p) {
                this.w_1484_f();
            }
        } else {
            this.RealmsScreenWithCallback();
            if (this.O_508_d.Y_259_p) {
                this.O_508_d.n_1700_B(ParticleTypes.B_1668_F, this.O_3598_v(), this.X_2960_b() + 0.5, this.l_2647_k(), 0.0, 0.0, 0.0);
            }
        }
    }

    private void w_1484_f() {
        float f = 4.0f;
        this.O_508_d.n_1700_B(this, this.O_3598_v(), this.P_1922_E(0.0625), this.l_2647_k(), 4.0f, F_1241_B.n_1700_B.J_1907_R);
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        compound.n_1700_B("Fuse", (short)this.v_4262_N());
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        this.n_1700_B(compound.v_4262_N("Fuse"));
    }

    @Nullable
    public r_4811_B P_1922_E() {
        return this.J_1907_R;
    }

    @Override
    protected float n_1700_B(I_1170_F poseIn, R_1815_U sizeIn) {
        return 0.15f;
    }

    public void n_1700_B(int fuseIn) {
        this.l_4537_E.J_1907_R(n_1700_B, fuseIn);
        this.R_4764_Y = fuseIn;
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (n_1700_B.equals(key)) {
            this.R_4764_Y = this.u_1723_Y();
        }
    }

    public int u_1723_Y() {
        return this.l_4537_E.n_1700_B(n_1700_B);
    }

    public int v_4262_N() {
        return this.R_4764_Y;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }
}


