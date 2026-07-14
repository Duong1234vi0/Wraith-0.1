package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.world.World;
import ua.babka.event.EventPacket;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;

@ModuleInformation(moduleName="AntiThorns", moduleDesc="Анти шипы на элитре ", moduleCategory=ModuleCategory.COMBAT)
public class AntiThorns
extends Module {
    private static final int THORNS_ENTITY_STATUS_OPCODE = 33;
    private int suppressVelocityTicks;

    @Subscribe
    private void onPacket(EventPacket event) {
        EntityVelocityUpdateS2CPacket packet;
        if (event.getType() == EventPacket.Type.SEND || this.mc.player == null || this.mc.world == null) {
            return;
        }
        Packet<?> packet2 = event.getPacket();
        if (packet2 instanceof EntityStatusS2CPacket) {
            EntityStatusS2CPacket packet3 = (EntityStatusS2CPacket)packet2;
            if (this.mc.player.isGliding()) {
                Entity entity;
                if (packet3.getStatus() == 33) {
                    this.suppressVelocityTicks = 8;
                }
                if (packet3.getStatus() == 2 && (entity = packet3.getEntity((World)this.mc.world)) == this.mc.player) {
                    this.suppressVelocityTicks = Math.max(this.suppressVelocityTicks, 8);
                }
            }
            return;
        }
        packet2 = event.getPacket();
        if (packet2 instanceof EntityVelocityUpdateS2CPacket && (packet = (EntityVelocityUpdateS2CPacket)packet2).getEntityId() == this.mc.player.getId() && this.mc.player.isGliding() && this.suppressVelocityTicks > 0) {
            event.cancelEvent();
            this.suppressVelocityTicks = 0;
        }
    }

    @Subscribe
    private void onTick(EventTick event) {
        if (this.mc.player == null || !this.mc.player.isGliding()) {
            this.suppressVelocityTicks = 0;
            return;
        }
        if (this.suppressVelocityTicks > 0) {
            --this.suppressVelocityTicks;
        }
    }

    @Override
    public void onEnable() {
        this.suppressVelocityTicks = 0;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.suppressVelocityTicks = 0;
        super.onDisable();
    }
}

