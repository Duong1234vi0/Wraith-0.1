/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AttributeMap;
import lightning.product.g_422_i;
import lightning.product.j_956_y;
import lightning.product.r_4811_B;

public class HealthBoostMobEffect
extends g_422_i {
    public HealthBoostMobEffect(j_956_y type, int liquidColor) {
        super(type, liquidColor);
    }

    @Override
    public void n_1700_B(r_4811_B entityLivingBaseIn, AttributeMap attributeMapIn, int amplifier) {
        super.n_1700_B(entityLivingBaseIn, attributeMapIn, amplifier);
        if (entityLivingBaseIn.g_46_E() > entityLivingBaseIn.L_1733_J()) {
            entityLivingBaseIn.t_1786_h(entityLivingBaseIn.L_1733_J());
        }
    }
}


