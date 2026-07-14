/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientLoginPacketListener;
import lightning.product.g_2336_b;
import lightning.product.Packet;

public class ClientboundCustomQueryPacket
implements Packet<ClientLoginPacketListener> {
    private int n_1700_B;
    private g_2336_b J_1907_R;
    private b_2585_i R_4764_Y;

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.P_4830_p();
        int i = buf.readableBytes();
        if (i < 0 || i > 0x100000) {
            throw new IOException("Payload may not be larger than 1048576 bytes");
        }
        this.R_4764_Y = new b_2585_i(buf.readBytes(i));
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.writeBytes(this.R_4764_Y.copy());
    }

    @Override
    public void n_1700_B(ClientLoginPacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }
}


