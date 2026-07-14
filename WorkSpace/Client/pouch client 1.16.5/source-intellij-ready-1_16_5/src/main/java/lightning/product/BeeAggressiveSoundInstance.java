/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.SoundEvents;
import lightning.product.AbstractTickableSoundInstance;
import lightning.product.BeeSoundInstance;
import lightning.product.b_1913_J;
import lightning.product.BeeFlyingSoundInstance;

public class BeeAggressiveSoundInstance
extends BeeSoundInstance {
    public BeeAggressiveSoundInstance(b_1913_J entity) {
        super(entity, SoundEvents.dtoRealmsServerAddress, D_38_f.v_4262_N);
        this.u_2550_I = 0;
    }

    @Override
    protected AbstractTickableSoundInstance n_1700_B() {
        return new BeeFlyingSoundInstance(this.n_1700_B);
    }

    @Override
    protected boolean J_1907_R() {
        return !this.n_1700_B.B_();
    }
}


