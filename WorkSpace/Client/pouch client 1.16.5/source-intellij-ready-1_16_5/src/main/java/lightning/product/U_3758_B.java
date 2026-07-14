/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.LinkedList;
import java.util.List;
import lightning.product.A_1306_N;
import lightning.product.Interface;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.b_3528_u;
import lightning.product.g_2336_b;
import lightning.product.l_3370_o;
import lightning.product.ClientBootstrap;
import lightning.product.o_4117_e;
import lightning.product.x_282_a;
import lightning.product.y_3417_N;

public class U_3758_B
implements MinecraftAccess {
    private static final List<o_4117_e> n_1700_B = new LinkedList<o_4117_e>();

    private static void n_1700_B(o_4117_e notification) {
        notification.u_1723_Y().J_1907_R((float)H_2857_Y.M_182_A() / 2.0f + 21.0f);
        notification.u_1723_Y().n_1700_B(13.5f);
        n_1700_B.add(0, notification);
        if (n_1700_B.size() > 10) {
            for (int i = 10; i < n_1700_B.size(); ++i) {
                n_1700_B.get(i).J_1907_R();
            }
        }
    }

    public static boolean n_1700_B() {
        return !n_1700_B.isEmpty();
    }

    public static void n_1700_B(g_2336_b image, x_282_a message) {
        U_3758_B.n_1700_B(new A_1306_N(image, message));
    }

    public static void n_1700_B(g_2336_b image, x_282_a message, int color) {
        U_3758_B.n_1700_B(new A_1306_N(image, message, color));
    }

    public static void n_1700_B(String title, String message, int color) {
        U_3758_B.n_1700_B(new y_3417_N(title, message, color));
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u.R_4764_Y event) {
        float startY = (float)H_2857_Y.M_182_A() / 2.0f + 21.0f;
        n_1700_B.removeIf(n -> n.n_1700_B() && n.v_4262_N().R_4764_Y());
        int renderedCount = 0;
        for (o_4117_e notification : n_1700_B) {
            float width;
            if (renderedCount >= 2) break;
            if (notification instanceof A_1306_N) {
                width = l_3370_o.G_564_y[12].n_1700_B(notification.R_4764_Y().getString()) + 24.0f;
            } else {
                y_3417_N fontNotification = (y_3417_N)notification;
                width = l_3370_o.u_1723_Y[16].n_1700_B(fontNotification.s_956_w()) + l_3370_o.G_564_y[12].n_1700_B(notification.R_4764_Y().getString()) + 17.0f;
            }
            float x = ((float)H_2857_Y.Q_4569_t() - width) / 2.0f;
            if (ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Interface.class).w_1484_f() && Interface.t_148_a.J_1907_R("\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u044f").booleanValue()) {
                notification.n_1700_B(x, startY, event.J_1907_R());
            }
            startY += 13.5f;
            ++renderedCount;
        }
    }
}


