/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1764
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1823
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_3532
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_746
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.MotionEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.combat.AntiBot;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import java.util.Comparator;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1764;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1823;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2846;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_746;

public class ProjectHelper
extends Module
implements QuickImports {
    private static final long HUD_KEEP_MS = 700L;
    private static final int MAX_PREDICT_TICKS = 60;
    private static final int SOLVER_ITERATIONS = 3;
    private static final double BOW_GRAVITY = 0.05;
    private static final double TRIDENT_GRAVITY = 0.05;
    private static final double SNOWBALL_GRAVITY = 0.03;
    private static final double CROSSBOW_GRAVITY = 0.05;
    private final MultiSelectSetting weapons = new MultiSelectSetting("\u041e\u0440\u0443\u0436\u0438\u0435", "\u0412\u044b\u0431\u043e\u0440 \u0442\u0438\u043f\u043e\u0432 \u043c\u0435\u0442\u0430\u0442\u0435\u043b\u044c\u043d\u043e\u0433\u043e \u043e\u0440\u0443\u0436\u0438\u044f").value("\u041b\u0443\u043a", "\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446", "\u0410\u0440\u0431\u0430\u043b\u0435\u0442", "\u0421\u043d\u0435\u0436\u043e\u043a").selected("\u041b\u0443\u043a", "\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446", "\u0410\u0440\u0431\u0430\u043b\u0435\u0442");
    private final SliderSettings distance = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043f\u043e\u0438\u0441\u043a\u0430 \u0446\u0435\u043b\u0438").setValue(50.0f).range(10.0f, 200.0f).step(5.0f);
    private final SliderSettings fov = new SliderSettings("FOV", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u044b\u0439 \u0443\u0433\u043e\u043b \u043f\u043e\u0438\u0441\u043a\u0430 \u0446\u0435\u043b\u0438").setValue(180.0f).range(10.0f, 180.0f).step(5.0f);
    private final SliderSettings predict = new SliderSettings("\u041f\u0440\u0435\u0434\u0438\u043a\u0442", "\u041c\u043d\u043e\u0436\u0438\u0442\u0435\u043b\u044c \u0443\u043f\u0440\u0435\u0436\u0434\u0435\u043d\u0438\u044f \u043f\u043e \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044e \u0446\u0435\u043b\u0438").setValue(1.0f).range(0.0f, 3.0f).step(0.1f);
    private final BooleanSetting moveCorrection = new BooleanSetting("\u041a\u043e\u0440\u0440\u0435\u043a\u0446\u0438\u044f \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f", "\u0418\u0441\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f \u043e\u0442\u043d\u043e\u0441\u0438\u0442\u0435\u043b\u044c\u043d\u043e \u0446\u0435\u043b\u0438").setValue(true);
    private final BooleanSetting throughWalls = new BooleanSetting("\u0427\u0435\u0440\u0435\u0437 \u0441\u0442\u0435\u043d\u044b", "\u0421\u0442\u0440\u0435\u043b\u044f\u0442\u044c \u0447\u0435\u0440\u0435\u0437 \u0431\u043b\u043e\u043a\u0438").setValue(false);
    private final SelectSetting sorting = new SelectSetting("\u0421\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u043a\u0430", "\u041a\u0430\u043a \u0432\u044b\u0431\u0438\u0440\u0430\u0442\u044c \u0446\u0435\u043b\u044c").value("\u0421\u043c\u0435\u0448\u0430\u043d\u043d\u0430\u044f", "\u041f\u043e \u0443\u0433\u043b\u0443", "\u041f\u043e \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u0438", "\u041f\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044e").selected("\u0421\u043c\u0435\u0448\u0430\u043d\u043d\u0430\u044f");
    private class_1309 currentTarget;
    private class_1309 hudTarget;
    private long lastHudTargetAt;
    private ProjectileProfile lastProjectileProfile;
    private long lastProjectileProfileAt;
    private Turns currentRotation;
    private Turns serverRotation;
    private boolean isAiming;

    public ProjectHelper() {
        super("ProjectHelper", "ProjectHelper", ModuleCategory.COMBAT);
        this.setup(this.weapons, this.distance, this.fov, this.predict, this.moveCorrection, this.throughWalls, this.sorting);
    }

    public static ProjectHelper getInstance() {
        return Instance.get(ProjectHelper.class);
    }

    @Override
    public void deactivate() {
        this.currentTarget = null;
        this.hudTarget = null;
        this.lastHudTargetAt = 0L;
        this.lastProjectileProfile = null;
        this.lastProjectileProfileAt = 0L;
        this.currentRotation = null;
        this.serverRotation = null;
        this.isAiming = false;
        super.deactivate();
    }

    @EventHandler
    public void onInput(InputEvent event) {
    }

    @EventHandler
    public void onMotion(MotionEvent event) {
        if (!this.isAiming || this.currentRotation == null || ProjectHelper.mc.field_1724 == null) {
            return;
        }
        event.setYaw(this.currentRotation.getYaw());
        event.setPitch(this.currentRotation.getPitch());
        ProjectHelper.mc.field_1724.field_6241 = this.currentRotation.getYaw();
        ProjectHelper.mc.field_1724.field_6283 = this.currentRotation.getYaw();
        ProjectHelper.mc.field_1724.field_6259 = this.currentRotation.getYaw();
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (PlayerInteractionHelper.nullCheck() || ProjectHelper.mc.field_1724.method_6128()) {
            this.resetState();
            return;
        }
        ProjectileProfile projectileProfile = this.resolveActiveWeapon();
        if (projectileProfile != null && ProjectHelper.mc.field_1724.method_6115()) {
            this.lastProjectileProfile = projectileProfile;
            this.lastProjectileProfileAt = System.currentTimeMillis();
            this.currentTarget = this.findTarget();
            if (this.currentTarget != null) {
                this.hudTarget = this.currentTarget;
                this.lastHudTargetAt = System.currentTimeMillis();
                Turns aimRotation = this.solveAim(this.currentTarget, projectileProfile);
                if (aimRotation != null) {
                    this.currentRotation = aimRotation;
                    this.serverRotation = aimRotation;
                    this.isAiming = true;
                } else {
                    this.isAiming = false;
                }
            } else {
                this.isAiming = false;
            }
            return;
        }
        this.isAiming = false;
        this.currentRotation = null;
        this.currentTarget = null;
        if (this.hudTarget != null && System.currentTimeMillis() - this.lastHudTargetAt > 700L) {
            this.hudTarget = null;
        }
        if (this.lastProjectileProfile != null && System.currentTimeMillis() - this.lastProjectileProfileAt > 700L) {
            this.lastProjectileProfile = null;
            this.lastProjectileProfileAt = 0L;
        }
    }

    private void resetState() {
        this.currentTarget = null;
        this.hudTarget = null;
        this.lastHudTargetAt = 0L;
        this.lastProjectileProfile = null;
        this.lastProjectileProfileAt = 0L;
        this.currentRotation = null;
        this.serverRotation = null;
        this.isAiming = false;
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        class_1309 target;
        if (!event.isSend() || PlayerInteractionHelper.nullCheck()) {
            return;
        }
        class_2596<?> class_25962 = event.getPacket();
        if (!(class_25962 instanceof class_2846)) {
            return;
        }
        class_2846 actionPacket = (class_2846)class_25962;
        if (actionPacket.method_12363() != class_2846.class_2847.field_12974) {
            return;
        }
        ProjectileProfile projectileProfile = this.resolveActiveWeapon();
        if (projectileProfile == null) {
            projectileProfile = this.lastProjectileProfile;
        }
        if (projectileProfile == null) {
            return;
        }
        class_1309 class_13092 = target = this.currentTarget != null ? this.currentTarget : this.findTarget();
        if (target == null) {
            return;
        }
        Turns turns = this.solveAim(target, projectileProfile);
        if (turns == null) {
            return;
        }
        event.setCancelled(true);
        this.hudTarget = target;
        this.currentTarget = target;
        this.lastHudTargetAt = System.currentTimeMillis();
        PlayerInteractionHelper.sendPacketWithOutEvent(new class_2828.class_2830(ProjectHelper.mc.field_1724.method_23317(), ProjectHelper.mc.field_1724.method_23318(), ProjectHelper.mc.field_1724.method_23321(), turns.getYaw(), turns.getPitch(), ProjectHelper.mc.field_1724.method_24828(), ProjectHelper.mc.field_1724.field_5976));
        PlayerInteractionHelper.sendPacketWithOutEvent(actionPacket);
        this.lastProjectileProfile = null;
        this.lastProjectileProfileAt = 0L;
    }

    public class_1309 getHudTarget() {
        if (!this.isState()) {
            return null;
        }
        if (this.hudTarget == null) {
            return null;
        }
        if (!this.hudTarget.method_5805()) {
            this.hudTarget = null;
            return null;
        }
        if (ProjectHelper.mc.field_1724 == null || ProjectHelper.mc.field_1724.method_5739((class_1297)this.hudTarget) > this.distance.getValue() + 5.0f) {
            return null;
        }
        if (ProjectHelper.mc.field_1724.method_6115() && this.resolveActiveWeapon() != null) {
            return this.hudTarget;
        }
        return System.currentTimeMillis() - this.lastHudTargetAt <= 700L ? this.hudTarget : null;
    }

    private class_1309 findTarget() {
        if (ProjectHelper.mc.field_1687 == null || ProjectHelper.mc.field_1724 == null) {
            return null;
        }
        return ProjectHelper.mc.field_1687.method_18456().stream().filter(this::isValidTarget).filter(entity -> ProjectHelper.mc.field_1724.method_5739((class_1297)entity) <= this.distance.getValue()).filter(arg_0 -> ((class_746)ProjectHelper.mc.field_1724).method_6057(arg_0)).filter(this::isInsideFov).min(Comparator.comparingDouble(this::getTargetScore)).orElse(null);
    }

    private boolean isValidTarget(class_1657 entity) {
        if (entity == ProjectHelper.mc.field_1724 || !entity.method_5805() || entity.method_7325()) {
            return false;
        }
        if (FriendUtils.isFriend((class_1297)entity)) {
            return false;
        }
        if (!this.throughWalls.isValue() && !this.canSeeTarget((class_1309)entity)) {
            return false;
        }
        return !AntiBot.isBot(entity);
    }

    private boolean canSeeTarget(class_1309 entity) {
        class_243 targetPos;
        if (ProjectHelper.mc.field_1687 == null || ProjectHelper.mc.field_1724 == null) {
            return false;
        }
        class_243 playerEye = ProjectHelper.mc.field_1724.method_33571();
        class_3959 context = new class_3959(playerEye, targetPos = entity.method_5829().method_1005(), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)ProjectHelper.mc.field_1724);
        class_3965 result = ProjectHelper.mc.field_1687.method_17742(context);
        return result == null || result.method_17783() == class_239.class_240.field_1333;
    }

    private double getTargetScore(class_1309 entity) {
        class_243 point = this.getTargetPoint(entity, 0.0);
        Turns angle = MathAngle.calculateAngle(point);
        double angleDiff = Math.hypot(class_3532.method_15393((float)(angle.getYaw() - ProjectHelper.mc.field_1724.method_36454())), angle.getPitch() - ProjectHelper.mc.field_1724.method_36455());
        return switch (this.sorting.getSelected()) {
            case "\u041f\u043e \u0443\u0433\u043b\u0443" -> angleDiff;
            case "\u041f\u043e \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u0438" -> ProjectHelper.mc.field_1724.method_5739((class_1297)entity);
            case "\u041f\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044e" -> entity.method_6032();
            default -> (double)ProjectHelper.mc.field_1724.method_5739((class_1297)entity) + angleDiff * 0.05;
        };
    }

    private Turns solveAim(class_1309 target, ProjectileProfile projectileProfile) {
        class_243 baseTargetPoint = this.getTargetPoint(target, 0.0);
        double travelTicks = Math.max(1.0, ProjectHelper.mc.field_1724.method_33571().method_1022(baseTargetPoint) / Math.max(0.1, projectileProfile.speed));
        class_243 predictedPoint = baseTargetPoint;
        for (int i = 0; i < 3; ++i) {
            predictedPoint = this.getTargetPoint(target, travelTicks);
            double horizontalDistance = this.horizontalDistance(predictedPoint);
            if (horizontalDistance < 1.0E-4) {
                return MathAngle.calculateAngle(predictedPoint);
            }
            double pitch = this.solvePitchFor(predictedPoint, projectileProfile);
            double yaw = Math.toDegrees(Math.atan2(predictedPoint.field_1350 - ProjectHelper.mc.field_1724.method_33571().field_1350, predictedPoint.field_1352 - ProjectHelper.mc.field_1724.method_33571().field_1352)) - 90.0;
            travelTicks = this.estimateTravelTicks(predictedPoint, pitch, projectileProfile);
        }
        if (predictedPoint == null) {
            return null;
        }
        double pitch = this.solvePitchFor(predictedPoint, projectileProfile);
        double yaw = Math.toDegrees(Math.atan2(predictedPoint.field_1350 - ProjectHelper.mc.field_1724.method_33571().field_1350, predictedPoint.field_1352 - ProjectHelper.mc.field_1724.method_33571().field_1352)) - 90.0;
        return new Turns((float)yaw, (float)class_3532.method_15350((double)pitch, (double)-89.0, (double)89.0));
    }

    private double solvePitchFor(class_243 targetPoint, ProjectileProfile projectileProfile) {
        class_243 eyePos = ProjectHelper.mc.field_1724.method_33571();
        double dx = targetPoint.field_1352 - eyePos.field_1352;
        double dz = targetPoint.field_1350 - eyePos.field_1350;
        double dy = targetPoint.field_1351 - eyePos.field_1351;
        double velocitySquared = projectileProfile.speed * projectileProfile.speed;
        double gravity = projectileProfile.gravity;
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
        double discriminant = velocitySquared * velocitySquared - gravity * (gravity * horizontalDistance * horizontalDistance + 2.0 * dy * velocitySquared);
        if (discriminant < 0.0) {
            return -Math.toDegrees(Math.atan2(dy, horizontalDistance));
        }
        double angle = Math.atan((velocitySquared - Math.sqrt(discriminant)) / (gravity * horizontalDistance));
        return -Math.toDegrees(angle);
    }

    private double estimateTravelTicks(class_243 targetPoint, double pitch, ProjectileProfile projectileProfile) {
        class_243 eyePos = ProjectHelper.mc.field_1724.method_33571();
        double yaw = Math.toDegrees(Math.atan2(targetPoint.field_1350 - eyePos.field_1350, targetPoint.field_1352 - eyePos.field_1352)) - 90.0;
        class_243 velocity = this.createVelocity((float)yaw, (float)pitch, projectileProfile.speed);
        class_243 pos = eyePos;
        int bestTick = 1;
        double bestDistanceSq = Double.MAX_VALUE;
        for (int tick = 1; tick <= 60; ++tick) {
            double distanceSq = (pos = pos.method_1019(velocity)).method_1025(targetPoint);
            if (distanceSq < bestDistanceSq) {
                bestDistanceSq = distanceSq;
                bestTick = tick;
            }
            velocity = velocity.method_1021(projectileProfile.drag);
            velocity = new class_243(velocity.field_1352, velocity.field_1351 - projectileProfile.gravity, velocity.field_1350);
        }
        return bestTick;
    }

    private class_243 createVelocity(float yaw, float pitch, double speed) {
        double yawRad = Math.toRadians(yaw);
        double pitchRad = Math.toRadians(pitch);
        double x = -Math.sin(yawRad) * Math.cos(pitchRad) * speed;
        double y = -Math.sin(pitchRad) * speed;
        double z = Math.cos(yawRad) * Math.cos(pitchRad) * speed;
        return new class_243(x, y, z);
    }

    private class_243 getTargetPoint(class_1309 target, double futureTicks) {
        double scaledTicks = Math.min(futureTicks * (double)this.predict.getValue(), 60.0);
        class_243 velocity = target.method_18798().method_1021(scaledTicks);
        class_243 basePos = target.method_19538().method_1019(velocity);
        double aimHeight = class_3532.method_15350((double)((double)target.method_17682() * 0.75), (double)0.85, (double)1.6);
        return basePos.method_1031(0.0, aimHeight, 0.0);
    }

    private boolean isInsideFov(class_1309 entity) {
        float pitchDiff;
        class_243 point = this.getTargetPoint(entity, 0.0);
        Turns angle = MathAngle.calculateAngle(point);
        float yawDiff = Math.abs(class_3532.method_15393((float)(angle.getYaw() - ProjectHelper.mc.field_1724.method_36454())));
        return Math.hypot(yawDiff, pitchDiff = Math.abs(angle.getPitch() - ProjectHelper.mc.field_1724.method_36455())) <= (double)this.fov.getValue();
    }

    private double horizontalDistance(class_243 point) {
        class_243 eyePos = ProjectHelper.mc.field_1724.method_33571();
        double dx = point.field_1352 - eyePos.field_1352;
        double dz = point.field_1350 - eyePos.field_1350;
        return Math.sqrt(dx * dx + dz * dz);
    }

    private ProjectileProfile resolveActiveWeapon() {
        if (ProjectHelper.mc.field_1724 == null) {
            return null;
        }
        class_1799 active = ProjectHelper.mc.field_1724.method_6030();
        ProjectileProfile fromActive = ProjectileProfile.fromStack(active, ProjectHelper.mc.field_1724.method_6048(), this.weapons);
        if (fromActive != null) {
            return fromActive;
        }
        return ProjectileProfile.fromStack(ProjectHelper.mc.field_1724.method_6047(), ProjectHelper.mc.field_1724.method_6048(), this.weapons);
    }

    public MultiSelectSetting getWeapons() {
        return this.weapons;
    }

    public SliderSettings getDistance() {
        return this.distance;
    }

    public SliderSettings getFov() {
        return this.fov;
    }

    public SliderSettings getPredict() {
        return this.predict;
    }

    public BooleanSetting getMoveCorrection() {
        return this.moveCorrection;
    }

    public BooleanSetting getThroughWalls() {
        return this.throughWalls;
    }

    public SelectSetting getSorting() {
        return this.sorting;
    }

    public class_1309 getCurrentTarget() {
        return this.currentTarget;
    }

    public long getLastHudTargetAt() {
        return this.lastHudTargetAt;
    }

    public ProjectileProfile getLastProjectileProfile() {
        return this.lastProjectileProfile;
    }

    public long getLastProjectileProfileAt() {
        return this.lastProjectileProfileAt;
    }

    public Turns getCurrentRotation() {
        return this.currentRotation;
    }

    public Turns getServerRotation() {
        return this.serverRotation;
    }

    public boolean isAiming() {
        return this.isAiming;
    }

    private static final class ProjectileProfile {
        final double speed;
        final double drag;
        final double gravity;

        ProjectileProfile(double speed, double drag, double gravity) {
            this.speed = speed;
            this.drag = drag;
            this.gravity = gravity;
        }

        static ProjectileProfile fromStack(class_1799 stack, int useTicks, MultiSelectSetting weapons) {
            if (stack == null || stack.method_7960()) {
                return null;
            }
            if (stack.method_31574(class_1802.field_8102) && weapons.isSelected("\u041b\u0443\u043a")) {
                float pullProgress = (float)useTicks / 20.0f;
                pullProgress = (pullProgress * pullProgress + pullProgress * 2.0f) / 3.0f;
                if ((pullProgress = Math.min(pullProgress, 1.0f)) <= 0.1f) {
                    return null;
                }
                return new ProjectileProfile(3.0 * (double)pullProgress, 0.99, 0.05);
            }
            if (stack.method_31574(class_1802.field_8547) && weapons.isSelected("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446")) {
                if (useTicks < 10) {
                    return null;
                }
                return new ProjectileProfile(2.5, 0.99, 0.05);
            }
            if (stack.method_31574(class_1802.field_8399) && weapons.isSelected("\u0410\u0440\u0431\u0430\u043b\u0435\u0442")) {
                if (!class_1764.method_7781((class_1799)stack)) {
                    return null;
                }
                return new ProjectileProfile(3.15, 0.99, 0.05);
            }
            if (stack.method_7909() instanceof class_1823 && weapons.isSelected("\u0421\u043d\u0435\u0436\u043e\u043a")) {
                return new ProjectileProfile(1.5, 0.99, 0.03);
            }
            return null;
        }
    }
}
