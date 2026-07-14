package ua.babka.integration.discord;

import java.lang.management.ManagementFactory;
import java.util.function.BiConsumer;
import java.util.function.Function;
import lombok.NonNull;
import ua.babka.integration.discord.ActivityInfo;
import ua.babka.integration.discord.IPCUser;
import ua.babka.integration.discord.connection.Connection;
import ua.babka.integration.discord.packet.CloseConnectionPacket;
import ua.babka.integration.discord.packet.DispatchPacket;
import ua.babka.integration.discord.packet.ErrorPacket;
import ua.babka.integration.discord.packet.HandsnakePacket;
import ua.babka.integration.discord.packet.S2CPacket;
import ua.babka.integration.discord.packet.SetActivityPacket;
import ua.babka.module.setting.OnChangeHolder;

public class DiscordIPC {
    private Connection connection;
    @NonNull
    private Runnable onReady = () -> {};
    @NonNull
    private BiConsumer<Integer, String> onError = (code, message) -> System.err.println("Discord IPC error " + code + " with message: " + message);
    private final OnChangeHolder<ActivityInfo> activityInfo = new OnChangeHolder<ActivityInfo>(new ActivityInfo(null, null, null, null, null, null, System.currentTimeMillis() / 1000L, null, null, null), info -> this.updateActivity());
    private boolean dispatch = false;
    private IPCUser user;

    public boolean start(long appId) {
        this.connection = Connection.open(this::onPacket);
        if (this.connection == null) {
            return false;
        }
        this.connection.write(new HandsnakePacket(appId, 1));
        return true;
    }

    public boolean isConnected() {
        return this.connection != null;
    }

    public void waitDispatch() {
        while (!this.dispatch) {
            Thread.yield();
        }
    }

    public void stop() {
        if (this.connection != null) {
            this.connection.close();
            this.dispatch = false;
            this.connection = null;
            this.user = null;
        }
    }

    public void updateActivity(Function<ActivityInfo, ActivityInfo> updateConsumer) {
        this.activityInfo.setValue(updateConsumer.apply(this.activityInfo.getValue()));
    }

    public void updateActivity() {
        if (this.isDispatch()) {
            this.connection.write(new SetActivityPacket(DiscordIPC.getPID(), this.activityInfo.getValue()));
        }
    }

    private void onPacket(S2CPacket p2) {
        Record packet;
        if (p2 instanceof CloseConnectionPacket) {
            packet = (CloseConnectionPacket)p2;
            this.onCloseConnection((CloseConnectionPacket)packet);
        }
        if (p2 instanceof ErrorPacket) {
            packet = (ErrorPacket)p2;
            this.onErrorPacket((ErrorPacket)packet);
        }
        if (p2 instanceof DispatchPacket) {
            packet = (DispatchPacket)p2;
            this.onDispatch((DispatchPacket)packet);
        }
    }

    private void onCloseConnection(CloseConnectionPacket packet) {
        this.onError.accept(packet.code(), packet.message());
        this.stop();
    }

    private void onErrorPacket(ErrorPacket packet) {
        this.onError.accept(packet.code(), packet.message());
    }

    private void onDispatch(DispatchPacket packet) {
        this.dispatch = true;
        this.user = packet.discordUser();
        this.onReady.run();
        this.updateActivity();
    }

    private static int getPID() {
        String pr = ManagementFactory.getRuntimeMXBean().getName();
        return Integer.parseInt(pr.substring(0, pr.indexOf(64)));
    }

    public void setOnReady(@NonNull Runnable onReady) {
        if (onReady == null) {
            throw new NullPointerException("onReady is marked non-null but is null");
        }
        this.onReady = onReady;
    }

    public void setOnError(@NonNull BiConsumer<Integer, String> onError) {
        if (onError == null) {
            throw new NullPointerException("onError is marked non-null but is null");
        }
        this.onError = onError;
    }

    public boolean isDispatch() {
        return this.dispatch;
    }

    public IPCUser getUser() {
        return this.user;
    }
}

