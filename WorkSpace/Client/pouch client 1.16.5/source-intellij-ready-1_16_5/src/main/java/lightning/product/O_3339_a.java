/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.io.IOException;
import lightning.product.X_933_l;
import lightning.product.c_4037_x;
import lightning.product.y_3193_B;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class O_3339_a {
    private static final Logger n_1700_B = LogManager.getLogger();

    public static void n_1700_B(int p_227804_0_) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        X_933_l.v_4262_N(p_227804_0_);
    }

    public static void n_1700_B(y_3193_B p_148077_0_) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        p_148077_0_.G_564_y().n_1700_B();
        p_148077_0_.R_4764_Y().n_1700_B();
        X_933_l.w_1484_f(p_148077_0_.n_1700_B());
    }

    public static int n_1700_B() throws IOException {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        int i = X_933_l.M_182_A();
        if (i <= 0) {
            throw new IOException("Could not create shader program (returned program ID " + i + ")");
        }
        return i;
    }

    public static void J_1907_R(y_3193_B p_148075_0_) throws IOException {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        p_148075_0_.G_564_y().n_1700_B(p_148075_0_);
        p_148075_0_.R_4764_Y().n_1700_B(p_148075_0_);
        X_933_l.t_148_a(p_148075_0_.n_1700_B());
        int i = X_933_l.R_4764_Y(p_148075_0_.n_1700_B(), 35714);
        if (i == 0) {
            n_1700_B.warn("Error encountered when linking program containing VS {} and FS {}. Log output:", (Object)p_148075_0_.R_4764_Y().J_1907_R(), (Object)p_148075_0_.G_564_y().J_1907_R());
            n_1700_B.warn(X_933_l.s_956_w(p_148075_0_.n_1700_B(), 32768));
        }
    }
}

