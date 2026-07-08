/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.hud;

import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.common.animation.implement.EaseOut;
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
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class CustomHotbar
extends AbstractDraggable {
    private static final float STATUS_BAR_Y_OFFSET = 39.0f;
    private static final float SLOT_SIZE = 20.0f;
    private static final float SLOT_SPACING = 2.0f;
    private static final float PANEL_PADDING = 3.0f;
    private static final int HOTBAR_SLOTS = 9;
    private static final float OFFHAND_GAP = 6.0f;
    private final Map<Integer, Animation> slotAnimations = new HashMap<Integer, Animation>();

    public static HotbarLayout computeLayout(int screenWidth, int screenHeight) {
        float rightEdge;
        float slotsWidth = 196.0f;
        float panelHeight = 26.0f;
        float hotbarPanelWidth = 6.0f + slotsWidth;
        float hotbarPanelX = ((float)screenWidth - hotbarPanelWidth) / 2.0f;
        float hotbarPanelY = (float)screenHeight - panelHeight - 2.0f;
        float offhandPanelWidth = 26.0f;
        float offhandPanelX = hotbarPanelX - 6.0f - offhandPanelWidth;
        if (offhandPanelX < 1.0f) {
            float shift = 1.0f - offhandPanelX;
            offhandPanelX += shift;
            hotbarPanelX += shift;
        }
        if ((rightEdge = hotbarPanelX + hotbarPanelWidth) > (float)screenWidth - 1.0f) {
            float shift = rightEdge - ((float)screenWidth - 1.0f);
            offhandPanelX -= shift;
            hotbarPanelX -= shift;
        }
        return new HotbarLayout(offhandPanelX, hotbarPanelX, hotbarPanelY, hotbarPanelWidth, panelHeight);
    }

    public static float getArmorBarGap() {
        return 6.0f;
    }

    public CustomHotbar() {
        super("CustomHotbar", 0, 0, 197, 24, false);
        this.scaleAnimation = new EaseOut().setValue(1.0).setMs(100);
        for (int i = 0; i < 9; ++i) {
            this.slotAnimations.put(i, new Decelerate().setMs(200).setValue(0.0));
        }
    }

    @Override
    public boolean visible() {
        Hud.getInstance();
        return Hud.interfaceSettings.isSelected("CustomHotbar") && Hud.getInstance().state;
    }

    @Override
    public void tick() {
        if (CustomHotbar.mc.field_1724 == null) {
            return;
        }
        int currentSlot = CustomHotbar.mc.field_1724.method_31548().field_7545;
        for (int i = 0; i < 9; ++i) {
            Animation anim = this.slotAnimations.get(i);
            if (i == currentSlot) {
                anim.setDirection(Direction.FORWARDS);
                continue;
            }
            anim.setDirection(Direction.BACKWARDS);
        }
    }

    @Override
    public void drawDraggable(class_332 context) {
        if (!this.visible() || CustomHotbar.mc.field_1724 == null) {
            return;
        }
        class_4587 matrix = context.method_51448();
        int screenWidth = window.getScaledWidth();
        int screenHeight = window.getScaledHeight();
        HotbarLayout layout = CustomHotbar.computeLayout(screenWidth, screenHeight);
        float offhandPanelX = layout.offhandPanelX();
        float hotbarPanelX = layout.hotbarPanelX();
        float hotbarPanelY = layout.hotbarPanelY();
        float hotbarPanelWidth = layout.hotbarPanelWidth();
        float panelHeight = layout.panelHeight();
        float offhandPanelWidth = 26.0f;
        float offhandPanelY = hotbarPanelY;
        this.setX((int)offhandPanelX);
        this.setY((int)hotbarPanelY);
        this.setWidth((int)(hotbarPanelX + hotbarPanelWidth - offhandPanelX));
        this.setHeight((int)panelHeight);
        this.clampToScreen();
        this.drawExperienceLevel(matrix, screenWidth, screenHeight);
        this.drawPanelBackground(matrix, offhandPanelX, offhandPanelY, offhandPanelWidth, panelHeight);
        this.drawOffhand(context, matrix, offhandPanelX + 3.0f, offhandPanelY + 3.0f, 20.0f);
        this.drawPanelBackground(matrix, hotbarPanelX, hotbarPanelY, hotbarPanelWidth, panelHeight);
        int selectedSlot = CustomHotbar.mc.field_1724.method_31548().field_7545;
        float rowY = hotbarPanelY + 3.0f;
        float hotbarStartX = hotbarPanelX + 3.0f;
        FontRenderer slotNumberFont = Fonts.getSize(20, Fonts.Type.SuisseIntlMedium);
        for (int i = 0; i < 9; ++i) {
            float animValue = this.slotAnimations.get(i).getOutput().floatValue();
            float slotX = hotbarStartX + (float)i * 22.0f;
            float slotY = rowY;
            boolean isSelected = i == selectedSlot;
            float offset = animValue * -2.0f;
            matrix.method_22903();
            matrix.method_46416(slotX + 10.0f, (slotY += offset) + 10.0f, 0.0f);
            float scale = 1.0f + animValue * 0.1f;
            matrix.method_22905(scale, scale, 1.0f);
            matrix.method_46416(-(slotX + 10.0f), -(slotY + 10.0f), 0.0f);
            if (isSelected) {
                int alpha = (int)(255.0f * (0.3f + animValue * 0.7f));
                rectangle.render(ShapeProperties.create(matrix, slotX, slotY, 20.0, 20.0).round(4.0f).thickness(0.5f).outlineColor(ColorAssist.multAlpha(ColorAssist.getHudColor(), alpha)).softness(2.5f).color(ColorAssist.multAlpha(ThemeManager.BackgroundSettings.getColor(), 0.5f + animValue * 0.5f)).build());
            } else {
                rectangle.render(ShapeProperties.create(matrix, slotX, slotY, 20.0, 20.0).round(4.0f).color(ThemeManager.BackgroundSettings.getColor()).build());
            }
            class_1799 stack = CustomHotbar.mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
                Render2D.defaultDrawStack(context, stack, slotX + 2.0f, slotY + 2.0f, true, true, 1.0f);
            } else {
                String slotNumber = String.valueOf(i + 1);
                int textAlpha = isSelected ? 200 : 80;
                float slotCenterX = slotX + 10.0f;
                slotNumberFont.drawCenteredBoth(matrix, slotNumber, slotCenterX, slotY, 20.0f, new Color(255, 255, 255, textAlpha).getRGB());
            }
            matrix.method_22909();
        }
    }

    private void drawPanelBackground(class_4587 matrix, float x, float y, float width, float height) {
        Hud.getInstance();
        if (Hud.blur.isValue() && !KawaseBlur.INSTANCE.fbos.isEmpty()) {
            Render2D.rectangleWithMask(matrix.method_23760().method_23761(), x, y, width, height, 6.0f, 0, KawaseBlur.INSTANCE.fbos.getFirst().method_30277());
        }
        rectangle.render(ShapeProperties.create(matrix, x, y, width, height).round(6.0f).softness(2.5f).color(new Color(0, 0, 0, 210).getRGB()).build());
    }

    private void drawOffhand(class_332 context, class_4587 matrix, float slotX, float slotY, float slotSize) {
        rectangle.render(ShapeProperties.create(matrix, slotX, slotY, slotSize, slotSize).round(4.0f).thickness(0.5f).outlineColor(ColorAssist.multAlpha(ColorAssist.getHudColor(), 120.0f)).softness(2.5f).color(ThemeManager.BackgroundSettings.getColor()).build());
        class_1799 offhand = CustomHotbar.mc.field_1724.method_6079();
        if (!offhand.method_7960()) {
            Render2D.defaultDrawStack(context, offhand, slotX + 2.0f, slotY + 2.0f, true, true, 1.0f);
        } else {
            FontRenderer iconFont = Fonts.getSize(14, Fonts.Type.SuisseIntlMedium);
            String label = "L";
            iconFont.drawCenteredBoth(matrix, label, slotX + slotSize / 2.0f, slotY, slotSize, new Color(255, 255, 255, 70).getRGB());
        }
    }

    private void drawExperienceLevel(class_4587 matrix, int screenWidth, int screenHeight) {
        int xpLevel = CustomHotbar.mc.field_1724.field_7520;
        if (xpLevel <= 0) {
            return;
        }
        FontRenderer levelFont = Fonts.getSize(16, Fonts.Type.SuisseIntlMedium);
        String levelText = String.valueOf(xpLevel);
        float levelBoxY = (float)screenHeight - 39.0f;
        levelFont.drawCenteredBoth(matrix, levelText, (float)screenWidth / 2.0f, levelBoxY, 9.0f, new Color(126, 252, 80).getRGB());
    }

    public record HotbarLayout(float offhandPanelX, float hotbarPanelX, float hotbarPanelY, float hotbarPanelWidth, float panelHeight) {
    }
}
