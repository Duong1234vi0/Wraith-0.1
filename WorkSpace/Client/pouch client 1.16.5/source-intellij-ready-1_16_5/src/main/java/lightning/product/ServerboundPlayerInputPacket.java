/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class ServerboundPlayerInputPacket
implements Packet<ServerGamePacketListener> {
    private float n_1700_B;
    private float J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;

    public ServerboundPlayerInputPacket() {
    }

    public ServerboundPlayerInputPacket(float strafeSpeedIn, float forwardSpeedIn, boolean jumpingIn, boolean sneakingIn) {
        this.n_1700_B = strafeSpeedIn;
        this.J_1907_R = forwardSpeedIn;
        this.R_4764_Y = jumpingIn;
        this.G_564_y = sneakingIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readFloat();
        this.J_1907_R = buf.readFloat();
        byte b0 = buf.readByte();
        this.R_4764_Y = (b0 & 1) > 0;
        this.G_564_y = (b0 & 2) > 0;
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeFloat(this.n_1700_B);
        buf.writeFloat(this.J_1907_R);
        byte b0 = 0;
        if (this.R_4764_Y) {
            b0 = (byte)(b0 | 1);
        }
        if (this.G_564_y) {
            b0 = (byte)(b0 | 2);
        }
        buf.writeByte(b0);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public float J_1907_R() {
        return this.n_1700_B;
    }

    public float R_4764_Y() {
        return this.J_1907_R;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }

    public boolean P_1922_E() {
        return this.G_564_y;
    }
}


