/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import lightning.product.X_933_l;
import net.optifine.Config;

public class Blender {
    public static final int BLEND_ALPHA = 0;
    public static final int BLEND_ADD = 1;
    public static final int BLEND_SUBSTRACT = 2;
    public static final int BLEND_MULTIPLY = 3;
    public static final int BLEND_DODGE = 4;
    public static final int BLEND_BURN = 5;
    public static final int BLEND_SCREEN = 6;
    public static final int BLEND_OVERLAY = 7;
    public static final int BLEND_REPLACE = 8;
    public static final int BLEND_DEFAULT = 1;

    public static int parseBlend(String str) {
        if (str == null) {
            return 1;
        }
        if ((str = str.toLowerCase().trim()).equals("alpha")) {
            return 0;
        }
        if (str.equals("add")) {
            return 1;
        }
        if (str.equals("subtract")) {
            return 2;
        }
        if (str.equals("multiply")) {
            return 3;
        }
        if (str.equals("dodge")) {
            return 4;
        }
        if (str.equals("burn")) {
            return 5;
        }
        if (str.equals("screen")) {
            return 6;
        }
        if (str.equals("overlay")) {
            return 7;
        }
        if (str.equals("replace")) {
            return 8;
        }
        Config.warn("Unknown blend: " + str);
        return 1;
    }

    public static void setupBlend(int blend, float brightness) {
        switch (blend) {
            case 0: {
                X_933_l.G_564_y();
                X_933_l.Q_4569_t();
                X_933_l.J_1907_R(770, 771);
                X_933_l.G_564_y(1.0f, 1.0f, 1.0f, brightness);
                break;
            }
            case 1: {
                X_933_l.G_564_y();
                X_933_l.Q_4569_t();
                X_933_l.J_1907_R(770, 1);
                X_933_l.G_564_y(1.0f, 1.0f, 1.0f, brightness);
                break;
            }
            case 2: {
                X_933_l.G_564_y();
                X_933_l.Q_4569_t();
                X_933_l.J_1907_R(775, 0);
                X_933_l.G_564_y(brightness, brightness, brightness, 1.0f);
                break;
            }
            case 3: {
                X_933_l.G_564_y();
                X_933_l.Q_4569_t();
                X_933_l.J_1907_R(774, 771);
                X_933_l.G_564_y(brightness, brightness, brightness, brightness);
                break;
            }
            case 4: {
                X_933_l.G_564_y();
                X_933_l.Q_4569_t();
                X_933_l.J_1907_R(1, 1);
                X_933_l.G_564_y(brightness, brightness, brightness, 1.0f);
                break;
            }
            case 5: {
                X_933_l.G_564_y();
                X_933_l.Q_4569_t();
                X_933_l.J_1907_R(0, 769);
                X_933_l.G_564_y(brightness, brightness, brightness, 1.0f);
                break;
            }
            case 6: {
                X_933_l.G_564_y();
                X_933_l.Q_4569_t();
                X_933_l.J_1907_R(1, 769);
                X_933_l.G_564_y(brightness, brightness, brightness, 1.0f);
                break;
            }
            case 7: {
                X_933_l.G_564_y();
                X_933_l.Q_4569_t();
                X_933_l.J_1907_R(774, 768);
                X_933_l.G_564_y(brightness, brightness, brightness, 1.0f);
                break;
            }
            case 8: {
                X_933_l.P_1922_E();
                X_933_l.h_1847_R();
                X_933_l.G_564_y(1.0f, 1.0f, 1.0f, brightness);
            }
        }
        X_933_l.v_4276_D();
    }

    public static void clearBlend(float rainBrightness) {
        X_933_l.G_564_y();
        X_933_l.Q_4569_t();
        X_933_l.J_1907_R(770, 1);
        X_933_l.G_564_y(1.0f, 1.0f, 1.0f, rainBrightness);
    }
}

