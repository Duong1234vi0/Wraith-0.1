/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundBlockUpdatePacket
implements Packet<ClientGamePacketListener> {
    private c_1514_x n_1700_B;
    private K_4074_S J_1907_R;

    public ClientboundBlockUpdatePacket() {
    }

    public ClientboundBlockUpdatePacket(c_1514_x p_i242080_1_, K_4074_S p_i242080_2_) {
        this.n_1700_B = p_i242080_1_;
        this.J_1907_R = p_i242080_2_;
    }

    public ClientboundBlockUpdatePacket(BlockGetter p_i48982_1_, c_1514_x pos) {
        this(pos, p_i48982_1_.getBlockState(pos));
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.R_4764_Y();
        this.J_1907_R = T_2915_h.t_4043_B.n_1700_B(buf.u_1723_Y());
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.G_564_y(T_2915_h.s_956_w(this.J_1907_R));
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public K_4074_S J_1907_R() {
        return this.J_1907_R;
    }

    public c_1514_x R_4764_Y() {
        return this.n_1700_B;
    }
}


