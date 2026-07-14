/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundCustomPayloadPacket
implements Packet<ClientGamePacketListener> {
    public static final g_2336_b n_1700_B = new g_2336_b("brand");
    public static final g_2336_b J_1907_R = new g_2336_b("debug/path");
    public static final g_2336_b R_4764_Y = new g_2336_b("debug/neighbors_update");
    public static final g_2336_b G_564_y = new g_2336_b("debug/caves");
    public static final g_2336_b P_1922_E = new g_2336_b("debug/structures");
    public static final g_2336_b u_1723_Y = new g_2336_b("debug/worldgen_attempt");
    public static final g_2336_b v_4262_N = new g_2336_b("debug/poi_ticket_count");
    public static final g_2336_b w_1484_f = new g_2336_b("debug/poi_added");
    public static final g_2336_b t_148_a = new g_2336_b("debug/poi_removed");
    public static final g_2336_b s_956_w = new g_2336_b("debug/village_sections");
    public static final g_2336_b u_2550_I = new g_2336_b("debug/goal_selector");
    public static final g_2336_b M_588_G = new g_2336_b("debug/brain");
    public static final g_2336_b P_4830_p = new g_2336_b("debug/bee");
    public static final g_2336_b h_1847_R = new g_2336_b("debug/hive");
    public static final g_2336_b Q_4569_t = new g_2336_b("debug/game_test_add_marker");
    public static final g_2336_b M_182_A = new g_2336_b("debug/game_test_clear");
    public static final g_2336_b t_1786_h = new g_2336_b("debug/raids");
    private g_2336_b multiplayerClientSuggestionProvider;
    private b_2585_i w_1457_N;

    public ClientboundCustomPayloadPacket() {
    }

    public ClientboundCustomPayloadPacket(g_2336_b channelIn, b_2585_i dataIn) {
        this.multiplayerClientSuggestionProvider = channelIn;
        this.w_1457_N = dataIn;
        if (dataIn.writerIndex() > 0x100000) {
            throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.multiplayerClientSuggestionProvider = buf.P_4830_p();
        int i = buf.readableBytes();
        if (i < 0 || i > 0x100000) {
            throw new IOException("Payload may not be larger than 1048576 bytes");
        }
        this.w_1457_N = new b_2585_i(buf.readBytes(i));
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.multiplayerClientSuggestionProvider);
        buf.writeBytes(this.w_1457_N.copy());
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public g_2336_b J_1907_R() {
        return this.multiplayerClientSuggestionProvider;
    }

    public b_2585_i R_4764_Y() {
        return new b_2585_i(this.w_1457_N.copy());
    }
}


