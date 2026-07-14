/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class I_4838_g
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;
    private String J_1907_R;

    public I_4838_g() {
    }

    public I_4838_g(int p_i47928_1_, String p_i47928_2_) {
        this.n_1700_B = p_i47928_1_;
        this.J_1907_R = p_i47928_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.P_1922_E(32500);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R, 32500);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public String R_4764_Y() {
        return this.J_1907_R;
    }
}


