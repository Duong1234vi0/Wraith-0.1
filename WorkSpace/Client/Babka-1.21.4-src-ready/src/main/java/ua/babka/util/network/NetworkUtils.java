package ua.babka.util.network;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.packet.Packet;
import ua.babka.util.IMinecraft;

public final class NetworkUtils
implements IMinecraft {
    private static final List<Packet<?>> silentPackets = new ArrayList();

    public static void sendSilentPacket(Packet<?> packet) {
        silentPackets.add(packet);
        NetworkUtils.sendPacket(packet);
    }

    public static void sendPacket(Packet<?> packet) {
        mc.getNetworkHandler().sendPacket(packet);
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static List<Packet<?>> getSilentPackets() {
        return silentPackets;
    }
}

