/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.q_1613_l;
import lightning.product.Packet;

public class G_4919_s
implements Packet<ClientGamePacketListener> {
    private q_1613_l n_1700_B;
    private int J_1907_R;

    public G_4919_s() {
    }

    public G_4919_s(q_1613_l itemIn, int ticksIn) {
        this.n_1700_B = itemIn;
        this.J_1907_R = ticksIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = q_1613_l.J_1907_R(buf.u_1723_Y());
        this.J_1907_R = buf.u_1723_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(q_1613_l.n_1700_B(this.n_1700_B));
        buf.G_564_y(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public q_1613_l J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }
}


