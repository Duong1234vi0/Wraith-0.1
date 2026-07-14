/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.util.function.IntConsumer;
import lightning.product.X_933_l;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A_1726_L {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final n_1700_B J_1907_R;
    private final J_1907_R R_4764_Y;
    private final int G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;
    private String v_4262_N;

    public A_1726_L(int indexIn, n_1700_B typeIn, J_1907_R usageIn, int count) {
        if (this.n_1700_B(indexIn, usageIn)) {
            this.R_4764_Y = usageIn;
        } else {
            n_1700_B.warn("Multiple vertex elements of the same type other than UVs are not supported. Forcing type to UV.");
            this.R_4764_Y = lightning.product.A_1726_L$J_1907_R.G_564_y;
        }
        this.J_1907_R = typeIn;
        this.G_564_y = indexIn;
        this.P_1922_E = count;
        this.u_1723_Y = typeIn.n_1700_B() * this.P_1922_E;
    }

    private boolean n_1700_B(int indexIn, J_1907_R usageIn) {
        return indexIn == 0 || usageIn == lightning.product.A_1726_L$J_1907_R.G_564_y;
    }

    public final n_1700_B n_1700_B() {
        return this.J_1907_R;
    }

    public final J_1907_R J_1907_R() {
        return this.R_4764_Y;
    }

    public final int R_4764_Y() {
        return this.G_564_y;
    }

    public String toString() {
        return this.v_4262_N != null ? this.v_4262_N : this.P_1922_E + "," + this.R_4764_Y.n_1700_B() + "," + this.J_1907_R.J_1907_R();
    }

    public final int G_564_y() {
        return this.u_1723_Y;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            A_1726_L vertexformatelement = (A_1726_L)p_equals_1_;
            if (this.P_1922_E != vertexformatelement.P_1922_E) {
                return false;
            }
            if (this.G_564_y != vertexformatelement.G_564_y) {
                return false;
            }
            if (this.J_1907_R != vertexformatelement.J_1907_R) {
                return false;
            }
            return this.R_4764_Y == vertexformatelement.R_4764_Y;
        }
        return false;
    }

    public int hashCode() {
        int i = this.J_1907_R.hashCode();
        i = 31 * i + this.R_4764_Y.hashCode();
        i = 31 * i + this.G_564_y;
        return 31 * i + this.P_1922_E;
    }

    public void n_1700_B(long pointerIn, int strideIn) {
        this.R_4764_Y.n_1700_B(this.P_1922_E, this.J_1907_R.R_4764_Y(), strideIn, pointerIn, this.G_564_y);
    }

    public void P_1922_E() {
        this.R_4764_Y.n_1700_B(this.G_564_y);
    }

    public final int u_1723_Y() {
        return this.P_1922_E;
    }

    public String v_4262_N() {
        return this.v_4262_N;
    }

    public void n_1700_B(String p_setName_1_) {
        this.v_4262_N = p_setName_1_;
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("Position", (p_lambda$static$0_0_, p_lambda$static$0_1_, p_lambda$static$0_2_, p_lambda$static$0_3_, p_lambda$static$0_5_) -> {
            X_933_l.J_1907_R(p_lambda$static$0_0_, p_lambda$static$0_1_, p_lambda$static$0_2_, p_lambda$static$0_3_);
            X_933_l.k_2293_S(32884);
        }, p_lambda$static$1_0_ -> X_933_l.q_2307_F(32884));
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("Normal", (p_lambda$static$2_0_, p_lambda$static$2_1_, p_lambda$static$2_2_, p_lambda$static$2_3_, p_lambda$static$2_5_) -> {
            X_933_l.n_1700_B(p_lambda$static$2_1_, p_lambda$static$2_2_, p_lambda$static$2_3_);
            X_933_l.k_2293_S(32885);
        }, p_lambda$static$3_0_ -> X_933_l.q_2307_F(32885));
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R("Vertex Color", (p_lambda$static$4_0_, p_lambda$static$4_1_, p_lambda$static$4_2_, p_lambda$static$4_3_, p_lambda$static$4_5_) -> {
            X_933_l.R_4764_Y(p_lambda$static$4_0_, p_lambda$static$4_1_, p_lambda$static$4_2_, p_lambda$static$4_3_);
            X_933_l.k_2293_S(32886);
        }, p_lambda$static$5_0_ -> {
            X_933_l.q_2307_F(32886);
            X_933_l.B_1668_F();
        });
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R("UV", (p_lambda$static$6_0_, p_lambda$static$6_1_, p_lambda$static$6_2_, p_lambda$static$6_3_, p_lambda$static$6_5_) -> {
            X_933_l.h_1847_R(33984 + p_lambda$static$6_5_);
            X_933_l.n_1700_B(p_lambda$static$6_0_, p_lambda$static$6_1_, p_lambda$static$6_2_, p_lambda$static$6_3_);
            X_933_l.k_2293_S(32888);
            X_933_l.h_1847_R(33984);
        }, p_lambda$static$7_0_ -> {
            X_933_l.h_1847_R(33984 + p_lambda$static$7_0_);
            X_933_l.q_2307_F(32888);
            X_933_l.h_1847_R(33984);
        });
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R("Padding", (p_lambda$static$8_0_, p_lambda$static$8_1_, p_lambda$static$8_2_, p_lambda$static$8_3_, p_lambda$static$8_5_) -> {}, p_lambda$static$9_0_ -> {});
        public static final /* enum */ J_1907_R u_1723_Y = new J_1907_R("Generic", (p_lambda$static$10_0_, p_lambda$static$10_1_, p_lambda$static$10_2_, p_lambda$static$10_3_, p_lambda$static$10_5_) -> {
            X_933_l.Z_875_P(p_lambda$static$10_5_);
            X_933_l.n_1700_B(p_lambda$static$10_5_, p_lambda$static$10_0_, p_lambda$static$10_1_, false, p_lambda$static$10_2_, p_lambda$static$10_3_);
        }, X_933_l::c_3005_b);
        private final String v_4262_N;
        private final n_1700_B w_1484_f;
        private final IntConsumer t_148_a;
        private static final /* synthetic */ J_1907_R[] s_956_w;

        public static J_1907_R[] values() {
            return (J_1907_R[])s_956_w.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(String displayNameIn, n_1700_B setupStateIn, IntConsumer clearStateIn) {
            this.v_4262_N = displayNameIn;
            this.w_1484_f = setupStateIn;
            this.t_148_a = clearStateIn;
        }

        private void n_1700_B(int countIn, int glTypeIn, int strideIn, long pointerIn, int indexIn) {
            this.w_1484_f.setupBufferState(countIn, glTypeIn, strideIn, pointerIn, indexIn);
        }

        public void n_1700_B(int indexIn) {
            this.t_148_a.accept(indexIn);
        }

        public String n_1700_B() {
            return this.v_4262_N;
        }

        private static /* synthetic */ J_1907_R[] J_1907_R() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            s_956_w = lightning.product.A_1726_L$J_1907_R.J_1907_R();
        }

        static interface n_1700_B {
            public void setupBufferState(int var1, int var2, int var3, long var4, int var6);
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(4, "Float", 5126);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(1, "Unsigned Byte", 5121);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(1, "Byte", 5120);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(2, "Unsigned Short", 5123);
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B(2, "Short", 5122);
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B(4, "Unsigned Int", 5125);
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B(4, "Int", 5124);
        private final int w_1484_f;
        private final String t_148_a;
        private final int s_956_w;
        private static final /* synthetic */ n_1700_B[] u_2550_I;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_2550_I.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(int sizeIn, String displayNameIn, int glConstantIn) {
            this.w_1484_f = sizeIn;
            this.t_148_a = displayNameIn;
            this.s_956_w = glConstantIn;
        }

        public int n_1700_B() {
            return this.w_1484_f;
        }

        public String J_1907_R() {
            return this.t_148_a;
        }

        public int R_4764_Y() {
            return this.s_956_w;
        }

        private static /* synthetic */ n_1700_B[] G_564_y() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            u_2550_I = lightning.product.A_1726_L$n_1700_B.G_564_y();
        }
    }
}

