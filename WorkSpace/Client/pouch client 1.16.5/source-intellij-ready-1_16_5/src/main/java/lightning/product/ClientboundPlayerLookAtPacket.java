/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.EntityAnchorArgument;

public class ClientboundPlayerLookAtPacket
implements Packet<ClientGamePacketListener> {
    private double n_1700_B;
    private double J_1907_R;
    private double R_4764_Y;
    private int G_564_y;
    private EntityAnchorArgument.n_1700_B P_1922_E;
    private EntityAnchorArgument.n_1700_B u_1723_Y;
    private boolean v_4262_N;

    public ClientboundPlayerLookAtPacket() {
    }

    public ClientboundPlayerLookAtPacket(EntityAnchorArgument.n_1700_B p_i48589_1_, double p_i48589_2_, double p_i48589_4_, double p_i48589_6_) {
        this.P_1922_E = p_i48589_1_;
        this.n_1700_B = p_i48589_2_;
        this.J_1907_R = p_i48589_4_;
        this.R_4764_Y = p_i48589_6_;
    }

    public ClientboundPlayerLookAtPacket(EntityAnchorArgument.n_1700_B p_i48590_1_, N_4263_v p_i48590_2_, EntityAnchorArgument.n_1700_B p_i48590_3_) {
        this.P_1922_E = p_i48590_1_;
        this.G_564_y = p_i48590_2_.j_276_v();
        this.u_1723_Y = p_i48590_3_;
        e_2866_D vector3d = p_i48590_3_.n_1700_B(p_i48590_2_);
        this.n_1700_B = vector3d.J_1907_R;
        this.J_1907_R = vector3d.R_4764_Y;
        this.R_4764_Y = vector3d.G_564_y;
        this.v_4262_N = true;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.P_1922_E = buf.n_1700_B(EntityAnchorArgument.n_1700_B.class);
        this.n_1700_B = buf.readDouble();
        this.J_1907_R = buf.readDouble();
        this.R_4764_Y = buf.readDouble();
        if (buf.readBoolean()) {
            this.v_4262_N = true;
            this.G_564_y = buf.u_1723_Y();
            this.u_1723_Y = buf.n_1700_B(EntityAnchorArgument.n_1700_B.class);
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.P_1922_E);
        buf.writeDouble(this.n_1700_B);
        buf.writeDouble(this.J_1907_R);
        buf.writeDouble(this.R_4764_Y);
        buf.writeBoolean(this.v_4262_N);
        if (this.v_4262_N) {
            buf.G_564_y(this.G_564_y);
            buf.n_1700_B(this.u_1723_Y);
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public EntityAnchorArgument.n_1700_B J_1907_R() {
        return this.P_1922_E;
    }

    @Nullable
    public e_2866_D n_1700_B(b_4507_u p_200531_1_) {
        if (this.v_4262_N) {
            N_4263_v entity = p_200531_1_.J_1907_R(this.G_564_y);
            return entity == null ? new e_2866_D(this.n_1700_B, this.J_1907_R, this.R_4764_Y) : this.u_1723_Y.n_1700_B(entity);
        }
        return new e_2866_D(this.n_1700_B, this.J_1907_R, this.R_4764_Y);
    }
}


