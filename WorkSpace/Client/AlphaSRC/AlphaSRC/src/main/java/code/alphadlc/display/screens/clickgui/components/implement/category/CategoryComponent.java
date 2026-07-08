/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  org.joml.Matrix4f
 */
package code.alphadlc.display.screens.clickgui.components.implement.category;

import code.alphadlc.AlphaDLC;
import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.InOutBack;
import code.alphadlc.display.screens.clickgui.MenuScreen;
import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.display.screens.clickgui.components.implement.module.ModuleComponent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.BackgroundIcons;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.scissor.ScissorAssist;
import code.alphadlc.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class CategoryComponent
extends AbstractComponent {
    private final List<ModuleComponent> moduleComponents = new ArrayList<ModuleComponent>();
    private static final Set<ModuleComponent> globalModuleComponents = new HashSet<ModuleComponent>();
    private final ModuleCategory category;
    private final Animation alphaAnimation = new InOutBack().setMs(300).setValue(1.0);
    private final Animation scaleAnimation = new InOutBack().setMs(300).setValue(1.0);
    private boolean initializedAnimations = false;
    private float scroll = 0.0f;
    private float smoothedScroll = 0.0f;

    private void initializeModules() {
        List<Module> modules = AlphaDLC.getInstance().getModuleRepository().modules();
        for (Module module : modules) {
            ModuleComponent newComponent;
            if (module.getCategory() != this.category || module.isHidden() || !globalModuleComponents.add(newComponent = new ModuleComponent(module))) continue;
            this.moduleComponents.add(newComponent);
        }
    }

    public void postInitialize() {
        if (!this.initializedAnimations) {
            if (MenuScreen.getInstance().getCategory().equals((Object)this.category)) {
                this.alphaAnimation.setDirection(Direction.FORWARDS);
                this.scaleAnimation.setDirection(Direction.FORWARDS);
                this.alphaAnimation.reset();
                this.scaleAnimation.reset();
                this.alphaAnimation.setMs(0);
                this.scaleAnimation.setMs(0);
            } else {
                this.alphaAnimation.setDirection(Direction.BACKWARDS);
                this.scaleAnimation.setDirection(Direction.BACKWARDS);
                this.alphaAnimation.reset();
                this.scaleAnimation.reset();
                this.alphaAnimation.setMs(0);
                this.scaleAnimation.setMs(0);
            }
            this.initializedAnimations = true;
        }
    }

    public CategoryComponent(ModuleCategory category) {
        this.category = category;
        this.initializeModules();
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        this.postInitialize();
        MenuScreen menuScreen = MenuScreen.getInstance();
        globalModuleComponents.clear();
        Matrix4f positionMatrix = context.method_51448().method_23760().method_23761();
        ScissorAssist scissorManager = AlphaDLC.getInstance().getScissorManager();
        this.drawCategoryTab(context, context.method_51448(), mouseX, mouseY);
        this.drawModulesVertical(context, mouseX, mouseY, delta, positionMatrix, scissorManager, menuScreen);
    }

    private void drawModulesVertical(class_332 context, int mouseX, int mouseY, float delta, Matrix4f positionMatrix, ScissorAssist scissorManager, MenuScreen menuScreen) {
        float totalModuleHeight = 0.0f;
        for (ModuleComponent component : this.moduleComponents) {
            if (!this.shouldRenderComponent(component)) continue;
            totalModuleHeight += (float)(component.getComponentHeight() + 5);
        }
        float offsetX = 35.0f;
        float offsetY = 14.0f;
        float visibleHeight = (float)menuScreen.height - offsetY;
        float categoryTop = menuScreen.y + 14 - 8;
        if (totalModuleHeight > visibleHeight) {
            float maxScroll = -(totalModuleHeight - visibleHeight);
            this.scroll = class_3532.method_15363((float)this.scroll, (float)maxScroll, (float)0.0f);
        } else {
            this.scroll = 0.0f;
        }
        this.smoothedScroll = Calculate.interpolateSmooth(8.0, this.smoothedScroll, this.scroll);
        scissorManager.push(positionMatrix, (float)menuScreen.x + offsetX - 75.0f, categoryTop, (float)menuScreen.width - offsetX + 150.0f, (float)menuScreen.height - categoryTop + 15.0f);
        float moduleX = menuScreen.x + 36;
        float moduleY = (float)(menuScreen.y + 25) + this.smoothedScroll;
        float clipTop = categoryTop;
        float clipBottom = clipTop + ((float)menuScreen.height - categoryTop + 15.0f);
        for (ModuleComponent component : this.moduleComponents) {
            if (!this.shouldRenderComponent(component)) continue;
            int componentHeight = component.getComponentHeight() + 5;
            float moduleBottom = moduleY + (float)componentHeight;
            if (moduleBottom < clipTop || moduleY > clipBottom) {
                moduleY += (float)componentHeight;
                continue;
            }
            component.x = moduleX;
            component.y = moduleY;
            component.width = 179.0f;
            if (component.y > (float)(menuScreen.y - componentHeight) && (float)(menuScreen.y + menuScreen.height + 15) > component.y) {
                component.render(context, mouseX, mouseY, delta);
            }
            moduleY += (float)componentHeight;
        }
        scissorManager.pop();
        if (totalModuleHeight > visibleHeight) {
            // empty if block
        }
    }

    private void renderScrollbar(class_332 context, MenuScreen menuScreen, float totalHeight, float visibleHeight, float offsetX, float offsetY) {
        float scrollbarWidth = 4.0f;
        float scrollbarX = (float)(menuScreen.x + menuScreen.width) - offsetX - scrollbarWidth + 50.0f;
        float scrollbarY = (float)menuScreen.y + offsetY + 12.0f;
        float scrollbarHeight = (float)menuScreen.height - offsetY * 2.0f;
        rectangle.render(ShapeProperties.create(context.method_51448(), scrollbarX, scrollbarY, scrollbarWidth, scrollbarHeight).round(2.0f).color(new Color(30, 30, 30, 100).getRGB()).build());
        float handleHeight = Math.max(20.0f, visibleHeight * (visibleHeight / totalHeight));
        float maxScroll = -(totalHeight - visibleHeight);
        float scrollRatio = maxScroll != 0.0f ? -this.smoothedScroll / -maxScroll : 0.0f;
        float handleY = scrollbarY + (scrollbarHeight - handleHeight) * scrollRatio;
        rectangle.render(ShapeProperties.create(context.method_51448(), scrollbarX, handleY, scrollbarWidth, handleHeight).round(2.0f).color(new Color(100, 100, 100, 150).getRGB()).build());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        float hoverY;
        MenuScreen menuScreen = MenuScreen.getInstance();
        float scale = 0.5f + this.scaleAnimation.getOutput().floatValue() * 0.5f;
        float baseWidth = 20.0f;
        float baseHeight = 20.0f;
        float scaledWidth = baseWidth * scale;
        float scaledHeight = baseHeight * scale;
        float baseX = ModuleCategory.RENDER.equals((Object)this.category) ? this.x + 4.65f : (ModuleCategory.MOVEMENT.equals((Object)this.category) ? this.x + 4.75f : this.x + 5.25f);
        float baseY = this.y;
        float centerX = baseX + baseWidth / 2.0f;
        float centerY = baseY + baseHeight / 2.0f;
        float scaledX = centerX - scaledWidth / 2.0f;
        float scaledY = centerY - scaledHeight / 2.0f;
        float hoverX = ModuleCategory.RENDER.equals((Object)this.category) ? this.x + 4.65f : (ModuleCategory.MOVEMENT.equals((Object)this.category) ? this.x + 4.75f : this.x + 5.25f);
        if (Calculate.isHovered(mouseX, mouseY, hoverX, hoverY = this.y, baseWidth, baseHeight) && button == 0) {
            MenuScreen.getInstance().setCategory(this.category);
            this.alphaAnimation.setMs(300);
            this.scaleAnimation.setMs(300);
            this.alphaAnimation.setDirection(Direction.FORWARDS);
            this.scaleAnimation.setDirection(Direction.FORWARDS);
            return true;
        }
        float offsetX = 84.0f;
        float offsetY = 29.0f;
        if (Calculate.isHovered(mouseX, mouseY, (float)menuScreen.x + offsetX, (float)menuScreen.y + offsetY, (float)menuScreen.width - offsetX, (float)menuScreen.height - offsetY)) {
            for (ModuleComponent moduleComponent : this.moduleComponents) {
                if (!this.shouldRenderComponent(moduleComponent) || !moduleComponent.isHover(mouseX, mouseY)) continue;
                moduleComponent.mouseClicked(mouseX, mouseY, button);
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        float baseY;
        float centerY;
        float scaledY;
        float scale = 0.5f + this.scaleAnimation.getOutput().floatValue() * 0.5f;
        float baseWidth = 20.0f;
        float baseHeight = 20.0f;
        float scaledWidth = baseWidth * scale;
        float scaledHeight = baseHeight * scale;
        float baseX = ModuleCategory.RENDER.equals((Object)this.category) ? this.x + 4.65f : (ModuleCategory.MOVEMENT.equals((Object)this.category) ? this.x + 4.75f : this.x + 5.25f);
        float centerX = baseX + baseWidth / 2.0f;
        float scaledX = centerX - scaledWidth / 2.0f;
        boolean isHovered = Calculate.isHovered(mouseX, mouseY, scaledX, scaledY = (centerY = (baseY = this.y) + baseHeight / 2.0f) - scaledHeight / 2.0f, scaledWidth, scaledHeight);
        if (isHovered) {
            return true;
        }
        this.moduleComponents.forEach(moduleComponent -> moduleComponent.isHover(mouseX, mouseY));
        for (ModuleComponent moduleComponent2 : this.moduleComponents) {
            if (!moduleComponent2.isHover(mouseX, mouseY)) continue;
            return true;
        }
        return super.isHover(mouseX, mouseY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.moduleComponents.forEach(moduleComponent -> moduleComponent.mouseReleased(mouseX, mouseY, button));
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        MenuScreen menuScreen = MenuScreen.getInstance();
        float offsetX = 35.0f;
        float offsetY = 13.0f;
        if (Calculate.isHovered(mouseX, mouseY, (float)menuScreen.x + offsetX, (float)menuScreen.y + offsetY, (float)menuScreen.width - offsetX + 7.0f, (float)menuScreen.height - offsetY + 15.0f)) {
            this.scroll = (float)((double)this.scroll + amount * 20.0);
        }
        this.moduleComponents.forEach(moduleComponent -> {
            if (this.shouldRenderComponent((ModuleComponent)moduleComponent)) {
                moduleComponent.mouseScrolled(mouseX, mouseY, amount);
            }
        });
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        this.moduleComponents.forEach(moduleComponent -> {
            if (this.shouldRenderComponent((ModuleComponent)moduleComponent)) {
                moduleComponent.keyPressed(keyCode, scanCode, modifiers);
            }
        });
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        this.moduleComponents.forEach(moduleComponent -> {
            if (this.shouldRenderComponent((ModuleComponent)moduleComponent)) {
                moduleComponent.charTyped(chr, modifiers);
            }
        });
        return super.charTyped(chr, modifiers);
    }

    private void drawCategoryTab(class_332 context, class_4587 matrix, int mouseX, int mouseY) {
        this.alphaAnimation.setDirection(MenuScreen.getInstance().getCategory().equals((Object)this.category) ? Direction.FORWARDS : Direction.BACKWARDS);
        this.scaleAnimation.setDirection(MenuScreen.getInstance().getCategory().equals((Object)this.category) ? Direction.FORWARDS : Direction.BACKWARDS);
        float anim = this.alphaAnimation.getOutput().floatValue();
        float scale = 0.5f + this.scaleAnimation.getOutput().floatValue() * 0.5f;
        int alpha = class_3532.method_15340((int)((int)(anim * 135.0f)), (int)0, (int)135);
        float baseWidth = 180.0f;
        float baseHeight = 20.0f;
        float scaledWidth = baseWidth * scale;
        float scaledHeight = baseHeight * scale;
        MenuScreen menuScreen = MenuScreen.getInstance();
        float moduleCenterX = (float)(menuScreen.x + 36) + 89.5f;
        float moduleCenterY = menuScreen.y + 14;
        float baseX = moduleCenterX - baseWidth / 2.0f;
        float baseY = moduleCenterY - baseHeight / 2.0f;
        float centerX = baseX + baseWidth / 2.0f;
        float centerY = baseY + baseHeight / 2.0f;
        float scaledX = centerX - scaledWidth / 2.0f;
        float scaledY = centerY - scaledHeight / 2.0f;
        float hoverX = baseX;
        float hoverY = baseY;
        if (!MenuScreen.getInstance().getCategory().equals((Object)this.category)) {
            Color glowColor = new Color(ColorAssist.getClientColor());
            rectangle.render(ShapeProperties.create(matrix, hoverX, hoverY, baseWidth, baseHeight).round(4.0f).color(ColorAssist.getClientColor(), ColorAssist.getClientColor2(), ColorAssist.getClientColor2(), ColorAssist.getClientColor()).build());
        }
        switch (this.category) {
            case COMBAT: {
                String icon = "b";
                break;
            }
            case MOVEMENT: {
                String icon = "c";
                break;
            }
            case RENDER: {
                String icon = "d";
                break;
            }
            case ESP: {
                String icon = "g";
                break;
            }
            case PLAYER: {
                String icon = "e";
                break;
            }
            case MISC: {
                String icon = "f";
                break;
            }
            case CONFIGS: {
                String icon = "h";
                break;
            }
            case CUSTOMIZATION: {
                String icon = "i";
                break;
            }
            default: {
                String icon = this.category.getReadableName().substring(0, 1);
            }
        }
        float iconX = centerX;
        float iconY = centerY;
        if (ModuleCategory.COMBAT.equals((Object)this.category)) {
            Fonts.getSize(21, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "A", iconX, iconY, ColorAssist.getText(0.7f));
        }
        if (ModuleCategory.MOVEMENT.equals((Object)this.category)) {
            Fonts.getSize(23, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "B", iconX, iconY, ColorAssist.getText(0.7f));
        }
        if (ModuleCategory.RENDER.equals((Object)this.category)) {
            Fonts.getSize(21, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "C", iconX, iconY, ColorAssist.getText(0.7f));
        }
        if (ModuleCategory.PLAYER.equals((Object)this.category)) {
            Fonts.getSize(23, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "D", iconX, iconY, ColorAssist.getText(0.7f));
        }
        if (ModuleCategory.MISC.equals((Object)this.category)) {
            Fonts.getSize(21, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "E", iconX, iconY, ColorAssist.getText(0.7f));
        }
        if (ModuleCategory.ESP.equals((Object)this.category)) {
            int iconColor = ColorAssist.getText(0.7f);
            float iconDrawX = centerX - 4.5f;
            float iconDrawY = centerY - 4.5f + 2.0f;
            BackgroundIcons.drawEspCategory(context, BackgroundIcons.ESP, iconDrawX, iconDrawY, iconColor);
        }
        if (ModuleCategory.CUSTOMIZATION.equals((Object)this.category)) {
            Fonts.getSize(21, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "H", iconX, iconY, ColorAssist.getText(0.7f));
        }
    }

    private boolean shouldRenderComponent(ModuleComponent component) {
        return component.getModule().getCategory().equals((Object)this.category);
    }
}
