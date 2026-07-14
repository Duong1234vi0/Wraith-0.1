/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;

public final class r_2090_h
implements MinecraftAccess {
    private r_2090_h() {
    }

    public static void n_1700_B(int slotId, int buttonId, a_408_T clickType, boolean silent) {
        if (r_2090_h.c_3005_b.Y_259_p == null || r_2090_h.c_3005_b.Y_259_p.H_1873_g == null || r_2090_h.c_3005_b.w_1457_N == null) {
            return;
        }
        r_2090_h.n_1700_B(r_2090_h.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, slotId, buttonId, clickType, silent);
    }

    public static void n_1700_B(int windowId, int slotId, int buttonId, a_408_T clickType, boolean silent) {
        if (r_2090_h.c_3005_b.w_1457_N == null || r_2090_h.c_3005_b.Y_259_p == null) {
            return;
        }
        r_2090_h.c_3005_b.w_1457_N.windowClick(windowId, slotId, buttonId, clickType, r_2090_h.c_3005_b.Y_259_p);
        if (silent && r_2090_h.c_3005_b.Y_259_p.H_1873_g != null) {
            r_2090_h.c_3005_b.Y_259_p.H_1873_g.n_1700_B(slotId, buttonId, clickType, r_2090_h.c_3005_b.Y_259_p);
        }
    }
}


