/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 */
package lightning.product;

import io.netty.buffer.Unpooled;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lightning.product.B_4088_l;
import lightning.product.H_2506_c;
import lightning.product.O_3036_q;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.g_2495_F;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.p_1977_n;
import lightning.product.y_2603_k;
import mods.voicechat.eventforge.ForgeNetworkEvents;
import net.minecraft.server.G_564_y;

public class a_2725_z
extends X_3546_T {
    public static a_2725_z v_4262_N;
    public final p_1977_n w_1484_f = new p_1977_n("\u041f\u043e\u0434\u0441\u0432\u0435\u0447\u0438\u0432\u0430\u0442\u044c \u0441\u0435\u0431\u044f", true);
    public final p_1977_n t_148_a = new p_1977_n("\u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b \u0433\u043b\u043e\u0431\u0430\u043b\u043e\u0432", true);
    public final h_2367_h s_956_w = new h_2367_h("\u0426\u0432\u0435\u0442 \u0433\u043b\u043e\u0431\u0430\u043b\u0430", true, H_2506_c.n_1700_B(255, 80, 160, 220));
    private static final g_2336_b u_2550_I;
    private final Map<UUID, n_1700_B> M_588_G = new HashMap<UUID, n_1700_B>();
    private int P_4830_p = 0;
    private static final int h_1847_R = 20;

    public a_2725_z() {
        super("Globals", y_2603_k.P_1922_E);
        v_4262_N = this;
        this.n_1700_B(this.w_1484_f, this.t_148_a, this.s_956_w);
        ForgeNetworkEvents.registerClientPacket(u_2550_I, a_2725_z::n_1700_B);
    }

    public static a_2725_z h_1847_R() {
        return v_4262_N;
    }

    private static void n_1700_B(g_2495_F packet) {
        a_2725_z g = v_4262_N;
        if (g == null) {
            return;
        }
        g.J_1907_R(packet);
    }

    private static void n_1700_B(O_3036_q packet, B_4088_l sender) {
        if (sender == null || sender.J_1907_R == null) {
            return;
        }
        b_2585_i buf = packet.R_4764_Y();
        if (buf == null) {
            return;
        }
        try {
            String uuidStr = buf.P_1922_E(Short.MAX_VALUE);
            if (buf.readableBytes() < 24) {
                return;
            }
            double x = buf.readDouble();
            double y = buf.readDouble();
            double z = buf.readDouble();
            UUID claimed = UUID.fromString(uuidStr);
            if (!claimed.equals(sender.w_2705_t())) {
                return;
            }
            String authoritativeName = sender.y_4642_Y().getName();
            G_564_y server = sender.J_1907_R;
            B_4088_l senderRef = sender;
            String uuidFinal = uuidStr;
            server.execute(() -> {
                for (B_4088_l other : server.p_178_J().w_1457_N()) {
                    if (other == senderRef) continue;
                    b_2585_i out = new b_2585_i(Unpooled.buffer());
                    out.n_1700_B(uuidFinal);
                    out.writeDouble(x);
                    out.writeDouble(y);
                    out.writeDouble(z);
                    out.n_1700_B(authoritativeName);
                    other.n_1700_B.n_1700_B(new g_2495_F(u_2550_I, out));
                }
            });
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        this.M_588_G.clear();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (a_2725_z.c_3005_b.Y_259_p == null || a_2725_z.c_3005_b.Y_601_j == null) {
            return;
        }
        if (a_2725_z.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        ++this.P_4830_p;
        if (this.P_4830_p >= 20) {
            this.P_4830_p = 0;
            this.Q_4569_t();
        }
        long currentTime = System.currentTimeMillis();
        this.M_588_G.entrySet().removeIf(entry -> currentTime - ((n_1700_B)entry.getValue()).u_1723_Y > 5000L);
    }

    private void J_1907_R(g_2495_F packet) {
        if (a_2725_z.c_3005_b.Y_259_p == null || a_2725_z.c_3005_b.Y_601_j == null) {
            return;
        }
        try {
            a_3913_L player;
            b_2585_i buffer = packet.R_4764_Y();
            if (buffer == null || buffer.readableBytes() == 0) {
                return;
            }
            String uuidString = buffer.P_1922_E(Short.MAX_VALUE);
            if (buffer.readableBytes() < 24) {
                return;
            }
            double x = buffer.readDouble();
            double y = buffer.readDouble();
            double z = buffer.readDouble();
            UUID uuid = UUID.fromString(uuidString);
            if (uuid.equals(a_2725_z.c_3005_b.Y_259_p.w_2705_t())) {
                return;
            }
            String playerName = buffer.readableBytes() > 0 ? buffer.P_1922_E(Short.MAX_VALUE) : ((player = a_2725_z.c_3005_b.Y_601_j.n_1700_B(uuid)) != null ? player.y_4642_Y().getName() : "Unknown");
            this.M_588_G.put(uuid, new n_1700_B(uuid, playerName, x, y, z, System.currentTimeMillis()));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void Q_4569_t() {
        if (a_2725_z.c_3005_b.Y_259_p == null || a_2725_z.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        if (a_2725_z.c_3005_b.Y_259_p.n_1700_B.getNetworkManager() == null) {
            return;
        }
        try {
            b_2585_i buffer = new b_2585_i(Unpooled.buffer());
            buffer.n_1700_B(a_2725_z.c_3005_b.Y_259_p.w_2705_t().toString());
            buffer.writeDouble(a_2725_z.c_3005_b.Y_259_p.O_3598_v());
            buffer.writeDouble(a_2725_z.c_3005_b.Y_259_p.X_2960_b());
            buffer.writeDouble(a_2725_z.c_3005_b.Y_259_p.l_2647_k());
            O_3036_q packet = new O_3036_q(u_2550_I, buffer);
            a_2725_z.c_3005_b.Y_259_p.n_1700_B.getNetworkManager().n_1700_B(packet);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public boolean n_1700_B(a_3913_L player) {
        if (player == null) {
            return false;
        }
        return this.M_588_G.containsKey(player.w_2705_t());
    }

    public boolean n_1700_B(UUID uuid) {
        if (uuid == null) {
            return false;
        }
        return this.M_588_G.containsKey(uuid);
    }

    public n_1700_B J_1907_R(a_3913_L player) {
        if (player == null) {
            return null;
        }
        return this.M_588_G.get(player.w_2705_t());
    }

    static {
        u_2550_I = new g_2336_b("pouch:globals");
        ForgeNetworkEvents.registerServerPacket(u_2550_I, a_2725_z::n_1700_B);
    }

    public static class n_1700_B {
        public final UUID n_1700_B;
        public final String J_1907_R;
        public final double R_4764_Y;
        public final double G_564_y;
        public final double P_1922_E;
        public final long u_1723_Y;

        public n_1700_B(UUID uuid, String name, double x, double y, double z, long lastUpdate) {
            this.n_1700_B = uuid;
            this.J_1907_R = name;
            this.R_4764_Y = x;
            this.G_564_y = y;
            this.P_1922_E = z;
            this.u_1723_Y = lastUpdate;
        }
    }
}

