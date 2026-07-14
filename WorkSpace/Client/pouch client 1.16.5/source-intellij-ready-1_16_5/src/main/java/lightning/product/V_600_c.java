/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import lightning.product.Z_1164_j;
import lightning.product.b_2971_b;
import lightning.product.DragonPhaseInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class V_600_c {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final b_2971_b J_1907_R;
    private final DragonPhaseInstance[] R_4764_Y = new DragonPhaseInstance[Z_1164_j.R_4764_Y()];
    private DragonPhaseInstance G_564_y;

    public V_600_c(b_2971_b dragonIn) {
        this.J_1907_R = dragonIn;
        this.n_1700_B(Z_1164_j.u_2550_I);
    }

    public void n_1700_B(Z_1164_j<?> phaseIn) {
        if (this.G_564_y == null || phaseIn != this.G_564_y.G_564_y()) {
            if (this.G_564_y != null) {
                this.G_564_y.v_4262_N();
            }
            this.G_564_y = this.J_1907_R(phaseIn);
            if (!this.J_1907_R.O_508_d.Y_259_p) {
                this.J_1907_R.D_60_a().J_1907_R(b_2971_b.n_1700_B, phaseIn.J_1907_R());
            }
            n_1700_B.debug("Dragon is now in phase {} on the {}", phaseIn, (Object)(this.J_1907_R.O_508_d.Y_259_p ? "client" : "server"));
            this.G_564_y.R_4764_Y();
        }
    }

    public DragonPhaseInstance n_1700_B() {
        return this.G_564_y;
    }

    public <T extends DragonPhaseInstance> T J_1907_R(Z_1164_j<T> phaseIn) {
        int i = phaseIn.J_1907_R();
        if (this.R_4764_Y[i] == null) {
            this.R_4764_Y[i] = phaseIn.n_1700_B(this.J_1907_R);
        }
        return (T)this.R_4764_Y[i];
    }
}


