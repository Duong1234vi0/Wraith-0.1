/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.class_124
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2561
 */
package code.alphadlc.utils.interactions.path;

import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_124;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2561;

public final class BaritoneHelper
implements QuickImports {
    private static final String BARITONE_MOD_ID = "baritone-meteor";
    private static final String DEFAULT_PREFIX = "#";
    private static final String[] SILENT_RENDER_SETTINGS = new String[]{"renderPath", "renderPathAsLine", "renderGoal", "renderGoalAnimated", "renderGoalXZBeacon", "renderGoalIgnoreDepth", "renderPathIgnoreDepth", "renderSelectionBoxes", "renderSelectionBoxesIgnoreDepth", "renderSelection", "renderSelectionIgnoreDepth", "renderSelectionCorners", "renderCachedChunks", "fadePath", "elytraRenderRaytraces", "elytraRenderHitboxRaytraces", "elytraRenderSimulation"};
    private static final String[] SILENT_BEHAVIOR_SETTINGS = new String[]{"allowBreak", "allowPlace", "autoTool", "walkWhileBreaking", "rightClickContainerOnArrival", "legitMine", "forceInternalMining"};
    private static final String[] SILENT_PROCESS_GETTERS = new String[]{"getMineProcess", "getBuilderProcess", "getExploreProcess", "getFarmProcess", "getGetToBlockProcess", "getElytraProcess", "getFollowProcess"};
    private static final Map<String, Object> silentModeBackup = new HashMap<String, Object>();
    private static boolean suppressChat;
    private static boolean silentModeActive;
    private static final Map<String, Object> mlgSettingsBackup;
    private static boolean mlgModeActive;

    public static boolean isBaritoneLoaded() {
        return FabricLoader.getInstance().isModLoaded(BARITONE_MOD_ID);
    }

    public static boolean isBaritoneMessage(String message) {
        if (message == null || message.isBlank()) {
            return false;
        }
        String lower = message.toLowerCase();
        return lower.contains("[baritone]") || lower.contains("baritone:");
    }

    public static void setSilentMode(boolean enabled) {
        if (enabled) {
            if (!silentModeActive) {
                silentModeActive = true;
                silentModeBackup.clear();
            }
            BaritoneHelper.applySilentMode();
            return;
        }
        if (!silentModeActive) {
            return;
        }
        BaritoneHelper.restoreSilentMode();
        silentModeActive = false;
    }

    public static void gotoChestBlock(class_2338 pos) {
        Object baritone = BaritoneHelper.getBaritone();
        if (baritone == null) {
            return;
        }
        try {
            BaritoneHelper.cancelAllProcesses(baritone);
            Object goal = Class.forName("baritone.api.pathing.goals.GoalGetToBlock").getConstructor(class_2338.class).newInstance(pos);
            Object customGoal = baritone.getClass().getMethod("getCustomGoalProcess", new Class[0]).invoke(baritone, new Object[0]);
            Class<?> goalClass = Class.forName("baritone.api.pathing.goals.Goal");
            customGoal.getClass().getMethod("setGoalAndPath", goalClass).invoke(customGoal, goal);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static void gotoPos(class_2338 pos) {
        Object baritone = BaritoneHelper.getBaritone();
        if (baritone == null) {
            return;
        }
        try {
            Object goal = Class.forName("baritone.api.pathing.goals.GoalBlock").getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(pos.method_10263(), pos.method_10264(), pos.method_10260());
            Object customGoal = baritone.getClass().getMethod("getCustomGoalProcess", new Class[0]).invoke(baritone, new Object[0]);
            Class<?> goalClass = Class.forName("baritone.api.pathing.goals.Goal");
            customGoal.getClass().getMethod("setGoalAndPath", goalClass).invoke(customGoal, goal);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static void stop() {
        Object baritone = BaritoneHelper.getBaritone();
        if (baritone == null) {
            return;
        }
        try {
            Object pathing = baritone.getClass().getMethod("getPathingBehavior", new Class[0]).invoke(baritone, new Object[0]);
            pathing.getClass().getMethod("forceCancel", new Class[0]).invoke(pathing, new Object[0]);
            BaritoneHelper.cancelAllProcesses(baritone);
            Object input = baritone.getClass().getMethod("getInputOverrideHandler", new Class[0]).invoke(baritone, new Object[0]);
            input.getClass().getMethod("clearAllKeys", new Class[0]).invoke(input, new Object[0]);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static boolean isPathing() {
        Object baritone = BaritoneHelper.getBaritone();
        if (baritone == null) {
            return false;
        }
        try {
            Object pathing = baritone.getClass().getMethod("getPathingBehavior", new Class[0]).invoke(baritone, new Object[0]);
            return (Boolean)pathing.getClass().getMethod("isPathing", new Class[0]).invoke(pathing, new Object[0]);
        }
        catch (Throwable ignored) {
            return false;
        }
    }

    public static boolean handleChatCommand(String message) {
        if (message == null || message.isBlank()) {
            return false;
        }
        String prefix = BaritoneHelper.getCommandPrefix();
        if (!BaritoneHelper.looksLikeBaritoneCommand(message, prefix)) {
            return false;
        }
        if (!BaritoneHelper.isBaritoneLoaded()) {
            BaritoneHelper.notifyMissingBaritone();
            return true;
        }
        Object baritone = BaritoneHelper.getBaritone();
        if (baritone == null) {
            BaritoneHelper.notifyMissingBaritone();
            return true;
        }
        try {
            Class<?> chatEventClass = Class.forName("baritone.api.event.events.ChatEvent");
            Object chatEvent = chatEventClass.getConstructor(String.class).newInstance(message);
            Object eventBus = baritone.getClass().getMethod("getGameEventHandler", new Class[0]).invoke(baritone, new Object[0]);
            eventBus.getClass().getMethod("onSendChatMessage", chatEventClass).invoke(eventBus, chatEvent);
            if (((Boolean)chatEventClass.getMethod("isCancelled", new Class[0]).invoke(chatEvent, new Object[0])).booleanValue()) {
                return true;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        BaritoneHelper.executePrefixedCommand(message, prefix);
        return true;
    }

    private static boolean looksLikeBaritoneCommand(String message, String prefix) {
        if (message.startsWith(prefix)) {
            return true;
        }
        try {
            String forcePrefix = (String)Class.forName("baritone.api.command.IBaritoneChatControl").getField("FORCE_COMMAND_PREFIX").get(null);
            return message.startsWith(forcePrefix);
        }
        catch (Throwable ignored) {
            return message.startsWith(DEFAULT_PREFIX);
        }
    }

    private static String getCommandPrefix() {
        if (!BaritoneHelper.isBaritoneLoaded()) {
            return DEFAULT_PREFIX;
        }
        try {
            String prefix;
            Object settings = Class.forName("baritone.api.BaritoneAPI").getMethod("getSettings", new Class[0]).invoke(null, new Object[0]);
            Object prefixSetting = settings.getClass().getField("prefix").get(settings);
            Object value = prefixSetting.getClass().getField("value").get(prefixSetting);
            if (value instanceof String && !(prefix = (String)value).isEmpty()) {
                return prefix;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return DEFAULT_PREFIX;
    }

    private static boolean executePrefixedCommand(String message, String prefix) {
        if (!message.startsWith(prefix)) {
            return false;
        }
        Object baritone = BaritoneHelper.getBaritone();
        if (baritone == null) {
            return false;
        }
        BaritoneHelper.executeBaritoneCommand(baritone, message.substring(prefix.length()));
        return true;
    }

    private static boolean executeBaritoneCommand(Object baritone, String command) {
        try {
            Object commandManager = baritone.getClass().getMethod("getCommandManager", new Class[0]).invoke(baritone, new Object[0]);
            return (Boolean)commandManager.getClass().getMethod("execute", String.class).invoke(commandManager, command);
        }
        catch (Throwable ignored) {
            return false;
        }
    }

    private static void notifyMissingBaritone() {
        if (BaritoneHelper.mc.field_1724 == null) {
            return;
        }
        BaritoneHelper.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)(String.valueOf(class_124.field_1061) + "Baritone \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d. \u0423\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u0435 baritone-meteor \u0434\u043b\u044f 1.21.4 \u0432 \u043f\u0430\u043f\u043a\u0443 mods.")), false);
    }

    private static void applySilentMode() {
        if (!BaritoneHelper.isBaritoneLoaded()) {
            return;
        }
        Object settings = BaritoneHelper.getBaritoneSettings();
        if (settings == null) {
            return;
        }
        for (String name : SILENT_RENDER_SETTINGS) {
            BaritoneHelper.saveAndSetBooleanSetting(settings, name, false);
        }
        for (String name : SILENT_BEHAVIOR_SETTINGS) {
            BaritoneHelper.saveAndSetBooleanSetting(settings, name, false);
        }
        BaritoneHelper.saveAndSetBooleanSetting(settings, "freeLook", true);
        BaritoneHelper.saveAndSetBooleanSetting(settings, "blockFreeLook", true);
        BaritoneHelper.saveAndSetBooleanSetting(settings, "elytraFreeLook", true);
        BaritoneHelper.saveAndSetBooleanSetting(settings, "smoothLook", false);
        BaritoneHelper.saveAndSetBooleanSetting(settings, "elytraSmoothLook", false);
        BaritoneHelper.saveAndSetBooleanSetting(settings, "remainWithExistingLookDirection", true);
        BaritoneHelper.saveAndSetDoubleSetting(settings, "randomLooking", 0.0);
        BaritoneHelper.saveAndSetDoubleSetting(settings, "randomLooking113", 0.0);
        BaritoneHelper.protectChestBlocksFromBreaking(settings);
    }

    private static void protectChestBlocksFromBreaking(Object settings) {
        Object setting = BaritoneHelper.getSetting(settings, "blocksToDisallowBreaking");
        if (setting == null) {
            return;
        }
        Object current = BaritoneHelper.readSettingValue(setting);
        if (!(current instanceof List)) {
            return;
        }
        List currentList = (List)current;
        silentModeBackup.putIfAbsent("blocksToDisallowBreaking", new ArrayList(currentList));
        List blocks = (List)current;
        if (!blocks.contains(class_2246.field_10034)) {
            blocks.add(class_2246.field_10034);
        }
        if (!blocks.contains(class_2246.field_10380)) {
            blocks.add(class_2246.field_10380);
        }
    }

    private static void cancelAllProcesses(Object baritone) {
        try {
            Object customGoal = baritone.getClass().getMethod("getCustomGoalProcess", new Class[0]).invoke(baritone, new Object[0]);
            customGoal.getClass().getMethod("onLostControl", new Class[0]).invoke(customGoal, new Object[0]);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        for (String getter : SILENT_PROCESS_GETTERS) {
            try {
                Object process = baritone.getClass().getMethod(getter, new Class[0]).invoke(baritone, new Object[0]);
                process.getClass().getMethod("onLostControl", new Class[0]).invoke(process, new Object[0]);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
    }

    private static void restoreSilentMode() {
        if (silentModeBackup.isEmpty()) {
            return;
        }
        Object settings = BaritoneHelper.getBaritoneSettings();
        if (settings == null) {
            silentModeBackup.clear();
            return;
        }
        for (Map.Entry<String, Object> entry : silentModeBackup.entrySet()) {
            BaritoneHelper.restoreSetting(settings, entry.getKey(), entry.getValue());
        }
        silentModeBackup.clear();
    }

    private static void restoreListSetting(Object settings, String name, Object backup) {
        Object setting = BaritoneHelper.getSetting(settings, name);
        if (setting == null || !(backup instanceof List)) {
            return;
        }
        List backupList = (List)backup;
        Object current = BaritoneHelper.readSettingValue(setting);
        if (!(current instanceof List)) {
            return;
        }
        List currentList = (List)current;
        List target = (List)current;
        target.clear();
        target.addAll(backupList);
    }

    private static Object getBaritoneSettings() {
        if (!BaritoneHelper.isBaritoneLoaded()) {
            return null;
        }
        try {
            return Class.forName("baritone.api.BaritoneAPI").getMethod("getSettings", new Class[0]).invoke(null, new Object[0]);
        }
        catch (Throwable ignored) {
            return null;
        }
    }

    private static void saveAndSetBooleanSetting(Object settings, String name, boolean value) {
        Object setting = BaritoneHelper.getSetting(settings, name);
        if (setting == null) {
            return;
        }
        silentModeBackup.putIfAbsent(name, BaritoneHelper.readSettingValue(setting));
        BaritoneHelper.writeSettingValue(setting, value);
    }

    private static void saveAndSetDoubleSetting(Object settings, String name, double value) {
        Object setting = BaritoneHelper.getSetting(settings, name);
        if (setting == null) {
            return;
        }
        silentModeBackup.putIfAbsent(name, BaritoneHelper.readSettingValue(setting));
        BaritoneHelper.writeSettingValue(setting, value);
    }

    private static void restoreSetting(Object settings, String name, Object value) {
        if ("blocksToDisallowBreaking".equals(name)) {
            BaritoneHelper.restoreListSetting(settings, name, value);
            return;
        }
        Object setting = BaritoneHelper.getSetting(settings, name);
        if (setting == null || value == null) {
            return;
        }
        BaritoneHelper.writeSettingValue(setting, value);
    }

    private static Object getSetting(Object settings, String name) {
        try {
            return settings.getClass().getField(name).get(settings);
        }
        catch (Throwable ignored) {
            return null;
        }
    }

    private static Object readSettingValue(Object setting) {
        try {
            return setting.getClass().getField("value").get(setting);
        }
        catch (Throwable ignored) {
            return null;
        }
    }

    private static void writeSettingValue(Object setting, Object value) {
        try {
            setting.getClass().getField("value").set(setting, value);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static void startMlgToBlock(class_2338 groundBlock) {
        if (!BaritoneHelper.isBaritoneLoaded() || groundBlock == null) {
            return;
        }
        if (!mlgModeActive) {
            mlgModeActive = true;
            mlgSettingsBackup.clear();
        }
        BaritoneHelper.applyMlgSettings();
        BaritoneHelper.gotoPos(groundBlock);
    }

    public static void stopMlg() {
        if (!mlgModeActive) {
            return;
        }
        BaritoneHelper.stop();
        BaritoneHelper.restoreMlgSettings();
        mlgModeActive = false;
    }

    public static boolean isPathingActive() {
        return BaritoneHelper.isPathing();
    }

    private static void applyMlgSettings() {
        Object settings = BaritoneHelper.getBaritoneSettings();
        if (settings == null) {
            return;
        }
        BaritoneHelper.saveAndSetBooleanSettingFor(settings, mlgSettingsBackup, "allowWaterBucketFall", true);
        BaritoneHelper.saveAndSetBooleanSettingFor(settings, mlgSettingsBackup, "allowInventory", true);
        BaritoneHelper.saveAndSetBooleanSettingFor(settings, mlgSettingsBackup, "freeLook", false);
        BaritoneHelper.saveAndSetBooleanSettingFor(settings, mlgSettingsBackup, "smoothLook", true);
    }

    private static void restoreMlgSettings() {
        if (mlgSettingsBackup.isEmpty()) {
            return;
        }
        Object settings = BaritoneHelper.getBaritoneSettings();
        if (settings == null) {
            mlgSettingsBackup.clear();
            return;
        }
        for (Map.Entry<String, Object> entry : mlgSettingsBackup.entrySet()) {
            BaritoneHelper.restoreSetting(settings, entry.getKey(), entry.getValue());
        }
        mlgSettingsBackup.clear();
    }

    private static void saveAndSetBooleanSettingFor(Object settings, Map<String, Object> backup, String name, boolean value) {
        Object setting = BaritoneHelper.getSetting(settings, name);
        if (setting == null) {
            return;
        }
        backup.putIfAbsent(name, BaritoneHelper.readSettingValue(setting));
        BaritoneHelper.writeSettingValue(setting, value);
    }

    private static Object getBaritone() {
        if (!BaritoneHelper.isBaritoneLoaded() || mc == null) {
            return null;
        }
        try {
            Object forPlayer;
            Class<?> apiClass = Class.forName("baritone.api.BaritoneAPI");
            Object provider = apiClass.getMethod("getProvider", new Class[0]).invoke(null, new Object[0]);
            if (BaritoneHelper.mc.field_1724 != null && (forPlayer = provider.getClass().getMethod("getBaritoneForPlayer", BaritoneHelper.mc.field_1724.getClass()).invoke(provider, BaritoneHelper.mc.field_1724)) != null) {
                return forPlayer;
            }
            Object forClient = provider.getClass().getMethod("getBaritoneForMinecraft", mc.getClass()).invoke(provider, mc);
            if (forClient != null) {
                return forClient;
            }
            return provider.getClass().getMethod("createBaritone", mc.getClass()).invoke(provider, mc);
        }
        catch (Throwable ignored) {
            return null;
        }
    }

    private BaritoneHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static boolean isSuppressChat() {
        return suppressChat;
    }

    public static void setSuppressChat(boolean suppressChat) {
        BaritoneHelper.suppressChat = suppressChat;
    }

    public static boolean isSilentModeActive() {
        return silentModeActive;
    }

    public static boolean isMlgModeActive() {
        return mlgModeActive;
    }

    static {
        mlgSettingsBackup = new HashMap<String, Object>();
    }
}
