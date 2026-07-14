/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.Packet;

public class H_1420_X
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;
    private c_1514_x J_1907_R;

    public H_1420_X() {
    }

    public H_1420_X(int p_i49756_1_, c_1514_x p_i49756_2_) {
        this.n_1700_B = p_i49756_1_;
        this.J_1907_R = p_i49756_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.R_4764_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public c_1514_x R_4764_Y() {
        return this.J_1907_R;
    }
}


