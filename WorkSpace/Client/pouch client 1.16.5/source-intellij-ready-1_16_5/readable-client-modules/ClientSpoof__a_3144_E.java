/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 */
package lightning.product;

import io.netty.buffer.Unpooled;
import lightning.product.O_3036_q;
import lightning.product.Q_2753_H;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.q_366_O;
import lightning.product.t_3138_Z;
import lightning.product.y_2603_k;

public class a_3144_E
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u041a\u043b\u0438\u0435\u043d\u0442", "Lunar Client", "Lunar Client", "Badlion Client", "Forge", "Fabric", "Optifine", "Vanilla", "LabyMod");

    public a_3144_E() {
        super("ClientSpoof", y_2603_k.P_1922_E);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        O_3036_q packet;
        g_2336_b channel;
        t_3138_Z<?> t_3138_Z2;
        if (a_3144_E.c_3005_b.Y_259_p == null || a_3144_E.c_3005_b.Y_601_j == null) {
            return;
        }
        if (event.R_4764_Y() && (t_3138_Z2 = event.G_564_y()) instanceof O_3036_q && (channel = (packet = (O_3036_q)t_3138_Z2).J_1907_R()) != null && channel.equals(O_3036_q.n_1700_B)) {
            event.n_1700_B(true);
            this.h_1847_R();
        }
    }

    private void h_1847_R() {
        String brand = this.Q_4569_t();
        b_2585_i buffer = new b_2585_i(Unpooled.buffer());
        buffer.n_1700_B(brand);
        O_3036_q brandPacket = new O_3036_q(O_3036_q.n_1700_B, buffer);
        a_3144_E.c_3005_b.Y_259_p.n_1700_B.J_1907_R(brandPacket);
    }

    private String Q_4569_t() {
        return switch ((String)this.v_4262_N.J_1907_R()) {
            case "Lunar Client" -> "lunarclient:v2.17.0-2452/v1.20.4-7d9e2a3";
            case "Badlion Client" -> "Badlion Client";
            case "Forge" -> "forge";
            case "Fabric" -> "fabric";
            case "Optifine" -> "optifine";
            case "Vanilla" -> "vanilla";
            case "LabyMod" -> "labymod";
            default -> "vanilla";
        };
    }
}

