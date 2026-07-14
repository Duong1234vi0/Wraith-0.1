/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.u_530_F;

public class D_940_S {
    public static void n_1700_B(MinecraftClient mc, g_221_o matrixStack, int screenWidth, int screenHeight, int mouseX, int mouseY, float alpha, float progress, float alphaBar) {
        int renderWidth = (int)((float)screenWidth * 1.05f);
        int renderHeight = (int)((float)screenHeight * 1.05f);
        int alphaFull = u_530_F.u_1723_Y(alpha * 255.0f);
        int colorFull = H_2506_c.n_1700_B(-1, alphaFull);
        int alphaBlack = u_530_F.u_1723_Y(200.0f * alpha);
        int colorBlack = H_2506_c.n_1700_B(-1, alphaBlack);
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/mainmenu/bg.png"), (float)(-(renderWidth - screenWidth)) / 2.0f, (float)(-(renderHeight - screenHeight)) / 2.0f, (float)renderWidth, (float)renderHeight, colorBlack);
        float targetLogoWidth = 180.0f;
        float targetLogoHeight = 207.0f;
        float logoX = ((float)screenWidth - targetLogoWidth) / 2.0f;
        float logoY = ((float)screenHeight - targetLogoHeight) / 2.0f;
        int alphaLogoFull = u_530_F.u_1723_Y(alpha * alphaBar * 255.0f);
        int colorLogo = H_2506_c.n_1700_B(-1, alphaLogoFull);
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/mainmenu/icon.png"), logoX, logoY - 5.0f, targetLogoWidth, targetLogoHeight, colorLogo);
        float barW = 240.0f;
        float barH = 8.0f;
        float barX = ((float)screenWidth - barW) / 2.0f;
        float barY = ((float)screenHeight - barH) / 2.0f + 108.0f;
        int baseColor = H_2506_c.n_1700_B(0, 145, 250, 255);
        int darkColor = H_2506_c.J_1907_R(baseColor, 0.5f);
        F_489_x.n_1700_B(barX, barY, barW, barH, 2.5f, darkColor, darkColor, baseColor, baseColor, alpha * alphaBar * 0.25f);
        float fillW = u_530_F.u_1723_Y(barW * progress);
        if (fillW > 0.0f) {
            F_489_x.n_1700_B(barX, barY, fillW, barH, 2.5f, darkColor, darkColor, baseColor, baseColor, alpha * alphaBar);
        }
    }
}


