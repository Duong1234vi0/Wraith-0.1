package ua.babka.ui.hud;

import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.font.Fonts;
import ua.babka.util.IMinecraft;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class NotificationsElement
implements IMinecraft {
    private static final CopyOnWriteArrayList<Notification> notifications = new CopyOnWriteArrayList();
    private float dragWidth;
    private float dragHeight;

    public void post(String name, boolean enabled) {
        notifications.add(0, Notification.module(name, enabled));
    }

    public void postWarning(String text) {
        notifications.add(0, Notification.warning(text));
    }

    public void postTotem(Text tagText, boolean enchanted) {
        notifications.add(0, Notification.totem(tagText.getString(), enchanted));
    }

    public void postSwap(String itemType) {
        notifications.add(0, Notification.swap(itemType));
    }

    public void render(DrawContext context, float dragX, float dragY) {
        this.render(context, dragX, dragY, false);
    }

    public void render(DrawContext context, float dragX, float dragY, boolean transparentBg) {
        float totalHeight;
        String screenName;
        Screen current = NotificationsElement.mc.currentScreen;
        if (current != null && (screenName = current.getClass().getName()).startsWith("ua.babka.ui")) {
            return;
        }
        long now = System.currentTimeMillis();
        boolean inChat = NotificationsElement.mc.currentScreen instanceof ChatScreen;
        for (Notification notification : notifications) {
            if (!notification.fadingOut && now - notification.timestamp > 1500L) {
                notification.fadingOut = true;
            }
            notification.alphaAnimation.run(notification.fadingOut ? 0.0f : 1.0f);
            if (!notification.fadingOut || !((double)notification.alphaAnimation.getValue() < 0.01)) continue;
            notifications.remove(notification);
        }
        float renderY = dragY;
        for (int i2 = notifications.size() - 1; i2 >= 0; --i2) {
            Notification n2 = notifications.get(i2);
            float alpha = n2.alphaAnimation.getValue();
            if (alpha <= 0.01f) continue;
            this.renderNotification(context, dragX, (renderY += 4.0f * alpha) - 4.0f, n2, alpha, transparentBg);
            renderY += 14.0f * alpha;
        }
        if ((!notifications.isEmpty() || inChat) && (totalHeight = renderY - dragY + 4.0f) > 0.0f) {
            this.dragWidth = 40.0f;
            this.dragHeight = totalHeight;
        }
    }

    public float getDragWidth() {
        return this.dragWidth;
    }

    public float getDragHeight() {
        return this.dragHeight;
    }

    private void renderNotification(DrawContext context, float centerX, float y2, Notification n2, float alpha, boolean transparentBg) {
        String notifText;
        String icon;
        float notificationHeight = 14.0f;
        if (n2.type == NotificationType.TEXT) {
            String text = n2.text;
            float textWidth = Fonts.SFMEDIUM.get().getWidth(text, 6.75f);
            float iconBgWidth = text.contains("Игрок ") && text.contains("просит о наблюдении") ? 9.0f : 14.0f;
            float width = iconBgWidth + (text.contains("Игрок ") && text.contains("просит о наблюдении") ? 8.0f : 6.0f) + textWidth;
            float x2 = centerX - width / 2.0f;
            this.drawNotifBackground(x2, y2, width, 12.0f, alpha, transparentBg);
            DrawUtil.drawRound(x2 + 13.0f, y2 + 1.0f, 0.5f, 10.5f, 0.0f, ColorProvider.rgba(166, 166, 166, (int)(255.0f * alpha)));
            String icon2 = "W";
            DrawUtil.drawText(Fonts.ICONS_NURIK.get(), icon2, x2 + 5.2f, y2 + 0.25f + 2.25f, ColorProvider.setAlpha(ColorProvider.getColorClient(), (int)(255.0f * alpha)), 7.5f);
            DrawUtil.drawText(Fonts.SFMEDIUM.get(), text, x2 + iconBgWidth + 3.0f + (text.contains("Игрок ") && text.contains("просит о наблюдении") ? 5.5f : 0.0f), y2 + 2.625f, ColorProvider.rgba(230, 230, 230, (int)(255.0f * alpha)), 6.75f);
            return;
        }
        if (n2.type == NotificationType.MODULE) {
            icon = n2.enabled ? "K" : "J";
            notifText = "«" + n2.title + "» " + (n2.enabled ? "enabled" : "disabled");
        } else if (n2.type == NotificationType.TOTEM) {
            icon = "O";
            notifText = n2.title + " pop";
        } else {
            icon = "C";
            notifText = "Swap to " + n2.title;
        }
        float iconWidth = Fonts.ICONS_NURIK.get().getWidth(icon, 9.0f);
        float separatorWidth = Fonts.SFMEDIUM.get().getWidth("|", 7.0f);
        float textWidth = Fonts.SFMEDIUM.get().getWidth(notifText, 6.5f);
        float padding = 5.0f;
        float iconPadding = 3.0f;
        float separatorPadding = 4.0f;
        float width = padding + iconWidth + iconPadding + separatorWidth + separatorPadding + textWidth + padding;
        float x3 = centerX - width / 2.0f;
        this.renderBlock(context, x3, y2, width, notificationHeight, alpha, transparentBg);
        float iconX = x3 + padding;
        float iconY = y2 + (notificationHeight - 9.0f) / 2.0f + 1.0f;
        DrawUtil.drawText(Fonts.ICONS_NURIK.get(), icon, iconX, iconY, ColorProvider.rgba(220, 220, 220, (int)(255.0f * alpha)), 9.0f);
        float separatorX = iconX + iconWidth + iconPadding;
        float separatorY = y2 + (notificationHeight - 7.0f) / 2.0f + 0.5f;
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), "|", separatorX, separatorY, ColorProvider.rgba(130, 130, 130, (int)(255.0f * alpha)), 7.0f);
        float textX = separatorX + separatorWidth + separatorPadding;
        float textY = y2 + (notificationHeight - 6.5f) / 2.0f + 0.5f;
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), notifText, textX, textY, ColorProvider.rgba(230, 230, 230, (int)(255.0f * alpha)), 6.5f);
    }

    private void renderBlock(DrawContext context, float x2, float y2, float w2, float h2, float alpha, boolean transparentBg) {
        this.drawNotifBackground(x2, y2, w2, h2, alpha, transparentBg);
    }

    private void drawNotifBackground(float x2, float y2, float w2, float h2, float alpha, boolean transparentBg) {
        if (transparentBg) {
            DrawUtil.drawRoundBlur(x2, y2, w2, h2, 3.0f, ColorProvider.rgba(120, 120, 120, (int)(255.0f * alpha * 0.35f)), 10.0f);
        } else {
            int blur = ColorProvider.rgba(80, 80, 80, (int)(255.0f * alpha * 0.35f));
            int bg = ColorProvider.rgba(18, 18, 18, (int)(200.0f * alpha));
            int outline = ColorProvider.rgba(255, 255, 255, (int)(18.0f * alpha));
            DrawUtil.drawRound(x2, y2, w2, h2, 3.0f, blur);
            DrawUtil.drawRound(x2, y2, w2, h2, 3.0f, bg);
            DrawUtil.drawRound(x2, y2, w2, 0.5f, 2.0f, outline);
            DrawUtil.drawRound(x2, y2 + h2 - 0.5f, w2, 0.5f, 2.0f, outline);
        }
    }

    private static class Notification {
        long timestamp = System.currentTimeMillis();
        boolean fadingOut = false;
        final Animation alphaAnimation = new Animation(Easing.CUBIC_OUT, 300L);
        NotificationType type;
        String title;
        String text;
        boolean enabled;
        boolean enchanted;

        private Notification() {
            this.alphaAnimation.run(1.0f);
        }

        static Notification module(String name, boolean enabled) {
            Notification n2 = new Notification();
            n2.type = NotificationType.MODULE;
            n2.title = name;
            n2.enabled = enabled;
            return n2;
        }

        static Notification warning(String text) {
            Notification n2 = new Notification();
            n2.type = NotificationType.TEXT;
            n2.text = text;
            return n2;
        }

        static Notification totem(String name, boolean enchanted) {
            Notification n2 = new Notification();
            n2.type = NotificationType.TOTEM;
            n2.title = name;
            n2.enchanted = enchanted;
            return n2;
        }

        static Notification swap(String itemType) {
            Notification n2 = new Notification();
            n2.type = NotificationType.SWAP;
            n2.title = itemType;
            return n2;
        }
    }

    private static enum NotificationType {
        MODULE,
        TEXT,
        TOTEM,
        SWAP;

    }
}

