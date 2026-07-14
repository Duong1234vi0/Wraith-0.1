/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.ContainerScreen;
import lightning.product.J_1907_R;
import lightning.product.MultiBooleanSetting;
import lightning.product.ChestMenu;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Y_2080_q;
import lightning.product.Slot;
import lightning.product.a_408_T;
import lightning.product.MinecraftClient;
import lightning.product.h_1015_G;
import lightning.product.ClientboundChatPacket;
import lightning.product.k_2603_m;
import lightning.product.n_1700_B;
import lightning.product.BooleanSetting;
import lightning.product.Items;
import lightning.product.Packet;
import lightning.product.ModuleCategory;

public class BotAutoCollector
extends Module {
    private static final MinecraftClient t_148_a = MinecraftClient.A_4115_X();
    public MultiBooleanSetting collectOptions = new MultiBooleanSetting("Collect", new BooleanSetting("/free", true), new BooleanSetting("/codes", false));
    private long s_956_w = 0L;
    private boolean u_2550_I;
    private boolean M_588_G;
    private int P_4830_p = 1;
    final List<n_1700_B> w_1484_f = new ArrayList<n_1700_B>();
    private n_1700_B h_1847_R = null;

    public BotAutoCollector() {
        super("BotAutoCollector", ModuleCategory.P_1922_E);
        this.addSettings(this.collectOptions);
    }

    private boolean n_1700_B(long ms) {
        return System.currentTimeMillis() - this.s_956_w >= ms;
    }

    private void h_1847_R() {
        this.s_956_w = System.currentTimeMillis();
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        n_1700_B bot1 = null;
        n_1700_B bot2 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (BotAutoCollector.t_148_a.C_2741_M == bot.P_1922_E.Q_2552_b) {
                bot1 = bot;
            }
            if (BotAutoCollector.t_148_a.C_2741_M != bot.P_1922_E.Q_2552_b) {
                bot2 = bot;
            }
            if (System.currentTimeMillis() - bot.s_956_w > 600000L) {
                this.w_1484_f.remove(bot);
                bot.w_1484_f = false;
            }
            if (!bot.w_1484_f) continue;
            if (bot1 != null && bot2 != null) {
                bot1.P_1922_E.Q_2552_b.n_1700_B(".bot control " + bot2.P_1922_E.Q_2552_b.t_4043_B());
                bot1.s_956_w = System.currentTimeMillis();
            }
            this.M_588_G = false;
            this.P_4830_p = 1;
            this.u_2550_I = false;
        }
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (bot.w_1484_f || BotAutoCollector.t_148_a.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            if (!this.M_588_G && this.collectOptions.isOptionEnabled("/free").booleanValue()) {
                k_2603_m screen;
                if (!this.u_2550_I) {
                    bot.P_1922_E.Q_2552_b.n_1700_B("/free");
                    this.h_1847_R();
                    this.u_2550_I = true;
                }
                if ((screen = BotAutoCollector.t_148_a.Y_1740_V) instanceof ContainerScreen) {
                    ContainerScreen c = (ContainerScreen)screen;
                    int slot = 20;
                    for (int i = 0; i < ((ChestMenu)c.n_()).n_1700_B().Y_259_p(); ++i) {
                        if (((Slot)((ChestMenu)c.n_()).P_1922_E.get(20)).n_1700_B().J_1907_R() == Items.s_3834_w) {
                            ++slot;
                        }
                        if (((Slot)((ChestMenu)c.n_()).P_1922_E.get(21)).n_1700_B().J_1907_R() == Items.s_3834_w) {
                            ++slot;
                        }
                        if (((Slot)((ChestMenu)c.n_()).P_1922_E.get(22)).n_1700_B().J_1907_R() == Items.s_3834_w) {
                            ++slot;
                        }
                        if (((Slot)((ChestMenu)c.n_()).P_1922_E.get(23)).n_1700_B().J_1907_R() == Items.s_3834_w) {
                            ++slot;
                        }
                        if (!this.n_1700_B(350L)) continue;
                        bot.P_1922_E.C_2741_M.n_1700_B(bot.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, slot, 0, a_408_T.n_1700_B, bot.P_1922_E.Q_2552_b);
                        new Thread(() -> {
                            try {
                                Thread.sleep(200L);
                                this.u_2550_I = false;
                            }
                            catch (InterruptedException interruptedException) {
                                // empty catch block
                            }
                        }).start();
                        this.h_1847_R();
                    }
                }
            }
            if (bot.t_148_a || !this.M_588_G || !this.collectOptions.isOptionEnabled("/codes").booleanValue()) continue;
            if (this.P_4830_p == 1) {
                bot.P_1922_E.Q_2552_b.n_1700_B("/codes");
                this.h_1847_R();
                this.P_4830_p = 2;
            }
            if ((this.P_4830_p == 4 || this.P_4830_p == 6 || this.P_4830_p == 8 || this.P_4830_p == 10) && this.h_1847_R == null) {
                bot.P_1922_E.Q_2552_b.n_1700_B("/codes");
                this.h_1847_R = bot;
            }
            if (this.P_4830_p == 2 && BotAutoCollector.t_148_a.Y_1740_V instanceof ContainerScreen && this.n_1700_B(500L)) {
                bot.P_1922_E.C_2741_M.n_1700_B(bot.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, 20, 0, a_408_T.n_1700_B, bot.P_1922_E.Q_2552_b);
                this.h_1847_R();
                this.P_4830_p = 3;
            }
            if (this.P_4830_p == 3 && BotAutoCollector.t_148_a.Y_1740_V instanceof ContainerScreen && this.n_1700_B(500L)) {
                bot.P_1922_E.C_2741_M.n_1700_B(bot.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, 13, 0, a_408_T.n_1700_B, bot.P_1922_E.Q_2552_b);
                this.h_1847_R();
                this.h_1847_R = null;
                this.P_4830_p = 4;
            }
            if (this.P_4830_p == 4 && BotAutoCollector.t_148_a.Y_1740_V instanceof ContainerScreen && this.n_1700_B(500L)) {
                bot.P_1922_E.C_2741_M.n_1700_B(bot.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, 21, 0, a_408_T.n_1700_B, bot.P_1922_E.Q_2552_b);
                this.h_1847_R();
                this.P_4830_p = 5;
            }
            if (this.P_4830_p == 5 && BotAutoCollector.t_148_a.Y_1740_V instanceof ContainerScreen && this.n_1700_B(500L)) {
                bot.P_1922_E.C_2741_M.n_1700_B(bot.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, 13, 0, a_408_T.n_1700_B, bot.P_1922_E.Q_2552_b);
                this.h_1847_R();
                this.h_1847_R = null;
                this.P_4830_p = 6;
            }
            if (this.P_4830_p == 6 && BotAutoCollector.t_148_a.Y_1740_V instanceof ContainerScreen && this.n_1700_B(500L)) {
                bot.P_1922_E.C_2741_M.n_1700_B(bot.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, 22, 0, a_408_T.n_1700_B, bot.P_1922_E.Q_2552_b);
                this.h_1847_R();
                this.P_4830_p = 7;
            }
            if (this.P_4830_p == 7 && BotAutoCollector.t_148_a.Y_1740_V instanceof ContainerScreen && this.n_1700_B(500L)) {
                bot.P_1922_E.C_2741_M.n_1700_B(bot.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, 13, 0, a_408_T.n_1700_B, bot.P_1922_E.Q_2552_b);
                this.h_1847_R();
                this.h_1847_R = null;
                this.P_4830_p = 8;
            }
            if (this.P_4830_p == 8 && BotAutoCollector.t_148_a.Y_1740_V instanceof ContainerScreen && this.n_1700_B(500L)) {
                bot.P_1922_E.C_2741_M.n_1700_B(bot.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, 23, 0, a_408_T.n_1700_B, bot.P_1922_E.Q_2552_b);
                this.h_1847_R();
                this.P_4830_p = 9;
            }
            if (this.P_4830_p == 9 && BotAutoCollector.t_148_a.Y_1740_V instanceof ContainerScreen && this.n_1700_B(500L)) {
                bot.P_1922_E.C_2741_M.n_1700_B(bot.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, 13, 0, a_408_T.n_1700_B, bot.P_1922_E.Q_2552_b);
                this.h_1847_R();
                this.h_1847_R = null;
                this.P_4830_p = 10;
            }
            if (this.P_4830_p == 10 && BotAutoCollector.t_148_a.Y_1740_V instanceof ContainerScreen && this.n_1700_B(500L)) {
                bot.P_1922_E.C_2741_M.n_1700_B(bot.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, 24, 0, a_408_T.n_1700_B, bot.P_1922_E.Q_2552_b);
                this.h_1847_R();
                this.P_4830_p = 11;
            }
            if (this.P_4830_p != 11 || !(BotAutoCollector.t_148_a.Y_1740_V instanceof ContainerScreen) || !this.n_1700_B(500L)) continue;
            bot.P_1922_E.C_2741_M.n_1700_B(bot.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, 13, 0, a_408_T.n_1700_B, bot.P_1922_E.Q_2552_b);
            bot.t_148_a = true;
            bot.w_1484_f = true;
            this.h_1847_R = null;
            this.M_588_G = false;
            this.P_4830_p = 1;
        }
    }

    @Y_1740_V
    public void n_1700_B(Y_2080_q e) {
        ClientboundChatPacket s;
        Packet<?> packet;
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (BotAutoCollector.t_148_a.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
            break;
        }
        if (bot1 != null && (packet = e.P_1922_E()) instanceof ClientboundChatPacket && ((s = (ClientboundChatPacket)packet).J_1907_R().getString().contains("You haven't played enough") || s.J_1907_R().getString().contains("\u0412\u044b \u043d\u0435 \u043d\u0430\u0438\u0433\u0440\u0430\u043b\u0438"))) {
            this.M_588_G = true;
        }
    }
}



