/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.Packet;

public class z_1181_o
implements Packet<ServerGamePacketListener> {
    private g_2336_b n_1700_B;

    public z_1181_o() {
    }

    public z_1181_o(Recipe<?> p_i242089_1_) {
        this.n_1700_B = p_i242089_1_.u_1723_Y();
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_4830_p();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public g_2336_b J_1907_R() {
        return this.n_1700_B;
    }
}


