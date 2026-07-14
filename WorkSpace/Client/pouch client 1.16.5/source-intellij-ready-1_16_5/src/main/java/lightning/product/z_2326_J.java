/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_4114_x;
import lightning.product.K_4074_S;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.d_742_e;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.t_5_h;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import lightning.product.y_4319_k;

public class z_2326_J
extends y_4319_k {
    private static final h_256_u<String> n_1700_B = C_4114_x.n_1700_B(z_2326_J.class, EntityDataSerializers.G_564_y);
    private static final h_256_u<x_282_a> J_1907_R = C_4114_x.n_1700_B(z_2326_J.class, EntityDataSerializers.P_1922_E);
    private final d_742_e R_4764_Y = new n_1700_B();
    private int G_564_y;

    public z_2326_J(t_5_h<? extends z_2326_J> type, b_4507_u world) {
        super(type, world);
    }

    public z_2326_J(b_4507_u worldIn, double x, double y, double z) {
        super(t_5_h.Z_976_R, worldIn, x, y, z);
    }

    @Override
    protected void a_() {
        super.a_();
        this.D_60_a().n_1700_B(n_1700_B, "");
        this.D_60_a().n_1700_B(J_1907_R, U_2871_b.R_4764_Y);
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.R_4764_Y.J_1907_R(compound);
        this.D_60_a().J_1907_R(n_1700_B, this.Y_259_p().w_1484_f());
        this.D_60_a().J_1907_R(J_1907_R, this.Y_259_p().v_4262_N());
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        this.R_4764_Y.n_1700_B(compound);
    }

    @Override
    public y_4319_k.n_1700_B h_1847_R() {
        return y_4319_k.n_1700_B.v_4262_N;
    }

    @Override
    public K_4074_S M_182_A() {
        return a_3742_W.N_260_m.multiplayerClientSuggestionProvider();
    }

    public d_742_e Y_259_p() {
        return this.R_4764_Y;
    }

    @Override
    public void n_1700_B(int x, int y, int z, boolean receivingPower) {
        if (receivingPower && this.RealmsWorldResetDto - this.G_564_y >= 4) {
            this.Y_259_p().n_1700_B(this.O_508_d);
            this.G_564_y = this.RealmsWorldResetDto;
        }
    }

    @Override
    public m_3054_I n_1700_B(a_3913_L player, x_1688_C hand) {
        return this.R_4764_Y.n_1700_B(player);
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        super.n_1700_B(key);
        if (J_1907_R.equals(key)) {
            try {
                this.R_4764_Y.J_1907_R(this.D_60_a().n_1700_B(J_1907_R));
            }
            catch (Throwable throwable) {}
        } else if (n_1700_B.equals(key)) {
            this.R_4764_Y.n_1700_B(this.D_60_a().n_1700_B(n_1700_B));
        }
    }

    @Override
    public boolean J_303_C() {
        return true;
    }

    public class n_1700_B
    extends d_742_e {
        @Override
        public e_3591_l n_1700_B() {
            return (e_3591_l)z_2326_J.this.O_508_d;
        }

        @Override
        public void J_1907_R() {
            z_2326_J.this.D_60_a().J_1907_R(n_1700_B, this.w_1484_f());
            z_2326_J.this.D_60_a().J_1907_R(J_1907_R, this.v_4262_N());
        }

        @Override
        public e_2866_D R_4764_Y() {
            return z_2326_J.this.s_4990_V();
        }

        public z_2326_J G_564_y() {
            return z_2326_J.this;
        }

        @Override
        public y_2498_m P_1922_E() {
            return new y_2498_m(this, z_2326_J.this.s_4990_V(), z_2326_J.this.f_1043_S(), this.n_1700_B(), 2, this.t_148_a().getString(), z_2326_J.this.c_(), this.n_1700_B().T_2506_i(), z_2326_J.this);
        }
    }
}


