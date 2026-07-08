/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.player.AttackEvent;
import code.alphadlc.events.player.BoundingBoxControlEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.features.aura.rotations.constructor.RotateConstructor;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.features.combat.TargetFinder;
import code.alphadlc.utils.math.task.TaskPriority;
import java.util.Random;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class HitBox
extends Module {
    private final SliderSettings attackRange = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043f\u043e\u0438\u0441\u043a\u0430 \u0446\u0435\u043b\u0435\u0439").setValue(3.5f).range(1.0f, 6.0f);
    private final SliderSettings expandSize = new SliderSettings("\u0420\u0430\u0441\u0448\u0438\u0440\u0435\u043d\u0438\u0435", "\u0420\u0430\u0437\u043c\u0435\u0440 \u0440\u0430\u0441\u0448\u0438\u0440\u0435\u043d\u0438\u044f \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u043e\u0432").setValue(0.5f).range(0.1f, 2.0f);
    private final SelectSetting hitboxMode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u0430", "\u0422\u0438\u043f \u0440\u0430\u0441\u0448\u0438\u0440\u0435\u043d\u0438\u044f \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u0430").value("\u0423\u043c\u043d\u0430\u044f", "\u0421\u0442\u0430\u043d\u0434\u0430\u0440\u0442\u043d\u0430\u044f").selected("\u0423\u043c\u043d\u0430\u044f");
    private final SliderSettings standardExpandXZ = new SliderSettings("\u0420\u0430\u0441\u0448\u0438\u0440\u0435\u043d\u0438\u0435 XZ", "\u0420\u0430\u0441\u0448\u0438\u0440\u0435\u043d\u0438\u0435 \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u0430 \u043f\u043e X \u0438 Z").setValue(0.5f).range(0.1f, 2.0f).visible(() -> this.hitboxMode.isSelected("\u0421\u0442\u0430\u043d\u0434\u0430\u0440\u0442\u043d\u0430\u044f"));
    private final SliderSettings standardExpandY = new SliderSettings("\u0420\u0430\u0441\u0448\u0438\u0440\u0435\u043d\u0438\u0435 Y", "\u0420\u0430\u0441\u0448\u0438\u0440\u0435\u043d\u0438\u0435 \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u0430 \u043f\u043e Y").setValue(0.5f).range(0.1f, 2.0f).visible(() -> this.hitboxMode.isSelected("\u0421\u0442\u0430\u043d\u0434\u0430\u0440\u0442\u043d\u0430\u044f"));
    private final SelectSetting rotationMode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c \u0440\u043e\u0442\u0430\u0446\u0438\u0438", "\u0422\u0438\u043f \u043d\u0430\u0432\u0435\u0434\u0435\u043d\u0438\u044f \u043d\u0430 \u0445\u0438\u0442\u0431\u043e\u043a\u0441").value("\u041f\u043b\u0430\u0432\u043d\u0430\u044f", "\u0420\u0435\u0437\u043a\u0430\u044f").selected("\u041f\u043b\u0430\u0432\u043d\u0430\u044f");
    private final MultiSelectSetting targetType = new MultiSelectSetting("\u0422\u0438\u043f \u0442\u0430\u0440\u0433\u0435\u0442\u0430", "\u0424\u0438\u043b\u044c\u0442\u0440\u0443\u0435\u0442 \u0446\u0435\u043b\u0438 \u043f\u043e \u0442\u0438\u043f\u0443").value("\u0418\u0433\u0440\u043e\u043a\u0438", "\u041c\u043e\u0431\u044b", "\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0435", "\u0414\u0440\u0443\u0437\u044c\u044f").selected("\u0418\u0433\u0440\u043e\u043a\u0438", "\u041c\u043e\u0431\u044b");
    private final BooleanSetting ignoreWalls = new BooleanSetting("\u0421\u043a\u0432\u043e\u0437\u044c \u0441\u0442\u0435\u043d\u044b", "\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0435\u0442 \u0430\u0442\u0430\u043a\u043e\u0432\u0430\u0442\u044c \u0447\u0435\u0440\u0435\u0437 \u0441\u0442\u0435\u043d\u044b").setValue(true);
    private final BooleanSetting antiReach = new BooleanSetting("\u0410\u043d\u0442\u0438-\u0420\u0438\u0447", "\u041e\u0442\u043c\u0435\u043d\u044f\u0435\u0442 \u0430\u0442\u0430\u043a\u0443 \u0435\u0441\u043b\u0438 \u0441\u043b\u0438\u0448\u043a\u043e\u043c \u0434\u0430\u043b\u0435\u043a\u043e \u043e\u0442 \u043e\u0440\u0438\u0433\u0438\u043d\u0430\u043b\u044c\u043d\u043e\u0433\u043e \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u0430").setValue(false);
    private final SliderSettings antiReachDistance = new SliderSettings("\u041c\u0430\u043a\u0441. \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043b\u044f \u0410\u043d\u0442\u0438-\u0420\u0438\u0447").setValue(3.0f).range(1.0f, 6.0f).visible(() -> this.antiReach.isValue());
    private final ColorSetting hitboxColor = new ColorSetting("\u0426\u0432\u0435\u0442 \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u0430", "\u0426\u0432\u0435\u0442 \u0440\u0430\u0441\u0448\u0438\u0440\u0435\u043d\u043d\u043e\u0433\u043e \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u0430").value(1684313855);
    private final BooleanSetting renderHitbox = new BooleanSetting("\u0420\u0435\u043d\u0434\u0435\u0440", "\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0435\u0442 \u0440\u0430\u0441\u0448\u0438\u0440\u0435\u043d\u043d\u044b\u0435 \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u044b").setValue(true);
    private final TargetFinder targetSelector = new TargetFinder();
    private final Random random = new Random();
    private final TurnsConfig rotationConfig = new TurnsConfig(new RotateConstructor(this, "HitBox"){

        @Override
        public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
            return targetAngle;
        }

        @Override
        public class_243 randomValue() {
            return new class_243(0.0, 0.0, 0.0);
        }
    }, true, true);
    private class_243 mpCurrent = class_243.field_1353;
    private float mpOrbit = 0.0f;
    private long mpLastUpdate = 0L;
    private int mpLastTargetId = -1;
    private float smoothedYaw;
    private float smoothedPitch;
    private int smoothTargetId = -1;
    private int snapTargetId = -1;
    private long snapEndTime = 0L;
    private int hitFlashTargetId = -1;
    private long hitFlashEndTime = 0L;

    public static HitBox getInstance() {
        return Instance.get(HitBox.class);
    }

    public HitBox() {
        super("HitBox", ModuleCategory.COMBAT);
        this.setup(this.attackRange, this.expandSize, this.hitboxMode, this.standardExpandXZ, this.standardExpandY, this.rotationMode, this.targetType, this.ignoreWalls, this.antiReach, this.antiReachDistance, this.hitboxColor, this.renderHitbox);
    }

    @Override
    public void deactivate() {
        this.resetRotation();
        super.deactivate();
    }

    @EventHandler
    public void onRotationUpdate(RotationUpdateEvent e) {
        if (!this.state) {
            return;
        }
        if (e.getType() != 0 || HitBox.mc.field_1724 == null || HitBox.mc.field_1687 == null) {
            return;
        }
        this.targetSelector.searchTargets(HitBox.mc.field_1687.method_18112(), this.attackRange.getValue(), 360.0f, this.ignoreWalls.isValue());
        TargetFinder.EntityFilter filter = new TargetFinder.EntityFilter(this.targetType.getSelected());
        this.targetSelector.validateTarget(filter::isValid);
        class_1309 target = this.targetSelector.getCurrentTarget();
        if (target != null && this.isLookingAtExpandedHitbox(target) && !this.isAuraEnabled()) {
            if (this.isSharpMode()) {
                if (this.shouldApplySharpRotation(target)) {
                    this.rotateSharp(target);
                } else {
                    this.resetRotation();
                }
            } else {
                this.rotateSmooth(target);
            }
        } else {
            this.resetRotation();
        }
    }

    @EventHandler
    public void onAttack(AttackEvent e) {
        class_243 closest;
        class_1309 living;
        block9: {
            block8: {
                if (!this.state) {
                    return;
                }
                class_1297 class_12972 = e.getEntity();
                if (!(class_12972 instanceof class_1309)) break block8;
                living = (class_1309)class_12972;
                if (HitBox.mc.field_1724 != null) break block9;
            }
            return;
        }
        if (this.isSharpMode()) {
            this.snapTargetId = living.method_5628();
            this.snapEndTime = System.currentTimeMillis() + 180L;
        }
        if (!this.isAuraEnabled() && this.isLookingAtExpandedHitbox(living)) {
            this.hitFlashTargetId = living.method_5628();
            this.hitFlashEndTime = System.currentTimeMillis() + 220L;
        }
        if (!this.antiReach.isValue()) {
            return;
        }
        class_238 originalBox = living.method_5864().method_18386().method_30757(living.method_19538());
        class_243 eyes = HitBox.mc.field_1724.method_33571();
        if (eyes.method_1022(closest = this.getClosestPoint(eyes, originalBox)) > (double)this.antiReachDistance.getValue()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBoundingBox(BoundingBoxControlEvent e) {
        class_1309 living;
        if (!this.state || !this.isStandardHitboxMode()) {
            return;
        }
        class_1297 class_12972 = e.getEntity();
        if (!(class_12972 instanceof class_1309) || (living = (class_1309)class_12972) == HitBox.mc.field_1724 || !living.method_5805()) {
            return;
        }
        TargetFinder.EntityFilter filter = new TargetFinder.EntityFilter(this.targetType.getSelected());
        if (!filter.isValid(living)) {
            return;
        }
        e.setBox(this.getStandardExpandedBox(living));
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (!this.state || !this.renderHitbox.isValue() || HitBox.mc.field_1687 == null) {
            return;
        }
        TargetFinder.EntityFilter filter = new TargetFinder.EntityFilter(this.targetType.getSelected());
        for (class_1297 entity : HitBox.mc.field_1687.method_18112()) {
            class_1309 living;
            if (!(entity instanceof class_1309) || (living = (class_1309)entity) == HitBox.mc.field_1724 || !living.method_5805() || !filter.isValid(living)) continue;
            class_238 box = this.getExpandedBox(living);
            Render3D.drawBox(e.getStack().method_23760(), box, this.hitboxColor.getColor(), 1.5f, true, true, true);
            if (!this.isHitFlashing(living)) continue;
            int flashColor = ColorAssist.setAlpha(this.hitboxColor.getColor(), 255);
            Render3D.drawBox(e.getStack().method_23760(), box, flashColor, 1.8f, true, false, true);
            Render3D.drawBox(e.getStack().method_23760(), box, flashColor, 1.0f, false, true, true);
            Render3D.drawBox(e.getStack().method_23760(), box, flashColor, 1.0f, false, true, true);
            Render3D.drawBox(e.getStack().method_23760(), box, flashColor, 1.0f, false, true, true);
        }
    }

    private void rotateSmooth(class_1309 target) {
        long now = System.currentTimeMillis();
        int tid = target.method_5628();
        if (tid != this.mpLastTargetId) {
            this.mpLastTargetId = tid;
            this.mpCurrent = class_243.field_1353;
            this.mpOrbit = this.random.nextFloat() * ((float)Math.PI * 2);
            this.mpLastUpdate = now;
            Turns current = TurnsConnection.INSTANCE.getRotation();
            if (current == null && HitBox.mc.field_1724 != null) {
                current = new Turns(HitBox.mc.field_1724.method_36454(), HitBox.mc.field_1724.method_36455());
            }
            if (current != null) {
                this.smoothedYaw = current.getYaw();
                this.smoothedPitch = current.getPitch();
            }
        }
        long dtMs = Math.min(now - this.mpLastUpdate, 60L);
        this.mpLastUpdate = now;
        float dtScale = (float)dtMs / 50.0f;
        this.mpOrbit += (0.36f + (this.random.nextFloat() - 0.5f) * 0.036f) * dtScale;
        class_243 orbitPoint = this.calculateOrbitPoint(this.mpOrbit);
        if (this.mpCurrent == class_243.field_1353) {
            this.mpCurrent = orbitPoint;
        } else {
            float alpha = class_3532.method_15363((float)((float)dtMs / 120.0f), (float)0.0f, (float)1.0f);
            alpha = alpha * alpha * (3.0f - 2.0f * alpha);
            this.mpCurrent = new class_243(class_3532.method_16436((double)alpha, (double)this.mpCurrent.field_1352, (double)orbitPoint.field_1352), class_3532.method_16436((double)alpha, (double)this.mpCurrent.field_1351, (double)orbitPoint.field_1351), class_3532.method_16436((double)alpha, (double)this.mpCurrent.field_1350, (double)orbitPoint.field_1350));
        }
        class_238 box = this.getExpandedBox(target);
        if (this.isStandardHitboxMode()) {
            this.rotateToStandardBox(target, box);
            return;
        }
        double x = box.field_1323 + (box.field_1320 - box.field_1323) * (0.5 + this.mpCurrent.field_1352 * 0.5);
        double y = box.field_1322 + (box.field_1325 - box.field_1322) * class_3532.method_15350((double)this.mpCurrent.field_1351, (double)0.36, (double)0.82);
        double z = box.field_1321 + (box.field_1324 - box.field_1321) * (0.5 + this.mpCurrent.field_1350 * 0.5);
        class_243 targetPoint = new class_243(x, y, z);
        Turns targetAngle = MathAngle.calculateAngle(targetPoint);
        float yawError = class_3532.method_15393((float)(targetAngle.getYaw() - this.smoothedYaw));
        float pitchError = targetAngle.getPitch() - this.smoothedPitch;
        float yawSpeed = 60.0f * dtScale;
        float pitchSpeed = 45.0f * dtScale;
        this.smoothedYaw += class_3532.method_15363((float)yawError, (float)(-yawSpeed), (float)yawSpeed);
        this.smoothedPitch += class_3532.method_15363((float)pitchError, (float)(-pitchSpeed), (float)pitchSpeed);
        float jitterYaw = (float)(Math.sin((double)now / 40.0) * 1.5);
        float jitterPitch = (float)(Math.cos((double)now / 40.0) * 0.8);
        Turns finalRotation = new Turns(this.smoothedYaw + jitterYaw, this.smoothedPitch + jitterPitch);
        TurnsConnection.INSTANCE.rotateTo(finalRotation, 1, this.rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
        this.smoothTargetId = tid;
    }

    private void rotateSharp(class_1309 target) {
        if (HitBox.mc.field_1724 == null) {
            return;
        }
        class_238 box = this.getExpandedBox(target);
        class_243 targetPoint = this.getClosestPoint(HitBox.mc.field_1724.method_33571(), box);
        Turns targetAngle = MathAngle.calculateAngle(targetPoint);
        Turns.VecRotation finalRotation = new Turns.VecRotation(targetAngle, targetAngle.toVector());
        TurnsConnection.INSTANCE.rotateTo(finalRotation, target, 1, this.rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
        this.smoothTargetId = target.method_5628();
    }

    private class_243 calculateOrbitPoint(float o) {
        float y = 0.56f + 0.1f * (float)Math.sin(o * 0.74f + 0.9f) + 0.06f * (float)Math.sin(o * 1.28f + 2.2f) - 0.05f * (float)(0.5 + 0.5 * Math.sin(o * 0.35f + 1.7f));
        float x = 0.22f * (float)Math.sin(o) + 0.06f * (float)Math.sin(o * 1.85f + 0.25f);
        float z = 0.08f * (float)Math.cos(o * 0.92f + 0.4f) + 0.03f * (float)Math.sin(o * 1.35f + 1.1f);
        return new class_243((double)x, (double)y, (double)z);
    }

    private void resetRotation() {
        if (this.smoothTargetId != -1) {
            TurnsConnection.INSTANCE.clear();
            TurnsConnection.INSTANCE.setRotation(null);
            this.smoothTargetId = -1;
            this.mpLastTargetId = -1;
            this.mpCurrent = class_243.field_1353;
        }
        if (!this.isSharpMode()) {
            this.snapTargetId = -1;
            this.snapEndTime = 0L;
        }
        if (this.hitFlashEndTime < System.currentTimeMillis()) {
            this.hitFlashTargetId = -1;
            this.hitFlashEndTime = 0L;
        }
    }

    private boolean isLookingAtExpandedHitbox(class_1309 target) {
        if (HitBox.mc.field_1724 == null) {
            return false;
        }
        float yaw = HitBox.mc.field_1724.method_36454();
        float pitch = HitBox.mc.field_1724.method_36455();
        float yawRad = (float)Math.toRadians(yaw);
        float pitchRad = (float)Math.toRadians(pitch);
        float x = (float)(-Math.sin(yawRad) * Math.cos(pitchRad));
        float y = (float)(-Math.sin(pitchRad));
        float z = (float)(Math.cos(yawRad) * Math.cos(pitchRad));
        class_243 direction = new class_243((double)x, (double)y, (double)z);
        class_243 eyes = HitBox.mc.field_1724.method_33571();
        class_238 expandedBox = this.getExpandedBox(target);
        if (expandedBox.method_1006(eyes)) {
            return true;
        }
        class_243 end = eyes.method_1019(direction.method_1021((double)this.attackRange.getValue() + 2.0));
        return expandedBox.method_992(eyes, end).isPresent();
    }

    private boolean isAuraEnabled() {
        AttackAura aura = AttackAura.getInstance();
        return aura != null && aura.state;
    }

    private boolean isStandardHitboxMode() {
        return this.hitboxMode.isSelected("\u0421\u0442\u0430\u043d\u0434\u0430\u0440\u0442\u043d\u0430\u044f");
    }

    private boolean isSharpMode() {
        return this.rotationMode.isSelected("\u0420\u0435\u0437\u043a\u0430\u044f");
    }

    private boolean shouldApplySharpRotation(class_1309 target) {
        long now = System.currentTimeMillis();
        if (target.method_5628() != this.snapTargetId) {
            return false;
        }
        if (now > this.snapEndTime) {
            this.snapTargetId = -1;
            this.snapEndTime = 0L;
            return false;
        }
        return true;
    }

    private boolean isHitFlashing(class_1309 target) {
        long now = System.currentTimeMillis();
        if (target.method_5628() != this.hitFlashTargetId) {
            return false;
        }
        if (now > this.hitFlashEndTime) {
            this.hitFlashTargetId = -1;
            this.hitFlashEndTime = 0L;
            return false;
        }
        return true;
    }

    private class_243 getClosestPoint(class_243 point, class_238 box) {
        return new class_243(class_3532.method_15350((double)point.field_1352, (double)box.field_1323, (double)box.field_1320), class_3532.method_15350((double)point.field_1351, (double)box.field_1322, (double)box.field_1325), class_3532.method_15350((double)point.field_1350, (double)box.field_1321, (double)box.field_1324));
    }

    private class_238 getExpandedBox(class_1309 target) {
        if (this.isStandardHitboxMode()) {
            return this.getStandardExpandedBox(target);
        }
        return this.getOriginalBox(target).method_1014((double)this.expandSize.getValue());
    }

    private class_238 getStandardExpandedBox(class_1309 target) {
        return this.getOriginalBox(target).method_1009((double)this.standardExpandXZ.getValue(), (double)this.standardExpandY.getValue(), (double)this.standardExpandXZ.getValue());
    }

    private class_238 getOriginalBox(class_1309 target) {
        return target.method_5864().method_18386().method_30757(target.method_19538());
    }

    private void rotateToStandardBox(class_1309 target, class_238 box) {
        if (HitBox.mc.field_1724 == null) {
            return;
        }
        class_243 targetPoint = this.getClosestPoint(HitBox.mc.field_1724.method_33571(), box);
        Turns targetAngle = MathAngle.calculateAngle(targetPoint);
        float yawError = class_3532.method_15393((float)(targetAngle.getYaw() - this.smoothedYaw));
        float pitchError = targetAngle.getPitch() - this.smoothedPitch;
        this.smoothedYaw += class_3532.method_15363((float)yawError, (float)-72.0f, (float)72.0f);
        this.smoothedPitch += class_3532.method_15363((float)pitchError, (float)-54.0f, (float)54.0f);
        Turns finalRotation = new Turns(this.smoothedYaw, this.smoothedPitch);
        TurnsConnection.INSTANCE.rotateTo(finalRotation, 1, this.rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
        this.smoothTargetId = target.method_5628();
    }

    public SliderSettings getAttackRange() {
        return this.attackRange;
    }

    public SliderSettings getExpandSize() {
        return this.expandSize;
    }

    public SelectSetting getHitboxMode() {
        return this.hitboxMode;
    }

    public SliderSettings getStandardExpandXZ() {
        return this.standardExpandXZ;
    }

    public SliderSettings getStandardExpandY() {
        return this.standardExpandY;
    }

    public SelectSetting getRotationMode() {
        return this.rotationMode;
    }

    public MultiSelectSetting getTargetType() {
        return this.targetType;
    }

    public BooleanSetting getIgnoreWalls() {
        return this.ignoreWalls;
    }

    public BooleanSetting getAntiReach() {
        return this.antiReach;
    }

    public SliderSettings getAntiReachDistance() {
        return this.antiReachDistance;
    }

    public ColorSetting getHitboxColor() {
        return this.hitboxColor;
    }

    public BooleanSetting getRenderHitbox() {
        return this.renderHitbox;
    }

    public TargetFinder getTargetSelector() {
        return this.targetSelector;
    }

    public Random getRandom() {
        return this.random;
    }

    public TurnsConfig getRotationConfig() {
        return this.rotationConfig;
    }

    public class_243 getMpCurrent() {
        return this.mpCurrent;
    }

    public float getMpOrbit() {
        return this.mpOrbit;
    }

    public long getMpLastUpdate() {
        return this.mpLastUpdate;
    }

    public int getMpLastTargetId() {
        return this.mpLastTargetId;
    }

    public float getSmoothedYaw() {
        return this.smoothedYaw;
    }

    public float getSmoothedPitch() {
        return this.smoothedPitch;
    }

    public int getSmoothTargetId() {
        return this.smoothTargetId;
    }

    public int getSnapTargetId() {
        return this.snapTargetId;
    }

    public long getSnapEndTime() {
        return this.snapEndTime;
    }

    public int getHitFlashTargetId() {
        return this.hitFlashTargetId;
    }

    public long getHitFlashEndTime() {
        return this.hitFlashEndTime;
    }
}
