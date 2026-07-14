/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.ServerLoginPacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class ServerboundCustomQueryPacket
implements Packet<ServerLoginPacketListener> {
    private int n_1700_B;
    private b_2585_i J_1907_R;

    public ServerboundCustomQueryPacket() {
    }

    public ServerboundCustomQueryPacket(int p_i49516_1_, @Nullable b_2585_i p_i49516_2_) {
        this.n_1700_B = p_i49516_1_;
        this.J_1907_R = p_i49516_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        if (buf.readBoolean()) {
            int i = buf.readableBytes();
            if (i < 0 || i > 0x100000) {
                throw new IOException("Payload may not be larger than 1048576 bytes");
            }
            this.J_1907_R = new b_2585_i(buf.readBytes(i));
        } else {
            this.J_1907_R = null;
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        if (this.J_1907_R != null) {
            buf.writeBoolean(true);
            buf.writeBytes(this.J_1907_R.copy());
        } else {
            buf.writeBoolean(false);
        }
    }

    @Override
    public void n_1700_B(ServerLoginPacketListener handler) {
        handler.n_1700_B(this);
    }
}


