/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.H_1491_c;
import lightning.product.U_2871_b;
import lightning.product.U_3758_B;
import lightning.product.U_679_Y;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_408_T;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.i_4434_b;
import lightning.product.l_3747_P;
import lightning.product.x_282_a;
import lightning.product.y_2603_k;

public class W_1349_b
extends X_3546_T {
    private final H_1491_c v_4262_N = new H_1491_c("\u041a\u0440\u0443\u0442\u0438\u0442\u044c \u0431\u0430\u0440\u0430\u0431\u0430\u043d", this::Q_4569_t);
    private final H_1491_c w_1484_f = new H_1491_c("\u041e\u0442\u043c\u0435\u043d\u0438\u0442\u044c \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u041f\u041a", this::h_1847_R);
    private boolean t_148_a = false;
    private boolean s_956_w = false;
    private final Random u_2550_I = new Random();
    private static final String[] M_588_G = new String[]{"!\u042f \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u044e \u0447\u0438\u0442\u044b \u0438 \u0433\u043e\u0440\u0436\u0443\u0441\u044c \u044d\u0442\u0438\u043c!", "!\u041f\u043e\u043c\u043e\u0433\u0438\u0442\u0435, \u044f \u0441 \u0447\u0438\u0442\u0430\u043c\u0438 \u0432 \u0442\u0440\u0430\u043f\u043a\u0435 \u0443\u043c\u0438\u0440\u0430\u044e.", "!\u041c\u0430\u043c\u0430, \u0437\u0430\u0431\u0435\u0440\u0438 \u043c\u0435\u043d\u044f, \u0442\u0443\u0442 \u0441\u0442\u0440\u0430\u0448\u043d\u043e!", "!\u041f\u0440\u043e\u0434\u0430\u043c \u0433\u0430\u0440\u0430\u0436, \u043a\u0443\u043f\u043b\u044e \u0447\u0438\u0442\u044b.", "!\u0410\u0434\u043c\u0438\u043d, \u0437\u0430\u0431\u0430\u043d\u044c \u043c\u0435\u043d\u044f, \u043f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430.", "!\u042f \u0447\u0438\u0442\u0435\u0440."};

    public W_1349_b() {
        super("RussianRoulette", y_2603_k.P_1922_E);
        this.n_1700_B(this.v_4262_N, this.w_1484_f);
    }

    private void h_1847_R() {
        if (!this.s_956_w) {
            U_3758_B.n_1700_B("K", "\u0412\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u043d\u0435 \u0437\u0430\u043f\u043b\u0430\u043d\u0438\u0440\u043e\u0432\u0430\u043d\u043e", 2000);
            return;
        }
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                Runtime.getRuntime().exec("shutdown -a");
                U_3758_B.n_1700_B("J", "\u0412\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u041f\u041a \u043e\u0442\u043c\u0435\u043d\u0435\u043d\u043e", 3000);
                this.s_956_w = false;
            } else {
                Runtime.getRuntime().exec("sudo shutdown -c");
                U_3758_B.n_1700_B("J", "\u0412\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u041f\u041a \u043e\u0442\u043c\u0435\u043d\u0435\u043d\u043e", 3000);
                this.s_956_w = false;
            }
        }
        catch (IOException e) {
            U_3758_B.n_1700_B("L", "\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043e\u0442\u043c\u0435\u043d\u0438\u0442\u044c \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435", 2000);
        }
    }

    private void Q_4569_t() {
        if (W_1349_b.c_3005_b.Y_259_p == null || W_1349_b.c_3005_b.Y_601_j == null) {
            U_3758_B.n_1700_B("L", "\u041d\u0443\u0436\u043d\u043e \u0431\u044b\u0442\u044c \u0432 \u0438\u0433\u0440\u0435", 2000);
            return;
        }
        int roll = this.u_2550_I.nextInt(100) + 1;
        U_3758_B.n_1700_B("D", "\u041a\u0440\u0443\u0442\u0438\u043c \u0431\u0430\u0440\u0430\u0431\u0430\u043d...", 1500);
        new Thread(() -> {
            try {
                Thread.sleep(1500L);
            }
            catch (InterruptedException e) {
                return;
            }
            c_3005_b.execute(() -> this.J_1907_R(roll));
        }).start();
    }

    private void J_1907_R(int roll) {
        if (roll <= 10) {
            U_3758_B.n_1700_B("L", "\u0412\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u041f\u041a \u0447\u0435\u0440\u0435\u0437 30 \u0441\u0435\u043a", 5000);
            U_3758_B.n_1700_B("K", "\u041d\u0430\u0436\u043c\u0438 \u043a\u043d\u043e\u043f\u043a\u0443 \u043e\u0442\u043c\u0435\u043d\u044b \u0438\u043b\u0438 Delete", 5000);
            this.s_956_w = true;
            new Thread(() -> {
                try {
                    Thread.sleep(2000L);
                    this.t_1786_h();
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }).start();
        } else if (roll <= 20) {
            U_3758_B.n_1700_B("L", "\u0412\u044b\u0431\u0440\u043e\u0448\u0435\u043d \u0432\u0435\u0441\u044c \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c", 3000);
            this.M_182_A();
        } else if (roll <= 45) {
            String message = M_588_G[this.u_2550_I.nextInt(M_588_G.length)];
            U_3758_B.n_1700_B("K", "\u0421\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435 \u043e\u0442\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u043e \u0432 \u0447\u0430\u0442", 2000);
            W_1349_b.c_3005_b.Y_259_p.n_1700_B(message);
        } else {
            U_3758_B.n_1700_B("J", "\u0422\u044b \u0432\u044b\u0436\u0438\u043b", 3000);
            if (W_1349_b.c_3005_b.Y_259_p != null) {
                W_1349_b.c_3005_b.Y_259_p.n_1700_B((x_282_a)new U_2871_b("\u00a7a\u00a7l\u2605 \u0422\u042b \u0412\u042b\u0416\u0418\u041b \u0412 \u0420\u0423\u0421\u0421\u041a\u041e\u0419 \u0420\u0423\u041b\u0415\u0422\u041a\u0415! \u2605"), W_1349_b.c_3005_b.Y_259_p.w_2705_t());
            }
        }
    }

    private void M_182_A() {
        if (W_1349_b.c_3005_b.Y_259_p == null || W_1349_b.c_3005_b.w_1457_N == null) {
            return;
        }
        for (int slot = 5; slot <= 45; ++slot) {
            W_1349_b.c_3005_b.w_1457_N.windowClick(0, slot, 1, a_408_T.P_1922_E, W_1349_b.c_3005_b.Y_259_p);
        }
    }

    private void t_1786_h() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                Runtime.getRuntime().exec("shutdown -s -t 30 -c \"Russian Roulette - nazhmi Delete v igre ili shutdown -a v CMD chtoby otmenit\"");
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("sudo shutdown -h +1");
            } else {
                Runtime.getRuntime().exec("shutdown -h 1");
            }
        }
        catch (IOException e) {
            this.s_956_w = false;
            U_3758_B.n_1700_B("L", "\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u0432\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u041f\u041a", 2000);
        }
    }

    private void N_4405_n() {
        if (W_1349_b.c_3005_b.Y_259_p == null) {
            return;
        }
        new Thread(() -> {
            for (int i = 0; i < 50 && W_1349_b.c_3005_b.Y_259_p != null; ++i) {
                float targetYaw = W_1349_b.c_3005_b.Y_259_p.p_178_J + 36.0f;
                float targetPitch = (float)(Math.sin((double)i * 0.3) * 30.0);
                c_3005_b.execute(() -> {
                    if (W_1349_b.c_3005_b.Y_259_p != null) {
                        W_1349_b.c_3005_b.Y_259_p.p_178_J = targetYaw;
                        W_1349_b.c_3005_b.Y_259_p.f_4016_n = Math.max(-90.0f, Math.min(90.0f, targetPitch));
                    }
                });
                try {
                    Thread.sleep(40L);
                    continue;
                }
                catch (InterruptedException ignored) {
                    break;
                }
            }
        }).start();
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b event) {
        if (this.t_148_a && event.n_1700_B() == 256) {
            this.t_148_a = false;
            U_3758_B.n_1700_B("J", "\u0420\u0430\u0434\u0443\u0433\u0430 \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u0430", 1500);
        }
        if (this.s_956_w && event.n_1700_B() == 256) {
            this.h_1847_R();
        }
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u.J_1907_R event) {
        if (!this.t_148_a || W_1349_b.c_3005_b.Y_259_p == null) {
            return;
        }
        U_679_Y window = c_3005_b.a_2085_x();
        int width = window.Q_4569_t();
        int height = window.M_182_A();
        float time = (float)(System.currentTimeMillis() % 5000L) / 1000.0f;
        c_4037_x.e_4240_b();
        c_4037_x.Y_601_j();
        c_4037_x.u_2550_I();
        c_4037_x.s_2632_s();
        c_4037_x.w_1484_f(7425);
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r buffer = tessellator.R_4764_Y();
        buffer.n_1700_B(7, E_688_b.Y_601_j);
        int segments = 12;
        float segmentHeight = (float)height / (float)segments;
        for (int i = 0; i < segments; ++i) {
            float hue1 = (time * 2.0f + (float)i / (float)segments) % 1.0f;
            float hue2 = (time * 2.0f + (float)(i + 1) / (float)segments) % 1.0f;
            int color1 = Color.HSBtoRGB(hue1, 1.0f, 1.0f);
            int color2 = Color.HSBtoRGB(hue2, 1.0f, 1.0f);
            float r1 = (float)(color1 >> 16 & 0xFF) / 255.0f;
            float g1 = (float)(color1 >> 8 & 0xFF) / 255.0f;
            float b1 = (float)(color1 & 0xFF) / 255.0f;
            float r2 = (float)(color2 >> 16 & 0xFF) / 255.0f;
            float g2 = (float)(color2 >> 8 & 0xFF) / 255.0f;
            float b2 = (float)(color2 & 0xFF) / 255.0f;
            float y1 = (float)i * segmentHeight;
            float y2 = (float)(i + 1) * segmentHeight;
            float wobble1 = (float)Math.sin(time * 5.0f + (float)i * 0.5f) * 30.0f;
            float wobble2 = (float)Math.sin(time * 5.0f + (float)(i + 1) * 0.5f) * 30.0f;
            buffer.pos(wobble1, y1, -90.0).n_1700_B(r1, g1, b1, 0.85f).endVertex();
            buffer.pos(wobble1, y2, -90.0).n_1700_B(r2, g2, b2, 0.85f).endVertex();
            buffer.pos((float)width + wobble2, y2, -90.0).n_1700_B(r2, g2, b2, 0.85f).endVertex();
            buffer.pos((float)width + wobble2, y1, -90.0).n_1700_B(r1, g1, b1, 0.85f).endVertex();
        }
        tessellator.J_1907_R();
        c_4037_x.x_607_J();
        String text = "\u041d\u0430\u0436\u043c\u0438 ESC \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c";
        int textWidth = W_1349_b.c_3005_b.t_148_a.J_1907_R(text);
        float pulse = (float)(Math.sin(time * 4.0f) * 0.5 + 0.5);
        int textColor = Color.HSBtoRGB(time % 1.0f, 1.0f, 1.0f);
        W_1349_b.c_3005_b.t_148_a.n_1700_B(event.J_1907_R(), "\u041d\u0430\u0436\u043c\u0438 ESC \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c", (float)(width - textWidth) / 2.0f, (float)height / 2.0f + 50.0f, textColor);
        c_4037_x.M_588_G();
        c_4037_x.w_1484_f(7424);
        c_4037_x.Y_259_p();
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        this.t_148_a = false;
    }
}

