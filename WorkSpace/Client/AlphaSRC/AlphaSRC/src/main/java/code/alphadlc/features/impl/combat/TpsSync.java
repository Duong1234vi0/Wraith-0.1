/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.packet.network.Network;
import net.minecraft.class_3532;

public class TpsSync
extends Module {
    public static TpsSync getInstance() {
        return Instance.get(TpsSync.class);
    }

    public TpsSync() {
        super("TpsSync", "\u0421\u0438\u043d\u0445\u0440\u043e\u043d\u0438\u0437\u0430\u0446\u0438\u044f \u0441 TPS \u0441\u0435\u0440\u0432\u0435\u0440\u0430", ModuleCategory.COMBAT);
    }

    public float getCurrentTPS() {
        return class_3532.method_15363((float)Network.TPS, (float)0.1f, (float)20.0f);
    }

    public long getAdjustedCooldown(long baseCooldown) {
        if (!this.isState()) {
            return baseCooldown;
        }
        float tps = this.getCurrentTPS();
        if (tps >= 20.0f) {
            return baseCooldown;
        }
        float multiplier = 20.0f / tps;
        float additionalFactor = 1.0f + (20.0f - tps) * 0.05f;
        long adjusted = (long)((float)baseCooldown * multiplier * additionalFactor);
        return Math.min(adjusted, 3000L);
    }

    public boolean canAttack(long lastAttackTime, long baseCooldown, long currentTime) {
        if (!this.isState()) {
            return currentTime >= lastAttackTime + baseCooldown;
        }
        long adjustedCooldown = this.getAdjustedCooldown(baseCooldown);
        return currentTime >= lastAttackTime + adjustedCooldown;
    }
}
