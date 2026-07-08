/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3532
 *  net.minecraft.class_5498
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.keyboard.MouseRotationEvent;
import code.alphadlc.events.render.CameraEvent;
import code.alphadlc.events.render.FovEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_3532;
import net.minecraft.class_5498;

public class FreeLook
extends Module {
    private class_5498 perspective;
    private Turns angle;
    public static BindSetting freeLookSetting = new BindSetting("\u0421\u0432\u043e\u0431\u043e\u0434\u043d\u044b\u0439 \u043e\u0431\u0437\u043e\u0440", "\u041a\u043b\u0430\u0432\u0438\u0448\u0430 \u0434\u043b\u044f \u0441\u0432\u043e\u0431\u043e\u0434\u043d\u043e\u0433\u043e \u043e\u0431\u0437\u043e\u0440\u0430");

    public FreeLook() {
        super("FreeLook", "FreeLook", ModuleCategory.RENDER);
        this.setup(freeLookSetting);
        this.angle = null;
    }

    @EventHandler
    public void onKey(KeyEvent e) {
        if (e.isKeyDown(freeLookSetting.getKey())) {
            this.perspective = FreeLook.mc.field_1690.method_31044();
            if (this.angle == null) {
                this.angle = MathAngle.cameraAngle();
            }
        }
    }

    @EventHandler
    public void onFov(FovEvent e) {
        if (PlayerInteractionHelper.isKey(freeLookSetting)) {
            if (FreeLook.mc.field_1690.method_31044().method_31034()) {
                FreeLook.mc.field_1690.method_31043(class_5498.field_26665);
            }
            if (this.angle == null) {
                this.angle = MathAngle.cameraAngle();
            }
        } else if (this.perspective != null) {
            FreeLook.mc.field_1690.method_31043(this.perspective);
            this.perspective = null;
            this.angle = null;
        }
    }

    @EventHandler
    public void onMouseRotation(MouseRotationEvent e) {
        if (PlayerInteractionHelper.isKey(freeLookSetting)) {
            if (this.angle == null) {
                this.angle = MathAngle.cameraAngle();
            }
            this.angle.setYaw(this.angle.getYaw() + e.getCursorDeltaX() * 0.15f);
            this.angle.setPitch(class_3532.method_15363((float)(this.angle.getPitch() + e.getCursorDeltaY() * 0.15f), (float)-90.0f, (float)90.0f));
            e.cancel();
        } else {
            this.angle = null;
        }
    }

    @EventHandler
    public void onCamera(CameraEvent e) {
        if (PlayerInteractionHelper.isKey(freeLookSetting) && this.angle != null) {
            e.setAngle(this.angle);
            e.cancel();
        }
    }
}
