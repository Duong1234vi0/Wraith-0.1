/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.b_4507_u;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class C_1375_J
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private byte J_1907_R;

    public C_1375_J() {
    }

    public C_1375_J(N_4263_v entityIn, byte opcodeIn) {
        this.n_1700_B = entityIn.j_276_v();
        this.J_1907_R = opcodeIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readInt();
        this.J_1907_R = buf.readByte();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeInt(this.n_1700_B);
        buf.writeByte(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public N_4263_v n_1700_B(b_4507_u worldIn) {
        return worldIn.J_1907_R(this.n_1700_B);
    }

    public byte J_1907_R() {
        return this.J_1907_R;
    }
}


