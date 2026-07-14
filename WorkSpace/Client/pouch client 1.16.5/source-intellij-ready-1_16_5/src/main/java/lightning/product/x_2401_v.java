/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class x_2401_v
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;

    public x_2401_v() {
    }

    public x_2401_v(int teleportIdIn) {
        this.n_1700_B = teleportIdIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }
}


