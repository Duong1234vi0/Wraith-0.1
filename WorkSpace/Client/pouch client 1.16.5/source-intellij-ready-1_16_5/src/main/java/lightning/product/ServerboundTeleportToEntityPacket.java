/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.ServerGamePacketListener;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.e_3591_l;
import lightning.product.Packet;

public class ServerboundTeleportToEntityPacket
implements Packet<ServerGamePacketListener> {
    private UUID n_1700_B;

    public ServerboundTeleportToEntityPacket() {
    }

    public ServerboundTeleportToEntityPacket(UUID uniqueIdIn) {
        this.n_1700_B = uniqueIdIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.w_1484_f();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Nullable
    public N_4263_v n_1700_B(e_3591_l worldIn) {
        return worldIn.J_1907_R(this.n_1700_B);
    }
}


