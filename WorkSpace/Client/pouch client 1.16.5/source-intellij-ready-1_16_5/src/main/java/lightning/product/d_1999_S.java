/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.ClientIntentionPacket;
import lightning.product.SharedConstants;
import lightning.product.L_4713_a;
import lightning.product.U_2871_b;
import lightning.product.V_173_d;
import lightning.product.c_1633_k;
import lightning.product.d_4952_K;
import lightning.product.handshakeServerHandshakePacketListener;
import lightning.product.o_1314_v;
import lightning.product.t_1786_h;
import lightning.product.x_282_a;
import net.minecraft.server.G_564_y;

public class d_1999_S
implements handshakeServerHandshakePacketListener {
    private static final x_282_a n_1700_B = new U_2871_b("Ignoring status request");
    private final G_564_y J_1907_R;
    private final c_1633_k R_4764_Y;

    public d_1999_S(G_564_y serverIn, c_1633_k netManager) {
        this.J_1907_R = serverIn;
        this.R_4764_Y = netManager;
    }

    @Override
    public void n_1700_B(ClientIntentionPacket packetIn) {
        switch (packetIn.J_1907_R()) {
            case G_564_y: {
                this.R_4764_Y.n_1700_B(d_4952_K.G_564_y);
                if (packetIn.R_4764_Y() != SharedConstants.n_1700_B().getProtocolVersion()) {
                    F_2904_S itextcomponent = packetIn.R_4764_Y() < 754 ? new F_2904_S("multiplayer.disconnect.outdated_client", SharedConstants.n_1700_B().getName()) : new F_2904_S("multiplayer.disconnect.incompatible", SharedConstants.n_1700_B().getName());
                    this.R_4764_Y.n_1700_B(new V_173_d(itextcomponent));
                    this.R_4764_Y.n_1700_B(itextcomponent);
                    break;
                }
                this.R_4764_Y.n_1700_B(new o_1314_v(this.J_1907_R, this.R_4764_Y));
                break;
            }
            case R_4764_Y: {
                if (this.J_1907_R.h_4320_q()) {
                    this.R_4764_Y.n_1700_B(d_4952_K.R_4764_Y);
                    this.R_4764_Y.n_1700_B(new L_4713_a(this.J_1907_R, this.R_4764_Y));
                    break;
                }
                this.R_4764_Y.n_1700_B(n_1700_B);
                break;
            }
            default: {
                throw new UnsupportedOperationException("Invalid intention " + String.valueOf((Object)packetIn.J_1907_R()));
            }
        }
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
}


