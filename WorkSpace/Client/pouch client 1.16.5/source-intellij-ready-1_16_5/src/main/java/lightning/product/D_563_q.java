/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.lang.constant.Constable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.L_3537_K;
import lightning.product.O_922_L;
import lightning.product.U_2871_b;
import lightning.product.X_4340_E;
import lightning.product.MinecraftAccess;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.Easing;
import lightning.product.i_4833_u;
import lightning.product.Animation;
import lightning.product.k_2603_m;
import lightning.product.l_3370_o;
import lightning.product.l_3729_r;
import lightning.product.t_4562_T;
import lightning.product.u_530_F;
import lightning.product.y_4842_Z;

public class D_563_q
extends k_2603_m {
    private boolean n_1700_B = false;
    private float J_1907_R;
    private float R_4764_Y;
    private final float G_564_y = 8.0f;
    private final float P_1922_E = 8.0f;
    private String u_1723_Y = "";
    private String v_4262_N = "";
    private boolean w_1484_f = false;
    private boolean t_148_a = false;
    private boolean s_956_w = false;
    private boolean u_2550_I = false;
    private long M_588_G = 0L;
    private final Map<String, Long> P_4830_p = new LinkedHashMap<String, Long>();
    private final Map<String, g_2336_b> h_1847_R = new HashMap<String, g_2336_b>();
    private final Map<String, Boolean> Q_4569_t = new HashMap<String, Boolean>();
    private String M_182_A = null;
    private static final g_2336_b t_1786_h = new g_2336_b("minecraft:textures/entity/steve.png");
    private final SimpleDateFormat multiplayerClientSuggestionProvider = new SimpleDateFormat("dd.MM.yy");
    private static final int w_1457_N = 16;
    private boolean Y_601_j = true;
    private static final int Y_259_p = H_2506_c.n_1700_B("#6F5EF6FF");
    private static final int Q_2552_b = H_2506_c.n_1700_B("#FFFFFF18");
    private static final int C_2741_M = H_2506_c.n_1700_B("#FFFFFF15");
    private static final int k_2293_S = H_2506_c.n_1700_B("#FFFFFF05");
    private static final int q_2307_F = H_2506_c.n_1700_B("#1A1A1AFF");
    private static final int Z_875_P = H_2506_c.n_1700_B("#D0DAF0FF");
    private static final int c_3005_b = H_2506_c.n_1700_B("#4A4A4AFF");
    private static final int H_2857_Y = H_2506_c.n_1700_B("#A4ABABA8");
    private static final int A_4115_X = H_2506_c.n_1700_B("#1A1A1DFF");
    private static final int Y_1740_V = H_2506_c.n_1700_B("#141416FF");
    private static final int t_4043_B = H_2506_c.n_1700_B("#FFFFFF12");
    private static final int x_607_J = H_2506_c.n_1700_B("#1E1E22FF");
    private static final int e_4240_b = C_2741_M;
    private static final int n_3318_d = A_4115_X;
    private static final int d_2427_y = k_2293_S;
    private static final int z_1737_N = H_2506_c.n_1700_B("#FFFFFF08");
    private static final int v_4276_D = H_2506_c.n_1700_B("#FFD700FF");
    private static final int d_2461_k = H_2506_c.n_1700_B("#4CAF50FF");
    private float G_624_v = 0.0f;
    private float T_2506_i = 0.0f;
    private float q_4610_l = 0.0f;
    private final Animation z_4693_k = new Animation(0.0f, 6.0f, Easing.u_2550_I);
    private static float g_221_o = 0.0f;
    private boolean e_2887_G = false;
    private final Animation B_1668_F = new Animation(0.0f, 6.0f, Easing.u_2550_I);
    private boolean g_164_R = false;
    private float X_933_l;
    private float Z_976_R;
    private static final float H_1990_U = 0.0f;
    private static final float N_2525_X = 20.0f;
    private static final float c_4037_x = 10.0f;
    private static final float g_2268_R = 360.0f;
    private static final float T_3594_S = 420.0f;
    private static final float D_4792_h = 23.0f;
    private static final float s_2632_s = 2.0f;
    private static final float l_1233_K = 23.0f;
    private static final float z_1333_t = 2.0f;
    private static final float O_508_d = 2.0f;
    private static final float r_715_M = 50.0f;
    private static final float A_1038_p = 44.0f;
    private static final float i_1637_u = 40.0f;
    private static final float Ping = 24.0f;
    private static final float p_178_J = 12.0f;
    private static final float RealmsClientConfig = 236.0f;
    private static final float f_4016_n = 23.0f;
    private static final float j_276_v = 53.0f;
    private static final float UploadStatus = 23.0f;
    private static final float e_1992_r = 54.0f;
    private static final float D_60_a = 3.0f;
    private static final float k_3961_g = 10.0f;
    private static final float Ops = 20.0f;
    private final L_3537_K h_4320_q;
    private final k_2603_m t_4219_U;

    private static int n_1700_B(int light, int dark) {
        return O_922_L.n_1700_B() ? dark : light;
    }

    public D_563_q(k_2603_m parentScreen) {
        super(new U_2871_b("\u0410\u043a\u043a\u0430\u0443\u043d\u0442\u044b"));
        this.t_4219_U = parentScreen;
        this.h_4320_q = new L_3537_K();
        this.J_1907_R();
        this.G_624_v = g_221_o;
        this.T_2506_i = g_221_o;
        this.z_4693_k.J_1907_R(g_221_o);
    }

    @Override
    protected void init() {
        super.init();
        this.e_2887_G = false;
        this.B_1668_F.J_1907_R(0.0f);
    }

    public float n_1700_B() {
        return this.G_624_v;
    }

    private void J_1907_R() {
        if (this.h_4320_q.u_2550_I() == null) {
            this.h_4320_q.n_1700_B();
        }
        this.P_4830_p.clear();
        this.P_4830_p.putAll(this.h_4320_q.P_4830_p());
        this.Q_4569_t.clear();
        this.Q_4569_t.putAll(this.h_4320_q.h_1847_R());
        this.M_182_A = this.h_4320_q.Q_4569_t();
        this.G_564_y();
    }

    private void R_4764_Y() {
        for (Map.Entry<String, Long> entry : this.P_4830_p.entrySet()) {
            if (this.h_4320_q.P_4830_p().containsKey(entry.getKey())) continue;
            this.h_4320_q.n_1700_B(entry.getKey());
        }
        for (Map.Entry<String, Constable> entry : this.Q_4569_t.entrySet()) {
            this.h_4320_q.n_1700_B(entry.getKey(), (Boolean)entry.getValue());
        }
        if (this.M_182_A != null) {
            this.h_4320_q.u_1723_Y(this.M_182_A);
        } else {
            this.h_4320_q.u_1723_Y(null);
        }
    }

    private void G_564_y() {
        LinkedHashMap sortedAccounts = new LinkedHashMap();
        this.P_4830_p.entrySet().stream().filter(entry -> this.Q_4569_t.getOrDefault(entry.getKey(), false)).forEach(entry -> sortedAccounts.put((String)entry.getKey(), (Long)entry.getValue()));
        this.P_4830_p.entrySet().stream().filter(entry -> this.Q_4569_t.getOrDefault(entry.getKey(), false) == false).sorted(Map.Entry.comparingByValue()).forEach(entry -> sortedAccounts.put((String)entry.getKey(), (Long)entry.getValue()));
        this.P_4830_p.clear();
        this.P_4830_p.putAll(sortedAccounts);
        this.R_4764_Y();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (!this.e_2887_G) {
            this.B_1668_F.n_1700_B(1.0f);
        } else {
            this.B_1668_F.n_1700_B(0.0f);
        }
        float alpha = this.B_1668_F.n_1700_B();
        if (this.e_2887_G && this.B_1668_F.R_4764_Y()) {
            super.closeScreen();
            return;
        }
        int baseScale = (int)MinecraftAccess.c_3005_b.RealmsServerPing().w_1457_N();
        MinecraftAccess.c_3005_b.s_956_w.n_1700_B(2.0f);
        float factor = (float)baseScale / 2.0f;
        int scaledMouseX = (int)((float)mouseX * factor);
        int scaledMouseY = (int)((float)mouseY * factor);
        if (this.t_4219_U != null) {
            this.t_4219_U.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        y_4842_Z.n_1700_B.n_1700_B(2.0f, 4);
        this.n_1700_B(matrixStack, scaledMouseX, scaledMouseY, alpha);
        this.J_1907_R(matrixStack, scaledMouseX, scaledMouseY, alpha);
        this.n_1700_B(matrixStack, scaledMouseX, scaledMouseY);
        super.render(matrixStack, scaledMouseX, scaledMouseY, partialTicks);
        MinecraftAccess.c_3005_b.s_956_w.R_4764_Y();
    }

    @Override
    public void closeScreen() {
        this.e_2887_G = true;
    }

    private void n_1700_B(g_221_o ms, float alpha) {
    }

    private void n_1700_B(g_221_o matrixStack, int mouseX, int mouseY, float alpha) {
        int crackedBg;
        boolean microsoftHovered;
        float windowWidth = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
        float windowHeight = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
        float centerX = (windowWidth - 360.0f) / 2.0f;
        float centerY = (windowHeight - 420.0f) / 2.0f + 0.0f;
        boolean darkTheme = O_922_L.n_1700_B();
        int headerBg = k_2293_S;
        int tabActiveBg = C_2741_M;
        int tabInactiveBg = k_2293_S;
        int contentBg = k_2293_S;
        int textPrimary = H_2506_c.n_1700_B(D_563_q.n_1700_B(q_2307_F, Z_875_P), alpha);
        int textInactive = H_2506_c.n_1700_B(D_563_q.n_1700_B(c_3005_b, H_2857_Y), alpha);
        float headerCardX = centerX + 20.0f;
        float headerCardY = centerY;
        float headerW = 293.0f;
        float closeBtnW = 23.0f;
        float headerCardH = 23.0f;
        F_489_x.n_1700_B(headerCardX, headerCardY, headerW, headerCardH, 8.0f, headerBg, alpha);
        F_489_x.J_1907_R(headerCardX, headerCardY, headerW, headerCardH, 8.0f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
        float headerIconW = l_3370_o.t_148_a[18].n_1700_B("c");
        float headerTextW = l_3370_o.G_564_y[18].n_1700_B("AltManager");
        float headerTotalW = headerIconW + 6.0f + headerTextW;
        float headerOffX = headerCardX + (headerW - headerTotalW) / 2.0f;
        l_3370_o.t_148_a[18].n_1700_B(matrixStack, "c", (double)headerOffX, (double)(headerCardY + (headerCardH - l_3370_o.t_148_a[18].h_1847_R()) / 2.0f + 1.0f), H_2506_c.n_1700_B(Y_259_p, alpha));
        l_3370_o.G_564_y[18].n_1700_B(matrixStack, "AltManager", (double)(headerOffX + headerIconW + 6.0f), (double)(headerCardY + (headerCardH - l_3370_o.G_564_y[18].h_1847_R()) / 2.0f + 1.0f), textPrimary);
        float closeX = headerCardX + headerW + 4.0f;
        float closeY = headerCardY;
        boolean closeHovered = (float)mouseX >= closeX && (float)mouseX <= closeX + closeBtnW && (float)mouseY >= closeY && (float)mouseY <= closeY + headerCardH;
        int closeBgColor = closeHovered ? C_2741_M : k_2293_S;
        F_489_x.n_1700_B(closeX, closeY, closeBtnW, headerCardH, 8.0f, closeBgColor, alpha);
        F_489_x.J_1907_R(closeX, closeY, closeBtnW, headerCardH, 8.0f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
        l_3370_o.t_148_a[18].n_1700_B(matrixStack, "z", (double)(closeX + (closeBtnW - l_3370_o.t_148_a[18].n_1700_B("z")) / 2.0f), (double)(closeY + (headerCardH - l_3370_o.t_148_a[18].h_1847_R()) / 2.0f + 1.0f), textPrimary);
        float tabCardX = centerX + 20.0f;
        float tabCardY = centerY + headerCardH + 2.0f;
        float tabCardW = 320.0f;
        float tabCardH = 23.0f;
        float tabBtnW = 158.0f;
        float tabGap = 4.0f;
        float crackedTabX = tabCardX;
        float microsoftTabX = tabCardX + tabBtnW + tabGap;
        boolean crackedHovered = (float)mouseX >= crackedTabX && (float)mouseX <= crackedTabX + tabBtnW && (float)mouseY >= tabCardY && (float)mouseY <= tabCardY + tabCardH;
        boolean bl = microsoftHovered = (float)mouseX >= microsoftTabX && (float)mouseX <= microsoftTabX + tabBtnW && (float)mouseY >= tabCardY && (float)mouseY <= tabCardY + tabCardH;
        int n = crackedHovered ? C_2741_M : (crackedBg = this.Y_601_j ? tabActiveBg : tabInactiveBg);
        int microsoftBg = microsoftHovered ? C_2741_M : (!this.Y_601_j ? tabActiveBg : tabInactiveBg);
        F_489_x.n_1700_B(crackedTabX, tabCardY, tabBtnW, tabCardH, 8.0f, crackedBg, alpha);
        F_489_x.J_1907_R(crackedTabX, tabCardY, tabBtnW, tabCardH, 8.0f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
        F_489_x.n_1700_B(microsoftTabX, tabCardY, tabBtnW, tabCardH, 8.0f, microsoftBg, alpha);
        F_489_x.J_1907_R(microsoftTabX, tabCardY, tabBtnW, tabCardH, 8.0f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
        int crackedIconColor = this.Y_601_j || crackedHovered ? H_2506_c.n_1700_B(Y_259_p, alpha) : textInactive;
        int microsoftIconColor = !this.Y_601_j || microsoftHovered ? H_2506_c.n_1700_B(Y_259_p, alpha) : textInactive;
        int crackedTextColor = this.Y_601_j || crackedHovered ? textPrimary : textInactive;
        int microsoftTextColor = !this.Y_601_j || microsoftHovered ? textPrimary : textInactive;
        float crackedIconW = l_3370_o.t_148_a[16].n_1700_B("b");
        float crackedTextW = l_3370_o.G_564_y[14].n_1700_B("Cracked");
        float crackedTotalW = crackedIconW + 4.0f + crackedTextW;
        float crackedOffX = crackedTabX + (tabBtnW - crackedTotalW) / 2.0f;
        l_3370_o.t_148_a[16].n_1700_B(matrixStack, "b", (double)crackedOffX, (double)(tabCardY + (tabCardH - l_3370_o.t_148_a[16].h_1847_R()) / 2.0f + 1.0f), crackedIconColor);
        l_3370_o.G_564_y[14].n_1700_B(matrixStack, "Cracked", (double)(crackedOffX + crackedIconW + 4.0f), (double)(tabCardY + (tabCardH - l_3370_o.G_564_y[14].h_1847_R()) / 2.0f + 1.0f), crackedTextColor);
        float msIconW = l_3370_o.t_148_a[16].n_1700_B("n");
        float msTextW = l_3370_o.G_564_y[14].n_1700_B("Microsoft");
        float msTotalW = msIconW + 4.0f + msTextW;
        float msOffX = microsoftTabX + (tabBtnW - msTotalW) / 2.0f;
        l_3370_o.t_148_a[16].n_1700_B(matrixStack, "n", (double)msOffX, (double)(tabCardY + (tabCardH - l_3370_o.t_148_a[16].h_1847_R()) / 2.0f + 1.0f), microsoftIconColor);
        l_3370_o.G_564_y[14].n_1700_B(matrixStack, "Microsoft", (double)(msOffX + msIconW + 4.0f), (double)(tabCardY + (tabCardH - l_3370_o.G_564_y[14].h_1847_R()) / 2.0f + 1.0f), microsoftTextColor);
        ArrayList<Map.Entry<String, Long>> filteredAccounts = new ArrayList<Map.Entry<String, Long>>();
        for (Map.Entry<String, Long> entry : this.P_4830_p.entrySet()) {
            boolean bl2 = this.Y_601_j ? !this.h_4320_q.R_4764_Y(entry.getKey()) : this.h_4320_q.R_4764_Y(entry.getKey());
            boolean matchTab = bl2;
            if (!matchTab || !this.u_1723_Y.isEmpty() && !entry.getKey().toLowerCase().contains(this.u_1723_Y.toLowerCase())) continue;
            filteredAccounts.add(entry);
        }
        this.q_4610_l = (float)filteredAccounts.size() * 40.0f;
        float contentTop = tabCardY + tabCardH + 2.0f;
        float contentLeft = centerX + 20.0f;
        float contentWidth = 320.0f;
        float contentHeight = 410.0f - headerCardH - 2.0f - tabCardH - 2.0f - 50.0f - 20.0f;
        if (this.q_4610_l > contentHeight) {
            this.G_624_v = u_530_F.n_1700_B(this.G_624_v, -this.q_4610_l + contentHeight, 0.0f);
            this.T_2506_i = u_530_F.n_1700_B(this.T_2506_i, -this.q_4610_l + contentHeight, 0.0f);
        } else {
            this.T_2506_i = 0.0f;
            this.G_624_v = 0.0f;
        }
        if (this.g_164_R) {
            this.z_4693_k.J_1907_R(this.G_624_v);
        }
        this.z_4693_k.n_1700_B(this.G_624_v);
        this.T_2506_i = this.z_4693_k.n_1700_B();
        F_489_x.n_1700_B(contentLeft, contentTop, contentWidth, contentHeight, 6.0f, contentBg, alpha);
        F_489_x.J_1907_R(contentLeft, contentTop, contentWidth, contentHeight, 6.0f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
        i_4833_u.n_1700_B(contentLeft, contentTop, contentWidth, contentHeight);
        float rowY = contentTop + 2.0f;
        int rowIndex = 0;
        int secondaryColor = textInactive;
        int nameColor = textPrimary;
        int dividerColor = darkTheme ? H_2506_c.n_1700_B(60, 60, 70, u_530_F.u_1723_Y(255.0f * alpha)) : H_2506_c.n_1700_B(180, 180, 190, u_530_F.u_1723_Y(255.0f * alpha));
        for (Map.Entry entry : filteredAccounts) {
            String name = (String)entry.getKey();
            long timestamp = (Long)entry.getValue();
            float y = rowY + this.T_2506_i;
            boolean isSelected = name.equals(this.M_182_A);
            String date = this.multiplayerClientSuggestionProvider.format(new Date(timestamp));
            float headX = contentLeft + 12.0f;
            float headY = y + 12.0f;
            F_489_x.n_1700_B(this.n_1700_B(name), null, headX, headY, 16.0f, 16.0f, 2.0f, alpha);
            F_489_x.J_1907_R(headX, headY, 16.0f, 16.0f, 2.0f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
            float infoX = contentLeft + 32.0f;
            boolean isFav = this.Q_4569_t.getOrDefault(name, false);
            float favIconSize = 10.0f;
            float line1Y = y + 15.0f;
            float line2Y = line1Y + l_3370_o.J_1907_R[14].h_1847_R() + 3.5f;
            l_3370_o.t_148_a[14].n_1700_B(matrixStack, isFav ? "k" : "j", (double)infoX, (double)(line1Y + 0.5f), secondaryColor);
            l_3370_o.J_1907_R[14].n_1700_B(matrixStack, name, (double)(infoX + 8.0f), (double)line1Y, nameColor);
            l_3370_o.t_148_a[14].n_1700_B(matrixStack, "g", (double)infoX, (double)line2Y, secondaryColor);
            l_3370_o.R_4764_Y[14].n_1700_B(matrixStack, date, (double)(infoX + 8.0f), (double)line2Y, secondaryColor);
            float afterDate = infoX + 8.0f + l_3370_o.R_4764_Y[14].n_1700_B(date);
            l_3370_o.R_4764_Y[14].n_1700_B(matrixStack, "  |  ", (double)afterDate, (double)line2Y, dividerColor);
            float afterSep = afterDate + l_3370_o.R_4764_Y[14].n_1700_B("  |  ");
            l_3370_o.t_148_a[11].n_1700_B(matrixStack, "G", (double)afterSep, (double)(line2Y + 1.0f), secondaryColor);
            l_3370_o.R_4764_Y[14].n_1700_B(matrixStack, "\u2014", (double)(afterSep + 12.0f), (double)(line2Y + 1.0f), secondaryColor);
            float btnCenterY = y + (40.0f - l_3370_o.R_4764_Y[12].h_1847_R()) / 2.0f;
            float rowBtnGap = 4.0f;
            float rowBtnRightMargin = 4.0f;
            float deleteX = contentLeft + contentWidth - 54.0f - rowBtnRightMargin;
            float selectX = deleteX - (l_3370_o.R_4764_Y[14].n_1700_B(isSelected ? "Selected" : "Select") + 15.0f);
            int rowBtnGray = textInactive;
            int selectIconColor = isSelected ? H_2506_c.n_1700_B(Y_259_p, alpha) : rowBtnGray;
            int selectTextColor = isSelected ? textPrimary : rowBtnGray;
            l_3370_o.t_148_a[14].n_1700_B(matrixStack, "s", (double)(selectX + 4.0f), (double)(line1Y + 0.5f), selectIconColor);
            l_3370_o.R_4764_Y[14].n_1700_B(matrixStack, isSelected ? "Selected" : "Select", (double)(selectX + 14.0f), (double)line1Y, selectTextColor);
            float selectEndX = selectX + 14.0f + l_3370_o.R_4764_Y[14].n_1700_B(isSelected ? "Selected" : "Select");
            float btnSepX = selectEndX + (deleteX - selectEndX) / 2.0f;
            l_3370_o.R_4764_Y[14].n_1700_B(matrixStack, "|", (double)(btnSepX + 1.0f), (double)line1Y, dividerColor);
            l_3370_o.t_148_a[14].n_1700_B(matrixStack, "q", (double)(deleteX + 4.0f), (double)(line1Y + 0.5f), rowBtnGray);
            l_3370_o.R_4764_Y[14].n_1700_B(matrixStack, "Delete", (double)(deleteX + 14.0f), (double)line1Y, rowBtnGray);
            rowY += 40.0f;
            if (++rowIndex >= filteredAccounts.size()) continue;
            float sepY = y + 40.0f;
            int sepColor = darkTheme ? H_2506_c.n_1700_B(255, 255, 255, u_530_F.u_1723_Y(15.0f * alpha)) : H_2506_c.n_1700_B(0, 0, 0, u_530_F.u_1723_Y(12.0f * alpha));
            F_489_x.n_1700_B(contentLeft + 12.0f, sepY, contentWidth - 24.0f, 0.5f, 0.0f, sepColor);
        }
        if (this.q_4610_l > contentHeight) {
            float scrollTrackH = contentHeight - 8.0f;
            float f = contentLeft + contentWidth - 3.0f - 4.0f;
            float scrollbarTop = contentTop + 4.0f;
            float thumbHeight = Math.max(20.0f, scrollTrackH * (contentHeight / this.q_4610_l));
            float maxScroll = -this.q_4610_l + contentHeight;
            float scrollRatio = maxScroll != 0.0f ? this.T_2506_i / maxScroll : 0.0f;
            float thumbY = scrollbarTop + (scrollTrackH - thumbHeight) * scrollRatio;
            boolean isHoveringScroll = (float)mouseX >= f - 3.5f && (float)mouseX <= f + 3.0f + 3.5f && (float)mouseY >= scrollbarTop && (float)mouseY <= scrollbarTop + scrollTrackH;
            int trackAlpha = u_530_F.u_1723_Y((float)(isHoveringScroll || this.g_164_R ? 20 : 10) * alpha);
            int thumbAlpha = u_530_F.u_1723_Y((float)(this.g_164_R ? 60 : (isHoveringScroll ? 40 : 25)) * alpha);
            F_489_x.n_1700_B(f, scrollbarTop, 3.0f, scrollTrackH, 1.5f, H_2506_c.n_1700_B(Y_259_p, trackAlpha));
            F_489_x.J_1907_R(f, scrollbarTop, 3.0f, scrollTrackH, 1.5f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
            F_489_x.n_1700_B(f, thumbY, 3.0f, thumbHeight, 1.5f, H_2506_c.n_1700_B(Y_259_p, thumbAlpha));
            F_489_x.J_1907_R(f, thumbY, 3.0f, thumbHeight, 1.5f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
        }
        i_4833_u.n_1700_B();
    }

    private void J_1907_R(g_221_o matrixStack, int mouseX, int mouseY, float alpha) {
        float windowWidth = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
        float windowHeight = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
        float centerX = (windowWidth - 360.0f) / 2.0f;
        float centerY = (windowHeight - 420.0f) / 2.0f + 0.0f;
        boolean darkTheme = O_922_L.n_1700_B();
        int tabActiveBg = darkTheme ? x_607_J : e_4240_b;
        int tabInactiveBg = darkTheme ? n_3318_d : d_2427_y;
        int textPrimary = H_2506_c.n_1700_B(D_563_q.n_1700_B(q_2307_F, Z_875_P), alpha);
        int textInactive = H_2506_c.n_1700_B(D_563_q.n_1700_B(c_3005_b, H_2857_Y), alpha);
        if (!this.Y_601_j) {
            float headerCardH = 23.0f;
            float tabCardH = 23.0f;
            float contentTop = centerY + headerCardH + 2.0f + tabCardH + 2.0f;
            float contentHeight = 410.0f - headerCardH - 2.0f - tabCardH - 2.0f - 50.0f - 20.0f;
            float loginBtnX = centerX + 20.0f;
            float loginBtnY = contentTop + contentHeight + 4.0f;
            float loginBtnW = 320.0f;
            float loginBtnH = 23.0f;
            boolean loginHovered = (float)mouseX >= loginBtnX && (float)mouseX <= loginBtnX + loginBtnW && (float)mouseY >= loginBtnY && (float)mouseY <= loginBtnY + loginBtnH;
            int loginBg = loginHovered ? C_2741_M : k_2293_S;
            F_489_x.n_1700_B(loginBtnX, loginBtnY, loginBtnW, loginBtnH, 6.0f, loginBg, alpha);
            F_489_x.J_1907_R(loginBtnX, loginBtnY, loginBtnW, loginBtnH, 6.0f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
            String loginText = "Login";
            float loginTextW = l_3370_o.G_564_y[14].n_1700_B(loginText);
            float iconW = l_3370_o.t_148_a[14].n_1700_B("n");
            float totalW = iconW + 6.0f + loginTextW;
            float startX = loginBtnX + (loginBtnW - totalW) / 2.0f;
            int loginTextColor = loginHovered ? textPrimary : textInactive;
            l_3370_o.t_148_a[14].n_1700_B(matrixStack, "n", (double)startX, (double)(loginBtnY + (loginBtnH - l_3370_o.t_148_a[14].h_1847_R()) / 2.0f + 1.0f), loginHovered ? H_2506_c.n_1700_B(Y_259_p, alpha) : textInactive);
            l_3370_o.G_564_y[14].n_1700_B(matrixStack, loginText, (double)(startX + iconW + 6.0f), (double)(loginBtnY + (loginBtnH - l_3370_o.G_564_y[14].h_1847_R()) / 2.0f + 1.0f), loginTextColor);
            return;
        }
        float headerCardH = 23.0f;
        float tabCardH = 23.0f;
        float crackedContentTop = centerY + headerCardH + 2.0f + tabCardH + 2.0f;
        float crackedContentHeight = 410.0f - headerCardH - 2.0f - tabCardH - 2.0f - 50.0f - 20.0f;
        float inputX = centerX + 20.0f;
        float inputY = crackedContentTop + crackedContentHeight + 4.0f;
        boolean inputHovered = (float)mouseX >= inputX && (float)mouseX <= inputX + 236.0f && (float)mouseY >= inputY && (float)mouseY <= inputY + 23.0f;
        int inputBg = inputHovered ? C_2741_M : k_2293_S;
        F_489_x.n_1700_B(inputX, inputY, 236.0f, 23.0f, 6.0f, inputBg, alpha);
        F_489_x.J_1907_R(inputX, inputY, 236.0f, 23.0f, 6.0f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
        l_3370_o.t_148_a[14].n_1700_B(matrixStack, "y", (double)(inputX + 10.0f), (double)(inputY + (23.0f - l_3370_o.t_148_a[14].h_1847_R()) / 2.0f + 1.0f), textInactive);
        String display = this.v_4262_N.isEmpty() && !this.t_148_a ? "Input Username" : this.v_4262_N;
        int inputTextColor = this.v_4262_N.isEmpty() && !this.t_148_a ? textInactive : textPrimary;
        l_3370_o.R_4764_Y[14].n_1700_B(matrixStack, display, (double)(inputX + 32.0f), (double)(inputY + (23.0f - l_3370_o.R_4764_Y[14].h_1847_R()) / 2.0f + 1.0f), inputTextColor);
        if (this.t_148_a) {
            boolean showCursor;
            long time = System.currentTimeMillis();
            if (this.M_588_G == 0L) {
                this.M_588_G = time;
            }
            boolean bl = showCursor = (time - this.M_588_G) % 1000L < 500L;
            if (showCursor) {
                float textWidth = l_3370_o.R_4764_Y[14].n_1700_B(this.v_4262_N);
                F_489_x.n_1700_B(matrixStack, inputX + 32.0f + textWidth, inputY + 23.0f - 10.0f, 5.0f, 0.5f, textPrimary);
            }
        } else {
            this.u_2550_I = false;
        }
        float createX = inputX + 236.0f + 4.0f;
        boolean createHovered = (float)mouseX >= createX && (float)mouseX <= createX + 53.0f && (float)mouseY >= inputY && (float)mouseY <= inputY + 23.0f;
        int createBg = createHovered ? C_2741_M : k_2293_S;
        F_489_x.n_1700_B(createX, inputY, 53.0f, 23.0f, 6.0f, createBg, alpha);
        F_489_x.J_1907_R(createX, inputY, 53.0f, 23.0f, 6.0f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
        float createIconW = l_3370_o.t_148_a[14].n_1700_B("e");
        float createTextW = l_3370_o.G_564_y[14].n_1700_B("Create");
        float createTotalW = createIconW + 4.0f + createTextW;
        float createOffX = createX + (53.0f - createTotalW) / 2.0f;
        int createIconColor = createHovered ? H_2506_c.n_1700_B(Y_259_p, alpha) : textPrimary;
        int createTextColor = createHovered ? textPrimary : textInactive;
        l_3370_o.t_148_a[14].n_1700_B(matrixStack, "e", (double)createOffX, (double)(inputY + (23.0f - l_3370_o.t_148_a[14].h_1847_R()) / 2.0f + 1.0f), createIconColor);
        l_3370_o.G_564_y[14].n_1700_B(matrixStack, "Create", (double)(createOffX + createIconW + 4.0f), (double)(inputY + (23.0f - l_3370_o.G_564_y[14].h_1847_R()) / 2.0f + 1.0f), createTextColor);
        float smileyX = createX + 53.0f + 4.0f;
        boolean smileyHovered = (float)mouseX >= smileyX && (float)mouseX <= smileyX + 23.0f && (float)mouseY >= inputY && (float)mouseY <= inputY + 23.0f;
        int smileyBgColor = smileyHovered ? C_2741_M : k_2293_S;
        F_489_x.n_1700_B(smileyX, inputY, 23.0f, 23.0f, 6.0f, smileyBgColor, alpha);
        F_489_x.J_1907_R(smileyX, inputY, 23.0f, 23.0f, 6.0f, Q_2552_b, (float)H_2506_c.G_564_y(Q_2552_b) * alpha);
        int smileyIconColor = smileyHovered ? H_2506_c.n_1700_B(Y_259_p, alpha) : textInactive;
        l_3370_o.t_148_a[14].n_1700_B(matrixStack, "p", (double)(smileyX + (23.0f - l_3370_o.t_148_a[14].n_1700_B("s")) / 2.0f), (double)(inputY + (23.0f - l_3370_o.t_148_a[14].h_1847_R()) / 2.0f + 1.0f), smileyIconColor);
    }

    private void n_1700_B(g_221_o matrixStack, int mouseX, int mouseY) {
        if (!this.n_1700_B) {
            return;
        }
        float dialogX = (float)(MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t() - 180) / 2.0f;
        float dialogY = (float)(MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A() - 60) / 2.0f + 20.0f;
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/mainmenu/black_background.png"), 0.0f, 0.0f, (float)MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t(), (float)MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A(), H_2506_c.n_1700_B(-1, 240));
        F_489_x.n_1700_B(dialogX, dialogY, 180.0f, 60.0f, 8.0f, H_2506_c.n_1700_B(11, 11, 11), H_2506_c.n_1700_B(10, 10, 10), H_2506_c.n_1700_B(31, 31, 31), H_2506_c.n_1700_B(12, 12, 12), 1.0f);
        F_489_x.J_1907_R(dialogX, dialogY, 180.0f, 60.0f, 8.0f, Q_2552_b, H_2506_c.G_564_y(Q_2552_b));
        String text = "\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0434\u0438\u0442\u044c";
        l_3370_o.J_1907_R[22].n_1700_B(matrixStack, text, (double)(dialogX + (180.0f - l_3370_o.J_1907_R[22].n_1700_B(text)) / 2.0f), (double)(dialogY + 14.5f), -1);
        this.J_1907_R = dialogX + 180.0f - 8.0f - 7.0f;
        this.R_4764_Y = dialogY + 6.0f;
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/alts/close.png"), this.J_1907_R, this.R_4764_Y, 8.0f, 8.0f, -1);
        float buttonWidth = 60.0f;
        float buttonHeight = 23.0f;
        float spacing = 20.0f;
        float buttonY = dialogY + 60.0f - buttonHeight - 4.0f;
        float yesButtonX = dialogX + (180.0f - buttonWidth * 2.0f - spacing) / 2.0f;
        float noButtonX = yesButtonX + buttonWidth + spacing;
        boolean yesHovered = (float)mouseX >= yesButtonX && (float)mouseX <= yesButtonX + buttonWidth && (float)mouseY >= buttonY && (float)mouseY <= buttonY + buttonHeight;
        boolean noHovered = (float)mouseX >= noButtonX && (float)mouseX <= noButtonX + buttonWidth && (float)mouseY >= buttonY && (float)mouseY <= buttonY + buttonHeight;
        F_489_x.n_1700_B(yesButtonX, buttonY, buttonWidth, buttonHeight, 5.0f, H_2506_c.n_1700_B(8, 8, 8), H_2506_c.n_1700_B(7, 7, 7), H_2506_c.n_1700_B(29, 29, 29), H_2506_c.n_1700_B(10, 10, 10), 1.0f);
        F_489_x.J_1907_R(yesButtonX, buttonY, buttonWidth, buttonHeight, 5.0f, Q_2552_b, H_2506_c.G_564_y(Q_2552_b));
        l_3370_o.J_1907_R[24].n_1700_B(matrixStack, "\u041e\u043a", (double)(yesButtonX + (buttonWidth - l_3370_o.J_1907_R[24].n_1700_B("\u041e\u043a")) / 2.0f), (double)(buttonY + (buttonHeight - l_3370_o.J_1907_R[24].h_1847_R()) / 2.0f - 1.5f), yesHovered ? H_2506_c.n_1700_B(255, 255, 255, 200) : H_2506_c.n_1700_B(255, 255, 255, 120));
        F_489_x.n_1700_B(noButtonX, buttonY, buttonWidth, buttonHeight, 5.0f, H_2506_c.n_1700_B(8, 8, 8), H_2506_c.n_1700_B(7, 7, 7), H_2506_c.n_1700_B(29, 29, 29), H_2506_c.n_1700_B(10, 10, 10), 1.0f);
        F_489_x.J_1907_R(noButtonX, buttonY, buttonWidth, buttonHeight, 5.0f, Q_2552_b, H_2506_c.G_564_y(Q_2552_b));
        l_3370_o.J_1907_R[24].n_1700_B(matrixStack, "\u041e\u0442\u043c\u0435\u043d\u0430", (double)(noButtonX + (buttonWidth - l_3370_o.J_1907_R[24].n_1700_B("\u041e\u0442\u043c\u0435\u043d\u0430")) / 2.0f), (double)(buttonY + (buttonHeight - l_3370_o.J_1907_R[24].h_1847_R()) / 2.0f - 1.5f), noHovered ? H_2506_c.n_1700_B(255, 255, 255, 200) : H_2506_c.n_1700_B(255, 255, 255, 120));
    }

    private g_2336_b n_1700_B(String name) {
        if (this.h_1847_R.containsKey(name)) {
            return this.h_1847_R.get(name);
        }
        try {
            g_2336_b location = X_4340_E.R_4764_Y(name);
            X_4340_E.n_1700_B(location, name);
            this.h_1847_R.put(name, location);
            return location;
        }
        catch (Exception e) {
            this.h_1847_R.put(name, t_1786_h);
            return t_1786_h;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        double totalContent;
        if (button != 0) {
            return super.mouseClicked(mouseX, mouseY, button);
        }
        int baseScale = (int)MinecraftAccess.c_3005_b.RealmsServerPing().w_1457_N();
        double factor = (double)baseScale / 2.0;
        double sx = mouseX * factor;
        double sy = mouseY * factor;
        double w2 = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
        double h2 = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
        double centerX = (w2 - 360.0) / 2.0;
        double centerY = (h2 - 420.0) / 2.0 + 0.0;
        double headerCardX = centerX + 20.0;
        double headerCardW = 320.0;
        double headerCardH = 23.0;
        double tabCardY = centerY + headerCardH + 2.0;
        double tabCardW = 320.0;
        double tabCardH = 23.0;
        if (!this.n_1700_B) {
            double closeX = headerCardX + 293.0 + 4.0;
            double closeBtnW = 23.0;
            if (sx >= closeX && sx <= closeX + closeBtnW && sy >= centerY && sy <= centerY + headerCardH) {
                this.closeScreen();
                return true;
            }
            double tabBtnW = 158.0;
            double tabGap = 4.0;
            double crackedTabX = centerX + 20.0;
            double microsoftTabX = crackedTabX + tabBtnW + tabGap;
            if (sx >= crackedTabX && sx <= crackedTabX + tabBtnW && sy >= tabCardY && sy <= tabCardY + tabCardH) {
                this.Y_601_j = true;
                return true;
            }
            if (sx >= microsoftTabX && sx <= microsoftTabX + tabBtnW && sy >= tabCardY && sy <= tabCardY + tabCardH) {
                this.Y_601_j = false;
                return true;
            }
            double crackedCTop = centerY + 23.0 + 2.0 + 23.0 + 2.0;
            double crackedCHeight = 290.0;
            double inputX = centerX + 20.0;
            double inputY = crackedCTop + crackedCHeight + 4.0;
            if (!this.Y_601_j) {
                double contentTopC = centerY + 23.0 + 2.0 + 23.0 + 2.0;
                double contentHeightC = 290.0;
                double loginBtnY = contentTopC + contentHeightC + 4.0;
                double loginBtnW = 320.0;
                if (sx >= inputX && sx <= inputX + loginBtnW && sy >= loginBtnY && sy <= loginBtnY + 23.0) {
                    t_4562_T.n_1700_B(refreshToken -> {
                        if (refreshToken == null) {
                            return;
                        }
                        t_4562_T.G_564_y data = t_4562_T.J_1907_R(refreshToken);
                        if (data.n_1700_B()) {
                            MinecraftAccess.c_3005_b.execute(() -> {
                                if (data.G_564_y != null && !data.G_564_y.isEmpty()) {
                                    if (!this.P_4830_p.containsKey(data.G_564_y)) {
                                        this.P_4830_p.put(data.G_564_y, System.currentTimeMillis());
                                        this.h_4320_q.J_1907_R(data.G_564_y);
                                        this.G_564_y();
                                    }
                                    MinecraftAccess.c_3005_b.w_1484_f.n_1700_B(data.G_564_y);
                                    this.M_182_A = data.G_564_y;
                                    this.h_4320_q.u_1723_Y(data.G_564_y);
                                }
                            });
                        }
                    });
                    return true;
                }
                return super.mouseClicked(sx, sy, button);
            }
            if (sx >= inputX && sx <= inputX + 236.0 && sy >= inputY && sy <= inputY + 23.0) {
                this.t_148_a = true;
                this.w_1484_f = false;
                this.M_588_G = System.currentTimeMillis();
                this.u_2550_I = false;
                return true;
            }
            double createX = inputX + 236.0 + 4.0;
            if (sx >= createX && sx <= createX + 53.0 && sy >= inputY && sy <= inputY + 23.0) {
                String accountName;
                if (!this.v_4262_N.trim().isEmpty() && !this.P_4830_p.containsKey(accountName = this.v_4262_N.trim())) {
                    this.P_4830_p.put(accountName, System.currentTimeMillis());
                    this.h_4320_q.n_1700_B(accountName);
                    this.R_4764_Y();
                    this.v_4262_N = "";
                    this.t_148_a = false;
                    this.u_2550_I = false;
                }
                return true;
            }
            double smileyX = createX + 53.0 + 4.0;
            if (sx >= smileyX && sx <= smileyX + 23.0 && sy >= inputY && sy <= inputY + 23.0) {
                String randomNick;
                this.v_4262_N = randomNick = l_3729_r.n_1700_B();
                this.t_148_a = true;
                this.M_588_G = System.currentTimeMillis();
                return true;
            }
            this.t_148_a = false;
            this.u_2550_I = false;
        }
        if (this.n_1700_B) {
            double dialogX = (w2 - 180.0) / 2.0;
            double dialogY = (h2 - 60.0) / 2.0 + 20.0;
            if (sx >= (double)this.J_1907_R && sx <= (double)(this.J_1907_R + 8.0f) && sy >= (double)this.R_4764_Y && sy <= (double)(this.R_4764_Y + 8.0f)) {
                this.n_1700_B = false;
                return true;
            }
            double buttonY = dialogY + 60.0 - 23.0 - 4.0;
            double yesButtonX = dialogX + 20.0;
            double noButtonX = yesButtonX + 60.0 + 20.0;
            if (sx >= yesButtonX && sx <= yesButtonX + 60.0 && sy >= buttonY && sy <= buttonY + 23.0) {
                ArrayList<String> accountList = new ArrayList<String>(this.P_4830_p.keySet());
                for (String accountName : accountList) {
                    this.h_4320_q.G_564_y(accountName);
                }
                this.P_4830_p.clear();
                this.h_1847_R.clear();
                this.Q_4569_t.clear();
                this.M_182_A = null;
                this.n_1700_B = false;
                return true;
            }
            if (sx >= noButtonX && sx <= noButtonX + 60.0 && sy >= buttonY && sy <= buttonY + 23.0) {
                this.n_1700_B = false;
                return true;
            }
            return true;
        }
        double contentLeft = centerX + 20.0;
        double contentWidth = 320.0;
        double contentTop = centerY + headerCardH + 2.0 + tabCardH + 2.0;
        double contentHeight = 410.0 - headerCardH - 2.0 - tabCardH - 2.0 - 50.0 - 20.0;
        boolean isInScissorArea = sx >= contentLeft && sx <= contentLeft + contentWidth && sy >= contentTop && sy <= contentTop + contentHeight;
        double scrollbarX = contentLeft + contentWidth - 3.0 - 4.0;
        double scrollHitX = scrollbarX - 3.5;
        if (sx >= scrollHitX && sx <= scrollHitX + 10.0 && sy >= contentTop && sy <= contentTop + contentHeight && (totalContent = (double)((float)this.P_4830_p.size() * 40.0f)) > contentHeight) {
            double scrollRatio;
            double maxScroll = -totalContent + contentHeight;
            double d = Math.max(20.0, contentHeight * (contentHeight / totalContent));
            double thumbY = contentTop + (contentHeight - d) * (scrollRatio = maxScroll != 0.0 ? (double)this.T_2506_i / maxScroll : 0.0);
            if (sy >= thumbY && sy <= thumbY + d) {
                this.g_164_R = true;
                this.X_933_l = (float)sy;
                this.Z_976_R = this.G_624_v;
            } else {
                double clickRatio = (sy - contentTop - d / 2.0) / (contentHeight - d);
                clickRatio = u_530_F.n_1700_B(clickRatio, 0.0, 1.0);
                this.G_624_v = (float)(clickRatio * maxScroll);
                this.g_164_R = true;
                this.X_933_l = (float)sy;
                this.Z_976_R = this.G_624_v;
            }
            return true;
        }
        ArrayList<Map.Entry<String, Long>> filteredAccounts = new ArrayList<Map.Entry<String, Long>>();
        for (Map.Entry<String, Long> entry : this.P_4830_p.entrySet()) {
            boolean bl = this.Y_601_j ? !this.h_4320_q.R_4764_Y(entry.getKey()) : this.h_4320_q.R_4764_Y(entry.getKey());
            boolean matchTab = bl;
            if (!matchTab || !this.u_1723_Y.isEmpty() && !entry.getKey().toLowerCase().contains(this.u_1723_Y.toLowerCase())) continue;
            filteredAccounts.add(entry);
        }
        double rowY = contentTop + 2.0;
        for (Map.Entry entry : filteredAccounts) {
            String name = (String)entry.getKey();
            double y = rowY + (double)this.T_2506_i;
            if (isInScissorArea) {
                double favX = contentLeft + 34.0;
                double favY = y + 6.0;
                double favSize = 10.0;
                if (sx >= favX && sx <= favX + favSize && sy >= favY && sy <= favY + favSize) {
                    boolean newFavoriteState = this.Q_4569_t.getOrDefault(name, false) == false;
                    this.Q_4569_t.put(name, newFavoriteState);
                    this.h_4320_q.n_1700_B(name, newFavoriteState);
                    this.G_564_y();
                    return true;
                }
                double rowBtnGap = 4.0;
                double rowBtnRightMargin = 4.0;
                double deleteX = contentLeft + contentWidth - 54.0 - rowBtnRightMargin;
                double selectX = deleteX - (double)l_3370_o.R_4764_Y[14].n_1700_B("Selected") - rowBtnGap;
                if (sx >= selectX && sx <= selectX + (double)l_3370_o.R_4764_Y[14].n_1700_B("Selected") && sy >= y && sy <= y + 40.0) {
                    this.M_182_A = name;
                    MinecraftAccess.c_3005_b.w_1484_f.n_1700_B(this.M_182_A);
                    this.h_4320_q.u_1723_Y(this.M_182_A);
                    return true;
                }
                if (sx >= deleteX && sx <= deleteX + 54.0 && sy >= y && sy <= y + 40.0) {
                    this.P_4830_p.remove(name);
                    this.h_1847_R.remove(name);
                    this.Q_4569_t.remove(name);
                    this.h_4320_q.G_564_y(name);
                    if (Objects.equals(this.M_182_A, name)) {
                        this.M_182_A = null;
                    }
                    this.G_564_y();
                    return true;
                }
                if (sx >= contentLeft && sx <= contentLeft + contentWidth - 150.0 && sy >= y && sy <= y + 40.0) {
                    this.M_182_A = name;
                    MinecraftAccess.c_3005_b.w_1484_f.n_1700_B(this.M_182_A);
                    this.h_4320_q.u_1723_Y(this.M_182_A);
                    return true;
                }
            }
            rowY += 40.0;
        }
        return super.mouseClicked(sx, sy, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.g_164_R) {
            this.g_164_R = false;
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.g_164_R && button == 0) {
            int baseScale = (int)MinecraftAccess.c_3005_b.RealmsServerPing().w_1457_N();
            double factor = (double)baseScale / 2.0;
            double sy = mouseY * factor;
            float windowWidth = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
            float windowHeight = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
            float centerY = (windowHeight - 420.0f) / 2.0f + 0.0f;
            float hCardH = 23.0f;
            float tCardH = 23.0f;
            float cTop = centerY + hCardH + 2.0f + tCardH + 2.0f;
            float cHeight = 410.0f - hCardH - 2.0f - tCardH - 2.0f - 50.0f - 20.0f;
            int filteredCount = 0;
            for (Map.Entry<String, Long> e : this.P_4830_p.entrySet()) {
                boolean bl = this.Y_601_j ? !this.h_4320_q.R_4764_Y(e.getKey()) : this.h_4320_q.R_4764_Y(e.getKey());
                boolean matchTab = bl;
                if (!matchTab || !this.u_1723_Y.isEmpty() && !e.getKey().toLowerCase().contains(this.u_1723_Y.toLowerCase())) continue;
                ++filteredCount;
            }
            float totalContent = (float)filteredCount * 40.0f;
            float maxScroll = -totalContent + cHeight;
            float thumbH = Math.max(20.0f, cHeight * (cHeight / totalContent));
            float scrollRange = cHeight - thumbH;
            if (scrollRange > 0.0f) {
                float mouseDelta = (float)sy - this.X_933_l;
                float scrollPerPixel = maxScroll / scrollRange;
                g_221_o = this.G_624_v = u_530_F.n_1700_B(this.Z_976_R + mouseDelta * scrollPerPixel, maxScroll, 0.0f);
            }
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        boolean hovered;
        int baseScale = (int)MinecraftAccess.c_3005_b.RealmsServerPing().w_1457_N();
        double factor = (double)baseScale / 2.0;
        double sx = mouseX * factor;
        double sy = mouseY * factor;
        float windowWidth = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
        float windowHeight = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
        float centerX = (windowWidth - 360.0f) / 2.0f;
        float centerY = (windowHeight - 420.0f) / 2.0f + 0.0f;
        float headerCardH = 23.0f;
        float tabCardH = 23.0f;
        float contentLeft = centerX + 20.0f;
        float contentWidth = 320.0f;
        float contentTop = centerY + headerCardH + 2.0f + tabCardH + 2.0f;
        float contentHeight = 410.0f - headerCardH - 2.0f - tabCardH - 2.0f - 50.0f - 20.0f;
        boolean bl = hovered = sx >= (double)contentLeft && sx <= (double)(contentLeft + contentWidth) && sy >= (double)contentTop && sy <= (double)(contentTop + contentHeight);
        if (hovered) {
            ArrayList<Map.Entry<String, Long>> filteredAccounts = new ArrayList<Map.Entry<String, Long>>();
            for (Map.Entry<String, Long> entry : this.P_4830_p.entrySet()) {
                boolean bl2 = this.Y_601_j ? !this.h_4320_q.R_4764_Y(entry.getKey()) : this.h_4320_q.R_4764_Y(entry.getKey());
                boolean matchTab = bl2;
                if (!matchTab || !this.u_1723_Y.isEmpty() && !entry.getKey().toLowerCase().contains(this.u_1723_Y.toLowerCase())) continue;
                filteredAccounts.add(entry);
            }
            float maxHeight = (float)filteredAccounts.size() * 40.0f;
            if (maxHeight > contentHeight) {
                float previousScroll = this.G_624_v;
                this.G_624_v += (float)(delta * 20.0);
                g_221_o = this.G_624_v = u_530_F.n_1700_B(this.G_624_v, -maxHeight + contentHeight, 0.0f);
                return this.G_624_v != previousScroll;
            }
        }
        return false;
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (!Character.toString(codePoint).matches("[a-zA-Z0-9_]")) {
            return true;
        }
        if (this.t_148_a && !this.n_1700_B) {
            if (this.u_2550_I) {
                this.v_4262_N = String.valueOf(codePoint);
                this.u_2550_I = false;
            } else if (this.v_4262_N.length() < 16) {
                this.v_4262_N = this.v_4262_N + codePoint;
            }
            return true;
        }
        if (this.w_1484_f && !this.n_1700_B) {
            if (this.s_956_w) {
                this.u_1723_Y = String.valueOf(codePoint);
                this.s_956_w = false;
            } else if (this.u_1723_Y.length() < 16) {
                this.u_1723_Y = this.u_1723_Y + codePoint;
            }
            return true;
        }
        return super.charTyped(codePoint, modifiers);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean panelHovered;
        float windowWidth = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
        float windowHeight = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
        float centerX = (windowWidth - 360.0f) / 2.0f;
        float centerY = (windowHeight - 420.0f) / 2.0f + 0.0f;
        double mouseX = MinecraftAccess.c_3005_b.h_1847_R.G_564_y() * (double)MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t() / (double)MinecraftAccess.c_3005_b.RealmsServerPing().P_4830_p();
        double mouseY = MinecraftAccess.c_3005_b.h_1847_R.P_1922_E() * (double)MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A() / (double)MinecraftAccess.c_3005_b.RealmsServerPing().h_1847_R();
        boolean bl = panelHovered = mouseX >= (double)centerX && mouseX <= (double)(centerX + 360.0f) && mouseY >= (double)centerY && mouseY <= (double)(centerY + 420.0f);
        if (panelHovered && keyCode == 86 && (modifiers & 2) != 0 && !this.n_1700_B) {
            String clipboardText = MinecraftAccess.c_3005_b.Q_4569_t.n_1700_B();
            if (!clipboardText.isEmpty()) {
                String trimmedText = clipboardText.trim();
                if (!(trimmedText = trimmedText.substring(0, Math.min(trimmedText.length(), 16))).isEmpty() && !this.P_4830_p.containsKey(trimmedText)) {
                    this.P_4830_p.put(trimmedText, System.currentTimeMillis());
                    this.h_4320_q.n_1700_B(trimmedText);
                    this.R_4764_Y();
                }
            }
            return true;
        }
        if (this.t_148_a && !this.n_1700_B) {
            if (keyCode == 259) {
                if (this.u_2550_I) {
                    this.v_4262_N = "";
                    this.u_2550_I = false;
                } else if (!this.v_4262_N.isEmpty()) {
                    this.v_4262_N = this.v_4262_N.substring(0, this.v_4262_N.length() - 1);
                }
                return true;
            }
            if (keyCode == 257 && !this.v_4262_N.trim().isEmpty()) {
                String accountName = this.v_4262_N.trim();
                if (!this.P_4830_p.containsKey(accountName)) {
                    this.P_4830_p.put(accountName, System.currentTimeMillis());
                    this.h_4320_q.n_1700_B(accountName);
                    this.R_4764_Y();
                    this.v_4262_N = "";
                    this.t_148_a = false;
                    this.u_2550_I = false;
                }
                return true;
            }
            if (keyCode == 65 && (modifiers & 2) != 0) {
                if (!this.v_4262_N.isEmpty()) {
                    this.u_2550_I = true;
                }
                return true;
            }
            if (keyCode == 67 && (modifiers & 2) != 0) {
                if (this.u_2550_I && !this.v_4262_N.isEmpty()) {
                    MinecraftAccess.c_3005_b.Q_4569_t.n_1700_B(this.v_4262_N);
                }
                return true;
            }
            if (keyCode == 86 && (modifiers & 2) != 0) {
                String clipboardText = MinecraftAccess.c_3005_b.Q_4569_t.n_1700_B();
                if (!clipboardText.isEmpty()) {
                    clipboardText = clipboardText.substring(0, Math.min(clipboardText.length(), 16));
                    if (this.u_2550_I) {
                        this.v_4262_N = clipboardText;
                        this.u_2550_I = false;
                    } else {
                        int remainingChars = 16 - this.v_4262_N.length();
                        if (remainingChars > 0) {
                            this.v_4262_N = this.v_4262_N + clipboardText.substring(0, Math.min(clipboardText.length(), remainingChars));
                        }
                    }
                }
                return true;
            }
        } else if (this.w_1484_f && !this.n_1700_B) {
            if (keyCode == 259) {
                if (this.s_956_w) {
                    this.u_1723_Y = "";
                    this.s_956_w = false;
                } else if (!this.u_1723_Y.isEmpty()) {
                    this.u_1723_Y = this.u_1723_Y.substring(0, this.u_1723_Y.length() - 1);
                }
                return true;
            }
            if (keyCode == 65 && (modifiers & 2) != 0) {
                if (!this.u_1723_Y.isEmpty()) {
                    this.s_956_w = true;
                }
                return true;
            }
            if (keyCode == 67 && (modifiers & 2) != 0) {
                if (this.s_956_w && !this.u_1723_Y.isEmpty()) {
                    MinecraftAccess.c_3005_b.Q_4569_t.n_1700_B(this.u_1723_Y);
                }
                return true;
            }
            if (keyCode == 86 && (modifiers & 2) != 0) {
                String clipboardText = MinecraftAccess.c_3005_b.Q_4569_t.n_1700_B();
                if (!clipboardText.isEmpty()) {
                    clipboardText = clipboardText.substring(0, Math.min(clipboardText.length(), 16));
                    if (this.s_956_w) {
                        this.u_1723_Y = clipboardText;
                        this.s_956_w = false;
                    } else {
                        int remainingChars = 16 - this.u_1723_Y.length();
                        if (remainingChars > 0) {
                            this.u_1723_Y = this.u_1723_Y + clipboardText.substring(0, Math.min(clipboardText.length(), remainingChars));
                        }
                    }
                }
                return true;
            }
            if (keyCode == 256) {
                this.u_1723_Y = "";
                this.w_1484_f = false;
                this.s_956_w = false;
                return true;
            }
        }
        if (keyCode == 256) {
            if (this.n_1700_B) {
                this.n_1700_B = false;
                return true;
            }
            if (this.t_148_a) {
                this.t_148_a = false;
                this.u_2550_I = false;
                return true;
            }
            this.closeScreen();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}



