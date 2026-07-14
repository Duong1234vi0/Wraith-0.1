/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.P_1922_E;
import lightning.product.U_2871_b;
import lightning.product.j_3341_s;
import lightning.product.n_1700_B;
import lightning.product.u_1723_Y;
import lightning.product.u_2550_I;
import lightning.product.v_4262_N;
import lightning.product.x_282_a;

public class w_1484_f
implements u_1723_Y {
    private final P_1922_E n_1700_B;
    private final int J_1907_R = 200;
    private int R_4764_Y = 0;
    private boolean G_564_y = false;
    private int P_1922_E = 0;

    public w_1484_f(int griefNumber) {
        this.n_1700_B = new P_1922_E(griefNumber, 1);
    }

    @Override
    public void n_1700_B(n_1700_B bot) {
        if (bot == null || bot.R_4764_Y == null) {
            return;
        }
        if (this.G_564_y) {
            ++this.R_4764_Y;
            if (this.R_4764_Y >= 200) {
                if (bot.J_1907_R == null || bot.G_564_y == null) {
                    this.n_1700_B(bot, "\u00a7c[Rebreak] \u041c\u0438\u0440 \u0435\u0449\u0451 \u043d\u0435 \u0433\u043e\u0442\u043e\u0432, \u0436\u0434\u0443 \u0435\u0449\u0451...");
                    return;
                }
                this.n_1700_B(bot, "\u00a7a[Rebreak] \u0412\u043e\u0437\u043e\u0431\u043d\u043e\u0432\u043b\u044f\u044e \u0446\u0438\u043a\u043b \u043b\u043e\u043c\u0430\u043d\u0438\u044f! \u0421\u043e\u0437\u0434\u0430\u044e CyclicRebreakBehavior...");
                bot.n_1700_B(new v_4262_N());
            } else if (this.R_4764_Y % 40 == 0) {
                this.n_1700_B(bot, "\u00a77[Rebreak] \u041e\u0436\u0438\u0434\u0430\u043d\u0438\u0435 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0438 \u043c\u0438\u0440\u0430... (" + (200 - this.R_4764_Y) / 20 + " \u0441\u0435\u043a \u043e\u0441\u0442\u0430\u043b\u043e\u0441\u044c)");
            }
            return;
        }
        this.n_1700_B.n_1700_B(bot);
        if (bot.M_588_G instanceof u_2550_I) {
            this.G_564_y = true;
            this.R_4764_Y = 0;
            this.n_1700_B(bot, "\u00a7a[Rebreak] \u0423\u0441\u043f\u0435\u0448\u043d\u043e \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0438\u043b\u0441\u044f \u043a \u0433\u0440\u0438\u0444\u0443, \u0436\u0434\u0443 10 \u0441\u0435\u043a\u0443\u043d\u0434 \u0434\u043b\u044f \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0438 \u043c\u0438\u0440\u0430...");
            bot.n_1700_B(this);
            return;
        }
        ++this.P_1922_E;
        if (this.P_1922_E % 40 == 0) {
            this.n_1700_B(bot, "\u00a77[Rebreak] \u0420\u0435\u0434\u0436\u043e\u0438\u043d: \u0432\u044b\u043f\u043e\u043b\u043d\u044f\u044e AutoJoinGrief... (\u0442\u0438\u043a " + this.P_1922_E + ")");
        }
    }

    private void n_1700_B(n_1700_B bot, String message) {
        if (bot.P_1922_E != null && bot.P_1922_E.Q_2552_b != null) {
            bot.P_1922_E.Q_2552_b.n_1700_B((x_282_a)new U_2871_b(message), j_3341_s.J_1907_R);
        }
    }
}

