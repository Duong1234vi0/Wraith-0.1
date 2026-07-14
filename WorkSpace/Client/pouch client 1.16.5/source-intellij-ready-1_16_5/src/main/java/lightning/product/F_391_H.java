/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import lightning.product.D_1410_T;
import lightning.product.MutableComponent;
import lightning.product.Q_2753_H;
import lightning.product.U_2871_b;
import lightning.product.U_3758_B;
import lightning.product.V_3137_a;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftAccess;
import lightning.product.g_2336_b;
import lightning.product.g_422_i;
import lightning.product.h_1015_G;
import lightning.product.k_2610_C;
import lightning.product.q_1613_l;

public class F_391_H
implements MinecraftAccess {
    private final Set<g_422_i> n_1700_B = new HashSet<g_422_i>();
    private final int[] J_1907_R = new int[4];
    private final q_1613_l[] R_4764_Y = new q_1613_l[4];

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (F_391_H.c_3005_b.Y_259_p == null || F_391_H.c_3005_b.Y_601_j == null) {
            return;
        }
        if (D_1410_T.u_1723_Y.t_148_a().booleanValue()) {
            HashSet<g_422_i> currentEffects = new HashSet<g_422_i>();
            for (k_2610_C eff : F_391_H.c_3005_b.Y_259_p.I_3457_f()) {
                currentEffects.add(eff.n_1700_B());
            }
            Iterator<g_422_i> it = this.n_1700_B.iterator();
            while (it.hasNext()) {
                g_422_i prev = it.next();
                if (currentEffects.contains(prev)) continue;
                U_3758_B.n_1700_B("M", "\u042d\u0444\u0444\u0435\u043a\u0442 '" + prev.G_564_y().getString() + "' \u0431\u043e\u043b\u044c\u0448\u0435 \u043d\u0435 \u0430\u043a\u0442\u0438\u0432\u0435\u043d!", -1);
                it.remove();
            }
            this.n_1700_B.addAll(currentEffects);
        }
        if (D_1410_T.P_1922_E.t_148_a().booleanValue()) {
            for (int idx = 0; idx < F_391_H.c_3005_b.Y_259_p.l_1268_F.J_1907_R.size(); ++idx) {
                int used;
                int maxD;
                int remaining;
                int remainingPercent;
                Z_1993_T stack = F_391_H.c_3005_b.Y_259_p.l_1268_F.J_1907_R.get(idx);
                if (stack.n_1700_B() || stack.w_1484_f() <= 0) {
                    this.R_4764_Y[idx] = null;
                    this.J_1907_R[idx] = 0;
                    continue;
                }
                q_1613_l item = stack.J_1907_R();
                if (this.R_4764_Y[idx] != item) {
                    this.R_4764_Y[idx] = item;
                    this.J_1907_R[idx] = 0;
                }
                if ((remainingPercent = (int)((double)(remaining = Math.max(0, (maxD = stack.w_1484_f()) - (used = stack.v_4262_N()))) * 100.0 / (double)maxD)) < 1 || remainingPercent > 5 || this.J_1907_R[idx] == remainingPercent) continue;
                g_2336_b key = V_3137_a.e_2887_G.J_1907_R(item);
                g_2336_b image = new g_2336_b(key.R_4764_Y(), "textures/item/" + key.J_1907_R() + ".png");
                MutableComponent message = new U_2871_b("").n_1700_B(stack.multiplayerClientSuggestionProvider()).n_1700_B(" \u043f\u043e\u0447\u0442\u0438 \u0441\u043b\u043e\u043c\u0430\u043d!");
                U_3758_B.n_1700_B(image, message);
                this.J_1907_R[idx] = remainingPercent;
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
    }
}



