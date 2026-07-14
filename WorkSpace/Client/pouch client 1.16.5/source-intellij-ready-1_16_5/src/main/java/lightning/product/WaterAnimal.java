/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_1869_h;
import lightning.product.P_11_z;
import lightning.product.T_1316_M;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.MobType;
import lightning.product.PathfinderMob;
import lightning.product.t_5_h;

public abstract class WaterAnimal
extends PathfinderMob {
    protected WaterAnimal(t_5_h<? extends WaterAnimal> type, b_4507_u p_i48565_2_) {
        super((t_5_h<? extends PathfinderMob>)type, p_i48565_2_);
        this.n_1700_B(I_1869_h.w_1484_f, 0.0f);
    }

    @Override
    public boolean P_328_a() {
        return true;
    }

    @Override
    public MobType F_2860_q() {
        return MobType.P_1922_E;
    }

    @Override
    public boolean n_1700_B(T_1316_M worldIn) {
        return worldIn.P_1922_E(this);
    }

    @Override
    public int v_4276_D() {
        return 120;
    }

    @Override
    protected int R_4764_Y(a_3913_L player) {
        return 1 + this.O_508_d.w_1457_N.nextInt(3);
    }

    protected void n_1700_B(int p_209207_1_) {
        if (this.RealmsLongRunningMcoTaskScreen() && !this.S_980_j()) {
            this.w_1484_f(p_209207_1_ - 1);
            if (this.L_4248_u() == -20) {
                this.w_1484_f(0);
                this.n_1700_B(P_11_z.w_1484_f, 2.0f);
            }
        } else {
            this.w_1484_f(300);
        }
    }

    @Override
    public void V_1446_Y() {
        int i = this.L_4248_u();
        super.V_1446_Y();
        this.n_1700_B(i);
    }

    @Override
    public boolean Y_776_s() {
        return false;
    }

    @Override
    public boolean G_564_y(a_3913_L player) {
        return false;
    }
}


