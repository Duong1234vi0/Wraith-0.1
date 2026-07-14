/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.U_2871_b;
import lightning.product.MinecraftAccess;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.k_2603_m;
import lightning.product.l_3370_o;
import lightning.product.n_4915_F;
import lightning.product.ClientBootstrap;
import lightning.product.u_530_F;

public class Z_1243_X
extends k_2603_m
implements MinecraftAccess {
    private final Animation n_1700_B = new Animation(1.0f, 10.0f, Easing.u_2550_I);
    private final Animation J_1907_R = new Animation(1.0f, 10.0f, Easing.u_2550_I);
    private final Animation R_4764_Y = new Animation(-10.0f, 10.0f, Easing.u_2550_I);
    private final Animation G_564_y = new Animation(-10.0f, 10.0f, Easing.u_2550_I);

    public Z_1243_X() {
        super(new U_2871_b(""));
        this.n_1700_B.J_1907_R(1.0f);
        this.J_1907_R.J_1907_R(1.0f);
        this.R_4764_Y.J_1907_R(-10.0f);
        this.G_564_y.J_1907_R(-10.0f);
    }

    @Override
    protected void init() {
        super.init();
        this.n_1700_B.J_1907_R(1.0f);
        this.J_1907_R.J_1907_R(1.0f);
        this.R_4764_Y.J_1907_R(-10.0f);
        this.G_564_y.J_1907_R(-10.0f);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        int screenWidth = c_3005_b.RealmsServerPing().Q_4569_t();
        int screenHeight = c_3005_b.RealmsServerPing().M_182_A();
        int renderWidth = (int)((float)screenWidth * 1.05f);
        int renderHeight = (int)((float)screenHeight * 1.05f);
        float normMouseX = (float)mouseX / (float)screenWidth * 2.0f - 1.0f;
        float normMouseY = (float)mouseY / (float)screenHeight * 2.0f - 1.0f;
        float maxOffsetX = (float)(renderWidth - screenWidth) / 2.0f;
        float maxOffsetY = (float)(renderHeight - screenHeight) / 2.0f;
        float offsetXBackground = u_530_F.n_1700_B(normMouseX * 3.0f, -maxOffsetX, maxOffsetX);
        float offsetYBackground = u_530_F.n_1700_B(normMouseY * 3.0f, -maxOffsetY, maxOffsetY);
        float offsetXBlack = u_530_F.n_1700_B(normMouseX * 2.0f, -maxOffsetX, maxOffsetX);
        float offsetYBlack = u_530_F.n_1700_B(normMouseY * 2.0f, -maxOffsetY, maxOffsetY);
        float offsetXBlue = u_530_F.n_1700_B(normMouseX * 2.0f, -maxOffsetX, maxOffsetX);
        float offsetYBlue = u_530_F.n_1700_B(normMouseY * 2.0f, -maxOffsetY, maxOffsetY);
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/mainmenu/blue_background.png"), offsetXBlue - (float)(renderWidth - screenWidth) / 2.0f, offsetYBlue - (float)(renderHeight - screenHeight) / 2.0f, (float)renderWidth, (float)renderHeight, -1);
        boolean shouldSwap = System.currentTimeMillis() / 500L % 2L == 0L;
        String title = shouldSwap ? "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u044f\u0437\u044b\u043a" : "Select language";
        float centerX = (float)screenWidth / 2.0f;
        float centerY = (float)screenHeight / 2.0f;
        float titleX = centerX - l_3370_o.J_1907_R[34].n_1700_B(title) / 2.0f;
        float titleY = centerY - 20.0f;
        l_3370_o.J_1907_R[34].n_1700_B(matrixStack, title, (double)titleX, (double)titleY, -1);
        float totalW = 100.0f;
        float startX = centerX - totalW / 2.0f;
        float iconWidth = 48.0f;
        float iconHeight = 48.0f;
        float spacing = 4.0f;
        float iconY = centerY - 4.0f;
        float ukX = startX + iconWidth + spacing;
        boolean hoverRu = (float)mouseX >= startX && (float)mouseX <= startX + 46.0f && (float)mouseY >= iconY + 8.0f && (float)mouseY <= iconY + 38.0f;
        boolean hoverUk = (float)mouseX >= ukX && (float)mouseX <= ukX + 46.0f && (float)mouseY >= iconY + 8.0f && (float)mouseY <= iconY + 38.0f;
        this.n_1700_B.n_1700_B(hoverRu ? 0.9f : 1.0f);
        this.J_1907_R.n_1700_B(hoverUk ? 0.9f : 1.0f);
        this.R_4764_Y.n_1700_B(hoverRu ? 0.0f : -10.0f);
        this.G_564_y.n_1700_B(hoverUk ? 0.0f : -10.0f);
        float ruCenterX = startX + iconWidth / 2.0f;
        float ruCenterY = iconY + iconHeight / 2.0f;
        float ukCenterX = ukX + iconWidth / 2.0f;
        float ukCenterY = iconY + iconHeight / 2.0f;
        String ruText = "\u0420\u0443\u0441\u0441\u043a\u0438\u0439";
        String ukText = "English";
        float ruTextX = ruCenterX - l_3370_o.J_1907_R[15].n_1700_B(ruText) / 2.0f;
        float ukTextX = ukCenterX - l_3370_o.J_1907_R[15].n_1700_B(ukText) / 2.0f;
        float baseTextY = iconY + iconHeight - 6.0f;
        float ruTextPosY = baseTextY + this.R_4764_Y.n_1700_B();
        float ukTextPosY = baseTextY + this.G_564_y.n_1700_B();
        if (hoverRu || this.R_4764_Y.n_1700_B() > -9.0f) {
            l_3370_o.J_1907_R[15].n_1700_B(matrixStack, ruText, (double)ruTextX, (double)ruTextPosY, -1);
        }
        if (hoverUk || this.G_564_y.n_1700_B() > -9.0f) {
            l_3370_o.J_1907_R[15].n_1700_B(matrixStack, ukText, (double)ukTextX, (double)ukTextPosY, -1);
        }
        F_489_x.n_1700_B(ruCenterX, ruCenterY, this.n_1700_B.n_1700_B());
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/locale/ru.png"), startX, iconY, iconWidth, iconHeight, -1);
        F_489_x.R_4764_Y();
        F_489_x.n_1700_B(ukCenterX, ukCenterY, this.J_1907_R.n_1700_B());
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/locale/uk.png"), ukX, iconY, iconWidth, iconHeight, -1);
        F_489_x.R_4764_Y();
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int mx = (int)mouseX;
        int my = (int)mouseY;
        int screenWidth = c_3005_b.RealmsServerPing().Q_4569_t();
        int screenHeight = c_3005_b.RealmsServerPing().M_182_A();
        float centerX = (float)screenWidth / 2.0f;
        float totalW = 100.0f;
        float startX = centerX - totalW / 2.0f;
        float iconWidth = 48.0f;
        float spacing = 4.0f;
        float iconY = (float)screenHeight / 2.0f - 4.0f;
        float ukX = startX + iconWidth + spacing;
        if (button == 0) {
            if ((float)mx >= startX && (float)mx <= startX + 46.0f && (float)my >= iconY + 8.0f && (float)my <= iconY + 38.0f) {
                n_4915_F cfg = ClientBootstrap.Y_601_j().u_1723_Y();
                cfg.P_1922_E("ru");
                c_3005_b.n_1700_B((k_2603_m)null);
                return true;
            }
            if ((float)mx >= ukX && (float)mx <= ukX + 46.0f && (float)my >= iconY + 8.0f && (float)my <= iconY + 38.0f) {
                n_4915_F cfg = ClientBootstrap.Y_601_j().u_1723_Y();
                cfg.P_1922_E("en");
                c_3005_b.n_1700_B((k_2603_m)null);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            c_3005_b.n_1700_B((k_2603_m)null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}



