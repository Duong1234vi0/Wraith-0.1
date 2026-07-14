/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lightning.product.D_3318_r;
import lightning.product.E_3343_g;
import lightning.product.E_688_b;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_686_h;
import lightning.product.M_1336_P;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.b_2152_i;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.h_3572_K;
import lightning.product.l_3747_P;
import lightning.product.o_2840_r;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.y_2603_k;

public class S_1648_L
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e", 30.0f, 5.0f, 100.0f, 1.0f);
    private final I_686_h w_1484_f = new I_686_h("\u0420\u0430\u0437\u043c\u0435\u0440", 1.0f, 0.2f, 3.0f, 0.1f);
    private final p_1977_n t_148_a = new p_1977_n("\u041e\u0442\u0441\u0435\u0447\u0438\u0432\u0430\u0442\u044c \u043e\u0442 \u0431\u043b\u043e\u043a\u043e\u0432", false);
    private final q_366_O s_956_w = new q_366_O("\u0426\u0432\u0435\u0442", "\u0420\u0430\u0434\u0443\u0436\u043d\u044b\u0439", "\u0420\u0430\u0434\u0443\u0436\u043d\u044b\u0439", "\u0421\u0442\u0430\u0442\u0438\u0447\u043d\u044b\u0439");
    private final h_2367_h u_2550_I = new h_2367_h("\u0426\u0432\u0435\u0442 \u0447\u0430\u0441\u0442\u0438\u0446", true, new Color(0, 200, 255).getRGB(), () -> ((String)this.s_956_w.J_1907_R()).equals("\u0421\u0442\u0430\u0442\u0438\u0447\u043d\u044b\u0439"));
    private final ArrayList<n_1700_B> M_588_G = new ArrayList();

    public S_1648_L() {
        super("WorldParticles", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        this.M_588_G.removeIf(n_1700_B::J_1907_R);
        while (this.M_588_G.size() < ((Float)this.v_4262_N.J_1907_R()).intValue()) {
            float x = (float)(S_1648_L.c_3005_b.Y_259_p.O_3598_v() + (double)F_747_P.G_564_y(-20.0f, 20.0f));
            float y = (float)(S_1648_L.c_3005_b.Y_259_p.X_2960_b() + (double)F_747_P.G_564_y(1.0f, 8.0f));
            float z = (float)(S_1648_L.c_3005_b.Y_259_p.l_2647_k() + (double)F_747_P.G_564_y(-20.0f, 20.0f));
            this.M_588_G.add(new n_1700_B(x, y, z));
        }
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        g_221_o matrixStack = new g_221_o();
        float partialTicks = event.J_1907_R();
        this.n_1700_B(matrixStack, partialTicks);
    }

    private void n_1700_B(g_221_o matrixStack, float partialTicks) {
        matrixStack.n_1700_B();
        c_4037_x.Y_601_j();
        c_4037_x.J_1907_R(770, 1);
        c_4037_x.N_4405_n();
        c_4037_x.J_1907_R(false);
        c_4037_x.q_2307_F();
        c_4037_x.u_2550_I();
        c_4037_x.x_607_J();
        c_3005_b.G_624_v().n_1700_B(new g_2336_b("minecraft:Pouch/icons/world_render/glow.png"));
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(7, E_688_b.k_2293_S);
        h_3572_K camera = S_1648_L.c_3005_b.s_956_w.M_588_G();
        float scale = ((Float)this.w_1484_f.J_1907_R()).floatValue();
        for (n_1700_B pixie : this.M_588_G) {
            c_1514_x particlePos;
            if (this.t_148_a.t_148_a().booleanValue() && !S_1648_L.c_3005_b.Y_601_j.getBlockState(particlePos = new c_1514_x(pixie.n_1700_B, pixie.J_1907_R, pixie.R_4764_Y)).v_4262_N() && !S_1648_L.c_3005_b.Y_601_j.getBlockState(particlePos).R_4764_Y().P_1922_E() && !S_1648_L.c_3005_b.Y_601_j.getBlockState(particlePos).R_4764_Y().n_1700_B()) continue;
            pixie.n_1700_B(buffer, matrixStack, camera, partialTicks, scale);
            pixie.R_4764_Y(buffer, matrixStack, camera, partialTicks, scale);
            if (!this.t_148_a.t_148_a().booleanValue()) continue;
            pixie.J_1907_R(buffer, matrixStack, camera, partialTicks, scale);
        }
        buffer.u_1723_Y();
        o_2840_r.n_1700_B(buffer);
        c_4037_x.e_4240_b();
        for (n_1700_B pixie : this.M_588_G) {
            if (pixie.M_182_A.size() < 2) continue;
            buffer.n_1700_B(3, E_688_b.Y_601_j);
            pixie.n_1700_B(buffer, matrixStack, camera, partialTicks);
            buffer.u_1723_Y();
            o_2840_r.n_1700_B(buffer);
        }
        c_4037_x.x_607_J();
        c_4037_x.M_588_G();
        c_4037_x.k_2293_S();
        c_4037_x.J_1907_R(true);
        c_4037_x.N_4405_n();
        c_4037_x.Y_259_p();
        matrixStack.J_1907_R();
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g event) {
        this.M_588_G.clear();
    }

    @Override
    public void J_1907_R() {
        this.M_588_G.clear();
        super.J_1907_R();
    }

    public class n_1700_B {
        float n_1700_B;
        float J_1907_R;
        float R_4764_Y;
        float G_564_y;
        float P_1922_E;
        float u_1723_Y;
        float v_4262_N;
        float w_1484_f;
        float t_148_a;
        float s_956_w;
        int u_2550_I;
        long M_588_G;
        int P_4830_p;
        int h_1847_R;
        float Q_4569_t;
        List<e_2866_D> M_182_A = new ArrayList<e_2866_D>();
        List<J_1907_R> t_1786_h = new ArrayList<J_1907_R>();
        static final int N_4405_n = 30;

        n_1700_B(float x, float y, float z) {
            this.n_1700_B = x;
            this.J_1907_R = y;
            this.R_4764_Y = z;
            this.G_564_y = x;
            this.P_1922_E = y;
            this.u_1723_Y = z;
            this.v_4262_N = this.w_1484_f = F_747_P.G_564_y(0.0f, 360.0f);
            this.t_148_a = F_747_P.G_564_y(0.08f, 0.18f);
            this.s_956_w = F_747_P.G_564_y(-0.05f, 0.08f);
            this.u_2550_I = (int)F_747_P.G_564_y(300.0f, 900.0f);
            this.M_588_G = System.currentTimeMillis();
            this.P_4830_p = this.h_1847_R = (int)F_747_P.G_564_y(200.0f, 400.0f);
            this.Q_4569_t = F_747_P.G_564_y(0.0f, 1.0f);
        }

        int n_1700_B() {
            if (((String)S_1648_L.this.s_956_w.J_1907_R()).equals("\u0420\u0430\u0434\u0443\u0436\u043d\u044b\u0439")) {
                return Color.HSBtoRGB(this.Q_4569_t, 0.8f, 1.0f);
            }
            return (Integer)S_1648_L.this.u_2550_I.J_1907_R();
        }

        boolean J_1907_R() {
            this.P_4830_p = b_2152_i.c_3005_b.Y_259_p.v_4262_N(this.n_1700_B, this.J_1907_R, this.R_4764_Y) > 900.0 ? (this.P_4830_p -= 5) : --this.P_4830_p;
            if (this.P_4830_p <= 0) {
                return true;
            }
            this.G_564_y = this.n_1700_B;
            this.P_1922_E = this.J_1907_R;
            this.u_1723_Y = this.R_4764_Y;
            if (System.currentTimeMillis() - this.M_588_G >= (long)this.u_2550_I) {
                this.u_2550_I = (int)F_747_P.G_564_y(300.0f, 900.0f);
                this.M_588_G = System.currentTimeMillis();
                this.w_1484_f = F_747_P.G_564_y(0.0f, 360.0f);
            }
            this.v_4262_N += (this.w_1484_f - this.v_4262_N) * 0.05f;
            double radYaw = Math.toRadians(this.v_4262_N);
            this.t_148_a /= 1.003f;
            float motionX = (float)(-Math.sin(radYaw) * (double)this.t_148_a);
            float motionZ = (float)(Math.cos(radYaw) * (double)this.t_148_a);
            this.s_956_w /= 1.015f;
            this.n_1700_B += motionX;
            this.J_1907_R += this.s_956_w;
            this.R_4764_Y += motionZ;
            this.M_182_A.add(new e_2866_D(this.n_1700_B, this.J_1907_R, this.R_4764_Y));
            if (this.M_182_A.size() > 30) {
                this.M_182_A.remove(0);
            }
            if (this.P_4830_p % 3 == 0) {
                this.t_1786_h.add(new J_1907_R(S_1648_L.this, this.n_1700_B, this.J_1907_R, this.R_4764_Y, 350));
            }
            this.t_1786_h.forEach(J_1907_R::R_4764_Y);
            this.t_1786_h.removeIf(J_1907_R::J_1907_R);
            return false;
        }

        void n_1700_B(D_3318_r buffer, g_221_o matrix, h_3572_K camera, float pt, float scale) {
            e_2866_D camPos = camera.J_1907_R();
            double x = (double)(this.G_564_y + (this.n_1700_B - this.G_564_y) * pt) - camPos.J_1907_R;
            double y = (double)(this.P_1922_E + (this.J_1907_R - this.P_1922_E) * pt) - camPos.R_4764_Y;
            double z = (double)(this.u_1723_Y + (this.R_4764_Y - this.u_1723_Y) * pt) - camPos.G_564_y;
            matrix.n_1700_B();
            matrix.n_1700_B(x, y, z);
            matrix.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-camera.P_1922_E()));
            matrix.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(camera.G_564_y()));
            float alpha = (float)this.P_4830_p / (float)this.h_1847_R;
            int baseColor = this.n_1700_B();
            int r = H_2506_c.n_1700_B(baseColor);
            int g = H_2506_c.J_1907_R(baseColor);
            int b = H_2506_c.R_4764_Y(baseColor);
            int a = (int)(255.0f * alpha);
            float size = 0.15f * scale;
            buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), -size * 2.0f, -size * 2.0f, 0.0f).tex(1.0f, 1.0f).color(r, g, b, (int)((float)a * 0.3f)).endVertex();
            buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), -size * 2.0f, size * 2.0f, 0.0f).tex(1.0f, 0.0f).color(r, g, b, (int)((float)a * 0.3f)).endVertex();
            buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), size * 2.0f, size * 2.0f, 0.0f).tex(0.0f, 0.0f).color(r, g, b, (int)((float)a * 0.3f)).endVertex();
            buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), size * 2.0f, -size * 2.0f, 0.0f).tex(0.0f, 1.0f).color(r, g, b, (int)((float)a * 0.3f)).endVertex();
            buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), -size, -size, 0.0f).tex(1.0f, 1.0f).color(r, g, b, a).endVertex();
            buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), -size, size, 0.0f).tex(1.0f, 0.0f).color(r, g, b, a).endVertex();
            buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), size, size, 0.0f).tex(0.0f, 0.0f).color(r, g, b, a).endVertex();
            buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), size, -size, 0.0f).tex(0.0f, 1.0f).color(r, g, b, a).endVertex();
            matrix.J_1907_R();
        }

        void J_1907_R(D_3318_r buffer, g_221_o matrix, h_3572_K camera, float pt, float scale) {
            e_2866_D camPos = camera.J_1907_R();
            double x = (double)(this.G_564_y + (this.n_1700_B - this.G_564_y) * pt) - camPos.J_1907_R;
            double y = (double)(this.P_1922_E + (this.J_1907_R - this.P_1922_E) * pt) - camPos.R_4764_Y;
            double z = (double)(this.u_1723_Y + (this.R_4764_Y - this.u_1723_Y) * pt) - camPos.G_564_y;
            float pixieAlpha = (float)this.P_4830_p / (float)this.h_1847_R;
            int baseColor = this.n_1700_B();
            int r = H_2506_c.n_1700_B(baseColor);
            int g = H_2506_c.J_1907_R(baseColor);
            int b = H_2506_c.R_4764_Y(baseColor);
            for (int i = 0; i < 5; ++i) {
                float glowSize = 0.15f * scale * (2.0f + (float)i * 0.8f);
                float glowAlpha = pixieAlpha * (0.4f - (float)i * 0.08f);
                int a = (int)(255.0f * glowAlpha);
                matrix.n_1700_B();
                matrix.n_1700_B(x, y, z);
                matrix.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-camera.P_1922_E()));
                matrix.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(camera.G_564_y()));
                int glowR = Math.min(255, r + 30);
                int glowG = Math.min(255, g + 30);
                int glowB = Math.min(255, b + 30);
                buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), -glowSize, -glowSize, 0.0f).tex(1.0f, 1.0f).color(glowR, glowG, glowB, a).endVertex();
                buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), -glowSize, glowSize, 0.0f).tex(1.0f, 0.0f).color(glowR, glowG, glowB, a).endVertex();
                buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), glowSize, glowSize, 0.0f).tex(0.0f, 0.0f).color(glowR, glowG, glowB, a).endVertex();
                buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), glowSize, -glowSize, 0.0f).tex(0.0f, 1.0f).color(glowR, glowG, glowB, a).endVertex();
                matrix.J_1907_R();
            }
        }

        void R_4764_Y(D_3318_r buffer, g_221_o matrix, h_3572_K camera, float pt, float scale) {
            e_2866_D camPos = camera.J_1907_R();
            int baseColor = this.n_1700_B();
            int r = H_2506_c.n_1700_B(baseColor);
            int g = H_2506_c.J_1907_R(baseColor);
            int b = H_2506_c.R_4764_Y(baseColor);
            float pixieAlpha = (float)this.P_4830_p / (float)this.h_1847_R;
            for (J_1907_R spark : this.t_1786_h) {
                float progress = spark.n_1700_B();
                float alphaPC = progress > 0.5f ? (1.0f - progress) * 2.0f : progress * 2.0f;
                int a = (int)(255.0f * alphaPC * 0.5f * pixieAlpha);
                if (a < 5) continue;
                float size = 0.08f * (1.0f - progress * 0.5f) * scale;
                e_2866_D pos = spark.n_1700_B(pt);
                matrix.n_1700_B();
                matrix.n_1700_B(pos.J_1907_R - camPos.J_1907_R, pos.R_4764_Y - camPos.R_4764_Y, pos.G_564_y - camPos.G_564_y);
                matrix.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-camera.P_1922_E()));
                matrix.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(camera.G_564_y()));
                buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), -size, -size, 0.0f).tex(1.0f, 1.0f).color(r, g, b, a).endVertex();
                buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), -size, size, 0.0f).tex(1.0f, 0.0f).color(r, g, b, a).endVertex();
                buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), size, size, 0.0f).tex(0.0f, 0.0f).color(r, g, b, a).endVertex();
                buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), size, -size, 0.0f).tex(0.0f, 1.0f).color(r, g, b, a).endVertex();
                matrix.J_1907_R();
            }
        }

        void n_1700_B(D_3318_r buffer, g_221_o matrix, h_3572_K camera, float pt) {
            if (this.M_182_A.size() < 2) {
                return;
            }
            e_2866_D camPos = camera.J_1907_R();
            int baseColor = this.n_1700_B();
            int r = H_2506_c.n_1700_B(baseColor);
            int g = H_2506_c.J_1907_R(baseColor);
            int b = H_2506_c.R_4764_Y(baseColor);
            float pixieAlpha = (float)this.P_4830_p / (float)this.h_1847_R;
            for (int i = 0; i < this.M_182_A.size(); ++i) {
                e_2866_D pos = this.M_182_A.get(i);
                float progress = (float)i / (float)this.M_182_A.size();
                int a = (int)(255.0f * progress * 0.5f * pixieAlpha);
                buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), (float)(pos.J_1907_R - camPos.J_1907_R), (float)(pos.R_4764_Y - camPos.R_4764_Y), (float)(pos.G_564_y - camPos.G_564_y)).color(r, g, b, a).endVertex();
            }
            double x = (double)(this.G_564_y + (this.n_1700_B - this.G_564_y) * pt) - camPos.J_1907_R;
            double y = (double)(this.P_1922_E + (this.J_1907_R - this.P_1922_E) * pt) - camPos.R_4764_Y;
            double z = (double)(this.u_1723_Y + (this.R_4764_Y - this.u_1723_Y) * pt) - camPos.G_564_y;
            buffer.n_1700_B(matrix.R_4764_Y().n_1700_B(), (float)x, (float)y, (float)z).color(r, g, b, (int)(153.0f * pixieAlpha)).endVertex();
        }
    }

    public class J_1907_R {
        double n_1700_B;
        double J_1907_R;
        double R_4764_Y;
        double G_564_y;
        double P_1922_E;
        double u_1723_Y;
        double v_4262_N;
        double w_1484_f;
        double t_148_a;
        long s_956_w;
        int u_2550_I;

        J_1907_R(S_1648_L this$0, float x, float y, float z, int maxTime) {
            this.n_1700_B = x;
            this.J_1907_R = y;
            this.R_4764_Y = z;
            this.G_564_y = x;
            this.P_1922_E = y;
            this.u_1723_Y = z;
            this.v_4262_N = Math.random() / 12.0 + 0.02;
            this.w_1484_f = Math.random() * 360.0;
            this.t_148_a = -90.0 + Math.random() * 180.0;
            this.s_956_w = System.currentTimeMillis();
            this.u_2550_I = maxTime;
        }

        float n_1700_B() {
            return Math.min(1.0f, (float)(System.currentTimeMillis() - this.s_956_w) / (float)this.u_2550_I);
        }

        boolean J_1907_R() {
            return this.n_1700_B() >= 1.0f;
        }

        void R_4764_Y() {
            double radYaw = Math.toRadians(this.w_1484_f);
            this.G_564_y = this.n_1700_B;
            this.P_1922_E = this.J_1907_R;
            this.u_1723_Y = this.R_4764_Y;
            this.n_1700_B += Math.sin(radYaw) * this.v_4262_N;
            this.J_1907_R += Math.cos(Math.toRadians(this.t_148_a - 90.0)) * this.v_4262_N;
            this.R_4764_Y += Math.cos(radYaw) * this.v_4262_N;
        }

        e_2866_D n_1700_B(float pt) {
            return new e_2866_D(this.G_564_y + (this.n_1700_B - this.G_564_y) * (double)pt, this.P_1922_E + (this.J_1907_R - this.P_1922_E) * (double)pt, this.u_1723_Y + (this.R_4764_Y - this.u_1723_Y) * (double)pt);
        }
    }
}

