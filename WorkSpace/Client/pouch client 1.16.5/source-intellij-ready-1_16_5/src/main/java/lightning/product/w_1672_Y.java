/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.MutableComponent;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.Y_3462_U;
import lightning.product.Z_1993_T;
import lightning.product.Z_256_c;
import lightning.product.a_408_T;
import lightning.product.MinecraftClient;
import lightning.product.c_1608_O;
import lightning.product.n_3932_q;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.KeyBindSetting;
import lightning.product.q_3386_W;
import lightning.product.Items;
import lightning.product.v_1669_V;
import lightning.product.v_1900_v;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;

public class w_1672_Y
extends n_3932_q {
    private final MinecraftClient n_1700_B = MinecraftClient.A_4115_X();
    private final List<KeyBindSetting> J_1907_R;
    private final BooleanSetting R_4764_Y;
    private final KeyBindSetting G_564_y;
    private String P_1922_E;
    private q_1613_l u_1723_Y;
    private boolean v_4262_N = false;
    private boolean w_1484_f = false;
    private boolean t_148_a = false;
    private boolean s_956_w = false;
    private int u_2550_I = -1;
    private int M_588_G = -1;
    private int P_4830_p = 0;
    private final String[] h_1847_R = new String[]{"\u041f\u0443\u0437\u044b\u0440\u0451\u043a \u043e\u043f\u044b\u0442\u0430", "\u0422\u0440\u0430\u043f\u043a\u0430", "\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", "\u041f\u0440\u043e\u0449\u0430\u043b\u044c\u043d\u044b\u0439 \u0433\u0443\u043b", "\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0448\u0442\u0443\u0447\u043a\u0430", "\u0421\u0442\u0430\u043d", "\u041a\u043e\u043c \u0441\u043d\u0435\u0433\u0430"};
    private final q_1613_l[] Q_4569_t = new q_1613_l[]{Items.s_3084_y, Items.MelonBlock, Items.v_4620_e, Items.FenceGateBlock, Items.CraftingTableBlock, Items.FallingBlock, Items.i_770_g};
    private boolean M_182_A = false;

    public w_1672_Y(List<KeyBindSetting> binds, BooleanSetting antiCrashSetting, KeyBindSetting backpackBind) {
        this.J_1907_R = binds;
        this.R_4764_Y = antiCrashSetting;
        this.G_564_y = backpackBind;
    }

    @Override
    public BooleanSetting v_4262_N() {
        return this.R_4764_Y;
    }

    @Override
    public String J_1907_R() {
        return "HolyWorld";
    }

    @Override
    public String R_4764_Y() {
        return "Holy";
    }

    @Override
    public List<KeyBindSetting> G_564_y() {
        return this.J_1907_R;
    }

    @Override
    public List<q_3386_W.n_1700_B> n_1700_B(boolean onlyBar) {
        ArrayList<q_3386_W.n_1700_B> renderItems = new ArrayList<q_3386_W.n_1700_B>();
        for (int i = 0; i < this.h_1847_R.length && i < this.J_1907_R.size(); ++i) {
            String status;
            int slot;
            c_1608_O itemSetting = Z_256_c.J_1907_R(this.h_1847_R[i]);
            int n = slot = itemSetting != null ? this.n_1700_B(itemSetting) : -1;
            if (slot == -1) {
                status = "\u2014";
            } else if (onlyBar && slot >= 9) {
                status = "\u2014";
            } else if (this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(this.Q_4569_t[i])) {
                float cooldown = this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(this.Q_4569_t[i], this.n_1700_B.RealmsClientConfig());
                float remainingSeconds = cooldown * 20.0f;
                status = String.format("%.0f\u0441", Float.valueOf(remainingSeconds));
            } else {
                status = "\u2713";
            }
            int bindKey = (Integer)this.J_1907_R.get(i).J_1907_R();
            String shortName = this.h_1847_R[i].length() > 8 ? this.h_1847_R[i].substring(0, 8) : this.h_1847_R[i];
            renderItems.add(new q_3386_W.n_1700_B(this.h_1847_R[i], shortName, status, this.Q_4569_t[i]).n_1700_B(bindKey));
        }
        return renderItems;
    }

    @Override
    public boolean n_1700_B(int keyCode) {
        if ((Integer)this.G_564_y.J_1907_R() != -1 && (Integer)this.G_564_y.J_1907_R() == keyCode) {
            this.Q_4569_t();
            return true;
        }
        for (int i = 0; i < this.h_1847_R.length && i < this.J_1907_R.size(); ++i) {
            if ((Integer)this.J_1907_R.get(i).J_1907_R() == -1 || (Integer)this.J_1907_R.get(i).J_1907_R() != keyCode) continue;
            this.n_1700_B(this.h_1847_R[i], this.Q_4569_t[i]);
            return true;
        }
        return false;
    }

    private void Q_4569_t() {
        int slot = this.M_182_A();
        if (slot == -1) {
            v_1900_v.n_1700_B("\u00a7c\u0420\u044e\u043a\u0437\u0430\u043a \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
            return;
        }
        this.u_2550_I = slot;
        this.w_1484_f = true;
        this.t_148_a = false;
    }

    public boolean w_1484_f() {
        if (!(this.w_1484_f || this.t_148_a || this.s_956_w)) {
            return false;
        }
        if (this.w_1484_f) {
            this.M_588_G = this.n_1700_B.Y_259_p.l_1268_F.G_564_y;
            if (this.u_2550_I < 9) {
                this.n_1700_B.Y_259_p.l_1268_F.G_564_y = this.u_2550_I;
                this.n_1700_B.w_1457_N.syncCurrentPlayItem();
            } else {
                this.n_1700_B.w_1457_N.windowClick(this.n_1700_B.Y_259_p.o_1800_r.u_1723_Y, this.u_2550_I, this.M_588_G, a_408_T.R_4764_Y, this.n_1700_B.Y_259_p);
                this.n_1700_B.w_1457_N.syncCurrentPlayItem();
            }
            this.n_1700_B.w_1457_N.processRightClick(this.n_1700_B.Y_259_p, this.n_1700_B.Y_601_j, x_1688_C.n_1700_B);
            this.w_1484_f = false;
            this.t_148_a = true;
            this.P_4830_p = 0;
            return true;
        }
        if (this.t_148_a) {
            if (this.n_1700_B.Y_1740_V == null) {
                this.t_148_a = false;
                this.s_956_w = true;
                this.P_4830_p = 0;
            }
            return true;
        }
        if (this.s_956_w) {
            ++this.P_4830_p;
            if (this.P_4830_p >= 3 && this.n_1700_B.Y_259_p.H_1873_g == this.n_1700_B.Y_259_p.o_1800_r) {
                if (this.u_2550_I >= 9) {
                    this.n_1700_B.w_1457_N.windowClick(this.n_1700_B.Y_259_p.o_1800_r.u_1723_Y, this.u_2550_I, this.M_588_G, a_408_T.R_4764_Y, this.n_1700_B.Y_259_p);
                }
                this.n_1700_B.Y_259_p.l_1268_F.G_564_y = this.M_588_G;
                this.n_1700_B.w_1457_N.syncCurrentPlayItem();
                this.s_956_w = false;
                this.u_2550_I = -1;
                this.M_588_G = -1;
                this.P_4830_p = 0;
            }
            return true;
        }
        return false;
    }

    private int M_182_A() {
        if (this.n_1700_B.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            T_2915_h block;
            Z_1993_T stack = this.n_1700_B.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || !(stack.J_1907_R() instanceof v_1669_V) || !((block = ((v_1669_V)stack.J_1907_R()).v_4262_N()) instanceof Y_3462_U)) continue;
            return i;
        }
        return -1;
    }

    public KeyBindSetting t_148_a() {
        return this.G_564_y;
    }

    @Override
    public boolean P_1922_E() {
        return this.v_4262_N && this.P_1922_E != null;
    }

    public void s_956_w() {
        this.v_4262_N = false;
        this.P_1922_E = null;
        this.u_1723_Y = null;
    }

    @Override
    public void u_1723_Y() {
        this.v_4262_N = false;
        this.P_1922_E = null;
        this.u_1723_Y = null;
    }

    public String u_2550_I() {
        return this.P_1922_E;
    }

    public q_1613_l M_588_G() {
        return this.u_1723_Y;
    }

    public boolean P_4830_p() {
        return this.v_4262_N;
    }

    public int h_1847_R() {
        if (this.P_1922_E == null) {
            return -1;
        }
        c_1608_O itemSetting = Z_256_c.J_1907_R(this.P_1922_E);
        if (itemSetting != null) {
            int slot = this.n_1700_B(itemSetting);
            if (slot != -1) {
                return slot;
            }
            slot = this.R_4764_Y(this.P_1922_E);
            if (slot != -1) {
                return slot;
            }
        }
        if (this.u_1723_Y != null) {
            return this.n_1700_B(this.u_1723_Y);
        }
        return -1;
    }

    private void n_1700_B(String itemName, q_1613_l item) {
        c_1608_O itemSetting = Z_256_c.J_1907_R(itemName);
        int slot = -1;
        if (itemSetting != null && (slot = this.n_1700_B(itemSetting)) == -1) {
            slot = this.R_4764_Y(itemName);
        }
        if (itemSetting == null && slot == -1) {
            slot = this.n_1700_B(item);
            if (slot == -1) {
                v_1900_v.n_1700_B("\u00a7c" + itemName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u043a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u0438", new Object[0]);
                return;
            }
        } else if (slot == -1) {
            slot = this.n_1700_B(item);
        }
        if (slot == -1) {
            v_1900_v.n_1700_B("\u00a7c" + itemName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
            return;
        }
        if (this.M_182_A && slot >= 9) {
            v_1900_v.n_1700_B("\u00a7c" + itemName + " \u043d\u0435 \u0432 \u0445\u043e\u0442\u0431\u0430\u0440\u0435", new Object[0]);
            return;
        }
        if (this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(item)) {
            float cooldown = this.n_1700_B.Y_259_p.p_1458_L().n_1700_B(item, this.n_1700_B.RealmsClientConfig());
            float remainingSeconds = cooldown * 20.0f;
            v_1900_v.n_1700_B("\u00a7c" + itemName + " \u0437\u0430\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u0430\u043d \u0435\u0449\u0435 " + String.format("%.1f", Float.valueOf(remainingSeconds)) + " \u0441\u0435\u043a", new Object[0]);
            return;
        }
        this.v_4262_N = true;
        this.u_1723_Y = item;
        this.P_1922_E = itemName;
    }

    private int R_4764_Y(String itemName) {
        if (this.n_1700_B == null || this.n_1700_B.Y_259_p == null || itemName == null) {
            return -1;
        }
        String cleanItemName = w_1672_Y.n_1700_B(itemName);
        for (int i = 0; i < 36; ++i) {
            String cleanDisplayName;
            Z_1993_T stack = this.n_1700_B.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || !(cleanDisplayName = w_1672_Y.n_1700_B(stack.multiplayerClientSuggestionProvider().getString())).contains(cleanItemName) && !cleanItemName.contains(cleanDisplayName) && !cleanDisplayName.equals(cleanItemName)) continue;
            return i;
        }
        return -1;
    }

    private int n_1700_B(q_1613_l item) {
        if (this.n_1700_B == null || this.n_1700_B.Y_259_p == null || item == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = this.n_1700_B.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || stack.J_1907_R() != item) continue;
            return i;
        }
        return -1;
    }

    public void J_1907_R(boolean onlyBar) {
        this.M_182_A = onlyBar;
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
            String cleanDisplayName = w_1672_Y.n_1700_B(stack.multiplayerClientSuggestionProvider().getString());
            String cleanItemName = w_1672_Y.n_1700_B(itemName);
            q_1613_l stackItem = stack.J_1907_R();
            boolean bl = nameMatches = cleanDisplayName.contains(cleanItemName) || cleanItemName.contains(cleanDisplayName) || cleanDisplayName.equals(cleanItemName);
            if (nameMatches && (requiredNbtParams.isEmpty() || this.n_1700_B(stack, requiredNbtParams))) {
                return i;
            }
            if (stackItem != targetItem) continue;
            if (requiredNbtParams.isEmpty()) {
                return i;
            }
            if (!this.n_1700_B(stack, requiredNbtParams)) continue;
            return i;
        }
        return -1;
    }

    private boolean n_1700_B(Z_1993_T stack, List<String> requiredParams) {
        U_2912_j display;
        if (requiredParams.isEmpty()) {
            return true;
        }
        U_2912_j nbt = stack.Q_4569_t();
        if (nbt == null) {
            return false;
        }
        if (nbt.R_4764_Y("display", 10) && (display = nbt.M_182_A("display")).R_4764_Y("Lore", 9)) {
            q_2896_o loreList = display.G_564_y("Lore", 8);
            StringBuilder loreText = new StringBuilder();
            for (int i = 0; i < loreList.size(); ++i) {
                String line = loreList.t_148_a(i);
                try {
                    MutableComponent component = x_282_a.n_1700_B.J_1907_R(line);
                    if (component != null) {
                        loreText.append(component.getString().toLowerCase()).append(" ");
                        continue;
                    }
                    loreText.append(line.toLowerCase()).append(" ");
                    continue;
                }
                catch (Exception e) {
                    loreText.append(line.toLowerCase()).append(" ");
                }
            }
            String fullLore = loreText.toString();
            for (String param : requiredParams) {
                if (fullLore.contains(param.toLowerCase())) continue;
                return false;
            }
            return true;
        }
        return false;
    }
}



