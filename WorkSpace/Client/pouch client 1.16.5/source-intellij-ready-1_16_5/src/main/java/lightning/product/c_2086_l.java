/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.HashMap;
import java.util.Map;
import lightning.product.V_537_k;
import lombok.Generated;

public class c_2086_l {
    private static Map<String, String> n_1700_B = new HashMap<String, String>();
    private static n_1700_B J_1907_R = lightning.product.c_2086_l$n_1700_B.n_1700_B;

    public static void n_1700_B(String code) {
        n_1700_B lang = "ru".equalsIgnoreCase(code) ? lightning.product.c_2086_l$n_1700_B.n_1700_B : ("en".equalsIgnoreCase(code) ? lightning.product.c_2086_l$n_1700_B.J_1907_R : ("uk".equalsIgnoreCase(code) ? lightning.product.c_2086_l$n_1700_B.R_4764_Y : lightning.product.c_2086_l$n_1700_B.n_1700_B));
        n_1700_B = new HashMap<String, String>(V_537_k.n_1700_B(lang.J_1907_R()));
        J_1907_R = lang;
    }

    public static String J_1907_R(String key) {
        if (key == null) {
            return null;
        }
        if (n_1700_B == null || n_1700_B.isEmpty()) {
            c_2086_l.n_1700_B(J_1907_R.n_1700_B());
        }
        return n_1700_B.get(key);
    }

    public static n_1700_B n_1700_B() {
        return J_1907_R;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("ru", "assets/minecraft/Pouch/locale/ru.lang");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("en", "assets/minecraft/Pouch/locale/eng.lang");
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("uk", "assets/minecraft/Pouch/locale/uk.lang");
        private final String G_564_y;
        private final String P_1922_E;
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String code, String resourcePath) {
            this.G_564_y = code;
            this.P_1922_E = resourcePath;
        }

        @Generated
        public String n_1700_B() {
            return this.G_564_y;
        }

        @Generated
        public String J_1907_R() {
            return this.P_1922_E;
        }

        private static /* synthetic */ n_1700_B[] R_4764_Y() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            u_1723_Y = lightning.product.c_2086_l$n_1700_B.R_4764_Y();
        }
    }
}

