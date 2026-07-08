/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 */
package code.alphadlc.display.screens.clickgui.components.implement.panel;

import code.alphadlc.AlphaDLC;
import code.alphadlc.common.animation.Easy.Direction;
import code.alphadlc.common.animation.Easy.EaseBackIn;
import code.alphadlc.display.screens.clickgui.MenuScreen;
import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.display.screens.clickgui.components.implement.other.SearchComponent;
import code.alphadlc.display.screens.clickgui.components.implement.panel.PanelComponent;
import code.alphadlc.features.impl.render.ClickGui;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.theme.ThemeManager;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_332;

public class PanelManager
extends AbstractComponent {
    private final List<PanelComponent> panels = new ArrayList<PanelComponent>();
    private float basePanelWidth = 110.0f;
    private float basePanelHeight = 290.0f;
    private float baseGap = 10.0f;
    private float panelWidth;
    private float panelHeight;
    private float gap;
    private float guiOffsetX = 0.0f;
    private float guiOffsetY = -25.0f;
    private final SearchComponent searchComponent = new SearchComponent();
    private final List<PanelAnimation> panelAnimations = new ArrayList<PanelAnimation>();
    private boolean lastCustomizationVisible = true;

    public PanelManager() {
        this.initializePanels();
        this.searchComponent.setOnTextChanged(this::updateSearch);
        this.panelWidth = this.basePanelWidth;
        this.panelHeight = this.basePanelHeight;
        this.gap = this.baseGap;
        ClickGui clickGui = ClickGui.getInstance();
        if (clickGui != null) {
            this.lastCustomizationVisible = clickGui.shouldRenderCustomization();
        }
    }

    private void updatePanelDimensions() {
        this.panelWidth = this.basePanelWidth;
        this.panelHeight = this.basePanelHeight;
        this.gap = this.baseGap;
    }

    private void initializePanels() {
        this.panels.clear();
        int index = 0;
        for (ModuleCategory category : ModuleCategory.values()) {
            if (category == ModuleCategory.CONFIGS) continue;
            this.panels.add(new PanelComponent(category, index));
            ++index;
        }
    }

    @Override
    public void tick() {
        for (PanelComponent panel : this.panels) {
            panel.tick();
        }
        this.searchComponent.tick();
        this.updatePanelPositions();
        for (PanelAnimation animation : this.panelAnimations) {
            animation.panel.x = animation.getAnimatedX();
            animation.panel.y = animation.getAnimatedY();
        }
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        for (PanelAnimation animation : this.panelAnimations) {
            if (!animation.isVisible()) continue;
            animation.panel.render(context, mouseX, mouseY, delta);
        }
        float screenWidth = window.getScaledWidth();
        float screenHeight = window.getScaledHeight();
        this.searchComponent.x = (screenWidth - this.searchComponent.width) / 2.0f + this.guiOffsetX;
        this.searchComponent.y = (screenHeight - this.panelHeight) / 2.0f + this.panelHeight + 10.0f + this.guiOffsetY + 60.0f;
        float backgroundWidth = this.panelWidth + 4.0f;
        float backgroundHeight = this.searchComponent.height + 10.0f;
        float backgroundX = (screenWidth - backgroundWidth) / 2.0f + this.guiOffsetX;
        float backgroundY = this.searchComponent.y - 5.0f;
        if (ThemeManager.isLiquidGlassTheme()) {
            Render2D.rectangleWithMask(context.method_51448().method_23760().method_23761(), backgroundX, backgroundY, backgroundWidth, backgroundHeight, 10.0f, 0, KawaseBlur.INSTANCE.getBlurTexture());
        }
        rectangle.render(ShapeProperties.create(context.method_51448(), backgroundX, backgroundY, backgroundWidth, backgroundHeight).round(10.0f).color(ThemeManager.BackgroundGui.getColor()).build());
        String playerName = AlphaDLC.getInstance().getNativeUsername();
        String playerUUID = "[" + AlphaDLC.getInstance().getNativeUserIdentifier() + "]";
        String role = "[" + this.normalizeRole(AlphaDLC.getInstance().getNativeUserRole()) + "]";
        String displayText = playerName + playerUUID + " " + role;
        float textWidth = Fonts.getSize(14, Fonts.Type.SuisseIntlMedium).getStringWidth(displayText);
        float secondBackgroundWidth = Math.max(52.0f, textWidth + 16.0f);
        float secondBackgroundHeight = 19.0f;
        float secondBackgroundX = (screenWidth - secondBackgroundWidth) / 2.0f + this.guiOffsetX;
        float secondBackgroundY = backgroundY + (backgroundHeight - secondBackgroundHeight) / 2.0f;
        if (ThemeManager.isLiquidGlassTheme()) {
            Render2D.rectangleWithMask(context.method_51448().method_23760().method_23761(), secondBackgroundX, secondBackgroundY - 30.0f, secondBackgroundWidth - 3.0f, secondBackgroundHeight, 8.0f, 0, KawaseBlur.INSTANCE.getBlurTexture());
        }
        rectangle.render(ShapeProperties.create(context.method_51448(), secondBackgroundX, secondBackgroundY - 30.0f, secondBackgroundWidth - 3.0f, secondBackgroundHeight).round(8.0f).color(ThemeManager.BackgroundGui.getColor()).build());
        float textX = secondBackgroundX + (secondBackgroundWidth - textWidth) / 2.0f;
        float textY = secondBackgroundY - 30.0f + (secondBackgroundHeight - Fonts.getSize(14, Fonts.Type.SuisseIntlMedium).getStringHeight(displayText)) / 2.0f;
        float nameWidth = Fonts.getSize(14, Fonts.Type.SuisseIntlMedium).getStringWidth(playerName);
        Fonts.getSize(14, Fonts.Type.SuisseIntlMedium).drawString(context.method_51448(), playerName, textX - 2.0f, (double)textY + 6.5, ThemeManager.textColor.getColor());
        float uidX = textX + nameWidth;
        Fonts.getSize(14, Fonts.Type.SuisseIntlMedium).drawRainbowString(context.method_51448(), playerUUID, uidX, (double)textY + 6.5, ColorAssist.getClientColor(), ColorAssist.getClientColor2());
        float roleX = uidX + Fonts.getSize(14, Fonts.Type.SuisseIntlMedium).getStringWidth(playerUUID) + 4.0f;
        int roleColor = "FREE".equals(this.normalizeRole(AlphaDLC.getInstance().getNativeUserRole())) ? ColorAssist.getClientColor2() : ThemeManager.textColor.getColor();
        Fonts.getSize(14, Fonts.Type.SuisseIntlMedium).drawString(context.method_51448(), role, roleX, (double)textY + 6.5, roleColor);
        float squareSize = 20.0f;
        float squareX = backgroundX + backgroundWidth - squareSize - 5.0f;
        float squareY = backgroundY + (backgroundHeight - squareSize) / 2.0f;
        rectangle.render(ShapeProperties.create(context.method_51448(), squareX, squareY, squareSize, squareSize).round(4.0f).color(ColorAssist.getClientColor(), ColorAssist.getClientColor2(), ColorAssist.getClientColor(), ColorAssist.getClientColor2()).build());
        float zX = squareX + (squareSize - Fonts.getSize(16, Fonts.Type.ALPHADLC_ICONS).getStringWidth("z")) / 2.0f;
        float zY = squareY + (squareSize - Fonts.getSize(16, Fonts.Type.ALPHADLC_ICONS).getStringHeight("z")) / 2.0f;
        Fonts.getSize(16, Fonts.Type.ALPHADLC_ICONS).drawString(context.method_51448(), "z", zX, zY + 6.0f, ThemeManager.textColor.getColor());
        this.searchComponent.render(context, mouseX, mouseY, delta);
    }

    private void updatePanelPositions() {
        boolean shouldBeVisible;
        boolean visibilityChanged;
        float screenWidth = window.getScaledWidth();
        float screenHeight = window.getScaledHeight();
        ClickGui clickGui = ClickGui.getInstance();
        boolean currentCustomizationVisible = clickGui != null && clickGui.shouldRenderCustomization();
        boolean bl = visibilityChanged = currentCustomizationVisible != this.lastCustomizationVisible;
        if (visibilityChanged) {
            this.lastCustomizationVisible = currentCustomizationVisible;
        }
        ArrayList<PanelComponent> visiblePanels = new ArrayList<PanelComponent>();
        for (PanelComponent panel : this.panels) {
            if (!this.shouldShowPanel(panel)) continue;
            visiblePanels.add(panel);
        }
        int visiblePanelCount = visiblePanels.size();
        float totalWidth = (float)visiblePanelCount * this.panelWidth + (visiblePanelCount > 0 ? (float)(visiblePanelCount - 1) * this.gap : 0.0f);
        float startX = (screenWidth - totalWidth) / 2.0f + this.guiOffsetX;
        float startY = (screenHeight - this.panelHeight) / 2.0f + this.guiOffsetY;
        if (!visibilityChanged) {
            if (this.panelAnimations.size() != this.panels.size()) {
                visibilityChanged = true;
            } else {
                for (int i = 0; i < this.panels.size(); ++i) {
                    PanelComponent panelComponent = this.panels.get(i);
                    PanelAnimation animation = this.panelAnimations.get(i);
                    shouldBeVisible = this.shouldShowPanel(panelComponent);
                    if (animation.visible == shouldBeVisible) continue;
                    visibilityChanged = true;
                    break;
                }
            }
        }
        if (visibilityChanged || this.panelAnimations.isEmpty()) {
            this.panelAnimations.clear();
            visibleIndex = 0;
            for (PanelComponent panel : this.panels) {
                shouldBeVisible = this.shouldShowPanel(panel);
                targetX = shouldBeVisible ? startX + (float)visibleIndex * (this.panelWidth + this.gap) : panel.x;
                targetY = startY;
                if (this.panelAnimations.isEmpty()) {
                    panel.x = targetX;
                    panel.y = targetY;
                }
                PanelAnimation animation = new PanelAnimation(panel, targetX, targetY, shouldBeVisible);
                this.panelAnimations.add(animation);
                if (!shouldBeVisible) continue;
                ++visibleIndex;
            }
        } else {
            visibleIndex = 0;
            for (PanelAnimation animation : this.panelAnimations) {
                shouldBeVisible = this.shouldShowPanel(animation.panel);
                targetX = shouldBeVisible ? startX + (float)visibleIndex * (this.panelWidth + this.gap) : animation.targetX;
                targetY = startY;
                animation.updatePosition(targetX, targetY, shouldBeVisible);
                if (!shouldBeVisible) continue;
                ++visibleIndex;
            }
        }
        for (PanelComponent panelComponent : this.panels) {
            panelComponent.width = this.panelWidth;
            panelComponent.height = this.panelHeight;
        }
    }

    private boolean shouldShowPanel(PanelComponent panel) {
        try {
            ClickGui clickGui = ClickGui.getInstance();
            if (clickGui == null) {
                return true;
            }
            ModuleCategory category = panel.getCategory();
            if (category == ModuleCategory.CUSTOMIZATION) {
                return clickGui.shouldRenderCustomization();
            }
            return category != ModuleCategory.CONFIGS;
        }
        catch (Exception ignored) {
            return true;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (PanelAnimation animation : this.panelAnimations) {
            if (!animation.isVisible() || !animation.panel.mouseClicked(mouseX, mouseY, button)) continue;
            return true;
        }
        if (this.searchComponent.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (PanelAnimation animation : this.panelAnimations) {
            if (!animation.isVisible()) continue;
            animation.panel.mouseReleased(mouseX, mouseY, button);
        }
        this.searchComponent.mouseReleased(mouseX, mouseY, button);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        for (PanelAnimation animation : this.panelAnimations) {
            if (!animation.isVisible()) continue;
            animation.panel.mouseScrolled(mouseX, mouseY, amount);
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 70 && (modifiers & 2) != 0) {
            MenuScreen.activateClickGuiInput(this.searchComponent);
            return true;
        }
        for (PanelComponent panel : this.panels) {
            if (!panel.keyPressed(keyCode, scanCode, modifiers)) continue;
            return true;
        }
        float moveSpeed = 10.0f;
        switch (keyCode) {
            case 263: {
                this.guiOffsetX -= moveSpeed;
                return true;
            }
            case 262: {
                this.guiOffsetX += moveSpeed;
                return true;
            }
            case 265: {
                this.guiOffsetY -= moveSpeed;
                return true;
            }
            case 264: {
                this.guiOffsetY += moveSpeed;
                return true;
            }
        }
        if (this.searchComponent.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        for (PanelComponent panel : this.panels) {
            panel.charTyped(chr, modifiers);
        }
        if (this.searchComponent.charTyped(chr, modifiers)) {
            return true;
        }
        return super.charTyped(chr, modifiers);
    }

    public void setSearchText(String text) {
        for (PanelComponent panel : this.panels) {
            panel.setSearchText(text);
        }
        this.searchComponent.setText(text);
    }

    public void updateSearch() {
        String searchText = this.searchComponent.getText();
        for (PanelComponent panel : this.panels) {
            panel.setSearchText(searchText);
        }
    }

    public List<PanelComponent> getPanels() {
        return this.panels;
    }

    public void setPanelDimensions(float width, float height, float gap) {
        this.basePanelWidth = width;
        this.basePanelHeight = height;
        this.baseGap = gap;
        this.updatePanelDimensions();
    }

    public void forceUpdatePositions() {
        this.panelAnimations.clear();
        this.updatePanelPositions();
    }

    private String normalizeRole(String rawRole) {
        if (rawRole == null) {
            return "FREE";
        }
        String role = rawRole.toUpperCase();
        if (role.contains("BETA")) {
            return "FREE";
        }
        if (role.contains("FREE")) {
            return "FREE";
        }
        return "FREE";
    }

    private static class PanelAnimation {
        PanelComponent panel;
        float targetX;
        float targetY;
        float startX;
        float startY;
        EaseBackIn xAnimation;
        EaseBackIn yAnimation;
        boolean visible;
        boolean animating;

        PanelAnimation(PanelComponent panel, float targetX, float targetY, boolean visible) {
            this.panel = panel;
            this.targetX = targetX;
            this.targetY = targetY;
            this.startX = panel.x;
            this.startY = panel.y;
            this.visible = visible;
            this.animating = false;
            this.xAnimation = new EaseBackIn(200, 1.0, 1.5f);
            this.yAnimation = new EaseBackIn(200, 1.0, 1.5f);
        }

        void updatePosition(float newTargetX, float newTargetY, boolean newVisible) {
            boolean visibilityChanged;
            boolean positionChanged = Math.abs(this.targetX - newTargetX) > 0.1f || Math.abs(this.targetY - newTargetY) > 0.1f;
            boolean bl = visibilityChanged = this.visible != newVisible;
            if (positionChanged || visibilityChanged) {
                this.startX = this.panel.x;
                this.startY = this.panel.y;
                this.xAnimation.reset();
                this.yAnimation.reset();
                this.xAnimation.setDirection(Direction.FORWARDS);
                this.yAnimation.setDirection(Direction.FORWARDS);
                this.targetX = newTargetX;
                this.targetY = newTargetY;
                this.visible = newVisible;
                this.animating = true;
            }
        }

        float getAnimatedX() {
            if (!this.animating || this.xAnimation.isDone()) {
                this.animating = false;
                return this.targetX;
            }
            double progress = this.xAnimation.getOutput();
            return (float)((double)this.startX + (double)(this.targetX - this.startX) * progress);
        }

        float getAnimatedY() {
            if (!this.animating || this.yAnimation.isDone()) {
                return this.targetY;
            }
            double progress = this.yAnimation.getOutput();
            return (float)((double)this.startY + (double)(this.targetY - this.startY) * progress);
        }

        boolean isVisible() {
            return this.visible;
        }
    }
}
