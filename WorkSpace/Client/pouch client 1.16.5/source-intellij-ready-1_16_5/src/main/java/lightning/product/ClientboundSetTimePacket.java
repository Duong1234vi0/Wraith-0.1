/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundSetTimePacket
implements Packet<ClientGamePacketListener> {
    private long n_1700_B;
    private long J_1907_R;

    public ClientboundSetTimePacket() {
    }

    public ClientboundSetTimePacket(long totalWorldTimeIn, long worldTimeIn, boolean doDaylightCycle) {
        this.n_1700_B = totalWorldTimeIn;
        this.J_1907_R = worldTimeIn;
        if (!doDaylightCycle) {
            this.J_1907_R = -this.J_1907_R;
            if (this.J_1907_R == 0L) {
                this.J_1907_R = -1L;
            }
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readLong();
        this.J_1907_R = buf.readLong();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeLong(this.n_1700_B);
        buf.writeLong(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public long J_1907_R() {
        return this.n_1700_B;
    }

    public long R_4764_Y() {
        return this.J_1907_R;
    }
}


