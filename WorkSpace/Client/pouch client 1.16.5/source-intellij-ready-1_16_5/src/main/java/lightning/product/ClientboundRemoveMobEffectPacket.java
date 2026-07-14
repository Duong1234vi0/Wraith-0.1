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
import lightning.product.g_422_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundRemoveMobEffectPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private g_422_i J_1907_R;

    public ClientboundRemoveMobEffectPacket() {
    }

    public ClientboundRemoveMobEffectPacket(int entityIdIn, g_422_i potionIn) {
        this.n_1700_B = entityIdIn;
        this.J_1907_R = potionIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = g_422_i.n_1700_B(buf.readUnsignedByte());
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.writeByte(g_422_i.n_1700_B(this.J_1907_R));
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Nullable
    public N_4263_v n_1700_B(b_4507_u worldIn) {
        return worldIn.J_1907_R(this.n_1700_B);
    }

    @Nullable
    public g_422_i J_1907_R() {
        return this.J_1907_R;
    }
}


