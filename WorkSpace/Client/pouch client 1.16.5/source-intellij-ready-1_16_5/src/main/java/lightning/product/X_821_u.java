/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class X_821_u
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;

    public X_821_u() {
    }

    public X_821_u(int windowIdIn, int propertyIn, int valueIn) {
        this.n_1700_B = windowIdIn;
        this.J_1907_R = propertyIn;
        this.R_4764_Y = valueIn;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readUnsignedByte();
        this.J_1907_R = buf.readShort();
        this.R_4764_Y = buf.readShort();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B);
        buf.writeShort(this.J_1907_R);
        buf.writeShort(this.R_4764_Y);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }
}


