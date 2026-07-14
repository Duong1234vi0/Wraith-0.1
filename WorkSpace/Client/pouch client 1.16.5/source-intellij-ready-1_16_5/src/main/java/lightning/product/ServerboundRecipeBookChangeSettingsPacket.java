/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.ServerGamePacketListener;
import lightning.product.I_3887_a;
import lightning.product.b_2585_i;
import lightning.product.Packet;

public class ServerboundRecipeBookChangeSettingsPacket
implements Packet<ServerGamePacketListener> {
    private I_3887_a n_1700_B;
    private boolean J_1907_R;
    private boolean R_4764_Y;

    public ServerboundRecipeBookChangeSettingsPacket() {
    }

    public ServerboundRecipeBookChangeSettingsPacket(I_3887_a p_i242088_1_, boolean p_i242088_2_, boolean p_i242088_3_) {
        this.n_1700_B = p_i242088_1_;
        this.J_1907_R = p_i242088_2_;
        this.R_4764_Y = p_i242088_3_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.n_1700_B(I_3887_a.class);
        this.J_1907_R = buf.readBoolean();
        this.R_4764_Y = buf.readBoolean();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        buf.writeBoolean(this.J_1907_R);
        buf.writeBoolean(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ServerGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public I_3887_a J_1907_R() {
        return this.n_1700_B;
    }

    public boolean R_4764_Y() {
        return this.J_1907_R;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }
}


