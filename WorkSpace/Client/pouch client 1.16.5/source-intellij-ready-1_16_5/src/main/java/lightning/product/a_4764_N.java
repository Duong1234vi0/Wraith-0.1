/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class a_4764_N
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;
    private Z_1993_T R_4764_Y = Z_1993_T.J_1907_R;

    public a_4764_N() {
    }

    public a_4764_N(int windowIdIn, int slotIn, Z_1993_T itemIn) {
        this.n_1700_B = windowIdIn;
        this.J_1907_R = slotIn;
        this.R_4764_Y = itemIn.t_148_a();
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readByte();
        this.J_1907_R = buf.readShort();
        this.R_4764_Y = buf.u_2550_I();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B);
        buf.writeShort(this.J_1907_R);
        buf.n_1700_B(this.R_4764_Y);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }

    public Z_1993_T G_564_y() {
        return this.R_4764_Y;
    }
}


