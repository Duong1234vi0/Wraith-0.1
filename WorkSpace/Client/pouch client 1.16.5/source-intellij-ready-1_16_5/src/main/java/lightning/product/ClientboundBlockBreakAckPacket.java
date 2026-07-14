/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.io.IOException;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientboundBlockBreakAckPacket
implements Packet<ClientGamePacketListener> {
    private static final Logger J_1907_R = LogManager.getLogger();
    private c_1514_x R_4764_Y;
    private K_4074_S G_564_y;
    ServerboundPlayerActionPacket.n_1700_B n_1700_B;
    private boolean P_1922_E;

    public ClientboundBlockBreakAckPacket() {
    }

    public ClientboundBlockBreakAckPacket(c_1514_x pos, K_4074_S state, ServerboundPlayerActionPacket.n_1700_B action, boolean successful, String context) {
        this.R_4764_Y = pos.toImmutable();
        this.G_564_y = state;
        this.n_1700_B = action;
        this.P_1922_E = successful;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.R_4764_Y = buf.R_4764_Y();
        this.G_564_y = T_2915_h.t_4043_B.n_1700_B(buf.u_1723_Y());
        this.n_1700_B = buf.n_1700_B(ServerboundPlayerActionPacket.n_1700_B.class);
        this.P_1922_E = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.R_4764_Y);
        buf.G_564_y(T_2915_h.s_956_w(this.G_564_y));
        buf.n_1700_B(this.n_1700_B);
        buf.writeBoolean(this.P_1922_E);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public K_4074_S J_1907_R() {
        return this.G_564_y;
    }

    public c_1514_x R_4764_Y() {
        return this.R_4764_Y;
    }

    public boolean G_564_y() {
        return this.P_1922_E;
    }

    public ServerboundPlayerActionPacket.n_1700_B P_1922_E() {
        return this.n_1700_B;
    }
}


