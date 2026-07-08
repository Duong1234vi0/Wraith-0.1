/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.stealth;

import code.alphadlc.features.impl.combat.HitBox;
import code.alphadlc.features.impl.combat.ProjectHelper;
import code.alphadlc.features.impl.combat.TargetPearl;
import code.alphadlc.features.impl.misc.FreeCam;
import code.alphadlc.features.impl.movement.AirStuck;
import code.alphadlc.features.impl.movement.ElytraMotion;
import code.alphadlc.features.impl.movement.Flight;
import code.alphadlc.features.impl.movement.NoSlow;
import code.alphadlc.features.impl.movement.Speed;
import code.alphadlc.features.impl.movement.Spider;
import code.alphadlc.features.impl.movement.Strafe;
import code.alphadlc.features.impl.movement.TargetStrafe;
import code.alphadlc.features.impl.movement.autoMystGlider.receivers.AutoMystGliderModule;
import code.alphadlc.features.module.Module;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.features.aura.AuraMixinHelper;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.features.aura.warp.TurnsConstructor;

public final class MovementMixinGuard {
    public static boolean shouldUseEntityMovementPhysics() {
        return AuraMixinHelper.isCombatActive() || MovementMixinGuard.isActive(Flight.class) || MovementMixinGuard.isActive(Speed.class) || MovementMixinGuard.isActive(Spider.class) || MovementMixinGuard.isActive(TargetStrafe.class) || MovementMixinGuard.isActive(Strafe.class) || MovementMixinGuard.isActive(ElytraMotion.class) || MovementMixinGuard.isActive(AirStuck.class) || MovementMixinGuard.isActive(FreeCam.class);
    }

    public static boolean shouldHandleVelocityStrafe() {
        if (AuraMixinHelper.isCombatActive()) {
            return true;
        }
        TurnsConstructor rotationPlan = TurnsConnection.INSTANCE.getCurrentRotationPlan();
        return rotationPlan != null && rotationPlan.isMoveCorrection();
    }

    public static boolean isHitBoxActive() {
        return MovementMixinGuard.isActive(HitBox.class);
    }

    public static boolean shouldHandleMoveEvent() {
        return MovementMixinGuard.isActive(Flight.class) || MovementMixinGuard.isActive(AirStuck.class) || MovementMixinGuard.isActive(FreeCam.class) || MovementMixinGuard.isActive(AutoMystGliderModule.class);
    }

    public static boolean shouldHandleMotionEvent() {
        return MovementMixinGuard.isActive(NoSlow.class) || MovementMixinGuard.isActive(AirStuck.class) || MovementMixinGuard.isActive(ProjectHelper.class);
    }

    public static boolean shouldHandlePostMotionEvent() {
        return MovementMixinGuard.isActive(TargetPearl.class);
    }

    public static boolean shouldHandleNoSlowRedirect() {
        return MovementMixinGuard.isActive(NoSlow.class);
    }

    private static boolean isActive(Class<? extends Module> clazz) {
        Module module = Instance.get(clazz);
        return module != null && module.isState();
    }

    private MovementMixinGuard() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
