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
import lightning.product.b_2585_i;
import lightning.product.ServerScoreboard;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundSetScorePacket
implements Packet<ClientGamePacketListener> {
    private String n_1700_B = "";
    @Nullable
    private String J_1907_R;
    private int R_4764_Y;
    private ServerScoreboard.n_1700_B G_564_y;

    public ClientboundSetScorePacket() {
    }

    public ClientboundSetScorePacket(ServerScoreboard.n_1700_B p_i47930_1_, @Nullable String p_i47930_2_, String p_i47930_3_, int p_i47930_4_) {
        if (p_i47930_1_ != ServerScoreboard.n_1700_B.J_1907_R && p_i47930_2_ == null) {
            throw new IllegalArgumentException("Need an objective name");
        }
        this.n_1700_B = p_i47930_3_;
        this.J_1907_R = p_i47930_2_;
        this.R_4764_Y = p_i47930_4_;
        this.G_564_y = p_i47930_1_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.P_1922_E(40);
        this.G_564_y = buf.n_1700_B(ServerScoreboard.n_1700_B.class);
        String s = buf.P_1922_E(16);
        String string = this.J_1907_R = Objects.equals(s, "") ? null : s;
        if (this.G_564_y != ServerScoreboard.n_1700_B.J_1907_R) {
            this.R_4764_Y = buf.u_1723_Y();
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.n_1700_B(this.G_564_y);
        buf.n_1700_B(this.J_1907_R == null ? "" : this.J_1907_R);
        if (this.G_564_y != ServerScoreboard.n_1700_B.J_1907_R) {
            buf.G_564_y(this.R_4764_Y);
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public String J_1907_R() {
        return this.n_1700_B;
    }

    @Nullable
    public String R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }

    public ServerScoreboard.n_1700_B P_1922_E() {
        return this.G_564_y;
    }
}


