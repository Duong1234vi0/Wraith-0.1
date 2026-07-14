/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import lightning.product.H_2506_c;
import lightning.product.P_4526_H;
import lightning.product.ClientboundOpenScreenPacket;
import lightning.product.Q_2753_H;
import lightning.product.U_2912_j;
import lightning.product.W_4328_U;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftAccess;
import lightning.product.n_3932_q;
import lightning.product.BooleanSetting;
import lightning.product.q_2896_o;
import lightning.product.KeyBindSetting;
import lightning.product.q_3386_W;
import lightning.product.Items;
import lightning.product.Packet;
import lightning.product.u_1934_K;
import lightning.product.v_1900_v;
import lightning.product.x_282_a;

public class I_1654_f
extends n_3932_q {
    private final List<KeyBindSetting> n_1700_B;
    private final BooleanSetting J_1907_R;
    private final BooleanSetting R_4764_Y;
    private final Set<String> G_564_y = new HashSet<String>(Arrays.asList("\u0430\u043a\u0440\u0438\u0435\u043d(\u0430|\u0443|\u043e\u043c|\u0435|\u0447\u0438\u043a)?", "\u0440\u0438\u0447(\u0430|\u0443|\u043e\u043c|\u0435\u0439|\u0435)?", "\u043d\u044c\u044e\u043a\u043e\u0434(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0438\u043a|\u0435)?", "\u044d\u043a\u0441\u043f\u0435\u043d\u0441\u0438\u0432(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0435)?", "\u0438\u043c\u043f\u0430\u043a\u0442(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0438\u043a|\u0435)?", "\u044d\u043a\u0441\u0435\u043b\u043b\u0435\u043d\u0442(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0438\u043a|\u0435)?", "\u044d\u043a\u0441\u0435\u043b\u0435\u043d\u0442(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0438\u043a)?", "\u043a\u0430\u0442\u043b\u0430\u0432\u0430\u043d(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0447\u0438\u043a)?", "\u043a\u0430\u0442\u043b\u043e\u0432\u0430\u043d(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0447\u0438\u043a)?", "\u0446\u0435\u043b\u0435\u0441\u0442\u0438\u0430\u043b(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0435)?", "\u0446\u0435\u043b\u043a(\u043e\u0439|\u0430|\u0443|\u0430\u043c\u0438|\u043e\u0447\u043a\u0430|\u0435)?", "\u043c\u0430\u0442\u0438\u043a\u0441(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0435)?", "\u0438\u043d\u0435\u0440\u0442\u0438(\u044f|\u0435\u0439|\u044e|\u044f\u043c\u0438|\u0435)?", "\u044d\u043a\u0441\u043f(\u0430|\u043e\u0439|\u043e\u044e|\u0443|\u0443\u043b\u0438\u0447\u043a\u0430|\u0435)?", "\u0444\u043b\u044e\u0433\u0435\u0440(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438)?", "\u0440\u0438\u043a\u0435\u0440(\u0430|\u0443|\u043e\u043c|\u043e\u0447\u0435\u043a)?", "\u0444\u0430\u043d\u043f\u0435(\u0439|\u044e|\u044f|\u0435\u043c|\u0435|\u0439\u0447\u0438\u043a)?", "\u0432\u0435\u043a\u0441\u0430\u0439\u0434(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0438\u043a|\u0435)?", "\u043d\u0443\u0440\u0441\u0443\u043b\u0442\u0430\u043d(\u0430|\u0443|\u0435|\u043e\u043c|\u0447\u0438\u043a)?", "\u043d\u0443\u0440\u0438\u043a(\u0430|\u0443|\u043e\u043c|\u0435)?", "\u043d\u0443\u0440\u043b\u0430\u043d(\u0430|\u0443|\u043e\u043c|\u0447\u0438\u043a|\u0435)?", "\u0432\u0435\u043a\u0441(\u043e\u043c|\u0443|\u0430|\u0430\u043c\u0438|\u0438\u043a|\u0435)?", "\u0440\u0435\u043b\u0435\u0439\u043a(\u043e\u043c|\u0443|\u0430|\u0430\u043c\u0438|\u0435)?", "\u0430\u0440\u0431\u0443\u0437(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0438\u043a|\u0435)?", "\u0432\u0438\u043b\u0434(\u043e\u043c|\u0443|\u0430|\u0430\u043c\u0438|\u0438\u043a|\u0435)?", "\u0444\u0430\u043d\u0442\u0430\u0439\u043c(\u0435|\u0430|\u0443)?", "\u0445\u043e\u043b\u0438\u043a(\u0435|\u0430|\u0443)?", "\u0445\u043e\u043b\u0438\u0432\u043e\u0440\u043b\u0434(\u0430|\u0443|\u0435)?", "\u0440\u043e\u043a\u0441\u0442\u0430\u0440(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0447\u0438\u043a|\u0435)?", "\u0440\u043e\u0433\u0430\u043b\u0438\u043a(\u0430|\u0443|\u043e\u043c|\u0435)?", "\u0442\u0430\u043d\u0434\u0435\u0440\u0445\u0430\u043a(\u043e\u043c|\u0443|\u0438|\u0430\u043c\u0438|\u0430|\u0435)?", "\u043b\u0438\u043a\u0432\u0438\u0434\u0431\u0430\u0443\u043d\u0441(\u0430|\u0443|\u0430\u043c\u0438|\u0435)?", "expensive", "celestial", "newcode", "arbuz", "akrien", "nursultan", "relake", "wild", "wurst", "catlovan", "excellent", "rockstar", "catlavan", "impact", "matix", "inertia", "wex", "wexside", "nurik", "nurlan", "rich", "funpay", "fluger", "riker", "funtime", "holyworld", "wwe", "hvh", "rogalik", "thunderhack", "liquidbounce"));

    public I_1654_f(List<KeyBindSetting> binds, BooleanSetting close, BooleanSetting filter) {
        this.n_1700_B = binds;
        this.J_1907_R = close;
        this.R_4764_Y = filter;
    }

    @Y_1740_V
    private void n_1700_B(Q_2753_H e) {
        Packet<?> t_3138_Z2 = e.G_564_y();
        if (t_3138_Z2 instanceof ClientboundOpenScreenPacket) {
            ClientboundOpenScreenPacket packet = (ClientboundOpenScreenPacket)t_3138_Z2;
            if (this.J_1907_R.t_148_a().booleanValue() && packet.G_564_y().getString().contains("\ua201\ua000\ua202\ua301\ua202\ua001\u00a70\ua203\ua100") && MinecraftAccess.c_3005_b.Y_259_p.RealmsWorldResetDto < 100) {
                MinecraftAccess.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(packet.J_1907_R()));
                e.n_1700_B(true);
            }
        }
        if (e.G_564_y() instanceof W_4328_U && this.R_4764_Y.t_148_a().booleanValue()) {
            String message = ((W_4328_U)e.G_564_y()).J_1907_R().toLowerCase();
            boolean banwords = false;
            for (String pattern : this.G_564_y) {
                if (!message.matches(".*" + pattern + ".*")) continue;
                banwords = true;
                break;
            }
            if (banwords) {
                e.n_1700_B(true);
                v_1900_v.n_1700_B("\u0412 \u0432\u0430\u0448\u0435\u043c \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0438 \u0431\u044b\u043b\u043e \u043d\u0430\u0439\u0434\u0435\u043d\u043e \u0437\u0430\u043f\u0440\u0435\u0442\u043d\u043e\u0435 \u0441\u043b\u043e\u0432\u043e, \u043e\u0442\u043f\u0440\u0430\u0432\u043a\u0430 \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u044f \u043e\u0442\u043c\u0435\u043d\u0435\u043d\u0430!", new Object[0]);
            }
        }
    }

    @Override
    public String J_1907_R() {
        return "Reallyworld";
    }

    @Override
    public String R_4764_Y() {
        return "RW";
    }

    @Override
    public List<KeyBindSetting> G_564_y() {
        return this.n_1700_B;
    }

    @Override
    public List<q_3386_W.n_1700_B> n_1700_B(boolean onlyBar) {
        ArrayList<q_3386_W.n_1700_B> renderItems = new ArrayList<q_3386_W.n_1700_B>();
        int slot = u_1934_K.n_1700_B(Items.FenceGateBlock);
        String status = slot == -1 ? "\u2014" : "\u2713";
        q_3386_W.n_1700_B data = new q_3386_W.n_1700_B("\u0410\u043d\u0442\u0438 \u043f\u043e\u043b\u0435\u0442", "AP", status, Items.FallingBlock);
        if (this.n_1700_B != null && !this.n_1700_B.isEmpty()) {
            data.n_1700_B((Integer)this.n_1700_B.get(0).J_1907_R());
        }
        renderItems.add(data);
        int potionSlot = this.R_4764_Y("\u0433\u0440\u0438\u043d\u0447");
        String potionStatus = potionSlot == -1 ? "\u2014" : "\u2713";
        q_3386_W.n_1700_B potionData = new q_3386_W.n_1700_B("\u0417\u0435\u043b\u044c\u0435 \u0413\u0440\u0438\u043d\u0447\u0430", "G", potionStatus, Items.g_2492_v);
        potionData.J_1907_R(H_2506_c.n_1700_B(50, 205, 50, 200));
        if (this.n_1700_B != null && this.n_1700_B.size() > 1) {
            potionData.n_1700_B((Integer)this.n_1700_B.get(1).J_1907_R());
        }
        renderItems.add(potionData);
        q_3386_W.n_1700_B headData = new q_3386_W.n_1700_B("AutoShift", "H", "\u2713", Items.C_3560_B);
        if (this.n_1700_B != null && this.n_1700_B.size() > 2) {
            headData.n_1700_B((Integer)this.n_1700_B.get(2).J_1907_R());
        }
        renderItems.add(headData);
        int horrorSlot = this.R_4764_Y("\u043d\u043e\u0432\u043e\u0433\u043e\u0434\u043d\u0438\u0439 \u0443\u0436\u0430\u0441");
        String horrorStatus = horrorSlot == -1 ? "\u2014" : "\u2713";
        q_3386_W.n_1700_B horrorData = new q_3386_W.n_1700_B("\u041d\u043e\u0432\u043e\u0433\u043e\u0434\u043d\u0438\u0439 \u0443\u0436\u0430\u0441", "\u041d\u0423", horrorStatus, Items.g_2492_v);
        horrorData.J_1907_R(H_2506_c.n_1700_B(220, 20, 60, 200));
        if (this.n_1700_B != null && this.n_1700_B.size() > 3) {
            horrorData.n_1700_B((Integer)this.n_1700_B.get(3).J_1907_R());
        }
        renderItems.add(horrorData);
        int essenceSlot = this.R_4764_Y("\u044d\u0441\u0441\u0435\u043d\u0446\u0438\u044f \u043a\u0440\u043e\u043c\u0435\u0448\u043d\u0438\u043a\u0430");
        String essenceStatus = essenceSlot == -1 ? "\u2014" : "\u2713";
        q_3386_W.n_1700_B essenceData = new q_3386_W.n_1700_B("\u042d\u0441\u0441\u0435\u043d\u0446\u0438\u044f \u043a\u0440\u043e\u043c\u0435\u0448\u043d\u0438\u043a\u0430", "\u042d\u041a", essenceStatus, Items.g_2492_v);
        essenceData.J_1907_R(H_2506_c.n_1700_B(75, 0, 130, 200));
        if (this.n_1700_B != null && this.n_1700_B.size() > 4) {
            essenceData.n_1700_B((Integer)this.n_1700_B.get(4).J_1907_R());
        }
        renderItems.add(essenceData);
        int snowballPotionSlot = this.R_4764_Y("\u0441\u043d\u0435\u0436\u043e\u043a");
        String snowballPotionStatus = snowballPotionSlot == -1 ? "\u2014" : "\u2713";
        q_3386_W.n_1700_B snowballPotionData = new q_3386_W.n_1700_B("\u0417\u0435\u043b\u044c\u0435 \u0421\u043d\u0435\u0436\u043e\u043a", "\u0421\u041d", snowballPotionStatus, Items.g_2492_v);
        snowballPotionData.J_1907_R(H_2506_c.n_1700_B(135, 206, 250, 200));
        if (this.n_1700_B != null && this.n_1700_B.size() > 5) {
            snowballPotionData.n_1700_B((Integer)this.n_1700_B.get(5).J_1907_R());
        }
        renderItems.add(snowballPotionData);
        int trapSlot = this.R_4764_Y("\u043b\u043e\u0432\u0443\u0448\u043a\u0430");
        String trapStatus = trapSlot == -1 ? "\u2014" : "\u2713";
        q_3386_W.n_1700_B trapData = new q_3386_W.n_1700_B("\u041b\u043e\u0432\u0443\u0448\u043a\u0430", "L", trapStatus, Items.g_2492_v);
        trapData.J_1907_R(H_2506_c.n_1700_B(255, 69, 0, 200));
        if (this.n_1700_B != null && this.n_1700_B.size() > 6) {
            trapData.n_1700_B((Integer)this.n_1700_B.get(6).J_1907_R());
        }
        renderItems.add(trapData);
        return renderItems;
    }

    @Override
    public boolean n_1700_B(int keyCode) {
        KeyBindSetting trapBind;
        KeyBindSetting snowballBind;
        KeyBindSetting essenceBind;
        KeyBindSetting horrorBind;
        KeyBindSetting shiftBind;
        KeyBindSetting potionBind;
        if (this.n_1700_B == null || this.n_1700_B.isEmpty()) {
            return false;
        }
        KeyBindSetting b = this.n_1700_B.get(0);
        if ((Integer)b.J_1907_R() != -1 && (Integer)b.J_1907_R() == keyCode) {
            n_3932_q.n_1700_B();
            return true;
        }
        if (this.n_1700_B.size() > 1 && (Integer)(potionBind = this.n_1700_B.get(1)).J_1907_R() != -1 && (Integer)potionBind.J_1907_R() == keyCode) {
            int slot = this.R_4764_Y("\u0433\u0440\u0438\u043d\u0447");
            if (slot == -1) {
                v_1900_v.n_1700_B("\u00a7c\u0417\u0435\u043b\u044c\u0435 \u0413\u0440\u0438\u043d\u0447\u0430 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
                return true;
            }
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot);
            if (!stack.n_1700_B()) {
                u_1934_K.n_1700_B(this, stack.J_1907_R());
            }
            return true;
        }
        if (this.n_1700_B.size() > 2 && (Integer)(shiftBind = this.n_1700_B.get(2)).J_1907_R() != -1 && (Integer)shiftBind.J_1907_R() == keyCode) {
            if (MinecraftAccess.c_3005_b == null || MinecraftAccess.c_3005_b.P_4830_p == null) {
                return true;
            }
            MinecraftAccess.c_3005_b.P_4830_p.p_178_J.n_1700_B(true);
            new Timer().schedule(new TimerTask(this){

                @Override
                public void run() {
                    if (MinecraftAccess.c_3005_b != null && MinecraftAccess.c_3005_b.P_4830_p != null) {
                        MinecraftAccess.c_3005_b.P_4830_p.p_178_J.n_1700_B(false);
                    }
                }
            }, 150L);
            return true;
        }
        if (this.n_1700_B.size() > 3 && (Integer)(horrorBind = this.n_1700_B.get(3)).J_1907_R() != -1 && (Integer)horrorBind.J_1907_R() == keyCode) {
            int slot = this.R_4764_Y("\u043d\u043e\u0432\u043e\u0433\u043e\u0434\u043d\u0438\u0439 \u0443\u0436\u0430\u0441");
            if (slot == -1) {
                v_1900_v.n_1700_B("\u00a7c\u041d\u043e\u0432\u043e\u0433\u043e\u0434\u043d\u0438\u0439 \u0443\u0436\u0430\u0441 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
                return true;
            }
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot);
            if (!stack.n_1700_B()) {
                u_1934_K.n_1700_B(this, stack.J_1907_R());
            }
            return true;
        }
        if (this.n_1700_B.size() > 4 && (Integer)(essenceBind = this.n_1700_B.get(4)).J_1907_R() != -1 && (Integer)essenceBind.J_1907_R() == keyCode) {
            int slot = this.R_4764_Y("\u044d\u0441\u0441\u0435\u043d\u0446\u0438\u044f \u043a\u0440\u043e\u043c\u0435\u0448\u043d\u0438\u043a\u0430");
            if (slot == -1) {
                v_1900_v.n_1700_B("\u00a7c\u042d\u0441\u0441\u0435\u043d\u0446\u0438\u044f \u043a\u0440\u043e\u043c\u0435\u0448\u043d\u0438\u043a\u0430 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430 \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
                return true;
            }
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot);
            if (!stack.n_1700_B()) {
                u_1934_K.n_1700_B(this, stack.J_1907_R());
            }
            return true;
        }
        if (this.n_1700_B.size() > 5 && (Integer)(snowballBind = this.n_1700_B.get(5)).J_1907_R() != -1 && (Integer)snowballBind.J_1907_R() == keyCode) {
            int slot = this.R_4764_Y("\u0441\u043d\u0435\u0436\u043e\u043a");
            if (slot == -1) {
                v_1900_v.n_1700_B("\u00a7c\u0417\u0435\u043b\u044c\u0435 '\u0421\u043d\u0435\u0436\u043e\u043a' \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
                return true;
            }
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot);
            if (!stack.n_1700_B()) {
                u_1934_K.n_1700_B(this, stack.J_1907_R());
            }
            return true;
        }
        if (this.n_1700_B.size() > 6 && (Integer)(trapBind = this.n_1700_B.get(6)).J_1907_R() != -1 && (Integer)trapBind.J_1907_R() == keyCode) {
            int slot = this.R_4764_Y("\u043b\u043e\u0432\u0443\u0448\u043a\u0430");
            if (slot == -1) {
                v_1900_v.n_1700_B("\u00a7c\u041f\u0440\u0435\u0434\u043c\u0435\u0442 '\u041b\u043e\u0432\u0443\u0448\u043a\u0430' \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
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
        return false;
    }

    @Override
    public void u_1723_Y() {
    }

    private int R_4764_Y(String searchQuery) {
        if (MinecraftAccess.c_3005_b == null || MinecraftAccess.c_3005_b.Y_259_p == null || searchQuery == null || searchQuery.isEmpty()) {
            return -1;
        }
        String needle = searchQuery.toLowerCase();
        boolean isTrapQuery = needle.contains("\u043b\u043e\u0432\u0443\u0448");
        String trapStem = isTrapQuery ? "\u043b\u043e\u0432\u0443\u0448\u043a" : "";
        for (int i = 0; i < 45; ++i) {
            U_2912_j display;
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B()) continue;
            String cleanName = I_1654_f.n_1700_B(stack.multiplayerClientSuggestionProvider().getString());
            if (cleanName.contains(needle) || isTrapQuery && cleanName.contains(trapStem)) {
                return i;
            }
            if (!stack.h_1847_R() || !stack.Q_4569_t().R_4764_Y("display", 10) || !(display = stack.Q_4569_t().M_182_A("display")).R_4764_Y("Lore", 9)) continue;
            q_2896_o lore = display.G_564_y("Lore", 8);
            for (int li = 0; li < lore.size(); ++li) {
                String text;
                String raw = lore.t_148_a(li);
                try {
                    text = x_282_a.n_1700_B.J_1907_R(raw).getString();
                }
                catch (Exception e) {
                    text = raw;
                }
                String cleanLore = I_1654_f.n_1700_B(text);
                if (!cleanLore.contains(needle) && (!isTrapQuery || !cleanLore.contains(trapStem))) continue;
                return i;
            }
        }
        return -1;
    }
}



