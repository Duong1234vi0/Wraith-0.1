/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2708
 *  net.minecraft.class_2828
 *  net.minecraft.class_3532
 */
package code.alphadlc.utils.features.aura.warp;

import code.alphadlc.AlphaDLC;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.PlayerVelocityStrafeEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.AuraMixinHelper;
import code.alphadlc.utils.features.aura.RotationStorage;
import code.alphadlc.utils.features.aura.rotations.constructor.LinearConstructor;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConstructor;
import code.alphadlc.utils.math.task.TaskPriority;
import code.alphadlc.utils.math.task.TaskProcessor;
import java.lang.runtime.SwitchBootstraps;
import java.util.Objects;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2828;
import net.minecraft.class_3532;

public class TurnsConnection
implements QuickImports {
    public static TurnsConnection INSTANCE = new TurnsConnection();
    private TurnsConstructor lastRotationPlan;
    private final TaskProcessor<TurnsConstructor> rotationPlanTaskProcessor = new TaskProcessor();
    public Turns currentAngle;
    private Turns previousAngle;
    private Turns serverAngle = Turns.DEFAULT;
    private boolean rotateBack = false;
    private long rotateBackTime = -1L;
    private Turns manualAngle;
    private int manualAngleTicks = 0;
    private LinearConstructor linearConstructor = new LinearConstructor();

    public TurnsConnection() {
        AlphaDLC.getInstance().getEventManager();
        EventManager.register(this);
    }

    public void setRotation(Turns value) {
        this.previousAngle = value == null ? (this.currentAngle != null ? this.currentAngle : MathAngle.cameraAngle()) : this.currentAngle;
        this.currentAngle = value;
    }

    public void holdRotation(Turns value) {
        this.rotationPlanTaskProcessor.activeTasks.clear();
        this.lastRotationPlan = null;
        this.rotationPlanTaskProcessor.tickCounter = 0;
        this.manualAngle = value;
        this.manualAngleTicks = 2;
        this.rotateBack = false;
        this.rotateBackTime = -1L;
        this.setRotation(value);
    }

    public Turns getRotation() {
        return this.currentAngle != null ? this.currentAngle : MathAngle.cameraAngle();
    }

    public Turns getPreviousRotation() {
        if (this.currentAngle != null && this.previousAngle != null) {
            return this.previousAngle;
        }
        if (TurnsConnection.mc.field_1724 == null) {
            return new Turns(0.0f, 0.0f);
        }
        return new Turns(TurnsConnection.mc.field_1724.field_5982, TurnsConnection.mc.field_1724.field_6004);
    }

    public Turns getMoveRotation() {
        TurnsConstructor rotationPlan = this.getCurrentRotationPlan();
        return this.currentAngle != null && rotationPlan != null && rotationPlan.isMoveCorrection() ? this.currentAngle : MathAngle.cameraAngle();
    }

    public TurnsConstructor getCurrentRotationPlan() {
        return this.rotationPlanTaskProcessor.fetchActiveTaskValue() != null ? this.rotationPlanTaskProcessor.fetchActiveTaskValue() : this.lastRotationPlan;
    }

    public void rotateTo(Turns.VecRotation vecRotation, class_1309 entity, int reset, TurnsConfig configurable, TaskPriority taskPriority, Module provider) {
        this.rotateTo(configurable.createRotationPlan(vecRotation.getAngle(), vecRotation.getVec(), (class_1297)entity, reset), taskPriority, provider);
    }

    public void rotateTo(Turns angle, int reset, TurnsConfig configurable, TaskPriority taskPriority, Module provider) {
        this.rotateTo(configurable.createRotationPlan(angle, angle.toVector(), null, reset), taskPriority, provider);
    }

    public void rotateTo(Turns angle, TurnsConfig configurable, TaskPriority taskPriority, Module provider) {
        this.rotateTo(configurable.createRotationPlan(angle, angle.toVector(), null, 1), taskPriority, provider);
    }

    public void rotateTo(TurnsConstructor plan, TaskPriority taskPriority, Module provider) {
        this.rotationPlanTaskProcessor.addTask(new TaskProcessor.Task<TurnsConstructor>(1, taskPriority.getPriority(), provider, plan));
    }

    public void update() {
        boolean hasActiveTask;
        if (this.manualAngleTicks > 0 && this.manualAngle != null) {
            this.rotateBack = false;
            this.rotateBackTime = -1L;
            this.setRotation(this.manualAngle.adjustSensitivity());
            --this.manualAngleTicks;
            if (this.manualAngleTicks <= 0) {
                this.manualAngle = null;
            }
            return;
        }
        TurnsConstructor activePlan = this.getCurrentRotationPlan();
        if (this.rotationPlanTaskProcessor.fetchActiveTaskValue() == null) {
            if (!this.rotateBack) {
                this.rotateBackTime = System.currentTimeMillis();
                this.rotateBack = true;
            }
        } else {
            this.rotateBack = false;
            this.rotateBackTime = -1L;
        }
        Turns clientAngle = MathAngle.cameraAngle();
        boolean bl = hasActiveTask = this.rotationPlanTaskProcessor.fetchActiveTaskValue() != null;
        if (hasActiveTask) {
            Turns newAngle = activePlan.nextRotation(this.currentAngle != null ? this.currentAngle : clientAngle, false).adjustSensitivity();
            this.setRotation(newAngle);
            this.lastRotationPlan = activePlan;
            this.rotationPlanTaskProcessor.tick(1);
        }
        if (this.rotateBack) {
            if (this.currentAngle == null) {
                this.rotateBack = false;
                return;
            }
            if (System.currentTimeMillis() - this.rotateBackTime < 1000L) {
                Turns delta = MathAngle.calculateDelta(this.currentAngle, clientAngle).adjustSensitivity();
                this.setRotation(new Turns(class_3532.method_16439((float)0.5f, (float)this.currentAngle.getYaw(), (float)(this.currentAngle.getYaw() + delta.getYaw())), class_3532.method_16439((float)0.5f, (float)this.currentAngle.getPitch(), (float)(this.currentAngle.getPitch() + delta.getPitch()))));
            } else {
                this.setRotation(null);
                this.lastRotationPlan = null;
                this.rotateBack = false;
            }
        }
    }

    public static double computeRotationDifference(Turns a, Turns b) {
        return Math.hypot(Math.abs(TurnsConnection.computeAngleDifference(a.getYaw(), b.getYaw())), Math.abs(a.getPitch() - b.getPitch()));
    }

    public static float computeAngleDifference(float a, float b) {
        return class_3532.method_15393((float)(a - b));
    }

    private class_243 fixVelocity(class_243 currVelocity, class_243 movementInput, float speed) {
        Float yaw = this.resolveMoveCorrectionYaw();
        if (yaw == null) {
            return currVelocity;
        }
        double d = movementInput.method_1027();
        if (d < 1.0E-7) {
            return class_243.field_1353;
        }
        class_243 vec3d = (d > 1.0 ? movementInput.method_1029() : movementInput).method_1021((double)speed);
        float f = class_3532.method_15374((float)(yaw.floatValue() * ((float)Math.PI / 180)));
        float g = class_3532.method_15362((float)(yaw.floatValue() * ((float)Math.PI / 180)));
        return new class_243(vec3d.method_10216() * (double)g - vec3d.method_10215() * (double)f, vec3d.method_10214(), vec3d.method_10215() * (double)g + vec3d.method_10216() * (double)f);
    }

    private Float resolveMoveCorrectionYaw() {
        if (this.currentAngle != null) {
            return Float.valueOf(this.currentAngle.getYaw());
        }
        if (!AuraMixinHelper.isCombatActive() || !AuraMixinHelper.hasMoveCorrection()) {
            return null;
        }
        RotationStorage storage = RotationStorage.instance;
        if (storage != null && storage.isRotating() && storage.targetRotation() != null) {
            return Float.valueOf(storage.targetRotation().getYaw());
        }
        return null;
    }

    public void clear() {
        this.rotationPlanTaskProcessor.activeTasks.clear();
        this.lastRotationPlan = null;
        this.rotationPlanTaskProcessor.tickCounter = 0;
        this.manualAngle = null;
        this.manualAngleTicks = 0;
    }

    @EventHandler
    public void onPlayerVelocityStrafe(PlayerVelocityStrafeEvent e) {
        TurnsConstructor currentRotationPlan = this.getCurrentRotationPlan();
        if (currentRotationPlan != null && currentRotationPlan.isMoveCorrection()) {
            e.setVelocity(this.fixVelocity(e.getVelocity(), e.getMovementInput(), e.getSpeed()));
            return;
        }
        if (AuraMixinHelper.isCombatActive() && AuraMixinHelper.hasMoveCorrection()) {
            e.setVelocity(this.fixVelocity(e.getVelocity(), e.getMovementInput(), e.getSpeed()));
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
        SafeEvents.dispatch(() -> new RotationUpdateEvent(0));
        this.update();
        SafeEvents.dispatch(() -> new RotationUpdateEvent(2));
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        if (!event.isCancelled()) {
            class_2596<?> class_25962 = event.getPacket();
            Objects.requireNonNull(class_25962);
            class_2596<?> class_25963 = class_25962;
            int n = 0;
            block4: while (true) {
                switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_2828.class, class_2708.class}, class_25963, n)) {
                    case 0: {
                        class_2828 player = (class_2828)class_25963;
                        if (!player.method_36172()) {
                            n = 1;
                            continue block4;
                        }
                        this.serverAngle = new Turns(player.method_12271(1.0f), player.method_12270(1.0f));
                        break block4;
                    }
                    case 1: {
                        class_2708 player = (class_2708)class_25963;
                        this.serverAngle = new Turns(player.comp_3228().comp_3150(), player.comp_3228().comp_3151());
                        break block4;
                    }
                }
                break;
            }
        }
    }

    public TurnsConstructor getLastRotationPlan() {
        return this.lastRotationPlan;
    }

    public TaskProcessor<TurnsConstructor> getRotationPlanTaskProcessor() {
        return this.rotationPlanTaskProcessor;
    }

    public Turns getCurrentAngle() {
        return this.currentAngle;
    }

    public Turns getPreviousAngle() {
        return this.previousAngle;
    }

    public Turns getServerAngle() {
        return this.serverAngle;
    }

    public boolean isRotateBack() {
        return this.rotateBack;
    }

    public long getRotateBackTime() {
        return this.rotateBackTime;
    }

    public Turns getManualAngle() {
        return this.manualAngle;
    }

    public int getManualAngleTicks() {
        return this.manualAngleTicks;
    }

    public LinearConstructor getLinearConstructor() {
        return this.linearConstructor;
    }
}
