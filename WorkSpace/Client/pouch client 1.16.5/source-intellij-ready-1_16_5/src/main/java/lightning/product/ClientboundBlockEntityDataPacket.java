/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.U_2912_j;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundBlockEntityDataPacket
implements Packet<ClientGamePacketListener> {
    private c_1514_x n_1700_B;
    private int J_1907_R;
    private U_2912_j R_4764_Y;

    public ClientboundBlockEntityDataPacket() {
    }

    public ClientboundBlockEntityDataPacket(c_1514_x blockPosIn, int tileEntityTypeIn, U_2912_j compoundIn) {
        this.n_1700_B = blockPosIn;
        this.J_1907_R = tileEntityTypeIn;
        this.R_4764_Y = compoundIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.R_4764_Y();
        this.J_1907_R = buf.readUnsignedByte();
        this.R_4764_Y = buf.t_148_a();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.writeByte((byte)this.J_1907_R);
        buf.n_1700_B(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }

    public U_2912_j G_564_y() {
        return this.R_4764_Y;
    }
}


