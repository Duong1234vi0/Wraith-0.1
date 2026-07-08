/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_310
 */
package code.alphadlc.main.client;

import net.minecraft.class_2596;
import net.minecraft.class_310;

public interface Api {
    public static final class_310 mc = class_310.method_1551();

    public static void sendPacket(class_2596<?> packet) {
        if (mc.method_1562() == null) {
            return;
        }
        mc.method_1562().method_52787(packet);
    }
}
