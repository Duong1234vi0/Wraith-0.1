/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class v_4727_z
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;

    public v_4727_z() {
    }

    public v_4727_z(int windowIdIn) {
        this.n_1700_B = windowIdIn;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readUnsignedByte();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B);
    }
}


