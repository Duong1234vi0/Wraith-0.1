/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class h_516_K
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;

    public h_516_K() {
    }

    public h_516_K(int windowIdIn, int buttonIn) {
        this.n_1700_B = windowIdIn;
        this.J_1907_R = buttonIn;
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readByte();
        this.J_1907_R = buf.readByte();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B);
        buf.writeByte(this.J_1907_R);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }
}


