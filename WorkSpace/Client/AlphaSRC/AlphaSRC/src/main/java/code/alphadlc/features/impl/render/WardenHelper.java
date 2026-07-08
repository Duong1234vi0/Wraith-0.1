/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1531
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2281
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 *  net.minecraft.class_5250
 *  net.minecraft.class_8113$class_8123
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.display.hud.Notifications;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.events.render.WorldLoadEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.projection.Projection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1531;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2281;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_5250;
import net.minecraft.class_8113;

public class WardenHelper
extends Module {
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2})\\s*:\\s*(\\d{2})");
    private static final class_1799 CHEST_ICON = class_1802.field_8106.method_7854();
    private static final class_2960 ARROW_ICON = class_2960.method_60654((String)"textures/arrow.png");
    private final SliderSettings rangeSetting = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043f\u043e\u0438\u0441\u043a\u0430 \u0441\u0443\u043d\u0434\u0443\u043a\u043e\u0432").range(10, 60).setValue(30.0f);
    private final SliderSettings tagSizeSetting = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440 \u0442\u0435\u0433\u0430", "\u0420\u0430\u0437\u043c\u0435\u0440 \u0442\u0435\u043a\u0441\u0442\u0430 \u043d\u0430\u0434 \u0441\u0443\u043d\u0434\u0443\u043a\u043e\u043c").range(10, 20).setValue(13.0f);
    private final BooleanSetting notifySoon = new BooleanSetting("\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u0435 30\u0441", "\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u0443\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u0435, \u043a\u043e\u0433\u0434\u0430 \u0441\u0443\u043d\u0434\u0443\u043a \u0441\u043a\u043e\u0440\u043e \u043e\u0442\u043a\u0440\u043e\u0435\u0442\u0441\u044f").setValue(true);
    private final BooleanSetting arrowsSetting = new BooleanSetting("Arrows", "\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u0441\u0442\u0440\u0435\u043b\u043a\u0438 \u043a \u0441\u0443\u043d\u0434\u0443\u043a\u0430\u043c").setValue(false);
    private final SliderSettings arrowRadiusSetting = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441 \u0441\u0442\u0440\u0435\u043b\u043e\u043a", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0441\u0442\u0440\u0435\u043b\u043e\u043a \u043e\u0442 \u043f\u0440\u0438\u0446\u0435\u043b\u0430").setValue(50.0f).range(30, 100).visible(this.arrowsSetting::isValue);
    private final SliderSettings arrowSizeSetting = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440 \u0441\u0442\u0440\u0435\u043b\u043e\u043a", "\u0420\u0430\u0437\u043c\u0435\u0440 \u0441\u0442\u0440\u0435\u043b\u043e\u043a").setValue(10.0f).range(8, 20).visible(this.arrowsSetting::isValue);
    private final Map<class_2338, ChestData> chests = new ConcurrentHashMap<class_2338, ChestData>();

    public WardenHelper() {
        super("WardenHelper", "WardenHelper", ModuleCategory.RENDER);
        this.setup(this.rangeSetting, this.tagSizeSetting, this.notifySoon, this.arrowsSetting, this.arrowRadiusSetting, this.arrowSizeSetting);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.chests.clear();
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        this.chests.clear();
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (WardenHelper.mc.field_1687 == null || WardenHelper.mc.field_1724 == null) {
            return;
        }
        if (WardenHelper.mc.field_1724.field_6012 % 5 == 0) {
            this.scanNearbyChests();
        }
        class_243 playerPos = WardenHelper.mc.field_1724.method_19538();
        double removeDistance = (double)this.rangeSetting.getValue() + 10.0;
        this.chests.entrySet().removeIf(entry -> {
            ChestData data = (ChestData)entry.getValue();
            if (playerPos.method_1022(((class_2338)entry.getKey()).method_46558()) > removeDistance) {
                return true;
            }
            return data.hasValidTimer() && data.getRemainingSeconds() <= 0;
        });
        if (this.notifySoon.isValue() && this.shouldSendChestNotification()) {
            this.chests.forEach((pos, data) -> this.maybeNotifySoon((class_2338)pos, (ChestData)data));
        }
    }

    private void scanNearbyChests() {
        int range = (int)this.rangeSetting.getValue();
        class_2338 playerPos = WardenHelper.mc.field_1724.method_24515();
        for (int x = -range; x <= range; ++x) {
            for (int z = -range; z <= range; ++z) {
                for (int y = -5; y <= 5; ++y) {
                    class_2338 pos = playerPos.method_10069(x, y, z);
                    if (!(WardenHelper.mc.field_1687.method_8320(pos).method_26204() instanceof class_2281)) continue;
                    this.updateChestTimer(pos);
                }
            }
        }
    }

    private void updateChestTimer(class_2338 pos) {
        class_243 center = pos.method_46558();
        class_238 searchBox = new class_238(center.field_1352 - 1.0, center.field_1351, center.field_1350 - 1.0, center.field_1352 + 1.0, center.field_1351 + 4.0, center.field_1350 + 1.0);
        for (class_1297 entity : WardenHelper.mc.field_1687.method_8335(null, searchBox)) {
            Matcher matcher;
            String rawText = null;
            if (entity instanceof class_8113.class_8123) {
                class_8113.class_8123 textDisplay = (class_8113.class_8123)entity;
                rawText = textDisplay.method_48915().getString();
            } else if (entity instanceof class_1531) {
                class_1531 armorStand = (class_1531)entity;
                String string = rawText = armorStand.method_5797() != null ? armorStand.method_5797().getString() : null;
            }
            if (rawText == null || !(matcher = TIME_PATTERN.matcher(class_124.method_539((String)rawText) == null ? rawText : class_124.method_539((String)rawText))).find()) continue;
            int totalSeconds = Integer.parseInt(matcher.group(1)) * 60 + Integer.parseInt(matcher.group(2));
            ChestData data = this.chests.get(pos);
            if (data == null) {
                this.chests.put(pos, new ChestData(totalSeconds, entity));
            } else {
                data.update(totalSeconds, entity);
            }
            return;
        }
        this.chests.putIfAbsent(pos, new ChestData(-1, null));
    }

    private void maybeNotifySoon(class_2338 pos, ChestData data) {
        if (!data.hasValidTimer() || data.notifiedSoon) {
            return;
        }
        int remaining = data.getRemainingSeconds();
        if (remaining > 30 || remaining <= 0) {
            return;
        }
        class_5250 text = class_2561.method_43470((String)"\u0421\u0443\u043d\u0434\u0443\u043a \u0441\u043a\u043e\u0440\u043e \u043e\u0442\u043a\u0440\u043e\u0435\u0442\u0441\u044f").method_27692(class_124.field_1060).method_10852((class_2561)class_2561.method_43470((String)(" [" + this.formatTime(remaining) + "]")).method_27692(class_124.field_1080)).method_10852((class_2561)class_2561.method_43470((String)(" " + pos.method_10263() + " " + pos.method_10264() + " " + pos.method_10260())).method_27692(class_124.field_1063));
        Notifications.getInstance().addList((class_2561)text, 3500, (Object)SoundManager.NOTIF, false, CHEST_ICON.method_7972());
        data.notifiedSoon = true;
    }

    private boolean shouldSendChestNotification() {
        Hud hud = Hud.getInstance();
        return hud != null && hud.notificationSettings.isSelected("\u0421\u043a\u043e\u0440\u043e\u043c \u043e\u0442\u043a\u0440\u044b\u0442\u0438\u0438 \u0441\u0443\u043d\u0434\u0443\u043a\u0430");
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (WardenHelper.mc.field_1687 == null || WardenHelper.mc.field_1724 == null) {
            return;
        }
        for (Map.Entry<class_2338, ChestData> entry : this.chests.entrySet()) {
            int remaining;
            ChestData data = entry.getValue();
            if (!data.hasValidTimer() || (remaining = data.getRemainingSeconds()) <= 0) continue;
            Render3D.drawBox(new class_238(entry.getKey()), this.getChestColor(remaining), 2.0f);
        }
    }

    @EventHandler
    public void onDraw(DrawEvent e) {
        if (WardenHelper.mc.field_1687 == null || WardenHelper.mc.field_1724 == null) {
            return;
        }
        class_332 context = e.getDrawContext();
        class_4587 matrix = context.method_51448();
        FontRenderer font = Fonts.getSize((int)this.tagSizeSetting.getValue());
        for (Map.Entry<class_2338, ChestData> entry : this.chests.entrySet()) {
            class_243 drawPos;
            int remaining;
            ChestData data = entry.getValue();
            if (!data.hasValidTimer() || (remaining = data.getRemainingSeconds()) <= 0) continue;
            class_243 class_2432 = drawPos = data.entity != null ? data.entity.method_19538().method_1031(0.0, (double)data.entity.method_17682() + 0.25, 0.0) : entry.getKey().method_46558().method_1031(0.0, 1.4, 0.0);
            if (!Projection.canSee(drawPos)) continue;
            class_243 screen = Projection.worldSpaceToScreenSpace(drawPos);
            if (screen.field_1350 <= 0.0 || screen.field_1350 >= 1.0) continue;
            class_5250 text = class_2561.method_43470((String)this.formatTime(remaining)).method_27692(this.getTextFormatting(remaining));
            this.drawTag(matrix, (class_2561)text, screen.field_1352, screen.field_1351, font);
        }
    }

    private void drawTag(class_4587 matrix, class_2561 text, double x, double y, FontRenderer font) {
        float width = font.getStringWidth(text);
        float height = (float)font.getFont().getSize() / 1.5f;
        float drawX = (float)x - width / 2.0f;
        float drawY = (float)y - height;
        blur.render(ShapeProperties.create(matrix, drawX - 3.0f, drawY - 1.0f, width + 6.0f, height + 3.0f).round(4.0f).color(ColorAssist.HALF_BLACK).build());
        font.drawText(matrix, text, drawX, drawY + 3.0f);
    }

    private int getChestColor(int remainingSeconds) {
        if (remainingSeconds <= 60) {
            return -1875640525;
        }
        if (remainingSeconds <= 120) {
            return -1862297293;
        }
        return -1862323405;
    }

    private class_124 getTextFormatting(int remainingSeconds) {
        if (remainingSeconds <= 60) {
            return class_124.field_1060;
        }
        if (remainingSeconds <= 120) {
            return class_124.field_1065;
        }
        return class_124.field_1061;
    }

    private String formatTime(int seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    public Optional<ChestSnapshot> getClosestChestUnder(int maxSeconds) {
        if (WardenHelper.mc.field_1724 == null) {
            return Optional.empty();
        }
        class_243 playerPos = WardenHelper.mc.field_1724.method_19538();
        return this.chests.entrySet().stream().map(entry -> {
            ChestData data = (ChestData)entry.getValue();
            if (!data.hasValidTimer()) {
                return null;
            }
            int remaining = data.getRemainingSeconds();
            if (remaining <= 0 || remaining > maxSeconds) {
                return null;
            }
            return new ChestSnapshot(((class_2338)entry.getKey()).method_10062(), remaining, data.entity);
        }).filter(snapshot -> snapshot != null).min(Comparator.comparingDouble(snapshot -> playerPos.method_1025(snapshot.pos().method_46558())));
    }

    public Optional<ChestSnapshot> getChestWithMinTimer(int maxSeconds) {
        if (WardenHelper.mc.field_1724 == null) {
            return Optional.empty();
        }
        return this.chests.entrySet().stream().map(entry -> {
            ChestData data = (ChestData)entry.getValue();
            if (!data.hasValidTimer()) {
                return null;
            }
            int remaining = data.getRemainingSeconds();
            if (remaining <= 0 || remaining > maxSeconds) {
                return null;
            }
            return new ChestSnapshot(((class_2338)entry.getKey()).method_10062(), remaining, data.entity);
        }).filter(snapshot -> snapshot != null).min(Comparator.comparingInt(ChestSnapshot::remainingSeconds));
    }

    public Optional<ChestSnapshot> getChestSnapshot(class_2338 pos) {
        ChestData data = this.chests.get(pos);
        if (data == null || !data.hasValidTimer()) {
            return Optional.empty();
        }
        int remaining = data.getRemainingSeconds();
        if (remaining <= 0) {
            return Optional.empty();
        }
        return Optional.of(new ChestSnapshot(pos.method_10062(), remaining, data.entity));
    }

    private static class ChestData {
        private int timerSeconds;
        private long startTime;
        private boolean hasTimer;
        private class_1297 entity;
        private boolean notifiedSoon;

        private ChestData(int seconds, class_1297 entity) {
            this.update(seconds, entity);
        }

        private void update(int seconds, class_1297 entity) {
            if (seconds > 0) {
                if (!this.hasTimer || Math.abs(this.timerSeconds - seconds) > 1) {
                    this.timerSeconds = seconds;
                    this.startTime = System.currentTimeMillis();
                    this.notifiedSoon = false;
                }
                this.hasTimer = true;
            } else if (!this.hasTimer) {
                this.timerSeconds = 0;
                this.startTime = System.currentTimeMillis();
                this.hasTimer = false;
            }
            this.entity = entity;
        }

        private int getRemainingSeconds() {
            if (!this.hasTimer) {
                return 0;
            }
            return Math.max(0, this.timerSeconds - (int)((System.currentTimeMillis() - this.startTime) / 1000L));
        }

        private boolean hasValidTimer() {
            return this.hasTimer;
        }
    }

    public record ChestSnapshot(class_2338 pos, int remainingSeconds, class_1297 entity) {
    }
}
