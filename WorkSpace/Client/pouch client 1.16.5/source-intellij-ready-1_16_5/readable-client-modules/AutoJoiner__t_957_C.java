/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.D_4024_W;
import lightning.product.E_3343_g;
import lightning.product.I_686_h;
import lightning.product.J_2751_l;
import lightning.product.P_3201_s;
import lightning.product.P_567_z;
import lightning.product.Q_2753_H;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Y_2498_n;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.a_408_T;
import lightning.product.h_1015_G;
import lightning.product.k_1836_E;
import lightning.product.p_1183_T;
import lightning.product.q_3115_L;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.u_1934_K;
import lightning.product.v_1900_v;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import lightning.product.y_2603_k;
import lightning.product.z_3427_G;

public class t_957_C
extends X_3546_T {
    public final q_366_O v_4262_N = new q_366_O("\u0417\u0430\u0445\u043e\u0434\u0438\u0442\u044c \u043d\u0430", "Spooky Duels", "Spooky Duels", "ReallyWorld");
    private final I_686_h w_1484_f = new I_686_h("\u0413\u0440\u0438\u0444\u0435\u0440\u0441\u043a\u0438\u0439 \u043c\u0438\u0440", 1.0f, 1.0f, 54.0f, 1.0f);
    private final I_686_h t_148_a = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 3.0f, 1.0f, 20.0f, 1.0f);

    public t_957_C() {
        super("AutoJoiner", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        this.h_1847_R();
        this.M_182_A();
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (!e.J_1907_R()) {
            return;
        }
        this.J_1907_R(e);
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g e) {
        if (!this.v_4262_N.J_1907_R("Spooky Duels")) {
            this.R_4764_Y();
        }
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        this.Q_4569_t();
    }

    private void J_1907_R(Q_2753_H event) {
        P_567_z packet;
        if (!event.J_1907_R()) {
            return;
        }
        q_3115_L serverUtil = new q_3115_L();
        if (this.v_4262_N.J_1907_R("Spooky Duels") && serverUtil.J_1907_R("spooky")) {
            if (event.G_564_y() instanceof k_1836_E) {
                this.Q_4569_t();
                t_957_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            } else if (event.G_564_y() instanceof P_567_z && (packet = (P_567_z)event.G_564_y()).G_564_y().getString().contains("\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0440\u0435\u0436\u0438\u043c")) {
                t_957_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_3201_s(packet.J_1907_R(), 14, 0, a_408_T.n_1700_B, t_957_C.c_3005_b.Y_259_p.H_1873_g.n_1700_B(14).n_1700_B(), t_957_C.c_3005_b.Y_259_p.H_1873_g.n_1700_B(t_957_C.c_3005_b.Y_259_p.l_1268_F)));
                event.n_1700_B(true);
            }
        }
        if (this.v_4262_N.J_1907_R("ReallyWorld") && serverUtil.w_1484_f()) {
            if (event.G_564_y() instanceof J_2751_l) {
                if (t_957_C.c_3005_b.M_588_G.v_4262_N().J_1907_R() == null) {
                    return;
                }
                v_1900_v.n_1700_B("\u0412\u0445\u043e\u0434 \u043d\u0430 " + ((Float)this.w_1484_f.J_1907_R()).intValue() + " \u0433\u0440\u0438\u0444 \u0443\u0441\u043f\u0435\u0448\u0435\u043d", new Object[0]);
                this.R_4764_Y();
            } else if (event.G_564_y() instanceof k_1836_E) {
                this.Q_4569_t();
            } else if (event.G_564_y() instanceof P_567_z) {
                packet = (P_567_z)event.G_564_y();
                v_1900_v.n_1700_B(packet.G_564_y().getString(), new Object[0]);
                if (packet.G_564_y().getString().contains("\u0412\u044b\u0431\u043e\u0440 \u0441\u0435\u0440\u0432\u0435\u0440\u0430")) {
                    t_957_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_3201_s(packet.J_1907_R(), 21, 0, a_408_T.n_1700_B, t_957_C.c_3005_b.Y_259_p.H_1873_g.n_1700_B(21).n_1700_B(), t_957_C.c_3005_b.Y_259_p.H_1873_g.n_1700_B(t_957_C.c_3005_b.Y_259_p.l_1268_F)));
                    event.n_1700_B(true);
                }
            }
        } else if (this.v_4262_N.J_1907_R("ReallyWorld") && serverUtil.J_1907_R("funtime")) {
            k_1836_E p;
            String m;
            if (event.G_564_y() instanceof J_2751_l) {
                if (t_957_C.c_3005_b.M_588_G.v_4262_N().J_1907_R() == null) {
                    return;
                }
                v_1900_v.n_1700_B("\u0412\u0445\u043e\u0434 \u043d\u0430 " + ((Float)this.w_1484_f.J_1907_R()).intValue() + " \u0430\u043d\u0430\u0440\u0445\u0438\u044e \u0443\u0441\u043f\u0435\u0448\u0435\u043d", new Object[0]);
                this.R_4764_Y();
            } else if (event.G_564_y() instanceof k_1836_E && (m = D_4024_W.n_1700_B((p = (k_1836_E)event.G_564_y()).J_1907_R().getString())).contains("\u0412\u044b \u0443\u0436\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u044b")) {
                this.R_4764_Y();
            }
        }
    }

    private void h_1847_R() {
        q_3115_L serverUtil = new q_3115_L();
        if (this.v_4262_N.J_1907_R("ReallyWorld") && serverUtil.J_1907_R("funtime")) {
            t_957_C.c_3005_b.Y_259_p.n_1700_B("/an" + ((Float)this.w_1484_f.J_1907_R()).intValue());
        }
        if (this.v_4262_N.J_1907_R("Spooky Duels") && serverUtil.J_1907_R("spooky")) {
            this.Q_4569_t();
            t_957_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            if (u_1934_K.n_1700_B(q_4592_V.N_2592_G) != -1) {
                v_1900_v.n_1700_B("\u0412\u0445\u043e\u0434 \u0443\u0441\u043f\u0435\u0448\u0435\u043d", new Object[0]);
                this.R_4764_Y();
            }
        }
    }

    private void Q_4569_t() {
        if (t_957_C.c_3005_b.Y_259_p == null) {
            return;
        }
        int slot = u_1934_K.n_1700_B(q_4592_V.X_1303_p);
        if (slot == -1) {
            return;
        }
        boolean containerOpen = t_957_C.c_3005_b.Y_1740_V instanceof z_3427_G;
        int hotbarIndex = -1;
        if (slot >= 0 && slot <= 8) {
            hotbarIndex = slot;
        } else if (slot >= 36 && slot <= 44) {
            hotbarIndex = slot - 36;
        }
        if (hotbarIndex != -1) {
            t_957_C.c_3005_b.Y_259_p.l_1268_F.G_564_y = hotbarIndex;
            t_957_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(hotbarIndex));
            t_957_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            return;
        }
        if (!containerOpen) {
            int containerSlot = slot < 9 ? slot + 36 : slot;
            int targetHotbar = t_957_C.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            t_957_C.c_3005_b.w_1457_N.windowClick(t_957_C.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, containerSlot, targetHotbar, a_408_T.R_4764_Y, t_957_C.c_3005_b.Y_259_p);
            t_957_C.c_3005_b.Y_259_p.l_1268_F.G_564_y = targetHotbar;
            t_957_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(targetHotbar));
            t_957_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
        }
    }

    private void M_182_A() {
        String itemName;
        Z_1993_T stack;
        Y_2498_n containerSlot;
        if (t_957_C.c_3005_b.Y_259_p.H_1873_g == null) {
            return;
        }
        boolean isGriefSelectionWindow = false;
        boolean isFirstPage = true;
        if (t_957_C.c_3005_b.Y_1740_V instanceof z_3427_G) {
            z_3427_G screen = (z_3427_G)t_957_C.c_3005_b.Y_1740_V;
            String title = screen.getTitle().getString();
            String titleClean = D_4024_W.n_1700_B(title);
            if (title.contains("\u0412\u044b\u0431\u043e\u0440 \u043c\u0438\u0440\u0430 \u0433\u0440\u0438\u0444\u0430") || title.contains("\u0433\u0440\u0438\u0444\u0430")) {
                isGriefSelectionWindow = true;
                if (titleClean.contains("2/2") || titleClean.contains("2/")) {
                    isFirstPage = false;
                }
            }
        }
        if (isGriefSelectionWindow && ((Float)this.w_1484_f.J_1907_R()).intValue() > 36 && isFirstPage) {
            for (int slot = 0; slot < t_957_C.c_3005_b.Y_259_p.H_1873_g.P_1922_E.size(); ++slot) {
                containerSlot = t_957_C.c_3005_b.Y_259_p.H_1873_g.n_1700_B(slot);
                if (containerSlot == null || !containerSlot.J_1907_R()) continue;
                stack = containerSlot.n_1700_B();
                itemName = stack.N_4405_n().getString();
                String itemNameClean = D_4024_W.n_1700_B(itemName);
                if (itemNameClean.contains("\u0421\u043b\u0435\u0434\u0443\u044e\u0449\u0430\u044f \u0441\u0442\u0440\u0430\u043d\u0438\u0446\u0430") || itemNameClean.contains("Next page") || itemNameClean.contains("\u0421\u043b\u0435\u0434\u0443\u044e\u0449\u0430\u044f")) {
                    t_957_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_3201_s(t_957_C.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, slot, 0, a_408_T.n_1700_B, stack, t_957_C.c_3005_b.Y_259_p.H_1873_g.n_1700_B(t_957_C.c_3005_b.Y_259_p.l_1268_F)));
                    return;
                }
                try {
                    if (t_957_C.c_3005_b.Y_1740_V != null) {
                        List<x_282_a> tooltip = t_957_C.c_3005_b.Y_1740_V.getTooltipFromItem(stack);
                        for (x_282_a component : tooltip) {
                            String tooltipText = D_4024_W.n_1700_B(component.getString());
                            if (!tooltipText.contains("\u0421\u043b\u0435\u0434\u0443\u044e\u0449\u0430\u044f \u0441\u0442\u0440\u0430\u043d\u0438\u0446\u0430") && !tooltipText.contains("Next page") && !tooltipText.contains("\u0421\u043b\u0435\u0434\u0443\u044e\u0449\u0430\u044f")) continue;
                            t_957_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_3201_s(t_957_C.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, slot, 0, a_408_T.n_1700_B, stack, t_957_C.c_3005_b.Y_259_p.H_1873_g.n_1700_B(t_957_C.c_3005_b.Y_259_p.l_1268_F)));
                            return;
                        }
                    }
                    continue;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
        for (int slot = 0; slot < t_957_C.c_3005_b.Y_259_p.H_1873_g.P_1922_E.size(); ++slot) {
            containerSlot = t_957_C.c_3005_b.Y_259_p.H_1873_g.n_1700_B(slot);
            if (containerSlot == null || !containerSlot.J_1907_R() || !(itemName = (stack = containerSlot.n_1700_B()).N_4405_n().getString()).contains("\u0413\u0420\u0418\u0424 #" + ((Float)this.w_1484_f.J_1907_R()).intValue() + " (1.16.5+)")) continue;
            t_957_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_3201_s(t_957_C.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, slot, 0, a_408_T.n_1700_B, stack, t_957_C.c_3005_b.Y_259_p.H_1873_g.n_1700_B(t_957_C.c_3005_b.Y_259_p.l_1268_F)));
            t_957_C.c_3005_b.Y_259_p.P_1922_E();
        }
    }
}

