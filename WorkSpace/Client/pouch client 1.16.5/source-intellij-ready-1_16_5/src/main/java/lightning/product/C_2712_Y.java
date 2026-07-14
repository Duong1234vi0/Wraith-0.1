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

public class C_2712_Y
extends n_3932_q {
    private final MinecraftClient n_1700_B = MinecraftClient.A_4115_X();
    private final List<KeyBindSetting> J_1907_R;
    private final String[] R_4764_Y = new String[]{"\u0414\u0435\u0437\u043e\u0440\u0438\u0435\u043d\u0442\u0430\u0446\u0438\u044f", "\u042f\u0432\u043d\u0430\u044f \u043f\u044b\u043b\u044c", "\u041f\u043b\u0430\u0441\u0442", "\u0422\u0440\u0430\u043f\u043a\u0430 Spooky", "\u041e\u0433\u043d\u0435\u043d\u043d\u044b\u0439 \u0441\u043c\u0435\u0440\u0447", "\u0421\u043d\u0435\u0436\u043e\u043a \u0437\u0430\u043c\u043e\u0440\u043e\u0437\u043a\u0430", "\u0411\u043e\u0436\u044c\u044f \u0430\u0443\u0440\u0430"};
    private final String[] G_564_y = new String[]{"\u0434\u0435\u0437\u043e\u0440\u0438\u0435\u043d\u0442\u0430\u0446", "\u044f\u0432\u043d\u0430\u044f \u043f\u044b\u043b\u044c", "\u043f\u043b\u0430\u0441\u0442", "\u0442\u0440\u0430\u043f\u043a\u0430", "\u0441\u043c\u0435\u0440\u0447", "\u0437\u0430\u043c\u043e\u0440\u043e\u0437\u043a", "\u0431\u043e\u0436\u044c\u044f \u0430\u0443\u0440\u0430"};
    private final q_1613_l[] P_1922_E = new q_1613_l[]{Items.V_1824_v, Items.o_3456_E, Items.MinMaxBounds, Items.m_396_H, Items.CraftingTableBlock, Items.i_770_g, Items.RotatedPillarBlock};
    private String u_1723_Y;
    private String v_4262_N;
    private q_1613_l w_1484_f;
    private boolean t_148_a = false;
    private boolean s_956_w = false;

    public C_2712_Y(List<KeyBindSetting> binds) {
        this.J_1907_R = binds;
    }

    @Override
    public String J_1907_R() {
        return "SpookyTime";
    }

    @Override
    public String R_4764_Y() {
        return "Spooky";
    }

    @Override
    public List<KeyBindSetting> G_564_y() {
        return this.J_1907_R;
    }

    @Override
    public List<q_3386_W.n_1700_B> n_1700_B(boolean onlyBar) {
        ArrayList<q_3386_W.n_1700_B> renderItems = new ArrayList<q_3386_W.n_1700_B>();
        for (int i = 0; i < this.R_4764_Y.length && i < this.J_1907_R.size(); ++i) {
            String status;
            int slot = this.R_4764_Y(this.G_564_y[i]);
            if (slot == -1) {
                status = "\u2014";
            } else if (onlyBar && slot >= 9) {
                status = "\u2014";
            } else if (this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(this.P_1922_E[i])) {
                float cooldown = this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(this.P_1922_E[i], this.n_1700_B.RealmsClientConfig());
                float remainingSeconds = cooldown * 20.0f;
                status = String.format("%.0f\u0441", Float.valueOf(remainingSeconds));
            } else {
                status = "\u2713";
            }
            int bindKey = (Integer)this.J_1907_R.get(i).J_1907_R();
            String shortName = this.R_4764_Y[i].length() > 8 ? this.R_4764_Y[i].substring(0, 8) : this.R_4764_Y[i];
            renderItems.add(new q_3386_W.n_1700_B(this.R_4764_Y[i], shortName, status, this.P_1922_E[i]).n_1700_B(bindKey));
        }
        return renderItems;
    }

    @Override
    public boolean n_1700_B(int keyCode) {
        for (int i = 0; i < this.R_4764_Y.length && i < this.J_1907_R.size(); ++i) {
            if ((Integer)this.J_1907_R.get(i).J_1907_R() == -1 || (Integer)this.J_1907_R.get(i).J_1907_R() != keyCode) continue;
            this.n_1700_B(this.R_4764_Y[i], this.G_564_y[i], this.P_1922_E[i]);
            return true;
        }
        return false;
    }

    @Override
    public boolean P_1922_E() {
        return this.t_148_a && this.v_4262_N != null;
    }

    public void w_1484_f() {
        this.t_148_a = false;
        this.u_1723_Y = null;
        this.v_4262_N = null;
        this.w_1484_f = null;
    }

    @Override
    public void u_1723_Y() {
        this.t_148_a = false;
        this.u_1723_Y = null;
        this.v_4262_N = null;
        this.w_1484_f = null;
    }

    public String t_148_a() {
        return this.v_4262_N;
    }

    public q_1613_l s_956_w() {
        return this.w_1484_f;
    }

    public void J_1907_R(boolean onlyBar) {
        this.s_956_w = onlyBar;
    }

    private void n_1700_B(String itemName, String searchQuery, q_1613_l item) {
        int slot = this.R_4764_Y(searchQuery);
        if (slot == -1) {
            v_1900_v.n_1700_B("\u00a7c" + itemName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
            return;
        }
        if (this.s_956_w && slot >= 9) {
            v_1900_v.n_1700_B("\u00a7c" + itemName + " \u043d\u0435 \u0432 \u0445\u043e\u0442\u0431\u0430\u0440\u0435", new Object[0]);
            return;
        }
        if (this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(item)) {
            v_1900_v.n_1700_B("\u00a7c" + itemName + " \u043d\u0430 \u043a\u0443\u043b\u0434\u0430\u0443\u043d\u0435", new Object[0]);
            return;
        }
        this.t_148_a = true;
        this.u_1723_Y = itemName;
        this.v_4262_N = searchQuery;
        this.w_1484_f = item;
    }

    private int R_4764_Y(String searchQuery) {
        if (this.n_1700_B == null || this.n_1700_B.Y_259_p == null || searchQuery == null || searchQuery.isEmpty()) {
            return -1;
        }
        String needle = searchQuery.toLowerCase();
        for (int i = 0; i < 36; ++i) {
            String cleanName;
            Z_1993_T stack = this.n_1700_B.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || !(cleanName = C_2712_Y.n_1700_B(stack.multiplayerClientSuggestionProvider().getString())).contains(needle)) continue;
            return i;
        }
        return -1;
    }

    public int u_2550_I() {
        if (this.v_4262_N == null) {
            return -1;
        }
        return this.R_4764_Y(this.v_4262_N);
    }
}



