/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.protocol.version.ProtocolVersion
 */
package lightning.product;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.b_2585_i;
import lightning.product.Packet;
import mods.viaversion.vialoadingbase.ViaLoadingBase;

public class V_674_I
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;
    private short J_1907_R;
    private boolean R_4764_Y;

    public V_674_I() {
    }

    public V_674_I(int windowIdIn, short uidIn, boolean acceptedIn) {
        this.n_1700_B = windowIdIn;
        this.J_1907_R = uidIn;
        this.R_4764_Y = acceptedIn;
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readByte();
        this.J_1907_R = buf.readShort();
        this.R_4764_Y = buf.readByte() != 0;
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        if (ViaLoadingBase.getInstance().getTargetVersion().newerThanOrEqualTo(ProtocolVersion.v1_17)) {
            buf.writeInt(this.n_1700_B);
        } else {
            buf.writeByte(this.n_1700_B);
            buf.writeShort(this.J_1907_R);
            buf.writeByte(this.R_4764_Y ? 1 : 0);
        }
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public short R_4764_Y() {
        return this.J_1907_R;
    }
}


