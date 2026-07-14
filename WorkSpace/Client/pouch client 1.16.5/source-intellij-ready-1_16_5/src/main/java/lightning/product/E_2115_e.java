/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.File;
import lightning.product.t_4864_b;

public final class E_2115_e {
    private static final File n_1700_B = new File("C:/Pouch/neuro");
    private static final File J_1907_R = new File(n_1700_B, "models");
    private static final File R_4764_Y = new File(n_1700_B, "data");

    private E_2115_e() {
    }

    public static synchronized void n_1700_B() {
        if (!n_1700_B.exists()) {
            n_1700_B.mkdirs();
        }
        if (!J_1907_R.exists()) {
            J_1907_R.mkdirs();
        }
        if (!R_4764_Y.exists()) {
            R_4764_Y.mkdirs();
        }
        t_4864_b.n_1700_B();
    }

    public static boolean J_1907_R() {
        return t_4864_b.J_1907_R();
    }

    public static File R_4764_Y() {
        if (!J_1907_R.exists()) {
            J_1907_R.mkdirs();
        }
        return J_1907_R;
    }

    public static File G_564_y() {
        if (!R_4764_Y.exists()) {
            R_4764_Y.mkdirs();
        }
        return R_4764_Y;
    }
}

