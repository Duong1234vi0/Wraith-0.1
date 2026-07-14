/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ParticleOptions;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.ParticleType;

public class ClientboundLevelParticlesPacket
implements Packet<ClientGamePacketListener> {
    private double n_1700_B;
    private double J_1907_R;
    private double R_4764_Y;
    private float G_564_y;
    private float P_1922_E;
    private float u_1723_Y;
    private float v_4262_N;
    private int w_1484_f;
    private boolean t_148_a;
    private ParticleOptions s_956_w;

    public ClientboundLevelParticlesPacket() {
    }

    public <T extends ParticleOptions> ClientboundLevelParticlesPacket(T p_i229960_1_, boolean p_i229960_2_, double p_i229960_3_, double p_i229960_5_, double p_i229960_7_, float p_i229960_9_, float p_i229960_10_, float p_i229960_11_, float p_i229960_12_, int p_i229960_13_) {
        this.s_956_w = p_i229960_1_;
        this.t_148_a = p_i229960_2_;
        this.n_1700_B = p_i229960_3_;
        this.J_1907_R = p_i229960_5_;
        this.R_4764_Y = p_i229960_7_;
        this.G_564_y = p_i229960_9_;
        this.P_1922_E = p_i229960_10_;
        this.u_1723_Y = p_i229960_11_;
        this.v_4262_N = p_i229960_12_;
        this.w_1484_f = p_i229960_13_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        ParticleType particletype = (ParticleType)V_3137_a.g_164_R.n_1700_B(buf.readInt());
        if (particletype == null) {
            particletype = ParticleTypes.R_4764_Y;
        }
        this.t_148_a = buf.readBoolean();
        this.n_1700_B = buf.readDouble();
        this.J_1907_R = buf.readDouble();
        this.R_4764_Y = buf.readDouble();
        this.G_564_y = buf.readFloat();
        this.P_1922_E = buf.readFloat();
        this.u_1723_Y = buf.readFloat();
        this.v_4262_N = buf.readFloat();
        this.w_1484_f = buf.readInt();
        this.s_956_w = this.n_1700_B(buf, particletype);
    }

    private <T extends ParticleOptions> T n_1700_B(b_2585_i p_199855_1_, ParticleType<T> p_199855_2_) {
        return p_199855_2_.u_1723_Y().J_1907_R(p_199855_2_, p_199855_1_);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeInt(V_3137_a.g_164_R.n_1700_B(this.s_956_w.G_564_y()));
        buf.writeBoolean(this.t_148_a);
        buf.writeDouble(this.n_1700_B);
        buf.writeDouble(this.J_1907_R);
        buf.writeDouble(this.R_4764_Y);
        buf.writeFloat(this.G_564_y);
        buf.writeFloat(this.P_1922_E);
        buf.writeFloat(this.u_1723_Y);
        buf.writeFloat(this.v_4262_N);
        buf.writeInt(this.w_1484_f);
        this.s_956_w.n_1700_B(buf);
    }

    public boolean J_1907_R() {
        return this.t_148_a;
    }

    public double R_4764_Y() {
        return this.n_1700_B;
    }

    public double G_564_y() {
        return this.J_1907_R;
    }

    public double P_1922_E() {
        return this.R_4764_Y;
    }

    public float u_1723_Y() {
        return this.G_564_y;
    }

    public float v_4262_N() {
        return this.P_1922_E;
    }

    public float w_1484_f() {
        return this.u_1723_Y;
    }

    public float t_148_a() {
        return this.v_4262_N;
    }

    public int s_956_w() {
        return this.w_1484_f;
    }

    public ParticleOptions u_2550_I() {
        return this.s_956_w;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }
}


