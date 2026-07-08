/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_2596
 *  net.minecraft.class_2656
 *  net.minecraft.class_2724
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 *  net.minecraft.class_7923
 */
package code.alphadlc.display.hud;

import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.common.animation.implement.EaseOut;
import code.alphadlc.display.hud.HudListPanel;
import code.alphadlc.display.hud.TimerTextAnimator;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.chat.StringHelper;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.BackgroundIcons;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.math.time.StopWatch;
import code.alphadlc.utils.theme.ThemeManager;
import java.awt.Color;
import java.lang.runtime.SwitchBootstraps;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2596;
import net.minecraft.class_2656;
import net.minecraft.class_2724;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_7923;

public class CoolDowns
extends AbstractDraggable {
    public final List<CoolDown> list = new ArrayList<CoolDown>();
    private final List<CoolDown> renderSnapshot = new ArrayList<CoolDown>();
    private long lastItemChange = 0L;
    private int currentItemIndex = 0;
    private static final class_1792[] EXAMPLE_ITEMS = new class_1792[]{class_1802.field_8449, class_1802.field_8634, class_1802.field_8479, class_1802.field_49814, class_1802.field_8367, class_1802.field_8547, class_1802.field_8399, class_1802.field_8551, class_1802.field_22021};
    private final FontRenderer fontMedium = Fonts.getSize(13, Fonts.Type.SuisseIntlMedium);
    private final FontRenderer fontHeader = Fonts.getSize(16, Fonts.Type.DEFAULT);
    private final FontRenderer fontDuration = Fonts.getSize(13, Fonts.Type.DEFAULT);
    private final FontRenderer fontItemName = Fonts.getSize(14, Fonts.Type.SuisseIntlMedium);

    public static CoolDowns getInstance() {
        return Instance.getDraggable(CoolDowns.class);
    }

    public CoolDowns() {
        super("Cooldowns", 10, 40, 80, 23, true);
        this.scaleAnimation = new EaseOut().setValue(1.0).setMs(100);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean visible() {
        if (this.list.isEmpty()) {
            if (!PlayerInteractionHelper.isChat(CoolDowns.mc.field_1755)) return false;
        }
        Hud.getInstance();
        if (!Hud.interfaceSettings.isSelected("Cooldowns")) return false;
        if (!Hud.getInstance().state) return false;
        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            FontRenderer iconFont = Fonts.getSize(15, Fonts.Type.ALPHADLC_ICONS);
            float pX = (float)(this.getX() + this.getWidth()) - iconFont.getStringWidth("p") - 8.0f;
            float pY = (float)this.getY() + 9.5f;
            float pWidth = iconFont.getStringWidth("p");
            float pHeight = iconFont.getStringHeight("p");
            if (mouseX >= (double)pX && mouseX <= (double)(pX + pWidth) && mouseY >= (double)pY && mouseY <= (double)(pY + pHeight)) {
                Hud.getInstance();
                ArrayList<String> selected = new ArrayList<String>(Hud.interfaceSettings.getSelected());
                selected.remove("Cooldowns");
                Hud.getInstance();
                Hud.interfaceSettings.setSelected(selected);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void tick() {
        long currentTime;
        this.list.removeIf(c -> c.anim.isFinished(Direction.BACKWARDS));
        this.list.stream().filter(c -> !Objects.requireNonNull(CoolDowns.mc.field_1724).method_7357().method_7904(c.item.method_7854())).forEach(coolDown -> coolDown.anim.setDirection(Direction.BACKWARDS));
        if (this.list.isEmpty() && PlayerInteractionHelper.isChat(CoolDowns.mc.field_1755) && (currentTime = System.currentTimeMillis()) - this.lastItemChange >= 1000L) {
            this.currentItemIndex = (this.currentItemIndex + 1) % EXAMPLE_ITEMS.length;
            this.lastItemChange = currentTime;
        }
    }

    @Override
    public void packet(PacketEvent e) {
        if (PlayerInteractionHelper.nullCheck()) {
            return;
        }
        class_2596<?> class_25962 = e.getPacket();
        Objects.requireNonNull(class_25962);
        class_2596<?> class_25963 = class_25962;
        int n = 0;
        switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_2656.class, class_2724.class}, class_25963, n)) {
            case 0: {
                class_2656 c = (class_2656)class_25963;
                class_1792 item = (class_1792)class_7923.field_41178.method_63535(c.comp_3082());
                this.list.stream().filter(coolDown -> coolDown.item.equals(item)).forEach(coolDown -> coolDown.anim.setDirection(Direction.BACKWARDS));
                if (c.comp_2199() == 0) break;
                this.list.add(new CoolDown(item, new StopWatch().setMs((long)(-c.comp_2199()) * 50L), new Decelerate().setMs(150).setValue(1.0)));
                break;
            }
            case 1: {
                class_2724 p = (class_2724)class_25963;
                this.list.clear();
                break;
            }
        }
    }

    @Override
    public void drawDraggable(class_332 context) {
        class_4587 matrix = context.method_51448();
        this.renderSnapshot.clear();
        this.renderSnapshot.addAll(this.list);
        FontRenderer fontMedium = this.fontMedium;
        FontRenderer font = this.fontHeader;
        FontRenderer fontDuration = this.fontDuration;
        FontRenderer fontItemName = this.fontItemName;
        float posX = this.getX();
        float posY = this.getY();
        float maxElementsWidth = HudListPanel.headerMinWidth(font, "Cooldowns");
        float headerHeight = 21.0f;
        float cooldownHeight = 11.0f;
        boolean isFound = false;
        for (CoolDown coolDown : this.renderSnapshot) {
            int time;
            String duration;
            float elementsWidth;
            if (!(coolDown.anim.getOutput().floatValue() > 0.0f)) continue;
            isFound = true;
            String name = coolDown.item.method_7854().method_7964().getString();
            if (name.equals("\u0417\u0430\u0447\u0430\u0440\u043e\u0432\u0430\u043d\u043d\u043e\u0435 \u0437\u043e\u043b\u043e\u0442\u043e\u0435 \u044f\u0431\u043b\u043e\u043a\u043e")) {
                name = "\u0427\u0430\u0440\u043a\u0430";
            }
            if (!((elementsWidth = HudListPanel.rowWidth(fontItemName, name, 0.0f, fontDuration, duration = StringHelper.getDuration(time = (int)(-coolDown.time.elapsedTime() / 1000L)))) > maxElementsWidth)) continue;
            maxElementsWidth = elementsWidth;
        }
        if (this.list.isEmpty() && PlayerInteractionHelper.isChat(CoolDowns.mc.field_1755)) {
            isFound = true;
            float elementsWidth = HudListPanel.rowWidth(fontItemName, "Example", 0.0f, fontDuration, "**:**");
            if (elementsWidth > maxElementsWidth) {
                maxElementsWidth = elementsWidth;
            }
        }
        float totalContentHeight = 0.0f;
        for (CoolDown coolDown : this.renderSnapshot) {
            float animValue = coolDown.anim.getOutput().floatValue();
            if (!(animValue > 0.0f)) continue;
            totalContentHeight += cooldownHeight * animValue;
        }
        if (this.list.isEmpty() && PlayerInteractionHelper.isChat(CoolDowns.mc.field_1755)) {
            totalContentHeight += cooldownHeight;
        }
        float f = HudListPanel.panelHeight(totalContentHeight);
        float width = maxElementsWidth;
        Hud.getInstance();
        if (Hud.blur.isValue() && !KawaseBlur.INSTANCE.fbos.isEmpty()) {
            Render2D.rectangleWithMask(matrix.method_23760().method_23761(), posX, posY, width, f, 6.0f, 0, KawaseBlur.INSTANCE.fbos.getFirst().method_30277());
        }
        rectangle.render(ShapeProperties.create(matrix, posX, posY, width, f).round(6.0f).softness(2.5f).color(new Color(0, 0, 0, 210).getRGB()).build());
        float headerTextY = HudListPanel.headerTextY(font, posY, "Cooldowns");
        font.drawString(matrix, "Cooldowns", Math.round(posX + 7.0f), Math.round(headerTextY), ThemeManager.textColor.getColor());
        float repBoxW = fontDuration.getStringWidth("0:00") + 6.0f;
        float timerCenterX = posX + width - 6.5f - repBoxW / 2.0f;
        BackgroundIcons.drawHudHeaderIcon(context, BackgroundIcons.COOLDOWNS, font, "Cooldowns", headerTextY, timerCenterX, ColorAssist.getHudColor());
        posY = HudListPanel.contentStartY(posY);
        if (this.list.isEmpty() && PlayerInteractionHelper.isChat(CoolDowns.mc.field_1755)) {
            class_1792 item = EXAMPLE_ITEMS[this.currentItemIndex];
            String name = "Example";
            String duration = "**:**";
            matrix.method_22903();
            matrix.method_46416(posX + width / 2.0f, posY + cooldownHeight / 2.0f, 0.0f);
            matrix.method_22905(1.0f, 1.0f, 1.0f);
            matrix.method_46416(-(posX + width / 2.0f), -(posY + cooldownHeight / 2.0f), 0.0f);
            Render2D.defaultDrawStack(context, item.method_7854(), posX + 7.0f, HudListPanel.rowIconY(posY, 9.0f), false, false, 0.5f);
            rectangle.render(ShapeProperties.create(matrix, posX + 18.0f, HudListPanel.rowDotY(posY), 2.0, 2.0).round(1.0f).color(new Color(87, 87, 90, 255).getRGB()).build());
            float nameY = HudListPanel.alignedRowTextY(fontItemName, fontItemName, posY);
            fontItemName.drawString(matrix, name, Math.round(posX + 22.0f), Math.round(nameY), ThemeManager.textColor.getColor());
            float durationWidth = fontDuration.getStringWidth(duration);
            float durationBoxWidth = durationWidth + 6.0f;
            float durationBoxX = posX + width - 6.5f - durationBoxWidth;
            float durationBoxY = HudListPanel.rowBadgeY(posY, 8.0f);
            rectangle.render(ShapeProperties.create(matrix, durationBoxX, durationBoxY, durationBoxWidth, 8.0).round(2.0f).thickness(1.0f).outlineColor(new Color(33, 33, 33, 255).getRGB()).color(new Color(0, 0, 0, 100).getRGB()).build());
            TimerTextAnimator.draw(fontDuration, matrix, "cooldowns_example_duration", duration, Math.round(durationBoxX + durationBoxWidth / 2.0f - durationWidth / 2.0f + 0.2f), (int)HudListPanel.rowBadgeTextY(fontDuration, posY, 8.0f, duration), ThemeManager.textColor.getColor());
            matrix.method_22909();
            posY += cooldownHeight;
        } else {
            int timerIndex = 0;
            for (CoolDown coolDown : this.renderSnapshot) {
                float animValue = coolDown.anim.getOutput().floatValue();
                if (!(animValue > 0.0f)) continue;
                int time = (int)(-coolDown.time.elapsedTime() / 1000L);
                String name = coolDown.item.method_7854().method_7964().getString();
                if (name.equals("\u0417\u0430\u0447\u0430\u0440\u043e\u0432\u0430\u043d\u043d\u043e\u0435 \u0437\u043e\u043b\u043e\u0442\u043e\u0435 \u044f\u0431\u043b\u043e\u043a\u043e")) {
                    name = "\u0427\u0430\u0440\u043a\u0430";
                }
                String duration = StringHelper.getDuration(time);
                int alpha = (int)(255.0f * animValue);
                Color textColor = new Color(255, 255, 255, alpha);
                matrix.method_22903();
                matrix.method_46416(posX + width / 2.0f, posY + cooldownHeight * animValue / 2.0f, 0.0f);
                matrix.method_22905(animValue, animValue, animValue);
                matrix.method_46416(-(posX + width / 2.0f), -(posY + cooldownHeight * animValue / 2.0f), 0.0f);
                Color gradColor1 = new Color(ColorAssist.getHudColor() >> 16 & 0xFF, ColorAssist.getHudColor() >> 8 & 0xFF, ColorAssist.getHudColor() & 0xFF, alpha);
                Color gradColor2 = new Color(ColorAssist.getHudColor2() >> 16 & 0xFF, ColorAssist.getHudColor2() >> 8 & 0xFF, ColorAssist.getHudColor2() & 0xFF, alpha);
                Render2D.defaultDrawStack(context, coolDown.item.method_7854(), posX + 7.0f, HudListPanel.rowIconY(posY, 9.0f), false, false, 0.5f);
                rectangle.render(ShapeProperties.create(matrix, posX + 18.0f, HudListPanel.rowDotY(posY), 2.0, 2.0).round(1.0f).color(new Color(87, 87, 90, alpha).getRGB()).build());
                float nameX = posX + 22.0f;
                float nameY = HudListPanel.alignedRowTextY(fontItemName, fontItemName, posY);
                fontItemName.drawString(matrix, name, nameX, nameY, textColor.getRGB());
                float durationWidth = fontDuration.getStringWidth(duration);
                float durationBoxWidth = durationWidth + 6.0f;
                float durationBoxX = posX + width - 6.5f - durationBoxWidth;
                float durationBoxY = HudListPanel.rowBadgeY(posY, 8.0f);
                rectangle.render(ShapeProperties.create(matrix, durationBoxX, durationBoxY, durationBoxWidth, 8.0f * animValue).round(2.0f).thickness(1.0f).outlineColor(new Color(33, 33, 33, alpha).getRGB()).color(new Color(0, 0, 0, (int)(100.0f * animValue)).getRGB()).build());
                String timerKey = "cooldowns_duration_" + coolDown.item.method_7876() + "_" + timerIndex;
                TimerTextAnimator.draw(fontDuration, matrix, timerKey, duration, Math.round(durationBoxX + durationBoxWidth / 2.0f - durationWidth / 2.0f + 0.2f), (int)HudListPanel.rowBadgeTextY(fontDuration, posY, 8.0f, duration), textColor.getRGB());
                ++timerIndex;
                matrix.method_22909();
                posY += cooldownHeight * animValue;
            }
        }
        this.setWidth((int)width);
        this.setHeight((int)f);
    }

    public record CoolDown(class_1792 item, StopWatch time, Animation anim) {
    }
}
