package ua.babka.util.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.session.Session;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.CommonPongC2SPacket;
import net.minecraft.network.packet.c2s.common.KeepAliveC2SPacket;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;
import net.minecraft.network.packet.s2c.common.CommonPingS2CPacket;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.common.KeepAliveS2CPacket;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import ua.babka.mixin.IMinecraftClientAccessor;

public final class BotSessionManager {
    private static final List<BotConnection> connections = new CopyOnWriteArrayList<BotConnection>();

    public static List<BotConnection> getConnections() {
        return new ArrayList<BotConnection>(connections);
    }

    public static void connect(String name, String address) {
        MinecraftClient mc = MinecraftClient.getInstance();
        BotSessionManager.freezeCurrentSession();
        ((IMinecraftClientAccessor)mc).setSession(BotSessionManager.createSessionWithName(mc.getSession(), name));
        mc.execute(() -> ConnectScreen.connect((Screen)new MultiplayerScreen((Screen)new TitleScreen()), (MinecraftClient)mc, (ServerAddress)ServerAddress.parse((String)address), (ServerInfo)new ServerInfo(address, address, ServerInfo.ServerType.OTHER), (boolean)false, null));
        connections.removeIf(b2 -> b2.name().equalsIgnoreCase(name));
    }

    public static void pulseBots(boolean rightClick) {
        for (BotConnection bot : connections) {
            if (bot.handler() == null || bot.player() == null) continue;
            if (rightClick) {
                bot.handler().sendPacket((Packet)new PlayerInteractItemC2SPacket(Hand.MAIN_HAND, 0, bot.player().getYaw(), bot.player().getPitch()));
                continue;
            }
            bot.handler().sendPacket((Packet)new HandSwingC2SPacket(Hand.MAIN_HAND));
        }
    }

    public static void sayAll(String message) {
        for (BotConnection bot : connections) {
            if (bot.handler() == null) continue;
            if (message.startsWith("/")) {
                bot.handler().sendChatCommand(message.substring(1));
                continue;
            }
            bot.handler().sendChatMessage(message);
        }
    }

    public static boolean control(String name) {
        MinecraftClient mc = MinecraftClient.getInstance();
        return connections.stream().filter(b2 -> b2.name().equalsIgnoreCase(name)).findFirst().map(bot -> {
            BotSessionManager.freezeCurrentSession();
            connections.remove(bot);
            Channel ch = BotSessionManager.getChannel(bot.connection());
            if (ch != null && ch.pipeline().get("bot_filter") != null) {
                ch.pipeline().remove("bot_filter");
            }
            mc.world = bot.world();
            mc.player = bot.player();
            mc.cameraEntity = bot.player();
            mc.interactionManager = bot.interactionManager();
            try {
                for (Field f2 : MinecraftClient.class.getDeclaredFields()) {
                    if (f2.getType() != ClientPlayNetworkHandler.class) continue;
                    f2.setAccessible(true);
                    f2.set(mc, bot.handler());
                    break;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (mc.worldRenderer != null) {
                mc.worldRenderer.setWorld(bot.world());
                mc.worldRenderer.reload();
            }
            bot.handler().sendPacket((Packet)new PlayerMoveC2SPacket.Full(mc.player.getX(), mc.player.getY(), mc.player.getZ(), mc.player.getYaw(), mc.player.getPitch(), mc.player.isOnGround(), mc.player.horizontalCollision));
            ((IMinecraftClientAccessor)mc).setSession(BotSessionManager.createSessionWithName(mc.getSession(), bot.name()));
            mc.setScreen(null);
            return true;
        }).orElse(false);
    }

    private static void freezeCurrentSession() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.getNetworkHandler() != null && mc.world != null) {
            ClientPlayNetworkHandler h2 = mc.getNetworkHandler();
            BotSessionManager.makeNettyBot(h2, mc.getSession().getUsername(), mc.player);
            connections.add(new BotConnection(mc.getSession().getUsername(), mc.getCurrentServerEntry() != null ? mc.getCurrentServerEntry().address : "", h2.getConnection(), h2, mc.world, mc.player, mc.interactionManager));
            mc.world = null;
            mc.player = null;
            try {
                for (Field f2 : MinecraftClient.class.getDeclaredFields()) {
                    if (f2.getType() != ClientPlayNetworkHandler.class) continue;
                    f2.setAccessible(true);
                    f2.set(mc, null);
                    break;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private static void makeNettyBot(final ClientPlayNetworkHandler handler, final String name, final ClientPlayerEntity botPlayer) {
        Channel channel = BotSessionManager.getChannel(handler.getConnection());
        if (channel == null) {
            return;
        }
        channel.pipeline().addBefore("packet_handler", "bot_filter", (ChannelHandler)new ChannelDuplexHandler(){

            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                if (msg instanceof KeepAliveS2CPacket) {
                    KeepAliveS2CPacket p2 = (KeepAliveS2CPacket)msg;
                    handler.getConnection().send((Packet)new KeepAliveC2SPacket(p2.getId()));
                    if (botPlayer != null) {
                        handler.sendPacket((Packet)new PlayerMoveC2SPacket.OnGroundOnly(botPlayer.isOnGround(), botPlayer.horizontalCollision));
                    }
                    ReferenceCountUtil.release((Object)msg);
                    return;
                }
                if (msg instanceof CommonPingS2CPacket) {
                    CommonPingS2CPacket p3 = (CommonPingS2CPacket)msg;
                    handler.getConnection().send((Packet)new CommonPongC2SPacket(p3.getParameter()));
                    ReferenceCountUtil.release((Object)msg);
                    return;
                }
                if (msg instanceof PlayerPositionLookS2CPacket) {
                    PlayerPositionLookS2CPacket p4 = (PlayerPositionLookS2CPacket)msg;
                    handler.sendPacket((Packet)new TeleportConfirmC2SPacket(p4.teleportId()));
                    ReferenceCountUtil.release((Object)msg);
                    return;
                }
                if (msg instanceof HealthUpdateS2CPacket) {
                    HealthUpdateS2CPacket p5 = (HealthUpdateS2CPacket)msg;
                    if (botPlayer != null) {
                        botPlayer.setHealth(p5.getHealth());
                    }
                    ReferenceCountUtil.release((Object)msg);
                    return;
                }
                if (msg instanceof DisconnectS2CPacket) {
                    connections.removeIf(b2 -> b2.name().equalsIgnoreCase(name));
                    ctx.close();
                    ReferenceCountUtil.release((Object)msg);
                    return;
                }
                String pName = msg.getClass().getSimpleName();
                if (pName.contains("Sound") || pName.contains("Particle") || pName.contains("Screen") || pName.contains("Chat") || pName.contains("Title") || pName.contains("Explosion") || pName.contains("BossBar") || pName.contains("Scoreboard") || pName.contains("OverlayMessage")) {
                    ReferenceCountUtil.release((Object)msg);
                    return;
                }
                super.channelRead(ctx, msg);
            }
        });
    }

    public static boolean say(String n2, String m2) {
        return connections.stream().filter(b2 -> b2.name().equalsIgnoreCase(n2)).findFirst().map(b2 -> {
            if (m2.startsWith("/")) {
                b2.handler().sendChatCommand(m2.substring(1));
            } else {
                b2.handler().sendChatMessage(m2);
            }
            return true;
        }).orElse(false);
    }

    public static boolean remove(String n2) {
        return connections.removeIf(b2 -> {
            if (b2.name().equalsIgnoreCase(n2)) {
                b2.connection().disconnect((Text)Text.literal((String)"Removed"));
                return true;
            }
            return false;
        });
    }

    public static boolean restore() {
        return !connections.isEmpty() && BotSessionManager.control(connections.get(connections.size() - 1).name());
    }

    private static Channel getChannel(ClientConnection c2) {
        try {
            for (Field f2 : ClientConnection.class.getDeclaredFields()) {
                if (!Channel.class.isAssignableFrom(f2.getType())) continue;
                f2.setAccessible(true);
                return (Channel)f2.get(c2);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    private static Session createSessionWithName(Session current, String name) {
        try {
            Constructor c2 = Session.class.getDeclaredConstructor(String.class, UUID.class, String.class, Optional.class, Optional.class, Session.AccountType.class);
            c2.setAccessible(true);
            return (Session)c2.newInstance(name, UUID.randomUUID(), current.getAccessToken(), Optional.empty(), Optional.empty(), Session.AccountType.MOJANG);
        }
        catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public record BotConnection(String name, String address, ClientConnection connection, ClientPlayNetworkHandler handler, ClientWorld world, ClientPlayerEntity player, ClientPlayerInteractionManager interactionManager) {
    }
}

