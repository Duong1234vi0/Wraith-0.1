/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.NumberSetting;
import lightning.product.V_4557_X;
import lightning.product.W_4328_U;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;
import lightning.product.c_1514_x;
import lightning.product.n_3932_q;
import lightning.product.BooleanSetting;
import lightning.product.KeyBindSetting;
import lightning.product.q_3386_W;
import lightning.product.Items;
import lightning.product.v_1900_v;
import lightning.product.x_1688_C;

public class w_3483_v
extends n_3932_q {
    private final List<KeyBindSetting> n_1700_B;
    private final NumberSetting J_1907_R;
    private final BooleanSetting R_4764_Y;
    private final V_4557_X G_564_y = new V_4557_X();
    private boolean P_1922_E = false;
    private int u_1723_Y = -1;
    private int v_4262_N = -1;

    public w_3483_v(List<KeyBindSetting> binds, NumberSetting lowHpThreshold, BooleanSetting autoMedicOnLowHp) {
        this.n_1700_B = binds;
        this.J_1907_R = lowHpThreshold;
        this.R_4764_Y = autoMedicOnLowHp;
    }

    @Override
    public String J_1907_R() {
        return "BravoHvH";
    }

    @Override
    public String R_4764_Y() {
        return "Bravo";
    }

    @Override
    public List<KeyBindSetting> G_564_y() {
        return this.n_1700_B;
    }

    @Override
    public List<q_3386_W.n_1700_B> n_1700_B(boolean onlyBar) {
        ArrayList<q_3386_W.n_1700_B> renderItems = new ArrayList<q_3386_W.n_1700_B>();
        int killerSlot = this.R_4764_Y("\u043a\u0438\u043b\u043b\u0435\u0440");
        String killerStatus = killerSlot != -1 ? "\u2713" : "\u2014";
        q_3386_W.n_1700_B killerData = new q_3386_W.n_1700_B("\u0417\u0435\u043b\u044c\u0435 \u043a\u0438\u043b\u043b\u0435\u0440\u0430", "K", killerStatus, Items.g_2492_v);
        if (this.n_1700_B != null && !this.n_1700_B.isEmpty()) {
            killerData.n_1700_B((Integer)this.n_1700_B.get(0).J_1907_R());
        }
        renderItems.add(killerData);
        int medicSlot = this.R_4764_Y("\u043c\u0435\u0434\u0438\u043a");
        String medicStatus = medicSlot != -1 ? "\u2713" : "\u2014";
        q_3386_W.n_1700_B medicData = new q_3386_W.n_1700_B("\u0417\u0435\u043b\u044c\u0435 \u043c\u0435\u0434\u0438\u043a\u0430", "M", medicStatus, Items.g_2492_v);
        if (this.n_1700_B != null && this.n_1700_B.size() > 1) {
            medicData.n_1700_B((Integer)this.n_1700_B.get(1).J_1907_R());
        }
        renderItems.add(medicData);
        q_3386_W.n_1700_B helpData = new q_3386_W.n_1700_B("\u041f\u043e\u0437\u0432\u0430\u0442\u044c \u043f\u043e\u043c\u043e\u0449\u044c", "H", "\u2713", Items.SpongeBlock);
        if (this.n_1700_B != null && this.n_1700_B.size() > 2) {
            helpData.n_1700_B((Integer)this.n_1700_B.get(2).J_1907_R());
        }
        renderItems.add(helpData);
        return renderItems;
    }

    @Override
    public boolean n_1700_B(int keyCode) {
        KeyBindSetting helpBind;
        KeyBindSetting medicBind;
        if (this.n_1700_B == null || this.n_1700_B.isEmpty()) {
            return false;
        }
        if (MinecraftAccess.c_3005_b.Y_259_p == null) {
            return false;
        }
        KeyBindSetting killerBind = this.n_1700_B.get(0);
        if ((Integer)killerBind.J_1907_R() != -1 && (Integer)killerBind.J_1907_R() == keyCode) {
            this.w_1484_f();
            return true;
        }
        if (this.n_1700_B.size() > 1 && (Integer)(medicBind = this.n_1700_B.get(1)).J_1907_R() != -1 && (Integer)medicBind.J_1907_R() == keyCode) {
            this.t_148_a();
            return true;
        }
        if (this.n_1700_B.size() > 2 && (Integer)(helpBind = this.n_1700_B.get(2)).J_1907_R() != -1 && (Integer)helpBind.J_1907_R() == keyCode) {
            this.s_956_w();
            return true;
        }
        return false;
    }

    private void w_1484_f() {
        int slot = this.R_4764_Y("\u043a\u0438\u043b\u043b\u0435\u0440");
        if (slot == -1) {
            v_1900_v.n_1700_B("\u00a7c\u0417\u0435\u043b\u044c\u0435 \u043a\u0438\u043b\u043b\u0435\u0440\u0430 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e!", new Object[0]);
            return;
        }
        this.n_1700_B(slot, false);
    }

    private void t_148_a() {
        int slot = this.R_4764_Y("\u043c\u0435\u0434\u0438\u043a");
        if (slot == -1) {
            v_1900_v.n_1700_B("\u00a7c\u0417\u0435\u043b\u044c\u0435 \u043c\u0435\u0434\u0438\u043a\u0430 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e!", new Object[0]);
            return;
        }
        float oldPitch = MinecraftAccess.c_3005_b.Y_259_p.f_4016_n;
        MinecraftAccess.c_3005_b.Y_259_p.f_4016_n = 90.0f;
        this.n_1700_B(slot, true);
        new Thread(() -> {
            try {
                Thread.sleep(50L);
                if (MinecraftAccess.c_3005_b.Y_259_p != null) {
                    MinecraftAccess.c_3005_b.Y_259_p.f_4016_n = oldPitch;
                }
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
        }).start();
    }

    private void n_1700_B(int slot, boolean splash) {
        if (MinecraftAccess.c_3005_b.Y_259_p == null || MinecraftAccess.c_3005_b.w_1457_N == null) {
            return;
        }
        int currentSlot = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        if (slot < 9) {
            MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y = slot;
            MinecraftAccess.c_3005_b.w_1457_N.processRightClick(MinecraftAccess.c_3005_b.Y_259_p, MinecraftAccess.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
            MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y = currentSlot;
        } else {
            int invSlot = slot;
            MinecraftAccess.c_3005_b.w_1457_N.windowClick(0, invSlot, currentSlot, a_408_T.R_4764_Y, MinecraftAccess.c_3005_b.Y_259_p);
            MinecraftAccess.c_3005_b.w_1457_N.processRightClick(MinecraftAccess.c_3005_b.Y_259_p, MinecraftAccess.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
            MinecraftAccess.c_3005_b.w_1457_N.windowClick(0, invSlot, currentSlot, a_408_T.R_4764_Y, MinecraftAccess.c_3005_b.Y_259_p);
        }
    }

    private void s_956_w() {
        if (MinecraftAccess.c_3005_b.Y_259_p == null || MinecraftAccess.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        c_1514_x pos = MinecraftAccess.c_3005_b.Y_259_p.b_2312_j();
        String message = "! \u041f\u043e\u043c\u043e\u0433\u0438\u0442\u0435, " + pos.getX() + " " + pos.getY() + " " + pos.getZ();
        MinecraftAccess.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new W_4328_U(message));
        v_1900_v.n_1700_B("\u00a7a\u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b \u043e\u0442\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u044b \u0432 \u0447\u0430\u0442!", new Object[0]);
    }

    private int R_4764_Y(String searchQuery) {
        if (MinecraftAccess.c_3005_b == null || MinecraftAccess.c_3005_b.Y_259_p == null || searchQuery == null) {
            return -1;
        }
        String needle = searchQuery.toLowerCase();
        for (int i = 0; i < 36; ++i) {
            String cleanName;
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || !(cleanName = w_3483_v.n_1700_B(stack.multiplayerClientSuggestionProvider().getString())).contains(needle)) continue;
            return i;
        }
        return -1;
    }

    @Override
    public boolean P_1922_E() {
        int medicSlot;
        float threshold;
        float currentHp;
        if (this.R_4764_Y.t_148_a().booleanValue() && MinecraftAccess.c_3005_b.Y_259_p != null && (currentHp = MinecraftAccess.c_3005_b.Y_259_p.g_46_E()) <= (threshold = ((Float)this.J_1907_R.J_1907_R()).floatValue()) && this.G_564_y.n_1700_B(1000.0) && (medicSlot = this.R_4764_Y("\u043c\u0435\u0434\u0438\u043a")) != -1) {
            float oldPitch = MinecraftAccess.c_3005_b.Y_259_p.f_4016_n;
            MinecraftAccess.c_3005_b.Y_259_p.f_4016_n = 90.0f;
            this.n_1700_B(medicSlot, true);
            new Thread(() -> {
                try {
                    Thread.sleep(50L);
                    if (MinecraftAccess.c_3005_b.Y_259_p != null) {
                        MinecraftAccess.c_3005_b.Y_259_p.f_4016_n = oldPitch;
                    }
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }).start();
            this.G_564_y.n_1700_B();
        }
        return false;
    }

    @Override
    public void u_1723_Y() {
        this.P_1922_E = false;
        this.u_1723_Y = -1;
        this.v_4262_N = -1;
    }
}



