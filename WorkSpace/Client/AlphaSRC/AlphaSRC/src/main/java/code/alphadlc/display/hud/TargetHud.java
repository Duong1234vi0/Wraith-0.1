/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10042
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_239
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_3966
 *  net.minecraft.class_4587
 *  net.minecraft.class_897
 *  net.minecraft.class_922
 */
package code.alphadlc.display.hud;

import code.alphadlc.AlphaDLC;
import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.common.animation.implement.EaseOut;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.combat.ProjectHelper;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.client.packet.network.ServerUtil;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.HudShine;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import code.alphadlc.utils.display.scissor.ScissorAssist;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.math.time.StopWatch;
import code.alphadlc.utils.theme.ThemeManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Locale;
import net.minecraft.class_10042;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_239;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_3966;
import net.minecraft.class_4587;
import net.minecraft.class_897;
import net.minecraft.class_922;

public class TargetHud
extends AbstractDraggable {
    private static TargetHud instance;
    private final Animation healthAnimation;
    private final Animation absorptionAnimation;
    private final Animation toggleAnimation;
    private final Animation faceAlphaAnimation;
    private final Animation heartColorAnimation;
    private final StopWatch stopWatch = new StopWatch();
    private class_1309 target;
    private class_2960 cachedFaceTexture;
    private float health;
    private float absorption;
    private float smoothHealthFraction = 0.0f;
    private float smoothAbsorptionValue = 0.0f;
    private boolean showOnHover = false;
    private final FontRenderer nameFont = Fonts.getSize(16, Fonts.Type.SuisseIntlMedium);
    private final FontRenderer hpFont = Fonts.getSize(15, Fonts.Type.SuisseIntlMedium);
    private final FontRenderer iconFont = Fonts.getSize(16, Fonts.Type.ALPHADLC_ICONS);
    private static final class_1304[] ARMOR_SLOTS;
    private static final class_1304[] HAND_SLOTS;

    public static TargetHud getInstance() {
        if (instance == null) {
            instance = new TargetHud();
        }
        return instance;
    }

    public TargetHud() {
        super("Target Hud", 10, 120, 100, 32, true);
        instance = this;
        this.scaleAnimation = new EaseOut().setValue(1.0).setMs(150);
        this.healthAnimation = new Decelerate().setMs(250).setValue(1.0);
        this.absorptionAnimation = new Decelerate().setMs(250).setValue(1.0);
        this.toggleAnimation = new Decelerate().setMs(250).setValue(1.0);
        this.faceAlphaAnimation = new Decelerate().setMs(125).setValue(1.0);
        this.heartColorAnimation = new Decelerate().setMs(1500).setValue(0.0);
    }

    public boolean isShowOnHover() {
        return this.showOnHover;
    }

    public void setShowOnHover(boolean showOnHover) {
        this.showOnHover = showOnHover;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean canDraw(Hud hud, AbstractDraggable draggable) {
        if (!hud.isState()) return false;
        if (!Hud.interfaceSettings.isSelected("Target Hud")) return false;
        if (!this.visible()) return false;
        return true;
    }

    @Override
    public boolean visible() {
        if (Hud.getInstance() == null) {
            return false;
        }
        Hud.getInstance();
        boolean isActive = Hud.interfaceSettings.isSelected("Target Hud") && Hud.getInstance().state;
        return isActive && this.toggleAnimation.getOutput().floatValue() > 0.01f;
    }

    @Override
    public void tick() {
        Hud.getInstance();
        if (!Hud.interfaceSettings.isSelected("Target Hud") || !Hud.getInstance().state) {
            return;
        }
        if (this.heartColorAnimation.isFinished(Direction.FORWARDS)) {
            this.heartColorAnimation.setDirection(Direction.BACKWARDS);
        } else if (this.heartColorAnimation.isFinished(Direction.BACKWARDS)) {
            this.heartColorAnimation.setDirection(Direction.FORWARDS);
        }
        class_1309 nextTarget = this.resolveTarget();
        if (nextTarget != null) {
            if (this.target != nextTarget) {
                this.resetHealthState(nextTarget);
            }
            this.target = nextTarget;
            this.stopWatch.reset();
            this.startAnimation();
            this.toggleAnimation.setDirection(Direction.FORWARDS);
            this.faceAlphaAnimation.setDirection(Direction.FORWARDS);
        } else {
            this.toggleAnimation.setDirection(Direction.BACKWARDS);
            this.faceAlphaAnimation.setDirection(Direction.BACKWARDS);
            if (this.toggleAnimation.isFinished(Direction.BACKWARDS)) {
                this.target = null;
                this.cachedFaceTexture = null;
            }
        }
        if (this.target != null) {
            this.updateCachedFaceTexture();
        }
    }

    private class_1309 resolveTarget() {
        class_1309 lookedAt;
        class_1309 auraTarget = AttackAura.getInstance().getTarget();
        if (auraTarget == null) {
            ProjectHelper projectHelper = ProjectHelper.getInstance();
            class_1309 class_13092 = auraTarget = projectHelper != null ? projectHelper.getHudTarget() : null;
        }
        if (auraTarget != null) {
            return auraTarget;
        }
        if (this.showOnHover && (lookedAt = this.getLookedAtEntity()) != null) {
            return lookedAt;
        }
        if (PlayerInteractionHelper.isChat(TargetHud.mc.field_1755) && TargetHud.mc.field_1724 != null) {
            return TargetHud.mc.field_1724;
        }
        return null;
    }

    private class_1309 getLookedAtEntity() {
        class_1309 living;
        class_3966 hit;
        if (mc == null || TargetHud.mc.field_1765 == null) {
            return null;
        }
        class_239 class_2392 = TargetHud.mc.field_1765;
        if (class_2392 instanceof class_3966 && (class_2392 = (hit = (class_3966)class_2392).method_17782()) instanceof class_1309 && (living = (class_1309)class_2392) != TargetHud.mc.field_1724 && living.method_5805()) {
            return living;
        }
        return null;
    }

    private void resetHealthState(class_1309 entity) {
        float maxHp = ServerUtil.getMaxHealth(entity);
        float actualHp = ServerUtil.getHealthValue(entity);
        this.smoothHealthFraction = maxHp > 0.0f ? class_3532.method_15363((float)(actualHp / maxHp), (float)0.0f, (float)1.0f) : 0.0f;
        this.smoothAbsorptionValue = class_3532.method_15363((float)entity.method_6067(), (float)0.0f, (float)7.1f);
    }

    private void updateHealthAnimations(class_1309 entity) {
        float maxHp = ServerUtil.getMaxHealth(entity);
        if (maxHp <= 0.0f) {
            return;
        }
        float targetFraction = entity.method_5805() ? class_3532.method_15363((float)(ServerUtil.getHealthValue(entity) / maxHp), (float)0.0f, (float)1.0f) : 0.0f;
        this.smoothHealthFraction = Calculate.interpolateSmooth(8.0, this.smoothHealthFraction, targetFraction);
        float targetAbsorption = class_3532.method_15363((float)entity.method_6067(), (float)0.0f, (float)7.1f);
        this.smoothAbsorptionValue = Calculate.interpolateSmooth(8.0, this.smoothAbsorptionValue, targetAbsorption);
    }

    private void updateCachedFaceTexture() {
        class_2960 texture;
        class_1309 entity = this.target;
        if (entity == null) {
            return;
        }
        class_897 baseRenderer = mc.method_1561().method_3953((class_1297)entity);
        if (!(baseRenderer instanceof class_922)) {
            return;
        }
        class_922 renderer = (class_922)baseRenderer;
        class_10042 state = (class_10042)renderer.method_62425((class_1297)entity, tickCounter.method_60637(false));
        if (state != null && (texture = renderer.method_3885(state)) != null) {
            this.cachedFaceTexture = texture;
        }
    }

    @Override
    public void drawDraggable(class_332 context) {
        float alpha;
        Hud.getInstance();
        if (Hud.interfaceSettings.isSelected("Target Hud") && Hud.getInstance().state && (alpha = this.toggleAnimation.getOutput().floatValue()) > 0.01f && this.target != null) {
            class_4587 matrix = context.method_51448();
            this.renderTargetHud(context, matrix, this.target, alpha);
        }
    }

    private void renderTargetHud(class_332 context, class_4587 matrix, class_1309 target, float animation) {
        this.updateHealthAnimations(target);
        RenderSystem.disableDepthTest();
        matrix.method_22903();
        float posX = this.getX();
        float posY = this.getY();
        float width = 100.0f;
        float height = 36.0f;
        float visualWidth = 89.0f;
        Hud.getInstance();
        if (Hud.blur.isValue() && !KawaseBlur.INSTANCE.fbos.isEmpty()) {
            Render2D.rectangleWithMask(matrix.method_23760().method_23761(), posX, posY, visualWidth, height, 8.0f, 0, KawaseBlur.INSTANCE.fbos.getFirst().method_30277());
        }
        rectangle.render(ShapeProperties.create(matrix, posX, posY, visualWidth, height).round(8.0f).softness(2.5f).color(ColorAssist.multAlpha(new Color(0, 0, 0, 210).getRGB(), animation)).build());
        float maxHp = ServerUtil.getMaxHealth(target);
        float displayHp = this.smoothHealthFraction * maxHp;
        this.health = this.smoothHealthFraction * 100.0f;
        float rawAbsorption = class_3532.method_15363((float)target.method_6067(), (float)0.0f, (float)7.1f);
        boolean hasAbsorptionEffect = target.method_6059(class_1294.field_5898) && rawAbsorption > 0.0f;
        float targetAbsorptionPercent = hasAbsorptionEffect && maxHp > 0.0f ? class_3532.method_15363((float)(this.smoothAbsorptionValue / maxHp * 100.0f), (float)0.0f, (float)100.0f) : 0.0f;
        this.absorption = class_3532.method_15363((float)targetAbsorptionPercent, (float)0.0f, (float)100.0f);
        float headX = posX + 9.0f;
        float headY = posY + 3.6f;
        float headSize = 20.0f;
        float contentX = headX + headSize - 0.6f;
        float contentY = posY + 5.0f;
        FontRenderer nameFont = this.nameFont;
        String targetName = target.method_5820();
        float maxNameWidth = visualWidth - (contentX - posX) - 4.0f;
        float barPadding = 4.0f;
        float barX = posX + barPadding;
        float barY = posY + height - 7.5f;
        float barWidth = visualWidth - barPadding * 2.0f;
        float barHeight = 3.5f;
        rectangle.render(ShapeProperties.create(matrix, barX, barY, barWidth, barHeight).round(1.75f).color(ColorAssist.multAlpha(ThemeManager.BackgroundSettings.getColor(), animation)).build());
        float healthWidth = this.health / 100.0f * barWidth;
        if (healthWidth > 1.0f) {
            Color gradColor1 = new Color(ColorAssist.getHudColor() >> 16 & 0xFF, ColorAssist.getHudColor() >> 8 & 0xFF, ColorAssist.getHudColor() & 0xFF, (int)(255.0f * animation));
            Color gradColor2 = new Color(ColorAssist.getHudColor2() >> 16 & 0xFF, ColorAssist.getHudColor2() >> 8 & 0xFF, ColorAssist.getHudColor2() & 0xFF, (int)(255.0f * animation));
            Color gradColor2Dark = new Color(Math.max(0, (ColorAssist.getHudColor2() >> 16 & 0xFF) * 8 / 10), Math.max(0, (ColorAssist.getHudColor2() >> 8 & 0xFF) * 8 / 10), Math.max(0, (ColorAssist.getHudColor2() & 0xFF) * 8 / 10), (int)(255.0f * animation));
            rectangle.render(ShapeProperties.create(matrix, barX, barY, healthWidth, barHeight).round(1.75f).color(gradColor2Dark.getRGB(), gradColor2.getRGB(), gradColor2.getRGB(), gradColor1.getRGB()).build());
        }
        if (hasAbsorptionEffect && this.absorption > 0.01f) {
            float absorptionWidth = this.absorption / 100.0f * barWidth;
            if (absorptionWidth > 1.0f) {
                float absorptionStartX = barX + healthWidth;
                float availableRight = Math.max(0.0f, barWidth - healthWidth);
                float drawWidth = Math.min(absorptionWidth, availableRight);
                if (drawWidth <= 1.0f && healthWidth > 1.0f) {
                    drawWidth = Math.min(absorptionWidth, healthWidth);
                    absorptionStartX = barX + Math.max(0.0f, healthWidth - drawWidth);
                }
                if (drawWidth > 1.0f) {
                    rectangle.render(ShapeProperties.create(matrix, absorptionStartX, barY, drawWidth, barHeight).round(1.75f).color(new Color(255, 215, 0, (int)(200.0f * animation)).getRGB(), new Color(255, 215, 0, (int)(200.0f * animation)).getRGB(), new Color(255, 165, 0, (int)(200.0f * animation)).getRGB(), new Color(255, 165, 0, (int)(200.0f * animation)).getRGB()).build());
                }
            }
        } else {
            this.smoothAbsorptionValue = 0.0f;
            this.absorption = 0.0f;
        }
        Rectangle.flushPending();
        ScissorAssist scissorManager = AlphaDLC.getInstance().getScissorManager();
        scissorManager.push(matrix.method_23760().method_23761(), contentX, contentY - 1.0f, maxNameWidth, 18.0f);
        float nameCharSpacing = -0.5f;
        nameFont.drawStringWithSpacing(matrix, targetName, contentX, contentY + 0.5f, ColorAssist.multAlpha(ThemeManager.textColor.getColor(), animation), nameCharSpacing);
        scissorManager.pop();
        String hpValue = String.format(Locale.ENGLISH, "%.0f", Float.valueOf(displayHp));
        FontRenderer hpFont = this.hpFont;
        FontRenderer iconFont = this.iconFont;
        float hpTextX = (float)Math.round(contentX) - 0.1f;
        float textY = contentY + 11.5f;
        float hpTextH = hpFont.getStringHeight("0");
        float iconH = iconFont.getStringHeight("r");
        float iconY = textY + (hpTextH - iconH) / 2.0f + 0.2f;
        iconFont.drawRainbowString(matrix, "r", hpTextX, iconY, ColorAssist.multAlpha(ColorAssist.getHudColor(), animation), ColorAssist.multAlpha(ColorAssist.getHudColor2(), animation));
        float iconWidth = iconFont.getStringWidth("r");
        float currentX = Math.round(hpTextX + iconWidth + 0.2f);
        int hpColor = ColorAssist.multAlpha(ThemeManager.textColor.getColor(), animation);
        hpFont.drawString(matrix, hpValue, currentX, textY, hpColor);
        currentX += hpFont.getStringWidth(hpValue);
        if (rawAbsorption > 0.0f) {
            String absValueNum = String.format(Locale.ENGLISH, "%.0f", Float.valueOf(this.smoothAbsorptionValue));
            String absTextPart = " (" + absValueNum + ")";
            int absColor = ColorAssist.multAlpha(new Color(255, 215, 0).getRGB(), animation);
            hpFont.drawString(matrix, absTextPart, currentX, textY, absColor);
        }
        this.drawFace(context, matrix, animation);
        float equipmentGap = 1.5f;
        float equipmentY = posY + height + equipmentGap;
        float equipmentHeight = this.renderEquipmentRow(context, matrix, target, posX, equipmentY, visualWidth, animation);
        float totalHeight = height + equipmentGap + equipmentHeight;
        this.setWidth((int)width);
        this.setHeight((int)Math.ceil(totalHeight));
        matrix.method_22909();
        RenderSystem.enableDepthTest();
    }

    private float renderEquipmentRow(class_332 context, class_4587 matrix, class_1309 target, float anchorX, float panelY, float hudVisualWidth, float animation) {
        float panelsGap = 8.0f;
        float armorWidth = this.measureEquipmentPanelWidth(ARMOR_SLOTS.length);
        float handsWidth = this.measureEquipmentPanelWidth(HAND_SLOTS.length);
        float totalWidth = armorWidth + panelsGap + handsWidth;
        float rowX = anchorX + (hudVisualWidth - totalWidth) / 2.0f;
        float panelHeight = this.renderEquipmentSlotsPanel(context, matrix, target, ARMOR_SLOTS, rowX, panelY, animation);
        this.renderEquipmentSlotsPanel(context, matrix, target, HAND_SLOTS, rowX + armorWidth + panelsGap, panelY, animation);
        return panelHeight;
    }

    private float measureEquipmentPanelWidth(int slotCount) {
        float slotSize = 12.0f;
        float spacing = 1.5f;
        float padding = 2.0f;
        return (float)slotCount * slotSize + (float)(slotCount - 1) * spacing + padding * 2.0f;
    }

    private float renderEquipmentSlotsPanel(class_332 context, class_4587 matrix, class_1309 target, class_1304[] slots, float panelX, float panelY, float animation) {
        float slotSize = 12.0f;
        float spacing = 1.5f;
        float padding = 2.0f;
        float durabilityBarHeight = 1.0f;
        float barSpacing = 1.0f;
        float itemScale = 0.7f;
        float panelRound = 4.0f;
        float contentWidth = (float)slots.length * slotSize + (float)(slots.length - 1) * spacing;
        float panelWidth = contentWidth + padding * 2.0f;
        float panelHeight = padding * 2.0f + slotSize + barSpacing + durabilityBarHeight;
        Hud.getInstance();
        if (Hud.blur.isValue() && !KawaseBlur.INSTANCE.fbos.isEmpty()) {
            Render2D.rectangleWithMask(matrix.method_23760().method_23761(), panelX, panelY, panelWidth, panelHeight, panelRound, 0, KawaseBlur.INSTANCE.fbos.getFirst().method_30277());
        }
        rectangle.render(ShapeProperties.create(matrix, panelX, panelY, panelWidth, panelHeight).round(panelRound).softness(2.5f).color(ColorAssist.multAlpha(new Color(0, 0, 0, 210).getRGB(), animation)).build());
        ArrayList<float[]> emptySlots = new ArrayList<float[]>();
        for (int i = 0; i < slots.length; ++i) {
            float slotX = panelX + padding + (float)i * (slotSize + spacing);
            float slotY = panelY + padding;
            rectangle.render(ShapeProperties.create(matrix, slotX, slotY, slotSize, slotSize).round(2.0f).color(ColorAssist.multAlpha(ThemeManager.BackgroundSettings.getColor(), animation)).build());
            class_1799 stack = target.method_6118(slots[i]);
            if (stack.method_7960()) {
                emptySlots.add(new float[]{slotX, slotY});
                continue;
            }
            float itemVisualSize = 16.0f * itemScale;
            float itemOffset = (slotSize - itemVisualSize) / 2.0f - 1.0f;
            float itemX = slotX + itemOffset;
            float itemY = slotY + itemOffset;
            Render2D.defaultDrawStack(context, stack, itemX, itemY, false, false, itemScale);
            if (!stack.method_7963()) continue;
            int maxDurability = stack.method_7936();
            int currentDurability = maxDurability - stack.method_7919();
            float durabilityPercent = maxDurability > 0 ? (float)currentDurability / (float)maxDurability : 0.0f;
            float barX = slotX + 1.0f;
            float barY = slotY + slotSize + barSpacing;
            float barWidth = slotSize - 2.0f;
            rectangle.render(ShapeProperties.create(matrix, barX, barY, barWidth, durabilityBarHeight).round(0.5f).color(ColorAssist.multAlpha(new Color(0, 0, 0, 210).getRGB(), animation)).build());
            Color barColor = this.getDurabilityColor(durabilityPercent);
            float filledWidth = barWidth * durabilityPercent;
            if (!(filledWidth > 0.5f)) continue;
            rectangle.render(ShapeProperties.create(matrix, barX, barY, filledWidth, durabilityBarHeight).round(0.5f).color(ColorAssist.multAlpha(barColor.getRGB(), animation)).build());
        }
        Rectangle.flushPending();
        for (float[] emptySlot : emptySlots) {
            this.drawEmptySlotCross(matrix, emptySlot[0], emptySlot[1], slotSize, animation);
        }
        Hud.getInstance();
        if (Hud.shine.isValue() && Hud.shineElements.isSelected("Target Hud")) {
            HudShine.render(matrix, panelX, panelY, panelWidth, panelHeight, panelRound, Hud.shineSize.getValue(), Hud.shineSpeed.getValue(), Hud.shineBrightness.getValue());
        }
        return panelHeight;
    }

    private void drawEmptySlotCross(class_4587 matrix, float slotX, float slotY, float slotSize, float animation) {
        float margin = 3.0f;
        float x1 = slotX + margin;
        float y1 = slotY + margin;
        float x2 = slotX + slotSize - margin;
        float y2 = slotY + slotSize - margin;
        int color = ColorAssist.multAlpha(new Color(150, 150, 155, 200).getRGB(), animation);
        float thickness = 1.25f;
        Render2D.drawLine(matrix, x1, y1, x2, y2, thickness, color);
        Render2D.drawLine(matrix, x1, y2, x2, y1, thickness, color);
    }

    private Color getDurabilityColor(float durabilityPercent) {
        if (durabilityPercent > 0.5f) {
            return new Color(76, 187, 23);
        }
        if (durabilityPercent > 0.25f) {
            return new Color(255, 187, 40);
        }
        return new Color(255, 51, 51);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void drawFace(class_332 context, class_4587 matrix, float animation) {
        if (this.target == null || this.cachedFaceTexture == null) {
            return;
        }
        float faceAlpha = animation * this.faceAlphaAnimation.getOutput().floatValue();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)class_3532.method_15363((float)faceAlpha, (float)0.0f, (float)1.0f));
        try {
            float headX = this.getX() + 4;
            float headY = (float)this.getY() + 4.1f;
            float headSize = 20.0f;
            float headRound = 6.0f;
            Render2D.drawTexture(context, this.cachedFaceTexture, headX, headY, headSize, headRound, 8, 8, 64, ColorAssist.getRect(1.0f), ColorAssist.multRed(-1, 1.0f + (float)this.target.field_6235 / 4.0f));
        }
        finally {
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderSystem.defaultBlendFunc();
        }
    }

    static {
        ARMOR_SLOTS = new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166};
        HAND_SLOTS = new class_1304[]{class_1304.field_6173, class_1304.field_6171};
    }
}
