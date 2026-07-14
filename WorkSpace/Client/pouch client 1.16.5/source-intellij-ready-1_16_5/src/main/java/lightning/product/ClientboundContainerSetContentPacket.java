/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.util.List;
import lightning.product.NonNullList;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundContainerSetContentPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private List<Z_1993_T> J_1907_R;

    public ClientboundContainerSetContentPacket() {
    }

    public ClientboundContainerSetContentPacket(int p_i47317_1_, NonNullList<Z_1993_T> p_i47317_2_) {
        this.n_1700_B = p_i47317_1_;
        this.J_1907_R = NonNullList.n_1700_B(p_i47317_2_.size(), Z_1993_T.J_1907_R);
        for (int i = 0; i < this.J_1907_R.size(); ++i) {
            this.J_1907_R.set(i, p_i47317_2_.get(i).t_148_a());
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readUnsignedByte();
        int i = buf.readShort();
        this.J_1907_R = NonNullList.n_1700_B(i, Z_1993_T.J_1907_R);
        for (int j = 0; j < i; ++j) {
            this.J_1907_R.set(j, buf.u_2550_I());
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B);
        buf.writeShort(this.J_1907_R.size());
        for (Z_1993_T itemstack : this.J_1907_R) {
            buf.n_1700_B(itemstack);
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public List<Z_1993_T> R_4764_Y() {
        return this.J_1907_R;
    }
}


