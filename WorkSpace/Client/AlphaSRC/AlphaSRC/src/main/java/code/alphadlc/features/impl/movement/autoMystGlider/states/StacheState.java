/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.movement.autoMystGlider.states;

import code.alphadlc.utils.display.interfaces.QuickImports;

public class StacheState
implements QuickImports {
    private final int anarchy;
    private final String homeName;
    private boolean teleported = false;
    private boolean connected = false;

    public StacheState(int anarchy, String homeName) {
        this.anarchy = anarchy;
        this.homeName = homeName;
    }

    public void connect() {
        mc.method_1562().method_45731("an" + this.anarchy);
        this.connected = true;
    }

    public void teleport() {
        mc.method_1562().method_45731("home " + this.homeName);
        this.teleported = true;
    }

    public void reload() {
        this.teleported = false;
        this.connected = false;
    }

    public int getAnarchy() {
        return this.anarchy;
    }

    public String getHomeName() {
        return this.homeName;
    }

    public boolean isTeleported() {
        return this.teleported;
    }

    public boolean isConnected() {
        return this.connected;
    }
}
