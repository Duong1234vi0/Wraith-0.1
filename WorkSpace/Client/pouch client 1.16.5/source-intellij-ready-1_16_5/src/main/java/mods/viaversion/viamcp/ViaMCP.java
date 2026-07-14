/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viabackwards.protocol.v1_17to1_16_4.Protocol1_17To1_16_4
 *  com.viaversion.viaversion.api.Via
 *  com.viaversion.viaversion.api.protocol.packet.ClientboundPacketType
 *  com.viaversion.viaversion.api.protocol.packet.ServerboundPacketType
 *  com.viaversion.viaversion.api.protocol.version.ProtocolVersion
 *  com.viaversion.viaversion.protocols.v1_16_1to1_16_2.packet.ClientboundPackets1_16_2
 *  com.viaversion.viaversion.protocols.v1_16_1to1_16_2.packet.ServerboundPackets1_16_2
 *  com.viaversion.viaversion.protocols.v1_16_4to1_17.packet.ClientboundPackets1_17
 *  com.viaversion.viaversion.protocols.v1_16_4to1_17.packet.ServerboundPackets1_17
 */
package mods.viaversion.viamcp;

import com.viaversion.viabackwards.protocol.v1_17to1_16_4.Protocol1_17To1_16_4;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.packet.ClientboundPacketType;
import com.viaversion.viaversion.api.protocol.packet.ServerboundPacketType;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.protocols.v1_16_1to1_16_2.packet.ClientboundPackets1_16_2;
import com.viaversion.viaversion.protocols.v1_16_1to1_16_2.packet.ServerboundPackets1_16_2;
import com.viaversion.viaversion.protocols.v1_16_4to1_17.packet.ClientboundPackets1_17;
import com.viaversion.viaversion.protocols.v1_16_4to1_17.packet.ServerboundPackets1_17;
import java.io.File;
import lightning.product.MinecraftClient;
import lightning.product.x_282_a;
import mods.viaversion.vialoadingbase.ViaLoadingBase;
import mods.viaversion.viamcp.gui.VersionSelectScreen;

public class ViaMCP {
    public static final int NATIVE_VERSION = 754;
    public static ViaMCP INSTANCE;
    private VersionSelectScreen versionSelectScreen;

    public static void create() {
        INSTANCE = new ViaMCP();
    }

    public VersionSelectScreen getVersionSelectScreen() {
        return this.versionSelectScreen;
    }

    public ViaMCP() {
        ViaLoadingBase.ViaLoadingBaseBuilder.create().runDirectory(new File("ViaMCP")).nativeVersion(754).onProtocolReload(protocolVersion -> {
            if (this.versionSelectScreen != null) {
                this.versionSelectScreen.setVersion(protocolVersion.getVersion());
            }
        }).build();
        this.versionSelectScreen = new VersionSelectScreen(MinecraftClient.A_4115_X().t_148_a, 5, 5, 75, 20, x_282_a.J_1907_R(ProtocolVersion.getProtocol((int)754).getName()));
        this.fixTransactions();
    }

    private void fixTransactions() {
        Protocol1_17To1_16_4 protocol = (Protocol1_17To1_16_4)Via.getManager().getProtocolManager().getProtocol(Protocol1_17To1_16_4.class);
        protocol.registerClientbound((ClientboundPacketType)ClientboundPackets1_17.PING, (ClientboundPacketType)ClientboundPackets1_16_2.CONTAINER_ACK, wrapper -> {}, true);
        protocol.registerServerbound((ServerboundPacketType)ServerboundPackets1_16_2.CONTAINER_ACK, (ServerboundPacketType)ServerboundPackets1_17.PONG, wrapper -> {}, true);
    }
}


