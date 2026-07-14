/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class w_3005_z
implements Packet<ClientGamePacketListener> {
    private c_1514_x n_1700_B;

    public w_3005_z() {
    }

    public w_3005_z(c_1514_x posIn) {
        this.n_1700_B = posIn;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.R_4764_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
    }

    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }
}


