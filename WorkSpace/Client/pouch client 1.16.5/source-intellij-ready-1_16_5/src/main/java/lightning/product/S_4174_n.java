/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Giant;
import lightning.product.AbstractZombieModel;

public class S_4174_n
extends AbstractZombieModel<Giant> {
    public S_4174_n() {
        this(0.0f, false);
    }

    public S_4174_n(float modelSize, boolean p_i51066_2_) {
        super(modelSize, 0.0f, 64, p_i51066_2_ ? 32 : 64);
    }

    @Override
    public boolean n_1700_B(Giant entityIn) {
        return false;
    }
}


