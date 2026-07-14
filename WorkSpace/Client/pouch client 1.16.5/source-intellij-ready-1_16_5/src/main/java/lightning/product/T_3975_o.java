/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.E_4700_p;

public class T_3975_o {

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R();
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R();
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R();
        public static final /* enum */ J_1907_R u_1723_Y = new J_1907_R();
        public static final /* enum */ J_1907_R v_4262_N = new J_1907_R();
        public static final /* enum */ J_1907_R w_1484_f = new J_1907_R();
        public static final /* enum */ J_1907_R t_148_a = new J_1907_R();
        public static final /* enum */ J_1907_R s_956_w = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] u_2550_I;

        public static J_1907_R[] values() {
            return (J_1907_R[])u_2550_I.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w};
        }

        static {
            u_2550_I = lightning.product.T_3975_o$J_1907_R.n_1700_B();
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B>
    implements E_4700_p {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("air");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("liquid");
        public static final Codec<n_1700_B> R_4764_Y;
        private static final Map<String, n_1700_B> G_564_y;
        private final String P_1922_E;
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String name) {
            this.P_1922_E = name;
        }

        public String J_1907_R() {
            return this.P_1922_E;
        }

        @Nullable
        public static n_1700_B n_1700_B(String p_236075_0_) {
            return G_564_y.get(p_236075_0_);
        }

        @Override
        public String n_1700_B() {
            return this.P_1922_E;
        }

        private static /* synthetic */ n_1700_B[] R_4764_Y() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            u_1723_Y = lightning.product.T_3975_o$n_1700_B.R_4764_Y();
            R_4764_Y = E_4700_p.n_1700_B(n_1700_B::values, n_1700_B::n_1700_B);
            G_564_y = Arrays.stream(lightning.product.T_3975_o$n_1700_B.values()).collect(Collectors.toMap(n_1700_B::J_1907_R, p_222672_0_ -> p_222672_0_));
        }
    }
}

