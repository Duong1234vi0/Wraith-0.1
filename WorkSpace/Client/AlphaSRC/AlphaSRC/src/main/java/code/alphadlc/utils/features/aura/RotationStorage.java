/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_3532
 */
package code.alphadlc.utils.features.aura;

import code.alphadlc.AlphaDLC;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.combat.components.gcd.GCDUtil;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.AuraMixinHelper;
import code.alphadlc.utils.features.aura.FreeLookStorage;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.features.rotate.Rotation;
import code.alphadlc.utils.interactions.simulate.MovingUtil;
import net.minecraft.class_1297;
import net.minecraft.class_3532;

public class RotationStorage
implements QuickImports {
    public static RotationStorage instance;
    private RotationTask currentTask = RotationTask.IDLE;
    private float currentYawSpeed;
    private float currentPitchSpeed;
    private float currentYawReturnSpeed;
    private float currentPitchReturnSpeed;
    private int currentPriority;
    private int currentTimeout;
    private int idleTicks;
    private Rotation targetRotation;
    private boolean clientRotation;

    public RotationStorage() {
        instance = this;
        AlphaDLC.getInstance().getEventManager();
        EventManager.register(this);
    }

    private void resetRotation() {
        if (RotationStorage.mc.field_1724 == null) {
            this.finishStop();
            return;
        }
        this.finishStop();
    }

    @EventHandler
    public void onEventTick(TickEvent event) {
        if (RotationStorage.mc.field_1724 == null) {
            return;
        }
        if (this.currentTask().equals((Object)RotationTask.AIM) && this.currentTimeout() > 0 && this.idleTicks() > this.currentTimeout()) {
            this.currentTask(RotationTask.RESET);
        }
        if (this.currentTask().equals((Object)RotationTask.RESET)) {
            this.resetRotation();
        }
        ++this.idleTicks;
    }

    @EventHandler
    public void onInput(InputEvent event) {
        if (this.isRotating() && FreeLookStorage.isActive() && !AuraMixinHelper.hasMoveCorrection()) {
            MovingUtil.fixMovementFree(event);
        }
    }

    public static void update(Rotation target, float yawSpeed, float pitchSpeed, float yawReturnSpeed, float pitchReturnSpeed, int timeout, int priority, boolean clientRotation) {
        RotationStorage storage = instance;
        if (RotationStorage.mc.field_1724 == null || storage == null) {
            return;
        }
        if (storage.currentPriority() > priority && storage.idleTicks() == 0) {
            return;
        }
        if (clientRotation) {
            TurnsConnection.INSTANCE.clear();
        }
        if (storage.currentTask().equals((Object)RotationTask.IDLE) && !clientRotation) {
            FreeLookStorage.activateForSilentRotation();
        }
        storage.currentYawSpeed(yawSpeed);
        storage.currentPitchSpeed(pitchSpeed);
        storage.currentYawReturnSpeed(yawReturnSpeed);
        storage.currentPitchReturnSpeed(pitchReturnSpeed);
        storage.currentTimeout(timeout);
        storage.currentPriority(priority);
        storage.currentTask(RotationTask.AIM);
        storage.targetRotation(target);
        storage.clientRotation = clientRotation;
        storage.updateRotation(target, yawSpeed, pitchSpeed);
    }

    public static void update(Rotation targetRotation, float turnSpeed, float returnSpeed, int timeout, int priority) {
        RotationStorage.update(targetRotation, turnSpeed, turnSpeed, returnSpeed, returnSpeed, timeout, priority, false);
    }

    public static void update(Rotation targetRotation, float yawSpeed, float pitchSpeed, float returnSpeed, int timeout, int priority) {
        RotationStorage.update(targetRotation, yawSpeed, pitchSpeed, returnSpeed, returnSpeed, timeout, priority, false);
    }

    private boolean updateRotation(Rotation targetRotation, float yawSpeed, float pitchSpeed) {
        if (RotationStorage.mc.field_1724 == null) {
            return false;
        }
        Rotation currentRotation = new Rotation((class_1297)RotationStorage.mc.field_1724);
        float yawDelta = class_3532.method_15393((float)(targetRotation.getYaw() - currentRotation.getYaw()));
        float pitchDelta = targetRotation.getPitch() - currentRotation.getPitch();
        float clampedYaw = Math.min(Math.abs(yawDelta), yawSpeed);
        float clampedPitch = Math.min(Math.abs(pitchDelta), pitchSpeed);
        float yaw = RotationStorage.mc.field_1724.method_36454();
        float pitch = class_3532.method_15363((float)(RotationStorage.mc.field_1724.method_36455() + GCDUtil.getFixedRotation(class_3532.method_15363((float)pitchDelta, (float)(-clampedPitch), (float)clampedPitch))), (float)-90.0f, (float)90.0f);
        RotationStorage.mc.field_1724.method_36456(yaw += GCDUtil.getFixedRotation(class_3532.method_15363((float)yawDelta, (float)(-clampedYaw), (float)clampedYaw)));
        RotationStorage.mc.field_1724.method_36457(pitch);
        if (!this.clientRotation) {
            TurnsConnection.INSTANCE.holdRotation(new Turns(yaw, pitch));
        }
        this.idleTicks(0);
        return new Rotation(yaw, pitch).getDelta(targetRotation) < 1.0f;
    }

    public void stopRotation() {
        this.finishStop();
    }

    public void stopWithoutCameraRestore() {
        this.clientRotation = false;
        this.currentTask(RotationTask.IDLE);
        this.currentPriority(0);
        FreeLookStorage.setActive(false);
        TurnsConnection.INSTANCE.clear();
        TurnsConnection.INSTANCE.setRotation(null);
    }

    private void finishStop() {
        if (RotationStorage.mc.field_1724 != null && FreeLookStorage.isActive()) {
            RotationStorage.mc.field_1724.method_36456(FreeLookStorage.getFreeYaw());
            RotationStorage.mc.field_1724.method_36457(FreeLookStorage.getFreePitch());
            RotationStorage.mc.field_1724.field_6241 = FreeLookStorage.getFreeYaw();
            RotationStorage.mc.field_1724.field_6283 = FreeLookStorage.getFreeYaw();
        }
        this.clientRotation = false;
        this.currentTask(RotationTask.IDLE);
        this.currentPriority(0);
        FreeLookStorage.setActive(false);
        TurnsConnection.INSTANCE.clear();
        TurnsConnection.INSTANCE.setRotation(null);
    }

    public boolean isRotating() {
        return !this.currentTask.equals((Object)RotationTask.IDLE);
    }

    public boolean isCameraReturning() {
        return this.currentTask.equals((Object)RotationTask.RESET);
    }

    public RotationTask currentTask() {
        return this.currentTask;
    }

    public float currentYawSpeed() {
        return this.currentYawSpeed;
    }

    public float currentPitchSpeed() {
        return this.currentPitchSpeed;
    }

    public float currentYawReturnSpeed() {
        return this.currentYawReturnSpeed;
    }

    public float currentPitchReturnSpeed() {
        return this.currentPitchReturnSpeed;
    }

    public int currentPriority() {
        return this.currentPriority;
    }

    public int currentTimeout() {
        return this.currentTimeout;
    }

    public int idleTicks() {
        return this.idleTicks;
    }

    public Rotation targetRotation() {
        return this.targetRotation;
    }

    public boolean clientRotation() {
        return this.clientRotation;
    }

    public RotationStorage currentTask(RotationTask currentTask) {
        this.currentTask = currentTask;
        return this;
    }

    public RotationStorage currentYawSpeed(float currentYawSpeed) {
        this.currentYawSpeed = currentYawSpeed;
        return this;
    }

    public RotationStorage currentPitchSpeed(float currentPitchSpeed) {
        this.currentPitchSpeed = currentPitchSpeed;
        return this;
    }

    public RotationStorage currentYawReturnSpeed(float currentYawReturnSpeed) {
        this.currentYawReturnSpeed = currentYawReturnSpeed;
        return this;
    }

    public RotationStorage currentPitchReturnSpeed(float currentPitchReturnSpeed) {
        this.currentPitchReturnSpeed = currentPitchReturnSpeed;
        return this;
    }

    public RotationStorage currentPriority(int currentPriority) {
        this.currentPriority = currentPriority;
        return this;
    }

    public RotationStorage currentTimeout(int currentTimeout) {
        this.currentTimeout = currentTimeout;
        return this;
    }

    public RotationStorage idleTicks(int idleTicks) {
        this.idleTicks = idleTicks;
        return this;
    }

    public RotationStorage targetRotation(Rotation targetRotation) {
        this.targetRotation = targetRotation;
        return this;
    }

    public RotationStorage clientRotation(boolean clientRotation) {
        this.clientRotation = clientRotation;
        return this;
    }

    public static enum RotationTask {
        AIM,
        RESET,
        IDLE;

    }
}
