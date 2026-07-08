/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;

public class TargetStrafe
extends Module {
    private static final class_310 mc = class_310.method_1551();
    public SelectSetting mode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0422\u0438\u043f \u0441\u0442\u0440\u0435\u0439\u0444\u0430").value("\u041c\u0430\u0442\u0440\u0438\u043a\u0441", "\u0413\u0440\u0438\u043c").selected("\u041c\u0430\u0442\u0440\u0438\u043a\u0441");
    SliderSettings distance = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043e\u0442 \u0446\u0435\u043b\u0438").setValue(0.87f).range(0.0f, 3.0f).step(0.1f).visible(() -> this.mode.getSelected().equals("\u0413\u0440\u0438\u043c"));
    MultiSelectSetting setting = new MultiSelectSetting("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438", "\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0435\u0442 \u043d\u0430\u0441\u0442\u0440\u043e\u0438\u0442\u044c \u0440\u0430\u0431\u043e\u0442\u0443 \u0441\u0442\u0440\u0435\u0439\u0444\u043e\u0432").value("\u0410\u0432\u0442\u043e \u043f\u0440\u044b\u0436\u043e\u043a", "\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u043d\u0430\u0436\u0430\u0442\u0438\u0438", "\u041f\u0435\u0440\u0435\u0434 \u0446\u0435\u043b\u044c\u044e").selected("\u0410\u0432\u0442\u043e \u043f\u0440\u044b\u0436\u043e\u043a");
    SliderSettings radius = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441", "\u0420\u0430\u0434\u0438\u0443\u0441 \u043e\u0431\u0445\u043e\u0434\u0430 \u0432\u043e\u043a\u0440\u0443\u0433 \u0446\u0435\u043b\u0438").setValue(2.5f).range(0.1f, 7.0f).visible(() -> this.mode.getSelected().equals("\u041c\u0430\u0442\u0440\u0438\u043a\u0441"));
    SliderSettings speed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0441\u0442\u0440\u0435\u0439\u0444\u0430").setValue(0.3f).range(0.1f, 1.0f).visible(() -> this.mode.getSelected().equals("\u041c\u0430\u0442\u0440\u0438\u043a\u0441"));
    private int grimPointIndex = 0;

    public TargetStrafe() {
        super("TargetStrafe", "TargetStrafe", ModuleCategory.MOVEMENT);
        this.setup(this.mode, this.distance, this.radius, this.speed, this.setting);
    }

    public static TargetStrafe getInstance() {
        return Instance.get(TargetStrafe.class);
    }

    @EventHandler
    public void onInput(InputEvent event) {
        class_243 nextPoint;
        if (TargetStrafe.mc.field_1724 == null || TargetStrafe.mc.field_1687 == null) {
            return;
        }
        class_1309 target = AttackAura.getInstance().getTarget();
        if (target == null || !target.method_5805()) {
            return;
        }
        String modeValue = this.mode.getSelected();
        if (!modeValue.equals("\u0413\u0440\u0438\u043c")) {
            return;
        }
        if (!TargetStrafe.mc.field_1724.method_5624() && TargetStrafe.mc.field_1724.method_24828()) {
            TargetStrafe.mc.field_1724.method_5728(true);
        }
        if (!(!this.setting.isSelected("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u043d\u0430\u0436\u0430\u0442\u0438\u0438") || TargetStrafe.mc.field_1690.field_1894.method_1434() || TargetStrafe.mc.field_1690.field_1881.method_1434() || TargetStrafe.mc.field_1690.field_1913.method_1434() || TargetStrafe.mc.field_1690.field_1849.method_1434())) {
            return;
        }
        class_243 playerPos = TargetStrafe.mc.field_1724.method_19538();
        class_243 targetPos = target.method_19538();
        double r = this.distance.getValue();
        if (this.setting.isSelected("\u041f\u0435\u0440\u0435\u0434 \u0446\u0435\u043b\u044c\u044e")) {
            float targetYaw = target.method_36454();
            double offset = Math.cos((double)System.currentTimeMillis() / 500.0) * 1.0;
            nextPoint = targetPos.method_1031(-Math.sin(Math.toRadians(targetYaw)) * r + Math.cos(Math.toRadians(targetYaw)) * offset, 0.0, Math.cos(Math.toRadians(targetYaw)) * r + Math.sin(Math.toRadians(targetYaw)) * offset);
        } else {
            class_243[] class_243Array = new class_243[]{new class_243(targetPos.field_1352 - r, playerPos.field_1351, targetPos.field_1350 - r), new class_243(targetPos.field_1352 - r, playerPos.field_1351, targetPos.field_1350 + r), new class_243(targetPos.field_1352 + r, playerPos.field_1351, targetPos.field_1350 + r), new class_243(targetPos.field_1352 + r, playerPos.field_1351, targetPos.field_1350 - r)};
            class_243[] points = class_243Array;
            nextPoint = points[this.grimPointIndex];
            if (playerPos.method_1022(nextPoint) < 0.5) {
                this.grimPointIndex = (this.grimPointIndex + 1) % points.length;
            }
        }
        class_243 direction = nextPoint.method_1020(playerPos).method_1029();
        float yaw = TurnsConnection.INSTANCE.getRotation().getYaw();
        float movementAngle = (float)Math.toDegrees(Math.atan2(direction.field_1350, direction.field_1352)) - 90.0f;
        float angleDiff = class_3532.method_15393((float)(movementAngle - yaw));
        boolean forward = false;
        boolean back = false;
        boolean left = false;
        boolean right = false;
        if ((double)angleDiff >= -22.5 && (double)angleDiff < 22.5) {
            forward = true;
        } else if ((double)angleDiff >= 22.5 && (double)angleDiff < 67.5) {
            forward = true;
            right = true;
        } else if ((double)angleDiff >= 67.5 && (double)angleDiff < 112.5) {
            right = true;
        } else if ((double)angleDiff >= 112.5 && (double)angleDiff < 157.5) {
            back = true;
            right = true;
        } else if ((double)angleDiff >= -67.5 && (double)angleDiff < -22.5) {
            forward = true;
            left = true;
        } else if ((double)angleDiff >= -112.5 && (double)angleDiff < -67.5) {
            left = true;
        } else if ((double)angleDiff >= -157.5 && (double)angleDiff < -112.5) {
            back = true;
            left = true;
        } else {
            back = true;
        }
        event.setDirectional(forward, back, left, right);
        if (this.setting.isSelected("\u0410\u0432\u0442\u043e \u043f\u0440\u044b\u0436\u043e\u043a") && TargetStrafe.mc.field_1724.method_24828()) {
            event.setJumping(true);
        }
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (TargetStrafe.mc.field_1724 == null || TargetStrafe.mc.field_1687 == null) {
            return;
        }
        class_1309 target = AttackAura.getInstance().getTarget();
        if (target == null || !target.method_5805()) {
            return;
        }
        String modeValue = this.mode.getSelected();
        if (!modeValue.equals("\u041c\u0430\u0442\u0440\u0438\u043a\u0441")) {
            return;
        }
        if (!TargetStrafe.mc.field_1724.method_5624() && TargetStrafe.mc.field_1724.method_24828()) {
            TargetStrafe.mc.field_1724.method_5728(true);
        }
        class_243 playerPos = TargetStrafe.mc.field_1724.method_19538();
        class_243 targetPos = target.method_19538();
        double r = this.radius.getValue();
        if (!(!this.setting.isSelected("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u043d\u0430\u0436\u0430\u0442\u0438\u0438") || TargetStrafe.mc.field_1690.field_1894.method_1434() || TargetStrafe.mc.field_1690.field_1881.method_1434() || TargetStrafe.mc.field_1690.field_1913.method_1434() || TargetStrafe.mc.field_1690.field_1849.method_1434())) {
            return;
        }
        if (this.setting.isSelected("\u0410\u0432\u0442\u043e \u043f\u0440\u044b\u0436\u043e\u043a") && TargetStrafe.mc.field_1724.method_24828()) {
            TargetStrafe.mc.field_1724.method_6043();
        }
        if (this.setting.isSelected("\u041f\u0435\u0440\u0435\u0434 \u0446\u0435\u043b\u044c\u044e")) {
            float targetYaw = target.method_36454();
            double x = targetPos.field_1352 - Math.sin(Math.toRadians(targetYaw)) * r;
            double z = targetPos.field_1350 + Math.cos(Math.toRadians(targetYaw)) * r;
            float yaw = (float)Math.toDegrees(Math.atan2(z - playerPos.field_1350, x - playerPos.field_1352)) - 90.0f;
            double motionSpeed = this.speed.getValue();
            TargetStrafe.mc.field_1724.method_18800(-Math.sin(Math.toRadians(yaw)) * motionSpeed, TargetStrafe.mc.field_1724.method_18798().field_1351, Math.cos(Math.toRadians(yaw)) * motionSpeed);
            return;
        }
        double angle = Math.atan2(playerPos.field_1350 - targetPos.field_1350, playerPos.field_1352 - targetPos.field_1352);
        double x = targetPos.field_1352 + r * Math.cos(angle += (double)this.speed.getValue() / Math.max(playerPos.method_1022(targetPos), r));
        double z = targetPos.field_1350 + r * Math.sin(angle);
        float yaw = (float)Math.toDegrees(Math.atan2(z - playerPos.field_1350, x - playerPos.field_1352)) - 90.0f;
        double motionSpeed = this.speed.getValue();
        TargetStrafe.mc.field_1724.method_18800(-Math.sin(Math.toRadians(yaw)) * motionSpeed, TargetStrafe.mc.field_1724.method_18798().field_1351, Math.cos(Math.toRadians(yaw)) * motionSpeed);
    }

    @Override
    public void activate() {
        super.activate();
        this.grimPointIndex = 0;
    }
}
