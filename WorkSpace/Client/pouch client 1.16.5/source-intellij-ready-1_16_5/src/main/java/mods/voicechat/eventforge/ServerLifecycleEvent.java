/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.eventforge;

import lightning.product.x_607_J;
import net.minecraft.server.G_564_y;

public class ServerLifecycleEvent
implements x_607_J {
    protected final G_564_y server;

    public ServerLifecycleEvent(G_564_y server) {
        this.server = server;
    }

    public G_564_y getServer() {
        return this.server;
    }
}

