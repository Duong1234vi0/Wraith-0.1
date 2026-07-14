/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.A_2352_Z;
import lightning.product.I_408_V;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.W_3491_f;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.n_1494_c;
import lightning.product.s_3548_s;
import lightning.product.t_5_h;
import lightning.product.w_748_f;
import lightning.product.y_4319_k;
import lightning.product.z_1477_l;
import lightning.product.Hopper;

public class MinecartHopper
extends s_3548_s
implements Hopper {
    private boolean G_564_y = true;
    private int P_1922_E = -1;
    private final c_1514_x u_1723_Y = c_1514_x.ZERO;

    public MinecartHopper(t_5_h<? extends MinecartHopper> type, b_4507_u worldIn) {
        super(type, worldIn);
    }

    public MinecartHopper(b_4507_u worldIn, double x, double y, double z) {
        super(t_5_h.N_2525_X, x, y, z, worldIn);
    }

    @Override
    public y_4319_k.n_1700_B h_1847_R() {
        return y_4319_k.n_1700_B.u_1723_Y;
    }

    @Override
    public K_4074_S M_182_A() {
        return a_3742_W.p_3749_n.multiplayerClientSuggestionProvider();
    }

    @Override
    public int w_1457_N() {
        return 1;
    }

    @Override
    public int Y_259_p() {
        return 5;
    }

    @Override
    public void n_1700_B(int x, int y, int z, boolean receivingPower) {
        boolean flag;
        boolean bl = flag = !receivingPower;
        if (flag != this.k_2293_S()) {
            this.R_4764_Y(flag);
        }
    }

    public boolean k_2293_S() {
        return this.G_564_y;
    }

    public void R_4764_Y(boolean blocked) {
        this.G_564_y = blocked;
    }

    @Override
    public b_4507_u c_3005_b() {
        return this.O_508_d;
    }

    @Override
    public double H_2857_Y() {
        return this.O_3598_v();
    }

    @Override
    public double A_4115_X() {
        return this.X_2960_b() + 0.5;
    }

    @Override
    public double Y_1740_V() {
        return this.l_2647_k();
    }

    @Override
    public void v_() {
        super.v_();
        if (!this.O_508_d.Y_259_p && this.RealmsLongRunningMcoTaskScreen() && this.k_2293_S()) {
            c_1514_x blockpos = this.b_2312_j();
            if (blockpos.equals(this.u_1723_Y)) {
                --this.P_1922_E;
            } else {
                this.M_588_G(0);
            }
            if (!this.e_4240_b()) {
                this.M_588_G(0);
                if (this.t_4043_B()) {
                    this.M_588_G(4);
                    this.J_1907_R();
                }
            }
        }
    }

    public boolean t_4043_B() {
        if (w_748_f.n_1700_B(this)) {
            return true;
        }
        List<N_4263_v> list = this.O_508_d.n_1700_B(n_1494_c.class, this.i_601_W().grow(0.25, 0.0, 0.25), I_408_V.n_1700_B);
        if (!list.isEmpty()) {
            w_748_f.n_1700_B((Container)this, (n_1494_c)list.get(0));
        }
        return false;
    }

    @Override
    public void J_1907_R(P_11_z source) {
        super.J_1907_R(source);
        if (this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
            this.n_1700_B(a_3742_W.p_3749_n);
        }
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("TransferCooldown", this.P_1922_E);
        compound.n_1700_B("Enabled", this.G_564_y);
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.P_1922_E = compound.w_1484_f("TransferCooldown");
        this.G_564_y = compound.P_1922_E("Enabled") ? compound.t_1786_h("Enabled") : true;
    }

    public void M_588_G(int transferTickerIn) {
        this.P_1922_E = transferTickerIn;
    }

    public boolean e_4240_b() {
        return this.P_1922_E > 0;
    }

    @Override
    public a_2900_S n_1700_B(int id, W_3491_f playerInventoryIn) {
        return new z_1477_l(id, playerInventoryIn, this);
    }
}


