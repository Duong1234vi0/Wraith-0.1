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
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.Q_4113_P;
import lightning.product.U_2871_b;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftAccess;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.Animation;
import lightning.product.k_2603_m;
import lightning.product.l_3370_o;
import lightning.product.l_3747_P;
import lightning.product.ClientBootstrap;
import lightning.product.q_3148_R;
import lightning.product.t_2932_z;
import lombok.Generated;
import org.lwjgl.opengl.GL11;

public class W_2756_H
extends k_2603_m
implements MinecraftAccess {
    private static final float n_1700_B = 110.0f;
    private static final float J_1907_R = 80.0f;
    private int R_4764_Y = -1;
    private final Animation[] G_564_y = new Animation[3];

    public W_2756_H() {
        super(new U_2871_b(""));
        for (int i = 0; i < 3; ++i) {
            this.G_564_y[i] = new Animation(1.0f, 8.0f);
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        String[] hints;
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        this.n_1700_B(mouseX, mouseY, centerX, centerY);
        double sectorStep = 2.0943951023931953;
        float itemRadius = 95.0f;
        double visibleFactor = 0.99;
        double gap = sectorStep * (1.0 - visibleFactor) / 2.0;
        t_2932_z handler = ClientBootstrap.Y_601_j().t_1786_h().R_4764_Y();
        int selectedIndex = handler != null ? handler.R_4764_Y() : -1;
        for (int i = 0; i < 3; ++i) {
            boolean isCurrent;
            double startAngle = -1.5707963267948966 + sectorStep * (double)i;
            double endAngle = startAngle + sectorStep;
            boolean hovered = i == this.R_4764_Y;
            boolean bl = isCurrent = i == selectedIndex;
            int segColor = isCurrent ? H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 170) : (hovered ? H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 120) : H_2506_c.n_1700_B(255, 255, 255, 80));
            this.n_1700_B(matrixStack, centerX, centerY, 80.0f, 110.0f, startAngle + gap, endAngle - gap, segColor);
            double midAngle = (startAngle + endAngle) / 2.0;
            float cx = (float)centerX + (float)(Math.cos(midAngle) * (double)itemRadius);
            float cy = (float)centerY + (float)(Math.sin(midAngle) * (double)itemRadius);
            Z_1993_T configured = this.n_1700_B(i);
            if (!configured.n_1700_B()) {
                float targetSize = hovered ? 1.2f : 1.0f;
                this.G_564_y[i].n_1700_B(targetSize);
                float iconSize = this.G_564_y[i].n_1700_B();
                float iw = 16.0f * iconSize;
                float ih = 16.0f * iconSize;
                F_489_x.n_1700_B(configured, cx - iw / 2.0f, cy - ih / 2.0f, iconSize);
                continue;
            }
            String plus = "+";
            float tw = l_3370_o.J_1907_R[32].n_1700_B(plus);
            float th = l_3370_o.J_1907_R[32].h_1847_R();
            l_3370_o.J_1907_R[32].n_1700_B(matrixStack, plus, (double)(cx - tw / 2.0f), (double)(cy - th / 2.0f), H_2506_c.n_1700_B(255, 255, 255, 220));
        }
        float hintsY = (float)centerY + 110.0f + 18.0f;
        int hintColor = H_2506_c.n_1700_B(255, 255, 255, 140);
        for (String hint : hints = new String[]{"\u041b\u041a\u041c \u2014 \u0432\u044b\u0431\u0440\u0430\u0442\u044c / \u043d\u0430\u0441\u0442\u0440\u043e\u0438\u0442\u044c", "\u041f\u041a\u041c \u2014 \u0443\u0434\u0430\u043b\u0438\u0442\u044c", "\u0421\u043b\u043e\u0442 \u0433\u043e\u043b\u043e\u0432\u044b (\u0448\u043b\u0435\u043c)"}) {
            float hw = l_3370_o.J_1907_R[14].n_1700_B(hint);
            l_3370_o.J_1907_R[14].n_1700_B(matrixStack, hint, (double)((float)centerX - hw / 2.0f), (double)hintsY, hintColor);
            hintsY += l_3370_o.J_1907_R[14].h_1847_R() + 2.0f;
        }
    }

    private void n_1700_B(g_221_o matrixStack, int centerX, int centerY, float innerRadius, float outerRadius, double startAngle, double endAngle, int color) {
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
        int outlineColor = H_2506_c.n_1700_B(color, 220);
        this.J_1907_R(matrixStack, centerX, centerY, innerRadius, outerRadius, startAngle, endAngle, outlineColor);
    }

    private void J_1907_R(g_221_o matrixStack, int centerX, int centerY, float innerRadius, float outerRadius, double startAngle, double endAngle, int color) {
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
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
            float xOuter1 = (float)centerX + cos1 * outerRadius;
            float yOuter1 = (float)centerY + sin1 * outerRadius;
            float xOuter2 = (float)centerX + cos2 * outerRadius;
            float yOuter2 = (float)centerY + sin2 * outerRadius;
            buffer.n_1700_B(matrix, xOuter1, yOuter1, 0.0f).n_1700_B(r, g, b, a).endVertex();
            buffer.n_1700_B(matrix, xOuter2, yOuter2, 0.0f).n_1700_B(r, g, b, a).endVertex();
            float xInner1 = (float)centerX + cos1 * innerRadius;
            float yInner1 = (float)centerY + sin1 * innerRadius;
            float xInner2 = (float)centerX + cos2 * innerRadius;
            float yInner2 = (float)centerY + sin2 * innerRadius;
            buffer.n_1700_B(matrix, xInner1, yInner1, 0.0f).n_1700_B(r, g, b, a).endVertex();
            buffer.n_1700_B(matrix, xInner2, yInner2, 0.0f).n_1700_B(r, g, b, a).endVertex();
        }
        float cosStart = (float)Math.cos(startAngle);
        float sinStart = (float)Math.sin(startAngle);
        float cosEnd = (float)Math.cos(endAngle);
        float sinEnd = (float)Math.sin(endAngle);
        float xOuterStart = (float)centerX + cosStart * outerRadius;
        float yOuterStart = (float)centerY + sinStart * outerRadius;
        float xInnerStart = (float)centerX + cosStart * innerRadius;
        float yInnerStart = (float)centerY + sinStart * innerRadius;
        float xOuterEnd = (float)centerX + cosEnd * outerRadius;
        float yOuterEnd = (float)centerY + sinEnd * outerRadius;
        float xInnerEnd = (float)centerX + cosEnd * innerRadius;
        float yInnerEnd = (float)centerY + sinEnd * innerRadius;
        buffer.n_1700_B(matrix, xOuterStart, yOuterStart, 0.0f).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, xInnerStart, yInnerStart, 0.0f).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, xOuterEnd, yOuterEnd, 0.0f).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, xInnerEnd, yInnerEnd, 0.0f).n_1700_B(r, g, b, a).endVertex();
        l_3747_P.n_1700_B().J_1907_R();
        GL11.glDisable((int)2848);
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
    }

    private void n_1700_B(int mouseX, int mouseY, int centerX, int centerY) {
        double sectorSize;
        int index;
        double dx = mouseX - centerX;
        double dy = mouseY - centerY;
        double dist = Math.hypot(dx, dy);
        if (dist < 32.0 || dist > 176.0) {
            this.R_4764_Y = -1;
            return;
        }
        double angle = Math.atan2(dy, dx) + 1.5707963267948966;
        if (angle < 0.0) {
            angle += Math.PI * 2;
        }
        if ((index = (int)(angle / (sectorSize = 2.0943951023931953))) >= 3) {
            index = 2;
        }
        this.R_4764_Y = index;
    }

    private Z_1993_T n_1700_B(int wheelIndex) {
        t_2932_z handler = ClientBootstrap.Y_601_j().t_1786_h().R_4764_Y();
        if (handler == null) {
            return Z_1993_T.J_1907_R;
        }
        return handler.J_1907_R(wheelIndex);
    }

    private void J_1907_R() {
        if (this.R_4764_Y < 0) {
            return;
        }
        t_2932_z handler = ClientBootstrap.Y_601_j().t_1786_h().R_4764_Y();
        if (handler != null) {
            handler.n_1700_B(this.R_4764_Y);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.R_4764_Y < 0) {
            return super.mouseClicked(mouseX, mouseY, button);
        }
        if (button == 0) {
            Z_1993_T current = this.n_1700_B(this.R_4764_Y);
            t_2932_z handler = ClientBootstrap.Y_601_j().t_1786_h().R_4764_Y();
            if (current.n_1700_B()) {
                this.J_1907_R();
            } else if (handler != null) {
                handler.G_564_y(this.R_4764_Y);
                c_3005_b.n_1700_B((k_2603_m)null);
            }
            return true;
        }
        if (button == 1) {
            t_2932_z handler = ClientBootstrap.Y_601_j().t_1786_h().R_4764_Y();
            if (handler != null) {
                handler.R_4764_Y(this.R_4764_Y);
            }
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void tick() {
        this.R_4764_Y();
        super.tick();
    }

    private void R_4764_Y() {
        W_2756_H.c_3005_b.P_4830_p.O_508_d.n_1700_B(Q_4113_P.n_1700_B(c_3005_b.RealmsServerPing().t_148_a(), W_2756_H.c_3005_b.P_4830_p.O_508_d.getKey().J_1907_R()));
        W_2756_H.c_3005_b.P_4830_p.A_1038_p.n_1700_B(Q_4113_P.n_1700_B(c_3005_b.RealmsServerPing().t_148_a(), W_2756_H.c_3005_b.P_4830_p.A_1038_p.getKey().J_1907_R()));
        W_2756_H.c_3005_b.P_4830_p.r_715_M.n_1700_B(Q_4113_P.n_1700_B(c_3005_b.RealmsServerPing().t_148_a(), W_2756_H.c_3005_b.P_4830_p.r_715_M.getKey().J_1907_R()));
        W_2756_H.c_3005_b.P_4830_p.i_1637_u.n_1700_B(Q_4113_P.n_1700_B(c_3005_b.RealmsServerPing().t_148_a(), W_2756_H.c_3005_b.P_4830_p.i_1637_u.getKey().J_1907_R()));
        W_2756_H.c_3005_b.P_4830_p.Ping.n_1700_B(Q_4113_P.n_1700_B(c_3005_b.RealmsServerPing().t_148_a(), W_2756_H.c_3005_b.P_4830_p.Ping.getKey().J_1907_R()));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Generated
    public int n_1700_B() {
        return this.R_4764_Y;
    }
}



