/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.b_4507_u;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundSetCameraPacket
implements Packet<ClientGamePacketListener> {
    public int n_1700_B;

    public ClientboundSetCameraPacket() {
    }

    public ClientboundSetCameraPacket(N_4263_v entityIn) {
        this.n_1700_B = entityIn.j_276_v();
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Nullable
    public N_4263_v n_1700_B(b_4507_u worldIn) {
        return worldIn.J_1907_R(this.n_1700_B);
    }
}


