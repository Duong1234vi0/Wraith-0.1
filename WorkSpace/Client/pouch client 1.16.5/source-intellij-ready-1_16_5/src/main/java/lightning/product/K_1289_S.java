/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.IllegalFormatException;
import lightning.product.l_4033_W;

public class K_1289_S {
    private static volatile l_4033_W n_1700_B = l_4033_W.R_4764_Y();

    static void n_1700_B(l_4033_W p_239502_0_) {
        n_1700_B = p_239502_0_;
    }

    public static String n_1700_B(String translateKey, Object ... parameters) {
        String s = n_1700_B.n_1700_B(translateKey);
        try {
            return String.format(s, parameters);
        }
        catch (IllegalFormatException illegalformatexception) {
            return "Format error: " + s;
        }
    }

    public static boolean n_1700_B(String key) {
        return n_1700_B.J_1907_R(key);
    }
}

