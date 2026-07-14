/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.g_2336_b;
import lightning.product.q_1613_l;

public class v_21_F
extends q_1613_l {
    private final int n_1700_B;
    private final String J_1907_R;

    public v_21_F(int armorValue, String tierArmor, q_1613_l.n_1700_B builder) {
        super(builder);
        this.n_1700_B = armorValue;
        this.J_1907_R = "textures/entity/horse/armor/horse_armor_" + tierArmor + ".png";
    }

    public g_2336_b v_4262_N() {
        return new g_2336_b(this.J_1907_R);
    }

    public int w_1484_f() {
        return this.n_1700_B;
    }
}

