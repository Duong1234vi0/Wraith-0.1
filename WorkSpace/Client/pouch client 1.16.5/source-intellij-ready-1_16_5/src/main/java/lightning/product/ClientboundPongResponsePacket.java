/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.M_2450_l;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class ClientboundPongResponsePacket
implements Packet<M_2450_l> {
    private long n_1700_B;

    public ClientboundPongResponsePacket() {
    }

    public ClientboundPongResponsePacket(long clientTimeIn) {
        this.n_1700_B = clientTimeIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readLong();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeLong(this.n_1700_B);
    }

    @Override
    public void n_1700_B(M_2450_l handler) {
        handler.handlePong(this);
    }
}


