/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.P_11_z;
import lightning.product.b_2971_b;
import lightning.product.h_384_L;
import lightning.product.AbstractDragonPhaseInstance;

public abstract class AbstractDragonSittingPhase
extends AbstractDragonPhaseInstance {
    public AbstractDragonSittingPhase(b_2971_b p_i46794_1_) {
        super(p_i46794_1_);
    }

    @Override
    public boolean u_() {
        return true;
    }

    @Override
    public float n_1700_B(P_11_z p_221113_1_, float p_221113_2_) {
        if (p_221113_1_.s_956_w() instanceof h_384_L) {
            p_221113_1_.s_956_w().P_1922_E(1);
            return 0.0f;
        }
        return super.n_1700_B(p_221113_1_, p_221113_2_);
    }
}


