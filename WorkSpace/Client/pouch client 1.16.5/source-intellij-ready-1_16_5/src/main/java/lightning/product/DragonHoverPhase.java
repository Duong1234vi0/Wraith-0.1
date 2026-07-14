/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.Z_1164_j;
import lightning.product.b_2971_b;
import lightning.product.e_2866_D;
import lightning.product.AbstractDragonPhaseInstance;

public class DragonHoverPhase
extends AbstractDragonPhaseInstance {
    private e_2866_D J_1907_R;

    public DragonHoverPhase(b_2971_b dragonIn) {
        super(dragonIn);
    }

    @Override
    public void J_1907_R() {
        if (this.J_1907_R == null) {
            this.J_1907_R = this.n_1700_B.s_4990_V();
        }
    }

    @Override
    public boolean u_() {
        return true;
    }

    @Override
    public void R_4764_Y() {
        this.J_1907_R = null;
    }

    @Override
    public float P_1922_E() {
        return 1.0f;
    }

    @Override
    @Nullable
    public e_2866_D u_1723_Y() {
        return this.J_1907_R;
    }

    public Z_1164_j<DragonHoverPhase> G_564_y() {
        return Z_1164_j.u_2550_I;
    }
}


