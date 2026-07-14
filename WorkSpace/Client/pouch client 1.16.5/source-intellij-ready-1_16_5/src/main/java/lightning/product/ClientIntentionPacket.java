/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.SharedConstants;
import lightning.product.b_2585_i;
import lightning.product.d_4952_K;
import lightning.product.handshakeServerHandshakePacketListener;
import lightning.product.Packet;

public class ClientIntentionPacket
implements Packet<handshakeServerHandshakePacketListener> {
    private int n_1700_B;
    private String J_1907_R;
    private int R_4764_Y;
    private d_4952_K G_564_y;

    public ClientIntentionPacket() {
    }

    public ClientIntentionPacket(String p_i47613_1_, int p_i47613_2_, d_4952_K p_i47613_3_) {
        this.n_1700_B = SharedConstants.n_1700_B().getProtocolVersion();
        this.J_1907_R = p_i47613_1_;
        this.R_4764_Y = p_i47613_2_;
        this.G_564_y = p_i47613_3_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.P_1922_E(255);
        this.R_4764_Y = buf.readUnsignedShort();
        this.G_564_y = d_4952_K.n_1700_B(buf.u_1723_Y());
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.writeShort(this.R_4764_Y);
        buf.G_564_y(this.G_564_y.n_1700_B());
    }

    @Override
    public void n_1700_B(handshakeServerHandshakePacketListener handler) {
        handler.n_1700_B(this);
    }

    public d_4952_K J_1907_R() {
        return this.G_564_y;
    }

    public int R_4764_Y() {
        return this.n_1700_B;
    }
}


