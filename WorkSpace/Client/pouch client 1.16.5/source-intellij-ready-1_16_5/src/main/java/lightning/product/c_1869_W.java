/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.K_4074_S;
import lightning.product.U_2912_j;
import lightning.product.X_1924_A;
import lightning.product.a_3913_L;
import lightning.product.Nameable;
import lightning.product.i_2154_H;
import lightning.product.BlockEntityType;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class c_1869_W
extends i_2154_H
implements X_1924_A,
Nameable {
    public int n_1700_B;
    public float J_1907_R;
    public float R_4764_Y;
    public float G_564_y;
    public float P_1922_E;
    public float u_1723_Y;
    public float v_4262_N;
    public float w_1484_f;
    public float t_148_a;
    public float s_956_w;
    private static final Random h_1847_R = new Random();
    private x_282_a Q_4569_t;

    public c_1869_W() {
        super(BlockEntityType.M_588_G);
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.t_3452_g()) {
            compound.n_1700_B("CustomName", x_282_a.n_1700_B.n_1700_B(this.Q_4569_t));
        }
        return compound;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        if (nbt.R_4764_Y("CustomName", 8)) {
            this.Q_4569_t = x_282_a.n_1700_B.n_1700_B(nbt.M_588_G("CustomName"));
        }
    }

    @Override
    public void P_1922_E() {
        float f2;
        this.v_4262_N = this.u_1723_Y;
        this.t_148_a = this.w_1484_f;
        a_3913_L playerentity = this.u_2550_I.n_1700_B((double)this.M_588_G.getX() + 0.5, (double)this.M_588_G.getY() + 0.5, (double)this.M_588_G.getZ() + 0.5, 3.0, false);
        if (playerentity != null) {
            double d0 = playerentity.O_3598_v() - ((double)this.M_588_G.getX() + 0.5);
            double d1 = playerentity.l_2647_k() - ((double)this.M_588_G.getZ() + 0.5);
            this.s_956_w = (float)u_530_F.G_564_y(d1, d0);
            this.u_1723_Y += 0.1f;
            if (this.u_1723_Y < 0.5f || h_1847_R.nextInt(40) == 0) {
                float f1 = this.G_564_y;
                do {
                    this.G_564_y += (float)(h_1847_R.nextInt(4) - h_1847_R.nextInt(4));
                } while (f1 == this.G_564_y);
            }
        } else {
            this.s_956_w += 0.02f;
            this.u_1723_Y -= 0.1f;
        }
        while (this.w_1484_f >= (float)Math.PI) {
            this.w_1484_f -= (float)Math.PI * 2;
        }
        while (this.w_1484_f < (float)(-Math.PI)) {
            this.w_1484_f += (float)Math.PI * 2;
        }
        while (this.s_956_w >= (float)Math.PI) {
            this.s_956_w -= (float)Math.PI * 2;
        }
        while (this.s_956_w < (float)(-Math.PI)) {
            this.s_956_w += (float)Math.PI * 2;
        }
        for (f2 = this.s_956_w - this.w_1484_f; f2 >= (float)Math.PI; f2 -= (float)Math.PI * 2) {
        }
        while (f2 < (float)(-Math.PI)) {
            f2 += (float)Math.PI * 2;
        }
        this.w_1484_f += f2 * 0.4f;
        this.u_1723_Y = u_530_F.n_1700_B(this.u_1723_Y, 0.0f, 1.0f);
        ++this.n_1700_B;
        this.R_4764_Y = this.J_1907_R;
        float f = (this.G_564_y - this.J_1907_R) * 0.4f;
        float f3 = 0.2f;
        f = u_530_F.n_1700_B(f, -0.2f, 0.2f);
        this.P_1922_E += (f - this.P_1922_E) * 0.9f;
        this.J_1907_R += this.P_1922_E;
    }

    @Override
    public x_282_a O_1309_Q() {
        return this.Q_4569_t != null ? this.Q_4569_t : new F_2904_S("container.enchant");
    }

    public void n_1700_B(@Nullable x_282_a name) {
        this.Q_4569_t = name;
    }

    @Override
    @Nullable
    public x_282_a k_2302_P() {
        return this.Q_4569_t;
    }
}


