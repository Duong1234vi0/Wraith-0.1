/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_266
 *  net.minecraft.class_268
 *  net.minecraft.class_269
 *  net.minecraft.class_270
 *  net.minecraft.class_2761
 *  net.minecraft.class_3532
 *  net.minecraft.class_8646
 *  net.minecraft.class_9011
 *  org.apache.commons.lang3.StringUtils
 */
package code.alphadlc.utils.client.packet.network;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.math.time.StopWatch;
import java.lang.runtime.SwitchBootstraps;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_266;
import net.minecraft.class_268;
import net.minecraft.class_269;
import net.minecraft.class_270;
import net.minecraft.class_2761;
import net.minecraft.class_3532;
import net.minecraft.class_8646;
import net.minecraft.class_9011;
import org.apache.commons.lang3.StringUtils;

public final class Network
implements QuickImports {
    private static final StopWatch pvpWatch = new StopWatch();
    public static String server = "Vanilla";
    public static float TPS = 20.0f;
    public static long timestamp;
    public static int anarchy;
    public static boolean pvpEnd;

    public static void tick() {
        anarchy = Network.getAnarchyMode();
        server = Network.getServer();
        pvpEnd = Network.inPvpEnd();
        if (Network.inPvp()) {
            pvpWatch.reset();
        }
    }

    public static void packet(PacketEvent e) {
        class_2596<?> class_25962 = e.getPacket();
        Objects.requireNonNull(class_25962);
        class_2596<?> class_25963 = class_25962;
        int n = 0;
        switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_2761.class}, class_25963, n)) {
            case 0: {
                class_2761 time = (class_2761)class_25963;
                long nanoTime = System.nanoTime();
                float maxTPS = 20.0f;
                float rawTPS = maxTPS * (1.0E9f / (float)(nanoTime - timestamp));
                TPS = class_3532.method_15363((float)rawTPS, (float)0.0f, (float)maxTPS);
                timestamp = nanoTime;
                break;
            }
        }
    }

    public static String getServer() {
        String title;
        class_266 objective;
        String brand;
        if (PlayerInteractionHelper.nullCheck() || mc.method_1562() == null || mc.method_1562().method_45734() == null) {
            return "Vanilla";
        }
        String serverIp = Network.mc.method_1562().method_45734().field_3761.toLowerCase();
        String string = brand = mc.method_1562().method_52790() != null ? mc.method_1562().method_52790().toLowerCase() : "";
        if (brand.contains("botfilter")) {
            return "FunTime";
        }
        if (brand.contains("\u00a76spooky\u00a7ccore")) {
            return "SpookyTime";
        }
        if (serverIp.contains("funtime") || serverIp.contains("skytime") || serverIp.contains("space-times") || serverIp.contains("funsky")) {
            return "CopyTime";
        }
        if (brand.contains("holyworld") || brand.contains("vk.com/idwok")) {
            return "HolyWorld";
        }
        if (serverIp.contains("reallyworld")) {
            return "ReallyWorld";
        }
        if (serverIp.contains("gulpvp")) {
            return "GulPvP";
        }
        if (Network.mc.field_1687 != null && Network.mc.field_1687.method_8428() != null && (objective = Network.mc.field_1687.method_8428().method_1189(class_8646.field_45157)) != null && (title = objective.method_1114().getString()) != null && (title.equals("HolyWorld.me") || title.equals("HolyWorld") || title.toLowerCase().contains("holyworld"))) {
            return "HolyWorld";
        }
        return "Vanilla";
    }

    private static int getAnarchyMode() {
        class_269 scoreboard = Network.mc.field_1687.method_8428();
        class_266 objective = scoreboard.method_1189(class_8646.field_45157);
        if (objective == null) {
            return -1;
        }
        switch (server) {
            case "FunTime": 
            case "SpookyTime": {
                String[] parts;
                String title = objective.method_1114().getString();
                if (title.contains("\u0410\u043d\u0430\u0440\u0445\u0438\u044f-") && (parts = title.split("\u0410\u043d\u0430\u0440\u0445\u0438\u044f-")).length > 1) {
                    try {
                        String numberStr = parts[1].trim().split(" ")[0];
                        return Integer.parseInt(numberStr);
                    }
                    catch (NumberFormatException numberStr) {
                        // empty catch block
                    }
                }
                List entries = scoreboard.method_1184(objective).stream().sorted((e1, e2) -> Integer.compare(e2.comp_2128(), e1.comp_2128())).toList();
                for (class_9011 entry : entries) {
                    String[] parts2;
                    String text = class_268.method_1142((class_270)scoreboard.method_1164(entry.comp_2127()), (class_2561)entry.method_55387()).getString();
                    if (!text.contains("\u0410\u043d\u0430\u0440\u0445\u0438\u044f-") || (parts2 = text.split("\u0410\u043d\u0430\u0440\u0445\u0438\u044f-")).length <= 1) continue;
                    try {
                        String numberStr = parts2[1].trim().split(" ")[0];
                        return Integer.parseInt(numberStr);
                    }
                    catch (NumberFormatException numberStr) {
                    }
                }
                break;
            }
            case "HolyWorld": {
                String numberAfterHash;
                class_9011 lastEntry;
                String lastText;
                String title = objective.method_1114().getString();
                List entries = scoreboard.method_1184(objective).stream().sorted((e1, e2) -> Integer.compare(e2.comp_2128(), e1.comp_2128())).toList();
                if (!entries.isEmpty() && !(lastText = class_268.method_1142((class_270)scoreboard.method_1164((lastEntry = (class_9011)entries.get(entries.size() - 1)).comp_2127()), (class_2561)lastEntry.method_55387()).getString()).isEmpty() && lastText.contains("#") && (numberAfterHash = StringUtils.substringAfter((String)lastText, (String)"#")) != null && !numberAfterHash.isEmpty()) {
                    try {
                        StringBuilder numBuilder = new StringBuilder();
                        for (char c : numberAfterHash.trim().toCharArray()) {
                            if (!Character.isDigit(c)) break;
                            numBuilder.append(c);
                        }
                        if (numBuilder.length() > 0) {
                            return Integer.parseInt(numBuilder.toString());
                        }
                    }
                    catch (NumberFormatException numBuilder) {
                        // empty catch block
                    }
                }
                for (class_9011 scoreboardEntry : entries) {
                    String numberAfterHash2;
                    String text = class_268.method_1142((class_270)scoreboard.method_1164(scoreboardEntry.comp_2127()), (class_2561)scoreboardEntry.method_55387()).getString();
                    if (text.isEmpty() || text.contains("\u041a\u043b\u0430\u043d:") || text.contains("\u0422\u0440\u0438\u043e:") || !text.contains("#") || (numberAfterHash2 = StringUtils.substringAfter((String)text, (String)"#")) == null || numberAfterHash2.isEmpty()) continue;
                    try {
                        StringBuilder numBuilder = new StringBuilder();
                        for (char c : numberAfterHash2.trim().toCharArray()) {
                            if (!Character.isDigit(c)) break;
                            numBuilder.append(c);
                        }
                        if (numBuilder.length() <= 0) continue;
                        return Integer.parseInt(numBuilder.toString());
                    }
                    catch (NumberFormatException numberFormatException) {
                    }
                }
                if (title == null || !title.equals("HolyWorld.me") && !title.equals("HolyWorld")) break;
                return -1;
            }
            case "ReallyWorld": {
                String title = objective.method_1114().getString();
                String number = StringUtils.substringAfter((String)title, (String)"#");
                if (number == null || number.isEmpty()) break;
                try {
                    return Integer.parseInt(number.trim().split(" ")[0]);
                }
                catch (NumberFormatException numberFormatException) {
                    // empty catch block
                }
            }
        }
        return -1;
    }

    public static boolean isPvp() {
        return !pvpWatch.finished(500.0);
    }

    private static boolean inPvp() {
        return Network.mc.field_1705.method_1740().field_2060.values().stream().map(c -> c.method_5414().getString().toLowerCase()).anyMatch(s -> s.contains("pvp") || s.contains("\u043f\u0432\u043f"));
    }

    private static boolean inPvpEnd() {
        return Network.mc.field_1705.method_1740().field_2060.values().stream().map(c -> c.method_5414().getString().toLowerCase()).anyMatch(s -> !(!s.contains("pvp") && !s.contains("\u043f\u0432\u043f") || !s.contains("0") && !s.contains("1")));
    }

    public static String getWorldType() {
        return Network.mc.field_1687.method_27983().method_29177().method_12832();
    }

    public static boolean isCopyTime() {
        return server.equals("CopyTime") || server.equals("SpookyTime") || server.equals("FunTime");
    }

    public static boolean isFunTime() {
        return server.equals("FunTime");
    }

    public static boolean isReallyWorld() {
        return server.equals("ReallyWorld");
    }

    public static boolean isGulPvP() {
        return server.equals("GulPvP");
    }

    public static boolean isHolyWorld() {
        return server.equals("HolyWorld");
    }

    public static boolean isSpookyTime() {
        return server.equals("SpookyTime");
    }

    public static boolean isAresMine() {
        return server.equals("aresmine");
    }

    public static boolean isVanilla() {
        return server.equals("Vanilla");
    }

    private Network() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static int getAnarchy() {
        return anarchy;
    }

    public static boolean isPvpEnd() {
        return pvpEnd;
    }
}
