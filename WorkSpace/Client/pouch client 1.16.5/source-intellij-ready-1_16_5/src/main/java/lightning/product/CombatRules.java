/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.u_530_F;

public class CombatRules {
    public static float n_1700_B(float damage, float totalArmor, float toughnessAttribute) {
        float f = 2.0f + toughnessAttribute / 4.0f;
        float f1 = u_530_F.n_1700_B(totalArmor - damage / f, totalArmor * 0.2f, 20.0f);
        return damage * (1.0f - f1 / 25.0f);
    }

    public static float n_1700_B(float damage, float enchantModifiers) {
        float f = u_530_F.n_1700_B(enchantModifiers, 0.0f, 20.0f);
        return damage * (1.0f - f / 25.0f);
    }
}


