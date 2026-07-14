/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;
import lightning.product.SerializableUUID;
import lightning.product.b_2585_i;
import lightning.product.ClientLoginPacketListener;
import lightning.product.Packet;

public class ClientboundGameProfilePacket
implements Packet<ClientLoginPacketListener> {
    private GameProfile n_1700_B;

    public ClientboundGameProfilePacket() {
    }

    public ClientboundGameProfilePacket(GameProfile profileIn) {
        this.n_1700_B = profileIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        int[] aint = new int[4];
        for (int i = 0; i < aint.length; ++i) {
            aint[i] = buf.readInt();
        }
        UUID uuid = SerializableUUID.n_1700_B(aint);
        String s = buf.P_1922_E(16);
        this.n_1700_B = new GameProfile(uuid, s);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        for (int i : SerializableUUID.n_1700_B(this.n_1700_B.getId())) {
            buf.writeInt(i);
        }
        buf.n_1700_B(this.n_1700_B.getName());
    }

    @Override
    public void n_1700_B(ClientLoginPacketListener handler) {
        handler.n_1700_B(this);
    }

    public GameProfile J_1907_R() {
        return this.n_1700_B;
    }
}


