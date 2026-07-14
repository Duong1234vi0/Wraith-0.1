/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class ServerboundLockDifficultyPacket
implements Packet<ServerGamePacketListener> {
    private boolean n_1700_B;

    public ServerboundLockDifficultyPacket() {
    }

    public ServerboundLockDifficultyPacket(boolean p_i50760_1_) {
        this.n_1700_B = p_i50760_1_;
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeBoolean(this.n_1700_B);
    }

    public boolean J_1907_R() {
        return this.n_1700_B;
    }
}


