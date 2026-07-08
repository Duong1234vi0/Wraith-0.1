/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2561
 */
package code.alphadlc.features.impl.misc.serverhelper;

import code.alphadlc.display.hud.Notifications;
import code.alphadlc.events.chat.ChatEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.impl.misc.ServerHelper;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperItemConfig;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperRenderItem;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.item.ItemTask;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2561;

public abstract class ServerHelperHandler
implements QuickImports {
    public static void useAntiFlight() {
        int slot = InventoryTask.findHotbarSlot(class_1802.field_8450);
        if (slot == -1) {
            slot = InventoryTask.findInventorySlot(class_1802.field_8450);
        }
        if (slot == -1) {
            ServerHelperHandler.notify("\u00a7c\u0423 \u0432\u0430\u0441 \u043e\u0442\u0441\u0443\u0442\u0441\u0442\u0432\u0443\u0435\u0442 \u0430\u043d\u0442\u0438 \u043f\u043e\u043b\u0451\u0442!");
            return;
        }
        ServerHelper.getInstance().queueSlotUse(slot);
    }

    public abstract String getModeId();

    public abstract String getOverlayTitle();

    public abstract List<BindSetting> getBinds();

    public abstract List<ServerHelperRenderItem> buildRenderItems(boolean var1);

    public abstract boolean onKey(int var1);

    public abstract boolean hasPendingUse();

    public abstract int getPendingSlot();

    public abstract void clearPending();

    public BooleanSetting getAntiCrashSetting() {
        return null;
    }

    public void setOnlyHotbar(boolean onlyHotbar) {
    }

    public void tick() {
    }

    public void onPacketReceive(PacketEvent event) {
    }

    public void onChatSend(ChatEvent event) {
    }

    protected static String cleanName(String value) {
        String stripped = ServerHelperHandler.stripFormatting(value == null ? "" : value);
        return ServerHelperHandler.normalizeSpaces(stripped).toLowerCase(Locale.ROOT);
    }

    protected static String stripFormatting(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder(value.length());
        boolean lastWasSpace = false;
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            if (c == '\u00ab' || c == '\u00bb' || c == '\"' || c == '\'' || c == '`') continue;
            if (Character.isWhitespace(c)) {
                if (lastWasSpace || result.isEmpty()) continue;
                result.append(' ');
                lastWasSpace = true;
                continue;
            }
            result.append(c);
            lastWasSpace = false;
        }
        int length = result.length();
        if (length > 0 && result.charAt(length - 1) == ' ') {
            result.setLength(length - 1);
        }
        return result.toString();
    }

    private static String normalizeSpaces(String value) {
        return value.replaceAll("\u00a7[0-9a-fk-or]", "").trim();
    }

    protected static int findSlotByName(String searchQuery) {
        if (ServerHelperHandler.mc.field_1724 == null || searchQuery == null || searchQuery.isEmpty()) {
            return -1;
        }
        String needle = searchQuery.toLowerCase(Locale.ROOT);
        for (int i = 0; i < 36; ++i) {
            String cleanName;
            class_1799 stack = ServerHelperHandler.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960() || !(cleanName = ServerHelperHandler.cleanName(stack.method_7964().getString())).contains(needle)) continue;
            return i;
        }
        return -1;
    }

    protected static int findSlotByItem(class_1792 item) {
        if (ServerHelperHandler.mc.field_1724 == null || item == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            class_1799 stack = ServerHelperHandler.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960() || !stack.method_31574(item)) continue;
            return i;
        }
        return -1;
    }

    protected static int findSlotByConfig(ServerHelperItemConfig config) {
        if (ServerHelperHandler.mc.field_1724 == null || config == null) {
            return -1;
        }
        String cleanItemName = ServerHelperHandler.cleanName(config.displayName());
        for (int i = 0; i < 36; ++i) {
            boolean nameMatches;
            class_1799 stack = ServerHelperHandler.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960()) continue;
            String cleanDisplayName = ServerHelperHandler.cleanName(stack.method_7964().getString());
            boolean bl = nameMatches = cleanDisplayName.contains(cleanItemName) || cleanItemName.contains(cleanDisplayName) || cleanDisplayName.equals(cleanItemName);
            if (nameMatches && (config.loreParams().isEmpty() || ServerHelperHandler.matchesLore(stack, config.loreParams()))) {
                return i;
            }
            if (!stack.method_31574(config.item()) || !config.loreParams().isEmpty() && !ServerHelperHandler.matchesLore(stack, config.loreParams())) continue;
            return i;
        }
        return -1;
    }

    protected static boolean matchesLore(class_1799 stack, List<String> requiredParams) {
        if (requiredParams.isEmpty()) {
            return true;
        }
        String loreText = InventoryTask.getCleanName(stack.method_7964());
        for (String param : requiredParams) {
            if (loreText.contains(param.toLowerCase(Locale.ROOT))) continue;
            return false;
        }
        return true;
    }

    protected static String cooldownStatus(class_1792 item) {
        float cooldown = ItemTask.getCooldownProgress(item);
        if (cooldown > 0.0f) {
            return String.format("%.0f\u0441", Float.valueOf(cooldown * 20.0f));
        }
        return "\u2713";
    }

    protected static void notify(String message) {
        Notifications.getInstance().addList((class_2561)class_2561.method_43470((String)message), 3000);
    }

    protected static void notifyMissing(String itemName) {
        ServerHelperHandler.notify("\u00a7c" + itemName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435");
    }

    protected static void notifyHotbarOnly(String itemName) {
        ServerHelperHandler.notify("\u00a7c" + itemName + " \u043d\u0435 \u0432 \u0445\u043e\u0442\u0431\u0430\u0440\u0435");
    }

    protected static void notifyCooldown(String itemName) {
        ServerHelperHandler.notify("\u00a7c" + itemName + " \u043d\u0430 \u043a\u0443\u043b\u0434\u0430\u0443\u043d\u0435");
    }
}
