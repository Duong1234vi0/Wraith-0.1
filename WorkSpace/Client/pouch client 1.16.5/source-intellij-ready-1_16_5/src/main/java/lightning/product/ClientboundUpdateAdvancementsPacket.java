/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import lightning.product.A_2629_w;
import lightning.product.C_3304_p;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundUpdateAdvancementsPacket
implements Packet<ClientGamePacketListener> {
    private boolean n_1700_B;
    private Map<g_2336_b, A_2629_w.n_1700_B> J_1907_R;
    private Set<g_2336_b> R_4764_Y;
    private Map<g_2336_b, C_3304_p> G_564_y;

    public ClientboundUpdateAdvancementsPacket() {
    }

    public ClientboundUpdateAdvancementsPacket(boolean p_i47519_1_, Collection<A_2629_w> p_i47519_2_, Set<g_2336_b> p_i47519_3_, Map<g_2336_b, C_3304_p> p_i47519_4_) {
        this.n_1700_B = p_i47519_1_;
        this.J_1907_R = Maps.newHashMap();
        for (A_2629_w advancement : p_i47519_2_) {
            this.J_1907_R.put(advancement.w_1484_f(), advancement.n_1700_B());
        }
        this.R_4764_Y = p_i47519_3_;
        this.G_564_y = Maps.newHashMap(p_i47519_4_);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readBoolean();
        this.J_1907_R = Maps.newHashMap();
        this.R_4764_Y = Sets.newLinkedHashSet();
        this.G_564_y = Maps.newHashMap();
        int i = buf.u_1723_Y();
        for (int j = 0; j < i; ++j) {
            g_2336_b resourcelocation = buf.P_4830_p();
            A_2629_w.n_1700_B advancement$builder = A_2629_w.n_1700_B.J_1907_R(buf);
            this.J_1907_R.put(resourcelocation, advancement$builder);
        }
        i = buf.u_1723_Y();
        for (int k = 0; k < i; ++k) {
            g_2336_b resourcelocation1 = buf.P_4830_p();
            this.R_4764_Y.add(resourcelocation1);
        }
        i = buf.u_1723_Y();
        for (int l = 0; l < i; ++l) {
            g_2336_b resourcelocation2 = buf.P_4830_p();
            this.G_564_y.put(resourcelocation2, C_3304_p.J_1907_R(buf));
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeBoolean(this.n_1700_B);
        buf.G_564_y(this.J_1907_R.size());
        for (Map.Entry<g_2336_b, A_2629_w.n_1700_B> entry : this.J_1907_R.entrySet()) {
            g_2336_b resourcelocation = entry.getKey();
            A_2629_w.n_1700_B advancement$builder = entry.getValue();
            buf.n_1700_B(resourcelocation);
            advancement$builder.n_1700_B(buf);
        }
        buf.G_564_y(this.R_4764_Y.size());
        for (g_2336_b g_2336_b2 : this.R_4764_Y) {
            buf.n_1700_B(g_2336_b2);
        }
        buf.G_564_y(this.G_564_y.size());
        for (Map.Entry entry : this.G_564_y.entrySet()) {
            buf.n_1700_B((g_2336_b)entry.getKey());
            ((C_3304_p)entry.getValue()).n_1700_B(buf);
        }
    }

    public Map<g_2336_b, A_2629_w.n_1700_B> J_1907_R() {
        return this.J_1907_R;
    }

    public Set<g_2336_b> R_4764_Y() {
        return this.R_4764_Y;
    }

    public Map<g_2336_b, C_3304_p> G_564_y() {
        return this.G_564_y;
    }

    public boolean P_1922_E() {
        return this.n_1700_B;
    }
}


