/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lightning.product.VillagerMetadataSectionSerializer;

public class K_2872_v {
    public static final VillagerMetadataSectionSerializer n_1700_B = new VillagerMetadataSectionSerializer();
    private final n_1700_B J_1907_R;

    public K_2872_v(n_1700_B p_i50904_1_) {
        this.J_1907_R = p_i50904_1_;
    }

    public n_1700_B n_1700_B() {
        return this.J_1907_R;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("none");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("partial");
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("full");
        private static final Map<String, n_1700_B> G_564_y;
        private final String P_1922_E;
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String p_i50447_3_) {
            this.P_1922_E = p_i50447_3_;
        }

        public String n_1700_B() {
            return this.P_1922_E;
        }

        public static n_1700_B n_1700_B(String p_217821_0_) {
            return G_564_y.getOrDefault(p_217821_0_, n_1700_B);
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            u_1723_Y = lightning.product.K_2872_v$n_1700_B.J_1907_R();
            G_564_y = Arrays.stream(lightning.product.K_2872_v$n_1700_B.values()).collect(Collectors.toMap(n_1700_B::n_1700_B, p_217822_0_ -> p_217822_0_));
        }
    }
}


