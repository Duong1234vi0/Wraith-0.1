/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.shorts.ShortIterator
 *  it.unimi.dsi.fastutil.shorts.ShortSet
 */
package lightning.product;

import it.unimi.dsi.fastutil.shorts.ShortIterator;
import it.unimi.dsi.fastutil.shorts.ShortSet;
import java.io.IOException;
import java.util.function.BiConsumer;
import lightning.product.K_4074_S;
import lightning.product.P_3550_Z;
import lightning.product.T_2915_h;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundSectionBlocksUpdatePacket
implements Packet<ClientGamePacketListener> {
    private SectionPos n_1700_B;
    private short[] J_1907_R;
    private K_4074_S[] R_4764_Y;
    private boolean G_564_y;

    public ClientboundSectionBlocksUpdatePacket() {
    }

    public ClientboundSectionBlocksUpdatePacket(SectionPos p_i242085_1_, ShortSet p_i242085_2_, P_3550_Z p_i242085_3_, boolean p_i242085_4_) {
        this.n_1700_B = p_i242085_1_;
        this.G_564_y = p_i242085_4_;
        this.n_1700_B(p_i242085_2_.size());
        int i = 0;
        ShortIterator shortIterator = p_i242085_2_.iterator();
        while (shortIterator.hasNext()) {
            short short1;
            this.J_1907_R[i] = short1 = ((Short)shortIterator.next()).shortValue();
            this.R_4764_Y[i] = p_i242085_3_.n_1700_B(SectionPos.n_1700_B(short1), SectionPos.J_1907_R(short1), SectionPos.R_4764_Y(short1));
            ++i;
        }
    }

    @Override
    private void n_1700_B(int p_244309_1_) {
        this.J_1907_R = new short[p_244309_1_];
        this.R_4764_Y = new K_4074_S[p_244309_1_];
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = SectionPos.n_1700_B(buf.readLong());
        this.G_564_y = buf.readBoolean();
        int i = buf.u_1723_Y();
        this.n_1700_B(i);
        for (int j = 0; j < this.J_1907_R.length; ++j) {
            long k = buf.v_4262_N();
            this.J_1907_R[j] = (short)(k & 0xFFFL);
            this.R_4764_Y[j] = T_2915_h.t_4043_B.n_1700_B((int)(k >>> 12));
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeLong(this.n_1700_B.P_4830_p());
        buf.writeBoolean(this.G_564_y);
        buf.G_564_y(this.J_1907_R.length);
        for (int i = 0; i < this.J_1907_R.length; ++i) {
            buf.n_1700_B(T_2915_h.s_956_w(this.R_4764_Y[i]) << 12 | this.J_1907_R[i]);
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(BiConsumer<c_1514_x, K_4074_S> p_244310_1_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i = 0; i < this.J_1907_R.length; ++i) {
            short short1 = this.J_1907_R[i];
            blockpos$mutable.n_1700_B(this.n_1700_B.G_564_y(short1), this.n_1700_B.P_1922_E(short1), this.n_1700_B.u_1723_Y(short1));
            p_244310_1_.accept(blockpos$mutable, this.R_4764_Y[i]);
        }
    }

    public boolean J_1907_R() {
        return this.G_564_y;
    }
}


