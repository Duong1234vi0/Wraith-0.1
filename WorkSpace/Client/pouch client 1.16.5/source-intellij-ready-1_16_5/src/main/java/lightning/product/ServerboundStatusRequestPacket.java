/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.I_2310_w;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class ServerboundStatusRequestPacket
implements Packet<I_2310_w> {
    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
    }

    @Override
    public void n_1700_B(I_2310_w handler) {
        handler.n_1700_B(this);
    }
}


