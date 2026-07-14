/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.B_707_U;
import lightning.product.D_1410_T;
import lightning.product.D_4024_W;
import lightning.product.E_3343_g;
import lightning.product.J_3635_s;
import lightning.product.N_1833_W;
import lightning.product.MultiBooleanSetting;
import lightning.product.Q_2753_H;
import lightning.product.R_4688_l;
import lightning.product.S_234_U;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Y_3623_f;
import lightning.product.Y_4293_u;
import lightning.product.Z_1993_T;
import lightning.product.a_1887_j;
import lightning.product.a_2727_J;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.SoundEventRegistration;
import lightning.product.h_2739_B;
import lightning.product.h_3270_j;
import lightning.product.Setting;
import lightning.product.ClientboundChatPacket;
import lightning.product.m_396_H;
import lightning.product.n_2412_y;
import lightning.product.ServerHelper;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_4124_m;
import lightning.product.Items;
import lightning.product.u_1934_K;
import lightning.product.ModuleCategory;
import lightning.product.y_4642_Y;
import lightning.product.z_283_n;
import lightning.product.z_4066_l;

public class Interface
extends Module {
    public static SoundEventRegistration v_4262_N = new SoundEventRegistration("ThemeEdit", false, "\u041e\u0442\u043a\u0440\u044b\u0442\u044c \u0440\u0435\u0434\u0430\u043a\u0442\u043e\u0440 \u0446\u0432\u0435\u0442\u043e\u0432", "\u0417\u0430\u043a\u0440\u044b\u0442\u044c \u0440\u0435\u0434\u0430\u043a\u0442\u043e\u0440 \u0446\u0432\u0435\u0442\u043e\u0432");
    public static SoundEventRegistration w_1484_f = new SoundEventRegistration("ConfigPanel", false, "\u041e\u0442\u043a\u0440\u044b\u0442\u044c \u043a\u043e\u043d\u0444\u0438\u0433\u0438", "\u0417\u0430\u043a\u0440\u044b\u0442\u044c \u043a\u043e\u043d\u0444\u0438\u0433\u0438");
    public static MultiBooleanSetting oknaOptions = new MultiBooleanSetting("\u041e\u043a\u043d\u0430", new BooleanSetting("\u0411\u0440\u043e\u043d\u044f", true), new BooleanSetting("\u0421\u0447\u0435\u0442\u0447\u0438\u043a \u0422\u043e\u0442\u0435\u043c\u043e\u0432", true), new BooleanSetting("\u0422\u043e\u0442\u0435\u043c\u044b \u0443 \u043f\u0440\u0438\u0446\u0435\u043b\u0430", false), new BooleanSetting("\u0418\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u044f \u043e \u0446\u0435\u043b\u0438", true), new BooleanSetting("\u041b\u043e\u0433\u043e\u0442\u0438\u043f", true), new BooleanSetting("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f \u043e\u043d\u043b\u0430\u0439\u043d", true), new BooleanSetting("\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u044f", true), new BooleanSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0438", true), new BooleanSetting("\u0417\u0435\u043b\u044c\u044f", true), new BooleanSetting("\u0418\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c", true), new BooleanSetting("\u0413\u043e\u0440\u044f\u0447\u0438\u0435 \u043a\u043b\u0430\u0432\u0438\u0448\u0438", true), new BooleanSetting("\u0410\u0440\u0440\u0430\u0439 \u043b\u0438\u0441\u0442", true), new BooleanSetting("\u041c\u0435\u0434\u0438\u0430 \u041f\u043b\u0435\u0435\u0440", true), new BooleanSetting("\u0410\u0439\u0442\u0435\u043c \u0425\u0435\u043b\u043f\u0435\u0440", true), new BooleanSetting("\u0420\u0430\u0441\u043f\u0438\u0441\u0430\u043d\u0438\u0435 \u044d\u0432\u0435\u043d\u0442\u043e\u0432", true), new BooleanSetting("\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043a\u0430 \u0441\u0435\u0441\u0441\u0438\u0438", true), new BooleanSetting("Custom Hotbar", true));
    z_283_n s_956_w;
    a_2727_J u_2550_I;
    m_396_H M_588_G;
    n_2412_y P_4830_p;
    N_1833_W h_1847_R;
    q_4124_m Q_4569_t;
    a_1887_j M_182_A;
    D_1410_T t_1786_h;
    Y_3623_f multiplayerClientSuggestionProvider;
    S_234_U w_1457_N;
    public Y_4293_u Y_601_j;
    R_4688_l Y_259_p;
    B_707_U Q_2552_b;
    z_4066_l C_2741_M;
    public static J_3635_s k_2293_S;

    @Y_1740_V
    private void n_1700_B(b_3528_u.R_4764_Y e) {
        if (Interface.c_3005_b.P_4830_p.r_3651_U || y_4642_Y.R_4764_Y()) {
            return;
        }
        if (this.R_4764_Y("\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u044f")) {
            this.t_1786_h.n_1700_B(e);
        }
        if (this.R_4764_Y("\u0421\u0447\u0435\u0442\u0447\u0438\u043a \u0422\u043e\u0442\u0435\u043c\u043e\u0432")) {
            this.M_182_A.n_1700_B(e);
        }
        if (this.R_4764_Y("\u0422\u043e\u0442\u0435\u043c\u044b \u0443 \u043f\u0440\u0438\u0446\u0435\u043b\u0430")) {
            this.J_1907_R(e);
        }
        if (this.R_4764_Y("\u0411\u0440\u043e\u043d\u044f")) {
            this.Q_4569_t.n_1700_B(e);
        }
        if (this.R_4764_Y("\u0410\u0440\u0440\u0430\u0439 \u043b\u0438\u0441\u0442")) {
            this.M_588_G.n_1700_B(e);
        }
        if (this.R_4764_Y("\u041b\u043e\u0433\u043e\u0442\u0438\u043f")) {
            this.P_4830_p.n_1700_B(e);
            if (n_2412_y.R_4764_Y.t_148_a().booleanValue() || n_2412_y.v_4262_N.t_148_a().booleanValue()) {
                this.P_4830_p.J_1907_R(e);
            }
        }
        for (J_3635_s draggable : ClientBootstrap.Y_601_j().M_182_A().h_1847_R()) {
            String name = draggable.n_1700_B();
            if (!this.R_4764_Y(name)) continue;
            switch (name) {
                case "\u0418\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u044f \u043e \u0446\u0435\u043b\u0438": {
                    this.s_956_w.n_1700_B(e);
                    break;
                }
                case "\u0413\u043e\u0440\u044f\u0447\u0438\u0435 \u043a\u043b\u0430\u0432\u0438\u0448\u0438": {
                    this.u_2550_I.n_1700_B(e);
                    break;
                }
                case "\u0418\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c": {
                    this.h_1847_R.n_1700_B(e);
                    break;
                }
                case "\u0417\u0435\u043b\u044c\u044f": {
                    this.multiplayerClientSuggestionProvider.n_1700_B(e);
                    break;
                }
                case "\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0438": {
                    this.w_1457_N.n_1700_B(e);
                    break;
                }
                case "\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f \u043e\u043d\u043b\u0430\u0439\u043d": {
                    this.Y_601_j.n_1700_B(e);
                    break;
                }
                case "\u041c\u0435\u0434\u0438\u0430 \u041f\u043b\u0435\u0435\u0440": {
                    break;
                }
                case "\u0420\u0430\u0441\u043f\u0438\u0441\u0430\u043d\u0438\u0435 \u044d\u0432\u0435\u043d\u0442\u043e\u0432": {
                    this.Q_2552_b.n_1700_B(e);
                    break;
                }
                case "\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043a\u0430 \u0441\u0435\u0441\u0441\u0438\u0438": {
                    this.C_2741_M.n_1700_B(e);
                }
            }
        }
    }

    @Y_1740_V
    private void n_1700_B(h_3270_j e) {
        if (e.J_1907_R() != h_3270_j.n_1700_B.multiplayerClientSuggestionProvider) {
            return;
        }
        if (this.w_1484_f() && this.R_4764_Y("\u0417\u0435\u043b\u044c\u044f")) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    private void n_1700_B(h_2739_B e) {
        if (this.C_2741_M != null) {
            this.C_2741_M.n_1700_B(e.J_1907_R());
        }
    }

    @Y_1740_V
    private void n_1700_B(E_3343_g e) {
        if (e.J_1907_R() == E_3343_g.n_1700_B.J_1907_R && this.C_2741_M != null) {
            this.C_2741_M.n_1700_B();
        }
    }

    @Y_1740_V
    private void n_1700_B(Q_2753_H e) {
        if (!e.J_1907_R()) {
            return;
        }
        if (!(e.G_564_y() instanceof ClientboundChatPacket)) {
            return;
        }
        ClientboundChatPacket chatPacket = (ClientboundChatPacket)e.G_564_y();
        String message = chatPacket.J_1907_R().getString();
        String cleanMessage = D_4024_W.n_1700_B(message);
        if (this.R_4764_Y("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f \u043e\u043d\u043b\u0430\u0439\u043d") && this.Y_601_j != null) {
            this.Y_601_j.n_1700_B(message);
        }
        if (this.R_4764_Y("\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u044f")) {
            String playerName = Interface.c_3005_b.Y_259_p != null ? Interface.c_3005_b.Y_259_p.O_1309_Q().getString() : "";
            D_1410_T.n_1700_B(cleanMessage, playerName);
        }
    }

    private boolean R_4764_Y(String elementName) {
        Boolean value = oknaOptions.J_1907_R(elementName);
        return value == null || value != false;
    }

    public Interface() {
        super("Interface", ModuleCategory.R_4764_Y);
        J_3635_s targetHudDrug = new J_3635_s("\u0418\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u044f \u043e \u0446\u0435\u043b\u0438", 4.0f, 100.0f, t_148_a, true);
        J_3635_s keybinds = new J_3635_s("\u0413\u043e\u0440\u044f\u0447\u0438\u0435 \u043a\u043b\u0430\u0432\u0438\u0448\u0438", 297.0f, 100.0f, t_148_a, true);
        J_3635_s arrayList = new J_3635_s("\u0410\u0440\u0440\u0430\u0439 \u043b\u0438\u0441\u0442", (float)c_3005_b.RealmsServerPing().Q_4569_t() / 2.0f - 70.0f, (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f - 40.0f, t_148_a, true);
        J_3635_s inventory = new J_3635_s("\u0418\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c", 4.0f, 34.0f, t_148_a, true);
        J_3635_s potions = new J_3635_s("\u0417\u0435\u043b\u044c\u044f", 357.0f, 100.0f, t_148_a, true);
        J_3635_s staff = new J_3635_s("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f \u043e\u043d\u043b\u0430\u0439\u043d", 447.0f, 100.0f, t_148_a, true);
        J_3635_s cooldowns = new J_3635_s("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0438", 217.0f, 85.0f, t_148_a, true);
        J_3635_s watermarkDrag = new J_3635_s("\u041b\u043e\u0433\u043e\u0442\u0438\u043f", 4.0f, 4.0f, t_148_a, false);
        J_3635_s notification = new J_3635_s("\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u044f", (float)c_3005_b.RealmsServerPing().Q_4569_t() / 2.0f, (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f + 21.0f, t_148_a, false);
        J_3635_s mediaplayerDrag = new J_3635_s("\u041c\u0435\u0434\u0438\u0430 \u041f\u043b\u0435\u0435\u0440", 4.0f, 100.0f, t_148_a, true);
        k_2293_S = new J_3635_s("\u0410\u0439\u0442\u0435\u043c \u0425\u0435\u043b\u043f\u0435\u0440", 4.0f, 200.0f, t_148_a, true);
        J_3635_s eventsDrag = new J_3635_s("\u0420\u0430\u0441\u043f\u0438\u0441\u0430\u043d\u0438\u0435 \u044d\u0432\u0435\u043d\u0442\u043e\u0432", 4.0f, 300.0f, t_148_a, true);
        J_3635_s sessionInfoDrag = new J_3635_s("\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043a\u0430 \u0441\u0435\u0441\u0441\u0438\u0438", 4.0f, 400.0f, t_148_a, true);
        this.s_956_w = new z_283_n(targetHudDrug);
        this.u_2550_I = new a_2727_J(keybinds);
        this.M_588_G = new m_396_H(arrayList);
        this.h_1847_R = new N_1833_W(inventory);
        this.Q_4569_t = new q_4124_m();
        this.M_182_A = new a_1887_j();
        this.P_4830_p = new n_2412_y(watermarkDrag);
        this.t_1786_h = new D_1410_T(notification);
        this.multiplayerClientSuggestionProvider = new Y_3623_f(potions);
        this.w_1457_N = new S_234_U(cooldowns);
        this.Y_601_j = new Y_4293_u(staff);
        this.Q_2552_b = new B_707_U(eventsDrag);
        this.C_2741_M = new z_4066_l(sessionInfoDrag);
        inventory.n_1700_B(N_1833_W.n_1700_B, N_1833_W.J_1907_R, N_1833_W.R_4764_Y, N_1833_W.G_564_y, N_1833_W.P_1922_E);
        keybinds.n_1700_B(a_2727_J.n_1700_B, a_2727_J.J_1907_R, a_2727_J.R_4764_Y, a_2727_J.G_564_y, a_2727_J.P_1922_E, a_2727_J.u_1723_Y);
        arrayList.n_1700_B(((List)m_396_H.n_1700_B.J_1907_R()).toArray(new Setting[0]));
        cooldowns.n_1700_B(S_234_U.n_1700_B, S_234_U.J_1907_R, S_234_U.R_4764_Y, S_234_U.G_564_y, S_234_U.P_1922_E);
        staff.n_1700_B(Y_4293_u.n_1700_B, Y_4293_u.J_1907_R, Y_4293_u.R_4764_Y, Y_4293_u.G_564_y, Y_4293_u.P_1922_E, Y_4293_u.u_1723_Y, Y_4293_u.v_4262_N);
        potions.n_1700_B(Y_3623_f.n_1700_B, Y_3623_f.J_1907_R, Y_3623_f.R_4764_Y, Y_3623_f.G_564_y, Y_3623_f.P_1922_E, Y_3623_f.u_1723_Y);
        notification.n_1700_B(D_1410_T.n_1700_B, D_1410_T.J_1907_R, D_1410_T.R_4764_Y, D_1410_T.G_564_y, D_1410_T.P_1922_E, D_1410_T.u_1723_Y, D_1410_T.v_4262_N, D_1410_T.w_1484_f, D_1410_T.t_148_a, D_1410_T.s_956_w);
        targetHudDrug.n_1700_B(z_283_n.n_1700_B, z_283_n.J_1907_R, z_283_n.R_4764_Y, z_283_n.G_564_y, z_283_n.P_1922_E, z_283_n.u_1723_Y, z_283_n.w_1484_f, z_283_n.t_148_a, z_283_n.s_956_w, z_283_n.u_2550_I);
        watermarkDrag.n_1700_B(n_2412_y.n_1700_B, n_2412_y.J_1907_R, n_2412_y.R_4764_Y, n_2412_y.G_564_y, n_2412_y.P_1922_E, n_2412_y.u_1723_Y, n_2412_y.v_4262_N, n_2412_y.w_1484_f, n_2412_y.t_148_a, n_2412_y.s_956_w, n_2412_y.u_2550_I, n_2412_y.M_588_G, n_2412_y.P_4830_p);
        mediaplayerDrag.n_1700_B(R_4688_l.v_4262_N, R_4688_l.w_1484_f, R_4688_l.t_148_a, R_4688_l.s_956_w, R_4688_l.u_2550_I, R_4688_l.M_588_G);
        k_2293_S.n_1700_B(ServerHelper.v_4262_N, ServerHelper.w_1484_f, ServerHelper.t_148_a, ServerHelper.s_956_w, ServerHelper.u_2550_I);
        sessionInfoDrag.n_1700_B(z_4066_l.n_1700_B, z_4066_l.J_1907_R, z_4066_l.R_4764_Y, z_4066_l.G_564_y, z_4066_l.P_1922_E);
        this.addSettings(oknaOptions, m_396_H.n_1700_B, v_4262_N, w_1484_f);
    }

    private void J_1907_R(b_3528_u.R_4764_Y e) {
        if (Interface.c_3005_b.Y_259_p == null) {
            return;
        }
        int totemCount = u_1934_K.v_4262_N(Items.N_81_X);
        if (totemCount <= 0) {
            return;
        }
        int screenWidth = c_3005_b.RealmsServerPing().Q_4569_t();
        int screenHeight = c_3005_b.RealmsServerPing().M_182_A();
        int cx = screenWidth / 2;
        int cy = screenHeight / 2;
        float scale = 0.75f;
        int x = cx + 4;
        int y = cy - 15;
        Z_1993_T stack = new Z_1993_T(Items.N_81_X, totemCount);
        c_4037_x.Y_601_j();
        c_4037_x.v_4276_D();
        c_4037_x.J_1907_R(scale, scale, 1.0f);
        int sx = Math.round((float)x / scale);
        int sy = Math.round((float)y / scale);
        c_3005_b.r_715_M().n_1700_B(stack, sx, sy);
        c_3005_b.r_715_M().n_1700_B(Interface.c_3005_b.t_148_a, stack, sx, sy);
        c_4037_x.d_2461_k();
        c_4037_x.Y_259_p();
    }
}



