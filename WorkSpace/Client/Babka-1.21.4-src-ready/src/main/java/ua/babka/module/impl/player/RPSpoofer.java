package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import net.minecraft.network.packet.c2s.common.ResourcePackStatusC2SPacket;
import net.minecraft.network.packet.s2c.common.ResourcePackSendS2CPacket;
import ua.babka.event.EventPacket;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.util.network.NetworkUtils;

@ModuleInformation(moduleName="RP Spoofer", moduleDesc="Подмена ресурс-пака", moduleCategory=ModuleCategory.MISC)
public class RPSpoofer
extends Module {
    @Subscribe
    private void onPacket(EventPacket e2) {
        if (e2.getPacket() instanceof ResourcePackSendS2CPacket) {
            NetworkUtils.sendPacket(new ResourcePackStatusC2SPacket(this.mc.player.getUuid(), ResourcePackStatusC2SPacket.Status.ACCEPTED));
            NetworkUtils.sendPacket(new ResourcePackStatusC2SPacket(this.mc.player.getUuid(), ResourcePackStatusC2SPacket.Status.DOWNLOADED));
            NetworkUtils.sendPacket(new ResourcePackStatusC2SPacket(this.mc.player.getUuid(), ResourcePackStatusC2SPacket.Status.SUCCESSFULLY_LOADED));
            e2.cancelEvent();
        }
    }
}

