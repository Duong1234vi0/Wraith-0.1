/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;
import lightning.product.Objective;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundSetDisplayObjectivePacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private String J_1907_R;

    public ClientboundSetDisplayObjectivePacket() {
    }

    public ClientboundSetDisplayObjectivePacket(int positionIn, @Nullable Objective objective) {
        this.n_1700_B = positionIn;
        this.J_1907_R = objective == null ? "" : objective.J_1907_R();
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.readByte();
        this.J_1907_R = buf.P_1922_E(16);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeByte(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    @Nullable
    public String R_4764_Y() {
        return Objects.equals(this.J_1907_R, "") ? null : this.J_1907_R;
    }
}


