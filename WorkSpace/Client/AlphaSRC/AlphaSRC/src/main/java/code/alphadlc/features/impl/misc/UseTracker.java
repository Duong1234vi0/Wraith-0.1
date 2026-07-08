/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1291
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1839
 *  net.minecraft.class_1844
 *  net.minecraft.class_1890
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2663
 *  net.minecraft.class_2783
 *  net.minecraft.class_6880
 *  net.minecraft.class_9334
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.display.hud.Notifications;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.math.time.StopWatch;
import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1839;
import net.minecraft.class_1844;
import net.minecraft.class_1890;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import net.minecraft.class_2783;
import net.minecraft.class_6880;
import net.minecraft.class_9334;

public class UseTracker
extends Module {
    private final MultiSelectSetting notifyMode = new MultiSelectSetting("\u0422\u0440\u0435\u043a\u0430\u0442\u044c", "\u041a\u0430\u043a\u0438\u0435 \u0443\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c").value("\u0421\u043d\u043e\u0441 \u0442\u043e\u0442\u0435\u043c\u0430", "\u041f\u043e\u043b\u0443\u0447\u0435\u043d\u043d\u044b\u0435 \u0437\u0435\u043b\u044c\u044f", "\u0421\u044a\u0435\u0434\u0435\u043d\u043d\u044b\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442").selected("\u0421\u043d\u043e\u0441 \u0442\u043e\u0442\u0435\u043c\u0430", "\u041f\u043e\u043b\u0443\u0447\u0435\u043d\u043d\u044b\u0435 \u0437\u0435\u043b\u044c\u044f", "\u0421\u044a\u0435\u0434\u0435\u043d\u043d\u044b\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442");
    private final Map<UUID, Map<String, class_1293>> playerEffects = new HashMap<UUID, Map<String, class_1293>>();
    private static final Map<UUID, Boolean> enchantedTotems = new HashMap<UUID, Boolean>();
    private final Map<UUID, class_1799> activeUseItem = new HashMap<UUID, class_1799>();
    private final Map<UUID, Integer> useStartTick = new HashMap<UUID, Integer>();
    private final StopWatch updateTimer = new StopWatch();
    private static final long UPDATE_INTERVAL_MS = 500L;

    public UseTracker() {
        super("UseTracker", ModuleCategory.MISC);
        this.setup(this.notifyMode);
        this.updateTimer.reset();
    }

    private boolean allowTotem() {
        return this.notifyMode.isSelected("\u0421\u043d\u043e\u0441 \u0442\u043e\u0442\u0435\u043c\u0430");
    }

    private boolean allowPotions() {
        return this.notifyMode.isSelected("\u041f\u043e\u043b\u0443\u0447\u0435\u043d\u043d\u044b\u0435 \u0437\u0435\u043b\u044c\u044f");
    }

    private boolean allowConsume() {
        return this.notifyMode.isSelected("\u0421\u044a\u0435\u0434\u0435\u043d\u043d\u044b\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442");
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (UseTracker.mc.field_1724 == null || UseTracker.mc.field_1687 == null) {
            return;
        }
        if (this.allowConsume()) {
            for (class_1657 player : UseTracker.mc.field_1687.method_18456()) {
                int minDuration;
                int useDuration;
                String verb;
                if (player == null || UseTracker.mc.field_1724 != null && player.method_5667().equals(UseTracker.mc.field_1724.method_5667())) continue;
                UUID id = player.method_5667();
                boolean using = player.method_6115();
                if (using) {
                    if (this.activeUseItem.containsKey(id)) continue;
                    this.activeUseItem.put(id, player.method_6030().method_7972());
                    this.useStartTick.put(id, player.field_6012);
                    continue;
                }
                class_1799 used = this.activeUseItem.remove(id);
                Integer startTick = this.useStartTick.remove(id);
                if (used == null || used.method_7960() || startTick == null) continue;
                class_1839 action = used.method_7976();
                if ((verb = (switch (action) {
                    case class_1839.field_8946 -> "\u0432\u044b\u043f\u0438\u043b";
                    case class_1839.field_8950 -> "\u0441\u044a\u0435\u043b";
                    default -> null;
                })) == null || (useDuration = player.field_6012 - startTick) < (minDuration = 31)) continue;
                String itemName = used.method_7964().getString().replaceAll("\u00a7.", "");
                String effectsStr = this.getPotionEffectsStringWithDuration(used);
                String playerName = player.method_5477().getString();
                Object effectsPart = effectsStr.isEmpty() ? "" : " \u00a78(\u00a77" + effectsStr + "\u00a78)";
                String chatMsg = "\u00a7r" + playerName + "\u00a77 " + verb + " \u00a7r" + itemName + ((String)effectsPart).replace("\u00a78", "\u00a77") + "\u00a7r";
                ChatMessage.brandmessage(chatMsg);
                if (!this.shouldSendHudNotification("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0438 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430")) continue;
                String notificationMessage = effectsStr.isEmpty() ? playerName + " " + verb + " " + itemName : playerName + " " + verb + " " + itemName + " (" + effectsStr.replaceAll("\u00a7.", "") + ")";
                Notifications.getInstance().addList((class_2561)class_2561.method_43470((String)notificationMessage), 3500, (Object)SoundManager.SETTING_INTERACT, false, used.method_46651(1));
            }
        }
        if (!this.updateTimer.finished(500.0)) {
            return;
        }
        this.updateTimer.reset();
        for (class_1657 player : UseTracker.mc.field_1687.method_18456()) {
            if (player == null || UseTracker.mc.field_1724 != null && player.method_5667().equals(UseTracker.mc.field_1724.method_5667())) continue;
            UUID playerId = player.method_5667();
            ArrayList currentEffectsCopy = new ArrayList(player.method_6026());
            HashMap<CallSite, class_1293> currentEffectsMap = new HashMap<CallSite, class_1293>();
            for (class_1293 e : currentEffectsCopy) {
                String key = ((class_1291)e.method_5579().comp_349()).method_5567() + ":" + e.method_5578();
                currentEffectsMap.put((CallSite)((Object)key), e);
            }
            this.playerEffects.put(playerId, currentEffectsMap);
        }
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        class_1657 player;
        class_1297 entity;
        class_2663 statusPacket;
        if (UseTracker.mc.field_1687 == null) {
            return;
        }
        class_2596<?> class_25962 = e.getPacket();
        if (class_25962 instanceof class_2663 && (statusPacket = (class_2663)class_25962).method_11470() == 35) {
            entity = statusPacket.method_11469((class_1937)UseTracker.mc.field_1687);
            if (!(entity instanceof class_1657)) {
                return;
            }
            player = (class_1657)entity;
            if (UseTracker.mc.field_1724 != null && player.method_5667().equals(UseTracker.mc.field_1724.method_5667())) {
                return;
            }
            boolean isEnchanted = class_1890.method_58117((class_1799)player.method_6079()) || class_1890.method_58117((class_1799)player.method_6047());
            enchantedTotems.put(player.method_5667(), isEnchanted);
            if (this.allowTotem()) {
                String message = "\u00a7r" + player.method_5477().getString() + "\u00a77 \u043f\u043e\u0442\u0435\u0440\u044f\u043b \u0442\u043e\u0442\u0435\u043c \u0431\u0435\u0441\u0441\u043c\u0435\u0440\u0442\u0438\u044f, \u0437\u0430\u0447\u0430\u0440\u043e\u0432\u0430\u043d: " + (isEnchanted ? "\u00a7a" : "\u00a7c") + "\u2b24\u00a7r";
                ChatMessage.brandmessage(message);
                if (this.shouldSendHudNotification("\u0421\u043d\u043e\u0441\u0435 \u0442\u043e\u0442\u0435\u043c\u0430")) {
                    Notifications.getInstance().addList((class_2561)class_2561.method_43470((String)(player.method_5477().getString() + " \u043f\u043e\u0442\u0435\u0440\u044f\u043b \u0442\u043e\u0442\u0435\u043c")), 3500, (Object)SoundManager.DISABLE_MODULE, false, class_1802.field_8288.method_7854());
                }
            }
        }
        if ((entity = e.getPacket()) instanceof class_2783) {
            class_2783 effectPacket = (class_2783)entity;
            if (this.allowPotions()) {
                entity = UseTracker.mc.field_1687.method_8469(effectPacket.method_11943());
                if (!(entity instanceof class_1657)) {
                    return;
                }
                player = (class_1657)entity;
                if (UseTracker.mc.field_1724 != null && player.method_5667().equals(UseTracker.mc.field_1724.method_5667())) {
                    return;
                }
                UUID playerId = player.method_5667();
                Map currentEffectsMap = this.playerEffects.getOrDefault(playerId, new HashMap());
                ArrayList currentEffectsCopy = new ArrayList(player.method_6026());
                HashMap<String, class_1293> updatedEffectsMap = new HashMap<String, class_1293>();
                for (class_1293 effect : currentEffectsCopy) {
                    String key = ((class_1291)effect.method_5579().comp_349()).method_5567() + ":" + effect.method_5578();
                    updatedEffectsMap.put(key, effect);
                }
                String newEffectKey = ((class_1291)effectPacket.method_11946().comp_349()).method_5567() + ":" + effectPacket.method_11945();
                class_1293 newEffect = new class_1293(effectPacket.method_11946(), effectPacket.method_11944(), effectPacket.method_11945(), effectPacket.method_11950(), effectPacket.method_11949(), effectPacket.method_11942());
                updatedEffectsMap.put(newEffectKey, newEffect);
                ArrayList<SpecialPotionType> detectedPotions = new ArrayList<SpecialPotionType>();
                HashSet<CallSite> newEffectKeys = new HashSet<CallSite>(Set.of(newEffectKey));
                this.create(updatedEffectsMap, newEffectKeys, detectedPotions, SpecialPotionType.KILLER, "effect.minecraft.strength:3", "effect.minecraft.resistance:0");
                this.create(updatedEffectsMap, newEffectKeys, detectedPotions, SpecialPotionType.URINE, "effect.minecraft.jump_boost:0", "effect.minecraft.speed:2");
                this.create(updatedEffectsMap, newEffectKeys, detectedPotions, SpecialPotionType.MEDIC, "effect.minecraft.health_boost:2", "effect.minecraft.regeneration:2");
                this.create(updatedEffectsMap, newEffectKeys, detectedPotions, SpecialPotionType.BURP, "effect.minecraft.blindness:0", "effect.minecraft.glowing:0", "effect.minecraft.hunger:9", "effect.minecraft.slowness:2", "effect.minecraft.wither:4");
                this.create(updatedEffectsMap, newEffectKeys, detectedPotions, SpecialPotionType.FLASH, "effect.minecraft.blindness:0", "effect.minecraft.glowing:0");
                this.create(updatedEffectsMap, newEffectKeys, detectedPotions, SpecialPotionType.SULFURIC_ACID, "effect.minecraft.poison:1", "effect.minecraft.slowness:3", "effect.minecraft.weakness:2", "effect.minecraft.wither:4");
                this.create(updatedEffectsMap, newEffectKeys, detectedPotions, SpecialPotionType.WINNER, "effect.minecraft.health_boost:1", "effect.minecraft.invisibility:0", "effect.minecraft.regeneration:1", "effect.minecraft.resistance:0");
                if (!detectedPotions.isEmpty()) {
                    for (SpecialPotionType type : detectedPotions) {
                        class_1799 potionStack = new class_1799((class_1935)class_1802.field_8574);
                        potionStack.method_57379(class_9334.field_49631, (Object)class_2561.method_43470((String)type.getDisplayName()));
                        this.displayPotionNotificationFromEffect(player, potionStack, type.getEffects().stream().map(d -> new class_1293(d.getEffect(), d.getDurationTicks().intValue(), d.getAmplifier().intValue(), false, false, true)).collect(Collectors.toList()));
                    }
                } else {
                    String localizedName = class_2561.method_43471((String)((class_1291)effectPacket.method_11946().comp_349()).method_5567()).getString().replaceAll("\u00a7.", "");
                    String duration = this.getPotionDurationString(newEffect, 1);
                    int amplifier = effectPacket.method_11945();
                    int level = Math.max(0, amplifier) + 1;
                    ChatMessage.brandmessage("\u00a7r" + player.method_5477().getString() + "\u00a77 \u043f\u043e\u043b\u0443\u0447\u0438\u043b \u00a7f" + localizedName + " " + level + "\u00a7r \u043d\u0430 \u00a77" + duration + "\u00a7r");
                    if (this.shouldSendHudNotification("\u041f\u043e\u043b\u0443\u0447\u0435\u043d\u043d\u043e\u043c \u0437\u0435\u043b\u044c\u0435")) {
                        class_1799 potionStack = new class_1799((class_1935)class_1802.field_8574);
                        potionStack.method_57379(class_9334.field_49631, (Object)class_2561.method_43470((String)(localizedName + " " + level)));
                        Notifications.getInstance().addList((class_2561)class_2561.method_43470((String)(player.method_5477().getString() + " \u043f\u043e\u043b\u0443\u0447\u0438\u043b " + localizedName + " " + level + " (" + duration + ")")), 3500, (Object)SoundManager.NOTIF, false, potionStack);
                    }
                }
                this.playerEffects.put(playerId, updatedEffectsMap);
            }
        }
    }

    private boolean create(Map<String, class_1293> currentEffectsMap, Set<String> newOrRefreshedKeys, List<SpecialPotionType> detectedPotions, SpecialPotionType type, String ... comboKeys) {
        HashSet<String> comboSet = new HashSet<String>(Arrays.asList(comboKeys));
        boolean allPresent = comboSet.stream().allMatch(currentEffectsMap::containsKey);
        boolean atLeastOneNew = comboSet.stream().anyMatch(newOrRefreshedKeys::contains);
        if (allPresent && atLeastOneNew) {
            detectedPotions.add(type);
            newOrRefreshedKeys.removeAll(comboSet);
            return true;
        }
        return false;
    }

    private void displayPotionNotificationFromEffect(class_1657 player, class_1799 itemStack, List<class_1293> appliedEffects) {
        String cleanName = itemStack.method_7964().getString().replaceAll("\u00a7.", "");
        ChatMessage.brandmessage("\u00a7r" + player.method_5477().getString() + "\u00a77 \u043f\u043e\u043b\u0443\u0447\u0438\u043b \u00a7r" + cleanName);
        if (this.shouldSendHudNotification("\u041f\u043e\u043b\u0443\u0447\u0435\u043d\u043d\u043e\u043c \u0437\u0435\u043b\u044c\u0435")) {
            Notifications.getInstance().addList((class_2561)class_2561.method_43470((String)(player.method_5477().getString() + " \u043f\u043e\u043b\u0443\u0447\u0438\u043b " + cleanName)), 3500, (Object)SoundManager.NOTIF, false, itemStack.method_7972());
        }
    }

    private boolean shouldSendHudNotification(String settingName) {
        Hud hud = Hud.getInstance();
        if (hud == null || hud.notificationSettings == null) {
            return false;
        }
        String normalized = settingName;
        if (normalized.contains("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0438")) {
            normalized = "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0438 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430";
        }
        if (normalized.contains("\u0421\u043d\u043e\u0441\u0435")) {
            normalized = "\u0421\u043d\u043e\u0441\u0435 \u0442\u043e\u0442\u0435\u043c\u0430";
        }
        if (normalized.contains("\u041f\u043e\u043b\u0443\u0447")) {
            normalized = "\u041f\u043e\u043b\u0443\u0447\u0435\u043d\u043d\u043e\u043c \u0437\u0435\u043b\u044c\u0435";
        }
        return hud.notificationSettings.isSelected(normalized);
    }

    private String getPotionDurationString(class_1293 effect, int multiplier) {
        if (effect.method_48559()) {
            return "\u221e";
        }
        int duration = effect.method_5584() / multiplier;
        int seconds = duration / 20;
        int minutes = seconds / 60;
        return minutes > 0 ? minutes + "m " + seconds + "s" : (seconds %= 60) + "s";
    }

    private String getPotionEffectsString(class_1799 stack) {
        class_1844 component = (class_1844)stack.method_57824(class_9334.field_49651);
        if (component == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (class_1293 eff : component.method_57397()) {
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
            String name = class_2561.method_43471((String)((class_1291)eff.method_5579().comp_349()).method_5567()).getString();
            int level = eff.method_5578() + 1;
            sb.append(name).append(" ").append(level);
        }
        return sb.toString();
    }

    private String getPotionEffectsStringWithDuration(class_1799 stack) {
        class_1844 component = (class_1844)stack.method_57824(class_9334.field_49651);
        if (component == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (class_1293 eff : component.method_57397()) {
            if (!sb.isEmpty()) {
                sb.append("\u00a78, \u00a77");
            }
            String name = class_2561.method_43471((String)((class_1291)eff.method_5579().comp_349()).method_5567()).getString().replaceAll("\u00a7.", "");
            int amplifier = eff.method_5578();
            int level = Math.max(0, amplifier) + 1;
            String duration = this.getPotionDurationString(eff, 1);
            sb.append(name).append(" ").append(level).append("\u00a7r \u043d\u0430 \u00a77").append(duration);
        }
        return sb.toString();
    }

    public static enum SpecialPotionType {
        FLASH(0xFFFFFF, "\u00a76[\u2605] \u00a7e\u0412\u0441\u043f\u044b\u0448\u043a\u0430", Arrays.asList(new PotionEffectData((class_6880<class_1291>)class_1294.field_5919, 20, 0), new PotionEffectData((class_6880<class_1291>)class_1294.field_5912, 240, 0)), Arrays.asList(0xFCFFF6, 0xFFFFF5, 0xFFFFFC, 0xFFF7FF, 0xF8FFF5, 0xFFFEFF, 0xF7FFF8, 0xFBFCFF, 0xFFF9FF)),
        KILLER(0xCC0000, "\u00a74[\u2605] \u00a7c\u0417\u0435\u043b\u044c\u0435 \u041a\u0438\u043b\u043b\u0435\u0440\u0430", Arrays.asList(new PotionEffectData((class_6880<class_1291>)class_1294.field_5907, 180, 0), new PotionEffectData((class_6880<class_1291>)class_1294.field_5910, 90, 3)), Arrays.asList(0xC00000, 0xE50000, 0xD60000)),
        BURP(0xFF6600, "\u00a7c[\u2605] \u00a76\u0417\u0435\u043b\u044c\u0435 \u041e\u0442\u0440\u044b\u0436\u043a\u0438", Arrays.asList(new PotionEffectData((class_6880<class_1291>)class_1294.field_5919, 10, 0), new PotionEffectData((class_6880<class_1291>)class_1294.field_5912, 180, 0), new PotionEffectData((class_6880<class_1291>)class_1294.field_5903, 90, 9), new PotionEffectData((class_6880<class_1291>)class_1294.field_5909, 180, 2), new PotionEffectData((class_6880<class_1291>)class_1294.field_5920, 30, 4)), Arrays.asList(16727040, 16733184, 16739072)),
        SULFURIC_ACID(0x99FF33, "\u00a72[\u2605] \u00a7a\u0421\u0435\u0440\u043d\u0430\u044f \u043a\u0438\u0441\u043b\u043e\u0442\u0430", Arrays.asList(new PotionEffectData((class_6880<class_1291>)class_1294.field_5899, 50, 1), new PotionEffectData((class_6880<class_1291>)class_1294.field_5909, 90, 3), new PotionEffectData((class_6880<class_1291>)class_1294.field_5911, 90, 2), new PotionEffectData((class_6880<class_1291>)class_1294.field_5920, 30, 4)), Arrays.asList(0x980000, 10223411, 0x98FFFF)),
        MEDIC(0xFF00FF, "\u00a75[\u2605] \u00a7d\u0417\u0435\u043b\u044c\u0435 \u041c\u0435\u0434\u0438\u043a\u0430", Arrays.asList(new PotionEffectData((class_6880<class_1291>)class_1294.field_5914, 45, 2), new PotionEffectData((class_6880<class_1291>)class_1294.field_5924, 45, 2)), Arrays.asList(0xFF11FF, 0xFEEFFF, 0xDFFFFF)),
        WINNER(65280, "\u00a72[\u2605] \u00a7a\u0417\u0435\u043b\u044c\u0435 \u041f\u043e\u0431\u0435\u0434\u0438\u0442\u0435\u043b\u044f", Arrays.asList(new PotionEffectData((class_6880<class_1291>)class_1294.field_5914, 180, 1), new PotionEffectData((class_6880<class_1291>)class_1294.field_5905, 900, 0), new PotionEffectData((class_6880<class_1291>)class_1294.field_5924, 60, 1), new PotionEffectData((class_6880<class_1291>)class_1294.field_5907, 60, 0)), Arrays.asList(59136, 57088, 63232, 65310, 65350)),
        URINE(65280, "\u00a73[\u2605] \u00a7b\u041c\u043e\u0447\u0430 \u0424\u043b\u0435\u0448\u0430", Arrays.asList(new PotionEffectData((class_6880<class_1291>)class_1294.field_5913, 120, 1), new PotionEffectData((class_6880<class_1291>)class_1294.field_5904, 120, 2)), Arrays.asList(65535));

        private final Integer baseColor;
        private final String displayName;
        private final List<PotionEffectData> effects;
        private final List<Integer> colorVariations;

        public Integer getBaseColor() {
            return this.baseColor;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public List<PotionEffectData> getEffects() {
            return this.effects;
        }

        public List<Integer> getColorVariations() {
            return this.colorVariations;
        }

        private SpecialPotionType(Integer baseColor, String displayName, List<PotionEffectData> effects, List<Integer> colorVariations) {
            this.baseColor = baseColor;
            this.displayName = displayName;
            this.effects = effects;
            this.colorVariations = colorVariations;
        }

        public static class PotionEffectData {
            private final class_6880<class_1291> effect;
            private final Integer durationSeconds;
            private final Integer amplifier;

            public Integer getDurationTicks() {
                return this.durationSeconds * 20;
            }

            public class_6880<class_1291> getEffect() {
                return this.effect;
            }

            public Integer getDurationSeconds() {
                return this.durationSeconds;
            }

            public Integer getAmplifier() {
                return this.amplifier;
            }

            public PotionEffectData(class_6880<class_1291> effect, Integer durationSeconds, Integer amplifier) {
                this.effect = effect;
                this.durationSeconds = durationSeconds;
                this.amplifier = amplifier;
            }
        }
    }
}
