/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.Q_1939_l;
import lightning.product.Q_4113_P;
import lightning.product.U_2871_b;
import lightning.product.V_772_m;
import lightning.product.Z_2049_e;
import lightning.product.MinecraftAccess;
import lightning.product.Emotions;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.Animation;
import lightning.product.k_2603_m;
import lightning.product.l_3370_o;
import lightning.product.l_3747_P;
import lightning.product.q_3148_R;
import lombok.Generated;
import org.lwjgl.opengl.GL11;

public class D_1621_L
extends k_2603_m
implements MinecraftAccess {
    private static final float J_1907_R = 110.0f;
    private static final float R_4764_Y = 80.0f;
    public static final String[] n_1700_B = new String[]{"\u041f\u0440\u0438\u0432\u0435\u0442\u0441\u0442\u0432\u0438\u0435", "\u0422\u0430\u043d\u0435\u0446", "\u0414\u0440\u043e\u0447\u043a\u0430", "\u041d\u0430\u043c\u0430\u0437", "\u0410\u043b\u044c\u0444\u0430 \u0445\u043e\u0434\u044c\u0431\u0430"};
    private static final int G_564_y = n_1700_B.length;
    private int P_1922_E = -1;
    private final Animation[] u_1723_Y = new Animation[G_564_y];
    private final Animation v_4262_N = new Animation(0.0f, 12.0f);
    private final Animation w_1484_f = new Animation(1.0f, 10.0f);
    private boolean t_148_a = false;
    private String s_956_w = null;
    private boolean u_2550_I = false;

    public D_1621_L() {
        super(new U_2871_b(""));
        for (int i = 0; i < G_564_y; ++i) {
            this.u_1723_Y[i] = new Animation(1.0f, 8.0f);
        }
    }

    @Override
    protected void init() {
        super.init();
        this.v_4262_N.J_1907_R(0.0f);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.v_4262_N.n_1700_B(1.0f);
        if (this.t_148_a) {
            this.w_1484_f.n_1700_B(0.0f);
            if (this.w_1484_f.n_1700_B() < 0.05f) {
                Emotions emotions = Emotions.h_1847_R();
                if (emotions != null) {
                    if (this.u_2550_I) {
                        emotions.P_1922_E(true);
                    } else if (this.s_956_w != null) {
                        emotions.R_4764_Y(this.s_956_w);
                        emotions.P_1922_E(false);
                    }
                }
                c_3005_b.n_1700_B((k_2603_m)null);
                return;
            }
        }
        float scale = this.v_4262_N.n_1700_B();
        float alpha = this.w_1484_f.n_1700_B();
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        this.n_1700_B(mouseX, mouseY, centerX, centerY);
        double sectorStep = Math.PI * 2 / (double)G_564_y;
        float itemRadius = 95.0f;
        double visibleFactor = 0.99;
        double gap = sectorStep * (1.0 - visibleFactor) / 2.0;
        Emotions emotions = Emotions.h_1847_R();
        String currentEmotion = emotions != null && !emotions.Y_601_j() ? emotions.w_1457_N() : "";
        for (int i = 0; i < G_564_y; ++i) {
            boolean isCurrent;
            double startAngle = -1.5707963267948966 + sectorStep * (double)i;
            double endAngle = startAngle + sectorStep;
            boolean hovered = i == this.P_1922_E;
            boolean bl = isCurrent = !currentEmotion.isEmpty() && n_1700_B[i].equals(currentEmotion);
            int segColor = isCurrent ? H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), (int)(170.0f * alpha)) : (hovered ? H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), (int)(120.0f * alpha)) : H_2506_c.n_1700_B(255, 255, 255, (int)(80.0f * alpha)));
            this.n_1700_B(matrixStack, centerX, centerY, 80.0f * scale, 110.0f * scale, startAngle + gap, endAngle - gap, segColor, alpha);
            double midAngle = (startAngle + endAngle) / 2.0;
            float cx = (float)centerX + (float)(Math.cos(midAngle) * (double)itemRadius * (double)scale);
            float cy = (float)centerY + (float)(Math.sin(midAngle) * (double)itemRadius * (double)scale);
            float iconScale = scale;
            if (D_1621_L.c_3005_b.Y_259_p == null || emotions == null || this.t_148_a) continue;
            emotions.G_564_y(n_1700_B[i]);
            Z_2049_e<V_772_m> renderer = c_3005_b.O_508_d().n_1700_B(D_1621_L.c_3005_b.Y_259_p);
            boolean hadLayers = renderer.R_4764_Y();
            renderer.n_1700_B(false);
            int playerScale = (int)(15.0f * iconScale);
            Q_1939_l.n_1700_B((int)cx, (int)(cy + (float)playerScale * 0.8f), playerScale, 0.0f, 0.0f, D_1621_L.c_3005_b.Y_259_p);
            renderer.n_1700_B(hadLayers);
            emotions.G_564_y(null);
        }
        this.n_1700_B(matrixStack, centerX, centerY, 80.0f * scale * 0.55f, currentEmotion, alpha);
    }

    private void n_1700_B(g_221_o matrixStack, int centerX, int centerY, float radius, String currentEmotion, float alpha) {
        boolean centerHovered = this.P_1922_E == -2;
        int color = centerHovered ? H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), (int)(150.0f * alpha)) : H_2506_c.n_1700_B(255, 255, 255, (int)(80.0f * alpha));
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        D_1098_v matrix = matrixStack.R_4764_Y().n_1700_B();
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(6, E_688_b.Y_601_j);
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        buffer.n_1700_B(matrix, (float)centerX, (float)centerY, 0.0f).n_1700_B(r, g, b, a).endVertex();
        int steps = 360;
        for (int i = 0; i <= steps; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)steps;
            float x = (float)centerX + (float)Math.cos(angle) * radius;
            float y = (float)centerY + (float)Math.sin(angle) * radius;
            buffer.n_1700_B(matrix, x, y, 0.0f).n_1700_B(r, g, b, a).endVertex();
        }
        l_3747_P.n_1700_B().J_1907_R();
        float or = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float og = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float ob = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float oa = Math.min(1.0f, (float)H_2506_c.G_564_y(color) / 255.0f * 2.5f) * alpha;
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        buffer.n_1700_B(1, E_688_b.Y_601_j);
        for (int i = 0; i < steps; ++i) {
            double a1 = Math.PI * 2 * (double)i / (double)steps;
            double a2 = Math.PI * 2 * (double)(i + 1) / (double)steps;
            buffer.n_1700_B(matrix, (float)centerX + (float)Math.cos(a1) * radius, (float)centerY + (float)Math.sin(a1) * radius, 0.0f).n_1700_B(or, og, ob, oa).endVertex();
            buffer.n_1700_B(matrix, (float)centerX + (float)Math.cos(a2) * radius, (float)centerY + (float)Math.sin(a2) * radius, 0.0f).n_1700_B(or, og, ob, oa).endVertex();
        }
        l_3747_P.n_1700_B().J_1907_R();
        GL11.glDisable((int)2848);
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        String text = centerHovered ? "\u041e\u0442\u043c\u0435\u043d\u0430" : (this.P_1922_E >= 0 && this.P_1922_E < n_1700_B.length ? n_1700_B[this.P_1922_E] : currentEmotion);
        if (!text.isEmpty()) {
            int textColor = H_2506_c.n_1700_B(255, 255, 255, (int)(220.0f * alpha));
            float tw = l_3370_o.P_1922_E[11].n_1700_B(text);
            l_3370_o.P_1922_E[11].n_1700_B(matrixStack, text, (double)((float)centerX - tw / 2.0f), (double)(centerY - 3), textColor);
        }
    }

    private void n_1700_B(g_221_o matrixStack, int centerX, int centerY, float innerRadius, float outerRadius, double startAngle, double endAngle, int color, float alpha) {
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        D_1098_v matrix = matrixStack.R_4764_Y().n_1700_B();
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(5, E_688_b.Y_601_j);
        int steps = 360;
        double angleStep = (endAngle - startAngle) / (double)steps;
        for (int i = 0; i <= steps; ++i) {
            double angle = startAngle + angleStep * (double)i;
            float cos = (float)Math.cos(angle);
            float sin = (float)Math.sin(angle);
            float xOuter = (float)centerX + cos * outerRadius;
            float yOuter = (float)centerY + sin * outerRadius;
            float xInner = (float)centerX + cos * innerRadius;
            float yInner = (float)centerY + sin * innerRadius;
            buffer.n_1700_B(matrix, xOuter, yOuter, 0.0f).n_1700_B(r, g, b, a).endVertex();
            buffer.n_1700_B(matrix, xInner, yInner, 0.0f).n_1700_B(r, g, b, a).endVertex();
        }
        l_3747_P.n_1700_B().J_1907_R();
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        this.J_1907_R(matrixStack, centerX, centerY, innerRadius, outerRadius, startAngle, endAngle, color, alpha);
    }

    private void J_1907_R(g_221_o matrixStack, int centerX, int centerY, float innerRadius, float outerRadius, double startAngle, double endAngle, int color, float alpha) {
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = Math.min(1.0f, (float)H_2506_c.G_564_y(color) / 255.0f * 2.5f) * alpha;
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        D_1098_v matrix = matrixStack.R_4764_Y().n_1700_B();
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(1, E_688_b.Y_601_j);
        int steps = 360;
        double angleStep = (endAngle - startAngle) / (double)steps;
        for (int i = 0; i < steps; ++i) {
            double a1 = startAngle + angleStep * (double)i;
            double a2 = startAngle + angleStep * (double)(i + 1);
            float cos1 = (float)Math.cos(a1);
            float sin1 = (float)Math.sin(a1);
            float cos2 = (float)Math.cos(a2);
            float sin2 = (float)Math.sin(a2);
            buffer.n_1700_B(matrix, (float)centerX + cos1 * outerRadius, (float)centerY + sin1 * outerRadius, 0.0f).n_1700_B(r, g, b, a).endVertex();
            buffer.n_1700_B(matrix, (float)centerX + cos2 * outerRadius, (float)centerY + sin2 * outerRadius, 0.0f).n_1700_B(r, g, b, a).endVertex();
            buffer.n_1700_B(matrix, (float)centerX + cos1 * innerRadius, (float)centerY + sin1 * innerRadius, 0.0f).n_1700_B(r, g, b, a).endVertex();
            buffer.n_1700_B(matrix, (float)centerX + cos2 * innerRadius, (float)centerY + sin2 * innerRadius, 0.0f).n_1700_B(r, g, b, a).endVertex();
        }
        float cosStart = (float)Math.cos(startAngle);
        float sinStart = (float)Math.sin(startAngle);
        float cosEnd = (float)Math.cos(endAngle);
        float sinEnd = (float)Math.sin(endAngle);
        buffer.n_1700_B(matrix, (float)centerX + cosStart * outerRadius, (float)centerY + sinStart * outerRadius, 0.0f).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, (float)centerX + cosStart * innerRadius, (float)centerY + sinStart * innerRadius, 0.0f).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, (float)centerX + cosEnd * outerRadius, (float)centerY + sinEnd * outerRadius, 0.0f).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, (float)centerX + cosEnd * innerRadius, (float)centerY + sinEnd * innerRadius, 0.0f).n_1700_B(r, g, b, a).endVertex();
        l_3747_P.n_1700_B().J_1907_R();
        GL11.glDisable((int)2848);
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
    }

    private void n_1700_B(int mouseX, int mouseY, int centerX, int centerY) {
        double sectorSize;
        int index;
        float scale;
        double dx = mouseX - centerX;
        double dy = mouseY - centerY;
        double dist = Math.hypot(dx, dy);
        if (dist < (double)(80.0f * (scale = this.v_4262_N.n_1700_B()) * 0.5f)) {
            this.P_1922_E = -2;
            return;
        }
        if (dist > (double)(110.0f * scale * 1.6f)) {
            this.P_1922_E = -1;
            return;
        }
        double angle = Math.atan2(dy, dx) + 1.5707963267948966;
        if (angle < 0.0) {
            angle += Math.PI * 2;
        }
        if ((index = (int)(angle / (sectorSize = Math.PI * 2 / (double)G_564_y))) >= G_564_y) {
            index = G_564_y - 1;
        }
        this.P_1922_E = index;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && !this.t_148_a) {
            if (this.P_1922_E == -2) {
                this.u_2550_I = true;
                this.t_148_a = true;
                return true;
            }
            if (this.P_1922_E >= 0 && this.P_1922_E < n_1700_B.length) {
                this.s_956_w = n_1700_B[this.P_1922_E];
                this.t_148_a = true;
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void tick() {
        this.J_1907_R();
        super.tick();
    }

    private void J_1907_R() {
        D_1621_L.c_3005_b.P_4830_p.O_508_d.n_1700_B(Q_4113_P.n_1700_B(c_3005_b.RealmsServerPing().t_148_a(), D_1621_L.c_3005_b.P_4830_p.O_508_d.getKey().J_1907_R()));
        D_1621_L.c_3005_b.P_4830_p.A_1038_p.n_1700_B(Q_4113_P.n_1700_B(c_3005_b.RealmsServerPing().t_148_a(), D_1621_L.c_3005_b.P_4830_p.A_1038_p.getKey().J_1907_R()));
        D_1621_L.c_3005_b.P_4830_p.r_715_M.n_1700_B(Q_4113_P.n_1700_B(c_3005_b.RealmsServerPing().t_148_a(), D_1621_L.c_3005_b.P_4830_p.r_715_M.getKey().J_1907_R()));
        D_1621_L.c_3005_b.P_4830_p.i_1637_u.n_1700_B(Q_4113_P.n_1700_B(c_3005_b.RealmsServerPing().t_148_a(), D_1621_L.c_3005_b.P_4830_p.i_1637_u.getKey().J_1907_R()));
        D_1621_L.c_3005_b.P_4830_p.Ping.n_1700_B(Q_4113_P.n_1700_B(c_3005_b.RealmsServerPing().t_148_a(), D_1621_L.c_3005_b.P_4830_p.Ping.getKey().J_1907_R()));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Generated
    public int n_1700_B() {
        return this.P_1922_E;
    }
}



