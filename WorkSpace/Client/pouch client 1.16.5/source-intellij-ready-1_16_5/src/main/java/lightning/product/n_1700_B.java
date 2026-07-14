/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.UUID;
import lightning.product.M_182_A;
import lightning.product.Q_2552_b;
import lightning.product.Z_875_P;
import lightning.product.c_3005_b;
import lightning.product.k_2293_S;
import lightning.product.t_1786_h;
import lightning.product.u_1723_Y;
import lombok.Generated;

public class n_1700_B {
    public t_1786_h n_1700_B;
    public c_3005_b J_1907_R;
    public Z_875_P R_4764_Y;
    public k_2293_S G_564_y;
    public M_182_A P_1922_E;
    public Q_2552_b u_1723_Y;
    public String v_4262_N;
    public boolean w_1484_f;
    public boolean t_148_a;
    public long s_956_w = 0L;
    public final UUID u_2550_I;
    public u_1723_Y M_588_G;
    public boolean P_4830_p;
    public int h_1847_R = 100;

    public n_1700_B(t_1786_h botNetwork, c_3005_b botWorld, Z_875_P botPlayer, k_2293_S botController, M_182_A botClientPlayNetHandler) {
        this.n_1700_B = botNetwork;
        this.J_1907_R = botWorld;
        this.R_4764_Y = botPlayer;
        this.G_564_y = botController;
        this.P_1922_E = botClientPlayNetHandler;
        this.u_2550_I = UUID.randomUUID();
        this.u_1723_Y = new Q_2552_b(this);
        if (botPlayer != null) {
            System.out.println("Bot created: " + botPlayer.O_1309_Q().getString());
        }
    }

    public void n_1700_B() {
        if (this.M_588_G != null) {
            this.M_588_G.n_1700_B(this);
        }
        if (this.u_1723_Y != null) {
            this.u_1723_Y.P_1922_E();
        }
    }

    @Generated
    public void n_1700_B(u_1723_Y behavior) {
        this.M_588_G = behavior;
    }
}

