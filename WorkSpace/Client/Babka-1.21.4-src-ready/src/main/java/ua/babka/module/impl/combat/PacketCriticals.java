package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import ua.babka.combat.IdealHitUtils;
import ua.babka.event.EventAttack;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.movement.AirStuck;
import ua.babka.util.Instance;

@ModuleInformation(moduleName="PacketCriticals", moduleDesc="Критические удары через пакеты", moduleCategory=ModuleCategory.COMBAT)
public class PacketCriticals
extends Module {
    @Subscribe
    public void onAttack(EventAttack event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        boolean inWeb = IdealHitUtils.isInCobweb();
        AirStuck airStuck = Instance.get(AirStuck.class);
        boolean inAirStuck = airStuck != null && airStuck.isEnabled();
        double x2 = this.mc.player.getX();
        double y2 = this.mc.player.getY();
        double z2 = this.mc.player.getZ();
        if (inWeb || inAirStuck) {
            if (inAirStuck) {
                airStuck.pauseForAttack();
            }
            this.mc.player.networkHandler.sendPacket((Packet)new PlayerMoveC2SPacket.PositionAndOnGround(x2, y2 + 0.003, z2, false, false));
            this.mc.player.networkHandler.sendPacket((Packet)new PlayerMoveC2SPacket.PositionAndOnGround(x2, y2, z2, false, false));
        }
    }
}

