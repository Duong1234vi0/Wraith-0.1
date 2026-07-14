/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.IOException;
import java.util.Map;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.o_98_P;
import lightning.product.ClientGamePacketListener;
import lightning.product.q_3277_O;
import lightning.product.Packet;

public class ClientboundAwardStatsPacket
implements Packet<ClientGamePacketListener> {
    private Object2IntMap<o_98_P<?>> n_1700_B;

    public ClientboundAwardStatsPacket() {
    }

    public ClientboundAwardStatsPacket(Object2IntMap<o_98_P<?>> p_i47942_1_) {
        this.n_1700_B = p_i47942_1_;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        int i = buf.u_1723_Y();
        this.n_1700_B = new Object2IntOpenHashMap(i);
        for (int j = 0; j < i; ++j) {
            this.n_1700_B((q_3277_O)V_3137_a.z_1333_t.n_1700_B(buf.u_1723_Y()), buf);
        }
    }

    private <T> void n_1700_B(q_3277_O<T> p_197684_1_, b_2585_i p_197684_2_) {
        int i = p_197684_2_.u_1723_Y();
        int j = p_197684_2_.u_1723_Y();
        this.n_1700_B.put(p_197684_1_.J_1907_R(p_197684_1_.n_1700_B().n_1700_B(i)), j);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B.size());
        for (Object2IntMap.Entry entry : this.n_1700_B.object2IntEntrySet()) {
            o_98_P stat = (o_98_P)entry.getKey();
            buf.G_564_y(V_3137_a.z_1333_t.n_1700_B(stat.G_564_y()));
            buf.G_564_y(this.n_1700_B(stat));
            buf.G_564_y(entry.getIntValue());
        }
    }

    private <T> int n_1700_B(o_98_P<T> p_197683_1_) {
        return p_197683_1_.G_564_y().n_1700_B().n_1700_B(p_197683_1_.P_1922_E());
    }

    public Map<o_98_P<?>, Integer> J_1907_R() {
        return this.n_1700_B;
    }
}


