/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class X_508_u
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;

    public X_508_u() {
    }

    public X_508_u(int p_i47316_1_, int p_i47316_2_, int p_i47316_3_) {
        this.n_1700_B = p_i47316_1_;
        this.J_1907_R = p_i47316_2_;
        this.R_4764_Y = p_i47316_3_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.u_1723_Y();
        this.R_4764_Y = buf.u_1723_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.G_564_y(this.J_1907_R);
        buf.G_564_y(this.R_4764_Y);
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

    public int G_564_y() {
        return this.R_4764_Y;
    }
}


