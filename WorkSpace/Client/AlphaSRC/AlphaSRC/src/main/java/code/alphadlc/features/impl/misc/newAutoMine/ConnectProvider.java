/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.misc.newAutoMine;

import code.alphadlc.features.impl.misc.newAutoMine.MiningAnarchyState;
import code.alphadlc.utils.display.interfaces.QuickImports;

public class ConnectProvider
implements QuickImports {
    private long lastConnectTime = -1L;
    private MiningAnarchyState currentState = null;

    public void connect(MiningAnarchyState miningAnarchyState) {
        if (miningAnarchyState != null && !miningAnarchyState.equals(this.currentState) && System.currentTimeMillis() - this.lastConnectTime > 2000L) {
            mc.method_1562().method_45731("an" + miningAnarchyState.getAnarchy());
            this.lastConnectTime = System.currentTimeMillis();
            this.currentState = miningAnarchyState;
        }
    }

    public long getLastConnectTime() {
        return this.lastConnectTime;
    }

    public MiningAnarchyState getCurrentState() {
        return this.currentState;
    }
}
