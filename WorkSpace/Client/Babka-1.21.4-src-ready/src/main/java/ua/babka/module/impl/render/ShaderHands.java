package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import ua.babka.event.EventHUD;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.shader.ShaderHandsRenderer;

@ModuleInformation(moduleName="ShaderHands", moduleDesc="Красивый Шейдер на руки и предметы", moduleCategory=ModuleCategory.RENDER)
public class ShaderHands
extends Module {
    private static final ShaderHandsRenderer RENDERER = ShaderHandsRenderer.getInstance();
    public final ModeSetting mode = new ModeSetting("Режим", "Свечение", "Свечение", "Красивый");
    public final SliderSetting waveSpeed = new SliderSetting("Скорость волн", 1.2f, 0.1f, 5.0, 0.1f).setVisible(() -> this.mode.is("Красивый"));
    public final SliderSetting waveScale = new SliderSetting("Частота волн", 1.0, 1.0, 3.0, 0.1f).setVisible(() -> this.mode.is("Красивый"));
    public final SliderSetting outline = new SliderSetting("Ширина обводки", 1.2f, 0.1f, 2.0, 0.1f);
    public final SliderSetting glow = new SliderSetting("Сила свечения", 1.0, 0.0, 5.0, 0.1f);
    public final SliderSetting fill = new SliderSetting("Заливка", 0.6f, 0.0, 1.0, 0.01f);
    public final SliderSetting alpha = new SliderSetting("Прозрачность", 1.0, 0.0, 1.0, 0.05f);

    @Override
    public void onDisable() {
        RENDERER.invalidateState();
        super.onDisable();
    }

    @Subscribe
    public void onRenderHUD(EventHUD event) {
        if (!this.isEnabled()) {
            return;
        }
        RENDERER.renderOverlayIfPending();
    }
}

