/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 */
package code.alphadlc.utils.features.aura.utils;

import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_1799;
import net.minecraft.class_1802;

public class Pressing
implements QuickImports {
    private long lastClickTime = System.currentTimeMillis();

    public boolean isCooldownComplete(boolean syncTps, int ticks) {
        boolean isMace = this.isHoldingMace();
        boolean cooldownReady = isMace || Pressing.mc.field_1724.method_7261((float)ticks) > 0.9f;
        long delay = syncTps ? (long)(450.0f * (20.0f / Math.max(Network.TPS, 0.1f))) : 450L;
        return cooldownReady && this.lastClickPassed() >= delay;
    }

    public long lastClickPassed() {
        return System.currentTimeMillis() - this.lastClickTime;
    }

    public void recalculate() {
        this.lastClickTime = System.currentTimeMillis();
    }

    private boolean isHoldingMace() {
        class_1799 mainHand = Pressing.mc.field_1724.method_6047();
        return mainHand.method_31574(class_1802.field_49814);
    }
}
