/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lightning.product.D_4024_W;
import lightning.product.H_2671_n;
import lightning.product.L_1875_m;
import lightning.product.N_4263_v;
import lightning.product.Q_2753_H;
import lightning.product.U_2871_b;
import lightning.product.U_3758_B;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Y_364_R;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.c_973_a;
import lightning.product.e_1174_E;
import lightning.product.f_691_R;
import lightning.product.g_2336_b;
import lightning.product.g_3316_o;
import lightning.product.p_1977_n;
import lightning.product.q_3092_O;
import lightning.product.q_4592_V;
import lightning.product.r_4811_B;
import lightning.product.t_3138_Z;
import lightning.product.v_1900_v;
import lightning.product.x_282_a;
import lightning.product.y_2603_k;

public class l_4896_Q
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u041e\u0442\u0441\u043b\u0435\u0436\u0438\u0432\u0430\u0442\u044c \u0441\u043d\u0435\u0441\u0435\u043d\u043d\u044b\u0435 \u0442\u043e\u0442\u0435\u043c\u044b", true);
    private static final Map<UUID, Boolean> w_1484_f = new HashMap<UUID, Boolean>();
    private final Set<Integer> t_148_a = new HashSet<Integer>();
    private final V_4557_X s_956_w = new V_4557_X();

    public l_4896_Q() {
        super("UseTracker", y_2603_k.P_1922_E);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(f_691_R event) {
        a_3913_L player;
        block7: {
            block6: {
                N_4263_v n_4263_v = event.J_1907_R();
                if (!(n_4263_v instanceof a_3913_L)) break block6;
                player = (a_3913_L)n_4263_v;
                if (this.v_4262_N.t_148_a().booleanValue()) break block7;
            }
            return;
        }
        Z_1993_T totemStack = player.S_4035_N().J_1907_R() == q_4592_V.N_81_X ? player.S_4035_N() : (player.A_2714_y().J_1907_R() == q_4592_V.N_81_X ? player.A_2714_y() : new Z_1993_T(q_4592_V.N_81_X));
        boolean isEnchanted = totemStack.k_2293_S();
        w_1484_f.put(player.w_2705_t(), isEnchanted);
        H_2671_n itemDisplayName = (H_2671_n)totemStack.N_4405_n();
        List<x_282_a> tooltipLines = totemStack.n_1700_B(player, g_3316_o.n_1700_B.n_1700_B);
        U_2871_b hoverText = new U_2871_b("");
        for (int i = 0; i < tooltipLines.size(); ++i) {
            x_282_a line = tooltipLines.get(i);
            if (line instanceof H_2671_n) {
                hoverText.n_1700_B(line);
            } else {
                hoverText.n_1700_B(new U_2871_b(line.getString()));
            }
            if (i >= tooltipLines.size() - 1) continue;
            hoverText.n_1700_B("\n");
        }
        c_973_a hoverEvent = new c_973_a(c_973_a.n_1700_B.n_1700_B, hoverText);
        H_2671_n message = new U_2871_b("").n_1700_B(new U_2871_b(player.O_1309_Q().getString()).n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.M_182_A))).n_1700_B(new U_2871_b(" \u043f\u043e\u0442\u0435\u0440\u044f\u043b ").n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.w_1484_f))).n_1700_B(itemDisplayName).n_1700_B(new U_2871_b(", \u0437\u0430\u0447\u0430\u0440\u043e\u0432\u0430\u043d: ").n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.w_1484_f))).n_1700_B(new U_2871_b("\u25cf").n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(isEnchanted ? D_4024_W.u_2550_I : D_4024_W.P_4830_p)));
        message.n_1700_B(message.n_1700_B().n_1700_B(hoverEvent));
        v_1900_v.n_1700_B(message, new Object[0]);
        U_3758_B.n_1700_B(new g_2336_b("minecraft", "textures/item/totem_of_undying.png"), message);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        q_3092_O sAnimateHandPacket;
        if (!e.J_1907_R()) {
            return;
        }
        t_3138_Z<?> packet = e.G_564_y();
        if (packet instanceof q_3092_O && (sAnimateHandPacket = (q_3092_O)packet).R_4764_Y() == 0) {
            this.t_148_a.add(sAnimateHandPacket.J_1907_R());
            this.s_956_w.n_1700_B();
        }
        if (packet instanceof Y_364_R) {
            N_4263_v raw;
            Y_364_R sEntityEquipmentPacket = (Y_364_R)packet;
            int entityId = sEntityEquipmentPacket.J_1907_R();
            if (this.t_148_a.contains(entityId)) {
                if (this.s_956_w.J_1907_R() < 300L) {
                    return;
                }
                this.t_148_a.remove(entityId);
            }
            if (!((raw = l_4896_Q.c_3005_b.Y_601_j.J_1907_R(entityId)) instanceof r_4811_B)) {
                return;
            }
            r_4811_B living = (r_4811_B)raw;
            Z_1993_T before = living.J_1907_R(e_1174_E.n_1700_B);
            for (Pair<e_1174_E, Z_1993_T> entry : sEntityEquipmentPacket.R_4764_Y()) {
                if (entry.getFirst() != e_1174_E.n_1700_B) continue;
                Z_1993_T after = (Z_1993_T)entry.getSecond();
                if (after.n_1700_B() || !this.n_1700_B(before, after, living)) break;
                H_2671_n itemDisplayName = (H_2671_n)after.N_4405_n();
                List<x_282_a> tooltipLines = after.n_1700_B(living instanceof a_3913_L ? (a_3913_L)living : null, g_3316_o.n_1700_B.n_1700_B);
                U_2871_b hoverText = new U_2871_b("");
                for (int i = 0; i < tooltipLines.size(); ++i) {
                    x_282_a line = tooltipLines.get(i);
                    if (line instanceof H_2671_n) {
                        hoverText.n_1700_B(line);
                    } else {
                        hoverText.n_1700_B(new U_2871_b(line.getString()));
                    }
                    if (i >= tooltipLines.size() - 1) continue;
                    hoverText.n_1700_B("\n");
                }
                c_973_a hoverEvent = new c_973_a(c_973_a.n_1700_B.n_1700_B, hoverText);
                H_2671_n message = new U_2871_b("").n_1700_B(new U_2871_b(living.O_1309_Q().getString()).n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.M_182_A))).n_1700_B(new U_2871_b(" \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043b ").n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.w_1484_f))).n_1700_B(itemDisplayName);
                message.n_1700_B(message.n_1700_B().n_1700_B(hoverEvent));
                g_2336_b texture = after.J_1907_R() == q_4592_V.p_863_D || after.J_1907_R() == q_4592_V.E_4612_l ? new g_2336_b("minecraft", "textures/item/golden_apple.png") : new g_2336_b("minecraft", "textures/item/" + String.valueOf(after.J_1907_R()) + ".png");
                int potionColor = L_1875_m.R_4764_Y(after);
                v_1900_v.n_1700_B(message, new Object[0]);
                U_3758_B.n_1700_B(texture, message, potionColor);
                break;
            }
        }
    }

    private boolean n_1700_B(Z_1993_T before, Z_1993_T after, r_4811_B living) {
        return Z_1993_T.R_4764_Y(before, after) && after.t_4043_B() < before.t_4043_B() && living.Y_601_j();
    }
}

