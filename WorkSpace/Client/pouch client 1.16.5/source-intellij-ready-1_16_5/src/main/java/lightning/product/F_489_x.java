/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.util.Set;
import lightning.product.B_3871_I;
import lightning.product.C_2701_A;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.I_4817_s;
import lightning.product.L_3848_p;
import lightning.product.N_4263_v;
import lightning.product.U_679_Y;
import lightning.product.X_933_l;
import lightning.product.Z_1993_T;
import lightning.product.Z_2491_A;
import lightning.product.MinecraftAccess;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.l_3747_P;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_4405_m;
import lightning.product.y_4842_Z;
import org.lwjgl.opengl.GL11;

public class F_489_x
implements MinecraftAccess {
    private static int n_1700_B = -1;
    private static int J_1907_R = -1;
    private static int R_4764_Y = -1;
    private static int G_564_y = -1;
    private static boolean P_1922_E = false;
    private static boolean u_1723_Y = false;
    private static boolean v_4262_N = false;
    private static boolean w_1484_f = false;
    private static boolean t_148_a = false;
    private static g_2336_b s_956_w = null;
    private static int u_2550_I = 0;
    private static final Set<q_1613_l> M_588_G = Set.of(Items.i_770_g, Items.MinMaxBounds, Items.V_1824_v, Items.i_4833_u, Items.H_274_C);

    public static boolean n_1700_B(N_4263_v entity) {
        if (c_3005_b.g_2268_R() == null) {
            return false;
        }
        if (entity == null) {
            return false;
        }
        double distance = c_3005_b.g_2268_R().G_564_y(entity);
        if (distance > 4096.0) {
            return false;
        }
        I_4817_s bb = entity.i_601_W();
        return F_489_x.c_3005_b.u_1723_Y.v_4276_D() == null || F_489_x.c_3005_b.u_1723_Y.v_4276_D().isBoundingBoxInFrustum(bb);
    }

    public static void n_1700_B(float x, float y, float width, float height, float radius, int color) {
        F_489_x.n_1700_B(x, y, width, height, new Z_2491_A(radius, radius, radius, radius), color);
    }

    public static void n_1700_B(float x, float y, float width, float height, float radius, int color0, int color1, int color2, int color3, float alpha) {
        F_489_x.n_1700_B(x, y, width, height, new Z_2491_A(radius, radius, radius, radius), color0, color1, color2, color3, alpha);
    }

    public static void n_1700_B(float x, float y, float width, float height, float radius, int color0, int color1, int color2, int color3, float alpha, float glowRadius) {
        F_489_x.n_1700_B(x, y, width, height, new Z_2491_A(radius, radius, radius, radius), color0, color1, color2, color3, alpha, glowRadius);
    }

    public static void n_1700_B(float x, float y, float width, float height, float radius, int color, float alpha) {
        F_489_x.n_1700_B(x, y, width, height, new Z_2491_A(radius, radius, radius, radius), color, alpha);
    }

    public static void J_1907_R(float x, float y, float width, float height, float radius, int color, float alpha) {
        F_489_x.n_1700_B(x, y, width, height, radius, 0.6f, color, color, color, color, alpha / 255.0f);
    }

    public static void n_1700_B(boolean withColor, boolean textured) {
        if (v_4262_N) {
            F_489_x.n_1700_B();
        }
        v_4262_N = true;
        w_1484_f = withColor;
        t_148_a = textured;
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.w_1484_f(7425);
        c_4037_x.n_1700_B(516, 0.01f);
        if (t_148_a) {
            c_4037_x.x_607_J();
        } else {
            c_4037_x.e_4240_b();
        }
        A_4115_X.n_1700_B(7, withColor ? E_688_b.k_2293_S : E_688_b.Q_2552_b);
    }

    public static void n_1700_B() {
        if (!v_4262_N) {
            return;
        }
        Y_1740_V.J_1907_R();
        if (!t_148_a) {
            c_4037_x.x_607_J();
        }
        c_4037_x.w_1484_f(7424);
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
        v_4262_N = false;
    }

    public static void n_1700_B(g_2336_b texture, float x0, float y0, float z0, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, int color0, int color1, int color2, int color3, float u0, float v0, float u1, float v1, float u2, float v2, float u3, float v3) {
        boolean needNewBatch;
        boolean bl = needNewBatch = !u_1723_Y || !texture.equals(s_956_w) || u_2550_I >= 8192;
        if (needNewBatch) {
            if (u_1723_Y) {
                Y_1740_V.J_1907_R();
            }
            s_956_w = texture;
            u_1723_Y = true;
            u_2550_I = 0;
            c_4037_x.Y_601_j();
            c_4037_x.u_2550_I();
            c_4037_x.w_1484_f(7425);
            c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E);
            c_4037_x.J_1907_R(false);
            c_4037_x.q_2307_F();
            c_4037_x.n_1700_B(516, 0.01f);
            c_3005_b.G_624_v().n_1700_B(texture);
            A_4115_X.n_1700_B(7, E_688_b.k_2293_S);
        }
        int r0 = color0 >> 16 & 0xFF;
        int g0 = color0 >> 8 & 0xFF;
        int b0 = color0 & 0xFF;
        int a0 = color0 >>> 24;
        int r1 = color1 >> 16 & 0xFF;
        int g1 = color1 >> 8 & 0xFF;
        int b1 = color1 & 0xFF;
        int a1 = color1 >>> 24;
        int r2 = color2 >> 16 & 0xFF;
        int g2 = color2 >> 8 & 0xFF;
        int b2 = color2 & 0xFF;
        int a2 = color2 >>> 24;
        int r3 = color3 >> 16 & 0xFF;
        int g3 = color3 >> 8 & 0xFF;
        int b3 = color3 & 0xFF;
        int a3 = color3 >>> 24;
        A_4115_X.pos(x0, y0, z0).tex(u0, v0).color(r0, g0, b0, a0).endVertex();
        A_4115_X.pos(x1, y1, z1).tex(u1, v1).color(r1, g1, b1, a1).endVertex();
        A_4115_X.pos(x2, y2, z2).tex(u2, v2).color(r2, g2, b2, a2).endVertex();
        A_4115_X.pos(x3, y3, z3).tex(u3, v3).color(r3, g3, b3, a3).endVertex();
        ++u_2550_I;
    }

    public static void n_1700_B(g_2336_b texture, boolean boost, float x0, float y0, float z0, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, int color) {
        int boostedColor = boost ? H_2506_c.J_1907_R(color, 30) : color;
        F_489_x.n_1700_B(texture, x0, y0, z0, x1, y1, z1, x2, y2, z2, x3, y3, z3, boostedColor, boostedColor, boostedColor, boostedColor, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f);
    }

    public static void J_1907_R() {
        if (u_1723_Y) {
            Y_1740_V.J_1907_R();
            c_4037_x.Y_259_p();
            c_4037_x.k_2293_S();
            c_4037_x.M_588_G();
            c_4037_x.w_1484_f(7424);
            c_4037_x.J_1907_R(true);
            u_1723_Y = false;
            s_956_w = null;
            u_2550_I = 0;
        }
    }

    public static void n_1700_B(g_2336_b image, float x, float y, float width, float height, int color) {
        c_3005_b.G_624_v().n_1700_B(image);
        int filter = width > 128.0f || height > 128.0f ? 9728 : 9729;
        GL11.glTexParameteri((int)3553, (int)10241, (int)filter);
        GL11.glTexParameteri((int)3553, (int)10240, (int)filter);
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        c_4037_x.G_564_y(r, g, b, a);
        F_489_x.n_1700_B(true, true);
        F_489_x.J_1907_R(x, y, width, height, color);
        F_489_x.n_1700_B();
    }

    public static void n_1700_B(g_2336_b image, float x, float y, float z, float width, float height, int color, boolean boost) {
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E);
        c_4037_x.J_1907_R(false);
        c_4037_x.q_2307_F();
        c_4037_x.n_1700_B(516, 0.01f);
        int boostalpha = boost ? 40 : 0;
        int red = Math.min(255, (color >> 16 & 0xFF) + boostalpha);
        int green = Math.min(255, (color >> 8 & 0xFF) + boostalpha);
        int blue = Math.min(255, (color & 0xFF) + boostalpha);
        int alpha = color >>> 24;
        c_3005_b.G_624_v().n_1700_B(image);
        A_4115_X.n_1700_B(7, E_688_b.k_2293_S);
        A_4115_X.pos(x, y + height, z).tex(0.0f, 1.0f).color(red, green, blue, alpha).endVertex();
        A_4115_X.pos(x + width, y + height, z).tex(1.0f, 1.0f).color(red, green, blue, alpha).endVertex();
        A_4115_X.pos(x + width, y, z).tex(1.0f, 0.0f).color(red, green, blue, alpha).endVertex();
        A_4115_X.pos(x, y, z).tex(0.0f, 0.0f).color(red, green, blue, alpha).endVertex();
        Y_1740_V.J_1907_R();
        c_4037_x.Y_259_p();
        c_4037_x.k_2293_S();
        c_4037_x.J_1907_R(true);
        c_4037_x.d_2461_k();
    }

    public static void n_1700_B(float x, float y, float width, float height, Z_2491_A radius, int color) {
        s_4405_m.n_1700_B.J_1907_R();
        s_4405_m.n_1700_B.J_1907_R("size", width, height);
        s_4405_m.n_1700_B.n_1700_B("radius", radius.n_1700_B(), radius.J_1907_R(), radius.R_4764_Y(), radius.G_564_y());
        s_4405_m.n_1700_B.n_1700_B("color", H_2506_c.P_1922_E(color));
        F_489_x.n_1700_B(false, false);
        F_489_x.n_1700_B((double)x, (double)y, (double)width, (double)height);
        F_489_x.n_1700_B();
        s_4405_m.n_1700_B.R_4764_Y();
    }

    public static void n_1700_B(float x, float y, float width, float height, Z_2491_A radius, int color0, int color1, int color2, int color3, float alpha) {
        s_4405_m.R_4764_Y.J_1907_R();
        s_4405_m.R_4764_Y.J_1907_R("size", width, height);
        s_4405_m.R_4764_Y.J_1907_R("radius", radius.n_1700_B(), radius.J_1907_R(), radius.R_4764_Y(), radius.G_564_y());
        s_4405_m.R_4764_Y.J_1907_R("color0", (float)(color0 >> 16 & 0xFF) / 255.0f, (float)(color0 >> 8 & 0xFF) / 255.0f, (float)(color0 & 0xFF) / 255.0f);
        s_4405_m.R_4764_Y.J_1907_R("color1", (float)(color1 >> 16 & 0xFF) / 255.0f, (float)(color1 >> 8 & 0xFF) / 255.0f, (float)(color1 & 0xFF) / 255.0f);
        s_4405_m.R_4764_Y.J_1907_R("color2", (float)(color2 >> 16 & 0xFF) / 255.0f, (float)(color2 >> 8 & 0xFF) / 255.0f, (float)(color2 & 0xFF) / 255.0f);
        s_4405_m.R_4764_Y.J_1907_R("color3", (float)(color3 >> 16 & 0xFF) / 255.0f, (float)(color3 >> 8 & 0xFF) / 255.0f, (float)(color3 & 0xFF) / 255.0f);
        s_4405_m.R_4764_Y.J_1907_R("alpha", alpha);
        F_489_x.n_1700_B(false, false);
        F_489_x.n_1700_B((double)x, (double)y, (double)width, (double)height);
        F_489_x.n_1700_B();
        s_4405_m.R_4764_Y.R_4764_Y();
    }

    public static void n_1700_B(float x, float y, float width, float height, Z_2491_A radius, int color0, int color1, int color2, int color3, float alpha, float glowRadius) {
        s_4405_m.G_564_y.J_1907_R();
        s_4405_m.G_564_y.J_1907_R("size", width, height);
        s_4405_m.G_564_y.J_1907_R("radius", radius.n_1700_B(), radius.J_1907_R(), radius.R_4764_Y(), radius.G_564_y());
        s_4405_m.G_564_y.J_1907_R("color0", (float)(color0 >> 16 & 0xFF) / 255.0f, (float)(color0 >> 8 & 0xFF) / 255.0f, (float)(color0 & 0xFF) / 255.0f);
        s_4405_m.G_564_y.J_1907_R("color1", (float)(color1 >> 16 & 0xFF) / 255.0f, (float)(color1 >> 8 & 0xFF) / 255.0f, (float)(color1 & 0xFF) / 255.0f);
        s_4405_m.G_564_y.J_1907_R("color2", (float)(color2 >> 16 & 0xFF) / 255.0f, (float)(color2 >> 8 & 0xFF) / 255.0f, (float)(color2 & 0xFF) / 255.0f);
        s_4405_m.G_564_y.J_1907_R("color3", (float)(color3 >> 16 & 0xFF) / 255.0f, (float)(color3 >> 8 & 0xFF) / 255.0f, (float)(color3 & 0xFF) / 255.0f);
        s_4405_m.G_564_y.J_1907_R("alpha", alpha);
        s_4405_m.G_564_y.J_1907_R("glowRadius", glowRadius);
        F_489_x.n_1700_B(false, false);
        F_489_x.n_1700_B((double)x, (double)y, (double)width, (double)height);
        F_489_x.n_1700_B();
        s_4405_m.G_564_y.R_4764_Y();
    }

    public static void n_1700_B(float x, float y, float width, float height, Z_2491_A radius, int color, float alpha) {
        if (!P_1922_E) {
            c_4037_x.v_4262_N(y_4842_Z.n_1700_B.J_1907_R.v_4262_N);
            s_4405_m.w_1484_f.J_1907_R();
            int resW = c_3005_b.RealmsServerPing().P_4830_p();
            int resH = c_3005_b.RealmsServerPing().h_1847_R();
            if (n_1700_B != resW || J_1907_R != resH) {
                s_4405_m.w_1484_f.J_1907_R("resolution", resW, resH);
                n_1700_B = resW;
                J_1907_R = resH;
            }
        }
        s_4405_m.w_1484_f.J_1907_R("start", x, y);
        s_4405_m.w_1484_f.J_1907_R("size", width, height);
        s_4405_m.w_1484_f.n_1700_B("round", radius.n_1700_B(), radius.J_1907_R(), radius.R_4764_Y(), radius.G_564_y());
        s_4405_m.w_1484_f.n_1700_B("alpha", alpha);
        s_4405_m.w_1484_f.n_1700_B("color", H_2506_c.P_1922_E(color));
        F_489_x.n_1700_B(x, y, width, height);
        if (!P_1922_E) {
            s_4405_m.w_1484_f.R_4764_Y();
        }
    }

    private static void n_1700_B(float x, float y, float width, float height) {
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.x_607_J();
        A_4115_X.n_1700_B(7, E_688_b.Q_2552_b);
        A_4115_X.pos(x, y + height, 0.0).tex(0.0f, 1.0f).endVertex();
        A_4115_X.pos(x + width, y + height, 0.0).tex(1.0f, 1.0f).endVertex();
        A_4115_X.pos(x + width, y, 0.0).tex(1.0f, 0.0f).endVertex();
        A_4115_X.pos(x, y, 0.0).tex(0.0f, 0.0f).endVertex();
        Y_1740_V.J_1907_R();
        c_4037_x.Y_259_p();
    }

    public static void n_1700_B(g_2336_b skin, r_4811_B target, float x, float y, float width, float height, float radius, float alpha) {
        float hurt_time = target != null ? (float)target.RealmsLongRunningMcoTaskScreen : 0.0f;
        hurt_time = hurt_time > 0.0f ? Math.min(0.25f, hurt_time / (float)target.i_2993_w) : 0.0f;
        c_3005_b.G_624_v().n_1700_B(skin);
        s_4405_m.s_956_w.J_1907_R();
        s_4405_m.s_956_w.J_1907_R("size", width, height);
        s_4405_m.s_956_w.J_1907_R("radius", radius);
        s_4405_m.s_956_w.J_1907_R("hurt_time", hurt_time);
        s_4405_m.s_956_w.J_1907_R("alpha", alpha);
        s_4405_m.s_956_w.J_1907_R("texXSize", 64.0f);
        s_4405_m.s_956_w.J_1907_R("texYSize", 64.0f);
        F_489_x.n_1700_B(false, true);
        F_489_x.n_1700_B((double)x, (double)y, (double)width, (double)height);
        F_489_x.n_1700_B();
        s_4405_m.s_956_w.R_4764_Y();
    }

    public static void n_1700_B(g_2336_b texture, float x, float y, float width, float height, float radius, float alpha) {
        if (!s_4405_m.u_2550_I.n_1700_B()) {
            F_489_x.n_1700_B(texture, x, y, width, height, H_2506_c.n_1700_B(-1, (int)(alpha * 255.0f)));
            return;
        }
        try {
            c_3005_b.G_624_v().n_1700_B(texture);
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            s_4405_m.u_2550_I.J_1907_R();
            s_4405_m.u_2550_I.J_1907_R("size", width, height);
            s_4405_m.u_2550_I.J_1907_R("radius", radius);
            s_4405_m.u_2550_I.J_1907_R("alpha", alpha);
            F_489_x.n_1700_B(false, true);
            F_489_x.n_1700_B((double)x, (double)y, (double)width, (double)height);
            F_489_x.n_1700_B();
            s_4405_m.u_2550_I.R_4764_Y();
            c_4037_x.Y_259_p();
        }
        catch (Exception e) {
            F_489_x.n_1700_B(texture, x, y, width, height, H_2506_c.n_1700_B(-1, (int)(alpha * 255.0f)));
        }
    }

    public static void n_1700_B(float x, float y, float width, float height, float radius, float borderSize, int color0, int color1, int color2, int color3, float alpha) {
        s_4405_m.J_1907_R.J_1907_R();
        s_4405_m.J_1907_R.J_1907_R("u_size", width, height);
        s_4405_m.J_1907_R.J_1907_R("u_radius", radius);
        s_4405_m.J_1907_R.J_1907_R("u_border_size", borderSize);
        s_4405_m.J_1907_R.J_1907_R("u_alpha", alpha);
        s_4405_m.J_1907_R.J_1907_R("u_color_1", (float)(color0 >> 16 & 0xFF) / 255.0f, (float)(color0 >> 8 & 0xFF) / 255.0f, (float)(color0 & 0xFF) / 255.0f);
        s_4405_m.J_1907_R.J_1907_R("u_color_2", (float)(color1 >> 16 & 0xFF) / 255.0f, (float)(color1 >> 8 & 0xFF) / 255.0f, (float)(color1 & 0xFF) / 255.0f);
        s_4405_m.J_1907_R.J_1907_R("u_color_3", (float)(color2 >> 16 & 0xFF) / 255.0f, (float)(color2 >> 8 & 0xFF) / 255.0f, (float)(color2 & 0xFF) / 255.0f);
        s_4405_m.J_1907_R.J_1907_R("u_color_4", (float)(color3 >> 16 & 0xFF) / 255.0f, (float)(color3 >> 8 & 0xFF) / 255.0f, (float)(color3 & 0xFF) / 255.0f);
        F_489_x.n_1700_B(false, false);
        F_489_x.n_1700_B((double)x, (double)y, (double)width, (double)height);
        F_489_x.n_1700_B();
        s_4405_m.J_1907_R.R_4764_Y();
    }

    public static void n_1700_B(Z_1993_T itemStack, float x, float y, float size) {
        F_489_x.n_1700_B(itemStack, x, y, size, false);
    }

    public static void n_1700_B(Z_1993_T itemStack, float x, float y, float size, boolean showOverlay) {
        if (itemStack.n_1700_B()) {
            return;
        }
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.M_588_G();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        boolean needClip = M_588_G.contains(itemStack.J_1907_R());
        if (needClip) {
            float itemSize = 16.0f * size;
            float margin = 1.0f * size;
            U_679_Y window = c_3005_b.RealmsServerPing();
            double scale = window.w_1457_N();
            int scissorX = (int)((double)(x + margin) * scale);
            int scissorY = (int)((double)((float)window.M_182_A() - y - itemSize + margin) * scale);
            int scissorW = (int)((double)(itemSize - margin * 2.0f) * scale);
            int scissorH = (int)((double)(itemSize - margin * 2.0f) * scale);
            GL11.glEnable((int)3089);
            GL11.glScissor((int)scissorX, (int)scissorY, (int)scissorW, (int)scissorH);
        }
        c_4037_x.v_4276_D();
        c_4037_x.R_4764_Y(x, y, 100.0f);
        c_4037_x.J_1907_R(size, size, size);
        c_3005_b.r_715_M().n_1700_B(itemStack, 0, 0);
        if (showOverlay) {
            c_3005_b.r_715_M().n_1700_B(F_489_x.c_3005_b.t_148_a, itemStack, 0, 0);
        }
        c_4037_x.d_2461_k();
        if (needClip) {
            GL11.glDisable((int)3089);
        }
        c_4037_x.t_1786_h();
        c_4037_x.u_2550_I();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void n_1700_B(int color, float x, float y, float width, float height) {
        c_3005_b.G_624_v().n_1700_B(L_3848_p.n_1700_B);
        L_3848_p atlas = (L_3848_p)c_3005_b.G_624_v().J_1907_R(L_3848_p.n_1700_B);
        B_3871_I fluidSprite = atlas.J_1907_R(new g_2336_b("minecraft", "item/potion_overlay"));
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.G_564_y(r, g, b, a);
        A_4115_X.n_1700_B(7, E_688_b.Q_2552_b);
        A_4115_X.pos(x, y + height, 0.0).tex(fluidSprite.u_1723_Y(), fluidSprite.t_148_a()).endVertex();
        A_4115_X.pos(x + width, y + height, 0.0).tex(fluidSprite.v_4262_N(), fluidSprite.t_148_a()).endVertex();
        A_4115_X.pos(x + width, y, 0.0).tex(fluidSprite.v_4262_N(), fluidSprite.w_1484_f()).endVertex();
        A_4115_X.pos(x, y, 0.0).tex(fluidSprite.u_1723_Y(), fluidSprite.w_1484_f()).endVertex();
        Y_1740_V.J_1907_R();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    public static void n_1700_B(g_221_o matrixStack, float x, float y, float width, float height, int color) {
        matrixStack.n_1700_B();
        matrixStack.n_1700_B((double)x, (double)y, 0.0);
        matrixStack.n_1700_B(width, height, 1.0f);
        C_2701_A.fill(matrixStack, 0, 0, 1, 1, color);
        matrixStack.J_1907_R();
    }

    public static void n_1700_B(g_221_o matrixStack, float x, float y, float width, float height, float radius, int color) {
        D_1098_v matrix = matrixStack.R_4764_Y().n_1700_B();
        s_4405_m.n_1700_B.J_1907_R();
        s_4405_m.n_1700_B.J_1907_R("size", width, height);
        s_4405_m.n_1700_B.n_1700_B("radius", radius, radius, radius, radius);
        s_4405_m.n_1700_B.n_1700_B("color", H_2506_c.P_1922_E(color));
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        A_4115_X.n_1700_B(7, E_688_b.Q_2552_b);
        A_4115_X.n_1700_B(matrix, x, y, 0.0f).tex(0.0f, 0.0f).endVertex();
        A_4115_X.n_1700_B(matrix, x, y + height, 0.0f).tex(0.0f, 1.0f).endVertex();
        A_4115_X.n_1700_B(matrix, x + width, y + height, 0.0f).tex(1.0f, 1.0f).endVertex();
        A_4115_X.n_1700_B(matrix, x + width, y, 0.0f).tex(1.0f, 0.0f).endVertex();
        Y_1740_V.J_1907_R();
        s_4405_m.n_1700_B.R_4764_Y();
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void n_1700_B(g_221_o matrixStack, float x, float y, float width, float height, float radius, int color, float alpha) {
        D_1098_v matrix = matrixStack.R_4764_Y().n_1700_B();
        c_4037_x.v_4262_N(y_4842_Z.n_1700_B.J_1907_R.v_4262_N);
        s_4405_m.w_1484_f.J_1907_R();
        s_4405_m.w_1484_f.J_1907_R("resolution", c_3005_b.RealmsServerPing().P_4830_p(), c_3005_b.RealmsServerPing().h_1847_R());
        s_4405_m.w_1484_f.J_1907_R("start", x, y);
        s_4405_m.w_1484_f.J_1907_R("size", width, height);
        s_4405_m.w_1484_f.n_1700_B("round", radius, radius, radius, radius);
        s_4405_m.w_1484_f.n_1700_B("alpha", alpha);
        s_4405_m.w_1484_f.n_1700_B("color", H_2506_c.P_1922_E(color));
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.x_607_J();
        A_4115_X.n_1700_B(7, E_688_b.Q_2552_b);
        A_4115_X.n_1700_B(matrix, x, y, 0.0f).tex(0.0f, 0.0f).endVertex();
        A_4115_X.n_1700_B(matrix, x, y + height, 0.0f).tex(0.0f, 1.0f).endVertex();
        A_4115_X.n_1700_B(matrix, x + width, y + height, 0.0f).tex(1.0f, 1.0f).endVertex();
        A_4115_X.n_1700_B(matrix, x + width, y, 0.0f).tex(1.0f, 0.0f).endVertex();
        Y_1740_V.J_1907_R();
        s_4405_m.w_1484_f.R_4764_Y();
        c_4037_x.Y_259_p();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void n_1700_B(g_221_o matrixStack, float x, float y, float width, float height, int color0, int color1) {
        matrixStack.n_1700_B();
        matrixStack.n_1700_B((double)x, (double)y, 0.0);
        matrixStack.n_1700_B(width, height, 1.0f);
        C_2701_A.fillGradient(matrixStack, 0, 0, 1, 1, color0, color1);
        matrixStack.J_1907_R();
    }

    public static void n_1700_B(float x, float y, float width, float height, int leftColor, int rightColor) {
        float a1 = (float)(leftColor >> 24 & 0xFF) / 255.0f;
        float r1 = (float)(leftColor >> 16 & 0xFF) / 255.0f;
        float g1 = (float)(leftColor >> 8 & 0xFF) / 255.0f;
        float b1 = (float)(leftColor & 0xFF) / 255.0f;
        float a2 = (float)(rightColor >> 24 & 0xFF) / 255.0f;
        float r2 = (float)(rightColor >> 16 & 0xFF) / 255.0f;
        float g2 = (float)(rightColor >> 8 & 0xFF) / 255.0f;
        float b2 = (float)(rightColor & 0xFF) / 255.0f;
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
        A_4115_X.pos(x, y, 0.0).n_1700_B(r1, g1, b1, a1).endVertex();
        A_4115_X.pos(x, y + height, 0.0).n_1700_B(r1, g1, b1, a1).endVertex();
        A_4115_X.pos(x + width, y + height, 0.0).n_1700_B(r2, g2, b2, a2).endVertex();
        A_4115_X.pos(x + width, y, 0.0).n_1700_B(r2, g2, b2, a2).endVertex();
        Y_1740_V.J_1907_R();
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    public static void n_1700_B(float x, float y, float width, float height, int color) {
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
        A_4115_X.pos(x, y, 0.0).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.pos(x, y + height, 0.0).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.pos(x + width, y + height, 0.0).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.pos(x + width, y, 0.0).n_1700_B(r, g, b, a).endVertex();
        Y_1740_V.J_1907_R();
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    public static void n_1700_B(float x1, float y1, float x2, float y2, float border, int leftGradient, int rightGradient, int borderColor) {
        if (x2 <= x1 || y2 <= y1) {
            return;
        }
        float w = x2 - x1;
        float h = y2 - y1;
        float b = Math.min(border, w / 2.0f);
        if ((b = Math.min(b, h / 2.0f)) < 0.01f) {
            b = 0.01f;
        }
        float iw = w - 2.0f * b;
        float ih = h - 2.0f * b;
        if (iw > 0.0f && ih > 0.0f) {
            F_489_x.n_1700_B(x1 + b, y1 + b, iw, ih, leftGradient, rightGradient);
        }
        F_489_x.n_1700_B(x1, y1, w, b, borderColor);
        F_489_x.n_1700_B(x1, y2 - b, w, b, borderColor);
        F_489_x.n_1700_B(x1, y1 + b, b, h - 2.0f * b, borderColor);
        F_489_x.n_1700_B(x2 - b, y1 + b, b, h - 2.0f * b, borderColor);
    }

    public static void n_1700_B(double x, double y, double width, double height) {
        boolean batching;
        boolean bl = batching = v_4262_N && !w_1484_f;
        if (!batching) {
            A_4115_X.n_1700_B(7, E_688_b.Q_2552_b);
        }
        A_4115_X.pos(x, y, 0.0).tex(0.0f, 0.0f).endVertex();
        A_4115_X.pos(x, y + height, 0.0).tex(0.0f, 1.0f).endVertex();
        A_4115_X.pos(x + width, y + height, 0.0).tex(1.0f, 1.0f).endVertex();
        A_4115_X.pos(x + width, y, 0.0).tex(1.0f, 0.0f).endVertex();
        if (!batching) {
            Y_1740_V.J_1907_R();
        }
    }

    public static void n_1700_B(float x, float y, float scale) {
        X_933_l.g_221_o();
        X_933_l.J_1907_R((double)x, (double)y, 0.0);
        X_933_l.n_1700_B((double)scale, (double)scale, 1.0);
        X_933_l.J_1907_R((double)(-x), (double)(-y), 0.0);
    }

    public static void R_4764_Y() {
        X_933_l.e_2887_G();
    }

    public static void J_1907_R(float x, float y, float width, float height, int color) {
        boolean batching;
        boolean bl = batching = v_4262_N && w_1484_f;
        if (!batching) {
            A_4115_X.n_1700_B(7, E_688_b.k_2293_S);
        }
        A_4115_X.pos(x, y, 0.0).tex(0.0f, 0.0f).n_1700_B(color).endVertex();
        A_4115_X.pos(x, y + height, 0.0).tex(0.0f, 1.0f).n_1700_B(color).endVertex();
        A_4115_X.pos(x + width, y + height, 0.0).tex(1.0f, 1.0f).n_1700_B(color).endVertex();
        A_4115_X.pos(x + width, y, 0.0).tex(1.0f, 0.0f).n_1700_B(color).endVertex();
        if (!batching) {
            Y_1740_V.J_1907_R();
        }
    }

    public static void n_1700_B(float centerX, float centerY, float radius, int color) {
        int segments = 100;
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        GL11.glEnable((int)2848);
        GL11.glEnable((int)2881);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        A_4115_X.n_1700_B(6, E_688_b.Y_601_j);
        A_4115_X.pos(centerX, centerY, 0.0).n_1700_B(r, g, b, a).endVertex();
        for (int i = 0; i <= segments; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)segments;
            double x = (double)centerX + Math.cos(angle) * (double)radius;
            double y = (double)centerY + Math.sin(angle) * (double)radius;
            A_4115_X.pos(x, y, 0.0).n_1700_B(r, g, b, a).endVertex();
        }
        Y_1740_V.J_1907_R();
        GL11.glDisable((int)2848);
        GL11.glDisable((int)2881);
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    public static void n_1700_B(float centerX, float centerY, float radius, int color, float lineWidth) {
        if (radius <= 0.0f) {
            return;
        }
        int segments = (int)(radius * 2.0f);
        if (segments < 32) {
            segments = 32;
        }
        if (segments > 360) {
            segments = 360;
        }
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        c_4037_x.t_1786_h();
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)lineWidth);
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        A_4115_X.n_1700_B(2, E_688_b.Y_601_j);
        for (int i = 0; i <= segments; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)segments;
            double x = (double)centerX + Math.cos(angle) * (double)radius;
            double y = (double)centerY + Math.sin(angle) * (double)radius;
            A_4115_X.pos(x, y, 0.0).n_1700_B(r, g, b, a).endVertex();
        }
        Y_1740_V.J_1907_R();
        GL11.glDisable((int)2848);
        GL11.glLineWidth((float)1.0f);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    public static void n_1700_B(g_221_o ms, float centerX, float centerY, float innerRadius, float outerRadius, double startAngle, double endAngle, int color) {
        float maxRadius = Math.max(outerRadius, innerRadius);
        float size = maxRadius * 2.5f;
        float x = centerX - size * 0.5f;
        float y = centerY - size * 0.5f;
        s_4405_m.C_2741_M.J_1907_R();
        s_4405_m.C_2741_M.J_1907_R("size", size, size);
        s_4405_m.C_2741_M.J_1907_R("center", size * 0.5f, size * 0.5f);
        s_4405_m.C_2741_M.J_1907_R("innerRadius", innerRadius);
        s_4405_m.C_2741_M.J_1907_R("outerRadius", outerRadius);
        s_4405_m.C_2741_M.J_1907_R("startAngle", (float)startAngle);
        s_4405_m.C_2741_M.J_1907_R("endAngle", (float)endAngle);
        float[] colorArray = H_2506_c.P_1922_E(color);
        s_4405_m.C_2741_M.n_1700_B("color", colorArray[0], colorArray[1], colorArray[2], colorArray[3]);
        F_489_x.n_1700_B(false, false);
        F_489_x.n_1700_B((double)x, (double)y, (double)size, (double)size);
        F_489_x.n_1700_B();
        s_4405_m.C_2741_M.R_4764_Y();
    }

    public static void n_1700_B(double factor) {
        if (MinecraftClient.n_1700_B) {
            factor *= 2.0;
        }
        F_489_x.n_1700_B((double)c_3005_b.RealmsServerPing().u_2550_I() / factor, (double)c_3005_b.RealmsServerPing().M_588_G() / factor);
    }

    public static void G_564_y() {
        U_679_Y mainWindow = c_3005_b.RealmsServerPing();
        F_489_x.n_1700_B((double)mainWindow.u_2550_I() / mainWindow.w_1457_N(), (double)mainWindow.M_588_G() / mainWindow.w_1457_N());
    }

    public static void n_1700_B(double width, double height) {
        c_4037_x.n_1700_B(256, MinecraftClient.n_1700_B);
        c_4037_x.u_2550_I(5889);
        c_4037_x.z_1737_N();
        c_4037_x.n_1700_B(0.0, width, height, 0.0, 1000.0, 3000.0);
        c_4037_x.u_2550_I(5888);
        c_4037_x.z_1737_N();
        c_4037_x.R_4764_Y(0.0f, 0.0f, -2000.0f);
    }

    public static void n_1700_B(I_4817_s bb, int color, boolean fill) {
        GL11.glPushMatrix();
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2884);
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r buffer = tessellator.R_4764_Y();
        if (fill && a > 0.01f) {
            X_933_l.G_564_y(r, g, b, 0.15f * a);
            buffer.n_1700_B(7, E_688_b.w_1457_N);
            buffer.pos(bb.minX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.maxZ).endVertex();
            tessellator.J_1907_R();
        }
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)1.5f);
        X_933_l.G_564_y(r, g, b, a);
        buffer.n_1700_B(2, E_688_b.w_1457_N);
        buffer.pos(bb.minX, bb.minY, bb.minZ).endVertex();
        buffer.pos(bb.maxX, bb.minY, bb.minZ).endVertex();
        buffer.pos(bb.maxX, bb.minY, bb.maxZ).endVertex();
        buffer.pos(bb.minX, bb.minY, bb.maxZ).endVertex();
        tessellator.J_1907_R();
        buffer.n_1700_B(2, E_688_b.w_1457_N);
        buffer.pos(bb.minX, bb.maxY, bb.minZ).endVertex();
        buffer.pos(bb.maxX, bb.maxY, bb.minZ).endVertex();
        buffer.pos(bb.maxX, bb.maxY, bb.maxZ).endVertex();
        buffer.pos(bb.minX, bb.maxY, bb.maxZ).endVertex();
        tessellator.J_1907_R();
        buffer.n_1700_B(1, E_688_b.w_1457_N);
        buffer.pos(bb.minX, bb.minY, bb.minZ).endVertex();
        buffer.pos(bb.minX, bb.maxY, bb.minZ).endVertex();
        buffer.pos(bb.maxX, bb.minY, bb.minZ).endVertex();
        buffer.pos(bb.maxX, bb.maxY, bb.minZ).endVertex();
        buffer.pos(bb.maxX, bb.minY, bb.maxZ).endVertex();
        buffer.pos(bb.maxX, bb.maxY, bb.maxZ).endVertex();
        buffer.pos(bb.minX, bb.minY, bb.maxZ).endVertex();
        buffer.pos(bb.minX, bb.maxY, bb.maxZ).endVertex();
        tessellator.J_1907_R();
        GL11.glLineWidth((float)1.0f);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glPopMatrix();
    }

    public static void J_1907_R(I_4817_s bb, int color, boolean fill) {
        GL11.glPushMatrix();
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2884);
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        float x0 = (float)bb.minX;
        float x1 = (float)bb.maxX;
        float y0 = (float)bb.minY;
        float y1 = (float)bb.maxY;
        float z0 = (float)bb.minZ;
        float z1 = (float)bb.maxZ;
        if (fill) {
            l_3747_P tessellator = l_3747_P.n_1700_B();
            D_3318_r buffer = tessellator.R_4764_Y();
            X_933_l.G_564_y(r, g, b, 0.15f * a);
            buffer.n_1700_B(7, E_688_b.w_1457_N);
            buffer.pos(bb.minX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.maxZ).endVertex();
            tessellator.J_1907_R();
        }
        float segSize = 0.15f;
        int segX = Math.max(1, Math.round((x1 - x0) / segSize));
        int segY = Math.max(1, Math.round((y1 - y0) / segSize));
        int segZ = Math.max(1, Math.round((z1 - z0) / segSize));
        GL11.glLineWidth((float)2.0f);
        GL11.glEnable((int)2848);
        F_489_x.n_1700_B(x0, y0, z0, x1, y0, z0, r, g, b, a, segX);
        F_489_x.n_1700_B(x1, y0, z0, x1, y0, z1, r, g, b, a, segZ);
        F_489_x.n_1700_B(x1, y0, z1, x0, y0, z1, r, g, b, a, segX);
        F_489_x.n_1700_B(x0, y0, z1, x0, y0, z0, r, g, b, a, segZ);
        F_489_x.n_1700_B(x0, y1, z0, x1, y1, z0, r, g, b, a, segX);
        F_489_x.n_1700_B(x1, y1, z0, x1, y1, z1, r, g, b, a, segZ);
        F_489_x.n_1700_B(x1, y1, z1, x0, y1, z1, r, g, b, a, segX);
        F_489_x.n_1700_B(x0, y1, z1, x0, y1, z0, r, g, b, a, segZ);
        F_489_x.n_1700_B(x0, y0, z0, x0, y1, z0, r, g, b, a, segY);
        F_489_x.n_1700_B(x1, y0, z0, x1, y1, z0, r, g, b, a, segY);
        F_489_x.n_1700_B(x1, y0, z1, x1, y1, z1, r, g, b, a, segY);
        F_489_x.n_1700_B(x0, y0, z1, x0, y1, z1, r, g, b, a, segY);
        GL11.glDisable((int)2848);
        GL11.glLineWidth((float)1.0f);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glPopMatrix();
    }

    private static void n_1700_B(float x1, float y1, float z1, float x2, float y2, float z2, float r, float g, float b, float a, int segments) {
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r buffer = tessellator.R_4764_Y();
        buffer.n_1700_B(1, E_688_b.Y_601_j);
        if (segments == 1) {
            buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
            buffer.pos(x2, y2, z2).n_1700_B(r, g, b, a).endVertex();
            tessellator.J_1907_R();
            return;
        }
        float segLen = 1.0f / (float)segments;
        float dashLen = segLen * 0.6f;
        for (int i = 0; i < segments; ++i) {
            float t1;
            float t0;
            if (i == 0) {
                t0 = 0.0f;
                t1 = dashLen;
            } else if (i == segments - 1) {
                t0 = 1.0f - dashLen;
                t1 = 1.0f;
            } else {
                float c = ((float)i + 0.5f) * segLen;
                t0 = c - dashLen / 2.0f;
                t1 = c + dashLen / 2.0f;
            }
            buffer.pos(x1 + (x2 - x1) * t0, y1 + (y2 - y1) * t0, z1 + (z2 - z1) * t0).n_1700_B(r, g, b, a).endVertex();
            buffer.pos(x1 + (x2 - x1) * t1, y1 + (y2 - y1) * t1, z1 + (z2 - z1) * t1).n_1700_B(r, g, b, a).endVertex();
        }
        tessellator.J_1907_R();
    }
}



