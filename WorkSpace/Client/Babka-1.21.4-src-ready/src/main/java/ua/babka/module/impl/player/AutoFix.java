package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import ua.babka.event.EventPacket;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;

@ModuleInformation(moduleName="AutoFix", moduleDesc="Автоматический ремонт предметов", moduleCategory=ModuleCategory.PLAYER)
public class AutoFix
extends Module {
    @Subscribe
    public void onPacket(EventPacket e2) {
        GameMessageS2CPacket p2;
        String raw;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        Packet<?> packet = e2.getPacket();
        if (packet instanceof GameMessageS2CPacket && (raw = (p2 = (GameMessageS2CPacket)packet).comp_763().getString()).contains("АнтиРелог") && raw.contains("PVP режим закончился")) {
            this.mc.getNetworkHandler().sendChatCommand("fix all");
        }
    }
}

