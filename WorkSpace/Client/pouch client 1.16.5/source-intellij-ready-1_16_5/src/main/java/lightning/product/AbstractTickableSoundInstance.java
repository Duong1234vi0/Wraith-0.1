/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.V_2473_P;
import lightning.product.SoundEvent;
import lightning.product.TickableSoundInstance;

public abstract class AbstractTickableSoundInstance
extends V_2473_P
implements TickableSoundInstance {
    private boolean n_1700_B;

    protected AbstractTickableSoundInstance(SoundEvent soundIn, D_38_f categoryIn) {
        super(soundIn, categoryIn);
    }

    @Override
    public boolean multiplayerClientSuggestionProvider() {
        return this.n_1700_B;
    }

    protected final void w_1457_N() {
        this.n_1700_B = true;
        this.s_956_w = false;
    }
}


