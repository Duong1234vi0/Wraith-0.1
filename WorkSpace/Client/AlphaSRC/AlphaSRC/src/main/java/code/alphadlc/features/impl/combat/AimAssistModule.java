/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1743
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1829
 *  net.minecraft.class_1835
 *  net.minecraft.class_1937
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_465
 *  net.minecraft.class_5321
 *  net.minecraft.class_9362
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.player.GameUpdateEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.render.geometry.Render2D;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1829;
import net.minecraft.class_1835;
import net.minecraft.class_1937;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_465;
import net.minecraft.class_5321;
import net.minecraft.class_9362;

public class AimAssistModule
extends Module {
    private final SliderSettings distanceSetting = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e \u0446\u0435\u043b\u0438").setValue(4.0f).range(1.0f, 6.0f).step(0.1f);
    private final SliderSettings fovSetting = new SliderSettings("FOV", "\u0423\u0433\u043e\u043b \u043e\u0431\u0437\u043e\u0440\u0430 \u0434\u043b\u044f \u0437\u0430\u0445\u0432\u0430\u0442\u0430 \u0446\u0435\u043b\u0438").setValue(90.0f).range(20.0f, 360.0f).step(1.0f);
    private final BooleanSetting drawFovRadiusSetting = new BooleanSetting("\u0420\u0438\u0441\u043e\u0432\u0430\u0442\u044c \u0440\u0430\u0434\u0438\u0443\u0441 FOV", "\u0420\u0438\u0441\u0443\u0435\u0442 \u043a\u0440\u0443\u0433 \u0440\u0430\u0434\u0438\u0443\u0441\u0430 FOV \u043d\u0430 \u044d\u043a\u0440\u0430\u043d\u0435").setValue(false);
    private final SliderSettings speedSetting = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0411\u0430\u0437\u043e\u0432\u0430\u044f \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043d\u0430\u0432\u0435\u0434\u0435\u043d\u0438\u044f").setValue(4.0f).range(0.5f, 10.0f).step(0.1f);
    private final SliderSettings aimHeightSetting = new SliderSettings("\u0412\u044b\u0441\u043e\u0442\u0430 \u043f\u0440\u0438\u0446\u0435\u043b\u0430", "\u0422\u043e\u0447\u043a\u0430 \u043f\u0440\u0438\u0446\u0435\u043b\u0438\u0432\u0430\u043d\u0438\u044f \u043f\u043e \u0432\u044b\u0441\u043e\u0442\u0435 \u0446\u0435\u043b\u0438").setValue(0.85f).range(0.0f, 1.0f).step(0.05f);
    private final SelectSetting prioritySetting = new SelectSetting("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442", "\u041f\u043e \u043a\u0430\u043a\u043e\u043c\u0443 \u043f\u0430\u0440\u0430\u043c\u0435\u0442\u0440\u0443 \u0432\u044b\u0431\u0438\u0440\u0430\u0442\u044c \u0446\u0435\u043b\u044c").value("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435").selected("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f");
    private final BooleanSetting onlyWithWeaponSetting = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0441 \u043e\u0440\u0443\u0436\u0438\u0435\u043c", "\u0420\u0430\u0431\u043e\u0442\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u0441 \u043e\u0440\u0443\u0436\u0438\u0435\u043c \u0432 \u0440\u0443\u043a\u0435").setValue(false);
    private final BooleanSetting onlyPlayersSetting = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0438\u0433\u0440\u043e\u043a\u0438", "\u0426\u0435\u043b\u0438\u0442\u044c\u0441\u044f \u0442\u043e\u043b\u044c\u043a\u043e \u043d\u0430 \u0438\u0433\u0440\u043e\u043a\u043e\u0432").setValue(true);
    private final BooleanSetting noAimInInventorySetting = new BooleanSetting("\u041d\u0435 \u0446\u0435\u043b\u0438\u0442\u044c \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", "\u041d\u0435 \u043d\u0430\u0432\u043e\u0434\u0438\u0442\u044c\u0441\u044f \u043a\u043e\u0433\u0434\u0430 \u043e\u0442\u043a\u0440\u044b\u0442 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c").setValue(true);
    private final BooleanSetting hitInvisibleSetting = new BooleanSetting("\u0411\u0438\u0442\u044c \u043d\u0435\u0432\u0438\u0434\u0438\u043c\u044b\u0445", "\u0426\u0435\u043b\u0438\u0442\u044c\u0441\u044f \u043d\u0430 \u043d\u0435\u0432\u0438\u0434\u0438\u043c\u044b\u0445 \u0438\u0433\u0440\u043e\u043a\u043e\u0432").setValue(false);
    private final BooleanSetting onlyArmoredSetting = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0432 \u0431\u0440\u043e\u043d\u0435", "\u0426\u0435\u043b\u0438\u0442\u044c\u0441\u044f \u0442\u043e\u043b\u044c\u043a\u043e \u043d\u0430 \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u0432 \u0431\u0440\u043e\u043d\u0435").setValue(false);
    private final BooleanSetting ignoreNakedSetting = new BooleanSetting("\u041d\u0435 \u0431\u0438\u0442\u044c \u0433\u043e\u043b\u044b\u0445", "\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u0431\u0435\u0437 \u0431\u0440\u043e\u043d\u0438").setValue(false);
    private final BooleanSetting onlyXSetting = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u043e X", "\u041c\u0435\u043d\u044f\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e X").setValue(false);
    private final BooleanSetting multipointSetting = new BooleanSetting("\u041c\u0443\u043b\u044c\u0442\u0438\u043f\u043e\u0438\u043d\u0442", "\u0420\u0430\u043d\u0434\u043e\u043c\u043d\u044b\u0435 \u0442\u043e\u0447\u043a\u0438 \u043f\u0440\u0438\u0446\u0435\u043b\u0438\u0432\u0430\u043d\u0438\u044f \u043d\u0430 \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u0435").setValue(true);
    private final BooleanSetting wallCheckSetting = new BooleanSetting("\u041f\u0440\u043e\u0432\u0435\u0440\u043a\u0430 \u0441\u0442\u0435\u043d", "\u041d\u0435 \u043d\u0430\u0432\u043e\u0434\u0438\u0442\u044c\u0441\u044f \u043d\u0430 \u0446\u0435\u043b\u0438 \u0437\u0430 \u0431\u043b\u043e\u043a\u0430\u043c\u0438").setValue(true);
    private final BooleanSetting disableOnWorldChangeSetting = new BooleanSetting("\u0412\u044b\u043a\u043b. \u043f\u0440\u0438 \u0441\u043c\u0435\u043d\u0435 \u043c\u0438\u0440\u0430", "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u043c\u043e\u0434\u0443\u043b\u044c \u043f\u0440\u0438 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0435 \u0432 \u0434\u0440\u0443\u0433\u043e\u0439 \u043c\u0438\u0440").setValue(false);
    private final BooleanSetting microMovementsSetting = new BooleanSetting("\u041c\u0438\u043a\u0440\u043e\u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f", "\u0420\u0430\u043d\u0434\u043e\u043c\u043d\u044b\u0439 \u0448\u0443\u043c \u0438 \u0432\u0430\u0440\u0438\u0430\u0446\u0438\u044f \u0434\u043b\u044f \u043e\u0431\u0445\u043e\u0434\u0430 \u0430\u043d\u0442\u0438\u0447\u0438\u0442\u0430").setValue(true);
    private class_1297 target;
    private long targetSince;
    private long lastFrameNano;
    private double smoothedYaw;
    private double smoothedPitch;
    private double yawWavePhase;
    private double pitchWavePhase;
    private double yawWaveSpeed;
    private double pitchWaveSpeed;
    private double easePower;
    private double speedVariance;
    private double closeBoost;
    private double heightNoise;
    private long lastHeightNoiseTime;
    private int easeType;
    private double mpCurX;
    private double mpCurY;
    private double mpCurZ;
    private double mpTargetX;
    private double mpTargetY;
    private double mpTargetZ;
    private double mpLerpSpeed;
    private long mpNextTime;
    private class_5321<class_1937> lastDimension;
    private static final double FIXED_STEP = 0.004166666666666667;

    public AimAssistModule() {
        super("AimAssist", "Aim Assist", ModuleCategory.COMBAT);
        this.ignoreNakedSetting.visible(() -> !this.onlyArmoredSetting.isValue());
        this.setup(this.distanceSetting, this.fovSetting, this.drawFovRadiusSetting, this.speedSetting, this.aimHeightSetting, this.prioritySetting, this.onlyWithWeaponSetting, this.onlyPlayersSetting, this.noAimInInventorySetting, this.hitInvisibleSetting, this.onlyArmoredSetting, this.ignoreNakedSetting, this.onlyXSetting, this.multipointSetting, this.wallCheckSetting, this.disableOnWorldChangeSetting, this.microMovementsSetting);
    }

    @Override
    public void activate() {
        this.reset();
    }

    @Override
    public void deactivate() {
        this.reset();
    }

    private void reset() {
        this.target = null;
        this.targetSince = 0L;
        this.lastFrameNano = 0L;
        this.smoothedYaw = 0.0;
        this.smoothedPitch = 0.0;
        this.lastDimension = null;
        this.mpCurX = 0.0;
        this.mpCurY = 0.0;
        this.mpCurZ = 0.0;
        this.mpTargetX = 0.0;
        this.mpTargetY = 0.0;
        this.mpTargetZ = 0.0;
        this.mpNextTime = 0L;
        this.randomizeWave();
    }

    private void randomizeWave() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        this.yawWavePhase = random.nextDouble(Math.PI * 2);
        this.pitchWavePhase = random.nextDouble(Math.PI * 2);
        this.yawWaveSpeed = random.nextDouble(0.08, 0.25);
        this.pitchWaveSpeed = random.nextDouble(0.06, 0.2);
        this.easePower = random.nextDouble(1.8, 3.5);
        this.speedVariance = random.nextDouble(0.75, 1.25);
        this.closeBoost = random.nextDouble(-0.1, 0.2);
        this.easeType = random.nextInt(3);
        this.heightNoise = random.nextDouble(-0.15, 0.1);
        this.lastHeightNoiseTime = System.currentTimeMillis();
        this.mpLerpSpeed = random.nextDouble(0.025, 0.065);
        this.randomizeMultipoint(random);
    }

    private void randomizeMultipoint(ThreadLocalRandom random) {
        this.mpTargetX = random.nextDouble(-0.35, 0.35);
        this.mpTargetY = random.nextDouble(-0.12, 0.12);
        this.mpTargetZ = random.nextDouble(-0.35, 0.35);
        this.mpNextTime = System.currentTimeMillis() + random.nextLong(300L, 900L);
        this.mpLerpSpeed = random.nextDouble(0.02, 0.07);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (AimAssistModule.mc.field_1724 == null || AimAssistModule.mc.field_1687 == null) {
            return;
        }
        class_5321 dimension = AimAssistModule.mc.field_1687.method_27983();
        if (this.lastDimension == null) {
            this.lastDimension = dimension;
        } else if (!this.lastDimension.equals((Object)dimension)) {
            if (this.disableOnWorldChangeSetting.isValue()) {
                this.setState(false);
                return;
            }
            this.reset();
            this.lastDimension = dimension;
            return;
        }
        if (!this.canAim()) {
            this.target = null;
            return;
        }
        class_1297 found = this.findTarget();
        if (found == null) {
            if (this.target != null) {
                this.reset();
            }
            return;
        }
        if (found != this.target) {
            this.target = found;
            this.targetSince = System.currentTimeMillis();
            this.randomizeWave();
        }
        if (!this.microMovementsSetting.isValue()) {
            return;
        }
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long time = System.currentTimeMillis();
        if (time - this.lastHeightNoiseTime > random.nextLong(180L, 450L)) {
            this.heightNoise += random.nextDouble(-0.06, 0.06);
            this.heightNoise = class_3532.method_15350((double)this.heightNoise, (double)-0.2, (double)0.15);
            this.lastHeightNoiseTime = time;
        }
        if (this.multipointSetting.isValue() && time >= this.mpNextTime) {
            this.randomizeMultipoint(random);
        }
    }

    private boolean canAim() {
        if (AimAssistModule.mc.field_1724 == null || AimAssistModule.mc.field_1687 == null) {
            return false;
        }
        if (this.onlyWithWeaponSetting.isValue() && !this.hasWeapon()) {
            return false;
        }
        return !this.noAimInInventorySetting.isValue() || !(AimAssistModule.mc.field_1755 instanceof class_465);
    }

    @EventHandler
    public void onGameUpdate(GameUpdateEvent event) {
        double dist;
        class_1309 living;
        AttackAura aura = AttackAura.getInstance();
        if (aura != null && aura.isState() && aura.getTarget() != null) {
            return;
        }
        if (this.target == null || !this.canAim()) {
            return;
        }
        class_1297 class_12972 = this.target;
        if (!(class_12972 instanceof class_1309) || (living = (class_1309)class_12972).method_29504() || living.method_6032() <= 0.0f) {
            return;
        }
        if ((double)AimAssistModule.mc.field_1724.method_5739(this.target) > (double)this.distanceSetting.getValue() + 1.0) {
            return;
        }
        boolean micro = this.microMovementsSetting.isValue();
        boolean onlyX = this.onlyXSetting.isValue();
        if (this.multipointSetting.isValue()) {
            this.mpCurX += (this.mpTargetX - this.mpCurX) * this.mpLerpSpeed;
            this.mpCurY += (this.mpTargetY - this.mpCurY) * this.mpLerpSpeed;
            this.mpCurZ += (this.mpTargetZ - this.mpCurZ) * this.mpLerpSpeed;
        }
        double[] angles = this.computeAngles(this.target);
        float yaw = AimAssistModule.mc.field_1724.method_36454();
        float pitch = AimAssistModule.mc.field_1724.method_36455();
        double yawDiff = AimAssistModule.wrap(angles[0] - (double)yaw);
        double pitchDiff = onlyX ? 0.0 : angles[1] - (double)pitch;
        double d = dist = onlyX ? Math.abs(yawDiff) : Math.hypot(yawDiff, pitchDiff);
        if (dist < 0.02) {
            return;
        }
        double speed = this.speedSetting.getValue();
        double factor = 1.0 - Math.exp(-speed * 0.004166666666666667);
        double progress = class_3532.method_15350((double)((double)(System.currentTimeMillis() - this.targetSince) / 200.0), (double)0.0, (double)1.0);
        factor *= 0.4 + 0.6 * progress;
        factor = class_3532.method_15350((double)factor, (double)0.0, (double)1.0);
        double yawStep = yawDiff * factor;
        double pitchStep = pitchDiff * factor;
        if (micro) {
            this.yawWavePhase += this.yawWaveSpeed * 0.004166666666666667 * 60.0;
            this.pitchWavePhase += this.pitchWaveSpeed * 0.004166666666666667 * 60.0;
            double amp = Math.min(Math.abs(yawDiff) * 0.06, 0.12);
            yawStep += Math.sin(this.yawWavePhase) * amp;
            pitchStep += Math.cos(this.pitchWavePhase) * amp * 0.7;
        }
        this.smoothedYaw = yawStep;
        this.smoothedPitch = pitchStep;
        float newYaw = yaw + (float)yawStep;
        float newPitch = class_3532.method_15363((float)(pitch + (float)pitchStep), (float)-90.0f, (float)90.0f);
        if (!(Float.isNaN(newYaw) || Float.isNaN(newPitch) || Float.isInfinite(newYaw) || Float.isInfinite(newPitch))) {
            AimAssistModule.mc.field_1724.method_36456(newYaw);
            AimAssistModule.mc.field_1724.method_36457(newPitch);
        }
    }

    @EventHandler
    public void onDraw(DrawEvent event) {
        int height;
        if (!this.isState() || AimAssistModule.mc.field_1724 == null || AimAssistModule.mc.field_1687 == null) {
            return;
        }
        if (!this.drawFovRadiusSetting.isValue() || AimAssistModule.mc.field_1755 != null) {
            return;
        }
        class_332 context = event.getDrawContext();
        int width = context.method_51421();
        float radius = this.fovRadius(width, height = context.method_51443());
        if (radius < 1.0f) {
            return;
        }
        float centerX = (float)width * 0.5f;
        float centerY = (float)height * 0.5f;
        int color = this.target != null ? -866779307 : -855638017;
        Render2D.drawCircleOutline(context.method_51448(), centerX, centerY, radius, 1.5f, color);
    }

    private float fovRadius(int width, int height) {
        double gameFov;
        float max = Math.max(0.0f, (float)Math.min(width, height) * 0.5f - 2.0f);
        if (max <= 0.0f) {
            return 0.0f;
        }
        double fov = this.fovSetting.getValue();
        if (fov >= (gameFov = class_3532.method_15350((double)((Integer)AimAssistModule.mc.field_1690.method_41808().method_41753()).doubleValue(), (double)30.0, (double)170.0))) {
            return max;
        }
        double halfFov = class_3532.method_15350((double)(fov * 0.5), (double)0.1, (double)89.9);
        double halfGame = class_3532.method_15350((double)(gameFov * 0.5), (double)1.0, (double)89.9);
        double ratio = Math.tan(Math.toRadians(halfFov)) / Math.tan(Math.toRadians(halfGame));
        double radius = ratio * ((double)Math.min(width, height) * 0.5);
        return (float)class_3532.method_15350((double)radius, (double)0.0, (double)max);
    }

    private class_1297 findTarget() {
        if (AimAssistModule.mc.field_1687 == null || AimAssistModule.mc.field_1724 == null) {
            return null;
        }
        double maxDistance = this.distanceSetting.getValue();
        double halfFov = (double)this.fovSetting.getValue() / 2.0;
        boolean byHealth = this.prioritySetting.isSelected("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435");
        class_1297 best = null;
        double bestScore = Double.MAX_VALUE;
        class_243 eyes = AimAssistModule.mc.field_1724.method_33571();
        class_243 look = AimAssistModule.mc.field_1724.method_5828(1.0f);
        for (class_1297 entity : AimAssistModule.mc.field_1687.method_18112()) {
            double angle;
            class_243 point;
            class_1657 player;
            class_1309 living;
            if (entity == AimAssistModule.mc.field_1724 || !(entity instanceof class_1309) || (living = (class_1309)entity).method_29504() || living.method_6032() <= 0.0f || this.onlyPlayersSetting.isValue() && !(entity instanceof class_1657) || entity instanceof class_1657 && FriendUtils.isFriend((class_1297)(player = (class_1657)entity)) || !this.hitInvisibleSetting.isValue() && living.method_5767() || (!this.onlyArmoredSetting.isValue() ? this.ignoreNakedSetting.isValue() && !this.hasArmor(living) : !this.hasArmor(living))) continue;
            double dist = AimAssistModule.mc.field_1724.method_5739(entity);
            if (dist > maxDistance || dist < 0.5 || this.wallCheckSetting.isValue() && this.isBlocked(eyes, point = entity.method_19538().method_1031(0.0, (double)(entity.method_17682() * this.aimHeightSetting.getValue()), 0.0)) || (angle = this.angleTo(eyes, look, entity)) > halfFov) continue;
            double d = byHealth ? (double)living.method_6032() : dist;
            double score = d;
            if (!(score < bestScore)) continue;
            bestScore = score;
            best = entity;
        }
        return best;
    }

    private double angleTo(class_243 eyes, class_243 look, class_1297 entity) {
        class_243 point = entity.method_19538().method_1031(0.0, (double)(entity.method_17682() * this.aimHeightSetting.getValue()), 0.0);
        class_243 delta = point.method_1020(eyes);
        double length = delta.method_1033();
        if (length < 0.001) {
            return 0.0;
        }
        delta = delta.method_1021(1.0 / length);
        double dot = look.field_1352 * delta.field_1352 + look.field_1351 * delta.field_1351 + look.field_1350 * delta.field_1350;
        dot = class_3532.method_15350((double)dot, (double)-1.0, (double)1.0);
        return Math.toDegrees(Math.acos(dot));
    }

    private double[] computeAngles(class_1297 entity) {
        class_243 eyes = AimAssistModule.mc.field_1724.method_33571();
        boolean micro = this.microMovementsSetting.isValue();
        double height = this.aimHeightSetting.getValue();
        double heightPoint = micro ? height + this.heightNoise : height;
        double offsetX = 0.0;
        double offsetY = 0.0;
        double offsetZ = 0.0;
        if (this.multipointSetting.isValue()) {
            double halfWidth = (double)entity.method_17681() * 0.5;
            offsetX = this.mpCurX * halfWidth;
            offsetZ = this.mpCurZ * halfWidth;
            offsetY = this.mpCurY;
        }
        double finalHeight = class_3532.method_15350((double)(heightPoint + offsetY), (double)0.05, (double)0.95);
        class_243 point = entity.method_19538().method_1031(offsetX, (double)entity.method_17682() * finalHeight, offsetZ);
        double dx = point.field_1352 - eyes.field_1352;
        double dy = point.field_1351 - eyes.field_1351;
        double dz = point.field_1350 - eyes.field_1350;
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        double yaw = horizontal < 0.001 ? (double)AimAssistModule.mc.field_1724.method_36454() : Math.toDegrees(Math.atan2(-dx, dz));
        double full = Math.sqrt(dx * dx + dy * dy + dz * dz);
        double pitch = full < 0.001 ? (double)AimAssistModule.mc.field_1724.method_36455() : -Math.toDegrees(Math.asin(class_3532.method_15350((double)(dy / full), (double)-1.0, (double)1.0)));
        return new double[]{yaw, pitch};
    }

    private boolean hasWeapon() {
        if (AimAssistModule.mc.field_1724 == null) {
            return false;
        }
        class_1792 item = AimAssistModule.mc.field_1724.method_6047().method_7909();
        return item instanceof class_1829 || item instanceof class_1743 || item instanceof class_1835 || item instanceof class_9362;
    }

    private boolean hasArmor(class_1309 living) {
        for (class_1799 stack : living.method_5661()) {
            if (stack.method_7960()) continue;
            return true;
        }
        return false;
    }

    private boolean isBlocked(class_243 from, class_243 to) {
        if (AimAssistModule.mc.field_1687 == null) {
            return false;
        }
        class_3959 context = new class_3959(from, to, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)AimAssistModule.mc.field_1724);
        class_3965 result = AimAssistModule.mc.field_1687.method_17742(context);
        return result != null && result.method_17783() == class_239.class_240.field_1332;
    }

    private static double wrap(double angle) {
        return class_3532.method_15338((double)angle);
    }
}
