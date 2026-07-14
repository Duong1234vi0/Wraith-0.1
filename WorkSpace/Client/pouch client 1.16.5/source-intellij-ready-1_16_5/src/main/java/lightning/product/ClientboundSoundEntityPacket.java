/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.Validate
 */
package lightning.product;

import java.io.IOException;
import lightning.product.D_38_f;
import lightning.product.N_4263_v;
import lightning.product.V_3137_a;
import lightning.product.SoundEvent;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import org.apache.commons.lang3.Validate;

public class ClientboundSoundEntityPacket
implements Packet<ClientGamePacketListener> {
    private SoundEvent n_1700_B;
    private D_38_f J_1907_R;
    private int R_4764_Y;
    private float G_564_y;
    private float P_1922_E;

    public ClientboundSoundEntityPacket() {
    }

    public ClientboundSoundEntityPacket(SoundEvent p_i50763_1_, D_38_f p_i50763_2_, N_4263_v p_i50763_3_, float p_i50763_4_, float p_i50763_5_) {
        Validate.notNull((Object)p_i50763_1_, (String)"sound", (Object[])new Object[0]);
        this.n_1700_B = p_i50763_1_;
        this.J_1907_R = p_i50763_2_;
        this.R_4764_Y = p_i50763_3_.j_276_v();
        this.G_564_y = p_i50763_4_;
        this.P_1922_E = p_i50763_5_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = (SoundEvent)V_3137_a.d_2461_k.n_1700_B(buf.u_1723_Y());
        this.J_1907_R = buf.n_1700_B(D_38_f.class);
        this.R_4764_Y = buf.u_1723_Y();
        this.G_564_y = buf.readFloat();
        this.P_1922_E = buf.readFloat();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(V_3137_a.d_2461_k.n_1700_B(this.n_1700_B));
        buf.n_1700_B(this.J_1907_R);
        buf.G_564_y(this.R_4764_Y);
        buf.writeFloat(this.G_564_y);
        buf.writeFloat(this.P_1922_E);
    }

    public SoundEvent J_1907_R() {
        return this.n_1700_B;
    }

    public D_38_f R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }

    public float P_1922_E() {
        return this.G_564_y;
    }

    public float u_1723_Y() {
        return this.P_1922_E;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }
}


