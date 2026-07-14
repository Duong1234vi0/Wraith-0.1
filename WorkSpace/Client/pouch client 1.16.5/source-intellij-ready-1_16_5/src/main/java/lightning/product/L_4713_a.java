/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.I_2310_w;
import lightning.product.K_4053_T;
import lightning.product.ServerboundStatusRequestPacket;
import lightning.product.ClientboundPongResponsePacket;
import lightning.product.c_1633_k;
import lightning.product.ClientboundStatusResponsePacket;
import lightning.product.t_1786_h;
import lightning.product.x_282_a;
import net.minecraft.server.G_564_y;

public class L_4713_a
implements I_2310_w {
    private static final x_282_a n_1700_B = new F_2904_S("multiplayer.status.request_handled");
    private final G_564_y J_1907_R;
    private final c_1633_k R_4764_Y;
    private boolean G_564_y;

    public L_4713_a(G_564_y serverIn, c_1633_k netManager) {
        this.J_1907_R = serverIn;
        this.R_4764_Y = netManager;
    }

    @Override
    public void onDisconnect(x_282_a reason) {
    }

    @Override
    public c_1633_k getNetworkManager() {
        return this.R_4764_Y;
    }

    @Override
    public t_1786_h getBotNetwork() {
        return null;
    }

    @Override
    public void n_1700_B(ServerboundStatusRequestPacket packetIn) {
        if (this.G_564_y) {
            this.R_4764_Y.n_1700_B(n_1700_B);
        } else {
            this.G_564_y = true;
            this.R_4764_Y.n_1700_B(new ClientboundStatusResponsePacket(this.J_1907_R.U_1241_n()));
        }
    }

    @Override
    public void n_1700_B(K_4053_T packetIn) {
        this.R_4764_Y.n_1700_B(new ClientboundPongResponsePacket(packetIn.J_1907_R()));
        this.R_4764_Y.n_1700_B(n_1700_B);
    }
}


