/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  org.joml.Matrix4f
 */
package code.alphadlc.display.screens.clickgui.components.implement.panel;

import code.alphadlc.AlphaDLC;
import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.display.screens.clickgui.components.implement.customization.CustomizationComponent;
import code.alphadlc.display.screens.clickgui.components.implement.module.ModuleComponent;
import code.alphadlc.features.impl.render.ClickGui;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.scissor.ScissorAssist;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class PanelComponent
extends AbstractComponent
implements QuickImports {
    private final List<ModuleComponent> moduleComponents = new ArrayList<ModuleComponent>();
    private final ModuleCategory category;
    private final int index;
    private final CustomizationComponent customizationComponent;
    private static final Logger logger = Logger.getLogger(PanelComponent.class.getName());
    private float scroll = 0.0f;
    private float smoothedScroll = 0.0f;
    private boolean searching = false;
    private String searchText = "";

    public PanelComponent(ModuleCategory category, int index) {
        this.category = category;
        this.index = index;
        this.customizationComponent = new CustomizationComponent();
        this.initializeModules();
    }

    private boolean shouldRenderCustomization() {
        try {
            ClickGui clickGui = ClickGui.getInstance();
            return clickGui != null && clickGui.shouldRenderCustomization();
        }
        catch (Exception ignored) {
            return true;
        }
    }

    private void initializeModules() {
        List<Module> modules = AlphaDLC.getInstance().getModuleRepository().modules();
        for (Module module : modules) {
            if (module.getCategory() != this.category) continue;
            this.moduleComponents.add(new ModuleComponent(module));
        }
    }

    public List<ModuleComponent> getModuleComponents() {
        return this.moduleComponents;
    }

    public boolean isModuleVisible(ModuleComponent component) {
        float moduleStartY;
        if (component == null) {
            return false;
        }
        float categoryTop = this.y + 35.0f - 8.0f;
        float categoryBottom = this.y + 35.0f + 22.0f;
        float clipTop = moduleStartY = categoryBottom + 4.0f;
        float clipBottom = this.y + this.height;
        float componentY = component.y;
        float componentHeight = component.getComponentHeight();
        float componentBottom = componentY + componentHeight;
        return componentBottom >= clipTop && componentY <= clipBottom;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        if (this.category == ModuleCategory.CUSTOMIZATION && !this.shouldRenderCustomization()) {
            return;
        }
        class_4587 matrix = context.method_51448();
        if (ThemeManager.isLiquidGlassTheme()) {
            this.blur(context, matrix, mouseX, mouseY);
        }
        this.drawPanelMainBackground(context, matrix, mouseX, mouseY);
        this.drawModulesVertical(context, matrix, mouseX, mouseY, delta);
        this.drawCategoryBackground(context, matrix, mouseX, mouseY);
        rectangle.render(ShapeProperties.create(matrix, this.x - 6.0f, this.y + 22.0f, this.width + 12.0f, 30.0).round(15.0f).color(255, 255, 255, 20).build());
        rectangle.render(ShapeProperties.create(matrix, this.x - 4.0f, this.y + 24.0f, this.width + 8.0f, 26.0).round(12.0f).color(255, 255, 255, 40).build());
        rectangle.render(ShapeProperties.create(matrix, this.x - 2.0f, this.y + 26.0f, this.width + 4.0f, 22.0).round(8.0f).color(255, 255, 255, 60).build());
        rectangle.render(ShapeProperties.create(matrix, this.x + 2.0f, this.y + 28.0f, this.width - 13.0f, 18.0).round(6.0f).color(255, 255, 255, 30).build());
    }

    private void blur(class_332 context, class_4587 matrix, int mouseX, int mouseY) {
        Render2D.rectangleWithMask(matrix.method_23760().method_23761(), this.x - 2.0f, this.y + 31.0f, this.width + 4.0f, this.height - 27.0f, 6.0f, 0, KawaseBlur.INSTANCE.getBlurTexture());
    }

    private void drawPanelMainBackground(class_332 context, class_4587 matrix, int mouseX, int mouseY) {
        rectangle.render(ShapeProperties.create(matrix, this.x - 2.0f, this.y + 31.0f, this.width + 4.0f, this.height - 27.0f).round(6.0f).color(ThemeManager.BackgroundGui.getColor()).build());
    }

    private void drawCategoryBackground(class_332 context, class_4587 matrix, int mouseX, int mouseY) {
        float moduleWidth = this.width - 4.0f;
        float categoryWidth = moduleWidth + 3.0f;
        float categoryX = this.x + 3.0f - 2.5f;
        float categoryY = this.y + 34.0f;
        rectangle.render(ShapeProperties.create(matrix, categoryX, categoryY, categoryWidth, 22.0).round(4.0f).softness(4.0f).color(ColorAssist.getClientColor(1.0f), ColorAssist.getClientColor(1.0f), ColorAssist.getClientColor2(1.0f), ColorAssist.getClientColor2(1.0f)).build());
        rectangle.render(ShapeProperties.create(matrix, categoryX - 4.0f, categoryY - 4.0f, categoryWidth + 8.0f, 30.0).round(8.0f).softness(4.0f).color(new Color(255, 255, 255, 5).getRGB()).build());
        rectangle.render(ShapeProperties.create(matrix, categoryX - 3.0f, categoryY - 3.0f, categoryWidth + 6.0f, 28.0).round(7.0f).softness(3.0f).color(ColorAssist.getClientColor(0.1f), ColorAssist.getClientColor(0.1f), ColorAssist.getClientColor2(0.1f), ColorAssist.getClientColor2(0.1f)).build());
        rectangle.render(ShapeProperties.create(matrix, categoryX - 2.0f, categoryY - 2.0f, categoryWidth + 4.0f, 26.0).round(6.0f).softness(2.0f).color(ColorAssist.getClientColor(0.1f), ColorAssist.getClientColor(0.1f), ColorAssist.getClientColor2(0.1f), ColorAssist.getClientColor2(0.1f)).build());
        String icon = this.getCategoryIcon();
        boolean isRenderOrMisc = this.category == ModuleCategory.RENDER || this.category == ModuleCategory.MISC;
        int iconFontSize = isRenderOrMisc ? 80 : 18;
        float scale = isRenderOrMisc ? 0.23684211f : 1.0f;
        float iconSize = Fonts.getSize(iconFontSize, Fonts.Type.ALPHADLC_ICONS).getStringWidth(icon) * scale;
        float nameWidth = Fonts.getSize(18, Fonts.Type.SuisseIntlSemiBold).getStringWidth(this.category.getReadableName());
        float categoryCenterX = categoryX + categoryWidth / 2.0f;
        float categoryCenterY = categoryY + 11.0f;
        float totalTextWidth = nameWidth + iconSize + 5.0f;
        float textStartX = categoryCenterX - totalTextWidth / 2.0f;
        float iconY = categoryCenterY - 1.0f;
        if (isRenderOrMisc) {
            iconY = categoryCenterY - 3.5f;
        }
        if (isRenderOrMisc) {
            matrix.method_22903();
            matrix.method_46416(textStartX, iconY, 0.0f);
            matrix.method_22905(scale, scale, 1.0f);
            Fonts.getSize(iconFontSize, Fonts.Type.ALPHADLC_ICONS).drawString(matrix, icon, 0.0, 0.0, ThemeManager.textColor.getColor());
            matrix.method_22909();
        } else {
            Fonts.getSize(iconFontSize, Fonts.Type.ALPHADLC_ICONS).drawString(matrix, icon, textStartX, iconY, ThemeManager.textColor.getColor());
        }
        FontRenderer fontCategory = Fonts.getSize(18, Fonts.Type.SuisseIntlMedium);
        fontCategory.drawString(matrix, this.category.getReadableName(), textStartX + iconSize + 5.0f, categoryCenterY - 1.5f, ThemeManager.textColor.getColor());
    }

    private void drawModulesVertical(class_332 context, class_4587 matrix, int mouseX, int mouseY, float delta) {
        float totalModuleHeight = 0.0f;
        if (this.category == ModuleCategory.CUSTOMIZATION && this.shouldRenderCustomization()) {
            totalModuleHeight += this.customizationComponent.height + 2.0f;
        }
        for (ModuleComponent component : this.moduleComponents) {
            if (!this.shouldRenderModule(component)) continue;
            totalModuleHeight += (float)(component.getComponentHeight() + 2);
        }
        float categoryTop = this.y + 35.0f - 8.0f;
        float categoryBottom = this.y + 35.0f + 22.0f;
        float moduleStartY = categoryBottom + 4.0f;
        float visibleHeight = this.y + this.height - moduleStartY;
        if (totalModuleHeight > visibleHeight) {
            float maxScroll = -(totalModuleHeight - visibleHeight);
            this.scroll = class_3532.method_15363((float)this.scroll, (float)maxScroll, (float)0.0f);
        } else {
            this.scroll = 0.0f;
        }
        this.smoothedScroll = Calculate.interpolateSmooth(2.0, this.smoothedScroll, this.scroll);
        Matrix4f positionMatrix = matrix.method_23760().method_23761();
        ScissorAssist scissorManager = AlphaDLC.getInstance().getScissorManager();
        float clipTop = moduleStartY;
        float clipBottom = this.y + this.height;
        scissorManager.push(positionMatrix, this.x, moduleStartY, this.width, clipBottom - moduleStartY);
        float moduleX = this.x + 1.5f;
        float moduleY = moduleStartY + this.smoothedScroll;
        if (this.category == ModuleCategory.CUSTOMIZATION && this.shouldRenderCustomization()) {
            float customizationHeight = this.customizationComponent.height + 2.0f;
            float customizationBottom = moduleY + this.customizationComponent.height;
            if (customizationBottom >= clipTop && moduleY <= clipBottom) {
                this.customizationComponent.x = moduleX;
                this.customizationComponent.y = moduleY;
                this.customizationComponent.width = this.width - 3.0f;
                this.customizationComponent.render(context, mouseX, mouseY, delta);
            }
            moduleY += customizationHeight;
        }
        for (ModuleComponent component : this.moduleComponents) {
            if (!this.shouldRenderModule(component)) continue;
            component.x = moduleX;
            component.y = moduleY;
            component.width = this.width - 3.0f;
            float componentBottom = moduleY + (float)component.getComponentHeight();
            if (componentBottom >= clipTop && moduleY <= clipBottom) {
                int heightBefore = component.getComponentHeight();
                component.render(context, mouseX, mouseY, delta);
                int n = component.getComponentHeight();
            }
            float settingsBottomY = component.getSettingsBottomY();
            moduleY = settingsBottomY + 2.0f;
        }
        scissorManager.pop();
        if (totalModuleHeight > visibleHeight) {
            // empty if block
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
            ArrayList<ModuleComponent> visibleModules = new ArrayList<ModuleComponent>();
            float categoryTop = this.y + 35.0f - 8.0f;
            float categoryBottom = this.y + 35.0f + 22.0f;
            float moduleStartY = categoryBottom + 4.0f;
            float moduleY = moduleStartY + this.smoothedScroll;
            float clipTop = moduleStartY;
            float clipBottom = this.y + this.height;
            if (this.category == ModuleCategory.CUSTOMIZATION && this.shouldRenderCustomization()) {
                int customizationHeight = (int)(this.customizationComponent.height + 2.0f);
                float customizationBottom = moduleY + (float)customizationHeight;
                if (customizationBottom >= clipTop && moduleY <= clipBottom) {
                    this.customizationComponent.x = this.x + 1.5f;
                    this.customizationComponent.y = moduleY;
                    this.customizationComponent.width = this.width - 3.0f;
                    if (this.customizationComponent.mouseClicked(mouseX, mouseY, button)) {
                        return true;
                    }
                }
                moduleY += (float)customizationHeight;
            }
            for (ModuleComponent component : this.moduleComponents) {
                if (!this.shouldRenderModule(component)) continue;
                component.x = this.x + 1.5f;
                component.y = moduleY;
                component.width = this.width - 3.0f;
                float moduleBottom = moduleY + (float)component.getComponentHeight();
                if (moduleBottom >= clipTop && moduleY <= clipBottom) {
                    visibleModules.add(component);
                }
                float settingsBottomY = component.getSettingsBottomY();
                moduleY = settingsBottomY + 2.0f;
            }
            for (int i = visibleModules.size() - 1; i >= 0; --i) {
                ModuleComponent moduleComponent = (ModuleComponent)visibleModules.get(i);
                if (!moduleComponent.mouseClicked(mouseX, mouseY, button)) continue;
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
            ArrayList<ModuleComponent> visibleModules = new ArrayList<ModuleComponent>();
            float categoryTop = this.y + 35.0f - 8.0f;
            float categoryBottom = this.y + 35.0f + 22.0f;
            float moduleStartY = categoryBottom + 4.0f;
            float moduleY = moduleStartY + this.smoothedScroll;
            float clipTop = moduleStartY;
            float clipBottom = this.y + this.height;
            if (this.category == ModuleCategory.CUSTOMIZATION && this.shouldRenderCustomization()) {
                int customizationHeight = (int)(this.customizationComponent.height + 2.0f);
                float customizationBottom = moduleY + (float)customizationHeight;
                if (customizationBottom >= clipTop && moduleY <= clipBottom) {
                    this.customizationComponent.x = this.x + 1.5f;
                    this.customizationComponent.y = moduleY;
                    this.customizationComponent.width = this.width - 3.0f;
                    if (this.customizationComponent.mouseReleased(mouseX, mouseY, button)) {
                        return true;
                    }
                }
                moduleY += (float)customizationHeight;
            }
            for (ModuleComponent component : this.moduleComponents) {
                if (!this.shouldRenderModule(component)) continue;
                component.x = this.x + 1.5f;
                component.y = moduleY;
                component.width = this.width - 3.0f;
                float moduleBottom = moduleY + (float)component.getComponentHeight();
                if (moduleBottom >= clipTop && moduleY <= clipBottom) {
                    visibleModules.add(component);
                }
                float settingsBottomY = component.getSettingsBottomY();
                moduleY = settingsBottomY + 2.0f;
            }
            for (int i = visibleModules.size() - 1; i >= 0; --i) {
                ModuleComponent moduleComponent = (ModuleComponent)visibleModules.get(i);
                if (!moduleComponent.mouseReleased(mouseX, mouseY, button)) continue;
                return true;
            }
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y + 56.0f, this.width, this.height - 52.0f)) {
            int scrollSpeed = (int)ClickGui.scrollSpeed.getValue();
            float baseModuleHeight = 22.0f;
            float scrollStep = baseModuleHeight * (float)scrollSpeed;
            this.scroll += amount > 0.0 ? scrollStep : -scrollStep;
        }
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
            ArrayList<ModuleComponent> visibleModules = new ArrayList<ModuleComponent>();
            float categoryTop = this.y + 35.0f - 8.0f;
            float categoryBottom = this.y + 35.0f + 22.0f;
            float moduleStartY = categoryBottom + 4.0f;
            float moduleY = moduleStartY + this.smoothedScroll;
            float clipTop = moduleStartY;
            float clipBottom = this.y + this.height;
            for (ModuleComponent component : this.moduleComponents) {
                if (!this.shouldRenderModule(component)) continue;
                component.x = this.x + 1.5f;
                component.y = moduleY;
                component.width = this.width - 3.0f;
                float moduleBottom = moduleY + (float)component.getComponentHeight();
                if (moduleBottom >= clipTop && moduleY <= clipBottom) {
                    visibleModules.add(component);
                }
                float settingsBottomY = component.getSettingsBottomY();
                moduleY = settingsBottomY + 2.0f;
            }
            for (int i = visibleModules.size() - 1; i >= 0; --i) {
                ModuleComponent moduleComponent = (ModuleComponent)visibleModules.get(i);
                if (!moduleComponent.mouseScrolled(mouseX, mouseY, amount)) continue;
                return true;
            }
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (!this.isPanelVisible()) {
            return false;
        }
        for (int i = this.moduleComponents.size() - 1; i >= 0; --i) {
            ModuleComponent moduleComponent = this.moduleComponents.get(i);
            if (!this.shouldRenderModule(moduleComponent) || !moduleComponent.keyPressed(keyCode, scanCode, modifiers)) continue;
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (!this.isPanelVisible()) {
            return false;
        }
        for (int i = this.moduleComponents.size() - 1; i >= 0; --i) {
            ModuleComponent moduleComponent = this.moduleComponents.get(i);
            if (!this.shouldRenderModule(moduleComponent) || !moduleComponent.charTyped(chr, modifiers)) continue;
            return true;
        }
        return super.charTyped(chr, modifiers);
    }

    private boolean shouldRenderModule(ModuleComponent component) {
        if (!this.searchText.isEmpty()) {
            boolean matches = component.getModule().getVisibleName().toLowerCase().contains(this.searchText.toLowerCase());
            if (matches) {
                // empty if block
            }
            return matches;
        }
        return true;
    }

    private boolean isPanelVisible() {
        boolean visible;
        float screenWidth = window.getScaledWidth();
        float screenHeight = window.getScaledHeight();
        boolean bl = visible = this.x < screenWidth && this.x + this.width > 0.0f && this.y < screenHeight && this.y + this.height > 0.0f;
        if (screenWidth < 320.0f || screenHeight < 240.0f) {
            return false;
        }
        return visible;
    }

    private String getCategoryIcon() {
        return switch (this.category) {
            default -> throw new MatchException(null, null);
            case ModuleCategory.COMBAT -> "b";
            case ModuleCategory.MOVEMENT -> "d";
            case ModuleCategory.RENDER -> "e";
            case ModuleCategory.ESP -> "i";
            case ModuleCategory.PLAYER -> "c";
            case ModuleCategory.MISC -> "f";
            case ModuleCategory.CUSTOMIZATION -> "g";
            case ModuleCategory.CONFIGS -> "h";
        };
    }

    public ModuleCategory getCategory() {
        return this.category;
    }

    public void setSearchText(String text) {
        this.searchText = text;
    }

    public int getIndex() {
        return this.index;
    }
}
