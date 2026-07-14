/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class ServerboundPaddleBoatPacket
implements Packet<ServerGamePacketListener> {
    private boolean n_1700_B;
    private boolean J_1907_R;

    public ServerboundPaddleBoatPacket() {
    }

    public ServerboundPaddleBoatPacket(boolean p_i46873_1_, boolean p_i46873_2_) {
        this.n_1700_B = p_i46873_1_;
        this.J_1907_R = p_i46873_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readBoolean();
        this.J_1907_R = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeBoolean(this.n_1700_B);
        buf.writeBoolean(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public boolean J_1907_R() {
        return this.n_1700_B;
    }

    public boolean R_4764_Y() {
        return this.J_1907_R;
    }
}


