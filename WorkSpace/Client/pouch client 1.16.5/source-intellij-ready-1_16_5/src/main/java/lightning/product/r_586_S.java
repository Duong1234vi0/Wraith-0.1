/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class r_586_S
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;
    private Z_1993_T J_1907_R = Z_1993_T.J_1907_R;

    public r_586_S() {
    }

    public r_586_S(int slotIdIn, Z_1993_T stackIn) {
        this.n_1700_B = slotIdIn;
        this.J_1907_R = stackIn.t_148_a();
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readShort();
        this.J_1907_R = buf.u_2550_I();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeShort(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public Z_1993_T R_4764_Y() {
        return this.J_1907_R;
    }
}


