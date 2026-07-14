/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundSetDefaultSpawnPositionPacket
implements Packet<ClientGamePacketListener> {
    private c_1514_x n_1700_B;
    private float J_1907_R;

    public ClientboundSetDefaultSpawnPositionPacket() {
    }

    public ClientboundSetDefaultSpawnPositionPacket(c_1514_x p_i242086_1_, float p_i242086_2_) {
        this.n_1700_B = p_i242086_1_;
        this.J_1907_R = p_i242086_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.R_4764_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    public float R_4764_Y() {
        return this.J_1907_R;
    }
}


