/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class d_1428_k
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;

    public d_1428_k() {
    }

    public d_1428_k(int primaryEffectIn, int secondaryEffectIn) {
        this.n_1700_B = primaryEffectIn;
        this.J_1907_R = secondaryEffectIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.u_1723_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.G_564_y(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }
}


