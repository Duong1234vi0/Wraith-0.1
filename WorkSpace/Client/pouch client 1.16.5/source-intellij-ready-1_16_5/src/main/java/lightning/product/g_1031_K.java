/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import lightning.product.H_2506_c;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.MinecraftAccess;
import lightning.product.b_3528_u;
import lightning.product.e_2866_D;
import lightning.product.l_3370_o;
import lightning.product.v_2826_q;
import lightning.product.y_4642_Y;
import org.joml.Vector2f;

public class g_1031_K
implements MinecraftAccess {
    private static final List<n_1700_B> n_1700_B = new LinkedList<n_1700_B>();

    public static void n_1700_B(String owner, double x, double y, double z, long ttlMs) {
        g_1031_K.n_1700_B(owner, x, y, z, null, ttlMs);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void n_1700_B(String owner, double x, double y, double z, UUID targetPlayerUuid, long ttlMs) {
        if (owner == null) {
            owner = "Unknown";
        }
        long expiresAt = System.currentTimeMillis() + Math.max(0L, ttlMs);
        List<n_1700_B> list = n_1700_B;
        synchronized (list) {
            n_1700_B.add(new n_1700_B(owner, x, y, z, targetPlayerUuid, expiresAt));
            while (n_1700_B.size() > 20) {
                n_1700_B.remove(0);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Y_1740_V
    public void n_1700_B(b_3528_u event) {
        if (g_1031_K.c_3005_b.Y_259_p == null || y_4642_Y.R_4764_Y()) {
            return;
        }
        long now = System.currentTimeMillis();
        double px = g_1031_K.c_3005_b.Y_259_p.O_3598_v();
        double pz = g_1031_K.c_3005_b.Y_259_p.l_2647_k();
        List<n_1700_B> list = n_1700_B;
        synchronized (list) {
            Iterator<n_1700_B> it = n_1700_B.iterator();
            while (it.hasNext()) {
                a_3913_L player;
                n_1700_B ping = it.next();
                if (now >= ping.u_1723_Y) {
                    it.remove();
                    continue;
                }
                if (ping.P_1922_E != null && g_1031_K.c_3005_b.Y_601_j != null && (player = g_1031_K.c_3005_b.Y_601_j.n_1700_B(ping.P_1922_E)) != null) {
                    ping.J_1907_R = player.O_3598_v();
                    ping.R_4764_Y = player.X_2960_b() + (double)(player.v_165_F() * 0.85f);
                    ping.G_564_y = player.l_2647_k();
                }
                e_2866_D worldPos = new e_2866_D(ping.J_1907_R, ping.R_4764_Y, ping.G_564_y);
                Vector2f screen = v_2826_q.n_1700_B(worldPos);
                if (Float.isInfinite(screen.x) || Float.isInfinite(screen.y) || screen.x == Float.MAX_VALUE || screen.y == Float.MAX_VALUE) continue;
                String icon = "M";
                String label = ping.n_1700_B + ": " + (int)Math.round(Math.hypot(px - ping.J_1907_R, pz - ping.G_564_y)) + "m";
                int iconColor = H_2506_c.n_1700_B(255, 80, 80, 255);
                int outline = H_2506_c.n_1700_B(0, 0, 0, 255);
                float iconW = l_3370_o.u_1723_Y[20].n_1700_B(icon);
                float iconH = l_3370_o.u_1723_Y[20].h_1847_R();
                float ix = screen.x - iconW / 2.0f;
                float iy = screen.y - iconH / 2.0f;
                l_3370_o.u_1723_Y[20].n_1700_B(event.J_1907_R(), icon, ix, iy, iconColor, false, true, false, true, outline);
                float textW = l_3370_o.G_564_y[14].n_1700_B(label);
                float tx = screen.x - textW / 2.0f;
                float ty = iy + iconH + 3.0f;
                l_3370_o.G_564_y[14].n_1700_B(event.J_1907_R(), label, tx, ty, -1, false, true, false, true, outline);
            }
        }
    }

    private static final class n_1700_B {
        final String n_1700_B;
        volatile double J_1907_R;
        volatile double R_4764_Y;
        volatile double G_564_y;
        final UUID P_1922_E;
        final long u_1723_Y;

        n_1700_B(String owner, double x, double y, double z, UUID targetPlayerUuid, long expiresAt) {
            this.n_1700_B = owner;
            this.J_1907_R = x;
            this.R_4764_Y = y;
            this.G_564_y = z;
            this.P_1922_E = targetPlayerUuid;
            this.u_1723_Y = expiresAt;
        }
    }
}


