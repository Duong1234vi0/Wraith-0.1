package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.util.network.BotSessionManager;

@ModuleInformation(moduleName="Tape Mouse", moduleDesc="Программные клики для ботов", moduleCategory=ModuleCategory.PLAYER)
public class TapeMouse
extends Module {
    private final SliderSetting cps = new SliderSetting("CPS", 1.0, 0.0, 2.0, 0.05f);
    private final BooleanSetting rightClick = new BooleanSetting("Правая кнопка", false);
    private final BooleanSetting onlyBots = new BooleanSetting("Только боты", true);
    private long lastClick;

    @Subscribe
    private void onTick(EventTick eventTick) {
        long delay = (long)(1000.0 / this.cps.getValue());
        long now = System.currentTimeMillis();
        if (now - this.lastClick < delay) {
            return;
        }
        if (!this.onlyBots.getValue()) {
            this.clickForLocalPlayer();
        }
        if (!BotSessionManager.getConnections().isEmpty()) {
            BotSessionManager.pulseBots(this.rightClick.getValue());
        }
        this.lastClick = now;
    }

    private void clickForLocalPlayer() {
        if (this.mc.player == null || this.mc.currentScreen != null) {
            return;
        }
        if (this.rightClick.getValue()) {
            this.mc.doItemUse();
        } else {
            this.mc.doAttack();
        }
    }
}

