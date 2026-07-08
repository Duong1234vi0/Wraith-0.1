/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.combat.ElytraResolver;
import code.alphadlc.features.impl.combat.PacketCriticals;
import code.alphadlc.features.impl.movement.ElytraTarget;
import code.alphadlc.features.impl.player.NoDelay;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_3532;

public final class CombatModules
implements QuickImports {
    public static AttackAura getAura() {
        return AttackAura.getInstance();
    }

    public static ElytraResolver getElytraResolver() {
        return ElytraResolver.getInstance();
    }

    public static ElytraTarget getElytraTarget() {
        return ElytraTarget.getInstance();
    }

    public static PacketCriticals getPacketCriticals() {
        return PacketCriticals.getInstance();
    }

    public static NoDelay getNoJumpDelay() {
        return NoDelay.getInstance();
    }

    public static boolean isElytraPredictActive() {
        ElytraTarget elytraTarget = CombatModules.getElytraTarget();
        return elytraTarget != null && elytraTarget.isOvertakeActive() && CombatModules.mc.field_1724 != null && CombatModules.mc.field_1724.method_6128();
    }

    public static float getElytraForward() {
        ElytraTarget elytraTarget = CombatModules.getElytraTarget();
        if (elytraTarget == null) {
            return 0.0f;
        }
        return elytraTarget.getPredictStrength();
    }

    public static int getElytraForwardTicks() {
        return Math.max(0, Math.round(CombatModules.getElytraForward()));
    }

    public static long getAdjustedCooldown(long baseCooldown, boolean enabled) {
        if (!enabled) {
            return baseCooldown;
        }
        float tps = class_3532.method_15363((float)Network.TPS, (float)0.1f, (float)20.0f);
        if (tps >= 20.0f) {
            return baseCooldown;
        }
        float multiplier = 20.0f / tps;
        float additionalFactor = 1.0f + (20.0f - tps) * 0.05f;
        return Math.min((long)((float)baseCooldown * multiplier * additionalFactor), 3000L);
    }

    private CombatModules() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
