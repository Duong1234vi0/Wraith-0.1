/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.List;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundSetPassengersPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private int[] J_1907_R;

    public ClientboundSetPassengersPacket() {
    }

    public ClientboundSetPassengersPacket(N_4263_v entityIn) {
        this.n_1700_B = entityIn.j_276_v();
        List<N_4263_v> list = entityIn.o_3599_Z();
        this.J_1907_R = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            this.J_1907_R[i] = list.get(i).j_276_v();
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.J_1907_R();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int[] J_1907_R() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        return this.n_1700_B;
    }
}


