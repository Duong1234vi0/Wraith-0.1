package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import ua.babka.event.EventPacket;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.ModeSetting;

@ModuleInformation(moduleName="Velocity", moduleCategory=ModuleCategory.COMBAT)
public class Velocity
extends Module {
    private final ModeSetting mode = new ModeSetting("Мод", "Vanilla", "Vanilla", "BravoHvH");

    @Subscribe
    private void onPacket(EventPacket e2) {
        Packet<?> packet = e2.getPacket();
        if (packet instanceof EntityVelocityUpdateS2CPacket) {
            EntityVelocityUpdateS2CPacket packet2 = (EntityVelocityUpdateS2CPacket)packet;
            if (packet2.getEntityId() != this.mc.player.getId()) {
                return;
            }
            if (this.mode.is("BravoHvH") && this.mc.player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA) {
                return;
            }
            e2.cancelEvent();
        }
    }
}

