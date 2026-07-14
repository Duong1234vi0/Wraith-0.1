/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.TypeAdapterFactory
 */
package lightning.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import java.io.IOException;
import lightning.product.ServerStatus;
import lightning.product.M_2450_l;
import lightning.product.N_1112_I;
import lightning.product.Z_1567_W;
import lightning.product.b_2585_i;
import lightning.product.i_4431_W;
import lightning.product.Packet;
import lightning.product.x_282_a;

public class ClientboundStatusResponsePacket
implements Packet<M_2450_l> {
    private static final Gson n_1700_B = new GsonBuilder().registerTypeAdapter(ServerStatus.R_4764_Y.class, (Object)new ServerStatus.R_4764_Y.n_1700_B()).registerTypeAdapter(ServerStatus.n_1700_B.class, (Object)new ServerStatus.n_1700_B.n_1700_B()).registerTypeAdapter(ServerStatus.class, (Object)new ServerStatus.J_1907_R()).registerTypeHierarchyAdapter(x_282_a.class, (Object)new x_282_a.n_1700_B()).registerTypeHierarchyAdapter(Z_1567_W.class, (Object)new Z_1567_W.n_1700_B()).registerTypeAdapterFactory((TypeAdapterFactory)new N_1112_I()).create();
    private ServerStatus J_1907_R;

    public ClientboundStatusResponsePacket() {
    }

    public ClientboundStatusResponsePacket(ServerStatus responseIn) {
        this.J_1907_R = responseIn;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.J_1907_R = i_4431_W.n_1700_B(n_1700_B, buf.P_1922_E(Short.MAX_VALUE), ServerStatus.class);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(n_1700_B.toJson((Object)this.J_1907_R));
    }

    @Override
    public void n_1700_B(M_2450_l handler) {
        handler.handleServerInfo(this);
    }

    public ServerStatus J_1907_R() {
        return this.J_1907_R;
    }
}


