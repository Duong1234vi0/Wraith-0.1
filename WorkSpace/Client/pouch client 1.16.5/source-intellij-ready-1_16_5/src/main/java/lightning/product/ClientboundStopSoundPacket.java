/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.D_38_f;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundStopSoundPacket
implements Packet<ClientGamePacketListener> {
    private g_2336_b n_1700_B;
    private D_38_f J_1907_R;

    public ClientboundStopSoundPacket() {
    }

    public ClientboundStopSoundPacket(@Nullable g_2336_b p_i47929_1_, @Nullable D_38_f p_i47929_2_) {
        this.n_1700_B = p_i47929_1_;
        this.J_1907_R = p_i47929_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        byte i = buf.readByte();
        if ((i & 1) > 0) {
            this.J_1907_R = buf.n_1700_B(D_38_f.class);
        }
        if ((i & 2) > 0) {
            this.n_1700_B = buf.P_4830_p();
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        if (this.J_1907_R != null) {
            if (this.n_1700_B != null) {
                buf.writeByte(3);
                buf.n_1700_B(this.J_1907_R);
                buf.n_1700_B(this.n_1700_B);
            } else {
                buf.writeByte(1);
                buf.n_1700_B(this.J_1907_R);
            }
        } else if (this.n_1700_B != null) {
            buf.writeByte(2);
            buf.n_1700_B(this.n_1700_B);
        } else {
            buf.writeByte(0);
        }
    }

    @Nullable
    public g_2336_b J_1907_R() {
        return this.n_1700_B;
    }

    @Nullable
    public D_38_f R_4764_Y() {
        return this.J_1907_R;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }
}


