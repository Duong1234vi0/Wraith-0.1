/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class W_4328_U
implements Packet<ServerGamePacketListener> {
    private String n_1700_B;

    public W_4328_U() {
    }

    public W_4328_U(String messageIn) {
        if (messageIn.length() > 256) {
            messageIn = messageIn.substring(0, 256);
        }
        this.n_1700_B = messageIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_1922_E(256);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public String J_1907_R() {
        return this.n_1700_B;
    }
}


