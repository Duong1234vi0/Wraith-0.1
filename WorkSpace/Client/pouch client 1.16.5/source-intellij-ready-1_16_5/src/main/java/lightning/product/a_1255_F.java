/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftClient;
import lightning.product.n_3932_q;
import lightning.product.q_1613_l;
import lightning.product.KeyBindSetting;
import lightning.product.q_3386_W;
import lightning.product.Items;
import lightning.product.v_1900_v;

public class a_1255_F
extends n_3932_q {
    private final MinecraftClient n_1700_B = MinecraftClient.A_4115_X();
    private final List<KeyBindSetting> J_1907_R;
    private String R_4764_Y;
    private q_1613_l G_564_y;
    private boolean P_1922_E = false;
    private boolean u_1723_Y = false;
    private final int[] v_4262_N = new int[12];
    private long w_1484_f = 0L;
    private static final long t_148_a = 100L;
    private List<q_3386_W.n_1700_B> s_956_w;
    private boolean u_2550_I = false;
    private static final String[] M_588_G = new String[]{"\u041e\u0431\u044b\u0447\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u0425\u043e\u0440\u043e\u0448\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u041e\u0442\u043b\u0438\u0447\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u042d\u043f\u0438\u0447\u0435\u0441\u043a\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u041b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u041b\u0438\u0432\u0430\u043b\u043a\u0430", "\u0410\u043c\u0443\u043b\u0435\u0442 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u0438", "\u0421\u043a\u043e\u043f\u043b\u0435\u043d\u0438\u0435 \u0441\u0432\u0435\u0442\u0430", "\u0412\u043e\u043b\u043d\u0430 \u043e\u0433\u043d\u044f", "\u041f\u043e\u0440\u043e\u0448\u043e\u043a \u0432\u043e\u0437\u0432\u0440\u0430\u0449\u0435\u043d\u0438\u044f", "\u041f\u043e\u0440\u043e\u0448\u043e\u043a \u0434\u0435\u0437\u043e\u0440\u0433\u0430\u043d\u0438\u0437\u0430\u0446\u0438\u0438", "\u0420\u0430\u0437\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044c \u0421\u0442\u043e\u0435\u043a"};
    private static final String[] P_4830_p = new String[M_588_G.length];
    private static final String[] h_1847_R;
    private static final q_1613_l[] Q_4569_t;
    private static final int[] M_182_A;

    public a_1255_F(List<KeyBindSetting> binds) {
        this.J_1907_R = binds;
        for (int i = 0; i < this.v_4262_N.length; ++i) {
            this.v_4262_N[i] = -1;
        }
    }

    @Override
    public String J_1907_R() {
        return "Sunrise";
    }

    @Override
    public String R_4764_Y() {
        return "Sunrise";
    }

    @Override
    public List<KeyBindSetting> G_564_y() {
        return this.J_1907_R;
    }

    public void J_1907_R(boolean onlyBar) {
        this.u_1723_Y = onlyBar;
    }

    private void P_4830_p() {
        long now = System.currentTimeMillis();
        if (now - this.w_1484_f < 100L) {
            return;
        }
        this.w_1484_f = now;
        if (this.n_1700_B == null || this.n_1700_B.Y_259_p == null) {
            return;
        }
        for (int i = 0; i < this.v_4262_N.length; ++i) {
            this.v_4262_N[i] = -1;
        }
        for (int slot = 0; slot < 36; ++slot) {
            Z_1993_T stack = this.n_1700_B.Y_259_p.l_1268_F.s_956_w(slot);
            if (stack.n_1700_B()) continue;
            String cleanName = a_1255_F.n_1700_B(stack.multiplayerClientSuggestionProvider().getString());
            for (int i = 0; i < P_4830_p.length; ++i) {
                if (this.v_4262_N[i] != -1 || !cleanName.contains(P_4830_p[i])) continue;
                this.v_4262_N[i] = slot;
            }
        }
        this.s_956_w = null;
    }

    private static String R_4764_Y(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        StringBuilder result = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (c == '\u00a7' && i + 1 < input.length()) {
                ++i;
                continue;
            }
            result.append(c);
        }
        return result.toString();
    }

    @Override
    public List<q_3386_W.n_1700_B> n_1700_B(boolean onlyBar) {
        this.P_4830_p();
        if (this.s_956_w != null && this.u_2550_I == onlyBar) {
            return this.s_956_w;
        }
        this.u_2550_I = onlyBar;
        ArrayList<q_3386_W.n_1700_B> renderItems = new ArrayList<q_3386_W.n_1700_B>(M_588_G.length);
        int bindSize = this.J_1907_R.size();
        for (int i = 0; i < M_588_G.length && i < bindSize; ++i) {
            int slot = this.v_4262_N[i];
            String status = slot == -1 ? "\u2014" : (onlyBar && slot >= 9 ? "\u2014" : "\u2713");
            q_3386_W.n_1700_B data = new q_3386_W.n_1700_B(M_588_G[i], h_1847_R[i], status, Q_4569_t[i]);
            data.n_1700_B((Integer)this.J_1907_R.get(i).J_1907_R());
            data.J_1907_R(M_182_A[i]);
            renderItems.add(data);
        }
        this.s_956_w = renderItems;
        return renderItems;
    }

    @Override
    public boolean n_1700_B(int keyCode) {
        int bindSize = this.J_1907_R.size();
        for (int i = 0; i < M_588_G.length && i < bindSize; ++i) {
            int bind = (Integer)this.J_1907_R.get(i).J_1907_R();
            if (bind == -1 || bind != keyCode) continue;
            this.J_1907_R(i);
            return true;
        }
        return false;
    }

    @Override
    public boolean P_1922_E() {
        return this.P_1922_E && this.R_4764_Y != null;
    }

    public void w_1484_f() {
        this.P_1922_E = false;
        this.R_4764_Y = null;
        this.G_564_y = null;
    }

    @Override
    public void u_1723_Y() {
        this.P_1922_E = false;
        this.R_4764_Y = null;
        this.G_564_y = null;
        this.s_956_w = null;
    }

    public String t_148_a() {
        return this.R_4764_Y;
    }

    public q_1613_l s_956_w() {
        return this.G_564_y;
    }

    public boolean u_2550_I() {
        return this.P_1922_E;
    }

    public int M_588_G() {
        if (this.R_4764_Y == null) {
            return -1;
        }
        for (int i = 0; i < M_588_G.length; ++i) {
            if (!M_588_G[i].equals(this.R_4764_Y)) continue;
            this.P_4830_p();
            return this.v_4262_N[i];
        }
        return -1;
    }

    private void J_1907_R(int index) {
        this.P_4830_p();
        int slot = this.v_4262_N[index];
        if (slot == -1) {
            v_1900_v.n_1700_B("\u00a7c" + M_588_G[index] + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
            return;
        }
        if (this.u_1723_Y && slot >= 9) {
            v_1900_v.n_1700_B("\u00a7c" + M_588_G[index] + " \u043d\u0435 \u0432 \u0445\u043e\u0442\u0431\u0430\u0440\u0435", new Object[0]);
            return;
        }
        this.P_1922_E = true;
        this.G_564_y = Q_4569_t[index];
        this.R_4764_Y = M_588_G[index];
    }

    static {
        for (int i = 0; i < M_588_G.length; ++i) {
            a_1255_F.P_4830_p[i] = M_588_G[i].toLowerCase();
        }
        h_1847_R = new String[]{"\u041e\u0422", "\u0425\u0422", "\u041e\u0442\u0422", "\u042d\u0422", "\u041b\u0422", "\u041b\u0438\u0432", "\u0410\u0422", "\u0421\u0421", "\u0412\u041e", "\u041f\u0412", "\u041f\u0414", "\u0420\u0421"};
        Q_4569_t = new q_1613_l[]{Items.PacketCriticals, Items.R_3213_X, Items.H_1475_K, Items.k_1608_N, Items.N_2266_w, Items.H_274_C, Items.v_2746_S, Items.AdvancementList, Items.CraftingTableBlock, Items.C_3528_u, Items.Easing, Items.A_2487_t};
        M_182_A = new int[]{-930391509, -939488373, -931135360, -922812161, -922757376, -922746881, -930468894, -922747136, -922794752, -922776576, -936234446, -925100996};
    }
}



