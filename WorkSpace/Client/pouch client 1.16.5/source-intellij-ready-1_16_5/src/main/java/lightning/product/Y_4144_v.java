/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.H_2506_c;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftAccess;
import lightning.product.n_3932_q;
import lightning.product.KeyBindSetting;
import lightning.product.q_3386_W;
import lightning.product.Items;
import lightning.product.u_1934_K;
import lightning.product.v_1900_v;

public class Y_4144_v
extends n_3932_q {
    private final List<KeyBindSetting> n_1700_B;
    private boolean J_1907_R = false;
    private final Map<String, Integer> R_4764_Y = new HashMap<String, Integer>();
    private long G_564_y = 0L;
    private static final long P_1922_E = 100L;
    private int u_1723_Y = -1;
    private boolean v_4262_N = false;
    private static final String[] w_1484_f = new String[]{"\u0444\u0438\u0442\u0438\u043b\u044c", "\u043f\u0435\u0440\u0435\u0441\u0442\u0430\u0432\u043b\u044f\u044e\u0449\u0435\u0435 \u0437\u0435\u043b\u044c\u0435", "\u0432\u0437\u0440\u044b\u0432\u043d\u043e\u0435 \u0437\u0435\u043b\u044c\u0435"};
    private static final int t_148_a = H_2506_c.n_1700_B(255, 165, 0, 200);
    private static final int s_956_w = H_2506_c.n_1700_B(138, 43, 226, 200);
    private static final int u_2550_I = H_2506_c.n_1700_B(220, 20, 60, 200);

    public Y_4144_v(List<KeyBindSetting> binds) {
        this.n_1700_B = binds;
    }

    @Override
    public String J_1907_R() {
        return "MineBlaze";
    }

    @Override
    public String R_4764_Y() {
        return "MB";
    }

    @Override
    public List<KeyBindSetting> G_564_y() {
        return this.n_1700_B;
    }

    public void J_1907_R(boolean onlyBar) {
        this.J_1907_R = onlyBar;
    }

    public int w_1484_f() {
        return this.u_1723_Y;
    }

    public void t_148_a() {
        this.v_4262_N = false;
        this.u_1723_Y = -1;
    }

    @Override
    public List<q_3386_W.n_1700_B> n_1700_B(boolean onlyBar) {
        this.s_956_w();
        ArrayList<q_3386_W.n_1700_B> renderItems = new ArrayList<q_3386_W.n_1700_B>();
        int fuseSlot = this.R_4764_Y.getOrDefault("\u0444\u0438\u0442\u0438\u043b\u044c", -1);
        String fuseStatus = fuseSlot == -1 ? "\u2014" : "\u2713";
        q_3386_W.n_1700_B fuseData = new q_3386_W.n_1700_B("\u0424\u0438\u0442\u0438\u043b\u044c", "\u0424", fuseStatus, Items.g_2492_v);
        fuseData.J_1907_R(t_148_a);
        if (this.n_1700_B != null && !this.n_1700_B.isEmpty()) {
            fuseData.n_1700_B((Integer)this.n_1700_B.get(0).J_1907_R());
        }
        renderItems.add(fuseData);
        int shuffleSlot = this.R_4764_Y.getOrDefault("\u043f\u0435\u0440\u0435\u0441\u0442\u0430\u0432\u043b\u044f\u044e\u0449\u0435\u0435 \u0437\u0435\u043b\u044c\u0435", -1);
        String shuffleStatus = shuffleSlot == -1 ? "\u2014" : "\u2713";
        q_3386_W.n_1700_B shuffleData = new q_3386_W.n_1700_B("\u041f\u0435\u0440\u0435\u0441\u0442\u0430\u0432\u043b\u044f\u044e\u0449\u0435\u0435", "\u041f\u0417", shuffleStatus, Items.g_2492_v);
        shuffleData.J_1907_R(s_956_w);
        if (this.n_1700_B != null && this.n_1700_B.size() > 1) {
            shuffleData.n_1700_B((Integer)this.n_1700_B.get(1).J_1907_R());
        }
        renderItems.add(shuffleData);
        int explosiveSlot = this.R_4764_Y.getOrDefault("\u0432\u0437\u0440\u044b\u0432\u043d\u043e\u0435 \u0437\u0435\u043b\u044c\u0435", -1);
        String explosiveStatus = explosiveSlot == -1 ? "\u2014" : "\u2713";
        q_3386_W.n_1700_B explosiveData = new q_3386_W.n_1700_B("\u0412\u0437\u0440\u044b\u0432\u043d\u043e\u0435", "\u0412\u0417", explosiveStatus, Items.g_2492_v);
        explosiveData.J_1907_R(u_2550_I);
        if (this.n_1700_B != null && this.n_1700_B.size() > 2) {
            explosiveData.n_1700_B((Integer)this.n_1700_B.get(2).J_1907_R());
        }
        renderItems.add(explosiveData);
        return renderItems;
    }

    @Override
    public boolean n_1700_B(int keyCode) {
        KeyBindSetting explosiveBind;
        KeyBindSetting shuffleBind;
        KeyBindSetting fuseBind;
        if (this.n_1700_B == null || this.n_1700_B.isEmpty()) {
            return false;
        }
        if (!this.n_1700_B.isEmpty() && (Integer)(fuseBind = this.n_1700_B.get(0)).J_1907_R() != -1 && (Integer)fuseBind.J_1907_R() == keyCode) {
            int slot = this.R_4764_Y("\u0444\u0438\u0442\u0438\u043b\u044c");
            if (slot == -1) {
                v_1900_v.n_1700_B("\u00a7c\u0424\u0438\u0442\u0438\u043b\u044c \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
                return true;
            }
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot);
            if (!stack.n_1700_B()) {
                u_1934_K.n_1700_B(this, stack.J_1907_R());
            }
            return true;
        }
        if (this.n_1700_B.size() > 1 && (Integer)(shuffleBind = this.n_1700_B.get(1)).J_1907_R() != -1 && (Integer)shuffleBind.J_1907_R() == keyCode) {
            int slot = this.R_4764_Y("\u043f\u0435\u0440\u0435\u0441\u0442\u0430\u0432\u043b\u044f\u044e\u0449\u0435\u0435 \u0437\u0435\u043b\u044c\u0435");
            if (slot == -1) {
                v_1900_v.n_1700_B("\u00a7c\u041f\u0435\u0440\u0435\u0441\u0442\u0430\u0432\u043b\u044f\u044e\u0449\u0435\u0435 \u0437\u0435\u043b\u044c\u0435 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
                return true;
            }
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot);
            if (!stack.n_1700_B()) {
                u_1934_K.n_1700_B(this, stack.J_1907_R());
            }
            return true;
        }
        if (this.n_1700_B.size() > 2 && (Integer)(explosiveBind = this.n_1700_B.get(2)).J_1907_R() != -1 && (Integer)explosiveBind.J_1907_R() == keyCode) {
            int slot = this.R_4764_Y("\u0432\u0437\u0440\u044b\u0432\u043d\u043e\u0435 \u0437\u0435\u043b\u044c\u0435");
            if (slot == -1) {
                v_1900_v.n_1700_B("\u00a7c\u0412\u0437\u0440\u044b\u0432\u043d\u043e\u0435 \u0437\u0435\u043b\u044c\u0435 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
                return true;
            }
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot);
            if (!stack.n_1700_B()) {
                u_1934_K.n_1700_B(this, stack.J_1907_R());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean P_1922_E() {
        return this.v_4262_N;
    }

    @Override
    public void u_1723_Y() {
        this.v_4262_N = false;
        this.u_1723_Y = -1;
    }

    private void s_956_w() {
        long now = System.currentTimeMillis();
        if (now - this.G_564_y < 100L) {
            return;
        }
        this.G_564_y = now;
        this.R_4764_Y.clear();
        if (MinecraftAccess.c_3005_b == null || MinecraftAccess.c_3005_b.Y_259_p == null) {
            return;
        }
        int maxSlot = this.J_1907_R ? 9 : 36;
        for (int i = 0; i < maxSlot; ++i) {
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B()) continue;
            String itemName = Y_4144_v.n_1700_B(stack.multiplayerClientSuggestionProvider().getString());
            for (String searchName : w_1484_f) {
                if (!itemName.contains(searchName) || this.R_4764_Y.containsKey(searchName)) continue;
                this.R_4764_Y.put(searchName, i);
            }
        }
    }

    private int R_4764_Y(String searchQuery) {
        this.s_956_w();
        return this.R_4764_Y.getOrDefault(searchQuery.toLowerCase(), -1);
    }

    private static String G_564_y(String input) {
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
}



