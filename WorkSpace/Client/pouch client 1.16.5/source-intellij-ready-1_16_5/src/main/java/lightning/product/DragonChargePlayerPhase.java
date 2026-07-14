/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.Z_1164_j;
import lightning.product.b_2971_b;
import lightning.product.e_2866_D;
import lightning.product.AbstractDragonPhaseInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DragonChargePlayerPhase
extends AbstractDragonPhaseInstance {
    private static final Logger J_1907_R = LogManager.getLogger();
    private e_2866_D R_4764_Y;
    private int G_564_y;

    public DragonChargePlayerPhase(b_2971_b dragonIn) {
        super(dragonIn);
    }

    @Override
    public void J_1907_R() {
        if (this.R_4764_Y == null) {
            J_1907_R.warn("Aborting charge player as no target was set.");
            this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.n_1700_B);
        } else if (this.G_564_y > 0 && this.G_564_y++ >= 10) {
            this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.n_1700_B);
        } else {
            double d0 = this.R_4764_Y.R_4764_Y(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k());
            if (d0 < 100.0 || d0 > 22500.0 || this.n_1700_B.D_60_a || this.n_1700_B.k_3961_g) {
                ++this.G_564_y;
            }
        }
    }

    @Override
    public void R_4764_Y() {
        this.R_4764_Y = null;
        this.G_564_y = 0;
    }

    public void n_1700_B(e_2866_D p_188668_1_) {
        this.R_4764_Y = p_188668_1_;
    }

    @Override
    public float P_1922_E() {
        return 3.0f;
    }

    @Override
    @Nullable
    public e_2866_D u_1723_Y() {
        return this.R_4764_Y;
    }

    public Z_1164_j<DragonChargePlayerPhase> G_564_y() {
        return Z_1164_j.t_148_a;
    }
}


