/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_1880_G;
import lightning.product.g_422_i;
import lightning.product.j_956_y;

public class AttackDamageMobEffect
extends g_422_i {
    protected final double n_1700_B;

    protected AttackDamageMobEffect(j_956_y type, int liquidColor, double bonusPerLevel) {
        super(type, liquidColor);
        this.n_1700_B = bonusPerLevel;
    }

    @Override
    public double n_1700_B(int amplifier, U_1880_G modifier) {
        return this.n_1700_B * (double)(amplifier + 1);
    }
}


