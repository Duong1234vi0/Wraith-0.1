/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Set;
import lightning.product.I_14_v;
import lightning.product.V_3137_a;
import lightning.product.Z_3903_F;
import lightning.product.b_2585_i;
import lightning.product.b_4507_u;
import lightning.product.f_2392_k;
import lightning.product.ClientGamePacketListener;
import lightning.product.r_4097_j;
import lightning.product.Packet;

public class ClientboundLoginPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private long J_1907_R;
    private boolean R_4764_Y;
    private I_14_v G_564_y;
    private I_14_v P_1922_E;
    private Set<f_2392_k<b_4507_u>> u_1723_Y;
    private r_4097_j.J_1907_R v_4262_N;
    private Z_3903_F w_1484_f;
    private f_2392_k<b_4507_u> t_148_a;
    private int s_956_w;
    private int u_2550_I;
    private boolean M_588_G;
    private boolean P_4830_p;
    private boolean h_1847_R;
    private boolean Q_4569_t;

    public ClientboundLoginPacket() {
    }

    public ClientboundLoginPacket(int p_i242082_1_, I_14_v p_i242082_2_, I_14_v p_i242082_3_, long p_i242082_4_, boolean p_i242082_6_, Set<f_2392_k<b_4507_u>> p_i242082_7_, r_4097_j.J_1907_R p_i242082_8_, Z_3903_F p_i242082_9_, f_2392_k<b_4507_u> p_i242082_10_, int p_i242082_11_, int p_i242082_12_, boolean p_i242082_13_, boolean p_i242082_14_, boolean p_i242082_15_, boolean p_i242082_16_) {
        this.n_1700_B = p_i242082_1_;
        this.u_1723_Y = p_i242082_7_;
        this.v_4262_N = p_i242082_8_;
        this.w_1484_f = p_i242082_9_;
        this.t_148_a = p_i242082_10_;
        this.J_1907_R = p_i242082_4_;
        this.G_564_y = p_i242082_2_;
        this.P_1922_E = p_i242082_3_;
        this.s_956_w = p_i242082_11_;
        this.R_4764_Y = p_i242082_6_;
        this.u_2550_I = p_i242082_12_;
        this.M_588_G = p_i242082_13_;
        this.P_4830_p = p_i242082_14_;
        this.h_1847_R = p_i242082_15_;
        this.Q_4569_t = p_i242082_16_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readInt();
        this.R_4764_Y = buf.readBoolean();
        this.G_564_y = I_14_v.n_1700_B(buf.readByte());
        this.P_1922_E = I_14_v.n_1700_B(buf.readByte());
        int i = buf.u_1723_Y();
        this.u_1723_Y = Sets.newHashSet();
        for (int j = 0; j < i; ++j) {
            this.u_1723_Y.add(f_2392_k.n_1700_B(V_3137_a.z_1737_N, buf.P_4830_p()));
        }
        this.v_4262_N = buf.n_1700_B(r_4097_j.J_1907_R.n_1700_B);
        this.w_1484_f = buf.n_1700_B(Z_3903_F.h_1847_R).get();
        this.t_148_a = f_2392_k.n_1700_B(V_3137_a.z_1737_N, buf.P_4830_p());
        this.J_1907_R = buf.readLong();
        this.s_956_w = buf.u_1723_Y();
        this.u_2550_I = buf.u_1723_Y();
        this.M_588_G = buf.readBoolean();
        this.P_4830_p = buf.readBoolean();
        this.h_1847_R = buf.readBoolean();
        this.Q_4569_t = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeInt(this.n_1700_B);
        buf.writeBoolean(this.R_4764_Y);
        buf.writeByte(this.G_564_y.n_1700_B());
        buf.writeByte(this.P_1922_E.n_1700_B());
        buf.G_564_y(this.u_1723_Y.size());
        for (f_2392_k<b_4507_u> registrykey : this.u_1723_Y) {
            buf.n_1700_B(registrykey.n_1700_B());
        }
        buf.n_1700_B(r_4097_j.J_1907_R.n_1700_B, this.v_4262_N);
        buf.n_1700_B(Z_3903_F.h_1847_R, () -> this.w_1484_f);
        buf.n_1700_B(this.t_148_a.n_1700_B());
        buf.writeLong(this.J_1907_R);
        buf.G_564_y(this.s_956_w);
        buf.G_564_y(this.u_2550_I);
        buf.writeBoolean(this.M_588_G);
        buf.writeBoolean(this.P_4830_p);
        buf.writeBoolean(this.h_1847_R);
        buf.writeBoolean(this.Q_4569_t);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public long R_4764_Y() {
        return this.J_1907_R;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }

    public I_14_v P_1922_E() {
        return this.G_564_y;
    }

    public I_14_v u_1723_Y() {
        return this.P_1922_E;
    }

    public Set<f_2392_k<b_4507_u>> v_4262_N() {
        return this.u_1723_Y;
    }

    public r_4097_j w_1484_f() {
        return this.v_4262_N;
    }

    public Z_3903_F t_148_a() {
        return this.w_1484_f;
    }

    public f_2392_k<b_4507_u> s_956_w() {
        return this.t_148_a;
    }

    public int u_2550_I() {
        return this.u_2550_I;
    }

    public boolean M_588_G() {
        return this.M_588_G;
    }

    public boolean P_4830_p() {
        return this.P_4830_p;
    }

    public boolean h_1847_R() {
        return this.h_1847_R;
    }

    public boolean Q_4569_t() {
        return this.Q_4569_t;
    }
}


