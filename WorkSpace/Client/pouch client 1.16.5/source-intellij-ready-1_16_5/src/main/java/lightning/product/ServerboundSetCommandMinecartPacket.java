/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.ServerGamePacketListener;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.b_4507_u;
import lightning.product.d_742_e;
import lightning.product.Packet;
import lightning.product.z_2326_J;

public class ServerboundSetCommandMinecartPacket
implements Packet<ServerGamePacketListener> {
    private int n_1700_B;
    private String J_1907_R;
    private boolean R_4764_Y;

    public ServerboundSetCommandMinecartPacket() {
    }

    public ServerboundSetCommandMinecartPacket(int entityIdIn, String commandIn, boolean trackOutputIn) {
        this.n_1700_B = entityIdIn;
        this.J_1907_R = commandIn;
        this.R_4764_Y = trackOutputIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.P_1922_E(Short.MAX_VALUE);
        this.R_4764_Y = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.writeBoolean(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Nullable
    public d_742_e n_1700_B(b_4507_u worldIn) {
        N_4263_v entity = worldIn.J_1907_R(this.n_1700_B);
        return entity instanceof z_2326_J ? ((z_2326_J)entity).Y_259_p() : null;
    }

    public String J_1907_R() {
        return this.J_1907_R;
    }

    public boolean R_4764_Y() {
        return this.R_4764_Y;
    }
}


