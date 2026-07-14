/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.eventforge;

import javax.annotation.Nullable;
import lightning.product.V_3163_W;
import lightning.product.V_772_m;
import lightning.product.c_1633_k;
import lightning.product.x_607_J;

public class ClientPlayerNetworkEvent
implements x_607_J {
    private final V_3163_W controller;
    private final V_772_m player;
    private final c_1633_k networkManager;

    @Nullable
    public V_3163_W getController() {
        return this.controller;
    }

    @Nullable
    public V_772_m getPlayer() {
        return this.player;
    }

    @Nullable
    public c_1633_k getNetworkManager() {
        return this.networkManager;
    }

    ClientPlayerNetworkEvent(V_3163_W controller, V_772_m player, c_1633_k networkManager) {
        this.controller = controller;
        this.player = player;
        this.networkManager = networkManager;
    }

    public static class LoggedOutEvent
    extends ClientPlayerNetworkEvent {
        public LoggedOutEvent(V_3163_W controller, V_772_m player, c_1633_k networkManager) {
            super(controller, player, networkManager);
        }
    }

    public static class LoggedInEvent
    extends ClientPlayerNetworkEvent {
        public LoggedInEvent(V_3163_W controller, V_772_m player, c_1633_k networkManager) {
            super(controller, player, networkManager);
        }
    }
}

