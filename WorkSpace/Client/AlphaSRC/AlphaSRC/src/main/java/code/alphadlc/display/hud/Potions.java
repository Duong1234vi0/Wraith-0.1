/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1291
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_1309
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2678
 *  net.minecraft.class_2718
 *  net.minecraft.class_2724
 *  net.minecraft.class_2783
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 *  net.minecraft.class_6880
 *  net.minecraft.class_7923
 */
package code.alphadlc.display.hud;

import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.common.animation.implement.EaseOut;
import code.alphadlc.display.hud.HudListPanel;
import code.alphadlc.display.hud.Notifications;
import code.alphadlc.display.hud.TimerTextAnimator;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.BackgroundIcons;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.theme.ThemeManager;
import java.awt.Color;
import java.lang.runtime.SwitchBootstraps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2678;
import net.minecraft.class_2718;
import net.minecraft.class_2724;
import net.minecraft.class_2783;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_6880;
import net.minecraft.class_7923;

public class Potions
extends AbstractDraggable {
    private final List<Potion> list = new ArrayList<Potion>();
    private final List<Potion> renderSnapshot = new ArrayList<Potion>();
    private static final class_6880<class_1291>[] NEGATIVE_EFFECTS = new class_6880[]{class_1294.field_5899, class_1294.field_5920, class_1294.field_5916, class_1294.field_5919, class_1294.field_5903, class_1294.field_5909, class_1294.field_5901, class_1294.field_5921, class_1294.field_5911, class_1294.field_5902, class_1294.field_5908, class_1294.field_16595};
    private long lastEffectChange = 0L;
    private class_6880<class_1291> currentRandomEffect = class_1294.field_5904;
    private static List<class_6880<class_1291>> cachedAllEffects;
    private static final Random RANDOM;
    private final FontRenderer fontMedium = Fonts.getSize(13, Fonts.Type.SuisseIntlMedium);
    private final FontRenderer fontHeader = Fonts.getSize(16, Fonts.Type.DEFAULT);
    private final FontRenderer fontLevel = Fonts.getSize(11, Fonts.Type.SuisseIntlMedium);
    private final FontRenderer fontDuration = Fonts.getSize(13, Fonts.Type.DEFAULT);
    private final FontRenderer fontPotionName = Fonts.getSize(14, Fonts.Type.SuisseIntlMedium);
    private final Map<String, class_1293> activeEffects = new HashMap<String, class_1293>();
    private final Map<String, Boolean> warnedEffects = new HashMap<String, Boolean>();

    public Potions() {
        super("Potions", 200, 40, 90, 23, true);
        this.scaleAnimation = new EaseOut().setValue(1.0).setMs(100);
    }

    @Override
    public boolean visible() {
        Hud.getInstance();
        if (!Hud.interfaceSettings.isSelected("Potions") || !Hud.getInstance().state) {
            return false;
        }
        return !this.list.isEmpty() || PlayerInteractionHelper.isChat(Potions.mc.field_1755);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            FontRenderer iconFont = Fonts.getSize(15, Fonts.Type.ALPHADLC_ICONS);
            float pX = (float)(this.getX() + this.getWidth()) - iconFont.getStringWidth("p") - 4.0f;
            float pY = (float)this.getY() + 7.0f;
            float pWidth = iconFont.getStringWidth("p");
            float pHeight = iconFont.getStringHeight("p");
            if (mouseX >= (double)pX && mouseX <= (double)(pX + pWidth) && mouseY >= (double)pY && mouseY <= (double)(pY + pHeight)) {
                Hud.getInstance();
                ArrayList<String> selected = new ArrayList<String>(Hud.interfaceSettings.getSelected());
                selected.remove("Potions");
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
        this.list.removeIf(p -> p.anim.isFinished(Direction.BACKWARDS));
        this.list.forEach(p -> p.effect.method_5585((class_1309)Potions.mc.field_1724, null));
        if (Hud.getInstance().notificationSettings.isSelected("\u0420\u2014\u0420\u00b0\u0420\u0454\u0420\u00b0\u0420\u0405\u0421\u2021\u0420\u0451\u0420\u0406\u0420\u00b0\u0421\u040b\u0421\u2030\u0420\u00b5\u0420\u0458\u0421\u0403\u0421\u040f \u0420\u00b7\u0420\u00b5\u0420\u00bb\u0421\u040a\u0420\u00b5") && !PlayerInteractionHelper.nullCheck()) {
            ArrayList<String> effectsToRemove = new ArrayList<String>();
            for (Map.Entry<String, class_1293> entry : this.activeEffects.entrySet()) {
                String effectKey = entry.getKey();
                class_1293 trackedEffect = entry.getValue();
                boolean stillActive = false;
                if (Potions.mc.field_1724 != null) {
                    for (class_1293 activeEffect : Potions.mc.field_1724.method_6026()) {
                        if (!activeEffect.method_5579().method_55840().equals(trackedEffect.method_5579().method_55840())) continue;
                        stillActive = true;
                        this.activeEffects.put(effectKey, activeEffect);
                        int remainingTicks = activeEffect.method_5584();
                        int warningTicks = 200;
                        boolean alreadyWarned = this.warnedEffects.getOrDefault(effectKey, false);
                        if (remainingTicks <= warningTicks && remainingTicks > 0 && !alreadyWarned) {
                            this.sendPotionEndingSoonNotification(activeEffect);
                            this.warnedEffects.put(effectKey, true);
                        }
                        if (remainingTicks <= warningTicks) break;
                        this.warnedEffects.remove(effectKey);
                        break;
                    }
                }
                if (stillActive) continue;
                this.sendPotionEndedNotification(trackedEffect);
                effectsToRemove.add(effectKey);
                this.warnedEffects.remove(effectKey);
            }
            effectsToRemove.forEach(this.activeEffects::remove);
        }
        if (this.list.isEmpty() && PlayerInteractionHelper.isChat(Potions.mc.field_1755) && (currentTime = System.currentTimeMillis()) - this.lastEffectChange >= 1000L) {
            if (cachedAllEffects == null) {
                cachedAllEffects = new ArrayList<class_6880<class_1291>>();
                for (class_2960 id : class_7923.field_41174.method_10235()) {
                    class_7923.field_41174.method_10223(id).ifPresent(cachedAllEffects::add);
                }
            }
            if (!cachedAllEffects.isEmpty()) {
                this.currentRandomEffect = cachedAllEffects.get(RANDOM.nextInt(cachedAllEffects.size()));
                this.lastEffectChange = currentTime;
            }
        }
    }

    @Override
    public void packet(PacketEvent e) {
        class_2596<?> class_25962 = e.getPacket();
        Objects.requireNonNull(class_25962);
        class_2596<?> class_25963 = class_25962;
        int n = 0;
        switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_2783.class, class_2718.class, class_2724.class, class_2678.class}, class_25963, n)) {
            case 0: {
                class_2783 effect = (class_2783)class_25963;
                if (PlayerInteractionHelper.nullCheck() || effect.method_11943() != Objects.requireNonNull(Potions.mc.field_1724).method_5628()) break;
                class_6880 effectId = effect.method_11946();
                String effectKey = effectId.method_55840();
                boolean isNewEffect = !this.activeEffects.containsKey(effectKey);
                this.list.stream().filter(p -> p.effect.method_5579().method_55840().equals(effectKey)).forEach(s -> s.anim.setDirection(Direction.BACKWARDS));
                class_1293 newEffect = new class_1293(effectId, effect.method_11944(), effect.method_11945(), effect.method_11950(), effect.method_11949(), effect.method_11942());
                this.list.add(new Potion(newEffect, new Decelerate().setMs(150).setValue(1.0)));
                this.activeEffects.put(effectKey, newEffect);
                if (!isNewEffect || !Hud.getInstance().notificationSettings.isSelected("\u0420\u045f\u0420\u0455\u0420\u00bb\u0421\u0453\u0421\u2021\u0420\u00b5\u0420\u0405\u0420\u0405\u0420\u0455\u0420\u0458 \u0420\u00b7\u0420\u00b5\u0420\u00bb\u0421\u040a\u0420\u00b5")) break;
                this.sendPotionReceivedNotification(newEffect);
                break;
            }
            case 1: {
                class_2718 effect = (class_2718)class_25963;
                String effectKey = effect.comp_2176().method_55840();
                this.list.stream().filter(s -> s.effect.method_5579().method_55840().equals(effectKey)).forEach(s -> s.anim.setDirection(Direction.BACKWARDS));
                class_1293 removedEffect = this.activeEffects.remove(effectKey);
                if (removedEffect != null && Hud.getInstance().notificationSettings.isSelected("\u0420\u2014\u0420\u00b0\u0420\u0454\u0420\u00b0\u0420\u0405\u0421\u2021\u0420\u0451\u0420\u0406\u0420\u00b0\u0421\u040b\u0421\u2030\u0420\u00b5\u0420\u0458\u0421\u0403\u0421\u040f \u0420\u00b7\u0420\u00b5\u0420\u00bb\u0421\u040a\u0420\u00b5")) {
                    this.sendPotionEndedNotification(removedEffect);
                }
                this.warnedEffects.remove(effectKey);
                break;
            }
            case 2: {
                class_2724 p2 = (class_2724)class_25963;
                this.list.clear();
                this.activeEffects.clear();
                this.warnedEffects.clear();
                break;
            }
            case 3: {
                class_2678 p3 = (class_2678)class_25963;
                this.list.clear();
                this.activeEffects.clear();
                this.warnedEffects.clear();
                break;
            }
        }
    }

    @Override
    public void drawDraggable(class_332 context) {
        this.drawMode1(context);
    }

    private void drawMode1(class_332 context) {
        class_4587 matrix = context.method_51448();
        this.renderSnapshot.clear();
        this.renderSnapshot.addAll(this.list);
        FontRenderer fontMedium = this.fontMedium;
        FontRenderer font = this.fontHeader;
        FontRenderer fontLevel = this.fontLevel;
        FontRenderer fontDuration = this.fontDuration;
        FontRenderer fontPotionName = this.fontPotionName;
        float posX = this.getX();
        float posY = this.getY();
        float maxElementsWidth = HudListPanel.headerMinWidth(font, "Potions");
        float headerHeight = 21.0f;
        float potionHeight = 11.0f;
        boolean isFound = false;
        for (Potion potion : this.renderSnapshot) {
            float elementsWidth;
            if (!(potion.anim.getOutput().floatValue() > 0.0f)) continue;
            isFound = true;
            class_1293 effect = potion.effect;
            String name = ((class_1291)effect.method_5579().comp_349()).method_5560().getString();
            String duration = this.getDuration(effect);
            int amplifier = effect.method_5578();
            float levelWidth = 0.0f;
            if (amplifier > 0) {
                levelWidth = fontLevel.getStringWidth(" LVL " + (amplifier + 1)) + 3.0f;
            }
            if (!((elementsWidth = HudListPanel.rowWidth(fontPotionName, name, levelWidth, fontDuration, duration)) > maxElementsWidth)) continue;
            maxElementsWidth = elementsWidth;
        }
        if (this.list.isEmpty() && PlayerInteractionHelper.isChat(Potions.mc.field_1755)) {
            isFound = true;
            float elementsWidth = HudListPanel.rowWidth(fontPotionName, "Example", 0.0f, fontDuration, "**:**");
            if (elementsWidth > maxElementsWidth) {
                maxElementsWidth = elementsWidth;
            }
        }
        float totalContentHeight = 0.0f;
        for (Potion potion : this.renderSnapshot) {
            float animValue = potion.anim.getOutput().floatValue();
            if (!(animValue > 0.0f)) continue;
            totalContentHeight += potionHeight * animValue;
        }
        if (this.list.isEmpty() && PlayerInteractionHelper.isChat(Potions.mc.field_1755)) {
            totalContentHeight += potionHeight;
        }
        float f = HudListPanel.panelHeight(totalContentHeight);
        float width = maxElementsWidth;
        Hud.getInstance();
        if (Hud.blur.isValue() && !KawaseBlur.INSTANCE.fbos.isEmpty()) {
            Render2D.rectangleWithMask(matrix.method_23760().method_23761(), posX, posY, width, f, 6.0f, 0, KawaseBlur.INSTANCE.fbos.getFirst().method_30277());
        }
        rectangle.render(ShapeProperties.create(matrix, posX, posY, width, f).round(6.0f).softness(2.5f).color(new Color(0, 0, 0, 210).getRGB()).build());
        float headerTextY = HudListPanel.headerTextY(font, posY, "Potions");
        font.drawString(matrix, "Potions", Math.round(posX + 7.0f), Math.round(headerTextY), ThemeManager.textColor.getColor());
        float repBoxW = fontDuration.getStringWidth("0:00") + 6.0f;
        float timerCenterX = posX + width - 6.5f - repBoxW / 2.0f;
        BackgroundIcons.drawHudHeaderIcon(context, BackgroundIcons.POTIONS, font, "Potions", headerTextY, timerCenterX, ColorAssist.getHudColor());
        posY = HudListPanel.contentStartY(posY);
        if (this.list.isEmpty() && PlayerInteractionHelper.isChat(Potions.mc.field_1755)) {
            String name = "Example";
            String duration = "**:**";
            matrix.method_22903();
            matrix.method_46416(posX + width / 2.0f, posY + potionHeight / 2.0f, 0.0f);
            matrix.method_22905(1.0f, 1.0f, 1.0f);
            matrix.method_46416(-(posX + width / 2.0f), -(posY + potionHeight / 2.0f), 0.0f);
            Render2D.drawSprite(matrix, mc.method_18505().method_18663(this.currentRandomEffect), posX + 7.0f, (int)HudListPanel.rowIconY(posY, 9.0f), 9.0f, 9, ThemeManager.textColor.getColor());
            rectangle.render(ShapeProperties.create(matrix, posX + 17.1f, HudListPanel.rowDotY(posY), 2.0, 2.0).round(1.0f).color(new Color(87, 87, 90, 255).getRGB()).build());
            float nameY = HudListPanel.alignedRowTextY(fontPotionName, fontPotionName, posY, name);
            fontPotionName.drawString(matrix, name, Math.round(posX + 22.0f), Math.round(nameY), ThemeManager.textColor.getColor());
            float durationWidth = fontDuration.getStringWidth(duration);
            float durationBoxWidth = durationWidth + 6.0f;
            float durationBoxX = posX + width - 6.5f - durationBoxWidth;
            float durationBoxY = HudListPanel.rowBadgeY(posY, 8.0f);
            rectangle.render(ShapeProperties.create(matrix, durationBoxX, durationBoxY, durationBoxWidth, 8.0).round(2.0f).thickness(1.0f).outlineColor(new Color(33, 33, 33, 255).getRGB()).color(new Color(0, 0, 0, 100).getRGB()).build());
            TimerTextAnimator.draw(fontDuration, matrix, "potions_example_duration", duration, Math.round(durationBoxX + durationBoxWidth / 2.0f - durationWidth / 2.0f + 0.2f), (int)HudListPanel.rowBadgeTextY(fontDuration, posY, 8.0f, duration), ThemeManager.textColor.getColor());
            matrix.method_22909();
            posY += potionHeight;
        } else {
            int timerIndex = 0;
            for (Potion potion : this.renderSnapshot) {
                boolean isLightTheme;
                float animValue = potion.anim.getOutput().floatValue();
                if (!(animValue > 0.0f)) continue;
                class_1293 effect = potion.effect;
                String name = ((class_1291)effect.method_5579().comp_349()).method_5560().getString();
                String duration = this.getDuration(effect);
                int amplifier = effect.method_5578();
                boolean isBadEffect = this.isBadEffect((class_6880<class_1291>)effect.method_5579());
                int textAlpha = 255;
                if (effect.method_5584() <= 200 && effect.method_5584() > 0) {
                    double output = 0.5 + 0.5 * Math.cos(Math.PI * 2 * (double)(System.currentTimeMillis() % 700L) / 700.0);
                    textAlpha = (int)(100.0 + 155.0 * output);
                } else if (effect.method_5584() == 0) {
                    textAlpha = 0;
                }
                int alpha = (int)((float)textAlpha * animValue);
                boolean bl = isLightTheme = ThemeManager.BackgroundGui.getColor() == Color.WHITE.getRGB() || (ThemeManager.BackgroundGui.getColor() >> 16 & 0xFF) > 200;
                Color textColor = isBadEffect ? new Color(255, 85, 75, alpha) : (isLightTheme ? new Color(0, 0, 0, alpha) : new Color(255, 255, 255, alpha));
                int iconColor = ColorAssist.rgba(255, 255, 255, alpha);
                matrix.method_22903();
                matrix.method_46416(posX + width / 2.0f, posY + potionHeight * animValue / 2.0f, 0.0f);
                matrix.method_22905(animValue, animValue, animValue);
                matrix.method_46416(-(posX + width / 2.0f), -(posY + potionHeight * animValue / 2.0f), 0.0f);
                Color gradColor1 = new Color(ColorAssist.getHudColor() >> 16 & 0xFF, ColorAssist.getHudColor() >> 8 & 0xFF, ColorAssist.getHudColor() & 0xFF, alpha);
                Color gradColor2 = new Color(ColorAssist.getHudColor2() >> 16 & 0xFF, ColorAssist.getHudColor2() >> 8 & 0xFF, ColorAssist.getHudColor2() & 0xFF, alpha);
                Render2D.drawSprite(matrix, mc.method_18505().method_18663(effect.method_5579()), posX + 7.0f, (int)HudListPanel.rowIconY(posY, 9.0f), 9.0f, 9, iconColor);
                rectangle.render(ShapeProperties.create(matrix, posX + 17.1f, HudListPanel.rowDotY(posY), 2.0, 2.0).round(1.0f).color(new Color(87, 87, 90, alpha).getRGB()).build());
                float nameX = posX + 22.0f;
                float nameY = HudListPanel.alignedRowTextY(fontPotionName, fontPotionName, posY, name);
                fontPotionName.drawString(matrix, name, nameX, nameY, textColor.getRGB());
                if (amplifier > 0) {
                    String level = " LVL " + (amplifier + 1);
                    float levelX = Math.round(posX + 22.0f + fontPotionName.getStringWidth(name) + 3.0f);
                    Color levelColor = new Color(157, 157, 160, alpha);
                    float levelY = HudListPanel.alignedRowTextY(fontPotionName, fontLevel, posY, name);
                    fontLevel.drawString(matrix, level, levelX, Math.round(levelY), levelColor.getRGB());
                }
                float durationWidth = fontDuration.getStringWidth(duration);
                float durationBoxWidth = durationWidth + 6.0f;
                float durationBoxX = posX + width - 6.5f - durationBoxWidth;
                float durationBoxY = HudListPanel.rowBadgeY(posY, 8.0f);
                rectangle.render(ShapeProperties.create(matrix, durationBoxX, durationBoxY, durationBoxWidth, 8.0f * animValue).round(2.0f).thickness(1.0f).outlineColor(new Color(33, 33, 33, alpha).getRGB()).color(new Color(0, 0, 0, (int)(100.0f * animValue)).getRGB()).build());
                String timerKey = "potions_duration_" + effect.method_5579().method_55840() + "_" + effect.method_5578() + "_" + timerIndex;
                TimerTextAnimator.draw(fontDuration, matrix, timerKey, duration, Math.round(durationBoxX + durationBoxWidth / 2.0f - durationWidth / 2.0f + 0.2f), (int)HudListPanel.rowBadgeTextY(fontDuration, posY, 8.0f, duration), textColor.getRGB());
                ++timerIndex;
                matrix.method_22909();
                posY += potionHeight * animValue;
            }
        }
        this.setWidth((int)width);
        this.setHeight((int)f);
    }

    private String getDuration(class_1293 pe) {
        int var1 = pe.method_5584();
        int mins = var1 / 1200;
        return pe.method_48559() || mins > 60 ? "**:**" : mins + ":" + String.format("%02d", var1 % 1200 / 20);
    }

    private boolean isBadEffect(class_6880<class_1291> effect) {
        for (class_6880<class_1291> negativeEffect : NEGATIVE_EFFECTS) {
            if (effect != negativeEffect) continue;
            return true;
        }
        return false;
    }

    private void sendPotionReceivedNotification(class_1293 effect) {
        String effectName = ((class_1291)effect.method_5579().comp_349()).method_5560().getString();
        String message = effectName + " \u0420\u0457\u0420\u0455\u0420\u00bb\u0421\u0453\u0421\u2021\u0420\u00b5\u0420\u0405!";
        Notifications.getInstance().addList((class_2561)class_2561.method_43470((String)message), 1000, (Object)SoundManager.ENABLE_MODULE, false, null);
        SoundManager.playSound(SoundManager.ENABLE_MODULE);
    }

    private void sendPotionEndedNotification(class_1293 effect) {
        String effectName = ((class_1291)effect.method_5579().comp_349()).method_5560().getString();
        String message = effectName + " \u0420\u00b7\u0420\u00b0\u0420\u0454\u0420\u0455\u0420\u0405\u0421\u2021\u0420\u0451\u0420\u00bb\u0421\u0403\u0421\u040f!";
        Notifications.getInstance().addList((class_2561)class_2561.method_43470((String)message), 1000, (Object)SoundManager.DISABLE_MODULE, false, null);
        SoundManager.playSound(SoundManager.DISABLE_MODULE);
    }

    private void sendPotionEndingSoonNotification(class_1293 effect) {
        String effectName = ((class_1291)effect.method_5579().comp_349()).method_5560().getString();
        String message = effectName + " \u0420\u00b7\u0420\u00b0\u0420\u0454\u0420\u0455\u0420\u0405\u0421\u2021\u0420\u0451\u0421\u201a\u0421\u0403\u0421\u040f \u0421\u2021\u0420\u00b5\u0421\u0402\u0420\u00b5\u0420\u00b7 10 \u0421\u0403\u0420\u00b5\u0420\u0454\u0421\u0453\u0420\u0405\u0420\u0491!";
        Notifications.getInstance().addList((class_2561)class_2561.method_43470((String)message), 1000, null, false, null);
    }

    private void drawMode2(class_332 context) {
        boolean showExamples;
        class_4587 matrix = context.method_51448();
        this.renderSnapshot.clear();
        this.renderSnapshot.addAll(this.list);
        FontRenderer titleFont = Fonts.getSize(14, Fonts.Type.BOLD);
        FontRenderer moduleFont = Fonts.getSize(13, Fonts.Type.BOLD);
        FontRenderer levelFont = Fonts.getSize(11, Fonts.Type.BOLD);
        FontRenderer durationFont = Fonts.getSize(12, Fonts.Type.BOLD);
        float posX = this.getX();
        float posY = this.getY();
        float panelWidth = 95.0f;
        float titleHeight = 24.0f;
        float potionHeight = 16.0f;
        float spacing = -3.5f;
        float titleSpacing = -1.5f;
        int potionCount = 0;
        for (Potion potion : this.renderSnapshot) {
            if (!(potion.anim.getOutput().floatValue() > 0.0f)) continue;
            ++potionCount;
        }
        boolean bl = showExamples = this.list.isEmpty() && PlayerInteractionHelper.isChat(Potions.mc.field_1755);
        if (showExamples) {
            potionCount = 3;
        }
        float contentHeight = (float)potionCount * (potionHeight + spacing) - (potionCount > 0 ? spacing : 0.0f);
        float totalHeight = titleHeight + titleSpacing + 7.0f + contentHeight;
        this.setWidth((int)panelWidth);
        this.setHeight((int)totalHeight);
        rectangle.render(ShapeProperties.create(matrix, posX, posY, panelWidth, totalHeight).round(7.0f).color(ThemeManager.BackgroundGui.getColor()).build());
        rectangle.render(ShapeProperties.create(matrix, posX + 4.5f, posY + 4.5f, panelWidth - 9.0f, titleHeight - 9.0f).round(3.5f).color(ThemeManager.BackgroundSettings.getColor()).build());
        titleFont.drawString(matrix, "Potions", posX + 10.0f, posY + 10.0f, ThemeManager.textColor.getColor());
        Fonts.getSize(19, Fonts.Type.ICONS).drawString(matrix, "D", posX + panelWidth - 19.0f, posY + 9.5f, ThemeManager.textColor.getColor());
        float currentY = posY + titleHeight + titleSpacing;
        rectangle.render(ShapeProperties.create(matrix, posX + 4.5f, currentY, panelWidth - 9.0f, contentHeight).round(3.5f).color(ThemeManager.BackgroundSettings.getColor()).build());
        if (showExamples) {
            String[] exampleNames = new String[]{"Speed", "Strength", "Regen"};
            String[] exampleDurations = new String[]{"5:00", "3:30", "10:00"};
            for (int i = 0; i < 3; ++i) {
                this.drawPotionEntry(matrix, currentY, posX, potionHeight, moduleFont, levelFont, durationFont, exampleNames[i], exampleDurations[i], 0, 1.0f, false, this.currentRandomEffect);
                currentY += potionHeight + spacing;
            }
        } else {
            for (Potion potion : this.renderSnapshot) {
                float animValue = potion.anim.getOutput().floatValue();
                if (!(animValue > 0.0f)) continue;
                class_1293 effect = potion.effect;
                String name = ((class_1291)effect.method_5579().comp_349()).method_5560().getString();
                String duration = this.getDuration(effect);
                int amplifier = effect.method_5578();
                boolean isBad = this.isBadEffect((class_6880<class_1291>)effect.method_5579());
                this.drawPotionEntry(matrix, currentY, posX, potionHeight, moduleFont, levelFont, durationFont, name, duration, amplifier, animValue, isBad, (class_6880<class_1291>)effect.method_5579());
                currentY += (potionHeight + spacing) * animValue;
            }
        }
    }

    private void drawPotionEntry(class_4587 matrix, float currentY, float posX, float potionHeight, FontRenderer moduleFont, FontRenderer levelFont, FontRenderer durationFont, String name, String duration, int amplifier, float animValue, boolean isBad, class_6880<class_1291> effectType) {
        float barWidth = 1.3f;
        float barHeight = 7.0f;
        float barX = posX + 4.5f + 2.8f;
        float barY = currentY + 5.5f;
        int alpha = (int)(255.0f * animValue);
        Color barColor1 = new Color(ColorAssist.getHudColor() >> 16 & 0xFF, ColorAssist.getHudColor() >> 8 & 0xFF, ColorAssist.getHudColor() & 0xFF, alpha);
        Color barColor2 = new Color(ColorAssist.getHudColor2() >> 16 & 0xFF, ColorAssist.getHudColor2() >> 8 & 0xFF, ColorAssist.getHudColor2() & 0xFF, alpha);
        rectangle.render(ShapeProperties.create(matrix, barX, barY, barWidth, barHeight * animValue).round(0.9f).color(barColor1.getRGB(), barColor2.getRGB(), barColor1.getRGB(), barColor2.getRGB()).build());
        float iconX = barX + barWidth + 3.5f;
        float iconY = currentY + potionHeight - 11.0f;
        Render2D.drawSprite(matrix, mc.method_18505().method_18663(effectType), iconX, iconY, 9.0f, 9, ColorAssist.rgba(255, 255, 255, alpha));
        float nameX = iconX + 11.0f;
        float nameY = currentY + potionHeight - 9.0f;
        Color textColor = isBad ? new Color(255, 85, 75, alpha) : new Color(ThemeManager.textColor.getColor() >> 16 & 0xFF, ThemeManager.textColor.getColor() >> 8 & 0xFF, ThemeManager.textColor.getColor() & 0xFF, alpha);
        moduleFont.drawString(matrix, name, nameX, nameY, textColor.getRGB());
        if (amplifier > 0) {
            String level = " LVL " + (amplifier + 1);
            float levelX = nameX + moduleFont.getStringWidth(name) + 3.0f;
            Color levelColor = new Color(157, 157, 160, alpha);
            float levelY = HudListPanel.alignedRowTextY(moduleFont, levelFont, currentY);
            levelFont.drawString(matrix, level, levelX, levelY, levelColor.getRGB());
        }
        float durationWidth = durationFont.getStringWidth(duration);
        float durationX = posX + 95.0f - 6.5f - durationWidth;
        Color durationColor = new Color(150, 150, 150, alpha);
        durationFont.drawString(matrix, duration, durationX, nameY, durationColor.getRGB());
    }

    static {
        RANDOM = new Random();
    }

    private record Potion(class_1293 effect, Animation anim) {
    }
}
