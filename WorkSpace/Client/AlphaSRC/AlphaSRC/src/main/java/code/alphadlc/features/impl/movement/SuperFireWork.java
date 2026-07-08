/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.FireworkEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.Setting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class SuperFireWork
extends Module {
    private static final float BUCKET_DEGREES = 5.0f;
    private static final int BUCKET_COUNT = 9;
    private final BooleanSetting acceleration = new BooleanSetting("\u0423\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u0435", "\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u043a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439 \u0431\u0443\u0441\u0442 \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a\u0430").setValue(true);
    private final SelectSetting modeSetting = new SelectSetting("\u0420\u0435\u0436\u0438\u043c \u0443\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u044f", "\u0422\u0438\u043f \u0440\u0430\u0441\u0447\u0451\u0442\u0430 \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u0438").value("ReallyWorld", "Bravo", "BravoGrief", "\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439").visible(this.acceleration::isValue);
    private final SliderSettings customSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432 \u043a\u0430\u0441\u0442\u043e\u043c\u043d\u043e\u043c \u0440\u0435\u0436\u0438\u043c\u0435").range(1.0f, 50.0f).setValue(20.0f).visible(() -> this.acceleration.isValue() && this.modeSetting.isSelected("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439"));
    private final SliderSettings[] xzBuckets = this.createBuckets("XZ", 1.52f);
    private final SliderSettings[] yBuckets = this.createBuckets("Y", 1.51f);
    private static final float[] BRAVO_GRIEF_XZ = new float[]{1.65f, 1.66f, 1.67f, 1.68f, 1.69f, 1.7f, 1.71f, 1.72f, 1.73f};
    private static final float[] BRAVO_GRIEF_Y = new float[]{1.6f, 1.61f, 1.62f, 1.63f, 1.64f, 1.65f, 1.66f, 1.67f, 1.68f};

    public SuperFireWork() {
        super("SuperFireWork", "SuperFirework", ModuleCategory.MOVEMENT);
        Setting[] all = new Setting[this.xzBuckets.length + this.yBuckets.length + 3];
        int i = 0;
        all[i++] = this.acceleration;
        all[i++] = this.modeSetting;
        all[i++] = this.customSpeed;
        System.arraycopy(this.xzBuckets, 0, all, i, this.xzBuckets.length);
        System.arraycopy(this.yBuckets, 0, all, i += this.xzBuckets.length, this.yBuckets.length);
        this.setup(all);
    }

    private SliderSettings[] createBuckets(String axis, float defaultValue) {
        SliderSettings[] buckets = new SliderSettings[9];
        for (int i = 0; i < 9; ++i) {
            int from = i * 5;
            int to = from + 5;
            buckets[i] = new SliderSettings(axis + " " + from + "-" + to + "\u00b0", "\u041c\u043d\u043e\u0436\u0438\u0442\u0435\u043b\u044c \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u0438").setValue(defaultValue + (float)i * 0.01f).range(1.0f, 3.0f).step(0.01f).visible(() -> this.acceleration.isValue() && this.modeSetting.isSelected("ReallyWorld"));
        }
        return buckets;
    }

    @EventHandler
    public void onFirework(FireworkEvent event) {
        float y;
        float xz;
        if (!this.acceleration.isValue() || SuperFireWork.mc.field_1724 == null) {
            return;
        }
        float yaw = class_3532.method_15393((float)SuperFireWork.mc.field_1724.method_36454());
        float pitch = SuperFireWork.mc.field_1724.method_36455();
        switch (this.modeSetting.getSelected()) {
            case "ReallyWorld": {
                xz = this.bucketValue(this.xzBuckets, this.foldAngle(yaw));
                y = this.bucketValue(this.yBuckets, this.foldAngle(pitch));
                if (!(y > xz)) break;
                xz = y;
                break;
            }
            case "Bravo": {
                xz = this.bravoSpeed(pitch, yaw);
                y = this.bravoPitchSpeed(pitch);
                break;
            }
            case "BravoGrief": {
                xz = this.lookup(BRAVO_GRIEF_XZ, this.foldAngle(yaw));
                y = this.lookup(BRAVO_GRIEF_Y, this.foldAngle(pitch));
                break;
            }
            default: {
                int ff = TurnsConnection.INSTANCE.getRotation().getYaw() > 0.0f ? 45 : -45;
                double accel = Math.abs((TurnsConnection.INSTANCE.getRotation().getYaw() + (float)ff) % 90.0f - (float)ff) / 45.0f;
                double rotationBoost = 1.0 + 0.3 * accel * accel;
                boolean yAccel = Math.abs(TurnsConnection.INSTANCE.getMoveRotation().getPitch()) > 60.0f;
                class_243 direction = TurnsConnection.INSTANCE.getMoveRotation().toVector();
                float speed = this.customSpeed.getValue() / 20.0f;
                double finalSpeed = (double)speed * rotationBoost;
                event.setVector(new class_243(direction.field_1352 * finalSpeed, yAccel ? direction.field_1351 * finalSpeed : direction.field_1351 * (double)speed, direction.field_1350 * finalSpeed));
                return;
            }
        }
        event.setVector(new class_243((double)xz, (double)y, (double)xz));
    }

    private float bucketValue(SliderSettings[] buckets, float angle) {
        int index = class_3532.method_15340((int)((int)(angle / 5.0f)), (int)0, (int)(buckets.length - 1));
        return buckets[index].getValue();
    }

    private float lookup(float[] table, float angle) {
        int index = class_3532.method_15340((int)((int)(angle / 5.0f)), (int)0, (int)(table.length - 1));
        return table[index];
    }

    private float foldAngle(float angle) {
        float value = Math.abs(angle);
        if (value > 90.0f) {
            value = 180.0f - value;
        }
        if (value > 45.0f) {
            value = 90.0f - value;
        }
        return value;
    }

    private float bravoPitchSpeed(float pitch) {
        float abs = Math.abs(pitch);
        if (abs <= 5.0f) {
            return 1.51f;
        }
        if (abs <= 10.0f) {
            return 2.0f;
        }
        if (abs <= 15.0f) {
            return 1.55f;
        }
        if (abs <= 20.0f) {
            return 1.56f;
        }
        if (abs <= 25.0f) {
            return 1.57f;
        }
        if (abs <= 30.0f) {
            return 1.58f;
        }
        if (abs <= 35.0f) {
            return 1.59f;
        }
        if (abs <= 40.0f) {
            return 1.6f;
        }
        return 1.61f;
    }

    private float bravoSpeed(float pitch, float yaw) {
        float absPitch = Math.abs(pitch);
        float absYaw = Math.abs(class_3532.method_15393((float)yaw) % 90.0f);
        float speed = absPitch >= 5.0f && absPitch <= 10.0f ? 2.0f : (absPitch >= 10.0f && absPitch <= 15.0f ? 1.54f : (absPitch >= 15.0f && absPitch <= 20.0f ? 1.55f : (absYaw >= 30.0f && absYaw <= 35.0f || absPitch >= 20.0f && absPitch <= 25.0f ? 1.56f : (absYaw >= 35.0f && absYaw <= 40.0f || absPitch >= 25.0f && absPitch <= 30.0f ? 1.57f : (absYaw >= 40.0f && absYaw <= 45.0f || absPitch >= 30.0f && absPitch <= 35.0f ? 1.58f : (absPitch >= 35.0f && absPitch <= 40.0f ? 1.59f : (absPitch >= 40.0f && absPitch <= 45.0f ? 1.6f : (absYaw <= 5.0f || absPitch <= 5.0f ? 1.53f : 1.62f))))))));
        return pitch > 0.0f ? speed : speed;
    }
}
