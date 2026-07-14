/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.MenuType;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.x_282_a;

public class ClientboundOpenScreenPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;
    private x_282_a R_4764_Y;

    public ClientboundOpenScreenPacket() {
    }

    public ClientboundOpenScreenPacket(int windowIdIn, MenuType<?> menuIdIn, x_282_a titleIn) {
        this.n_1700_B = windowIdIn;
        this.J_1907_R = V_3137_a.T_3594_S.n_1700_B(menuIdIn);
        this.R_4764_Y = titleIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        this.J_1907_R = buf.u_1723_Y();
        this.R_4764_Y = buf.P_1922_E();
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.G_564_y(this.J_1907_R);
        buf.n_1700_B(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    @Nullable
    public MenuType<?> R_4764_Y() {
        return (MenuType)V_3137_a.T_3594_S.n_1700_B(this.J_1907_R);
    }

    public x_282_a G_564_y() {
        return this.R_4764_Y;
    }
}


