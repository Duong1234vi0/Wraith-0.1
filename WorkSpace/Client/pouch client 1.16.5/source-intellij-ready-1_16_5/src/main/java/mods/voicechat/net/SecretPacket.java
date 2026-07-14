/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import java.util.UUID;
import lightning.product.B_4088_l;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.config.ServerConfig;
import mods.voicechat.net.Packet;
import mods.voicechat.plugins.PluginManager;
import mods.voicechat.voice.common.Secret;

public class SecretPacket
implements Packet<SecretPacket> {
    public static final g_2336_b SECRET = new g_2336_b("voicechat", "secret");
    private Secret secret;
    private int serverPort;
    private UUID playerUUID;
    private ServerConfig.Codec codec;
    private int mtuSize;
    private double voiceChatDistance;
    private int keepAlive;
    private boolean groupsEnabled;
    private String voiceHost;
    private boolean allowRecording;

    public SecretPacket() {
    }

    public SecretPacket(B_4088_l player, Secret secret, int port, ServerConfig serverConfig) {
        this.secret = secret;
        this.serverPort = port;
        this.playerUUID = player.w_2705_t();
        this.codec = (ServerConfig.Codec)((Object)serverConfig.voiceChatCodec.get());
        this.mtuSize = (Integer)serverConfig.voiceChatMtuSize.get();
        this.voiceChatDistance = (Double)serverConfig.voiceChatDistance.get();
        this.keepAlive = (Integer)serverConfig.keepAlive.get();
        this.groupsEnabled = (Boolean)serverConfig.groupsEnabled.get();
        this.voiceHost = PluginManager.instance().getVoiceHost((String)serverConfig.voiceHost.get());
        this.allowRecording = (Boolean)serverConfig.allowRecording.get();
    }

    public Secret getSecret() {
        return this.secret;
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public UUID getPlayerUUID() {
        return this.playerUUID;
    }

    public ServerConfig.Codec getCodec() {
        return this.codec;
    }

    public int getMtuSize() {
        return this.mtuSize;
    }

    public double getVoiceChatDistance() {
        return this.voiceChatDistance;
    }

    public int getKeepAlive() {
        return this.keepAlive;
    }

    public boolean groupsEnabled() {
        return this.groupsEnabled;
    }

    public String getVoiceHost() {
        return this.voiceHost;
    }

    @Override
    public g_2336_b getIdentifier() {
        return SECRET;
    }

    public boolean allowRecording() {
        return this.allowRecording;
    }

    @Override
    public SecretPacket fromBytes(b_2585_i buf) {
        this.secret = Secret.fromBytes(buf);
        this.serverPort = buf.readInt();
        this.playerUUID = buf.w_1484_f();
        this.codec = ServerConfig.Codec.values()[buf.readByte()];
        this.mtuSize = buf.readInt();
        this.voiceChatDistance = buf.readDouble();
        this.keepAlive = buf.readInt();
        this.groupsEnabled = buf.readBoolean();
        this.voiceHost = buf.P_1922_E(Short.MAX_VALUE);
        this.allowRecording = buf.readBoolean();
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        this.secret.toBytes(buf);
        buf.writeInt(this.serverPort);
        buf.n_1700_B(this.playerUUID);
        buf.writeByte(this.codec.ordinal());
        buf.writeInt(this.mtuSize);
        buf.writeDouble(this.voiceChatDistance);
        buf.writeInt(this.keepAlive);
        buf.writeBoolean(this.groupsEnabled);
        buf.n_1700_B(this.voiceHost);
        buf.writeBoolean(this.allowRecording);
    }
}

