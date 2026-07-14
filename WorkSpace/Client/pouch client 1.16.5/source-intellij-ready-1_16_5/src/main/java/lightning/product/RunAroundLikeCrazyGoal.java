/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.N_4263_v;
import lightning.product.U_2534_D;
import lightning.product.W_3371_U;
import lightning.product.a_3913_L;
import lightning.product.e_2866_D;
import lightning.product.Goal;

public class RunAroundLikeCrazyGoal
extends Goal {
    private final U_2534_D n_1700_B;
    private final double J_1907_R;
    private double R_4764_Y;
    private double G_564_y;
    private double P_1922_E;

    public RunAroundLikeCrazyGoal(U_2534_D horse, double speedIn) {
        this.n_1700_B = horse;
        this.J_1907_R = speedIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        if (!this.n_1700_B.o_4117_e() && this.n_1700_B.H_1883_T()) {
            e_2866_D vector3d = W_3371_U.n_1700_B(this.n_1700_B, 5, 4);
            if (vector3d == null) {
                return false;
            }
            this.R_4764_Y = vector3d.J_1907_R;
            this.G_564_y = vector3d.R_4764_Y;
            this.P_1922_E = vector3d.G_564_y;
            return true;
        }
        return false;
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.e_4240_b().n_1700_B(this.R_4764_Y, this.G_564_y, this.P_1922_E, this.J_1907_R);
    }

    @Override
    public boolean J_1907_R() {
        return !this.n_1700_B.o_4117_e() && !this.n_1700_B.e_4240_b().M_588_G() && this.n_1700_B.H_1883_T();
    }

    @Override
    public void P_1922_E() {
        if (!this.n_1700_B.o_4117_e() && this.n_1700_B.M_3508_C().nextInt(50) == 0) {
            N_4263_v entity = this.n_1700_B.o_3599_Z().get(0);
            if (entity == null) {
                return;
            }
            if (entity instanceof a_3913_L) {
                int i = this.n_1700_B.ModuleCategory();
                int j = this.n_1700_B.SoundEventRegistration();
                if (j > 0 && this.n_1700_B.M_3508_C().nextInt(j) < i) {
                    this.n_1700_B.w_1484_f((a_3913_L)entity);
                    return;
                }
                this.n_1700_B.Q_2552_b(5);
            }
            this.n_1700_B.C_3538_G();
            this.n_1700_B.c_1608_O();
            this.n_1700_B.O_508_d.n_1700_B((N_4263_v)this.n_1700_B, (byte)6);
        }
    }
}



