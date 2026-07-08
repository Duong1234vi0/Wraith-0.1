/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_310
 */
package code.alphadlc.utils.network;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.class_2596;
import net.minecraft.class_310;

public class NetworkUtils {
    private static final class_310 mc = class_310.method_1551();
    private static final Set<class_2596<?>> silentPackets = new HashSet();

    public static void sendSilentPacket(class_2596<?> packet) {
        if (NetworkUtils.mc.field_1724 == null || NetworkUtils.mc.field_1724.field_3944 == null) {
            return;
        }
        silentPackets.add(packet);
        NetworkUtils.mc.field_1724.field_3944.method_52787(packet);
    }

    public static void sendPacket(class_2596<?> packet) {
        if (NetworkUtils.mc.field_1724 == null || NetworkUtils.mc.field_1724.field_3944 == null) {
            return;
        }
        NetworkUtils.mc.field_1724.field_3944.method_52787(packet);
    }

    public static boolean isSilent(class_2596<?> packet) {
        return silentPackets.contains(packet);
    }

    public static void removeSilent(class_2596<?> packet) {
        silentPackets.remove(packet);
    }

    public static void clearSilent() {
        silentPackets.clear();
    }
}
