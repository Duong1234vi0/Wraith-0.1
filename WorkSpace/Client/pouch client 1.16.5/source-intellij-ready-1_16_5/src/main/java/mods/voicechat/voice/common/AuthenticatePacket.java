/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.common;

import java.util.UUID;
import lightning.product.b_2585_i;
import mods.voicechat.voice.common.Packet;
import mods.voicechat.voice.common.Secret;

public class AuthenticatePacket
implements Packet<AuthenticatePacket> {
    private UUID playerUUID;
    private Secret secret;

    public AuthenticatePacket(UUID playerUUID, Secret secret) {
        this.playerUUID = playerUUID;
        this.secret = secret;
    }

    public AuthenticatePacket() {
    }

    public UUID getPlayerUUID() {
        return this.playerUUID;
    }

    public Secret getSecret() {
        return this.secret;
    }

    @Override
    public AuthenticatePacket fromBytes(b_2585_i buf) {
        AuthenticatePacket packet = new AuthenticatePacket();
        packet.playerUUID = buf.w_1484_f();
        packet.secret = Secret.fromBytes(buf);
        return packet;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.n_1700_B(this.playerUUID);
        this.secret.toBytes(buf);
    }
}

