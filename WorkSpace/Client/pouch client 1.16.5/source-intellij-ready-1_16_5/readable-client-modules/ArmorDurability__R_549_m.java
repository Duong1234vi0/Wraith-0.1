/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.N_4463_r;
import lightning.product.X_3546_T;
import lightning.product.a_3913_L;
import lightning.product.c_1404_X;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.r_4811_B;
import lightning.product.y_2603_k;

public class R_549_m
extends X_3546_T {
    private static final float[][] w_1484_f = new float[][]{{0.6f, 0.0f, 0.0f}, {0.8f, 0.0f, 0.0f}, {1.0f, 0.0f, 0.0f}, {1.0f, 0.2f, 0.0f}, {1.0f, 0.4f, 0.0f}, {1.0f, 0.6f, 0.0f}, {1.0f, 0.8f, 0.0f}, {1.0f, 1.0f, 0.0f}, {0.9f, 1.0f, 0.0f}, {0.7f, 1.0f, 0.0f}, {0.5f, 1.0f, 0.0f}, {0.0f, 0.6f, 0.0f}, {0.0f, 0.7f, 0.0f}, {0.0f, 0.8f, 0.0f}, {0.0f, 0.9f, 0.0f}, {0.0f, 1.0f, 0.0f}, {0.2f, 1.0f, 0.2f}, {0.4f, 1.0f, 0.4f}, {0.6f, 1.0f, 0.6f}, {0.8f, 1.0f, 0.8f}, {1.0f, 1.0f, 1.0f}};
    public static N_4463_r v_4262_N = new N_4463_r("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c", new p_1977_n("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", true), new p_1977_n("\u0414\u0440\u0443\u0437\u0435\u0439", true));

    public R_549_m() {
        super("ArmorDurability", y_2603_k.R_4764_Y);
        this.n_1700_B(v_4262_N);
    }

    public static boolean n_1700_B(r_4811_B entity) {
        c_1404_X mc = c_1404_X.A_4115_X();
        if (entity == mc.Y_259_p) {
            return true;
        }
        if (entity instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)entity;
            boolean isFriend = ((List)o_148_s.Y_601_j().v_4262_N().u_2550_I()).contains(player.O_1309_Q().getString());
            if (isFriend) {
                return v_4262_N.J_1907_R("\u0414\u0440\u0443\u0437\u0435\u0439");
            }
            return v_4262_N.J_1907_R("\u0418\u0433\u0440\u043e\u043a\u043e\u0432");
        }
        return false;
    }

    public static float[] n_1700_B(float durability) {
        if (durability < 0.0f || durability > 1.0f) {
            return new float[]{1.0f, 1.0f, 1.0f};
        }
        int interval = (int)(durability * 100.0f / 5.0f);
        return w_1484_f[Math.min(interval, w_1484_f.length - 1)];
    }
}

