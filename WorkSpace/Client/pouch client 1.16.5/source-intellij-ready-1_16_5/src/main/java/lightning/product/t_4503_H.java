/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class t_4503_H
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private c_1514_x J_1907_R;
    private int R_4764_Y;

    public t_4503_H() {
    }

    public t_4503_H(int breakerIdIn, c_1514_x positionIn, int progressIn) {
        this.n_1700_B = breakerIdIn;
        this.J_1907_R = positionIn;
        this.R_4764_Y = progressIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.R_4764_Y();
        this.R_4764_Y = buf.readUnsignedByte();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.writeByte(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public c_1514_x R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }
}


