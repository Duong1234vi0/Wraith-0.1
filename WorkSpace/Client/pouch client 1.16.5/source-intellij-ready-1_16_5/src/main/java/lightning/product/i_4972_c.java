/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientLoginPacketListener;
import lightning.product.Packet;

public class i_4972_c
implements Packet<ClientLoginPacketListener> {
    private int n_1700_B;

    public i_4972_c() {
    }

    public i_4972_c(int thresholdIn) {
        this.n_1700_B = thresholdIn;
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
    public void n_1700_B(ClientLoginPacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }
}


