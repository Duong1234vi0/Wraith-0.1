/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.bridge.game.GameSession
 */
package lightning.product;

import com.mojang.bridge.game.GameSession;
import java.util.UUID;
import lightning.product.A_2226_Q;
import lightning.product.M_182_A;
import lightning.product.V_772_m;
import lightning.product.W_2853_p;
import lightning.product.Z_875_P;
import lightning.product.c_3005_b;
import lightning.product.k_4690_i;

public class Session
implements GameSession {
    private final int n_1700_B;
    private final boolean J_1907_R;
    private final String R_4764_Y;
    private final String G_564_y;
    private final UUID P_1922_E;

    public Session(k_4690_i world, V_772_m player, W_2853_p netHandler) {
        this.n_1700_B = netHandler.P_1922_E().size();
        this.J_1907_R = !netHandler.getNetworkManager().G_564_y();
        this.R_4764_Y = world.x_607_J().R_4764_Y();
        A_2226_Q networkplayerinfo = netHandler.n_1700_B(player.w_2705_t());
        this.G_564_y = networkplayerinfo != null ? networkplayerinfo.J_1907_R().J_1907_R() : "unknown";
        this.P_1922_E = netHandler.P_4830_p();
    }

    public Session(c_3005_b world, Z_875_P player, M_182_A netHandler) {
        this.n_1700_B = netHandler.P_1922_E().size();
        this.J_1907_R = !netHandler.getNetworkManager().G_564_y();
        this.R_4764_Y = world.x_607_J().R_4764_Y();
        A_2226_Q networkplayerinfo = netHandler.n_1700_B(player.w_2705_t());
        this.G_564_y = networkplayerinfo != null ? networkplayerinfo.J_1907_R().J_1907_R() : "unknown";
        this.P_1922_E = netHandler.P_4830_p();
    }

    public int getPlayerCount() {
        return this.n_1700_B;
    }

    public boolean isRemoteServer() {
        return this.J_1907_R;
    }

    public String getDifficulty() {
        return this.R_4764_Y;
    }

    public String getGameMode() {
        return this.G_564_y;
    }

    public UUID getSessionId() {
        return this.P_1922_E;
    }
}


