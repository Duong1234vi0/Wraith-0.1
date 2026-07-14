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
import lightning.product.ServerLoginPacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class ServerboundHelloPacket
implements Packet<ServerLoginPacketListener> {
    private GameProfile n_1700_B;

    public ServerboundHelloPacket() {
    }

    public ServerboundHelloPacket(GameProfile profileIn) {
        this.n_1700_B = profileIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = new GameProfile((UUID)null, buf.P_1922_E(16));
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B.getName());
    }

    @Override
    public void n_1700_B(ServerLoginPacketListener handler) {
        handler.n_1700_B(this);
    }

    public GameProfile J_1907_R() {
        return this.n_1700_B;
    }
}


