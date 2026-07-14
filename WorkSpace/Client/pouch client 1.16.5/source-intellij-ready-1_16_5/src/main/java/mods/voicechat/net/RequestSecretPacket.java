/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.net.Packet;

public class RequestSecretPacket
implements Packet<RequestSecretPacket> {
    public static final g_2336_b REQUEST_SECRET = new g_2336_b("voicechat", "request_secret");
    private int compatibilityVersion;

    public RequestSecretPacket() {
    }

    public RequestSecretPacket(int compatibilityVersion) {
        this.compatibilityVersion = compatibilityVersion;
    }

    public int getCompatibilityVersion() {
        return this.compatibilityVersion;
    }

    @Override
    public g_2336_b getIdentifier() {
        return REQUEST_SECRET;
    }

    @Override
    public RequestSecretPacket fromBytes(b_2585_i buf) {
        this.compatibilityVersion = buf.readInt();
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.writeInt(this.compatibilityVersion);
    }
}

