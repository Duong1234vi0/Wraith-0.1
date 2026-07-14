/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import lightning.product.P_11_z;
import lightning.product.X_4340_E;
import lightning.product.MinecraftClient;
import lightning.product.c_3005_b;
import lightning.product.e_2866_D;
import lightning.product.k_4690_i;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class Q_1187_u
extends X_4340_E {
    public Q_1187_u(k_4690_i world, GameProfile profile) {
        super(world, profile);
        this.RealmsServerPing = 1.0f;
        this.j_1564_a = true;
    }

    public Q_1187_u(c_3005_b world, GameProfile profile) {
        super(world, profile);
        this.RealmsServerPing = 1.0f;
        this.j_1564_a = true;
    }

    @Override
    public boolean n_1700_B(double distance) {
        double d0 = this.i_601_W().getAverageEdgeLength() * 10.0;
        if (Double.isNaN(d0)) {
            d0 = 1.0;
        }
        return distance < (d0 = d0 * 64.0 * Q_1187_u.S_3139_t()) * d0;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        return true;
    }

    @Override
    public void v_() {
        super.v_();
        this.n_1700_B((r_4811_B)this, false);
    }

    @Override
    public void Y_1740_V() {
        if (this.O_1309_Q > 0) {
            double d0 = this.O_3598_v() + (this.O_2934_T - this.O_3598_v()) / (double)this.O_1309_Q;
            double d1 = this.X_2960_b() + (this.l_4088_R - this.X_2960_b()) / (double)this.O_1309_Q;
            double d2 = this.l_2647_k() + (this.Z_735_d - this.l_2647_k()) / (double)this.O_1309_Q;
            this.p_178_J = (float)((double)this.p_178_J + u_530_F.u_1723_Y(this.P_925_e - (double)this.p_178_J) / (double)this.O_1309_Q);
            this.f_4016_n = (float)((double)this.f_4016_n + (this.X_4895_T - (double)this.f_4016_n) / (double)this.O_1309_Q);
            --this.O_1309_Q;
            this.J_1907_R(d0, d1, d2);
            this.J_1907_R(this.p_178_J, this.f_4016_n);
        }
        if (this.n_3197_X > 0) {
            this.f_3449_S = (float)((double)this.f_3449_S + u_530_F.u_1723_Y(this.L_103_L - (double)this.f_3449_S) / (double)this.n_3197_X);
            --this.n_3197_X;
        }
        this.X_290_I = this.O_1795_e;
        this.k_3129_Y();
        float f1 = this.e_1992_r && !this.Z_2812_M() ? Math.min(0.1f, u_530_F.n_1700_B(Q_1187_u.R_4764_Y(this.I_4348_c()))) : 0.0f;
        if (!this.e_1992_r && !this.Z_2812_M()) {
            float f = (float)Math.atan(-this.I_4348_c().R_4764_Y * (double)0.2f) * 15.0f;
        } else {
            float f = 0.0f;
        }
        this.O_1795_e += (f1 - this.O_1795_e) * 0.4f;
        this.O_508_d.D_4792_h().n_1700_B("push");
        this.F_391_H();
        this.O_508_d.D_4792_h().R_4764_Y();
    }

    public void R_4764_Y() {
        this.S_3139_t = this.O_3598_v();
        this.k_2302_P = this.X_2960_b();
        this.t_3452_g = this.l_2647_k();
        assert (MinecraftClient.A_4115_X().Y_259_p != null);
        e_2866_D position = MinecraftClient.A_4115_X().Y_259_p.s_4990_V();
        e_2866_D from = new e_2866_D(this.d_2545_n, this.x_92_N, this.i_601_W);
        e_2866_D to = new e_2866_D(this.V_118_c, this.I_1407_m, this.o_2767_H);
        e_2866_D target = position.u_1723_Y(from) > position.u_1723_Y(to) ? to : from;
        this.J_1907_R(target.J_1907_R, target.R_4764_Y, target.G_564_y);
    }

    public void u_1723_Y() {
        if (this.k_2302_P != -999.0) {
            this.J_1907_R(this.S_3139_t, this.k_2302_P, this.t_3452_g);
            this.k_2302_P = -999.0;
        }
    }

    @Override
    protected void b_() {
    }

    @Override
    public void n_1700_B(x_282_a component, UUID senderUUID) {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        if (!minecraft.n_1700_B(senderUUID)) {
            minecraft.M_588_G.R_4764_Y().n_1700_B(component);
        }
    }
}



