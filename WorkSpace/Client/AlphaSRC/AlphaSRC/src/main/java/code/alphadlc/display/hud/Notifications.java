/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1799
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 */
package code.alphadlc.display.hud;

import code.alphadlc.display.hud.DynamicIsland;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_332;

public class Notifications
extends AbstractDraggable {
    private static final Notifications INSTANCE = new Notifications();

    private Notifications() {
        super("Notifications", 0, 350, 120, 20, false);
    }

    public static Notifications getInstance() {
        return INSTANCE;
    }

    public void addList(String text, int durationMs) {
        this.push(text, DynamicIsland.NotificationType.DEFAULT);
    }

    public void addList(String text, int durationMs, boolean error) {
        this.push(text, error ? DynamicIsland.NotificationType.ERROR : DynamicIsland.NotificationType.SUCCESS);
    }

    public void addList(String text, int durationMs, Object sound) {
        this.push(text, DynamicIsland.NotificationType.DEFAULT);
    }

    public void addList(String text, int durationMs, Object sound, boolean error, class_1799 icon) {
        this.push(text, error ? DynamicIsland.NotificationType.ERROR : DynamicIsland.NotificationType.DEFAULT);
    }

    public void addList(class_2561 text, int durationMs) {
        this.push(text == null ? null : text.getString(), DynamicIsland.NotificationType.DEFAULT);
    }

    public void addList(class_2561 text, int durationMs, Object sound) {
        this.push(text == null ? null : text.getString(), DynamicIsland.NotificationType.DEFAULT);
    }

    public void addList(class_2561 text, int durationMs, Object sound, boolean error, class_1799 icon) {
        this.push(text == null ? null : text.getString(), error ? DynamicIsland.NotificationType.ERROR : DynamicIsland.NotificationType.DEFAULT);
    }

    private void push(String text, DynamicIsland.NotificationType type) {
        String normalized = this.normalize(text);
        if (normalized == null || normalized.isEmpty()) {
            return;
        }
        DynamicIsland.addNotification(normalized, type);
    }

    private String normalize(String text) {
        if (text == null) {
            return "";
        }
        String stripped = class_124.method_539((String)text);
        return stripped == null ? text : stripped;
    }

    @Override
    public void drawDraggable(class_332 context) {
    }
}
