/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.hud;

import code.alphadlc.common.animation.implement.EaseOut;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.theme.ThemeManager;
import java.awt.Color;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class TotemBar
extends AbstractDraggable {
    private int totemCount = 0;

    public TotemBar() {
        super("TotemBar", 10, 150, 29, 36, true);
        this.scaleAnimation = new EaseOut().setValue(1.0).setMs(100);
    }

    @Override
    public boolean visible() {
        Hud.getInstance();
        return Hud.interfaceSettings.isSelected("TotemBar") && Hud.getInstance().state;
    }

    @Override
    public void tick() {
        if (TotemBar.mc.field_1724 == null) {
            this.totemCount = 0;
            return;
        }
        this.totemCount = 0;
        for (int i = 0; i < TotemBar.mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 stack = TotemBar.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != class_1802.field_8288) continue;
            this.totemCount += stack.method_7947();
        }
    }

    @Override
    public void drawDraggable(class_332 context) {
        if (!this.visible()) {
            return;
        }
        class_4587 matrix = context.method_51448();
        float slotSize = 26.0f;
        float padding = 4.0f;
        float textHeight = 12.0f;
        float totalWidth = slotSize + padding * 2.0f;
        float totalHeight = slotSize + textHeight + padding * 3.0f;
        this.setWidth((int)totalWidth);
        this.setHeight((int)totalHeight);
        float x = this.getX();
        float y = this.getY();
        Hud.getInstance();
        if (Hud.blur.isValue() && !KawaseBlur.INSTANCE.fbos.isEmpty()) {
            Render2D.rectangleWithMask(matrix.method_23760().method_23761(), x, y, totalWidth, totalHeight, 6.0f, 0, KawaseBlur.INSTANCE.fbos.getFirst().method_30277());
        }
        rectangle.render(ShapeProperties.create(matrix, x, y, totalWidth, totalHeight).round(6.0f).softness(2.5f).color(new Color(0, 0, 0, 210).getRGB()).build());
        float slotX = x + padding;
        float slotY = y + padding;
        rectangle.render(ShapeProperties.create(matrix, slotX, slotY, slotSize, slotSize).round(4.0f).color(ThemeManager.BackgroundSettings.getColor()).build());
        class_1799 totemStack = new class_1799((class_1935)class_1802.field_8288);
        float itemSize = 16.0f;
        float itemX = slotX + (slotSize - itemSize) / 2.0f - 1.0f;
        float itemY = slotY + (slotSize - itemSize) / 2.0f - 1.0f;
        Render2D.defaultDrawStack(context, totemStack, itemX, itemY, false, false, 1.0f);
        FontRenderer countFont = Fonts.getSize(21, Fonts.Type.SuisseIntlMedium);
        String countText = String.valueOf(this.totemCount);
        float textWidth = countFont.getStringWidth(countText);
        float countTextHeight = countFont.getStringHeight(countText);
        float textX = x + totalWidth / 2.0f - textWidth / 2.0f;
        float textY = slotY + slotSize + padding + (textHeight - countTextHeight) / 2.0f - 1.5f;
        int textColor = this.totemCount == 0 ? new Color(255, 85, 75, 255).getRGB() : (this.totemCount < 5 ? new Color(255, 170, 0, 255).getRGB() : ThemeManager.textColor.getColor());
        countFont.drawString(matrix, countText, textX, textY, textColor);
    }
}
