/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class t_3906_J
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;

    public t_3906_J() {
    }

    public t_3906_J(int p_i50776_1_, int p_i50776_2_, int p_i50776_3_) {
        this.n_1700_B = p_i50776_1_;
        this.J_1907_R = p_i50776_2_;
        this.R_4764_Y = p_i50776_3_;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readUnsignedByte();
        this.J_1907_R = buf.u_1723_Y();
        this.R_4764_Y = buf.readInt();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B);
        buf.G_564_y(this.J_1907_R);
        buf.writeInt(this.R_4764_Y);
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


