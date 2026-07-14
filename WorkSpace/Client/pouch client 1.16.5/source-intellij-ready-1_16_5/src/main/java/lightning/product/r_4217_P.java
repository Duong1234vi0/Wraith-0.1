/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_3091_S;
import lightning.product.ContainerScreen;
import lightning.product.M_766_z;
import lightning.product.N_260_m;
import lightning.product.MultiBooleanSetting;
import lightning.product.Q_2753_H;
import lightning.product.R_2515_i;
import lightning.product.U_2912_j;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.Z_390_O;
import lightning.product.a_408_T;
import lightning.product.ShulkerBoxScreen;
import lightning.product.e_1174_E;
import lightning.product.h_1015_G;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.Packet;
import lightning.product.u_1934_K;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;

public class r_4217_P
extends Module {
    private final MultiBooleanSetting v_4262_N = new MultiBooleanSetting("\u041d\u0430 \u0447\u0442\u043e \u0440\u0430\u0431\u043e\u0442\u0430\u0442\u044c", new BooleanSetting("\u041d\u0435 \u0441\u0432\u0430\u043f\u0430\u0442\u044c \u0441\u043b\u043e\u0442\u044b", true), new BooleanSetting("\u041d\u0435 \u0432\u044b\u043a\u0438\u0434\u0430\u0442\u044c \u044d\u043b\u0438\u0442\u0440\u0443", false), new BooleanSetting("\u041d\u0435 \u0432\u044b\u043a\u0438\u0434\u0430\u0442\u044c \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a", false), new BooleanSetting("\u041d\u0435 \u0432\u044b\u043a\u0438\u0434\u0430\u0442\u044c \u0448\u0430\u0440", false), new BooleanSetting("\u041d\u0435 \u0432\u044b\u043a\u0438\u0434\u0430\u0442\u044c \u043e\u0441\u043a\u043e\u043b\u043a\u0438", false));

    public r_4217_P() {
        super("NoChangeSlot", "\u0411\u043b\u043e\u043a\u0438\u0440\u0443\u0435\u0442 \u043d\u0435\u0436\u0435\u043b\u0430\u0442\u0435\u043b\u044c\u043d\u0443\u044e \u0441\u043c\u0435\u043d\u0443 \u0441\u043b\u043e\u0442\u043e\u0432 \u0438 \u0432\u044b\u0431\u0440\u043e\u0441 \u0437\u0430\u0449\u0438\u0449\u0451\u043d\u043d\u044b\u0445 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432", ModuleCategory.n_1700_B);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(M_766_z e) {
        R_2515_i armorItem;
        Slot clicked = e.J_1907_R();
        a_408_T clickType = e.P_1922_E();
        if (clicked == null || clickType == a_408_T.R_4764_Y || clickType == a_408_T.J_1907_R || r_4217_P.c_3005_b.Y_1740_V instanceof ContainerScreen || r_4217_P.c_3005_b.Y_1740_V instanceof B_3091_S || r_4217_P.c_3005_b.Y_1740_V instanceof ShulkerBoxScreen) {
            return;
        }
        q_1613_l item = clicked.n_1700_B().J_1907_R();
        if (this.v_4262_N.n_1700_B(1).t_148_a().booleanValue() && item == Items.NyliumBlock) {
            e.n_1700_B(true);
        }
        if (this.v_4262_N.n_1700_B(2).t_148_a().booleanValue() && item instanceof R_2515_i && (armorItem = (R_2515_i)item).R_4764_Y() == e_1174_E.P_1922_E) {
            e.n_1700_B(true);
        }
        if (this.v_4262_N.n_1700_B(3).t_148_a().booleanValue() && item == Items.C_3560_B) {
            e.n_1700_B(true);
        }
        if (this.v_4262_N.J_1907_R("\u041d\u0435 \u0432\u044b\u043a\u0438\u0434\u0430\u0442\u044c \u043e\u0441\u043a\u043e\u043b\u043a\u0438").booleanValue() && this.n_1700_B(clicked.n_1700_B())) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        R_2515_i armorItem;
        q_1613_l q_1613_l2;
        boolean bl;
        if (r_4217_P.c_3005_b.Y_1740_V instanceof ContainerScreen || r_4217_P.c_3005_b.Y_1740_V instanceof B_3091_S || r_4217_P.c_3005_b.Y_1740_V instanceof ShulkerBoxScreen) {
            return;
        }
        Z_1993_T itemStack = r_4217_P.c_3005_b.Y_259_p.l_1268_F.s_956_w();
        int n = u_1934_K.u_1723_Y();
        boolean bl2 = bl = n != -1;
        if (this.v_4262_N.J_1907_R("\u041d\u0435 \u0432\u044b\u043a\u0438\u0434\u0430\u0442\u044c \u044d\u043b\u0438\u0442\u0440\u0443").booleanValue() && u_1934_K.n_1700_B(Items.NyliumBlock) == -1 && bl && itemStack.J_1907_R() == Items.NyliumBlock) {
            r_4217_P.c_3005_b.w_1457_N.windowClick(0, n, 1, a_408_T.n_1700_B, r_4217_P.c_3005_b.Y_259_p);
        }
        if (this.v_4262_N.J_1907_R("\u041d\u0435 \u0432\u044b\u043a\u0438\u0434\u0430\u0442\u044c \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a").booleanValue() && u_1934_K.R_4764_Y() == -1 && bl && (q_1613_l2 = itemStack.J_1907_R()) instanceof R_2515_i && (armorItem = (R_2515_i)q_1613_l2).R_4764_Y() == e_1174_E.P_1922_E) {
            r_4217_P.c_3005_b.w_1457_N.windowClick(0, n, 1, a_408_T.n_1700_B, r_4217_P.c_3005_b.Y_259_p);
        }
        if (this.v_4262_N.J_1907_R("\u041d\u0435 \u0432\u044b\u043a\u0438\u0434\u0430\u0442\u044c \u0448\u0430\u0440").booleanValue() && u_1934_K.n_1700_B(Items.C_3560_B) == -1 && bl && itemStack.J_1907_R() == Items.C_3560_B) {
            r_4217_P.c_3005_b.w_1457_N.windowClick(0, n, 1, a_408_T.n_1700_B, r_4217_P.c_3005_b.Y_259_p);
        }
        if (this.v_4262_N.J_1907_R("\u041d\u0435 \u0432\u044b\u043a\u0438\u0434\u0430\u0442\u044c \u043e\u0441\u043a\u043e\u043b\u043a\u0438").booleanValue() && this.n_1700_B(itemStack)) {
            r_4217_P.c_3005_b.w_1457_N.windowClick(0, n, 1, a_408_T.n_1700_B, r_4217_P.c_3005_b.Y_259_p);
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        Packet<?> t_3138_Z2;
        if (this.v_4262_N.J_1907_R("\u041d\u0435 \u0441\u0432\u0430\u043f\u0430\u0442\u044c \u0441\u043b\u043e\u0442\u044b").booleanValue() && !N_260_m.J_1907_R() && (t_3138_Z2 = e.G_564_y()) instanceof Z_390_O) {
            Z_390_O fix = (Z_390_O)t_3138_Z2;
            if (e.J_1907_R() && fix.J_1907_R() != r_4217_P.c_3005_b.Y_259_p.l_1268_F.G_564_y) {
                int newSlot = u_530_F.n_1700_B(r_4217_P.c_3005_b.Y_259_p.l_1268_F.G_564_y >= 8 ? r_4217_P.c_3005_b.Y_259_p.l_1268_F.G_564_y - 1 : r_4217_P.c_3005_b.Y_259_p.l_1268_F.G_564_y + 1, 0, 8);
                r_4217_P.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(newSlot));
                r_4217_P.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(r_4217_P.c_3005_b.Y_259_p.l_1268_F.G_564_y));
                e.n_1700_B(true);
            }
        }
    }

    private boolean n_1700_B(Z_1993_T stack) {
        if (stack == null || !stack.h_1847_R()) {
            return false;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null) {
            return false;
        }
        boolean hasPBV = tag.R_4764_Y("PublicBukkitValues", 10);
        boolean hasDisplay = tag.R_4764_Y("display", 10);
        boolean hasCmd = tag.R_4764_Y("CustomModelData", 3) || hasDisplay && tag.M_182_A("display").R_4764_Y("CustomModelData", 3);
        return hasPBV && hasDisplay && hasCmd;
    }
}



