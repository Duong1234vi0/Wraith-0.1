/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class p_4692_E
implements Packet<ServerGamePacketListener> {
    private long n_1700_B;

    public p_4692_E() {
    }

    public p_4692_E(long idIn) {
        this.n_1700_B = idIn;
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readLong();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeLong(this.n_1700_B);
    }

    public long J_1907_R() {
        return this.n_1700_B;
    }
}


