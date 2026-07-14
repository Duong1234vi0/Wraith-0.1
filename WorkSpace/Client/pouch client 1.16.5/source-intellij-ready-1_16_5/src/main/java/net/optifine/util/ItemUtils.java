/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.q_1613_l;

public class ItemUtils {
    public static q_1613_l getItem(g_2336_b loc) {
        return !V_3137_a.e_2887_G.R_4764_Y(loc) ? null : V_3137_a.e_2887_G.n_1700_B(loc);
    }

    public static int getId(q_1613_l item) {
        return V_3137_a.e_2887_G.n_1700_B(item);
    }
}

