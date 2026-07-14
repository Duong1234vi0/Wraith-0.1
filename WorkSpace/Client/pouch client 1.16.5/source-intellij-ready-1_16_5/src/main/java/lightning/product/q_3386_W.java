/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.Z_1993_T;
import lightning.product.Z_2491_A;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.j_1376_w;
import lightning.product.j_1654_T;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.q_1613_l;
import lightning.product.q_3148_R;

public class q_3386_W {
    private final MinecraftClient n_1700_B = MinecraftClient.A_4115_X();
    private final J_3635_s J_1907_R;
    private final Animation R_4764_Y = new Animation(0.0f, 10.0f);
    private final h_2367_h G_564_y;
    private final h_2367_h P_1922_E;
    private final h_2367_h u_1723_Y;
    private final h_2367_h v_4262_N;
    private final h_2367_h w_1484_f;
    private float[] t_148_a = new float[0];
    private boolean[] s_956_w = new boolean[0];

    public q_3386_W(J_3635_s dragging, h_2367_h headerColor, h_2367_h bgColor, h_2367_h textColor, h_2367_h outlineColor, h_2367_h glowColor) {
        this.J_1907_R = dragging;
        this.G_564_y = headerColor;
        this.P_1922_E = bgColor;
        this.u_1723_Y = textColor;
        this.v_4262_N = outlineColor;
        this.w_1484_f = glowColor;
    }

    public void n_1700_B(g_221_o ms, String title, List<n_1700_B> items) {
        if (this.n_1700_B.Y_259_p == null || this.n_1700_B.P_4830_p.r_3651_U || items.isEmpty()) {
            return;
        }
        float posX = this.J_1907_R.J_1907_R();
        float posY = this.J_1907_R.R_4764_Y();
        this.R_4764_Y.n_1700_B(1.0f);
        float alpha = this.R_4764_Y.n_1700_B();
        if (alpha <= 0.01f) {
            return;
        }
        float headerHeight = 14.0f;
        float itemHeight = 11.5f;
        float separatorWidth = 0.5f;
        float padding = 2.0f;
        float fixedIconSize = 0.5f;
        float slotSize = 16.0f;
        float iconRenderedSize = slotSize * fixedIconSize;
        this.n_1700_B(items.size());
        float contentWidth = padding * 2.0f;
        for (int i = 0; i < items.size(); ++i) {
            boolean hasBindValue;
            String bindText = items.get(i).G_564_y();
            this.s_956_w[i] = hasBindValue = bindText != null && !bindText.isEmpty();
            if (hasBindValue) {
                float bindWidth = l_3370_o.J_1907_R[12].n_1700_B(bindText);
                this.t_148_a[i] = 2.0f + iconRenderedSize + 2.0f + separatorWidth + 2.0f + bindWidth + 3.5f;
            } else {
                this.t_148_a[i] = 2.0f + iconRenderedSize + 2.0f;
            }
            contentWidth += this.t_148_a[i];
            if (i >= items.size() - 1) continue;
            contentWidth += separatorWidth;
        }
        float totalWidth = contentWidth;
        float totalHeight = headerHeight + itemHeight + padding * 2.0f;
        int glow = (Integer)this.w_1484_f.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glow) / 255.0f;
        F_489_x.n_1700_B(posX - 10.0f, posY - 10.0f, totalWidth + 20.0f, totalHeight + 20.0f, 5.0f, glow, glow, glow, glow, glowAlpha * alpha, 10.0f);
        F_489_x.n_1700_B(posX, posY, totalWidth, totalHeight, 5.0f, (int)((Integer)this.P_1922_E.J_1907_R()), alpha);
        F_489_x.n_1700_B(posX, posY, totalWidth, headerHeight, new Z_2491_A(5.0f, 0.0f, 5.0f, 0.0f), (int)((Integer)this.G_564_y.J_1907_R()), alpha);
        int outline = (Integer)this.v_4262_N.J_1907_R();
        float outlineAlphaValue = (float)H_2506_c.G_564_y(outline) * alpha;
        F_489_x.J_1907_R(posX, posY, totalWidth, totalHeight, 5.0f, outline, outlineAlphaValue);
        int headerTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), alpha);
        MutableComponent gradientTitle = j_1376_w.n_1700_B(title, q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        float iconPadding = 5.0f;
        float titleWidth = l_3370_o.J_1907_R[15].n_1700_B(title);
        float iconWidth = l_3370_o.u_1723_Y[16].n_1700_B("T");
        float availableTitleWidth = Math.max(0.0f, totalWidth - (iconWidth + iconPadding + 8.0f));
        if (titleWidth > availableTitleWidth) {
            l_3370_o.J_1907_R[15].n_1700_B(ms, title, posX + 4.5f, posY + 5.5f, headerTextColor, availableTitleWidth);
        } else {
            l_3370_o.J_1907_R[15].n_1700_B(ms, gradientTitle, (double)(posX + 4.5f), (double)(posY + 5.5f), headerTextColor);
        }
        MutableComponent iconText = j_1376_w.n_1700_B("T", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.u_1723_Y[16].n_1700_B(ms, iconText, (double)(posX + totalWidth - l_3370_o.u_1723_Y[16].n_1700_B("T") - iconPadding), (double)(posY + 6.5f), headerTextColor);
        float itemsStartY = posY + headerHeight + 1.0f;
        float currentX = posX + (totalWidth - contentWidth) / 2.0f + padding;
        for (int i = 0; i < items.size(); ++i) {
            n_1700_B itemData = items.get(i);
            float itemWidth = this.t_148_a[i];
            int itemBgColor = H_2506_c.n_1700_B(H_2506_c.n_1700_B(50, 50, 50, 255), alpha * 0.7f);
            F_489_x.n_1700_B(currentX, itemsStartY, itemWidth, itemHeight, 2.0f, itemBgColor);
            int itemOutline = H_2506_c.n_1700_B(outline, alpha * 0.4f);
            F_489_x.J_1907_R(currentX, itemsStartY, itemWidth, itemHeight, 2.0f, itemOutline, alpha * 0.3f);
            float iconX = currentX + 2.0f;
            float iconY = itemsStartY + (itemHeight - iconRenderedSize) / 2.0f;
            if (itemData.u_1723_Y()) {
                int tint = itemData.P_1922_E();
                int glowTint = H_2506_c.n_1700_B(tint, alpha * 0.6f);
                F_489_x.n_1700_B(iconX - 1.0f, iconY - 1.0f, iconRenderedSize + 2.0f, iconRenderedSize + 2.0f, 2.0f, glowTint);
            }
            F_489_x.n_1700_B(itemData.R_4764_Y(), iconX, iconY, fixedIconSize);
            String bindText = itemData.G_564_y();
            if (this.s_956_w[i] && bindText != null && !bindText.isEmpty()) {
                float separatorX = iconX + iconRenderedSize + 2.0f;
                int sepColor = H_2506_c.n_1700_B(outline, alpha * 0.4f);
                F_489_x.n_1700_B(separatorX, itemsStartY + 2.0f, separatorWidth, itemHeight - 4.0f, 0.5f, sepColor);
                float bindX = separatorX + separatorWidth + 2.0f;
                l_3370_o.J_1907_R[12].n_1700_B(ms, bindText, (double)bindX, (double)(itemsStartY + 4.5f), (int)((Integer)this.u_1723_Y.J_1907_R()));
            }
            currentX += itemWidth + separatorWidth;
        }
        this.J_1907_R.R_4764_Y(totalWidth);
        this.J_1907_R.G_564_y(totalHeight);
    }

    private void n_1700_B(int size) {
        if (this.t_148_a.length < size) {
            this.t_148_a = new float[size];
            this.s_956_w = new boolean[size];
        }
    }

    public static class n_1700_B {
        private final String n_1700_B;
        private final String J_1907_R;
        private final String R_4764_Y;
        private final q_1613_l G_564_y;
        private final Z_1993_T P_1922_E;
        private String u_1723_Y;
        private int v_4262_N = -1;

        public n_1700_B(String fullName, String shortName, String status, q_1613_l icon) {
            this.n_1700_B = fullName;
            this.J_1907_R = shortName;
            this.R_4764_Y = status;
            this.G_564_y = icon;
            this.P_1922_E = new Z_1993_T(icon);
            this.u_1723_Y = "";
        }

        public n_1700_B n_1700_B(String bindKey) {
            this.u_1723_Y = bindKey;
            return this;
        }

        public n_1700_B n_1700_B(int keyCode) {
            this.u_1723_Y = keyCode == -1 ? "" : j_1654_T.n_1700_B(keyCode);
            return this;
        }

        public n_1700_B J_1907_R(int color) {
            this.v_4262_N = color;
            return this;
        }

        public String n_1700_B() {
            return this.R_4764_Y;
        }

        public q_1613_l J_1907_R() {
            return this.G_564_y;
        }

        public Z_1993_T R_4764_Y() {
            return this.P_1922_E;
        }

        public String G_564_y() {
            return this.u_1723_Y;
        }

        public int P_1922_E() {
            return this.v_4262_N;
        }

        public boolean u_1723_Y() {
            return this.v_4262_N != -1;
        }
    }
}



