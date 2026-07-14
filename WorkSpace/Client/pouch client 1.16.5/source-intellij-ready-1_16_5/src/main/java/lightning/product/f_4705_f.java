/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL30
 */
package lightning.product;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.HashMap;
import java.util.Map;
import lightning.product.D_1098_v;
import lightning.product.E_688_b;
import lightning.product.X_933_l;
import lightning.product.MinecraftAccess;
import lightning.product.l_3370_o;
import lombok.Generated;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

public abstract class f_4705_f
implements MinecraftAccess {
    protected final Map<Character, n_1700_B> n_1700_B = new HashMap<Character, n_1700_B>();
    protected int J_1907_R;
    protected int R_4764_Y;
    protected int G_564_y;
    protected float P_1922_E;
    protected String u_1723_Y;
    protected boolean v_4262_N;
    private boolean w_1484_f = false;
    private D_1098_v t_148_a = null;

    public abstract float n_1700_B();

    protected final void n_1700_B(BufferedImage img) {
        int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
        ByteBuffer buffer = BufferUtils.createByteBuffer((int)(pixels.length * 4));
        try {
            for (int pixel : pixels) {
                buffer.put((byte)(pixel >> 16 & 0xFF));
                buffer.put((byte)(pixel >> 8 & 0xFF));
                buffer.put((byte)(pixel & 0xFF));
                buffer.put((byte)(pixel >> 24 & 0xFF));
            }
            buffer.flip();
        }
        catch (BufferOverflowException | ReadOnlyBufferException ex) {
            this.J_1907_R = -1;
            return;
        }
        int textureID = X_933_l.G_624_v();
        X_933_l.w_1457_N(textureID);
        X_933_l.J_1907_R(3553, 10241, 9728);
        X_933_l.J_1907_R(3553, 10240, 9728);
        GL30.glTexImage2D((int)3553, (int)0, (int)32856, (int)img.getWidth(), (int)img.getHeight(), (int)0, (int)6408, (int)5121, (ByteBuffer)buffer);
        X_933_l.w_1457_N(0);
        this.J_1907_R = textureID;
    }

    public final void J_1907_R() {
        X_933_l.w_1457_N(this.J_1907_R);
    }

    public final void R_4764_Y() {
        X_933_l.w_1457_N(0);
    }

    public static Font n_1700_B(String fileName, int style, int size) {
        String path = "/assets/minecraft/Pouch/fonts/".concat(fileName);
        Font font = null;
        try {
            InputStream stream = l_3370_o.class.getResourceAsStream(path);
            if (stream == null) {
                stream = l_3370_o.class.getClassLoader().getResourceAsStream(path);
            }
            if (stream == null) {
                stream = ClassLoader.getSystemResourceAsStream(path);
            }
            if (stream == null) {
                String altPath = "/Pouch/fonts/" + fileName;
                stream = l_3370_o.class.getResourceAsStream(altPath);
            }
            if (stream == null) {
                System.err.println("Failed to load font: " + path);
                return null;
            }
            font = Font.createFont(0, stream).deriveFont(style, size);
            stream.close();
        }
        catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return font;
    }

    public final Graphics2D n_1700_B(BufferedImage img, Font font) {
        Graphics2D graphics = img.createGraphics();
        graphics.setFont(font);
        graphics.setColor(new Color(255, 255, 255, 0));
        graphics.fillRect(0, 0, this.R_4764_Y, this.G_564_y);
        graphics.setColor(Color.WHITE);
        if (this.v_4262_N) {
            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        return graphics;
    }

    public float n_1700_B(D_1098_v matrix, char c, float x, float y, float red, float green, float blue, float alpha) {
        D_1098_v usedMatrix;
        n_1700_B glyph = this.n_1700_B.get(Character.valueOf(c));
        if (glyph == null) {
            return 0.0f;
        }
        float pageX = (float)glyph.n_1700_B / (float)this.R_4764_Y;
        float pageY = (float)glyph.J_1907_R / (float)this.G_564_y;
        float pageWidth = (float)glyph.R_4764_Y / (float)this.R_4764_Y;
        float pageHeight = (float)glyph.G_564_y / (float)this.G_564_y;
        float width = glyph.R_4764_Y;
        float height = glyph.G_564_y;
        D_1098_v d_1098_v = usedMatrix = this.w_1484_f && this.t_148_a != null ? this.t_148_a : matrix;
        if (!this.w_1484_f) {
            A_4115_X.n_1700_B(7, E_688_b.C_2741_M);
        }
        A_4115_X.n_1700_B(usedMatrix, x, y + height, 0.0f).n_1700_B(red, green, blue, alpha).tex(pageX, pageY + pageHeight).endVertex();
        A_4115_X.n_1700_B(usedMatrix, x + width, y + height, 0.0f).n_1700_B(red, green, blue, alpha).tex(pageX + pageWidth, pageY + pageHeight).endVertex();
        A_4115_X.n_1700_B(usedMatrix, x + width, y, 0.0f).n_1700_B(red, green, blue, alpha).tex(pageX + pageWidth, pageY).endVertex();
        A_4115_X.n_1700_B(usedMatrix, x, y, 0.0f).n_1700_B(red, green, blue, alpha).tex(pageX, pageY).endVertex();
        if (!this.w_1484_f) {
            Y_1740_V.J_1907_R();
        }
        return width + this.n_1700_B();
    }

    public void n_1700_B(D_1098_v matrix) {
        if (this.w_1484_f) {
            this.G_564_y();
        }
        this.w_1484_f = true;
        this.t_148_a = matrix;
        X_933_l.Q_4569_t();
        X_933_l.J_1907_R(770, 771);
        this.J_1907_R();
        A_4115_X.n_1700_B(7, E_688_b.C_2741_M);
    }

    public void G_564_y() {
        if (!this.w_1484_f) {
            return;
        }
        Y_1740_V.J_1907_R();
        this.R_4764_Y();
        X_933_l.h_1847_R();
        this.w_1484_f = false;
        this.t_148_a = null;
    }

    public float n_1700_B(char ch) {
        n_1700_B glyph = this.n_1700_B.get(Character.valueOf(ch));
        return glyph != null ? (float)glyph.R_4764_Y : 0.0f;
    }

    @Generated
    public Map<Character, n_1700_B> P_1922_E() {
        return this.n_1700_B;
    }

    @Generated
    public int u_1723_Y() {
        return this.J_1907_R;
    }

    @Generated
    public int v_4262_N() {
        return this.R_4764_Y;
    }

    @Generated
    public int w_1484_f() {
        return this.G_564_y;
    }

    @Generated
    public float t_148_a() {
        return this.P_1922_E;
    }

    @Generated
    public String s_956_w() {
        return this.u_1723_Y;
    }

    @Generated
    public boolean u_2550_I() {
        return this.v_4262_N;
    }

    @Generated
    public boolean M_588_G() {
        return this.w_1484_f;
    }

    @Generated
    public D_1098_v P_4830_p() {
        return this.t_148_a;
    }

    public static class n_1700_B {
        public int n_1700_B;
        public int J_1907_R;
        public int R_4764_Y;
        public int G_564_y;
    }
}


