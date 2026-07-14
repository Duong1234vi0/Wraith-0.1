/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JComponent;
import javax.swing.Timer;
import lightning.product.j_3341_s;
import net.minecraft.server.G_564_y;

public class o_1543_q
extends JComponent {
    private static final DecimalFormat n_1700_B = j_3341_s.n_1700_B(new DecimalFormat("########0.000"), p_212730_0_ -> p_212730_0_.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));
    private final int[] J_1907_R = new int[256];
    private int R_4764_Y;
    private final String[] G_564_y = new String[11];
    private final G_564_y P_1922_E;
    private final Timer u_1723_Y;

    public o_1543_q(G_564_y serverIn) {
        this.P_1922_E = serverIn;
        this.setPreferredSize(new Dimension(456, 246));
        this.setMinimumSize(new Dimension(456, 246));
        this.setMaximumSize(new Dimension(456, 246));
        this.u_1723_Y = new Timer(500, p_210466_1_ -> this.J_1907_R());
        this.u_1723_Y.start();
        this.setBackground(Color.BLACK);
    }

    private void J_1907_R() {
        long i = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        this.G_564_y[0] = "Memory use: " + i / 1024L / 1024L + " mb (" + Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory() + "% free)";
        this.G_564_y[1] = "Avg tick: " + n_1700_B.format(this.n_1700_B(this.P_1922_E.v_4262_N) * 1.0E-6) + " ms";
        this.J_1907_R[this.R_4764_Y++ & 0xFF] = (int)(i * 100L / Runtime.getRuntime().maxMemory());
        this.repaint();
    }

    private double n_1700_B(long[] values) {
        long i = 0L;
        for (long j : values) {
            i += j;
        }
        return (double)i / (double)values.length;
    }

    public void n_1700_B(Graphics p_paint_1_) {
        p_paint_1_.setColor(new Color(0xFFFFFF));
        p_paint_1_.fillRect(0, 0, 456, 246);
        for (int i = 0; i < 256; ++i) {
            int j = this.J_1907_R[i + this.R_4764_Y & 0xFF];
            p_paint_1_.setColor(new Color(j + 28 << 16));
            p_paint_1_.fillRect(i, 100 - j, 1, j);
        }
        p_paint_1_.setColor(Color.BLACK);
        for (int k = 0; k < this.G_564_y.length; ++k) {
            String s = this.G_564_y[k];
            if (s == null) continue;
            p_paint_1_.drawString(s, 32, 116 + k * 16);
        }
    }

    public void n_1700_B() {
        this.u_1723_Y.stop();
    }
}

