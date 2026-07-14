/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lightning.product.A_229_v;
import lightning.product.D_4024_W;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2671_n;
import lightning.product.N_4263_v;
import lightning.product.Q_356_t;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Y_3462_U;
import lightning.product.Z_1993_T;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.e_933_M;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_800_J;
import lightning.product.k_2603_m;
import lightning.product.n_1494_c;
import lightning.product.p_1977_n;
import lightning.product.v_1669_V;
import lightning.product.v_2826_q;
import lightning.product.x_282_a;
import lightning.product.y_2603_k;
import org.joml.Vector2f;

public class r_2173_g
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0432 \u043c\u0438\u0440\u0435", true);

    public r_2173_g() {
        super("ShulkerPreview", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(A_229_v e) {
        if (r_2173_g.n_1700_B(e.J_1907_R)) {
            e.n_1700_B(true);
            r_2173_g.n_1700_B(e.n_1700_B, e.J_1907_R, e.R_4764_Y, e.G_564_y);
        }
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u e) {
        if (this.v_4262_N.t_148_a().booleanValue()) {
            ArrayList<n_1494_c> shulkerDrops = new ArrayList<n_1494_c>();
            for (N_4263_v ent : r_2173_g.c_3005_b.Y_601_j.J_1907_R()) {
                n_1494_c itemEnt;
                Z_1993_T drop;
                if (!(ent instanceof n_1494_c) || !((drop = (itemEnt = (n_1494_c)ent).P_1922_E()).J_1907_R() instanceof v_1669_V) || !(((v_1669_V)drop.J_1907_R()).v_4262_N() instanceof Y_3462_U)) continue;
                shulkerDrops.add(itemEnt);
            }
            shulkerDrops.sort(Comparator.comparingDouble(ie -> ie.u_1723_Y(r_2173_g.c_3005_b.Y_259_p.s_4990_V())).reversed());
            for (n_1494_c itemEnt : shulkerDrops) {
                Z_1993_T drop = itemEnt.P_1922_E();
                U_2912_j rootTag = drop.Q_4569_t();
                if (rootTag == null || !rootTag.P_1922_E("BlockEntityTag")) continue;
                Vector2f screen = v_2826_q.n_1700_B(F_747_P.n_1700_B((N_4263_v)itemEnt, e.R_4764_Y()));
                if (screen.x == Float.MAX_VALUE || screen.y == Float.MAX_VALUE) continue;
                int baseOffsetX = 14;
                float panelX = screen.x + (float)baseOffsetX;
                int baseOffset = 20;
                float panelY = screen.y - (float)baseOffset;
                e_933_M dropColor = Y_3462_U.J_1907_R(drop.J_1907_R());
                int tint = 0xFF000000 | (dropColor != null ? dropColor.w_1484_f() : e_933_M.u_2550_I.w_1484_f());
                F_489_x.n_1700_B(new g_2336_b("Pouch/icons/shulker_view/shulker_box_tooltip.png"), panelX, panelY, 129.0f, 129.0f, tint);
                U_2912_j blockTag = rootTag.M_182_A("BlockEntityTag");
                Q_356_t<Z_1993_T> items = Q_356_t.n_1700_B(27, Z_1993_T.J_1907_R);
                j_800_J.J_1907_R(blockTag, items);
                for (int i = 0; i < items.size(); ++i) {
                    Z_1993_T content = items.get(i);
                    if (content.n_1700_B()) continue;
                    int col = i % 9;
                    int row = i / 9;
                    float slotX = panelX + 4.0f + (float)(col * 9);
                    float slotY = panelY + 4.0f + (float)(row * 9);
                    F_489_x.n_1700_B(content, slotX, slotY, 0.5f);
                }
            }
        }
    }

    public static boolean n_1700_B(Z_1993_T stack) {
        return k_2603_m.hasControlDown() && stack.J_1907_R() instanceof v_1669_V && ((v_1669_V)stack.J_1907_R()).v_4262_N() instanceof Y_3462_U;
    }

    public static void n_1700_B(g_221_o matrixStack, Z_1993_T stack, int mouseX, int mouseY) {
        e_933_M dye;
        ArrayList<H_2671_n> texts = new ArrayList<H_2671_n>();
        texts.add(stack.N_4405_n().P_1922_E().n_1700_B(D_4024_W.M_182_A));
        texts.add(new U_2871_b("\u0421\u043e\u0434\u0435\u0440\u0436\u0438\u0442 27 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432").n_1700_B(D_4024_W.w_1484_f));
        List procs = texts.stream().map(x_282_a::u_1723_Y).collect(Collectors.toList());
        r_2173_g.c_3005_b.Y_1740_V.renderTooltip(matrixStack, procs, mouseX, mouseY);
        int maxWidth = procs.stream().mapToInt(r_2173_g.c_3005_b.t_148_a::n_1700_B).max().orElse(0);
        int x = mouseX + 12;
        int y = mouseY - 12;
        int k = 8;
        if (procs.size() > 1) {
            k += 2 + (procs.size() - 1) * 10;
        }
        if (x + maxWidth > r_2173_g.c_3005_b.Y_1740_V.width) {
            x -= 28 + maxWidth;
        }
        if (y + k + 6 > r_2173_g.c_3005_b.Y_1740_V.height) {
            y = r_2173_g.c_3005_b.Y_1740_V.height - k - 6;
        }
        int tint = 0xFF000000 | ((dye = Y_3462_U.J_1907_R(stack.J_1907_R())) != null ? dye.w_1484_f() : e_933_M.u_2550_I.w_1484_f());
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/shulker_view/shulker_box_tooltip.png"), (float)(x - 4), (float)(y + k + 5), 256.0f, 256.0f, tint);
        U_2912_j rootTag = stack.Q_4569_t();
        if (rootTag != null && rootTag.P_1922_E("BlockEntityTag")) {
            U_2912_j blockTag = rootTag.M_182_A("BlockEntityTag");
            Q_356_t<Z_1993_T> items = Q_356_t.n_1700_B(27, Z_1993_T.J_1907_R);
            j_800_J.J_1907_R(blockTag, items);
            int startX = x + 4;
            int startY = y + k + 13;
            for (int i = 0; i < items.size(); ++i) {
                Z_1993_T content = items.get(i);
                if (content.n_1700_B()) continue;
                int col = i % 9;
                int row = i / 9;
                int slotX = startX + col * 18;
                int slotY = startY + row * 18;
                c_4037_x.v_4276_D();
                c_4037_x.R_4764_Y(0.0f, 0.0f, 32.0f);
                r_2173_g.c_3005_b.Y_1740_V.setBlitOffset(200);
                r_2173_g.c_3005_b.r_715_M().J_1907_R = 200.0f;
                F_489_x.n_1700_B(content, (float)slotX, (float)slotY, 1.0f);
                r_2173_g.c_3005_b.Y_1740_V.setBlitOffset(0);
                r_2173_g.c_3005_b.r_715_M().J_1907_R = 0.0f;
                c_4037_x.d_2461_k();
            }
        }
    }
}

