/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Color;
import java.nio.ByteBuffer;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.P_4249_L;
import lightning.product.MinecraftAccess;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.l_4397_i;
import lightning.product.q_3148_R;
import lombok.Generated;
import org.lwjgl.opengl.GL11;

public class R_3213_X {
    private static R_3213_X n_1700_B = null;
    private boolean J_1907_R = false;
    private boolean R_4764_Y = false;
    private float G_564_y;
    private float P_1922_E;
    private final h_2367_h u_1723_Y;
    private float v_4262_N;
    private float w_1484_f;
    private float t_148_a;
    private float s_956_w;
    private boolean u_2550_I = false;
    private boolean M_588_G = false;
    private boolean P_4830_p = false;
    private final Animation h_1847_R = new Animation(0.0f, 12.0f);
    private final Animation Q_4569_t = new Animation(0.0f, 6.0f);
    private final Animation M_182_A = new Animation(0.0f, 6.0f);
    private final Animation t_1786_h = new Animation(0.0f, 10.0f);

    public R_3213_X(h_2367_h setting) {
        this.u_1723_Y = setting;
        this.J_1907_R();
    }

    public void n_1700_B(boolean colorPickMode) {
        if (colorPickMode && n_1700_B != this) {
            if (n_1700_B != null) {
                R_3213_X.n_1700_B.J_1907_R = false;
                R_3213_X.n_1700_B.h_1847_R.n_1700_B(0.0f);
            }
            n_1700_B = this;
            this.J_1907_R();
            this.h_1847_R.J_1907_R(0.0f);
            l_4397_i.R_4764_Y();
        } else if (!colorPickMode && n_1700_B == this) {
            n_1700_B = null;
        }
        this.J_1907_R = colorPickMode;
        this.h_1847_R.n_1700_B(colorPickMode ? 1.0f : 0.0f);
        if (colorPickMode) {
            this.u_2550_I = false;
            this.M_588_G = false;
            this.P_4830_p = false;
        }
    }

    public void n_1700_B(g_221_o stack, float mouseX, float mouseY) {
        this.n_1700_B(stack, mouseX, mouseY, 1.0f);
    }

    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float parentAlpha) {
        this.h_1847_R.n_1700_B(this.J_1907_R ? 1.0f : 0.0f);
        if (this.Q_4569_t.J_1907_R() == 1.0f && this.Q_4569_t.R_4764_Y()) {
            this.Q_4569_t.n_1700_B(0.0f);
        } else if (!this.Q_4569_t.R_4764_Y()) {
            this.Q_4569_t.n_1700_B(this.Q_4569_t.J_1907_R());
        }
        if (this.M_182_A.J_1907_R() == 1.0f && this.M_182_A.R_4764_Y()) {
            this.M_182_A.n_1700_B(0.0f);
        } else if (!this.M_182_A.R_4764_Y()) {
            this.M_182_A.n_1700_B(this.M_182_A.J_1907_R());
        }
        boolean showAlpha = this.u_1723_Y.P_1922_E;
        float alphaOffset = showAlpha ? 0.0f : -7.0f;
        float PICKER_WIDTH = 93.0f + alphaOffset;
        float PICKER_HEIGHT = 90.0f;
        this.t_1786_h.n_1700_B(this.t_1786_h.J_1907_R());
        float panelAlpha = this.h_1847_R.n_1700_B() * parentAlpha;
        F_489_x.n_1700_B((float)Math.round(this.G_564_y + 7.0f), (float)Math.round(this.P_1922_E), PICKER_WIDTH, PICKER_HEIGHT, 4.0f, q_3148_R.n_1700_B(K_1200_E.n_1700_B), panelAlpha);
        F_489_x.J_1907_R(this.G_564_y + 7.0f, this.P_1922_E, PICKER_WIDTH, PICKER_HEIGHT, 4.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), (int)(q_3148_R.J_1907_R(K_1200_E.h_1847_R) * panelAlpha));
        F_489_x.n_1700_B(this.G_564_y + 11.0f, this.P_1922_E + 4.5f, 70.0f, 70.0f, 3.0f, H_2506_c.n_1700_B(255, 255, 255, 255), Color.BLACK.getRGB(), new Color(Color.HSBtoRGB(this.v_4262_N, 1.0f, 1.0f)).getRGB(), Color.BLACK.getRGB(), panelAlpha);
        F_489_x.J_1907_R(Math.max(this.G_564_y + 13.0f, Math.min(this.G_564_y + 75.0f, this.G_564_y + 11.0f + this.w_1484_f * 70.0f)), Math.max(this.P_1922_E + 6.0f, Math.min(this.P_1922_E + 68.0f, this.P_1922_E + 4.0f + (1.0f - this.t_148_a) * 70.0f)), 4.0f, 4.0f, 1.5f, -1, 255.0f * panelAlpha);
        float hueX = this.G_564_y + 84.0f;
        float hueY = this.P_1922_E + 5.0f;
        float hueHeight = 68.0f;
        float hueSegment = hueHeight / 6.0f;
        int[] hueColors = new int[]{H_2506_c.n_1700_B(255, 0, 0), H_2506_c.n_1700_B(255, 255, 0), H_2506_c.n_1700_B(0, 255, 0), H_2506_c.n_1700_B(0, 255, 255), H_2506_c.n_1700_B(0, 0, 255), H_2506_c.n_1700_B(255, 0, 255), H_2506_c.n_1700_B(255, 0, 0)};
        for (int i = 0; i < 6; ++i) {
            float segY = hueY + (float)i * hueSegment;
            F_489_x.n_1700_B(stack, hueX, segY, 4.0f, hueSegment, H_2506_c.n_1700_B(hueColors[i], panelAlpha), H_2506_c.n_1700_B(hueColors[i + 1], panelAlpha));
        }
        F_489_x.n_1700_B(stack, hueX - 1.0f, hueY + this.v_4262_N * 68.0f, 6.0f, 1.0f, H_2506_c.n_1700_B(-1, panelAlpha));
        float alphaX = this.G_564_y + 91.0f + alphaOffset;
        float alphaY = this.P_1922_E + 5.0f;
        if (showAlpha) {
            F_489_x.n_1700_B(stack, alphaX, alphaY, 4.0f, 68.0f, H_2506_c.n_1700_B(-1, panelAlpha), H_2506_c.n_1700_B(255, 255, 255, 0));
            F_489_x.n_1700_B(stack, alphaX - 1.0f, alphaY + (1.0f - this.s_956_w) * 68.0f, 6.0f, 1.0f, H_2506_c.n_1700_B(-1, panelAlpha));
        }
        float copyAnim = this.Q_4569_t.n_1700_B();
        int copyBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), copyAnim);
        float copyAlpha = (float)H_2506_c.G_564_y(copyBg) / 255.0f * panelAlpha;
        int copyText = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.n_1700_B(K_1200_E.R_4764_Y), copyAnim);
        float copyTextAlpha = (float)H_2506_c.G_564_y(copyText) / 255.0f * panelAlpha;
        F_489_x.n_1700_B(this.G_564_y + 11.0f, this.P_1922_E + 76.0f, 37.0f, 10.0f, 1.5f, H_2506_c.n_1700_B(copyBg, copyAlpha));
        l_3370_o.R_4764_Y[12].n_1700_B(stack, "\u041a\u043e\u043f\u0438\u0440\u043e\u0432\u0430\u0442\u044c", (double)(this.G_564_y + 11.0f + (37.0f - l_3370_o.R_4764_Y[12].n_1700_B("\u041a\u043e\u043f\u0438\u0440\u043e\u0432\u0430\u0442\u044c")) / 2.0f), (double)(this.P_1922_E + 80.0f), H_2506_c.n_1700_B(copyText, copyTextAlpha));
        float pasteAnim = this.M_182_A.n_1700_B();
        int pasteBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), pasteAnim);
        float pasteAlpha = (float)H_2506_c.G_564_y(pasteBg) / 255.0f * panelAlpha;
        int pasteText = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.n_1700_B(K_1200_E.R_4764_Y), pasteAnim);
        float pasteTextAlpha = (float)H_2506_c.G_564_y(pasteText) / 255.0f * panelAlpha;
        F_489_x.n_1700_B(this.G_564_y + 49.0f, this.P_1922_E + 76.0f, 37.0f + alphaOffset, 10.0f, 1.5f, H_2506_c.n_1700_B(pasteBg, pasteAlpha));
        l_3370_o.R_4764_Y[12].n_1700_B(stack, "\u0412\u0441\u0442\u0430\u0432\u0438\u0442\u044c", (double)(this.G_564_y + 49.0f + alphaOffset / 2.0f + (37.0f - l_3370_o.R_4764_Y[12].n_1700_B("\u0412\u0441\u0442\u0430\u0432\u0438\u0442\u044c")) / 2.0f), (double)(this.P_1922_E + 80.0f), H_2506_c.n_1700_B(pasteText, pasteTextAlpha));
        float pipetteAnim = this.t_1786_h.n_1700_B();
        int pipetteBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), pipetteAnim);
        float pipetteAlpha = (float)H_2506_c.G_564_y(pipetteBg) / 255.0f * panelAlpha;
        int pipetteText = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.n_1700_B(K_1200_E.R_4764_Y), pipetteAnim);
        float pipetteTextAlpha = (float)H_2506_c.G_564_y(pipetteText) / 255.0f * panelAlpha;
        F_489_x.n_1700_B(this.G_564_y + 87.0f + alphaOffset, this.P_1922_E + 76.0f, 10.0f, 10.0f, 1.5f, H_2506_c.n_1700_B(pipetteBg, pipetteAlpha));
        l_3370_o.u_1723_Y[12].n_1700_B(stack, "R", (double)(this.G_564_y + 89.0f + alphaOffset), (double)(this.P_1922_E + 81.0f), H_2506_c.n_1700_B(pipetteText, pipetteTextAlpha));
        this.J_1907_R(mouseX, mouseY);
    }

    private void J_1907_R(float mouseX, float mouseY) {
        if (this.u_2550_I) {
            this.R_4764_Y(mouseX, mouseY);
        }
        if (this.M_588_G) {
            this.v_4262_N(mouseY);
        }
        if (this.P_4830_p) {
            this.w_1484_f(mouseY);
        }
    }

    public boolean n_1700_B(float mouseX, float mouseY) {
        float alphaOffset;
        boolean showAlpha = this.u_1723_Y.P_1922_E;
        float f = alphaOffset = showAlpha ? 0.0f : -7.0f;
        if (F_747_P.n_1700_B(mouseX, mouseY, this.G_564_y + 87.0f + alphaOffset, this.P_1922_E + 76.0f, 10.0f, 10.0f)) {
            this.R_4764_Y = !this.R_4764_Y;
            this.t_1786_h.n_1700_B(this.R_4764_Y ? 1.0f : 0.0f);
            return true;
        }
        if (this.R_4764_Y) {
            this.G_564_y(mouseX, mouseY);
            this.R_4764_Y = false;
            this.t_1786_h.n_1700_B(0.0f);
            return true;
        }
        if (F_747_P.n_1700_B(mouseX, mouseY, this.G_564_y + 11.0f, this.P_1922_E + 76.0f, 37.0f, 10.0f)) {
            if (this.Q_4569_t.R_4764_Y()) {
                this.Y_259_p();
                this.Q_4569_t.n_1700_B(1.0f);
            }
            return true;
        }
        if (F_747_P.n_1700_B(mouseX, mouseY, this.G_564_y + 49.0f + alphaOffset, this.P_1922_E + 76.0f, 37.0f, 10.0f)) {
            if (this.M_182_A.R_4764_Y()) {
                this.Y_601_j();
                this.M_182_A.n_1700_B(1.0f);
            }
            return true;
        }
        if (F_747_P.n_1700_B(mouseX, mouseY, this.G_564_y + 11.0f, this.P_1922_E + 4.0f, 70.0f, 70.0f)) {
            this.u_2550_I = true;
            this.R_4764_Y(mouseX, mouseY);
            return true;
        }
        if (F_747_P.n_1700_B(mouseX, mouseY, this.G_564_y + 84.0f - 1.0f, this.P_1922_E + 5.0f, 6.0f, 68.0f)) {
            this.M_588_G = true;
            this.v_4262_N(mouseY);
            return true;
        }
        if (showAlpha && F_747_P.n_1700_B(mouseX, mouseY, this.G_564_y + 91.0f + alphaOffset - 1.0f, this.P_1922_E + 5.0f, 6.0f, 68.0f)) {
            this.P_4830_p = true;
            this.w_1484_f(mouseY);
            return true;
        }
        return false;
    }

    public void n_1700_B() {
        this.u_2550_I = false;
        this.M_588_G = false;
        this.P_4830_p = false;
        this.Q_2552_b();
    }

    private void R_4764_Y(float mouseX, float mouseY) {
        float pickerX = this.G_564_y + 13.0f;
        float pickerY = this.P_1922_E + 7.0f;
        float clampedX = Math.max(pickerX, Math.min(mouseX, pickerX + 70.0f));
        float clampedY = Math.max(pickerY, Math.min(mouseY, pickerY + 70.0f));
        this.w_1484_f = (clampedX - pickerX) / 70.0f;
        this.t_148_a = 1.0f - (clampedY - pickerY) / 70.0f;
        this.w_1484_f = Math.max(0.0f, Math.min(1.0f, this.w_1484_f));
        this.t_148_a = Math.max(0.0f, Math.min(1.0f, this.t_148_a));
        this.Q_2552_b();
    }

    private void v_4262_N(float mouseY) {
        float hueY = this.P_1922_E + 5.0f;
        this.v_4262_N = (Math.max(hueY, Math.min(mouseY, hueY + 68.0f)) - hueY) / 68.0f;
        this.v_4262_N = Math.max(0.0f, Math.min(1.0f, this.v_4262_N));
        this.Q_2552_b();
    }

    private void w_1484_f(float mouseY) {
        if (!this.u_1723_Y.P_1922_E) {
            return;
        }
        float alphaY = this.P_1922_E + 5.0f;
        this.s_956_w = 1.0f - (Math.max(alphaY, Math.min(mouseY, alphaY + 68.0f)) - alphaY) / 68.0f;
        this.s_956_w = Math.max(0.0f, Math.min(1.0f, this.s_956_w));
        this.Q_2552_b();
    }

    public void J_1907_R() {
        Color color = new Color((Integer)this.u_1723_Y.J_1907_R(), true);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        this.v_4262_N = hsb[0];
        this.w_1484_f = hsb[1];
        this.t_148_a = hsb[2];
        if (this.u_1723_Y.P_1922_E) {
            this.s_956_w = (float)color.getAlpha() / 255.0f;
        } else {
            this.s_956_w = 1.0f;
            int rgb = (Integer)this.u_1723_Y.J_1907_R() & 0xFFFFFF;
            this.u_1723_Y.n_1700_B(0xFF000000 | rgb);
        }
    }

    private void Y_601_j() {
        try {
            String clipboardText = MinecraftAccess.c_3005_b.Q_4569_t.n_1700_B().trim();
            if (!clipboardText.matches("^#([A-Fa-f0-9]{6,8})$")) {
                return;
            }
            this.n_1700_B(clipboardText.substring(1).toUpperCase());
            this.M_182_A.J_1907_R(0.0f);
            this.M_182_A.n_1700_B(1.0f);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void n_1700_B(String hex) {
        int b;
        int g;
        int r;
        int a = 255;
        if (hex.length() == 6) {
            r = Integer.parseInt(hex.substring(0, 2), 16);
            g = Integer.parseInt(hex.substring(2, 4), 16);
            b = Integer.parseInt(hex.substring(4, 6), 16);
        } else {
            try {
                r = Integer.parseInt(hex.substring(0, 2), 16);
                g = Integer.parseInt(hex.substring(2, 4), 16);
                b = Integer.parseInt(hex.substring(4, 6), 16);
                a = Integer.parseInt(hex.substring(6, 8), 16);
            }
            catch (Exception e) {
                a = Integer.parseInt(hex.substring(0, 2), 16);
                r = Integer.parseInt(hex.substring(2, 4), 16);
                g = Integer.parseInt(hex.substring(4, 6), 16);
                b = Integer.parseInt(hex.substring(6, 8), 16);
            }
        }
        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        this.v_4262_N = hsb[0];
        this.w_1484_f = hsb[1];
        this.t_148_a = hsb[2];
        if (this.u_1723_Y.P_1922_E) {
            this.s_956_w = (float)a / 255.0f;
        } else {
            this.s_956_w = 1.0f;
            a = 255;
        }
        this.u_1723_Y.n_1700_B(a << 24 | new Color(r, g, b).getRGB() & 0xFFFFFF);
    }

    private void Y_259_p() {
        Color color = new Color((Integer)this.u_1723_Y.J_1907_R(), true);
        String hex = String.format("#%02X%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        MinecraftAccess.c_3005_b.Q_4569_t.n_1700_B(hex);
        this.Q_4569_t.J_1907_R(0.0f);
        this.Q_4569_t.n_1700_B(1.0f);
    }

    private void G_564_y(float mouseX, float mouseY) {
        P_4249_L framebuffer = MinecraftAccess.c_3005_b.G_564_y();
        double guiScale = MinecraftAccess.c_3005_b.RealmsServerPing().w_1457_N();
        int fbX = (int)((double)mouseX * guiScale);
        int fbY = framebuffer.G_564_y - (int)((double)mouseY * guiScale) - 1;
        if (fbX < 0 || fbX >= framebuffer.R_4764_Y || fbY < 0 || fbY >= framebuffer.G_564_y) {
            return;
        }
        ByteBuffer buffer = ByteBuffer.allocateDirect(3);
        GL11.glReadPixels((int)fbX, (int)fbY, (int)1, (int)1, (int)6407, (int)5121, (ByteBuffer)buffer);
        int r = buffer.get(0) & 0xFF;
        int g = buffer.get(1) & 0xFF;
        int b = buffer.get(2) & 0xFF;
        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        this.v_4262_N = hsb[0];
        this.w_1484_f = hsb[1];
        this.t_148_a = hsb[2];
        int currentAlpha = (Integer)this.u_1723_Y.J_1907_R() >> 24 & 0xFF;
        if (!this.u_1723_Y.P_1922_E) {
            currentAlpha = 255;
        }
        this.u_1723_Y.n_1700_B(currentAlpha << 24 | r << 16 | g << 8 | b);
    }

    private void Q_2552_b() {
        int alphaValue = (int)(this.s_956_w * 255.0f);
        if (!this.u_1723_Y.P_1922_E) {
            alphaValue = 255;
        }
        int newColor = Color.HSBtoRGB(this.v_4262_N, this.w_1484_f, this.t_148_a);
        this.u_1723_Y.n_1700_B(alphaValue << 24 | newColor & 0xFFFFFF);
    }

    public void R_4764_Y() {
        this.Q_2552_b();
        this.n_1700_B();
        this.R_4764_Y = false;
        this.n_1700_B(false);
        this.Q_4569_t.J_1907_R(0.0f);
        this.M_182_A.J_1907_R(0.0f);
        this.t_1786_h.J_1907_R(0.0f);
    }

    @Generated
    public boolean G_564_y() {
        return this.J_1907_R;
    }

    @Generated
    public boolean P_1922_E() {
        return this.R_4764_Y;
    }

    @Generated
    public float u_1723_Y() {
        return this.G_564_y;
    }

    @Generated
    public float v_4262_N() {
        return this.P_1922_E;
    }

    @Generated
    public h_2367_h w_1484_f() {
        return this.u_1723_Y;
    }

    @Generated
    public float t_148_a() {
        return this.v_4262_N;
    }

    @Generated
    public float s_956_w() {
        return this.w_1484_f;
    }

    @Generated
    public float u_2550_I() {
        return this.t_148_a;
    }

    @Generated
    public float M_588_G() {
        return this.s_956_w;
    }

    @Generated
    public boolean P_4830_p() {
        return this.u_2550_I;
    }

    @Generated
    public boolean h_1847_R() {
        return this.M_588_G;
    }

    @Generated
    public boolean Q_4569_t() {
        return this.P_4830_p;
    }

    @Generated
    public Animation M_182_A() {
        return this.h_1847_R;
    }

    @Generated
    public Animation t_1786_h() {
        return this.Q_4569_t;
    }

    @Generated
    public Animation multiplayerClientSuggestionProvider() {
        return this.M_182_A;
    }

    @Generated
    public Animation w_1457_N() {
        return this.t_1786_h;
    }

    @Generated
    public void J_1907_R(boolean eyeDropperActive) {
        this.R_4764_Y = eyeDropperActive;
    }

    @Generated
    public void n_1700_B(float x) {
        this.G_564_y = x;
    }

    @Generated
    public void J_1907_R(float y) {
        this.P_1922_E = y;
    }

    @Generated
    public void R_4764_Y(float hue) {
        this.v_4262_N = hue;
    }

    @Generated
    public void G_564_y(float saturation) {
        this.w_1484_f = saturation;
    }

    @Generated
    public void P_1922_E(float brightness) {
        this.t_148_a = brightness;
    }

    @Generated
    public void u_1723_Y(float alpha) {
        this.s_956_w = alpha;
    }

    @Generated
    public void R_4764_Y(boolean isDraggingPicker) {
        this.u_2550_I = isDraggingPicker;
    }

    @Generated
    public void G_564_y(boolean isDraggingHue) {
        this.M_588_G = isDraggingHue;
    }

    @Generated
    public void P_1922_E(boolean isDraggingAlpha) {
        this.P_4830_p = isDraggingAlpha;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof R_3213_X)) {
            return false;
        }
        R_3213_X other = (R_3213_X)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.G_564_y() != other.G_564_y()) {
            return false;
        }
        if (this.P_1922_E() != other.P_1922_E()) {
            return false;
        }
        if (Float.compare(this.u_1723_Y(), other.u_1723_Y()) != 0) {
            return false;
        }
        if (Float.compare(this.v_4262_N(), other.v_4262_N()) != 0) {
            return false;
        }
        if (Float.compare(this.t_148_a(), other.t_148_a()) != 0) {
            return false;
        }
        if (Float.compare(this.s_956_w(), other.s_956_w()) != 0) {
            return false;
        }
        if (Float.compare(this.u_2550_I(), other.u_2550_I()) != 0) {
            return false;
        }
        if (Float.compare(this.M_588_G(), other.M_588_G()) != 0) {
            return false;
        }
        if (this.P_4830_p() != other.P_4830_p()) {
            return false;
        }
        if (this.h_1847_R() != other.h_1847_R()) {
            return false;
        }
        if (this.Q_4569_t() != other.Q_4569_t()) {
            return false;
        }
        h_2367_h this$setting = this.w_1484_f();
        h_2367_h other$setting = other.w_1484_f();
        if (this$setting == null ? other$setting != null : !this$setting.equals(other$setting)) {
            return false;
        }
        Animation this$openAnimation = this.M_182_A();
        Animation other$openAnimation = other.M_182_A();
        if (this$openAnimation == null ? other$openAnimation != null : !this$openAnimation.equals(other$openAnimation)) {
            return false;
        }
        Animation this$copyAnimation = this.t_1786_h();
        Animation other$copyAnimation = other.t_1786_h();
        if (this$copyAnimation == null ? other$copyAnimation != null : !this$copyAnimation.equals(other$copyAnimation)) {
            return false;
        }
        Animation this$pasteAnimation = this.multiplayerClientSuggestionProvider();
        Animation other$pasteAnimation = other.multiplayerClientSuggestionProvider();
        if (this$pasteAnimation == null ? other$pasteAnimation != null : !this$pasteAnimation.equals(other$pasteAnimation)) {
            return false;
        }
        Animation this$pipetteAnimation = this.w_1457_N();
        Animation other$pipetteAnimation = other.w_1457_N();
        return !(this$pipetteAnimation == null ? other$pipetteAnimation != null : !this$pipetteAnimation.equals(other$pipetteAnimation));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof R_3213_X;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.G_564_y() ? 79 : 97);
        result = result * 59 + (this.P_1922_E() ? 79 : 97);
        result = result * 59 + Float.floatToIntBits(this.u_1723_Y());
        result = result * 59 + Float.floatToIntBits(this.v_4262_N());
        result = result * 59 + Float.floatToIntBits(this.t_148_a());
        result = result * 59 + Float.floatToIntBits(this.s_956_w());
        result = result * 59 + Float.floatToIntBits(this.u_2550_I());
        result = result * 59 + Float.floatToIntBits(this.M_588_G());
        result = result * 59 + (this.P_4830_p() ? 79 : 97);
        result = result * 59 + (this.h_1847_R() ? 79 : 97);
        result = result * 59 + (this.Q_4569_t() ? 79 : 97);
        h_2367_h $setting = this.w_1484_f();
        result = result * 59 + ($setting == null ? 43 : $setting.hashCode());
        Animation $openAnimation = this.M_182_A();
        result = result * 59 + ($openAnimation == null ? 43 : $openAnimation.hashCode());
        Animation $copyAnimation = this.t_1786_h();
        result = result * 59 + ($copyAnimation == null ? 43 : $copyAnimation.hashCode());
        Animation $pasteAnimation = this.multiplayerClientSuggestionProvider();
        result = result * 59 + ($pasteAnimation == null ? 43 : $pasteAnimation.hashCode());
        Animation $pipetteAnimation = this.w_1457_N();
        result = result * 59 + ($pipetteAnimation == null ? 43 : $pipetteAnimation.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "ColorPickerElement(colorPickMode=" + this.G_564_y() + ", eyeDropperActive=" + this.P_1922_E() + ", x=" + this.u_1723_Y() + ", y=" + this.v_4262_N() + ", setting=" + String.valueOf(this.w_1484_f()) + ", hue=" + this.t_148_a() + ", saturation=" + this.s_956_w() + ", brightness=" + this.u_2550_I() + ", alpha=" + this.M_588_G() + ", isDraggingPicker=" + this.P_4830_p() + ", isDraggingHue=" + this.h_1847_R() + ", isDraggingAlpha=" + this.Q_4569_t() + ", openAnimation=" + String.valueOf(this.M_182_A()) + ", copyAnimation=" + String.valueOf(this.t_1786_h()) + ", pasteAnimation=" + String.valueOf(this.multiplayerClientSuggestionProvider()) + ", pipetteAnimation=" + String.valueOf(this.w_1457_N()) + ")";
    }
}



