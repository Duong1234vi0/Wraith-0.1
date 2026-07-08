/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 *  net.minecraft.class_3414
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.hud;

import code.alphadlc.common.animation.implement.EaseOut;
import code.alphadlc.display.hud.Notifications;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.render.geometry.Render2D;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1304;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_3414;
import net.minecraft.class_4587;

public class Armor
extends AbstractDraggable {
    private static final int WARNING_PERCENT = 15;
    private static final int CRITICAL_PERCENT = 5;
    private final List<class_1799> armorStacks = new ArrayList<class_1799>();
    private final Map<class_1304, class_1792> trackedItems = new EnumMap<class_1304, class_1792>(class_1304.class);
    private final Map<class_1304, Integer> warningStage = new EnumMap<class_1304, Integer>(class_1304.class);

    public Armor() {
        super("Armor", 10, 120, 80, 15, true);
        this.scaleAnimation = new EaseOut().setValue(1.0).setMs(100);
    }

    @Override
    public boolean visible() {
        Hud.getInstance();
        return Hud.interfaceSettings.isSelected("Armor") && Hud.getInstance().state;
    }

    @Override
    public void tick() {
        if (Armor.mc.field_1724 == null) {
            return;
        }
        this.armorStacks.clear();
        this.trackArmorSlot(class_1304.field_6169);
        this.trackArmorSlot(class_1304.field_6174);
        this.trackArmorSlot(class_1304.field_6172);
        this.trackArmorSlot(class_1304.field_6166);
    }

    @Override
    public void drawDraggable(class_332 context) {
        if (!this.visible()) {
            return;
        }
        class_4587 matrix = context.method_51448();
        int slotSize = 18;
        int spacing = 2;
        int totalWidth = (slotSize + spacing) * 4 - spacing;
        int totalHeight = slotSize + 2;
        this.setWidth(totalWidth);
        this.setHeight(totalHeight);
        float x = this.getX();
        float y = this.getY();
        for (int i = 0; i < 4; ++i) {
            class_1799 stack;
            float slotX = x + (float)(i * (slotSize + spacing));
            float slotY = y;
            class_1799 class_17992 = stack = i < this.armorStacks.size() ? this.armorStacks.get(i) : class_1799.field_8037;
            if (stack.method_7960()) continue;
            Render2D.defaultDrawStack(context, stack, slotX, slotY, true, true, 1.0f);
        }
    }

    private void trackArmorSlot(class_1304 slot) {
        Hud hud;
        class_1799 stack = Armor.mc.field_1724.method_6118(slot);
        this.armorStacks.add(stack);
        if (stack.method_7960() || !stack.method_7963()) {
            this.trackedItems.remove(slot);
            this.warningStage.remove(slot);
            return;
        }
        if (this.trackedItems.get(slot) != stack.method_7909()) {
            this.trackedItems.put(slot, stack.method_7909());
            this.warningStage.put(slot, 0);
        }
        if ((hud = Hud.getInstance()) == null || !hud.notificationSettings.isSelected("\u0421\u043a\u043e\u0440\u043e\u0439 \u043f\u043e\u043b\u043e\u043c\u043a\u0435 \u0431\u0440\u043e\u043d\u0438")) {
            return;
        }
        int percent = Math.round((float)(stack.method_7936() - stack.method_7919()) * 100.0f / (float)stack.method_7936());
        int currentStage = this.warningStage.getOrDefault(slot, 0);
        if (percent > 15) {
            this.warningStage.put(slot, 0);
            return;
        }
        if (percent <= 5 && currentStage < 2) {
            this.notifyArmorState(stack, "\u041a\u0440\u0438\u0442\u0438\u0447\u0435\u0441\u043a\u0430\u044f \u043f\u0440\u043e\u0447\u043d\u043e\u0441\u0442\u044c: " + stack.method_7964().getString() + " (" + percent + "%)", SoundManager.DISABLE_MODULE);
            this.warningStage.put(slot, 2);
            return;
        }
        if (currentStage < 1) {
            this.notifyArmorState(stack, "\u0421\u043a\u043e\u0440\u043e \u0441\u043b\u043e\u043c\u0430\u0435\u0442\u0441\u044f: " + stack.method_7964().getString() + " (" + percent + "%)", null);
            this.warningStage.put(slot, 1);
        }
    }

    private void notifyArmorState(class_1799 stack, String message, class_3414 sound) {
        Notifications.getInstance().addList((class_2561)class_2561.method_43470((String)message), 3000, (Object)sound, false, stack.method_46651(1));
    }
}
