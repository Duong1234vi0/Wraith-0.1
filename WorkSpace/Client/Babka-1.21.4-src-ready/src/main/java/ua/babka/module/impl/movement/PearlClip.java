package ua.babka.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import ua.babka.event.EventPacket;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.SliderSetting;
import ua.babka.util.network.NetworkUtils;

@ModuleInformation(moduleName="PearlClip", moduleDesc="Позволяет пролетать сквозь блоки после броска жемчуга", moduleCategory=ModuleCategory.MOVEMENT)
public class PearlClip
extends Module {
    private final List<Packet<?>> bufferedPackets = new ArrayList();
    private final SliderSetting upOffset = new SliderSetting("Вверх", 1.2, 0.1, 5.0, 0.1);
    private double savedX;
    private double savedY;
    private double savedZ;
    private float savedYaw;
    private float savedPitch;
    private boolean positionSaved = false;

    @Subscribe
    private void onPacket(EventPacket e2) {
        Packet<?> packet;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (e2.getPacket() instanceof PlayerMoveC2SPacket) {
            this.bufferedPackets.add(e2.getPacket());
            e2.cancelEvent();
            return;
        }
        if (e2.getType() == EventPacket.Type.RECEIVE && (packet = e2.getPacket()) instanceof PlayerPositionLookS2CPacket) {
            PlayerPositionLookS2CPacket packet2 = (PlayerPositionLookS2CPacket)packet;
            NetworkUtils.sendPacket(new TeleportConfirmC2SPacket(packet2.teleportId()));
            double yOffset = this.upOffset.getFloatValue();
            double x2 = packet2.comp_3228().comp_3148().getX();
            double y2 = packet2.comp_3228().comp_3148().getY() + yOffset;
            double z2 = packet2.comp_3228().comp_3148().getZ();
            this.savedX = x2;
            this.savedY = y2;
            this.savedZ = z2;
            this.savedYaw = packet2.comp_3228().yaw();
            this.savedPitch = packet2.comp_3228().pitch();
            this.positionSaved = true;
            this.mc.player.setPosition(this.savedX, this.savedY, this.savedZ);
            this.mc.player.setYaw(this.savedYaw);
            this.mc.player.setPitch(this.savedPitch);
            this.bufferedPackets.clear();
            this.sendPositionPacket();
            e2.cancelEvent();
        }
    }

    @Subscribe
    private void onPlayerUpdate(EventPlayerUpdate ignored) {
        if (this.mc.player == null) {
            return;
        }
        if (!this.positionSaved) {
            this.savedX = this.mc.player.getX();
            this.savedY = this.mc.player.getY();
            this.savedZ = this.mc.player.getZ();
            this.savedYaw = this.mc.player.getYaw();
            this.savedPitch = this.mc.player.getPitch();
            this.positionSaved = true;
        }
        this.mc.player.setPosition(this.savedX, this.savedY, this.savedZ);
        this.mc.player.setVelocity(0.0, 0.0, 0.0);
    }

    private void sendPositionPacket() {
        if (this.mc.player == null || this.mc.player.networkHandler == null) {
            return;
        }
        NetworkUtils.sendSilentPacket(new PlayerMoveC2SPacket.Full(this.savedX, this.savedY, this.savedZ, this.savedYaw, this.savedPitch, false, false));
    }

    @Override
    public void onEnable() {
        this.bufferedPackets.clear();
        this.positionSaved = false;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (!this.bufferedPackets.isEmpty()) {
            ArrayList copy = new ArrayList(this.bufferedPackets);
            for (Packet packet : copy) {
                this.mc.player.networkHandler.sendPacket(packet);
            }
            this.bufferedPackets.clear();
        }
        this.positionSaved = false;
        super.onDisable();
    }
}

