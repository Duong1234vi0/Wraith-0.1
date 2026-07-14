/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import java.util.List;
import lightning.product.H_2506_c;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.b_3528_u;
import lightning.product.e_2866_D;
import lightning.product.l_3370_o;
import lightning.product.ClientBootstrap;
import lightning.product.v_2826_q;
import lightning.product.y_2447_C;
import lightning.product.y_4642_Y;
import org.joml.Vector2f;

public class z_2372_L
implements MinecraftAccess {
    @Y_1740_V
    public void n_1700_B(b_3528_u eventRender2D) {
        if (z_2372_L.c_3005_b.Y_259_p == null || y_4642_Y.R_4764_Y()) {
            return;
        }
        String serverKey = c_3005_b.t_4043_B() != null ? z_2372_L.c_3005_b.t_4043_B().J_1907_R : "singleplayer";
        List<y_2447_C.n_1700_B> waypoints = ClientBootstrap.Y_601_j().s_956_w().n_1700_B(serverKey);
        if (waypoints.isEmpty()) {
            return;
        }
        double px = z_2372_L.c_3005_b.Y_259_p.O_3598_v();
        double pz = z_2372_L.c_3005_b.Y_259_p.l_2647_k();
        for (y_2447_C.n_1700_B wp : waypoints) {
            e_2866_D worldPos = new e_2866_D(wp.J_1907_R(), wp.R_4764_Y(), wp.G_564_y());
            Vector2f screen = v_2826_q.n_1700_B(worldPos);
            if (Float.isInfinite(screen.x) || Float.isInfinite(screen.y) || screen.x == Float.MAX_VALUE || screen.y == Float.MAX_VALUE) continue;
            String icon = "B";
            String label = wp.n_1700_B() + ": " + (int)Math.round(Math.hypot(px - wp.J_1907_R(), pz - wp.G_564_y())) + "m";
            float iconWidth = l_3370_o.v_4262_N[36].n_1700_B(icon);
            float iconHeight = l_3370_o.v_4262_N[36].h_1847_R();
            float drawX = screen.x - iconWidth / 2.0f;
            float drawY = screen.y - iconHeight / 2.0f;
            l_3370_o.v_4262_N[36].n_1700_B(eventRender2D.J_1907_R(), icon, drawX, drawY, -1, false, true, false, true, H_2506_c.n_1700_B(0, 0, 0, 255));
            float textWidth = l_3370_o.G_564_y[15].n_1700_B(label);
            float textX = screen.x - textWidth / 2.0f;
            float textY = drawY + iconHeight + 2.0f + 3.0f;
            l_3370_o.G_564_y[15].n_1700_B(eventRender2D.J_1907_R(), label, textX, textY, -1, false, true, false, true, H_2506_c.n_1700_B(0, 0, 0, 255));
        }
    }
}


