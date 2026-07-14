/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Y_1740_V;
import lightning.product.a_178_J;
import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;
import lightning.product.f_1574_f;
import lightning.product.h_1015_G;
import lightning.product.x_1688_C;

public class k_2348_i
implements MinecraftAccess {
    private static final int n_1700_B = 1;
    private static n_1700_B J_1907_R = lightning.product.k_2348_i$n_1700_B.n_1700_B;
    private static int R_4764_Y;
    private static int G_564_y;
    private static int P_1922_E;
    private static boolean u_1723_Y;
    private static boolean v_4262_N;
    private static int w_1484_f;
    private static int t_148_a;
    private static int s_956_w;
    private static J_1907_R u_2550_I;
    private static Object M_588_G;

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        if (k_2348_i.G_564_y()) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            e.P_1922_E(false);
        }
    }

    @Y_1740_V
    public void n_1700_B(f_1574_f e) {
        if (k_2348_i.G_564_y()) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (J_1907_R == lightning.product.k_2348_i$n_1700_B.J_1907_R && --R_4764_Y <= 0) {
            J_1907_R = lightning.product.k_2348_i$n_1700_B.R_4764_Y;
        }
        this.n_1700_B();
        this.J_1907_R();
    }

    private void n_1700_B() {
        if (!u_1723_Y || --G_564_y > 0) {
            return;
        }
        k_2348_i.R_4764_Y();
        if (P_1922_E >= 0) {
            k_2348_i.c_3005_b.w_1457_N.syncCurrentPlayItem();
            k_2348_i.c_3005_b.Y_259_p.l_1268_F.G_564_y = P_1922_E;
        }
        k_2348_i.P_1922_E();
    }

    private void J_1907_R() {
        if (!v_4262_N || k_2348_i.c_3005_b.Y_259_p == null || k_2348_i.c_3005_b.w_1457_N == null || --w_1484_f > 0) {
            return;
        }
        switch (u_2550_I.ordinal()) {
            case 0: {
                s_956_w = k_2348_i.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                k_2348_i.c_3005_b.w_1457_N.windowClick(0, t_148_a, s_956_w, a_408_T.R_4764_Y, k_2348_i.c_3005_b.Y_259_p);
                u_2550_I = lightning.product.k_2348_i$J_1907_R.J_1907_R;
                k_2348_i.c_3005_b.Y_259_p.P_1922_E();
                k_2348_i.u_1723_Y();
                break;
            }
            case 1: {
                k_2348_i.R_4764_Y();
                u_2550_I = lightning.product.k_2348_i$J_1907_R.R_4764_Y;
                k_2348_i.u_1723_Y();
                break;
            }
            case 2: {
                k_2348_i.c_3005_b.w_1457_N.windowClick(0, t_148_a, s_956_w, a_408_T.R_4764_Y, k_2348_i.c_3005_b.Y_259_p);
                k_2348_i.c_3005_b.Y_259_p.P_1922_E();
                k_2348_i.v_4262_N();
            }
        }
    }

    private static void R_4764_Y() {
        k_2348_i.c_3005_b.w_1457_N.processRightClick(k_2348_i.c_3005_b.Y_259_p, k_2348_i.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
        k_2348_i.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
    }

    private static boolean G_564_y() {
        return J_1907_R != lightning.product.k_2348_i$n_1700_B.n_1700_B || v_4262_N;
    }

    public static void n_1700_B(Object owner) {
        if (M_588_G != null && M_588_G != owner) {
            return;
        }
        M_588_G = owner;
        J_1907_R = lightning.product.k_2348_i$n_1700_B.J_1907_R;
        R_4764_Y = 1;
    }

    public static boolean J_1907_R(Object owner) {
        return J_1907_R == lightning.product.k_2348_i$n_1700_B.R_4764_Y && M_588_G == owner;
    }

    public static void R_4764_Y(Object owner) {
        if (M_588_G != owner) {
            return;
        }
        J_1907_R = lightning.product.k_2348_i$n_1700_B.n_1700_B;
        R_4764_Y = 0;
        M_588_G = null;
        if (k_2348_i.c_3005_b.Y_259_p != null) {
            k_2348_i.c_3005_b.Y_259_p.P_1922_E();
        }
    }

    public static void n_1700_B(Object owner, int slot) {
        if (k_2348_i.c_3005_b.Y_259_p == null || k_2348_i.c_3005_b.w_1457_N == null) {
            return;
        }
        if (M_588_G != null && M_588_G != owner) {
            return;
        }
        M_588_G = owner;
        P_1922_E = k_2348_i.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        k_2348_i.c_3005_b.w_1457_N.syncCurrentPlayItem();
        k_2348_i.c_3005_b.Y_259_p.l_1268_F.G_564_y = slot;
        u_1723_Y = true;
        G_564_y = 1;
    }

    public static void J_1907_R(Object owner, int slotId) {
        if (k_2348_i.c_3005_b.Y_259_p == null || k_2348_i.c_3005_b.w_1457_N == null) {
            return;
        }
        if (M_588_G != null && M_588_G != owner) {
            return;
        }
        M_588_G = owner;
        v_4262_N = true;
        u_2550_I = lightning.product.k_2348_i$J_1907_R.n_1700_B;
        k_2348_i.u_1723_Y();
        t_148_a = slotId;
    }

    private static void P_1922_E() {
        u_1723_Y = false;
        G_564_y = 0;
        P_1922_E = -1;
        k_2348_i.w_1484_f();
    }

    private static void u_1723_Y() {
        w_1484_f = 1;
    }

    private static void v_4262_N() {
        v_4262_N = false;
        u_2550_I = lightning.product.k_2348_i$J_1907_R.n_1700_B;
        w_1484_f = 0;
        s_956_w = -1;
        t_148_a = -1;
        k_2348_i.w_1484_f();
    }

    private static void w_1484_f() {
        if (!u_1723_Y && !v_4262_N && J_1907_R == lightning.product.k_2348_i$n_1700_B.n_1700_B) {
            M_588_G = null;
        }
    }

    static {
        P_1922_E = -1;
        t_148_a = -1;
        s_956_w = -1;
        u_2550_I = lightning.product.k_2348_i$J_1907_R.n_1700_B;
        M_588_G = null;
    }

    private static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.k_2348_i$n_1700_B.n_1700_B();
        }
    }

    private static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] G_564_y;

        public static J_1907_R[] values() {
            return (J_1907_R[])G_564_y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.k_2348_i$J_1907_R.n_1700_B();
        }
    }
}


