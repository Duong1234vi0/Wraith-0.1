/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.K_1200_E;
import lightning.product.O_1309_Q;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.g_2336_b;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;
import lightning.product.y_4642_Y;

public class V_1665_T
implements MinecraftAccess {
    @Y_1740_V
    public void n_1700_B(b_3528_u eventRender2D) {
        if (!O_1309_Q.J_1907_R || V_1665_T.c_3005_b.Y_259_p == null || y_4642_Y.R_4764_Y()) {
            return;
        }
        float arrowSize = 16.0f;
        double dx = O_1309_Q.R_4764_Y - V_1665_T.c_3005_b.Y_259_p.O_3598_v();
        double dz = O_1309_Q.G_564_y - V_1665_T.c_3005_b.Y_259_p.l_2647_k();
        double yawRad = Math.toRadians(V_1665_T.c_3005_b.Y_259_p.p_178_J);
        double sin = Math.sin(yawRad);
        double cos = Math.cos(yawRad);
        double forwardComp = -dx * sin + dz * cos;
        double rightComp = -dx * cos - dz * sin;
        float yaw = (float)Math.toDegrees(Math.atan2(rightComp, forwardComp));
        int dst = (int)Math.sqrt(dx * dx + dz * dz);
        int screenWidth = c_3005_b.RealmsServerPing().Q_4569_t();
        int screenHeight = c_3005_b.RealmsServerPing().M_182_A();
        float drawX = ((float)screenWidth - arrowSize) / 2.0f;
        float drawY = (float)screenHeight / 2.0f - 220.0f;
        int interfaceColor = q_3148_R.n_1700_B(K_1200_E.w_1457_N);
        c_4037_x.v_4276_D();
        c_4037_x.R_4764_Y(drawX + arrowSize / 2.0f, drawY + arrowSize / 2.0f, 0.0f);
        c_4037_x.R_4764_Y(yaw, 0.0f, 0.0f, 1.0f);
        c_4037_x.R_4764_Y(-arrowSize / 2.0f, -arrowSize / 2.0f, 0.0f);
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/world_render/arrow.png"), 0.0f, 0.0f, arrowSize, arrowSize, interfaceColor);
        c_4037_x.d_2461_k();
        String text = dst + "m";
        float textWidth = l_3370_o.P_1922_E[14].n_1700_B(text);
        float centerX = drawX + arrowSize / 2.0f;
        float textY = drawY + 17.0f;
        float textX = centerX - textWidth / 2.0f;
        l_3370_o.P_1922_E[14].n_1700_B(eventRender2D.J_1907_R(), text, (double)textX, (double)textY, -1);
    }
}



