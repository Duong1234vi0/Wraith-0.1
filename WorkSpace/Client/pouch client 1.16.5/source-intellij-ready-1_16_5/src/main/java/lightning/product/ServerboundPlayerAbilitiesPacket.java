/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Abilities;
import lightning.product.Packet;

public class ServerboundPlayerAbilitiesPacket
implements Packet<ServerGamePacketListener> {
    private boolean n_1700_B;

    public ServerboundPlayerAbilitiesPacket() {
    }

    public ServerboundPlayerAbilitiesPacket(Abilities capabilities) {
        this.n_1700_B = capabilities.J_1907_R;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        byte b0 = buf.readByte();
        this.n_1700_B = (b0 & 2) != 0;
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        int b0 = 0;
        if (this.n_1700_B) {
            b0 = (byte)(b0 | 2);
        }
        buf.writeByte(b0);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public boolean J_1907_R() {
        return this.n_1700_B;
    }
}


