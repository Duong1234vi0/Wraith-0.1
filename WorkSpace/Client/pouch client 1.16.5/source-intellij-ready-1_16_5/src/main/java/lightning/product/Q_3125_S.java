/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_1045_N;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.q_4293_E;

public class Q_3125_S
extends V_1045_N {
    protected Q_3125_S(q_4293_E.P_1922_E properties) {
        super(true, properties);
    }

    @Override
    protected SoundEvent n_1700_B(boolean isOn) {
        return isOn ? SoundEvents.D_3746_J : SoundEvents.StandingSignBlock;
    }
}


