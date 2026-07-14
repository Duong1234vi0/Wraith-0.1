/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.Properties;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.X_933_l;
import lightning.product.g_2336_b;
import lightning.product.l_3747_P;
import net.optifine.Config;
import net.optifine.CustomLoadingScreens;

public class CustomLoadingScreen {
    private g_2336_b locationTexture;
    private int scaleMode = 0;
    private int scale = 2;
    private boolean center;
    private static final int SCALE_DEFAULT = 2;
    private static final int SCALE_MODE_FIXED = 0;
    private static final int SCALE_MODE_FULL = 1;
    private static final int SCALE_MODE_STRETCH = 2;

    public CustomLoadingScreen(g_2336_b locationTexture, int scaleMode, int scale, boolean center) {
        this.locationTexture = locationTexture;
        this.scaleMode = scaleMode;
        this.scale = scale;
        this.center = center;
    }

    public static CustomLoadingScreen parseScreen(String path, int dimId, Properties props) {
        g_2336_b resourcelocation = new g_2336_b(path);
        int i = CustomLoadingScreen.parseScaleMode(CustomLoadingScreen.getProperty("scaleMode", dimId, props));
        int j = i == 0 ? 2 : 1;
        int k = CustomLoadingScreen.parseScale(CustomLoadingScreen.getProperty("scale", dimId, props), j);
        boolean flag = Config.parseBoolean(CustomLoadingScreen.getProperty("center", dimId, props), false);
        return new CustomLoadingScreen(resourcelocation, i, k, flag);
    }

    private static String getProperty(String key, int dim, Properties props) {
        if (props == null) {
            return null;
        }
        String s = props.getProperty("dim" + dim + "." + key);
        return s != null ? s : props.getProperty(key);
    }

    private static int parseScaleMode(String str) {
        if (str == null) {
            return 0;
        }
        if ((str = str.toLowerCase().trim()).equals("fixed")) {
            return 0;
        }
        if (str.equals("full")) {
            return 1;
        }
        if (str.equals("stretch")) {
            return 2;
        }
        CustomLoadingScreens.warn("Invalid scale mode: " + str);
        return 0;
    }

    private static int parseScale(String str, int def) {
        if (str == null) {
            return def;
        }
        int i = Config.parseInt(str = str.trim(), -1);
        if (i < 1) {
            CustomLoadingScreens.warn("Invalid scale: " + str);
            return def;
        }
        return i;
    }

    public void drawBackground(int width, int height) {
        X_933_l.v_4262_N();
        X_933_l.H_2857_Y();
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        Config.getTextureManager().n_1700_B(this.locationTexture);
        X_933_l.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        float f = 16 * this.scale;
        float f1 = (float)width / f;
        float f2 = (float)height / f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        if (this.center) {
            f3 = (f - (float)width) / (f * 2.0f);
            f4 = (f - (float)height) / (f * 2.0f);
        }
        switch (this.scaleMode) {
            case 1: {
                f = Math.max(width, height);
                f1 = (float)(this.scale * width) / f;
                f2 = (float)(this.scale * height) / f;
                if (!this.center) break;
                f3 = (float)this.scale * (f - (float)width) / (f * 2.0f);
                f4 = (float)this.scale * (f - (float)height) / (f * 2.0f);
                break;
            }
            case 2: {
                f1 = this.scale;
                f2 = this.scale;
                f3 = 0.0f;
                f4 = 0.0f;
            }
        }
        bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
        bufferbuilder.pos(0.0, height, 0.0).tex(f3, f4 + f2).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(width, height, 0.0).tex(f3 + f1, f4 + f2).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(width, 0.0, 0.0).tex(f3 + f1, f4).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.0, 0.0, 0.0).tex(f3, f4).color(255, 255, 255, 255).endVertex();
        tessellator.J_1907_R();
    }
}

