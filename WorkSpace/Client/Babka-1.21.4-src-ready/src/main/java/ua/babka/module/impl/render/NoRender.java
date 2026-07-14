package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;

@ModuleInformation(moduleName="Removals", moduleDesc="Скрывает ненужные элементы", moduleCategory=ModuleCategory.RENDER)
public class NoRender
extends Module {
    public final ModeListSetting elements = new ModeListSetting("Элементы", new BooleanSetting("Огонь", false), new BooleanSetting("Плохие эффекты", false), new BooleanSetting("Оверлей в блоке", false), new BooleanSetting("Частицы", false), new BooleanSetting("Погода", false), new BooleanSetting("Облака", false), new BooleanSetting("Блок-сущности", false), new BooleanSetting("Тени", false), new BooleanSetting("Анимацию тотема", false), new BooleanSetting("Размытие в воде", true), new BooleanSetting("Зрение в блоках", true), new BooleanSetting("Камераклип", true), new BooleanSetting("Тряска камеры", false));

    @Subscribe
    public void onUpdate(EventTick e2) {
        if (!this.isEnabled()) {
            return;
        }
        if (this.elements.isEnabled("Тряска камеры")) {
            this.mc.options.getDamageTiltStrength().setValue((Object)0.0);
        } else {
            this.mc.options.getDamageTiltStrength().setValue((Object)0.5);
        }
    }

    @Override
    public void onDisable() {
        this.mc.options.getDamageTiltStrength().setValue((Object)0.5);
        super.onDisable();
    }

    public boolean isRemoved(String element) {
        return this.isEnabled() && this.elements.isEnabled(element);
    }
}

