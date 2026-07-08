/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_2596
 *  net.minecraft.class_7439
 */
package code.alphadlc.features.impl.misc.serverhelper;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperHandler;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperItemConfig;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperItemRegistry;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperRenderItem;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.interactions.item.ItemTask;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2596;
import net.minecraft.class_7439;

public class LonyGriefHandler
extends ServerHelperHandler {
    private static final Pattern EQUATION_A = Pattern.compile("([xX\u0445\u0425])\\s*([+\\-*/])\\s*([\\d.,]+)\\s*=\\s*([\\d.,]+)");
    private static final Pattern EQUATION_B = Pattern.compile("([\\d.,]+)\\s*([+\\-*/])\\s*([xX\u0445\u0425])\\s*=\\s*([\\d.,]+)");
    private static final String[] NAMES = new String[]{"\u041e\u0431\u044b\u0447\u043d\u0430\u044f \u043b\u0438\u0432\u0430\u043b\u043a\u0430", "\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u043e\u0435 \u043f\u0435\u0440\u044b\u0448\u043a\u043e", "\u041b\u0438\u0432\u0430\u043b\u043a\u0430 \u0441 \u043f\u043b\u0430\u0442\u0444\u043e\u0440\u043c\u043e\u0439", "\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430"};
    private static final class_1792[] ITEMS = new class_1792[]{class_1802.field_8153, class_1802.field_8153, class_1802.field_8153, class_1802.field_22021};
    private final List<BindSetting> binds;
    private final BooleanSupplier chatSolverEnabled;
    private boolean onlyHotbar;
    private String pendingName;
    private long lastChatSolve;

    public LonyGriefHandler(List<BindSetting> binds, BooleanSupplier chatSolverEnabled) {
        this.binds = binds;
        this.chatSolverEnabled = chatSolverEnabled;
    }

    @Override
    public String getModeId() {
        return "LonyGrief";
    }

    @Override
    public String getOverlayTitle() {
        return "Lony";
    }

    @Override
    public List<BindSetting> getBinds() {
        return this.binds;
    }

    @Override
    public void setOnlyHotbar(boolean onlyHotbar) {
        this.onlyHotbar = onlyHotbar;
    }

    @Override
    public List<ServerHelperRenderItem> buildRenderItems(boolean onlyBar) {
        ArrayList<ServerHelperRenderItem> items = new ArrayList<ServerHelperRenderItem>();
        for (int i = 0; i < NAMES.length && i < this.binds.size(); ++i) {
            int slot;
            ServerHelperItemConfig config = ServerHelperItemRegistry.get(NAMES[i]);
            int n = slot = config == null ? -1 : LonyGriefHandler.findSlotByConfig(config);
            String status = slot == -1 ? "\u2014" : (onlyBar && slot >= 9 ? "\u2014" : LonyGriefHandler.cooldownStatus(ITEMS[i]));
            String shortName = NAMES[i].length() > 8 ? NAMES[i].substring(0, 8) : NAMES[i];
            items.add(new ServerHelperRenderItem(NAMES[i], shortName, status, ITEMS[i]).withBind(this.binds.get(i).getKey()));
        }
        return items;
    }

    @Override
    public boolean onKey(int keyCode) {
        for (int i = 0; i < NAMES.length && i < this.binds.size(); ++i) {
            if (this.binds.get(i).getKey() == -1 || this.binds.get(i).getKey() != keyCode) continue;
            this.queueUse(NAMES[i]);
            return true;
        }
        return false;
    }

    private void queueUse(String itemName) {
        ServerHelperItemConfig config = ServerHelperItemRegistry.get(itemName);
        if (config == null) {
            LonyGriefHandler.notify("\u00a7c" + itemName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u043a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u0438");
            return;
        }
        int slot = LonyGriefHandler.findSlotByConfig(config);
        if (slot == -1) {
            LonyGriefHandler.notifyMissing(itemName);
            return;
        }
        if (this.onlyHotbar && slot >= 9) {
            LonyGriefHandler.notifyHotbarOnly(itemName);
            return;
        }
        if (ItemTask.getCooldownProgress(config.item()) > 0.0f) {
            LonyGriefHandler.notifyCooldown(itemName);
            return;
        }
        this.pendingName = itemName;
    }

    @Override
    public void onPacketReceive(PacketEvent event) {
        boolean looksLikeGame;
        if (event.isSend() || !this.chatSolverEnabled.getAsBoolean() || LonyGriefHandler.mc.field_1724 == null) {
            return;
        }
        class_2596<?> class_25962 = event.getPacket();
        if (!(class_25962 instanceof class_7439)) {
            return;
        }
        class_7439 packet = (class_7439)class_25962;
        if (System.currentTimeMillis() - this.lastChatSolve < 400L) {
            return;
        }
        String line = LonyGriefHandler.sanitizeChat(packet.comp_763().getString());
        if (!line.contains("=")) {
            return;
        }
        String lower = line.toLowerCase();
        boolean bl = looksLikeGame = lower.contains("\u043d\u0430\u0439\u0434\u0438\u0442\u0435") || lower.contains("\u0447\u0430\u0442-\u0438\u0433\u0440\u0430") || lower.contains("\u0440\u0435\u0448\u0438\u0442\u0435 \u0443\u0440\u0430\u0432\u043d\u0435\u043d\u0438\u0435");
        if (!looksLikeGame) {
            return;
        }
        String answer = LonyGriefHandler.solveEquation(line);
        if (answer == null) {
            return;
        }
        this.lastChatSolve = System.currentTimeMillis();
        LonyGriefHandler.mc.field_1724.field_3944.method_45729(answer);
    }

    private static String sanitizeChat(String raw) {
        String s = raw.replaceAll("\u00a7[0-9a-fk-or]", "");
        s = s.replace("\u23b9", " ").replace("\u23a7", " ").replace("\u23a9", " ");
        s = s.replace("\u25c2", " ").replace("\u25b8", " ");
        s = s.replace(',', '.');
        return s.trim();
    }

    private static String solveEquation(String line) {
        Matcher m1 = EQUATION_A.matcher(line);
        if (m1.find()) {
            double x = LonyGriefHandler.solve(m1.group(2), Double.parseDouble(m1.group(3)), Double.parseDouble(m1.group(4)));
            return LonyGriefHandler.formatAnswer(x);
        }
        Matcher m2 = EQUATION_B.matcher(line);
        if (m2.find()) {
            double x = LonyGriefHandler.solveReverse(Double.parseDouble(m2.group(1)), m2.group(2), Double.parseDouble(m2.group(4)));
            return LonyGriefHandler.formatAnswer(x);
        }
        return null;
    }

    private static double solve(String op, double b, double c) {
        return switch (op) {
            case "+" -> c - b;
            case "-" -> c + b;
            case "*" -> c / b;
            case "/" -> c * b;
            default -> Double.NaN;
        };
    }

    private static double solveReverse(double a, String op, double c) {
        return switch (op) {
            case "+" -> c - a;
            case "-" -> a - c;
            case "*" -> c / a;
            case "/" -> a / c;
            default -> Double.NaN;
        };
    }

    private static String formatAnswer(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return null;
        }
        long rounded = Math.round(x);
        if (Math.abs(x - (double)rounded) < 1.0E-4) {
            return Long.toString(rounded);
        }
        return Double.toString(x);
    }

    @Override
    public boolean hasPendingUse() {
        return this.pendingName != null;
    }

    @Override
    public int getPendingSlot() {
        if (this.pendingName == null) {
            return -1;
        }
        ServerHelperItemConfig config = ServerHelperItemRegistry.get(this.pendingName);
        return config == null ? -1 : LonyGriefHandler.findSlotByConfig(config);
    }

    @Override
    public void clearPending() {
        this.pendingName = null;
    }
}
