/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.x_1688_C;

public class x_2680_y
implements Packet<ClientGamePacketListener> {
    private x_1688_C n_1700_B;

    public x_2680_y() {
    }

    public x_2680_y(x_1688_C hand) {
        this.n_1700_B = hand;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.n_1700_B(x_1688_C.class);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public x_1688_C J_1907_R() {
        return this.n_1700_B;
    }
}


