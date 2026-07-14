/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.c_2086_l;

public final class U_1697_c {
    private U_1697_c() {
    }

    public static String n_1700_B(String key) {
        String value = c_2086_l.J_1907_R(key);
        return value != null && !value.isEmpty() ? value : key;
    }

    public static void J_1907_R(String code) {
        c_2086_l.n_1700_B(code);
    }

    public static String n_1700_B() {
        c_2086_l.n_1700_B current = c_2086_l.n_1700_B();
        return current != null ? current.n_1700_B() : "ru";
    }
}

