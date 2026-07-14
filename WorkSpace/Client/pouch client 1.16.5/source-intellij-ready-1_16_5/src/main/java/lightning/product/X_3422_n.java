/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_1045_N;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.q_4293_E;

public class X_3422_n
extends V_1045_N {
    protected X_3422_n(q_4293_E.P_1922_E properties) {
        super(false, properties);
    }

    @Override
    protected SoundEvent n_1700_B(boolean isOn) {
        return isOn ? SoundEvents.BaseEntityBlock : SoundEvents.F_4905_S;
    }
}


