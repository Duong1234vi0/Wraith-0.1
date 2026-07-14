/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SoundEvents;
import lightning.product.Z_1164_j;
import lightning.product.AbstractDragonSittingPhase;
import lightning.product.b_2971_b;

public class DragonSittingAttackingPhase
extends AbstractDragonSittingPhase {
    private int J_1907_R;

    public DragonSittingAttackingPhase(b_2971_b dragonIn) {
        super(dragonIn);
    }

    @Override
    public void n_1700_B() {
        this.n_1700_B.O_508_d.n_1700_B(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k(), SoundEvents.z_2025_Z, this.n_1700_B.r_2478_U(), 2.5f, 0.8f + this.n_1700_B.M_3508_C().nextFloat() * 0.3f, false);
    }

    @Override
    public void J_1907_R() {
        if (this.J_1907_R++ >= 40) {
            this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.u_1723_Y);
        }
    }

    @Override
    public void R_4764_Y() {
        this.J_1907_R = 0;
    }

    public Z_1164_j<DragonSittingAttackingPhase> G_564_y() {
        return Z_1164_j.w_1484_f;
    }
}


