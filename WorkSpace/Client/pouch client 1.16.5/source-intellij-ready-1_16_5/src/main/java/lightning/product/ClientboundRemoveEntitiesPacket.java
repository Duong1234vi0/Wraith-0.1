/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundRemoveEntitiesPacket
implements Packet<ClientGamePacketListener> {
    private int[] n_1700_B;

    public ClientboundRemoveEntitiesPacket() {
    }

    public ClientboundRemoveEntitiesPacket(int ... entityIdsIn) {
        this.n_1700_B = entityIdsIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = new int[buf.u_1723_Y()];
        for (int i = 0; i < this.n_1700_B.length; ++i) {
            this.n_1700_B[i] = buf.u_1723_Y();
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B.length);
        for (int i : this.n_1700_B) {
            buf.G_564_y(i);
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int[] J_1907_R() {
        return this.n_1700_B;
    }
}


