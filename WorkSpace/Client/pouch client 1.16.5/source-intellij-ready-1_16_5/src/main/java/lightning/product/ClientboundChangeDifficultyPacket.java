/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.R_2450_T;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundChangeDifficultyPacket
implements Packet<ClientGamePacketListener> {
    private R_2450_T n_1700_B;
    private boolean J_1907_R;

    public ClientboundChangeDifficultyPacket() {
    }

    public ClientboundChangeDifficultyPacket(R_2450_T difficultyIn, boolean difficultyLockedIn) {
        this.n_1700_B = difficultyIn;
        this.J_1907_R = difficultyLockedIn;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = R_2450_T.n_1700_B(buf.readUnsignedByte());
        this.J_1907_R = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B.n_1700_B());
        buf.writeBoolean(this.J_1907_R);
    }

    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    public R_2450_T R_4764_Y() {
        return this.n_1700_B;
    }
}


