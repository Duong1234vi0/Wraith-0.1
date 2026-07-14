/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Locale;
import lightning.product.D_4024_W;
import lightning.product.O_1043_U;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.a_408_T;
import lightning.product.n_1700_B;
import lightning.product.Items;
import lightning.product.u_1723_Y;
import lightning.product.u_2550_I;
import lightning.product.x_1688_C;

public class P_1922_E
implements u_1723_Y {
    private static final long n_1700_B = 50L;
    private static final long J_1907_R = 500L;
    private static final long R_4764_Y = 125L;
    private final int G_564_y;
    private final long P_1922_E;
    private final long u_1723_Y;
    private final String v_4262_N;
    private final String w_1484_f;
    private final String t_148_a;
    private final String s_956_w;
    private final O_1043_U u_2550_I = new O_1043_U();
    private final O_1043_U M_588_G = new O_1043_U();
    private final O_1043_U P_4830_p = new O_1043_U();
    private boolean h_1847_R = false;

    public P_1922_E(int griefNumber) {
        this(griefNumber, 1);
    }

    public P_1922_E(int griefNumber, int intervalSeconds) {
        this(griefNumber, intervalSeconds, 0L);
    }

    public P_1922_E(int griefNumber, int intervalSeconds, long initialDelayMillis) {
        this.G_564_y = griefNumber;
        int safeIntervalSeconds = Math.max(1, intervalSeconds);
        this.P_1922_E = (long)safeIntervalSeconds * 1000L;
        this.u_1723_Y = Math.max(0L, initialDelayMillis);
        String number = String.valueOf(griefNumber);
        this.v_4262_N = "\u0433\u0440\u0438\u0444 #" + number;
        this.w_1484_f = "\u0433\u0440\u0438\u0444 \u2116" + number;
        this.t_148_a = "grief #" + number;
        this.s_956_w = "grief " + number;
    }

    @Override
    public void n_1700_B(n_1700_B bot) {
        if (bot == null || bot.R_4764_Y == null || bot.P_1922_E == null) {
            return;
        }
        if (!this.h_1847_R) {
            this.u_2550_I.R_4764_Y(-(this.u_1723_Y + this.G_564_y(bot)));
            this.h_1847_R = true;
        }
        if (bot.R_4764_Y.H_1873_g == null || bot.R_4764_Y.H_1873_g.u_1723_Y == 0) {
            if (this.u_2550_I.n_1700_B(this.P_1922_E) && this.J_1907_R(bot)) {
                this.u_2550_I.J_1907_R();
            }
        } else if (this.P_4830_p.n_1700_B(125L)) {
            this.R_4764_Y(bot);
            this.P_4830_p.J_1907_R();
        }
    }

    private boolean J_1907_R(n_1700_B bot) {
        for (int i = 0; i < 9; ++i) {
            Z_1993_T stack = bot.R_4764_Y.l_1268_F.s_956_w(i);
            if (stack.J_1907_R() != Items.X_1303_p) continue;
            bot.R_4764_Y.l_1268_F.G_564_y = i;
            bot.P_1922_E.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            return true;
        }
        return false;
    }

    private void R_4764_Y(n_1700_B bot) {
        if (bot.R_4764_Y.H_1873_g == null) {
            return;
        }
        int windowId = bot.R_4764_Y.H_1873_g.u_1723_Y;
        boolean needSecondPage = this.G_564_y > 36;
        boolean onSecondPage = false;
        boolean hasMainMenuEntry = false;
        boolean hasHints = false;
        int backSlot = -1;
        int mainMenuSlot = -1;
        int nextPageSlot = -1;
        int previousPageSlot = -1;
        int targetGriefSlot = -1;
        for (int i = 0; i < bot.R_4764_Y.H_1873_g.P_1922_E.size(); ++i) {
            String name;
            Slot slot = bot.R_4764_Y.H_1873_g.P_1922_E.get(i);
            Z_1993_T stack = slot.n_1700_B();
            if (stack.n_1700_B() || (name = this.J_1907_R(stack.multiplayerClientSuggestionProvider().getString())).isEmpty()) continue;
            if (name.contains("\u0433\u0440\u0438\u0444\u0435\u0440\u0441\u043a\u043e\u0435 \u0432\u044b\u0436\u0438\u0432\u0430\u043d\u0438\u0435")) {
                hasMainMenuEntry = true;
                if (mainMenuSlot < 0) {
                    mainMenuSlot = i;
                }
            }
            if (name.contains("\u043f\u043e\u0434\u0441\u043a\u0430\u0437")) {
                hasHints = true;
            }
            if (this.R_4764_Y(stack, name)) {
                backSlot = i;
            }
            if (this.J_1907_R(stack, name)) {
                onSecondPage = true;
                if (previousPageSlot < 0) {
                    previousPageSlot = i;
                }
            }
            if (this.n_1700_B(stack, name) && nextPageSlot < 0) {
                nextPageSlot = i;
            }
            if (targetGriefSlot >= 0 || !this.n_1700_B(name)) continue;
            targetGriefSlot = i;
        }
        if (hasHints && backSlot >= 0 && !hasMainMenuEntry) {
            this.n_1700_B(bot, windowId, backSlot, 50L);
            return;
        }
        if (mainMenuSlot >= 0 && this.n_1700_B(bot, windowId, mainMenuSlot, 50L)) {
            return;
        }
        if (needSecondPage && !onSecondPage && this.n_1700_B(bot, windowId, nextPageSlot, 50L)) {
            return;
        }
        if (!needSecondPage && onSecondPage && this.n_1700_B(bot, windowId, previousPageSlot, 50L)) {
            return;
        }
        if (needSecondPage && !onSecondPage) {
            return;
        }
        if (this.n_1700_B(bot, windowId, targetGriefSlot, 500L)) {
            bot.n_1700_B(new u_2550_I());
        }
    }

    private boolean n_1700_B(Z_1993_T stack, String name) {
        return name.contains("\u0441\u043b\u0435\u0434\u0443\u044e\u0449\u0430\u044f \u0441\u0442\u0440\u0430\u043d\u0438\u0446\u0430") || this.n_1700_B(stack) && !name.contains("\u043f\u0440\u0435\u0434\u044b\u0434\u0443\u0449\u0430\u044f") && !name.contains("\u043d\u0430\u0437\u0430\u0434") && !name.contains("back");
    }

    private boolean J_1907_R(Z_1993_T stack, String name) {
        return name.contains("\u043f\u0440\u0435\u0434\u044b\u0434\u0443\u0449\u0430\u044f \u0441\u0442\u0440\u0430\u043d\u0438\u0446\u0430") || name.contains("\u043f\u0440\u0435\u0434\u044b\u0434\u0443\u0449\u0430\u044f");
    }

    private boolean R_4764_Y(Z_1993_T stack, String name) {
        if (!this.n_1700_B(stack) && stack.J_1907_R() != Items.AimAssist) {
            return false;
        }
        return name.contains("\u043d\u0430\u0437\u0430\u0434") || name.contains("back") || name.contains("\u043c\u0435\u043d\u044e") || name.contains("\u0432\u044b\u0445\u043e\u0434") || name.contains("\u0437\u0430\u043a\u0440\u044b\u0442\u044c");
    }

    private boolean n_1700_B(String name) {
        return name.contains(this.v_4262_N) || name.contains(this.w_1484_f) || name.contains(this.t_148_a) || name.contains(this.s_956_w);
    }

    private String J_1907_R(String name) {
        String clean = name;
        if (clean.indexOf(167) >= 0) {
            clean = D_4024_W.n_1700_B(clean);
        }
        if (clean == null) {
            return "";
        }
        return clean.toLowerCase(Locale.ROOT);
    }

    private boolean n_1700_B(Z_1993_T stack) {
        return stack.J_1907_R() == Items.g_24_p;
    }

    private long G_564_y(n_1700_B bot) {
        if (bot.P_1922_E == null || bot.P_1922_E.Q_2552_b == null) {
            return 0L;
        }
        String botName = bot.P_1922_E.Q_2552_b.t_4043_B();
        if (botName == null || botName.isEmpty()) {
            return 0L;
        }
        return (long)Math.abs(botName.hashCode()) % 900L;
    }

    private boolean n_1700_B(n_1700_B bot, int windowId, int slot, long delayMs) {
        if (slot < 0) {
            return false;
        }
        if (!this.M_588_G.n_1700_B(delayMs)) {
            return false;
        }
        bot.G_564_y.n_1700_B(windowId, slot, 0, a_408_T.n_1700_B, bot.R_4764_Y);
        this.M_588_G.J_1907_R();
        return true;
    }
}



