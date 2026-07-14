/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import java.util.List;
import lightning.product.D_2364_U;
import lightning.product.I_4817_s;
import lightning.product.J_133_e;
import lightning.product.L_2225_p;
import lightning.product.a_3913_L;
import lightning.product.TargetingConditions;
import lightning.product.Goal;
import lightning.product.r_4811_B;

public class DefendVillageTargetGoal
extends J_133_e {
    private final D_2364_U n_1700_B;
    private r_4811_B J_1907_R;
    private final TargetingConditions R_4764_Y = new TargetingConditions().n_1700_B(64.0);

    public DefendVillageTargetGoal(D_2364_U ironGolemIn) {
        super(ironGolemIn, false, true);
        this.n_1700_B = ironGolemIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.G_564_y));
    }

    @Override
    public boolean n_1700_B() {
        I_4817_s axisalignedbb = this.n_1700_B.i_601_W().grow(10.0, 8.0, 10.0);
        List<L_2225_p> list = this.n_1700_B.O_508_d.n_1700_B(L_2225_p.class, this.R_4764_Y, this.n_1700_B, axisalignedbb);
        List<a_3913_L> list1 = this.n_1700_B.O_508_d.n_1700_B(this.R_4764_Y, this.n_1700_B, axisalignedbb);
        for (r_4811_B r_4811_B2 : list) {
            L_2225_p villagerentity = (L_2225_p)r_4811_B2;
            for (a_3913_L playerentity : list1) {
                int i = villagerentity.P_1922_E(playerentity);
                if (i > -100) continue;
                this.J_1907_R = playerentity;
            }
        }
        if (this.J_1907_R == null) {
            return false;
        }
        return !(this.J_1907_R instanceof a_3913_L) || !this.J_1907_R.d_2461_k() && !((a_3913_L)this.J_1907_R).G_624_v();
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.R_4764_Y(this.J_1907_R);
        super.R_4764_Y();
    }
}


