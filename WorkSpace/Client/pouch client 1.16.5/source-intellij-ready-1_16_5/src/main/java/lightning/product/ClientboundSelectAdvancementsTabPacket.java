/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundSelectAdvancementsTabPacket
implements Packet<ClientGamePacketListener> {
    @Nullable
    private g_2336_b n_1700_B;

    public ClientboundSelectAdvancementsTabPacket() {
    }

    public ClientboundSelectAdvancementsTabPacket(@Nullable g_2336_b p_i47596_1_) {
        this.n_1700_B = p_i47596_1_;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        if (buf.readBoolean()) {
            this.n_1700_B = buf.P_4830_p();
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeBoolean(this.n_1700_B != null);
        if (this.n_1700_B != null) {
            buf.n_1700_B(this.n_1700_B);
        }
    }

    @Nullable
    public g_2336_b J_1907_R() {
        return this.n_1700_B;
    }
}


