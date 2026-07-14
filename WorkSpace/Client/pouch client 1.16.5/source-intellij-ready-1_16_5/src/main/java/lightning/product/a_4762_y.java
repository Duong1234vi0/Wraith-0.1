/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.List;
import lightning.product.C_4114_x;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class a_4762_y
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private List<C_4114_x.n_1700_B<?>> J_1907_R;

    public a_4762_y() {
    }

    public a_4762_y(int entityIdIn, C_4114_x dataManagerIn, boolean sendAll) {
        this.n_1700_B = entityIdIn;
        if (sendAll) {
            this.J_1907_R = dataManagerIn.R_4764_Y();
            dataManagerIn.P_1922_E();
        } else {
            this.J_1907_R = dataManagerIn.J_1907_R();
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = C_4114_x.n_1700_B(buf);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        C_4114_x.n_1700_B(this.J_1907_R, buf);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public List<C_4114_x.n_1700_B<?>> J_1907_R() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        return this.n_1700_B;
    }
}


