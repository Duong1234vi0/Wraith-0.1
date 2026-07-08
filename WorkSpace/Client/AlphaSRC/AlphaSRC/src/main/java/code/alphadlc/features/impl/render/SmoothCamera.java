/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.render.CameraEvent;
import code.alphadlc.events.render.CameraPositionEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.warp.Turns;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class SmoothCamera
extends Module {
    private final SliderSettings speed = new SliderSettings("Smooth", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u044f \u043a\u0430\u043c\u0435\u0440\u044b").range(0.01f, 0.3f).setValue(0.08f);
    private float smoothYaw = 0.0f;
    private float smoothPitch = 0.0f;
    private class_243 smoothPos = null;
    private boolean initialized = false;

    public static SmoothCamera getInstance() {
        return Instance.get(SmoothCamera.class);
    }

    public SmoothCamera() {
        super("SmoothCamera", "SmoothCamera", ModuleCategory.RENDER);
        this.setup(this.speed);
    }

    @Override
    public void activate() {
        this.initialized = false;
        this.smoothPos = null;
    }

    @Override
    public void deactivate() {
        this.initialized = false;
        this.smoothPos = null;
    }

    @EventHandler
    public void onCamera(CameraEvent e) {
        Turns target = e.getAngle();
        if (target == null) {
            return;
        }
        if (!this.initialized) {
            this.smoothYaw = target.getYaw();
            this.smoothPitch = target.getPitch();
            this.initialized = true;
            return;
        }
        float factor = this.speed.getValue();
        float deltaYaw = class_3532.method_15393((float)(target.getYaw() - this.smoothYaw));
        this.smoothYaw += deltaYaw * factor;
        this.smoothPitch += (target.getPitch() - this.smoothPitch) * factor;
        e.setAngle(new Turns(this.smoothYaw, this.smoothPitch));
        e.cancel();
    }

    @EventHandler
    public void onCameraPos(CameraPositionEvent e) {
        class_243 target = e.getPos();
        if (target == null) {
            return;
        }
        if (this.smoothPos == null) {
            this.smoothPos = target;
            return;
        }
        double factor = this.speed.getValue();
        this.smoothPos = new class_243(this.smoothPos.field_1352 + (target.field_1352 - this.smoothPos.field_1352) * factor, this.smoothPos.field_1351 + (target.field_1351 - this.smoothPos.field_1351) * factor, this.smoothPos.field_1350 + (target.field_1350 - this.smoothPos.field_1350) * factor);
        e.setPos(this.smoothPos);
    }
}
