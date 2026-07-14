/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lightning.product.C_4114_x;
import lightning.product.E_3343_g;
import lightning.product.N_4263_v;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.h_1015_G;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.y_2603_k;

public class E_2264_m
extends X_3546_T {
    private final q_366_O w_1484_f = new q_366_O("\u041e\u0431\u0445\u043e\u0434", "ReallyWorld", "ReallyWorld", "Matrix");
    private final p_1977_n t_148_a = new p_1977_n("\u0423\u0434\u0430\u043b\u044f\u0442\u044c \u0438\u0437 \u043c\u0438\u0440\u0430", false, () -> this.w_1484_f.J_1907_R("UniAC"));
    public static final List<N_4263_v> v_4262_N = new ArrayList<N_4263_v>();
    private final Set<Integer> s_956_w = new HashSet<Integer>();

    public E_2264_m() {
        super("AntiBot", y_2603_k.n_1700_B);
        this.n_1700_B(this.w_1484_f, this.t_148_a);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (E_2264_m.c_3005_b.Y_259_p == null || E_2264_m.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.w_1484_f.J_1907_R("UniAC") && this.t_148_a.t_148_a().booleanValue()) {
            for (N_4263_v entity : new ArrayList<N_4263_v>(v_4262_N)) {
                if (!(entity instanceof a_3913_L) || !this.s_956_w.contains(entity.j_276_v())) continue;
                E_2264_m.c_3005_b.Y_601_j.n_1700_B(entity.j_276_v());
            }
        } else {
            this.s_956_w.clear();
        }
        for (N_4263_v entity : E_2264_m.c_3005_b.Y_601_j.J_1907_R()) {
            a_3913_L player;
            if (!(entity instanceof a_3913_L) || (player = (a_3913_L)entity).equals(E_2264_m.c_3005_b.Y_259_p)) continue;
            boolean isBot = false;
            if (this.w_1484_f.J_1907_R("ReallyWorld")) {
                boolean hasValidArmor = player.l_1268_F.J_1907_R.stream().allMatch(armorItem -> armorItem.J_1907_R() != q_4592_V.n_1700_B && armorItem.C_2741_M() && !armorItem.u_1723_Y());
                boolean hasValidEquipment = player.S_4035_N().J_1907_R() == q_4592_V.n_1700_B && player.l_1268_F.J_1907_R.stream().anyMatch(armorItem -> armorItem.J_1907_R() == q_4592_V.a_1344_X || armorItem.J_1907_R() == q_4592_V.z_2759_Q || armorItem.J_1907_R() == q_4592_V.r_2090_h || armorItem.J_1907_R() == q_4592_V.t_1509_b || armorItem.J_1907_R() == q_4592_V.j_2302_z || armorItem.J_1907_R() == q_4592_V.U_2474_c || armorItem.J_1907_R() == q_4592_V.k_2282_P || armorItem.J_1907_R() == q_4592_V.T_1170_t);
                boolean hasFullFood = player.P_2295_B().n_1700_B() == 20;
                isBot = hasValidArmor && hasValidEquipment && hasFullFood;
            } else if (this.w_1484_f.J_1907_R("Matrix")) {
                isBot = player.H_3699_F() && !v_4262_N.contains(player) && !player.w_2705_t().equals(a_3913_L.u_1723_Y(player.O_1309_Q().getString()));
            } else if (this.w_1484_f.J_1907_R("UniAC")) {
                isBot = this.n_1700_B(player);
            }
            if (isBot) {
                player.g_4106_L = true;
                if (v_4262_N.contains(player)) continue;
                v_4262_N.add(player);
                if (!this.w_1484_f.J_1907_R("UniAC") || !this.t_148_a.t_148_a().booleanValue()) continue;
                this.s_956_w.add(player.j_276_v());
                continue;
            }
            player.g_4106_L = false;
            v_4262_N.remove(player);
            this.s_956_w.remove(player.j_276_v());
        }
    }

    private boolean n_1700_B(a_3913_L player) {
        if (E_2264_m.c_3005_b.Y_259_p == null) {
            return false;
        }
        if (E_2264_m.c_3005_b.Y_259_p.G_564_y((N_4263_v)player) < 9.0) {
            try {
                C_4114_x tracker = player.D_60_a();
                if (tracker == null) {
                    return true;
                }
                boolean hasComplexData = false;
                int changedCount = 0;
                List<C_4114_x.n_1700_B<?>> entries = tracker.J_1907_R();
                if (entries == null) {
                    return false;
                }
                for (C_4114_x.n_1700_B<?> entry : entries) {
                    Number number;
                    Object value = entry.J_1907_R();
                    if (value instanceof Number && (number = (Number)value).intValue() == 1) {
                        return true;
                    }
                    ++changedCount;
                    if (value == null) {
                        return true;
                    }
                    if (value instanceof Number || value instanceof Boolean || value instanceof String) continue;
                    hasComplexData = true;
                    break;
                }
                if (!hasComplexData && changedCount <= 3) {
                    return true;
                }
            }
            catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g e) {
        for (N_4263_v entity : v_4262_N) {
            entity.g_4106_L = false;
        }
        v_4262_N.clear();
        this.s_956_w.clear();
    }

    @Override
    public void J_1907_R() {
        for (N_4263_v entity : v_4262_N) {
            entity.g_4106_L = false;
        }
        v_4262_N.clear();
        this.s_956_w.clear();
        super.J_1907_R();
    }
}

