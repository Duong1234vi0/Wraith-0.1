/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundGameEventPacket
implements Packet<ClientGamePacketListener> {
    public static final n_1700_B n_1700_B = new n_1700_B(0);
    public static final n_1700_B J_1907_R = new n_1700_B(1);
    public static final n_1700_B R_4764_Y = new n_1700_B(2);
    public static final n_1700_B G_564_y = new n_1700_B(3);
    public static final n_1700_B P_1922_E = new n_1700_B(4);
    public static final n_1700_B u_1723_Y = new n_1700_B(5);
    public static final n_1700_B v_4262_N = new n_1700_B(6);
    public static final n_1700_B w_1484_f = new n_1700_B(7);
    public static final n_1700_B t_148_a = new n_1700_B(8);
    public static final n_1700_B s_956_w = new n_1700_B(9);
    public static final n_1700_B u_2550_I = new n_1700_B(10);
    public static final n_1700_B M_588_G = new n_1700_B(11);
    private n_1700_B P_4830_p;
    private float h_1847_R;

    public ClientboundGameEventPacket() {
    }

    public ClientboundGameEventPacket(n_1700_B p_i241263_1_, float p_i241263_2_) {
        this.P_4830_p = p_i241263_1_;
        this.h_1847_R = p_i241263_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.P_4830_p = (n_1700_B)lightning.product.ClientboundGameEventPacket$n_1700_B.J_1907_R.get((int)buf.readUnsignedByte());
        this.h_1847_R = buf.readFloat();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.P_4830_p.n_1700_B);
        buf.writeFloat(this.h_1847_R);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public n_1700_B J_1907_R() {
        return this.P_4830_p;
    }

    public float R_4764_Y() {
        return this.h_1847_R;
    }

    public static class n_1700_B {
        private static final Int2ObjectMap<n_1700_B> J_1907_R = new Int2ObjectOpenHashMap();
        public final int n_1700_B;

        public n_1700_B(int p_i241264_1_) {
            this.n_1700_B = p_i241264_1_;
            J_1907_R.put(p_i241264_1_, (Object)this);
        }
    }
}


