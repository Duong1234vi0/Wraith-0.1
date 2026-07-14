/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.I_14_v;
import lightning.product.V_3137_a;
import lightning.product.Z_3903_F;
import lightning.product.b_2585_i;
import lightning.product.b_4507_u;
import lightning.product.f_2392_k;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundRespawnPacket
implements Packet<ClientGamePacketListener> {
    private Z_3903_F n_1700_B;
    private f_2392_k<b_4507_u> J_1907_R;
    private long R_4764_Y;
    private I_14_v G_564_y;
    private I_14_v P_1922_E;
    private boolean u_1723_Y;
    private boolean v_4262_N;
    private boolean w_1484_f;

    public ClientboundRespawnPacket() {
    }

    public ClientboundRespawnPacket(Z_3903_F p_i242084_1_, f_2392_k<b_4507_u> p_i242084_2_, long p_i242084_3_, I_14_v p_i242084_5_, I_14_v p_i242084_6_, boolean p_i242084_7_, boolean p_i242084_8_, boolean p_i242084_9_) {
        this.n_1700_B = p_i242084_1_;
        this.J_1907_R = p_i242084_2_;
        this.R_4764_Y = p_i242084_3_;
        this.G_564_y = p_i242084_5_;
        this.P_1922_E = p_i242084_6_;
        this.u_1723_Y = p_i242084_7_;
        this.v_4262_N = p_i242084_8_;
        this.w_1484_f = p_i242084_9_;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.n_1700_B(Z_3903_F.h_1847_R).get();
        this.J_1907_R = f_2392_k.n_1700_B(V_3137_a.z_1737_N, buf.P_4830_p());
        this.R_4764_Y = buf.readLong();
        this.G_564_y = I_14_v.n_1700_B(buf.readUnsignedByte());
        this.P_1922_E = I_14_v.n_1700_B(buf.readUnsignedByte());
        this.u_1723_Y = buf.readBoolean();
        this.v_4262_N = buf.readBoolean();
        this.w_1484_f = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(Z_3903_F.h_1847_R, () -> this.n_1700_B);
        buf.n_1700_B(this.J_1907_R.n_1700_B());
        buf.writeLong(this.R_4764_Y);
        buf.writeByte(this.G_564_y.n_1700_B());
        buf.writeByte(this.P_1922_E.n_1700_B());
        buf.writeBoolean(this.u_1723_Y);
        buf.writeBoolean(this.v_4262_N);
        buf.writeBoolean(this.w_1484_f);
    }

    public Z_3903_F J_1907_R() {
        return this.n_1700_B;
    }

    public f_2392_k<b_4507_u> R_4764_Y() {
        return this.J_1907_R;
    }

    public long G_564_y() {
        return this.R_4764_Y;
    }

    public I_14_v P_1922_E() {
        return this.G_564_y;
    }

    public I_14_v u_1723_Y() {
        return this.P_1922_E;
    }

    public boolean v_4262_N() {
        return this.u_1723_Y;
    }

    public boolean w_1484_f() {
        return this.v_4262_N;
    }

    public boolean t_148_a() {
        return this.w_1484_f;
    }
}


