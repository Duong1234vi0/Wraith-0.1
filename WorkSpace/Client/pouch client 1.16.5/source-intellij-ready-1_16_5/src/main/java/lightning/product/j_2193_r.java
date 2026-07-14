/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ClientIntentionPacket;
import lightning.product.c_1633_k;
import lightning.product.handshakeServerHandshakePacketListener;
import lightning.product.o_1314_v;
import lightning.product.t_1786_h;
import lightning.product.x_282_a;
import net.minecraft.server.G_564_y;

public class j_2193_r
implements handshakeServerHandshakePacketListener {
    private final G_564_y n_1700_B;
    private final c_1633_k J_1907_R;

    public j_2193_r(G_564_y mcServerIn, c_1633_k networkManagerIn) {
        this.n_1700_B = mcServerIn;
        this.J_1907_R = networkManagerIn;
    }

    @Override
    public void n_1700_B(ClientIntentionPacket packetIn) {
        this.J_1907_R.n_1700_B(packetIn.J_1907_R());
        this.J_1907_R.n_1700_B(new o_1314_v(this.n_1700_B, this.J_1907_R));
    }

    @Override
    public void onDisconnect(x_282_a reason) {
    }

    @Override
    public c_1633_k getNetworkManager() {
        return this.J_1907_R;
    }

    @Override
    public t_1786_h getBotNetwork() {
        return null;
    }
}


