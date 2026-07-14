/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.MinecraftClient;
import lightning.product.n_3932_q;
import lightning.product.q_1613_l;
import lightning.product.KeyBindSetting;
import lightning.product.q_3386_W;
import lightning.product.Items;
import lightning.product.u_1934_K;
import lightning.product.v_1900_v;

public class p_4879_r
extends n_3932_q {
    private final MinecraftClient n_1700_B = MinecraftClient.A_4115_X();
    private final List<KeyBindSetting> J_1907_R;
    private final String[] R_4764_Y = new String[]{"\u0422\u0440\u0430\u043f\u0430", "\u0414\u0435\u0437\u043e\u0440\u0435\u043d\u0442", "\u041f\u043b\u0430\u0441\u0442", "\u042f\u0432\u043d\u0430\u044f \u043f\u044b\u043b\u044c", "\u0411\u043e\u0436\u044c\u044f \u0430\u0443\u0440\u0430", "\u041e\u0433\u043d\u0435\u043d \u0441\u043c\u0435\u0440\u0447", "\u0421\u043d\u0435\u0436\u043e\u043a"};
    private final q_1613_l[] G_564_y = new q_1613_l[]{Items.m_396_H, Items.V_1824_v, Items.MinMaxBounds, Items.o_3456_E, Items.RotatedPillarBlock, Items.CraftingTableBlock, Items.i_770_g};
    private q_1613_l P_1922_E;
    private boolean u_1723_Y = false;
    private boolean v_4262_N = false;

    public p_4879_r(List<KeyBindSetting> binds) {
        this.J_1907_R = binds;
    }

    @Override
    public String J_1907_R() {
        return "FunTime";
    }

    @Override
    public String R_4764_Y() {
        return "FunTime";
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
            int slot = u_1934_K.n_1700_B(this.G_564_y[i]);
            if (slot == -1) {
                status = "\u2014";
            } else if (onlyBar && slot >= 9) {
                status = "\u2014";
            } else if (this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(this.G_564_y[i])) {
                float cooldown = this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(this.G_564_y[i], this.n_1700_B.RealmsClientConfig());
                float remainingSeconds = cooldown * 20.0f;
                status = String.format("%.0f\u0441", Float.valueOf(remainingSeconds));
            } else {
                status = "\u2713";
            }
            int bindKey = (Integer)this.J_1907_R.get(i).J_1907_R();
            String shortName = this.R_4764_Y[i].length() > 8 ? this.R_4764_Y[i].substring(0, 8) : this.R_4764_Y[i];
            renderItems.add(new q_3386_W.n_1700_B(this.R_4764_Y[i], shortName, status, this.G_564_y[i]).n_1700_B(bindKey));
        }
        return renderItems;
    }

    @Override
    public boolean n_1700_B(int keyCode) {
        for (int i = 0; i < this.R_4764_Y.length && i < this.J_1907_R.size(); ++i) {
            if ((Integer)this.J_1907_R.get(i).J_1907_R() == -1 || (Integer)this.J_1907_R.get(i).J_1907_R() != keyCode) continue;
            this.n_1700_B(this.G_564_y[i]);
            return true;
        }
        return false;
    }

    @Override
    public boolean P_1922_E() {
        return this.u_1723_Y && this.P_1922_E != null;
    }

    public void w_1484_f() {
        this.u_1723_Y = false;
        this.P_1922_E = null;
    }

    @Override
    public void u_1723_Y() {
        this.u_1723_Y = false;
        this.P_1922_E = null;
    }

    public void J_1907_R(boolean onlyBar) {
        this.v_4262_N = onlyBar;
    }

    private void n_1700_B(q_1613_l item) {
        int slot = u_1934_K.n_1700_B(item);
        if (slot == -1) {
            v_1900_v.n_1700_B("\u00a7c" + this.J_1907_R(item) + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
            return;
        }
        if (this.v_4262_N && slot >= 9) {
            v_1900_v.n_1700_B("\u00a7c" + this.J_1907_R(item) + " \u043d\u0435 \u0432 \u0445\u043e\u0442\u0431\u0430\u0440\u0435", new Object[0]);
            return;
        }
        if (this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(item)) {
            float cooldown = this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(item, this.n_1700_B.RealmsClientConfig());
            float remainingSeconds = cooldown * 20.0f;
            v_1900_v.n_1700_B("\u00a7c" + this.J_1907_R(item) + " \u043d\u0430 \u043a\u0443\u043b\u0434\u0430\u0443\u043d\u0435", new Object[0]);
            return;
        }
        this.u_1723_Y = true;
        this.P_1922_E = item;
    }

    private String J_1907_R(q_1613_l item) {
        for (int i = 0; i < this.G_564_y.length; ++i) {
            if (this.G_564_y[i] != item) continue;
            return this.R_4764_Y[i];
        }
        return "\u041f\u0440\u0435\u0434\u043c\u0435\u0442";
    }

    public int t_148_a() {
        if (this.P_1922_E == null) {
            return -1;
        }
        return u_1934_K.n_1700_B(this.P_1922_E);
    }
}



