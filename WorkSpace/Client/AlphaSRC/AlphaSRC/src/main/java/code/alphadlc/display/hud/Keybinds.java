/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.hud;

import code.alphadlc.AlphaDLC;
import code.alphadlc.common.animation.implement.EaseOut;
import code.alphadlc.display.hud.HudListPanel;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.ModuleRepository;
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
import code.alphadlc.utils.theme.ThemeManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;

public class Keybinds
extends AbstractDraggable {
    private final List<Module> keysList = new ArrayList<Module>();
    private long lastKeyChange = 0L;
    private String currentRandomKey = "NONE";
    private static final String[] EXAMPLE_KEYS = new String[]{"A", "B", "C", "D", "E"};
    private static final Random RANDOM = new Random();
    private final FontRenderer fontMedium = Fonts.getSize(13, Fonts.Type.SuisseIntlMedium);
    private final FontRenderer fontHeader = Fonts.getSize(16, Fonts.Type.DEFAULT);
    private final FontRenderer fontBind = Fonts.getSize(13, Fonts.Type.DEFAULT);
    private final FontRenderer fontModule = Fonts.getSize(14, Fonts.Type.DEFAULT);
    private final FontRenderer categoryIcon = Fonts.getSize(16, Fonts.Type.ALPHADLC_ICONS);
    private static final float KEYBIND_ROW_ICON_SIZE = 9.0f;

    public Keybinds() {
        super("Keybinds", 300, 40, 80, 23, true);
        this.scaleAnimation = new EaseOut().setValue(1.0).setMs(100);
    }

    @Override
    public boolean visible() {
        Hud.getInstance();
        if (!Hud.interfaceSettings.isSelected("Keybinds") || !Hud.getInstance().state) {
            return false;
        }
        return !this.keysList.isEmpty() || PlayerInteractionHelper.isChat(Keybinds.mc.field_1755);
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
                selected.remove("Keybinds");
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
        AlphaDLC alphaDlc = AlphaDLC.getInstance();
        if (alphaDlc == null) {
            this.keysList.clear();
            return;
        }
        ModuleRepository moduleRepository = alphaDlc.getModuleRepository();
        this.keysList.clear();
        if (moduleRepository != null) {
            for (Module module : moduleRepository.modules()) {
                if (module.getAnimation().getOutput().floatValue() == 0.0f || module.getKey() == -1 || "ClickGui".equals(module.getName())) continue;
                this.keysList.add(module);
            }
        }
        if (this.keysList.isEmpty() && PlayerInteractionHelper.isChat(Keybinds.mc.field_1755) && (currentTime = System.currentTimeMillis()) - this.lastKeyChange >= 1000L) {
            this.currentRandomKey = EXAMPLE_KEYS[RANDOM.nextInt(EXAMPLE_KEYS.length)];
            this.lastKeyChange = currentTime;
        }
    }

    @Override
    public void drawDraggable(class_332 context) {
        this.drawMode1(context);
    }

    private void drawMode1(class_332 context) {
        class_4587 matrix = context.method_51448();
        FontRenderer fontMedium = this.fontMedium;
        FontRenderer font = this.fontHeader;
        FontRenderer fontBind = this.fontBind;
        FontRenderer fontModule = this.fontModule;
        FontRenderer categoryIcon = this.categoryIcon;
        float posX = this.getX();
        float posY = this.getY();
        float maxElementsWidth = HudListPanel.headerMinWidth(font, "Keybinds");
        float headerHeight = 21.0f;
        float moduleHeight = 11.0f;
        boolean isFound = false;
        for (Module module : this.keysList) {
            if (!(module.getAnimation().getOutput().floatValue() > 0.0f)) continue;
            isFound = true;
            String bind = StringHelper.getBindName(module.getKey());
            float elementsWidth = HudListPanel.rowWidth(fontModule, module.getName(), 0.0f, fontBind, bind);
            if (!(elementsWidth > maxElementsWidth)) continue;
            maxElementsWidth = elementsWidth;
        }
        if (this.keysList.isEmpty() && PlayerInteractionHelper.isChat(Keybinds.mc.field_1755)) {
            isFound = true;
            float elementsWidth = HudListPanel.rowWidth(fontModule, "Module", 0.0f, fontBind, this.currentRandomKey);
            if (elementsWidth > maxElementsWidth) {
                maxElementsWidth = elementsWidth;
            }
        }
        float totalContentHeight = 0.0f;
        for (Module module : this.keysList) {
            float animValue = module.getAnimation().getOutput().floatValue();
            if (!(animValue > 0.0f)) continue;
            totalContentHeight += moduleHeight * animValue;
        }
        if (this.keysList.isEmpty() && PlayerInteractionHelper.isChat(Keybinds.mc.field_1755)) {
            totalContentHeight += moduleHeight;
        }
        float f = HudListPanel.panelHeight(totalContentHeight);
        float width = maxElementsWidth;
        Hud.getInstance();
        if (Hud.blur.isValue() && !KawaseBlur.INSTANCE.fbos.isEmpty()) {
            Render2D.rectangleWithMask(matrix.method_23760().method_23761(), posX, posY, width, f, 6.0f, 0, KawaseBlur.INSTANCE.fbos.getFirst().method_30277());
        }
        rectangle.render(ShapeProperties.create(matrix, posX, posY, width, f).round(6.0f).softness(2.5f).color(new Color(0, 0, 0, 210).getRGB()).build());
        float headerTextY = HudListPanel.headerTextY(font, posY, "Keybinds");
        font.drawString(matrix, "Keybinds", posX + 7.0f, headerTextY, ThemeManager.textColor.getColor());
        float repBoxW = fontBind.getStringWidth("R") + 6.0f;
        float bindCenterX = posX + width - 6.5f - repBoxW / 2.0f;
        BackgroundIcons.drawHudHeaderIcon(context, BackgroundIcons.KEYBINDS, font, "Keybinds", headerTextY, bindCenterX, ColorAssist.getHudColor());
        posY = HudListPanel.contentStartY(posY);
        if (this.keysList.isEmpty() && PlayerInteractionHelper.isChat(Keybinds.mc.field_1755)) {
            String bind = this.currentRandomKey;
            String moduleName = "Module";
            String icon = "b";
            matrix.method_22903();
            float centerX = posX + width / 2.0f;
            float centerY = posY + moduleHeight / 2.0f;
            matrix.method_46416(centerX, centerY, 0.0f);
            matrix.method_22905(1.0f, 1.0f, 1.0f);
            matrix.method_46416(-centerX, -centerY, 0.0f);
            categoryIcon.drawRainbowString(matrix, icon, posX + 7.0f, HudListPanel.alignedRowTextY(fontModule, categoryIcon, posY), ColorAssist.getHudColor(), ColorAssist.getHudColor2());
            rectangle.render(ShapeProperties.create(matrix, posX + 17.8f, HudListPanel.rowDotY(posY), 2.0, 2.0).round(1.0f).color(new Color(87, 87, 90, 255).getRGB()).build());
            float rowTextY = HudListPanel.rowIconAlignedTextY(fontModule, posY, 9.0f, moduleName);
            fontModule.drawString(matrix, moduleName, Math.round(posX + 22.0f), Math.round(rowTextY), ThemeManager.textColor.getColor());
            float bindWidth = fontBind.getStringWidth(bind);
            float bindBoxWidth = bindWidth + 6.0f;
            float bindBoxX = posX + width - 6.5f - bindBoxWidth;
            float bindBoxHeight = 8.0f;
            float bindBoxY = HudListPanel.rowBadgeY(posY, bindBoxHeight);
            rectangle.render(ShapeProperties.create(matrix, bindBoxX, bindBoxY, bindBoxWidth, bindBoxHeight).round(2.0f).thickness(1.0f).outlineColor(new Color(33, 33, 33, 255).getRGB()).color(new Color(0, 0, 0, 100).getRGB()).build());
            float bindTextX = bindBoxX + bindBoxWidth / 2.0f - bindWidth / 2.0f;
            fontBind.drawString(matrix, bind, Math.round(bindTextX), Math.round(HudListPanel.rowBadgeTextY(fontBind, posY, bindBoxHeight, bind)), ThemeManager.textColor.getColor());
            matrix.method_22909();
            posY += moduleHeight;
        } else {
            for (Module module : this.keysList) {
                float animValue = module.getAnimation().getOutput().floatValue();
                if (!(animValue > 0.0f)) continue;
                String bind = StringHelper.getBindName(module.getKey());
                String moduleName = module.getName();
                int alpha = (int)(255.0f * animValue);
                boolean isLightTheme = ThemeManager.BackgroundGui.getColor() == Color.WHITE.getRGB() || (ThemeManager.BackgroundGui.getColor() >> 16 & 0xFF) > 200;
                Color textColor = isLightTheme ? new Color(0, 0, 0, alpha) : new Color(255, 255, 255, alpha);
                matrix.method_22903();
                float centerX = posX + width / 2.0f;
                float centerY = posY + moduleHeight / 2.0f;
                matrix.method_46416(centerX, centerY, 0.0f);
                matrix.method_22905(animValue, animValue, 1.0f);
                matrix.method_46416(-centerX, -centerY, 0.0f);
                Color gradColor1 = new Color(ColorAssist.getHudColor() >> 16 & 0xFF, ColorAssist.getHudColor() >> 8 & 0xFF, ColorAssist.getHudColor() & 0xFF, alpha);
                Color gradColor2 = new Color(ColorAssist.getHudColor2() >> 16 & 0xFF, ColorAssist.getHudColor2() >> 8 & 0xFF, ColorAssist.getHudColor2() & 0xFF, alpha);
                this.drawKeybindCategoryIcon(context, matrix, module.getCategory(), posX + 7.0f, posY, gradColor1.getRGB(), gradColor2.getRGB(), alpha);
                rectangle.render(ShapeProperties.create(matrix, posX + 17.9f, HudListPanel.rowDotY(posY), 2.0, 2.0).round(4.0f).color(new Color(87, 87, 90, alpha).getRGB()).build());
                float shaderAlpha = (float)alpha / 255.0f;
                RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)class_3532.method_15363((float)shaderAlpha, (float)0.0f, (float)1.0f));
                float rowTextY = HudListPanel.rowIconAlignedTextY(fontModule, posY, 9.0f, moduleName);
                fontModule.drawString(matrix, moduleName, Math.round(posX + 20.0f), Math.round(rowTextY), textColor.getRGB());
                RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                float bindWidth = fontBind.getStringWidth(bind);
                float bindBoxWidth = bindWidth + 6.0f;
                float bindBoxX = posX + width - 6.5f - bindBoxWidth;
                float bindBoxHeight = 8.0f;
                float bindBoxY = HudListPanel.rowBadgeY(posY, bindBoxHeight);
                rectangle.render(ShapeProperties.create(matrix, bindBoxX, bindBoxY, bindBoxWidth, bindBoxHeight).round(2.0f).thickness(1.0f).outlineColor(new Color(33, 33, 33, alpha).getRGB()).color(new Color(0, 0, 0, (int)(100.0f * animValue)).getRGB()).build());
                float bindTextX = bindBoxX + bindBoxWidth / 2.0f - bindWidth / 2.0f;
                fontBind.drawString(matrix, bind, bindTextX, HudListPanel.rowBadgeTextY(fontBind, posY, bindBoxHeight, bind), textColor.getRGB());
                matrix.method_22909();
                posY += moduleHeight * animValue;
            }
        }
        this.setWidth((int)width);
        this.setHeight((int)f);
    }

    private void drawKeybindCategoryIcon(class_332 context, class_4587 matrix, ModuleCategory category, float x, float rowY, int color1, int color2, int alpha) {
        if (category == ModuleCategory.ESP) {
            float iconY = HudListPanel.rowIconY(rowY, 9.0f);
            long time = System.currentTimeMillis();
            float t = (float)(Math.sin((double)time / 500.0) + 1.0) / 2.0f;
            int iconColor = ColorAssist.interpolateColor(color1, color2, t);
            BackgroundIcons.draw(context, BackgroundIcons.ESP, x, iconY, 9.0f, iconColor);
            return;
        }
        this.categoryIcon.drawRainbowString(matrix, Keybinds.getCategoryIcon(category), x, HudListPanel.alignedRowTextY(this.fontModule, this.categoryIcon, rowY), color1, color2);
    }

    private static String getCategoryIcon(ModuleCategory category) {
        if (category == ModuleCategory.COMBAT) {
            return "b";
        }
        if (category == ModuleCategory.MOVEMENT) {
            return "d";
        }
        if (category == ModuleCategory.RENDER) {
            return "e";
        }
        if (category == ModuleCategory.ESP) {
            return "i";
        }
        if (category == ModuleCategory.PLAYER) {
            return "c";
        }
        if (category == ModuleCategory.MISC) {
            return "f";
        }
        return category.getReadableName().substring(0, 1);
    }

    private void drawMode2(class_332 context) {
        class_4587 matrix = context.method_51448();
        FontRenderer titleFont = Fonts.getSize(14, Fonts.Type.BOLD);
        FontRenderer moduleFont = Fonts.getSize(13, Fonts.Type.BOLD);
        FontRenderer keyFont = Fonts.getSize(13, Fonts.Type.BOLD);
        float x = this.getX();
        float y = this.getY();
        float panelWidth = 95.0f;
        float titleHeight = 24.0f;
        float playerHeight = 16.0f;
        float spacing = -3.5f;
        float titleSpacing = -1.5f;
        float statusPanelWidth = 16.0f;
        float mainPanelWidth = panelWidth - statusPanelWidth - 11.0f;
        boolean showExamples = this.keysList.isEmpty() && PlayerInteractionHelper.isChat(Keybinds.mc.field_1755);
        int moduleCount = showExamples ? 3 : this.keysList.size();
        float contentHeight = (float)moduleCount * (playerHeight + spacing) - (moduleCount > 0 ? spacing : 0.0f);
        float totalHeight = titleHeight + titleSpacing + 7.0f + contentHeight;
        this.setWidth((int)panelWidth);
        this.setHeight((int)totalHeight);
        rectangle.render(ShapeProperties.create(matrix, x, y, panelWidth, totalHeight).round(7.0f).color(ThemeManager.BackgroundGui.getColor()).build());
        rectangle.render(ShapeProperties.create(matrix, x + 4.5f, y + 4.5f, panelWidth - 9.0f, titleHeight - 9.0f).round(3.5f).color(ThemeManager.BackgroundSettings.getColor()).build());
        titleFont.drawString(matrix, "Binds", x + 10.0f, y + 10.0f, ThemeManager.textColor.getColor());
        Fonts.getSize(19, Fonts.Type.ICONS).drawString(matrix, "D", x + panelWidth - 19.0f, y + 9.5f, ThemeManager.textColor.getColor());
        float currentY = y + titleHeight + titleSpacing;
        rectangle.render(ShapeProperties.create(matrix, x + 4.5f, currentY, mainPanelWidth, contentHeight).round(3.5f).color(ThemeManager.BackgroundSettings.getColor()).build());
        float statusPanelX = x + 4.5f + mainPanelWidth + 2.5f;
        rectangle.render(ShapeProperties.create(matrix, statusPanelX, currentY, statusPanelWidth, contentHeight).round(3.5f).color(ThemeManager.BackgroundSettings.getColor()).build());
        if (showExamples) {
            String[] exampleModules = new String[]{"Aura", "Velocity", "Flight"};
            String[] exampleBinds = new String[]{"R", "V", "F"};
            for (int i = 0; i < 3; ++i) {
                this.drawModuleBind(matrix, currentY, x, statusPanelX, statusPanelWidth, playerHeight, moduleFont, keyFont, exampleModules[i], exampleBinds[i]);
                currentY += playerHeight + spacing;
            }
        } else {
            for (Module module : this.keysList) {
                String moduleName = module.getVisibleName();
                String keyName = StringHelper.getBindName(module.getKey());
                this.drawModuleBind(matrix, currentY, x, statusPanelX, statusPanelWidth, playerHeight, moduleFont, keyFont, moduleName, keyName);
                currentY += playerHeight + spacing;
            }
        }
    }

    private void drawModuleBind(class_4587 matrix, float currentY, float x, float statusPanelX, float statusPanelWidth, float playerHeight, FontRenderer moduleFont, FontRenderer keyFont, String moduleName, String keyName) {
        float barWidth = 1.3f;
        float barHeight = 7.0f;
        float barX = x + 4.5f + 2.8f;
        float barY = currentY + 5.5f;
        rectangle.render(ShapeProperties.create(matrix, barX, barY, barWidth, barHeight).round(0.9f).color(ColorAssist.getHudColor(), ColorAssist.getHudColor2(), ColorAssist.getHudColor(), ColorAssist.getHudColor2()).build());
        float nameX = barX + barWidth + 3.5f;
        float nameY = currentY + playerHeight - 9.0f;
        moduleFont.drawString(matrix, moduleName, nameX, nameY, ThemeManager.textColor.getColor());
        float keyTextWidth = keyFont.getStringWidth(keyName);
        float maxWidth = statusPanelWidth - 2.0f;
        if (keyTextWidth > maxWidth) {
            float scale = maxWidth / keyTextWidth;
            float keyX = statusPanelX + 1.0f;
            float keyY = currentY + playerHeight - 9.0f;
            matrix.method_22903();
            matrix.method_46416(keyX, keyY, 0.0f);
            matrix.method_22905(scale, scale, 1.0f);
            keyFont.drawString(matrix, keyName, 0.0, 0.0, ColorAssist.getHudColor());
            matrix.method_22909();
        } else {
            float keyX = statusPanelX + (statusPanelWidth - keyTextWidth) / 2.0f;
            float keyY = currentY + playerHeight - 9.0f;
            keyFont.drawString(matrix, keyName, keyX, keyY, ColorAssist.getHudColor());
        }
    }
}
