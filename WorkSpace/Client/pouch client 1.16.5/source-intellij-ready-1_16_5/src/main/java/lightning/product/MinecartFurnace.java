/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_2352_Z;
import lightning.product.A_3138_X;
import lightning.product.C_4114_x;
import lightning.product.K_4074_S;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.Items;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.y_4319_k;

public class MinecartFurnace
extends y_4319_k {
    private static final h_256_u<Boolean> R_4764_Y = C_4114_x.n_1700_B(MinecartFurnace.class, EntityDataSerializers.t_148_a);
    private int G_564_y;
    public double n_1700_B;
    public double J_1907_R;
    private static final b_3278_X P_1922_E = b_3278_X.n_1700_B(Items.T_797_O, Items.d_560_A);

    public MinecartFurnace(t_5_h<? extends MinecartFurnace> furnaceCart, b_4507_u world) {
        super(furnaceCart, world);
    }

    public MinecartFurnace(b_4507_u worldIn, double x, double y, double z) {
        super(t_5_h.H_1990_U, worldIn, x, y, z);
    }

    @Override
    public y_4319_k.n_1700_B h_1847_R() {
        return y_4319_k.n_1700_B.R_4764_Y;
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(R_4764_Y, false);
    }

    @Override
    public void v_() {
        super.v_();
        if (!this.O_508_d.v_4276_D()) {
            if (this.G_564_y > 0) {
                --this.G_564_y;
            }
            if (this.G_564_y <= 0) {
                this.n_1700_B = 0.0;
                this.J_1907_R = 0.0;
            }
            this.R_4764_Y(this.G_564_y > 0);
        }
        if (this.Y_259_p() && this.RealmsWorldOptions.nextInt(4) == 0) {
            this.O_508_d.n_1700_B(ParticleTypes.d_2461_k, this.O_3598_v(), this.X_2960_b() + 0.8, this.l_2647_k(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected double P_1922_E() {
        return 0.2;
    }

    @Override
    public void J_1907_R(P_11_z source) {
        super.J_1907_R(source);
        if (!source.G_564_y() && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
            this.n_1700_B(a_3742_W.P_925_e);
        }
    }

    @Override
    protected void R_4764_Y(c_1514_x pos, K_4074_S state) {
        double d0 = 1.0E-4;
        double d1 = 0.001;
        super.R_4764_Y(pos, state);
        e_2866_D vector3d = this.I_4348_c();
        double d2 = MinecartFurnace.R_4764_Y(vector3d);
        double d3 = this.n_1700_B * this.n_1700_B + this.J_1907_R * this.J_1907_R;
        if (d3 > 1.0E-4 && d2 > 0.001) {
            double d4 = u_530_F.n_1700_B(d2);
            double d5 = u_530_F.n_1700_B(d3);
            this.n_1700_B = vector3d.J_1907_R / d4 * d5;
            this.J_1907_R = vector3d.G_564_y / d4 * d5;
        }
    }

    @Override
    protected void v_4262_N() {
        double d0 = this.n_1700_B * this.n_1700_B + this.J_1907_R * this.J_1907_R;
        if (d0 > 1.0E-7) {
            d0 = u_530_F.n_1700_B(d0);
            this.n_1700_B /= d0;
            this.J_1907_R /= d0;
            this.v_4262_N(this.I_4348_c().G_564_y(0.8, 0.0, 0.8).J_1907_R(this.n_1700_B, 0.0, this.J_1907_R));
        } else {
            this.v_4262_N(this.I_4348_c().G_564_y(0.98, 0.0, 0.98));
        }
        super.v_4262_N();
    }

    @Override
    public m_3054_I n_1700_B(a_3913_L player, x_1688_C hand) {
        Z_1993_T itemstack = player.R_4764_Y(hand);
        if (P_1922_E.n_1700_B(itemstack) && this.G_564_y + 3600 <= 32000) {
            if (!player.C_415_h.G_564_y) {
                itemstack.v_4262_N(1);
            }
            this.G_564_y += 3600;
        }
        if (this.G_564_y > 0) {
            this.n_1700_B = this.O_3598_v() - player.O_3598_v();
            this.J_1907_R = this.l_2647_k() - player.l_2647_k();
        }
        return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("PushX", this.n_1700_B);
        compound.n_1700_B("PushZ", this.J_1907_R);
        compound.n_1700_B("Fuel", (short)this.G_564_y);
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.n_1700_B = compound.u_2550_I("PushX");
        this.J_1907_R = compound.u_2550_I("PushZ");
        this.G_564_y = compound.v_4262_N("Fuel");
    }

    protected boolean Y_259_p() {
        return this.l_4537_E.n_1700_B(R_4764_Y);
    }

    protected void R_4764_Y(boolean powered) {
        this.l_4537_E.J_1907_R(R_4764_Y, powered);
    }

    @Override
    public K_4074_S M_182_A() {
        return (K_4074_S)((K_4074_S)a_3742_W.P_925_e.multiplayerClientSuggestionProvider().n_1700_B(A_3138_X.P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(A_3138_X.h_1847_R, this.Y_259_p());
    }
}


