/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.D_563_q;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.I_1084_e;
import lightning.product.K_1964_I;
import lightning.product.R_4398_I;
import lightning.product.S_2919_l;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.MinecraftAccess;
import lightning.product.e_465_j;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.l_3370_o;
import lightning.product.q_3131_N;
import lightning.product.u_530_F;
import lightning.product.y_4842_Z;
import lombok.Generated;

public class O_922_L
extends k_2603_m {
    private final List<n_1700_B> J_1907_R = new ArrayList<n_1700_B>();
    private static final float R_4764_Y = 128.0f;
    private static final float G_564_y = 32.0f;
    private static final float P_1922_E = 4.0f;
    private static final float u_1723_Y = 65.0f;
    private static final float v_4262_N = 23.0f;
    private static final float w_1484_f = 23.0f;
    private static final float t_148_a = 4.0f;
    private static final float s_956_w = 8.0f;
    private static final float u_2550_I = 128.0f;
    private static final float M_588_G = 8.0f;
    private static final float P_4830_p = 0.5f;
    private static final float h_1847_R = 0.4f;
    private static final int Q_4569_t = H_2506_c.n_1700_B("#FFFFFFFF");
    private static final int M_182_A = H_2506_c.n_1700_B("#000000FF");
    private static final float t_1786_h = 0.5f;
    private static final int multiplayerClientSuggestionProvider = H_2506_c.n_1700_B("#FFFFFF15");
    private static final int w_1457_N = H_2506_c.n_1700_B("#FFFFFF05");
    private static final int Y_601_j = H_2506_c.n_1700_B("#FFFFFF18");
    private static final int Y_259_p = H_2506_c.n_1700_B("#6F5EF6");
    private static final int Q_2552_b = H_2506_c.n_1700_B("#1A1A1AFF");
    private static final int C_2741_M = H_2506_c.n_1700_B("#4A4A4AFF");
    private static final int k_2293_S = H_2506_c.n_1700_B("#6F5EF6FF");
    private static final int q_2307_F = H_2506_c.n_1700_B("#A2A6FF");
    private static final int Z_875_P = H_2506_c.n_1700_B("#D0DAF0FF");
    private static final int c_3005_b = H_2506_c.n_1700_B("#A4ABABA8");
    private float H_2857_Y = 0.0f;
    private float A_4115_X = 0.0f;
    private static boolean Y_1740_V = true;

    public static boolean n_1700_B() {
        return Y_1740_V;
    }

    public O_922_L() {
        super(I_1084_e.n_1700_B);
    }

    @Override
    protected void init() {
        super.init();
        this.J_1907_R.clear();
        if (MinecraftAccess.c_3005_b.RealmsServerPing() != null) {
            MinecraftAccess.c_3005_b.RealmsServerPing().n_1700_B(Y_1740_V);
        }
        int windowWidth = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
        int windowHeight = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
        float totalWidth = 260.0f;
        float totalHeight = 68.0f;
        float startX = ((float)windowWidth - totalWidth) / 2.0f;
        float startY = ((float)windowHeight - totalHeight) / 2.0f;
        this.n_1700_B("u", "Singleplayer", "Create and explore offline worlds.", startX, startY, () -> {
            assert (this.minecraft != null);
            this.minecraft.n_1700_B(new R_4398_I(this));
        });
        this.n_1700_B("o", "Multiplayer", "Join servers and play with others.", startX + 128.0f + 4.0f, startY, () -> {
            assert (this.minecraft != null);
            k_2603_m screen = this.minecraft.P_4830_p.l_1233_K ? new q_3131_N(this) : new S_2919_l(this);
            this.minecraft.n_1700_B(screen);
        });
        this.n_1700_B("c", "AltManager", "Manage accounts and switch alts.", startX, startY + 32.0f + 4.0f, () -> {
            assert (this.minecraft != null);
            this.minecraft.n_1700_B(new D_563_q(this));
        });
        this.n_1700_B("t", "Settings", "Configure client and your preferences.", startX + 128.0f + 4.0f, startY + 32.0f + 4.0f, () -> {
            assert (this.minecraft != null);
            this.minecraft.n_1700_B(new e_465_j(this, this.minecraft.P_4830_p));
        });
    }

    private void n_1700_B(String icon, String title, String description, float x, float y, Runnable action) {
        n_1700_B button = new n_1700_B((int)x, (int)y, 128, 32, icon, title, description, btn -> action.run());
        this.J_1907_R.add(button);
        this.buttons.add(button);
    }

    @Override
    public void render(g_221_o matrices, int mouseX, int mouseY, float delta) {
        int baseScale = (int)MinecraftAccess.c_3005_b.RealmsServerPing().w_1457_N();
        MinecraftAccess.c_3005_b.s_956_w.n_1700_B(2.0f);
        float factor = (float)baseScale / 2.0f;
        mouseX = (int)((float)mouseX * factor);
        mouseY = (int)((float)mouseY * factor);
        int windowWidth = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
        int windowHeight = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
        int hoverMouseX = mouseX;
        int hoverMouseY = mouseY;
        if (MinecraftAccess.c_3005_b.Y_1740_V != this) {
            hoverMouseX = -10000;
            hoverMouseY = -10000;
        }
        float totalWidth = 260.0f;
        float totalHeight = 68.0f;
        float startX = ((float)windowWidth - totalWidth) / 2.0f;
        float startY = ((float)windowHeight - totalHeight) / 2.0f;
        for (int i = 0; i < this.J_1907_R.size(); ++i) {
            n_1700_B b = this.J_1907_R.get(i);
            int col = i % 2;
            int row = i / 2;
            b.x = (int)(startX + (float)col * 132.0f);
            b.y = (int)(startY + (float)row * 36.0f);
        }
        O_922_L.fill(matrices, 0, 0, windowWidth, windowHeight, M_182_A);
        float normMouseX = (float)mouseX / (float)windowWidth * 2.0f - 1.0f;
        float normMouseY = (float)mouseY / (float)windowHeight * 2.0f - 1.0f;
        float parallaxStrength = 2.0f;
        float targetOffsetX = normMouseX * parallaxStrength;
        float targetOffsetY = normMouseY * parallaxStrength;
        this.H_2857_Y = u_530_F.v_4262_N(0.02f, this.H_2857_Y, targetOffsetX);
        this.A_4115_X = u_530_F.v_4262_N(0.02f, this.A_4115_X, targetOffsetY);
        float bgScale = 1.01f;
        float renderWidth = (float)windowWidth * bgScale;
        float renderHeight = (float)windowHeight * bgScale;
        float bgX = -(renderWidth - (float)windowWidth) / 2.0f + this.H_2857_Y;
        float bgY = -(renderHeight - (float)windowHeight) / 2.0f + this.A_4115_X;
        String bgTexture = Y_1740_V ? "Pouch/icons/mainmenu/bg.png" : "Pouch/icons/mainmenu/background_light.png";
        F_489_x.n_1700_B(new g_2336_b(bgTexture), bgX, bgY, renderWidth, renderHeight, -1);
        if (!Y_1740_V) {
            O_922_L.fill(matrices, 0, 0, windowWidth, windowHeight, H_2506_c.n_1700_B(Q_4569_t, 0.5f));
        }
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/mainmenu/noise.png"), bgX, bgY, renderWidth, renderHeight, H_2506_c.n_1700_B(-1, Y_1740_V ? 0.5f : 0.4f));
        this.n_1700_B(matrices, windowWidth, windowHeight);
        y_4842_Z.n_1700_B.n_1700_B(4.0f, 3);
        for (n_1700_B btn : this.J_1907_R) {
            btn.render(matrices, hoverMouseX, hoverMouseY, delta);
        }
        this.n_1700_B(matrices, hoverMouseX, hoverMouseY, windowWidth, windowHeight);
        this.J_1907_R(matrices, hoverMouseX, hoverMouseY, windowWidth, windowHeight);
        MinecraftAccess.c_3005_b.s_956_w.R_4764_Y();
    }

    private static int n_1700_B(int light, int dark) {
        return Y_1740_V ? dark : light;
    }

    private void n_1700_B(g_221_o matrices, int windowWidth, int windowHeight) {
        float totalWidth = 260.0f;
        float totalHeight = 68.0f;
        float startY = ((float)windowHeight - totalHeight) / 2.0f;
        String logoText = "Pouch Client";
        float logoHeight = l_3370_o.G_564_y[23].h_1847_R();
        float textWidth = l_3370_o.G_564_y[23].n_1700_B(logoText);
        float gridCenterX = (float)windowWidth / 2.0f;
        float logoX = gridCenterX - textWidth / 2.0f;
        int logoColor = O_922_L.n_1700_B(Y_259_p, q_2307_F);
        int textColor = O_922_L.n_1700_B(Q_2552_b, Z_875_P);
        int textInactive = O_922_L.n_1700_B(C_2741_M, c_3005_b);
        l_3370_o.w_1484_f[40].n_1700_B(matrices, "P", (double)(gridCenterX - l_3370_o.w_1484_f[40].n_1700_B("P") / 2.0f), (double)(startY - 40.0f - l_3370_o.w_1484_f[40].h_1847_R()), logoColor);
        l_3370_o.G_564_y[23].n_1700_B(matrices, logoText, (double)logoX, (double)(startY - 20.0f - logoHeight), textColor);
        l_3370_o.R_4764_Y[15].n_1700_B(matrices, "pouchclient.fun", (double)(gridCenterX - l_3370_o.R_4764_Y[15].n_1700_B("pouchclient.fun") / 2.0f), (double)(startY - 8.0f - logoHeight), textInactive);
    }

    private void n_1700_B(g_221_o matrices, int mouseX, int mouseY, int windowWidth, int windowHeight) {
        float startX;
        float gridTotalHeight = 68.0f;
        float gridStartY = ((float)windowHeight - gridTotalHeight) / 2.0f;
        float iconsY = gridStartY + gridTotalHeight + 8.0f;
        float totalW = 230.0f;
        float x = startX = ((float)windowWidth - totalW) / 2.0f;
        this.n_1700_B(matrices, mouseX, mouseY, x, iconsY, 65.0f, "v", "Telegram", true);
        this.n_1700_B(matrices, mouseX, mouseY, x += 69.0f, iconsY, 65.0f, "h", "Discord", true);
        this.n_1700_B(matrices, mouseX, mouseY, x += 69.0f, iconsY, 65.0f, "d", "Changelog", true);
        String moonIcon = Y_1740_V ? "f" : "l";
        this.n_1700_B(matrices, mouseX, mouseY, x += 69.0f, iconsY, 23.0f, moonIcon, null, false);
    }

    private void J_1907_R(g_221_o matrices, int mouseX, int mouseY, int windowWidth, int windowHeight) {
        float gridTotalHeight = 68.0f;
        float gridStartY = ((float)windowHeight - gridTotalHeight) / 2.0f;
        float iconsY = gridStartY + gridTotalHeight + 8.0f;
        float quitY = iconsY + 23.0f + 20.0f;
        float quitX = ((float)windowWidth - 128.0f) / 2.0f;
        this.n_1700_B(matrices, mouseX, mouseY, quitX, quitY, 128.0f, "i", "Quit", true);
    }

    private void n_1700_B(g_221_o matrices, int mouseX, int mouseY, float x, float y, float width, String icon, String text, boolean hasText) {
        boolean hovered = (float)mouseX >= x && (float)mouseX <= x + width && (float)mouseY >= y && (float)mouseY <= y + 23.0f;
        int bgColor = hovered ? multiplayerClientSuggestionProvider : w_1457_N;
        int textColor = hovered ? O_922_L.n_1700_B(Q_2552_b, Z_875_P) : O_922_L.n_1700_B(C_2741_M, c_3005_b);
        int iconColor = hovered ? k_2293_S : textColor;
        int outlineColor = Y_601_j;
        float outlineAlpha = H_2506_c.G_564_y(Y_601_j);
        F_489_x.n_1700_B(x, y, width, 23.0f, 6.0f, bgColor, 1.0f);
        F_489_x.J_1907_R(x, y, width, 23.0f, 6.0f, outlineColor, outlineAlpha);
        float iconW = l_3370_o.t_148_a[16].n_1700_B(icon);
        float iconY = y + (23.0f - l_3370_o.t_148_a[16].h_1847_R()) / 2.0f + 1.0f;
        if (hasText && text != null) {
            float textW = l_3370_o.G_564_y[13].n_1700_B(text);
            float gap = 6.0f;
            float totalW = iconW + gap + textW;
            float startX = x + (width - totalW) / 2.0f;
            l_3370_o.t_148_a[16].n_1700_B(matrices, icon, (double)startX, (double)iconY, iconColor);
            float textY = y + (23.0f - l_3370_o.G_564_y[13].h_1847_R()) / 2.0f + 1.0f;
            l_3370_o.G_564_y[13].n_1700_B(matrices, text, (double)(startX + iconW + gap), (double)textY, textColor);
        } else {
            float centerX = x + width / 2.0f;
            float iconX = Math.round(centerX - iconW / 2.0f);
            l_3370_o.t_148_a[16].n_1700_B(matrices, icon, (double)iconX, (double)iconY, iconColor);
        }
    }

    private void J_1907_R() {
        boolean bl = Y_1740_V = !Y_1740_V;
        if (MinecraftAccess.c_3005_b.RealmsServerPing() != null) {
            MinecraftAccess.c_3005_b.RealmsServerPing().n_1700_B(Y_1740_V);
        }
    }

    private boolean isClickInside(double rawX, double rawY, double scaledX, double scaledY, float x, float y, float width, float height) {
        return rawX >= (double)x && rawX <= (double)(x + width) && rawY >= (double)y && rawY <= (double)(y + height)
            || scaledX >= (double)x && scaledX <= (double)(x + width) && scaledY >= (double)y && scaledY <= (double)(y + height);
    }
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int baseScale = (int)MinecraftAccess.c_3005_b.RealmsServerPing().w_1457_N();
        double factor = (double)baseScale / 2.0;
        double sx = mouseX * factor;
        double sy = mouseY * factor;
        int windowWidth = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
        int windowHeight = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
        float gridTotalHeight = 68.0f;
        float gridStartY = ((float)windowHeight - gridTotalHeight) / 2.0f;
        float iconsY = gridStartY + gridTotalHeight + 8.0f;
        float totalW = 230.0f;
        float startX = ((float)windowWidth - totalW) / 2.0f;
        float x = startX;
        if (this.isClickInside(mouseX, mouseY, sx, sy, x, iconsY, 65.0f, 23.0f)) {
            j_3341_s.t_148_a().n_1700_B("https://t.me/pouchclient");
            return true;
        }
        if (this.isClickInside(mouseX, mouseY, sx, sy, x += 69.0f, iconsY, 65.0f, 23.0f)) {
            j_3341_s.t_148_a().n_1700_B("https://discord.gg/7Jw99gnDXF");
            return true;
        }
        if (this.isClickInside(mouseX, mouseY, sx, sy, x += 69.0f, iconsY, 65.0f, 23.0f)) {
            assert (this.minecraft != null);
            this.minecraft.n_1700_B(new K_1964_I(this));
            return true;
        }
        if (this.isClickInside(mouseX, mouseY, sx, sy, x += 69.0f, iconsY, 23.0f, 23.0f)) {
            this.J_1907_R();
            return true;
        }
        float quitY = iconsY + 23.0f + 20.0f;
        float quitX = ((float)windowWidth - 128.0f) / 2.0f;
        if (this.isClickInside(mouseX, mouseY, sx, sy, quitX, quitY, 128.0f, 23.0f)) {
            assert (this.minecraft != null);
            this.minecraft.h_1847_R();
            return true;
        }
        boolean handled = false;
        for (n_1700_B widget : this.J_1907_R) {
            if (!widget.mouseClicked(sx, sy, button) && !widget.mouseClicked(mouseX, mouseY, button)) continue;
            handled = true;
        }
        return handled || super.mouseClicked(sx, sy, button);
    }

    public static class n_1700_B
    extends Button {
        private final String n_1700_B;
        private final String J_1907_R;

        public n_1700_B(int x, int y, int width, int height, String icon, String title, String description, Button.n_1700_B pressedAction) {
            super(x, y, width, height, new U_2871_b(title), pressedAction);
            this.n_1700_B = icon;
            this.J_1907_R = description;
        }

        @Override
        public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
            float buttonX = this.x;
            float buttonY = this.y;
            boolean hovered = (float)mouseX >= buttonX && (float)mouseX <= buttonX + 128.0f && (float)mouseY >= buttonY && (float)mouseY <= buttonY + 32.0f;
            int bgColor = hovered ? multiplayerClientSuggestionProvider : w_1457_N;
            int outlineColor = Y_601_j;
            float outlineAlpha = H_2506_c.G_564_y(Y_601_j);
            F_489_x.n_1700_B(buttonX, buttonY, 128.0f, 32.0f, 8.0f, bgColor, 1.0f);
            F_489_x.J_1907_R(buttonX, buttonY, 128.0f, 32.0f, 8.0f, outlineColor, outlineAlpha);
            float padding = 14.0f;
            float iconSize = 22.0f;
            float iconY = buttonY + (32.0f - iconSize) / 2.0f;
            int titleColor = O_922_L.n_1700_B(Q_2552_b, Z_875_P);
            int descColor = O_922_L.n_1700_B(C_2741_M, c_3005_b);
            int iconColor = hovered ? k_2293_S : O_922_L.n_1700_B(C_2741_M, c_3005_b);
            l_3370_o.t_148_a[18].n_1700_B(matrixStack, this.n_1700_B, (double)(buttonX + 8.0f), (double)(buttonY + l_3370_o.G_564_y[16].h_1847_R() + 4.5f), iconColor);
            l_3370_o.G_564_y[15].n_1700_B(matrixStack, this.getMessage().getString(), (double)(buttonX + l_3370_o.t_148_a[18].n_1700_B(this.n_1700_B) + 12.0f), (double)(buttonY + l_3370_o.G_564_y[16].h_1847_R() + 4.0f), titleColor);
            l_3370_o.R_4764_Y[12].n_1700_B(matrixStack, this.J_1907_R, (double)(buttonX + l_3370_o.t_148_a[18].n_1700_B(this.n_1700_B)), (double)(buttonY + l_3370_o.t_148_a[18].h_1847_R() * 2.0f + l_3370_o.R_4764_Y[14].h_1847_R() + 6.0f), descColor);
            float totalLineHeight = l_3370_o.G_564_y[15].h_1847_R() + l_3370_o.R_4764_Y[12].h_1847_R() + l_3370_o.t_148_a[18].h_1847_R();
            float chevronW = l_3370_o.G_564_y[14].n_1700_B("\u203a");
            float chevronX = buttonX + 128.0f - padding - chevronW;
            l_3370_o.t_148_a[14].n_1700_B(matrixStack, "r", (double)chevronX, (double)(buttonY + (32.0f - l_3370_o.G_564_y[14].h_1847_R()) / 2.0f + 0.5f), descColor);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            boolean isWithinBounds;
            boolean bl = isWithinBounds = mouseX >= (double)this.x && mouseX < (double)((float)this.x + 128.0f) && mouseY >= (double)this.y && mouseY < (double)((float)this.y + 32.0f);
            if (isWithinBounds && this.active && this.visible) {
                this.onClick(mouseX, mouseY);
                return true;
            }
            return false;
        }

        @Generated
        public String n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public String J_1907_R() {
            return this.J_1907_R;
        }
    }
}



