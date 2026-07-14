package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import ua.babka.event.EventKeyInput;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BindSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.StringSetting;
import ua.babka.util.ChatUtil;
import ua.babka.util.player.InventoryUtil;

@ModuleInformation(moduleName="Cords Dropper", moduleDesc="Автоматическая сброс координат", moduleCategory=ModuleCategory.PLAYER)
public class CordsDropper
extends Module {
    private final ModeSetting mode = new ModeSetting("Режим", "Чат", "Чат", "Вебхук");
    private final BindSetting bind = new BindSetting("Key", -1);
    private final StringSetting webhook = new StringSetting("Webhook URL", "");

    public CordsDropper() {
        this.webhook.setVisible(() -> this.mode.is("Вебхук"));
    }

    @Subscribe
    private void onKey(EventKeyInput e2) {
        if (e2.getAction() == 0) {
            return;
        }
        if (this.mc.player == null) {
            return;
        }
        if (e2.getKey() != this.bind.getValue().intValue()) {
            return;
        }
        String coords = String.format("%.0f %.0f", this.mc.player.getX(), this.mc.player.getZ());
        String chatMessage = "! " + coords + " Помогите Пожалуйста";
        if (this.mode.is("Чат")) {
            this.mc.player.networkHandler.sendChatMessage(chatMessage);
            ChatUtil.send((Object)("Координаты отправлены в чат: " + coords));
            return;
        }
        if (this.mode.is("Вебхук")) {
            if (this.webhook.getValue() == null || this.webhook.getValue().isEmpty()) {
                ChatUtil.send((Object)"Укажите Webhook URL в настройках");
                return;
            }
            String serverNick = this.mc.player.getName().getString();
            String discordMessage = ".gps " + coords + " by " + serverNick;
            InventoryUtil.sendDiscordWebhook(this.webhook.getValue(), discordMessage);
            ChatUtil.send((Object)("Координаты отправлены в Discord: " + discordMessage));
        }
    }
}

