/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_4557_X;
import lightning.product.Objective;
import lightning.product.Y_1740_V;
import lightning.product.Slot;
import lightning.product.Z_3504_M;
import lightning.product.a_2900_S;
import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;
import lightning.product.h_1015_G;
import lightning.product.k_2603_m;
import lightning.product.p_1183_T;
import lightning.product.Items;
import lightning.product.u_1934_K;
import lightning.product.x_1688_C;
import lightning.product.z_3427_G;

public class h_3859_C
implements MinecraftAccess {
    private static final V_4557_X n_1700_B = new V_4557_X();
    private static n_1700_B J_1907_R = lightning.product.h_3859_C$n_1700_B.n_1700_B;
    private static int R_4764_Y = -1;
    private static Integer G_564_y = null;

    public static void n_1700_B() {
        if (h_3859_C.c_3005_b.Y_259_p == null) {
            return;
        }
        int n = R_4764_Y = G_564_y != null ? G_564_y : h_3859_C.J_1907_R();
        if (R_4764_Y <= 0) {
            return;
        }
        J_1907_R = lightning.product.h_3859_C$n_1700_B.J_1907_R;
        h_3859_C.c_3005_b.Y_259_p.n_1700_B("/hub");
        n_1700_B.n_1700_B();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (J_1907_R == lightning.product.h_3859_C$n_1700_B.n_1700_B) {
            return;
        }
        if (J_1907_R == lightning.product.h_3859_C$n_1700_B.J_1907_R && n_1700_B.J_1907_R(500L)) {
            this.G_564_y();
            J_1907_R = lightning.product.h_3859_C$n_1700_B.R_4764_Y;
            n_1700_B.n_1700_B();
            return;
        }
        if (J_1907_R == lightning.product.h_3859_C$n_1700_B.R_4764_Y && !(h_3859_C.c_3005_b.Y_1740_V instanceof z_3427_G)) {
            if (n_1700_B.J_1907_R(500L)) {
                this.G_564_y();
                n_1700_B.n_1700_B();
            }
            return;
        }
        k_2603_m k_2603_m2 = h_3859_C.c_3005_b.Y_1740_V;
        if (!(k_2603_m2 instanceof z_3427_G)) {
            return;
        }
        z_3427_G container = (z_3427_G)k_2603_m2;
        for (int i = 0; i < ((a_2900_S)container.n_()).P_1922_E.size(); ++i) {
            Slot slot = ((a_2900_S)container.n_()).P_1922_E.get(i);
            if (slot.n_1700_B().n_1700_B()) continue;
            if (J_1907_R == lightning.product.h_3859_C$n_1700_B.R_4764_Y && slot.n_1700_B().multiplayerClientSuggestionProvider().getString().contains("\u0413\u0420\u0418\u0424\u0415\u0420\u0421\u041a\u041e\u0415 \u0412\u042b\u0416\u0418\u0412\u0410\u041d\u0418\u0415 (1.16.5-1.20.4)")) {
                this.J_1907_R(i);
                J_1907_R = lightning.product.h_3859_C$n_1700_B.G_564_y;
                break;
            }
            if (J_1907_R != lightning.product.h_3859_C$n_1700_B.G_564_y || !slot.n_1700_B().multiplayerClientSuggestionProvider().getString().contains("\u0413\u0420\u0418\u0424 #" + R_4764_Y + " (1.16.5+)") || !n_1700_B.J_1907_R(50L)) continue;
            this.J_1907_R(i);
            h_3859_C.P_1922_E();
            break;
        }
    }

    private void G_564_y() {
        int slot = u_1934_K.n_1700_B(Items.X_1303_p);
        if (slot == -1 || h_3859_C.c_3005_b.Y_259_p == null) {
            return;
        }
        h_3859_C.c_3005_b.Y_259_p.l_1268_F.G_564_y = slot;
        h_3859_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(slot));
        h_3859_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
    }

    private void J_1907_R(int slotIndex) {
        h_3859_C.c_3005_b.w_1457_N.windowClick(h_3859_C.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, slotIndex, 0, a_408_T.n_1700_B, h_3859_C.c_3005_b.Y_259_p);
        n_1700_B.n_1700_B();
    }

    public static int J_1907_R() {
        if (h_3859_C.c_3005_b.Y_601_j == null) {
            return -1;
        }
        for (Objective team : h_3859_C.c_3005_b.Y_601_j.Q_4569_t().n_1700_B()) {
            String board = team.G_564_y().getString();
            if (!board.contains("\u0413\u0420\u0418\u0424 #")) continue;
            return Integer.parseInt(board.split("#")[1].replace(" ", ""));
        }
        return -1;
    }

    private static void P_1922_E() {
        J_1907_R = lightning.product.h_3859_C$n_1700_B.n_1700_B;
        R_4764_Y = -1;
        n_1700_B.n_1700_B();
    }

    public static void n_1700_B(int grief) {
        G_564_y = grief;
    }

    public static void R_4764_Y() {
        G_564_y = null;
    }

    private static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.h_3859_C$n_1700_B.n_1700_B();
        }
    }
}



