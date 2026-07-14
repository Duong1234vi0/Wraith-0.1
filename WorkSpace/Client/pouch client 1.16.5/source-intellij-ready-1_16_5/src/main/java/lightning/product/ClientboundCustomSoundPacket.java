/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.D_38_f;
import lightning.product.b_2585_i;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundCustomSoundPacket
implements Packet<ClientGamePacketListener> {
    private g_2336_b n_1700_B;
    private D_38_f J_1907_R;
    private int R_4764_Y;
    private int G_564_y = Integer.MAX_VALUE;
    private int P_1922_E;
    private float u_1723_Y;
    private float v_4262_N;

    public ClientboundCustomSoundPacket() {
    }

    public ClientboundCustomSoundPacket(g_2336_b p_i47939_1_, D_38_f p_i47939_2_, e_2866_D p_i47939_3_, float p_i47939_4_, float p_i47939_5_) {
        this.n_1700_B = p_i47939_1_;
        this.J_1907_R = p_i47939_2_;
        this.R_4764_Y = (int)(p_i47939_3_.J_1907_R * 8.0);
        this.G_564_y = (int)(p_i47939_3_.R_4764_Y * 8.0);
        this.P_1922_E = (int)(p_i47939_3_.G_564_y * 8.0);
        this.u_1723_Y = p_i47939_4_;
        this.v_4262_N = p_i47939_5_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_4830_p();
        this.J_1907_R = buf.n_1700_B(D_38_f.class);
        this.R_4764_Y = buf.readInt();
        this.G_564_y = buf.readInt();
        this.P_1922_E = buf.readInt();
        this.u_1723_Y = buf.readFloat();
        this.v_4262_N = buf.readFloat();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.writeInt(this.R_4764_Y);
        buf.writeInt(this.G_564_y);
        buf.writeInt(this.P_1922_E);
        buf.writeFloat(this.u_1723_Y);
        buf.writeFloat(this.v_4262_N);
    }

    public g_2336_b J_1907_R() {
        return this.n_1700_B;
    }

    public D_38_f R_4764_Y() {
        return this.J_1907_R;
    }

    public double G_564_y() {
        return (float)this.R_4764_Y / 8.0f;
    }

    public double P_1922_E() {
        return (float)this.G_564_y / 8.0f;
    }

    public double u_1723_Y() {
        return (float)this.P_1922_E / 8.0f;
    }

    public float v_4262_N() {
        return this.u_1723_Y;
    }

    public float w_1484_f() {
        return this.v_4262_N;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }
}


