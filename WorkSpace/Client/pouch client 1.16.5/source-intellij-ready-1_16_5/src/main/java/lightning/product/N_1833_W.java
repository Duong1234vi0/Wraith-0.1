/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.Z_1993_T;
import lightning.product.Z_2491_A;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.j_1376_w;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;
import lombok.Generated;

public class N_1833_W
implements ServerHandshakePacketListener {
    public static h_2367_h n_1700_B = new h_2367_h("\u0425\u0435\u0434\u0435\u0440", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h J_1907_R = new h_2367_h("\u0424\u043e\u043d", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h R_4764_Y = new h_2367_h("\u0422\u0435\u043a\u0441\u0442", true, H_2506_c.n_1700_B(180, 140, 255, 255));
    public static h_2367_h G_564_y = new h_2367_h("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", true, H_2506_c.n_1700_B(120, 80, 160, 255));
    public static h_2367_h P_1922_E = new h_2367_h("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", true, H_2506_c.n_1700_B(120, 80, 160, 100));
    private final J_3635_s u_1723_Y;
    private int v_4262_N = -1;
    private boolean w_1484_f;

    @Override
    public void n_1700_B(b_3528_u event) {
        if (N_1833_W.c_3005_b.Y_259_p == null) {
            return;
        }
        g_221_o ms = event.J_1907_R();
        float posX = this.u_1723_Y.J_1907_R();
        float posY = this.u_1723_Y.R_4764_Y();
        if (!this.n_1700_B()) {
            return;
        }
        float alpha = 1.0f;
        int textColorValue = H_2506_c.n_1700_B((int)((Integer)R_4764_Y.J_1907_R()), alpha);
        int glow = (Integer)P_1922_E.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glow) / 255.0f;
        F_489_x.n_1700_B(posX - 10.0f, posY - 10.0f, 164.0f, 84.5f, 5.0f, glow, glow, glow, glow, glowAlpha * alpha, 10.0f);
        F_489_x.n_1700_B(posX, posY, 144.0f, 64.5f, 5.0f, (int)((Integer)J_1907_R.J_1907_R()), alpha);
        F_489_x.n_1700_B(posX, posY, 144.0f, 14.0f, new Z_2491_A(5.0f, 0.0f, 5.0f, 0.0f), (int)((Integer)n_1700_B.J_1907_R()), alpha);
        int outline = (Integer)G_564_y.J_1907_R();
        float outlineAlphaValue = (float)H_2506_c.G_564_y(outline) * alpha;
        F_489_x.J_1907_R(posX, posY, 144.0f, 64.5f, 5.0f, outline, outlineAlphaValue);
        int headerTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), alpha);
        MutableComponent gradientTitle = j_1376_w.n_1700_B("Inventory", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.J_1907_R[15].n_1700_B(ms, gradientTitle, (double)(posX + 4.5f), (double)(posY + 5.5f), headerTextColor);
        MutableComponent iconText = j_1376_w.n_1700_B("A", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.u_1723_Y[16].n_1700_B(ms, iconText, (double)(posX + 144.0f - l_3370_o.u_1723_Y[16].n_1700_B("A") - 4.5f), (double)(posY + 6.0f), headerTextColor);
        float slotY = posY + 15.5f;
        float slotHeight = 16.0f;
        int itemIndex = 9;
        float itemSize = 0.5f;
        for (int row = 0; row < 3; ++row) {
            for (int j = 1; j < 9; ++j) {
                float offsetX = posX + (float)(j * 16);
                F_489_x.n_1700_B(ms, offsetX - 0.5f, slotY + 2.0f, 0.5f, slotHeight - 4.0f, textColorValue);
            }
            for (int col = 0; col < 9 && itemIndex < 36; ++itemIndex, ++col) {
                Z_1993_T stack = N_1833_W.c_3005_b.Y_259_p.l_1268_F.n_1700_B.get(itemIndex);
                int itemX = (int)(posX + (float)(col * 16) + 8.0f - 8.0f * itemSize);
                int itemY = (int)(slotY + 8.0f - 8.0f * itemSize);
                F_489_x.n_1700_B(stack, (float)itemX, (float)itemY, itemSize);
            }
            slotY += slotHeight;
        }
        this.u_1723_Y.R_4764_Y(144.0f);
        this.u_1723_Y.G_564_y(63.5f);
    }

    private boolean n_1700_B() {
        int tick = N_1833_W.c_3005_b.Y_259_p.RealmsWorldResetDto;
        if (tick == this.v_4262_N) {
            return this.w_1484_f;
        }
        this.v_4262_N = tick;
        this.w_1484_f = false;
        for (int i = 9; i < 36; ++i) {
            if (N_1833_W.c_3005_b.Y_259_p.l_1268_F.n_1700_B.get(i).n_1700_B()) continue;
            this.w_1484_f = true;
            break;
        }
        return this.w_1484_f;
    }

    @Generated
    public N_1833_W(J_3635_s dragging) {
        this.u_1723_Y = dragging;
    }
}


