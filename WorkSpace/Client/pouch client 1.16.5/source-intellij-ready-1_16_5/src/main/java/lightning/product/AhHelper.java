/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import lightning.product.AutoBuy;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.S_4258_d;
import lightning.product.T_2971_J;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.Z_2491_A;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.n_3864_h;
import lightning.product.q_3148_R;
import lightning.product.r_2478_U;
import lightning.product.t_2598_a;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;
import lightning.product.z_3427_G;

public class AhHelper
extends Module {
    private final h_2367_h v_4262_N = new h_2367_h("\u0426\u0432\u0435\u0442 \u0432\u044b\u0433\u043e\u0434\u043d\u043e\u0433\u043e \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430", true, H_2506_c.n_1700_B("#53FF00"));
    private final NumberSetting kolichestvoVygodnyhPredmetovSetting = new NumberSetting("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0432\u044b\u0433\u043e\u0434\u043d\u044b\u0445 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432", 3.0f, 1.0f, 5.0f, 1.0f);
    private final Animation t_148_a = new Animation(0.0f, 4.0f);
    private boolean s_956_w = true;
    private float u_2550_I = 0.1f;
    private float M_588_G = 0.05f;
    private boolean P_4830_p = false;
    private float h_1847_R = 0.0f;
    private float Q_4569_t = 0.0f;
    private int M_182_A = 0;
    private int t_1786_h = 0;
    private int multiplayerClientSuggestionProvider = 0;
    private int w_1457_N = 0;
    private boolean Y_601_j = false;

    public AhHelper() {
        super("AhHelper", ModuleCategory.P_1922_E);
        this.addSettings(this.v_4262_N, this.kolichestvoVygodnyhPredmetovSetting);
    }

    @Y_1740_V
    public void n_1700_B(n_3864_h.J_1907_R e) {
        if (!(AhHelper.c_3005_b.Y_1740_V instanceof z_3427_G)) {
            return;
        }
        String title = AhHelper.c_3005_b.Y_1740_V.getTitle().getString();
        if (!title.contains("\u0410\u0443\u043a\u0446\u0438\u043e\u043d") && !title.contains("\u041f\u043e\u0438\u0441\u043a:")) {
            return;
        }
        this.M_182_A = e.R_4764_Y();
        this.t_1786_h = e.G_564_y();
        this.multiplayerClientSuggestionProvider = ((z_3427_G)AhHelper.c_3005_b.Y_1740_V).R_4764_Y();
        this.w_1457_N = ((z_3427_G)AhHelper.c_3005_b.Y_1740_V).G_564_y();
        this.Y_601_j = true;
        float target = this.s_956_w ? 0.0f : 1.0f;
        this.t_148_a.n_1700_B(target);
        if (this.t_148_a.R_4764_Y()) {
            this.s_956_w = !this.s_956_w;
        }
        int alpha = (int)(105.0f + this.t_148_a.n_1700_B() * 120.0f);
        ArrayList<n_1700_B> priced = new ArrayList<n_1700_B>();
        for (Slot slot : e.P_1922_E().P_1922_E) {
            Z_1993_T stack;
            long price;
            if (slot == null || !slot.J_1907_R() || (price = AutoBuy.n_1700_B(stack = slot.n_1700_B())) <= 0L) continue;
            priced.add(new n_1700_B(slot, price));
        }
        if (priced.isEmpty()) {
            return;
        }
        priced.sort(Comparator.comparingLong(ps -> ps.J_1907_R));
        int toHighlight = (int)Math.min((float)priced.size(), ((Float)this.kolichestvoVygodnyhPredmetovSetting.getValue()).floatValue());
        int highlightColor = H_2506_c.n_1700_B((int)((Integer)this.v_4262_N.J_1907_R()), alpha);
        for (int i = 0; i < toHighlight; ++i) {
            Slot slot = ((n_1700_B)priced.get((int)i)).n_1700_B;
            float rx = e.R_4764_Y() + slot.P_1922_E;
            float ry = e.G_564_y() + slot.u_1723_Y;
            F_489_x.n_1700_B(e.J_1907_R(), rx, ry, 16.0f, 16.0f, highlightColor);
        }
    }

    @Y_1740_V
    public void n_1700_B(n_3864_h.n_1700_B e) {
        if (!(AhHelper.c_3005_b.Y_1740_V instanceof z_3427_G)) {
            return;
        }
        String title = AhHelper.c_3005_b.Y_1740_V.getTitle().getString();
        if (!title.contains("\u0410\u0443\u043a\u0446\u0438\u043e\u043d") && !title.contains("\u041f\u043e\u0438\u0441\u043a:")) {
            return;
        }
        this.M_182_A = e.R_4764_Y();
        this.t_1786_h = e.G_564_y();
        this.multiplayerClientSuggestionProvider = ((z_3427_G)AhHelper.c_3005_b.Y_1740_V).R_4764_Y();
        this.w_1457_N = ((z_3427_G)AhHelper.c_3005_b.Y_1740_V).G_564_y();
        this.Y_601_j = true;
        this.n_1700_B(e.J_1907_R());
    }

    @Y_1740_V
    public void n_1700_B(S_4258_d e) {
        if (!this.h_1847_R()) {
            return;
        }
        List<t_2598_a.J_1907_R> entries = t_2598_a.R_4764_Y();
        float scale = (float)c_3005_b.RealmsServerPing().w_1457_N();
        float mouseX = e.R_4764_Y() * scale;
        float mouseY = e.G_564_y() * scale;
        int screenWidth = c_3005_b.RealmsServerPing().P_4830_p();
        int screenHeight = c_3005_b.RealmsServerPing().h_1847_R();
        float rawGuiLeft = this.Y_601_j ? (float)this.M_182_A * scale : 0.0f;
        float rawGuiTop = this.Y_601_j ? (float)this.t_1786_h * scale : 0.0f;
        int panelWidth = 220;
        int headerHeight = 20;
        float panelX = rawGuiLeft - (float)panelWidth - 10.0f;
        float panelY = rawGuiTop;
        int entriesToShow = Math.min(entries.size(), 10);
        panelX = u_530_F.n_1700_B(panelX, 0.0f, (float)(screenWidth - panelWidth));
        panelY = u_530_F.n_1700_B(panelY, 0.0f, (float)(screenHeight - headerHeight));
    }

    private boolean h_1847_R() {
        if (!(AhHelper.c_3005_b.Y_1740_V instanceof z_3427_G)) {
            return false;
        }
        String title = AhHelper.c_3005_b.Y_1740_V.getTitle().getString();
        return title != null && (title.contains("\u0410\u0443\u043a\u0446\u0438\u043e\u043d") || title.contains("\u041f\u043e\u0438\u0441\u043a:"));
    }

    @Y_1740_V
    public void n_1700_B(T_2971_J e) {
        if (e.J_1907_R() == 0 && this.P_4830_p) {
            this.P_4830_p = false;
        }
    }

    @Y_1740_V
    public void n_1700_B(r_2478_U e) {
    }

    private void n_1700_B(g_221_o matrixStack) {
        List<t_2598_a.J_1907_R> entries = t_2598_a.R_4764_Y();
        int screenWidth = c_3005_b.RealmsServerPing().Q_4569_t();
        int screenHeight = c_3005_b.RealmsServerPing().M_182_A();
        float guiLeft = this.Y_601_j ? (float)this.M_182_A : 0.0f;
        float guiTop = this.Y_601_j ? (float)this.t_1786_h : 0.0f;
        int maxEntries = 6;
        int entriesToShow = Math.max(1, Math.min(entries.size(), maxEntries));
        int panelWidth = 180;
        int panelHeight = entriesToShow * 33 + 29;
        float panelX = u_530_F.n_1700_B(guiLeft - (float)panelWidth - 4.0f, 0.0f, (float)(screenWidth - panelWidth));
        float panelY = u_530_F.n_1700_B(guiTop, 0.0f, (float)(screenHeight - panelHeight));
        int headerColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_1922_E), q_3148_R.J_1907_R(K_1200_E.P_1922_E) / 255.0f);
        int outlineColor = q_3148_R.n_1700_B(K_1200_E.h_1847_R);
        float outlineAlpha = q_3148_R.J_1907_R(K_1200_E.h_1847_R) / 255.0f;
        int bgColor = q_3148_R.n_1700_B(K_1200_E.n_1700_B);
        int textColor = q_3148_R.n_1700_B(K_1200_E.R_4764_Y);
        int priceColor = H_2506_c.n_1700_B(255, 215, 0, 255);
        int baseBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.k_2293_S), q_3148_R.n_1700_B(K_1200_E.k_2293_S), q_3148_R.J_1907_R(K_1200_E.k_2293_S));
        int headerBg = H_2506_c.n_1700_B(baseBg, (float)H_2506_c.G_564_y(baseBg) / 255.0f);
        F_489_x.n_1700_B(panelX - 10.0f, panelY - 10.0f, (float)(panelWidth + 20), (float)(panelHeight + 20), 9.0f, q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.J_1907_R(K_1200_E.q_2307_F) / 255.0f, 10.0f);
        F_489_x.n_1700_B(panelX, panelY, (float)panelWidth, (float)panelHeight, 9.0f, bgColor, 1.0f);
        F_489_x.n_1700_B(panelX, panelY, (float)panelWidth, 28.0f, new Z_2491_A(9.0f, 0.0f, 9.0f, 0.0f), headerBg);
        F_489_x.J_1907_R(panelX, panelY, panelWidth, panelHeight, 9.0f, outlineColor, outlineAlpha);
        String headerText = "\u0418\u0441\u0442\u043e\u0440\u0438\u044f \u043f\u043e\u043a\u0443\u043f\u043e\u043a";
        float headerY = panelY - l_3370_o.J_1907_R[21].h_1847_R() / 2.0f + 13.0f;
        l_3370_o.J_1907_R[21].n_1700_B(matrixStack, headerText, (double)(panelX + 8.0f), (double)(headerY + 1.0f), headerColor);
        int entryHeight = 30;
        float yOffset = panelY + 29.0f;
        for (int i = 0; i < entriesToShow && i < entries.size(); ++i) {
            t_2598_a.J_1907_R entry = entries.get(i);
            Z_1993_T item = entry.n_1700_B();
            long price = entry.J_1907_R();
            int count = entry.R_4764_Y();
            float entryX = panelX + 5.0f;
            float entryY = yOffset;
            float entryWidth = panelWidth - 10;
            F_489_x.n_1700_B(entryX, entryY, entryWidth, (float)entryHeight, 3.0f, H_2506_c.n_1700_B(bgColor, 200));
            F_489_x.J_1907_R(entryX, entryY, entryWidth, entryHeight, 3.0f, outlineColor, outlineAlpha);
            float itemX = entryX + 5.0f;
            float itemY = entryY + 6.0f;
            F_489_x.n_1700_B(item, itemX, itemY, 1.0f, true);
            Object itemName = item.multiplayerClientSuggestionProvider().getString();
            if (((String)itemName).length() > 20) {
                itemName = ((String)itemName).substring(0, 20) + "...";
            }
            String priceText = this.n_1700_B(price);
            Object countText = count > 1 ? " x" + count : "";
            String timeText = this.J_1907_R(entry.G_564_y());
            float textX = entryX + 25.0f;
            float textY = entryY + 5.0f;
            if (l_3370_o.R_4764_Y[12] != null) {
                l_3370_o.J_1907_R[15].n_1700_B(matrixStack, (String)itemName + (String)countText, (double)textX, (double)(textY + 4.0f), textColor);
                l_3370_o.J_1907_R[15].n_1700_B(matrixStack, priceText, (double)textX, (double)(textY + 12.0f), priceColor);
                l_3370_o.J_1907_R[25].n_1700_B(matrixStack, timeText, (double)(textX + 105.0f), (double)(textY + 5.0f), H_2506_c.n_1700_B(textColor, 180));
            }
            yOffset += (float)(entryHeight + 5);
        }
    }

    private String n_1700_B(long number) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        return formatter.format(number);
    }

    private String J_1907_R(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - timestamp;
        long seconds = elapsed / 1000L;
        long minutes = seconds / 60L;
        long hours = minutes / 60L;
        seconds %= 60L;
        minutes %= 60L;
        if (hours > 0L) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        }
        return String.format("%d:%02d", minutes, seconds);
    }

    private static class n_1700_B {
        final Slot n_1700_B;
        final long J_1907_R;

        n_1700_B(Slot slot, long price) {
            this.n_1700_B = slot;
            this.J_1907_R = price;
        }
    }
}



