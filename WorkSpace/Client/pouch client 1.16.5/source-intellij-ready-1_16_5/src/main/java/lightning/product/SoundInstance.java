/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.D_38_f;
import lightning.product.O_728_b;
import lightning.product.g_2336_b;
import lightning.product.k_4218_M;
import lightning.product.WeighedSoundEvents;

public interface SoundInstance {
    public g_2336_b u_1723_Y();

    @Nullable
    public WeighedSoundEvents n_1700_B(k_4218_M var1);

    public O_728_b v_4262_N();

    public D_38_f w_1484_f();

    public boolean t_148_a();

    public boolean s_956_w();

    public int u_2550_I();

    public float M_588_G();

    public float P_4830_p();

    public double h_1847_R();

    public double Q_4569_t();

    public double M_182_A();

    public n_1700_B t_1786_h();

    default public boolean G_564_y() {
        return false;
    }

    default public boolean P_1922_E() {
        return true;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.SoundInstance$n_1700_B.n_1700_B();
        }
    }
}


