/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.TagContainer;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class I_4656_k
implements Packet<ClientGamePacketListener> {
    private TagContainer n_1700_B;

    public I_4656_k() {
    }

    public I_4656_k(TagContainer p_i242087_1_) {
        this.n_1700_B = p_i242087_1_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = TagContainer.J_1907_R(buf);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        this.n_1700_B.n_1700_B(buf);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public TagContainer J_1907_R() {
        return this.n_1700_B;
    }
}


