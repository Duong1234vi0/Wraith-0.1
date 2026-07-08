/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_746
 */
package code.alphadlc.utils.features.aura;

import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.utils.features.aura.RotationStorage;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.features.rotate.Rotation;
import net.minecraft.class_310;
import net.minecraft.class_746;

public final class AuraMixinHelper {
    public static boolean isCombatActive() {
        AttackAura aura = AttackAura.getInstance();
        return aura != null && aura.isCombatRotationActive();
    }

    public static boolean hasMoveCorrection() {
        AttackAura aura = AttackAura.getInstance();
        return aura != null && aura.hasMoveCorrection();
    }

    public static float resolveYaw(float fallback) {
        Rotation rotation = AuraMixinHelper.resolveRotation();
        return rotation != null ? rotation.getYaw() : fallback;
    }

    public static float resolvePitch(float fallback) {
        Rotation rotation = AuraMixinHelper.resolveRotation();
        return rotation != null ? rotation.getPitch() : fallback;
    }

    private static Rotation resolveRotation() {
        if (TurnsConnection.INSTANCE.getCurrentAngle() != null) {
            return new Rotation(TurnsConnection.INSTANCE.getRotation().getYaw(), TurnsConnection.INSTANCE.getRotation().getPitch());
        }
        RotationStorage storage = RotationStorage.instance;
        class_746 player = class_310.method_1551().field_1724;
        if (storage != null && storage.isRotating() && player != null) {
            return new Rotation(player.method_36454(), player.method_36455());
        }
        return null;
    }

    private AuraMixinHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
