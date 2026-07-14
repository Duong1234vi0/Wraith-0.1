/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class p_1183_T
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;

    public p_1183_T() {
    }

    public p_1183_T(int slotIdIn) {
        this.n_1700_B = slotIdIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readShort();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeShort(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }
}


