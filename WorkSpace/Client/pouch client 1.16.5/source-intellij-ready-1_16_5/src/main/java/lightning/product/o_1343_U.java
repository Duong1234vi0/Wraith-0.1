/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.R_1148_E;
import lightning.product.Z_1993_T;
import lightning.product.Z_256_c;
import lightning.product.MinecraftClient;
import lightning.product.c_1608_O;
import lightning.product.m_2594_d;
import lightning.product.n_3932_q;
import lightning.product.ServerHelper;
import lightning.product.ClientBootstrap;
import lightning.product.q_1613_l;
import lightning.product.KeyBindSetting;
import lightning.product.q_3386_W;
import lightning.product.AttackAura;
import lightning.product.r_4811_B;
import lightning.product.u_1934_K;
import lightning.product.v_1900_v;

public class o_1343_U
extends n_3932_q {
    private final MinecraftClient n_1700_B = MinecraftClient.A_4115_X();
    private final List<R_1148_E> J_1907_R = new ArrayList<R_1148_E>();
    private final Map<R_1148_E, KeyBindSetting> R_4764_Y = new HashMap<R_1148_E, KeyBindSetting>();
    private final KeyBindSetting G_564_y = new KeyBindSetting("\u041e\u0442\u043a\u0440\u044b\u0442\u044c GUI", () -> {
        ServerHelper manager = (ServerHelper)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ServerHelper.class);
        return manager != null && manager.Q_4569_t().equals("Custom");
    });
    private q_1613_l P_1922_E;
    private boolean u_1723_Y = false;
    private boolean v_4262_N = false;

    public void n_1700_B(List<R_1148_E> configs) {
        this.J_1907_R.clear();
        this.J_1907_R.addAll(configs);
        this.P_4830_p();
    }

    private void P_4830_p() {
        HashMap<R_1148_E, KeyBindSetting> oldBinds = new HashMap<R_1148_E, KeyBindSetting>(this.R_4764_Y);
        this.R_4764_Y.clear();
        for (R_1148_E config : this.J_1907_R) {
            if (config.R_4764_Y() == -1) continue;
            KeyBindSetting bind = (KeyBindSetting)oldBinds.get(config);
            if (bind == null) {
                bind = new KeyBindSetting(config.J_1907_R(), () -> {
                    ServerHelper manager = (ServerHelper)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ServerHelper.class);
                    return manager != null && manager.Q_4569_t().equals("Custom");
                });
            }
            bind.n_1700_B(config.R_4764_Y());
            this.R_4764_Y.put(config, bind);
        }
    }

    public List<R_1148_E> w_1484_f() {
        return new ArrayList<R_1148_E>(this.J_1907_R);
    }

    @Override
    public String J_1907_R() {
        return "C";
    }

    @Override
    public String R_4764_Y() {
        return "C";
    }

    @Override
    public List<KeyBindSetting> G_564_y() {
        ArrayList<KeyBindSetting> binds = new ArrayList<KeyBindSetting>();
        binds.add(this.G_564_y);
        binds.addAll(this.R_4764_Y.values());
        return binds;
    }

    public Map<R_1148_E, KeyBindSetting> t_148_a() {
        return this.R_4764_Y;
    }

    @Override
    public List<q_3386_W.n_1700_B> n_1700_B(boolean onlyBar) {
        ArrayList<q_3386_W.n_1700_B> renderItems = new ArrayList<q_3386_W.n_1700_B>();
        for (R_1148_E config : this.J_1907_R) {
            String itemStatus;
            if (config.R_4764_Y() == -1) continue;
            if (!config.t_148_a().isEmpty()) {
                String potionStatus;
                c_1608_O itemSetting = Z_256_c.J_1907_R(config.t_148_a());
                if (itemSetting == null) continue;
                int potionSlot = this.n_1700_B(itemSetting);
                if (potionSlot == -1) {
                    potionStatus = "\u2014";
                } else if (onlyBar && potionSlot >= 9) {
                    potionStatus = "\u2014";
                } else {
                    q_1613_l item = itemSetting.t_148_a().J_1907_R();
                    if (this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(item)) {
                        float cooldown = this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(item, this.n_1700_B.RealmsClientConfig());
                        float remainingSeconds = cooldown * 20.0f;
                        potionStatus = String.format("%.0f\u0441", Float.valueOf(remainingSeconds));
                    } else {
                        potionStatus = "\u2713";
                    }
                }
                String displayName = config.t_148_a();
                String shortName = displayName.length() > 8 ? displayName.substring(0, 8) : displayName;
                q_3386_W.n_1700_B renderData = new q_3386_W.n_1700_B(displayName, shortName, potionStatus, itemSetting.t_148_a().J_1907_R());
                renderData.n_1700_B(config.R_4764_Y());
                renderItems.add(renderData);
                continue;
            }
            int itemSlot = u_1934_K.n_1700_B(config.n_1700_B());
            if (itemSlot == -1) {
                itemStatus = "\u2014";
            } else if (onlyBar && itemSlot >= 9) {
                itemStatus = "\u2014";
            } else if (this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(config.n_1700_B())) {
                float cooldown = this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(config.n_1700_B(), this.n_1700_B.RealmsClientConfig());
                float remainingSeconds = cooldown * 20.0f;
                itemStatus = String.format("%.0f\u0441", Float.valueOf(remainingSeconds));
            } else {
                itemStatus = "\u2713";
            }
            String shortName = config.J_1907_R().length() > 8 ? config.J_1907_R().substring(0, 8) : config.J_1907_R();
            q_3386_W.n_1700_B renderData = new q_3386_W.n_1700_B(config.J_1907_R(), shortName, itemStatus, config.n_1700_B());
            renderData.n_1700_B(config.R_4764_Y());
            renderItems.add(renderData);
        }
        return renderItems;
    }

    @Override
    public boolean n_1700_B(int keyCode) {
        if ((Integer)this.G_564_y.J_1907_R() != -1 && (Integer)this.G_564_y.J_1907_R() == keyCode) {
            this.n_1700_B.n_1700_B(new m_2594_d(this));
            return true;
        }
        for (R_1148_E config : this.J_1907_R) {
            if (config.R_4764_Y() == -1 || config.R_4764_Y() != keyCode) continue;
            this.n_1700_B(config);
            return true;
        }
        if (this.n_1700_B.Y_259_p != null && this.n_1700_B.Y_259_p.q_2307_F() && keyCode == 344) {
            for (R_1148_E config : this.J_1907_R) {
                if (!config.P_1922_E()) continue;
                this.n_1700_B(config);
                return true;
            }
        }
        return false;
    }

    private int n_1700_B(c_1608_O itemSetting) {
        if (this.n_1700_B == null || this.n_1700_B.Y_259_p == null || itemSetting == null) {
            return -1;
        }
        q_1613_l targetItem = itemSetting.t_148_a().J_1907_R();
        List<String> requiredNbtParams = itemSetting.h_1847_R();
        String itemName = itemSetting.n_1700_B();
        for (int i = 0; i < 36; ++i) {
            boolean nameMatches;
            Z_1993_T stack = this.n_1700_B.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B()) continue;
            String cleanDisplayName = o_1343_U.n_1700_B(stack.multiplayerClientSuggestionProvider().getString());
            String cleanItemName = o_1343_U.n_1700_B(itemName);
            q_1613_l stackItem = stack.J_1907_R();
            boolean bl = nameMatches = cleanDisplayName.contains(cleanItemName) || cleanItemName.contains(cleanDisplayName) || cleanDisplayName.equals(cleanItemName);
            if (!nameMatches || !requiredNbtParams.isEmpty() && !this.n_1700_B(stack, requiredNbtParams)) continue;
            return i;
        }
        return -1;
    }

    private boolean n_1700_B(Z_1993_T stack, List<String> requiredParams) {
        if (!stack.h_1847_R() || requiredParams.isEmpty()) {
            return requiredParams.isEmpty();
        }
        for (String param : requiredParams) {
            if (stack.Q_4569_t().P_1922_E(param)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean P_1922_E() {
        return this.u_1723_Y && this.P_1922_E != null;
    }

    public void s_956_w() {
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

    private void n_1700_B(R_1148_E config) {
        if (!config.t_148_a().isEmpty()) {
            c_1608_O itemSetting = Z_256_c.J_1907_R(config.t_148_a());
            if (itemSetting == null) {
                v_1900_v.n_1700_B("\u00a7c" + config.t_148_a() + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u043a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u0438", new Object[0]);
                return;
            }
            int slot = this.n_1700_B(itemSetting);
            if (slot == -1) {
                v_1900_v.n_1700_B("\u00a7c" + config.t_148_a() + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
                return;
            }
            if (this.v_4262_N && slot >= 9) {
                v_1900_v.n_1700_B("\u00a7c" + config.t_148_a() + " \u043d\u0435 \u0432 \u0445\u043e\u0442\u0431\u0430\u0440\u0435", new Object[0]);
                return;
            }
            q_1613_l item = itemSetting.t_148_a().J_1907_R();
            if (this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(item)) {
                float cooldown = this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(item, this.n_1700_B.RealmsClientConfig());
                float remainingSeconds = cooldown * 20.0f;
                v_1900_v.n_1700_B("\u00a7c" + config.t_148_a() + " \u0437\u0430\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u0430\u043d \u0435\u0449\u0435 " + String.format("%.1f", Float.valueOf(remainingSeconds)) + " \u0441\u0435\u043a", new Object[0]);
                return;
            }
            this.u_1723_Y = true;
            this.P_1922_E = item;
            return;
        }
        q_1613_l item = config.n_1700_B();
        int slot = u_1934_K.n_1700_B(item);
        if (slot == -1) {
            v_1900_v.n_1700_B("\u00a7c" + config.J_1907_R() + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
            return;
        }
        if (this.v_4262_N && slot >= 9) {
            v_1900_v.n_1700_B("\u00a7c" + config.J_1907_R() + " \u043d\u0435 \u0432 \u0445\u043e\u0442\u0431\u0430\u0440\u0435", new Object[0]);
            return;
        }
        if (this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(item)) {
            float cooldown = this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(item, this.n_1700_B.RealmsClientConfig());
            float remainingSeconds = cooldown * 20.0f;
            v_1900_v.n_1700_B("\u00a7c" + config.J_1907_R() + " \u043d\u0430 \u043a\u0443\u043b\u0434\u0430\u0443\u043d\u0435", new Object[0]);
            return;
        }
        if (config.G_564_y()) {
            r_4811_B target;
            AttackAura attackAura = (AttackAura)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AttackAura.class);
            r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
            if (target == null) {
                v_1900_v.n_1700_B("\u00a7c\u041d\u0435\u0442 \u0446\u0435\u043b\u0438 \u0434\u043b\u044f \u043a\u0438\u0434\u0430\u043d\u0438\u044f", new Object[0]);
                return;
            }
        }
        if (config.u_1723_Y()) {
            if (slot < 9) {
                this.n_1700_B.Y_259_p.l_1268_F.G_564_y = slot;
                this.n_1700_B.w_1457_N.syncCurrentPlayItem();
            } else {
                ServerHelper manager = (ServerHelper)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ServerHelper.class);
                if (manager != null) {
                    manager.J_1907_R(slot);
                    return;
                }
            }
        }
        this.u_1723_Y = true;
        this.P_1922_E = item;
    }

    public int u_2550_I() {
        if (this.P_1922_E == null) {
            return -1;
        }
        return u_1934_K.n_1700_B(this.P_1922_E);
    }

    public KeyBindSetting M_588_G() {
        return this.G_564_y;
    }
}



