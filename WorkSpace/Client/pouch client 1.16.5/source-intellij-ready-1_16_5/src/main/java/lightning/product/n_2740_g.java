/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.protocol.version.ProtocolVersion
 */
package lightning.product;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import mods.viaversion.vialoadingbase.ViaLoadingBase;

public class n_2740_g
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private short J_1907_R;
    private boolean R_4764_Y;

    public n_2740_g() {
    }

    public n_2740_g(int windowIdIn, short actionNumberIn, boolean acceptedIn) {
        this.n_1700_B = windowIdIn;
        this.J_1907_R = actionNumberIn;
        this.R_4764_Y = acceptedIn;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        if (ViaLoadingBase.getInstance().getTargetVersion().newerThanOrEqualTo(ProtocolVersion.v1_17)) {
            this.n_1700_B = buf.readInt();
        } else {
            this.n_1700_B = buf.readUnsignedByte();
            this.J_1907_R = buf.readShort();
            this.R_4764_Y = buf.readBoolean();
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B);
        buf.writeShort(this.J_1907_R);
        buf.writeBoolean(this.R_4764_Y);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public short R_4764_Y() {
        return this.J_1907_R;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }
}


