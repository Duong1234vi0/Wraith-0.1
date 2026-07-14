package ua.babka.server;

import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.world.World;
import ua.babka.Babka;
import ua.babka.event.EventPacket;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.event.EventPopTotem;
import ua.babka.event.EventTick;
import ua.babka.util.IMinecraft;

public class ServerManager
implements IMinecraft {
    private int serverSlot;
    private float serverYaw;
    private float serverPitch;
    private float fallDistance;
    private double serverX;
    private double serverY;
    private double serverZ;
    private boolean serverOnGround;
    private boolean serverSprinting;
    private boolean serverSneaking;
    private boolean serverHorizontalCollision;
    private float sprintingChangeTicks;

    public ServerManager() {
        Babka.getInstance().getEventBus().register((Object)this);
    }

    @Subscribe
    private void onTick(EventTick ignored) {
        if (ServerManager.mc.player == null) {
            return;
        }
        double y2 = ServerManager.mc.player.prevY - ServerManager.mc.player.getY();
        if (ServerManager.mc.player.isOnGround()) {
            this.fallDistance = 0.0f;
        } else if (y2 > 0.0) {
            this.fallDistance += (float)y2;
        }
    }

    @Subscribe
    private void onPlayerUpdate(EventPlayerUpdate ignored) {
        this.sprintingChangeTicks += 1.0f;
    }

    @Subscribe
    public void onPacketSend(EventPacket e2) {
        PlayerMoveC2SPacket packet;
        Packet<?> packet2 = e2.getPacket();
        if (packet2 instanceof PlayerMoveC2SPacket) {
            packet = (PlayerMoveC2SPacket)packet2;
            if (packet.changesPosition()) {
                this.serverX = packet.getX(ServerManager.mc.player.getX());
                this.serverY = packet.getY(ServerManager.mc.player.getY());
                this.serverZ = packet.getZ(ServerManager.mc.player.getZ());
            }
            if (packet.changesLook()) {
                this.serverYaw = packet.getYaw(ServerManager.mc.player.getYaw());
                this.serverPitch = packet.getPitch(ServerManager.mc.player.getPitch());
            }
            this.serverOnGround = packet.isOnGround();
            this.serverHorizontalCollision = packet.horizontalCollision();
        }
        if ((packet2 = e2.getPacket()) instanceof UpdateSelectedSlotC2SPacket) {
            packet = (UpdateSelectedSlotC2SPacket)packet2;
            this.serverSlot = packet.getSelectedSlot();
        }
        if ((packet2 = e2.getPacket()) instanceof ClientCommandC2SPacket) {
            packet = (ClientCommandC2SPacket)packet2;
            switch (packet.getMode()) {
                case PRESS_SHIFT_KEY: {
                    this.serverSneaking = true;
                    break;
                }
                case RELEASE_SHIFT_KEY: {
                    this.serverSneaking = false;
                }
            }
        }
        if ((packet2 = e2.getPacket()) instanceof ClientCommandC2SPacket) {
            ClientCommandC2SPacket command = (ClientCommandC2SPacket)packet2;
            if (command.getMode().equals((Object)ClientCommandC2SPacket.Mode.START_SPRINTING)) {
                e2.setCancelled(this.serverSprinting);
                if (!e2.isCancelled()) {
                    this.sprintingChangeTicks = 0.0f;
                }
                this.serverSprinting = true;
            } else if (command.getMode().equals((Object)ClientCommandC2SPacket.Mode.STOP_SPRINTING)) {
                e2.setCancelled(!this.serverSprinting);
                if (!e2.isCancelled()) {
                    this.sprintingChangeTicks = 0.0f;
                }
                this.serverSprinting = false;
            }
        }
    }

    @Subscribe
    public void onPacketReceive(EventPacket e2) {
        EntityStatusS2CPacket packet;
        Packet<?> packet2 = e2.getPacket();
        if (packet2 instanceof EntityStatusS2CPacket && (packet = (EntityStatusS2CPacket)packet2).getStatus() == 35) {
            Entity entity = packet.getEntity((World)ServerManager.mc.world);
            if (!(entity instanceof PlayerEntity)) {
                return;
            }
            PlayerEntity player = (PlayerEntity)entity;
            new EventPopTotem(player).post();
        }
    }

    public int getServerSlot() {
        return this.serverSlot;
    }

    public float getServerYaw() {
        return this.serverYaw;
    }

    public float getServerPitch() {
        return this.serverPitch;
    }

    public float getFallDistance() {
        return this.fallDistance;
    }

    public double getServerX() {
        return this.serverX;
    }

    public double getServerY() {
        return this.serverY;
    }

    public double getServerZ() {
        return this.serverZ;
    }

    public boolean isServerOnGround() {
        return this.serverOnGround;
    }

    public boolean isServerSprinting() {
        return this.serverSprinting;
    }

    public boolean isServerSneaking() {
        return this.serverSneaking;
    }

    public boolean isServerHorizontalCollision() {
        return this.serverHorizontalCollision;
    }

    public float getSprintingChangeTicks() {
        return this.sprintingChangeTicks;
    }
}

