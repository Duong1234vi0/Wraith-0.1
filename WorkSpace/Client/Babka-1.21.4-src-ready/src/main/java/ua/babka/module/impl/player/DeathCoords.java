package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.StringSetting;
import ua.babka.util.ChatUtil;
import ua.babka.util.player.InventoryUtil;

@ModuleInformation(moduleName="Death Coords", moduleDesc="Сохраняет координаты смерти", moduleCategory=ModuleCategory.PLAYER)
public class DeathCoords
extends Module {
    private final ModeSetting mode = new ModeSetting("Режим", "Чат", "Чат", "Вебхук");
    private final StringSetting webhook = (StringSetting)new StringSetting("Webhook URL", "").setVisible(() -> this.mode.is("Вебхук"));
    private boolean send;

    @Subscribe
    private void onTick(EventTick e2) {
        if (this.mc.player == null) {
            return;
        }
        if (this.mc.player.isDead()) {
            if (!this.send) {
                String coords = String.format("%.0f %.0f %.0f", this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ());
                String message = "Смерть на XYZ: " + coords;
                if (this.mode.is("Чат")) {
                    ChatUtil.send((Object)message);
                } else if (this.mode.is("Вебхук") && !this.webhook.getValue().isEmpty()) {
                    InventoryUtil.sendDiscordWebhook(this.webhook.getValue(), "💀 " + message);
                }
                this.send = true;
            }
        } else {
            this.send = false;
        }
    }
}

