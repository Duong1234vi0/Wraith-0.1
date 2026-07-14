/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.Validate
 */
package lightning.product;

import java.io.IOException;
import lightning.product.D_38_f;
import lightning.product.V_3137_a;
import lightning.product.SoundEvent;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import org.apache.commons.lang3.Validate;

public class ClientboundSoundPacket
implements Packet<ClientGamePacketListener> {
    private SoundEvent n_1700_B;
    private D_38_f J_1907_R;
    private int R_4764_Y;
    private int G_564_y;
    private int P_1922_E;
    private float u_1723_Y;
    private float v_4262_N;

    public ClientboundSoundPacket() {
    }

    public ClientboundSoundPacket(SoundEvent soundIn, D_38_f categoryIn, double xIn, double yIn, double zIn, float volumeIn, float pitchIn) {
        Validate.notNull((Object)soundIn, (String)"sound", (Object[])new Object[0]);
        this.n_1700_B = soundIn;
        this.J_1907_R = categoryIn;
        this.R_4764_Y = (int)(xIn * 8.0);
        this.G_564_y = (int)(yIn * 8.0);
        this.P_1922_E = (int)(zIn * 8.0);
        this.u_1723_Y = volumeIn;
        this.v_4262_N = pitchIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = (SoundEvent)V_3137_a.d_2461_k.n_1700_B(buf.u_1723_Y());
        this.J_1907_R = buf.n_1700_B(D_38_f.class);
        this.R_4764_Y = buf.readInt();
        this.G_564_y = buf.readInt();
        this.P_1922_E = buf.readInt();
        this.u_1723_Y = buf.readFloat();
        this.v_4262_N = buf.readFloat();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(V_3137_a.d_2461_k.n_1700_B(this.n_1700_B));
        buf.n_1700_B(this.J_1907_R);
        buf.writeInt(this.R_4764_Y);
        buf.writeInt(this.G_564_y);
        buf.writeInt(this.P_1922_E);
        buf.writeFloat(this.u_1723_Y);
        buf.writeFloat(this.v_4262_N);
    }

    public SoundEvent J_1907_R() {
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


