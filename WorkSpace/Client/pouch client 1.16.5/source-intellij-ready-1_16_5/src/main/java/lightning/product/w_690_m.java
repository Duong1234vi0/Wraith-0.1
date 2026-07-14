/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.x_282_a;

public class w_690_m
implements Packet<ClientGamePacketListener> {
    private x_282_a n_1700_B;

    public w_690_m() {
    }

    public w_690_m(x_282_a messageIn) {
        this.n_1700_B = messageIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_1922_E();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public x_282_a J_1907_R() {
        return this.n_1700_B;
    }
}


