/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2868
 *  net.minecraft.class_2886
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.rotate.RotationUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2868;
import net.minecraft.class_2886;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public class WindHop
extends Module {
    private static final double WALL_CHECK_DISTANCE = 2.5;
    private static final double MIN_UPWARD_VELOCITY = 0.05;
    private final BindSetting bind = new BindSetting("\u0411\u0438\u043d\u0434", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0437\u0430\u0440\u044f\u0434 \u0432\u0435\u0442\u0440\u0430 \u043f\u043e \u0431\u0438\u043d\u0434\u0443");
    private boolean bindQueued;

    public WindHop() {
        super("WindHop", "WindHop", ModuleCategory.MOVEMENT);
        this.setup(this.bind);
    }

    public static WindHop getInstance() {
        return Instance.get(WindHop.class);
    }

    @Override
    public void deactivate() {
        this.bindQueued = false;
        super.deactivate();
    }

    @EventHandler
    public void onKey(KeyEvent event) {
        if (!event.isKeyDown(this.bind.getKey())) {
            return;
        }
        this.bindQueued = true;
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (WindHop.mc.field_1724 == null || WindHop.mc.field_1687 == null || WindHop.mc.field_1761 == null || !this.bindQueued) {
            return;
        }
        this.bindQueued = false;
        this.useWindCharge();
    }

    private void useWindCharge() {
        int slot = this.findWindChargeSlot();
        if (slot == -1 || mc.method_1562() == null) {
            return;
        }
        boolean offhand = slot == 40;
        boolean hotbar = slot < 9;
        int previousSlot = WindHop.mc.field_1724.method_31548().field_7545;
        class_1268 hand = offhand ? class_1268.field_5810 : class_1268.field_5808;
        float yaw = WindHop.mc.field_1724.method_36454();
        float pitch = this.resolvePitch(yaw);
        if (hotbar && !offhand && slot != previousSlot) {
            mc.method_1562().method_52787((class_2596)new class_2868(slot));
        }
        mc.method_1562().method_52787((class_2596)new class_2886(hand, 0, yaw, pitch));
        WindHop.mc.field_1724.method_6104(hand);
        if (hotbar && !offhand && slot != previousSlot) {
            mc.method_1562().method_52787((class_2596)new class_2868(previousSlot));
        }
    }

    private float resolvePitch(float yaw) {
        if (!WindHop.mc.field_1724.method_24828() && this.blockBelowOffset(0.0, -0.6, 0.0) == class_2246.field_10124 && WindHop.mc.field_1724.method_18798().field_1351 > 0.05) {
            for (int angle = 0; angle < 360; angle += 45) {
                if (!this.raycastWall(yaw, angle, 2.5f)) continue;
                return WindHop.normalizeYaw(yaw + (float)angle);
            }
        }
        return 90.0f;
    }

    private boolean raycastWall(float yaw, float angleOffset, float distance) {
        class_243 direction;
        class_243 eye = WindHop.mc.field_1724.method_33571();
        class_3965 hit = WindHop.mc.field_1687.method_17742(new class_3959(eye, eye.method_1019(direction = RotationUtils.getRotationVector(WindHop.normalizeYaw(yaw + angleOffset), 0.0f).method_1021((double)distance)), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)WindHop.mc.field_1724));
        return hit.method_17783() == class_239.class_240.field_1332;
    }

    private class_2248 blockBelowOffset(double x, double y, double z) {
        if (WindHop.mc.field_1724 == null || WindHop.mc.field_1687 == null) {
            return class_2246.field_10124;
        }
        class_2338 pos = class_2338.method_49638((class_2374)WindHop.mc.field_1724.method_19538().method_1031(x, y, z));
        return WindHop.mc.field_1687.method_8320(pos).method_26204();
    }

    private int findWindChargeSlot() {
        if (WindHop.mc.field_1724 == null) {
            return -1;
        }
        if (WindHop.mc.field_1724.method_6079().method_31574(class_1802.field_49098)) {
            return 40;
        }
        for (int i = 0; i < 9; ++i) {
            if (!WindHop.mc.field_1724.method_31548().method_5438(i).method_31574(class_1802.field_49098)) continue;
            return i;
        }
        return -1;
    }

    private static float normalizeYaw(float yaw) {
        while (yaw > 180.0f) {
            yaw -= 360.0f;
        }
        while (yaw < -180.0f) {
            yaw += 360.0f;
        }
        return yaw;
    }
}
