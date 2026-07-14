/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AttributeMap;
import lightning.product.g_422_i;
import lightning.product.j_956_y;
import lightning.product.r_4811_B;

public class AbsoptionMobEffect
extends g_422_i {
    protected AbsoptionMobEffect(j_956_y type, int liquidColor) {
        super(type, liquidColor);
    }

    @Override
    public void n_1700_B(r_4811_B entityLivingBaseIn, AttributeMap attributeMapIn, int amplifier) {
        entityLivingBaseIn.Y_259_p(entityLivingBaseIn.U_3823_u() - (float)(4 * (amplifier + 1)));
        super.n_1700_B(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public void J_1907_R(r_4811_B entityLivingBaseIn, AttributeMap attributeMapIn, int amplifier) {
        entityLivingBaseIn.Y_259_p(entityLivingBaseIn.U_3823_u() + (float)(4 * (amplifier + 1)));
        super.J_1907_R(entityLivingBaseIn, attributeMapIn, amplifier);
    }
}


