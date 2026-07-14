/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.U_2912_j;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundTagQueryPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    @Nullable
    private U_2912_j J_1907_R;

    public ClientboundTagQueryPacket() {
    }

    public ClientboundTagQueryPacket(int p_i49757_1_, @Nullable U_2912_j p_i49757_2_) {
        this.n_1700_B = p_i49757_1_;
        this.J_1907_R = p_i49757_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.t_148_a();
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

    public int J_1907_R() {
        return this.n_1700_B;
    }

    @Nullable
    public U_2912_j R_4764_Y() {
        return this.J_1907_R;
    }

    @Override
    public boolean n_1700_B() {
        return true;
    }
}


