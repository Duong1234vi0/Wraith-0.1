/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class W_4148_E
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;

    public W_4148_E() {
    }

    public W_4148_E(int xIn, int zIn) {
        this.n_1700_B = xIn;
        this.J_1907_R = zIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readInt();
        this.J_1907_R = buf.readInt();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeInt(this.n_1700_B);
        buf.writeInt(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }
}


