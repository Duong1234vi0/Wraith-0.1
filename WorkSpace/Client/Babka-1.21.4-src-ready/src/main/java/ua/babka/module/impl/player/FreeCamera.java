package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import com.mojang.authlib.GameProfile;
import java.util.Set;
import java.util.UUID;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerPosition;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.util.math.Vec3d;
import ua.babka.event.EventPacket;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.SliderSetting;
import ua.babka.util.network.NetworkUtils;
import ua.babka.util.player.MoveUtil;

@ModuleInformation(moduleName="Free Camera", moduleDesc="Свободная камера вне тела", moduleCategory=ModuleCategory.PLAYER)
public class FreeCamera
extends Module {
    public final SliderSetting xyi = new SliderSetting("Скорость по Y", 0.5, 0.1, 1.0, 0.1f);
    private Vec3d frozenPos;
    private float frozenYaw;
    private float frozenPitch;
    public OtherClientPlayerEntity fakePlayer;

    @Override
    public void onEnable() {
        if (this.mc.player == null || this.mc.world == null || this.mc.getNetworkHandler() == null) {
            return;
        }
        this.frozenPos = this.mc.player.getPos();
        this.frozenYaw = this.mc.player.getYaw();
        this.frozenPitch = this.mc.player.getPitch();
        GameProfile profile = new GameProfile(UUID.randomUUID(), this.mc.player.getName().getString());
        this.fakePlayer = new OtherClientPlayerEntity(this.mc.world, profile);
        this.fakePlayer.copyFrom((Entity)this.mc.player);
        this.fakePlayer.setPos(this.frozenPos.x, this.frozenPos.y, this.frozenPos.z);
        this.fakePlayer.setYaw(this.frozenYaw);
        this.fakePlayer.setPitch(this.frozenPitch);
        this.mc.world.addEntity((Entity)this.fakePlayer);
        this.mc.player.noClip = true;
        this.mc.player.setVelocity(Vec3d.ZERO);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        this.mc.player.noClip = false;
        this.mc.player.setPos(this.frozenPos.x, this.frozenPos.y, this.frozenPos.z);
        this.mc.player.setYaw(this.frozenYaw);
        this.mc.player.setPitch(this.frozenPitch);
        this.mc.player.setVelocity(Vec3d.ZERO);
        if (this.fakePlayer != null) {
            this.mc.world.removeEntity(this.fakePlayer.getId(), Entity.RemovalReason.DISCARDED);
            this.fakePlayer = null;
        }
        super.onDisable();
    }

    @Subscribe
    private void onLivingUpdate(EventTick e2) {
        if (this.mc.player == null) {
            return;
        }
        this.mc.player.noClip = true;
        this.mc.player.setVelocity(Vec3d.ZERO);
        float speed = (float)this.xyi.getValue();
        Vec3d motion = Vec3d.ZERO;
        MoveUtil.setMotion(1.0);
        if (this.mc.options.jumpKey.isPressed()) {
            motion = motion.add(0.0, (double)speed, 0.0);
        }
        if (this.mc.options.sneakKey.isPressed()) {
            motion = motion.subtract(0.0, (double)speed, 0.0);
        }
        this.mc.player.setVelocity(this.mc.player.getVelocity().x, motion.y, this.mc.player.getVelocity().z);
    }

    private void setPosition(PlayerPosition pos, Set<PositionFlag> flags) {
        PlayerPosition playerPosition = PlayerPosition.fromEntityLerpTarget((Entity)this.fakePlayer);
        PlayerPosition playerPosition2 = PlayerPosition.apply((PlayerPosition)playerPosition, (PlayerPosition)pos, flags);
        this.frozenPos = new Vec3d(playerPosition2.comp_3148().getX(), playerPosition2.comp_3148().getY(), playerPosition2.comp_3148().getZ());
        this.frozenYaw = playerPosition2.yaw();
        this.frozenPitch = playerPosition2.pitch();
    }

    @Subscribe
    private void onPacket(EventPacket e2) {
        Packet<?> packet = e2.getPacket();
        if (packet instanceof PlayerPositionLookS2CPacket) {
            PlayerPositionLookS2CPacket packet2 = (PlayerPositionLookS2CPacket)packet;
            if (this.mc.world == null || this.mc.player == null || !this.mc.player.isAlive()) {
                return;
            }
            NetworkUtils.sendPacket(new TeleportConfirmC2SPacket(packet2.teleportId()));
            if (!this.mc.player.hasVehicle()) {
                this.setPosition(packet2.comp_3228(), packet2.comp_3229());
            }
            NetworkUtils.sendSilentPacket(new PlayerMoveC2SPacket.Full(this.frozenPos.getX(), this.frozenPos.getY(), this.frozenPos.getZ(), this.frozenYaw, this.frozenPitch, false, false));
            e2.cancelEvent();
        }
        if (e2.getPacket() instanceof PlayerMoveC2SPacket) {
            e2.cancelEvent();
        }
        if ((packet = e2.getPacket()) instanceof PlayerInteractBlockC2SPacket) {
            PlayerInteractBlockC2SPacket p2 = (PlayerInteractBlockC2SPacket)packet;
            NetworkUtils.sendPacket(new PlayerInteractItemC2SPacket(p2.getHand(), p2.getSequence(), this.mc.player.getYaw(), this.mc.player.getPitch()));
            e2.cancelEvent();
        }
        if (e2.getPacket() instanceof PlayerRespawnS2CPacket) {
            this.setEnabled(false);
        }
        if (e2.getPacket() instanceof GameJoinS2CPacket) {
            this.setEnabled(false);
        }
        if (e2.getPacket() instanceof DisconnectS2CPacket) {
            this.setEnabled(false);
        }
    }
}

