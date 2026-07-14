package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import org.lwjgl.glfw.GLFW;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BindSetting;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.SliderSetting;

@ModuleInformation(moduleName="Zoom", moduleDesc="Приближение камеры", moduleCategory=ModuleCategory.RENDER)
public class Zoom
extends Module {
    public static boolean zooming = false;
    public static float currentFovModifier = 1.0f;
    public final BindSetting zoomKey = new BindSetting("Клавиша", 67);
    public final SliderSetting zoomAmount = new SliderSetting("Зум", 4.0, 2.0, 50.0, 1.0);
    public final SliderSetting smoothness = new SliderSetting("Плавность", 5.0, 1.0, 8.0, 1.0);
    public final BooleanSetting smoothCamera = new BooleanSetting("Откл. плавную камеру", true);

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        zooming = false;
    }

    @Subscribe
    private void onTick(EventTick event) {
        boolean keyDown;
        if (this.mc.getWindow() == null) {
            return;
        }
        boolean bl = keyDown = GLFW.glfwGetKey((long)this.mc.getWindow().getHandle(), (int)this.zoomKey.getValue()) == 1;
        if (keyDown && this.isEnabled()) {
            zooming = true;
            float target = (float)(1.0 / this.zoomAmount.getValue());
            currentFovModifier += (target - currentFovModifier) * 0.15f * (float)this.smoothness.getValue();
            if (this.smoothCamera.getValue()) {
                this.mc.options.smoothCameraEnabled = false;
            }
        } else {
            zooming = false;
            if ((currentFovModifier += (1.0f - currentFovModifier) * 0.15f * (float)this.smoothness.getValue()) > 0.999f) {
                currentFovModifier = 1.0f;
            }
        }
    }
}

