/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 */
package code.alphadlc.features.impl.misc.serverhelper;

import code.alphadlc.features.impl.misc.serverhelper.ServerHelperItemConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1792;
import net.minecraft.class_1802;

public final class ServerHelperItemRegistry {
    private static final Map<String, ServerHelperItemConfig> BY_NAME = new HashMap<String, ServerHelperItemConfig>();

    private ServerHelperItemRegistry() {
    }

    private static void register(String name, class_1792 item) {
        BY_NAME.put(ServerHelperItemRegistry.cleanKey(name), new ServerHelperItemConfig(item, name));
    }

    private static void register(String name, class_1792 item, String lore) {
        BY_NAME.put(ServerHelperItemRegistry.cleanKey(name), new ServerHelperItemConfig(item, name, List.of(lore.toLowerCase())));
    }

    public static ServerHelperItemConfig get(String name) {
        if (name == null) {
            return null;
        }
        return BY_NAME.get(ServerHelperItemRegistry.cleanKey(name));
    }

    private static String cleanKey(String name) {
        return name.toLowerCase().trim();
    }

    static {
        ServerHelperItemRegistry.register("\u041f\u0443\u0437\u044b\u0440\u0451\u043a \u043e\u043f\u044b\u0442\u0430", class_1802.field_8287);
        ServerHelperItemRegistry.register("\u0422\u0440\u0430\u043f\u043a\u0430", class_1802.field_8882, "\u043f\u0440\u0438 \u0432\u0437\u0440\u044b\u0432\u0435 \u043d\u0430\u043d\u043e\u0441\u0438\u0442 \u0443\u0440\u043e\u043d");
        ServerHelperItemRegistry.register("\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", class_1802.field_8662, "\u043f\u0440\u0438 \u0432\u0437\u0440\u044b\u0432\u0435 \u043d\u0430\u043d\u043e\u0441\u0438\u0442 \u0443\u0440\u043e\u043d \u0432 \u0440\u0430\u0434\u0438\u0443\u0441\u0435");
        ServerHelperItemRegistry.register("\u041f\u0440\u043e\u0449\u0430\u043b\u044c\u043d\u044b\u0439 \u0433\u0443\u043b", class_1802.field_8693, "\u043e\u0442\u0442\u0430\u043b\u043a\u0438\u0432\u0430\u0435\u0442 \u0432\u0441\u0435\u0445 \u0438\u0433\u0440\u043e\u043a\u043e\u0432");
        ServerHelperItemRegistry.register("\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0448\u0442\u0443\u0447\u043a\u0430", class_1802.field_8814, "\u043f\u0440\u0438 \u043d\u0430\u0436\u0430\u0442\u0438\u0438 \u043f\u043a\u043c \u0432\u044b\u0437\u044b\u0432\u0430\u0435\u0442 \u0432\u0437\u0440\u044b\u0432");
        ServerHelperItemRegistry.register("\u0421\u0442\u0430\u043d", class_1802.field_8137, "\u0441\u043e\u0437\u0434\u0430\u0435\u0442 \u043a\u0443\u0431");
        ServerHelperItemRegistry.register("\u041a\u043e\u043c \u0441\u043d\u0435\u0433\u0430", class_1802.field_8543, "\u043f\u0440\u0438 \u043f\u043e\u043f\u0430\u0434\u0430\u043d\u0438\u0438 \u043d\u0430\u043a\u043b\u0430\u0434\u044b\u0432\u0430\u0435\u0442 \u0441\u043b\u0435\u043f\u043e\u0442\u0443");
        ServerHelperItemRegistry.register("\u041e\u0431\u044b\u0447\u043d\u0430\u044f \u043b\u0438\u0432\u0430\u043b\u043a\u0430", class_1802.field_8153);
        ServerHelperItemRegistry.register("\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u043e\u0435 \u043f\u0435\u0440\u044b\u0448\u043a\u043e", class_1802.field_8153);
        ServerHelperItemRegistry.register("\u041b\u0438\u0432\u0430\u043b\u043a\u0430 \u0441 \u043f\u043b\u0430\u0442\u0444\u043e\u0440\u043c\u043e\u0439", class_1802.field_8153);
        ServerHelperItemRegistry.register("\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", class_1802.field_22021);
    }
}
