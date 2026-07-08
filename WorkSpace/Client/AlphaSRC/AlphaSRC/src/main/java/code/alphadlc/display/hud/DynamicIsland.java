/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1291
 *  net.minecraft.class_1293
 *  net.minecraft.class_1304
 *  net.minecraft.class_1799
 *  net.minecraft.class_332
 *  net.minecraft.class_345
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_640
 */
package code.alphadlc.display.hud;

import code.alphadlc.display.hud.TimerTextAnimator;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.theme.ThemeManager;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_124;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_345;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_640;

public class DynamicIsland
extends AbstractDraggable {
    private static final long SHOW_MS = 2200L;
    private static final float PILL_H = 15.0f;
    private static final float ORB_R = 3.2f;
    private static final float ICON_TEXT_GAP = 5.0f;
    private static final float TIMER_PREFIX_GAP = 3.0f;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final Pattern TIME_MM_SS = Pattern.compile("\\b\\d{1,2}:\\d{2}\\b");
    private static final Pattern TIME_WITH_SUFFIX = Pattern.compile("\\b\\d+\\s*(?:s|sec|secs|\u0441\u0435\u043a|\u0441)\\b", 66);
    private static final Pattern TIME_DIGITS = Pattern.compile("\\b\\d{1,3}\\b");
    private static volatile Notif active = null;
    private static volatile long activeEnd = 0L;
    private static volatile long activeRevision = 0L;
    private static final Queue<Notif> queue = new ArrayDeque<Notif>();
    private final FontRenderer font = Fonts.getSize(16, Fonts.Type.DEFAULT);
    private float pillW = 80.0f;
    private float notifAnim = 0.0f;
    private long lastAnimTime = System.currentTimeMillis();
    private Notif lastNotif = null;
    private long lastSeenRevision = -1L;
    private final Set<String> warnedExpiringEffects = new HashSet<String>();
    private long nextArmorCheckAt = 0L;
    private float pingHoverAnim = 0.0f;
    private String cachedTimeString = "";
    private long cachedTimeAt = 0L;
    private String cachedPvpBossbarText = null;
    private String cachedTeleportBossbarText = null;
    private String cachedEventStatus = null;
    private static Method cachedEventStatusMethod;

    public DynamicIsland() {
        super("Watermark", 10, 10, 80, 18, false);
    }

    public static void addNotification(String text, NotificationType type) {
        if (text == null || text.isEmpty()) {
            return;
        }
        String clean = class_124.method_539((String)text) == null ? text : class_124.method_539((String)text);
        boolean isDynamic = text.contains("\u0418\u0432\u0435\u043d\u0442 \u0447\u0435\u0440\u0435\u0437") || text.contains("\u0418\u0432\u0435\u043d\u0442 \u0443\u0436\u0435 \u0438\u0434\u0435\u0442");
        Notif notif = new Notif(clean, type.color, isDynamic);
        if (active == null || System.currentTimeMillis() >= activeEnd) {
            active = notif;
            activeEnd = System.currentTimeMillis() + 2200L;
            ++activeRevision;
        } else {
            queue.offer(notif);
        }
    }

    public static void notifyModuleToggle(String name, boolean enabled) {
        String cleanName = class_124.method_539((String)name) == null ? name : class_124.method_539((String)name);
        active = new Notif(cleanName + (enabled ? " enabled" : " disabled"), (enabled ? NotificationType.SUCCESS : NotificationType.ERROR).color, false);
        activeEnd = System.currentTimeMillis() + 2200L;
        ++activeRevision;
    }

    public static void strengthNotification() {
        if (DynamicIsland.mc.field_1724 == null) {
            return;
        }
        class_1304[] slots = new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166};
        String[] names = new String[]{"\u0428\u043b\u0435\u043c", "\u041d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a", "\u041f\u043e\u043d\u043e\u0436\u0438", "\u0411\u043e\u0442\u0438\u043d\u043a\u0438"};
        for (int i = 0; i < slots.length; ++i) {
            float pct;
            class_1799 stack = DynamicIsland.mc.field_1724.method_6118(slots[i]);
            if (stack.method_7960() || !stack.method_7963() || !((pct = (float)(stack.method_7936() - stack.method_7919()) / (float)stack.method_7936()) <= 0.1f) || !(pct > 0.0f)) continue;
            DynamicIsland.addNotification(names[i] + " \u0441\u043a\u043e\u0440\u043e \u0441\u043b\u043e\u043c\u0430\u0435\u0442\u0441\u044f!", NotificationType.WARNING);
        }
    }

    public static String getEventStatus() {
        if (cachedEventStatusMethod == null) {
            return null;
        }
        try {
            return (String)cachedEventStatusMethod.invoke(null, new Object[0]);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (active != null && System.currentTimeMillis() >= activeEnd) {
            Notif next;
            active = next = queue.poll();
            activeEnd = next != null ? System.currentTimeMillis() + 2200L : 0L;
            ++activeRevision;
        }
        if (this.lastSeenRevision != activeRevision) {
            this.notifAnim = 0.0f;
            this.lastNotif = active;
            this.lastSeenRevision = activeRevision;
        }
        this.tickArmorDurabilityWarnings();
        this.tickExpiringPotionWarnings();
        this.updateRenderCache();
    }

    private void updateRenderCache() {
        Notif currentActive;
        this.cachedPvpBossbarText = null;
        this.cachedTeleportBossbarText = null;
        if (mc != null && DynamicIsland.mc.field_1705 != null && DynamicIsland.mc.field_1705.method_1740() != null) {
            for (class_345 bar : DynamicIsland.mc.field_1705.method_1740().field_2060.values()) {
                String raw;
                if (bar == null || bar.method_5414() == null || (raw = class_124.method_539((String)bar.method_5414().getString())) == null || raw.isEmpty()) continue;
                String low = raw.toLowerCase(Locale.ROOT);
                if (this.cachedPvpBossbarText == null && (low.contains("pvp") || low.contains("\u043f\u0432\u043f"))) {
                    this.cachedPvpBossbarText = raw;
                }
                if (this.cachedTeleportBossbarText == null && (low.contains("\u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446") || low.contains("\u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442") || low.contains("\u043f\u0435\u0440\u0435\u043c\u0435\u0449") || low.contains("teleport") || low.contains("tp "))) {
                    this.cachedTeleportBossbarText = raw;
                }
                if (this.cachedPvpBossbarText == null || this.cachedTeleportBossbarText == null) continue;
                break;
            }
        }
        long now = System.currentTimeMillis();
        if (this.cachedTimeAt == 0L || now - this.cachedTimeAt >= 1000L) {
            this.cachedTimeString = LocalTime.now().format(TIME_FORMATTER);
            this.cachedTimeAt = now;
        }
        this.cachedEventStatus = (currentActive = active) != null && currentActive.isDynamic() && now < activeEnd ? DynamicIsland.getEventStatus() : null;
    }

    private void tickArmorDurabilityWarnings() {
        Hud hud = Hud.getInstance();
        if (hud == null || hud.notificationSettings == null) {
            return;
        }
        if (!hud.notificationSettings.isSelected("\u0421\u043a\u043e\u0440\u043e\u0439 \u043f\u043e\u043b\u043e\u043c\u043a\u0435 \u0431\u0440\u043e\u043d\u0438")) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now < this.nextArmorCheckAt) {
            return;
        }
        this.nextArmorCheckAt = now + 1200L;
        DynamicIsland.strengthNotification();
    }

    private void tickExpiringPotionWarnings() {
        Hud hud = Hud.getInstance();
        if (hud == null || hud.notificationSettings == null || DynamicIsland.mc.field_1724 == null) {
            return;
        }
        if (!hud.notificationSettings.isSelected("\u0417\u0430\u043a\u0430\u043d\u0447\u0438\u0432\u0430\u044e\u0449\u0435\u043c\u0441\u044f \u0437\u0435\u043b\u044c\u0435")) {
            return;
        }
        HashSet<CallSite> activeKeys = new HashSet<CallSite>();
        for (class_1293 effect : DynamicIsland.mc.field_1724.method_6026()) {
            String effectName = ((class_1291)effect.method_5579().comp_349()).method_5560().getString();
            int amp = effect.method_5578() + 1;
            String key = effectName + ":" + amp;
            activeKeys.add((CallSite)((Object)key));
            int seconds = effect.method_5584() / 20;
            if (seconds <= 0 || seconds > 30 || this.warnedExpiringEffects.contains(key)) continue;
            DynamicIsland.addNotification("\u0417\u0435\u043b\u044c\u0435 " + effectName + " " + amp + " \u0437\u0430\u043a\u0430\u043d\u0447\u0438\u0432\u0430\u0435\u0442\u0441\u044f (" + seconds + "s)", NotificationType.WARNING);
            this.warnedExpiringEffects.add(key);
        }
        Iterator<String> it = this.warnedExpiringEffects.iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (activeKeys.contains(key)) continue;
            it.remove();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean canDraw(Hud hud, AbstractDraggable draggable) {
        if (hud == null) return false;
        if (!hud.isState()) return false;
        if (!Hud.interfaceSettings.isSelected("Watermark")) return false;
        return true;
    }

    private IslandCapsule resolveCapsule(boolean showNotif, Notif renderNotif) {
        String pvpRaw = this.cachedPvpBossbarText;
        if (pvpRaw != null) {
            String timer = this.extractPvpTimer(pvpRaw);
            int accent = new Color(12131356).getRGB();
            return new IslandCapsule(IslandState.PVP, "\u0412\u044b \u0432 PVP \u0440\u0435\u0436\u0438\u043c\u0435", "\u0412\u044b \u0432 PvP", timer, accent);
        }
        String tpRaw = this.cachedTeleportBossbarText;
        if (tpRaw != null) {
            String timer = this.extractPvpTimer(tpRaw);
            int accent = new Color(6530303).getRGB();
            return new IslandCapsule(IslandState.TELEPORT, "\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u044f...", "\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u044f \u0447\u0435\u0440\u0435\u0437 ", timer, accent);
        }
        if (showNotif && renderNotif != null) {
            String updatedStatus;
            String text = renderNotif.text();
            if (renderNotif.isDynamic() && (updatedStatus = this.cachedEventStatus) != null && !updatedStatus.isEmpty()) {
                text = updatedStatus;
            }
            return new IslandCapsule(IslandState.NOTIFICATION, text, "", null, renderNotif.color().getRGB());
        }
        return new IslandCapsule(IslandState.DEFAULT, this.getDisplayName(), "", null, ThemeManager.primaryColor.getColor());
    }

    @Override
    public void drawDraggable(class_332 context) {
        class_4587 matrices = context.method_51448();
        int scaledWidth = window.getScaledWidth();
        int themeText = ThemeManager.textColor.getColor();
        long now = System.currentTimeMillis();
        boolean hasNotif = active != null && now < activeEnd;
        float targetAnim = hasNotif ? 1.0f : 0.0f;
        float dt = (float)(now - this.lastAnimTime) / 1000.0f;
        this.lastAnimTime = now;
        this.notifAnim += (targetAnim - this.notifAnim) * Math.min(1.0f, dt * 12.0f);
        if (this.notifAnim < 0.005f) {
            this.notifAnim = 0.0f;
        }
        if (this.notifAnim > 0.995f) {
            this.notifAnim = 1.0f;
        }
        if (hasNotif) {
            this.lastNotif = active;
        }
        if (!hasNotif && this.notifAnim == 0.0f) {
            this.lastNotif = null;
        }
        Notif renderNotif = hasNotif ? active : (this.notifAnim > 0.0f ? this.lastNotif : null);
        boolean showNotif = renderNotif != null;
        IslandCapsule capsule = this.resolveCapsule(showNotif, renderNotif);
        IslandState state = capsule.state();
        boolean hasTeleport = state == IslandState.TELEPORT;
        boolean hasPvp = state == IslandState.PVP;
        int textBase = themeText;
        String time = this.cachedTimeString;
        float timeW = this.font.getStringWidth(time);
        this.pillW += ((switch (state.ordinal()) {
            case 2, 3 -> {
                String full = capsule.timer() == null ? capsule.text() : capsule.prefix() + " " + capsule.timer();
                float tw = this.font.getStringWidth(full) + (capsule.timer() != null ? 3.0f : 0.0f);
                yield 17.4f + tw + 8.0f;
            }
            case 1 -> {
                float tw = this.font.getStringWidth(capsule.text());
                yield 17.4f + tw + 8.0f;
            }
            default -> {
                float nameW = this.font.getStringWidth(capsule.text());
                yield 17.4f + nameW + 8.0f;
            }
        }) - this.pillW) * 0.16f;
        float pingGroupW = 16.2f;
        float totalW = timeW + 6.0f + this.pillW + 6.0f + pingGroupW;
        float startX = (float)scaledWidth / 2.0f - totalW / 2.0f;
        startX = class_3532.method_15363((float)startX, (float)2.0f, (float)Math.max(2.0f, (float)scaledWidth - totalW - 2.0f));
        float pillX = startX + timeW + 6.0f;
        float pillY = 7.0f;
        float midY = pillY + 7.5f;
        float textY = this.centeredTextY(this.font, pillY, 15.0f);
        int timePingColor = themeText;
        this.font.drawString(matrices, time, startX, textY, timePingColor);
        Hud.getInstance();
        if (Hud.blur.isValue() && !KawaseBlur.INSTANCE.fbos.isEmpty()) {
            Render2D.rectangleWithMask(matrices.method_23760().method_23761(), pillX, pillY, this.pillW, 15.0f, 7.5f, 0, KawaseBlur.INSTANCE.fbos.getFirst().method_30277());
        }
        rectangle.render(ShapeProperties.create(matrices, pillX, pillY, this.pillW, 15.0).round(7.5f).softness(2.5f).color(new Color(0, 0, 0, 210).getRGB()).build());
        float cx = pillX + 6.0f;
        if (hasTeleport) {
            int dotColor = ColorAssist.multAlpha(capsule.accentColor(), 0.95f);
            rectangle.render(ShapeProperties.create(matrices, cx, midY - 3.2f, 6.4f, 6.4f).round(3.2f).color(dotColor).build());
            cx += 11.4f;
            if (capsule.timer() == null) {
                this.font.drawString(matrices, capsule.text(), cx, textY, textBase);
            } else {
                this.font.drawString(matrices, capsule.prefix(), cx, textY, textBase);
                TimerTextAnimator.draw(this.font, matrices, "dynamic_island_tp_timer", capsule.timer(), cx + this.font.getStringWidth(capsule.prefix()) + 3.0f, textY, textBase);
            }
        } else if (hasPvp) {
            int dotColor = ColorAssist.multAlpha(capsule.accentColor(), 0.95f);
            rectangle.render(ShapeProperties.create(matrices, cx, midY - 3.2f, 6.4f, 6.4f).round(3.2f).color(dotColor).build());
            cx += 11.4f;
            if (capsule.timer() == null) {
                this.font.drawString(matrices, capsule.text(), cx, textY, textBase);
            } else {
                this.font.drawString(matrices, capsule.prefix(), cx, textY, textBase);
                TimerTextAnimator.draw(this.font, matrices, "dynamic_island_pvp_timer", capsule.timer(), cx + this.font.getStringWidth(capsule.prefix()) + 3.0f, textY, textBase);
            }
        } else if (showNotif) {
            float slide = (1.0f - this.notifAnim) * 7.0f;
            int dotColor = ColorAssist.multAlpha(capsule.accentColor(), Math.max(0.1f, this.notifAnim));
            int textColor = ColorAssist.multAlpha(textBase, Math.max(0.2f, this.notifAnim));
            rectangle.render(ShapeProperties.create(matrices, cx + slide, midY - 3.2f, 6.4f, 6.4f).round(3.2f).color(dotColor).build());
            this.font.drawString(matrices, capsule.text(), (cx += 11.4f) + slide, textY, textColor);
        } else {
            this.renderClientOrb(matrices, cx, midY);
            int titleColor = ThemeManager.textColor.getColor();
            this.font.drawString(matrices, capsule.text(), Math.round(cx += 11.4f), Math.round(textY), titleColor);
        }
        float barsX = pillX + this.pillW + 6.0f;
        float barsY = midY - 5.0f;
        float mouseX = (float)(DynamicIsland.mc.field_1729.method_1603() / window.getScaleFactor());
        float mouseY = (float)(DynamicIsland.mc.field_1729.method_1604() / window.getScaleFactor());
        boolean pingHovered = mouseX >= barsX - 3.0f && mouseX <= barsX + 17.0f && mouseY >= barsY - 3.0f && mouseY <= barsY + 14.0f;
        this.pingHoverAnim += ((pingHovered ? 1.0f : 0.0f) - this.pingHoverAnim) * 0.2f;
        this.renderPing(matrices, barsX, barsY, timePingColor, this.pingHoverAnim);
        if (this.pingHoverAnim > 0.03f) {
            int pingTextColor = ColorAssist.multAlpha(timePingColor, this.pingHoverAnim);
            String pingText = this.getPlayerPing() + " ms";
            float pingTextX = barsX + 16.0f;
            this.font.drawString(matrices, pingText, pingTextX, textY, pingTextColor);
        }
        this.setX((int)startX);
        this.setY((int)pillY);
        this.setWidth((int)Math.ceil(totalW));
        this.setHeight(15);
        this.clampToScreen();
    }

    private void renderPing(class_4587 matrices, float x, float y, int textBase, float hoverAnim) {
        int ping = this.getPlayerPing();
        int lit = ping <= 50 ? 4 : (ping <= 80 ? 3 : (ping <= 120 ? 2 : (ping <= 180 ? 1 : 0)));
        int[] maxH = new int[]{4, 6, 8, 10};
        long now = System.currentTimeMillis();
        float slideX = 4.0f * hoverAnim;
        for (int i = 0; i < 4; ++i) {
            float breath = (float)(Math.sin((double)now / 600.0 + (double)i * 0.7) * 0.5 + 0.5);
            float bh = i < lit ? (float)maxH[i] * (0.6f + 0.4f * breath) : (float)maxH[i] * (0.25f + 0.1f * breath);
            int col = i < lit ? ColorAssist.multAlpha(textBase, 0.85f * (1.0f - hoverAnim * 0.2f)) : ColorAssist.multAlpha(textBase, 0.18f * (1.0f - hoverAnim * 0.25f));
            rectangle.render(ShapeProperties.create(matrices, x + (float)i * 3.3f + slideX, y + (10.0f - bh), 2.8f, bh).round(1.0f).color(col).build());
        }
    }

    private float centeredTextY(FontRenderer font, float boxY, float boxHeight) {
        return font.getCenteredY(boxY, boxHeight);
    }

    private void renderClientOrb(class_4587 matrices, float x, float midY) {
        long now = System.currentTimeMillis();
        float t = (float)((Math.sin((double)now / 320.0) + 1.0) * 0.5);
        int c1 = ThemeManager.primaryColor.getColor();
        int c2 = ThemeManager.secondaryColor.getColor();
        int orb = DynamicIsland.blend(c1, c2, t);
        rectangle.render(ShapeProperties.create(matrices, x, midY - 3.2f, 6.4f, 6.4f).round(3.2f).color(orb).build());
    }

    private String extractPvpTimer(String text) {
        Matcher mmss = TIME_MM_SS.matcher(text);
        if (mmss.find()) {
            return mmss.group();
        }
        Matcher withSuffix = TIME_WITH_SUFFIX.matcher(text);
        if (withSuffix.find()) {
            return withSuffix.group().replaceAll("\\s+", "");
        }
        Matcher digits = TIME_DIGITS.matcher(text);
        if (digits.find()) {
            return digits.group() + "s";
        }
        return null;
    }

    private String getDisplayName() {
        return "AlphaDLC";
    }

    private int getPlayerPing() {
        if (DynamicIsland.mc.field_1724 == null || mc.method_1562() == null) {
            return 0;
        }
        class_640 entry = mc.method_1562().method_2871(DynamicIsland.mc.field_1724.method_5667());
        return entry != null ? entry.method_2959() : 0;
    }

    private static int blend(int from, int to, float t) {
        float p = class_3532.method_15363((float)t, (float)0.0f, (float)1.0f);
        int fr = from >> 16 & 0xFF;
        int fg = from >> 8 & 0xFF;
        int fb = from & 0xFF;
        int fa = from >>> 24 & 0xFF;
        int tr = to >> 16 & 0xFF;
        int tg = to >> 8 & 0xFF;
        int tb = to & 0xFF;
        int ta = to >>> 24 & 0xFF;
        int r = (int)((float)fr + (float)(tr - fr) * p);
        int g = (int)((float)fg + (float)(tg - fg) * p);
        int b = (int)((float)fb + (float)(tb - fb) * p);
        int a = (int)((float)fa + (float)(ta - fa) * p);
        return a << 24 | r << 16 | g << 8 | b;
    }

    static {
        try {
            Class<?> trackerClass = Class.forName("code.alphadlc.features.impl.movement.autoMystGlider.EventStatusTracker");
            cachedEventStatusMethod = trackerClass.getMethod("getDisplayStatus", new Class[0]);
        }
        catch (Exception ignored) {
            cachedEventStatusMethod = null;
        }
    }

    private record Notif(String text, Color color, boolean isDynamic) {
    }

    public static enum NotificationType {
        SUCCESS(new Color(4908362)),
        ERROR(new Color(0xFF4545)),
        WARNING(new Color(16758832)),
        INFO(new Color(4894207)),
        DEFAULT(new Color(11032055));

        public final Color color;

        private NotificationType(Color color) {
            this.color = color;
        }
    }

    private record IslandCapsule(IslandState state, String text, String prefix, String timer, int accentColor) {
    }

    private static enum IslandState {
        DEFAULT,
        NOTIFICATION,
        PVP,
        TELEPORT;

    }
}
