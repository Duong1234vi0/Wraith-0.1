/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import lightning.product.F_747_P;
import lightning.product.u_530_F;

public class H_2506_c {
    public static int n_1700_B(int c) {
        return c >> 16 & 0xFF;
    }

    public static int J_1907_R(int c) {
        return c >> 8 & 0xFF;
    }

    public static int R_4764_Y(int c) {
        return c & 0xFF;
    }

    public static int G_564_y(int c) {
        return c >> 24 & 0xFF;
    }

    public static float[] P_1922_E(int color) {
        return new float[]{(float)H_2506_c.n_1700_B(color) / 255.0f, (float)H_2506_c.J_1907_R(color) / 255.0f, (float)H_2506_c.R_4764_Y(color) / 255.0f, (float)H_2506_c.G_564_y(color) / 255.0f};
    }

    public static int n_1700_B(int r, int g, int b, int a) {
        return u_530_F.n_1700_B(a, 0, 255) << 24 | u_530_F.n_1700_B(r, 0, 255) << 16 | u_530_F.n_1700_B(g, 0, 255) << 8 | u_530_F.n_1700_B(b, 0, 255);
    }

    public static int n_1700_B(int r, int g, int b) {
        return H_2506_c.n_1700_B(r, g, b, 255);
    }

    public static int n_1700_B(int color, float alpha) {
        return H_2506_c.n_1700_B(color, (int)(u_530_F.n_1700_B(alpha, 0.0f, 1.0f) * 255.0f));
    }

    public static int n_1700_B(int color, int alpha) {
        return H_2506_c.n_1700_B(H_2506_c.n_1700_B(color), H_2506_c.J_1907_R(color), H_2506_c.R_4764_Y(color), alpha);
    }

    public static int n_1700_B(int start, int end, float value) {
        double percent = u_530_F.n_1700_B(value, 0.0f, 1.0f);
        return H_2506_c.n_1700_B(F_747_P.n_1700_B(H_2506_c.n_1700_B(start), H_2506_c.n_1700_B(end), percent), F_747_P.n_1700_B(H_2506_c.J_1907_R(start), H_2506_c.J_1907_R(end), percent), F_747_P.n_1700_B(H_2506_c.R_4764_Y(start), H_2506_c.R_4764_Y(end), percent), F_747_P.n_1700_B(H_2506_c.G_564_y(start), H_2506_c.G_564_y(end), percent));
    }

    public static int J_1907_R(int color, float factor) {
        float[] rgb = H_2506_c.P_1922_E(color);
        float[] hsb = Color.RGBtoHSB((int)(rgb[0] * 255.0f), (int)(rgb[1] * 255.0f), (int)(rgb[2] * 255.0f), null);
        hsb[2] = hsb[2] * factor;
        hsb[2] = Math.max(0.0f, Math.min(1.0f, hsb[2]));
        return H_2506_c.n_1700_B(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]), rgb[3] * 255.0f);
    }

    public static int J_1907_R(int color, int boost) {
        return H_2506_c.n_1700_B(Math.min(255, H_2506_c.n_1700_B(color) + boost), Math.min(255, H_2506_c.J_1907_R(color) + boost), Math.min(255, H_2506_c.R_4764_Y(color) + boost), H_2506_c.G_564_y(color));
    }

    public static int n_1700_B(String hex) {
        int a;
        int b;
        Object s = hex.trim();
        if (((String)s).startsWith("#")) {
            s = ((String)s).substring(1);
        }
        if (((String)s).startsWith("0x") || ((String)s).startsWith("0X")) {
            s = ((String)s).substring(2);
        }
        if (((String)s).length() == 3 || ((String)s).length() == 4) {
            char c = ((String)s).charAt(0);
            char c2 = ((String)s).charAt(1);
            b = ((String)s).charAt(2);
            a = ((String)s).length() == 4 ? (int)((String)s).charAt(3) : 70;
            s = "" + c + c + c2 + c2 + (char)b + (char)b + (char)a + (char)a;
        }
        if (((String)s).length() == 6) {
            int n = Integer.parseInt(((String)s).substring(0, 2), 16);
            int n2 = Integer.parseInt(((String)s).substring(2, 4), 16);
            b = Integer.parseInt(((String)s).substring(4, 6), 16);
            return H_2506_c.n_1700_B(n, n2, b, 255);
        }
        if (((String)s).length() == 8) {
            int n = Integer.parseInt(((String)s).substring(0, 2), 16);
            int n3 = Integer.parseInt(((String)s).substring(2, 4), 16);
            b = Integer.parseInt(((String)s).substring(4, 6), 16);
            a = Integer.parseInt(((String)s).substring(6, 8), 16);
            return H_2506_c.n_1700_B(n, n3, b, a);
        }
        throw new IllegalArgumentException("Unsupported hex format: " + hex);
    }

    public static int J_1907_R(int speed, int index, int first, int second) {
        int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
        angle = (angle > 180 ? 360 - angle : angle) + 180;
        return H_2506_c.n_1700_B(first, second, (float)(angle - 180) / 180.0f);
    }

    public static int R_4764_Y(int color, int alpha) {
        return H_2506_c.n_1700_B(H_2506_c.n_1700_B(color), H_2506_c.J_1907_R(color), H_2506_c.R_4764_Y(color), alpha);
    }
}

