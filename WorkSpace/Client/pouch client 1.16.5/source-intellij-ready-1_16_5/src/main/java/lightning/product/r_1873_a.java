/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class r_1873_a
implements Packet<ClientGamePacketListener> {
    private long n_1700_B;

    public r_1873_a() {
    }

    public r_1873_a(long idIn) {
        this.n_1700_B = idIn;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
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


