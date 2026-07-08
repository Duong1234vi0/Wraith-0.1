/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.keyboard.HotBarScrollEvent;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.render.CameraEvent;
import code.alphadlc.events.render.FovEvent;
import code.alphadlc.features.impl.player.FreeLook;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.math.calc.Calculate;
import net.minecraft.class_3532;

public class CameraSettings
extends Module {
    private float fov = 110.0f;
    private float smoothFov = 30.0f;
    private float lastChangedFov = 30.0f;
    static BooleanSetting clipSetting = new BooleanSetting("\u0421\u043a\u0432\u043e\u0437\u044c \u0431\u043b\u043e\u043a\u0438", "\u041a\u0430\u043c\u0435\u0440\u0430 \u043f\u0440\u043e\u0445\u043e\u0434\u0438\u0442 \u0441\u043a\u0432\u043e\u0437\u044c \u0431\u043b\u043e\u043a\u0438").setValue(true);
    private SliderSettings distanceSetting = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0430\u043c\u0435\u0440\u044b", "\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0430 \u0440\u0430\u0441\u0441\u0442\u043e\u044f\u043d\u0438\u044f \u043a\u0430\u043c\u0435\u0440\u044b").setValue(3.0f).range(2.0f, 15.0f);
    private BindSetting zoomSetting = new BindSetting("\u0417\u0443\u043c", "\u041a\u043b\u0430\u0432\u0438\u0448\u0430 \u0434\u043b\u044f \u0443\u0432\u0435\u043b\u0438\u0447\u0435\u043d\u0438\u044f \u043a\u0430\u043c\u0435\u0440\u044b");

    public static CameraSettings getInstance() {
        return Instance.get(CameraSettings.class);
    }

    public CameraSettings() {
        super("CameraSettings", "CameraSettings", ModuleCategory.RENDER);
        this.setup(clipSetting, this.zoomSetting, this.distanceSetting);
    }

    @EventHandler
    public void onKey(KeyEvent e) {
        if (e.isKeyDown(this.zoomSetting.getKey())) {
            this.fov = Math.min(this.lastChangedFov, (float)((Integer)CameraSettings.mc.field_1690.method_41808().method_41753() - 20));
        }
        if (e.isKeyReleased(this.zoomSetting.getKey(), true)) {
            this.lastChangedFov = this.fov;
            this.fov = ((Integer)CameraSettings.mc.field_1690.method_41808().method_41753()).intValue();
        }
    }

    @EventHandler
    public void onHotBarScroll(HotBarScrollEvent e) {
        if (PlayerInteractionHelper.isKey(this.zoomSetting)) {
            this.fov = (int)class_3532.method_15350((double)((double)this.fov - e.getVertical() * 10.0), (double)10.0, (double)((Integer)CameraSettings.mc.field_1690.method_41808().method_41753()).intValue());
            e.cancel();
        }
    }

    @EventHandler
    public void onFov(FovEvent e) {
        this.smoothFov = Calculate.interpolateSmooth(1.6, this.smoothFov, this.fov);
        e.setFov((int)class_3532.method_15363((float)(this.smoothFov + 1.0f), (float)10.0f, (float)((Integer)CameraSettings.mc.field_1690.method_41808().method_41753()).intValue()));
        e.cancel();
    }

    @EventHandler
    public void onCamera(CameraEvent e) {
        block3: {
            block2: {
                e.setCameraClip(clipSetting.isValue());
                e.setDistance(this.distanceSetting.getValue());
                FreeLook freeLook = Instance.get(FreeLook.class);
                if (!freeLook.isState()) break block2;
                if (PlayerInteractionHelper.isKey(FreeLook.freeLookSetting)) break block3;
            }
            e.setAngle(MathAngle.cameraAngle());
        }
        e.cancel();
    }
}
