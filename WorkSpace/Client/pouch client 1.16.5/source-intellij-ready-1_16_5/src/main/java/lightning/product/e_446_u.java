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
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class e_446_u
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;

    public e_446_u() {
    }

    public e_446_u(N_4263_v entityIn, @Nullable N_4263_v vehicleIn) {
        this.n_1700_B = entityIn.j_276_v();
        this.J_1907_R = vehicleIn != null ? vehicleIn.j_276_v() : 0;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readInt();
        this.J_1907_R = buf.readInt();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeInt(this.n_1700_B);
        buf.writeInt(this.J_1907_R);
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
}


