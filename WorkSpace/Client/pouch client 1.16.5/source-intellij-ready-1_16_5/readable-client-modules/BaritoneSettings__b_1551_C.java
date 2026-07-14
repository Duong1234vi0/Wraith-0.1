/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.b_2152_i;
import lightning.product.h_1015_G;
import lightning.product.p_1977_n;
import lightning.product.q_4592_V;
import lightning.product.y_2603_k;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;

public class b_1551_C
extends X_3546_T
implements b_2152_i {
    public static final p_1977_n v_4262_N = new p_1977_n("Ignore Screens", false);
    public static final p_1977_n w_1484_f = new p_1977_n("Ignore Pause", false);
    private final p_1977_n t_148_a = new p_1977_n("Auto Eat", false);
    private final I_686_h s_956_w = new I_686_h("Min Food", 10.0f, 1.0f, 20.0f, 1.0f, this.t_148_a::t_148_a);

    public b_1551_C() {
        super("BaritoneSettings", y_2603_k.G_564_y);
        BaritoneAPI.getSettings().chunkCaching.value = false;
        BaritoneAPI.getSettings().pruneRegionsFromRAM.value = true;
        BaritoneAPI.getSettings().cachedChunksExpirySeconds.value = 1L;
        this.n_1700_B(v_4262_N, w_1484_f, this.t_148_a, this.s_956_w);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (b_1551_C.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.t_148_a.t_148_a().booleanValue() && (float)b_1551_C.c_3005_b.Y_259_p.P_2295_B().n_1700_B() <= ((Float)this.s_956_w.J_1907_R()).floatValue()) {
            this.h_1847_R();
        }
    }

    private void h_1847_R() {
        Z_1993_T offhand = b_1551_C.c_3005_b.Y_259_p.S_4035_N();
        boolean hasFood = offhand.J_1907_R().Y_259_p();
        if (!hasFood) {
            for (int i = 0; i < 36; ++i) {
                Z_1993_T stack = b_1551_C.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
                if (!stack.J_1907_R().Y_259_p() || stack.J_1907_R() == q_4592_V.m_1964_F || stack.J_1907_R() == q_4592_V.r_2687_x) continue;
                int slot = i < 9 ? i + 36 : i;
                b_1551_C.c_3005_b.w_1457_N.windowClick(0, slot, 0, a_408_T.n_1700_B, b_1551_C.c_3005_b.Y_259_p);
                b_1551_C.c_3005_b.w_1457_N.windowClick(0, 45, 0, a_408_T.n_1700_B, b_1551_C.c_3005_b.Y_259_p);
                Z_1993_T cursor = b_1551_C.c_3005_b.Y_259_p.l_1268_F.s_956_w();
                if (!cursor.n_1700_B()) {
                    b_1551_C.c_3005_b.w_1457_N.windowClick(0, slot, 0, a_408_T.n_1700_B, b_1551_C.c_3005_b.Y_259_p);
                }
                break;
            }
        } else if (!b_1551_C.c_3005_b.Y_259_p.Y_601_j()) {
            b_1551_C.c_3005_b.P_4830_p.e_1992_r.n_1700_B(true);
        }
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        b_1551_C.c_3005_b.P_4830_p.e_1992_r.n_1700_B(false);
    }
}

